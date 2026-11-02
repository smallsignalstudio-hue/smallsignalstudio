# Ticket Hold Service — Live Coding Interview

This service is the on-sale path for reserved seating. A customer picks seats, we hold them for a few minutes, then payment confirms the hold and the seats become sold. If the customer walks away, the seats must go back on sale. Two customers must never end up with the same seat.

You have ~75 minutes. Talk through the business rules before changing code. Tests and a debugger are expected. AI is allowed if you can explain the result.

## Business rules

- A hold lasts **10 minutes** (`holds.ttl-minutes`).
- A hold is all-or-nothing: if any requested seat cannot be taken, **none** of them stay held.
- Only the customer who created a hold can confirm it.
- Confirming sells the seats. An expired or abandoned hold must not touch a seat that was already sold.
- One order can take at most `maxSeatsPerOrder` seats (6 for tonight's concert).
- The app container runs in `Europe/Berlin`. Postgres runs in UTC. That split is intentional and matches production.

## Setup

```bash
docker compose up --build -d
curl -s localhost:8080/actuator/health
./gradlew test
```

`requests.http` has the main calls. Customer identity is the `X-Customer-Id` header (the edge gateway sets it; do not build login).

## Part 1 — Reports

> **BOX-201 — Abandoned checkout still blocks the seats**
> Customer 8 started a hold on Philharmonie seat B1-3 about 20 minutes ago and closed the tab. The seat is still not for sale. TTL is 10 minutes. `POST /internal/expire` is what production runs every few seconds.

> **BOX-202 — A paid booking was put back on sale**
> Customer 7 paid yesterday for Philharmonie seats B1-1 and B1-2. This morning those seats are available again and a second customer can hold them. The hold is still marked confirmed.

> **BOX-203 — Failed group booking leaves seats stuck**
> A customer asked for A1-1, A1-2, A1-3 and A1-4 together. A1-4 is already sold, so the call failed. The other three seats now show as held, the customer has no hold id that works, and nobody else can buy them.

## Part 2 — Feature

> **BOX-210 — "Best available" for the mobile app**
>
> The app wants `POST /events/{eventId}/holds/best-available` with `{ "section": "A", "count": 3 }`.
>
> Place the party in one row, in consecutive seat numbers, inside the requested section. Never leave a single leftover seat in that row (a gap of one is unsellable). If the only way to seat them creates such a gap, fail clearly and seat nobody.
>
> On-sale traffic is a burst: thousands of clients hit this at the same second. Two parties must not be given the same seats, and a slow party must not block a whole row for everyone else.

## Part 3 — Refactor

> `HoldService` is an anemic script: status changes are scattered, time is read from the wall clock, and the expiry job reaches into tables with SQL the service does not own.
>
> Make seat and hold state transitions explicit, take time from the `Clock` bean that already exists, and keep the TTL in configuration. Behaviour of the fixed bugs must stay covered by tests.

## Afterwards

How would you stop two buyers getting seat A1-5 at the same moment? What would you change before this runs as two app replicas?
