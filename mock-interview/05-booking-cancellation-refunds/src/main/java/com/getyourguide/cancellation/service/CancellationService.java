package com.getyourguide.cancellation.service;

import com.getyourguide.cancellation.model.Booking;
import com.getyourguide.cancellation.model.Refund;
import com.getyourguide.cancellation.repository.BookingRepository;
import com.getyourguide.cancellation.repository.RefundRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class CancellationService {

    private final BookingRepository bookings;
    private final RefundRepository refunds;
    private final PspClient psp;
    private final Mailer mailer;
    private final Clock clock;

    public CancellationService(BookingRepository bookings, RefundRepository refunds, PspClient psp, Mailer mailer, Clock clock) {
        this.bookings = bookings;
        this.refunds = refunds;
        this.psp = psp;
        this.mailer = mailer;
        this.clock = clock;
    }

    @Transactional
    public Map<String, String> cancel(long bookingId, String idempotencyKey, String note) {
        Booking booking = bookings.findById(bookingId).orElseThrow();
        BigDecimal amount = refundAmount(booking);
        psp.refund(booking.getId(), amount, booking.getCustomer().getEmail());
        booking.setStatus("CANCELLED");
        booking.setNote(note);
        bookings.saveAndFlush(booking);
        refunds.save(Refund.of(bookingId, amount, idempotencyKey));
        mailer.notifyCancelled(bookingId);
        return Map.of("amount", amount.toPlainString());
    }

    private BigDecimal refundAmount(Booking booking) {
        LocalDateTime now = LocalDateTime.now(clock);
        long hours = Duration.between(now, booking.getStartAt()).toHours();
        boolean full = hours > 24;
        BigDecimal fee = BigDecimal.ZERO;
        if (full) {
            return booking.getPaidAmount().subtract(fee);
        }
        return booking.getListPrice().multiply(new BigDecimal("0.5")).subtract(fee);
    }
}
