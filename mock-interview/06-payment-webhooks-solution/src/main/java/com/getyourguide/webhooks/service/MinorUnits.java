package com.getyourguide.webhooks.service;

import java.math.BigDecimal;
import java.util.Currency;

public final class MinorUnits {

    private MinorUnits() {
    }

    public static BigDecimal major(long minor, String currency) {
        int digits = Currency.getInstance(currency).getDefaultFractionDigits();
        if (digits < 0) {
            digits = 2;
        }
        return BigDecimal.valueOf(minor).movePointLeft(digits);
    }
}
