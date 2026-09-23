# Phase 7 Report Template

Use this schema for the deep-dive report. Replace bracketed placeholders with research-backed content. Cite snapshot URLs, store listings, and landings.

```markdown
# [Working title] — Ads-validated idea report

**Date:** YYYY-MM-DD
**Vertical:** [vertical]
**Country / platform:** [US / iOS-first]
**Finalist score:** [X/100]
**Apify run:** [runId + local raw JSON path]
**Recommendation:** [Hand off to lets-blueprint / genius-ideas deep dive / Research more / Kill]

---

## 1. Executive bet

[One paragraph: the app **we** would ship (wedge), the incumbent that is already paying for ads, why the spend is a demand signal, why we are not cloning their IA.]

---

## 2. Market from ads

| Metric | Value | Quality |
|--------|-------|---------|
| Search terms | [list] | — |
| Ads scraped | N | Verified (dataset) |
| Unique advertisers | N | Verified |
| Active / inactive | N / N | Verified |
| Median days running | N | Verified |
| Max days running | N | Verified |
| Top formats | [VIDEO / IMAGE / CAROUSEL %] | Verified |
| Top CTAs | [list] | Verified |
| Spend / reach | [ranges or "not disclosed"] | Verified if present, else omit |

**Read:** [2–4 sentences: concentration vs fragmentation, whether this looks like a real app category.]

---

## 3. Ranked advertisers / apps

| Rank | Advertiser / app | Score | Ads | Max days live | Landing / store | Rating | Kill? |
|------|------------------|-------|-----|---------------|-----------------|--------|-------|
| 1 | [name] | 00 | N | N | [url] | X.X★ (N) | — |
| 2 | … | | | | | | |
| 3 | … | | | | | | |

**Why #1 beat #2:** [evidence]

---

## 4. Winning-ad swipe file

Top 8–12 ads by `daysRunning` (then variant count). Quote copy; do not dump the full dataset.

| Days | Advertiser | Format / CTA | Copy snippet | Snapshot |
|------|------------|--------------|--------------|----------|
| N | | | "…" | [link] |

**Angles that keep running:** [3–5 patterns — problem, proof, offer, CTA.]

---

## 5. Organic vs paid (finalist)

**IG handle:** [@…](url) or none

| Surface | What they push | Overlap with long-running ads |
|---------|----------------|-------------------------------|
| Paid (Meta) | | |
| Organic (latest posts) | | |

[One paragraph: content engine vs media-buy-only. If no IG, say skipped.]

---

## 6. Business model (inferred)

| Signal | Finding | Quality |
|--------|---------|---------|
| Ads CTA / destination | | Verified |
| Store listing / IAP | | Verified / Estimated |
| Trial / price | | |
| Our proposed tiers | Free / Pro $X/mo / $X/yr | Estimated |

Do not tag MRR **Verified** unless a founder published a number.

---

## 7. Incumbent weakness / our wedge

**Incumbent:** [name, rating, reviews]

**1-star themes (quoted):**

| Quote | Source |
|-------|--------|
| "…" | [link] |

### Killer feature (one)
[Single sentence. This is our app.]

### Supporting features (max 2)
1. …
2. …

### Explicitly NOT building
- [clone of their tab 3]
- [social / feed / chat]
- [backend we do not need]

### Why we're different
[Falsifiable: they do X; reviews complain about Z; we do Y.]

---

## 8. Honest money

| Scenario | Month 6 | Month 12 | Month 24 |
|----------|---------|----------|----------|
| Pessimistic | $X MRR | $X MRR | $X MRR |
| Base | $X MRR | $X MRR | $X MRR |
| Optimistic | $X MRR | $X MRR | $X MRR |

**Band:** [$0–1k / $1k–10k / $10k–50k / $50k+] — **Estimated**

**Assumptions:** [downloads/mo, conversion, churn, ARPU]. Ad longevity supports demand, not these numbers.

### Kill criteria

| Milestone | Metric | Action if missed |
|-----------|--------|------------------|
| Day 30 | | |
| Day 60 | | |
| Day 90 | | |

---

## 9. Build sketch (not a blueprint)

| Week | Deliverable |
|------|-------------|
| 1 | |
| 2 | |
| 3 | |
| 4 | MVP |

- **Stack:** [e.g. SwiftUI / Expo, local-first]
- **Backend:** None / Minimal — [what]
- **Compliance risks:** [health claims, kids, subscriptions]

Full md pack = **lets-blueprint**. Do not scaffold app code here.

---

## 10. Devil's advocate, homework, next decision

1. [fail reason]
2. …
3. …
4. …
5. …

**Homework (3):** [manual Ad Library click, 50 reviews, price screenshot]

**Recommendation:** [one of the four Phase 8 options] — [why]

### Plan B — [name]
[Same paid demand, different wedge. Score: X/100.]

### Plan C — [name]
[Adjacent advertiser or quieter MVP. Score: X/100.]
```
