package com.getyourguide.webhooks.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "booking")
public class Booking {

    @Id
    private Long id;
    private String status;
    private String currency;
    @Column(name = "amount_minor")
    private Long amountMinor;
    @Column(name = "charged_amount")
    private BigDecimal chargedAmount;

    public Long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getChargedAmount() {
        return chargedAmount;
    }

    public void setChargedAmount(BigDecimal chargedAmount) {
        this.chargedAmount = chargedAmount;
    }
}
