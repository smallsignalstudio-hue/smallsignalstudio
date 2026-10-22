package com.getyourguide.cancellation.service;

import com.getyourguide.cancellation.model.Notification;
import com.getyourguide.cancellation.repository.NotificationRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class Mailer {

    private final NotificationRepository notifications;

    public Mailer(NotificationRepository notifications) {
        this.notifications = notifications;
    }

    @Async
    public void notifyCancelled(Long bookingId, String email) {
        notifications.save(Notification.of(bookingId, "cancelled " + email));
    }
}
