package com.getyourguide.pricing.controller;

import com.getyourguide.pricing.TestcontainersConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Import(TestcontainersConfiguration.class)
class QuoteHappyPathTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void quotesASingleAdult() throws Exception {
        mockMvc.perform(post("/quotes").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"customerId\":\"c1\",\"currency\":\"EUR\",\"lines\":[{\"category\":\"ADULT\",\"quantity\":1}]}"))
                .andExpect(status().isOk());
    }
}
