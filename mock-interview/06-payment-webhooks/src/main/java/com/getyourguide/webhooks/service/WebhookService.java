package com.getyourguide.webhooks.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.getyourguide.webhooks.model.Booking;
import com.getyourguide.webhooks.model.Inbox;
import com.getyourguide.webhooks.repository.BookingRepository;
import com.getyourguide.webhooks.repository.InboxRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class WebhookService {

    private final ObjectMapper json;
    private final HmacVerifier hmac;
    private final BookingRepository bookings;
    private final InboxRepository inbox;
    private final VoucherIssuer vouchers;
    private final String merchantAccount;

    public WebhookService(ObjectMapper json, HmacVerifier hmac, BookingRepository bookings, InboxRepository inbox,
                          VoucherIssuer vouchers, @Value("${psp.merchant-account}") String merchantAccount) {
        this.json = json;
        this.hmac = hmac;
        this.bookings = bookings;
        this.inbox = inbox;
        this.vouchers = vouchers;
        this.merchantAccount = merchantAccount;
    }

    public void receive(String raw, String signature) {
        hmac.verify(raw, signature);
        try {
            JsonNode node = json.readTree(raw);
            if (!merchantAccount.equals(node.path("merchantAccount").asText()) || !node.path("live").asBoolean()) {
                throw new SignatureException();
            }
            String event = node.path("eventCode").asText();
            String psp = node.path("pspReference").asText();
            long bookingId = node.path("bookingId").asLong();
            inbox.save(Inbox.of(psp + ":" + event, event, raw));
            Booking booking = bookings.findById(bookingId).orElseThrow();
            if ("AUTHORISATION".equals(event)) {
                int minor = node.path("amount").path("value").asInt();
                BigDecimal major = BigDecimal.valueOf(minor).divide(new BigDecimal("100"));
                BigDecimal fee = BigDecimal.ZERO;
                booking.setChargedAmount(major.subtract(fee));
                booking.setStatus("AUTHORISED");
                bookings.save(booking);
                vouchers.issue(bookingId, psp);
                return;
            }
            if ("CAPTURE".equals(event)) {
                booking.setStatus("CAPTURED");
                bookings.save(booking);
                return;
            }
            if ("REFUND".equals(event)) {
                booking.setStatus("REFUNDED");
                bookings.save(booking);
                return;
            }
            throw new IllegalStateException("unsupported event " + event);
        } catch (SignatureException | IllegalStateException ex) {
            throw ex;
        } catch (RuntimeException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }
}
