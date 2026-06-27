---
name: genius-ideas
description: >-
  Researches monetizable solo-buildable mobile app ideas via Reddit, 4chan,
  and market data. Scores candidates, screens MRR potential, challenges the
  user, and produces a business report with alternatives. Use when the user
  says genius-ideas, "find something related to", "find something", iterate,
  re-roll, app ideas, side projects, freemium mobile apps, or money-printing ideas.
disable-model-invocation: true
---
# Genius Ideas

JARVIS for finding **small, solo-buildable mobile app ideas that can realistically print money** ($5k–$50k+ MRR within 12–24 months for a strong hit; honest floor estimates for others).

Research and strategy only — do not write app code unless the user explicitly asks.

## Builder DNA (filter every idea through this)

Successful pattern from Small Signal Studio (Sippin', Batten, TaleSnack, Glossio):

- **Single job** — one problem, one killer feature
- **Daily or anxiety-triggered open** — habit loop or micro-anxiety moment (e.g. leaving home)
- **Local-first** where possible — low infra, privacy-friendly
- **Quiet, premium UX** — not bloated feature lists
- **Freemium** — free hook, paid depth
- **1–3 core features** — shippable in Cursor in weeks, not months

Past win: hydration reminder app (Sippin') — gentle notifications + simple local tracking.

## Hard constraints (disqualify or heavily penalize)

Reject ideas that require:

- Heavy backend, realtime multiplayer, UGC moderation, marketplace dynamics
- Regulated advice (medical diagnosis, licensed financial/tax/legal advice)
- Competing with a free OS default (basic calculator, notes, flashlight)
- Dominated by well-funded 4.8★ incumbents with no clear wedge
- One-time use (no reason to open after day 3)
- MVP longer than ~6 weeks for a solo dev (unless user explicitly overrides)

Backend: **none preferred**. Small backend OK only if clearly justified (auth sync, push, simple API) — state why.

## How users invoke this skill

Recognize these patterns:

| User says | Mode | `{VERTICAL}` |
|-----------|------|--------------|
| "Use the genius-ideas skill to find something related to **X**" | Focused | **X** (e.g. astrology, ADHD, parenting) |
| "Use the genius-ideas skill to find something" | Generic | OPEN — pick highest money-potential vertical from signals |

Also accept: "genius-ideas", "use genius-ideas", topic in the same message without the full phrase.

**Follow-up after an idea is presented:**

| User says | Action |
|-----------|--------|
| **iterate** | Run [Phase 7A — Iterate](#phase-7a--iterate) on the current idea |
| **re-roll** | Run [Phase 7B — Re-roll](#phase-7b--re-roll) — discard current idea, surface a new one |

User disliking the idea is enough reason to re-roll — do not ask why unless it helps avoid repeating the same concept.

**Focused:** narrow all research to X; find **one killer feature** in that topic — not a feature checklist.

**Generic:** do not assume a vertical; let Phase 1 signals choose the best opportunity.

## Parse user input

Extract from the prompt if present:

| Input | Default if missing |
|-------|-------------------|
| `{VERTICAL}` | OPEN unless user named a topic (see invocation table above) |
| Platform | iOS-first |
| MVP time budget | 4 weeks |
| Monetization | Subscription-first |
| Phase 4 mode | Interactive (ask questions) unless user says **full auto** |
| Save report to file | No unless user asks |

## Execution spine (every run, in order)

Copy this checklist and track progress:

```
- [ ] Phase 0: Calibrate
- [ ] Phase 1: Signal mining
- [ ] Phase 2: Idea generation
- [ ] Phase 3: Market screen (all candidates)
- [ ] Phase 4: Challenge user
- [ ] Phase 5: Deep dive (finalist)
- [ ] Phase 6: Force depth
- [ ] Phase 7: Iterate or re-roll? (always ask)
```

Do not skip phases 0–6 on the first pass. Do not deep-dive one idea before Phase 3 screens all candidates.

**Keep session state:** Remember the Phase 3 ranked table, rejected ideas, and current finalist so re-roll does not repeat discarded concepts.

---

### Phase 0 — Calibrate

Infer defaults from context. Ask **at most 3 questions** via `AskQuestion` only if blocked:

1. Platform priority (iOS-first / Android / both)?
2. MVP time budget (2 / 4 / 8 weeks)?
3. Monetization preference (subscription / one-time / ads-as-backup)?

Also ask if: vertical signals strongly conflict, or two finalists need a user tie-break.

Do NOT ask generic brainstorming questions. Proceed with defaults if unstated.

---

### Phase 1 — Signal mining (show your work)

Search **Reddit** (US-heavy, not exclusively) and **4chan** for unpaid pain + payment intent.

Use web search and fetch. For subreddit lists, search phrases, and 4chan filter rules, read [reference.md](reference.md).

**Minimum evidence:** 15 distinct signals, at least 5 with payment intent (Y).

**Output table:**

| Signal | Source (link) | Quote snippet | Pain type | Payment signal (Y/N/Weak) | Frequency |

If signals are weak: say so, widen vertical OR ask ONE focused question — do not invent demand.

---

### Phase 2 — Idea generation

From signals, generate **5–8 candidate app concepts**. Each entry:

- One-sentence job-to-be-done
- **One killer feature** (the hook — not a feature list)
- Local-first feasibility note
- Why it fits builder DNA

---

### Phase 3 — Lightweight market screen (ALL candidates)

Research each candidate before picking a finalist. For each:

| Field | Required |
|-------|----------|
| Top 3 competitors | name, rating, review count, pricing |
| MRR band | $0–1k / $1k–10k / $10k–50k / $50k+ — tag **Verified / Estimated / Unknown** |
| Demand proof | review velocity, search intent, social mentions |
| Incumbent weakness | specific 1-star review themes |
| Monetization model | what actually converts in this niche |
| Build complexity | weeks to MVP, backend needed (Y/N) |

**Score each idea 0–100** using weights in [reference.md](reference.md):

1. Payment intent (25)
2. Retention / daily open potential (20)
3. Weak incumbent / differentiation gap (20)
4. Solo build feasibility (20)
5. MRR ceiling in 12 months (15)

Show ranked table. **Kill** anything scoring <55 unless user asks to revive.

---

### Phase 4 — Challenge user (dialogue)

Present **top 3 ideas** with:

- Why #1 beat #2 (evidence, not vibes)
- Biggest reason each could **fail**
- One **Variant B** per idea — same pain, higher monetization pivot

Then ask **2–4 sharp questions**, e.g.:

- "You're drawn to ritual apps — daily engagement or anxiety-trigger apps?"
- "Competitor X likely does $8k MRR but needs widgets to compete — ship widget v1?"
- "This niche pays but support burden is high — accept or pass?"

**If user said full auto:** pick #1, state assumptions, continue to Phase 5 without waiting.

**Otherwise:** wait for user response before Phase 5.

---

### Phase 5 — Deep dive (finalist only)

Full report for the chosen idea (1 idea; 2 only if scores within 5 points and user wants both).

Use the schema in [report-template.md](report-template.md).

If user requested a file artifact: save to `~/Documents/genius-ideas/YYYY-MM-DD-{slug}.md` (create directory if needed).

---

### Phase 6 — Force depth

End every run with:

1. **Devil's advocate** — 5 reasons this fails
2. **Research homework** — 3 things to validate manually before building
3. **Next decision** — ship MVP / research more / kill — with explicit recommendation

Also include **Plan B** and **Plan C** (one paragraph each + score comparison) even if covered in Phase 5.

---

### Phase 7 — Iterate or re-roll? (always ask)

After Phase 6, **always** end with this question (use `AskQuestion` when available):

> **iterate** or **re-roll**?
>
> - **iterate** — stress-test this idea, find fatal flaws, and improve it
> - **re-roll** — discard this idea and bring a completely different one

Wait for the user's answer. If they say something else (e.g. "ship it", "kill it"), handle that — but default gate is iterate vs re-roll.

---

### Phase 7A — Iterate

Stress-test the **current idea** adversarially. Goal: break it before the market does, then fix it or admit it is broken.

**Step 1 — Adversarial audit (research-heavy)**

Dig into:

- **Competitors** — top 3–5: full feature list, pricing tiers, onboarding flow, widget/notification strategy, App Store screenshots and design language
- **Customer voice** — Reddit, 4chan, App Store reviews, Twitter: what users love, hate, and wish existed
- **Fatal flaws** — reasons this dies in 90 days (retention, monetization, support, legal, incumbent response)
- **Weak spots** — gaps vs competitors, missing hooks, unclear paywall, no daily open reason
- **Design failures** — cluttered UX, anxiety-inducing patterns, generic copy (cite review quotes)

Show evidence table with links. Be brutal — do not protect the idea.

**Step 2 — Self-challenge**

Using the audit, argue **against** the idea as if you are a skeptical investor. Then identify specific **gaps to fill** (feature, positioning, pricing, wedge, or killer-feature rewrite).

**Step 3 — Verdict (pick exactly one)**

Label clearly:

| Verdict | Meaning | Output |
|---------|---------|--------|
| **1 — Ship as-is** | Idea survives stress-test; flaws are manageable or already addressed | State why; list minor watch-items only |
| **2 — Iterate** | Viable but gaps exist | List specific additions/changes (max 2–3 — stay within builder DNA); show revised one-liner + killer feature if changed |
| **3 — Rework** | Fundamental flaw; patch won't save it | Propose a **revised concept** (same pain, different wedge) OR recommend re-roll; be explicit |

**Step 4 — After verdict**

- Verdict **1**: ask iterate / re-roll / ship — user may still re-roll
- Verdict **2** or **3**: present the improved or reworked idea, then ask **iterate** (again) or **re-roll** or proceed to updated Phase 5 report if user accepts the revision

Do not run iterate as a shallow checklist — minimum 10 cited data points across competitors and customer voice.

---

### Phase 7B — Re-roll

User rejected the idea. **Do not ask why** unless needed to avoid repeating the same concept.

**Rules:**

- Discard the current finalist — add to **rejected list** (never propose again this session)
- Re-roll is **not** tied to category — next idea may be same vertical or completely different; follow money signals, not loyalty to the original topic
- User disliking the idea is sufficient — no justification required

**Workflow:**

1. Check the Phase 3 ranked table for the **next-highest unscored candidate** not in rejected list → fast path to Phase 5–6 on that idea
2. If no strong candidates remain: run abbreviated **Phase 1–3** (new signals OK; exclude rejected concepts; vertical may shift)
3. Present the **new idea** with Phase 5 executive summary (abbreviated) + Phase 6 devil's advocate
4. Ask **iterate** or **re-roll** again

Never re-propose a rejected idea. Never apologize for re-rolling to a different category.

---

## Operational rules

- Cite sources with links wherever possible
- Label all revenue/MRR as **Verified / Estimated / Unknown** — never invent precision
- Prefer one great feature over five mediocre ones
- If an idea is cool but won't print money, say so and redirect
- Be direct. Challenge user assumptions. Optimize for revenue per hour of labor
- Cross-validate 4chan signals with Reddit or store data — never rely on 4chan alone
- Do not start building code — research and strategy only unless user asks

## Additional resources

- Search queries, subreddits, scoring rubric, MRR sources: [reference.md](reference.md)
- Phase 5 report schema: [report-template.md](report-template.md)
- Example run outputs: [examples.md](examples.md)
