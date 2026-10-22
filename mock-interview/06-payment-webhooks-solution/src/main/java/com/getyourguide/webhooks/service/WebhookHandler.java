package com.getyourguide.webhooks.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.getyourguide.webhooks.model.Booking;

public interface WebhookHandler {
    boolean supports(String eventCode);

    PaymentStatus target();

    boolean emitsVoucher();

    void apply(Booking booking, JsonNode event);
}
