package com.getyourguide.availability.service;

import com.getyourguide.availability.dto.BookRequest;
import com.getyourguide.availability.dto.SlotDto;
import com.getyourguide.availability.model.Activity;
import com.getyourguide.availability.model.Booking;
import com.getyourguide.availability.repository.ActivityRepository;
import com.getyourguide.availability.repository.BookingRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AvailabilityService {

    private final ActivityRepository activities;
    private final BookingRepository bookings;
    private final Clock clock;

    public AvailabilityService(ActivityRepository activities, BookingRepository bookings, Clock clock) {
        this.activities = activities;
        this.bookings = bookings;
        this.clock = clock;
    }

    @Transactional(readOnly = true)
    @Cacheable(cacheNames = "slots", key = "#activityId + '-' + #from + '-' + #to")
    public List<SlotDto> slots(long activityId, LocalDate from, LocalDate to) {
        Activity activity = activities.findById(activityId).orElseThrow();
        List<SlotDto> slots = new ArrayList<>();
        for (ZonedDateTime departure : ScheduleExpander.departures(activity, from, to)) {
            Instant start = departure.toInstant();
            Instant end = start.plus(Duration.ofMinutes(activity.getDurationMinutes()));
            int remaining = remaining(activity, start, end);
            slots.add(new SlotDto(departure.toOffsetDateTime(), departure.plusMinutes(activity.getDurationMinutes()).toOffsetDateTime(), remaining));
        }
        return slots;
    }

    @Transactional
    @CacheEvict(cacheNames = "slots", allEntries = true)
    public Booking book(BookRequest request) {
        Activity activity = activities.findById(request.activityId()).orElseThrow();
        Instant start = request.slotStart();
        Instant cutoff = start.minus(Duration.ofHours(activity.getCutoffHours()));
        if (!clock.instant().isBefore(cutoff)) {
            throw new IllegalStateException("Past the booking cutoff");
        }
        Instant end = start.plus(Duration.ofMinutes(activity.getDurationMinutes()));
        if (request.partySize() > remaining(activity, start, end)) {
            throw new IllegalStateException("Not enough seats");
        }
        Booking booking = new Booking();
        booking.setActivityId(request.activityId());
        booking.setSlotStart(start);
        booking.setPartySize(request.partySize());
        booking.setStatus("CONFIRMED");
        return bookings.save(booking);
    }

    private int remaining(Activity activity, Instant start, Instant end) {
        long own = bookings.confirmedPartyAt(activity.getId(), start);
        int ownLeft = (int) (activity.getCapacity() - own);
        if (activity.getResourceId() == null) {
            return ownLeft;
        }
        long shared = bookings.resourceUsage(activity.getResourceId(), start, end);
        int poolLeft = bookings.poolCapacity(activity.getResourceId()) - (int) shared;
        return Math.min(ownLeft, poolLeft);
    }
}
