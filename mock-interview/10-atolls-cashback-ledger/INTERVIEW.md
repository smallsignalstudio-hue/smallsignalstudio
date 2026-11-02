# Cashback Ledger — Live Coding Interview

Atolls credits shoppers when an affiliate network confirms a purchase, then pays the available balance out. Amounts in this service are integer euro cents. Config holds today's rates (`fx.current.USD=0.50`, `fx.current.EUR=1`). Each network event also carries the rate from the day it happened.

~75 minutes. State the rules before editing. `POST /internal/poll` is what a worker runs. Two workers are two overlapping calls; you do not need a second container.

## Rules

- Convert with the `fxRate` on the event. Half-up to euro cents. `1000` USD cents at `0.10` is `100` euro cents. `15` USD cents at `0.10` is `1.5`, which rounds to `2`.
- One `externalId` is one purchase. A repeated confirmation does not credit again. A higher confirmation credits only the difference. A `PENDING` notice that arrives after `CONFIRMED` does not rewind the balance or the status. `DECLINED` removes what was credited.
- Pending wallet balance is not payable. A payout moves only `availableCents` and leaves `pendingCents` where it is.
- The same `Idempotency-Key` pays once.
- A decline after the money has already been paid may drive the balance negative. Do not clamp at zero.
- A network fee of zero, if you find one, is not the bug.
- Two workers must not credit the same stored event twice. Two different confirmations for one shopper, arriving together, must both land.

## Setup

```bash
docker compose up --build -d
./gradlew test
```

## Part 1 — Reports

> **CB-501 — Ten dollars became five euros**
> The network confirmed `1000` USD cents at `fxRate` `0.10`. The wallet shows `500` euro cents.

> **CB-502 — Fifteen cents became seven**
> The network confirmed `15` USD cents at `0.10`. The wallet shows `7` euro cents.

> **CB-503 — The retry paid twice**
> The same confirmation, `250` EUR cents at rate `1`, was delivered twice. The wallet shows `500`.

> **CB-504 — Pending undid a confirmation**
> A purchase was confirmed for `250` EUR cents. A `PENDING` notice for that same id arrived afterwards. The balance doubled and the stored status is `PENDING`.

> **CB-505 — A correction was added on top**
> The network confirmed `250` EUR cents, then confirmed the same purchase again at `400`. The wallet shows `650`.

> **CB-506 — Pending cash was paid out**
> Shopper 2 has `500` available and `300` still pending. The payout sent `800` and the pending balance is now `0`.

> **CB-507 — Two confirmations, one credit**
> Two different confirmations of `100` EUR cents each hit shopper 30 together. The wallet shows `100`.

> **CB-508 — Both workers paid the same row**
> One unprocessed confirmation of `100` EUR cents is waiting. Two overlapping polls both succeed. The wallet shows `200`.

## Part 2 — Feature

> **CB-510 — A ledger that can go negative**
>
> Every credit, payout, and reversal is a double entry: the shopper's leg and the opposite leg (`NETWORK` for commission, `CASH` for a payout). The two legs sum to zero.
> `GET /wallets/{id}/ledger` returns those rows. The wallet's available balance is the sum of the `USER` legs.
> `POST /wallets/{id}/payouts` with `Idempotency-Key` pays the available balance once. A second call with the same key does not pay again.
> A `DECLINED` event after that payout writes a reversal. The balance may be negative. The next payout must not invent money to hide it.

## Part 3 — Refactor

> The wallet columns are a projection. The append-only ledger is the source of truth. Split intake, polling, payout, and posting so a balance update cannot happen without a ledger line.

## Afterwards

The projection says `0` and the ledger says the shopper owes `1000`. Which number do you pay from, and how do you repair the row?
