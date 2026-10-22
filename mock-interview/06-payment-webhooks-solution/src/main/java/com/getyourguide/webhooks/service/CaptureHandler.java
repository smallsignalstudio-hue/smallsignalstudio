package com.getyourguide.webhooks.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.getyourguide.webhooks.model.Booking;
import org.springframework.stereotype.Component;

@Component
public class CaptureHandler implements WebhookHandler {
    @Override
    public boolean supports(String eventCode) {
        return "CAPTURE".equals(eventCode);
    }

    @Override
    public PaymentStatus target() {
        return PaymentStatus.CAPTURED;
    }

    @Override
    public boolean emitsVoucher() {
        return false;
    }

    @Override
    public void apply(Booking booking, JsonNode event) {
        booking.setStatus(target().name());
    }
}
