package com.getyourguide.availability.feature;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.getyourguide.availability.TestcontainersConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(properties = "spring.main.allow-bean-definition-overriding=true")
@AutoConfigureMockMvc
@Import({TestcontainersConfiguration.class, SharedBoatTest.FixedClock.class})
class SharedBoatTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper json;

    @Test
    void overlappingCruiseConsumesTheSharedBoat() throws Exception {
        mockMvc.perform(post("/bookings").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"activityId\":2,\"slotStart\":\"2026-06-03T09:00:00Z\",\"partySize\":6}"))
                .andExpect(status().isCreated());
        String raw = mockMvc.perform(get("/activities/1/slots").param("from", "2026-06-03").param("to", "2026-06-03"))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        int remaining = -1;
        for (JsonNode node : json.readTree(raw)) {
            var start = OffsetDateTime.parse(node.get("start").asText()).atZoneSameInstant(ZoneId.of("Europe/Berlin"));
            if (start.toLocalDate().equals(LocalDate.of(2026, 6, 3)) && start.getHour() == 10) {
                remaining = node.get("remaining").asInt();
            }
        }
        assertThat(remaining).isEqualTo(4);
    }

    @TestConfiguration
    static class FixedClock {
        @Bean
        @Primary
        Clock clock() {
            return Clock.fixed(Instant.parse("2026-06-01T12:00:00Z"), ZoneOffset.UTC);
        }
    }
}
