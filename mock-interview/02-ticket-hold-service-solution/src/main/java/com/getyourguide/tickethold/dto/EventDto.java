package com.getyourguide.tickethold.dto;

import java.time.LocalDateTime;

public record EventDto(Long id, String name, String venue, LocalDateTime startsAt, LocalDateTime onSaleFrom,
                       int maxSeatsPerOrder) {
}
