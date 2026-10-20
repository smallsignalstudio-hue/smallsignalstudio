# Availability Calendar — Live Coding Interview

Travelers pick a date and see which departures still have space. Each activity runs every day at a local time, in its own city. Some activities share a boat or a guide.

~75 minutes. State the rules before editing. The `Clock` bean is the time source you should build on.

## Rules

- `from` and `to` are **inclusive** calendar dates in the activity's zone.
- The departure local time does not move when daylight saving starts or ends.
- A tour that runs 22:00–02:00 belongs to the day it **starts**.
- `CANCELLED` bookings do not consume capacity.
- Booking closes `cutoffHours` before departure, measured in the **activity** zone.
- Slot lists are cached. A booking must not keep serving the previous remaining count.

## Setup

```bash
docker compose up --build -d
./gradlew test
```

## Part 1 — Reports

> **AVL-301 — "Show me just 1 June" is empty, and 30 March is missing from a range that ends that day**
> `GET /activities/1/slots?from=2026-06-01&to=2026-06-01` returns nothing. A range that ends on 2026-03-30 also drops 30 March.

> **AVL-302 — The morning cruise jumped an hour after the clocks changed**
> Berlin canal cruise is 10:00. On 29 March 2026 (Europe/Berlin springs forward) the calendar shows 11:00.

> **AVL-303 — Tokyo night walk is on the wrong day, and a cancelled group still blocks seats**
> The 22:00–02:00 food walk does not appear on the evening the guest selected; it shows up the next day. Separately, a cancelled party of 4 on the 1 June 10:00 Berlin cruise still reduces remaining from 10 to 6.

## Part 2 — Feature

> **AVL-310 — One boat, two cruises**
>
> Activity 1 (10:00–12:00) and activity 2 (11:00–13:00) share `resource_pool` "Spree boat 1" (capacity 10). Overlapping departures must draw from that pool, not from each activity's own capacity. If the midday cruise already has 6 confirmed seats, the morning cruise has 4 left for the overlapping hour. Non-overlapping days are independent. Say what you do with the slot cache when a booking on one activity changes the other.

## Part 3 — Refactor

> Slot generation is mixed into the booking service and depends on the server zone. Pull it into a pure function of `(activity, from, to)`, take "now" from `Clock`, and cover DST, inclusive dates and the overnight tour with plain unit tests.

## Afterwards

How stale can a cached calendar be during on-sale, and what would you cache instead?
