package com.getyourguide.webhooks.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.getyourguide.webhooks.model.Booking;
import org.springframework.stereotype.Component;

@Component
public class AuthorisationHandler implements WebhookHandler {
    @Override
    public boolean supports(String eventCode) {
        return "AUTHORISATION".equals(eventCode);
    }

    @Override
    public PaymentStatus target() {
        return PaymentStatus.AUTHORISED;
    }

    @Override
    public boolean emitsVoucher() {
        return true;
    }

    @Override
    public void apply(Booking booking, JsonNode event) {
        long minor = event.path("amount").path("value").asLong();
        String currency = event.path("amount").path("currency").asText();
        booking.setChargedAmount(MinorUnits.major(minor, currency));
        booking.setStatus(target().name());
    }
}
