INSERT INTO customer (id, name) VALUES (1, 'Ada'), (2, 'Grace');
INSERT INTO activity (id, title) VALUES (1, 'Canal cruise'), (2, 'New tour');
INSERT INTO booking (id, customer_id, activity_id, status, start_at) VALUES
    (1, 1, 1, 'CONFIRMED', '2026-05-01 10:00:00+00'),
    (2, 1, 1, 'CANCELLED', '2026-05-01 10:00:00+00'),
    (3, 1, 1, 'CONFIRMED', '2026-12-01 10:00:00+00'),
    (4, 2, 1, 'CONFIRMED', '2026-05-01 10:00:00+00'),
    (6, 1, 1, 'CONFIRMED', '2026-05-02 10:00:00+00'),
    (21, 1, 2, 'CONFIRMED', '2026-05-01 10:00:00+00'),
    (22, 1, 2, 'CONFIRMED', '2026-05-01 10:00:00+00'),
    (23, 1, 2, 'CONFIRMED', '2026-05-01 10:00:00+00'),
    (24, 1, 2, 'CONFIRMED', '2026-05-01 10:00:00+00'),
    (25, 1, 2, 'CONFIRMED', '2026-05-01 10:00:00+00'),
    (26, 1, 2, 'CONFIRMED', '2026-05-01 10:00:00+00'),
    (27, 1, 2, 'CONFIRMED', '2026-05-01 10:00:00+00'),
    (28, 1, 2, 'CONFIRMED', '2026-05-01 10:00:00+00');
INSERT INTO review (id, booking_id, customer_id, activity_id, rating, body, created_at, hidden) VALUES
    (1, NULL, 1, 1, 5, 'A', '2026-06-01 10:00:00+00', false),
    (2, NULL, 1, 1, 5, 'B', '2026-06-02 10:00:00+00', false),
    (3, NULL, 1, 1, 4, 'C', '2026-06-03 10:00:00+00', false),
    (4, NULL, 1, 1, 5, 'D', '2026-06-04 10:00:00+00', false),
    (5, NULL, 1, 1, 4, 'E', '2026-06-04 10:00:00+00', false),
    (6, NULL, 1, 2, 5, 'F', '2026-06-01 10:00:00+00', false);
SELECT setval('review_id_seq', 6);
INSERT INTO photo (review_id, url) VALUES (1, 'p1'), (2, 'p2'), (3, 'p3'), (4, 'p4'), (5, 'p5');
INSERT INTO activity_rating (activity_id, rating_sum, rating_count, average_rating) VALUES
    (1, 23, 5, 4),
    (2, 5, 1, 5);
