CREATE UNIQUE INDEX inbox_dedup_key ON inbox (dedup_key);

CREATE TABLE outbox (
    id bigserial PRIMARY KEY,
    dedup_key varchar(160) NOT NULL UNIQUE,
    booking_id bigint NOT NULL REFERENCES booking (id),
    attempts int NOT NULL,
    status varchar(16) NOT NULL
);
