package com.getyourguide.availability.regression;

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
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(properties = "spring.main.allow-bean-definition-overriding=true")
@AutoConfigureMockMvc
@Import({TestcontainersConfiguration.class, BugRegressionTest.FixedClock.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BugRegressionTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper json;

    // AVL-301
    @Test
    void singleDayAndRangeEndAreInclusive() throws Exception {
        assertThat(starts(1, "2026-06-02", "2026-06-02")).hasSize(1);
        assertThat(localDates(1, "2026-03-28", "2026-03-30", "Europe/Berlin"))
                .contains(LocalDate.of(2026, 3, 30));
    }

    // AVL-302
    @Test
    void departureStaysAtTenOnDstSpringForward() throws Exception {
        assertThat(localHoursOn(1, "2026-03-28", "2026-03-30", "Europe/Berlin", LocalDate.of(2026, 3, 29)))
                .contains(10);
    }

    // AVL-303 date
    @Test
    void overnightTourStaysOnTheStartDate() throws Exception {
        assertThat(localHoursOn(3, "2026-06-01", "2026-06-01", "Asia/Tokyo", LocalDate.of(2026, 6, 1)))
                .contains(22);
    }

    // AVL-303 capacity
    @Test
    void cancelledBookingDoesNotConsumeCapacity() throws Exception {
        assertThat(remainingAt(1, "2026-06-01", "2026-06-02", "Europe/Berlin", LocalDate.of(2026, 6, 1), 10))
                .isEqualTo(10);
    }

    // Hidden: cache key ignores the date range
    @Test
    void differentRangesAreNotServedFromTheSameCacheEntry() throws Exception {
        List<String> july = starts(1, "2026-07-01", "2026-07-02");
        List<String> august = starts(1, "2026-08-01", "2026-08-02");
        assertThat(august).isNotEqualTo(july);
    }

    // Hidden: booking does not evict the calendar
    @Test
    void bookingChangesTheRemainingCount() throws Exception {
        int before = remainingAt(1, "2026-06-02", "2026-06-03", "Europe/Berlin", LocalDate.of(2026, 6, 2), 10);
        mockMvc.perform(post("/bookings").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"activityId\":1,\"slotStart\":\"2026-06-02T08:00:00Z\",\"partySize\":2}"))
                .andExpect(status().isCreated());
        int after = remainingAt(1, "2026-06-02", "2026-06-03", "Europe/Berlin", LocalDate.of(2026, 6, 2), 10);
        assertThat(after).isEqualTo(before - 2);
    }

    // Hidden: cutoff uses the server zone
    @Test
    void tokyoCutoffUsesTheActivityZone() throws Exception {
        mockMvc.perform(post("/bookings").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"activityId\":3,\"slotStart\":\"2026-06-01T13:00:00Z\",\"partySize\":1}"))
                .andExpect(status().isConflict());
    }

    private List<String> starts(long activity, String from, String to) throws Exception {
        List<String> starts = new ArrayList<>();
        for (JsonNode node : body(activity, from, to)) {
            starts.add(node.get("start").asText());
        }
        return starts;
    }

    private List<LocalDate> localDates(long activity, String from, String to, String zone) throws Exception {
        return starts(activity, from, to).stream()
                .map(value -> OffsetDateTime.parse(value).atZoneSameInstant(ZoneId.of(zone)).toLocalDate())
                .toList();
    }

    private List<Integer> localHoursOn(long activity, String from, String to, String zone, LocalDate day) throws Exception {
        ZoneId zoneId = ZoneId.of(zone);
        return starts(activity, from, to).stream()
                .map(value -> OffsetDateTime.parse(value).atZoneSameInstant(zoneId))
                .filter(value -> value.toLocalDate().equals(day))
                .map(value -> value.getHour())
                .toList();
    }

    private int remainingAt(long activity, String from, String to, String zone, LocalDate day, int hour) throws Exception {
        ZoneId zoneId = ZoneId.of(zone);
        for (JsonNode node : body(activity, from, to)) {
            var start = OffsetDateTime.parse(node.get("start").asText()).atZoneSameInstant(zoneId);
            if (start.toLocalDate().equals(day) && start.getHour() == hour) {
                return node.get("remaining").asInt();
            }
        }
        throw new AssertionError("no slot on " + day);
    }

    private JsonNode body(long activity, String from, String to) throws Exception {
        String raw = mockMvc.perform(get("/activities/{id}/slots", activity).param("from", from).param("to", to))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        return json.readTree(raw);
    }

    @TestConfiguration
    public static class FixedClock {
        @Bean
        @Primary
        Clock clock() {
            return Clock.fixed(Instant.parse("2026-06-01T12:00:00Z"), ZoneOffset.UTC);
        }
    }
}
