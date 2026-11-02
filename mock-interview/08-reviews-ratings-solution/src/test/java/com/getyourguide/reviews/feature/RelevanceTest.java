package com.getyourguide.reviews.feature;

import com.getyourguide.reviews.TestcontainersConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Import(TestcontainersConfiguration.class)
@TestPropertySource(properties = "reviews.suite=feature")
class RelevanceTest {
    @Autowired MockMvc mockMvc;

    @Test
    void establishedActivityRanksAboveASingleFive() throws Exception {
        mockMvc.perform(get("/activities").param("sort", "relevant"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").value(1))
                .andExpect(jsonPath("$[1]").value(2));
    }

    @Test
    void hidingAReviewUpdatesTheAggregate() throws Exception {
        mockMvc.perform(post("/reviews/1/hide")).andExpect(status().isOk());
        mockMvc.perform(post("/internal/ratings/rebuild")).andExpect(status().isOk());
        mockMvc.perform(get("/activities/1/rating")).andExpect(jsonPath("$.average").value("4.5")).andExpect(jsonPath("$.count").value(4));
        mockMvc.perform(get("/activities/1/reviews").param("limit", "10")).andExpect(jsonPath("$.reviews[?(@.id == 1)]").doesNotExist());
    }
}
