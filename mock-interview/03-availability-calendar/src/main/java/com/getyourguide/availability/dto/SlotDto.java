package com.getyourguide.availability.dto;

import java.time.OffsetDateTime;

public record SlotDto(OffsetDateTime start, OffsetDateTime end, int remaining) {
}
