package com.getyourguide.supplier.regression;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.getyourguide.supplier.TestcontainersConfiguration;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.serverError;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Import(TestcontainersConfiguration.class)
class BugRegressionTest {
    static final WireMockServer SUPPLIER = new WireMockServer(WireMockConfiguration.options().dynamicPort());
    static { SUPPLIER.start(); }

    @DynamicPropertySource
    static void urls(DynamicPropertyRegistry registry) {
        registry.add("supplier.base-url", () -> "http://localhost:" + SUPPLIER.port());
        registry.add("payment.base-url", () -> "http://localhost:" + SUPPLIER.port());
    }

    @AfterAll
    static void stop() { SUPPLIER.stop(); }

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper json;

    @BeforeEach
    void reset() { SUPPLIER.resetAll(); }

    @Test
    void slowSupplierFailsFast() throws Exception {
        SUPPLIER.stubFor(WireMock.post(urlPathEqualTo("/reservations"))
                .willReturn(aResponse().withStatus(200).withBody("{\"reservationId\":\"R-slow\"}").withFixedDelay(3000)));
        SUPPLIER.stubFor(WireMock.post(urlPathEqualTo("/payments")).willReturn(okJson("{}")));
        SUPPLIER.stubFor(WireMock.post(urlPathEqualTo("/bookings")).willReturn(okJson("{\"bookingId\":\"B-slow\"}")));
        long started = System.nanoTime();
        mockMvc.perform(order(1, "2026-06-03T10:00", "ok", "slow-1")).andExpect(status().isGatewayTimeout());
        assertThat((System.nanoTime() - started) / 1_000_000).isLessThan(2000);
    }

    @Test
    void bookIsNotRetriedAfterAServerError() throws Exception {
        stubReserveAndPay("R-once");
        SUPPLIER.stubFor(WireMock.post(urlPathEqualTo("/bookings")).inScenario("book")
                .whenScenarioStateIs(com.github.tomakehurst.wiremock.stubbing.Scenario.STARTED)
                .willReturn(serverError()).willSetStateTo("again"));
        SUPPLIER.stubFor(WireMock.post(urlPathEqualTo("/bookings")).inScenario("book")
                .whenScenarioStateIs("again")
                .willReturn(okJson("{\"bookingId\":\"B-2\"}")));
        MvcResult result = mockMvc.perform(order(1, "2026-06-04T10:00", "ok", "once-1")).andExpect(status().isOk()).andReturn();
        assertThat(json.readTree(result.getResponse().getContentAsString()).get("status").asText()).isEqualTo("UNKNOWN");
        SUPPLIER.verify(1, postRequestedFor(urlPathEqualTo("/bookings")));
    }

    @Test
    void failedPaymentReleasesTheReservation() throws Exception {
        SUPPLIER.stubFor(WireMock.post(urlPathEqualTo("/reservations")).willReturn(okJson("{\"reservationId\":\"R-pay\"}")));
        SUPPLIER.stubFor(WireMock.post(urlPathEqualTo("/payments")).willReturn(aResponse().withStatus(402)));
        SUPPLIER.stubFor(WireMock.post(urlPathEqualTo("/reservations/R-pay/cancellations")).willReturn(okJson("{}")));
        mockMvc.perform(order(1, "2026-06-05T10:00", "fail", "pay-1"))
                .andExpect(status().isPaymentRequired())
                .andExpect(jsonPath("$.reservationStatus").value("RELEASED"));
        SUPPLIER.verify(1, postRequestedFor(urlPathEqualTo("/reservations/R-pay/cancellations")));
    }

    @Test
    void failedAvailabilityIsNotCached() throws Exception {
        SUPPLIER.stubFor(WireMock.get(urlPathEqualTo("/availability")).willReturn(serverError()));
        mockMvc.perform(get("/activities/1/slots")).andReturn();
        SUPPLIER.resetAll();
        SUPPLIER.stubFor(WireMock.get(urlPathEqualTo("/availability")).willReturn(okJson("{\"slots\":[\"10:00\"]}")));
        mockMvc.perform(get("/activities/1/slots"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").value("10:00"));
    }

    @Test
    void httpOkWithErrorCodeIsNotABooking() throws Exception {
        stubReserveAndPay("R-err");
        SUPPLIER.stubFor(WireMock.post(urlPathEqualTo("/bookings")).willReturn(okJson("{\"errorCode\":\"NO_AVAILABILITY\"}")));
        SUPPLIER.stubFor(WireMock.post(urlPathEqualTo("/reservations/R-err/cancellations")).willReturn(okJson("{}")));
        mockMvc.perform(order(1, "2026-06-06T10:00", "ok", "err-1"))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.reservationStatus").value("RELEASED"));
    }

    @Test
    void overlapNightUsesTheLaterOffset() throws Exception {
        stubReserveAndPay("R-dst");
        SUPPLIER.stubFor(WireMock.post(urlPathEqualTo("/bookings")).willReturn(okJson("{\"bookingId\":\"B-dst\"}")));
        mockMvc.perform(order(1, "2026-10-25T02:30", "ok", "dst-1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.startAt").value("2026-10-25T01:30:00Z"))
                .andExpect(jsonPath("$.status").value("BOOKED"));
    }

    private void stubReserveAndPay(String reservationId) {
        SUPPLIER.stubFor(WireMock.post(urlPathEqualTo("/reservations")).willReturn(okJson("{\"reservationId\":\"" + reservationId + "\"}")));
        SUPPLIER.stubFor(WireMock.post(urlPathEqualTo("/payments")).willReturn(okJson("{}")));
    }

    private org.springframework.test.web.servlet.RequestBuilder order(long activityId, String local, String pay, String key) {
        String body = "{\"activityId\":" + activityId + ",\"localStart\":\"" + local + "\",\"partySize\":2,\"pay\":\"" + pay + "\"}";
        return post("/orders").header("Idempotency-Key", key).contentType(MediaType.APPLICATION_JSON).content(body);
    }
}
