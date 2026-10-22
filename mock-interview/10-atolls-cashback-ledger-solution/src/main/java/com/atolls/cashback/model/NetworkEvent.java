package com.atolls.cashback.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "network_event")
public class NetworkEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "external_id") private String externalId;
    @Column(name = "user_id") private long userId;
    private String status;
    @Column(name = "amount_cents") private int amountCents;
    private String currency;
    @Column(name = "fx_rate") private BigDecimal fxRate;
    private boolean processed;
    @Column(name = "applied_cents") private int appliedCents;

    public Long getId() { return id; }
    public String getExternalId() { return externalId; }
    public void setExternalId(String externalId) { this.externalId = externalId; }
    public long getUserId() { return userId; }
    public void setUserId(long userId) { this.userId = userId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public int getAmountCents() { return amountCents; }
    public void setAmountCents(int amountCents) { this.amountCents = amountCents; }
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
    public BigDecimal getFxRate() { return fxRate; }
    public void setFxRate(BigDecimal fxRate) { this.fxRate = fxRate; }
    public boolean isProcessed() { return processed; }
    public void setProcessed(boolean processed) { this.processed = processed; }
    public int getAppliedCents() { return appliedCents; }
    public void setAppliedCents(int appliedCents) { this.appliedCents = appliedCents; }
}
