package com.getyourguide.tickethold;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class TicketHoldApplication {

    public static void main(String[] args) {
        SpringApplication.run(TicketHoldApplication.class, args);
    }
}
