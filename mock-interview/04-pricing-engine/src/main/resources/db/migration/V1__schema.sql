CREATE TABLE rate (
    id bigserial PRIMARY KEY,
    category varchar(16) NOT NULL,
    currency varchar(3) NOT NULL,
    unit_price double precision NOT NULL,
    group_min int,
    group_price double precision,
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
