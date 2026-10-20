package com.getyourguide.tickethold.service;

public class InvalidHoldException extends RuntimeException {

    public InvalidHoldException(String message) {
        super(message);
    }
}
