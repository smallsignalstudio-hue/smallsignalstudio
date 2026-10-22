package com.getyourguide.webhooks.feature;

import com.getyourguide.webhooks.model.OutboxMessage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OutboxDeliveryTest {

    @Test
    void thirdFailureParksTheMessageOnTheDlq() {
        OutboxMessage message = OutboxMessage.pending("X:AUTHORISATION", 1L);
        message.recordAttempt(false);
        message.recordAttempt(false);
        assertThat(message.getStatus()).isEqualTo("PENDING");
        message.recordAttempt(false);
        assertThat(message.getStatus()).isEqualTo("DLQ");
        assertThat(message.getAttempts()).isEqualTo(3);
    }

    @Test
    void successPublishes() {
        OutboxMessage message = OutboxMessage.pending("Y:AUTHORISATION", 2L);
        message.recordAttempt(true);
        assertThat(message.getStatus()).isEqualTo("PUBLISHED");
    }
}
