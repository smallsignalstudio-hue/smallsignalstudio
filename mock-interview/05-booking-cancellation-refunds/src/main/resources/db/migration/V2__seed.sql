INSERT INTO customer (id, email) VALUES
    (1, 'ada@example.test'),
    (2, 'guest@example.test'),
    (3, 'bounce@supplier.test');

INSERT INTO booking (id, customer_id, status, start_at, zone_id, list_price, paid_amount, currency, slot_id) VALUES
    (1, 1, 'CONFIRMED', '2026-06-02 18:00:00', 'Asia/Tokyo', 100.00, 40.00, 'EUR', NULL),
    (2, 1, 'CONFIRMED', '2026-06-02 12:30:00', 'UTC', 100.00, 100.00, 'EUR', NULL),
    (3, 1, 'CANCELLED', '2026-07-01 10:00:00', 'UTC', 100.00, 100.00, 'EUR', NULL),
    (4, 1, 'COMPLETED', '2026-05-01 10:00:00', 'UTC', 100.00, 100.00, 'EUR', NULL),
    (5, 1, 'CONFIRMED', '2026-08-01 10:00:00', 'UTC', 80.00, 80.00, 'EUR', NULL),
    (6, 1, 'CONFIRMED', '2026-06-01 18:00:00', 'UTC', 100.00, 40.00, 'EUR', NULL),
    (7, 2, 'CONFIRMED', '2026-06-10 12:00:00', 'UTC', 80.00, 80.00, 'EUR', NULL),
    (8, 2, 'CONFIRMED', '2026-06-11 12:00:00', 'UTC', 80.00, 80.00, 'EUR', NULL),
    (9, 2, 'CONFIRMED', '2026-06-12 12:00:00', 'UTC', 80.00, 80.00, 'EUR', NULL),
    (10, 1, 'CONFIRMED', '2026-09-01 10:00:00', 'UTC', 30.00, 30.00, 'EUR', 77),
    (11, 2, 'CONFIRMED', '2026-09-01 10:00:00', 'UTC', 30.00, 30.00, 'EUR', 77),
    (12, 3, 'CONFIRMED', '2026-09-01 10:00:00', 'UTC', 30.00, 30.00, 'EUR', 77);
