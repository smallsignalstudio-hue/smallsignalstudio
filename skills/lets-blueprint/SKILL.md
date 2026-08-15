---
name: lets-blueprint
description: >-
  Let's Blueprint — turns an app idea (or pasted agent project dump) into a complete
  Small Signal Studio documentation pack: APP.md, APP_STITCH_DESIGN.md, APP_BUILD.md,
  AGENTS.md, plus tasks/docs stubs and Week 0 third-party provisioning gates.
  Use when the user says Let's Blueprint, lets-blueprint, bu fikir için blueprint,
  Stitch + Build docs, proje md paketini hazırla, or pastes a full idea and asks for
  project markdown / Stitch / AGENTS blueprints. Never scaffolds app code.
disable-model-invocation: true
---

# Let's Blueprint

End-to-end **documentation pack** for a new Small Signal Studio mobile app — before any application code.

Sibling: [`lets-todo-init`](../lets-todo-init/SKILL.md) expands Scrum epics after this pack. Sibling: [`lets-release`](../lets-release/SKILL.md) ships later. Sibling: [`genius-ideas`](../genius-ideas/SKILL.md) finds ideas; this skill **specifies** them.

## When to use (auto-trigger phrases)

- `Let's Blueprint` / `lets-blueprint` / `Let's blueprint`
- `bu fikir için blueprint` / `proje md paketini hazırla`
- `Stitch + Build docs` / `Stitch prompt hazırla`
- User pastes another agent's project dump and asks for ENOCH/Sippin-style md pack

## Hard rules

1. **No app code.** No `create-expo-app`, no `app/`, no native modules. Docs + git + provisioning prompts only.
2. **User owns the folder.** Expect an already-created `~/Repo/<project>/` workspace. Do **not** create the parent directory. If cwd is wrong, use `move_agent_to_root` / ask the user to open the project folder.
3. **Never assume.** If a product/tech fact is missing or ambiguous, **stop and ask**. Do not invent name, monetization, backend need, screens, pricing, or content strategy. Only fill a gap if the user explicitly says "decide for me" for that field.
4. **Ask in small batches.** 1–2 critical questions at a time until the source of truth is complete.
5. **Week 0 gates coding.** Before saying "ready to code", walk the user through every required third-party setup and wait for confirmation. Do not claim GitHub/Supabase/RevenueCat/etc. exist without user confirmation.
6. **Pre-launch decisions are binary.** Every brainstormed premium-hook idea must be listed with only: **Implement in v1** or **Defer to v2**. No maybe / skip / silent drop.

## Studio defaults (confirm or override — do not silently lock)

| Field | Default if user does not contradict |
|-------|-------------------------------------|
| Studio | Small Signal Studio |
| Platform | iOS + Android (React Native + Expo) |
| Build approach | Cursor / AI-agent driven, solo |
| MVP horizon | ~4 weeks |
| Design bar | Apple Head-of-Design: radical simplicity, premium, luxury |
| Local-first | Prefer; backend only when product requires it |

Defaults still require a quick confirm when the paste is silent on them — one batched "OK to use studio defaults?" is enough.

## Execution spine (every run, in order)

Copy this checklist and track progress:

```
- [ ] Phase 0: Workspace
- [ ] Phase 1: Intake
- [ ] Phase 2: Interview (until SoT complete)
- [ ] Phase 3: Third-party map
- [ ] Phase 4: Outline lock
- [ ] Phase 5: Write docs
- [ ] Phase 6: Git init + docs commit
- [ ] Phase 6b: Offer Let's Todo Init (do not emit epic backlog here)
- [ ] Phase 7: Week 0 provisioning walkthrough
```

Do not write files until Phases 0–4 are done (except reading existing files in the folder).

### Phase 0 — Workspace

1. Confirm cwd is `~/Repo/<project>/` (or the folder the user named).
2. If empty or docs-only, proceed. If app code already exists, ask before overwriting any `{SLUG}*.md` / `AGENTS.md`. **Never delete** an existing `mobile/` / Expo shell; do not re-run `create-expo-app`.
3. Note whether `.git` already exists.

### Phase 1 — Intake

Parse the user's paste (idea, genius-ideas report, or full agent dump). Extract **only** what is explicitly stated into a working fact list. Mark unknowns as `UNKNOWN`.

Read [reference.md](reference.md) for the interview bank and third-party catalog.

### Phase 2 — Interview

For every `UNKNOWN` that blocks a complete blueprint, ask. Minimum cleared topics are listed in [reference.md](reference.md) § Interview bank.

Must clear before Phase 5:

- Display name, `{SLUG}`, tagline, one-sentence job
- Core loop + exact MVP screens
- Anti-features
- Backend yes/no (and why)
- Free vs Premium split (+ price band or "suggest later")
- Content strategy (static vs runtime APIs)
- Privacy / permissions (location, Health, notifications, camera, etc.)
- ≥6 killer-feature candidates for BUILD §10
- GitHub org + repo name preference

### Phase 3 — Third-party map

List every external service this MVP needs. Mark each: **Required Week 0** | **Week N** | **Not used**.

Typical candidates: GitHub, Supabase, RevenueCat, Expo, App Store Connect / ASC API, Open-Meteo, Sentry, PostHog, Resend, Apple Health, etc. See [reference.md](reference.md) § Third-party catalog.

Never add a backend "just in case."

### Phase 4 — Outline lock

Briefly restate to the user (bullet outline):

- Screens (complete list)
- Core logic modules
- Monetization split
- Anti-features
- §10 decision candidates (≥6)
- Week 0 third parties

Get explicit OK (or corrections) before writing files.

### Phase 5 — Write docs

Use section skeletons in [templates.md](templates.md). Write **all** of the following into the project root (and subfolders):

| File | Purpose |
|------|---------|
| `{SLUG}.md` | Product overview |
| `{SLUG}_STITCH_DESIGN.md` | Self-contained Google Stitch paste brief |
| `{SLUG}_BUILD.md` | Technical SoT + Week 0 + §10 decisions + agent workflow |
| `AGENTS.md` | TDD / DoD / commit protocol / app-specific mobile standards |
| `README.md` | Short pointer to the docs |
| `tasks/todo.md` | Week 0 provisioning **only** + pointer to Let's Todo Init |
| `tasks/lessons.md` | Empty self-improvement starter |
| `docs/ENGINEERING_STANDARDS.md` | Stub → AGENTS + coverage gates |
| `docs/SECURITY.md` | Stub checklist |
| `docs/API.md` | Third-party references from Phase 3 |

`{SLUG}` = uppercase token confirmed with user (`ENOCH`, `SIPPIN`, …).

#### Stitch mandate (must appear in `{SLUG}_STITCH_DESIGN.md`)

Design exactly as if Apple's Head of Design shipped this app:

- Radical simplicity; one primary focal point per screen
- Quiet luxury — premium, not cluttered or gimmicky
- Whitespace is a feature
- Every screen/feature listed so Stitch skips nothing
- Explicit anti-pattern list for the category

#### BUILD §10 — Pre-Launch Decision List (mandatory)

Brainstormed premium hooks (≥6). Each line:

`[ ] Implement in v1` / `[ ] Defer to v2`

Only those two answers are acceptable. Submission is blocked until every item is resolved (encode this in `AGENTS.md` + `tasks/todo.md`).

### Phase 6 — Git

1. If no `.git`: `git init`, default branch `main`.
2. Stage docs only; commit with a scoped message, e.g. `docs: initial Let's Blueprint pack for {Name}`.
3. **Ask before push.** Remote may not exist yet — that is a Week 0 GitHub step.
4. Never force-push; never invent remotes/secrets.

### Phase 6b — Let's Todo Init handoff

Do **not** generate epic/story backlogs in this skill. After the docs commit, tell the user to run **Let's Todo Init** (`lets-todo-init`) so `tasks/epics/` is filled from `{SLUG}.md` + BUILD + GDD. If they already asked for both in one session, run Todo Init **after** Phase 5 files exist, as a **separate git commit**.

### Phase 7 — Week 0 provisioning

For each **Required Week 0** service, present numbered human steps and **wait for confirmation** before the next service. Do not start Expo/app scaffolding in this skill.

Typical order:

1. GitHub repo create + `git remote add` + first push of docs
2. Supabase (only if required) — project + schema notes recorded in `docs/API.md`
3. RevenueCat project/apps/entitlement **naming** (SDK code stays Week 4)
4. Any other Required Week 0 items

Update `tasks/todo.md` checkboxes as the user confirms.

**Ready-to-code handoff line** (end of BUILD + final reply):

> Next session: read `{SLUG}.md` + `{SLUG}_BUILD.md` + `tasks/todo.md`. If `tasks/epics/` is empty, run Let's Todo Init. Do not write app code until Week 0 boxes are checked.

## Final operator reply

Keep it short:

1. Paths of files written
2. `{SLUG}` + GitHub repo name (if known)
3. Week 0 remaining checklist
4. Reminder: no app code until Week 0 is green; backlog via Let's Todo Init, not this skill

## Additional resources

- Interview bank + third-party catalog: [reference.md](reference.md)
- Doc section skeletons: [templates.md](templates.md)
