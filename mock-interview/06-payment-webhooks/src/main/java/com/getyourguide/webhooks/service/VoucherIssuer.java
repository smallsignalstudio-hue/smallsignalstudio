package com.getyourguide.webhooks.service;

import com.getyourguide.webhooks.model.Voucher;
import com.getyourguide.webhooks.repository.VoucherRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class VoucherIssuer {

    private final VoucherRepository vouchers;

    public VoucherIssuer(VoucherRepository vouchers) {
        this.vouchers = vouchers;
    }

    public void issue(long bookingId, String pspReference) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        String code = "V-" + pspReference + "-" + UUID.randomUUID().toString().substring(0, 8);
        vouchers.save(Voucher.of(bookingId, pspReference, code));
    }
}
