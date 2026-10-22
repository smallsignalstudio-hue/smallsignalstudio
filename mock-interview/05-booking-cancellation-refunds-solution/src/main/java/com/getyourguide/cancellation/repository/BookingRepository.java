package com.getyourguide.cancellation.repository;

import com.getyourguide.cancellation.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findBySlotIdAndStatus(Long slotId, String status);
}
