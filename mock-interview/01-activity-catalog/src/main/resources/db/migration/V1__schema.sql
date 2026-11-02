-- Suppliers are synced nightly from the partner system (see supplier-sync job).
-- Activities are imported from the content pipeline independently, so there is
-- deliberately no foreign key between the two tables.
CREATE TABLE supplier (
    id       BIGINT PRIMARY KEY,
    name     VARCHAR(255) NOT NULL,
    address  VARCHAR(255),
    zip      VARCHAR(20),
    city     VARCHAR(100),
    country  VARCHAR(100)
);

CREATE TABLE activity (
    id            BIGINT PRIMARY KEY,
    title         VARCHAR(255)     NOT NULL,
    price         DOUBLE PRECISION NOT NULL,
    currency      VARCHAR(3)       NOT NULL,
    rating        DOUBLE PRECISION,
    special_offer BOOLEAN          NOT NULL DEFAULT FALSE,
    supplier_id   BIGINT
);
