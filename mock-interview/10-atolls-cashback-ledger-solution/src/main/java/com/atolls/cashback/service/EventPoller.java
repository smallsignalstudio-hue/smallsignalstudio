package com.atolls.cashback.service;

import com.atolls.cashback.ledger.CommissionBooking;
import com.atolls.cashback.ledger.LedgerWriter;
import com.atolls.cashback.model.NetworkEvent;
import com.atolls.cashback.repository.NetworkEventRepository;
import com.atolls.cashback.repository.WalletRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventPoller {
    private final NetworkEventRepository events;
    private final WalletRepository wallets;
    private final LedgerWriter ledger;

    public EventPoller(NetworkEventRepository events, WalletRepository wallets, LedgerWriter ledger) {
        this.events = events;
        this.wallets = wallets;
        this.ledger = ledger;
    }

    @Transactional
    public int poll() {
        int count = 0;
        for (Number id : events.lockUnprocessed()) {
            NetworkEvent event = events.findById(id.longValue()).orElseThrow();
            int target = CommissionBooking.targetCents(event.getStatus(), event.getAmountCents(), event.getFxRate(), event.getAppliedCents());
            int delta = target - event.getAppliedCents();
            event.setAppliedCents(target);
            event.setProcessed(true);
            events.saveAndFlush(event);
            if (delta != 0) {
                wallets.addAvailable(event.getUserId(), delta);
                ledger.post(event.getUserId(), event.getExternalId(), delta < 0 ? "REVERSAL" : "CREDIT", delta);
            }
            count++;
        }
        return count;
    }
}
