package com.getyourguide.catalog.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Data
public class Activity {

    @Id
    private Long id;

    private String title;

    private double price;

    private String currency;

    private Double rating;

    private boolean specialOffer;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
}
