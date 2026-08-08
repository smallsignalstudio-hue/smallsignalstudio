# Let's Blueprint — Reference

Read during Phases 1–3 and when authoring Week 0 / §10. Do not load unless running the skill.

## Interview bank

Ask only what is still `UNKNOWN` after intake. Batch 1–2 questions. Never invent answers.

### Identity

- [ ] Display name (App Store facing)
- [ ] `{SLUG}` for filenames (`ENOCH`, `SIPPIN`, …) — uppercase, stable
- [ ] Repo folder name (should match `~/Repo/<folder>/`)
- [ ] Tagline (one short line)
- [ ] One-sentence job-to-be-done
- [ ] GitHub org + desired repo name (default org if known: `smallsignalstudio-hue` — **confirm**, do not assume)

### Product

- [ ] Problem statement (who hurts, why existing solutions fail)
- [ ] Research / signal sources (optional if paste already has them)
- [ ] Competitors + wedge
- [ ] Core daily/weekly loop (what the user does on open)
- [ ] Exact MVP screen list (every screen — Stitch needs all of them)
- [ ] Explicit anti-features (will NOT build)
- [ ] Onboarding steps (or "none")
- [ ] Notifications needed? (what, when)
- [ ] Widgets / share cards / Health / camera / location? (each yes/no)

### Technical shape

- [ ] Platforms (default: RN + Expo iOS/Android — confirm)
- [ ] Local-first vs sync/backend
- [ ] If backend: what exactly (auth, realtime, content CMS, …)
- [ ] Content strategy: static JSON shipped in app vs remote vs LLM runtime
- [ ] Offline requirements for core loop
- [ ] Analytics / crash reporting needed in MVP? (default often defer)

### Monetization

- [ ] Free tier contents (must be genuinely useful)
- [ ] Premium unlocks
- [ ] Price band (or "suggest in BUILD, decide Week 4")
- [ ] Trial? Lifetime? (default: keep simple — confirm)
- [ ] ≥6 premium-hook / killer-feature candidates for BUILD §10

### Design

- [ ] Emotional target (default: quiet luxury / Apple-premium)
- [ ] Light vs dark default
- [ ] Any brand colors already chosen? (if not, propose in Stitch doc **after** asking permission to propose)
- [ ] Category clichés to forbid (list explicitly)

### Process

- [ ] MVP week count (default ~4)
- [ ] RevenueCat only after core loop stable? (default yes)
- [ ] Studio defaults OK? (Small Signal Studio, Cursor/agent, solo)

## Anti-assume rules

| Situation | Correct behavior |
|-----------|------------------|
| Paste mentions "maybe Supabase" | Ask: required for MVP or defer? |
| No price given | Ask band or permission to suggest |
| "Beautiful UI" only | Still write full screen inventory; ask if screen count is locked |
| Genius-ideas report conflicts with user chat | Prefer explicit user chat; ask on conflict |
| Similar to Sippin/Enoch | Do not copy features; ask what transfers |
| Killer features < 6 | Brainstorm candidates **as questions** for user to accept/reject, then list in §10 |

## Naming

| Token | Example | Used in |
|-------|---------|---------|
| Display name | Enoch: Daily Gates | Store, README title |
| `{SLUG}` | `ENOCH` | `ENOCH.md`, `ENOCH_STITCH_DESIGN.md`, `ENOCH_BUILD.md` |
| folder | `enoch-daily-gates` | `~/Repo/enoch-daily-gates` |
| npm/package | `enoch-daily-gates` | Future scaffold (not this skill) |
| RC entitlement | e.g. `premium` | docs/API.md naming only in Week 0 |

Filenames: `{SLUG}.md`, `{SLUG}_STITCH_DESIGN.md`, `{SLUG}_BUILD.md` — always.

## Design quality bar (Stitch)

Encode in every Stitch brief:

1. **Single instruction:** Design as if Apple's Head of Design shipped this; Apple itself is the publisher.
2. Radical simplicity — one primary focal point; remove until true.
3. Quiet luxury — premium restraint, not category kitsch.
4. Whitespace is a feature.
5. Max one primary CTA per screen.
6. No urgency reds unless the product is genuinely safety-critical (default: never).
7. Motion: slow, deliberate, one element at a time (describe intent even for static frames).
8. List **every** screen in the brief with layout notes so Stitch cannot skip.

Propose palette/type only after asking, or after user says "decide for me" on design.

## Third-party catalog

Mark each app: **Required Week 0** | **Week N** | **Not used**.

| Service | When it appears | Week 0 action (human) |
|---------|-----------------|------------------------|
| **GitHub** | Always | Create empty repo; add remote; push docs |
| **Expo** | Always (RN apps) | Account ready; no project init in this skill |
| **Supabase** | Backend, auth, realtime, shared data | Create project; note URL + anon key in `.env.example` later; schema outline in `docs/API.md` |
| **RevenueCat** | Subscriptions / IAP | Create RC project + iOS/Android apps + entitlement/offering **names**; SDK code Week 4 |
| **App Store Connect** | Ship path | App record can wait; ASC API key when release tooling needed |
| **Open-Meteo / public APIs** | Feature needs weather/etc. | Document endpoint; usually no key |
| **Sentry** | Crash reporting in MVP | Ask; often Week 3+ |
| **PostHog / analytics** | Product analytics in MVP | Ask; often defer |
| **Resend / email** | Transactional email | Only if product sends email |
| **Apple Health / HealthKit** | Hydration/fitness sync | Capability + privacy copy; confirm need |
| **Push (Expo Notifications)** | Local or remote | Local: no server. Remote: needs backend |

### Week 0 walkthrough style

For each Required Week 0 service:

```
### N. {Service}
Why this app needs it: …
Steps for you:
1. …
2. …
Reply "done" (and paste any IDs/URLs safe to store in docs) when finished.
```

Never store secret API keys in git. Public anon keys / project URLs may go in `docs/API.md` only if user pastes them and `.env.example` is the pattern for env vars.

## Pre-launch §10 rules

- Minimum **6** items (more if product warrants).
- Items already in MVP scope still appear (confirm stay in v1 vs cut).
- Each item: only `[ ] Implement in v1` or `[ ] Defer to v2`.
- Deferred items → note in `tasks/todo.md` / future `v2-roadmap.md` with reason at decision time.
- `AGENTS.md` must block "App Store Submission" todo until all §10 rows are resolved.

## AGENTS.md adaptation checklist

Start from Sippin/Enoch AGENTS pattern, then:

- [ ] Deep References table points at this app's `{SLUG}*.md` files
- [ ] Coverage gates: `lib/`, `services/`, `store/` (or note N/A until scaffold)
- [ ] Mobile standards match Phase 3 (no AR unless explicit; offline-first statement; RC week lock)
- [ ] App-specific bans (privacy bucketing, content file paths, etc.)
- [ ] Section 10 / pre-launch decision gate referenced
- [ ] "When no application code exists yet" paragraph kept for docs-only phase

## Agent workflow block (paste into BUILD)

Mirror Antigravity / Sippin workflow:

1. Read `{SLUG}.md` + `{SLUG}_BUILD.md` every session start
2. Read `tasks/todo.md` + `tasks/lessons.md`
3. Plan mode for non-trivial work
4. Todo → implement → verify → commit+push per slice
5. Update lessons after corrections

## Git conventions

```
docs: initial Let's Blueprint pack for {Display Name}
```

Branch: `main` for initial docs. Later: `dev` + `feature/<name>` as in studio apps.

Commit protocol for future code sessions lives in `AGENTS.md` (per-todo commit + push).
