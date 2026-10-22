package com.getyourguide.supplier.service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;

public final class SlotInstant {
    private SlotInstant() {}

    public static OffsetDateTime resolve(LocalDateTime local, ZoneId zone) {
        return local.atZone(zone).withLaterOffsetAtOverlap().toOffsetDateTime();
    }
}
