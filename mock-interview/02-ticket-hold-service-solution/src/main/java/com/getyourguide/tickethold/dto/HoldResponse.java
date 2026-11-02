package com.getyourguide.tickethold.dto;

import java.time.LocalDateTime;
import java.util.List;

public record HoldResponse(
        Long id,
        Long eventId,
        Long customerId,
        String status,
        List<Long> seatIds,
        LocalDateTime expiresAt) {
}
