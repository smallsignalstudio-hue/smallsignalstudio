package com.getyourguide.cancellation.controller;

import com.getyourguide.cancellation.model.Booking;
import com.getyourguide.cancellation.model.Notification;
import com.getyourguide.cancellation.model.Refund;
import com.getyourguide.cancellation.repository.BookingRepository;
import com.getyourguide.cancellation.repository.NotificationRepository;
import com.getyourguide.cancellation.repository.RefundRepository;
import com.getyourguide.cancellation.service.CancellationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class CancellationController {

    private final CancellationService cancellations;
    private final BookingRepository bookings;
    private final RefundRepository refunds;
    private final NotificationRepository notifications;

    public CancellationController(CancellationService cancellations, BookingRepository bookings,
                                  RefundRepository refunds, NotificationRepository notifications) {
        this.cancellations = cancellations;
        this.bookings = bookings;
        this.refunds = refunds;
        this.notifications = notifications;
    }

    @GetMapping("/bookings/{id}")
    public Map<String, String> booking(@PathVariable long id) {
        Booking booking = bookings.findById(id).orElseThrow();
        return Map.of("id", booking.getId().toString(), "status", booking.getStatus());
    }

    @PostMapping("/bookings/{id}/cancellations")
    public Map<String, String> cancel(@PathVariable long id,
                                      @RequestHeader("Idempotency-Key") String key,
                                      @RequestBody(required = false) Map<String, String> body) {
        String note = body == null ? null : body.get("note");
        return cancellations.cancel(id, key, note);
    }

    @GetMapping("/bookings/{id}/refunds")
    public List<Refund> refunds(@PathVariable long id) {
        return refunds.findByBookingId(id);
    }

    @GetMapping("/bookings/{id}/notifications")
    public List<Notification> notifications(@PathVariable long id) {
        return notifications.findByBookingId(id);
    }
}
