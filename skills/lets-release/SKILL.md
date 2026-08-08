---
name: lets-release
description: >-
  Let's Release — full App Store / TestFlight pre-upload gate for Small Signal Studio.
  Use when the user says Let's release, yeni yük, TestFlight, markete gönder, App Store Connect,
  ASC metadata, ASO, pricing, or preflight before upload. Orchestrates code preflight, ASO/keyword
  refresh across localizations (shipped + upcoming features), and ASC metadata/pricing sync.
  Never uploads screenshots. Never builds/submits a binary unless the user explicitly asks.
---

# Let's Release

End-to-end **pre-upload** workflow for shipping mobile apps (Expo / React Native) across all `~/Repo` projects.

Sibling: [`app-store-preflight`](../app-store-preflight/SKILL.md) remains the code/compliance gate. This skill **orchestrates** that gate and adds ASO + ASC metadata.

## When to use (auto-trigger phrases)

- `Let's release` / `Let's release the app` / `Let's release a new load to TestFlight`
- `yeni yük` / `TestFlight'a yük` / `markete gönder`
- ASC metadata, ASO keywords, store pricing before upload
- Before `eas build` / `eas submit` / selecting a build for review

## Hard rules

1. **No binary** unless the user explicitly asks to upload / build / submit.
2. **Never touch screenshots** (create, replace, delete, or upload). If they look stale, mention it **only at the very end** of the final reply with a short reason.
3. Prefer project ASC scripts when present (`npm run asc:sync`, `asc:push-metadata`, etc.).
4. ASC auth: project `.env` `ASC_KEY_*` first; sibling org fallback only if the project documents it; else report the blocker honestly — do not claim ASC was updated.

## Mandatory order

### 1. Discover project

- Mobile root (`mobile/` or repo root with `app.json`)
- Version / bundle ID from `app.json`
- Store listing drafts (`docs/store/`, `store.config.json`, locale markdown)
- `tasks/todo.md` / roadmap for shipped vs upcoming features
- Existing preflight script or `npm test` / lint / typecheck

### 2. Preflight / tests

- Run the strongest available gate (`app-store-preflight`, project `preflight`, or `npm test` + typecheck + forbidden-copy).
- Fix CRITICAL blockers or report clearly. Do not pretend green.

### 3. ASO refresh (all supported storefront locales)

For each locale with a listing draft:

1. Diff **shipped** product surfaces vs listing body (honesty).
2. Diff **upcoming** features in todo/backlog — add keyword foresight only when honesty allows; never claim unshipped megafatures in description.
3. Competitor / mid-tail pass for that market (local heads beat English transliteration).
4. Fill ASC keywords ≤100 chars (comma-separated, no spaces after commas; no competitor brands; no charity/donation lemmas).
5. Update locked listing docs + regenerate `store.config.json` when the project has a builder.

Respect project monetization / forbidden-copy gates (e.g. Saff `forbiddenCopy.ts`).

### 4. ASC metadata + pricing (no screenshots)

When credentials work:

- Upsert localizations: name, subtitle, keywords, description, promotional text, what’s new, support/marketing/privacy URLs
- Ensure IAP / subscription products + display names + price points match product SoT
- Leave screenshots alone
- Report console-only leftovers (agreements, age rating, Privacy Nutrition Labels, etc.)

### 5. Final operator reply

Keep it short:

1. Preflight result
2. ASO what changed (high level)
3. ASC what landed vs blocked
4. Remaining human gates
5. How to re-trigger (`Let's release…`)
6. **Screenshot note last, only if needed**

## Project-specific notes

### Saff

- Listings: `docs/store/asc-play-listing.*.md` + `docs/research/aso-localization.md`
- Scripts: `cd mobile && npm run asc:sync` (build-config → setup → push-metadata)
- SKUs: `saff_premium_monthly` ($2.99) / `saff_premium_yearly` ($19.99)
- Thin Qur’an is shipped — describe honestly; no mushaf megapp / mosque finder / Diyanet-in-keywords
- Privacy / Terms: `https://smallsignalstudio.com/privacy-saff.html`, `…/terms-saff.html`

### Sippin / Batten

- Prefer existing `asc:*` / `eas:metadata:push` / preflight npm scripts in that repo
- Still run ASO honesty + feature/todo keyword check before upload

## Failure handling

- Tests fail → fix or stop
- ASC 401 / missing key → finish repo listing updates; print exact auth fix steps
- Missing bundle ID in Developer portal → cannot create ASC app until registered
