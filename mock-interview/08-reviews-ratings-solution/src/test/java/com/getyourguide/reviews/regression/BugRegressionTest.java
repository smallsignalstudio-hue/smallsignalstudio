package com.getyourguide.reviews.regression;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.getyourguide.reviews.TestcontainersConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import jakarta.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Import(TestcontainersConfiguration.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BugRegressionTest {
    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper json;
    @Autowired EntityManagerFactory emf;

    @Test
    @Order(1)
    void averageKeepsOneDecimal() throws Exception {
        mockMvc.perform(get("/activities/1/rating")).andExpect(status().isOk()).andExpect(jsonPath("$.average").value("4.6"));
    }

    @Test
    void reviewListDoesNotQueryPerAuthor() throws Exception {
        Statistics stats = emf.unwrap(SessionFactory.class).getStatistics();
        stats.setStatisticsEnabled(true);
        stats.clear();
        mockMvc.perform(get("/activities/1/reviews").param("limit", "10")).andExpect(status().isOk());
        assertThat(stats.getPrepareStatementCount()).isLessThanOrEqualTo(4);
    }

    @Test
    void nextPageDoesNotRepeatTheRowYouJustSaw() throws Exception {
        MvcResult first = mockMvc.perform(get("/activities/1/reviews").param("limit", "1")).andExpect(status().isOk()).andReturn();
        JsonNode page = json.readTree(first.getResponse().getContentAsString());
        long firstId = page.get("reviews").get(0).get("id").asLong();
        String cursor = page.get("nextCursor").asText();
        MvcResult created = mockMvc.perform(post("/reviews").header("X-Customer-Id", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"bookingId\":6,\"rating\":5,\"body\":\"new\"}"))
                .andExpect(status().isCreated()).andReturn();
        long newId = json.readTree(created.getResponse().getContentAsString()).get("id").asLong();
        MvcResult second = mockMvc.perform(get("/activities/1/reviews").param("limit", "1").param("cursor", cursor))
                .andExpect(status().isOk()).andReturn();
        long secondId = json.readTree(second.getResponse().getContentAsString()).get("reviews").get(0).get("id").asLong();
        assertThat(secondId).isNotEqualTo(firstId);
        assertThat(secondId).isNotEqualTo(newId);
    }

    @Test
    void emojiBodyCountsCodePoints() throws Exception {
        mockMvc.perform(post("/reviews").header("X-Customer-Id", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"bookingId\":1,\"rating\":5,\"body\":\"" + "👍".repeat(15) + "\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    void cancelledBookingCannotBeReviewed() throws Exception {
        mockMvc.perform(post("/reviews").header("X-Customer-Id", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"bookingId\":2,\"rating\":5,\"body\":\"no\"}"))
                .andExpect(status().isConflict());
    }

    @Test
    void futureBookingCannotBeReviewed() throws Exception {
        mockMvc.perform(post("/reviews").header("X-Customer-Id", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"bookingId\":3,\"rating\":5,\"body\":\"early\"}"))
                .andExpect(status().isConflict());
    }

    @Test
    void travellerCannotReviewSomeoneElsesBooking() throws Exception {
        mockMvc.perform(post("/reviews").header("X-Customer-Id", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"bookingId\":4,\"rating\":1,\"body\":\"mine\"}"))
                .andExpect(status().isForbidden());
    }

    @Test
    void concurrentReviewsDoNotLoseUpdates() throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(8);
        CountDownLatch ready = new CountDownLatch(8);
        CountDownLatch start = new CountDownLatch(1);
        List<Future<?>> futures = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            int bookingId = 21 + i;
            futures.add(pool.submit(() -> {
                ready.countDown();
                start.await();
                mockMvc.perform(post("/reviews").header("X-Customer-Id", "1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"bookingId\":" + bookingId + ",\"rating\":5,\"body\":\"go\"}"))
                        .andExpect(status().isCreated());
                return null;
            }));
        }
        ready.await();
        start.countDown();
        for (Future<?> future : futures) {
            future.get();
        }
        pool.shutdown();
        mockMvc.perform(get("/activities/2/rating")).andExpect(jsonPath("$.count").value(9));
    }
}
