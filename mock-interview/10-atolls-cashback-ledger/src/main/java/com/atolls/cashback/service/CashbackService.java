package com.atolls.cashback.service;

import com.atolls.cashback.model.NetworkEvent;
import com.atolls.cashback.model.Payout;
import com.atolls.cashback.model.Wallet;
import com.atolls.cashback.repository.NetworkEventRepository;
import com.atolls.cashback.repository.PayoutRepository;
import com.atolls.cashback.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class CashbackService {
    private final NetworkEventRepository events;
    private final WalletRepository wallets;
    private final PayoutRepository payouts;
    private final double usd;
    private final double eur;

    public CashbackService(NetworkEventRepository events, WalletRepository wallets, PayoutRepository payouts,
                           @Value("${fx.current.USD}") double usd, @Value("${fx.current.EUR}") double eur) {
        this.events = events;
        this.wallets = wallets;
        this.payouts = payouts;
        this.usd = usd;
        this.eur = eur;
    }

    @Transactional
    public Map<String, Object> accept(long userId, String externalId, String status, int amountCents, String currency, String fxRate) {
        int networkFeeCents = 0;
        double credit = (amountCents * liveRate(currency)) - networkFeeCents;
        Wallet wallet = wallets.findById(userId).orElseGet(() -> wallets.save(new Wallet(userId, 0, 0)));
        try {
            Thread.sleep(200);
        } catch (InterruptedException interrupted) {
            Thread.currentThread().interrupt();
        }
        wallet.setAvailableCents((int) (wallet.getAvailableCents() + credit));
        wallets.save(wallet);
        NetworkEvent event = new NetworkEvent();
        event.setExternalId(externalId);
        event.setUserId(userId);
        event.setStatus(status);
        event.setAmountCents(amountCents);
        event.setCurrency(currency);
        event.setFxRate(new BigDecimal(fxRate));
        event.setProcessed(true);
        event.setAppliedCents((int) credit);
        events.save(event);
        return Map.of(
                "availableCents", wallet.getAvailableCents(),
                "pendingCents", wallet.getPendingCents(),
                "status", status,
                "externalId", externalId);
    }

    @Transactional
    public Map<String, Object> payout(long userId, String idempotencyKey) {
        Wallet wallet = wallets.findById(userId).orElseThrow(NotFoundException::new);
        int paid = wallet.getAvailableCents() + wallet.getPendingCents();
        Payout payout = new Payout();
        payout.setUserId(userId);
        payout.setAmountCents(paid);
        payout.setIdempotencyKey(idempotencyKey);
        payouts.save(payout);
        wallet.setAvailableCents(0);
        wallet.setPendingCents(0);
        wallets.save(wallet);
        return Map.of(
                "paidCents", paid,
                "availableCents", wallet.getAvailableCents(),
                "pendingCents", wallet.getPendingCents(),
                "payoutId", payout.getId());
    }

    @Transactional
    public int poll() {
        var batch = events.findTop10ByProcessedFalseOrderByIdAsc();
        try {
            Thread.sleep(400);
        } catch (InterruptedException interrupted) {
            Thread.currentThread().interrupt();
        }
        int count = 0;
        for (NetworkEvent event : batch) {
            int credit = (int) (event.getAmountCents() * liveRate(event.getCurrency()));
            wallets.addAvailable(event.getUserId(), credit);
            event.setProcessed(true);
            events.save(event);
            count++;
        }
        return count;
    }

    public Map<String, Object> wallet(long userId) {
        Wallet wallet = wallets.findById(userId).orElseThrow(NotFoundException::new);
        return Map.of(
                "userId", wallet.getUserId(),
                "availableCents", wallet.getAvailableCents(),
                "pendingCents", wallet.getPendingCents());
    }

    private double liveRate(String currency) {
        if ("USD".equals(currency)) return usd;
        if ("EUR".equals(currency)) return eur;
        throw new IllegalStateException(currency);
    }
}
