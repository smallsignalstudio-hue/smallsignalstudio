package com.getyourguide.catalog.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Supplier {

    @Id
    private Long id;

    private String name;

    private String address;

    private String zip;

    private String city;

    private String country;

    @OneToMany(mappedBy = "supplier")
    private List<Activity> activities = new ArrayList<>();

    @Override
    public String toString() {
        return "Supplier{id=" + id + ", name='" + name + "'}";
    }
}
