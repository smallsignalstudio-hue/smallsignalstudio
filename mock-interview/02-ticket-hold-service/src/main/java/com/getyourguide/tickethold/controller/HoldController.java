package com.getyourguide.tickethold.controller;

import com.getyourguide.tickethold.dto.CreateHoldRequest;
import com.getyourguide.tickethold.dto.HoldResponse;
import com.getyourguide.tickethold.dto.SeatDto;
import com.getyourguide.tickethold.model.Seat;
import com.getyourguide.tickethold.repository.SeatRepository;
import com.getyourguide.tickethold.service.ExpiryJob;
import com.getyourguide.tickethold.service.HoldService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HoldController {

    private final HoldService holdService;
    private final SeatRepository seatRepository;
    private final ExpiryJob expiryJob;

    public HoldController(HoldService holdService, SeatRepository seatRepository, ExpiryJob expiryJob) {
        this.holdService = holdService;
        this.seatRepository = seatRepository;
        this.expiryJob = expiryJob;
    }

    @GetMapping("/events/{eventId}/seats")
    public List<SeatDto> seats(@PathVariable long eventId) {
        return seatRepository.findByEventIdOrderBySectionAscSeatRowAscSeatNumberAsc(eventId).stream()
                .map(HoldController::toDto)
                .toList();
    }

    @PostMapping("/events/{eventId}/holds")
    @ResponseStatus(HttpStatus.CREATED)
    public HoldResponse create(@PathVariable long eventId,
                               @RequestHeader("X-Customer-Id") long customerId,
                               @Valid @RequestBody CreateHoldRequest request) {
        return holdService.createHold(eventId, customerId, request);
    }

    @PostMapping("/holds/{holdId}/confirm")
    public HoldResponse confirm(@PathVariable long holdId, @RequestHeader("X-Customer-Id") long customerId) {
        return holdService.confirm(holdId, customerId);
    }

    @GetMapping("/holds/{holdId}")
    public HoldResponse get(@PathVariable long holdId) {
        return holdService.getHold(holdId);
    }

    @PostMapping("/internal/expire")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void expire() {
        expiryJob.expire();
    }

    private static SeatDto toDto(Seat seat) {
        return new SeatDto(seat.getId(), seat.getSection(), seat.getSeatRow(), seat.getSeatNumber(),
                seat.getPriceCategory(), seat.getStatus().name(), seat.getHoldId());
    }
}
