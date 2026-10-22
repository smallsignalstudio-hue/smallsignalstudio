package com.atolls.cashback.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ledger_entry")
public class LedgerEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id") private long userId;
    @Column(name = "external_id") private String externalId;
    private String kind;
    private String account;
    @Column(name = "amount_cents") private int amountCents;

    public Long getId() { return id; }
    public long getUserId() { return userId; }
    public void setUserId(long userId) { this.userId = userId; }
    public String getExternalId() { return externalId; }
    public void setExternalId(String externalId) { this.externalId = externalId; }
    public String getKind() { return kind; }
    public void setKind(String kind) { this.kind = kind; }
    public String getAccount() { return account; }
    public void setAccount(String account) { this.account = account; }
    public int getAmountCents() { return amountCents; }
    public void setAmountCents(int amountCents) { this.amountCents = amountCents; }
}
