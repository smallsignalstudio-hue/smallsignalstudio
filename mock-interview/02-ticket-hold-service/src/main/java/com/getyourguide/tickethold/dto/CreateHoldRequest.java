package com.getyourguide.tickethold.dto;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record CreateHoldRequest(@NotEmpty List<Long> seatIds) {
}
