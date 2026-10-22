package com.atolls.coupon.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;
@Configuration
public class ClockConfig {
    @Bean
    Clock clock(@Value("${coupon.now:}") String now) {
        if (now == null || now.isBlank()) return Clock.systemUTC();
        return Clock.fixed(Instant.parse(now), ZoneOffset.UTC);
    }
}
