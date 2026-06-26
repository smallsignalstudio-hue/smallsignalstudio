# Small Signal Ideas — Reference

Read this file during Phase 1 and Phase 3. Do not load unless running the skill.

## Reddit targets

Search broadly — not only these subs:

**Idea / builder communities**
- r/SomebodyMakeThis
- r/AppIdeas
- r/Entrepreneur
- r/SideProject
- r/indiehackers
- r/startups
- r/passive_income

**Platform / dev**
- r/iosprogramming
- r/androidapps
- r/iOSBeta
- r/apple

**Vertical subs** — match `{VERTICAL}` or top signal themes (e.g. r/ADHD, r/astrology, r/NewParents, r/fitness, r/languagelearning)

## Search query patterns

Run multiple literal searches per vertical:

```
"I wish there was an app"
"is there an app for"
"I'd pay for"
"worth the subscription"
"not worth the subscription"
"why is every [X] app"
"shut up and take my money"
"cancelled my subscription because"
"looking for an app that"
"any app recommendations for"
```

Combine with vertical keywords when `{VERTICAL}` is set.

## 4chan boards

| Board | Use for |
|-------|---------|
| /g/ | Tool gaps, privacy-local apps, "just make X", tech pain |
| /biz/ | Money ideas — **heavy noise**; cross-validate only |
| Niche boards | When vertical set: /fit/, /an/, etc. |

**Filter rules:**
- Ignore shitposts, memes, and single-post rants
- Extract only **recurring pain patterns** seen 2+ times or with agreement replies
- Never cite 4chan alone — require Reddit, App Store, or Indie Hackers corroboration

## Scoring rubric (0–100)

| Criterion | Weight | High score signals |
|-----------|--------|-------------------|
| Payment intent | 25 | "I'd pay for", existing paid subs in niche, price complaints on competitors |
| Retention / daily open | 20 | Daily ritual, widget value, anxiety trigger, streak mechanics |
| Differentiation gap | 20 | Weak incumbents (3.0–3.8★), clear 1-star themes you can fix |
| Solo build feasibility | 20 | Local-first, no backend, MVP ≤4 weeks, no moderation |
| MRR ceiling (12 mo) | 15 | Comps in $10k–50k band, growing category, annual sub friendly |

**Kill threshold:** score <55 — discard unless user revives.

## MRR research sources (triangulate)

Label every figure **Verified / Estimated / Unknown**.

| Source | What to extract |
|--------|-----------------|
| App Store / Play Store | Review count, rating, subscription mentions in reviews, pricing |
| Indie Hackers | "MRR update", "revenue", launch posts |
| Twitter/X | Founder MRR screenshots — verify if possible |
| Sensor Tower / Appfigures / data.ai | Download/revenue estimates — always **Estimated** |
| MicroAcquire / Acquire.com | Listing revenue if public |
| Product Hunt | Launch comments, competitor comparisons |
| Similar apps' pricing pages | Actual price points |

Never present a single source as fact. Ranges beat false precision.

## Money-printing signals (small mobile apps)

| Signal | Why it monetizes |
|--------|------------------|
| Daily ritual | Habit → retention → sub conversion |
| Identity / emotion | Astrology, parenting, ADHD, grief — users pay for "this is me" |
| Micro-anxiety moment | One stressful instant, one tap to relief (Batten pattern) |
| iOS widget / Live Activity | Glanceable value → daily opens without opening app |
| Weak incumbents | 3.2★ + 10k reviews = proven demand, bad supply |
| Annual plan anchor | $24.99–$39.99/yr utilities; $59.99+ identity niches |
| Low support surface | No UGC, no chat, no moderation |
| ASO-friendly keyword | Discoverable job-to-be-done in App Store search |

## Disqualifiers (auto-penalize or reject)

- Needs realtime multiplayer or social graph
- Needs ML model training or heavy GPU
- Crowded with well-funded 4.8★ apps and no wedge
- Google/Apple ships it free by default
- Seasonal-only use (e.g. one week per year) without retention hook
- HIPAA, financial licensing, or medical device territory

## Vertical focus mode

When `{VERTICAL}` is set (e.g. "astrology"):

1. Prioritize vertical subreddits and niche boards
2. Screen named incumbents first (e.g. Co-Star, Sanctuary, The Pattern for astrology)
3. Find **one intriguing killer feature** — not a full platform clone
4. Variant B should stay in vertical but shift monetization (e.g. widget-first vs notification-first)

When `{VERTICAL}` is OPEN:

1. Let Phase 1 signals determine the vertical
2. Prefer verticals where payment intent + weak incumbents overlap
3. State why the chosen vertical beats alternatives in the ranked table
