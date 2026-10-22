INSERT INTO resource_pool (id, name, capacity) VALUES
    (1, 'Spree boat 1', 10),
    (2, 'Tokyo guide team', 8);

INSERT INTO activity (id, title, zone_id, local_time, duration_minutes, capacity, cutoff_hours, resource_id) VALUES
    (1, 'Berlin: Morning canal cruise', 'Europe/Berlin', '10:00', 120, 10, 2, 1),
    (2, 'Berlin: Midday canal cruise', 'Europe/Berlin', '11:00', 120, 10, 2, 1),
    (3, 'Tokyo: Night food walk', 'Asia/Tokyo', '22:00', 240, 8, 2, 2);

INSERT INTO booking (activity_id, slot_start, party_size, status) VALUES
    (1, '2026-06-01 10:00:00+02', 4, 'CANCELLED');
