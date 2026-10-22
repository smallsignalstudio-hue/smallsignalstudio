package com.getyourguide.pricing.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "quote")
public class Quote {

    @Id
    private UUID id;
    @Column(name = "customer_id")
    private String customerId;
    private String currency;
    private String total;
    private String signature;
    @Column(name = "expires_at")
    private Instant expiresAt;
    @Column(name = "promo_code")
    private String promoCode;
    private boolean consumed;

    public static Quote create(UUID id, String customerId, String currency, String total, String signature,
                               Instant expiresAt, String promoCode) {
        Quote quote = new Quote();
        quote.id = id;
        quote.customerId = customerId;
        quote.currency = currency;
        quote.total = total;
        quote.signature = signature;
        quote.expiresAt = expiresAt;
        quote.promoCode = promoCode;
        return quote;
    }

    public UUID getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getCurrency() {
        return currency;
    }

    public String getTotal() {
        return total;
    }

    public String getSignature() {
        return signature;
    }

    public Instant getExpiresAt() {
        return expiresAt;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public boolean isConsumed() {
        return consumed;
    }

    public void consume() {
        this.consumed = true;
    }
}
