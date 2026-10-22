package com.getyourguide.supplier.service;

import java.util.Map;

public class RejectedOrder extends RuntimeException {
    private final Map<String, Object> order;
    public RejectedOrder(Map<String, Object> order) {
        super("rejected");
        this.order = order;
    }
    public Map<String, Object> order() { return order; }
}
