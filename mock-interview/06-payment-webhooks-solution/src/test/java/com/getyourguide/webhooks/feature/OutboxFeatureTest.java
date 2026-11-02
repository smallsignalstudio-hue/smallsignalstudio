package com.getyourguide.webhooks.feature;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.getyourguide.webhooks.TestcontainersConfiguration;
import com.getyourguide.webhooks.repository.VoucherRepository;
import com.getyourguide.webhooks.service.VoucherIssuer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.HexFormat;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Import({TestcontainersConfiguration.class, OutboxFeatureTest.CaptureIssuer.class})
class OutboxFeatureTest {

    static volatile String issuerThread;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper json;

    @Test
    void voucherIsIssuedOnceOffTheRequestThread() throws Exception {
        String raw = "{\"eventCode\":\"AUTHORISATION\",\"pspReference\":\"FEAT-8\",\"bookingId\":8,"
                + "\"merchantAccount\":\"GYG-EU\",\"live\":true,\"amount\":{\"value\":1500,\"currency\":\"EUR\"}}";
        String caller = Thread.currentThread().getName();
        notify(raw).andExpect(status().isOk());
        String vouchers = "[]";
        for (int i = 0; i < 40 && "[]".equals(vouchers); i++) {
            Thread.sleep(50);
            vouchers = mockMvc.perform(get("/bookings/8/vouchers")).andReturn().getResponse().getContentAsString();
        }
        assertThat(json.readTree(vouchers)).hasSize(1);
        assertThat(issuerThread).isNotBlank().isNotEqualTo(caller);
        notify(raw).andExpect(status().isOk());
        mockMvc.perform(post("/internal/outbox/flush")).andExpect(status().isOk());
        assertThat(json.readTree(mockMvc.perform(get("/bookings/8/vouchers")).andReturn().getResponse().getContentAsString())).hasSize(1);
        assertThat(mockMvc.perform(get("/bookings/8/outbox")).andReturn().getResponse().getContentAsString()).contains("PUBLISHED");
    }

    private org.springframework.test.web.servlet.ResultActions notify(String raw) throws Exception {
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec("test-secret".getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
        String signature = HexFormat.of().formatHex(mac.doFinal(raw.getBytes(StandardCharsets.UTF_8)));
        return mockMvc.perform(post("/webhooks/psp").header("X-Signature", signature)
                .contentType(MediaType.APPLICATION_JSON).content(raw));
    }

    @TestConfiguration
    public static class CaptureIssuer {
        @Bean
        @Primary
        VoucherIssuer voucherIssuer(VoucherRepository vouchers) {
            return new VoucherIssuer(vouchers) {
                @Override
                public void issue(long bookingId, String pspReference) {
                    issuerThread = Thread.currentThread().getName();
                    super.issue(bookingId, pspReference);
                }
            };
        }
    }
}
