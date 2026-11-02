CREATE TABLE campaign (
    id bigint PRIMARY KEY,
    name varchar(80) NOT NULL,
    market varchar(8),
    zone_id varchar(64) NOT NULL,
    global_offer boolean NOT NULL,
    active boolean NOT NULL,
    valid_until date NOT NULL,
    per_user_limit int NOT NULL
);
CREATE TABLE coupon (
    id bigint PRIMARY KEY,
    campaign_id bigint NOT NULL REFERENCES campaign (id),
    code varchar(40) NOT NULL,
    redeemed_by bigint
);
CREATE TABLE redemption (
    id bigserial PRIMARY KEY,
    coupon_id bigint NOT NULL REFERENCES coupon (id),
    user_id bigint NOT NULL,
    campaign_id bigint NOT NULL
);
CREATE TABLE code_job (
    id bigserial PRIMARY KEY,
    campaign_id bigint NOT NULL,
    requested int NOT NULL,
    generated int NOT NULL,
    status varchar(16) NOT NULL
);
