package com.atolls.cashback.ledger;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class CommissionBooking {
    private CommissionBooking() {}

    public static int rank(String status) {
        return switch (status) {
            case "PENDING" -> 1;
            case "CONFIRMED" -> 2;
            case "DECLINED" -> 3;
            default -> 0;
        };
    }

    public static int targetCents(String status, int amountCents, BigDecimal eventRate, int alreadyApplied) {
        if ("DECLINED".equals(status)) return 0;
        if ("PENDING".equals(status)) return alreadyApplied;
        return new BigDecimal(amountCents).multiply(eventRate).setScale(0, RoundingMode.HALF_UP).intValueExact();
    }
}
