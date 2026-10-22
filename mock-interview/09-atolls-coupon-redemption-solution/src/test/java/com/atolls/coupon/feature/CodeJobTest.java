package com.atolls.coupon.feature;

import com.atolls.coupon.TestcontainersConfiguration;
import com.atolls.coupon.service.CodeMint;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Import(TestcontainersConfiguration.class)
@TestPropertySource(properties = "coupon.suite=feature")
class CodeJobTest {
    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper json;

    @Test
    void codesAreUnambiguousAndTheJobFinishes() throws Exception {
        String body = mockMvc.perform(post("/campaigns/1/codes").contentType(MediaType.APPLICATION_JSON).content("{\"count\":20}"))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        long jobId = json.readTree(body).get("jobId").asLong();
        String status = "";
        for (int i = 0; i < 40 && !"DONE".equals(status); i++) {
            Thread.sleep(50);
            status = json.readTree(mockMvc.perform(get("/jobs/" + jobId)).andReturn().getResponse().getContentAsString()).get("status").asText();
        }
        assertThat(status).isEqualTo("DONE");
        JsonNode codes = json.readTree(mockMvc.perform(get("/campaigns/1/codes")).andReturn().getResponse().getContentAsString());
        assertThat(codes).hasSize(20);
        for (JsonNode code : codes) {
            assertThat(code.asText()).matches("[" + CodeMint.ALPHABET + "]{8}");
        }
    }

    @Test
    void repeatedMissesAreLimited() throws Exception {
        for (int i = 0; i < 5; i++) {
            mockMvc.perform(post("/redemptions").header("X-User-Id", "99")
                            .contentType(MediaType.APPLICATION_JSON).content("{\"code\":\"NOPE\"}"))
                    .andExpect(status().isNotFound());
        }
        mockMvc.perform(post("/redemptions").header("X-User-Id", "99")
                        .contentType(MediaType.APPLICATION_JSON).content("{\"code\":\"NOPE\"}"))
                .andExpect(status().isTooManyRequests());
    }
}
