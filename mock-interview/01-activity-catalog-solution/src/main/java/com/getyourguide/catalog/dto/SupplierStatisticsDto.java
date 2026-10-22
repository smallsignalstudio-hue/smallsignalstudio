package com.getyourguide.catalog.dto;

import java.math.BigDecimal;
import java.util.List;

public record SupplierStatisticsDto(
        Long supplierId,
        String supplierName,
        long activityCount,
        Double averageRating,
        List<Price> cheapestPrices
) {

    public record Price(String currency, BigDecimal amount) {
    }
}
