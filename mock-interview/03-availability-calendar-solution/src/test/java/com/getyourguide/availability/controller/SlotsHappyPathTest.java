package com.getyourguide.availability.controller;

import com.getyourguide.availability.TestcontainersConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Import(TestcontainersConfiguration.class)
class SlotsHappyPathTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void returnsSlotsForAMultiDayRange() throws Exception {
        mockMvc.perform(get("/activities/1/slots").param("from", "2026-06-01").param("to", "2026-06-04"))
                .andExpect(status().isOk());
    }
}
