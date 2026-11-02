package com.getyourguide.supplier.service;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public final class CircuitBreaker {
    private final int threshold;
    private final Map<Long, Integer> failures = new ConcurrentHashMap<>();
    private final Set<Long> open = ConcurrentHashMap.newKeySet();

    public CircuitBreaker(int threshold) {
        this.threshold = threshold;
    }

    public boolean allow(long supplierId) {
        return !open.contains(supplierId);
    }

    public void success(long supplierId) {
        failures.remove(supplierId);
        open.remove(supplierId);
    }

    public void failure(long supplierId) {
        int count = failures.merge(supplierId, 1, Integer::sum);
        if (count >= threshold) {
            open.add(supplierId);
        }
    }
}
