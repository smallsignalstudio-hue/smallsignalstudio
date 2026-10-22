package com.getyourguide.cancellation.repository;

import com.getyourguide.cancellation.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByBookingId(Long bookingId);
}
