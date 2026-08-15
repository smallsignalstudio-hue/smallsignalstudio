---
name: lets-todo-init
description: >-
  Let's Todo Init — after Let's Blueprint (or an existing {SLUG}.md + BUILD + GDD),
  reads every product/tech doc and emits a Scrum epic/story backlog: tasks/todo.md
  index plus tasks/epics/E*.md with fully specified stories. Use when the user says
  Let's Todo Init, lets-todo-init, todo backlog çıkar, epic'lere böl, Scrum todo,
  or after Blueprint asks to commit the skeleton and split work. Never writes app code.
  Never one mega-commit for v1.
disable-model-invocation: true
---

# Let's Todo Init

Turns locked product docs into a **Scrum backlog of small stories**. Sibling: [`lets-blueprint`](../lets-blueprint/SKILL.md) writes the md pack. Sibling: [`lets-release`](../lets-release/SKILL.md) ships later.

This skill does **not** implement features. It only writes `tasks/` markdown.

## When to use (auto-trigger phrases)

- `Let's Todo Init` / `lets-todo-init` / `Let's todo init`
- `todo backlog çıkar` / `epic'lere böl` / `Scrum todo`
- After Blueprint: “iskeleti commit et, işleri parçala”

## Hard rules

1. **No app code.** No `create-expo-app`, no feature implementation, no “while we’re here” refactors.
2. **Docs already exist.** Require `{SLUG}.md` + `{SLUG}_BUILD.md`. Also read `{SLUG}_STITCH_DESIGN.md`, `AGENTS.md`, GDD under `docs/`, existing `tasks/`. If Blueprint is missing, stop and tell the user to run Let's Blueprint first.
3. **Never invent scope.** Epics and stories must trace to BUILD scope lock + GDD (or explicit user chat). Do not add civs, IAP, or social that BUILD deferred to v2.
4. **One story = one commit.** If a story needs two commits, split it.
5. **Research before implementation.** Every **feature** epic’s **first** story is research (GDD + Reddit + 4chan related categories + competitor deep-dive + target-audience psychology / UI/UX). Provisioning epics (Week 0) skip this.
6. **Do not smash v1 into one commit.** The backlog exists so agents never ship the whole launch in one change.
7. **Ask before push.** Commit message: `docs: initial Scrum backlog from Let's Todo Init` (or `docs: refresh Scrum backlog from Let's Todo Init` on reruns).
8. **Overwrite policy.** If `tasks/epics/` already exists, ask before replacing. Prefer additive IDs (`E15-…`) over silent delete.

## Story fields (mandatory — use this wording)

Every story in `tasks/epics/*.md` MUST include these fields, filled in (not left as headings only):

- Description
- Neyi amaçlıyor
- Blokladığı / bloklandığı işler
- Acceptance criteria: %100 unit + integration coverage; her business function için en az 5 edge-case senaryo testi
- SOLID
- Doğru loglama
- Mimariye bağlı, dinamik ve extend edilebilir kod
- OWASP Top 10 kontrolleri (bu hata sınıfları kodda yaşamaz)
- GDPR senaryoları
- Hedef kitle psikolojisi + UI/UX araştırma/planlama
- Feature’dan önce Reddit + 4chan ilgili kategoriler + rakip derin araştırma
- Sıkı güvenlik
- Yeni kod eski işleyişi bozmaz (regresyon)

Copy the skeleton in [templates.md](templates.md). Do not drop or paraphrase these field names.

## Execution spine

```
- [ ] Phase 0: Workspace + doc inventory
- [ ] Phase 1: Scope extract (v1 vs deferred)
- [ ] Phase 2: Epic map (user OK if first run)
- [ ] Phase 3: Write tasks/todo.md index
- [ ] Phase 4: Write tasks/epics/E*.md stories
- [ ] Phase 5: Cross-link blockers
- [ ] Phase 6: Docs-only git commit; ask before push
```

### Phase 0 — Workspace

1. Confirm cwd is the app repo (`~/Repo/<project>/`).
2. Inventory: `{SLUG}.md`, `{SLUG}_BUILD.md`, Stitch, `AGENTS.md`, GDD, `tasks/todo.md`.
3. Note existing Expo/`mobile/` so stories say “extend, do not delete shell.”

### Phase 1 — Scope extract

From BUILD §1 / GDD launch slice, list:

- In v1
- Deferred (must not appear as implementation stories)
- Week 0 third parties (human; still listed as E00 stories)

### Phase 2 — Epic map

Propose 8–16 epics. Typical TD/mobile launch shape is in [reference.md](reference.md). Show the user the **epic titles only** if this is a first run and BUILD did not already lock them; if the user already locked epics in chat/plan, do not re-ask — write files.

### Phase 3 — Index

Write [`tasks/todo.md`](templates.md) as:

- Week 0 checkboxes (from BUILD §7)
- Epic table with file links + story counts
- Current sprint = next unblocked stories (usually E00 then E01 research)

Do **not** paste full story bodies into `todo.md`.

### Phase 4 — Epic files

One file per epic: `tasks/epics/E00-week-0.md`, `tasks/epics/E01-combat-core.md`, …

Inside each file:

1. Epic goal (5–10 lines, GDD citations)
2. Stories `E01-S01`, `E01-S02`, … each with **all** mandatory fields
3. First story = research (except E00)

Keep stories small: one engine verb, one screen, one data table, or one third-party provisioning step.

### Phase 5 — Blockers

Every story’s blocker field must name real IDs (`E01-S03` blocks `E01-S04`). Cycle-free. E00 Required rows block all implementation epics.

### Phase 6 — Git

Stage `tasks/todo.md` + `tasks/epics/**` only (plus `AGENTS.md` / README links if you added them). Ask before push.

## Final operator reply

1. Epic count + story count
2. Paths
3. Next unblocked story IDs
4. Reminder: implement **one** story per session; research story before that epic’s code

## Additional resources

- Story skeleton + index skeleton: [templates.md](templates.md)
- Epic catalog + OWASP/GDPR/research notes: [reference.md](reference.md)
