# Activity Catalog — Live Coding Interview

Welcome! This is a small slice of our catalog backend. It serves the activity cards you see on search result pages and on the activity detail page, plus a supplier lookup used by our partner support team.

You have **~75 minutes**. We don't expect you to finish everything — we're interested in how you reason about the business, how you debug, and how you make trade-offs. Think out loud, ask questions, and feel free to use the debugger, tests, and AI tools (tell us what you prompt and why).

## Business context

- **Activity**: something a traveler can book (a walking tour, a boat cruise, a museum ticket). It has a price in a currency, an average rating, and may be part of a **special offer** (10% off, shown as `finalPrice`).
- **Supplier**: the local company that operates the activity. Suppliers are synced nightly from the partner system; activities come from the content pipeline. The two pipelines are independent.
- Some activities are operated by GetYourGuide itself and have **no supplier**.
- Search results must be **stable and predictable**: the mobile app paginates the list endpoint and lowercases what the user types.

## Setup

```bash
docker compose up --build -d        # starts Postgres + the app on :8080
curl -s localhost:8080/actuator/health
./gradlew test                      # uses Testcontainers (Docker must be running)
```

Running from the IDE: `docker compose up -d db`, then run `CatalogApplication`.
Example requests are in `requests.http`.

## Part 1 — Bug reports (from our support & partner teams)

> **CAT-101 — Some activity pages are broken**
> "The detail page of *Berlin: Reichstag Dome and Government Quarter Tour* shows a generic error. Other Berlin tours open fine. Search for 'Berlin' also fails on the web — it worked last week."

> **CAT-102 — Partner integration gets 500 for removed activities**
> "Our affiliate partner polls `/activities/{id}` for activities that were taken offline. They get HTTP 500 and their alerting pages them at night. They expect a clear 'this does not exist' answer."

> **CAT-103 — Search behaves differently on mobile**
> "On the web, searching *Spree* shows the river cruises. On mobile the same search sometimes shows nothing at all."

Please fix these. We'd like to see how you prove the fix works.

## Part 2 — Feature request

> **CAT-110 — Supplier statistics for the partner dashboard**
>
> Partner managers want a new endpoint that gives an overview of each supplier's catalog:
>
> - number of activities,
> - average rating of their activities,
> - the cheapest activity price.
>
> The dashboard shows 20 suppliers per page, best-performing first. Product isn't sure yet what "best-performing" means — make a proposal.

## Part 3 — Refactoring

> The team plans to add more catalog features (filters, sorting options, more endpoints) next quarter. Today, almost every change touches `ActivityService` and `SupplierController`, and both are hard to test in isolation.
>
> Refactor the code so the next engineer can add features confidently. Explain what you change, in what order, and why.

## Things we may talk about afterwards

How would this behave with 300k activities and 5k requests per second? How would you know in production that something like CAT-101 is happening again?
