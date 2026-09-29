# GDD Research — Live Ops, FTUE & Meta Progression

> Working brief for **Historical Civ Tower Defence** (Babylon + 10 researched factions).  
> Soft USP: playable history with an optional **Educational Mode** (accuracy labels, museum/codex) alongside an **Arcade Mode** (fantasy juice, myth units, balance-first).  
> Status: design research — not final ship spec.

---

## 0. Design north stars

| Pillar | Intent |
|---|---|
| **Teach without preaching** | First 10 minutes sell *fun TD*; history rides along as flavor → unlocks. |
| **Civ identity** | Every season, event, and mastery track foregrounds one civilization’s keep, units, and aesthetic. |
| **Mode honesty** | Always show accuracy labels so players know when content is documented history vs stylized fantasy. |
| **Light social** | Co-op boss / async raid / clan war *lite* — no mandatory live schedule for casuals. |
| **Retention without dark patterns** | Streaks and battle pass exist; no FOMO that blocks core civ unlocks permanently. |

**Civ roster (player factions)**  
0 Babylon (Etemenanki) · 1 Rome · 2 Persia · 3 Egypt · 4 China · 5 Maya–Mexica · 6 Viking · 7 Greece · 8 Japan · 9 Mongol · 10 European Castle

---

## 1. First-time user experience (first 10 minutes)

### 1.1 Goals (measurable)

| Minute | Player should… |
|---|---|
| 0–1 | Understand “defend the sacred keep” + place one tower. |
| 1–3 | Clear Wave 1–2; learn path, sell/upgrade, one enemy type. |
| 3–6 | Unlock second tower type; beat tutorial mission; see first **Codex** card. |
| 6–8 | Choose **starter civ** (Rome or Babylon soft-recommend); see Educational ↔ Arcade toggle once. |
| 8–10 | Complete “graduation” skirmish; earn Account XP + first Museum slot; soft prompt for daily login / push opt-in. |

**Hard rules:** no IAP wall before minute 10; no ads in FTUE; skippable text after first combat beat; death = retry with one free tip, not paywall.

### 1.2 Script outline (beat-by-beat)

#### M0 — Cold open (0:00–0:45)
- **Visual:** Full-bleed keep under siege (Babylon ziggurat silhouette *or* Capitoline hill — art swaps to starter civ after choice).
- **VO / text (1 line):** “A city stands. Hold the heart.”
- **Action:** Tap to place **Watchtower / Archer** on highlighted pad. Auto-start Wave 1.
- **Skip:** “Skip intro” appears after 3s (returns to same placement prompt).

#### M1 — First blood (0:45–2:00)
- Wave 1: 8–12 light infantry, single lane.
- Teach: **gold per kill**, **HP of keep**, **wave timer**.
- Fail-safe: if keep <50% HP, ghost-place a free second tower.
- End card: *“Tower damage stops the path.”* — no lore dump.

#### M2 — Upgrade & choke (2:00–3:30)
- Wave 2: mix + one **shield / armored** unit.
- Teach: upgrade tower once; optional **sell** with 70% refund (show number).
- Micro-reward: **Account XP +15**, toast “Apprentice Defender”.

#### M3 — Second tower & hero tease (3:30–5:30)
- Unlock **Barricade / Spike** *or* civ-flavored trap (Rome *vallum*, Babylon canal gate — cosmetic only in FTUE).
- Place second type; clear Wave 3 mini-boss (named but *not* historical figure yet — e.g. “Raid Captain”).
- **Codex unlock #1:** “What is a keep?” — 40-word plain language + accuracy badge **Documented**.
- Soft USP beat: museum door icon animates; do **not** force museum UI yet.

#### M4 — Civ select (5:30–7:00)
- Screen: **Choose your civilization** — 3 featured (Babylon, Rome, Egypt) + “More later” carousel ghosts for locked civs.
- Each card: keep art, 1 signature tower, 1 signature unit, 1-sentence hook.
- Default highlight: Rome (broad familiarity) or Babylon (brand myth) A/B.
- On confirm: load civ skin; play 8s “arrival” motion (keep rises / banner unfurls).

#### M5 — Mode toggle reveal (7:00–7:45)
- Modal (once): **How do you want history?**
  - **Educational** — accuracy labels on; myth units off or clearly tagged; Codex quizzes optional.
  - **Arcade** — myth/hero units on; balance-first; labels still visible on hover/long-press.
- Default: **Arcade** for D0 conversion; Educational one tap away. Preference stored on account.
- Copy: “You can change this anytime in Settings.”

#### M6 — Graduation skirmish (7:45–9:30)
- Mission: **“First Watch”** — 5 waves, 2 lanes, one flying or siege tease (telegraphed).
- Mid-mission tip: hero ability button unlocks for one free cast (civ starter hero).
- Win → **Account Level 2**, **Civ Mastery I (starter)**, **Museum Slot: Keep Model** + Codex entry for that keep.
- Lose → retry with recommended build; no penalty to XP.

#### M7 — Meta porch (9:30–10:00)
- Hub reveal (one composition): Keep diorama, **Play**, **Museum**, **Season** (locked teaser), **Clan** (locked teaser).
- Soft prompts (pick one, not all):
  1. Daily quest: “Win 1 battle” (+ streak seed).
  2. Push permission (OS) — after reward screen, never during combat.
  3. Optional short quiz (Educational only): 1 question from Codex #1 — reward cosmetic pin, skippable.
- End state: player can freely play Mission 2 or return to menu. FTUE flagged complete → analytics `ftue_complete_10m`.

### 1.3 FTUE analytics hooks

`ftue_place_first_tower` · `ftue_wave1_clear` · `ftue_civ_select` · `ftue_mode_select` · `ftue_grad_win` / `ftue_grad_lose` · `ftue_museum_open` · `ftue_complete_10m` · time-to-each-beat.

### 1.4 Drop-off mitigations

| Risk | Fix |
|---|---|
| Lore walls | Max 1 sentence until Museum; Codex is pull, not push. |
| Complexity | Cap tower types at 2 until Level 3. |
| Mode confusion | Persistent small badge: EDU / ARC on HUD. |
| Civ regret | Free civ respec once before Account Level 5. |

---

## 2. Meta progression

### 2.1 Account XP & levels

- **Account Level** = global progression (cosmetics, museum capacity, social unlocks, soft power caps).
- XP sources: mission clear, daily/weekly quests, season pass track, co-op/async participation, **Codex study** (read + optional quiz).
- Curve: fast to L10 (habit), then decelerating; prestige **not** required Year-1.
- Soft gates (examples):
  - L3 — second civ trial unlock
  - L5 — Clan join
  - L8 — Async Raid
  - L12 — Co-op Boss queue
  - L15 — Clan War lite

**No pay-to-skip account levels** that gate civ *ownership*; battle pass may speed XP within a season cap.

### 2.2 Civilization mastery

Each civ has a **Mastery track** (I–X) independent of Account Level.

| Mastery | Unlock examples |
|---|---|
| I | Starter clear / FTUE |
| II–III | Alternate tower skins, voice pack |
| IV–V | Elite unit blueprint; historical loadout preset |
| VI–VII | Keep “restoration” visual tier; Museum wing for that civ |
| VIII–IX | Signature modifier for Ranked/Arcade; title |
| X | Mastery statue in Museum + season portrait frame |

XP into mastery: wins *with* that civ, challenges tagged to that civ, season featured bonuses (+25% when season matches civ).

**Educational soft USP:** Mastery IV+ can unlock **annotated diagrams** (e.g. castrum layers, wengcheng, ringfort) that Arcade players still receive as cosmetics but EDU players get quiz/credit toward Museum completion %.

### 2.3 Museum / Codex (educational soft USP)

**Museum** = 3D/2.5D hub of unlocked artifacts, keeps, and unit dioramas.  
**Codex** = card/database layer feeding the Museum.

#### Unlock loops
1. **Play** → drop Codex fragment (mission first-clear guaranteed; repeats RNG with pity).
2. **Read** → mark card “studied” (+Account XP).
3. **Quiz** (optional, EDU default on) → pin / XP / mastery crumb.
4. **Exhibit** → place artifact in Museum slot (capacity scales with Account Level).

#### Card taxonomy
- **Keep / Fortification**
- **Unit / Troop**
- **Tactic / Doctrine**
- **Myth / Legend** (always tagged; see §5)
- **Site / Campaign** (seasonal)

#### Soft USP messaging (store & hub)
- “Build a museum of civilizations you defend.”
- Not “history homework” — **collection + identity**. Partner potential: museum patronage cosmetics, classroom EDU playlist (no ads).

### 2.4 Currencies (meta-facing)

| Currency | Earn | Sink |
|---|---|---|
| **Gold** | Missions | In-run upgrades (run-only) |
| **Legacy Seals** | Account/meta | Permanent tower ranks, Museum slots |
| **Season Tokens** | Season track / events | Season cosmetics, featured civ boosts |
| **Clan Marks** | Social modes | Clan cosmetics, war banners |

Avoid a fourth premium soft-currency confusion in FTUE; introduce Season Tokens at first season gate only.

---

## 3. Live-ops calendar (seasons themed to civs)

### 3.1 Cadence

| Layer | Length | Content |
|---|---|---|
| **Season** | ~6–7 weeks (Year-1: 8 seasons ≈ 12 months with short breaks) | Featured civ, battle pass, exclusive Codex wing, map skin |
| **Event week** | 7–10 days mid-season | Modifier playlist, boss preview, 2× mastery |
| **Weekend raid** | Fri–Mon | Async Raid target refresh |
| **Daily** | 24h | 3 quests + login seal |

Break weeks (1 week) between seasons: catch-up XP, Museum spotlight, no new pass required.

### 3.2 Season structure (template)

1. **Launch trailer / hub takeover** — featured keep full-bleed.
2. **Track A (free):** seals, Codex cards, 1 tower skin.
3. **Track B (pass):** hero skin, keep restoration tier, portrait, exclusive myth-tagged unit *Arcade-only* unless EDU toggle allows “display only”.
4. **Season mission chain** (8–12 nodes) — narrative lightly based on documented campaigns; accuracy labels per node.
5. **Finale:** Co-op Boss themed to season civ (or rival civ as antagonist).

### 3.3 Year-1 season themes

| # | Window (indicative) | Theme title | Featured civ | Antagonist / pressure | Signature content |
|---|---|---|---|---|---|
| **S1** | Launch + 6w | **Etemenanki Rising** | Babylon | Tribal / Elamite-styled waves | Ziggurat keep tiers; canal traps; teach season UX |
| **S2** | +6w | **Capitoline Thunder** | Rome | Gallic / Germanic raid fantasy | Castrum layers; *testudo* enemy modifier; Jovian keep |
| **S3** | +6w | **Nilotic Watch** | Egypt | Sea Peoples–inspired pressure | Buhen aesthetic; chariot lane; flood-gate mechanic |
| **S4** | +6w | **Wall & Beacon** | China | Steppe cavalry pressure | Wengcheng choke tutorial; beacon-tower vision |
| **S5** | Mid-year | **Jade & Obsidian** | Maya–Mexica | Rival city-state raids | Dual-civ picker week; ballcourt FX; respectful ritual framing (see risks) |
| **S6** | +6w | **Longship Horizon** | Viking | Frankish / Anglo fort defense flip | Ringfort keep; coastal dual-lane; shield-wall enemies |
| **S7** | +6w | **Polis Under Siege** | Greece | Persian-themed fantasy pressure | Acropolis keep; phalanx block; trireme side-objective |
| **S8** | Year-end | **Eastern Gates** | Persia *or* **Japan** (pick by metrics) | Cross-civ “Silk & Steel” festival | If Persia: immortal guard myth-tag rules; if Japan: masugata pathing map. **Mongol** + **European Castle** held as Year-2 S1–S2 headliners with mid-season event cameos in S4/S6/S8. |

**Year-1 cameo plan (non-headline civs):**  
- Mongol: S4 event week “Ordu on the March” (attacker-flavor modifiers).  
- European Castle: S2 or S8 weekend “Concentric Walls” map mutator.  
- Whichever of Persia/Japan is not S8 gets a 10-day satellite event.

### 3.4 Live-ops content budget (per season)

- 1 keep skin + 2 tower skins + 1 hero skin  
- 8–12 Codex cards (mix Documented / Interpreted / Mythic)  
- 1 co-op boss + 1 async raid roster refresh  
- 1 clan war map skin  
- Balance patch mid-season (week 3–4)

---

## 4. Social systems (light)

### 4.1 Co-op boss

- **Players:** 2 (optionally 3 if performance allows).  
- **Structure:** Shared keep HP; split build budgets; ping wheel (need gold / cover lane / ult).  
- **Boss:** Season-themed; phases telegraph historical *or* mythic abilities with labels.  
- **Rewards:** personal XP + shared chest (cosmetics / seals); no griefable loot steal.  
- **Matchmaking:** power band by Account Level ± season pass tier soft; private invite always available.  
- **Casual promise:** checkpoint every phase; disconnect → AI assist 60s then pause vote.

### 4.2 Async raid

- **Fantasy:** Scout another player’s **Defense Layout** (opt-in Defense Export after a clear).  
- **Attacker:** runs a wave budget against ghost layout; no chat required.  
- **Defender:** earns **Clan Marks / Seals** when their layout is challenged (win or lose), capped daily to prevent farm.  
- **Fairness:** layouts snapshot at publish; banned infinite stall cheese; Educational Mode layouts can disable myth towers for “Historical Challenge” badge.  
- Unlock: Account L8.

### 4.3 Clan war lite

- **Size:** 5–15 members; wars are **asynchronous**.  
- **Format:** 48–72h window; each member plays 1–2 attacks on shared **War Map** nodes; stars by keep HP remaining / time.  
- **No** mandatory voice, **no** real-time simultaneous login.  
- **Rewards:** clan XP, banner cosmetics, small personal seals; never exclusive civ unlocks.  
- **Moderation:** report + kick; season sportsmanship score soft-gates war entry if toxic kicks spike.  
- Unlock: Account L15 + clan role.

### 4.4 Social UX principles

- Solo player can ignore all three forever without losing PvE campaign.  
- Hub shows social as **optional wing**, not center CTA, until L5+.  
- Cross-play parties: host’s Educational/Arcade mode applies for the session; clients show banner “Session: Arcade” etc.

---

## 5. Educational Mode vs Arcade Mode

### 5.1 Toggle

- **Location:** Settings + FTUE M5 + Museum header.  
- **Scope:** account default; overridable **per session** / per party host.  
- **HUD badge:** always visible (`EDU` / `ARC`).

### 5.2 Behavior matrix

| System | Educational | Arcade |
|---|---|---|
| Myth / legendary units | Off, or **display-only** in Museum; not in loadout | On, balance-tuned |
| Enemy names | Prefer documented troop types | Fantasy names OK |
| Mission blurbs | Short + source-tier label | Flavor-first |
| Quizzes | Opt-out available; default gentle prompts | Hidden unless Museum opened |
| Co-op boss telegraphs | Prefer Interpreted tactics | Full spectacle |
| Monetization | Same cosmetics; EDU packs lean Codex/Museum | Same; myth skins highlighted |

### 5.3 History accuracy labels (required in both modes)

Every Codex card, unit tooltip, and season node carries one of:

| Label | Meaning |
|---|---|
| **Documented** | Anchored in primary/secondary historical or archaeological consensus used in research bibles. |
| **Interpreted** | Reasonable reconstruction / composite for gameplay (e.g. “7-layer defense” pedagogy). |
| **Mythic** | Folklore, epic, or invented fantasy — fun, not fact. |
| **Anachronistic** | Intentional cross-era mash for event (must be rare and flagged). |

**UI:** color-blind safe icons + text; long-press on any unit → label + 1-line “Why?”.  
**Store ethics:** never market Mythic content as real history; EDU mode store filters can hide Mythic IAPs.

### 5.4 Classroom / partner hook (stretch)

- EDU playlist: FTUE → 5 civ missions → Museum tour; export completion certificate cosmetic.  
- Ads disabled in EDU playlist if partner/skool build flag set.

---

## 6. Risks & open questions

| Risk | Mitigation |
|---|---|
| Cultural sensitivity (esp. Maya–Mexica ritual framing, sacred sites) | Consult research docs; avoid sacred violence as gore juice; prefer fortification & unit craft. |
| Scope: 11 civs × seasons | Year-1 headlines 7–8; cameos for rest; reuse systems, swap art kits. |
| EDU feels punitive | Never reduce rewards vs Arcade for same clear; only content filters. |
| Async raid toxicity | Caps, snapshots, opt-in defense publish. |
| Clone of generic TD pass | Museum + labels + civ keeps as durable differentiator. |

**Open questions**  
- S8 headliner: Persia vs Japan (decide after S3 retention by civ).  
- Co-op 2 vs 3 players for mobile heat.  
- Whether Documented quizzes ever grant *power* (recommend cosmetics/XP only).

---

## 7. Build priorities (meta / live-ops slice)

1. FTUE M0–M7 + analytics  
2. Account XP + Civ Mastery I–III  
3. Codex cards + accuracy labels + mode toggle  
4. Museum hub v1 (slots + keep model)  
5. Season framework + S1 Babylon pass  
6. Async Raid → Co-op Boss → Clan War lite  

---

## 8. Summary for GDD merge

- **FTUE:** 10-minute script from place-tower → civ select → mode toggle → graduation → meta porch.  
- **Meta:** Account XP, per-civ mastery, Museum/Codex as educational soft USP.  
- **Live-ops:** ~6–7 week civ-themed seasons; Year-1 themes S1 Babylon → S2 Rome → S3 Egypt → S4 China → S5 Maya–Mexica → S6 Viking → S7 Greece → S8 Persia or Japan (Mongol & European Castle cameos / Year-2).  
- **Social:** co-op boss, async raid, clan war lite — all optional.  
- **Modes:** Educational vs Arcade with mandatory **Documented / Interpreted / Mythic / Anachronistic** labels.

---

*Sources for historical flavor: repo research bibles under `docs/` (Roman, Persian, Egyptian, Chinese, Maya–Aztec, Viking, Greek, Japanese, Mongol, European Castle). Design systems herein are original GDD proposals.*
