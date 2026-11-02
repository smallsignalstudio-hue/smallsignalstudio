package com.getyourguide.tickethold.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "seat")
public class Seat {

    @Id
    private Long id;

    private Long eventId;

    private String section;

    private int seatRow;

    private int seatNumber;

    private String priceCategory;

    @Enumerated(EnumType.STRING)
    private SeatStatus status;

    private Long holdId;
}
