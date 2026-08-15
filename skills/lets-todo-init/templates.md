# Let's Todo Init — Templates

## tasks/todo.md (index only)

```markdown
# {Display Name} — Tasks

Scrum backlog. Story bodies live under [`epics/`](epics/). Do not implement more than one story per commit.

**How to pick work:** next unblocked `[ ]` in the current sprint. Feature epics: finish `S01` research before any code story.

## Week 0 — Provisioning (blocks coding)

- [ ] {copy BUILD §7 Required rows}

## Epic index

| ID | Epic | File | Stories | Status |
|----|------|------|---------|--------|
| E00 | Week 0 | [epics/E00-week-0.md](epics/E00-week-0.md) | n | |
| E01 | … | [epics/E01-….md](epics/E01-….md) | n | blocked on E00 |

## Current sprint

- [ ] {E00-S01 or first unblocked}
- [ ] {E00-S02}

## Pre-launch

- [ ] `{SLUG}_BUILD.md` §11 — every item Implement in v1 or Defer to v2
- [ ] App Store Submission (blocked on §11 + Let's Release)
```

## Story skeleton (copy for every story)

```markdown
### {EPIC}-S{NN} — {short title}

**Status:** [ ] todo | [ ] in progress | [ ] done

**Description**
{What to build or research, in 2–5 sentences. Cite GDD/BUILD section.}

**Neyi amaçlıyor**
{Player or studio outcome. One paragraph.}

**Blokladığı / bloklandığı işler**
- Blocked by: {IDs or "none (after E00)"}
- Blocks: {IDs or "none"}

**Acceptance criteria: %100 unit + integration coverage; her business function için en az 5 edge-case senaryo testi**
- [ ] Unit tests cover every new/changed export in `lib/`, `services/`, `store/` (or documented equivalent). `npm run test:coverage` — 100% statements/branches/functions/lines on those trees (documented exclusions only).
- [ ] Integration tests cover this story’s business flow end-to-end.
- [ ] Each business function has **at least 5** distinct edge-case scenario tests (empty, overflow, offline, invalid input, regression of prior win/lose).
- [ ] Research-only stories: deliver a dated note in `docs/research/` (or the epic file) with sources; no production code; coverage gates N/A until code stories.
- [ ] {story-specific AC 1}
- [ ] {story-specific AC 2}

**SOLID**
- [ ] Single responsibility: this story’s module does one job.
- [ ] Open/closed: civ/content data can extend without editing the core loop.
- [ ] Dependencies point inward (engine ← content, not the reverse).

**Doğru loglama**
- [ ] Structured logs for success, failure, and security-relevant events; no PII/secrets in log lines; player ids hashed or omitted.

**Mimariye bağlı, dinamik ve extend edilebilir kod**
- [ ] Follows `{SLUG}_BUILD.md` structure (`lib/` engine, content as data). New civ/map is data + skin, not a fork of the loop.

**OWASP Top 10 kontrolleri (bu hata sınıfları kodda yaşamaz)**
- [ ] Broken access control, crypto failures, injection, insecure design, misconfiguration, vulnerable components, auth failures, integrity failures, logging failures, SSRF — reviewed for this change. Client never trusts itself for IAP, scores, or entitlements.

**GDPR senaryoları**
- [ ] Lawful basis named (or N/A: no personal data). Minimize. No analytics PII. Export/delete path considered if accounts exist. Push/ATT consent not collected inside combat.

**Hedef kitle psikolojisi + UI/UX araştırma/planlama**
- [ ] Decision cites persona (GDD A–E) and one UX risk (cognitive load, fairness, shame, FOMO). Quiet luxury; one primary CTA.

**Feature’dan önce Reddit + 4chan ilgili kategoriler + rakip derin araştırma**
- [ ] S01 research story completed for this epic, **or** this **is** that research story (sources listed below).
- Sources: {GDD §…; r/towerdefense, r/kingdomrush, r/btd6; 4chan /vg/ TD threads if available; KR, BTD6, PvZ2, Defense Grid as relevant}

**Sıkı güvenlik**
- [ ] No secrets in git or client bundles. `.env.example` updated. New native modules justified.

**Yeni kod eski işleyişi bozmaz (regresyon)**
- [ ] Existing campaign/FTUE/combat tests still pass. No silent change to gold, lives, wave order, or IAP rules when this story is off/default.
```

## Research story extras

S01 of a feature epic must list:

- Subreddits / threads read (title + date)
- Competitor behaviors copied vs rejected
- 4chan/anon sentiment if the GDD already captured it (`GDD-4CHAN` missing → use GDD Reddit/store synthesis and **say so**)
- UI/UX recommendation for the implement stories
