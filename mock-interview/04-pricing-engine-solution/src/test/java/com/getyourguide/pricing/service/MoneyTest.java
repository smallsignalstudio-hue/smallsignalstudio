package com.getyourguide.pricing.service;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class MoneyTest {

    @Test
    void formatsByCurrencyAndClamps() {
        assertThat(Money.of(new BigDecimal("0.10"), "EUR").times(8).format()).isEqualTo("0.80");
        assertThat(Money.of(new BigDecimal("333"), "JPY").times(3).percentOff(10).format()).isEqualTo("899");
        assertThat(Money.of(new BigDecimal("20"), "EUR").percentOff(150).clampAtZero().format()).isEqualTo("0.00");
    }
}
