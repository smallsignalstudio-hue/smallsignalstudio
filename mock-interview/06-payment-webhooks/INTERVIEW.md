# Payment Webhooks — Live Coding Interview

The payment provider notifies us when a booking is authorised, captured, or refunded. We confirm the booking and issue the voucher. Notifications are delivered at least once, sometimes out of order, and the provider signs the raw HTTP body.

~75 minutes. State the delivery rules before editing. The app container runs in `Europe/Berlin`. Postgres is UTC. Shared HMAC secret is `test-secret` (`psp.hmac-secret`). Header: `X-Signature` (hex HMAC-SHA256).

## Rules

- Verify the signature on the **raw** body. Reject anything else with 401.
- The same `pspReference` + `eventCode` must not confirm a booking twice or issue two vouchers.
- A payment only moves forward: `PENDING → AUTHORISED → CAPTURED → REFUNDED`.
- Amounts arrive in minor units. EUR has 2 decimals, JPY has 0, BHD has 3.
- Unknown event types are acknowledged so the provider can move on.
- The HTTP handler returns as soon as the notification is stored. Voucher rendering is not part of the request.

## Setup

```bash
docker compose up --build -d
./gradlew test
```

## Part 1 — Reports

> **WH-501 — Two vouchers for one payment**
> Booking 1 was authorised once. The provider timed out and sent the same notification again. The traveller has two voucher codes.

> **WH-502 — A captured booking went back to authorised**
> Booking 3 was already `CAPTURED`. A delayed `AUTHORISATION` for the same booking arrived later. The booking is now `AUTHORISED`.

> **WH-503 — A notification the provider signed was rejected**
> Booking 7. The provider signed the raw bytes, including `"pspFee":10.50`. We answered 401. Their dashboard shows the signature as valid.

> **WH-504 — Yen charged as 10, dinars charged as 12.34**
> Booking 2 is JPY. Minor value `1000` means 1000 yen. The booking shows 10. Booking 4 is BHD. Minor value `1234` means 1.234. The booking shows 12.34.

> **WH-505 — The queue is stuck on a report notification**
> Booking 5 received `REPORT_AVAILABLE`. We returned 500. The provider will retry it forever and will not deliver the capture behind it.

## Part 2 — Feature

> **WH-510 — Exactly once, even when rendering fails**
>
> Store the notification, answer immediately, and issue the voucher from an outbox. A second delivery is a no-op. `POST /internal/outbox/flush` retries anything still pending. After 3 failed attempts the row is `DLQ`, not an infinite retry.

## Part 3 — Refactor

> One class currently both verifies the call and applies every event. Split a handler per event type. Keep the forward-only status rule in one place.

## Afterwards

What do you do when the voucher service succeeds and this process dies before the outbox row is marked published?
