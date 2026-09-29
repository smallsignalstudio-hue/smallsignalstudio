# JAPANESE CASTLES AND FORTIFICATIONS — Tower-Defence Game Bible

> Dense research notes for **keep / map / layer** design across medieval–early modern Japanese castle architecture (*yamajiro* mountain forts → Sengoku–Azuchi–Momoyama prototypes → Edo-period *hirajiro* / *hirayamajiro* complexes).  
> **Method:** Prefer measured site data, UNESCO / Agency for Cultural Affairs documentation, near-contemporary chronicles, and specialist architectural glossaries (JAANUS). Flag tourist nicknames and anime folklore. **No European high-medieval curtain-castle clone** as the default Japanese brand.  
> Companion briefs (same project style): fortification dossiers for Viking / Greek / Egyptian / Chinese / Persian / Maya–Aztec packs.

### Evidence tags (use throughout)

| Tag | Meaning |
|---|---|
| **ARCH** | Excavation, standing fabric survey, UNESCO / Agency for Cultural Affairs / municipal castle office metrics, dendro where cited |
| **LIT** | Near-contemporary literary / chronicle testimony (*Shinchō Kōki*, siege diaries, Tokugawa-period castle maps *shiro ezu*, Edo-period military manuals) |
| **MOD** | Modern scholarly consensus or active debate (Naitō Akira on Azuchi; castle typology handbooks; reconstruction critiques) |
| **FOLK** | Popular culture / tourist myth (“White Heron impregnable forever,” ninja-army gates, katana-only battlefield, eternal bushidō) — flavour only |

**Design rule:** Prefer **ARCH/LIT** for map geometry and layer stacks. Use **MOD** where reconstruction heights disagree or secondary handbooks compress ranges. Never let **FOLK** overwrite moat profiles, keep heights, or siege chronology.

**Chronology note:** Absolute years for early *yamajiro* phases are often soft. Political anchors (Azuchi build **1576–79**; Osaka Winter **1614** / Summer **1615**; Himeji Ikeda rebuild **1601–09**; Meireki fire **1657**) are hard **[LIT][ARCH]**.

---

## 0. What “Japanese castle” is — and is not

### 0.1 Is
- A **layered compound system** (*nawabari* layout; *kuruwa* / *maru* baileys) organized as defence-in-depth, not a single freestanding donjon **[ARCH][LIT]**
- **Ishigaki** dry-fitted stone bases under timber–plaster superstructures (especially late Sengoku → Edo) **[ARCH]**
- **Tenshu / tenshukaku** keep as political–military brand tower, often linked to subsidiary keeps (*kotenshu*) and connecting corridors (*watari-yagura*) **[ARCH]**
- **Yagura** turrets / arsenals / wall-walk nodes as the real distributed firepower layer **[ARCH][LIT]**
- **Hori** (wet or dry moats), **dorui** earthen banks, and **koguchi** “tiger’s mouth” gate traps (**masugata**, **umadashi**, *kuichigai*) **[ARCH]**
- Terrain classes: **yamajiro** (mountain), **hirajiro** (flatland), **hirayamajiro** (hill-on-plain) **[MOD]**

### 0.2 Is not (default TD keep)
- A **European concentric stone curtain** with corner drum towers and a free-standing square keep as the native Japanese default **[MOD]**
- Treating the white plaster aesthetic alone as “decoration only” — plaster (*shikkui*) is fire-resistant skin on earthen walls and part of the defensive envelope **[ARCH]**
- Assuming every castle was assaulted like a Hollywood siege movie — many Edo castles were **deterrence + domain HQ** after 1615 **[MOD]**
- Katana-primary battlefield orthodoxy or ninja-army gate mechanics as default physics **[FOLK]**
- One generic “samurai castle” identical from early *yamajiro* earthworks to Tokugawa mega-complexes — **period and terrain dominate form** **[MOD]**

### 0.3 Chronological / typological frame

| Horizon | Defence language | Signature sites |
|---|---|---|
| Medieval *yamajiro* | Ridge baileys, *dorui*, dry *tatebori*, timber palisades; limited stone | Early mountain forts (many regions) |
| Late Sengoku | Larger earthworks; early organized *ishigaki*; gun-era *sama* | Regional daimyō forts |
| Azuchi–Momoyama (c.1570s–1600) | Monumental *tenshu* brand + massive *ishigaki*; castle–town fusion | **Azuchi** (prototype); Osaka (Toyotomi) |
| Early Edo (c.1600–1650s) | Peak wooden castle architecture; spiral maze approaches; multi-bailey *hirayamajiro* | **Himeji**, Kumamoto, Matsumoto, Edo scale-up |
| Post-Meireki / late Edo | Many keeps lost to fire / bakufu limits; surviving originals rare | Himeji, Matsumoto, etc. as rare intact keeps |
| Meiji–modern | Demolition, military reuse, concrete reconstructions (flag carefully) | Osaka (concrete keep); Kumamoto (rebuilds) |

---

## 1. Japanese castle anatomy — parts dictionary (with numbers where known)

### 1.1 *Nawabari* and *kuruwa* / *maru* (layout grammar)

**Nawabari** (“stretched rope”): the surveyed plan of baileys, moats, and gates — the castle’s blueprint **[LIT][MOD]** Nippon.com / castle typologies.

**Kuruwa** (compound / bailey): subdivided defensive zones. The classic inner triad:

| Zone | Japanese | Role |
|---|---|---|
| Main bailey | **Honmaru** (本丸) | Lord’s residence / HQ / final hold; often hosts *tenshu* |
| Second bailey | **Ninomaru** (二の丸) | Administrative / residence / fallback |
| Third bailey | **Sannomaru** (三の丸) | Outer military / retainer zone |

Additional named forms: *obi-kuruwa* (belt bailey wrapping a perimeter); *koshi-kuruwa* (mid-slope “waist” bailey on *yamajiro*) **[MOD]**.

**TD map note:** Layers are **named baileys + moats + gates**, not identical concentric European wards. Spiral / offset *nawabari* is a feature, not a bug.

### 1.2 *Ishigaki* (石垣) — stone bases

- Dry-stone (typically **no mortar**) retaining / foundation walls under timber buildings **[ARCH]** JAANUS
- Late Warring States → Nobunaga / Hideyoshi era: organized *ishigaki* proliferate; earlier Sengoku defence often **dorui**-primary **[MOD]** Nippon.com
- Stacking styles (Edo-period classification labels; do not over-project labels onto 1570s sites without care) **[MOD]**:
  - **Nozurazumi** — natural-face rough stones; good drainage
  - **Uchikomihagi** — pounded / fitted corners; steeper faces; common after Sekigahara (**1600**) in many handbooks
  - **Kirikomihagi** — cut cubic stones; near-gapless faces + separate drains
- Corner technique **sangizumi** (log-like alternating long/short corner stones) enables steeper corners **[ARCH][MOD]**
- Face incline often **concave** (“fan” / *musha-gaeshi* reading at some sites); handbook angles vary — Ogyū Sorai *Kenroku* (1727) discusses elevation classes around **~45° / ~50° / steep ~80°** bands **[LIT]** JAANUS summary — treat as Edo theoretical taxonomy, not a universal field rule
- **Kumamoto** signature: famously steep / curved *musha-gaeshi* faces associated with Katō Kiyomasa’s school of masonry **[ARCH][MOD]**

**TD use:** *Ishigaki* = climb-cost + artillery-resistant base HP. Overhanging / curved faces = anti-ladder modifier. Collapse after earthquake (Kumamoto **2016**) = optional disaster event, not default lore.

### 1.3 *Tenshu* / *tenshukaku* (天守) — keep

- Multi-storey timber tower on *ishigaki*; political symbol **and** observation / last-refuge node **[ARCH][MOD]**
- External roof-count ≠ internal floor-count (classic Himeji / Matsumoto mismatch) **[ARCH]**
- Forms: independent keep; **linked cluster** (*renritsu*: main + subsidiary keeps + *watari-yagura*) **[ARCH]**
- Azuchi traditionally framed as the **prototype full-scale tenshu** brand for later mega-castles **[LIT][MOD]** — standing fabric mostly lost (see §3.1)

### 1.4 *Yagura* (櫓) — turrets

- Watch / fire / store structures on walls and corners; often more important for day-to-day defence than the keep alone **[ARCH]**
- **Yaguramon**: gate with turret above — common for *ōtemon* / honmaru fronts **[MOD]**
- **Tamon-yagura**: long horizontal turret (name from Matsunaga Hisahide’s Tamon Castle tradition); can run tens of metres (e.g. Fukuoka Castle examples cited at **~54 m** *nagaya* linked length in popular site notes — verify per site) **[MOD]**
- Corner *sumi-yagura*, connecting *watari-yagura*, three-storey *sanjū-yagura* as prestige / firepower nodes **[ARCH]**

**TD use:** Placeable towers = *yagura* class. Keep is boss brand; *yagura* are the stack’s working DPS.

### 1.5 *Hori* (堀) — moats

| Type | Notes | Tag |
|---|---|---|
| **Mizubori** | Water-filled moat | **ARCH** |
| **Karabori** | Dry moat | **ARCH** |
| **Tatebori** | Vertical dry cuts on mountain slopes to block lateral movement | **ARCH** |
| **Unejō tatebori** | Series of furrowed vertical dry moats | **ARCH** |
| **Yagenbori** | V-section (mortar-shaped) | **MOD** |
| **Hakobori** | Box / flat-bottom section | **MOD** |
| **Kenukibori** | U-section | **MOD** |
| **Shōjibori** | Grid-partitioned dry moat (famous at Yamanaka Castle, Hōjō) | **ARCH** |

Soil from ditching becomes **dorui** banks; attackers in the ditch are shot from the bank **[MOD]**. Dorui faces often ~**45°**, sometimes steeper toward *kirigishi* bluff readings **[MOD]**.

### 1.6 *Koguchi*, *masugata*, *umadashi* — gate ecology

**Koguchi** (虎口, “tiger’s mouth”): the dangerous entrance zone. Path bends force slowing; fire from *sama* and *yagura* **[MOD]**.

| Device | Function | Notes / numbers |
|---|---|---|
| **Kuichigai-koguchi** | Offset / staggered walls create bent approach | Early / common mountain–hill form |
| **Masugata** | Square (masu-shaped) court between outer and inner gates at right angles | Kill-box; bottleneck; textbook Edo gate |
| **Umadashi** (*uma-dashi* / 馬出) | Barrier / small compound **in front of** the gate; sally / screen / barbican analogue | Types: simple *azuchi umadashi* (straight screen often **~20–30 m** long, human-concealment height) **[MOD]** JAANUS; *maru-umadashi* (semicircular); *kaku-umadashi* (square) |
| **Yaguramon** | Turret gate over the opening | Elevated fire + prestige |

**Do not** equate *umadashi* with European barbican 1:1 without noting timber–earth vocabulary and sally doctrine **[MOD]**.

### 1.7 *Ishi-otoshi* (石落とし) and *sama* (狭間)

| Feature | What it is | Combat reading |
|---|---|---|
| **Ishi-otoshi** | Overhanging floor opening / chute above the *ishigaki* face | Drop stones **or** shoot downward into the dead angle *sama* cannot cover **[ARCH]** (site interpretations at Marugame / Matsumoto etc.) |
| **Sama** | Loopholes in walls / keeps | Arrow and matchlock ports; often wider inside than outside for traverse **[ARCH]** |
| Matsumoto Great Keep cluster | **115** loopholes; **11** *ishi-otoshi* openings cited in MLIT multilingual castle text | **[ARCH]** / official interpretive synthesis |
| Matsumoto inner moat width | **~60 m** — framed as matchlock effective armour-pierce distance in castle interpretation | **[MOD]** interpretive; still useful TD range band |

### 1.8 Spiral / maze paths

- Not accidental tourist confusion: **deliberate spiral / labyrinth approaches** force longer exposure under fire **[ARCH]**
- **Himeji textbook numbers:** straight-line Hishi Gate → daitenshu **~130 m**; actual path **~325 m** **[ARCH]** widely cited site / encyclopedia synthesis
- Dead ends, 180° turns, single-file squeezes, and walls on both flanks = stacked choke layers **[ARCH]**
- Gate naming by *iroha* syllabary (I, Ro, Ha…) at Himeji: originally many named gates; **21** gates remain of an original tradition of **~84** **[ARCH]** encyclopedia / World History Encyclopedia synthesis — flag counts as secondary compilations where primary gate lists are not fully reconstructed

**TD use:** Path length multipliers + facing-away moments (backs exposed after turns) + optional wrong-turn softlocks for AI pathing comedy (label as optional FOLK UX, not history).

---

## 2. Himeji Castle — primary military brand keep

### 2.1 Why Himeji is the default Japanese TD keep

- Finest **surviving** early-17th-c. Japanese castle architecture in UNESCO framing **[ARCH]** UNESCO WHL **661** (*Himeji-jo*), inscribed **1993**
- Intact wooden *tenshu-gun* + maze approach + white plaster brand readable at silhouette distance
- Never successfully storm-tested in the spiral manner tourists imagine — system is **designed**, not battle-proven in that exact configuration **[ARCH][MOD]**

### 2.2 Measured brand metrics (cite carefully)

| Metric | Value | Evidence / caution |
|---|---|---|
| **Daitenshu height** | **~46.4 m** | Widely cited keep height; reconciles with hilltop ASL math below **[ARCH]** secondary compilations / site literature |
| Hill / complex ASL | Himeyama summit cited **~45.6 m** ASL; keep top cited **~92 m** ASL | **92 − 45.6 ≈ 46.4** — internal consistency check **[ARCH]** |
| Alternate “33 m” figure | Appears in some atlas / tourist cards for “donjon” | **Do not silently merge** with 46.4 m — likely different measuring convention (wooden body vs full stack). Prefer **46.4 m** with footnote **[MOD]** |
| External appearance | **5** roof tiers visible | **[ARCH]** |
| Internal floors | **6** storeys + **basement** | **[ARCH]** |
| Basement area | **~385 m²** | **[ARCH]** encyclopedia synthesis |
| Subsidiary keeps | **3** *kotenshu* linked with main keep into a cluster | **[ARCH]** |
| Building count | UNESCO OUV: **82** buildings; other UNESCO blurbs / many guides: **83** | **Preserve the 82–83 range**; do not invent a fake exact without saying which inventory **[ARCH]** |
| Property area (UNESCO) | **107 ha** property; **143 ha** buffer (total **250 ha** in WH documentation) | **[ARCH]** UNESCO |
| Highest walls in complex | Up to **~26 m** cited in encyclopedia synthesis | **[ARCH]** secondary |
| Moats | Avg width **~20 m**; max width **~34.5 m**; depth **~2.7 m** | Japan Atlas / widely repeated site figures **[ARCH]** — treat as complex-average synthesis, not one trench log |
| Moat rings | Originally **three** (outer / middle / inner); outer largely buried / lost in urban fabric | **[ARCH]** |
| Sangoku-bori (三国堀) | Interior pond **~2,500 m²**; fire-prevention water among functions | **[ARCH]** |
| Approach path | **~130 m** straight vs **~325 m** walking spiral (Hishi-mon → daitenshu) | **[ARCH]** |
| Surviving gates | **21** of original **~84** tradition | **[ARCH]** secondary |
| Plaster brand | White *shikkui* → popular name **Shirasagi-jō** (White Heron Castle) | **[ARCH][FOLK]** nickname is historical-popular, not a separate fortress type |
| Build phases (standing complex) | Major Ikeda Terumasa rebuild **1601–1609**; Honda additions **1617–1618**; earlier fort traditions on site from medieval horizon (Akamatsu **1333** tradition; Hideyoshi **1581** expansion often cited) | **[LIT][ARCH]** — standing visitor experience is early-17th-c. |
| Continuity | Feudal domain centre until **1868** | **[ARCH]** UNESCO |

### 2.3 Defensive reading (military, not postcard)

1. **Outer town + moat ring** — *jōkamachi* and *sotobori* as first strategic depth  
2. **Gate / masugata chain** — named gates force turns into fire sacks  
3. **Bailey spiral** — sannomaru / ninomaru tissue as maze HP  
4. **Yagura + earthen / plaster walls** — distributed fire along flanks  
5. **Ishigaki climb denial** under white walls  
6. **Honmaru** — last compound before the keep cluster  
7. **Tenshu-gun** — daitenshu + 3 kotenshu as brand boss room  

**UNESCO OUV criteria:** (i) masterpiece of wooden construction + white plaster aesthetic; (iv) culmination of Japanese castle architecture in wood, significant features intact **[ARCH]**.

### 2.4 What Himeji is *not*
- Not proof that white castles were never attacked successfully elsewhere  
- Not a European concentric curtain with a free keep in a round ward  
- Not “the only Japanese castle” — it is the **best-preserved brand exemplar** for TD silhouette + maze grammar **[MOD]**

---

## 3. Alternatives and campaign maps

### 3.1 Azuchi Castle (Oda Nobunaga) — prototype tenshu (mostly lost)

| Claim | Status | Tag |
|---|---|---|
| Built **1576–1579** on Azuchi-yama above Lake Biwa | Standard chronology | **[LIT][ARCH]** |
| Hill rises **~100 m** above the lake in common descriptions | Site geography | **[ARCH]** |
| Strategic node on eastern approaches to Kyoto | Contemporary strategic reading | **[LIT][MOD]** |
| Tenshu as unprecedented prestige tower (audience halls, Kanō Eitoku decoration tradition) | Strong literary / art-historical tradition | **[LIT]** |
| Height **~46 m** (Naitō Akira 1976 synthesis; base dimensions in ken → metre conversions; some reconstructions nearer **~38 m**) | **Active debate** — do not present 46 m as surveyed standing fabric | **[MOD]** |
| Storey tradition | Often **7** levels counting basement (6 above + 1 below) in handbook tradition | **[MOD]** |
| Stone walls | Massive granite *ishigaki* without mortar in classic descriptions; thickness figures in secondary accounts sometimes **~5.5–6.5 m** — verify per excavation report before locking TD numbers | **[ARCH][MOD]** |
| Fate | Destroyed **1582** (Honnō-ji aftermath / Akechi period burning); **mostly lost** above foundations | **[LIT][ARCH]** |
| Legacy | Template for later monumental *tenshu* politics (Osaka, etc.) | **[MOD]** |

**TD use:** Optional **prototype / origin DLC keep** — gold–lacquer fantasy skin allowed as **LIT**-flavoured brand, but map should show **foundation archaeology + lost superstructure** honesty, not fake intact Azuchi equal to Himeji.

### 3.2 Osaka Castle — siege campaign maps (1614–15)

| Item | Facts | Tag |
|---|---|---|
| Builder context | Toyotomi Hideyoshi from **1583** on Ishiyama Hongan-ji site; modelled to **surpass Azuchi** | **[LIT][MOD]** |
| Winter Campaign | **1614** (*fuyu no jin*) | **[LIT]** |
| Summer Campaign | **1615** (*natsu no jin*) | **[LIT]** |
| Army scales (order-of-magnitude) | Tokugawa often cited **~164,000–200,000**; Toyotomi **~90,000–120,000** incl. rōnin — **ranges, not census** | **[LIT][MOD]** |
| Sanada-maru | Semicircular southern outwork under Sanada Yukimura; famous Winter defence node | **[LIT][ARCH]** |
| Peace trick | After Winter truce, outer (then further) moats **filled** / outer works dismantled — castle stripped of water depth | **[LIT]** |
| Outcome | Summer field battles + fall; Toyotomi extinguished; buildings burned | **[LIT]** |
| Standing keep today | Later reconstructions (incl. modern concrete iterations) — **not** Hideyoshi’s original tenshu | **[ARCH]** — skin carefully |

**TD use:** Best as **campaign map set**, not as “authentic wooden keep brand.” Layers: outer forts / Sanada-maru → moat politics → honmaru → burning ending. Mechanic: **moat-fill victory condition** after false peace.

### 3.3 Kumamoto Castle — *ishigaki* specialist

- Associated with **Katō Kiyomasa**; major early-17th-c. works (completion horizon often cited **1607**) **[LIT][ARCH]**
- Famous for steep / curved stone faces (*musha-gaeshi* popular label) **[ARCH][FOLK]** (label is interpretive)
- Popular inventories cite large turret / gate counts (e.g. **49** turrets, **18** turret gates, **29** gates, **~120** wells in tourist–handbook lists) — **flag as secondary tallies**; useful for TD density, not as excavated census without site-report cross-check **[MOD]**
- Fought over in **1877** Satsuma Rebellion; heavy later reconstruction; **2016** earthquake damage → ongoing repair narrative **[ARCH]**
- Perimeter / grounds figures vary in popular sources (sometimes **~5.3 km** class) — keep as soft **[MOD]**

**TD use:** Stone-wall climb hell + well/siege-endurance meters + earthquake hazard modifier.

### 3.4 Matsumoto Castle — flatland black keep

| Metric | Value | Tag |
|---|---|---|
| Type | **Hirajiro** (flatland) | **ARCH** |
| Great Keep height | **~29.4 m** (MLIT multilingual DB) | **ARCH** |
| Storeys | **5** external / **6** internal (classic) | **ARCH** |
| Linked design | Early *renritsu* cluster (Great Keep + Inui + watari, later moon-viewing tower additions) | **ARCH** |
| National Treasure | Among the rare original wooden keeps with NT designation | **ARCH** |
| Complex scale (historical) | Three baileys / moats; extent toward Metoba River **~1.3 km** S; compound area cited **~390,000 m²** | **ARCH** interpretive DB |
| Loopholes / drops | **115** *sama*; **11** *ishi-otoshi* | **ARCH** interpretive |
| Inner moat | **~60 m** across (matchlock-range interpretive claim) | **MOD** |
| Nickname “Crow Castle” | Popular black-lacquer reading; castle office notes lack of historical document for the nickname | **FOLK** vs site caution |

**TD use:** Alternate keep skin (black vs Himeji white); flatland moat-primary defence without mountain cliffs.

### 3.5 Edo Castle — scale extremity (Imperial Palace grounds)

| Metric | Value | Caution |
|---|---|---|
| Outer perimeter tradition | **~16 km** (four *ri* waterway / moat circumference tradition) | **[LIT][MOD]** museum / encyclopedia |
| Inner vs outer moat bands | Inner often **~5 km**; outer **~16 km** in World History Encyclopedia synthesis | Secondary synthesis |
| Outer enclosed area | Sometimes **~2,082 ha** in specialist popular syntheses | Soft — cite as order-of-magnitude mega-scale |
| Bailey set | Honmaru, Ninomaru, Sannomaru, Nishinomaru, Kitanomaru, Fukiage, etc. | **ARCH** |
| Tenshu iterations | Built / rebuilt **1607 / 1623 / 1638** horizons; third keep destroyed in **1657** Meireki fire; **not rebuilt** | **LIT][ARCH]** |
| Tenshu height claims | Handbook figures vary: tower alone often **~51–58 m**; with base sometimes pushed toward **~80 m** class in popular accounts | **Preserve range**; do not lock one number without specifying which rebuild |
| Surviving tenshu-dai | Stone base today **~41 m** E–W × **~45 m** N–S × **~11 m** high | **ARCH** |
| Gates / turrets (popular tallies) | e.g. **~38** gates / **~20–29** turret traditions in secondary lists | Soft |

**TD use:** Not a default climbable wooden keep (lost). Use as **endgame mega-map**: spiral moats, gatehouses, empty tenshu-dai boss plinth, daimyō procession lanes. Scale flex vs Himeji intimacy.

### 3.6 Early *yamajiro* — mountain castles

- Pre- and early-Sengoku default: **ridge chains**, small flattened baileys, **dorui**, dry **tatebori**, timber fences — stone *tenshu* not required **[ARCH][MOD]**
- Defence = **altitude + choke paths + vertical dry moats** blocking contour traversal  
- Excellent “early tech” TD era before Azuchi brand towers unlock  
- Do not reskin every *yamajiro* as mini-Himeji white plaster **[MOD]**

---

## 4. Dual recommendation (bible stance)

### 4.1 Primary keep
**Himeji daitenshu + tenshu-gun** as the **military aesthetic brand keep**: measured height **~46.4 m**, UNESCO authenticity, white plaster silhouette, linked *kotenshu*, and the best-documented spiral approach numbers (**130 / 325 m**).

### 4.2 Optional second axis
Ship **one** (or both) as campaign / alternate modes — do not replace Himeji as the default silhouette:

1. **Azuchi** — origin myth of the monumental *tenshu* (mostly archaeological / reconstruction UI)  
2. **Osaka 1614–15** — full **siege campaign maps** (Sanada-maru, cannon, moat-fill betrayal, summer collapse)

**Matsumoto** = palette-swap keep (black flatland). **Kumamoto** = *ishigaki* mastery map. **Edo** = scale fantasy / shogunal endgame. **Yamajiro** = early-era tech tree.

---

## 5. Grounded ~7-layer stack (outer → core)

Use this as the default Japanese defence onion (Himeji-grammar, adaptable):

| Layer | Name | Player-facing job |
|---|---|---|
| **1** | **Outer town (*jōkamachi*) + outer moat (*sotobori* / hori)** | Economic tissue + first water/dry ditch; burning town optional morale event |
| **2** | **Masugata / koguchi gate traps (+ optional umadashi screen)** | Right-angle kill-boxes; sally points; first hard chokes |
| **3** | **Outer baileys (*sannomaru* and named outer *kuruwa*)** | Retainer housing / barracks HP; long walls; false paths |
| **4** | **Mid baileys (*ninomaru*) + spiral / maze connectors** | Path-length tax; 180° turns; single-file lanes |
| **5** | **Yagura line + plastered earthen walls on *ishigaki*** | Distributed towers; *sama* fire; *ishi-otoshi* anti-climb |
| **6** | **Honmaru** | Final compound; lord’s HQ; last gate set |
| **7** | **Tenshu / tenshu-gun (daitenshu + kotenshu cluster)** | Brand keep boss; observation; ideological surrender condition |

**Optional bleed lanes:** wells / Sangoku-bori fire-water; hidden guard rooms; dead-end bailey pockets; postern sally from *umadashi*.

**Attic comparison note for designers:** This is **bailey–gate–turret grammar**, not Greek Long Walls corridor grammar and not Viking turf ring grammar. Do not paste European curtain HP bars onto layer 5 by default.

---

## 6. Obscure / high-value details (spice, still tagged)

1. **Roof count lies:** Himeji looks 5-tiered, hides 6 + basement — UI can show “false storey” armour **[ARCH]**  
2. **Iroha gate names:** literacy / mnemonic as navigation puzzle **[ARCH]**  
3. **Sangoku-bori as firefighter, not only moat:** interior water for fire defence **~2,500 m²** **[ARCH]**  
4. **Azuchi atrium / stūpa theories:** spectacular, contested — label **[MOD]** if used  
5. **Osaka cannon:** European-style ordnance in Winter Campaign narrative — unlock rare siege ammo **[LIT]**  
6. **Moat fill as victory:** Osaka peace clause as unique win condition **[LIT]**  
7. **Matchlock range moat:** Matsumoto **~60 m** interpretive band for gun-era map tuning **[MOD]**  
8. **Shōjibori grids:** Yamanaka-style dry moat partitions as anti-lateral-move tiles **[ARCH]**  
9. **Unejō tatebori:** mountain “furrow” dry moats as ladder denial on contours **[ARCH]**  
10. **Edo empty plinth:** tenshu-dai after **1657** as haunted boss arena with no tower — rare aesthetic **[ARCH]**  
11. **White plaster ≠ fragility:** *shikkui* as fire-resist skin **[ARCH]**  
12. **Himeji never storm-tested in spiral mode:** design confidence ≠ battle proof **[MOD]**  
13. **Building inventory 82 vs 83:** teach designers to preserve source disagreement **[ARCH]**  
14. **Umadashi as airlock:** cavalry / infantry sally without opening the main gate fully **[MOD]** JAANUS  
15. **Concrete Osaka keep:** modern reconstruction honesty prevents “authentic Toyotomi tower” false advertising **[ARCH]**  
16. **Kumamoto 2016:** earthquake as anti-ishigaki special rule — stone can fail **[ARCH]**  
17. **Nishinomaru / west bailey politics at Himeji:** residential + later military reuse layers — strip Meiji noise for Sengoku skin **[ARCH]** UNESCO integrity notes  
18. **Seismic raft under Himeji donjon:** modern RC foundation intervention admitted in UNESCO authenticity text — preservation, not original **[ARCH]**

---

## 7. Do / Don’t

### 7.1 Do
- Dual-ship **Himeji military brand keep** + optional **Azuchi origin** and/or **Osaka siege campaigns**
- Build maps from **nawabari → maru → koguchi → yagura → tenshu**, not from European curtain templates
- Prefer **Himeji 46.4 m / 20–34.5–2.7 m moat** set with citation notes
- Use **130→325 m** spiral as the path-length tutorial number
- Distinguish **yamajiro / hirajiro / hirayamajiro** in era unlocks
- Let *yagura* be the workhorse towers; tenshu is the brand
- Tag reconstruction keeps (Osaka concrete; post-quake Kumamoto) honestly
- Preserve metric **ranges** where sources disagree (Azuchi height; Edo tenshu height; Himeji 82/83)

### 7.2 Don’t
- Default to a **European stone curtain castle** with corner round towers as “Japanese” **[MOD]**
- Make **katana** the primary battlefield weapon fantasy for siege defence **[FOLK]**
- Field a **ninja army** as standard garrison doctrine **[FOLK]**
- Treat **bushidō** slogans as Edo building specs **[FOLK]**
- Equate **Himeji white** and **Matsumoto black** as mere paint — finishes encode brand + fire/weather logic **[ARCH]**
- Present **Azuchi** as an intact climbable equal to Himeji without archaeological honesty **[ARCH]**
- Use today’s **Osaka** keep silhouette as Hideyoshi-original without a reconstruction flag **[ARCH]**
- Merge Japan Atlas **33 m** and site **46.4 m** without explaining measuring conventions **[MOD]**
- Assume every maze castle was battle-proven like a constant warzone after **1615** **[MOD]**
- Clone **Beaumaris / Krak** concentric grammar into the Japanese faction by default (save that for a European pack)

---

## 8. TD systems hooks (brief)

| System | Japanese hook |
|---|---|
| Pathing | Spiral / masugata forced turns; wrong-gate soft paths |
| Tower types | *Yagura* tiers; *tamon* long fire; corner *sumi-yagura*; tenshu ultimate |
| Anti-climb | *Ishigaki* curve + *ishi-otoshi* |
| Range bands | Bow *sama* vs matchlock *sama*; moat widths as range tutors (20 / 34.5 / 60 m sets) |
| Siege timers | Wells (Kumamoto density fantasy); Sangoku-bori fire water; food in baileys |
| Win cons | Break tenshu brand; OR fill moats (Osaka); OR burn plaster skin |
| Era unlocks | Yamajiro earth → Azuchi prototype tenshu → Himeji maze peak → Edo mega-spiral |
| Hazards | Fire (timber), earthquake (ishigaki), betrayal diplomacy (Osaka fill) |

---

## 9. Bibliography (starting set)

### Himeji / UNESCO
- UNESCO World Heritage Centre, *Himeji-jo* (WHC **661**) — OUV, **107 ha** / **143 ha** buffer, criteria (i)(iv), building-count integrity notes (**82** in OUV text; **83** in some blurbs) **[ARCH]**
- Japan Atlas (Web Japan), Himeji Castle card — moat **34.5 / 20 / ~2.7 m** figures **[ARCH]** secondary government atlas
- Himeji municipal / castle management materials; conservation history (**1934** onward; seismic studies post-**2006**) as cited in UNESCO management text **[ARCH]**
- World History Encyclopedia / standard encyclopedia syntheses for gate counts and **130 / 325 m** approach — use as **secondary**, cross-check on site **[MOD]**

### Anatomy / typology
- JAANUS entries: *ishigaki*, *umadashi*, *umadashi koguchi*, *kaku-umadashi*, *azuchi umadashi*, related castle terms **[MOD]**
- Nippon.com, “Japanese Castles’ Defensive Features” — *nawabari*, *kuruwa*, *hori* types, *dorui*, *koguchi*, *masugata*, *yagura* overview **[MOD]**
- Agency for Cultural Affairs / National Treasure designations for surviving keeps (Himeji, Matsumoto, etc.) **[ARCH]**

### Azuchi / Osaka / sieges
- *Shinchō Kōki* and related Oda chronicles for Azuchi prestige descriptions **[LIT]**
- Naitō Akira (1976) Azuchi tenshu reconstruction thesis and subsequent critiques (Miyakami et al.) — height / atrium debates **[MOD]**
- Siege of Osaka narratives in Tokugawa/Toyotomi chronicle tradition; modern military histories summarizing Winter **1614** / Summer **1615**, Sanada-maru, moat filling **[LIT][MOD]**

### Other sites
- MLIT multilingual cultural-resource database texts for Matsumoto (**29.4 m** keep; **115** loopholes; **11** *ishi-otoshi*; **~60 m** inner moat interpretive; **~390,000 m²** compound tradition) **[ARCH]**
- Edo-Tokyo Museum interpretive materials; Imperial Palace East Gardens on-site tenshu-dai metrics (**41 × 45 × 11 m**) **[ARCH]**
- Kumamoto Castle official repair / earthquake documentation (2016+) **[ARCH]**
- Yamanaka Castle *shōjibori* site presentations **[ARCH]**

### Comparative / method
- Standard handbooks on Japanese castle architecture (*tenshu* typology; *hirajiro* / *yamajiro* / *hirayamajiro*) **[MOD]**
- Companion TD briefs in this repo: `ANCIENT-GREEK-FORTIFICATIONS-AND-DEFENCE.md`, `VIKING-AGE-FORTIFICATIONS-AND-DEFENCE.md` — for parallel keep + 7-layer formatting only

---

## 10. Quick designer cheat-sheet

| Need | Best evidence pick |
|---|---|
| Default military brand keep | **Himeji** daitenshu **~46.4 m**, white plaster, kotenshu cluster |
| Moat tutorial numbers | Himeji **~20 m** avg / **~34.5 m** max / **~2.7 m** deep |
| Spiral path lesson | **130 m** straight → **325 m** walk |
| Prototype tenshu myth | **Azuchi** (lost; height debate ~38–46 m) |
| Siege campaign | **Osaka 1614–15** + Sanada-maru + moat-fill |
| Flatland black keep | **Matsumoto** **~29.4 m**; **115** sama |
| Ishigaki climb hell | **Kumamoto** curved faces |
| Mega-scale endgame | **Edo** **~16 km** outer perimeter class; empty tenshu-dai |
| Early era | **Yamajiro** dorui + tatebori (no white mega-keep) |
| Gate kill-box | **Masugata** (+ **umadashi** sally screen) |
| Anti-climb gadget | **Ishi-otoshi** |
| Loophole DPS | **Sama** (bow → matchlock eras) |

---

## 11. Keep recommendation + 7-layer summary (executive)

**Keep recommendation:** Dual centerpiece — **Himeji Castle (military aesthetic brand keep: daitenshu ~46.4 m, UNESCO, white plaster, connected kotenshu cluster)** + optional **Azuchi (prototype / mostly lost)** and/or **Osaka 1614–15 siege campaign maps**.

**7-layer Japanese stack:**  
**(1)** Outer town (*jōkamachi*) + outer moat (*sotobori* / *hori*) → **(2)** *Masugata* / *koguchi* (+ optional *umadashi*) → **(3)** Outer baileys (*sannomaru* / outer *kuruwa*) → **(4)** Mid baileys (*ninomaru*) + spiral/maze paths → **(5)** *Yagura* + walls on *ishigaki* (*sama* / *ishi-otoshi*) → **(6)** *Honmaru* → **(7)** *Tenshu-gun* (daitenshu + kotenshu).

---

*End of fortifications brief. Metric claims are tied to ARCH/LIT/MOD tags; where handbooks disagree (Himeji 82 vs 83 buildings; 33 m vs 46.4 m measuring conventions; Azuchi height; Edo tenshu height; Kumamoto turret censuses), ranges are preserved rather than false precision invented. Do not ship a European curtain-castle as the default Japanese keep.*
