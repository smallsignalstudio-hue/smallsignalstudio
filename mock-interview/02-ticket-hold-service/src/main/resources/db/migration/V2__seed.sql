INSERT INTO event (id, name, venue, starts_at, on_sale_from, max_seats_per_order)
VALUES (100, 'Berlin Philharmonic: Mahler 5', 'Philharmonie Berlin',
        TIMESTAMP '2026-11-14 20:00:00', TIMESTAMP '2026-09-01 10:00:00', 6);

INSERT INTO seat (id, event_id, section, seat_row, seat_number, price_category, status, hold_id) VALUES
    (1,  100, 'A', 1, 1, 'PREMIUM', 'AVAILABLE', NULL),
    (2,  100, 'A', 1, 2, 'PREMIUM', 'AVAILABLE', NULL),
    (3,  100, 'A', 1, 3, 'PREMIUM', 'AVAILABLE', NULL),
    (4,  100, 'A', 1, 4, 'PREMIUM', 'SOLD',      NULL),
    (5,  100, 'A', 1, 5, 'PREMIUM', 'AVAILABLE', NULL),
    (6,  100, 'A', 1, 6, 'PREMIUM', 'AVAILABLE', NULL),
    (7,  100, 'A', 1, 7, 'PREMIUM', 'AVAILABLE', NULL),
    (8,  100, 'A', 1, 8, 'PREMIUM', 'AVAILABLE', NULL),
    (9,  100, 'A', 2, 1, 'STANDARD', 'AVAILABLE', NULL),
    (10, 100, 'A', 2, 2, 'STANDARD', 'AVAILABLE', NULL),
    (11, 100, 'A', 2, 3, 'STANDARD', 'AVAILABLE', NULL),
    (12, 100, 'A', 2, 4, 'STANDARD', 'AVAILABLE', NULL),
    (13, 100, 'A', 2, 5, 'STANDARD', 'AVAILABLE', NULL),
    (14, 100, 'A', 2, 6, 'STANDARD', 'AVAILABLE', NULL);

INSERT INTO hold (id, event_id, customer_id, status, created_at, expires_at, confirmed_at)
VALUES
    (50, 100, 7, 'CONFIRMED', now() - interval '1 day', now() - interval '1 day', now() - interval '1 day'),
    (51, 100, 8, 'HELD', (now() AT TIME ZONE 'Europe/Berlin') - interval '25 minutes',
        (now() AT TIME ZONE 'Europe/Berlin') - interval '15 minutes', NULL);

INSERT INTO seat (id, event_id, section, seat_row, seat_number, price_category, status, hold_id) VALUES
    (15, 100, 'B', 1, 1, 'STANDARD', 'SOLD', 50),
    (16, 100, 'B', 1, 2, 'STANDARD', 'SOLD', 50),
    (17, 100, 'B', 1, 3, 'STANDARD', 'HELD', 51);

SELECT setval(pg_get_serial_sequence('hold', 'id'), 100);
