package com.getyourguide.reviews.controller;
import com.getyourguide.reviews.TestcontainersConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest @AutoConfigureMockMvc @Import(TestcontainersConfiguration.class)
class ReviewReadTest {
    @Autowired MockMvc mockMvc;
    @Test void seededReviewIsReadable() throws Exception {
        mockMvc.perform(get("/reviews/1")).andExpect(status().isOk()).andExpect(jsonPath("$.body").value("A"));
    }
}
