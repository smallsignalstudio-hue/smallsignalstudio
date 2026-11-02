package com.atolls.cashback.service;

import com.atolls.cashback.ledger.CommissionBooking;
import com.atolls.cashback.ledger.LedgerWriter;
import com.atolls.cashback.model.NetworkEvent;
import com.atolls.cashback.model.Wallet;
import com.atolls.cashback.repository.NetworkEventRepository;
import com.atolls.cashback.repository.WalletRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class IntakeService {
    private final NetworkEventRepository events;
    private final WalletRepository wallets;
    private final LedgerWriter ledger;

    public IntakeService(NetworkEventRepository events, WalletRepository wallets, LedgerWriter ledger) {
        this.events = events;
        this.wallets = wallets;
        this.ledger = ledger;
    }

    @Transactional
    public Map<String, Object> accept(long userId, String externalId, String status, int amountCents, String currency, String fxRate) {
        wallets.ensure(userId);
        BigDecimal rate = new BigDecimal(fxRate);
        NetworkEvent event = events.findByExternalId(externalId).orElse(null);
        if (event != null && CommissionBooking.rank(status) < CommissionBooking.rank(event.getStatus())) {
            return view(load(userId), event.getStatus(), externalId);
        }
        int already = event == null ? 0 : event.getAppliedCents();
        int target = CommissionBooking.targetCents(status, amountCents, rate, already);
        int delta = target - already;
        if (event == null) {
            event = new NetworkEvent();
            event.setExternalId(externalId);
            event.setUserId(userId);
        }
        event.setStatus(status);
        event.setAmountCents(amountCents);
        event.setCurrency(currency);
        event.setFxRate(rate);
        event.setProcessed(true);
        event.setAppliedCents(target);
        events.saveAndFlush(event);
        if (delta != 0) {
            wallets.addAvailable(userId, delta);
            ledger.post(userId, externalId, delta < 0 ? "REVERSAL" : "CREDIT", delta);
        }
        return view(load(userId), status, externalId);
    }

    private Wallet load(long userId) {
        return wallets.findById(userId).orElseThrow(NotFoundException::new);
    }

    private Map<String, Object> view(Wallet wallet, String status, String externalId) {
        return Map.of(
                "availableCents", wallet.getAvailableCents(),
                "pendingCents", wallet.getPendingCents(),
                "status", status,
                "externalId", externalId);
    }
}
