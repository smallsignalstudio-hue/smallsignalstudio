package com.getyourguide.webhooks.service;

import com.getyourguide.webhooks.model.OutboxMessage;
import com.getyourguide.webhooks.repository.OutboxRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class OutboxPublisher {

    private final OutboxRepository outbox;
    private final VoucherIssuer issuer;

    public OutboxPublisher(OutboxRepository outbox, VoucherIssuer issuer) {
        this.outbox = outbox;
        this.issuer = issuer;
    }

    @Async
    public void publish(Long outboxId) {
        apply(outboxId);
    }

    public int flush() {
        int count = 0;
        for (OutboxMessage message : outbox.findByStatus("PENDING")) {
            apply(message.getId());
            count++;
        }
        return count;
    }

    private void apply(Long outboxId) {
        OutboxMessage message = outbox.findById(outboxId).orElseThrow();
        if ("PUBLISHED".equals(message.getStatus()) || "DLQ".equals(message.getStatus())) {
            return;
        }
        String psp = message.getDedupKey().substring(0, message.getDedupKey().indexOf(':'));
        try {
            issuer.issue(message.getBookingId(), psp);
            message.recordAttempt(true);
        } catch (RuntimeException ex) {
            message.recordAttempt(false);
        }
        outbox.save(message);
    }
}
