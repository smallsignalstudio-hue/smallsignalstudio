package com.atolls.coupon.model;
import jakarta.persistence.*;
import java.time.LocalDate;
@Entity @Table(name="campaign")
public class Campaign {
    @Id private Long id;
    private String name;
    private String market;
    @Column(name="zone_id") private String zoneId;
    @Column(name="global_offer") private boolean globalOffer;
    private boolean active;
    @Column(name="valid_until") private LocalDate validUntil;
    @Column(name="per_user_limit") private int perUserLimit;
    public Long getId(){return id;} public String getName(){return name;} public String getMarket(){return market;}
    public String getZoneId(){return zoneId;} public boolean isGlobalOffer(){return globalOffer;}
    public boolean isActive(){return active;} public LocalDate getValidUntil(){return validUntil;}
    public int getPerUserLimit(){return perUserLimit;}
}
