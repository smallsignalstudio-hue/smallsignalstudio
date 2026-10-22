package com.getyourguide.webhooks.controller;

import com.getyourguide.webhooks.model.Booking;
import com.getyourguide.webhooks.model.Voucher;
import com.getyourguide.webhooks.repository.BookingRepository;
import com.getyourguide.webhooks.repository.VoucherRepository;
import com.getyourguide.webhooks.service.SignatureException;
import com.getyourguide.webhooks.service.WebhookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
public class WebhookController {

    private final WebhookService webhooks;
    private final BookingRepository bookings;
    private final VoucherRepository vouchers;

    public WebhookController(WebhookService webhooks, BookingRepository bookings, VoucherRepository vouchers) {
        this.webhooks = webhooks;
        this.bookings = bookings;
        this.vouchers = vouchers;
    }

    @PostMapping("/webhooks/psp")
    public void receive(@RequestBody String raw, @RequestHeader("X-Signature") String signature) {
        webhooks.receive(raw, signature);
    }

    @GetMapping("/bookings/{id}")
    public Map<String, Object> booking(@PathVariable long id) {
        Booking booking = bookings.findById(id).orElseThrow();
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("id", booking.getId());
        body.put("status", booking.getStatus());
        body.put("currency", booking.getCurrency());
        body.put("chargedAmount", booking.getChargedAmount() == null ? null : booking.getChargedAmount().toPlainString());
        return body;
    }

    @GetMapping("/bookings/{id}/vouchers")
    public List<Map<String, String>> voucherList(@PathVariable long id) {
        return vouchers.findByBookingId(id).stream().map(WebhookController::voucher).toList();
    }

    private static Map<String, String> voucher(Voucher voucher) {
        return Map.of("code", voucher.getCode(), "pspReference", voucher.getPspReference());
    }
}

@RestControllerAdvice
class WebhookErrors {
    @ExceptionHandler(SignatureException.class)
    ProblemDetail signature(SignatureException exception) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, exception.getMessage());
    }
}
