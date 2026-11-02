package com.getyourguide.tickethold.service;

import com.getyourguide.tickethold.dto.CreateHoldRequest;
import com.getyourguide.tickethold.dto.HoldResponse;
import com.getyourguide.tickethold.model.Event;
import com.getyourguide.tickethold.model.Hold;
import com.getyourguide.tickethold.model.HoldStatus;
import com.getyourguide.tickethold.model.Seat;
import com.getyourguide.tickethold.model.SeatStatus;
import com.getyourguide.tickethold.repository.EventRepository;
import com.getyourguide.tickethold.repository.HoldRepository;
import com.getyourguide.tickethold.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class HoldService {

    private final EventRepository eventRepository;
    private final HoldRepository holdRepository;
    private final SeatRepository seatRepository;
    private final Clock clock;
    private final int ttlMinutes;

    public HoldService(EventRepository eventRepository,
                       HoldRepository holdRepository,
                       SeatRepository seatRepository,
                       Clock clock,
                       @Value("${holds.ttl-minutes}") int ttlMinutes) {
        this.eventRepository = eventRepository;
        this.holdRepository = holdRepository;
        this.seatRepository = seatRepository;
        this.clock = clock;
        this.ttlMinutes = ttlMinutes;
    }

    @Transactional
    public HoldResponse createHold(long eventId, long customerId, CreateHoldRequest request) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new NotFoundException("Event " + eventId));
        List<Long> seatIds = request.seatIds().stream().distinct().sorted().toList();
        if (seatIds.size() != request.seatIds().size()) {
            throw new InvalidHoldException("Duplicate seat in request");
        }
        if (seatIds.size() > event.getMaxSeatsPerOrder()) {
            throw new InvalidHoldException("At most " + event.getMaxSeatsPerOrder() + " seats per order");
        }
        claim(eventId, seatIds, newHold(eventId, customerId));
        return toResponse(holdRepository.findById(lastHoldId(eventId, customerId)).orElseThrow());
    }

    @Transactional
    public HoldResponse bestAvailable(long eventId, long customerId, String section, int count) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new NotFoundException("Event " + eventId));
        if (count < 1 || count > event.getMaxSeatsPerOrder()) {
            throw new InvalidHoldException("Count must be between 1 and " + event.getMaxSeatsPerOrder());
        }
        List<Seat> available = seatRepository.lockAvailableInSection(eventId, section);
        List<Seat> chosen = BestSeatPicker.pick(available, count);
        if (chosen == null) {
            throw new SeatNotAvailableException(null);
        }
        Hold hold = newHold(eventId, customerId);
        hold = holdRepository.saveAndFlush(hold);
        for (Seat seat : chosen) {
            seat.setStatus(SeatStatus.HELD);
            seat.setHoldId(hold.getId());
        }
        return toResponse(hold);
    }

    @Transactional
    public HoldResponse confirm(long holdId, long customerId) {
        Hold hold = holdRepository.findById(holdId).orElseThrow(() -> new NotFoundException("Hold " + holdId));
        if (!hold.getCustomerId().equals(customerId)) {
            throw new ForbiddenException("Hold " + holdId + " belongs to another customer");
        }
        if (hold.getStatus() != HoldStatus.HELD) {
            throw new InvalidHoldException("Hold " + holdId + " is " + hold.getStatus());
        }
        if (!hold.getExpiresAt().isAfter(LocalDateTime.now(clock))) {
            throw new InvalidHoldException("Hold " + holdId + " has expired");
        }
        hold.setStatus(HoldStatus.CONFIRMED);
        hold.setConfirmedAt(LocalDateTime.now(clock));
        for (Seat seat : seatRepository.findByHoldId(holdId)) {
            seat.setStatus(SeatStatus.SOLD);
        }
        return toResponse(hold);
    }

    @Transactional(readOnly = true)
    public HoldResponse getHold(long holdId) {
        return toResponse(holdRepository.findById(holdId).orElseThrow(() -> new NotFoundException("Hold " + holdId)));
    }

    private void claim(long eventId, List<Long> seatIds, Hold draft) {
        List<Seat> seats = seatRepository.lockByIds(seatIds);
        if (seats.size() != seatIds.size()) {
            throw new NotFoundException("Unknown seat in " + seatIds);
        }
        for (Seat seat : seats) {
            if (seat.getStatus() != SeatStatus.AVAILABLE || seat.getEventId() != eventId) {
                throw new SeatNotAvailableException(seat.getId());
            }
        }
        Hold hold = holdRepository.saveAndFlush(draft);
        for (Seat seat : seats) {
            seat.setStatus(SeatStatus.HELD);
            seat.setHoldId(hold.getId());
        }
    }

    private Hold newHold(long eventId, long customerId) {
        Hold hold = new Hold();
        hold.setEventId(eventId);
        hold.setCustomerId(customerId);
        hold.setStatus(HoldStatus.HELD);
        LocalDateTime now = LocalDateTime.now(clock);
        hold.setCreatedAt(now);
        hold.setExpiresAt(now.plusMinutes(ttlMinutes));
        return hold;
    }

    private Long lastHoldId(long eventId, long customerId) {
        return holdRepository.findFirstByEventIdAndCustomerIdOrderByIdDesc(eventId, customerId)
                .map(Hold::getId)
                .orElseThrow();
    }

    private HoldResponse toResponse(Hold hold) {
        List<Long> seatIds = new ArrayList<>();
        for (Seat seat : seatRepository.findByHoldId(hold.getId())) {
            seatIds.add(seat.getId());
        }
        return new HoldResponse(hold.getId(), hold.getEventId(), hold.getCustomerId(),
                hold.getStatus().name(), seatIds, hold.getExpiresAt());
    }
}
