package com.getyourguide.webhooks.regression;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.getyourguide.webhooks.TestcontainersConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.HexFormat;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Import(TestcontainersConfiguration.class)
class BugRegressionTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper json;

    @Test
    void duplicateAuthorisationIsFastAndIssuesOneVoucher() throws Exception {
        String raw = auth(1, "DUP-1", "EUR", 1050);
        long started = System.nanoTime();
        notify(raw).andExpect(status().isOk());
        long millis = (System.nanoTime() - started) / 1_000_000;
        assertThat(millis).isLessThan(1200);
        notify(raw).andExpect(status().isOk());
        assertThat(vouchers(1)).hasSize(1);
    }

    @Test
    void capturedBookingDoesNotMoveBackwards() throws Exception {
        notify(auth(3, "LATE-3", "EUR", 2000)).andExpect(status().isOk());
        assertThat(statusOf(3)).isEqualTo("CAPTURED");
    }

    @Test
    void signatureIsOverTheRawBody() throws Exception {
        String raw = "{\"eventCode\":\"AUTHORISATION\",\"pspReference\":\"FEE-1\",\"bookingId\":7,"
                + "\"merchantAccount\":\"GYG-EU\",\"live\":true,\"amount\":{\"value\":1050,\"currency\":\"EUR\"},\"pspFee\":10.50}";
        notify(raw).andExpect(status().isOk());
        assertThat(statusOf(7)).isEqualTo("AUTHORISED");
        assertThat(charged(7)).isEqualByComparingTo("10.50");
    }

    @Test
    void yenIsNotDividedByOneHundred() throws Exception {
        notify(auth(2, "JPY-2", "JPY", 1000)).andExpect(status().isOk());
        assertThat(charged(2)).isEqualByComparingTo("1000");
    }

    @Test
    void bahrainiFilsUseThreeDecimalPlaces() throws Exception {
        notify(auth(4, "BHD-4", "BHD", 1234)).andExpect(status().isOk());
        assertThat(charged(4)).isEqualByComparingTo("1.234");
    }

    @Test
    void unknownEventIsAccepted() throws Exception {
        String raw = "{\"eventCode\":\"REPORT_AVAILABLE\",\"pspReference\":\"R-5\",\"bookingId\":5,"
                + "\"merchantAccount\":\"GYG-EU\",\"live\":true}";
        notify(raw).andExpect(status().isOk());
        assertThat(statusOf(5)).isEqualTo("PENDING");
    }

    private org.springframework.test.web.servlet.ResultActions notify(String raw) throws Exception {
        return mockMvc.perform(post("/webhooks/psp")
                .header("X-Signature", sign(raw))
                .contentType(MediaType.APPLICATION_JSON)
                .content(raw));
    }

    private String statusOf(long id) throws Exception {
        return json.readTree(mockMvc.perform(get("/bookings/{id}", id)).andReturn().getResponse().getContentAsString())
                .get("status").asText();
    }

    private BigDecimal charged(long id) throws Exception {
        JsonNode node = json.readTree(mockMvc.perform(get("/bookings/{id}", id)).andReturn().getResponse().getContentAsString());
        return new BigDecimal(node.get("chargedAmount").asText());
    }

    private JsonNode vouchers(long id) throws Exception {
        String body = "[]";
        for (int i = 0; i < 40; i++) {
            body = mockMvc.perform(get("/bookings/{id}/vouchers", id)).andReturn().getResponse().getContentAsString();
            if (!"[]".equals(body)) {
                break;
            }
            Thread.sleep(50);
        }
        return json.readTree(body);
    }

    private static String auth(long bookingId, String psp, String currency, int minor) {
        return "{\"eventCode\":\"AUTHORISATION\",\"pspReference\":\"" + psp + "\",\"bookingId\":" + bookingId
                + ",\"merchantAccount\":\"GYG-EU\",\"live\":true,\"amount\":{\"value\":" + minor
                + ",\"currency\":\"" + currency + "\"}}";
    }

    private static String sign(String raw) throws Exception {
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec("test-secret".getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
        return HexFormat.of().formatHex(mac.doFinal(raw.getBytes(StandardCharsets.UTF_8)));
    }
}
