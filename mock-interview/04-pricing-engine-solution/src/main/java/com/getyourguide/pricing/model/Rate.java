package com.getyourguide.pricing.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "rate")
public class Rate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String category;
    private String currency;
    @Column(name = "unit_price")
    private BigDecimal unitPrice;
    @Column(name = "group_min")
    private Integer groupMin;
    @Column(name = "group_price")
    private BigDecimal groupPrice;

    public String getCategory() {
        return category;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public Integer getGroupMin() {
        return groupMin;
    }

    public BigDecimal getGroupPrice() {
        return groupPrice;
    }
}
