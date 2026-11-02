# VIKING-AGE FORTIFICATIONS AND DEFENCE — Tower-Defence Game Bible

> Dense research notes for **keep / map / layer** design across Scandinavian and diaspora fortifications (~750–1100 CE conventional “Viking Age”).  
> **Method:** Prefer measured archaeology and contemporary chronicles. Flag sagas and modern folklore. **No invented stone curtain castles** as the default Norse keep.  
> Companion briefs: `VIKING-AGE-MILITARY-UNITS-AND-TACTICS.md`, `VIKING-TOWER-DEFENCE-RESEARCH.md`.

### Evidence tags (use throughout)

| Tag | Meaning |
|---|---|
| **ARCH** | Excavation, survey, dendrochronology, geoarchaeology, UNESCO nomination data |
| **CHR** | Near-contemporary chronicles / annals (Anglo-Saxon Chronicle, Annals of Ulster, Frankish annals, Abbo, Asser, *Vita Anskarii*) |
| **SAGA** | Later Norse saga / kings’ saga tradition — literary, often centuries after events |
| **MOD** | Modern scholarly consensus or active debate |
| **FOLK** | Popular culture / tourist myth (horned helmets, “berserker army,” MCU aesthetics) — flavour only |

**Design rule:** Prefer **ARCH/CHR** for map geometry and layer stacks. Use **SAGA** for named heroes and drama. Never let **FOLK** overwrite ditch profiles or dates.

**Chronology note:** Absolute CE years for undated earthworks are soft. Dendro dates (Trelleborg timber felled autumn 980 → build spring **981**) are hard anchors **[ARCH]**. Irish longphort foundation years are annalistic **[CHR]** and may lag actual first use.

---

## 0. What “Viking fortification” is — and is not

### 0.1 Is
- **Turf–timber ramparts** with timber cladding / palisade walks **[ARCH]**
- **Pointed-bottom dry ditches** (not wet moats by default) **[ARCH]** Trelleborg, Fyrkat, Nonnebakken
- **Cardinal gates** on geometric ring forts **[ARCH]** UNESCO 2023 set
- **Harbour / pile barriers**, river-fork banks, promontory use of marshes and cliffs **[ARCH]** Hedeby, Birka, longphorts
- **Linear border earthworks** (Danevirke / Kovirke) **[ARCH]**
- **Ideological enclosures** (Jelling palisade + mounds + stones) **[ARCH]**
- Short-lived **royal state projects** (~970s–980s) vs multi-century emporium banks **[MOD]**

### 0.2 Is not (default TD keep)
- Concentric **European high-medieval stone curtain castles** with corner keeps — those are later / different tradition **[MOD]**
- Permanent “Viking empire capital wall” identical from Dublin to Birka — local forms dominate **[ARCH]**
- Horned-helmet gate guardians **[FOLK]**
- Ring forts as proven **barracks for the conquest of England** — chronology disproves the classic version of that claim (see §1.5) **[ARCH][MOD]**

### 0.3 Geographic frame
“Viking” here = age / activity label, not one polity. Fortification practice differs among:
- **Danish royal monuments** (Jelling dynasty ring forts, Danevirke phases, Hedeby link)
- **Swedish emporium + rare VA hillfort** (Birka)
- **Norwegian fjord / cliff sites** (mostly earlier Iron Age *bygdeborger*; VA use must be argued case-by-case) **[MOD]**
- **Diaspora bases** (Irish longphorts → towns; Frankish river war as *attacker* scenario)

---

## 1. Trelleborg-type ring fortresses (UNESCO 2023 Danish set)

**Inscription:** *Viking-Age Ring Fortresses* — Aggersborg, Fyrkat, Nonnebakken, Trelleborg (Slagelse), Borgring — UNESCO WHL **2023** **[ARCH]**  
**Builder context:** Reign of **Harald Bluetooth (Harald Blátǫnn) Gormsson** (~958–c.987) **[CHR]** Jelling stone claim; **[ARCH]** association via dating + geometry.  
**UNESCO synthesis:** Built ~**970–980 CE**; uniform geometric, scalable design; four gateways near cardinal points; usually concentric ditch; axial streets + ring street; longhouses in four quadrants where fully built **[ARCH]**.

### 1.1 Shared design grammar (measured where known)

| Feature | Attested pattern | Evidence |
|---|---|---|
| Plan | Strict circle; gates ≈ N–S–E–W | **[ARCH]** all five |
| Unit | Often analysed in “Trelleborg foot” ~**29.5 cm** (Roman-foot tradition debated) | **[MOD]** Sindbæk / Olsen–Schmidt |
| Rampart | Turf / earth core, timber faces, walkway | **[ARCH]** Fyrkat, Trelleborg, Borgring |
| Ditch | Pointed profile; **usually dry**; berm between ditch and rampart | **[ARCH]** Trelleborg, Fyrkat, Nonnebakken |
| Interior | Cross of timber-paved axial roads; optional ring street | **[ARCH]** Trelleborg, Fyrkat, Aggersborg; **absent / unproven** at Borgring interior |
| Houses | Curved-wall longhouses in square “blocks” (4×4 per quadrant at Aggersborg = 12 houses/quadrant) | **[ARCH]** |
| Lifespan | Short: little maintenance; often <20 years, sometimes ~a decade | **[ARCH][MOD]** |

### 1.2 Comparison table — five UNESCO forts

| Site | Inner Ø (approx.) | Outer Ø / notes | Rampart width (base) | Rampart height (recon./est.) | Ditch | Gates | Longhouses | Dendro / date |
|---|---|---|---|---|---|---|---|---|
| **Aggersborg** (Limfjord) | **240 m** | Largest; ~800 tf courtyard tradition | ~**9–11 m** (site texts ~9 m; tables often 11 m) | ~**4 m** turf body + timber | Concentric ditch present in system | **4 cardinal**; **protruding front gates** special | **48** houses (~**32 m**) in 12 squares | Late **970s**; slightly younger than Fyrkat in relative schemes |
| **Trelleborg** (Slagelse) | **~136–137 m** | Courtyard ~450 tf / 132.75 m in late phase | ~**17.5–19 m** (widest) | ~**5 m** (reconstructions sometimes ~6 m) | E side: berm ~**5 m**; ditch ~**17 m** wide × **~4 m** deep, pointed, **dry**, palisade in ditch base | **4**; stone-lined interiors | **16** inside (~**29.4 m**) + **~14–15** in unique **outer bailey** (~**26.3 m**) | Timber felled **autumn 980** → build **spring 981** |
| **Fyrkat** (Onsild Å) | **120 m** | | **12–13 m** (~36 tf without street) | Outer wall ~**4 m** presumed | NE: pointed ditch **7–8 m** wide, **<2 m** deep; W/SW unfinished; river/marsh elsewhere | **4**; possible W gatehouse | **16** houses (~**28.5 m**; mid-width ~7.5 m) | ~**980**; may be slightly older than Trelleborg |
| **Nonnebakken** (Odense) | **~120 m** | Urban-covered; ditch outer edge = property | Rampart poorly preserved above ground | — | Pointed ditch observations: ≥**4 m** wide × **~2 m** deep; other cuts **8 m×~4 m**, **11 m×~3 m**; berm up to **10–12 m** | **4** (layout) | Interior structures indicated by georadar/excavation after earlier doubt | ~**980** (UNESCO 970–980 band; oak spade dendro from “functioning” phase) |
| **Borgring** (Køge Å) | Courtyard ~**118 m** if 9-tf ring street | **Outer Ø 144.5 m** exact circle | Rampart **~10.6 m** (36 tf) | Front may reach ~**3 m** earth + palisade **~1.5–1.8 m** | Incomplete / terrain-dependent; SE without ditch | **4**; each ~**4.6–4.8 m** wide × **~10.6–10.7 m** long, roofed | **No Trelleborg-type house blocks found** despite large sample of interior | Wiggle-matched **¹⁴C** → late 10th c.; gates burned (not clearly external assault) |

Sources: UNESCO OUV + nomination metrics **[ARCH]**; Nørlund / Olsen–Schmidt / Roesdahl–Sindbæk / Christensen et al. Borgring 2021 **[ARCH][MOD]**; aggersborg.com site synthesis **[ARCH]** (popular but tied to museum research).

### 1.3 Site-by-site notes for TD maps

#### Aggersborg — military mega-ring
- Controls **Limfjord** choke / crossing landscape **[ARCH]**
- **48** longhouses = densest known barracks-scale capacity in the set **[ARCH]**
- Pre-fort **trading settlement** under the ring; post-abandonment royal estate continuity **[ARCH]**
- Central crossing of axial streets: evidence interpreted as a **tower** at the cross **[ARCH]** (site presentation) — excellent mid-map “cross tower” if labelled as reconstruction-level
- **Protruding gate fronts** = unique kill-funnel geometry vs simpler gates **[ARCH]**

#### Trelleborg (Slagelse) — best-excavated classic
- Promontory between **Tude Å** and **Vårby Å**; seaward link toward Storebælt ~3 km via Tude Å **[ARCH]**
- Unique **bailey** with radial longhouses pointing to ring centre **[ARCH]**
- Combat traces reported in museum synthesis: arrows in rampart, burned gates, mass graves of fighting-age men **[ARCH]** (interpret as short violent end, not perpetual war)
- Capacity estimates in older literature ~**1,300** people for whole complex — order-of-magnitude only **[MOD]**

#### Fyrkat — river-marsh hybrid defence
- Plateau / ness above Onsild Å; unfinished ditch shows **labour rationing** or aborted completion **[ARCH]**
- ~**10,000 m³** turf in rampart fill cited in excavation summaries **[ARCH]**
- Cemetery NE of fort **[ARCH]** — narrative layer, not wall layer
- Burned; not rebuilt **[ARCH]**

#### Nonnebakken — urban ghost fort
- Same diameter class as Fyrkat (**120 m**) **[ARCH]**
- Later nunnery / city fabric erase surface drama — TD map must “reconstruct” from ditch cuts **[ARCH]**
- Georadar revived interior structure debate after earlier “empty” conclusions **[ARCH][MOD]**

#### Borgring — geometry without house city
- Built on **steep relief** (~8% grade); **>1,900 m³** levelling fill to force the circle into the valley **[ARCH]**
- Gate fires: forensic/archaeological reading leans against simple external storming narrative **[ARCH]**
- Strategic lesson: ring forts are **surveyed statements + choke control**, not always filled barracks cities **[MOD]** Sindbæk et al.

### 1.4 Dendro / chronology band
| Anchor | Date | Tag |
|---|---|---|
| Trelleborg oak | Felled **autumn 980**; construction **981** | **[ARCH]** dendro |
| UNESCO group | ~**970–980** | **[ARCH]** nomination |
| Natmus framing | **970–980**, Harald Bluetooth; pressure context vs **Otto II** | **[MOD]/CHR]** |
| Relative order (classic) | Fyrkat ≈ slightly older; Aggersborg ≈ slightly younger | **[MOD]** |
| Occupation | Short; little repair; abandonment often before ~1000 | **[ARCH]** |

### 1.5 Purpose debate — what is disproven vs open

| Hypothesis | Status | Why |
|---|---|---|
| Barracks built for **Sweyn/Cnut conquest of England** | **DISPROVEN as primary dating fit** | Forts peak ~**980**; major English conquest under Sweyn/Cnut is **early 11th c.**; forts already short-lived / abandoned **[ARCH][MOD]** |
| Training camps for “Great Army 2.0” | Unproven | No textual naming of the forts **[CHR]** silence; short use |
| **Royal control / surveillance** of land–sea routes | Strong **[MOD]** reading | UNESCO OUV: routes, topography, centralized power |
| **Refuge + reinforcement hubs** (Sindbæk) | Plausible **[MOD]** | Local shelter while king’s force moves |
| Administrative / muster / tax nodes | Open **[MOD]** | Fits state-formation narrative; hard to prove per house |
| Purely symbolic geometry | Incomplete alone | Real ditches, burned gates, arrows at Trelleborg = functional defence too **[ARCH]** |

**Game bible stance:** Treat ring forts as **Harald’s Burgenpolitik** — military architecture *and* state brand — not as “England invasion shipyards.”

---

## 2. Danevirke linear earthwork + Kovirke

### 2.1 System overview
- Crosses the **Schleswig isthmus** (narrowest Jutland neck), linking marsh / Treene approaches in the west to Schlei / Baltic side in the east **[ARCH]**
- Total Danevirke complex length often cited ~**30–32 km** of walls/trenches (serial components, not one continuous ribbon of identical height) **[ARCH]** UNESCO Hedeby–Danevirke
- Combines **earthen ramparts, palisades, ditches, stone facing, brick (later), and offshore timber work** in the Schlei **[ARCH]**

### 2.2 Named segments (UNESCO / research concordance)

| Segment | Approx. length | Role |
|---|---|---|
| **Crooked / Curved Wall** (west) | ~**7.5 km** | Earthen rampart toward Treene marshes |
| **Main Wall** (*Hovedvolden*) | ~**5.5 km** | Most rebuilt; ditches; stone/brick facing phases |
| **North Wall** | ~**1.5 km** | Lake Dannewerk → Schlei flats |
| **Connection Wall** (*Verbindungswall*) | ~**4 km** | Links Danevirke line to **Hedeby** semicircle |
| **Kovirke** (*Kograben*) | ~**6.5 km** | Straight southern forward line |
| **East Wall** | ~**5.5 km** total (sections) | Schwansen / Baltic side |
| **Offshore work** | ~**670 m** cribwork/posts | Schlei shallows east of Hedeby |

### 2.3 Phasing (simplified, evidence-led)

| Phase | Date anchors | Works | Evidence |
|---|---|---|---|
| Early earthworks | **¹⁴C** pushes some activity earlier than classic 8th-c. story (debates from ~500s / 7th c.) | Simple soil banks | **[ARCH]**; interpretation contested **[MOD]** |
| Major timber/stone Main Wall | Dendro cluster around **737** | Palisade + later stone reinforcement traditions | **[ARCH]** |
| Godfred (Gudfred) wall tradition | **808** Frankish Royal Annals | Wall across Jutland neck with a gate | **[CHR]**; equation with specific rampart (often Kovirke debate) **[MOD]** |
| Hedeby semicircle + Connection Wall | Mid–late **10th c.**; Connection Wall dendro samples **951–61**, **968** | First hard link of emporium into Danevirke system | **[ARCH]** |
| **Kovirke** | Late **10th c.** (Harald Bluetooth horizon in many readings) | Straight 6.5 km line south of older Danevirke, protecting Hedeby approaches | **[ARCH][MOD]** |
| Valdemar brick wall | ~**1170** | Brick facing on Main Wall ≥~4 km | **[ARCH]** — **post-Viking**; do not use as VA default skin |

### 2.4 Connection to Hedeby
- Until 10th-c. Connection Wall, Hedeby could sit **south of** the main defensive claim line — politically awkward **[MOD]**
- Linking Hedeby = claiming the emporium inside the Danish frontier system (defence + legal/fiscal symbolism) **[MOD]** Dobat / Danevirke project
- **Kovirke** adds a forward southern screen specifically relevant to Hedeby’s land approaches **[ARCH]**

### 2.5 TD use
- Best as a **campaign map linear wall** (wave spawns from south / Frankish–Saxon side) rather than a circular keep.
- Gate excavation (2010–2014 Danevirke project) = choke-point boss lane **[ARCH]**.

---

## 3. Hedeby / Haithabu and Birka town defences

### 3.1 Hedeby (Haithabu)

| Element | Measured / attested facts | Tag |
|---|---|---|
| Semicircular rampart (*Halbkreiswall*) | Arc ~**1,300 m** enclosing landward sides; Schlei = open water side | **[ARCH]** |
| Height | Survives up to ~**6 m** walkable; geoarchaeology cites max construction heights toward ~**10 m** locally | **[ARCH]** |
| Volume | Order **~100,000 m³** earthwork material estimated in geoarchaeology | **[ARCH]** |
| Date of land rampart | Mid / second half **10th c.** (multi-phase) | **[ARCH]** |
| Harbour | Expanded **880s**; heavy landing stages; wooden harbour works | **[ARCH]** |
| Offshore barrier | ~**670 m** timber cribwork/posts in Schlei | **[ARCH]** |
| Hillfort | Nearby hillfort component in UNESCO serial property | **[ARCH]** |
| Population order | Often estimated **1,500–2,000** at height — model, not census | **[MOD]** |
| End | Burned / abandoned mid-11th c. tradition (**~1050**) | **[CHR]/[ARCH]** |

**Defence logic:** Water front + semicircle bank + Danevirke link + offshore stakes = **emporium lock**, not a free-field castle.

### 3.2 Birka (Björkö, Lake Mälaren)

| Element | Facts | Tag |
|---|---|---|
| Settlement (“Black Earth”) | ~**12 ha**; active ~mid-8th → late 10th c. | **[ARCH]** |
| Town rampart | Width ~**7–12 m**, height at least ~**2 m**; openings may have held timber towers | **[ARCH]** |
| Moat / ditch | Sections of town rampart confirmed with defensive ditch (2021 Stockholm Univ. work) | **[ARCH]** |
| **Borg** hillfort | Semicircular / oval rampart ~**350 m** long, **2–3 m** high, **7–8 m** thick (some sections reported **8–15 m** wide); cliff side to former shoreline | **[ARCH]** |
| Borg structure | Hollow rampart + wooden parapet/battlements (1996 section analysis) | **[ARCH]** |
| Borg phasing | Early rampart burns early **9th c.**; later stronger rampart fires late **10th–early 11th** | **[ARCH]** |
| Garrison | Terrace complex between Borg and water; warriors’ hall; weapons | **[ARCH]** |
| Harbour denial | **Pile barricades** limiting water approaches | **[ARCH]** + **[CHR]** early descriptions |
| Refuge narrative | *Vita Anskarii*: townsfolk flee to a fort in Anund’s attack | **[CHR]** |

**Dating caution:** Most Mälar / Swedish **hillforts** are Migration Period; **Birka Borg is the rare clear Viking-Age reuse/build** of that morphology **[MOD]** (“Viking Age paradox” of relatively few forts).

### 3.3 Pairing for TD
| Map | Feel |
|---|---|
| Hedeby | Trade-town semicircle + Danevirke umbilical + harbour stakes |
| Birka | Island emporium: water piles → town bank → garrison slope → Borg summit keep |

---

## 4. Longphorts (Ireland): Dublin, Waterford, Limerick

### 4.1 Term and form
- Irish **longphort / longphuirt** = ship-harbour fortress / shore fortress used in annals from **840s** **[CHR]** (*Annals of Ulster*; cf. Frankish notices)
- Typical landscape: **river confluence / tidal point**, D-shaped or banked enclosure, earthen bank + ditch, fleet protection **[ARCH][MOD]** Kelly / Sheehan / Simpson debates
- Not all longphorts become towns; many last one–few seasons **[CHR][ARCH]**

### 4.2 Named urban trajectories

| Site | Annalistic anchors | Defence / landscape | Evolution |
|---|---|---|---|
| **Dublin** (*Duiblinn / Áth Cliath*) | Longphort **841**; expelled **902**; return **917** | Black Pool / Liffey; banks on ridge; later successive **palisaded earthen banks** (10th then 11th c.) encircling settlement **[ARCH]** | Longphort → Hiberno-Norse town → later stone walls (post-core VA) |
| **Waterford** | **914** settlement tradition | Suir estuary; gateway archaeology later | Town; nearby **Woodstown** = elongated **D-shaped** enclosure with Scandinavian finds (weights, hack-silver, weapons) — likely earlier fortified base **[ARCH]** |
| **Limerick** | **922** (Uí Ímair horizon in many narratives) | Shannon fork / estuary logic | Becomes enduring Norse-Irish town **[CHR][ARCH]** |

### 4.3 Archaeological caution
- Ninth-century Dublin longphort exact footprint debated; later medieval streets fossilize bank lines **[MOD]**
- Woodstown shows the **military-economic base** type more clearly than mythic “circular Danish twin” plans **[ARCH]**
- Game maps should show **mud, tidal flats, stake lines, river fork kill-zones** — not stone concentric castles

### 4.4 TD angle
Longphort maps = **attacker-turned-settler** bases: beach/river spawn denial first, then bank, then urban densification unlocks.

---

## 5. Jelling complex — ideology keep candidate

### 5.1 Components (UNESCO + expansion archaeology)

| Element | Measurements / facts | Tag |
|---|---|---|
| North & South mounds | Diameters ~**70 m**; height up to ~**11 m**; turf-built truncated cones | **[ARCH]** |
| Great rune stone (Harald) | Between mounds; claims Denmark+Norway + Christianization of the Danes | **[ARCH]** inscription |
| Smaller stone | Gorm/Thyra memorial tradition; moved historically | **[ARCH]** |
| Church | Medieval stone over ≥**3** earlier timber buildings | **[ARCH]** |
| Ship setting | Monumental stone setting; north tip recovered | **[ARCH]** |
| **Palisade** | Rhombus ~**358–360 × 358–360 m**; area ~**12.5–12.7 ha** | **[ARCH]** |
| Palisade build | Vertical oak planks in trench **1–1.2 m** deep; round posts every ~**1.25 m** both sides; height estimated **>3 m** | **[ARCH]** |
| Gate | One confirmed opening ~**2 m** with flanking timber building; other gates probable but under town | **[ARCH]** |
| Interior houses | Trelleborg-type large houses inside enclosure | **[ARCH]** |
| Dendro | Palisade oak felled **958–985**; one sample ~**968** | **[ARCH]** |
| Geometry | North mound at diagonal crossing centre of rhombus | **[ARCH]** |

### 5.2 What Jelling is for design
- Not a ring fort barracks city
- A **royal-ideological precinct**: burial memory, conversion propaganda, geometric enclosure, elite halls
- Perfect **“brand keep”** / final sanctum while Aggersborg/Trelleborg supply the **military geometry keep**

### 5.3 Linked infrastructure
- **Ravning Enge bridge** (Harald-horizon timber bridge on land route toward Jelling) — logistics spine, not a wall **[ARCH]**
- Same dynastic package as ring forts + Danevirke 10th-c. work **[MOD]**

---

## 6. Norwegian mountain/cliff forts and Swedish forts (dating care)

### 6.1 Norway — *bygdeborger*
- ~**450** known hillforts nationally; dense in SE (e.g. Vingulmark ~**109**) **[ARCH][MOD]**
- Main use horizon for dated sites: **Roman Iron Age–Migration Period (~AD 200–550)** **[MOD]**
- Western Norway fjord forts: control seaways, refuge, elite association — mostly **pre-Viking** dated samples **[MOD]**
- **Do not** paste every cliff fort into a 980 CE Harald map without site-specific dates

### 6.2 Sweden — hillfort paradox
- ~**1,000** hillforts in Sweden broadly; Mälar region ~**500** — overwhelmingly earlier **[MOD]**
- Viking Age often looks relatively **“fort-poor”** in eastern Scandinavia except emporia and special sites **[MOD]** Hedenstierna-Jonson et al.
- **Birka Borg + garrison + piles** = the showcase VA Swedish fortified complex **[ARCH]**
- Scanian ring-like sites (**Borgeby**, **Trelleborg/Trelleborgen**) discussed as related/possible cousins; dating and full Trelleborg-type status debated — **not** in the 2023 Danish UNESCO five **[MOD]**

### 6.3 TD guidance
Use Norwegian cliffs as **optional early-age or regional maps** with an on-screen dating caveat. Default late-10th-c. Danish royal campaign should centre ring forts + Jelling + Danevirke/Hedeby.

---

## 7. Anglo-Saxon burhs (Alfred) — anti-Viking defence (reverse TD)

### 7.1 System
- After Edington **878**, Alfred’s Wessex develops a network of **burhs** (fortified towns/forts) **[CHR]** Asser / ASC; **[MOD]**
- Documented in the **Burghal Hidage** (list often copied in Edward the Elder’s reign but reflecting Alfredian system) **[CHR]/[MOD]**
- Spacing ideal: no settlement more than ~**20 miles** from a burh **[MOD]**

### 7.2 Manpower formula (Hidage)
- Assessment ties **hides** to wall length: commonly analysed as **1 man per hide**, with wall-length correspondence used to check archaeological perimeters **[MOD]**
- Large burhs: e.g. Winchester / Wallingford **2400** hides; Southwark **1800**; Wareham **1600** (list values) **[CHR]**

### 7.3 Archaeology of form
- Reused **Roman walls** + added ditches
- **New earth-and-timber** circuits
- Later “double burhs” across rivers with bridges restricting Viking ship movement inland **[MOD]** Brookes et al.
- Strategic offensive as well as refuge: bases for field armies **[MOD]**

### 7.4 Reverse-map design
| Layer | Burh side (defender AI or player flip) |
|---|---|
| Approach | Road/river corridor denial |
| Outer ditch + bank | Earth-timber (or Roman stone face) |
| Gatehouses | Urban choke |
| Interior | Militia muster / mint / refuge population |

Playable fantasy: **Viking player assaults Wareham / London bridge / Rochester** using longship lanes; English player is the “tower defence” historically.

---

## 8. Frankish defences / Seine bridges vs 885–886 Paris siege

### 8.1 Policy background
- Charles the Bald’s **Edict of Pîtres (864)** and bridge policy: fortified bridges to block ship ascent **[CHR]**
- Example: **Pont-de-l’Arche** complex (860s) — bridge between fortified heads **[CHR][MOD]**

### 8.2 Paris 885–886 — attested defence anatomy
| Feature | Detail | Tag |
|---|---|---|
| City core | **Île de la Cité** | **[CHR]** |
| Bridges | Low **stone Grand Pont** (N) + **wooden Petit Pont** (S) blocking ship passage | **[CHR]** Abbo |
| Bridgehead towers | Towers/chatelets at outer ends (Grand Châtelet unfinished stone; Petit wooden) | **[CHR]** |
| Commanders | Count **Odo**; Bishop **Gozlin**; allies | **[CHR]** |
| Primary narrative | Abbo of Saint-Germain, *Bella Parisiacae urbis* (eyewitness poem) | **[CHR]** |
| Fleet numbers | Abbo: **700** ships / **30–40k** men — scholars treat as **gross exaggeration**; modern guesses often ~**300** ships | **[CHR]** vs **[MOD]** |
| Garrison claim | Abbo: ~**200** men-at-arms initially — also rhetorical | **[CHR]/[MOD]** |
| Tactics | Assault on towers; ship archery; fire, oil/wax/pitch sorties; engines | **[CHR]** Abbo (literary siege colour) |
| Crisis | Flood wrecks Petit Pont; isolated tower falls | **[CHR]** |
| End | Charles the Fat pays / redirects Vikings (Burgundy raid permission tradition) rather than annihilating | **[CHR]** |

### 8.3 TD use
- Best **attacker scenario**: player is Viking wave commander vs bridge-tower defence.
- Or “last stand” Frankish map: two bridge lanes + island core.
- **Do not** treat Abbo’s headcounts as simulation truth.

---

## 9. Recommended TD centerpiece + alternatives

### 9.1 Preferred dual centerpiece

| Role | Site | Why |
|---|---|---|
| **Military keep** | **Aggersborg** *or* **Trelleborg (Slagelse)** | Measured geometry; 4-gate funnels; turf-timber walls; UNESCO clarity; short violent biography |
| **Royal-ideological brand** | **Jelling** | Mounds + Christ stone + >3 m oak rhombus palisade + halls — “why we fight” sanctum |

**Default pairing recommendation:** **Trelleborg** (best excavation storytelling + bailey) **+ Jelling** (brand). Use **Aggersborg** when you want max scale / 48-house barracks spectacle / fjord approach.

### 9.2 Alternatives — pros / cons

| Candidate | Pros | Cons |
|---|---|---|
| **Aggersborg** | Largest Ø; 48 houses; Limfjord drama; gate protrusions | Less “classic photo” bailey story than Trelleborg |
| **Trelleborg** | Dendro 981; ditch metrics; bailey; combat traces | Smaller than Aggersborg |
| **Fyrkat** | Clear house plan; unfinished ditch = gameplay “incomplete upgrade” | Smaller; less unique exterior |
| **Borgring** | Exact survey story; burned gates; terrain engineering | Empty interior — harder “barracks fantasy” |
| **Nonnebakken** | Same class; urban mystery | Poor surface readability |
| **Hedeby + Danevirke** | Epic linear+town system; harbour stakes | Diffuse “keep”; more campaign than single arena |
| **Birka** | Island layers; Borg+garrison+piles; *Vita Anskarii* colour | Swedish/emporium, not Harald ring grammar |
| **Dublin longphort** | Diaspora identity; river-fork | Footprint debates; later medieval overlay |
| **Paris bridges** | Famous siege; dual lanes | Player is usually the *attacker*; Frankish not Norse keep |
| **Alfred burh** | Perfect anti-Viking mirror | Enemy architecture pack |

---

## 10. Grounded ~7-layer defence stack

Compose from **attested** features only. This is a **synthetic stack for one playable “royal Danish” arena**, not a claim that every site had all seven.

| Layer | Attested feature sources | TD function |
|---|---|---|
| **1. Sea / fjord approach** | Limfjord at Aggersborg; Schlei at Hedeby; Mälar approaches at Birka; Storebælt via Tude Å | Naval spawn lanes; fog-of-war on water |
| **2. Beach / landing denial** | Harbour piles (Birka, Hedeby offshore work); longphort stake/mud approaches; low bridges as *enemy* tech (Paris/Pistres) | Slowing debuffs; destroyable stake fields |
| **3. Outer ditch** | Trelleborg 17×4 m pointed dry ditch; Fyrkat 7–8 m; Nonnebakken multi-cut; Birka moat sections; burh ditches | First melee sink; no romantic wet moat required |
| **4. Turf–timber rampart** | Oak-clad earth walls; Fyrkat ~4 m face; Trelleborg ~5 m × 17.5 m base; Borgring palisade top-up | Main wall HP; walkway archer track |
| **5. Gates (cardinal)** | Four gates; Borgring roofed tunnels ~4.7×10.7 m; Aggersborg protruding fronts; stone-lined Trelleborg gates | Kill funnels; boss doorways; fire vulnerability (Borgring/Trelleborg burns) |
| **6. Internal roads / quadrants** | Axial cross + ring street; longhouse blocks (16 / 48); bailey radial houses at Trelleborg | Barracks economy; repair speed; quadrant capture |
| **7. Hall / ideology core** | Jelling palisade + mounds + rune Christ stone + great hall traces; or Aggersborg cross-tower reading | Final keep / morale totem / conversion victory condition |

**Explicit non-layers (do not add by default):** concentric stone curtains, corner donjons, machicolated gatehouses, star bastions.

---

## 11. Obscure fortification details for gameplay

Use these as mechanics unlocked by research, not as undecorated trivia:

1. **Dry pointed ditches** with **palisade in the ditch bottom** (Trelleborg) — “spike trench” trap **[ARCH]**
2. **Berm** (Trelleborg ~5 m; Nonnebakken up to 10–12 m) — dead ground / ladder fail zone **[ARCH]**
3. **Unfinished ditch sectors** (Fyrkat) — map sabotage / incomplete build state **[ARCH]**
4. **Radial turf gang labour marks** in Borgring rampart — “corvée construction” mini-game **[ARCH]**
5. **Massive pre-build landscaping** (>1900 m³ at Borgring) — terraforming cost before wall HP appears **[ARCH]**
6. **Roofed gate tunnels** — interior ambush / boiling pitch fantasy must be labelled **CHR/SAGA colour**, not Borgring fact; fire damage to gates is **ARCH**
7. **Bailey as second ring** only proven at Trelleborg among the five — unique upgrade path **[ARCH]**
8. **Harbour piles vs open water** — ships can’t beach freely (Birka/Hedeby) **[ARCH]**
9. **Jelling 2 m gate + flanking building** — ceremonial choke, not army gate **[ARCH]**
10. **Wheel-ruts reuse of ruined Borgring gates in early 11th c.** — post-keep “haunted road” epilogue map **[ARCH]**
11. **Arrowheads in Trelleborg rampart** — evidence for archery phase vs wall **[ARCH]**
12. **Double defence at Birka**: town bank *and* Borg summit — vertical layered map **[ARCH]**
13. **Danevirke single-gate tradition** (Godfred annal + excavated gate research) — linear map with one official breach **[CHR][ARCH]**
14. **Longphort D-shape** elongated along river — asymmetric lanes **[ARCH]**
15. **Burghal Hidage hide math** — enemy scales garrison with wall length **[CHR][MOD]**
16. **Paris low bridges** as anti-longship tech — environmental hazard for Viking ships **[CHR]**
17. **Hedeby Connection Wall dendro 968** — unlock “link the emporium to the border wall” campaign beat **[ARCH]**
18. **Roman-foot / Trelleborg-foot surveying** — build accuracy buff if player holds “groma team” **[MOD]**
19. **Ship-setting + mounds inside Jelling** — ideology damage type vs purely physical walls **[ARCH]**
20. **Scanian lookalikes** as optional DLC maps with “affiliation uncertain” tooltip **[MOD]**

---

## 12. Do / Don’t + bibliography

### 12.1 Do
- Use **turf–timber + ditch + gates + water** as the Norse defensive language
- Prefer **measured numbers** from Trelleborg/Fyrkat/Aggersborg/Borgring/Jelling/Hedeby/Birka
- Keep ring forts in the **970s–980s Harald** window
- Offer **dual keep**: military ring + Jelling ideology
- Use burhs / Paris bridges as **anti-Viking** or attacker-flip content
- Tag Abbo and saga numbers as unreliable for balance

### 12.2 Don’t
- Default to **stone concentric castles**
- Treat ring forts as **Cnut’s England invasion camps**
- Date all Norwegian/Swedish hillforts to the Viking Age
- Put **horned helmets** on gatehouses **[FOLK]**
- Assume every longphort was a Dublin-scale town
- Quote **700 ships / 40,000 Vikings** at Paris as fact
- Merge Kovirke / Valdemar brick wall into one timeless texture
- Invent four-gate geometry for Hedeby semicircle or Jelling rhombus

### 12.3 Bibliography (starting set)

**UNESCO / official syntheses**
- UNESCO World Heritage Centre, *Viking-Age Ring Fortresses* (list 1660, inscribed 2023).
- UNESCO, *Archaeological Border Landscape of Hedeby and the Danevirke*.
- Danish Agency for Culture and Palaces, *Nomination of Viking-Age Ring Fortresses* (2021).
- National Museum of Denmark, Viking-Age Ring Fortresses portal.

**Ring forts / Jelling**
- Nørlund, Poul. *Trelleborg* (1948).
- Olsen, Olaf & Holger Schmidt. *Fyrkat* volumes (1977).
- Roesdahl, Else; Søren M. Sindbæk; Anne Pedersen (eds.). Aggersborg publication (2014).
- Christensen, Jonas et al. “Borgring. Uncovering the strategy for a Viking Age ring fortress in Denmark.” *Danish Journal of Archaeology* (2021).
- Runge, Mads. Nonnebakken investigations (2018–2019 papers).
- Jelling palisade reports / UNESCO boundary expansion documentation (Natmus; WHC docs).

**Danevirke / Hedeby / Birka**
- Dobat, Andres S. / Danevirke excavation syntheses (2010–2014 project papers).
- Tummuscheit, Astrid & Frauke Witte. Danevirke overview articles.
- Hedeby geoarchaeology datasets (rampart cores; volume estimates).
- Hedenstierna-Jonson, Charlotte et al. Birka garrison / “Viking Age paradox” fortification studies.
- *Vita Anskarii* (for Birka fort refuge episode) **[CHR]**.

**Ireland**
- Kelly, Eamonn P. longphort archaeological surveys.
- Simpson, Linzi / Dublin defence sequences.
- Woodstown excavation summaries (NRA / Waterford).
- *Annals of Ulster* (841, 902, 914, 917, 922 entries as relevant) **[CHR]**.

**England / Frankia**
- *Burghal Hidage*; Asser; Anglo-Saxon Chronicle **[CHR]**.
- Brookes, Stuart — burh comparative studies.
- Abbo Cernuus, *Bella Parisiacae urbis* **[CHR]**.
- Analyses of Edict of Pîtres / Pont-de-l’Arche bridge policy **[MOD]**.

**Critical MOD overviews**
- Sindbæk, Søren M. on Bluetooth *Burgenpolitik* / refuge hypothesis.
- Price, T. Douglas. *Ancient Scandinavia* (fortification chapters).
- Lund & Sindbæk, “Crossing the Maelstrom” (recent VA archaeology review).

---

## 13. Quick designer cheat-sheet

| Need | Best evidence pick |
|---|---|
| Circular arena | Trelleborg or Aggersborg |
| Exact ditch trap numbers | Trelleborg 17×4 m dry pointed |
| Max barracks spectacle | Aggersborg 48×~32 m |
| Ideology final room | Jelling 360 m rhombus palisade |
| Harbour map | Hedeby 1300 m semicircle + 670 m offshore |
| Island verticality | Birka piles → town bank → garrison → Borg |
| River-fork settler map | Dublin / Woodstown / Limerick longphort logic |
| Enemy mirror | Alfred burh + Hidage scaling |
| Siege-as-attacker | Paris 885–886 bridge towers |
| Linear border campaign | Danevirke + Kovirke |

---

*End of fortifications brief. All metric claims above are tied to ARCH/CHR/MOD tags; where reconstructions disagree (e.g. Aggersborg rampart width 9 vs 11 m; Trelleborg height 5 vs ~6 m), the table preserves the range rather than inventing a false precision.*
