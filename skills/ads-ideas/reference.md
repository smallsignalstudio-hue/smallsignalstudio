# Ads Ideas — Reference

Read this file during Phases 0–5. Do not load unless running the skill.

## Authentication

```bash
# Token must exist before any Actor call
: "${APIFY_TOKEN:?APIFY_TOKEN is required}"

# Optional: source studio env
# [ -f "$HOME/Repo/.cursor-env.sh" ] && source "$HOME/Repo/.cursor-env.sh"
```

REST uses the same token as `apify login`. Actor IDs in URLs replace `/` with `~`.

Prefer CLI if `apify` is on PATH; otherwise curl.

### Start a run (CLI)

```bash
apify actors call "brilliant_gum/facebook-ads-library-scraper" \
  -i '{"searchTerms":["habit tracker"],"countries":["US"],"adType":"ALL","adActiveStatus":"ACTIVE","maxAds":150,"resolveSnapshotUrls":false}' \
  --json
```

From the JSON take `.id` (run ID), `.status`, `.defaultDatasetId`, `.consoleUrl`.

### Start a run (REST)

```bash
curl -sS -X POST \
  "https://api.apify.com/v2/acts/brilliant_gum~facebook-ads-library-scraper/runs?token=${APIFY_TOKEN}" \
  -H "Content-Type: application/json" \
  -d '{"searchTerms":["habit tracker"],"countries":["US"],"adType":"ALL","adActiveStatus":"ACTIVE","maxAds":150,"resolveSnapshotUrls":false}'
```

Poll until status is `SUCCEEDED` / `FAILED` / `ABORTED` / `TIMED-OUT` (sleep 5–15s; ads pulls often take 1–10 minutes):

```bash
curl -sS "https://api.apify.com/v2/actor-runs/${RUN_ID}?token=${APIFY_TOKEN}"
```

Do **not** use `run-sync-get-dataset-items` for 150+ ads — it times out. Start + poll + fetch.

### Fetch dataset and save locally

```bash
# CLI
apify datasets get-items "$DATASET_ID" --format json \
  > "$OUTDIR/${RUN_ID}.json"

# REST
curl -sS "https://api.apify.com/v2/datasets/${DATASET_ID}/items?token=${APIFY_TOKEN}&format=json" \
  -o "$OUTDIR/${RUN_ID}.json"
```

Default `$OUTDIR`: `$HOME/Documents/ads-ideas/raw` (create it). Workspace fallback: `./ads-ideas/raw`.

All later analysis reads **this file**. Do not call `get-items` again for the same run.

## Actor routing

| Need | Primary | Fallback |
|------|---------|----------|
| Keyword / niche Meta ads | `brilliant_gum/facebook-ads-library-scraper` | `apify/facebook-ads-scraper` (Ad Library URLs) |
| Ads from a known Page | Same primary with page name as `searchTerms` | `apify/facebook-ads-scraper` `startUrls` with `view_all_page_id` Library URL |
| Finalist Instagram profile + latest posts | `apify/instagram-profile-scraper` | Skip if no public handle |
| Landing page markdown | WebFetch first | `apify/website-content-crawler` (max 5 pages) |
| App Store listing | WebFetch / WebSearch first | App Store Actor only if public pages fail |

### Primary input — `brilliant_gum/facebook-ads-library-scraper`

`maxAds` is **per search term × country**. Three terms × `US` × 150 ≈ 450 ads. Keep the product of terms × countries × maxAds near the calibrated budget (default **150 total-ish**: e.g. 3 terms × 1 country × `maxAds: 50`, or 1 term × 150).

```json
{
  "searchTerms": ["habit tracker", "habit widget"],
  "countries": ["US"],
  "adType": "ALL",
  "adActiveStatus": "ACTIVE",
  "maxAds": 75,
  "resolveSnapshotUrls": false
}
```

Set `resolveSnapshotUrls: true` only for a **second, small** run on the top ~20 `adArchiveId` / snapshot URLs if `creatives[].destinationUrl` is empty and you need landings.

Optional: `startDate` / `endDate` (`YYYY-MM-DD`) to bound recency.

Do **not** set `adType` to political/housing/employment unless the user asked.

### Fallback input — `apify/facebook-ads-scraper`

Takes **URLs**, not keywords. Build a Library search URL:

```
https://www.facebook.com/ads/library/?active_status=active&ad_type=all&country=US&q=habit%20tracker&search_type=keyword_unordered&media_type=all
```

```json
{
  "startUrls": [
    { "url": "https://www.facebook.com/ads/library/?active_status=active&ad_type=all&country=US&q=habit%20tracker&search_type=keyword_unordered&media_type=all" }
  ],
  "resultsLimit": 150
}
```

Map fallback fields onto the primary schema before scoring (`pageName`, start/stop dates, snapshot URL, body text). Compute `daysRunning` yourself if missing: calendar days from delivery start to today if still active, else to stop time.

### Instagram — `apify/instagram-profile-scraper`

One handle. Latest ~12 posts on the profile payload are enough for organic vs paid.

```json
{
  "usernames": ["exampleapp"]
}
```

### Website crawler — `apify/website-content-crawler`

Only if WebFetch failed. `proxyConfiguration` is required.

```json
{
  "startUrls": [{ "url": "https://example.com" }],
  "maxCrawlPages": 5,
  "proxyConfiguration": { "useApifyProxy": true }
}
```

## Output field mapping (primary Actor)

Each dataset item is **one ad**. Rank and group from these fields:

| Field | Use |
|-------|-----|
| `adArchiveId` | Dedupe key |
| `pageId` / `pageName` | Advertiser group |
| `adActiveStatus` | Active vs inactive split |
| `adDeliveryStartTime` / `adDeliveryStopTime` | Recency |
| `daysRunning` | **Primary rank key** — longevity ≈ keep-spending proxy |
| `creativeVariantCount` | How hard they are testing this collation |
| `displayFormat` / `mediaType` / `cardCount` | Image vs video vs carousel |
| `publisherPlatforms` | FB / IG / etc. |
| `creatives[].body` / `title` / `ctaText` / `ctaType` | Swipe-file copy |
| `creatives[].destinationUrl` | Landing / store mapping |
| `snapshotUrl` | Citation link |
| `spend` / `impressions` / `euTotalReach` | Cite **only if non-null**; usually empty for commercial ads |
| `pageLikeCount` / `pageCategories` | Advertiser size / category |

**Rank ads:** `daysRunning` desc, then `creativeVariantCount` desc, then newest `adDeliveryStartTime`.

**Rank advertisers:** max `daysRunning`, then ad count, then unique destination domains that look like apps.

Ignore or kill rows that are clearly political (`categories`, `fundingEntity`, `bylines`) unless the user asked.

## Cost caps

| Actor | Ballpark | Cap |
|-------|----------|-----|
| `brilliant_gum/facebook-ads-library-scraper` | ~$0.0005–$0.015 per ad (plan-dependent) + tiny start fee | Default product of terms×countries×maxAds **≤ 150**. Warn at **400+**. Hard-stop ask at **800+** |
| `apify/facebook-ads-scraper` | ~$3.40–$5.80 / 1k ads | Same row budget |
| `apify/instagram-profile-scraper` | ~$1.60 / 1k profiles | **1 profile** (finalist) |
| `apify/website-content-crawler` | compute + proxy | **≤ 5 pages**, one domain |

150 ads is the default because the source video's 766-ad pull is a spend choice, not a quality floor. A clean 80–150 active ads in-niche beats a noisy 766.

If a run `FAILED` / `TIMED-OUT`: open `consoleUrl`, reduce `maxAds` or terms, retry once. Never silently skip.

## Scoring rubric (0–100)

| Criterion | Weight | High score | Low score |
|-----------|--------|------------|-----------|
| Paid-demand proof | 30 | Many active ads, max `daysRunning` 30+, variants being tested | 1–2 ads, all <7 days, no variants |
| Clone-ability / weak incumbent | 20 | 3.0–4.3★ or cluttered UX with quoted 1-star themes we can fix | 4.8★ funded giant, no wedge |
| Solo / local-first feasibility | 20 | Widget/tracker/reminder; no backend | Marketplace, realtime, UGC, ML training |
| Retention / daily open | 15 | Daily ritual or anxiety trigger in the ads themselves | One-shot download, seasonal gimmick |
| Monetization clarity | 15 | Ads + listing show sub/IAP/trial | Vague "learn more" into a blog with no paywall |

**Kill threshold:** score <55 — discard unless the user revives.

Sub-score paid-demand (0–30) using evidence, not vibes:

- +10 if max `daysRunning` ≥ 45 and still `ACTIVE`
- +8 if advertiser has ≥ 8 distinct ads
- +7 if `creativeVariantCount` or multiple copy angles
- +5 if destinations resolve to an app store or a clearly metered product

## Mapping ads → an app we would ship

1. Collect destination domains. Prefer `apps.apple.com` / `play.google.com` / branded app sites.
2. If the landing is Shopify/ecom only: **kill** (unless user wants ecom).
3. Name the incumbent honestly. Then write **our** one-liner + killer feature (studio DNA). Cloning their five-tab IA is a fail.
4. MRR band (`$0–1k` / `$1k–10k` / `$10k–50k` / `$50k+`) is **Estimated** from: review count, rating, ad longevity, pricing. Never tag Verified unless the founder published a number.

## Seed-term ideas (OPEN mode)

Prefer DNA-compatible consumer jobs, not brand names:

- habit / streak / quit / morning ritual
- leave-home / lock / keys / anxiety checklist
- hydration / fasting / supplements reminder
- parenting / newborn / school morning
- focus / body-double / ADHD timer (watch support burden)
- widgets / lock screen / quiet daily one-thing

Skip as OPEN seeds: fashion, dropshipping, crypto, betting, dating mega-apps, "ChatGPT wrapper" SaaS.

## Error handling

| Symptom | Action |
|---------|--------|
| 401 / auth | Token missing or revoked — stop and ask |
| `Actor not found` | Check `user~name` spelling |
| 0 items | Fallback Actor; then different `searchTerms` / country; then one user question |
| Timeout | Lower `maxAds`; split terms into separate runs |
| `proxy is required` | Add `"proxyConfiguration": {"useApifyProxy": true}` |
| Spend/impressions all null | Normal for commercial ads — do not treat as zero spend |
| Instagram private / no handle | Skip Phase 5; say so |

## Honesty rules

- `daysRunning` ≠ ROAS. Phrase: "still buying this creative after N days — **Estimated** paid-demand signal."
- Empty `euTotalReach` / `spend`: omit, do not zero-fill.
- Cross-check one long-running ad against a store page before calling the cluster an app idea.
