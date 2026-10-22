package com.getyourguide.supplier.service;

import java.util.Map;

public class PaymentDeclined extends RuntimeException {
    private final Map<String, Object> order;

    public PaymentDeclined(Map<String, Object> order) {
        super("payment declined");
        this.order = order;
    }

    public Map<String, Object> order() {
        return order;
    }
}
