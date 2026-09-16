# Example Runs

Abbreviated outputs showing expected quality and format. Full runs include all phases.

---

## Example A — Focused vertical: habit tracking (Phase 3–6 excerpt)

**Context:** `{VERTICAL}` = habit tracking, country US, `maxAds` 75 × 2 terms, iOS-first, interactive.

**Seeds:** `habit tracker`, `habit widget`

### Phase 3 advertiser table (excerpt)

| Rank | Advertiser / app | Ads | Max days | Median days | Destinations |
|------|------------------|-----|----------|-------------|--------------|
| 1 | Streaks-like utility page | 22 | 118 | 41 | app store + site |
| 2 | Noisier "AI coach" habit app | 31 | 67 | 19 | paywall web + store |
| 3 | Generic planner template shop | 14 | 9 | 4 | Shopify — **killed (ecom)** |

### Phase 6 ranked ideas

| Rank | Idea (our wedge) | Score | MRR band | Kill? |
|------|------------------|-------|----------|-------|
| 1 | One-habit lock-screen widget (no social, no AI chat) | 79 | $1k–10k Est. | — |
| 2 | Quiet morning checklist from leave-home DNA | 71 | $1k–10k Est. | — |
| 3 | AI coach clone of #2 advertiser | 48 | $10k–50k Est. | **Killed** — backend + 4.6★ crowded |

**Why #1 won:** 118-day live image ads with "don't break the chain" copy; store reviews hate streak-guilt and clutter; widget-first is DNA-compatible. #2 has more ads but shorter median life and a chatty coach we will not build.

### Phase 4 store notes (finalist incumbent)

- 4.2★, ~18k reviews, sub mentioned in listing
- 1-star themes: "too many habits", "notifications nag", "widget is ugly / paid"
- Our NOT list: social feed, AI coach, habit marketplace

### Sample Phase 6 questions

1. Widget-only v1 vs one in-app habit detail screen for the paywall?
2. Incumbent already sells a widget — is "one habit, no guilt copy" enough of a wedge?
3. This is close to Sippin'/streak utilities — new app or pass?

---

## Example B — Swipe file + organic (Phase 5/7 excerpt)

**Winning ads (excerpt)**

| Days | Format / CTA | Snippet | Snapshot |
|------|----------------|---------|----------|
| 118 | IMAGE / LEARN_MORE | "One box. Did you do it today." | [Ad Library] |
| 96 | VIDEO / INSTALL | "Stop stacking 12 habits." | [Ad Library] |
| 41 | CAROUSEL / LEARN_MORE | "Morning: 3 taps. That's it." | [Ad Library] |

**Organic vs paid:** IG posts recycle the same "one box" stills as the 118-day ad (media buy + light content). Not a full UGC engine — good; we do not need Remotion to validate the idea.

**Killer feature:** Lock-screen widget: one habit, one checkbox, no streak shame copy.

**Explicitly NOT:** AI coach, friend feed, 12-habit dashboard.

---

## Example C — Phase 7 close + Phase 8 gate

### Devil's advocate

1. Habit category is crowded; ASO for "habit tracker" is expensive without a narrower keyword.
2. Widget-first apps convert worse if users never open the paywall.
3. Incumbent can ship a quieter widget in one update.
4. Ads running 118 days might be retargeting, not prospecting — demand overstated.
5. Daily checkbox without identity (ADHD / anxiety / leave-home) may churn after week two.

### Homework

1. Open the 118-day snapshot; confirm destination is still the same store listing.
2. Read 50 recent 1-star reviews for "widget" and "nag".
3. Screenshot competitor paywall prices.

### Recommendation

**Research more** — then **lets-blueprint** only if homework confirms the widget gap. Otherwise **re-roll** to the leave-home checklist (#2).

> **iterate**, **re-roll**, **genius-ideas**, or **lets-blueprint**?

---

## Example D — Token missing / pasted data

**User:** ads-ideas, no `APIFY_TOKEN`

**Agent:** Stop. Ask for token **or** a JSON/CSV export. Do not fabricate 150 ads.

**User:** pastes Library export.

**Agent:** Skip Phase 2 scrape. Normalize whatever fields exist; compute `daysRunning` if only start dates are present; continue Phase 3+ with **Unknown** on missing platforms/CTAs.

---

## Example E — Re-roll

**Rejected:** One-habit widget (user: "too close to apps I already use").

**Next:** Phase 3 row #2's quieter cousin — morning leave-home checklist. Score 71. New Phase 7 executive summary + devil's advocate. Ask the Phase 8 gate again.
