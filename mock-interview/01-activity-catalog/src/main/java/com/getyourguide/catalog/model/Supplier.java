package com.getyourguide.catalog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Supplier {

    @Id
    private Long id;

    private String name;

    private String address;

    private String zip;

    private String city;

    private String country;

    @JsonIgnore
    @OneToMany(mappedBy = "supplier")
    private List<Activity> activities;
}
