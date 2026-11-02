package com.getyourguide.catalog.repository;

import java.math.BigDecimal;

public record CheapestPriceRow(Long supplierId, String currency, BigDecimal amount) {
}
