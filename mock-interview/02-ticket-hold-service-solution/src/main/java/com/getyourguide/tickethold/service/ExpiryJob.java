package com.getyourguide.tickethold.service;

import com.getyourguide.tickethold.repository.HoldRepository;
import com.getyourguide.tickethold.repository.SeatRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.LocalDateTime;

@Component
public class ExpiryJob {

    private final SeatRepository seatRepository;
    private final HoldRepository holdRepository;
    private final Clock clock;

    public ExpiryJob(SeatRepository seatRepository, HoldRepository holdRepository, Clock clock) {
        this.seatRepository = seatRepository;
        this.holdRepository = holdRepository;
        this.clock = clock;
    }

    @Scheduled(fixedDelayString = "${holds.expire-ms}")
    @Transactional
    public void expire() {
        holdRepository.markExpiredHolds(LocalDateTime.now(clock));
        seatRepository.releaseSeatsOfExpiredHolds();
    }
}
