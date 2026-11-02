package com.atolls.cashback.model;

import jakarta.persistence.*;

@Entity
@Table(name = "wallet")
public class Wallet {
    @Id
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "available_cents") private int availableCents;
    @Column(name = "pending_cents") private int pendingCents;

    public Wallet() {}

    public Wallet(long userId, int availableCents, int pendingCents) {
        this.userId = userId;
        this.availableCents = availableCents;
        this.pendingCents = pendingCents;
    }

    public Long getUserId() { return userId; }
    public int getAvailableCents() { return availableCents; }
    public void setAvailableCents(int availableCents) { this.availableCents = availableCents; }
    public int getPendingCents() { return pendingCents; }
    public void setPendingCents(int pendingCents) { this.pendingCents = pendingCents; }
}
