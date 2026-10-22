package com.getyourguide.pricing.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

public final class Money {

    private final BigDecimal amount;
    private final String currency;

    private Money(BigDecimal amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public static Money of(BigDecimal amount, String currency) {
        return new Money(amount, currency);
    }

    public static Money zero(String currency) {
        return new Money(BigDecimal.ZERO, currency);
    }

    public Money add(Money other) {
        return new Money(amount.add(other.amount), currency);
    }

    public Money times(int quantity) {
        return new Money(amount.multiply(BigDecimal.valueOf(quantity)), currency);
    }

    public Money percentOff(int percent) {
        BigDecimal factor = BigDecimal.valueOf(100L - percent).movePointLeft(2);
        return new Money(amount.multiply(factor), currency);
    }

    public Money clampAtZero() {
        return amount.signum() < 0 ? zero(currency) : this;
    }

    public int compareAmount(BigDecimal other) {
        return amount.compareTo(other);
    }

    public String format() {
        int digits = Currency.getInstance(currency).getDefaultFractionDigits();
        return amount.setScale(digits, RoundingMode.HALF_UP).toPlainString();
    }
}
