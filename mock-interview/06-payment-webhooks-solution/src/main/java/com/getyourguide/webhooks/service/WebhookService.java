package com.getyourguide.webhooks.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.getyourguide.webhooks.model.Booking;
import com.getyourguide.webhooks.model.Inbox;
import com.getyourguide.webhooks.model.OutboxMessage;
import com.getyourguide.webhooks.repository.BookingRepository;
import com.getyourguide.webhooks.repository.InboxRepository;
import com.getyourguide.webhooks.repository.OutboxRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

@Service
public class WebhookService {

    private final ObjectMapper json;
    private final HmacVerifier hmac;
    private final BookingRepository bookings;
    private final InboxRepository inbox;
    private final OutboxRepository outbox;
    private final List<WebhookHandler> handlers;
    private final OutboxPublisher publisher;
    private final TransactionTemplate transactions;
    private final String merchantAccount;

    public WebhookService(ObjectMapper json, HmacVerifier hmac, BookingRepository bookings, InboxRepository inbox,
                          OutboxRepository outbox, List<WebhookHandler> handlers, OutboxPublisher publisher,
                          TransactionTemplate transactions, @Value("${psp.merchant-account}") String merchantAccount) {
        this.json = json;
        this.hmac = hmac;
        this.bookings = bookings;
        this.inbox = inbox;
        this.outbox = outbox;
        this.handlers = handlers;
        this.publisher = publisher;
        this.transactions = transactions;
        this.merchantAccount = merchantAccount;
    }

    public void receive(String raw, String signature) {
        hmac.verify(raw, signature);
        JsonNode node;
        try {
            node = json.readTree(raw);
        } catch (Exception ex) {
            throw new SignatureException();
        }
        if (!merchantAccount.equals(node.path("merchantAccount").asText()) || !node.path("live").asBoolean()) {
            throw new SignatureException();
        }
        String event = node.path("eventCode").asText();
        String dedup = node.path("pspReference").asText() + ":" + event;
        if (inbox.findByDedupKey(dedup).isPresent()) {
            return;
        }
        Long outboxId;
        try {
            JsonNode body = node;
            outboxId = transactions.execute(status -> persist(body, raw, dedup, event));
        } catch (DataIntegrityViolationException ex) {
            return;
        }
        if (outboxId != null) {
            publisher.publish(outboxId);
        }
    }

    private Long persist(JsonNode node, String raw, String dedup, String event) {
        inbox.save(Inbox.of(dedup, event, raw));
        WebhookHandler handler = handlers.stream().filter(candidate -> candidate.supports(event)).findFirst().orElse(null);
        if (handler == null) {
            return null;
        }
        Booking booking = bookings.findById(node.path("bookingId").asLong()).orElseThrow();
        if (!PaymentStatus.of(booking.getStatus()).canMoveTo(handler.target())) {
            return null;
        }
        handler.apply(booking, node);
        bookings.save(booking);
        if (!handler.emitsVoucher()) {
            return null;
        }
        return outbox.saveAndFlush(OutboxMessage.pending(dedup, booking.getId())).getId();
    }
}
