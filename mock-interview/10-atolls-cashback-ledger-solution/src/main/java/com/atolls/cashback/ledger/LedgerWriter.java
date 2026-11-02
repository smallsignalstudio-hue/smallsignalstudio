package com.atolls.cashback.ledger;

import com.atolls.cashback.model.LedgerEntry;
import com.atolls.cashback.repository.LedgerRepository;
import org.springframework.stereotype.Component;

@Component
public class LedgerWriter {
    private final LedgerRepository entries;

    public LedgerWriter(LedgerRepository entries) {
        this.entries = entries;
    }

    public void post(long userId, String externalId, String kind, int userDelta) {
        append(userId, externalId, kind, "USER", userDelta);
        String contra = "PAYOUT".equals(kind) ? "CASH" : "NETWORK";
        append(userId, externalId, kind, contra, -userDelta);
    }

    public java.util.List<java.util.Map<String, Object>> list(long userId) {
        return entries.findByUserIdOrderByIdAsc(userId).stream().map(entry -> java.util.Map.<String, Object>of(
                "id", entry.getId(),
                "externalId", entry.getExternalId(),
                "kind", entry.getKind(),
                "account", entry.getAccount(),
                "amountCents", entry.getAmountCents()
        )).toList();
    }

    private void append(long userId, String externalId, String kind, String account, int amount) {
        LedgerEntry entry = new LedgerEntry();
        entry.setUserId(userId);
        entry.setExternalId(externalId);
        entry.setKind(kind);
        entry.setAccount(account);
        entry.setAmountCents(amount);
        entries.save(entry);
    }
}
