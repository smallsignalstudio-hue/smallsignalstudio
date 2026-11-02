package com.getyourguide.tickethold.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record BestAvailableRequest(@NotBlank String section, @Min(1) int count) {
}
