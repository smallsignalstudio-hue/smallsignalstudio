package com.getyourguide.cancellation.repository;

import com.getyourguide.cancellation.model.SlotCancellation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SlotCancellationRepository extends JpaRepository<SlotCancellation, Long> {

    List<SlotCancellation> findByIdempotencyKeyOrderByBookingIdAsc(String idempotencyKey);
}
