CREATE UNIQUE INDEX customer_order_idempotency ON customer_order (idempotency_key);
