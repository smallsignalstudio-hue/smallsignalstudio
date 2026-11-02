package com.getyourguide.webhooks.repository;

import com.getyourguide.webhooks.model.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VoucherRepository extends JpaRepository<Voucher, Long> {
    List<Voucher> findByBookingId(long bookingId);
    Optional<Voucher> findByBookingIdAndPspReference(long bookingId, String pspReference);
}
