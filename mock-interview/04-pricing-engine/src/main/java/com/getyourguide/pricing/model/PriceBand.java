package com.getyourguide.pricing.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "price_band")
public class PriceBand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String currency;
    private BigDecimal threshold;
    @Column(name = "flat_total")
    private BigDecimal flatTotal;

    public BigDecimal getThreshold() {
        return threshold;
    }

    public BigDecimal getFlatTotal() {
        return flatTotal;
    }
}
