# GDD Research — Defense Systems (Historical Civ Tower Defence)

> **Agent:** A7 Defense systems  
> **Status:** Design research / playable grammar (not final balance numbers)  
> **Depends on:** civ fortification briefs’ grounded ~7-layer stacks; shared “keep + onion” rule in `docs/TOWER-DEFENCE-TARIHSEL-ARASTIRMA-INDEX.md`  
> **Civ roster:** Babylon · Rome · Persia · Egypt · China · Maya–Mexica · Viking · Greece · Japan · Mongol · European Castle  

---

## 0. Design verdict (one screen)

Defense is **five verbs**, not five menus:

| Verb | System | Job |
|---|---|---|
| **Shoot** | Towers | Lane DPS / control from fixed or pad positions |
| **Hold** | Barracks / garrison | Block, stall, soak; create time for Shoot |
| **Shape** | Walls / gates / lists | Force path length, chokes, and fire corridors |
| **Punish** | Traps / hazards | Burst tax on forced tiles (gates, ditches, bridges) |
| **Pivot** | Heroes | Player agency: plug leaks, open sorties, spend Favor |

Everything else (resources, pads, upgrade trees, civ skins) exists to make these verbs readable on a phone and faithful to historical fort grammar **without** drawing seven full concentric yards as UI chrome.

---

## 1. Core defense pillars (ship checklist)

1. **Lane clarity** — player always knows where enemies walk and where fire is legal.  
2. **Choke > carpet** — value concentrates on gates, bridges, corners, not uniform spam.  
3. **Hold + Shoot loop** — barracks create dwell time; towers convert dwell into kills.  
4. **Layered HP, not layered clutter** — 7 historical layers map to ≤4 playable strata + soft events.  
5. **Shared archetypes, civ skins** — 6–8 core tower jobs; uniqueness via 1–3 civ towers + hero/trap kits.  
6. **Hybrid placement** — pads for clarity; free-place only where history demands (stakes, ditches, field camps).  
7. **Four resources with roles** — Gold (ops), Wood (field/fast), Stone (permanent HP), Favor (ritual/hero/myth).  
8. **Synergy vocabulary** — tags (Pierce, Splash, Slow, Block, Armor-Break, Anti-Climb, Naval, Ideology) compose builds.  
9. **Gate as boss doorway** — every map’s emotional beat is a named gate/trap-court, not a faceless wall.  
10. **Defeat modes beyond “keep HP = 0”** — ideology/morale (chapel, temple, throne), supply cut, garrison wage collapse.  

---

## 2. Defense object taxonomy

### 2.1 Towers (Shoot)

**Definition:** Immobile (or rarely relocatable) emitters with range, target rules, and upgrade paths.

| Property | Guidance |
|---|---|
| Placement | Prefer **pads** on walls / mural points / bailey corners |
| Targeting | Priority presets: First / Last / Strong / Weak / Flying / Siege |
| Soft caps | Soft soft-cap on identical archetype per lane (anti-spam, not hard ban) |
| Visual | Silhouette must read at 50% zoom; civ skin ≠ new hitbox |

**Anti-patterns:** Towers that only exist as “+10% damage aura with no shot”; invisible range; seven tower tiers that are pure stat clones.

### 2.2 Barracks / garrison buildings (Hold)

**Definition:** Spawn or house **blockers** (soldiers) that path-block or leash enemies in melee.

| Role | Notes |
|---|---|
| Lane blocker | Classic KR-style barracks; revive timer; armor/HP identity |
| Wall garrison | Occupies curtain segment; fires weak volleys OR buffs adjacent towers |
| Sortie nest | Unlocks timed sally (umadashi, Roman *eruptio*, knight counter-charge) |

**Rules:** Barracks without a choke feel weak; chokes without barracks feel brittle. Pair them. Cap simultaneous living blockers per lane so Hold never replaces Shoot.

### 2.3 Traps & site hazards (Punish)

**Definition:** Tile- or segment-bound effects, often single-use / recharge / consumable.

| Family | Historical cue | Play |
|---|---|---|
| Ditch / moat slow | Universal outer layer | Permanent or repairable Slow + cavalry tax |
| Stake / caltrop field | Viking landing denial; field camps | Anti-cav; burns/removable by sappers |
| Gate vertical tax | Murder-holes, *ishi-otoshi*, portcullis stages | Burst in gate segment only |
| Trap courtyard | Masugata, Messene Arcadian Gate, barbican | Forced 90° turn + dwell + multi-angle fire |
| Fire / pitch | Hoardings, naphtha traditions (civ-gated) | AoE + friendly-fire risk on timber |
| Bridge / ford charge | Destroyable span | Path rewrite; expensive |

Traps are **not** free-place carpet mines. Default = **site-authored** or **consumable on marked tiles**.

### 2.4 Heroes (Pivot)

**Definition:** One (campaign) or 1–2 (skirmish) player-controlled units with cooldowns, not a third build menu.

| Mode | Use |
|---|---|
| Active micromanage | Drag to leak; ability on choke |
| Autocast + rally | Casual / FTUE |
| Ideology ult | Spend Favor for map-wide beat (rite, banner, omen) |

Heroes **should not** out-DPS a fully upgraded core tower forever; they **should** uniquely: revive Hold, break armor, reveal betrayal lanes, or spend Favor for once-per-wave pivots.

Civ hero kits come from existing research figures (Leonidas choke aura, Sanada keep stand, Marshal household rally, etc.) — see civ TD briefs.

### 2.5 Walls, gates, lists (Shape)

**Definition:** Path and HP infrastructure. Not primarily DPS.

| Element | Playable job |
|---|---|
| Curtain / rampart | Lane HP; mural tower pads; walkway for garrison |
| Gate / gatehouse | Multi-stage lock (doors → portcullis → murder box); boss doorway |
| Lists / kill field | Open ground under inner fire after outer curtain falls |
| Moat / ditch | Slow + siege-engine parking denial |
| Keep / ideology core | Final refuge **or** morale victory totem (temple/chapel/throne) |

**Repair economy:** Stone + time; Wood for timber hoardings (high buff, fire vulnerability). Empty max-HP walls fall if **Garrison Wage** unpaid (European research lesson).

---

## 3. Mapping 7 historical layers → playable strata (anti-clutter)

Historical briefs use a **synthetic ~7-layer onion** (outer → core). Shipping seven simultaneous interactive yards causes:

- unreadable minimap  
- tutorial overload  
- identical “another HP bar” fatigue  

### 3.1 Compression rule — **4 playable strata + 3 soft layers**

| Playable stratum | Absorbs historical layers | Player verbs |
|---|---|---|
| **S1 Approach** | Outer town, pasture, sea/fjord, beach stakes, suburbs | Slow, reveal, optional burnable economy tissue |
| **S2 Threshold** | Gate / barbican / masugata / Dipylon / cardinal gates | Trap courtyard, portcullis stages, named choke boss |
| **S3 Shell** | Outer + inner curtains, mural towers, yagura line, rampart | Main pad ring; wall HP; flanking fire |
| **S4 Core** | Keep / honmaru+tenshu / palace / acropolis temples / chapel-hall | Final HP **or** ideology meter; elite garrison |

| Soft layer (event / meter, not full yard) | Examples |
|---|---|
| Economy tissue | Jōkamachi burn; extramural kilns; refugee crowding |
| Supply umbilical | Long Walls corridor; harbour moles; Way-from-the-Sea dock |
| Ideology / legitimacy | Chapel, Parthenon, Silver Tree, Etemenanki, Mandate rites |

**Rule of thumb:** If a historical layer does not change **pathing**, **pad legal set**, or **win/lose meter**, it is soft — flavour VFX, optional objective, or campaign scar — not a seventh build zone.

### 3.2 Civ stack → strata cheat-sheet

| Civ | Historical 7 (abbrev.) | Playable focus |
|---|---|---|
| **European Castle** | ditch → gate/barbican → outer curtain → lists → inner curtain → keep → chapel/hall | S2 gatehouse star; S3 concentric dual curtain; S4 keep **or** gatehouse-keep; ideology soft |
| **Japan** | town+moat → masugata → sannomaru → ninomaru maze → yagura → honmaru → tenshu | S2 masugata puzzle; S3 spiral path-tax; S4 tenshu-gun brand |
| **Greece (Attic)** | Piraeus → Long Walls → city wall → gates → urban tissue → Propylaea → temples | Soft supply corridor; S2 Dipylon; S4 sacred core (morale) |
| **Viking** | sea → beach stakes → ditch → turf-timber rampart → gates → interior grid → hall/temple | S1 naval+stakes free-place; S3 timber fire risk; S4 hall |
| **Mongol (defend capital)** | pasture → suburbs → hangtu wall → gates → quarter maze → palace enclosure → throne | S1 remount screen; light S3 (don’t over-fortify fantasy); S4 brick palace |
| **Mongol (attacker fantasy)** | reverse 7 siege stack | Different mode: engineer park + moat-fill — not defender clutter |
| **China** | (wengcheng / multi-gate grammar from Chinese briefs) | S2 trap-court mastery; tall shell; gunpowder coda gated |
| **Rome** | marching camp → walls → gates → principia grammar (Capitolium stack in TR brief) | Fast Wood castra; Stone upgrade; doctrine pads |
| **Egypt** | Nile/approach → Buhen-class forts → gates → inner → temple ideology | River S1; mudbrick Soft HP; stone prestige |
| **Persia** | parade/approach → walls → gates → palace → Apadana ideology | Wide kill fields; satrap reinforcement as Hold |
| **Maya–Mexica** | terrain/causeway → earthworks → gates → precinct → temple-pyramid | Causeway chokes; capture-priority enemies vs pure DPS |
| **Babylon** | processional → walls → gates (Ishtar) → processional court → ziggurat core | Gate brand; Favor-heavy ideology core |

### 3.3 UI declutter techniques

1. **One HP ribbon** for Shell (outer/inner curtains are upgrade states or breach stages, not separate meters).  
2. **Gate as multi-phase object** (3 locks) instead of three buildings.  
3. **Path heat overlay** (FTUE / photo mode) instead of labeling every bailey.  
4. **Breach theatre:** losing S2 collapses path into S3 lists automatically — drama without extra widgets.  
5. **Civ glossary on long-press** — “masugata” teaches once; daily play sees icon + Slow/Turn tags.

---

## 4. Placement: pads vs free-place

### 4.1 Default hybrid

| Mode | Where | Why |
|---|---|---|
| **Pads (primary)** | Curtain towers, bailey corners, gate flanks, keep roof | Mobile readability; historical mural points; balance |
| **Segment build** | Walls, ditches, gate upgrades | Shape verb; spends Stone/Wood |
| **Marked trap tiles** | Gate throat, bridge, ditch lip, trap court | Punish without carpet |
| **Free-place (limited)** | Stakes, caltrops, field pavises, Mongol screen, temporary camps | History + player expression; hard count caps |

### 4.2 Free-place guardrails

- Cap by **count** and **resource**, not by invisible “illegal green goo.”  
- Illegal: blocking the **only** path entirely (unless map flags “sealable postern”).  
- Illegal: stacking 12 stake fields into a stun-lock; use diminishing returns.  
- Civ toggles: Viking/Mongol lean freer; Edwardian concentric leans pad-strict.

### 4.3 Kingdom Rush lesson, historical twist

Pads teach fairness; historical forts teach **forced geometry**. Use pads **on** historically attested mural rhythms (Beaumaris tower cadence, yagura lines), not random lawn dots.

---

## 5. Resources — Gold / Wood / Stone / Favor

### 5.1 Roles (orthogonal, not four greys)

| Resource | Fantasy | Buys | Scarcity feel |
|---|---|---|---|
| **Gold** | Coin, plunder, tax, tribute | Troop wages, trap refreshes, mercenary Hold, rush repairs | Wave income + optional side objectives |
| **Wood** | Timber, fascines, ships, hoardings | Fast towers, stakes, temporary barracks, bridges, fire ammo | Abundant early; burns; weak late vs Stone meta |
| **Stone** | Ashlar, brick, hangtu, ishigaki | Permanent curtains, gatehouses, heavy towers, keep upgrades | Slow income / masonry channel; defines mid–late power |
| **Favor** | Gods, ancestors, Mandate, relics, Tengri omens, kami, saints | Hero ults, ideology shields, omen reveals, myth skins **as buffs** | Mission deeds, rites, optional risk rituals — **not** P2W mana faucet |

### 5.2 Conversion & tension

- **Wood → Stone** is a campaign tech (Norman timber → stone keep), not an instant exchange button mid-wave.  
- **Gold upkeep** on elite garrison: high Stone walls with zero Gold wage → surrender event (European dossier).  
- **Favor** never permanently replaces Gold DPS; it **times** pivots and soft win conditions (save the temple, complete the vow).  
- Civ weights: Egypt/Babylon/Greece Favor-rich ideology; Rome/China Gold+Stone admin; Viking Wood-forward; Mongol Gold/Favor (loot + heaven) with borrowed Stone engines.

### 5.3 Income channels (defense-facing)

- Passive wave stipend (Gold).  
- Held markets / harbours / pasture (map buildings on S1).  
- Salvage from breached siege engines (Wood/Metal as Gold).  
- Mason’s progress mini-channel (Stone between waves).  
- Liturgical actions / relics / omens (Favor).

---

## 6. Upgrade trees

### 6.1 Shared structure (all civs)

```
Tier 0  Foundation          (build)
Tier 1  Trade-up            (one of two branches)
Tier 2  Specialization      (branch deepens)
Tier 3  Mastery / emblem    (civ-flavoured capstone; not raw +damage only)
```

- **Two branches max** per tower (readability).  
- Branch names = **verbs** (Pierce vs Volley; Oil vs Stones; Discipline vs Fanatic).  
- Capstones change **rules** (bounce, armor-break, dual-target, gate-phase skip) more than +% sheets.  
- Global tech (campaign) unlocks tiers; in-mission gold/wood/stone buys ranks.

### 6.2 Barracks tree

- Path A: **Stubborn** (HP, armor, revive).  
- Path B: **Sharp** (damage, special anti-type).  
- Side nodes: Sortie charge, Shield wall (block flying? no — block cav), Javelin prep.

### 6.3 Gate / wall tree

- Gate: extra lock → murder-hole trap → boiling/sand/stone vertical (civ-accurate; **no default boiling oil FOLK** unless flagged).  
- Wall: walkway → hoarding (Wood, fire risk) → talus / anti-mine (Stone) → gunports (late coda civs only).

### 6.4 Hero tree (meta, light)

Skill pages: Passive lane aura · Active gap-closer · Favor ult. Avoid 20-node webs for launch.

### 6.5 Age / chronology gates

Upgrade availability follows civ chronology peaks (stone florescence, teppō era, gunpowder twilight) so the tree **teaches history** instead of generic RPG levels.

---

## 7. Synergies (tag grammar)

### 7.1 Core tags

`Pierce` `Splash` `Single` `Slow` `Root` `Block` `Armor-Break` `Anti-Shield` `Anti-Climb` `Anti-Cav` `Anti-Siege` `Flying` `Naval` `Burn` `Reveal` `Ideology` `Wage`

### 7.2 Intended combos (teach in FTUE)

| Combo | Pieces | Payoff |
|---|---|---|
| **Anvil & Hammer** | Barracks Block + Splash tower | Classic |
| **Kill corridor** | Slow ditch + Pierce line + gate trap | Choke mastery |
| **Open the tin** | Armor-Break hero/trap + Single sniper | Elite delete |
| **Fire discipline** | Burn ammo + stone court (non-flammable) | Safe burn zone |
| **Ideology brace** | Favor shield on Core + Wage-paid garrison | Survive boss wave |
| **Path tax** | Maze/lists + long-range mural pads | Japan/Europe specialty |
| **Naval cork** | Naval tag towers + chain/harbour | Greece/Viking/Egypt maps |

### 7.3 Anti-synergy (honesty)

- All-Burn on timber Viking ramparts → self-grief.  
- Pure Splash vs single super-armor boss → fail.  
- Favor stacking without Gold wage → pretty temples, empty walls.  
- Free-place stakes behind pads that never see path → wasted Wood.

### 7.4 Aura budget

At most **one** strong aura per lane sector. Prefer short-range banners (hero, standard tower) over map-wide mush.

---

## 8. Choke design

### 8.1 Choke types

| Type | Example | Design notes |
|---|---|---|
| **Gate throat** | Barbican, masugata, Dipylon | Best teaching choke; multi-phase |
| **Bridge / ford** | River maps, Long Walls logic | Destroyable; high drama |
| **Pass / paleo-choke** | Thermopylae-style | Terrain, not walls; betrayal side-path risk |
| **Spiral tax** | Japanese bailey connectors | Length without extra HP meters |
| **Lists killfield** | After outer breach | Temporary open under inner fire |
| **Harbour mouth** | Chain, moles, stakes | Naval + land hybrid |

### 8.2 Rules of good chokes

1. **Telegraphed** one wave early (scout dust, drum, horn).  
2. **Flank honesty** — if a betrayal path exists, scouting/Favor reveal can close or delay it.  
3. **Pad ownership** — choke flanks get the best pads; lawn pads are weaker.  
4. **One primary choke per sector** — secondary is optional leak, not equal twin (avoids split-attention hell on mobile).  
5. **Boss doorway cadence** — mid-mission and finale use named gates, not anonymous wall segments.

### 8.3 Choke scoring (design QA)

A choke is “healthy” if a mid-skill player with Anvil & Hammer clears it, and a splash-only or barracks-only build visibly struggles.

---

## 9. Core tower archetypes (shared across civs)

Shared **jobs**; civs supply art, SFX, ammo fiction, and minor passive tweaks.

| ID | Archetype | Primary tags | Role |
|---|---|---|---|
| **T1 Archer / missile** | Bow, sling, crossbow skin | Pierce / Single | Cheap reliable DPS |
| **T2 Spearmen nest / watch** | Short-range defensive | Anti-Cav, weak Splash | Early Hold assist / wall pad |
| **T3 Barracks** | (building; listed as defense piece) | Block | Anvil |
| **T4 Stone-throw / light artillery** | Sling-staff, mangonel lite | Splash | Clump clear |
| **T5 Heavy engine** | Ballista, trebuchet, traction | Anti-Siege / Single big | Elite & engine delete; slow RoF |
| **T6 Support / standard** | Banner, horn, signal | Aura (tiny), Reveal | Wage/morale; vision |
| **T7 Fire / specialty ammo** | Pitch, naphtha, fire-arrows | Burn | Zone denial; map-risk |
| **T8 Sacred / ideology** | Shrine, chapel-gun, stela | Ideology, Favor sink | Soft shields, anti-fear; low raw DPS |

**Launch loadout:** T1–T5 mandatory; T6–T8 unlock via campaign acts so FTUE stays clean.

### 9.1 Branch examples (shared)

- **T1:** Volley (RoF) vs Bodkin (Armor-Break).  
- **T4:** Wide Splash vs Stun pebble (brief Root).  
- **T5:** Bolt (Pierce line) vs Crush (siege bonus).  
- **T7:** Ground fire vs Sticky pitch (Slow+Burn).  
- **T8:** Ward (shield Core) vs Wrath (Favor nuke, long CD).

---

## 10. Civ-unique towers (1–3 per faction)

Uniques must **change decisions**, not just rename T1.

| Civ | Unique tower ideas | Hook |
|---|---|---|
| **Babylon** | Ishtar Gate lion-shot; ziggurat beacon (Favor vision) | Processional killbox + ideology |
| **Rome** | Scorpio battery; *testudo* drill yard (mobile-ish Hold buff) | Doctrine + engine science |
| **Persia** | Sparabara screen tower; satrap relay (reinforcement beacon) | Soft Block + empire call |
| **Egypt** | Nile inundation sluice (path Slow); temple pylon sniper | River control + sacred axial fire |
| **China** | Wengcheng overwatch; traction trebuchet; optional early gunpowder coda | Trap-court + engine depth |
| **Maya** | Atlatl gallery; forest blind (Reveal/ambush pad) | Skirmish tempo |
| **Mexica** | Captive-priority shrine tower (bonus for non-lethal on elites); vertical temple archer | Capture economy vs pure kill |
| **Viking** | Ship-chain / harbour boom; stake-yard free-place hub | Naval S1 identity |
| **Greece** | Long-Wall battery (corridor dual-fire); gastraphetes pad | Supply corridor + tech bow |
| **Japan** | Yagura with *ishi-otoshi*; teppō volley yagura (late) | Anti-climb + gun age gate |
| **Mongol** | Mobile screen wagon (relocatable pad-lite); captured Chinese engine yard | Attacker-defender duality |
| **European Castle** | Flanking mural turret (crossfire mandate); hoarding module; springald | Concentric crossfire fantasy grounded |

**Budget:** Launch with **1 unique + 1 signature trap/gate module** per civ; second unique in live ops.

---

## 11. Walls & gates as equal citizens

Do not treat walls as passive skin.

| Action | Cost bias | Effect |
|---|---|---|
| Raise / thicken curtain | Stone | Shell HP, new pads |
| Add hoarding | Wood | +fire rate / drop; Burn vulnerability |
| Upgrade gate locks | Stone + Gold | Extra phase before Core exposure |
| Install vertical trap | Wood/Stone | Punish in throat |
| Pay garrison | Gold | Prevent auto-surrender / accuracy loss |
| Bless / dedicate | Favor | Ideology HP buffer on Core |

Breach is a **state machine**: Outer lock lost → lists open → inner fire amplifies → Core exposed. Players should *feel* European lists / Japanese spiral / Greek Propylaea as that state change.

---

## 12. Mission sheet template (for level designers)

```
Map:
Civ skin:
Playable strata in use: S1 / S2 / S3 / S4
Soft layers: (economy / supply / ideology)
Primary choke:
Betrayal / leak path: yes-no + reveal method
Pad count by stratum:
Free-place caps:
Starting resources G/W/S/F:
Forced tutorial combo:
Boss doorway name:
Defeat conditions: Core HP / Ideology / Supply timer / Wage collapse
```

---

## 13. Competitive & genre anchors (design only)

| Lesson | Source genre | Apply |
|---|---|---|
| Barracks create time | Kingdom Rush | Hold+Shoot mandatory teach |
| Pad fairness | KR / many mobile TDs | Default pad ring |
| Status combos | BTD6 | Tag synergies, not 50 tower types |
| Plant slots as Shape | PvZ | Gate/ditch as “plants” that alter path |
| Operator identity | Arknights-lite | Heroes + blockers as roles |
| Don’t drown in towers | Reddit hate pattern | Shared archetypes + few uniques |

---

## 14. Risks & non-goals

| Risk | Mitigation |
|---|---|
| 11 civs × 12 towers = production death | Shared T1–T8 mesh; 1 unique launch |
| 7 yards of UI | 4 strata compression |
| Myth lasers as Favor DPS | Favor = pivot/shield/reveal; FOLK toggle for fantasy skins |
| Boiling oil everywhere | Civ-accurate vertical ammo; flag FOLK |
| Free-place chaos on small screens | Caps + marked tiles |
| Clone-KR accusation | Historical choke grammar, four resources, ideology defeat, chronology-gated trees |

**Non-goals for defense systems v1:** full base-builder sim; player-drawn arbitrary mazes on every map; PvP tower mirror without separate design pass.

---

## 15. Build priority (defense track)

1. Prototype **S2 gate choke + T1/T4 + barracks** on one European pad ring.  
2. Add **resource quartet** with visible wage pressure.  
3. Implement **breach state machine** (outer → lists → inner).  
4. Port **Japan masugata** as second choke tutor (teaches trap court).  
5. Layer **heroes + Favor ult**.  
6. Skin shared archetypes per civ; add first unique each.  
7. Compress each civ’s 7-layer brief into mission sheets (S1–S4).  

---

## 16. Return block — core defense pillar list

1. Lane clarity  
2. Choke > carpet  
3. Hold + Shoot loop  
4. Layered HP without layered clutter (7→4+soft)  
5. Shared archetypes + sparse civ uniques  
6. Hybrid pads / limited free-place  
7. Gold · Wood · Stone · Favor roles  
8. Tag synergies  
9. Named gate as boss doorway  
10. Multiple defeat modes (HP / ideology / supply / wage)  

---

## 17. Cross-links

- Index: `docs/TOWER-DEFENCE-TARIHSEL-ARASTIRMA-INDEX.md`  
- Plan: `docs/_GDD_PLAN_NOTES.md` (A7)  
- Fort stacks: European / Japanese / Greek / Viking / Mongol fortification briefs (and civ Turkish masters)  
- Companion GDD research (when present): attack/waves, camera, monetization, retention  

---

*End of A7 defense-systems research. Balance numbers intentionally omitted; verbs, tags, and strata are the shippable grammar.*
