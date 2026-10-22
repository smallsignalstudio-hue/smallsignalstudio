package com.getyourguide.supplier.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.OffsetDateTime;

@Entity
@Table(name = "customer_order")
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "activity_id")
    private Long activityId;
    private String status;
    @Column(name = "reservation_status")
    private String reservationStatus;
    @Column(name = "supplier_reservation_id")
    private String supplierReservationId;
    @Column(name = "supplier_booking_id")
    private String supplierBookingId;
    @Column(name = "start_at")
    private OffsetDateTime startAt;
    @Column(name = "idempotency_key")
    private String idempotencyKey;

    public static CustomerOrder start(Long activityId, String key, OffsetDateTime startAt) {
        CustomerOrder order = new CustomerOrder();
        order.activityId = activityId;
        order.idempotencyKey = key;
        order.startAt = startAt;
        order.status = "PENDING";
        order.reservationStatus = "NONE";
        return order;
    }

    public Long getId() { return id; }
    public Long getActivityId() { return activityId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getReservationStatus() { return reservationStatus; }
    public void setReservationStatus(String reservationStatus) { this.reservationStatus = reservationStatus; }
    public String getSupplierReservationId() { return supplierReservationId; }
    public void setSupplierReservationId(String supplierReservationId) { this.supplierReservationId = supplierReservationId; }
    public String getSupplierBookingId() { return supplierBookingId; }
    public void setSupplierBookingId(String supplierBookingId) { this.supplierBookingId = supplierBookingId; }
    public OffsetDateTime getStartAt() { return startAt; }
    public String getIdempotencyKey() { return idempotencyKey; }
}
