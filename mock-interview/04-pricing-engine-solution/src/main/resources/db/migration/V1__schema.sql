CREATE TABLE rate (
    id bigserial PRIMARY KEY,
    category varchar(16) NOT NULL,
    currency varchar(3) NOT NULL,
    unit_price numeric(12, 2) NOT NULL,
    group_min int,
    group_price numeric(12, 2),
    UNIQUE (category, currency)
);

CREATE TABLE price_band (
    id bigserial PRIMARY KEY,
    currency varchar(3) NOT NULL,
    threshold numeric(12, 1) NOT NULL,
    flat_total numeric(12, 2) NOT NULL
);

CREATE TABLE promo (
    id bigserial PRIMARY KEY,
    code varchar(32) NOT NULL UNIQUE,
    percent int NOT NULL,
    min_subtotal numeric(12, 2) NOT NULL,
    max_redemptions int NOT NULL,
    redemptions int NOT NULL DEFAULT 0
);

CREATE TABLE quote (
    id uuid PRIMARY KEY,
    customer_id varchar(64) NOT NULL,
    currency varchar(3) NOT NULL,
    total varchar(32) NOT NULL,
    signature varchar(128) NOT NULL,
    expires_at timestamptz NOT NULL,
    promo_code varchar(32),
    consumed boolean NOT NULL DEFAULT false
);

CREATE TABLE promo_redemption (
    promo_id bigint NOT NULL REFERENCES promo (id),
    customer_id varchar(64) NOT NULL,
    PRIMARY KEY (promo_id, customer_id)
);
