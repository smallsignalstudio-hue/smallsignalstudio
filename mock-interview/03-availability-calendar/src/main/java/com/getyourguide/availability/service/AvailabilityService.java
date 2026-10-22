package com.getyourguide.availability.service;

import com.getyourguide.availability.dto.BookRequest;
import com.getyourguide.availability.dto.SlotDto;
import com.getyourguide.availability.model.Activity;
import com.getyourguide.availability.model.Booking;
import com.getyourguide.availability.repository.ActivityRepository;
import com.getyourguide.availability.repository.BookingRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
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

    @Cacheable(cacheNames = "slots", key = "#activityId")
    public List<SlotDto> slots(long activityId, LocalDate from, LocalDate to) {
        Activity activity = activities.findById(activityId).orElseThrow();
        ZoneId zone = ZoneId.systemDefault();
        ZonedDateTime cursor = ZonedDateTime.of(from, activity.getLocalTime(), ZoneId.of(activity.getZoneId()))
                .withZoneSameInstant(zone);
        ZonedDateTime end = to.atStartOfDay(zone);
        List<SlotDto> slots = new ArrayList<>();
        while (cursor.isBefore(end)) {
            Instant start = cursor.toInstant();
            ZonedDateTime inCity = start.atZone(ZoneId.of(activity.getZoneId()));
            ZonedDateTime cityEnd = inCity.plusMinutes(activity.getDurationMinutes());
            ZonedDateTime reported = ZonedDateTime.of(cityEnd.toLocalDate(), inCity.toLocalTime(), inCity.getZone());
            long used = bookings.partySizeAt(activityId, start);
            slots.add(new SlotDto(reported.toOffsetDateTime(),
                    reported.plusMinutes(activity.getDurationMinutes()).toOffsetDateTime(),
                    (int) (activity.getCapacity() - used)));
            cursor = start.plus(Duration.ofHours(24)).atZone(zone);
        }
        return slots;
    }

    public Booking book(BookRequest request) {
        Activity activity = activities.findById(request.activityId()).orElseThrow();
        LocalDateTime wall = LocalDateTime.ofInstant(request.slotStart(), ZoneId.of(activity.getZoneId()));
        ZonedDateTime start = wall.atZone(ZoneId.systemDefault());
        if (ZonedDateTime.now(clock).isAfter(start.minusHours(activity.getCutoffHours()))) {
            throw new IllegalStateException("Past the booking cutoff");
        }
        long used = bookings.partySizeAt(request.activityId(), request.slotStart());
        if (used + request.partySize() > activity.getCapacity()) {
            throw new IllegalStateException("Not enough seats");
        }
        Booking booking = new Booking();
        booking.setActivityId(request.activityId());
        booking.setSlotStart(request.slotStart());
        booking.setPartySize(request.partySize());
        booking.setStatus("CONFIRMED");
        return bookings.save(booking);
    }
}
