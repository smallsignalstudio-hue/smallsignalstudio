package com.getyourguide.availability;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class AvailabilityApplication {

    public static void main(String[] args) {
        SpringApplication.run(AvailabilityApplication.class, args);
    }
}
