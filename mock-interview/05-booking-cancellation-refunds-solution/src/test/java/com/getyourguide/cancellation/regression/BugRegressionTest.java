package com.getyourguide.cancellation.regression;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.getyourguide.cancellation.TestcontainersConfiguration;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;

import static com.github.tomakehurst.wiremock.client.WireMock.containing;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Import({TestcontainersConfiguration.class, BugRegressionTest.FixedClock.class})
class BugRegressionTest {

    static final WireMockServer PSP = new WireMockServer(WireMockConfiguration.options().dynamicPort());

    static {
        PSP.start();
        PSP.stubFor(com.github.tomakehurst.wiremock.client.WireMock.post(urlPathEqualTo("/refunds")).willReturn(ok()));
    }

    @DynamicPropertySource
    static void psp(DynamicPropertyRegistry registry) {
        registry.add("psp.base-url", () -> "http://localhost:" + PSP.port());
    }

    @AfterAll
    static void stop() {
        PSP.stop();
    }

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper json;

    // CXL-501
    @Test
    void fullRefundWhenMoreThanTwentyFourHoursRemain() throws Exception {
        assertThat(amount(2, "day-and-a-half")).isEqualByComparingTo("100.00");
    }

    // Hidden: activity zone, not the server clock
    @Test
    void cutoffUsesTheActivityZone() throws Exception {
        assertThat(amount(1, "tokyo")).isEqualByComparingTo("20.00");
    }

    // Hidden: 50% of what was paid, not the list price
    @Test
    void partialRefundIsHalfOfTheAmountPaid() throws Exception {
        assertThat(amount(6, "same-day")).isEqualByComparingTo("20.00");
    }

    // CXL-502
    @Test
    void sameIdempotencyKeyRefundsOnce() throws Exception {
        cancel(8, "retry-me", null);
        cancel(8, "retry-me", null);
        assertThat(mockMvc.perform(get("/bookings/8/refunds")).andReturn().getResponse().getContentAsString())
                .containsOnlyOnce("retry-me");
        PSP.verify(1, postRequestedFor(urlPathEqualTo("/refunds")).withRequestBody(containing("\"bookingId\":8")));
    }

    // CXL-503
    @Test
    void failedSaveDoesNotMoveMoney() throws Exception {
        mockMvc.perform(post("/bookings/9/cancellations").header("Idempotency-Key", "long-note")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"note\":\"" + "x".repeat(80) + "\"}"))
                .andExpect(status().is5xxServerError());
        assertThat(statusOf(9)).isEqualTo("CONFIRMED");
        PSP.verify(0, postRequestedFor(urlPathEqualTo("/refunds")).withRequestBody(containing("\"bookingId\":9")));
    }

    // Hidden: cancelled and completed stay cancelled
    @Test
    void cancelledBookingCannotBeCancelledAgain() throws Exception {
        mockMvc.perform(post("/bookings/3/cancellations").header("Idempotency-Key", "again")
                        .contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isConflict());
        assertThat(mockMvc.perform(get("/bookings/3/refunds")).andReturn().getResponse().getContentAsString())
                .isEqualTo("[]");
    }

    // Hidden: async mail must not swallow a lazy-load failure
    @Test
    void travellerIsEmailed() throws Exception {
        cancel(5, "mail", "ok");
        String body = "[]";
        for (int i = 0; i < 40 && "[]".equals(body); i++) {
            Thread.sleep(50);
            body = mockMvc.perform(get("/bookings/5/notifications")).andReturn().getResponse().getContentAsString();
        }
        assertThat(body).contains("ada@example.test");
    }

    private BigDecimal amount(long bookingId, String key) throws Exception {
        JsonNode node = json.readTree(cancel(bookingId, key, null));
        return new BigDecimal(node.get("amount").asText());
    }

    private String cancel(long bookingId, String key, String note) throws Exception {
        String content = note == null ? "{}" : "{\"note\":\"" + note + "\"}";
        return mockMvc.perform(post("/bookings/{id}/cancellations", bookingId)
                        .header("Idempotency-Key", key)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }

    private String statusOf(long id) throws Exception {
        return json.readTree(mockMvc.perform(get("/bookings/{id}", id)).andReturn().getResponse().getContentAsString())
                .get("status").asText();
    }

    @TestConfiguration
    public static class FixedClock {
        @Bean
        @Primary
        Clock fixedClock() {
            return Clock.fixed(Instant.parse("2026-06-01T12:00:00Z"), ZoneOffset.UTC);
        }
    }
}
