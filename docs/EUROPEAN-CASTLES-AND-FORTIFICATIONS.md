# EUROPEAN CASTLES AND FORTIFICATIONS — Tower-Defence Game Bible

> Dense research notes for **keep / map / layer** design across Norman–Angevin–Edwardian castle evolution in England, Wales, Normandy, and Crusader Outremer (c. 1066–c. 1500, with a gunpowder coda only).  
> **Method:** Prefer measured archaeology, Cadw / Historic England / French Ministry of Culture dossiers, and near-contemporary chronicles. Flag dating debates and tourist folklore. **No invented “generic medieval castle” identical from Hastings to Henry VIII.**  
> Companion style: same project as Viking / Greek / Egyptian / Chinese / Persian / Maya–Aztec fortification briefs.

### Evidence tags (use throughout)

| Tag | Meaning |
|---|---|
| **ARCH** | Excavation, survey, building fabric, Cadw / RCAHMW / Historic England / French culture ministry documentation, UNESCO nomination data |
| **CHR** | Near-contemporary chronicles / pipe rolls / building accounts (Orderic, William of Newburgh, Pipe Rolls, Liberate Rolls, Edwardian wardrobe accounts) |
| **MOD** | Modern scholarly consensus or active debate (Cathcart King, Taylor, Mesqui, Kennedy, Boas, Pringle, Gravett, Allen Brown, etc.) |
| **FOLK** | Popular culture / tourist myth (“impregnable forever,” Disney turret kits, boiling oil as default, single timeless “castle look”) — flavour only |

**Design rule:** Prefer **ARCH/CHR** for map geometry, thicknesses, and layer stacks. Use **MOD** where secondary handbooks compress ranges or phases disagree. Never let **FOLK** overwrite ditch profiles, tower plans, or construction dates.

**Chronology note:** Absolute years for timber phases are soft unless dendro or accounts pin them. Edwardian Welsh foundation years and Krak Hospitaller transfer / fall dates are hard political anchors **[CHR]**; fabric phases still require **[ARCH][MOD]** flags (especially Krak outer enceinte timing).

---

## 0. What “European castle fortification” is — and is not

### 0.1 Is
- **Motte-and-bailey** earth–timber packages for rapid conquest control **[ARCH][CHR]**
- **Stone keeps / donjons** as residence + last refuge + power display **[ARCH]**
- **Curtain walls** with **mural towers**, **gatehouses**, and (later) **barbicans** as layered kill funnels **[ARCH]**
- **Concentric** “walls within walls” systems — Edwardian Wales and mature Crusader fortresses as peak military grammar **[ARCH][MOD]**
- **Early concentric-ish** experiments (notably **Château Gaillard**) before full Welsh / Hospitaller maturity **[ARCH][MOD]**
- **Ideology cores** — chapel, great hall, lord’s chambers — not only wall HP **[ARCH][CHR]**

### 0.2 Is not (default TD keep)
- One silhouette reused from 1066 to 1600 without tech-tree evolution **[MOD]**
- Treating every castle as finished concentric Beaumaris — most sites are **partial**, **hybrid**, or **unfinished** **[ARCH]**
- Assuming **Krak des Chevaliers** was built in one campaign as a perfect concentric diagram — it is **phased** **[ARCH][MOD]**
- Gunpowder **trace italienne** star forts as the medieval keep (coda only) **[MOD]**
- Boiling oil / murder-hole fantasy without labelling when fabric does not support it **[FOLK]**

### 0.3 Geographic / chronological frame

| Horizon | Defence language | Signature sites |
|---|---|---|
| Mid–late 11th c. | Motte + bailey (timber → early stone) | Hastings / Pevensey tradition; Domesday-era mottes |
| Late 11th–12th c. | Great tower / donjon dominance | White Tower (London); Rochester; Loches tradition |
| 12th–early 13th c. | Curtain + mural towers; early concentric-ish | Dover phases; Château Gaillard (1196–98) |
| Mid–late 13th c. | Mature concentric + monumental gatehouses | Edwardian Wales; Krak outer enceinte phase |
| 14th–15th c. | Gun loops, artillery adaptation, residential palace-castles | Later gatehouse houses; coastal artillery |
| 16th c.+ | Trace italienne (coda) | Italian / Low Countries bastion systems |

---

## 1. Motte-and-bailey — conquest package

### 1.1 Grammar
A **motte** is an artificial (or scarped natural) mound with a timber or early stone tower / palisade on the flattened summit. A **bailey** is an enclosed courtyard (often kidney-shaped against a circular motte) with hall, kitchens, stables, workshops, and economic life. Ditches typically join in a **figure-eight** plan **[ARCH][MOD]**.

### 1.2 Measured ranges (England & Wales corpus)

| Metric | Attested range / pattern | Evidence |
|---|---|---|
| Motte height | **3–30 m**; academic floor often **≥3 m** to exclude non-military mounds | **[MOD]** Corpus syntheses (Castellogy / standard handbooks summarizing England–Wales samples) |
| Height distribution (E&W) | **~7%** >10 m; **~24%** 5–10 m; **~69%** <5 m | **[MOD]** same corpus |
| Motte diameter | Roughly **30–90 m** class common in handbook ranges | **[MOD]** |
| Bailey content | Hall, chapel, barracks, stores, forges — economic centre under motte overwatch | **[ARCH][MOD]** |
| Link | Flying bridge or steps cut into motte | **[ARCH]** |
| Water | Streams dammed where possible → wet ditch / artificial lakes | **[ARCH]** |

### 1.3 TD reading
- **Early-game keep:** cheap vertical HP spike (motte) + horizontal economy yard (bailey).
- **Vulnerability:** timber fires; ditch fill; bridge cut.
- **Upgrade path:** replace timber tower with stone shell keep on motte, then abandon motte logic for curtain–tower systems.
- **Do not** default late-game maps to motte-and-bailey once concentric tech unlocks **[MOD]**.

### 1.4 Chronology flags
- Norman introduction / mass use in England–Wales after **1066** **[CHR][MOD]**.
- By the **13th c.** the form is largely superseded for major royal works, though earthworks persist as landscape fossils **[MOD]**.
- Exact count of “all mottes” varies by survey definition — **do not invent a single empire-wide number** without citing the survey **[MOD]**.

---

## 2. Stone keep / donjon

### 2.1 Role
The great tower is simultaneously:
1. **Last refuge** if bailey falls  
2. **Residential / ceremonial** stack (halls, chambers, chapel)  
3. **Political brand** — visible domination of landscape and town **[ARCH][CHR][MOD]**

Entrance often at **first-floor** level via removable timber stair / forebuilding — ground floor as storage / security buffer **[ARCH]**.

### 2.2 White Tower, Tower of London — measured brand keep

| Metric | Value | Evidence |
|---|---|---|
| Plan (excl. corner projections) | **~36 × 32–32.5 m** at base | **[ARCH]** Historic England / standard fabric descriptions |
| Height | **~27–27.5 m** at southern battlements | **[ARCH]** |
| Corner forms | Square W towers; NE round stair tower; SE semi-circular chapel apse projection | **[ARCH]** |
| Floors (original) | Basement + entrance level + upper floor (later roof / floor alterations) | **[ARCH]** |
| Materials | Kentish ragstone; Caen stone details (much later replaced in places) | **[ARCH]** |
| Chapel | St John’s Chapel in SE — ideology room inside the keep fabric | **[ARCH]** |
| Build band | Under way by **c. 1077–1080**; complete by **c. 1100** (prison use by 1101 for Ranulf Flambard) | **[ARCH][CHR]** |
| Name “White Tower” | Whitewashing project **1240** (lime) — **not** the 11th-c. name | **[CHR][ARCH]** |

**TD note:** White Tower = excellent **English Norman keep skin** and final-room ideology (chapel + royal brand). Later concentric curtains at the Tower of London are **separate later layers** — do not collapse all centuries into one texture **[ARCH]**.

### 2.3 Keep evolution (design, not one site)
| Type | Notes | Tag |
|---|---|---|
| Rectangular great tower | White Tower class; thick walls; mural chambers | **[ARCH]** |
| Shell keep | Stone ring on motte summit replacing timber | **[ARCH][MOD]** |
| Circular / polygonal donjon | Later 12th–13th c. trends; better missile deflection | **[MOD]** |
| Gatehouse-residence | Later medieval shift of status rooms into monumental gatehouses | **[MOD]** |

**Design rule:** In a concentric map, the **keep may be reduced or absent** as a freestanding tower (Beaumaris relies on gatehouses + inner ward ranges more than a classic donjon). Krak and Gaillard still stage a strong inner mass / keep logic **[ARCH]**.

---

## 3. Curtain walls, mural towers, gatehouses, barbicans

### 3.1 Curtain wall
A continuous enceinte linking towers. Height and thickness vary by period and budget. Inner curtains are often **higher** than outer curtains in concentric schemes so that both can shoot simultaneously **[ARCH][MOD]**.

### 3.2 Mural towers
Projecting towers (square → round / D-plan over time) give **flanking fire** along the curtain face and deny dead angles. Round forms resist mining / artillery better than sharp square corners **[MOD]**.

### 3.3 Gatehouse
The primary attack magnet. Twin-towered gatehouses with sequential doors, portcullises, and overhead murder-holes become standard high-medieval grammar. Harlech’s great gatehouse is a textbook Edwardian example (**~80 × 54 ft** twin-towered mass with multiple portcullises cited in secondary syntheses) **[ARCH][MOD]** — treat exact internal counts from primary Cadw surveys when implementing collision volumes.

### 3.4 Barbican
An **outwork** controlling the approach to the main gate — forcing attackers through an exposed, angled, or roofed kill corridor before the main gatehouse. Beaumaris south gate received a barbican addition from **1306** in Cadw/WH syntheses **[ARCH]**. Krak’s southern approach includes ditch + triangular barbican logic on the plateau side **[ARCH]** French culture ministry site dossier.

### 3.5 TD component roles

| Component | Combat job | Failure mode |
|---|---|---|
| Curtain | Continuous wall HP; wall-walk archer track | Breach / ladder / mine |
| Mural tower | Flank buff; local strongpoint | Isolate after curtain breach |
| Gatehouse | Boss doorway; multi-stage locks | Portcullis burn / sap / treachery |
| Barbican | Outer filter; forces pathing | Capture turns it into attacker staging |

---

## 4. Concentric castles — Edwardian Wales + Crusader Krak

### 4.1 What “concentric” means here
Two (or more) complete defensive circuits where the **inner wall dominates the outer** by height and firepower, with an open **list / ward** between them. Attackers who take the outer curtain remain under continuous fire while crossing predictable ground to the next gate **[MOD]**.

**UNESCO frame (Wales):** *Castles and Town Walls of King Edward in Gwynedd* — Conwy, Caernarfon, Harlech, Beaumaris **[ARCH]**.

Master mason: **Master James of St George** (Savoyard background; Master of the King’s Works in Wales) **[CHR][ARCH]**.

### 4.2 Beaumaris (begun 1295) — near-perfect plan, unfinished height

| Feature | Attested pattern | Evidence |
|---|---|---|
| Start | **1295**; last of Edward’s great North Wales ring | **[CHR][ARCH]** Cadw |
| Site | Flat “beau mareys” marsh by Menai Strait — blank canvas for geometry | **[ARCH]** |
| Rings | Moat (tidal) + outer curtain + outer ward + inner curtain (+ intended domestic ranges) | **[ARCH]** Cadw “four concentric rings” framing |
| Outer curtain | Octagonal-ish / eight-sided system with **12** towers + 2 gateways | **[ARCH]** |
| Inner ward area | **~0.75 acre (0.30 ha)** | **[ARCH]** |
| Inner walls | **~11 m (36 ft)** high × **~4.7 m (15.5 ft)** thick | **[ARCH]** |
| Inner towers / gates | 6 massive towers + 2 large D-plan / twin gatehouses (N & S) | **[ARCH]** |
| Outer–inner gap | **~18 m (60 ft)** average list width | **[ARCH]** |
| Arrow furniture | Cadw: outer walls bristled with **~300** firing positions; commonly cited **164** arrow slits among them (some later blocked) | **[ARCH]** — preserve both Cadw “300” and slit-count figures; do not collapse without note |
| Gate offset | Outer gate **not aligned** with inner gate → forced rightward traverse under fire | **[ARCH]** |
| Sea supply | South gate / dock for ship resupply; Gunners’ Walk firing platform | **[ARCH]** |
| Incomplete | Work peters out **1320s–c.1330**; south gatehouse & inner towers never reach full intended height; Llanfaes Gate barely started | **[ARCH][CHR]** |

**TD gold:** Beaumaris is the **clearest diagrammatic concentric arena** — ideal tech-tree “finished geometry” even though historically **short**. Label incompleteness as a campaign state (full height vs squat unfinished).

### 4.3 Caernarfon (from 1283) — palace-fortress + town walls (not pure concentric diagram)

| Feature | Attested pattern | Evidence |
|---|---|---|
| Role | Royal seat / propaganda fortress with banded masonry recalling imperial imagery (often linked to Constantinople in **MOD** interpretation) | **[ARCH][MOD]** |
| Eagle Tower | **~30 m** high; walls **~5.4 m (18 ft)** thick; three turrets; multi-floor + basement | **[ARCH]** Cadw / fabric syntheses |
| Eagle Tower plan | Ten-sided; up to **~10.5 m** across | **[ARCH]** |
| Town walls | Circuit **~734 m**; **8** towers; 2 main gateways | **[ARCH]** |
| Concentric purity | Less a textbook double-curtain concentric than a **polygonal royal fortress + walled borough** system | **[MOD]** |

**TD use:** Caernarfon = **English royal skin** + ideology (Eagle Tower as lord’s hall / prince brand). Prefer Beaumaris/Harlech for pure concentric combat grammar.

### 4.4 Harlech (1283; largely complete in ~7 years) — cliff + gatehouse concentric

| Feature | Attested pattern | Evidence |
|---|---|---|
| Setting | Rock / cliff; land approach concentrated on east | **[ARCH]** Cadw |
| Design | Classic walls-within-walls; outer wall now more ruined than inner | **[ARCH]** |
| Gatehouse | Twin-towered; **~80 × 54 ft** in secondary descriptions; multiple portcullises / doors / murder holes | **[ARCH][MOD]** |
| Inner ward | Compact square; great hall, kitchen, chapel against curtains | **[ARCH]** |
| Sea / “Way from the Sea” | Stepped / fortified approach historically linked to supply when sea reached closer to the rock | **[ARCH][MOD]** — sea level / shoreline change must be flagged |

**TD use:** Best **asymmetric concentric** — cliff denial on three sides, gatehouse boss on the vulnerable face.

### 4.5 Krak des Chevaliers — phased concentric (flag dating)

**Location:** Spur above Homs Gap corridor (Syria); UNESCO World Heritage (with Qal’at Salah El-Din) **[ARCH]**.

#### Phase table (do not flatten)

| Phase | Approximate band | What happens | Tag |
|---|---|---|---|
| Pre-Hospitaller | 11th c. Kurdish / Homs-linked fort tradition on the spur | Earlier fort, not the present concentric masterpiece | **[ARCH][MOD]** — foundation anecdotes vary in popular sources; prefer site dossier over tourist “1031 forever” certainty |
| Transfer to Hospitallers | **1142** (Raymond II of Tripoli) — some popular texts say 1144; **flag the year** | Order receives castle | **[CHR][MOD]** |
| First Hospitaller build | **1142–c.1170** | Inner ward: curtain + **square** towers; courtyard with vaulted chambers | **[ARCH]** French Ministry of Culture Krak dossier |
| Earthquake damage | **29 June 1170** (and later **1202**) | Damage → rebuild windows | **[CHR][ARCH]** |
| Strengthening / redesign | Early–mid 13th c. after quakes | Rounder towers, talus, upgraded inner mass | **[ARCH][MOD]** |
| Outer enceinte (mature concentric) | Mid-13th c. final major phase (dossier: around mid-century) | Second lower enceinte, stables on south, large hall + portico inside earlier castle | **[ARCH]** |
| Fall | **1271** to Mamluks (Baybars) | End of Hospitaller hold | **[CHR]** |

#### Measured / described form (surviving castle)

| Feature | Value / pattern | Evidence |
|---|---|---|
| Overall footprint | **~300 m** longest × **~140 m** widest | **[ARCH]** culture.gouv.fr Krak dossier |
| System | Two concentric enceintes; inner higher, dominates outer | **[ARCH]** |
| South | Ditch / moat zone; triangular barbican on plateau approach | **[ARCH]** |
| Inner south/west | Round / horseshoe towers emerging from enormous **talus** (glacis) | **[ARCH]** |
| Stables | Outer south range **~60 m** long — **cannot** hold 300 horses at once (dossier correction of myth) | **[ARCH]** |
| Cistern | Large open *birkeh* created when outer enceinte reshapes old southern ditch | **[ARCH]** |

**Dating warning for TD writers:** Calling Krak “the concentric castle of 1142” is false precision. Concentric maturity is largely a **13th-c.** achievement on a **12th-c.** Hospitaller core **[ARCH][MOD]**.

**TD gold:** Krak = **Crusader skin** + talus + sloping spur + cistern endurance + chapel ideology. Harder geometry than Beaumaris’s flat diagram — better “legendary keep showcase.”

---

## 5. Château Gaillard — innovative early concentric-ish (1196–1198)

### 5.1 Context
Built by **Richard I** (King of England / Duke of Normandy) at Les Andelys above the Seine to control the river approach to Rouen. Construction band **1196–1198**; extraordinary speed and cost **[CHR][ARCH]**. Associated fortified town of Petit-Andely.

### 5.2 Defence-in-depth grammar

| Element | Pattern | Evidence |
|---|---|---|
| Site | Limestone spur / cliff; river denial on multiple faces; attack lane from plateau | **[ARCH]** |
| Three enclosures | Outer bailey → middle bailey → inner bailey + keep, separated by dry moats / ditches | **[ARCH][MOD]** |
| Outer bailey | Triangular / pentagonal outwork; ditch cited **~12 m** deep in local / military-history syntheses; walls often given **~30 ft** high × **~12 ft** thick with multiple towers | **[MOD]** — prefer fabric survey when placing exact collision; keep ranges tagged |
| Causeway | Zigzag / controlled bridge from outer to middle to frustrate rolling siege engines | **[MOD]** |
| Inner curtain | Famous **scalloped / corrugated** wall of successive arcs (often cited as **19** arcs) with arrow slits — reduces dead angles and softens impact geometry | **[ARCH][MOD]** |
| Keep | Massive tower with thick walls (popular syntheses ~**16 ft**), battered / keeled base, early **machicolation** tradition in Western fabric | **[ARCH][MOD]** |
| Machicolations | Among earliest European uses; Crusader influence often argued | **[MOD]** |

### 5.3 Why “concentric-ish” not “full concentric”
Gaillard stages **sequential baileys along a spur** plus an inner wall that already thinks in concentric fire terms. It is **advanced for the 1190s** and precedes the fully diagrammatic Welsh / mature Krak outer-enceinte systems **[MOD]**. Wikipedia-level and specialist syntheses both stress early concentric principles without equating it to Beaumaris’s dual complete circuits on a flat plane.

### 5.4 TD use
- **French / Angevin skin** and “breakthrough tech” node between single-curtain keeps and mature concentric.
- Campaign beat: unlock **scalloped inner wall** and **machicolation** modules here.
- Siege of **1203–1204** (Philip II vs John’s garrison) is a ready attacker-flip scenario **[CHR]** — do not treat the castle as unfallen myth **[FOLK]**.

---

## 6. Dual recommendation for the TD pack

### 6.1 Military keep showcase (pick one primary, ship the other as alternate map)

**Primary options (equal grade, different fantasy):**

| Pick | Why | Skin |
|---|---|---|
| **Krak des Chevaliers** | Phased concentric masterpiece; talus; spur; cistern; chapel; legendary siege depth | **Crusader / Hospitaller** |
| **Beaumaris** | Clearest playable concentric diagram; offset gates; tidal moat + dock; arrow-loop density | **English Edwardian** |

**Recommendation:** Ship **both** if budget allows. If only one “hero keep” art pass: prefer **Krak** for spectacle and **Beaumaris** as the clean **tutorial / tech-tree reference** geometry. Do not merge their plans into one mashup mesh **[MOD]**.

### 6.2 Generic concentric grammar for the tech tree
Independent of hero map art, unlock modular systems:

1. Ditch / wet moat  
2. Outer curtain + mural towers  
3. Gatehouse (+ optional barbican)  
4. Lists / outer ward (kill field)  
5. Inner curtain (higher)  
6. Inner gatehouse / keep mass  
7. Chapel + great hall ideology rooms  

This grammar skins as:
- **English** — Edwardian ashlar, D-towers, twin gatehouses (Beaumaris/Harlech language)  
- **French** — Gaillard scalloped inner wall, Seine cliff palette, early machicolation  
- **Crusader** — Krak talus, irregular spur fit, Hospitaller chapel / hall  

### 6.3 What not to recommend as the sole keep
- Motte-and-bailey alone (early campaign only)  
- White Tower alone (keep-centric, pre-concentric)  
- Caernarfon alone (palace-propaganda; weaker pure concentric combat read)  
- Trace italienne (wrong century for this pack’s core)  

---

## 7. Grounded ~7-layer defence stack

Compose from **attested** high-medieval features. This is a **synthetic playable stack** for a concentric European arena, not a claim that every castle had all seven as separate named yards.

| Layer | Attested feature sources | TD function |
|---|---|---|
| **1. Outer bailey / ditch (and/or wet moat)** | Motte–bailey outer ditch tradition; Beaumaris tidal moat; Gaillard outer ditch ~12 m class; Krak southern ditch | First slow / sink; siege-engine parking denial; amphibious dock threat at Beaumaris |
| **2. Gatehouse / barbican** | Harlech twin gatehouse; Beaumaris offset gates + 1306 barbican; Krak triangular barbican | Boss doorway; multi-lock portcullis stages; pathing forced into fire lanes |
| **3. Outer curtain (+ mural towers)** | Beaumaris 12-tower outer; Krak lower enceinte with round bastions; general mural-tower flanking | First continuous wall HP; arrow-loop gallery (~300 firing positions language at Beaumaris) |
| **4. Middle ward / lists** | Beaumaris ~18 m list; Gaillard middle bailey; Krak outer ward / stables zone | Open kill field under inner-wall fire; capture = attacker staging yard |
| **5. Inner curtain (+ higher towers / talus)** | Beaumaris inner 11×4.7 m walls; Krak talus + higher polygonal inner; Gaillard scalloped inner | Main fortress HP; simultaneous fire over outer wall; mine-resistant glacis (Krak) |
| **6. Keep / donjon (or gatehouse-keep mass)** | White Tower archetype; Gaillard keep; Krak SW castellan mass; Beaumaris gatehouse-residence logic if keep absent | Final physical refuge; elite garrison; collapse = military defeat |
| **7. Chapel / lord’s hall ideology** | St John’s in White Tower; Krak chapel + great hall; Harlech/Caernarfon halls; Eagle Tower residential brand | Morale / legitimacy victory condition; burn or capture without total wall wipe |

**Explicit non-layers (do not add by default to medieval pack):** star bastions, cavaliers, covered way of full trace italienne, Napoleonic polygonal forts.

**Optional sub-systems (attach to layers, not new primary layers):** wall-walks, posterns, cisterns (*birkeh*), docks, murder-holes, machicolations, sea bridges / “Way from the Sea.”

---

## 8. Evolution cheat-sheet with numbers (designer quick view)

| Stage | Approx. band | Signature numbers to remember | TD unlock |
|---|---|---|---|
| Motte-bailey | 11th–12th c. | Mottes mostly **<5 m**; rare **>10 m** (~7%) | Cheap vertical keep |
| Stone keep | late 11th–12th c. | White Tower **~36×32 m**, **~27 m** high | Brand donjon |
| Curtain + towers + gate | 12th–13th c. | Twin gatehouses; round flanking becomes normal | Wall + flank modules |
| Early concentric-ish | **1196–98** Gaillard | 3 baileys; scalloped inner; deep outer ditch | Defence-in-depth node |
| Mature concentric (Wales) | **1283–1330s** | Beaumaris inner **11×4.7 m**; list **~18 m**; **~300** firing positions | Dual-wall tech |
| Mature concentric (Krak) | Outer enceinte **mid-13th c.** on 12th-c. core; fall **1271** | Footprint **~300×140 m**; phased towers | Legendary keep map |
| Gunpowder twilight | 14th–16th c. | Gun loops → bastions | Coda only |

---

## 9. Siege pressure tools (attacker kit — historically grounded)

| Tool | Period relevance | Notes | Tag |
|---|---|---|---|
| Mining / sapping | Throughout stone era | Round towers + talus are anti-mine answers | **[MOD]** |
| Traction / counterweight trebuchet | 12th–13th c. peak | Softens curtains; less decisive vs mature concentric alone | **[MOD]** |
| Belfry / ladders | Continuous | Needs filled ditch / drained moat first | **[MOD]** |
| Blockade / hunger | Always | Cisterns and sea docks are defender counters (Krak *birkeh*; Beaumaris dock) | **[ARCH]** |
| Treachery / forged surrender | Krak 1271 tradition in chronicles | Optional non-HP win | **[CHR][MOD]** |
| Early guns | 14th–15th c. | Starts to punish high thin medieval walls | **[MOD]** |

---

## 10. Skins — English / French / Crusader

### 10.1 English (Norman → Edwardian)
- Motte packages → White Tower brand → Dover-style curtain science → **Harlech / Beaumaris** concentric  
- Visual: grey-green Welsh ashlar, D-towers, twin gatehouses, tidal dock, town walls at Caernarfon  
- Voice: royal writs, Master James, Menai logistics  

### 10.2 French (Angevin / Capetian)
- Donjon tradition + **Château Gaillard** breakthrough  
- Visual: Seine cliff limestone, scalloped inner wall, triangular outer bailey, machicolated keep  
- Voice: Richard vs Philip; 1204 fall as campaign twist  

### 10.3 Crusader (Hospitaller Outremer)
- **Krak** phased concentric; talus; chapel; hall; cistern  
- Visual: spur silhouette, basalt/limestone contrast as site allows, Arabic–Latin frontier UI  
- Voice: earthquake rebuilds; 1271 Mamluk endgame  

**Do not** paint all three skins on one plan. Share the **7-layer grammar**; swap silhouettes and modules.

---

## 11. Gunpowder twilight — coda only

From the later Middle Ages, gunports and artillery reshape towers and gatehouses. By the **16th century**, Italian engineers systematize the **trace italienne**: low thick bastions, angled flanking, wide glacis — a different game genre.

**TD stance for this pack:**
- Medieval concentric campaign ends at **high curtain / gatehouse / keep** grammar.  
- Optional epilogue map: “bastion retrofit” or coastal device fort.  
- **Do not** replace Beaumaris/Krak keep art with star forts as the default European brand.

---

## 12. Obscure fabric details for gameplay (evidence-tied)

1. **Beaumaris gate offset** — forced right-hand traverse under crossfire **[ARCH]**  
2. **Beaumaris unfinished height** — campaign state: “budget cut / Scotland divert” squat towers **[CHR][ARCH]**  
3. **~300 firing positions** language (Cadw) vs **164** slit counts — implement as stacked loop + battlement slots **[ARCH]**  
4. **Blocked low loops** (Beaumaris; early 15th c. or Civil War explanations in literature) — anti-exploit patch mid-campaign **[ARCH][MOD]**  
5. **Krak talus** — ladder fail / mine resist zone **[ARCH]**  
6. **Krak *birkeh*** — siege timer resource created by outer-wall rebuild **[ARCH]**  
7. **Stables ≠ 300 horses** — dossier myth-check; capacity propaganda debuff **[ARCH]**  
8. **Gaillard zigzag causeway** — siege-engine pathing puzzle **[MOD]**  
9. **Scalloped inner wall** — no dead-angle buff; unique French module **[ARCH][MOD]**  
10. **Early machicolations** — drop-slot gallery unlock at Gaillard node **[MOD]**  
11. **Harlech cliff denial** — three faces nearly free; east gatehouse is the war **[ARCH]**  
12. **Caernarfon town wall 734 m** — borough layer as optional outer civilian HP **[ARCH]**  
13. **Eagle Tower river postern** — stealth water lane **[ARCH]**  
14. **White Tower first-floor entrance** — removable stair = early-game drawbridge analogue **[ARCH]**  
15. **Motte height distribution** — most mottes are short; mega-motte is rare spectacle **[MOD]**  
16. **Figure-eight ditches** — motte+bailey pathing template **[ARCH]**  
17. **Krak phase mismatch** — earthquake rebuild as mid-campaign “reforge walls” quest **[CHR][ARCH]**  
18. **Sea dock inside Beaumaris** — naval resupply bypasses land siege meter **[ARCH]**  
19. **Murder-holes / portcullis stacks** — multi-stage gatehouse combat, not one door HP bar **[ARCH]**  
20. **Chapel as victory condition** — capture ideology without razing every curtain **[ARCH][CHR]**

---

## 13. Do / Don’t + bibliography

### 13.1 Do
- Evolve the pack: motte → keep → curtain/gate → Gaillard node → concentric maturity  
- Offer **dual hero keeps**: **Krak** and/or **Beaumaris** + **generic concentric grammar**  
- Skin English / French / Crusader separately  
- Use the **7-layer stack** in §7 for balance  
- Flag Krak dating phases and Beaumaris incompleteness  
- Keep numbers with tags; preserve ranges when sources disagree  

### 13.2 Don’t
- Ship one timeless Disney castle as “Europe”  
- Date Krak’s outer enceinte to 1142 without flag  
- Treat Beaumaris as historically finished to full height  
- Make Caernarfon the only concentric tutorial (it’s a palace-fortress hybrid)  
- Default boiling oil **[FOLK]**  
- Replace the pack with trace italienne except as coda  
- Invent garrison sizes (e.g. “300 horses in the stables”) contradicted by site dossiers  

### 13.3 Bibliography (starting set)

**Official / site dossiers**
- Cadw guides and site pages: Beaumaris, Harlech, Caernarfon, Conwy.  
- RCAHMW / Coflein entries for Edwardian castles.  
- UNESCO, *Castles and Town Walls of King Edward in Gwynedd*.  
- French Ministry of Culture, *Le Crac des Chevaliers* archaeology portal (phases, enceintes, stables, *birkeh*).  
- Historic England / HRP fabric descriptions for the White Tower / Tower of London.

**Core MOD overviews**
- Taylor, Arnold — Edwardian castle studies / Cadw guidebook tradition.  
- Cathcart King, D. J. — *Castellarium Anglicanum* and castle typology.  
- Allen Brown, R. — Norman castles / White Tower context.  
- Mesqui, Jean — French castle architecture; Gaillard discussions.  
- Kennedy, Hugh — Crusader castles.  
- Boas, Adrian / Pringle, Denys — Crusader fortification archaeology.  
- Gravett, Christopher — general medieval siege / castle primers (use cautiously; verify numbers).

**Primary / near-primary**
- Pipe Rolls / building accounts for Edwardian works **[CHR]**.  
- Chronicles for Gaillard 1203–04 and Krak 1271 **[CHR]**.  
- Domesday and early Norman narrative for motte horizon **[CHR]**.

---

## 14. Quick designer cheat-sheet

| Need | Best evidence pick |
|---|---|
| Early-game earth–timber | Motte-and-bailey (height distribution realism) |
| Iconic English keep | White Tower **36×32 m / ~27 m** |
| Breakthrough concentric-ish | Château Gaillard 1196–98 |
| Pure concentric diagram | **Beaumaris** (flag unfinished) |
| Cliff asymmetric concentric | **Harlech** |
| Royal propaganda fortress | **Caernarfon** + Eagle Tower + town walls |
| Legendary military showcase | **Krak des Chevaliers** (phased!) |
| Tech-tree backbone | Generic 7-layer concentric grammar |
| Skins | English / French / Crusader |
| Coda | Trace italienne mention only |

---

## 15. Return block — keep + 7-layer (implementation summary)

### Recommended keep (dual)
- **Showcase A:** **Krak des Chevaliers** (Crusader phased concentric; talus; cistern; chapel)  
- **Showcase B:** **Beaumaris** (Edwardian near-perfect concentric diagram; offset gates; moat/dock)  
- **Tech tree:** generic concentric grammar skinned English / French / Crusader  
- **Bridge node:** Château Gaillard (early concentric-ish)  
- **Early nodes:** motte-bailey → stone keep (White Tower class)

### Grounded 7-layer stack
1. **Outer bailey / ditch (moat)**  
2. **Gatehouse / barbican**  
3. **Outer curtain**  
4. **Middle ward / lists**  
5. **Inner curtain**  
6. **Keep / donjon** (or gatehouse-keep mass)  
7. **Chapel / lord’s hall ideology**

---

*End of fortifications brief. Metric claims are tied to ARCH/CHR/MOD tags; where Cadw “300 firing positions” and slit-count figures, or Gaillard ditch/wall popular measurements, or Krak transfer year 1142 vs 1144 disagree across handbooks, ranges and flags are preserved rather than inventing false precision.*
