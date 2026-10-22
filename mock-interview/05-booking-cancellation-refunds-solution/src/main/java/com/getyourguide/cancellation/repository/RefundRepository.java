package com.getyourguide.cancellation.repository;

import com.getyourguide.cancellation.model.Refund;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RefundRepository extends JpaRepository<Refund, Long> {

    List<Refund> findByBookingId(Long bookingId);

    Optional<Refund> findByIdempotencyKey(String idempotencyKey);
}
