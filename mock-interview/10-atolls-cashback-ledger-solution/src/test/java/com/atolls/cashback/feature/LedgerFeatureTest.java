package com.atolls.cashback.feature;

import com.atolls.cashback.TestcontainersConfiguration;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Import(TestcontainersConfiguration.class)
@TestPropertySource(properties = "cashback.suite=feature")
class LedgerFeatureTest {
    @Autowired MockMvc mockMvc;

    @Test
    void payoutIsIdempotentAndDeclineGoesNegative() throws Exception {
        mockMvc.perform(post("/events").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"externalId\":\"cb-1\",\"userId\":40,\"status\":\"CONFIRMED\",\"amountCents\":1000,\"currency\":\"EUR\",\"fxRate\":\"1\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.availableCents").value(1000));

        MvcResult first = mockMvc.perform(post("/wallets/40/payouts").header("Idempotency-Key", "pay-40"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.paidCents").value(1000))
                .andExpect(jsonPath("$.availableCents").value(0))
                .andExpect(jsonPath("$.pendingCents").value(0))
                .andReturn();
        int payoutId = ((Number) JsonPath.read(first.getResponse().getContentAsString(), "$.payoutId")).intValue();

        mockMvc.perform(post("/wallets/40/payouts").header("Idempotency-Key", "pay-40"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.payoutId").value(payoutId))
                .andExpect(jsonPath("$.paidCents").value(1000))
                .andExpect(jsonPath("$.availableCents").value(0));
        mockMvc.perform(get("/wallets/40/ledger")).andExpect(jsonPath("$.length()").value(4));

        mockMvc.perform(post("/events").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"externalId\":\"cb-1\",\"userId\":40,\"status\":\"DECLINED\",\"amountCents\":1000,\"currency\":\"EUR\",\"fxRate\":\"1\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.availableCents").value(-1000));

        String ledger = mockMvc.perform(get("/wallets/40/ledger"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(6))
                .andReturn().getResponse().getContentAsString();
        List<Number> all = JsonPath.read(ledger, "$[*].amountCents");
        List<Number> user = JsonPath.read(ledger, "$[?(@.account=='USER')].amountCents");
        assertThat(all.stream().mapToInt(Number::intValue).sum()).isZero();
        assertThat(user.stream().mapToInt(Number::intValue).sum()).isEqualTo(-1000);
        mockMvc.perform(get("/wallets/40")).andExpect(jsonPath("$.availableCents").value(-1000));
    }
}
