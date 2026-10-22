package com.atolls.cashback.model;

import jakarta.persistence.*;

@Entity
@Table(name = "payout")
public class Payout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id") private long userId;
    @Column(name = "amount_cents") private int amountCents;
    @Column(name = "idempotency_key") private String idempotencyKey;

    public Long getId() { return id; }
    public long getUserId() { return userId; }
    public void setUserId(long userId) { this.userId = userId; }
    public int getAmountCents() { return amountCents; }
    public void setAmountCents(int amountCents) { this.amountCents = amountCents; }
    public String getIdempotencyKey() { return idempotencyKey; }
    public void setIdempotencyKey(String idempotencyKey) { this.idempotencyKey = idempotencyKey; }
}
