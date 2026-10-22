package com.getyourguide.cancellation.service;

public enum BookingStatus {
    CONFIRMED, CANCELLED, COMPLETED;

    public static BookingStatus of(String stored) {
        return BookingStatus.valueOf(stored);
    }

    public void requireCancellable(long bookingId) {
        if (this != CONFIRMED) {
            throw new ConflictException("Booking " + bookingId + " is " + this);
        }
    }
}
