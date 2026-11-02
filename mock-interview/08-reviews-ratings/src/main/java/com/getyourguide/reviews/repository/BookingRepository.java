package com.getyourguide.reviews.repository;
import com.getyourguide.reviews.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
public interface BookingRepository extends JpaRepository<Booking, Long> {}
