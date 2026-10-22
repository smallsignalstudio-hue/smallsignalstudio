package com.getyourguide.supplier.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.getyourguide.supplier.model.Activity;
import com.getyourguide.supplier.model.CustomerOrder;
import com.getyourguide.supplier.repository.ActivityRepository;
import com.getyourguide.supplier.repository.CustomerOrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class BookingService {
    private final ActivityRepository activities;
    private final CustomerOrderRepository orders;
    private final SupplierClient client;
    private final Map<Long, List<String>> slotCache = new ConcurrentHashMap<>();

    public BookingService(ActivityRepository activities, CustomerOrderRepository orders, SupplierClient client) {
        this.activities = activities;
        this.orders = orders;
        this.client = client;
    }

    public Map<String, Object> book(long activityId, String localStart, int partySize, String pay, String idempotencyKey) {
        if (partySize > 8) {
            throw new IllegalArgumentException("party too large");
        }
        Activity activity = activities.findById(activityId).orElseThrow();
        LocalDateTime local = LocalDateTime.parse(localStart);
        OffsetDateTime start = local.atZone(ZoneId.of(activity.getSupplier().getZoneId())).toOffsetDateTime();
        CustomerOrder order = orders.save(CustomerOrder.start(activityId, idempotencyKey, start));
        JsonNode reserved = client.postSupplier("/reservations", Map.of("activityId", activityId, "partySize", partySize));
        order.setSupplierReservationId(reserved.path("reservationId").asText());
        order.setReservationStatus("HELD");
        orders.save(order);
        try {
            client.postPayment(Map.of("orderId", order.getId(), "pay", pay));
        } catch (RuntimeException ex) {
            order.setStatus("PAYMENT_FAILED");
            orders.save(order);
            throw new PaymentDeclined(OrderView.of(order));
        }
        JsonNode booked = bookWithRetry(order);
        if (booked.path("bookingId").isMissingNode() || booked.path("bookingId").isNull()) {
            order.setSupplierBookingId(null);
        } else {
            order.setSupplierBookingId(booked.path("bookingId").asText());
        }
        order.setStatus("BOOKED");
        order.setReservationStatus("BOOKED");
        return OrderView.of(orders.save(order));
    }

    public List<String> slots(long activityId) {
        return slotCache.computeIfAbsent(activityId, this::loadSlots);
    }

    private List<String> loadSlots(long activityId) {
        try {
            JsonNode body = client.getSupplier("/availability?activityId=" + activityId);
            if (body.hasNonNull("errorCode")) {
                return List.of();
            }
            List<String> slots = new ArrayList<>();
            body.path("slots").forEach(node -> slots.add(node.asText()));
            return slots;
        } catch (RuntimeException ex) {
            return List.of();
        }
    }

    private JsonNode bookWithRetry(CustomerOrder order) {
        RuntimeException last = null;
        for (int attempt = 0; attempt < 2; attempt++) {
            try {
                return client.postSupplier("/bookings", Map.of(
                        "reservationId", order.getSupplierReservationId(),
                        "orderId", order.getId()));
            } catch (HttpServerErrorException ex) {
                last = ex;
            }
        }
        throw last == null ? new IllegalStateException("book failed") : last;
    }
}
