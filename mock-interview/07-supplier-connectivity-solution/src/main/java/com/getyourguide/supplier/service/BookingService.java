package com.getyourguide.supplier.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.getyourguide.supplier.model.Activity;
import com.getyourguide.supplier.model.CustomerOrder;
import com.getyourguide.supplier.repository.ActivityRepository;
import com.getyourguide.supplier.repository.CustomerOrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BookingService {
    private final ActivityRepository activities;
    private final CustomerOrderRepository orders;
    private final HttpSupplierAdapter suppliers;

    public BookingService(ActivityRepository activities, CustomerOrderRepository orders, HttpSupplierAdapter suppliers) {
        this.activities = activities;
        this.orders = orders;
        this.suppliers = suppliers;
    }

    public Map<String, Object> book(long activityId, String localStart, int partySize, String pay, String idempotencyKey) {
        if (partySize > 8) {
            throw new IllegalArgumentException("party too large");
        }
        return orders.findByIdempotencyKey(idempotencyKey).map(OrderView::of).orElseGet(
                () -> place(activityId, localStart, partySize, pay, idempotencyKey));
    }

    public List<String> slots(long activityId) {
        JsonNode body = suppliers.availability(activityId);
        if (body.hasNonNull("errorCode")) {
            throw new SupplierBusinessException();
        }
        List<String> slots = new ArrayList<>();
        body.path("slots").forEach(node -> slots.add(node.asText()));
        return slots;
    }

    public int reconcile() {
        int updated = 0;
        for (CustomerOrder order : orders.findByStatus("UNKNOWN")) {
            Activity activity = activities.findById(order.getActivityId()).orElseThrow();
            long supplierId = activity.getSupplier().getId();
            suppliers.findBooking(order.getIdempotencyKey()).ifPresentOrElse(bookingId -> {
                order.setSupplierBookingId(bookingId);
                order.setStatus("BOOKED");
                order.setReservationStatus("BOOKED");
            }, () -> {
                suppliers.cancelReservation(supplierId, order.getSupplierReservationId(), order.getIdempotencyKey());
                order.setStatus("FAILED");
                order.setReservationStatus("RELEASED");
            });
            orders.save(order);
            updated++;
        }
        return updated;
    }

    private Map<String, Object> place(long activityId, String localStart, int partySize, String pay, String key) {
        Activity activity = activities.findById(activityId).orElseThrow();
        long supplierId = activity.getSupplier().getId();
        OffsetDateTime start = SlotInstant.resolve(LocalDateTime.parse(localStart), ZoneId.of(activity.getSupplier().getZoneId()));
        CustomerOrder order = orders.saveAndFlush(CustomerOrder.start(activityId, key, start));
        JsonNode reserved = suppliers.reserve(supplierId, Map.of("activityId", activityId, "partySize", partySize), key);
        order.setSupplierReservationId(reserved.path("reservationId").asText());
        order.setReservationStatus("HELD");
        orders.save(order);
        try {
            suppliers.pay(Map.of("orderId", order.getId(), "pay", pay));
        } catch (HttpClientErrorException ex) {
            release(supplierId, order, key);
            order.setStatus("PAYMENT_FAILED");
            orders.save(order);
            throw new PaymentDeclined(OrderView.of(order));
        }
        try {
            JsonNode booked = suppliers.book(supplierId, Map.of(
                    "reservationId", order.getSupplierReservationId(),
                    "orderId", order.getId()), key);
            order.setSupplierBookingId(booked.path("bookingId").asText());
            order.setStatus("BOOKED");
            order.setReservationStatus("BOOKED");
            return OrderView.of(orders.save(order));
        } catch (SupplierUnknown ex) {
            order.setStatus("UNKNOWN");
            return OrderView.of(orders.save(order));
        } catch (SupplierBusinessException ex) {
            release(supplierId, order, key);
            order.setStatus("REJECTED");
            orders.save(order);
            throw new RejectedOrder(OrderView.of(order));
        }
    }

    private void release(long supplierId, CustomerOrder order, String key) {
        if (order.getSupplierReservationId() != null) {
            suppliers.cancelReservation(supplierId, order.getSupplierReservationId(), key);
        }
        order.setReservationStatus("RELEASED");
    }
}
