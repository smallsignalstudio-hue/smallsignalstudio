# Coupon Redemption — Live Coding Interview

Atolls shoppers redeem a single-use code. The market this process serves is Turkey, but campaigns also exist for Germany and Hawaii. The fixed clock in this exercise is `2026-10-22T06:00:00Z` (`coupon.now`).

~75 minutes. State the rules before editing.

## Rules

- Codes are case-insensitive and ignore surrounding space. ` indirim ` is the code `INDIRIM`.
- `valid_until` is the last day the code works, in the campaign's own zone, inclusive.
- A per-user limit applies to that campaign, not to every campaign the user ever touched.
- A code can be redeemed once, even if two requests arrive together.
- The campaign list for a market is active offers for that market or active global offers that are still in date. Inactive and expired rows stay hidden.
- A basket minimum, if you find one, is not the bug.

## Setup

```bash
docker compose up --build -d
./gradlew test
```

## Part 1 — Reports

> **CPN-501 — `indirim` is not found**
> The code in the database is `INDIRIM`. The shopper typed ` indirim `. We returned 404.

> **CPN-502 — The last day is already over**
> Campaign `LAST` is valid through 2026-10-22 in UTC. The clock is that morning. Redeem is rejected.

> **CPN-503 — Hawaii still has a day left**
> Campaign `ISLAND` is valid through 2026-10-21 in `Pacific/Honolulu`. At 06:00 UTC it is still 21 October there. Redeem is rejected.

> **CPN-504 — A limit from another campaign**
> User 7 already used a code on the French campaign. The German campaign allows one use and this user has not used it. Redeem of `FRESH` is rejected.

> **CPN-505 — The German list is wrong**
> `GET /campaigns?market=DE` shows a closed German campaign and a global offer that ended in 2020.

> **CPN-506 — One code, two redemptions**
> Two shoppers redeemed `RACE` at the same moment. Both got a success. There are two redemption rows.

## Part 2 — Feature

> **CPN-510 — Mint codes, and slow the guesses down**
>
> `POST /campaigns/{id}/codes` with `{"count": N}` starts a job. Codes are 8 characters from a base32 alphabet with no `0`, `O`, `1`, `I` or `L`, from `SecureRandom`. A collision retries. `GET /jobs/{id}` reports progress and `DONE`.
> After 5 unknown codes for the same user, further attempts return 429.

## Part 3 — Refactor

> Split listing, redeem, and minting. A CSV of existing codes should stream in batches, not `readAllBytes`.

## Afterwards

The mint job dies at 400_000 of 1_000_000. How do you resume without duplicates?
