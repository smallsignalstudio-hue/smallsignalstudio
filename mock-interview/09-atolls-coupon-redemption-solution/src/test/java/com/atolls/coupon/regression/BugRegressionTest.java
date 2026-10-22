package com.atolls.coupon.regression;

import com.atolls.coupon.TestcontainersConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Import(TestcontainersConfiguration.class)
class BugRegressionTest {
    @Autowired MockMvc mockMvc;

    @Test
    void turkishLowerCaseStillFindsTheCode() throws Exception {
        redeem(8, " indirim ").andExpect(status().isCreated());
    }

    @Test
    void lastValidDayIsInclusive() throws Exception {
        redeem(3, "LAST").andExpect(status().isCreated());
    }

    @Test
    void marketZoneDecidesTheCalendarDay() throws Exception {
        redeem(4, "ISLAND").andExpect(status().isCreated());
    }

    @Test
    void limitIsPerCampaign() throws Exception {
        redeem(7, "FRESH").andExpect(status().isCreated());
    }

    @Test
    void inactiveAndExpiredGlobalsStayHidden() throws Exception {
        mockMvc.perform(get("/campaigns").param("market", "DE"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0]").value(1));
    }

    @Test
    void oneCodeCannotBeRedeemedTwice() throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        CountDownLatch ready = new CountDownLatch(2);
        CountDownLatch start = new CountDownLatch(1);
        AtomicInteger created = new AtomicInteger();
        List<Future<?>> futures = new ArrayList<>();
        for (int user = 9; user <= 10; user++) {
            int userId = user;
            futures.add(pool.submit(() -> {
                ready.countDown();
                start.await();
                int status = mockMvc.perform(post("/redemptions").header("X-User-Id", String.valueOf(userId))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"code\":\"RACE\"}"))
                        .andReturn().getResponse().getStatus();
                if (status == 201) created.incrementAndGet();
                return null;
            }));
        }
        ready.await();
        start.countDown();
        for (Future<?> future : futures) future.get();
        pool.shutdown();
        mockMvc.perform(get("/coupons/RACE/redemptions")).andExpect(jsonPath("$.count").value(1));
        org.assertj.core.api.Assertions.assertThat(created.get()).isEqualTo(1);
    }

    private org.springframework.test.web.servlet.ResultActions redeem(long user, String code) throws Exception {
        return mockMvc.perform(post("/redemptions").header("X-User-Id", String.valueOf(user))
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"code\":\"" + code + "\"}"));
    }
}
