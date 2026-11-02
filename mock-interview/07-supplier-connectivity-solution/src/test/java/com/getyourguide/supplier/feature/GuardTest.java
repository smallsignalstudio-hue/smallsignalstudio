package com.getyourguide.supplier.feature;

import com.getyourguide.supplier.service.Bulkhead;
import com.getyourguide.supplier.service.CircuitBreaker;
import com.getyourguide.supplier.service.SlotInstant;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;

class GuardTest {
    @Test
    void thirdPermitIsRejected() {
        Bulkhead bulkhead = new Bulkhead(2);
        assertThat(bulkhead.tryAcquire()).isTrue();
        assertThat(bulkhead.tryAcquire()).isTrue();
        assertThat(bulkhead.tryAcquire()).isFalse();
        bulkhead.release();
        assertThat(bulkhead.tryAcquire()).isTrue();
    }

    @Test
    void breakerOpensOnTheThirdFailure() {
        CircuitBreaker breaker = new CircuitBreaker(3);
        breaker.failure(1);
        breaker.failure(1);
        assertThat(breaker.allow(1)).isTrue();
        breaker.failure(1);
        assertThat(breaker.allow(1)).isFalse();
        breaker.success(1);
        assertThat(breaker.allow(1)).isTrue();
    }

    @Test
    void overlapUsesTheLaterOffset() {
        Instant instant = SlotInstant.resolve(LocalDateTime.parse("2026-10-25T02:30"), ZoneId.of("Europe/Amsterdam")).toInstant();
        assertThat(instant).isEqualTo(Instant.parse("2026-10-25T01:30:00Z"));
    }
}
