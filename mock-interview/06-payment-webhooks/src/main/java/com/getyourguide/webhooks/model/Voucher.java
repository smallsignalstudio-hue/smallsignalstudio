package com.getyourguide.webhooks.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "voucher")
public class Voucher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "booking_id")
    private Long bookingId;
    @Column(name = "psp_reference")
    private String pspReference;
    private String code;

    public static Voucher of(Long bookingId, String pspReference, String code) {
        Voucher voucher = new Voucher();
        voucher.bookingId = bookingId;
        voucher.pspReference = pspReference;
        voucher.code = code;
        return voucher;
    }

    public String getCode() {
        return code;
    }

    public String getPspReference() {
        return pspReference;
    }
}
