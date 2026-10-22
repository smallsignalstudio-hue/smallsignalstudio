package com.getyourguide.cancellation.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "booking_id")
    private Long bookingId;
    private String body;

    public static Notification of(Long bookingId, String body) {
        Notification notification = new Notification();
        notification.bookingId = bookingId;
        notification.body = body;
        return notification;
    }

    public String getBody() {
        return body;
    }
}
