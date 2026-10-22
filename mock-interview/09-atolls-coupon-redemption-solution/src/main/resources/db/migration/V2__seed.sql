INSERT INTO campaign (id, name, market, zone_id, global_offer, active, valid_until, per_user_limit) VALUES
    (1, 'Fresh', 'DE', 'Europe/Berlin', false, true, '2026-12-01', 1),
    (2, 'Other', 'FR', 'Europe/Paris', false, true, '2026-12-01', 1),
    (3, 'Closed', 'DE', 'Europe/Berlin', false, false, '2026-12-01', 1),
    (4, 'Old global', NULL, 'Europe/Berlin', true, true, '2020-01-01', 1),
    (5, 'Last day', 'UTC', 'UTC', false, true, '2026-10-22', 1),
    (6, 'Island', 'HI', 'Pacific/Honolulu', false, true, '2026-10-21', 1);
INSERT INTO coupon (id, campaign_id, code, redeemed_by) VALUES
    (10, 1, 'FRESH', NULL),
    (11, 2, 'OTHER', 7),
    (12, 5, 'LAST', NULL),
    (13, 6, 'ISLAND', NULL),
    (14, 1, 'INDIRIM', NULL),
    (15, 1, 'RACE', NULL);
INSERT INTO redemption (coupon_id, user_id, campaign_id) VALUES (11, 7, 2);
