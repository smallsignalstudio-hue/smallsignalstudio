# Booking Cancellation — Live Coding Interview

A traveler cancels a confirmed booking. Inside 24 hours of the start we refund half of what they paid. Earlier than that we refund all of it. The payment provider must not move money unless our booking ends up cancelled.

~75 minutes. State the policy before editing. The app container runs in `Europe/Berlin`. Postgres is UTC.

## Rules

- Full refund at **24 hours or more** before the activity's own start. Otherwise 50% of the **amount paid** (after promo), never more than paid, never off the list price.
- Cancelling an already cancelled or completed booking is rejected.
- The same `Idempotency-Key` must not refund twice.
- The provider call happens only after the cancellation is stored. A failed save must not move money.
- The traveler gets an email. A failure to render it must not be silent.
- Supplier can cancel a whole slot. Each booking is refunded on its own. A retry of the same request does not pay twice. Failures are listed, not swallowed.

## Setup

```bash
docker compose up --build -d
./gradlew test
```

## Part 1 — Reports

> **CXL-501 — "I cancelled a day and a half ahead and only got half"**
> Booking 2 starts 2026-06-02 12:30 UTC. The traveler cancelled at 2026-06-01 12:00 UTC. Refund was 50, not the 100 they paid.

> **CXL-502 — Retry charged the card back twice**
> Booking 8 was cancelled, the client timed out, and the app sent the same `Idempotency-Key` again. There are two refund rows.

> **CXL-503 — Provider paid out, booking is still confirmed**
> A cancellation for booking 9 included a long note. The call failed and the booking is still `CONFIRMED`, but the payment provider already received a refund.

## Part 2 — Feature

> **CXL-510 — Weather closes the departure**
>
> `POST /slots/{slotId}/cancellations` with an `Idempotency-Key`. Refund every confirmed booking on that slot. If the provider rejects one, record it under `failed` and continue. A retry returns the same report and does not refund again.

## Part 3 — Refactor

> Cancellation is a script of status strings. Make allowed transitions explicit and turn the 24h / 50% rules into policy objects. Keep the tests green at each step.

## Afterwards

What would you do if the provider succeeds and our process dies before we store the reference?
