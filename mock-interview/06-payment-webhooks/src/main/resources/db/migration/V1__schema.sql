CREATE TABLE booking (
    id bigint PRIMARY KEY,
    status varchar(16) NOT NULL,
    currency varchar(3) NOT NULL,
    amount_minor bigint NOT NULL,
    charged_amount numeric(14, 3)
);

CREATE TABLE voucher (
    id bigserial PRIMARY KEY,
    booking_id bigint NOT NULL REFERENCES booking (id),
    psp_reference varchar(80) NOT NULL,
    code varchar(80) NOT NULL
);

CREATE TABLE inbox (
    id bigserial PRIMARY KEY,
    dedup_key varchar(160) NOT NULL,
    event_code varchar(40) NOT NULL,
    raw_body text NOT NULL
);
