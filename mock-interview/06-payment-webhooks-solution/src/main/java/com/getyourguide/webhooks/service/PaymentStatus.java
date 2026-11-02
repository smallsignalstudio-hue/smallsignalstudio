package com.getyourguide.webhooks.service;

public enum PaymentStatus {
    PENDING(0), AUTHORISED(1), CAPTURED(2), REFUNDED(3);

    private final int rank;

    PaymentStatus(int rank) {
        this.rank = rank;
    }

    public static PaymentStatus of(String stored) {
        return PaymentStatus.valueOf(stored);
    }

    public boolean canMoveTo(PaymentStatus next) {
        return next.rank > this.rank;
    }
}
