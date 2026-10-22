package com.getyourguide.catalog.regression;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.getyourguide.catalog.TestcontainersConfiguration;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Black-box regression tests: they only use HTTP and the seeded data, so they can be copied into the
 * task project to demonstrate each bug.
 */
@SpringBootTest(properties = "spring.jpa.properties.hibernate.generate_statistics=true")
@AutoConfigureMockMvc
@Import(TestcontainersConfiguration.class)
class BugRegressionTest {

    private static final long ORPHAN_SUPPLIER_ACTIVITY = 40882;
    private static final long GYG_OPERATED_ACTIVITY = 40600;
    private static final int SEEDED_ACTIVITIES = 26;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    // CAT-101
    @Test
    void activityWhoseSupplierIsNotSyncedYetIsStillServed() throws Exception {
        mockMvc.perform(get("/activities/{id}", ORPHAN_SUPPLIER_ACTIVITY))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Berlin: Reichstag Dome and Government Quarter Tour"))
                .andExpect(jsonPath("$.supplierName").value(nullValue()))
                .andExpect(jsonPath("$.supplierLocation").value(nullValue()));
    }

    // CAT-101
    @Test
    void activityWithoutSupplierIsServed() throws Exception {
        mockMvc.perform(get("/activities/{id}", GYG_OPERATED_ACTIVITY))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.supplierName").value(nullValue()));
    }

    // CAT-101: the same mapping bug lives in search
    @Test
    void searchIncludesActivitiesWithMissingSupplier() throws Exception {
        mockMvc.perform(get("/activities/search").param("q", "Berlin"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].id").value(hasItem((int) ORPHAN_SUPPLIER_ACTIVITY)));
    }

    // CAT-102
    @Test
    void unknownActivityIsNotFound() throws Exception {
        mockMvc.perform(get("/activities/{id}", 999_999))
                .andExpect(status().isNotFound());
    }

    // CAT-103
    @Test
    void searchIsCaseInsensitive() throws Exception {
        List<Long> upper = ids(get("/activities/search").param("q", "Spree"));
        List<Long> lower = ids(get("/activities/search").param("q", "spree"));

        assertThat(lower).isNotEmpty().containsExactlyInAnyOrderElementsOf(upper);
        assertThat(lower).contains(25740L, 25742L, 25744L, 25746L);
    }

    // Hidden: an activity matching by title AND supplier name was returned twice
    @Test
    void searchDoesNotReturnDuplicates() throws Exception {
        List<Long> result = ids(get("/activities/search").param("q", "Spree"));

        assertThat(result).doesNotHaveDuplicates();
    }

    // Hidden: LIKE wildcards in user input
    @Test
    void searchTreatsWildcardsLiterally() throws Exception {
        assertThat(ids(get("/activities/search").param("q", "%"))).containsExactly(40508L);
        assertThat(ids(get("/activities/search").param("q", "_"))).isEmpty();
        assertThat(ids(get("/activities/search").param("q", "100%"))).containsExactly(40508L);
    }

    // Hidden: SQL injection in supplier search
    @Test
    void supplierSearchIsNotInjectable() throws Exception {
        String body = mockMvc.perform(get("/suppliers/search/{search}", "x' OR 1=1 --"))
                .andReturn().getResponse().getContentAsString();

        assertThat(body).doesNotContain("Berlin Walks GmbH");
    }

    // Hidden: money stored and calculated as double
    @Test
    void specialOfferPriceIsRoundedToCurrencyPrecision() throws Exception {
        String body = mockMvc.perform(get("/activities/{id}", 40502))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertThat(objectMapper.readTree(body).get("finalPrice").decimalValue()).isEqualByComparingTo("58.41");
        assertThat(body).doesNotContain("58.410000000000004");
    }

    // Hidden: invalid paging input becomes 500
    @Test
    void invalidPagingIsBadRequest() throws Exception {
        mockMvc.perform(get("/activities").param("page", "-1")).andExpect(status().isBadRequest());
        mockMvc.perform(get("/activities").param("size", "100000")).andExpect(status().isBadRequest());
    }

    // Hidden: NULL ratings sort first in Postgres (DESC = NULLS FIRST)
    @Test
    void unratedActivitiesAreNotRankedFirst() throws Exception {
        String body = mockMvc.perform(get("/activities").param("size", "1"))
                .andReturn().getResponse().getContentAsString();

        assertThat(objectMapper.readTree(body).get(0).get("rating").isNull()).isFalse();
    }

    // Hidden: no tie-breaker in ORDER BY makes pages overlap
    @Test
    void walkingAllPagesReturnsEveryActivityExactlyOnce() throws Exception {
        Set<Long> seen = new HashSet<>();
        List<Long> all = new ArrayList<>();
        for (int page = 0; page < 10; page++) {
            List<Long> pageIds = ids(get("/activities").param("page", String.valueOf(page)).param("size", "4"));
            all.addAll(pageIds);
            seen.addAll(pageIds);
        }
        assertThat(all).hasSize(SEEDED_ACTIVITIES);
        assertThat(seen).hasSize(SEEDED_ACTIVITIES);
    }

    // Hidden: N+1 when loading suppliers for a page of activities
    @Test
    void listingAPageDoesNotIssueOneQueryPerSupplier() throws Exception {
        Statistics statistics = entityManagerFactory.unwrap(SessionFactory.class).getStatistics();
        statistics.clear();

        mockMvc.perform(get("/activities").param("size", "20")).andExpect(status().isOk());

        assertThat(statistics.getPrepareStatementCount()).isLessThanOrEqualTo(2);
    }

    private List<Long> ids(org.springframework.test.web.servlet.RequestBuilder request) throws Exception {
        String body = mockMvc.perform(request).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        List<Long> ids = new ArrayList<>();
        for (JsonNode node : objectMapper.readTree(body)) {
            ids.add(node.get("id").asLong());
        }
        return ids;
    }
}
