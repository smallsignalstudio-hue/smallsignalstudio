package com.atolls.cashback.controller;

import com.atolls.cashback.ledger.LedgerWriter;
import com.atolls.cashback.service.EventPoller;
import com.atolls.cashback.service.IntakeService;
import com.atolls.cashback.service.NotFoundException;
import com.atolls.cashback.service.PayoutService;
import com.atolls.cashback.service.WalletQuery;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class CashbackController {
    private final IntakeService intake;
    private final PayoutService payouts;
    private final EventPoller poller;
    private final WalletQuery wallets;
    private final LedgerWriter ledger;

    public CashbackController(IntakeService intake, PayoutService payouts, EventPoller poller, WalletQuery wallets, LedgerWriter ledger) {
        this.intake = intake;
        this.payouts = payouts;
        this.poller = poller;
        this.wallets = wallets;
        this.ledger = ledger;
    }

    @PostMapping("/events")
    public Map<String, Object> accept(@RequestBody EventBody body) {
        return intake.accept(body.userId, body.externalId, body.status, body.amountCents, body.currency, body.fxRate);
    }

    @GetMapping("/wallets/{userId}")
    public Map<String, Object> wallet(@PathVariable long userId) {
        return wallets.wallet(userId);
    }

    @GetMapping("/wallets/{userId}/ledger")
    public List<Map<String, Object>> ledger(@PathVariable long userId) {
        return ledger.list(userId);
    }

    @PostMapping("/wallets/{userId}/payouts")
    public Map<String, Object> payout(@PathVariable long userId, @RequestHeader("Idempotency-Key") String key) {
        return payouts.payout(userId, key);
    }

    @PostMapping("/internal/poll")
    public Map<String, Integer> poll() {
        return Map.of("processed", poller.poll());
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
