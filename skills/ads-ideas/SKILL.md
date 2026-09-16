---
name: ads-ideas
description: >-
  Finds solo-buildable mobile app ideas from live Meta/Instagram ads via Apify.
  Scores advertisers by ad longevity and volume, maps winning ads to apps, and
  writes a research report with a studio-DNA wedge. Use when the user says
  ads-ideas, "find a working app", "clone from ads", Meta Ad Library, Apify ads,
  paid-demand validation, or wants an ads-validated app idea.
disable-model-invocation: true
---
# Ads Ideas

Sibling of [`genius-ideas`](../genius-ideas/SKILL.md). That skill mines **unpaid pain** (Reddit/4chan). This skill reverse-engineers **paid demand**: apps already buying Meta/Instagram ads long enough that the spend is a profitability proxy.

Research and strategy only — do not write app code, generate Remotion/fal.ai creatives, or scaffold a project. Hand off a finalist to genius-ideas (deep dive / iterate) or [`lets-blueprint`](../lets-blueprint/SKILL.md) (spec pack).

## Builder DNA (filter every clone candidate through this)

Successful pattern from Small Signal Studio (Sippin', Batten, TaleSnack, Glossio):

- **Single job** — one problem, one killer feature
- **Daily or anxiety-triggered open** — habit loop or micro-anxiety moment
- **Local-first** where possible — low infra, privacy-friendly
- **Quiet, premium UX** — not a feature dump of the incumbent
- **Freemium** — free hook, paid depth
- **1–3 core features** — shippable in Cursor in weeks, not months

We clone **working small apps with a wedge**, not Co-Star. A 4.8★ well-funded incumbent with no review-theme gap is a kill, even if it runs 500 ads.

## Hard constraints (disqualify or heavily penalize)

Reject ideas that require:

- Heavy backend, realtime multiplayer, UGC moderation, marketplace dynamics
- Regulated advice (medical diagnosis, licensed financial/tax/legal advice)
- Competing with a free OS default (basic calculator, notes, flashlight)
- Dominated by well-funded 4.8★ incumbents with no clear wedge
- One-time use (no reason to open after day 3)
- MVP longer than ~6 weeks for a solo dev (unless user explicitly overrides)
- Pure ecommerce / dropshipping storefronts with no app job (unless user asked for that)

Backend: **none preferred**. Small backend OK only if clearly justified — state why.

## How users invoke this skill

| User says | Mode | Seed |
|-----------|------|------|
| "Use the ads-ideas skill to find a working app in **X**" | Focused | **X** as `{VERTICAL}` |
| "Use the ads-ideas skill to find a working app" | Generic | OPEN — pick highest paid-demand niche that still fits DNA |
| "ads-ideas" + competitor (app name, IG, Meta page, store URL) | Competitor | that advertiser + adjacent keywords |

Also accept: "ads-ideas", "clone from ads", "Meta Ad Library", "Apify ads", "find a working app".

**Follow-up after a finalist is presented:**

| User says | Action |
|-----------|--------|
| **iterate** | Stress-test the current wedge (more reviews, more ads on that advertiser, rewrite killer feature) |
| **re-roll** | Discard current finalist; take next-ranked advertiser/app |
| **genius-ideas** / deep dive | Hand off current finalist into genius-ideas Phase 5+ |
| **lets-blueprint** / blueprint | Stop research; user should open a project folder and invoke Let's Blueprint |

## Parse user input

| Input | Default if missing |
|-------|-------------------|
| `{VERTICAL}` or competitor | OPEN unless user named a topic or URL |
| Country | `US` (use `TR` if user says so) |
| Platform | iOS-first |
| `maxAds` | **150** total-ish (see reference). Warn before **400+**. 766 is a cost choice, not the default |
| Apify spend cap | **$5 USD** for the whole skill run (all Actor calls). Override only if the user names a higher cap |
| Instagram organic | **On for the finalist advertiser only** |
| Resolve landing-page snapshots | Off on the bulk pull; on only for the top ~20 ads if destinations are missing |
| Phase 6 mode | Interactive unless user says **full auto** |
| Save report to file | No unless user asks |

## Token and data rules

This environment has **no Apify MCP**. Call Apify **REST** (or `apify` CLI if installed) with `APIFY_TOKEN`.

1. Read token from env (`APIFY_TOKEN`), `~/Repo/.cursor-env.sh`, or a `.env` the user points at.
2. **If missing:** stop. Ask for the token **or** a pasted Ad Library JSON/CSV. Do **not** invent ad rows, days-running, or advertiser lists.
3. Save every Actor dataset to disk before analyzing. Do not re-download the same dataset every follow-up.
4. Always set `maxAds` / `resultsLimit` **and** Apify `maxTotalChargeUsd`.
5. **Spend cap is $5** for the entire ads-ideas session unless the user explicitly raises it. Pass remaining budget on every Actor run (`maxTotalChargeUsd`). Do not start another Actor if remaining budget is under ~$0.10. This is separate from Apify's own Free-plan **$5/month platform credit**.
6. Label every dollar figure **Verified / Estimated / Unknown**. Ad longevity is a **proxy**, not MRR.

Actor IDs, curl examples, field mapping, and scoring weights: [reference.md](reference.md).

## Execution spine (every run, in order)

Copy this checklist and track progress:

```
- [ ] Phase 0: Calibrate + check APIFY_TOKEN
- [ ] Phase 1: Seed queries
- [ ] Phase 2: Apify Meta Ad Library pull
- [ ] Phase 3: Normalize, rank ads, map to apps
- [ ] Phase 4: Lightweight store + landing screen (top 5)
- [ ] Phase 5: Instagram organic for the finalist only
- [ ] Phase 6: Score, kill, present top 3
- [ ] Phase 7: Deep-dive report + devil's advocate
- [ ] Phase 8: Ask iterate / re-roll / hand off
```

Do not skip phases 0–7 on the first pass. Do not deep-dive one advertiser before Phase 4 screens the top set.

**Keep session state:** ranked advertiser table, rejected apps, current finalist, Apify `runId` / local JSON path.

---

### Phase 0 — Calibrate + token

Infer defaults from context. Ask **at most 3 questions** via `AskQuestion` only if blocked:

1. Missing `APIFY_TOKEN` and no pasted dataset — token **or** file?
2. Vertical / competitor ambiguous (two niches, or OPEN with no hint)?
3. `maxAds` above 400 **or** spend cap above $5 — confirm spend?

Do not ask generic brainstorming questions. Proceed with defaults if unstated.

If the user pastes JSON/CSV: skip Phase 2 scrape; still run Phase 3+ on the file. Say so.

---

### Phase 1 — Seed queries

Build **2–5** Meta search terms. Show them before scraping.

**Focused:** `{VERTICAL}` plus 1–2 adjacent job-to-be-done phrases (e.g. `habit tracker`, `streak habit`, `habit widget`).

**Competitor:** advertiser/page name + the category keywords around it.

**OPEN:** pick seed terms from studio-DNA niches with paid-app density (habit/anxiety utilities, parenting micro-jobs, quiet ritual widgets) — **not** generic ecommerce brands. State why those seeds beat alternatives.

Also WebSearch for 3–5 named apps in the niche (store links). Those names become extra `searchTerms` if they look like advertisers.

---

### Phase 2 — Apify Meta Ad Library pull

Read [reference.md](reference.md) for exact Actor input.

1. Primary: `brilliant_gum/facebook-ads-library-scraper` with `searchTerms`, `countries`, `adActiveStatus: "ACTIVE"`, `maxAds` as calibrated. **Always** pass `maxTotalChargeUsd` = remaining session budget (default **5**).
2. `resolveSnapshotUrls: false` on the bulk run.
3. Start the run, poll until `SUCCEEDED` (or `ABORTED` because the $5 cap was hit — still fetch whatever items exist), **write** `~/Documents/ads-ideas/raw/{runId}.json` (create dirs). If the Documents path is unavailable, use `./ads-ideas/raw/{runId}.json` in the workspace.
4. If the primary returns 0 rows or fails: fallback `apify/facebook-ads-scraper` with Ad Library URLs (`q=` + country), same remaining `maxTotalChargeUsd`. Map fields per reference.
5. Report item count, **usageTotalUsd**, remaining cap, and console run URL. Subtract `usageTotalUsd` from the session budget before Phase 5 Instagram / optional crawler.

Do not proceed to scoring on an empty dataset — widen terms or ask one question.

---

### Phase 3 — Normalize, rank, map to apps

From the saved JSON (not a live re-fetch):

1. Deduplicate by `adArchiveId`.
2. Group by `pageName` / `pageId`.
3. Per advertiser compute: ad count, **max** and **median** `daysRunning`, active share, top `displayFormat`, top CTAs, unique `creatives[].destinationUrl` domains, `creativeVariantCount` signal.
4. Rank ads by `daysRunning` descending (tie-break: `creativeVariantCount`, then recency of `adDeliveryStartTime`).
5. Map each top advertiser → product: landing domain, App Store / Play URL (WebSearch if destination is a site), inferred model (subscription / IAP / lead-gen / ecom).

**Kill immediately:** political/issue ads, housing/employment/credit, obvious dropshipping catalogs, non-app lead-gen funnels (unless user asked).

Output an advertiser table (top 10) before Phase 4.

---

### Phase 4 — Store + landing screen (top 5)

For the **top 5** mapped apps, use WebSearch / WebFetch on public pages first (no extra Apify):

| Field | Required |
|-------|----------|
| Store listing | name, rating, review count, category, IAP/sub mention |
| Pricing | from listing, site, or ads CTA |
| 1-star themes | 3–5 quoted themes with links |
| Landing | job promised vs what the ads sell |
| Build complexity | weeks to a DNA-compatible MVP, backend Y/N |
| Clone-ability | funded 4.8 wall vs weak incumbent |

Only if public pages fail: optional App Store Actor or `apify/website-content-crawler` — see reference. Cap extra Actor calls.

Do not invent Sensor Tower downloads. MRR band is **Estimated** from review volume + ad longevity, tagged.

---

### Phase 5 — Instagram organic (finalist only)

After Phase 4, pick a **provisional finalist** (highest score that still passes DNA). Then:

1. Resolve IG handle from ads destinations, page, or WebSearch.
2. Run `apify/instagram-profile-scraper` with that one username (latest ~12 posts is enough).
3. Classify recent posts as organic vs boosting the same angles as long-running ads.
4. If no public IG: say so; skip. Do not scrape the whole niche's Instagram.

---

### Phase 6 — Score, kill, present top 3

Score **each of the top 5** 0–100 using weights in [reference.md](reference.md):

1. Paid-demand proof (30)
2. Clone-ability / weak incumbent (20)
3. Solo / local-first feasibility (20)
4. Retention / daily open (15)
5. Monetization clarity from the ads (15)

**Kill** anything scoring <55 unless the user revives it.

Present **top 3** with:

- Why #1 beat #2 (ad evidence, not vibes)
- Biggest reason each could **fail**
- One **Variant B** — same paid demand, quieter/local-first wedge (the thing we would actually ship)

Then ask **2–4 sharp questions**. **Full auto:** pick #1, state assumptions, continue.

Otherwise wait before Phase 7.

---

### Phase 7 — Deep dive + force depth

Full report for the chosen idea. Schema: [report-template.md](report-template.md).

If the user asked to save: write `~/Documents/ads-ideas/YYYY-MM-DD-{slug}.md` (and keep the raw JSON).

End every run with:

1. **Devil's advocate** — 5 reasons this fails
2. **Research homework** — 3 manual checks before building
3. **Next decision** — ship-to-blueprint / research more / kill — with an explicit recommendation
4. **Plan B** and **Plan C** (one paragraph + score)

The product we recommend is **our wedge**, not a 1:1 clone of the advertiser's feature list.

---

### Phase 8 — Iterate, re-roll, or hand off

Always end with (use `AskQuestion` when available):

> **iterate**, **re-roll**, **genius-ideas**, or **lets-blueprint**?
>
> - **iterate** — stress-test this wedge (reviews, more ads on this advertiser, killer-feature rewrite)
> - **re-roll** — discard this app; next-ranked advertiser
> - **genius-ideas** — take this finalist into genius-ideas deep dive / iterate
> - **lets-blueprint** — stop here; specify the app in a project folder next

**Iterate:** adversarial audit (min 10 cited points: ads + store reviews + organic). Verdict ship / iterate / rework — same spirit as genius-ideas Phase 7A. Stay inside builder DNA (max 2–3 changes).

**Re-roll:** add current finalist to rejected list; never re-propose this session. Fast-path Phase 5–7 on the next-ranked surviving candidate. If none remain, abbreviated Phase 1–3 with new seeds.

---

## Operational rules

- Cite snapshot URLs, store listings, and landing pages
- Never present ad longevity as verified revenue
- Prefer one great feature over cloning five mediocre ones
- If the ads prove a market but the incumbent is unbeatable, say so and re-roll
- Do not start building code or ad videos unless the user explicitly leaves this skill
- Cross-validate a long-running ad with a store listing or landing page before calling it an "app idea"

## Additional resources

- Actors, curl, field map, scoring, cost: [reference.md](reference.md)
- Phase 7 report schema: [report-template.md](report-template.md)
- Example run outputs: [examples.md](examples.md)
