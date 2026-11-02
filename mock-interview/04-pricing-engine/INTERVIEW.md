# Pricing Engine — Live Coding Interview

Travelers pick adult, child and add-on tickets. Some parties get a group rate. Promotions and partner price bands change the quote. Checkout must charge the amount the traveler was shown.

~75 minutes. Say how money, rounding and "a group" work before editing.

## Rules

- Catalog prices are **tax-included**. Do not subtract VAT.
- Money is exact. JPY has no fraction digits; EUR has two. Round the **quote total** once, half up.
- The adult group rate applies when the party reaches `group_min` (**10**), not only above it.
- A promotion's minimum is judged on the **pre-discount** subtotal. The total never goes below zero.
- Children are a separate ticket. Finance has not written down whether they count toward the group size — ask if you need a rule.
- A quote is the price we will charge. It stays valid for **15 minutes**.

## Setup

```bash
docker compose up --build -d
./gradlew test
```

`requests.http` has sample calls. Identity is `customerId` in the body.

## Part 1 — Reports

> **PRC-401 — Add-on total is a long decimal**
> Eight map add-ons are 0.10 each. `POST /quotes` shows `0.7999999999999999` instead of 0.80.

> **PRC-402 — A party of exactly 10 still pays the full adult price**
> The group rate (15 instead of 20) is published for parties of 10 or more. A quote for 10 adults comes back as 200.

> **PRC-403 — Yen quotes show a fraction**
> Three adult tickets in JPY with promo `TEN` (10% off) should be a whole number of yen. The quote shows a fractional amount.

## Part 2 — Feature

> **PRC-410 — Promo limits and a locked quote**
>
> Promo `ONCE` may be used **5 times in total** and **once per customer**. Two checkouts at the same moment must not both take the last use.
>
> `POST /quotes` returns an id, a total, an expiry 15 minutes out, and a signature. `POST /quotes/{id}/checkout` charges that total even if the catalog changed, rejects a bad signature, and rejects an expired quote.

## Part 3 — Refactor

> Pricing is a chain of `if`s on `double`. Introduce a `Money` value and a small rule chain (base rate, group rate, promo, clamp, currency scale) so a new rule does not edit the middle of the method.

## Afterwards

Where would you put the FX conversion if a partner settles in a second currency?
