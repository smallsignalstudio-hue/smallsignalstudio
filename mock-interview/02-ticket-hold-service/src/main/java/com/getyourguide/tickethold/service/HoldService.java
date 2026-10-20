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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class HoldService {

    private final EventRepository eventRepository;
    private final HoldRepository holdRepository;
    private final SeatRepository seatRepository;
    private final int ttlMinutes;

    public HoldService(EventRepository eventRepository,
                       HoldRepository holdRepository,
                       SeatRepository seatRepository,
                       @Value("${holds.ttl-minutes}") int ttlMinutes) {
        this.eventRepository = eventRepository;
        this.holdRepository = holdRepository;
        this.seatRepository = seatRepository;
        this.ttlMinutes = ttlMinutes;
    }

    public HoldResponse createHold(long eventId, long customerId, CreateHoldRequest request) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new NotFoundException("Event " + eventId));
        if (request.seatIds().size() > event.getMaxSeatsPerOrder()) {
            throw new InvalidHoldException("At most " + event.getMaxSeatsPerOrder() + " seats per order");
        }
        Hold hold = new Hold();
        hold.setEventId(eventId);
        hold.setCustomerId(customerId);
        hold.setStatus(HoldStatus.HELD);
        hold.setCreatedAt(LocalDateTime.now());
        hold.setExpiresAt(LocalDateTime.now().plusMinutes(ttlMinutes));
        hold = holdRepository.saveAndFlush(hold);
        for (Long seatId : request.seatIds()) {
            reserveSeat(seatId, hold);
        }
        return toResponse(hold);
    }

    @Transactional
    public void reserveSeat(Long seatId, Hold hold) {
        Seat seat = seatRepository.findById(seatId).orElseThrow(() -> new NotFoundException("Seat " + seatId));
        if (seat.getStatus() != SeatStatus.AVAILABLE || !seat.getEventId().equals(hold.getEventId())) {
            throw new SeatNotAvailableException(seatId);
        }
        seat.setStatus(SeatStatus.HELD);
        seat.setHoldId(hold.getId());
        seatRepository.saveAndFlush(seat);
    }

    public HoldResponse confirm(long holdId, long customerId) {
        Hold hold = holdRepository.findById(holdId).orElseThrow(() -> new NotFoundException("Hold " + holdId));
        if (hold.getStatus() != HoldStatus.HELD) {
            throw new InvalidHoldException("Hold " + holdId + " is " + hold.getStatus());
        }
        if (hold.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new InvalidHoldException("Hold " + holdId + " has expired");
        }
        hold.setStatus(HoldStatus.CONFIRMED);
        hold.setConfirmedAt(LocalDateTime.now());
        holdRepository.save(hold);
        for (Seat seat : seatRepository.findByHoldId(holdId)) {
            seat.setStatus(SeatStatus.SOLD);
            seatRepository.save(seat);
        }
        return toResponse(hold);
    }

    public HoldResponse getHold(long holdId) {
        return toResponse(holdRepository.findById(holdId).orElseThrow(() -> new NotFoundException("Hold " + holdId)));
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
