package com.getyourguide.catalog.dto;

import java.math.BigDecimal;

public record ActivityDto(
        Long id,
        String title,
        BigDecimal price,
        BigDecimal finalPrice,
        String currency,
        Double rating,
        boolean specialOffer,
        String supplierName,
        String supplierLocation
) {
}
