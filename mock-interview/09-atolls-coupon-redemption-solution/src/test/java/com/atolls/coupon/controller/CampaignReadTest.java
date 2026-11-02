package com.atolls.coupon.controller;
import com.atolls.coupon.TestcontainersConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest @AutoConfigureMockMvc @Import(TestcontainersConfiguration.class)
class CampaignReadTest {
    @Autowired MockMvc mockMvc;
    @Test void campaignIsReadable() throws Exception {
        mockMvc.perform(get("/campaigns/1")).andExpect(status().isOk()).andExpect(jsonPath("$.name").value("Fresh"));
    }
}
