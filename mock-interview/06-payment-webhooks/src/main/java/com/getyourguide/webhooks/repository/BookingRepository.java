package com.getyourguide.webhooks.repository;

import com.getyourguide.webhooks.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
