CREATE TABLE supplier_account (
    id bigint PRIMARY KEY,
    code varchar(40) NOT NULL,
    zone_id varchar(64) NOT NULL
);

CREATE TABLE activity (
    id bigint PRIMARY KEY,
    supplier_id bigint NOT NULL REFERENCES supplier_account (id),
    name varchar(80) NOT NULL
);

CREATE TABLE customer_order (
    id bigserial PRIMARY KEY,
    activity_id bigint NOT NULL REFERENCES activity (id),
    status varchar(24) NOT NULL,
    reservation_status varchar(16) NOT NULL,
    supplier_reservation_id varchar(80),
    supplier_booking_id varchar(80),
    start_at timestamptz,
    idempotency_key varchar(80) NOT NULL
);
