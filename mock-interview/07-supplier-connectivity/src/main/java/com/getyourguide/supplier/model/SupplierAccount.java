package com.getyourguide.supplier.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "supplier_account")
public class SupplierAccount {
    @Id
    private Long id;
    private String code;
    @Column(name = "zone_id")
    private String zoneId;

    public Long getId() { return id; }
    public String getZoneId() { return zoneId; }
}
