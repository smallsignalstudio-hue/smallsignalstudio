package com.getyourguide.pricing.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "promo")
public class Promo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private int percent;
    @Column(name = "min_subtotal")
    private BigDecimal minSubtotal;

    public String getCode() {
        return code;
    }

    public int getPercent() {
        return percent;
    }

    public BigDecimal getMinSubtotal() {
        return minSubtotal;
    }
}
