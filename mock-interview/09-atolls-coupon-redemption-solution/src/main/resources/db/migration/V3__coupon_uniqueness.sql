CREATE UNIQUE INDEX coupon_code ON coupon (code);
CREATE UNIQUE INDEX redemption_coupon ON redemption (coupon_id);
