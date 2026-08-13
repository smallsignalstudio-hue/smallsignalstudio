# GDD — Attack / Wave Systems

> Design-research bible for **Historical Civ Tower Defence** (Babylon + Rome, Persia, Egypt, China, Maya–Mexica, Viking, Greece, Japan, Mongol, European Castle).  
> Source spine: civ research bundle `cursor/tower-defence-historical-research-bundle-2959`.  
> Evidence tags on historical hooks: **ARCH / CHR / LIT / MOD / FOLK** (never silent FOLK).  
> Scope: enemy taxonomy, modifiers, multi-lane & naval, signature events (esp. Mongol feigned retreat), difficulty, endless, optional PvP offense, fair RNG, **attacker-faction campaigns**.

---

## 0. Design thesis

Classic TD treats the attacker as a **scripted faucet**. This game treats the attacker as a **doctrine**.

| Axis | Classic mobile TD | Historical Civ TD |
|---|---|---|
| Path | 1–2 fixed lanes | Multi-lane + naval + traitor gates + terrain events |
| Enemy meaning | Colour/HP skins | **Roles** that counter tower jobs |
| Escalation | HP ×1.1 / wave | Doctrine packs + era tech + siege stamina |
| Boss | Big HP sponge | Named figure / campaign hinge with a **rule change** |
| RNG | Hidden spawn luck | **Declared** decks, seeds, and FOLK-tagged miracles |
| Player role | Always defender | Defender **or** attacker-faction campaigns |

**North star:** Every wave should answer *which historical pressure is on the wall today?* — not only *how much HP?*

---

## 1. Enemy role taxonomy (universal)

Roles are **mechanical jobs**. Civ skins map onto roles; they do not invent parallel rulesets.

| Role ID | Job vs defence | Typical counters | Fail if overused |
|---|---|---|---|
| **SWARM** | Overwhelm single-target DPS; clog choke | AoE, splash, fire, list kill-fields | Map becomes spam mush; no readable threat |
| **ARMORED** | Soak piercing; force armour-pen / fire / siege | Bolt, oil, heavy bolt, hero pierce | Armour meta freezes early acts |
| **FAST** | Leak past slow towers; punish bad placement | Slow, roots, chokepoints, horsemen response | Feels unfair if spawn RNG stacks them |
| **FLYER** | Ignore ground path / walls (where historically plausible) | Dedicated AA, nets, naval AA | Anachronistic “always flying units” |
| **SIEGE ENGINE** | Damage towers / gates / walls; slow but existential | Focus fire, sorties, mining counter, sticky slows | If too tanky, becomes mandatory every wave |
| **BOSS** | Rule-change event (aura, phase, lane open, politics) | Prep window, multi-phase plan, hero timing | Pure HP sponge = boring |
| **TRAITOR / SAPPER** | Open inner path, mine walls, flip a gate, spawn inside | Detection, loyalty meter, inner patrols, kill-courtyards | Softlocks if invisible forever |

### 1.1 Role → historical skins (examples, not TO&Es)

| Role | Rome | Persia | Egypt | China | Maya–Mexica | Viking | Greece | Japan | Mongol | European Castle |
|---|---|---|---|---|---|---|---|---|---|---|
| Swarm | Aux levy, skirmishers | Subject levy mass | Conscript / Nubian light | Crossbow ranks, peasant levy | Skirmisher clouds | Bondi / raiding parties | Light peltasts, rowers ashore | Ashigaru mass | Decimal light horse packs | Foot sergeants, levy |
| Armored | Legionary / testudo packs | Immortals / heavy spear | Chariot nobles (elite soak) | Cataphract / heavy crossbow line | Jaguar / eagle elites | Hirdmen / huscarls | Hoplite / phalanx block | Samurai / ō-yoroi elites | Heavy lancers (late) | Men-at-arms / plate late |
| Fast | Equites / Numidian | Horse archer screens | Chariot runners | Light cavalry, steppe allies | Fast capture teams | Ship-to-shore rush | Prodromoi / light horse | Mounted samurai | **Horse archer core** | Hobelars / chevauchée riders |
| Flyer* | Rare — artillery stones as “air hazard” | Rare | Solar-barque **FOLK** night raid | Rare / rocket **late FOLK** | Rare | Rare | Rare | Storm kami **FOLK** | Rare | Rare |
| Siege | Onager, ram, sap | Siege trains (late Achaemenid/Hellenistic pressure) | Siege after Megiddo-style field win | *Pao*, rams, gunpowder late | Limited (ladders, fire) — capture focus | Rare engines; fire + ladders | Helepolis, rams, causeway | Fire, mining, moat-fill | **Captured engineers** + trebuchet line | Trebuchet, ram, mine, bombard late |
| Boss | Consul / barbarian king | Great King / satrap | Pharaoh / Apophis **FOLK** | Warlord / dynasty hinge | Tlatoani / star-war captain | Jarl / Alfred-as-defender | Polemarch / king / Demetrius | Daimyō / league abbot | Qaghan / Subutai corps | King / Saladin / Llywelyn |
| Traitor/Sapper | Gate bribe, mine | Defecting satrapy | Temple politics / Delta fracture | Beacon false alarm, An Lushan flip | Elite capture teams as “inner threat” | Local collaborator | Ephialtes path | *Kuchiyaku* / betrayal flip | Subject artisans + inner agents | Mining crews, gate bribes, Roses flip |

\*Flyer is the **rarest** role. Prefer naval bypass, cliff paths, and sapper gates over fantasy air units unless tagged **FOLK** (kami, Apophis, onryō seasons).

### 1.2 Role verbs (implementation contract)

```
SWARM:   high count, low HP, low armour, leak score × count
ARMORED: armour ≥ threshold; slow; high keep damage if leaks
FAST:    move_speed high; prefers open lanes; weak to slow fields
FLYER:   path_layer = air; ignores walls; dies to AA tags
SIEGE:   targets STRUCTURE; low leak priority until structure HP low
BOSS:    event_id + phases[]; may spawn escorts; unique win/lose text
SAPPER:  can_toggle_lane | can_damage_wall_segment | can_spawn_inside
TRAITOR: faction_flip on loyalty ≤ 0; converts tower → enemy spawner
```

---

## 2. Modifiers (wave & map)

Modifiers stack **visibly** in the wave banner. Cap: **2 combat + 1 narrative** per wave on Normal; higher difficulties raise caps (see §6).

### 2.1 Combat modifiers

| ID | Effect | Historical flavour hooks |
|---|---|---|
| `ARMOUR_UP` | +armour on Armored/Boss | Plate eras, testudo, Immortals |
| `SWARM_TIDE` | +count, −HP slightly | Levy drafts, ashigaru, bondi |
| `FORCED_MARCH` | +speed, −HP | Chevauchée, Mongol remounts |
| `NIGHT` | Vision down; sapper buff | Night escalade, Apophis night raid **FOLK** |
| `WINTER` | Slow non-adapted; freeze river lanes | Rus’ winter campaigns **CHR** |
| `HEAT_JUNGLE` | Cavalry floor; disease DoT on attacker if attacker-campaign | Vietnam/Champa limit **CHR** |
| `FAMINE` | Defender spawn/repair caps down | Great Famine deck **CHR** |
| `PLAGUE` | Periodic civilian/tower crew loss | Athens plague **LIT** |
| `FIRE` | Structures take DoT; wooden keeps vulnerable | Japanese tenshu, Viking longphort |
| `GUNPOWDER` | Siege engines ignore vertical armour | Castillon / late HYW **CHR** |
| `STORM_SEA` | Naval lane hazard; sink chance declared | Japan 1274/81 — **not** auto-win; tag FOLK/MOD |

### 2.2 Narrative / political modifiers

| ID | Effect | Notes |
|---|---|---|
| `SUCCESSION` | Mid-map freeze or civil spawn | Mongol qaghan death; Viking king death |
| `REVOLT` | Internal spawners | Jacquerie; provincial Japanese rebellions |
| `BETRAYAL_FOG` | Ally DPS unreliable; delayed flank | Sekigahara **LIT/MOD** |
| `RELIGIOUS_ZEAL` | Morale aura; ignores some fear | Ikkō leagues; crusade vows |
| `TERROR_CLEMENCY` | Attacker offers surrender card | Mongol “surrender now” doctrine **CHR** |
| `DANEGELD` | Pay gold to skip wave → inflation later | Viking England **CHR** |
| `TRIBUTE_REVOLT` | Subject lanes flip if tyranny high | Delian League; Yuan |

### 2.3 Modifier rules

1. **Declare before build phase** (except Betrayal Fog telegraphs 1 wave early).  
2. **Never stack hidden spawn luck** with Forced March + Swarm Tide without a telegraph icon.  
3. FOLK modifiers (divine wind, kami, Apophis) require an on-screen **FOLK** chip.  
4. Attacker-campaign modifiers invert: defender AI gets Famine/Revolt; player gets Terror/Clemency tools.

---

## 3. Multi-lane architecture

### 3.1 Lane types

| Lane type | What it is | Design job |
|---|---|---|
| **Approach** | Main road / valley | Teach funnel + AoE |
| **Gate / barbican** | Kill-courtyard puzzle | Sapper vs trap courtyard |
| **Wall-walk / list** | Parallel path after breach | Reward layered defence |
| **Postern / traitor** | Conditional inner spawn | Loyalty / detection meta |
| **Resource / chevauchée** | Side lane that burns economy | Win without touching keep HP |
| **Naval** | River / harbour / beach | See §4 |
| **Relief** | Friendly army path (timer) | Race: hold until relief |

Maps declare **active lanes** per act. Concentric keeps (Europe, China wengcheng, Japan masugata) unlock inner lanes only after outer HP thresholds.

### 3.2 Multi-lane pressure patterns

| Pattern | Wave script | Civ examples |
|---|---|---|
| **Pin & flank** | Swarm front + Fast side | Mongol multi-column; Greek false retreat at Salamis (naval) |
| **Siege focus** | Siege engines on gate + Swarm soak | European castle; Xiangyang |
| **Economy raid** | Fast on resource lane while keep quiet | HYW chevauchée; Viking coastal |
| **Inner betrayal** | Traitor opens postern mid-boss | Thermopylae path; Sekigahara; Roses |
| **Staggered corps** | Lane A then B delayed | Mohi dawn multi-corps **CHR** |

### 3.3 Lane fairness

- Player can see **all spawn portals** that will activate this wave (fog only for Betrayal Fog’s *which ally flips*, not *whether* a flip can happen).  
- Simultaneous 4-lane opens are **Hard+** only, or boss phases.  
- Softlock ban: at least one legal tower footprint covers each lane within the era’s tech.

---

## 4. Naval lanes

Naval is a **first-class path layer**, not a skin.

### 4.1 Naval unit jobs

| Naval job | Maps to role | Behaviour |
|---|---|---|
| Landing craft / longship | Swarm/Fast delivery | Reach beach → spawn ground pack |
| War galley / trireme | Armored (afloat) | Contests harbour; shoots towers |
| Fire ship | Siege (anti-fleet / anti-dock) | One-shot dock HP or chain burn |
| Transport elite | Boss escort | Must die before boss lands |
| Harbour sapper | Sapper | Cuts chain boom / bridge tower |

### 4.2 Naval theatres (by civ)

| Civ | Naval signature | Fair-play note |
|---|---|---|
| **Viking** | River capitals, longphort overwinter, bridge towers (Paris 885–86) | Ship retreat = alternate win for attacker |
| **Greece** | Trireme narrows (Salamis), Long Walls keep-link | Land fail can force sea retreat (linked maps) |
| **Egypt** | Nile corridor, Delta fleets, Ptolemaic zenith | River = main logistics lane |
| **China** | Red Cliffs fire navy; Yamen last stand | Fire-ship = declared hazard, not RNG wipe |
| **Japan** | Dannoura tide arena; Hakata beach vs Mongol; Imjin denial | Storm = weighted seasonal table, FOLK-tagged |
| **Mongol** | Japan amphibious **fail specialty**; Song river war | Fleet supply meter; shore castles strong |
| **Europe** | Harbour sieges (Acre, Calais), river Seine locks | Naval supports starve clocks |
| **Persia** | Amphibious landings (Marathon as defender view) | Beach defence tutorial |
| **Rome** | Mediterranean projection; rare core loop | Optional act, not default every map |
| **Maya–Mexica** | Canoe / lake causeway (Tenochtitlan) | Causeway break = lane delete |

### 4.3 Naval rules

1. Beach landings create **temporary ground portals** — destroyable if ships burn first.  
2. Chain booms / fortified bridges are **buildables**, not lore text.  
3. Typhoon / storm rolls use **public weights** (see §8). Never “kamikaze button wins the campaign.”

---

## 5. Signature events & Mongol feigned retreat

### 5.1 Feigned retreat (Mongol signature event)

**Historical basis:** Open-field doctrine observed across Eurasia; Inner Asian toolkit, Mongol operational trademark in many **CHR/MOD** accounts — **not** a Mongol copyright, but their campaign signature in this roster.

**Event ID:** `FEIGNED_RETREAT`

| Phase | What player sees | Rules |
|---|---|---|
| 0 Telegraph | Banner: “Enemy breaks — pursue?” | 3s decision window on Normal+ |
| 1 Fake rout | Fast/Armored packs flee toward map edge | Towers still shoot; **sortie units** may chase |
| 2 Ambush | Hidden reserves enter from side lanes | If player chased past **lure line**, ambush spawns with +damage |
| 3 Resolve | If player held lure line: ambush weakened / gold bonus | If player over-chased: leak spike + structure chip |

**Teaching maps:** Kalka (tutorial boss), Liegnitz (knight-charge punish), Mohi (bridge control + multi-corps).  
**Counter-tech:** Mamluk “learned the trick” at ʿAyn Jālūt — late enemy factions can gain `IGNORE_FEIGNED` (no chase bait).  
**Attacker-campaign:** Player **triggers** feigned retreat as an ability with cooldown + remount cost.

### 5.2 Other universal event templates

| Event | Trigger | Effect |
|---|---|---|
| `SUCCESSION_FREEZE` | Boss/king death or calendar | Army freeze N seconds; civil fork |
| `RELIEF_ARMY` | Timer | Friendly lane opens; fail if keep dies first |
| `STARVE_CLOCK` | Long siege maps | Win by stores = 0 even if walls stand |
| `MOAT_FILL` | Attacker progress | Opens siege-engine lane |
| `GATE_BRIBE` | Traitor meter | Instant lane to list/keep |
| `ENGINEER_CAPTURE` | Kill sapper elite | Unlock next-era siege tech (attacker) |
| `SURRENDER_OFFER` | Terror meter high | Skip remaining waves for tyranny cost |

---

## 6. Difficulty modes

| Mode | HP/Count | Lanes | Modifiers | Telegraph | Notes |
|---|---|---|---|---|---|
| **Story** | 0.85 | 1–2 | 0–1 | Full | Teaches roles; feigned retreat auto-hints |
| **Normal** | 1.0 | 2–3 | ≤2+1 | Full | Default |
| **Hard** | 1.2 | 3–4 | ≤3+1 | Short | Simultaneous multi-lane allowed |
| **Legend** | 1.35 | Full map kit | Harsh decks | Minimal | Ironman optional |
| **Historical** | Variable | Era-locked | Era decks only | Full | No anachronistic gunpowder early |
| **Myth** | Cosmetic FOLK on | Same as Normal | FOLK bosses allowed | Full | Kami/Apophis/oni seasons |

**Historical mode law:** Unit eras follow campaign year. No plate at Hastings; no teppō at Genpei; no bombard at Krak’s first act.

---

## 7. Endless / Horde

Endless is a **post-campaign lab**, not the core fantasy.

### 7.1 Rules

1. **Seeded weekly mutators** from §2 (public seed).  
2. Escalation: every 5 waves add one role weight; every 10 waves add a Boss event from a civ deck.  
3. **Doctrine breaks** every 25 waves (feigned retreat, starve clock, naval landing, revolt).  
4. Scoring: leaks, keep HP, gold efficiency, “historical accuracy” bonus if no FOLK units on Historical Endless.  
5. Soft pity: after 3 brutal modifier rolls, next roll excludes `FAMINE+PLAGUE` double.

### 7.2 Civ endless decks

Players pick a **defender civ**; attacker deck rotates or is chosen (Mongol Horde, Great Heathen Army, Hellenic Siege Age, etc.).  
Boss every 10th wave is drawn from that deck’s signature boss table (§10).

---

## 8. Fair RNG

### 8.1 Principles

| Principle | Implementation |
|---|---|
| **No silent wipe RNG** | Storms, plagues, betrayals show probabilities |
| **Seeded runs** | Shareable seed; ranked uses server seed |
| **Pity / anti-spike** | No 3× Fast leak packs in a row on Story/Normal without telegraph |
| **Declared tables** | Storm season table for Hakata; famine deck weights for HYW |
| **FOLK labelled** | Divine wind ≠ physics; chip required |
| **Attacker abilities ≠ dice** | Feigned retreat is a skill, not a coin flip |

### 8.2 Example: Japan invasion storm table (public)

| Season weight | Storm severity | Game effect |
|---|---|---|
| Summer/autumn high | Light squall | −10% naval accuracy |
| Peak typhoon window | Heavy | Sink 10–25% of **unharboured** ships (rolled once, shown) |
| Off-season | None | 0% |

Defender still needs shore towers; storm never auto-clears a landed army.

### 8.3 Spawn composition RNG

Wave recipes are **authored mixes** with ±10% count jitter, not pure weighted random from full bestiary. Boss escorts are fixed lists.

---

## 9. PvP tower offense (optional mode)

**Recommendation:** Ship as **async Attack-the-Base** (Kingdom Rush / Clash-like), not realtime 1v1 lane spam at launch.

### 9.1 Mode: Siege Cards (async)

1. Player A builds a defence layout (budget + era cap).  
2. Player B spends an **attack budget** on wave cards (roles + 1 event).  
3. Simulation runs with **shared seed**; both see replay.  
4. Scoring: leaks + structure damage + time-to-breach.

### 9.2 Allowed attack cards

- Role packs: Swarm, Armored, Fast, Siege, Sapper (limited).  
- One Event card: Feigned Retreat, Gate Bribe, Naval Landing, Starve Pressure.  
- Boss cards: ranked/seasonal only (anti-whale: one boss slot).

### 9.3 Ban list (fairness / culture)

- Instant FOLK wipe (full kamikaze clear).  
- Invisible permanent traitors.  
- Anachronistic tech outside era playlist.  
- Pay-to-skip defender walls.

### 9.4 Why not realtime lane PvP at v1

Realtime needs netcode, disconnect rules, and toxic spawn-micro. Async preserves historical puzzle fantasy and replays for design QA.

---

## 10. Attacker-faction campaigns

### 10.1 Fantasy

Player is the **besieger / invader doctrine**, not the wall. Example headline: **Play as Mongols besieging a European castle** (Liegnitz–Mohi theatre or a generic concentric keep using European defence AI).

### 10.2 Core loop (attacker)

| Phase | Player verbs | Win metrics |
|---|---|---|
| Recon | Place scout camps; reveal lanes | Vision |
| Investment | Deploy role packs as “waves you author” | Pressure |
| Engineering | Unlock siege via captured artisans | Gate/wall HP |
| Doctrine | Feigned retreat, multi-column timing, terror/clemency | Break morale / breach |
| Politics | Succession risk; ally tumens | Don’t auto-lose to freeze |

**Inversion:** Defender is AI with tower build rules per civ. Player’s “lives” = **remounts / corps cohesion / qaghan mandate**. Lose if mandate breaks or siege stamina hits 0.

### 10.3 Mongol → European castle (set-piece design)

**Map kit:** Concentric keep (Beaumaris-like diagram) + open field approaches + optional frozen river.  
**Player tools:** Horse-archer Swarm/Fast, feigned retreat event, engineer unlock after first outer-town fall, winter buff.  
**Defender AI:** Barbican traps, list kill-field, sortie button, relief-army timer (Hungarian/Polish coalition flavour).  
**Teach:** Kiting knights into lure line; don’t frontal-spam siege until outer bailey cracked; watch succession abort event (1241–42 recall) as optional hard fail branch.

### 10.4 Other attacker campaigns (roster)

| Player attacker | Defender AI | Signature lesson |
|---|---|---|
| Mongol | European Castle / Jin / Song / Japan shore | Feigned retreat; engineer capture; naval limit |
| Viking | Frankish / Wessex burhs | Raid → overwinter → siege race vs Alfred relief |
| Rome | Hellenistic / Gallic oppida | Engineering grind; discipline vs rush |
| Persia | Greek poleis | Multiethnic swarm + beach landings; coalition fragility |
| Greece (Athens) | Subject islands / Syracuse | Naval empire; overseas supply death |
| Greece (Macedon) | Tyre / Asian keeps | Causeway + siege train |
| China (Qin/Han/Tang) | Rival *guo* / steppe | Quota war; walls vs horse |
| Egypt | Levant forts | Pursuit discipline after Megiddo; ditch siege |
| Japan (Sengoku) | Rival castles | Fire, betrayal, moat-fill Osaka |
| Europe (Plantagenet/Capetian) | Rival keeps | Mining + trebuchet + starve; chevauchée side win |
| Maya–Mexica | Rival city | Capture-priority “alive take” scoring |
| Babylon | Rival Mesopotamian / Elamite | Labour logistics; river lanes (prior research) |

### 10.5 Capture vs kill (Mexica special)

Attacker waves can tag units `CAPTURE_PREF` — scoring prefers living takes for prestige economy. Changes optimal tower (non-lethal nets / stun) and boss rules (tlatoani capture ≠ corpse).

---

## 11. Enemy taxonomy + signature events per civ

Quick-reference for content pipelines. Roles = primary mechanical read.

### 11.1 Babylon (Etemenanki — prior research)

| Roles emphasis | Signature events |
|---|---|
| Swarm labour levies; Armored chariot elites; Siege ramps; Boss priest-king; Sapper canal breach | Ziggurat labour revolt; river flood lane; ritual omen deck (**FOLK**-tagged) |

### 11.2 Rome

| Roles emphasis | Signature events |
|---|---|
| Armored legion blocks; Swarm auxiliaries; Siege (ram/onager/sap); Boss barbarian king / rival consul; Traitor gate politics | Testudo push; mining breach; civil-war tower flip; triumph vs sack fork |

### 11.3 Persia (Achaemenid)

| Roles emphasis | Signature events |
|---|---|
| Swarm subject levies; Armored Immortals; Fast horse archers; Boss Great King; Traitor satrap flip | Marathon beach (as landing); multiethnic morale fragility; royal aura loss on king damage |

### 11.4 Egypt

| Roles emphasis | Signature events |
|---|---|
| Fast chariots; Swarm conscripts; Nile naval; Boss pharaoh / Apophis (**FOLK**); Siege after field win | Megiddo loot-discipline fail; Kadesh mistiming; Assyria super-siege boss; Amarna schism revolt |

### 11.5 China

| Roles emphasis | Signature events |
|---|---|
| Swarm crossbow ranks; Armored elites; Siege *pao*/gunpowder late; Boss warlord; Traitor (An Lushan) | Changping annihilation logistics; Red Cliffs fire navy; Fei River contagion retreat; wengcheng kill-box; beacon false-alarm FOLK |

### 11.6 Maya–Mexica

| Roles emphasis | Signature events |
|---|---|
| Fast capture teams; Armored knight societies; Swarm skirmishers; Boss tlatoani; limited Siege | Flower-war prestige rules; causeway break; star-war season; captive scoring |

### 11.7 Viking / Norse

| Roles emphasis | Signature events |
|---|---|
| Fast ship-to-shore; Swarm bondi; Armored huscarls; Naval core; Boss jarl; collaborator Traitor | Lindisfarne tutorial raid; Paris bridge towers; Edington conversion treaty; Danegeld pay-skip; Stamford fatigue |

### 11.8 Greece

| Roles emphasis | Signature events |
|---|---|
| Armored hoplite/phalanx; Swarm light; Naval triremes; Siege helepolis; Boss king/Demetrius; **Traitor path** | Thermopylae betrayal lane; Salamis geography trap; Sicilian supply death; Tyre causeway; Leuctra formation editor |

### 11.9 Japan

| Roles emphasis | Signature events |
|---|---|
| Swarm ashigaru; Armored samurai; Fast cavalry; Siege fire/moat-fill; Boss daimyō / ikki; Betrayal Traitor | Hakata beach + storm table; Dannoura tide; Nagashino gun-line; Sekigahara loyalty fog; Osaka moat-fill |

### 11.10 Mongol

| Roles emphasis | Signature events |
|---|---|
| Fast horse archers; Swarm decimal packs; Siege via **captured engineers**; Boss qaghan/Subutai; Sapper artisans; rare Naval (weak) | **Feigned retreat**; Mohi bridge multi-corps; succession freeze; Xiangyang long siege; Japan fleet fail; ʿAyn Jālūt anti-feint |

### 11.11 European Castle (~1000–1500)

| Roles emphasis | Signature events |
|---|---|
| Swarm levy; Armored men-at-arms; Fast chevauchée; Siege trebuchet/ram/mine/bombard; Boss king/resistance prince; Sapper miners; Traitor Roses flip | Gaillard mining fall; Edwardian ring vs revolt waves; Calais starve; Orléans saint-banner (**CHR**≠magic); Castillon gun coda |

---

## 12. Wave authoring pipeline (practical)

1. Pick **defender civ + act year** → lock era tech.  
2. Pick **attacker doctrine deck** (often another civ).  
3. Author 8–12 waves: teach role → mix → siege pressure → event → boss.  
4. Assign ≤ modifier cap; telegraph icons.  
5. Playtest leak causes: if Fast unexplained, fix placement tools — don’t buff HP.  
6. Tag every FOLK beat.  
7. Export seed + recipe JSON for Endless reuse.

### 12.1 Minimum teach order (FTUE)

1. Swarm only  
2. Armored intro  
3. Fast leak  
4. Multi-lane pin & flank  
5. Siege engine vs gate  
6. Sapper detection  
7. Boss + event (feigned retreat or betrayal — civ-appropriate)  
8. Naval landing (if civ has sea)

---

## 13. Anti-patterns (do not ship)

1. Boss = HP sponge with no rule change.  
2. Invisible traitors without detection gameplay.  
3. Divine wind / miracle as unpaid win button.  
4. Four lanes + Forced March + Swarm Tide on Story.  
5. Flyer spam in Bronze/Iron age without FOLK label.  
6. Equating all steppe / all “Oriental” attackers into one deck.  
7. PvP pay-to-win boss cards.  
8. Attacker campaign that is only “place spawners” with no engineering/politics.  
9. Silent anachronism (bombards in Act I Krak).  
10. RNG that cannot be seeded or explained in the UI.

---

## 14. Acceptance checklist

- [ ] Every enemy prefab has a **Role ID**  
- [ ] Every wave banner lists modifiers + lane plan  
- [ ] Feigned retreat implemented as phased event with lure line  
- [ ] Naval path layer exists for at least Viking, Greek, Japan, China, Egypt maps  
- [ ] Historical difficulty era-locks tech  
- [ ] Endless uses public weekly seed  
- [ ] One attacker-faction vertical slice: **Mongol vs European Castle**  
- [ ] Async Siege Cards prototype behind flag (optional)  
- [ ] FOLK chips on myth modifiers  
- [ ] Civ signature event table wired to campaign acts (§11)

---

## 15. Sources / cross-links

- Bundle index: `docs/TOWER-DEFENCE-TARIHSEL-ARASTIRMA-INDEX.md` (research branch)  
- Especially: Mongol / European Castle / Japanese / Viking / Greek research briefs (signature battle tables §4 in each)  
- Feeds mega design doc: `docs/HISTORICAL-CIV-TOWER-DEFENCE-OYUN-TASARIMI.md` (planned)  
- Sibling GDD slices: defense systems, camera, monetization, retention, FTUE, live ops, risks

---

*End of GDD-ATTACK-WAVES. Roles are universal; doctrines and signature events carry civilization identity. Fair RNG is declared RNG. Attacker campaigns invert the fantasy without abandoning tower-defence clarity.*
