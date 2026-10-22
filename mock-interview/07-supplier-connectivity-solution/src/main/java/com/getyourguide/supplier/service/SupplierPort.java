package com.getyourguide.supplier.service;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Map;
import java.util.Optional;

public interface SupplierPort {
    JsonNode reserve(long supplierId, Map<String, Object> body, String idempotencyKey);
    JsonNode book(long supplierId, Map<String, Object> body, String idempotencyKey);
    void cancelReservation(long supplierId, String reservationId, String idempotencyKey);
    JsonNode availability(long activityId);
    Optional<String> findBooking(String idempotencyKey);
}
