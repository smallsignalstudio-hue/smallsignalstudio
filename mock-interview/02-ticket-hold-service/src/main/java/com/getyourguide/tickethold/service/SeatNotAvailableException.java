package com.getyourguide.tickethold.service;

public class SeatNotAvailableException extends RuntimeException {

    public SeatNotAvailableException(Long seatId) {
        super("Seat " + seatId + " is not available");
    }
}
