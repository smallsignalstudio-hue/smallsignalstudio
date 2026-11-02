package com.atolls.cashback.service;

import com.atolls.cashback.model.Wallet;
import com.atolls.cashback.repository.WalletRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class WalletQuery {
    private final WalletRepository wallets;

    public WalletQuery(WalletRepository wallets) {
        this.wallets = wallets;
    }

    public Map<String, Object> wallet(long userId) {
        Wallet wallet = wallets.findById(userId).orElseThrow(NotFoundException::new);
        return Map.of(
                "userId", wallet.getUserId(),
                "availableCents", wallet.getAvailableCents(),
                "pendingCents", wallet.getPendingCents());
    }
}
