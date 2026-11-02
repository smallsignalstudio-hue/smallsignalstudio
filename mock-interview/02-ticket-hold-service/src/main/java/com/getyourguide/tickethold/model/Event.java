package com.getyourguide.tickethold.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "event")
public class Event {

    @Id
    private Long id;

    private String name;

    private String venue;

    private LocalDateTime startsAt;

    private LocalDateTime onSaleFrom;

    private int maxSeatsPerOrder;
}
