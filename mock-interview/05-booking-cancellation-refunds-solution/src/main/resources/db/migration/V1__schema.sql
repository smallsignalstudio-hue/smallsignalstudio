CREATE TABLE customer (
    id bigint PRIMARY KEY,
    email varchar(120) NOT NULL
);

CREATE TABLE booking (
    id bigint PRIMARY KEY,
    customer_id bigint NOT NULL REFERENCES customer (id),
    status varchar(16) NOT NULL,
    start_at timestamp NOT NULL,
    zone_id varchar(64) NOT NULL,
    list_price numeric(12, 2) NOT NULL,
    paid_amount numeric(12, 2) NOT NULL,
    currency varchar(3) NOT NULL,
    slot_id bigint,
    note varchar(40)
);

CREATE TABLE refund (
    id bigserial PRIMARY KEY,
    booking_id bigint NOT NULL REFERENCES booking (id),
    amount numeric(12, 2) NOT NULL,
    idempotency_key varchar(80) NOT NULL
);

CREATE TABLE notification (
    id bigserial PRIMARY KEY,
    booking_id bigint NOT NULL REFERENCES booking (id),
    body varchar(200) NOT NULL
);
