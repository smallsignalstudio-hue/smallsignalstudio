package com.getyourguide.cancellation.feature;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static com.github.tomakehurst.wiremock.client.WireMock.containing;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.serverError;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Import(com.getyourguide.cancellation.TestcontainersConfiguration.class)
class SlotCancellationTest {

    static final WireMockServer PSP = new WireMockServer(WireMockConfiguration.options().dynamicPort());

    static {
        PSP.start();
        PSP.stubFor(com.github.tomakehurst.wiremock.client.WireMock.post(urlPathEqualTo("/refunds")).willReturn(ok()));
        PSP.stubFor(com.github.tomakehurst.wiremock.client.WireMock.post(urlPathEqualTo("/refunds"))
                .atPriority(1)
                .withRequestBody(containing("bounce@supplier.test")).willReturn(serverError()));
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

    @Test
    void supplierCancellationIsIdempotentAndReportsPartialFailure() throws Exception {
        postSlot().andExpect(status().isOk())
                .andExpect(jsonPath("$.refunded").value(org.hamcrest.Matchers.containsInAnyOrder(10, 11)))
                .andExpect(jsonPath("$.failed").value(org.hamcrest.Matchers.contains(12)));
        postSlot().andExpect(jsonPath("$.failed").value(org.hamcrest.Matchers.contains(12)));
        PSP.verify(1, postRequestedFor(urlPathEqualTo("/refunds")).withRequestBody(containing("\"bookingId\":10")));
        PSP.verify(1, postRequestedFor(urlPathEqualTo("/refunds")).withRequestBody(containing("bounce@supplier.test")));
    }

    private org.springframework.test.web.servlet.ResultActions postSlot() throws Exception {
        return mockMvc.perform(post("/slots/77/cancellations").header("Idempotency-Key", "storm"));
    }
}
