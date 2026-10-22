package com.getyourguide.webhooks.repository;

import com.getyourguide.webhooks.model.OutboxMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OutboxRepository extends JpaRepository<OutboxMessage, Long> {
    List<OutboxMessage> findByBookingId(long bookingId);
    List<OutboxMessage> findByStatus(String status);
}
