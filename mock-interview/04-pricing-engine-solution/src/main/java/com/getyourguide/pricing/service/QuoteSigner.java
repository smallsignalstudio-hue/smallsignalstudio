package com.getyourguide.pricing.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.Instant;
import java.util.HexFormat;
import java.util.UUID;

@Component
public class QuoteSigner {

    private final byte[] secret;

    public QuoteSigner(@Value("${pricing.quote-secret}") String secret) {
        this.secret = secret.getBytes(StandardCharsets.UTF_8);
    }

    public String sign(UUID id, String total, String currency, Instant expiresAt) {
        String payload = id + "|" + total + "|" + currency + "|" + expiresAt.toEpochMilli();
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(secret, "HmacSHA256"));
            return HexFormat.of().formatHex(mac.doFinal(payload.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public boolean matches(UUID id, String total, String currency, Instant expiresAt, String provided) {
        if (provided == null) {
            return false;
        }
        byte[] expected = sign(id, total, currency, expiresAt).getBytes(StandardCharsets.UTF_8);
        byte[] actual = provided.getBytes(StandardCharsets.UTF_8);
        return MessageDigest.isEqual(expected, actual);
    }
}
