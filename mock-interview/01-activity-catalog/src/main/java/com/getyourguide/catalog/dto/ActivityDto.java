package com.getyourguide.catalog.dto;

public record ActivityDto(
        Long id,
        String title,
        double price,
        double finalPrice,
        String currency,
        Double rating,
        boolean specialOffer,
        String supplierName,
        String supplierLocation
) {
}
