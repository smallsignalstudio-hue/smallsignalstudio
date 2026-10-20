ALTER TABLE activity ALTER COLUMN price TYPE NUMERIC(12, 2) USING ROUND(price::numeric, 2);

CREATE INDEX idx_activity_supplier_id ON activity (supplier_id);
CREATE INDEX idx_activity_rating_id ON activity (rating DESC NULLS LAST, id);
