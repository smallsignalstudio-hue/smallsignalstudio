package com.atolls.cashback.service;

import com.atolls.cashback.ledger.LedgerWriter;
import com.atolls.cashback.model.Payout;
import com.atolls.cashback.model.Wallet;
import com.atolls.cashback.repository.PayoutRepository;
import com.atolls.cashback.repository.WalletRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class PayoutService {
    private final WalletRepository wallets;
    private final PayoutRepository payouts;
    private final LedgerWriter ledger;

    public PayoutService(WalletRepository wallets, PayoutRepository payouts, LedgerWriter ledger) {
        this.wallets = wallets;
        this.payouts = payouts;
        this.ledger = ledger;
    }

    @Transactional
    public Map<String, Object> payout(long userId, String idempotencyKey) {
        Payout existing = payouts.findByIdempotencyKey(idempotencyKey).orElse(null);
        if (existing != null) return view(existing, load(userId));
        Wallet wallet = load(userId);
        int paid = wallet.getAvailableCents();
        Payout payout = new Payout();
        payout.setUserId(userId);
        payout.setAmountCents(paid);
        payout.setIdempotencyKey(idempotencyKey);
        try {
            payouts.saveAndFlush(payout);
        } catch (DataIntegrityViolationException collision) {
            Payout stored = payouts.findByIdempotencyKey(idempotencyKey).orElseThrow();
            return view(stored, load(userId));
        }
        if (paid != 0) {
            wallets.addAvailable(userId, -paid);
            ledger.post(userId, idempotencyKey, "PAYOUT", -paid);
        }
        return view(payout, load(userId));
    }

    private Wallet load(long userId) {
        return wallets.findById(userId).orElseThrow(NotFoundException::new);
    }

    private Map<String, Object> view(Payout payout, Wallet wallet) {
        return Map.of(
                "paidCents", payout.getAmountCents(),
                "availableCents", wallet.getAvailableCents(),
                "pendingCents", wallet.getPendingCents(),
                "payoutId", payout.getId());
    }
}
