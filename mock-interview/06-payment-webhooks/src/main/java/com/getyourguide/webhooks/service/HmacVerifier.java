package com.getyourguide.webhooks.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.HexFormat;

@Component
public class HmacVerifier {

    private final ObjectMapper json;
    private final byte[] secret;

    public HmacVerifier(ObjectMapper json, @Value("${psp.hmac-secret}") String secret) {
        this.json = json;
        this.secret = secret.getBytes(StandardCharsets.UTF_8);
    }

    public void verify(String raw, String signature) {
        try {
            String canonical = json.writeValueAsString(json.readTree(raw));
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(secret, "HmacSHA256"));
            String expected = HexFormat.of().formatHex(mac.doFinal(canonical.getBytes(StandardCharsets.UTF_8)));
            if (!expected.equals(signature)) {
                throw new SignatureException();
            }
        } catch (SignatureException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new SignatureException();
        }
    }
}
