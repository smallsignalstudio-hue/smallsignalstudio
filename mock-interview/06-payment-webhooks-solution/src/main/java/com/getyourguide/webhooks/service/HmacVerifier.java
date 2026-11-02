package com.getyourguide.webhooks.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HexFormat;

@Component
public class HmacVerifier {

    private final byte[] secret;

    public HmacVerifier(@Value("${psp.hmac-secret}") String secret) {
        this.secret = secret.getBytes(StandardCharsets.UTF_8);
    }

    public void verify(String raw, String signature) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(secret, "HmacSHA256"));
            byte[] expected = mac.doFinal(raw.getBytes(StandardCharsets.UTF_8));
            byte[] actual = HexFormat.of().parseHex(signature);
            if (actual.length != expected.length || !MessageDigest.isEqual(actual, expected)) {
                throw new SignatureException();
            }
        } catch (SignatureException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new SignatureException();
        }
    }
}
