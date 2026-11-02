package com.getyourguide.cancellation.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "booking")
public class Booking {

    @Id
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;
    private String status;
    @Column(name = "start_at")
    private LocalDateTime startAt;
    @Column(name = "zone_id")
    private String zoneId;
    @Column(name = "list_price")
    private BigDecimal listPrice;
    @Column(name = "paid_amount")
    private BigDecimal paidAmount;
    private String currency;
    @Column(name = "slot_id")
    private Long slotId;
    private String note;

    public Long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getStartAt() {
        return startAt;
    }

    public String getZoneId() {
        return zoneId;
    }

    public BigDecimal getListPrice() {
        return listPrice;
    }

    public BigDecimal getPaidAmount() {
        return paidAmount;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
