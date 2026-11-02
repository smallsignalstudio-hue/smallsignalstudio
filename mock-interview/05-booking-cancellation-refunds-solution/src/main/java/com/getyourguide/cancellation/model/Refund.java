package com.getyourguide.cancellation.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "refund")
public class Refund {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "booking_id")
    private Long bookingId;
    private BigDecimal amount;
    @Column(name = "idempotency_key")
    private String idempotencyKey;

    public static Refund of(Long bookingId, BigDecimal amount, String key) {
        Refund refund = new Refund();
        refund.bookingId = bookingId;
        refund.amount = amount;
        refund.idempotencyKey = key;
        return refund;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getIdempotencyKey() {
        return idempotencyKey;
    }
}
