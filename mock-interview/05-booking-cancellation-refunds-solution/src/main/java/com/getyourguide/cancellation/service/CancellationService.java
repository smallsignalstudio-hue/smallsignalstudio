package com.getyourguide.cancellation.service;

import com.getyourguide.cancellation.model.Booking;
import com.getyourguide.cancellation.model.Refund;
import com.getyourguide.cancellation.repository.BookingRepository;
import com.getyourguide.cancellation.repository.RefundRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;
import java.time.Clock;
import java.util.Map;
import java.util.Optional;

@Service
public class CancellationService {

    private final BookingRepository bookings;
    private final RefundRepository refunds;
    private final PspClient psp;
    private final Mailer mailer;
    private final Clock clock;
    private final TransactionTemplate transactions;

    public CancellationService(BookingRepository bookings, RefundRepository refunds, PspClient psp, Mailer mailer,
                               Clock clock, TransactionTemplate transactions) {
        this.bookings = bookings;
        this.refunds = refunds;
        this.psp = psp;
        this.mailer = mailer;
        this.clock = clock;
        this.transactions = transactions;
    }

    public Map<String, String> cancel(long bookingId, String idempotencyKey, String note) {
        Optional<Refund> existing = refunds.findByIdempotencyKey(idempotencyKey);
        if (existing.isPresent()) {
            return Map.of("amount", existing.get().getAmount().toPlainString());
        }
        String email = transactions.execute(status -> persist(bookingId, idempotencyKey, note));
        BigDecimal amount = refunds.findByIdempotencyKey(idempotencyKey).orElseThrow().getAmount();
        psp.refund(bookingId, amount, email);
        mailer.notifyCancelled(bookingId, email);
        return Map.of("amount", amount.toPlainString());
    }

    private String persist(long bookingId, String idempotencyKey, String note) {
        Booking booking = bookings.findById(bookingId).orElseThrow();
        BookingStatus.of(booking.getStatus()).requireCancellable(bookingId);
        BigDecimal amount = HoursBeforeStartPolicy.refund(booking, clock.instant());
        String email = booking.getCustomer().getEmail();
        booking.setStatus("CANCELLED");
        booking.setNote(note);
        bookings.saveAndFlush(booking);
        refunds.saveAndFlush(Refund.of(bookingId, amount, idempotencyKey));
        return email;
    }
}
