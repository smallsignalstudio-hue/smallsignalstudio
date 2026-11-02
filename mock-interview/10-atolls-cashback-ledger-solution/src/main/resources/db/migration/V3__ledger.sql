create unique index network_event_external_id_uk on network_event (external_id);
create unique index payout_idempotency_uk on payout (idempotency_key);

create table ledger_entry (
    id bigserial primary key,
    user_id bigint not null,
    external_id varchar(64) not null,
    kind varchar(16) not null,
    account varchar(16) not null,
    amount_cents int not null
);
