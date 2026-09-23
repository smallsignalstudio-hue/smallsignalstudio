# AZTEC / MEXICA MILITARY UNITS, TACTICS & TENOCHTITLAN FORTIFICATIONS — Research Brief (Tower-Defence Game Bible)

> English research notes for campaign / TD design. Classical Nahuatl terms given with **macron-length vowels** on first mention where securely reconstructible, then a working form. Prefer **Mexica** / **Tenochca** for the island capital’s people; **Triple Alliance** (*Ēxcān Tlahtōlōyān*) for the imperial coalition (Tenochtitlan–Tetzcoco–Tlacopan); “Aztec” only as a modern umbrella (post-conquest / popular usage).  
> **Method:** Separate **Spanish chronicler** claims (Díaz, Cortés’s letters, Sahagún/Florentine Codex, Durán / *Crónica X* tradition) from **archaeology** and **pictorial manuscripts** (esp. Codex Mendoza). Flag debates. Do **not** treat Osprey plates, Age of Empires / Total War rosters, or Assassin’s Creed kit as evidence.

### Source classes (use throughout)
| Tag | Meaning |
|---|---|
| **CHR** | Spanish / mestizo chroniclers & letters: Hernán **Cortés** *Cartas de relación*; Bernal **Díaz del Castillo** *Historia verdadera*; Francisco **Aguilar**; Andrés de **Tapia**; López de **Gómara** (secondary, Cortés’s chaplain) — eyewitness or near-eyewitness but **partisan, number-inflating, culturally alien** |
| **ETH** | Ethnohistorical compilations with Indigenous informants: Bernardino de **Sahagún** *Historia general* / **Florentine Codex**; Diego **Durán** *Historia* (heavily *Crónica X*); **Codex Mendoza** (~1541, Indigenous painters + Spanish glosses for Viceroy Mendoza); Chimalpahin; Alva Ixtlilxóchitl (Tetzcocan bias) |
| **PIC** | Pictorial manuscripts without Spanish essay prose as primary layer (Mendoza warrior folios; Lienzo de Tlaxcala; Magliabechiano; etc.) — high value for **costume / rank signalling**, lower for doctrine |
| **ARCH** | Archaeology: Proyecto Templo Mayor (Matos Moctezuma; López Luján et al.); Hueyi Tzompantli finds; Basin of Mexico settlement / chinampa studies; weapon finds (rare for organic arms) |
| **MOD** | Modern scholarship: Ross **Hassig**; Inga **Clendinnen**; Barry **Isaac**; Marco A. **Cervera Obregón** (weapons / experiment); Leonardo **López Luján** / Eduardo **Matos Moctezuma**; Barbara **Mundy**; Matthew **Restall**; Camilla **Townsend**; recent *The Americas* work on brigantine logistics |

**Bias rule for design:** Prefer **ARCH/PIC** for “what the city and kit looked like”; use **ETH** for institutions and rank ladders (Mendoza is gold for TD upgrade trees); treat **CHR** as combat narrative with **numbers, motives, and “savagery” framing** heavily discounted. Never let Díaz’s horse-decapitation rhetoric become a physics engine without an experimental-archaeology asterisk.

**Orthography note:** Classical Nahuatl length is marked with macrons where conventional in modern scholarship (e.g. *Huēyi Teōcalli*, *xōchiyāōyōtl*). Glottal stops (*saltillo*) are often unmarked in popular spellings; do not invent them where sources disagree. Spanish colonial spellings (*macana*, *estólica*) are glosses, not Nahuatl.

---

## 0. Framing: what “the Aztec army” was

**What it is.** Not a European standing army. Late Postclassic central Mexican warfare under the Triple Alliance combined: (1) **calpulli**-based commoner levies with lifelong school training; (2) **noble / professional warrior societies** ranked by captives taken; (3) allied and subject contingents; (4) ritual–political aims (hegemony, tribute, captive display) intertwined with lethal conquest war. Peak descriptive evidence: mid–late 15th c. through the **1521** siege.

**Evidence quality.** High for **costume ranks** (Mendoza) and **city form** (chroniclers + Templo Mayor archaeology); medium for **battlefield doctrine** (Hassig reconstructions from ethnohistory); low for exact TO&Es, drill manuals, and “never kill, only capture” absolutism (see controversies below).

**Game-use potential:** Faction is a **meritocracy-of-captives** layered on a **lake fortress**—unlock trees by captive count; map geometry is causeway chokepoints + canoe flanks + temple summit boss, not a land castle with concentric stone curtains.

---

## 1. Soldier types & training

### 1.1 Commoners (*mācēhualtin*) and the levy (*yāōquizqueh*)

**What.** Most fighters were commoners (*mācēhualli*, pl. *mācēhualtin*) drafted through calpulli obligations when war was declared. Lowest support tier: porters (*tlamemeh*) carrying arms and supplies; youths as shield-bearers / messengers before full warrior status. Full “those who have gone to war”: *yāōquizqueh*.

**Placement.** Bulk of the line in conquest campaigns; rear ranks after elite shock societies entered melee (Hassig’s reconstructed entry order).

**Sources.** Codex Mendoza education/warrior folios (**PIC/ETH**); Sahagún Books 3, 8 (**ETH**); Hassig, *Aztec Warfare* (1988) (**MOD**).

**Controversies.**
1. “No standing army” is **oversimple**: elite societies and frontier garrisons were effectively full-time; the mass was seasonal (**MOD** Hassig).
2. Popular claim that **every** male was a warrior overstates: training was near-universal for boys, but campaign service was selective and status-graded (**ETH/MOD**).

**Game-use:** Cheap wave infantry; upgrade path via “first captive” into *tlamanih*; calpulli banners as squad IDs.

---

### 1.2 Schools: *calmecac* and *tēlpochcalli*

**What.**
| School | Classical form | Typical intake | Military role |
|---|---|---|---|
| **Telpochcalli** | *tēlpochcalli* “youth house” | Commoner youths (~15–20; ward-based) | Primary military socialization; veteran instructors; youths accompany campaigns as porters/shield-bearers before taking captives |
| **Calmecac** | *calmecac* “lineage / priest house” | Mostly nobles (*pīpiltin*); some talented commoners (Sahagún) | Leadership, priestly careers, elite warfare; attached to temples (Tenochtitlan’s calmecac linked to Quetzalcoatl) |

Formal weapons training commonly cited from **~age 15**; noble boys often entered calmecac earlier (king’s sons ~5 in some accounts—treat ages as ethnohistorical tradition, not census).

**Sources.** Codex Mendoza fols. ~56–65 (**PIC/ETH**); Sahagún (**ETH**); Hassig 1988/1992 (**MOD**); archaeological calmecac remains near Templo Mayor reported 2007+ (**ARCH**—structure ID debated in detail).

**Controversies.**
1. Sharp **noble-only calmecac vs commoner-only telpochcalli** is blurred in Mendoza and Sahagún (mobility both ways) (**ETH**).
2. Exact curricula and ages vary by source; Spanish glosses moralize “idleness” (**CHR/ETH** filter).

**Game-use:** Two tech buildings—Telpochcalli = mass infantry XP; Calmecac = officer aura / ritual buffs / elite unlocks. Youth “porter” units that convert after surviving a mission.

---

### 1.3 Captive-count ranks (attested ladder)

**What.** Promotion and costume (*tlahuiztli*) tracked **enemies captured alive** (*malli* / pl. *māltin*), not kills. Attested ladder in Mendoza pictorial sequence (with Spanish glosses):

| Captives (approx.) | Grade / costume notes | Nahuatl labels (as used in scholarship) |
|---|---|---|
| 0 → trainee | Top-knot hair signalling no captive yet | Youth of telpochcalli |
| **1** | Plain *mācuahuitl* + undecorated *chīmalli*; distinctive capes / loincloth awards | *tlamanih* “captors” |
| **2** | Red-and-black *tlahuiztli*; conical Huastec-style cap (*cuextecatl* costume tradition after Huastec wars) | Often called *cuextecatl* in secondary lit. |
| **3** | Butterfly back-device | *pāpalōtl* (“butterfly”) |
| **4** | Entry to Eagle / Jaguar orders | *cuāuhtli* / *ocēlōtl* → joint *cuāuhocēlōtl* |
| **5–6** | Otomi society grade | *otomitl* / pl. *otōntin* |
| **6+** + many deeds | Shorn Ones | *cuāchic* / pl. *cuāchicqueh* |

**Sources.** Codex Mendoza warrior folios (**PIC**—primary for the ladder); Aguilar-Moreno handbook synthesis; Mexicolore summaries citing same (**MOD** popularization of Mendoza).

**Controversies.** Exact captive thresholds for Otontin vs Cuachicqueh vary slightly across secondary summaries; **trust Mendoza images + Hassig** over web “army list” blogs. Whether commoners could enter Otontin/Cuachicqueh: often said **noble-restricted** for the highest two—flag as ethnohistorical claim, not archaeology.

**Game-use:** **Captive meter** as XP (not kill count)—unique TD hook. Skins unlock at 1/2/3/4/5/6 captives.

---

### 1.4 Eagle (*cuāuhtli*) and Jaguar (*ocēlōtl*) warriors

**What.** Premier **warrior societies** open (in sources) to outstanding commoners and nobles after ~**four** captives; jointly *cuāuhocēlōtl*. Visual kit: eagle—feathered helmet with open beak; jaguar—pelt / jaguar-head helmet over face opening. Associated with solar / Tezcatlipoca–jaguar symbolism in later interpretive literature (iconographic reading, not a field manual).

**Placement.** Full-time elite after admission; command roles; merchant escort; palace/city guard functions appear in ethnohistory. In Hassig’s melee sequence: enter **after** Cuachicqueh / Otontin shock tip, **before** mass commoners.

**Sources.** Mendoza (**PIC**); Florentine Codex (**ETH**); terracotta eagle-warrior sculptures (**ARCH** aesthetic, not TO&E); Hassig (**MOD**).

**Controversies.**
1. “Knights” is a **European analogy**—use *warrior society* / *order*.
2. Divine patronage splits (Huitzilopochtli vs Tezcatlipoca) are tidy in popular texts; treat as **interpretive**.
3. Noble vs commoner suit materials (full featherwork vs skin over cotton) appear in secondary synthesis—verify per image set before hard-coding classism into gear stats.

**Game-use:** Mid-elite tower garrison / shock infantry; paired skins with same stats, different auras (day/sun vs night/terror) if designers want cosmology without claiming drill-manual truth.

---

### 1.5 Otomi grade (*otōntin*) vs ethnic Otomi

**What.** Honorific warrior grade named for the reputation of ethnic **Otomi** fighters; **not** identical to ethnicity. Sources stress vow-like ferocity; paired institutionally with Cuachicqueh as supreme vanguard under high generals.

**Sources.** Florentine Codex Book 8 praise contexts (**ETH**); Hassig battle-order reconstruction (**MOD**); ethnic Otomi as allies/mercenaries also real—**disambiguate in UI**.

**Controversies.** Text “otomitl” in chronicles can mean grade **or** ethnicity—context required (**ETH/MOD**).

**Game-use:** Elite spear tip; label clearly “Otomi *order*” vs “Otomi *allies*” as two units.

---

### 1.6 Shorn Ones (*cuāchicqueh*)

**What.** Highest prestige society: head shaved except braid over left ear; face/head paint half blue / half red or yellow; yellow *tlahuiztli*; shock troops; sources say they refused captaincies to stay in the fight; oath **not to step back** on pain of death by comrades. Entry: many captives + numerous brave deeds (often summarized as 6+ and ~20 deeds—**ETH** thresholds, not a statute book).

**Sources.** Mendoza (**PIC**); Sahagún (**ETH**); Hassig (**MOD**).

**Controversies.** Romantic “special forces” framing is modern; core costume + prestige + no-retreat ethic is source-grounded.

**Game-use:** Ultimate melee unlock; “No Step Back” passive (cannot be force-retreated; dies instead / enrages). High DPS, high risk.

---

### 1.7 High command titles (attested)

| Title | Gloss (conventional) | Role (ethnohistorical) |
|---|---|---|
| *tlàtoāni* / *huei tlatoani* | Speaker / great speaker | Supreme war decision |
| *tlacochcalcatl* | “Man of the house of darts” | High general / field marshal analogue; often led when ruler busy |
| *tlacateccatl* | General / “cutter of men” (conventional glosses vary) | Second high general; associated with *tlacatecco* military quarter; Mendoza shows him with *pāmitl* |
| *cihuacoatl* | “Woman serpent” (office) | Powerful administrative/military office (Tlacaelel tradition)—**political**, not a line unit |

**Sources.** Codex Mendoza fol. 67r officers (**PIC**); Hassig (**MOD**); succession anecdotes in chronicles (**ETH/CHR**—Tlacaelel’s centrality partly *Crónica X* ideology).

**Game-use:** Hero generals with unique *pāmitl* banners; if slain, named successor spawns (attested practice of pre-naming replacements).

---

## 2. Weapons, armour, banners

### 2.1 *Mācuahuitl* (obsidian-edged wooden sword)

**What.** Wooden billet with grooves holding prismatic **obsidian** blades (often 6–8 per side in reconstructions; blade size frequently **exaggerated** in museums/games). Typical length reconstructions ~70–80 cm for common type; longer “two-handed” forms appear in some pictorials. Spanish called it *macana* (Taíno loan).

**Sources.** Pictorials: Mendoza, Durán, Lienzo de Tlaxcala, Ixtlilxóchitl (**PIC/ETH**); chroniclers on wounds to horses (**CHR**—sensational); Cervera Obregón 2006 *Arms & Armour* (**MOD** + experiment); **no intact battlefield macuahuitl** with full archaeological context survives (**ARCH** gap).

**Controversies.**
1. “Could decapitate a horse” = **CHR** set-piece (Díaz tradition / later amplification). Experimental work shows blades **shatter** against good cotton armour and struggle vs steel (**MOD** Cervera).
2. Designed “only to wound for capture”: **overstated**. Captives mattered, but weapons were lethally capable; strategy ≠ edge geometry (**MOD** recent captive reevaluations).
3. Late Postclassic innovation thesis (Cervera) vs deeper Mesoamerican club traditions—flag as live research.

**Game-use:** High bleed / armour-shred vs unarmoured; **brittle** durability vs metal or thick ichcahuipilli; anti-cavalry bonus with a “chronicle exaggeration” tooltip optional.

---

### 2.2 *Tepoztōpīlli* (obsidian-bladed spear / polearm)

**What.** Long wooden shaft (up to ~2 m in modern descriptions) with broad head edged in obsidian blades—thrusting and tearing. Shown with elite officers in Mendoza.

**Sources.** Pictorials (**PIC**); Cervera / Mexicolore synthesis (**MOD**); chroniclers’ “long lances” (**CHR**).

**Controversies.** Exact blade arrangement and fencing style = reconstruction.

**Game-use:** Anti-charge / causeway phalanx tower unit; longer reach than macuahuitl.

---

### 2.3 Atlatl + *tlacochtli* (dart-thrower)

**What.** Spear-thrower (*ahtlatl* / common *atlatl*) launching heavy darts (*tlacochtli*). Deep Mesoamerican pedigree (Teotihuacan murals onward). Ethnohistory places atlatl waves before melee; sometimes marked as elite/prestigious.

**Sources.** Codices (**PIC**); experimental range claims vary (popular figures up to ~100–120 m—**treat as modern estimate**); Cervera / Atlatl México experiments vs armour (**MOD**).

**Controversies.** Handedness with shield; how many darts carried (often “few”); prestige vs mass use.

**Game-use:** Mid-range armour-piercing volley before melee; reload slow; strong on causeway flanks from canoes.

---

### 2.4 Bow (*tlahhuītōlli*) and sling (*tēmātlatl*)

**What.** Self-bow ~1.5 m class in secondary descriptions; arrows (*yāōmītl*) with stone/obsidian/bone points. Sling of maguey fibre; clay or stone shot—Díaz stresses severity of sling fire even vs armoured Spaniards (**CHR** qualitative, useful).

**Sources.** Durán / pictorial bows (**PIC/ETH**); Díaz on slings (**CHR**); Hassig (**MOD**).

**Controversies.** Quiver use vs hand-held arrow bunches; bow+shield practicality.

**Game-use:** Cheap mass towers (sling = high RoF stun; bow = range). Sling as “anti-conquistador” early counter before steel meta.

---

### 2.5 Other melee (attested / pictorial)

- *Quauholōlli*: ball-headed club (wood/stone/copper head)—skull-fracture impact weapon (**PIC/ETH**).
- *Tlāximaltepōztli*: axe (stone/copper)—less iconic than macuahuitl.
- *Tecpatl*: flint/obsidian knife—**sacrifice and sidearm**; do not make it the default infantry sword.
- Blowgun (*tlacalhuazcuahuitl*): primarily **hunting** with toxin darts—weak as mass war weapon (**ETH**).

**Game-use:** Club = blunt vs cotton; knife = priest unit / execution finisher (handle sacred violence carefully in tone).

---

### 2.6 Cotton armour *ichcahuīpīlli* and suits *tlahuiztli*

**What.** Quilted cotton armour, often described as brine-soaked so salt crystallizes in the batting—one to two fingers thick in secondary accounts. Spaniards adopted cotton armour in Mesoamerica. Over-armour costume: *tlahuiztli* (rank suit); some nobles’ tunic *ēhuatl*. Helmets *cuacalalatli* (hardwood animal/deity forms)—protection **and** status.

**Sources.** Chroniclers on cotton armour (**CHR**); Mendoza suits (**PIC**); experimental resistance vs macuahuitl/atlatl (**MOD** Cervera—cotton performs well vs obsidian slash).

**Controversies.** Salt-brine recipe is **widely repeated ethnohistorical/secondary**; archaeological textile survival is poor—do not claim lab-proven period manufacturing for every detail. Feather suits’ combat practicality vs parade function debated (**MOD**).

**Game-use:** Baseline armour upgrade; “salted cotton” tech vs obsidian; weak vs steel crossbow/arquebus (historical asymmetry without needing fantasy).

---

### 2.7 Shields *chīmalli* and banners *pāmitl*

**What.** Combat shields of wood (*cuauhchimalli*) or cane (*otlachimalli*); ornate feather shields for display. **Pāmitl**: back-worn (or hand-held) identifying banners for officers and prestigious warriors—command-and-control + intimidation (compare functionally to Japanese *uma-jirushi* only as analogy).

**Sources.** Mendoza officers with *pāmitl* (**PIC**); surviving feather shields in European collections (often **ceremonial**); chroniclers (**CHR**).

**Controversies.** How many battlefield shields were “art objects”; European collectors’ bias toward fancy pieces.

**Game-use:** *Pāmitl* as visible officer auras / rally radius; losing the banner = morale shock. Shield patterns as unlockable cosmetics tied to captive rank.

---

## 3. Flower wars (*xōchiyāōyōtl*)

### 3.1 What ethnohistory says

**What.** *Xōchiyāōyōtl* (“flower war / flowery war”) denotes arranged, often **non-conquest** hostilities—especially the long conflict between the Triple Alliance and the Tlaxcala–Puebla valley polities (Tlaxcala, Huexotzinco, Cholula, etc.). Durán / *Crónica X* tradition ties institutionalization to **Tlacaelel** after the 1450s famine, framing sacrificial supply. Combatants included elites and skilled warriors; spectacle, captive-taking, and intimidation loom large in Spanish-period narratives.

**Difference from conquest war (*yāōyōtl* aimed at subjugation).** Conquest war seeks **altepetl** surrender, tribute, and political incorporation. Flower war, in the sources that use the term, is war **without** immediate annexation—training, attrition, status, and captive flow are the motives stressed by modern analysts (and variously by the sources themselves).

**Sources.** Durán (**ETH**, ideological); Chimalpahin (**ETH**, crucial for term usage); Sahagún on martial education (**ETH**); Cortés/Díaz on Tlaxcalan enmity (**CHR**—see why allies existed in 1519).

### 3.2 Controversies (do not flatten)

| Position | Claim | Notes |
|---|---|---|
| **Ritualist / sacrificial-primary** | Flower wars staged mainly to feed gods with captives | Strong in older popular history; leans on Durán–Tlacaelel story |
| **Isaac 1983 geopolitical** | Alliance **failed to conquer** Tlaxcala bloc despite incentive; “flower” label misread as pure ritual | Undercuts “they could have conquered anytime but preferred flowers” |
| **Hassig** | Training, attrition, propaganda, low-intensity pressure while busy elsewhere; captives useful but not the sole strategic driver | Still mainstream military-anthropology frame |
| **Recent captive reevaluation** | Need for captives **not** large enough to dictate battlefield conduct; captives opportunistic vs strategic | Pushes back on “capture-only combat” myth |
| **Chimalpahin-focused readings** | “Flower war” = wars not aimed at conquest; training emphasized | Term history matters—do not assume one meaning in all texts |

**Historicity:** The **long hostile relationship** with Tlaxcala is solid (**CHR/ETH**). The neat story that Mexica **chose** endless ritual war instead of conquest is **debated and partly rejected**. Sacrificial cosmology was real; turning it into the master key for all strategy is **chronicler + modern stereotype**.

**Game-use:** Alternate mode—“Flower War skirmish” with captive scoring and no base destruction vs “Conquest War” with tribute and map painting. Tlaxcala as the rival that never falls until Spanish campaign DLC—historically grounded tragedy engine.

---

## 4. Tenochtitlan as fortress-city

### 4.1 Lake Texcoco setting

**What.** Twin island settlements **Tenochtitlan** and **Tlatelolco** in Lake Texcoco (Basin of Mexico), expanded with fill and **chinampas** (human-made wetland agricultural plots; Nahuatl *chināmitl* related forms). Fresh water via aqueducts from **Chapultepec** springs (not the brackish lake alone). Population: commonly cited **150,000–250,000+** for the island complex—**estimates**, not a census (**MOD** debate).

**Sources.** Cortés Second Letter; Díaz “Venice” awe (**CHR**); Mundy on maps/urban form (**MOD**); chinampa archaeology/ethnohistory (**ARCH/ETH**).

**Controversies.** Lake levels, salinity zones, and exact shoreline shifted historically; Nuremberg 1524 map is schematic propaganda/geography hybrid (**MOD** Mundy).

**Game-use:** Water = both moat and enemy canoe highway. Chinampa tiles = food income + fragile outer farms.

---

### 4.2 Three main causeways + removable bridges

**What.** Principal causeways typically listed:
1. **Tepeyacac** (north; toward Tepeyac)
2. **Tlacopan / Tacuba** (west)
3. **Iztapalapan** (south; Cortés’s first entry route)

(East more open to lake / shorter links; Tetzcoco shore opposite.) Causeways pierced by gaps with **wooden spans that could be removed or raised**—not medieval counterweight drawbridges. Gaps allowed canoe traffic and created **moat segments**. Spanish escape (“Noche Triste,” 1520) failed in part at these gaps.

**Sources.** Cortés letters; Díaz; Francisco de Aguilar on removable wooden bridges (**CHR**); Mexicolore notes on *puente levadiza* translation traps (**MOD** popular); Mundy on causeway urbanism (**MOD**).

**Controversies.** Tower-like structures on European map views may be **European visual habits** (**MOD** caution). Exact bridge engineering underspecified—no surviving Aztec bridge treatise.

**Game-use:** Classic TD lanes = three causeways; player can **pull bridges** (temporary lane breaks) at stamina/food cost; enemies with canoes bypass.

---

### 4.3 Districts, canals, sacred precinct

**What.** City divided into four great quarters plus Tlatelolco; dense canal grid. **Sacred precinct** (*teocalli* compound): rectangular ceremonial core with many temples, calmecac, ballcourt, skull racks, weapon houses (*tlacochcalco*), etc. Ethnohistorical/architectural estimates for the enclosing platform ~**340 × 360 m** order (~20 ha interior)—**reconstruction from texts + limited archaeology** (López Austin & López Luján syntheses).

**Coatepantli (“serpent wall”):** popularly imagined as full precinct curtain. Archaeology at Templo Mayor shows **paired serpent walls** flanking the Great Temple (Tlaloc blue north / Huitzilopochtli ochre south readings)—**not** simply a European castle wall around everything (**ARCH** vs older **CHR** readings).

**Sources.** Sahagún Book 2 appendix lists (**ETH**); López Luján / Matos (**ARCH/MOD**); coatepantli scholarship revisions (**MOD**).

**Controversies.** Number of precinct gates (3–4 in sources); whether coatepantli = “sacred boundary” universally—**no consensus**.

**Game-use:** Districts as expandable defence rings; sacred platform as hard inner wall with gate shrines; coatepantli as **thematic** serpent-wall segment at temple, not full-map cheat wall.

---

### 4.4 *Huēyi Teōcalli* (Templo Mayor) — archaeological dimensions

**What.** Twin stair pyramid: **south** shrine **Huitzilopochtli** (war/sun); **north** shrine **Tlāloc** (rain). Seven major enlargement stages (I–VII) with additional partial enlargements; Stage VII is what Cortés saw—almost destroyed after 1521.

| Measure | Best archaeological/scholarly estimate | Notes |
|---|---|---|
| Height (final) | **~45 m** | López Luján & Michelle De Anda AutoCAD estimate; museum markers; **not** the popular 60 m conflation |
| Base (final, N–S × E–W) | **~84 × 77 m** | INAH / Arqueología Mexicana / site markers |
| Older handbook figure | ~100 × 80 m | Still widely copied; treat as rounded outdated figure |
| Stair count (chroniclers) | ~114–120 steps | **CHR** (Cortés assault narrative); useful for “climb time” game feel |
| Construction | Enlargement by encasement | Each tlatoani stage as visual “upgrade skin” |

**Sources.** Matos Moctezuma excavations from 1978 (**ARCH**); López Luján publications (**ARCH/MOD**); Cortés/Díaz descriptions (**CHR**); Gods’ Collections / INAH summaries (**MOD**).

**Controversies.** Wikipedia-class “60 m” figures mix shrine+pyramid rhetoric with older guesses—**prefer ~45 m ARCH**. Stage-to-ruler correlations are partly historical inference.

**Game-use:** Natural final tower / boss ladder: dual stairs = two assault lanes to twin shrines; height as vertical wave map.

---

### 4.5 Hueyi Tzompantli (great skull rack) — archaeology vs chroniclers

**What.** Skull display scaffold(s) in the sacred precinct. Chroniclers give enormous counts (Andrés de Tapia’s figure led to later **60,000** upper-bound calculations by Ortiz de Montellano—still not a tally sheet). **ARCH (2015–):** circular tower/rack structure near Templo Mayor with **hundreds** of skulls recovered (reports ~650+ in progressive finds), including substantial **female** and some child remains—complicating “only captive warriors” stereotype.

**Sources.** Díaz/Tapia (**CHR**); INAH announcements (**ARCH**); bioarchaeological sex ratios in press/scholarship (**ARCH/MOD**).

**Controversies.** Scaling from excavated portion to 1519 totality; ritual meaning of non-male victims (myth reenactment vs war captive categories)—**active debate**.

**Game-use:** Optional grim monument building that boosts morale/fear aura; keep tone adult and non-edgelord; archaeology supports mixed victims—do not ship “warrior-only” if claiming historical mode.

---

## 5. Siege of Tenochtitlan, 1521 — what happened / what failed

### 5.1 Strategic situation

After **Toxcatl massacre** (1520), **Noche Triste** retreat, **Otumba**, Tlaxcalan re-alliance, and **smallpox** (late 1520; kills *Cuitlahuac*), Cuauhtémoc defends the island. Cortés rebuilds at **Tetzcoco**, assembles **~13 brigantines** (prefabricated in Tlaxcala, carried by thousands of Indigenous porters—**Native logistics are the story**, not Spanish magic ships).

**Sources.** Cortés Third Letter; Díaz (**CHR**); Hassig *Mexico and the Spanish Conquest*; Restall *Seven Myths*; 2023 *The Americas* / *TAM* on brigantine portage (**MOD**).

### 5.2 Spanish + allied tactics (what worked)

1. **Diplomatic fracturing** of the empire—Tlaxcala, Tetzcoco faction, Chalco, etc. War is as much **Indigenous civil war** as European conquest (**MOD** Hassig/Restall consensus).
2. **Brigantines** seize lake control → stop canoe flanking of causeway columns; escort inland advances.
3. **Cut Chapultepec aqueduct** → fresh water crisis; lake water inadequate/brackish (**CHR** + hydraulic studies).
4. **Simultaneous pressure** on multiple causeways (Alvarado, Olid, Sandoval sectors in Díaz’s organization).
5. **Breach causeways** so ships can cover both flanks (**CHR** Díaz).
6. Attrition, famine, disease inside the city; house-to-house fill-and-advance.

### 5.3 Mexica defence (what worked until it didn’t)

1. Removable bridges / causeway gaps as force multipliers.
2. Canoe swarms flanking causeway troops; roof-top missile fire; urban ambush (effective in 1520).
3. Stakes and ambushes against brigantines (some Spanish captains killed—**CHR**).
4. Sorties to help allies outside (Chalco, Chapultepec)—repeatedly beaten as coalition grew.
5. Refusal of early surrender; fierce block-by-block resistance into August 1521.

### 5.4 What failed (Mexica)

| Failure | Why it mattered | Evidence class |
|---|---|---|
| Loss of **lake supremacy** | Causeway defence assumed canoe dominance | **CHR** + **MOD** |
| Aqueduct cut | Thirst + disease synergy | **CHR** |
| Alliance collapse | Empire was hegemonic, not nation-state; subjects defected | **MOD** |
| Smallpox leadership/population shock | Cuitlahuac dead; labour/food systems crippled | **ETH/CHR** |
| Steel, powder, cavalry **on open approaches** | Less decisive *inside* canals, decisive in combined system | **CHR/MOD** |
| Static island hold | Allowed encirclement once water lost | **MOD** operational reading |

**Controversies.** “Guns germs steel” monocausalism vs Restall/Hassig Indigenous-agency models—**designers should centre Native allies and lake engineering**. Quetzalcoatl-mistaken-identity as explanation for Moctezuma’s policy is largely **discarded** as Spanish/rationalizing myth (**MOD** Townsend et al.).

**Game-use:** Campaign finale: defend while aqueduct HP and lake-control meter degrade; brigantine spawns as timed enemy elite; Tlaxcalan units as enemy swell, not “Spanish only.”

---

## 6. Best TD centerpiece: recommendation vs alternatives

### Recommendation: **Huēyi Teōcalli (Templo Mayor) as final keep**, with **causeway–lake system as the map**

**Why this wins.**
1. **Archaeologically real vertical monument** with dual stairs / dual shrines—readable “final tower.”
2. Cosmic-political heart of the empire (Huitzilopochtli war cult)—faction fantasy without inventing a citadel type they did not build.
3. Layers outward through precinct → city → causeways → lake match both history and TD grammar.
4. ~45 m / ~84×77 m is an authoritative scale for art bibles (**ARCH**).

### Strong alternative: **Causeway fort / bridge-gap defence as primary mode**

**Why viable.** The historically decisive “fortification” of Tenochtitlan was **hydrology + removable bridges**, not a land curtain wall. A causeway-primary TD would be *more* operationally authentic to 1521.

**Why secondary to Templo Mayor as *centerpiece*.** Causeway fights are lane puzzles; they need a symbolic climax object. Without the temple, the map risks becoming generic bridge-defence.

### Weaker alternatives
| Idea | Pros | Cons |
|---|---|---|
| Chapultepec aqueduct head as HQ | Real strategic target | Not a fortress-temple; poor final-climb fantasy |
| Tlatelolco market-temple | Twin-city depth | Less iconic globally; splits focus |
| Malinalco / Oztoma highland forts | Real imperial garrisons | Not Tenochtitlan; different biome |
| Pure chinampa maze | Unique look | Weak “tower” silhouette |

**Verdict for bible:** **Centerpiece = Templo Mayor summit shrines**; **signature mechanic = causeway gaps + canoe lake**; aqueduct as critical side objective.

---

## 7. Layered defence reconstruction (~7 historically grounded layers)

Aim: playable rings that map onto real urban-ritual geography—not European star forts.

| Layer | Historical anchor | Defence fantasy (keep grounded) | Failure mode (1521 lesson) |
|---|---|---|---|
| **L1 Lake Texcoco** | Water barrier; canoe fleet | Naval/canoe towers; amphibious enemy paths | Brigantines erase canoe advantage |
| **L2 Causeway gaps / removable bridges** | Wooden spans removed → moat segments | Manual bridge-pull ability; rebuild mini-game | Enemy portable bridges / filled gaps / ship cover |
| **L3 Causeway approach & land gates** | Long constrained lanes into city | Chokepoint towers, sling/atlatl galleries | Multi-lane sync pressure |
| **L4 Outer districts & canals** | Quarters, canal grid, chinampa edges | Canal ambush tiles; roof-archer bonus | House-to-house attrition, famine |
| **L5 District strongpoints / calpulli halls** | Local organization, armouries | Rally banners (*pāmitl*), militia spawners | Population collapse (disease meter) |
| **L6 Sacred precinct platform** | ~340×360 m class enclosure, entries | Gate shrines, priest buffs, tzompantli fear aura | Once breached, symbolic collapse |
| **L7 Templo Mayor bodies → summit twin shrines** | Terraces + dual sanctuaries (~45 m) | Vertical boss: Tlaloc lane & Huitzilopochtli lane | Stair assault under missile fire; final fall |

**Optional L0:** Chapultepec aqueduct as **external vital**—if destroyed, citywide debuff (historical).

**Coatepantli note:** Place serpent-wall art at **L6/L7 junction** (temple flanks), not as a full-city Great Wall clone (**ARCH** correction).

---

## 8. Obscure gameable details (high flavour, source-aware)

1. **Birth warrior kit:** Boy’s umbilical cord buried on a battlefield with tiny shield/arrow symbolism (**ETH**)—tutorial flavour, not literal baby soldiers.
2. **Messenger relay:** Posts ~4 km apart along roads (**ETH/MOD**)—map “whisper network” vision tech.
3. ***Quimichtin* (“mice”) spies:** Night-only, local dress, high risk—stealth units; capture = family punishment in lore tooltips (**ETH**—verify severity claims per source).
4. ***Pochteca* merchant-spies:** Trade caravans as intel; killing merchants as casus belli (**ETH**).
5. **Coronation war:** New tlatoani must campaign—failed war = legitimacy crisis (Tizoc tradition) (**ETH**).
6. **Pre-named successor generals:** Commanders appoint replacements before battle (**ETH**/Hassig)—hero respawn lore.
7. **Priest-borne god effigies** in the army (**ETH**)—banner units that buff but attract focus fire.
8. **Portable Spanish bridge** in Noche Triste vs Mexica removed spans—asymmetric bridge tech (**CHR**).
9. **Impaling stakes** vs brigantines (**CHR**)—anti-ship trap tech that can backfire.
10. **Salted cotton** adopted by Spaniards (**CHR**)—”enemy equips your armour” event.
11. **Dual shrine ladder count ~114–120**—climb stamina (**CHR**).
12. **Cuextecatl conical cap** after Huastec wars—costume tech unlocked by campaign geography (**PIC**).
13. **Butterfly *pāpalōtl* banner** at three captives—mid-tier back-banner unit (**PIC**).
14. **Yellow shorn-one suits + half-face paint**—highest intimidation aura (**PIC/ETH**).
15. **Tlacochcalco** “house of darts” as sacred weapon store in precinct (**ETH**)—armoury building that blesses atlatl ammo.
16. **Chinampa food vs siege famine**—farm tiles outside walls that enemies can ravage (**ARCH/ETH**).
17. **Women defending in 1521**—Aguilar notes women in final defence (**CHR**); Hueyi Tzompantli sex ratios complicate warrior-only sacrifice myths (**ARCH**)—optional defender skins with careful writing.
18. **Brigantine portage:** 8,000–10,000+ carriers in chronicles—enemy “wonder weapon” assembled off-map (**CHR/MOD**).
19. **Ixtlilxóchitl II / Tetzcoco split**—allied Native prince as rival hero (**CHR/ETH**).
20. **No surprise-attack etiquette** in some Restall/Pohl readings of Mesoamerican battle theatre—contrast with Spanish night ops (**MOD**—do not absolutize).
21. **Macuahuitl brittle vs ichcahuipilli**—experimental result as armour rock-paper-scissors (**MOD**).
22. **Obsidian supply**—weapon durability tied to quarry trade routes (Teotihuacan-era obsidian paths reused)—logistics mini-map.
23. **Tzompantli count inflation**—chronicle UI lie vs archaeological hundreds—meta commentary mode.
24. **Four-captive joint eagle-jaguar**—same rank, two cosmologies (**PIC**).
25. **Lake stakes + filled canals** late siege—terrain literally rewritten (**CHR**)—destructible/alterable pathing.

---

## 9. Quick reference — Nahuatl military lexicon (careful forms)

| Working form | Classical-style | Meaning |
|---|---|---|
| macuahuitl | *mācuahuitl* | Obsidian-edged wooden sword |
| tepoztopilli | *tepoztōpīlli* | Obsidian-bladed spear |
| atlatl | *ahtlatl* | Dart-thrower |
| tlacochtli | *tlacochtli* | Atlatl dart |
| ichcahuipilli | *ichcahuīpīlli* | Quilted cotton armour |
| chimalli | *chīmalli* | Shield |
| pamitl | *pāmitl* | Officer/warrior banner |
| tlahuiztli | *tlahuiztli* | Rank war suit |
| cuauhtli | *cuāuhtli* | Eagle (warrior) |
| ocelotl | *ocēlōtl* | Jaguar (warrior) |
| cuachicqueh | *cuāchicqueh* | Shorn Ones |
| otomitl / otontin | *otomitl* / *otōntin* | Otomi-grade warrior(s) |
| tlamanih | *tlamanih* | Captors (one-captive grade) |
| xochiyaoyotl | *xōchiyāōyōtl* | Flower war |
| calmecac | *calmecac* | Elite/priestly school |
| telpochcalli | *tēlpochcalli* | Youth military school |
| Hueyi Teocalli | *Huēyi Teōcalli* | Great Temple |
| coatepantli | *coatepantli* | Serpent wall |
| tzompantli | *tzompantli* | Skull rack |
| chinampa | from *chināmitl* complex | Wetland plot |
| altepetl | *āltepētl* | City-state |
| tlatoani | *tlàtoāni* | Ruler |
| tlacochcalcatl | *tlacochcalcatl* | High general |
| tlacateccatl | *tlacateccatl* | General |

---

## 10. Core bibliography (entry points)

**Chroniclers / ethnohistory:** Cortés, *Letters from Mexico* (trans. Pagden); Díaz del Castillo, *True History*; Sahagún, *Florentine Codex* (Dibble & Anderson); Durán, *History of the Indies of New Spain*; Codex Mendoza (Bodleian; Berdan & Anawalt facsimile/commentary).

**Modern military / conquest:** Ross Hassig, *Aztec Warfare* (1988); *Mexico and the Spanish Conquest* (rev. ed.); Barry L. Isaac, “The Aztec ‘Flowery War’” (*JAR* 1983); Inga Clendinnen, “The Cost of Courage in Aztec Society”; Matthew Restall, *Seven Myths of the Spanish Conquest*; Camilla Townsend, *Fifth Sun*.

**Weapons:** Marco A. Cervera Obregón, “The macuahuitl…” (*Arms & Armour* 2006); Mexicolore weaponry feature (Cervera).

**City / temple archaeology:** Eduardo Matos Moctezuma; Leonardo López Luján (Templo Mayor Project publications); López Austin & López Luján on sacred precinct; Barbara Mundy on Tenochtitlan mapping; INAH Hueyi Tzompantli reports.

**Brigantines / logistics:** Recent work in *The Americas* / *The Americas* journal on Native ship/canal labour in the Spanish–Mexica war (2023).

---

## 11. Designer do / don’t

**Do:** Captive-based progression; three-lane causeways; bridge pulls; canoe flanks; aqueduct side-quest; Templo Mayor dual-summit finale; Native allies as the true enemy army size; label chronicle vs archaeology.

**Don’t:** European stone concentric castles as “Aztec default”; immortal horse-cutting macuahuitl as physics; flower wars as *only* ritual farming of victims; Quetzalcoatl-god Cortés as strategy AI; erase Tlaxcala/Tetzcoco agency; claim Stage-VII height as 60 m without footnote; treat Wikipedia army lists as primary sources.

---

*Companion style to Egyptian / Achaemenid / Chinese / Roman TD research briefs in this project. Written for game-bible use; not a substitute for reading Hassig, the Mendoza facsimiles, or Templo Mayor excavation reports.*
