package com.getyourguide.pricing.feature;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.getyourguide.pricing.TestcontainersConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Timestamp;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Import(TestcontainersConfiguration.class)
class LockedQuoteTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper json;
    @Autowired
    private JdbcTemplate jdbc;
    @LocalServerPort
    private int port;

    @BeforeEach
    void resetPromoUsage() {
        jdbc.update("DELETE FROM promo_redemption");
        jdbc.update("UPDATE promo SET redemptions = 0");
        jdbc.update("DELETE FROM quote");
        jdbc.update("UPDATE rate SET unit_price = 20 WHERE category = 'ADULT' AND currency = 'EUR'");
    }

    @Test
    void checkoutKeepsTheQuotedTotalAfterTheCatalogChanges() throws Exception {
        JsonNode quote = quote("c-lock", null, 1);
        jdbc.update("UPDATE rate SET unit_price = 999 WHERE category = 'ADULT' AND currency = 'EUR'");
        String raw = checkout(quote, quote.get("signature").asText(), 200);
        assertThat(json.readTree(raw).get("total").asText()).isEqualTo("20.00");
        jdbc.update("UPDATE rate SET unit_price = 20 WHERE category = 'ADULT' AND currency = 'EUR'");
    }

    @Test
    void badSignatureAndExpiryAreRejected() throws Exception {
        JsonNode quote = quote("c-sig", null, 1);
        checkout(quote, "deadbeef", 409);
        jdbc.update("UPDATE quote SET expires_at = ? WHERE id = CAST(? AS uuid)",
                Timestamp.from(Instant.parse("2020-01-01T00:00:00Z")), quote.get("quoteId").asText());
        checkout(quote, quote.get("signature").asText(), 409);
    }

    @Test
    void oneCustomerCannotRedeemTwice() throws Exception {
        JsonNode first = quote("c-once", "ONCE", 1);
        JsonNode second = quote("c-once", "ONCE", 1);
        checkout(first, first.get("signature").asText(), 200);
        checkout(second, second.get("signature").asText(), 409);
    }

    @Test
    void globalLimitHoldsUnderConcurrentCheckout() throws Exception {
        int n = 12;
        JsonNode[] quotes = new JsonNode[n];
        for (int i = 0; i < n; i++) {
            quotes[i] = quote("rush-" + i, "ONCE", 1);
        }
        ExecutorService pool = Executors.newFixedThreadPool(n);
        CountDownLatch ready = new CountDownLatch(n);
        CountDownLatch go = new CountDownLatch(1);
        AtomicInteger ok = new AtomicInteger();
        HttpClient http = HttpClient.newHttpClient();
        for (JsonNode quote : quotes) {
            pool.submit(() -> {
                ready.countDown();
                try {
                    go.await();
                    HttpRequest request = HttpRequest.newBuilder(URI.create("http://127.0.0.1:" + port + "/quotes/"
                                    + quote.get("quoteId").asText() + "/checkout"))
                            .header("Content-Type", "application/json")
                            .POST(HttpRequest.BodyPublishers.ofString(
                                    "{\"signature\":\"" + quote.get("signature").asText() + "\"}"))
                            .build();
                    int status = http.send(request, HttpResponse.BodyHandlers.ofString()).statusCode();
                    if (status == 200) {
                        ok.incrementAndGet();
                    }
                } catch (Exception ignored) {
                    // a failed request is not a successful redemption
                }
            });
        }
        ready.await();
        go.countDown();
        pool.shutdown();
        pool.awaitTermination(30, java.util.concurrent.TimeUnit.SECONDS);
        assertThat(ok.get()).isEqualTo(5);
    }

    private JsonNode quote(String customer, String promo, int adults) throws Exception {
        String promoField = promo == null ? "" : ",\"promoCode\":\"" + promo + "\"";
        String raw = mockMvc.perform(post("/quotes").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"customerId\":\"" + customer + "\",\"currency\":\"EUR\"" + promoField
                                + ",\"lines\":[{\"category\":\"ADULT\",\"quantity\":" + adults + "}]}"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        return json.readTree(raw);
    }

    private String checkout(JsonNode quote, String signature, int status) throws Exception {
        return mockMvc.perform(post("/quotes/{id}/checkout", quote.get("quoteId").asText())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"signature\":\"" + signature + "\"}"))
                .andExpect(status().is(status))
                .andReturn().getResponse().getContentAsString();
    }
}
