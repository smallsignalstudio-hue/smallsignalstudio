package com.getyourguide.catalog.feature;

import com.getyourguide.catalog.TestcontainersConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Import(TestcontainersConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class SupplierStatisticsTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void returnsAggregatesPerSupplierOrderedByRatingThenCount() throws Exception {
        mockMvc.perform(get("/suppliers/statistics"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements").value(4))
                .andExpect(jsonPath("$.items", hasSize(4)))
                // Bavarian Castle Tours: 4.7, 4.5, 4.8, 4.6, 4.9 -> 4.70 over 5 activities
                .andExpect(jsonPath("$.items[0].supplierName").value("Bavarian Castle Tours"))
                .andExpect(jsonPath("$.items[0].activityCount").value(5))
                .andExpect(jsonPath("$.items[0].averageRating").value(4.7))
                .andExpect(jsonPath("$.items[0].cheapestPrices[0].currency").value("EUR"))
                .andExpect(jsonPath("$.items[0].cheapestPrices[0].amount").value(33.0));
    }

    @Test
    void averageIgnoresUnratedActivitiesButCountIncludesThem() throws Exception {
        mockMvc.perform(get("/suppliers/statistics"))
                .andExpect(jsonPath("$.items[?(@.supplierName == 'Berlin Walks GmbH')].activityCount").value(9))
                .andExpect(jsonPath("$.items[?(@.supplierName == 'Berlin Walks GmbH')].cheapestPrices[0].amount")
                        .value(16.0));
    }

    @Test
    void neverMixesCurrenciesInTheCheapestPrice() throws Exception {
        jdbcTemplate.update("""
                INSERT INTO activity (id, title, price, currency, rating, special_offer, supplier_id)
                VALUES (99001, 'Rome: Budget Gelato Walk', 9.50, 'CHF', 4.0, FALSE, 4)
                """);
        try {
            mockMvc.perform(get("/suppliers/statistics"))
                    .andExpect(jsonPath("$.items[?(@.supplierName == 'Roma Antica Experiences')].cheapestPrices[*].currency")
                            .value(org.hamcrest.Matchers.containsInAnyOrder("CHF", "EUR")));
        } finally {
            jdbcTemplate.update("DELETE FROM activity WHERE id = 99001");
        }
    }

    @Test
    void supplierWithoutActivitiesIsListedLast() throws Exception {
        jdbcTemplate.update("INSERT INTO supplier (id, name) VALUES (99, 'Brand New Supplier')");
        try {
            mockMvc.perform(get("/suppliers/statistics"))
                    .andExpect(jsonPath("$.items[4].supplierName").value("Brand New Supplier"))
                    .andExpect(jsonPath("$.items[4].activityCount").value(0))
                    .andExpect(jsonPath("$.items[4].averageRating").value(nullValue()))
                    .andExpect(jsonPath("$.items[4].cheapestPrices", hasSize(0)));
        } finally {
            jdbcTemplate.update("DELETE FROM supplier WHERE id = 99");
        }
    }

    @Test
    void paginates() throws Exception {
        mockMvc.perform(get("/suppliers/statistics").param("page", "1").param("size", "3"))
                .andExpect(jsonPath("$.items", hasSize(1)))
                .andExpect(jsonPath("$.totalPages").value(2));
        mockMvc.perform(get("/suppliers/statistics").param("size", "0"))
                .andExpect(status().isBadRequest());
    }
}
