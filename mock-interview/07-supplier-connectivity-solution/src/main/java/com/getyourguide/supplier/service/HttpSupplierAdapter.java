package com.getyourguide.supplier.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;

import java.net.http.HttpClient;
import java.time.Duration;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class HttpSupplierAdapter implements SupplierPort {
    private final RestClient supplier;
    private final RestClient payment;
    private final ObjectMapper json;
    private final CircuitBreaker breaker = new CircuitBreaker(3);
    private final Map<Long, Bulkhead> gates = new ConcurrentHashMap<>();

    public HttpSupplierAdapter(ObjectMapper json,
                               @Value("${supplier.base-url}") String supplierUrl,
                               @Value("${payment.base-url}") String paymentUrl) {
        this.json = json;
        this.supplier = client(supplierUrl);
        this.payment = client(paymentUrl);
    }

    public void pay(Map<String, Object> body) {
        payment.post().uri("/payments").contentType(MediaType.APPLICATION_JSON).body(body).retrieve().toBodilessEntity();
    }

    @Override
    public JsonNode reserve(long supplierId, Map<String, Object> body, String idempotencyKey) {
        return send(supplierId, "/reservations", body, idempotencyKey, true);
    }

    @Override
    public JsonNode book(long supplierId, Map<String, Object> body, String idempotencyKey) {
        return send(supplierId, "/bookings", body, idempotencyKey, true);
    }

    @Override
    public void cancelReservation(long supplierId, String reservationId, String idempotencyKey) {
        send(supplierId, "/reservations/" + reservationId + "/cancellations", Map.of("reservationId", reservationId), idempotencyKey, false);
    }

    @Override
    public JsonNode availability(long activityId) {
        try {
            return read(supplier.get().uri("/availability?activityId=" + activityId).retrieve().body(String.class));
        } catch (HttpServerErrorException | ResourceAccessException ex) {
            throw new SupplierTimeout();
        }
    }

    @Override
    public Optional<String> findBooking(String idempotencyKey) {
        try {
            JsonNode node = read(supplier.get().uri("/bookings?idempotencyKey=" + idempotencyKey).retrieve().body(String.class));
            if (node.path("bookingId").isMissingNode() || node.path("bookingId").isNull()) {
                return Optional.empty();
            }
            return Optional.of(node.path("bookingId").asText());
        } catch (HttpClientErrorException.NotFound ex) {
            return Optional.empty();
        }
    }

    private JsonNode send(long supplierId, String path, Map<String, Object> body, String key, boolean counted) {
        if (!breaker.allow(supplierId)) {
            throw new CircuitOpen();
        }
        Bulkhead gate = gates.computeIfAbsent(supplierId, id -> new Bulkhead(4));
        if (!gate.tryAcquire()) {
            throw new CircuitOpen();
        }
        try {
            String raw = supplier.post().uri(path)
                    .header("Idempotency-Key", key)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(body)
                    .retrieve()
                    .body(String.class);
            if (counted) {
                breaker.success(supplierId);
            }
            JsonNode node = read(raw);
            if (node.hasNonNull("errorCode")) {
                throw new SupplierBusinessException();
            }
            return node;
        } catch (SupplierBusinessException ex) {
            throw ex;
        } catch (HttpServerErrorException ex) {
            if (counted) {
                breaker.failure(supplierId);
            }
            if ("/bookings".equals(path)) {
                throw new SupplierUnknown();
            }
            throw ex;
        } catch (ResourceAccessException ex) {
            if (counted) {
                breaker.failure(supplierId);
            }
            throw new SupplierTimeout();
        } finally {
            gate.release();
        }
    }

    private RestClient client(String baseUrl) {
        HttpClient http = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .connectTimeout(Duration.ofMillis(500))
                .build();
        JdkClientHttpRequestFactory factory = new JdkClientHttpRequestFactory(http);
        factory.setReadTimeout(Duration.ofSeconds(1));
        return RestClient.builder().baseUrl(baseUrl).requestFactory(factory).build();
    }

    private JsonNode read(String raw) {
        try {
            if (raw == null || raw.isBlank()) {
                return json.createObjectNode();
            }
            return json.readTree(raw);
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }
}
