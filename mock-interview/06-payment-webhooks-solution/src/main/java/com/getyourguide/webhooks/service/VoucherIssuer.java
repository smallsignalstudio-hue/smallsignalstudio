package com.getyourguide.webhooks.service;

import com.getyourguide.webhooks.model.Voucher;
import com.getyourguide.webhooks.repository.VoucherRepository;
import org.springframework.stereotype.Component;

@Component
public class VoucherIssuer {

    private final VoucherRepository vouchers;

    public VoucherIssuer(VoucherRepository vouchers) {
        this.vouchers = vouchers;
    }

    public void issue(long bookingId, String pspReference) {
        if (vouchers.findByBookingIdAndPspReference(bookingId, pspReference).isPresent()) {
            return;
        }
        vouchers.save(Voucher.of(bookingId, pspReference, "V-" + pspReference));
    }
}
