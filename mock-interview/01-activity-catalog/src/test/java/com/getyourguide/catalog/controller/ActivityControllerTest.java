package com.getyourguide.catalog.controller;

import com.getyourguide.catalog.TestcontainersConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Import(TestcontainersConfiguration.class)
class ActivityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void listsActivities() throws Exception {
        mockMvc.perform(get("/activities"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(20));
    }

    @Test
    void returnsSingleActivity() throws Exception {
        mockMvc.perform(get("/activities/25732"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Berlin: Third Reich and Cold War Walking Tour"))
                .andExpect(jsonPath("$.supplierName").value("Berlin Walks GmbH"));
    }
}
