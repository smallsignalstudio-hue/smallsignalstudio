package com.getyourguide.availability.dto;

import jakarta.validation.constraints.Min;

import java.time.Instant;

public record BookRequest(long activityId, Instant slotStart, @Min(1) int partySize) {
}
