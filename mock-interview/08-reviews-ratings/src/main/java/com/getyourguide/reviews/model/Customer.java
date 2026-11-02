package com.getyourguide.reviews.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity @Table(name = "customer")
public class Customer {
    @Id private Long id;
    private String name;
    public Long getId() { return id; }
    public String getName() { return name; }
}
