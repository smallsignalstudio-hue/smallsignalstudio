package com.getyourguide.catalog.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Activity {

    @Id
    private Long id;

    private String title;

    private BigDecimal price;

    private String currency;

    private Double rating;

    private boolean specialOffer;

    // supplier_id may reference a supplier that has not been synced yet (no FK between the pipelines).
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @Override
    public String toString() {
        return "Activity{id=" + id + ", title='" + title + "'}";
    }
}
