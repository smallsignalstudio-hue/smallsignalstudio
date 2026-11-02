package com.getyourguide.cancellation.service;

import com.getyourguide.cancellation.model.Booking;
import com.getyourguide.cancellation.model.SlotCancellation;
import com.getyourguide.cancellation.repository.BookingRepository;
import com.getyourguide.cancellation.repository.SlotCancellationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class SlotCancellationService {

    private final BookingRepository bookings;
    private final SlotCancellationRepository jobs;
    private final CancellationService cancellations;

    public SlotCancellationService(BookingRepository bookings, SlotCancellationRepository jobs,
                                   CancellationService cancellations) {
        this.bookings = bookings;
        this.jobs = jobs;
        this.cancellations = cancellations;
    }

    public Map<String, List<Long>> cancelSlot(long slotId, String idempotencyKey) {
        List<SlotCancellation> stored = jobs.findByIdempotencyKeyOrderByBookingIdAsc(idempotencyKey);
        if (!stored.isEmpty()) {
            return report(stored);
        }
        List<SlotCancellation> rows = new ArrayList<>();
        for (Booking booking : bookings.findBySlotIdAndStatus(slotId, "CONFIRMED")) {
            String outcome = "REFUNDED";
            try {
                cancellations.cancel(booking.getId(), idempotencyKey + ":" + booking.getId(), "slot closed");
            } catch (RuntimeException ex) {
                outcome = "FAILED";
            }
            rows.add(jobs.save(SlotCancellation.of(idempotencyKey, booking.getId(), outcome)));
        }
        return report(rows);
    }

    private static Map<String, List<Long>> report(List<SlotCancellation> rows) {
        List<Long> refunded = new ArrayList<>();
        List<Long> failed = new ArrayList<>();
        for (SlotCancellation row : rows) {
            if ("FAILED".equals(row.getOutcome())) {
                failed.add(row.getBookingId());
            } else {
                refunded.add(row.getBookingId());
            }
        }
        Map<String, List<Long>> body = new LinkedHashMap<>();
        body.put("refunded", refunded);
        body.put("failed", failed);
        return body;
    }
}
