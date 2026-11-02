package com.getyourguide.webhooks.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "outbox")
public class OutboxMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "dedup_key")
    private String dedupKey;
    @Column(name = "booking_id")
    private Long bookingId;
    private int attempts;
    private String status;

    public static OutboxMessage pending(String dedupKey, Long bookingId) {
        OutboxMessage message = new OutboxMessage();
        message.dedupKey = dedupKey;
        message.bookingId = bookingId;
        message.attempts = 0;
        message.status = "PENDING";
        return message;
    }

    public void recordAttempt(boolean success) {
        attempts = attempts + 1;
        if (success) {
            status = "PUBLISHED";
        } else if (attempts >= 3) {
            status = "DLQ";
        } else {
            status = "PENDING";
        }
    }

    public Long getId() {
        return id;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public String getDedupKey() {
        return dedupKey;
    }

    public String getStatus() {
        return status;
    }

    public int getAttempts() {
        return attempts;
    }
}
