package com.getyourguide.availability.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@Entity
public class Activity {

    @Id
    private Long id;
    private String title;
    private String zoneId;
    private LocalTime localTime;
    private int durationMinutes;
    private int capacity;
    private int cutoffHours;
    private Long resourceId;
}
