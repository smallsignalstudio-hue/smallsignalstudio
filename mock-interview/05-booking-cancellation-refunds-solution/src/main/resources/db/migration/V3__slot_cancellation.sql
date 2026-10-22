CREATE TABLE slot_cancellation (
    id               BIGSERIAL PRIMARY KEY,
    idempotency_key  VARCHAR(80)  NOT NULL,
    booking_id       BIGINT       NOT NULL REFERENCES booking (id),
    outcome          VARCHAR(16)  NOT NULL,
    UNIQUE (idempotency_key, booking_id)
);

CREATE UNIQUE INDEX refund_idempotency_key ON refund (idempotency_key);
