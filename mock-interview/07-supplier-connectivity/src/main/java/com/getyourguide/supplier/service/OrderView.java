package com.getyourguide.supplier.service;

import com.getyourguide.supplier.model.CustomerOrder;

import java.util.LinkedHashMap;
import java.util.Map;

public final class OrderView {
    private OrderView() {}

    public static Map<String, Object> of(CustomerOrder order) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("id", order.getId());
        body.put("status", order.getStatus());
        body.put("reservationStatus", order.getReservationStatus());
        body.put("supplierBookingId", order.getSupplierBookingId());
        body.put("startAt", order.getStartAt() == null ? null : order.getStartAt().toInstant().toString());
        return body;
    }
}
