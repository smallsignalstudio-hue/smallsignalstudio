# Supplier Connectivity — Live Coding Interview

A traveller books an activity that we do not operate ourselves. We reserve capacity with the supplier, take payment, then confirm the supplier booking. The supplier API is slow, sometimes answers `200` with an error body, and speaks in its own local time.

~75 minutes. State the failure rules before editing. The app container runs in `Europe/Berlin`. Postgres is UTC. Supplier and payment base URLs come from `supplier.base-url` and `payment.base-url`.

## Rules

- A call that does not return in about a second is a failure. Do not hold the request open.
- `POST /bookings` is not idempotent on the supplier side. Do not retry it blindly. If the outcome is unknown, record that and reconcile later.
- If payment fails, the supplier reservation must be released.
- An empty or failed availability response must not be cached.
- `200` with `errorCode` is not a booking.
- The supplier sends a local date-time and a zone. On the autumn overlap, `02:30` means the **later** instant, after the clocks fall back.
- Party size above 8 is rejected. That check is already in place.

## Setup

```bash
docker compose up --build -d
./gradlew test
```

## Part 1 — Reports

> **SUP-501 — The supplier hung and our pool filled up**
> Reserving activity 1 took the supplier 3 seconds. Our request sat there until the supplier answered. A few of those at once and the app stops taking traffic.

> **SUP-502 — One click, two bookings at the supplier**
> `POST /bookings` returned 500 and we tried again. The supplier created the booking on the second call, and also on the first. The traveller can be charged twice.

> **SUP-503 — Payment failed, the seats are still held**
> The card was declined. The supplier reservation is still `HELD`. That departure looks sold out to everyone else.

> **SUP-504 — Availability stayed empty after the supplier recovered**
> The first availability call failed. The supplier then returned slot `10:00`. We still show no slots.

> **SUP-505 — "No availability" was confirmed**
> The supplier answered HTTP 200 with `{"errorCode":"NO_AVAILABILITY"}`. We marked the order booked.

> **SUP-506 — The 02:30 cruise is an hour early**
> Activity 1 is `Europe/Amsterdam`. The supplier slot `2026-10-25T02:30` is the later of the two times that night (the clocks fall back). We stored the earlier instant.

## Part 2 — Feature

> **SUP-510 — Stop calling a sick supplier, then finish unknown bookings**
>
> After 3 consecutive reserve/book transport failures for one supplier, stop calling them and return 503. A bulkhead caps in-flight calls per supplier. `POST /internal/reconcile` looks up an `UNKNOWN` order by its idempotency key and either links the supplier booking or releases the reservation. It must not `POST /bookings` again.

## Part 3 — Refactor

> Hide the supplier JSON behind a port. Map their payloads in one adapter. The booking service should not know whether a field was called `reservationId` or `errorCode`.

## Afterwards

The book call timed out, reconcile finds nothing, and the supplier creates the booking five minutes later. What do you do?
