package com.getyourguide.pricing.dto;

import java.util.List;

public record QuoteRequest(String customerId, String currency, String promoCode, List<Line> lines) {

    public record Line(String category, int quantity) {
    }
}
