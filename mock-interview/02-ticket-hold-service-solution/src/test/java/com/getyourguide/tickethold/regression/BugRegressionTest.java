package com.getyourguide.tickethold.regression;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.getyourguide.tickethold.TestcontainersConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(properties = "holds.expire-ms=3600000")
@AutoConfigureMockMvc
@Import(TestcontainersConfiguration.class)
class BugRegressionTest {

    static {
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Berlin"));
    }

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper json;
    @Autowired
    private JdbcTemplate jdbc;

    // BOX-201
    @Test
    void holdExpiresTenMinutesAfterItWasCreatedRegardlessOfZone() throws Exception {
        long holdId = hold(14, 80);
        jdbc.update("UPDATE hold SET expires_at = expires_at - interval '30 minutes' WHERE id = ?", holdId);
        mockMvc.perform(post("/internal/expire")).andExpect(status().isNoContent());
        assertThat(statusOf(holdId)).isEqualTo("EXPIRED");
        assertThat(seatStatus(14)).isEqualTo("AVAILABLE");
    }

    // BOX-202
    @Test
    void expiryDoesNotReleaseSeatsOfAConfirmedBooking() throws Exception {
        mockMvc.perform(post("/internal/expire")).andExpect(status().isNoContent());
        assertThat(seatStatus(15)).isEqualTo("SOLD");
        assertThat(seatStatus(16)).isEqualTo("SOLD");
        assertThat(jdbc.queryForObject("SELECT status FROM hold WHERE id = 50", String.class)).isEqualTo("CONFIRMED");
    }

    // BOX-203
    @Test
    void failedGroupHoldRollsBackEverySeat() throws Exception {
        mockMvc.perform(post("/events/100/holds").header("X-Customer-Id", 81)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"seatIds\":[1,2,3,4]}"))
                .andExpect(status().isConflict());
        assertThat(List.of(seatStatus(1), seatStatus(2), seatStatus(3))).containsOnly("AVAILABLE");
    }

    // Hidden: confirm is an IDOR
    @Test
    void onlyTheOwningCustomerCanConfirm() throws Exception {
        long holdId = hold(6, 82);
        mockMvc.perform(post("/holds/{id}/confirm", holdId).header("X-Customer-Id", 83))
                .andExpect(status().isForbidden());
        assertThat(seatStatus(6)).isEqualTo("HELD");
    }

    // Hidden: two buyers, one seat
    @Test
    void oneSeatIsHeldByExactlyOneCustomer() throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(16);
        List<Callable<Integer>> calls = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            long customer = 200 + i;
            calls.add(() -> mockMvc.perform(post("/events/100/holds").header("X-Customer-Id", customer)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"seatIds\":[5]}")).andReturn().getResponse().getStatus());
        }
        int created = 0;
        for (Future<Integer> future : pool.invokeAll(calls)) {
            if (future.get() == 201) {
                created++;
            }
        }
        pool.shutdown();
        assertThat(created).isEqualTo(1);
        assertThat(ZonedDateTime.now(ZoneId.of("Europe/Berlin")).getOffset().getTotalSeconds()).isNotZero();
    }

    private long hold(long seatId, long customerId) throws Exception {
        MvcResult result = mockMvc.perform(post("/events/100/holds").header("X-Customer-Id", customerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"seatIds\":[" + seatId + "]}"))
                .andExpect(status().isCreated())
                .andReturn();
        return json.readTree(result.getResponse().getContentAsString()).get("id").asLong();
    }

    private String statusOf(long holdId) throws Exception {
        String body = mockMvc.perform(get("/holds/{id}", holdId)).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        return json.readTree(body).get("status").asText();
    }

    private String seatStatus(long seatId) throws Exception {
        String body = mockMvc.perform(get("/events/100/seats")).andReturn().getResponse().getContentAsString();
        for (JsonNode seat : json.readTree(body)) {
            if (seat.get("id").asLong() == seatId) {
                return seat.get("status").asText();
            }
        }
        throw new AssertionError("seat " + seatId);
    }
}
