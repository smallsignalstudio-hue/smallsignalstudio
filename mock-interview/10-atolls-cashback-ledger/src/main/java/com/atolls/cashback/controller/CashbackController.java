package com.atolls.cashback.controller;

import com.atolls.cashback.service.CashbackService;
import com.atolls.cashback.service.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class CashbackController {
    private final CashbackService cashback;

    public CashbackController(CashbackService cashback) {
        this.cashback = cashback;
    }

    @PostMapping("/events")
    public Map<String, Object> accept(@RequestBody EventBody body) {
        return cashback.accept(body.userId, body.externalId, body.status, body.amountCents, body.currency, body.fxRate);
    }

    @GetMapping("/wallets/{userId}")
    public Map<String, Object> wallet(@PathVariable long userId) {
        return cashback.wallet(userId);
    }

    @PostMapping("/wallets/{userId}/payouts")
    public Map<String, Object> payout(@PathVariable long userId, @RequestHeader("Idempotency-Key") String key) {
        return cashback.payout(userId, key);
    }

    @PostMapping("/internal/poll")
    public Map<String, Integer> poll() {
        return Map.of("processed", cashback.poll());
    }

    public static class EventBody {
        public String externalId;
        public long userId;
        public String status;
        public int amountCents;
        public String currency;
        public String fxRate;
    }
}

@RestControllerAdvice
class CashbackErrors {
    @ExceptionHandler(NotFoundException.class)
    ResponseEntity<Void> missing(NotFoundException exception) {
        return ResponseEntity.notFound().build();
    }
}
