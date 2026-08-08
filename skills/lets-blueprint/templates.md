# Let's Blueprint — Document Templates

Use these skeletons in Phase 5. Replace `{SLUG}`, `{Display Name}`, and all bracketed fields with interview-locked facts. Delete sections that truly do not apply only after user confirmation — prefer explicit "Not used" over silent omission for third parties.

Do not invent product facts to fill brackets; if still unknown, return to Phase 2.

---

## README.md

```markdown
# {Display Name}

> {One-sentence job}

**Studio:** Small Signal Studio  
**Platform:** iOS + Android (React Native + Expo)  
**Status:** Documentation / Week 0 — no application code yet

## Documents

| File | Purpose |
|------|---------|
| [`{SLUG}.md`]({SLUG}.md) | Product overview — read first |
| [`{SLUG}_STITCH_DESIGN.md`]({SLUG}_STITCH_DESIGN.md) | Paste into Google Stitch |
| [`{SLUG}_BUILD.md`]({SLUG}_BUILD.md) | Technical blueprint + Week 0 + pre-launch decisions |
| [`AGENTS.md`](AGENTS.md) | Agent standards (TDD, commits, DoD) |
| [`tasks/todo.md`](tasks/todo.md) | Active checklist |

## Start here

1. Finish Week 0 boxes in `tasks/todo.md`
2. Open `{SLUG}_STITCH_DESIGN.md` in Stitch for UI
3. Next Cursor session: follow `{SLUG}_BUILD.md` — do not scaffold until Week 0 is green
```

---

## {SLUG}.md — Product overview

```markdown
# {Display Name} — Project Overview

> {One-line positioning}

**Platform:** iOS + Android (React Native + Expo)  
**Build approach:** Cursor / AI-agent driven, solo dev  
**Studio:** Small Signal Studio  
**GitHub:** `{github-url-or-TBD}`

---

## Table of Contents

1. [Problem Statement](#1-problem-statement)
2. [Research Summary](#2-research-summary)
3. [App Concept](#3-app-concept)
4. [What This App Is NOT](#4-what-this-app-is-not)
5. [Core Feature Summary](#5-core-feature-summary)
6. [Market Position](#6-market-position)
7. [Related Documents](#7-related-documents)

---

## 1. Problem Statement

{Who hurts. Why. Core insight in a blockquote.}

## 2. Research Summary

{Sources + table: Problem | What People Think | Root Cause | What's Missing}

{Why this app wins in this category}

## 3. App Concept

**{Display Name}** {paragraph}

**Tagline:** *{tagline}*

**Design mandate:** Apple Head-of-Design bar — radical simplicity, quiet luxury. See `{SLUG}_STITCH_DESIGN.md`.

## 4. What This App Is NOT

{Bullet list of explicit exclusions — permanent unless strategic review reopens}

## 5. Core Feature Summary

| Feature | Tier | One-line description |
|---------|------|----------------------|
| … | Free / Premium | … |

Full screen-by-screen and logic detail: `{SLUG}_BUILD.md`.

## 6. Market Position

| App | … | … |
|-----|---|---|
| Competitors | | |
| **{Display Name}** | | |

**Edge:** {one paragraph}

## 7. Related Documents

| Document | Purpose |
|----------|---------|
| `{SLUG}.md` | This file — product overview, read first |
| `{SLUG}_STITCH_DESIGN.md` | Google Stitch design brief |
| `{SLUG}_BUILD.md` | Technical blueprint + Week 0 + §10 decisions |
| `AGENTS.md` | Agent standards |
```

---

## {SLUG}_STITCH_DESIGN.md — Google Stitch brief

```markdown
# {Display Name} — Google Stitch Design Brief

> Paste this entire document into Google Stitch as the design prompt. It is self-contained.

---

## 0. What This App Is (Context for the Designer)

{2–4 sentences. Entire scope. What is out of scope.}

## 1. The Single Most Important Instruction

**Design this exactly as if you were the Head of Design at Apple, and Apple itself were shipping this app.**

That means:
- Radical simplicity. If a screen has more than one primary focal point, remove elements until it does.
- Every screen should feel like it could ship inside iOS itself — restraint, confidence, nothing unnecessary.
- Emotional target: **quiet luxury** — premium, calm, not category kitsch.
- If in doubt, remove. Whitespace is a feature.
- Never let the design read as "{category} cliché." Prefer the calm of a premium utility / luxury companion over loud marketing UI.

## 2. Visual Direction

### Palette
- {Base / surface / text / single accent — hex if locked}
- Absolutely no neon soup; no more than one accent hue on screen at once.
- {Dark/light default}

### Typography
- {Display face role} + {Body/UI face role}
- No more than two font families.
- Type should breathe.

### Iconography
- {Rules: line weight, no glow, no mascots, etc.}

### Motion
- Slow, deliberate, singular — one element moves at a time.

## 3. Screens to Design (Design Every One — Do Not Skip Any)

For each screen: full high-fidelity mobile frame (iOS-style portrait, modern iPhone safe areas).

### 3.1 {Screen name}
- {Layout, hierarchy, primary CTA, what must NOT appear}

### 3.2 …
{Continue until EVERY MVP screen + paywall + share card if any is listed}

## 4. Explicit Anti-Patterns (Do Not Produce These)

- {Category clichés}
- No dense feature-bullet onboarding carousels
- No red/orange urgency colors {unless user explicitly required}
- No more than one primary CTA per screen
- {App-specific bans}

## 5. Deliverable Checklist for Stitch

Generate all screens in Section 3, visual language in Section 2, Section 1 above all else:
**if Apple's head of design shipped this, what would it look like.**
```

---

## {SLUG}_BUILD.md — Technical blueprint

```markdown
# {Display Name} — Technical Build Blueprint

> Master technical document for Cursor / AI-agent-driven development. Read `{SLUG}.md` first.

**GitHub:** `{url-or-TBD}`  
**Platform:** iOS + Android (React Native + Expo)  
**Build approach:** Cursor / AI-agent driven, solo dev

---

## Table of Contents

1. [Scope Lock](#1-scope-lock)
2. [Core Logic](#2-core-logic)
3. [App Screens](#3-app-screens)
4. [Design Direction (Summary)](#4-design-direction-summary)
5. [Tech Stack](#5-tech-stack)
6. [Project Structure](#6-project-structure)
7. [Week 0 — Third-Party Provisioning](#7-week-0--third-party-provisioning)
8. [Build Timeline](#8-build-timeline)
9. [Monetization Model](#9-monetization-model)
10. [Anti-Feature List](#10-anti-feature-list)
11. [Pre-Launch Decision List — MANDATORY](#11-pre-launch-decision-list--mandatory)
12. [Agent Workflow](#12-agent-workflow)

---

## 1. Scope Lock

The MVP is exactly this loop, nothing more, until Section 11 decisions are made:

1. …
2. …

Out of scope permanently (see `{SLUG}.md`): …

## 2. Core Logic

### 2.1 …
{Pure functions, data models, algorithms — unit-testable}

## 3. App Screens

Visual spec: `{SLUG}_STITCH_DESIGN.md`. Functional spec:

### Screen …
- …

## 4. Design Direction (Summary)

Full brief: `{SLUG}_STITCH_DESIGN.md`. Implementation notes: palette, type, CTA rule, no urgency colors.

## 5. Tech Stack

| Layer | Technology | Why |
|-------|------------|-----|
| Framework | React Native + Expo | Studio standard |
| Local storage | … | … |
| Backend | … or None | … |
| Notifications | Expo Notifications | … |
| Navigation | Expo Router | … |
| State | Zustand | … |
| Monetization | RevenueCat | … |
| Testing | Jest + RNTL | AGENTS.md gates |

**Explicitly not used:** …

## 6. Project Structure

\`\`\`
{folder}/
├── app/
├── components/
├── lib/
├── services/
├── store/
├── content/
├── tasks/
├── docs/
├── __tests__/
├── {SLUG}.md
├── {SLUG}_STITCH_DESIGN.md
├── {SLUG}_BUILD.md
├── AGENTS.md
└── README.md
\`\`\`

(Structure is target state after scaffold — this skill does not create `app/`.)

## 7. Week 0 — Third-Party Provisioning

**Do not write application code until every Required item below is checked in `tasks/todo.md`.**

| Service | Status | Week 0 human steps |
|---------|--------|--------------------|
| GitHub | Required | Create repo `{name}`; add remote; push docs |
| Supabase | Required / Not used | … |
| RevenueCat | Required / Week 4 define names in Week 0 | … |
| … | | |

Agent must walk the user through each Required row and wait for confirmation.

## 8. Build Timeline

### Week 0 — Provisioning
- [ ] All Section 7 Required rows complete

### Week 1 — Foundation
- [ ] …

### Week 2 — …
- [ ] …

### Week 3 — …
- [ ] …

### Week 4 — Monetization + Launch Prep
- [ ] RevenueCat SDK (if applicable)
- [ ] Section 11 decisions resolved
- [ ] Store metadata / TestFlight
- [ ] Submission (blocked on §11)

## 9. Monetization Model

### Free Tier (Forever)
- …

### Premium — {price band}
- …

## 10. Anti-Feature List

| Feature | Reason |
|---------|--------|
| … | … |

## 11. Pre-Launch Decision List — MANDATORY

Candidates for "the feature that makes someone pay." **Before App Store submission, each item must be resolved.** Only acceptable resolutions:

- **Implement in v1** — build before submission, or
- **Defer to v2** — move to post-launch roadmap with reason.

No other answer is acceptable.

1. **{Idea}** — {one line}. [ ] Implement in v1 / [ ] Defer to v2
2. …
3. …
4. …
5. …
6. …

Revisit in Week 4; record decisions in `tasks/todo.md` before marking App Store Submission complete.

## 12. Agent Workflow

### Initialization (Start of Every Session)
1. Read `{SLUG}.md` and `{SLUG}_BUILD.md`
2. Read `tasks/todo.md` (including §11 status)
3. Read `tasks/lessons.md`
4. Confirm branch + git status

### Task Execution Order
1. Write/update `tasks/todo.md` for the work unit
2. Check in if plan is unclear
3. Implement one item at a time (TDD per `AGENTS.md`)
4. Verify
5. Commit + push per completed slice
6. Update `tasks/todo.md` + `tasks/lessons.md` if corrected

### Handoff

Next session: read `{SLUG}.md` + `{SLUG}_BUILD.md` + `tasks/todo.md`. **Do not write app code until Week 0 boxes are checked.**

See `AGENTS.md` for Definition of Done and pre-merge checklist.
```

---

## AGENTS.md

```markdown
> Every change in this repository must follow the rules below. If a task conflicts with them, stop and ask rather than silently lowering the bar.

### Definition of Done (Mandatory)

Before saying "done":

- [ ] Tests written **first** (Red), then code to pass (Green), then refactor
- [ ] Coverage: line + branch coverage complete in every changed module; `npm run test:coverage` passes (100% statements/branches/functions/lines on `lib/`, `services/`, `store/` per `jest.config.js`, plus documented exclusions only)
- [ ] Edge cases: at least **two distinct edge-case tests** per happy-path scenario (for `lib/` and `services/` exports, treat each export as its own happy path unless documented otherwise)
- [ ] Integration tests cover every business case end-to-end
- [ ] App builds from a clean state; all tests pass locally
- [ ] Security: no secrets in repo or client bundles; `.env.example` updated if new vars added
- [ ] SOLID review on new abstractions (single responsibility, clear boundaries, dependency direction)
- [ ] Version control: after each completed todo (or separable slice), `git commit` (scoped message) + `git push` to tracked remote — not end-of-session batch

### TDD Workflow

1. **Red:** Write a failing test that describes the desired behavior
2. **Green:** Implement the minimum code to make it pass
3. **Refactor:** Clean up while keeping tests green
4. Add two edge-case tests minimum per happy path
5. Add or extend integration tests for the full business flow
6. Run full test suite for every touched module

### Pre-Merge Checklist (Copy-Paste Before Every Commit)

\`\`\`
[ ] TDD: tests existed before production code for this change
[ ] Line + branch coverage complete (with documented exclusions only)
[ ] `npm run test:coverage` — clean pass (thresholds on lib/services/store)
[ ] >= 2 edge-case tests per happy path (per export in lib/services unless documented)
[ ] Integration tests cover all new business cases
[ ] npm test — clean pass
[ ] No secrets in repo or client bundles; .env.example updated if new vars
[ ] Each completed todo/slice was committed and pushed (unless user waived push)
[ ] tasks/todo.md updated — item marked complete
[ ] tasks/lessons.md updated if a correction was made
\`\`\`

### Deep References

| Document | Purpose |
|----------|---------|
| `{SLUG}.md` | Product overview — read at every session start |
| `{SLUG}_STITCH_DESIGN.md` | Visual brief — before any UI component |
| `{SLUG}_BUILD.md` | Technical blueprint — includes Week 0 + pre-launch §11 |
| `AGENTS.md` | Agent standards — this document |
| `tasks/todo.md` | Active task checklist |
| `tasks/lessons.md` | Self-improvement log |
| `docs/ENGINEERING_STANDARDS.md` | TDD / coverage gates |
| `docs/SECURITY.md` | Security checklist |
| `docs/API.md` | External API / third-party references |

### When No Application Code Exists Yet

Documentation-only changes must still:
- Update cross-links if file names or flows change
- Not claim "tests passed" for code that does not exist — state explicitly that gates apply from first scaffold commit onward

### Mobile-Specific Standards

- **Expo SDK version:** Lock to current stable. Do not upgrade mid-build without a plan
- **No native code in MVP:** Stick to Expo managed workflow. No ejecting
- **Offline first:** {state core loop offline requirement}
- **RevenueCat:** Only integrate after core loop is stable (see BUILD timeline). Week 0 may define project/entitlement names only
- **Week 0:** Do not scaffold application code until `{SLUG}_BUILD.md` §7 Required rows are checked in `tasks/todo.md`
- **Pre-launch §11:** Before "App Store Submission" can be marked complete, every item in `{SLUG}_BUILD.md` §11 must have explicit Implement in v1 or Defer to v2
- {App-specific bans: privacy, AR, content paths, etc.}
```

---

## tasks/todo.md

```markdown
# {Display Name} — Tasks

## Week 0 — Provisioning (blocks coding)

- [ ] GitHub repo created + remote added + docs pushed
- [ ] {Supabase project … / skip if Not used}
- [ ] {RevenueCat project + entitlement naming …}
- [ ] {Other Required Week 0}
- [ ] `docs/API.md` updated with project IDs/URLs (no secrets)

## Week 1 — Foundation

- [ ] (fill from BUILD timeline after Week 0)

## Pre-launch (Week 4)

- [ ] `{SLUG}_BUILD.md` §11 — every item Implement in v1 or Defer to v2
- [ ] App Store Submission (blocked on §11)
```

---

## tasks/lessons.md

```markdown
# Lessons

> After any user correction, add a dated entry: what went wrong, the rule to prevent repeat.

| Date | Lesson | Rule |
|------|--------|------|
| | | |
```

---

## docs/ENGINEERING_STANDARDS.md

```markdown
# Engineering Standards

Source of truth for agent workflow and coverage: [`AGENTS.md`](../AGENTS.md).

- TDD: Red → Green → Refactor
- Coverage gates on `lib/`, `services/`, `store/` once scaffold exists
- Per-todo commit + push

Until the first Expo scaffold commit, do not claim test suite results.
```

---

## docs/SECURITY.md

```markdown
# Security Checklist

- [ ] No secrets in git or client bundles
- [ ] `.env.example` lists every env var (placeholders only)
- [ ] {Privacy: location bucketing / PII / Health — as applicable}
- [ ] Dependency review before adding native modules
- [ ] RevenueCat / Supabase keys only via env
```

---

## docs/API.md

```markdown
# External APIs & Third Parties

| Service | Purpose | Week | Notes / project IDs |
|---------|---------|------|---------------------|
| GitHub | Source | 0 | `{repo url}` |
| Supabase | … | … | … |
| RevenueCat | Subscriptions | 0 names / 4 SDK | entitlement: `{name}` |
| … | | | |

Never commit secret keys. Public URLs and anon keys only when appropriate; prefer env.
```
