INSERT INTO supplier_account (id, code, zone_id) VALUES
    (1, 'canal', 'Europe/Amsterdam'),
    (2, 'night', 'Asia/Tokyo');

INSERT INTO activity (id, supplier_id, name) VALUES
    (1, 1, 'Canal cruise'),
    (2, 2, 'Night tour');

INSERT INTO customer_order (id, activity_id, status, reservation_status, supplier_reservation_id, supplier_booking_id, start_at, idempotency_key)
VALUES (50, 2, 'BOOKED', 'BOOKED', 'R-50', 'S-50', '2026-06-02 01:00:00+00', 'seed-50');

SELECT setval('customer_order_id_seq', 50);
