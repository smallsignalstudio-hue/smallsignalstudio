package com.getyourguide.cancellation.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;
import java.net.http.HttpClient;
import java.util.Map;

@Component
public class PspClient {

    private final RestClient http;

    public PspClient(@Value("${psp.base-url}") String baseUrl) {
        HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build();
        this.http = RestClient.builder().baseUrl(baseUrl).requestFactory(new JdkClientHttpRequestFactory(client)).build();
    }

    public void refund(long bookingId, BigDecimal amount, String email) {
        http.post().uri("/refunds")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Map.of("bookingId", bookingId, "amount", amount.toPlainString(), "email", email))
                .retrieve()
                .toBodilessEntity();
    }
}
