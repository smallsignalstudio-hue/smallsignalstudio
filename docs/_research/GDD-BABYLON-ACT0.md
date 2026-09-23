# GDD — Act 0 Babylon (Etemenanki) Campaign Hooks

> **Role:** Tutorial / FTUE act that teaches the shared **7-layer keep grammar** every later civ remixes.  
> **Keep:** **Etemenanki** (“House of the Foundation of Heaven and Earth”) + Esagila as sacred ground floor.  
> **Face:** Late Neo-Babylonian (Nebuchadnezzar II restorations) as default silhouette.  
> **Status:** Fills `GDD-CIV-HOOKS` **FLAG: needs-doc**. Numbers are design targets, not locked balance.  
> **Evidence rule:** Tag every hook **ARCH / LIT / FOLK / SPN / MOD**. Never let FOLK silently overwrite ARCH chronology.

**One-line pitch:** Climb Marduk’s staged mountain and learn place → upgrade → barracks → keep HP → early-call before empires rewrite the grammar.

---

## 0. Tag legend (use on every content card)

| Tag | Meaning | Act 0 use |
|---|---|---|
| **ARCH** | Archaeology / excavation / measured remains | Default map bones, wall names, brick language |
| **LIT** | Ancient literary source (Herodotus, Berossus fragments via later authors, Bible as literature) | Twin tellings, classical “7 stages,” river-drain twist — always labeled |
| **FOLK** | Later legend, tourist myth, Bible–Babel height fantasy, Hanging Gardens as proven wonder | Skins, optional Mythic playlist, Codex “myth vs dig” pages — **off by default in Historical** |
| **SPN** | Sponsor / propaganda text (building inscriptions, chronicle royal voice) | Nebuchadnezzar voice lines; akītu rhetoric as buff flavor |
| **MOD** | Modern scholarly debate / reconstruction range | Terrace count, base ~91 m classically cited, height estimates as ranges |

**Do-not-assume traps (hard):**
- Babel Bible story ≠ Koldewey elevation.
- Hanging Gardens existence/location disputed → atmosphere only (**FOLK/MOD**).
- “7 colors like Ecbatana” is **not** Babylon archaeology (**LIT** Persian transfer — refuse as ARCH paint).
- Kaynar yağ / boiling oil as default Babylon kit = weak.
- Nebuchadnezzar ≠ cartoon wizard-king.
- mušḫuššu as mass army unit = **FOLK** stretch; relief guardian language is **ARCH**.

---

## 1. Design goals for Act 0

| Goal | Target | Source alignment |
|---|---|---|
| Time-to-first-tower | ≤ 60s | `GDD-LIVEOPS-FTUE` |
| First wave clear + reward | ≤ 3 min | FTUE |
| Soft ★3 or near | ≤ 8 min | FTUE |
| Soft finale + Codex page | ≤ 10 min | FTUE |
| Tutorial maps | 3–5 min, 5–8 waves | `GDD-WAVES-ECONOMY` |
| Finale map | 8–12 min, ~12–15 waves + boss phases | Waves + Defense docs |
| Teach grammar | 7 layers named in HUD once each | Civ hooks |
| Economy literacy | Start gold → kill gold → clear bonus → early-call → sell refund | Economy doc |

Act 0 is **not** a full Neo-Babylonian military encyclopedia. It is a **staged classroom** that happens to be the real city’s processional spine.

---

## 2. Narrative frame (skippable, short)

**Cold start cinematic (≤ 8s, skippable):** Dust settles on a baked-brick silhouette of Etemenanki at dusk; Processional Way lions flash once; title card *Etemenanki*. No lore wall.

**Player role:** A junior *šaknu* / wall-ward under a named commander tutor (voice only). Commander names the player after first victory (FTUE beat).

**Threat spine (tutorial fiction, chronology soft):**
1. Desert / canal fringe raiders (generic runners) — teach basics.
2. Assyrian-style siege pressure (**earlier antagonist kit**, labeled as training memory / flashback drill — **MOD framing** so we do not claim Nebuchadnezzar fights Assurbanipal on the same Tuesday).
3. Elamite highland rush — armor + climb tease.
4. Median/Persian late pressure → **Boss: Cyrus at the Gate** with twin-telling UI.

**Moral lose condition:** Summit **cella / Marduk presence** Integrity hits 0. Flavor: “the foundation of heaven and earth cracks.” Not “lives = 20 balloons.”

---

## 3. Map topology — Processional Avenue → Ziggurat climb

### 3.1 World grammar (matches civ 7-layer fantasy)

| Layer | In-world name | Topology function | Teach beat |
|---|---|---|---|
| L1 | Outer approach / canal fringe | Wide approach lane + optional canal side-path | Place first tower; see path |
| L2 | **Imgur-Enlil** outer wall | Curtain with tower-interval build plots | Curtain DPS / cheap archers |
| L3 | **Nemetti-Enlil** inner wall | Second curtain; shorter kill zone | Second line / sell-and-rebuild |
| L4 | Processional Way (*Ay-ibur-šabu*) approach | Long straight “lion road” with flank plots | Enfilade / support aura |
| L5 | **Ishtar Gate** choke | Single choke + gate HP script | Barracks block + gate interact |
| L6 | Ziggurat terrace climb | Switchback / terrace rings; climbers ignore some rails | Anti-climber / focus fire |
| L7 | Summit cella / Marduk presence | Keep core | Integrity, repair cost teach (discourage) |

**Evidence notes:**
- Double-wall names Imgur-Enlil / Nemetti-Enlil → **ARCH/SPN** (inscriptional + excavation tradition).
- Processional Way + Ishtar Gate glazed-brick program → **ARCH** (Koldewey; Pergamon Museum reconstruction as visual reference, not “the gate is in Berlin in-game”).
- Etemenanki baked-brick core, ~square base classically ~91 m → **ARCH/MOD** ranges; UI shows “~90 m class” not tourist Babel km.
- Classical/Herodotean **7 terraces** → treat as **LIT/MOD play stack**; Codex admits terrace count is reconstruction, not a tape measure.

### 3.2 Map set (FTUE sequence)

| Map ID | Name | Layers open | Topology | Duration |
|---|---|---|---|---|
| B0-M1 | **Canal Fringe** | L1–L2 | Single lane + soft fork at canal bridge | 3–4 min |
| B0-M2 | **Double Curtain** | L1–L3 | Outer→inner wall yards; first sell teach | 4–5 min |
| B0-M3 | **Lion Road** | L2–L5 | Processional straight + Ishtar Gate choke unlock mid-run | 5–7 min |
| B0-M4 | **Terrace Drill** | L4–L6 | Switchback climb; climber enemies | 5–7 min |
| B0-M5 | **Foundation of Heaven** | L1–L7 full stack | Staged yards → gate → climb → cella; boss finale | 8–12 min |

**Topology keywords for level art / pathing:**
- **Staged yards** (siege approach) — already listed in `GDD-DEFENSE-ATTACK`.
- **Chokepoint gate** at Ishtar (masugata cousin, but Babylonian: long approach → gate chamber → inner court).
- **Vertical metaphor:** each cleared yard unlocks the next terrace camera beat (juice, not mandatory fog unlock).

### 3.3 Interactive terrain (scripted, few)

| Interactable | Effect | Tag | FTUE when |
|---|---|---|---|
| Canal sluice | Briefly floods side-path (slow + tiny DoT) | **ARCH** canals exist; combat use = **MOD** design | Optional tip on M1 after leak |
| Ishtar Gate close | Stuns gate-crossing enemies 2s; long CD | **ARCH** gate as choke; stun = game | Forced once on M3 |
| Terrace scaffold | Temporary build plot on climb | **MOD** | M4 |
| Akītu banner (optional Mythic) | Lane lock 4s | **FOLK/SPN** ritual flavor | Off in Historical |

---

## 4. Tutorial FTUE beats (minute script)

Aligned to `GDD-LIVEOPS-FTUE` + Act 0 map set. **No account gate.** Guest play → link later.

### 4.1 Beat table

| t | Beat | Player action | System teach | Fail-soft |
|---|---|---|---|---|
| 0:00 | Cold start cinematic ≤8s | Skip allowed | Brand: Etemenanki | — |
| 0:08 | Ghost hand on highlighted plot | Place **Bow Gallery** | Build plot + gold cost | Auto-place if idle 12s |
| 0:25 | Wave 1 runners | Watch kills → gold ticks | Kill gold | — |
| 0:50 | Forced upgrade | Upgrade once | Upgrade path A tier 1 | Ghost hand |
| 1:20 | First leak scripted (1 runner) | — | **Keep Integrity** explanation (not shame) | Integrity −1 only |
| 1:40 | Place second tower | Place **Spear Barracks** | Blockers / KR DNA | Ghost hand |
| 2:30 | Wave clear | Collect clear bonus | Clear bonus SFX | — |
| 3:00 | M1 victory | Soft stars | ★1 guaranteed; ★2/★3 tips | Retry free |
| 3:30 | Hub strip | Only **Play / Codex / Shop (grey)** | Hub anti-clutter | — |
| 4:00 | M2 start gold tight | Choose 2–3 towers | Starting gold philosophy | — |
| 5:00 | Sell teach | Sell misplaced tower | 50–70% refund | — |
| 6:00 | Early-call intro (after wave 4) | Call early once | +% gold vs overlap risk | Safety pause first time |
| 7:30 | M3 gate interact | Close Ishtar Gate once | Terrain interact | — |
| 8:30 | Armor wave | Place / upgrade **Sling Battery** or heavy path | Armor matrix tease | Tip strip |
| 9:30 | M4 climbers | Place terrace archers | Path-ignore partial | — |
| 10:00 | M5 soft finale | Boss phases | Focus fire + hero skill | Checkpoint every 5 waves (a11y) |
| Post | Name commander | Confirm name | Identity dopamine | Default name OK |
| Post | Codex page 1 | Open “Etemenanki: Dig vs Story” | ARCH vs FOLK literacy | Skippable |

### 4.2 Anti-patterns (Act 0 specific)

- No 8-currency dump.
- No Enuma Elish essay before first tower.
- No boiling-oil “signature” unlock in tutorial.
- No forced Mythic mušḫuššu laser.
- No energy gate on retry.
- Returning player: “Commander returning” → skip Act 0 combat, keep Codex gifts, drop into Act 1 with loadout help (`GDD-LIVEOPS-FTUE` §7).

### 4.3 Accessibility FTUE

- Colorblind-distinct tower silhouettes (bow / spear / sling / gate).
- Speed 1× default; 2× unlocks after M3 clear.
- One-hand bottom build bar.
- Dyslexia font = later settings, mentioned once in Codex footer.

---

## 5. Towers (Act 0 unlock ladder)

Universal classes from `GDD-DEFENSE-ATTACK`, Babylon-skinned. Launch suggestion: **2 paths × 3 tiers** on Act 0 (simpler than full 3×4); full paths unlock Act 1+.

### 5.1 Starter kit (M1–M2)

| Tower | Class | Role | Evidence | Notes |
|---|---|---|---|---|
| **Bow Gallery** | Arrow / missile | Cheap DPS | **ARCH** archery ubiquitous; mural/relief language | Ghost-hand first place |
| **Spear Barracks** | Barracks / spawn | Melee blockers | **ARCH** infantry; garrison fictionalized as plot building | Forced teach M1 |
| **Mudbrick Curtain Post** | Arrow support | Short range, cheap wall-slot | **ARCH** mudbrick + tower intervals | Auto-highlighted on L2 |

### 5.2 Mid tutorial (M3–M4)

| Tower | Class | Role | Evidence | Notes |
|---|---|---|---|---|
| **Sling Battery** | Splash light | Soft AoE vs swarm | **ARCH/MOD** sling use in Near East; not Babylon exclusive | Armor/swarm teach |
| **Processional Flank** | Support / buff | +range aura along straight lane | **MOD** design; road is **ARCH** | Lion-brick VFX **ARCH** |
| **Ishtar Gate Ward** | Special civ / gate | Gate HP buffer + slow in choke | Gate **ARCH**; buff numbers **MOD** | One per map |
| **Terrace Archer Platform** | Arrow (elevated) | Bonus vs climbers | Terraces **LIT/MOD**; archery **ARCH** | M4 unlock |

### 5.3 Finale unlocks (M5 / post-clear cosmetics)

| Tower | Class | Role | Evidence | Notes |
|---|---|---|---|---|
| **Esagila Precinct Ward** | Support | Tiny regen aura near keep | Temple complex **ARCH**; regen **MOD** | Discourages keep-repair sink |
| **Glazed Brick Ballista** | Heavy bolt | Anti-armor | Heavy bolt = universal class; glazed brick = **ARCH** visual | Optional if pacing allows |
| **Mušḫuššu Relief** | Special / Mythic | Fear pulse / taunt | Relief creature **ARCH**; combat pet **FOLK** | Historical mode: cosmetic only |

### 5.4 Upgrade philosophy (Act 0)

- Path A: **Rate / volley** (teach DPS stacking).
- Path B: **Utility** (slow, armor shred, or blocker HP).
- Mutual exclusion not required until Act 1.
- Sell refund 60% during tutorial (forgiving); 50% after Act 0.

### 5.5 Hero (tutor → player)

| Hero | Skill | Teach | Tag |
|---|---|---|---|
| Tutor Commander (NPC) | Rally: +block for 5s | Passive use once on M3 | **MOD** |
| Player Commander (unlock post-M3) | Flare: reveal + small slow | Active ability slot | **MOD** |
| Optional skin: *šangu* priest | Akītu lane hush | Mythic only | **FOLK/SPN** |

No gacha. Unlock by stars / map clears.

---

## 6. Enemies (wave grammar for Act 0)

Wave sentence from economy doc:  
`[opener runners] + [main force] + [special] + [optional bosslet]`

### 6.1 Roster

| Enemy | Role | Counter teach | Chronology / tag |
|---|---|---|---|
| **Canal Runner** | Runner / leak | Barracks + slow | Generic **MOD** |
| **Levy Spearman** | Main force | Bow galleries | Neo-Babylonian levy flavor **MOD** |
| **Shield Pair** | Shield wall | Flank plots / AoE behind | **MOD** tactics |
| **Elamite Highlander** | Armored / partial climber | Sling / heavy / terrace | Elamite pressure **ARCH** rivalry era (soft) |
| **Assyrian Sapper** | Siege vs curtain plots | Focus fire | Assyrian siege craft **ARCH**; as drill antagonist **MOD** |
| **Siege Ram Crew** | Siege engine → gate/keep damage | Gate close + focus | Near Eastern siege **ARCH** |
| **Median Horse Scout** | Fast flank on forks | Early-call discipline | Median/Persian prelude **ARCH** soft |
| **Persian Immortal (teaser)** | Elite armored | Armor shred | Greek lens name → Codex **LIT** vs Persian self-terms **MOD** |
| **Boat Raider (optional)** | Canal spawn mid-path | Announce 2s prior | Canal warfare **MOD**; fairness rule from waves doc |

### 6.2 Wave pacing by map

| Map | Waves | Composition beat |
|---|---|---|---|
| M1 | 5 | W1–3 single type; W4 mix; W5 tiny elite |
| M2 | 6 | Armor intro W4; sell window between W2–W3 |
| M3 | 8 | Gate ram W5; early-call after W4; Immortal teaser W8 |
| M4 | 8 | Climbers W3+; terrace focus |
| M5 | 12–15 + boss | Full mix; scripted gate hit mid; Cyrus phases |

Leak damage (Integrity): runner 1, armored 2, ram hit 8, boss phase leak 15 — matches defense doc spirit.

---

## 7. Boss — Cyrus at the Gate (M5)

### 7.1 Twin-telling design (mandatory labels)

| Telling | Hook | Tag | Game expression |
|---|---|---|---|
| **A — Opis / Nabonidus Chronicle** | Battle at Opis, then entry into Babylon | **ARCH/SPN** (chronicle) | Phase 1 field army; Phase 2 “city opens” morale shock |
| **B — Herodotean river-drain** | Divert Euphrates, enter along riverbed | **LIT** | Optional phase modifier: canal lane empties, new dry path opens |

**UI rule:** Before finale, Codex card: “Two ancient stories — you can play either telling. Neither is silent truth.” Default Historical playlist = **Telling A**. Telling B = optional modifier (still labeled LIT, not FOLK cartoon whirlpool).

### 7.2 Phases

| Phase | Script | Player teach | Juice |
|---|---|---|---|
| P1 | Mixed army + ram on Ishtar Gate | Focus fire + gate interact | Camera shake on ram (`GDD-WAVES-ECONOMY` juice) |
| P2 | “City entry” — spawn points flip to inner court | Rebuild inner plots; sell outer if needed | Horn SFX; Integrity warning |
| P3 | Elite guard push toward cella | Hero skill + Esagila ward | Summit camera |
| P3alt (LIT B) | Canal dry-path opens | Emergency terrace / flank towers | Water VFX drain — labeled **LIT** |

### 7.3 Boss fail / win

- Win: soft finale banner in Akkadian-flavored English (“The foundation holds”); ★ stars; Codex page; name commander.
- Lose: keep cracks, tip (“Barracks at the gate”), free retry with small gold bonus — no shame ad.

### 7.4 Soft myth ultimates (post-tutorial previews, not required to clear)

| Ultimate | Effect | Tag | Availability |
|---|---|---|---|
| **Marduk Order** | Global brief slow + Integrity shield pulse | Cult ideology **SPN/ARCH** names; combat = **MOD** | Preview cinematic only in Act 0 |
| **Ishtar Gate Aura** | Choke slow aura | Gate **ARCH** | Passive on Gate Ward tower |
| **Akītu Procession** | Temporary lane lock | Ritual **SPN**; combat lock **FOLK** | Mythic playlist |
| **Mušḫuššu Guardian** | Summon taunt unit | Relief **ARCH**; summon **FOLK** | Mythic only |

---

## 8. Economy teaching moments

Map to `GDD-WAVES-ECONOMY` sources/sinks. Act 0 is where the player **learns to read gold**, not min-max interest.

| Moment | Map | Mechanic | Lesson |
|---|---|---|---|
| **E1 Starting purse** | M1 | Gold for exactly 2 towers | “Enough for 2–3 base towers; not full board” |
| **E2 Kill ticks** | M1 W1 | Floating +gold | Per-kill value |
| **E3 Clear bonus** | M1 end | Banner bonus | Wave clear ≠ only kills |
| **E4 Upgrade opportunity cost** | M1–M2 | Forced upgrade then tight next wave | Upgrade vs new place |
| **E5 Sell refund** | M2 | Sell misplaced post | Refund %; planning > panic spam |
| **E6 Early-call** | M3 after W4 | Button + first-time pause | Risk overlap vs +% gold / seal chance |
| **E7 Gate repair temptation** | M3 | Expensive gate repair | Sinks: repair discouraged |
| **E8 Interest tease (optional)** | M5 only | “Merchant caravan” support plot offers tiny interest | Optional hard-map DNA; **off** for first clear |
| **E9 Star economy** | Post each map | ★1 clear / ★2 Integrity / ★3 speed or no-sell-abuse | Stars → Codex seals later |
| **E10 Soft reward shower** | Post M5 | 1 soft currency + 1 cosmetic brick + Codex | Not 50 currencies |

**Anti-P2W in tutorial:** no starter-pack hard gate; ethical pack only after D3 engagement (`GDD-LIVEOPS-FTUE` care table) — outside Act 0 combat script.

---

## 9. Evidence vs FOLK — content cards (Codex seeds)

### 9.1 ARCH-first cards (always on)

1. **Etemenanki name & role** — Marduk temple-tower; Esagila pairing.
2. **Koldewey excavation** — baked-brick core; base scale as range.
3. **Imgur-Enlil / Nemetti-Enlil** — named double walls.
4. **Processional Way** — paved approach; animal relief program.
5. **Ishtar Gate** — glazed brick; mušḫuššu & aurochs relief language.
6. **Canal / Euphrates urban water** — city in a hydraulic landscape.
7. **Nebuchadnezzar II restoration voice** — SPN inscriptions as flavor, cross-checked.

### 9.2 LIT cards (labeled)

1. Classical staged tower / terrace readings (incl. Herodotus-adjacent tradition).
2. Herodotean capture via river diversion.
3. Biblical Tower of Babel as **literature** parallel — “not a measured blueprint.”

### 9.3 FOLK cards (Mythic / cosmetic only)

1. Tourist infinite-height Babel.
2. Hanging Gardens as confirmed on-map wonder (disputed → atmosphere skybox at most).
3. Ecbatana seven-color paint transferred onto Babylon.
4. Boiling oil “Babylon specialty.”
5. Wizard-king Nebuchadnezzar casting Enuma Elish lasers.
6. Playable army of living mušḫuššu.

### 9.4 Codex page 1 (forced offer post-M5)

**Title:** *Etemenanki — Dig vs Story*  
**Layout:** Left column ARCH bullets; right column LIT/FOLK with red “story” chips.  
**CTA:** “Play Historical” (default) / “Enable Mythic cosmetics.”

---

## 10. Star ratings & medals (Act 0)

| Star | Condition |
|---|---|
| ★1 | Clear |
| ★2 | Keep Integrity ≥ 70% OR no leak past Nemetti-Enlil (inner wall) |
| ★3 | Integrity ≥ 90% + speed clear OR no sell-abuse (M2+ ) |

**Codex seals (preview):** Heroic / Iron medals mentioned in victory tip but full modes unlock after Act 0 — avoids FTUE overload.

---

## 11. Audio / juice checklist (presence, not noise)

1. Wave horn with Akkadian-flavored brass (original, not movie trailer choir spam).
2. Ram-hits-gate camera shake (M3/M5).
3. Glazed-brick chime when placing on Processional flank plots.
4. Victory banner: short line, civ language flavor, skippable.
5. Defeat: crack VFX on cella, calm tip, retry.

Intentional motions (art direction note): terrace parallax on camera push-in; Processional Way scroll of reliefs during build phase; boss dry-path water drain only if LIT B enabled.

---

## 12. Vertical-slice acceptance (before numbers lock)

- [ ] M1 places first tower ≤ 60s on usability test.
- [ ] All seven layer names appear once in HUD tooltips across M1–M5.
- [ ] Every FOLK unit/skin behind Mythic toggle.
- [ ] Cyrus twin tellings both labeled in UI.
- [ ] Early-call taught once with safety pause.
- [ ] Economy moments E1–E7 observed in telemetry funnels.
- [ ] No boiling oil, no Babel height claim, no Gardens as ARCH objective.
- [ ] Returning-player skip works without losing Codex gifts.

---

## 13. Handoff to later acts

Act 0 graduates the player with:

| Unlocked concept | Reused in |
|---|---|
| 7-layer grammar | All civs |
| Gate choke interact | Japan masugata, China wengcheng, Europe gatehouse |
| Staged siege yards | Persia terrace, Castle lists |
| Twin-source labeling | Persia 539, Greece Archimedes FOLK toggle, etc. |
| Integrity keep model | All acts |
| Early-call + stars | Live ops / dailies |

**Pitch line for store / trailer (Act 0 only):**  
*Hold the Processional Way. Close the Ishtar Gate. Keep Marduk’s mountain standing — then the world teaches you every other wall.*

---

## 14. Open MOD questions (do not block FTUE)

1. Exact terrace count in art vs “7” play metaphor — pick one silhouette, footnote in Codex.
2. Whether Assyrian kit is framed as flashback drill or separate “memory siege” map skin.
3. Whether canal boat spawns appear before M5 (fairness vs spectacle).
4. Ballista glazed-brick unlock in M5 vs defer to Act 1 Rome tease.
5. Interest / merchant caravan — include as optional tooltip only until economy telemetry exists.

---

## 15. Source pointers (public ARCH / design crosswalk)

| Topic | Pointer | Tag |
|---|---|---|
| Etemenanki / Esagila | Koldewey tradition; Neo-Babylonian building program | ARCH/MOD |
| Walls | Imgur-Enlil, Nemetti-Enlil naming | ARCH/SPN |
| Ishtar Gate / Processional Way | Excavation + glazed brick program | ARCH |
| 539 BCE capture | Nabonidus Chronicle / Opis vs Herodotus diversion | ARCH/SPN vs LIT |
| Design grammar | `GDD-CIV-HOOKS` Act 0 card; `GDD-LIVEOPS-FTUE`; `GDD-WAVES-ECONOMY`; `GDD-DEFENSE-ATTACK` | Design |

---

*End of GDD-BABYLON-ACT0. Tutorial act hooks for historical TD; ARCH-first, FOLK labeled, economy and FTUE beats explicit. No balance numbers locked.*
