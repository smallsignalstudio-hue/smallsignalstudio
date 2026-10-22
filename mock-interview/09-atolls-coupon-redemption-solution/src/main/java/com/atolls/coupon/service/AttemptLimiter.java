package com.atolls.coupon.service;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
@Component
public class AttemptLimiter {
    private final Map<Long, AtomicInteger> failures = new ConcurrentHashMap<>();
    public void check(long userId) {
        if (failures.getOrDefault(userId, new AtomicInteger()).get() >= 5) {
            throw new TooManyException();
        }
    }
    public void miss(long userId) { failures.computeIfAbsent(userId, id -> new AtomicInteger()).incrementAndGet(); }
    public void hit(long userId) { failures.remove(userId); }
}
