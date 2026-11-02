package com.getyourguide.catalog.repository;

public record SupplierStatisticsRow(Long supplierId, String supplierName, Long activityCount, Double averageRating) {
}
