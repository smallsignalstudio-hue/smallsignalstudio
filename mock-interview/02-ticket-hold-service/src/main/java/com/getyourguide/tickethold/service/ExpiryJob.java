package com.getyourguide.tickethold.service;

import com.getyourguide.tickethold.repository.HoldRepository;
import com.getyourguide.tickethold.repository.SeatRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ExpiryJob {

    private final SeatRepository seatRepository;
    private final HoldRepository holdRepository;

    public ExpiryJob(SeatRepository seatRepository, HoldRepository holdRepository) {
        this.seatRepository = seatRepository;
        this.holdRepository = holdRepository;
    }

    @Scheduled(fixedDelayString = "${holds.expire-ms}")
    @Transactional
    public void expire() {
        seatRepository.releaseSeatsOfExpiredHolds();
        holdRepository.markExpiredHolds();
    }
}
