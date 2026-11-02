insert into wallet (user_id, available_cents, pending_cents) values
    (1, 0, 0),
    (2, 500, 300),
    (3, 0, 0),
    (30, 0, 0);

insert into network_event (external_id, user_id, status, amount_cents, currency, fx_rate, processed, applied_cents)
values ('poll-1', 3, 'CONFIRMED', 100, 'EUR', 1.000000, false, 0);
