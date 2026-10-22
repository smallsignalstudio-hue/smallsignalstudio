package com.atolls.cashback.regression;

import com.atolls.cashback.TestcontainersConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
    void usdUsesTheRateOnTheEvent() throws Exception {
        send(10, "usd-10", "CONFIRMED", 1000, "USD", "0.10").andExpect(status().isOk());
        available(10, 100);
    }

    @Test
    void halfUpEuroCent() throws Exception {
        send(14, "usd-14", "CONFIRMED", 15, "USD", "0.10").andExpect(status().isOk());
        available(14, 2);
    }

    @Test
    void theSameConfirmationCreditsOnce() throws Exception {
        send(11, "eur-11", "CONFIRMED", 250, "EUR", "1").andExpect(status().isOk());
        send(11, "eur-11", "CONFIRMED", 250, "EUR", "1").andExpect(status().isOk());
        available(11, 250);
    }

    @Test
    void pendingAfterConfirmedDoesNotRewind() throws Exception {
        send(12, "eur-12", "CONFIRMED", 250, "EUR", "1").andExpect(status().isOk());
        send(12, "eur-12", "PENDING", 250, "EUR", "1")
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("CONFIRMED"))
                .andExpect(jsonPath("$.availableCents").value(250));
        available(12, 250);
    }

    @Test
    void aHigherConfirmationCreditsTheDelta() throws Exception {
        send(13, "eur-13", "CONFIRMED", 250, "EUR", "1").andExpect(status().isOk());
        send(13, "eur-13", "CONFIRMED", 400, "EUR", "1").andExpect(status().isOk());
        available(13, 400);
    }

    @Test
    void payoutLeavesPendingUntouched() throws Exception {
        mockMvc.perform(post("/wallets/2/payouts").header("Idempotency-Key", "pay-2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.paidCents").value(500))
                .andExpect(jsonPath("$.availableCents").value(0))
                .andExpect(jsonPath("$.pendingCents").value(300));
    }

    @Test
    void twoConfirmationsBothLand() throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        CountDownLatch ready = new CountDownLatch(2);
        CountDownLatch start = new CountDownLatch(1);
        List<Future<?>> futures = new ArrayList<>();
        for (String externalId : List.of("race-a", "race-b")) {
            futures.add(pool.submit(() -> {
                ready.countDown();
                start.await();
                send(30, externalId, "CONFIRMED", 100, "EUR", "1");
                return null;
            }));
        }
        ready.await();
        start.countDown();
        for (Future<?> future : futures) future.get();
        pool.shutdown();
        available(30, 200);
    }

    @Test
    void twoPollersCreditTheStoredEventOnce() throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        CountDownLatch ready = new CountDownLatch(2);
        CountDownLatch start = new CountDownLatch(1);
        List<Future<?>> futures = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            futures.add(pool.submit(() -> {
                ready.countDown();
                start.await();
                mockMvc.perform(post("/internal/poll")).andReturn();
                return null;
            }));
        }
        ready.await();
        start.countDown();
        for (Future<?> future : futures) future.get();
        pool.shutdown();
        available(3, 100);
    }

    private void available(long userId, int cents) throws Exception {
        mockMvc.perform(get("/wallets/" + userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.availableCents").value(cents));
    }

    private ResultActions send(long userId, String externalId, String status, int amountCents, String currency, String fxRate) throws Exception {
        String body = "{\"externalId\":\"" + externalId + "\",\"userId\":" + userId
                + ",\"status\":\"" + status + "\",\"amountCents\":" + amountCents
                + ",\"currency\":\"" + currency + "\",\"fxRate\":\"" + fxRate + "\"}";
        return mockMvc.perform(post("/events").contentType(MediaType.APPLICATION_JSON).content(body));
    }
}
