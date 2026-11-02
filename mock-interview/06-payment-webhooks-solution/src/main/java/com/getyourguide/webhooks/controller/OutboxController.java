package com.getyourguide.webhooks.controller;

import com.getyourguide.webhooks.model.OutboxMessage;
import com.getyourguide.webhooks.repository.OutboxRepository;
import com.getyourguide.webhooks.service.OutboxPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class OutboxController {

    private final OutboxPublisher publisher;
    private final OutboxRepository outbox;

    public OutboxController(OutboxPublisher publisher, OutboxRepository outbox) {
        this.publisher = publisher;
        this.outbox = outbox;
    }

    @PostMapping("/internal/outbox/flush")
    public Map<String, Integer> flush() {
        return Map.of("flushed", publisher.flush());
    }

    @GetMapping("/bookings/{id}/outbox")
    public List<Map<String, String>> rows(@PathVariable long id) {
        return outbox.findByBookingId(id).stream()
                .map(OutboxController::row)
                .toList();
    }

    private static Map<String, String> row(OutboxMessage message) {
        return Map.of("status", message.getStatus(), "dedupKey", message.getDedupKey());
    }
}
