package com.getyourguide.pricing.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "rate")
public class Rate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String category;
    private String currency;
    @Column(name = "unit_price")
    private double unitPrice;
    @Column(name = "group_min")
    private Integer groupMin;
    @Column(name = "group_price")
    private Double groupPrice;

    public String getCategory() {
        return category;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public Integer getGroupMin() {
        return groupMin;
    }

    public Double getGroupPrice() {
        return groupPrice;
    }
}
