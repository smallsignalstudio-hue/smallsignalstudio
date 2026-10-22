INSERT INTO rate (category, currency, unit_price, group_min, group_price) VALUES
    ('ADULT', 'EUR', 20, 10, 15),
    ('CHILD', 'EUR', 8, NULL, NULL),
    ('ADDON', 'EUR', 0.10, NULL, NULL),
    ('ADULT', 'JPY', 333, NULL, NULL);

INSERT INTO price_band (currency, threshold, flat_total) VALUES
    ('EUR', 10.0, 9.00);

INSERT INTO promo (code, percent, min_subtotal, max_redemptions) VALUES
    ('TEN', 10, 0, 1000),
    ('MIN100', 20, 100, 1000),
    ('OVER', 150, 0, 1000),
    ('ONCE', 10, 0, 5);
