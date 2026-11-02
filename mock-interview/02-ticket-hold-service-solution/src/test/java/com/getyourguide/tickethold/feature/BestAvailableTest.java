package com.getyourguide.tickethold.feature;

import com.getyourguide.tickethold.TestcontainersConfiguration;
import com.getyourguide.tickethold.model.Seat;
import com.getyourguide.tickethold.service.BestSeatPicker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(properties = "holds.expire-ms=3600000")
@AutoConfigureMockMvc
@Import(TestcontainersConfiguration.class)
class BestAvailableTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JdbcTemplate jdbc;

    @Test
    void seatsAPartyOnTheEndOfARow() throws Exception {
        insertRun("C", 30, 8);
        mockMvc.perform(post("/events/100/holds/best-available").header("X-Customer-Id", 90)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"section\":\"C\",\"count\":3}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.seatIds").value(org.hamcrest.Matchers.containsInAnyOrder(30, 31, 32)));
    }

    @Test
    void rejectsAPartyThatWouldLeaveASingleSeat() throws Exception {
        insertRun("D", 40, 6);
        mockMvc.perform(post("/events/100/holds/best-available").header("X-Customer-Id", 91)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"section\":\"D\",\"count\":5}"))
                .andExpect(status().isConflict());
    }

    private void insertRun(String section, int firstId, int count) {
        for (int i = 0; i < count; i++) {
            jdbc.update("""
                    INSERT INTO seat (id, event_id, section, seat_row, seat_number, price_category, status)
                    VALUES (?, 100, ?, 1, ?, 'STANDARD', 'AVAILABLE')
                    """, firstId + i, section, i + 1);
        }
    }

    @Test
    void pickerSkipsWindowsThatLeaveOneSeat() {
        assertThat(numbers(BestSeatPicker.pick(row(1, 2, 3), 2))).isNull();
        assertThat(numbers(BestSeatPicker.pick(row(1, 2, 3, 4), 2))).containsExactly(1, 2);
        assertThat(numbers(BestSeatPicker.pick(row(1, 2, 4, 5, 6), 2))).containsExactly(1, 2);
    }

    private static List<Integer> numbers(List<Seat> seats) {
        return seats == null ? null : seats.stream().map(Seat::getSeatNumber).toList();
    }

    private static List<Seat> row(int... numbers) {
        List<Seat> seats = new java.util.ArrayList<>();
        for (int number : numbers) {
            Seat seat = new Seat();
            seat.setSeatRow(1);
            seat.setSeatNumber(number);
            seats.add(seat);
        }
        return seats;
    }
}
