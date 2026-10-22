package com.getyourguide.cancellation.service;

import com.getyourguide.cancellation.model.Notification;
import com.getyourguide.cancellation.repository.BookingRepository;
import com.getyourguide.cancellation.repository.NotificationRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class Mailer {

    private final BookingRepository bookings;
    private final NotificationRepository notifications;

    public Mailer(BookingRepository bookings, NotificationRepository notifications) {
        this.bookings = bookings;
        this.notifications = notifications;
    }

    @Async
    public void notifyCancelled(Long bookingId) {
        try {
            var booking = bookings.findById(bookingId).orElseThrow();
            String email = booking.getCustomer().getEmail();
            notifications.save(Notification.of(bookingId, "cancelled " + email));
        } catch (RuntimeException ignored) {
        }
    }
}
