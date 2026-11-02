CREATE TABLE customer (
    id bigint PRIMARY KEY,
    name varchar(80) NOT NULL
);
CREATE TABLE activity (
    id bigint PRIMARY KEY,
    title varchar(80) NOT NULL
);
CREATE TABLE booking (
    id bigint PRIMARY KEY,
    customer_id bigint NOT NULL REFERENCES customer (id),
    activity_id bigint NOT NULL REFERENCES activity (id),
    status varchar(16) NOT NULL,
    start_at timestamptz NOT NULL
);
CREATE TABLE review (
    id bigserial PRIMARY KEY,
    booking_id bigint,
    customer_id bigint NOT NULL REFERENCES customer (id),
    activity_id bigint NOT NULL REFERENCES activity (id),
    rating int NOT NULL,
    body varchar(80) NOT NULL,
    created_at timestamptz NOT NULL,
    hidden boolean NOT NULL
);
CREATE TABLE photo (
    id bigserial PRIMARY KEY,
    review_id bigint NOT NULL REFERENCES review (id),
    url varchar(120) NOT NULL
);
CREATE TABLE activity_rating (
    activity_id bigint PRIMARY KEY REFERENCES activity (id),
    rating_sum int NOT NULL,
    rating_count int NOT NULL,
    average_rating int NOT NULL
);
