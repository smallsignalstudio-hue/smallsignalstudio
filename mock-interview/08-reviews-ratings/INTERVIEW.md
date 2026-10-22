# Reviews and Ratings — Live Coding Interview

Travellers review an activity after it has happened. The activity page shows an average, a page of reviews, and the author's name and photos.

~75 minutes. State the rules before editing. The app container runs in `Europe/Berlin`. Postgres is UTC. The caller sends `X-Customer-Id`.

## Rules

- A review belongs to the customer who booked. The booking must be `CONFIRMED` and already started.
- The average is the arithmetic mean, one decimal place. 23 points over 5 reviews is 4.6, not 4.
- Two writers updating the same activity's totals must not lose a point.
- Paging must not show the same review twice when a newer review arrives between pages.
- Body length is counted in Unicode code points. The limit is 20. The column holds 80.
- Loading a page of reviews must not query once per author and once per photo.
- Rating must be from 1 to 5. That check is already correct.

## Setup

```bash
docker compose up --build -d
./gradlew test
```

## Part 1 — Reports

> **REV-501 — The average is a whole number**
> Activity 1 has reviews 5, 5, 4, 5 and 4. The page shows 4. The traveller expects 4.6.

> **REV-502 — Two reviews posted together and one vanished from the total**
> Activity 2 started at one review. Eight travellers posted a 5 at the same moment. The count is not 9.

> **REV-503 — Page two showed the review I had just read**
> I asked for one review, then someone posted a newer one, then I asked for the next page using the cursor. I got the same review again.

> **REV-504 — A short emoji review was rejected**
> Fifteen thumbs-up is under the 20-character limit. The API returned 400.

> **REV-505 — Reviews that should not exist**
> A cancelled booking was reviewed. A booking that has not happened yet was reviewed. Customer 1 reviewed customer 2's booking.

> **REV-506 — The review page got slow with the number of reviews**
> Each review on the page costs extra queries for the author and the photos.

## Part 2 — Feature

> **REV-510 — "Most relevant", and hide a review without lying about the average**
>
> `GET /activities?sort=relevant` ranks by a Bayesian average: prior mean 3.5, prior weight 5. A single 5 must not beat a long-standing 4.6.
> `POST /reviews/{id}/hide` removes the review from the list and from the sum and the count.
> `POST /internal/ratings/rebuild` recomputes every activity from the visible reviews, so a drift can be repaired.

## Part 3 — Refactor

> Keep the write of a review separate from the read model. The page should read a projection. Rebuild is the reconciliation, not the request path.

## Afterwards

You hide a review while a new one is being posted. How do you keep the projection honest?
