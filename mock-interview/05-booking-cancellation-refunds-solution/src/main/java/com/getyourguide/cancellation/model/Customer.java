package com.getyourguide.cancellation.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    private Long id;
    private String email;

    public String getEmail() {
        return email;
    }
}
