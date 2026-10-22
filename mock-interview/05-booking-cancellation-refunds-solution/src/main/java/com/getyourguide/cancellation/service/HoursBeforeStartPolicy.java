package com.getyourguide.cancellation.service;

import com.getyourguide.cancellation.model.Booking;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public final class HoursBeforeStartPolicy {

    private HoursBeforeStartPolicy() {
    }

    public static BigDecimal refund(Booking booking, Instant now) {
        ZonedDateTime start = booking.getStartAt().atZone(ZoneId.of(booking.getZoneId()));
        boolean full = !now.isAfter(start.toInstant().minus(Duration.ofHours(24)));
        if (full) {
            return booking.getPaidAmount();
        }
        return booking.getPaidAmount().multiply(new BigDecimal("0.5")).setScale(2, RoundingMode.HALF_UP);
    }
}
