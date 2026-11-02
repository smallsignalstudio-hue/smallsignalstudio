package com.getyourguide.supplier.feature;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.getyourguide.supplier.TestcontainersConfiguration;
import com.getyourguide.supplier.service.Bulkhead;
import com.getyourguide.supplier.service.CircuitBreaker;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;

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
class ReconciliationTest {
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

    @Test
    void unknownBookIsReconciledWithoutASecondPost() throws Exception {
        SUPPLIER.stubFor(WireMock.post(urlPathEqualTo("/reservations")).willReturn(okJson("{\"reservationId\":\"R-rec\"}")));
        SUPPLIER.stubFor(WireMock.post(urlPathEqualTo("/payments")).willReturn(okJson("{}")));
        SUPPLIER.stubFor(WireMock.post(urlPathEqualTo("/bookings")).willReturn(serverError()));
        String created = mockMvc.perform(post("/orders").header("Idempotency-Key", "recon-1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"activityId\":2,\"localStart\":\"2026-07-01T10:00\",\"partySize\":2,\"pay\":\"ok\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("UNKNOWN"))
                .andReturn().getResponse().getContentAsString();
        long id = json.readTree(created).get("id").asLong();
        SUPPLIER.stubFor(WireMock.get(urlPathEqualTo("/bookings")).willReturn(okJson("{\"bookingId\":\"B-9\"}")));
        mockMvc.perform(post("/internal/reconcile")).andExpect(status().isOk());
        mockMvc.perform(get("/orders/{id}", id))
                .andExpect(jsonPath("$.status").value("BOOKED"))
                .andExpect(jsonPath("$.supplierBookingId").value("B-9"));
        SUPPLIER.verify(1, postRequestedFor(urlPathEqualTo("/bookings")));
    }

    @Test
    void openCircuitStopsCallingTheSupplier() throws Exception {
        SUPPLIER.resetAll();
        SUPPLIER.stubFor(WireMock.post(urlPathEqualTo("/reservations"))
                .willReturn(aResponse().withStatus(200).withBody("{\"reservationId\":\"R\"}").withFixedDelay(3000)));
        for (int i = 0; i < 3; i++) {
            mockMvc.perform(post("/orders").header("Idempotency-Key", "open-" + i)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"activityId\":1,\"localStart\":\"2026-07-0" + (2 + i) + "T10:00\",\"partySize\":2,\"pay\":\"ok\"}"))
                    .andExpect(status().isGatewayTimeout());
        }
        mockMvc.perform(post("/orders").header("Idempotency-Key", "open-closed")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"activityId\":1,\"localStart\":\"2026-07-03T10:00\",\"partySize\":2,\"pay\":\"ok\"}"))
                .andExpect(status().isServiceUnavailable());
        SUPPLIER.verify(3, postRequestedFor(urlPathEqualTo("/reservations")));
    }
}
