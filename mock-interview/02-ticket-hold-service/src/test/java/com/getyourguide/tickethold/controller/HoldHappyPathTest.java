package com.getyourguide.tickethold.controller;

import com.getyourguide.tickethold.TestcontainersConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(properties = "holds.expire-ms=3600000")
@AutoConfigureMockMvc
@Import(TestcontainersConfiguration.class)
class HoldHappyPathTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void listsSeats() throws Exception {
        mockMvc.perform(get("/events/100/seats"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(17));
    }

    @Test
    void holdsTwoAvailableSeats() throws Exception {
        mockMvc.perform(post("/events/100/holds")
                        .header("X-Customer-Id", 42)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"seatIds\":[7,8]}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value("HELD"))
                .andExpect(jsonPath("$.seatIds.length()").value(2));
    }
}
