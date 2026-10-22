create table network_event (
    id bigserial primary key,
    external_id varchar(64) not null,
    user_id bigint not null,
    status varchar(16) not null,
    amount_cents int not null,
    currency varchar(3) not null,
    fx_rate numeric(12, 6) not null,
    processed boolean not null default false,
    applied_cents int not null default 0
);

create table wallet (
    user_id bigint primary key,
    available_cents int not null,
    pending_cents int not null
);

create table payout (
    id bigserial primary key,
    user_id bigint not null,
    amount_cents int not null,
    idempotency_key varchar(64) not null
);
