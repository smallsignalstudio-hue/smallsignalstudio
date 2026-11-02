package com.getyourguide.supplier.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.net.http.HttpClient;
import java.util.Map;

@Component
public class SupplierClient {
    private final RestClient supplier;
    private final RestClient payment;
    private final ObjectMapper json;

    public SupplierClient(ObjectMapper json,
                          @Value("${supplier.base-url}") String supplierUrl,
                          @Value("${payment.base-url}") String paymentUrl) {
        this.json = json;
        HttpClient http = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build();
        JdkClientHttpRequestFactory factory = new JdkClientHttpRequestFactory(http);
        this.supplier = RestClient.builder().baseUrl(supplierUrl).requestFactory(factory).build();
        this.payment = RestClient.builder().baseUrl(paymentUrl).requestFactory(factory).build();
    }

    public JsonNode postSupplier(String path, Map<String, Object> body) {
        return read(supplier.post().uri(path).contentType(MediaType.APPLICATION_JSON).body(body).retrieve().body(String.class));
    }

    public JsonNode getSupplier(String path) {
        return read(supplier.get().uri(path).retrieve().body(String.class));
    }

    public void postPayment(Map<String, Object> body) {
        payment.post().uri("/payments").contentType(MediaType.APPLICATION_JSON).body(body).retrieve().toBodilessEntity();
    }

    private JsonNode read(String raw) {
        try {
            return json.readTree(raw);
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }
}
