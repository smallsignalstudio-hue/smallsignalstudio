package com.getyourguide.pricing.regression;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.getyourguide.pricing.TestcontainersConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Import(TestcontainersConfiguration.class)
public class BugRegressionTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper json;

    // PRC-401
    @Test
    void eightAddOnsAreEightyCents() throws Exception {
        assertThat(total("EUR", null, "ADDON", 8)).isEqualTo("0.80");
    }

    // PRC-402
    @Test
    void partyOfTenGetsTheGroupRate() throws Exception {
        assertThat(total("EUR", null, "ADULT", 10)).isEqualTo("150.00");
    }

    // PRC-403
    @Test
    void yenDiscountHasNoFractionDigits() throws Exception {
        assertThat(total("JPY", "TEN", "ADULT", 3)).isEqualTo("899");
    }

    // Hidden: BigDecimal.equals scale
    @Test
    void priceBandMatchesRegardlessOfScale() throws Exception {
        String raw = mockMvc.perform(get("/price-bands/match").param("currency", "EUR").param("amount", "10.00"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertThat(json.readTree(raw).get("flatTotal").asText()).isEqualTo("9.00");
    }

    // Hidden: discount below zero
    @Test
    void discountDoesNotGoBelowZero() throws Exception {
        assertThat(total("EUR", "OVER", "ADULT", 1)).isEqualTo("0.00");
    }

    // Hidden: min order after discount
    @Test
    void minimumIsCheckedBeforeTheDiscount() throws Exception {
        assertThat(total("EUR", "MIN100", "ADULT", 5)).isEqualTo("80.00");
    }

    // Hidden: children counted in the group
    @Test
    void childrenDoNotCountTowardTheGroup() throws Exception {
        String raw = mockMvc.perform(post("/quotes").contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"customerId":"c1","currency":"EUR","lines":[
                                  {"category":"ADULT","quantity":9},
                                  {"category":"CHILD","quantity":2}
                                ]}
                                """))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertThat(json.readTree(raw).get("total").asText()).isEqualTo("196.00");
    }

    private String total(String currency, String promo, String category, int quantity) throws Exception {
        String promoField = promo == null ? "" : ",\"promoCode\":\"" + promo + "\"";
        String raw = mockMvc.perform(post("/quotes").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"customerId\":\"c1\",\"currency\":\"" + currency + "\"" + promoField
                                + ",\"lines\":[{\"category\":\"" + category + "\",\"quantity\":" + quantity + "}]}"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        JsonNode body = json.readTree(raw);
        return body.get("total").asText();
    }
}
