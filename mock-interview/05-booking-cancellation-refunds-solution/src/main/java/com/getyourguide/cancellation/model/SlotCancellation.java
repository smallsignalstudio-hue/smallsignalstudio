package com.getyourguide.cancellation.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "slot_cancellation")
public class SlotCancellation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "idempotency_key")
    private String idempotencyKey;
    @Column(name = "booking_id")
    private Long bookingId;
    private String outcome;

    public static SlotCancellation of(String key, Long bookingId, String outcome) {
        SlotCancellation row = new SlotCancellation();
        row.idempotencyKey = key;
        row.bookingId = bookingId;
        row.outcome = outcome;
        return row;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public String getOutcome() {
        return outcome;
    }
}
