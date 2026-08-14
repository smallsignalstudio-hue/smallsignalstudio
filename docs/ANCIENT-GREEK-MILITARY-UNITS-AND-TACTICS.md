# ANCIENT GREEK MILITARY UNITS AND TACTICS — Research Brief (Tower-Defence Game Doc)

> English research notes for a tower-defence game bible (same project family as Viking / Persian / Egyptian TD research).  
> **Method:** Prefer **primary literary** + **inscription** + **archaeology**; flag bias and scholarly conflict. Do **not** invent TO&Es, regiment sizes, or “standard polis kit lists.” Ancient Greece is **not one kingdom**—polis armies differ; **Macedonian / Hellenistic** systems are related but distinct.  
> **Hard myths:** **300** (film) = **FOLK**; “Greek national army” = false; Spartan red cloaks / lambda shields as universal kit = over-read popular reconstruction; Archimedes “death ray” = **FOLK**/late anecdote vs Polybius–Livy siege mechanics.

### Evidence tags (use throughout)
| Tag | Meaning |
|---|---|
| **ARCH** | Archaeology (arms, armour, fortifications, ship sheds, wrecks, sites) |
| **LIT** | Literary (Herodotus, Thucydides, Xenophon, Polybius, Arrian, Diodorus, Plutarch, etc.—**bias flagged**) |
| **INSCR** | Inscriptions / documentary (casualty lists, decrees, naval inventories, treaties) |
| **MOD** | Modern scholarship (Hanson, Lazenby, Pritchett, van Wees, Sekunda, Heckel, Sabin, Krentz, etc.) |
| **FOLK** | Popular myth, film, games, Victorian romance, wargame TO&Es treated as fact |

### Core primary sources (bias table)
| Source | Nature / caution |
|---|---|
| **Herodotus**, *Histories* | Greco-Persian Wars narrative; ethnographic digressions; numbers often inflated; Athenian/Greek framing. **LIT** |
| **Thucydides**, *History of the Peloponnesian War* | Analytical, oligarchic-leaning; best Classical operational detail; speeches partly composed. **LIT** |
| **Xenophon**, *Hellenica*, *Anabasis*, *Constitution of the Lacedaemonians*, *Hipparchicus*, *Cyropaedia* | Soldier-eyewitness for late 5th/early 4th c.; pro-Spartan; *Cyropaedia* didactic/semi-fiction. **LIT** |
| **Polybius**, *Histories* | Hellenistic / mid-Republic; shrewd on tactics and sieges; Greek elite viewpoint. **LIT** |
| **Arrian**, *Anabasis of Alexander*; *Tactica* | Late (2nd c. CE) using earlier traditions (esp. Ptolemy, Aristobulus); Alexander-centred. **LIT** |
| **Diodorus Siculus**, *Bibliotheca* | Compilatory; useful for Epaminondas, Sacred Band, sieges; chronological slips. **LIT** |
| **Plutarch**, *Lives* (esp. Lycurgus, Agesilaus, Pelopidas, Alexander, Demetrius) | Moral biography, not after-action report; anecdote-heavy. **LIT** |
| **Pausanias**, *Description of Greece* | Travel + local tradition; trophies, monuments, later memory. **LIT** |
| **Aeneas Tacticus**, *How to Survive under Siege* (mid-4th c.) | Rare Classical siege manual; practical. **LIT** |
| **Inscriptions** (IG, SEG; Athenian casualty lists; naval *tabulae*; Spartan dedications) | Authentic but partial; ritual and civic framing. **INSCR** |
| **Ship sheds / Olympias** reconstruction; arms from graves and sanctuaries | Physical constraints on trireme and kit. **ARCH** |

### Core modern scholarship (entry points)
Victor Davis **Hanson** (*The Western Way of War*—influential, contested); Hans van **Wees** (*Greek Warfare: Myths and Realities*); J. F. **Lazenby**; W. K. **Pritchett** (*The Greek State at War*); Peter **Krentz**; Donald **Kagan**; J. E. **Lendon**; Nicholas **Sekunda**; Waldemar **Heckel**; A. B. **Bosworth**; Donald **Engels**; J. S. **Morrison** / J. F. **Coates** / N. **Rankov** (*The Athenian Trireme*); Philip **de Souza**; E. W. **Marsden** (*Greek and Roman Artillery*); A. W. **Lawrence** / F. E. **Winter** on fortification; Paul **Cartledge** (Sparta); John **Buckler** / Hans **Beck** (Boeotia); Roel **Konijnendijk**; Christopher **Matthew** (phalanx debates).

---

## 0. Framing: what “Greek armies” were

**Not a unitary kingdom.** Classical “Greece” = hundreds of *poleis* (city-states) plus ethnos-leagues, colonies, and later Macedonian monarchy. Doctrine, recruitment, and prestige units vary by polis and century. Treating “the Greek army” as one roster is false. **LIT** + **MOD**

**Historical placement (game eras, not rigid TO&Es).**
| Era (broad) | Approx. | Military character |
|---|---|---|
| Archaic → early Classical | c. 700–480 BCE | Rise of hoplite civic warfare; Persian Wars climax |
| High Classical | 480–404 | Athenian naval empire; Peloponnesian War; mercenary growth |
| Late Classical | 404–338 | Spartan hegemony → Theban challenge → Macedonian rise |
| Macedonian / early Hellenistic | 359–323 / after | Philip–Alexander professionalizing monarchy; Successor wars |
| Hellenistic | 323–c. 30 BCE | Polyremes, torsion artillery, mercenary markets, fortress science |

**Citizen-militia vs mercenaries.**
- Classical ideal: **citizen-soldier** (*politēs*) equipping himself as hoplite or rower; political rights tied to military obligation (Athens: Solonian/Cleisthenic strata debates; Sparta: *homoioi*). **LIT** + **MOD**
- Reality: from mid–late 5th c., **mercenaries** (*misthophoroi*) proliferate—especially light troops, specialists, and overseas service (Xenophon’s Ten Thousand). **LIT** Xen. *Anab.*; Thuc. on hired troops.
- Do **not** invent fixed ratios of citizen:mercenary for “the Greek army.” **MOD**

**Sparta — *homoioi*, *perioikoi*, helots.**
- ***Homoioi* (“Equals”):** full Spartiate citizens; agoge training; mess-group (*syssition*) membership required; core of the phalanx. Numbers decline across Classical period (**LIT** Xen., Arist. *Pol.*; **MOD** Cartledge).
- ***Perioikoi*:** free non-citizen Laconians/Messenians in surrounding communities; served in army and navy; economic producers. **LIT** + **MOD**
- **Helots:** unfree agrarian underclass (esp. Messenia); sometimes light-armed attendants; feared revolt; *krypteia* debates (below §7). **LIT** Thuc. 4.80; Plut. *Lyc.*
- Do not treat every “Spartan” figure in film as a homoios with identical kit. **FOLK** vs **LIT**/**ARCH**

**Athens — democracy and navy.**
- Massive **trireme** fleet manned largely by **thetes** (poorer citizens) as rowers; naval pay as democratic pillar (Thucydides’ “naval mob” framing—elite bias). **LIT** Thuc. 1.142–143; 8.72ff.; **INSCR** naval inventories.
- Hoplite class still important for land campaigns and prestige; cavalry (*hippeis*) small relative to Boeotia/Thessaly. **LIT** Xen. *Hipparchicus*
- Empire (*arkhē*): tribute, cleruchies, subject allies—maritime logistics as power. **LIT** Thuc. + **INSCR** tribute lists

**Boeotian League / Thebes.**
- Federal structure with Thebes dominant at peaks; **Sacred Band** (*Hieros Lochos*) as elite paired infantry (**LIT** Plut. *Pel.*; Diod.). Oblique / deep-wing tactics at **Leuctra** (371) overturn Spartan myth of invincibility. **LIT** Xen. *Hell.* 6.4; Diod. 15; **MOD** Buckler

**Macedon — professionalizing monarchy (distinct system).**
- Philip II reforms: standing / semi-standing elements, **sarissa** phalanx (*pezhetairoi*), Companion cavalry (*hetairoi*), hypaspists, combined-arms doctrine, siege train. Alexander inherits and scales this. **LIT** Arr.; Diod.; **MOD** Heckel, Bosworth
- Culturally Greek-adjacent elite identity; politically a **kingdom**, not a polis militia. Keep separate tech tree / doctrine layer in game. **MOD**

**Evidence quality (framing).**
| Topic | Quality |
|---|---|
| Polis diversity / no single army | **High** |
| Hoplite equipment existence | **High** (**ARCH** + **LIT**) |
| Exact file depths as universal rule | **Medium–low** — vary by army/battle |
| Shield-overlap mechanics | **Debated** (**MOD**) |
| Film “300 Spartans” kit / dialogue | **FOLK** |

**Game-use potential:** Faction = **polis / league / dynasty unlocks**, not one Greek blob. Age track: Archaic hoplite clash → Athenian naval empire → Theban deep phalanx → Macedonian combined arms → Hellenistic artillery–mercenary endgame.

---

## 1. Core land units (Classical → Macedonian hinge)

### 1.1 Hoplite (*hoplitēs*)

**What.** Heavy infantry citizen (or equivalent) fighting primarily with large round shield and thrusting spear in close-order battle line (*phalanx*). Name linked to *hopla* (arms) / popularly to *hoplon* as shield—terminology debates exist (**MOD**).

**Historical placement.** Dominant prestige land fighter of Archaic–Classical poleis; still present under Macedon as allies/mercenaries; Hellenistic successors field varied heavy infantry alongside sarissa troops.

**Equipment (attested categories — not a universal loadout).**
| Item | Greek | Notes | Evidence |
|---|---|---|---|
| Shield | *aspis* / *hoplon* | Large concave bronze-faced wood; *porpax* (arm-band) + *antilabē* (grip); ~80–100 cm diameter typical of finds | **ARCH** + **LIT** |
| Spear | *dory* | Primary weapon; butt-spike (*saurotēr*) attested | **ARCH** + **LIT** |
| Sword | *xiphos* (straight) / *kopis* or *machaira* (curved cutters) | Secondary; curved types more cutting | **ARCH** + **LIT** |
| Body armour | Bronze bell / muscle cuirass; later linen (*linothorax*—construction debated); sometimes none for poorer | Class/wealth variation | **ARCH** + **LIT** + **MOD** |
| Helmet | Corinthian, Chalcidian, Attic types evolving | Face coverage vs hearing/vision tradeoffs | **ARCH** |
| Greaves | *knēmides* | Common on richer depictions/finds | **ARCH** |

**Tactical role.** Frontal shock / push contest (*ōthismos*—meaning contested: literal shove vs sustained close fighting). Flanks vulnerable without cavalry/light cover. **LIT** + **MOD** Hanson vs van Wees / Krentz debates.

**Controversies.**
1. ***Ōthismos*:** scrum-like mass shove (**MOD** Hanson tradition) vs spear-fighting in denser-than-Homeric order without rugby scrum (**MOD** Krentz, van Wees, Matthew). Do not hard-code one physics model as “fact.”
2. **Shield overlap:** right-side coverage of neighbour often inferred from Thuc. 5.71 (tendency to drift right); degree of systematic overlap debated. **LIT** + **MOD**
3. **Linothorax:** organic armour rarely survives; vase and sculpture evidence + reconstruction experiments. **ARCH** sparse; **MOD** reconstructive.

**Game-use:** Core durable melee lane-blocker; high frontal defence, slow, weak flanks/rear; upgrade tree by polis (Spartan discipline aura; Athenian citizen levy cheaper; Boeotian deeper ranks).

---

### 1.2 Phalanx mechanics (Classical)

**What.** Close-order heavy infantry formation; depth commonly cited **8–16** ranks in Classical contexts, with famous exceptions (Theban deep wing). **LIT** + **MOD**

**Depth examples (do not universalize).**
- Typical textbook Classical depth ~8; deeper arrays appear in specific battles. **MOD**
- **Leuctra (371):** Theban left deepened massively (tradition of ~50—treat as **LIT** figure, not drill manual). **LIT** Xen. *Hell.* 6.4.12; Diod.; **MOD** Buckler
- Macedonian sarissa phalanx often reconstructed deeper still (see §1.10)—different system.

**Shield-overlap debates.** Rightward crowding (Thuc. 5.71) used to argue each man sought cover of neighbour’s shield; whether this yields continuous overlapping wall is interpretive. **LIT** + **MOD**

**Frontage / file.** Do **not** invent exact metres-per-man TO&E; experiments (Olympias-style for navy; reenactment for land) give constraints only. **MOD** + **ARCH**

**Game-use:** Formation stance—frontal DR↑, speed↓, flanking damage↑; “deepen ranks” ability trades width for breakthrough (Leuctra mode).

---

### 1.3 Peltasts (*peltastai*) and Iphicrates reforms

**What.** Light/medium infantry named for the *peltē* (light shield—Thracian associations in origin stories). Javelins primary; capable of skirmish and, when reformed, denser fighting. **LIT**

**Historical placement.** Thrace-influenced types early; crucial in Peloponnesian and 4th-c. wars; **Iphicrates** (early 4th c.) credited with reforms: longer spears, lighter/longer swords, revised boots (*iphikratides*), possibly revised shields—details vary by source and may be schematic. **LIT** Diod. 15.44; Nepos *Iphicrates*; **MOD**

**Famous action.** Athenian/Iphicratean peltasts destroy a Spartan mora near **Lechaeum** (390)—shock to Spartan prestige. **LIT** Xen. *Hell.* 4.5

**Game-use:** Fast anti-hoplite harasser on rough terrain; bonus vs phalanx flanks; poor in prolonged frontal lock vs deep spears.

---

### 1.4 Psiloi (light skirmishers)

**What.** Generic light-armed (*psiloi*): stones, javelins, slings, bows—often poorer citizens, attendants, or hired specialists. Screen, harry, pursue broken foes. **LIT** Thuc.; Xen.

**Game-use:** Cheap expendable ranged; dies to cavalry/charge; softens towers before hoplite push.

---

### 1.5 Hippeis — Classical cavalry (limited)

**What.** Mounted citizen elites (*hippeis*); expensive horses; terrain and social structure limited mass cavalry in much of southern Greece. **Stronger** traditions in **Thessaly**, **Boeotia**, later **Macedon**. **LIT** Xen. *Hipparchicus*; **MOD**

**Role.** Flank protection, pursuit, reconnaissance; rarely the Classical battle-winner alone in southern polis wars (exceptions and regional variation apply).

**Game-use:** Expensive flankers; Thessalian/Macedonian unlocks far stronger than early Attic hippeis.

---

### 1.6 Archers

**What.** Native archery uneven; **Cretan** archers famed as mercenaries; **Scythian** (and “police” Scythians at Athens—public slaves) appear in Athenian contexts. **LIT** + **INSCR** + **MOD**

**Game-use:** Hireable specialist tower/support; Cretan = premium accuracy; Scythian = rate-of-fire / mobility flavour—label as hire, not “default Greek bowline.”

---

### 1.7 Slingers (*sphendonētai*)

**What.** **Rhodian** slingers especially reputed; lead bullets (*molybdides*) with inscriptions known archaeologically. Effective vs lightly protected targets; Xenophon praises sling ranges vs Persian bows in places. **LIT** Xen. *Anab.* 3.3–4; **ARCH** bullets; **MOD**

**Game-use:** Cheap long-range chip vs unarmoured; ammo types (stone → lead) as upgrades; inscribed bullets as cosmetic/crit flavour.

---

### 1.8 Spartan *hippeis* — royal guard (careful naming)

**What.** Elite Spartan infantry guard of three hundred traditionally attached to the kings—**not** primarily a cavalry corps despite the name *hippeis*. Selection and exact roles debated; do not confuse with ordinary polis hippeis cavalry. **LIT** Hdt. 8.124; Xen. *Lac. Pol.*; Thuc.; **MOD** Cartledge

**Game-use:** Tiny elite buff aura around king-hero; name tooltip must disambiguate “Spartan Hippeis (royal infantry guard)” vs “Hippeis (cavalry).”

---

### 1.9 Theban Sacred Band (*Hieros Lochos*)

**What.** Elite Theban unit of 300 traditionally paired lovers; trained as standing elite; destroyed at **Chaeronea** (338) by Macedon (tradition of Philip weeping / burial mound—*LIT* Plut. *Pel.*, *Alex.*; archaeology of Lion of Chaeronea monument context debated in detail). **LIT** + **ARCH** + **MOD**

**Role.** Spearhead of Theban shock; associated with Pelopidas / Epaminondas era victories.

**Game-use:** Ultra-elite small stack; morale aura; unique “pairs” buff; tragic Chaeronea defeat event if overcommitted vs Macedonian unlock.

---

### 1.10 Macedonian *pezhetairoi* — sarissa phalanx

**What.** Foot companions (*pezhetairoi*) fighting with **sarissa**—very long pike, often reconstructed ~5–6+ m (~18 ft class in popular figures; exact lengths vary by period/source—do not freeze one museum replica as eternal law). Dense multi-rank hedge of points. **LIT** Arr.; Polyb. 18.29–30 (vs Roman); **MOD** Heckel, Sekunda, Matthew

**Distinct from Classical hoplite phalanx.** Smaller shield (*pelte*-like / *telamon* arrangements in reconstructions), two-handed pike employment, deeper dependence on flank protection by cavalry and hypaspists. **MOD**

**Game-use:** Extreme frontal zone control; glacial turn rate; shredded if flanked or broken terrain; “hammer and anvil” synergy with Companion cavalry.

---

### 1.11 Hypaspists (*hypaspistai*)

**What.** Elite Macedonian infantry—“shield-bearers”; flexible crack troops for rough ground, assaults, and linking phalanx to cavalry. Equipment debates (hoplite-like vs lighter). Later *argyraspides* (“Silver Shields”) as veteran label in Successor wars. **LIT** Arr.; Diod.; **MOD** Heckel

**Game-use:** Elite multipurpose infantry; siege assault bonus; not as rigid as pezhetairoi.

---

### 1.12 Companion cavalry (*hetairoi*)

**What.** Macedonian aristocratic heavy cavalry; decisive strike arm under Philip/Alexander; wedge tactics reported. Alexander’s personal leadership of Companions at key battles. **LIT** Arr.; Diod.; **MOD**

**Game-use:** Expensive hammer unit; charges into disrupted enemies; king-hero attachment high risk/reward.

---

### 1.13 *Prodromoi* / scout cavalry

**What.** Advance scouts / lightish Macedonian cavalry; scouting and skirmish roles before main engagement. **LIT** Arr.; **MOD**

**Game-use:** Fog-of-war reveal; harass economy lanes; poor in prolonged melee vs heavies.

---

### 1.14 Siege engineers / technical specialists

**What.** Classical Greeks comparatively limited in offensive siegecraft vs later Hellenistic science; Philip–Alexander and Successors professionalize engines, ramps, mines. Named engineers appear in Alexander narratives. **LIT** Arr.; Diod.; **MOD** Marsden

**Game-use:** Support unit unlocking towers/rams/artillery ages; Macedonian/Hellenistic tech gate.

---

## 2. Naval warfare

### 2.1 Trireme (*triērēs*)

**What.** Three-banked war galley; primary Classical capital ship. Crew ~170 rowers + marines (*epibatai*) + officers (order-of-magnitude from inventories/reconstruction—not invent finer TO&E). **LIT** + **INSCR** + **ARCH** (ship sheds) + **MOD** Morrison/Coates/Rankov; Olympias sea trials

**Rams.** Bronze-sheathed ram (*embolos*) for ship-killing; speed and oar-skill decisive. **ARCH** + **LIT**

**Olympias.** Modern reconstruction informs acceleration, turning, and rower endurance constraints—experimental, not a Classical primary source. **MOD** + **ARCH** experimental

---

### 2.2 *Diekplous* and *periplous*

| Manoeuvre | Idea | Evidence |
|---|---|---|
| ***Diekplous*** | Breakthrough / shearing through enemy line to rake oars or ram stern/side | **LIT** Thuc.; **MOD** naval historians |
| ***Periplous*** | Outflanking circumnavigation of enemy wing/line | **LIT** + **MOD** |

Skill of helmsmen and rower fitness > “hit points” of hull alone. Confined waters (e.g. Salamis tradition) blunt pure manoeuvre fleets. **LIT** Hdt. 8; **MOD**

---

### 2.3 Athenian maritime empire

**What.** Delian League → Athenian *arkhē*; shipbuilding, harbour (Piraeus / Munichia / Zea sheds), and **trierarchy** liturgy (wealthy citizens fund/command ships—see §7). Naval victory at **Salamis** (480) foundational in Greek memory; later Sicilian disaster (415–413) shows overreach. **LIT** Hdt.; Thuc.; **INSCR** + **ARCH**

**Game-use:** Sea lanes as resource/tribute routes; trireme waves; loss of rower manpower = democratic/economy crisis event.

---

### 2.4 Hellenistic polyremes (brief)

**What.** After Alexander, arms race in larger galleys (*tetreres*, *penteres*, and monstrous showpieces). Greater marine decks and artillery platforms; less about Classical diekplous purity. **LIT** Polyb.; Plut. *Demetr.*; **MOD** de Souza, Murray

**Game-use:** Late-age floating siege towers; slow, costly, terrifying vs early triremes.

---

## 3. Landmark battles / campaigns (tactical lessons)

### 3.1 Marathon (490 BCE)

**Outline.** Athenian (+ Plataean) hoplites defeat Datis/Artaphernes’ landing force on Attic plain. Herodotus’ account: weakened centre, stronger wings enveloping. Numbers and Persian force composition debated. **LIT** Hdt. 6.102–117; **MOD** Lazenby, Krentz

**TD lesson.** Timing charge vs missile prep; wing weight as envelop unlock; do not treat “Marathon run” legend as military mechanic (**FOLK** layers exist around later traditions).

---

### 3.2 Thermopylae (480)

**Outline.** Greek blocking force under Leonidas holds pass against Xerxes; Anopaea path betrayal; Spartan/Thespian last stand. Herodotus central; numbers and “300 only” myth distort allied presence. **LIT** Hdt. 7.201–233; **FOLK** *300* film

**TD lesson.** Chokepoint defence multiplies elite infantry value; path-around fog-of-war failure = classic tower-defence betrayal lane.

---

### 3.3 Plataea (479)

**Outline.** Combined Greek victory over Mardonius; Spartan–Tegean push vs Persians; Athenian sector vs medizing Greeks; wicker-shield wall broken in Hdt.’s telling. **LIT** Hdt. 9; **MOD** Konijnendijk et al.

**TD lesson.** Coalition AI with divergent objectives; anti-*spara* breakthrough as phase shift from missile to melee.

---

### 3.4 Sphacteria (425)

**Outline.** Athenian light troops and Cleon/Demosthenes operations trap Spartans on island; Spartiate surrender shocks Greek world. **LIT** Thuc. 4.2–41

**TD lesson.** Psiloi/peltast harassment + isolation > frontal hoplite honour duel; “Spartan morale unbroken” myth fails under missile/starve pressure.

---

### 3.5 Leuctra (371)

**Outline.** Epaminondas’ **oblique order** / refused right and **deepened left** with Sacred Band; Spartan king Cleombrotus falls; Spartan hegemony cracked. **LIT** Xen. *Hell.* 6.4; Diod. 15; **MOD** Buckler

**TD lesson.** Asymmetric lane weighting—stack one breakthrough path, delay others; depth as temporary superweapon with flank risk.

---

### 3.6 Chaeronea (338)

**Outline.** Philip II defeats Athenian–Theban alliance; Sacred Band destroyed; Macedonian combined arms (phalanx + cavalry) over Classical coalition. **LIT** Diod. 16; Plut.; **MOD** Heckel

**TD lesson.** Era gate: Classical hoplite meta loses to professional combined-arms faction.

---

### 3.7 Gaugamela (331) — Greek–Macedonian hinge

**Outline.** Alexander’s Macedonian army (Greek allies/mercenaries included) defeats Darius III on open plain; false gaps, Companion strike, phalanx advance—Arrian’s polished narrative. **LIT** Arr. *Anab.* 3; Diod.; Curtius; **MOD** Bosworth

**TD lesson.** Use as **crossover scenario**: player fields Macedonian Greek-led system vs Achaemenid roster (separate Persian brief). Not a “polis civil war” map.

---

## 4. Best attack vs best defence (TD both sides)

### 4.1 Best attack methods (aggressor toolkit)

| Method | When historically strong | TD translation | Tags |
|---|---|---|---|
| Weighted-wing / oblique breakthrough | Leuctra; wing emphasis at Marathon tradition | Stack one lane; refuse others | **LIT** + **MOD** |
| Combined arms (sarissa + Companion hammer) | Chaeronea–Gaugamela meta | Phalanx pins, cavalry deletes backline | **LIT** |
| Light-troop isolation / missile starve | Sphacteria; Iphicrates vs mora | Harass + cut supply nodes | **LIT** |
| Naval ram manoeuvre / blockade | Athenian empire playbook | Sea-lane cut; port starve | **LIT** + **INSCR** |
| Hellenistic artillery–tower–ram complex | Demetrius; late sieges | Siege age unlocks | **LIT** Polyb.; **MOD** Marsden |
| Betrayal / path around chokepoint | Thermopylae Anopaea | Optional stealth path if scouts fail | **LIT** |

### 4.2 Best defence methods (defender toolkit)

| Method | Historical cue | TD translation | Tags |
|---|---|---|---|
| Chokepoint hoplite hold | Thermopylae; narrow gates | Terrain funnels | **LIT** |
| Depth + elite tip | Theban left / Sacred Band | Deep formation + hero stack | **LIT** |
| Skirmisher screen + rough ground | Peltasts vs rigid spears | Slow phalanx in hills/brush | **LIT** |
| Fleet in being / harbour chain | Athenian Piraeus system | Naval towers + boom | **ARCH** + **LIT** |
| City walls + active sorties | Classical *teichos*; Aeneas Tacticus | Wall HP + sally buttons | **LIT** Aeneas; **ARCH** |
| Macedonian refused flank + cavalry reserve | Alexander battle geometry | Mobile reserve deletes breakthroughs | **LIT** Arr. |
| Torsion batteries on walls | Hellenistic Rhodes et al. | Artillery towers | **LIT** + **MOD** |

### 4.3 Lane translation (concise)
- **Lane 1 (open plain):** Cavalry and sarissa shine; Classical shallow phalanx struggles if flanked.
- **Lane 2 (pass/gate):** Hoplite elite defenders overperform; engines matter more than numbers.
- **Lane 3 (coast/harbour):** Trireme control decides reinforcement timing.
- **Lane 4 (siege approaches):** Classical attackers weak; Hellenistic artillery age required for fair assault.

---

## 5. Siege warfare

### 5.1 Classical limits

**What.** Many Classical sieges = blockade, betrayal, starvation, limited rams/mounds; stone city walls increasingly common (5th–4th c.). Amphipolis, Plataea (siege in Thuc.), Syracuse Athenian failure—operationally hard. **LIT** Thuc.; **MOD**

**Aeneas Tacticus.** Mid-4th-c. handbook: watchwords, fire precautions, gatedrills, morale—rare practical voice. **LIT**

---

### 5.2 Hellenistic torsion artillery

| Engine (Greek) | Role | Notes |
|---|---|---|
| ***Katapeltēs* / *katapeltai*** | Bolt-shooters | Torsion powered (hair/sinew); technical manuals later |
| ***Lithoboloi*** | Stone-throwers | Wall/personnel bombardment |
| Rams / tortoises / mobile towers | Approach & batter | Demetrius “Poliorcetes” fame |

**Evidence.** Polybius on mechanized war; Philo / Biton / later engineering tradition; Marsden’s reconstructions. **LIT** + **MOD** Marsden

---

### 5.3 Demetrius Poliorcetes, Rhodes, Syracuse / Archimedes

**Demetrius I** (“Besieger”): spectacular engines vs **Rhodes** (305/4)—*helepolis* tradition; Rhodian resistance and dedications. **LIT** Diod. 20; Plut. *Demetr.*; **MOD**

**Syracuse / Archimedes (212 BCE tradition):** Polybius and Livy describe mechanical counters (cranes, engines) against Marcellus’ Romans; **“burning mirrors / death ray”** popular claims are **FOLK**/late scientific romance relative to core Polybian–Livian siege narrative—flag separately. **LIT** Polyb. 8; Livy 24; **FOLK** mirrors

**Game-use:** Siege age = tech tree, not Classical default. Archimedes = legendary defender hero with **documented** engine bonuses; mirror beam as optional **FOLK** cosmetic togglable myth mode.

---

## 6. Obscure / gameable details (20+)

1. **Paean (*paiān*)** — pre-battle / victory hymn-cry; morale buff / telegraph charge. **LIT** Thuc.; Xen.
2. **Trophy (*tropaion*)** — armour hung on stake at turning point of victory; map marker / score object. **LIT** + **ARCH** depictions
3. **Salpinx** — war trumpet for signals; audio cue for formation changes. **LIT** + **ARCH**
4. **Trierarchy** — Athenian liturgy: rich citizen funds a trireme; economic mini-game / political risk. **LIT** + **INSCR**
5. ***Perioikoi*** — Spartan peripheral free populations in army/economy; recruitment pool distinct from homoioi. **LIT** + **MOD**
6. ***Krypteia*** — debated Spartan youth institution: terror vs helots and/or training ordeal; Plutarch vs cautious modern readings—**do not** present as confirmed special-ops TO&E. **LIT** Plut. *Lyc.*; Plato *Laws*; **MOD** debate
7. ***Syssitia / phiditia*** — Spartan messes; social cohesion mechanic tied to citizenship. **LIT** Xen. *Lac. Pol.*
8. **Agoge** — Spartan upbringing system; long-train elite unlock, tiny replacement rate. **LIT** + **MOD**
9. ***Lochos* / *enōmotia* / *mora*** — Spartan subunit vocabulary (sizes vary by period/source—**no invented fixed TO&E**). **LIT** Thuc.; Xen.
10. **Casualty lists (*stelai*)** — Athenian public names of dead; narrative memorial unlocks. **INSCR**
11. ***Epibatai*** — marines on triremes; boarding vs ram doctrine slider. **LIT** + **INSCR**
12. ***Diekplous* oar-shear** — disable enemy mobility without sinking; status effect “crippled banks.” **LIT** + **MOD**
13. **Lead sling bullets with insults** — **ARCH** inscribed *molybdides*; crit-flavour text.
14. ***Saurotēr*** — spear butt-spike; rear-rank plant / finishing weapon flavour. **ARCH** + **LIT**
15. **Rightward drift (Thuc. 5.71)** — phalanx AI bias exposing left; exploitable. **LIT**
16. ***Aspis* as bier** — tradition of “with shield or on it” is proverbial/popular Sparta; handle as **FOLK**/late proverb vs documentary drill. Still gameable as morale quip if flagged.
17. **Boeotian federal banners / league politics** — unit availability depends on league diplomacy. **LIT** + **MOD**
18. ***Hamippoi*** — infantry mixed with cavalry in some Greek practices (esp. Boeotian contexts in scholarship)—niche combo unit; verify per battle before hardcoding. **LIT**/ **MOD**
19. **Olympic sacred truce (*ekecheiria*)** — campaign calendar pause / religious constraint event (scope and enforcement debated). **LIT** + **INSCR** + **MOD**
20. ***Seer (mantis)* & omens** — pre-battle sacrifice; delay attack until “signs fair” (Plataea traditions; Spartan King + seer dynamics). **LIT** Hdt.; Xen.
21. **Helot attendants** — carry kit; potential revolt event if Spartan field army overextended. **LIT**
22. ***Kleros* / land crisis** — Spartan citizen shrinkage as long-campaign debuff. **MOD** + **LIT**
23. **Mercenary market (Corinth, Cape Taenarum traditions)** — hire panel with loyalty risk. **LIT**
24. ***Helepolis*** — city-taker tower; slow apocalypse unit for Hellenistic age. **LIT** Diod.; Plut.
25. **Rhodian colossus financing legend** — siege salvage → wonder economy myth; flavour, not secure engineering account. **LIT**/later tradition
26. **Amphorae / *klepsydra* timekeeping in courts** vs military—skip stretch; instead **water-clock irrelevant**—prefer **camp *ostraka*/passwords** from Aeneas as stealth infiltration defence. **LIT** Aeneas
27. ***Xenagia*** — command of allies/mercenaries; allied AI obedience stat. **LIT**
28. **Shield blazons (*episēmata*)** — lambda for Sparta popularized; historical blazon diversity—avoid single-symbol monoculture. **ARCH** + **MOD** + **FOLK** overuse
29. **Funeral games / *epitaphios logos*** — Athenian civic mourning speech (Pericles in Thuc.—composed); meta narrative between waves. **LIT**
30. **Alexander’s baggage & medical train** — logistics as victory condition (Engels’ study—**MOD** debated magnitudes).

---

## 7. Designer do / don’t

### Do
- Split **polis / league / Macedonian** doctrines as separate unlock paths.
- Tag every unit card with era + evidence quality.
- Let **terrain** break phalanx value (Sphacteria / peltast lessons).
- Model **navy** as strategic layer, not only cosmetic ships.
- Use **Leuctra depth** and **Companion hammer** as distinct climax abilities.
- Flag **krypteia**, **ōthismos**, **linothorax**, **Archimedes mirrors**, **300** as contested or folk.
- Prefer **inscriptions + Thucydides/Xenophon** for Classical logistics flavour.

### Don’t
- Invent **TO&Es**, company strengths, or “standard Greek army lists.”
- Treat **Sparta = all Greece** or **Leonidas = Greek commander-in-chief** always.
- Confuse **Spartan hippeis guard** with **cavalry hippeis**.
- Equate **Classical hoplite phalanx** with **Macedonian sarissa phalanx**.
- Hard-lock **shield overlap** or **ōthismos scrum** as settled physics.
- Use **hornless Corinthian helms gleaming in sunset** Hollywood as kit law (**FOLK**).
- Present **mercenaries** as late “corruption” only—they are structural by late 5th/4th c.
- Give Classical factions **torsion artillery** parity with Demetrius-age engines.
- Cite **wargame army books** as primary sources.

---

## 8. Quick roster index (game bible — labelled)

| Unit | Era bias | Role | Evidence floor |
|---|---|---|---|
| Hoplite | Archaic–Classical | Line melee | **High** |
| Peltast | Classical–4th c. | Skirmish / flank | **High** |
| Psiloi | All Classical | Cheap missile | **High** |
| Hippeis (cavalry) | Regional | Flank / pursuit | **Medium–High** |
| Cretan archer | Hire | Precision missile | **Medium** |
| Rhodian slinger | Hire | Long chip | **Medium–High** |
| Spartiate homoios | Sparta | Elite phalanx | **High** (numbers soft) |
| Spartan hippeis guard | Sparta | Tiny royal elite | **Medium** (naming care) |
| Sacred Band | Thebes | Ultra elite | **Medium–High** |
| Pezhetairoi | Macedon | Sarissa wall | **High** |
| Hypaspist | Macedon | Elite flexible | **Medium–High** |
| Hetairoi | Macedon | Shock cavalry | **High** |
| Prodromoi | Macedon | Scout cavalry | **Medium** |
| Trireme | Classical | Naval capital | **High** |
| Polyreme | Hellenistic | Floating fortress | **Medium–High** |
| Katapeltai / lithoboloi | Hellenistic | Siege DPS | **High** (tech) |
| Helepolis | Hellenistic | Super siege | **Medium** (spectacle **LIT**) |

---

## 9. Bibliography entry points (suggested reading order)

### Start here (modern syntheses)
- Hans van **Wees**, *Greek Warfare: Myths and Realities*
- J. E. **Lendon**, *Soldiers and Ghosts* (cultural continuity thesis—use critically)
- Victor Davis **Hanson**, *The Western Way of War* (classic; contest with van Wees/Krentz)
- Paul **Cartledge**, Sparta studies (*The Spartans*; *Sparta and Lakonia*)
- Waldemar **Heckel**, Alexander / Macedonian command structure works
- J. S. **Morrison**, J. F. **Coates**, N. **Rankov**, *The Athenian Trireme*
- E. W. **Marsden**, *Greek and Roman Artillery*

### Primary / translated entry points
- Herodotus 6–9 (Marathon–Plataea–Mycale cycles)
- Thucydides (esp. Corcyra, Sphacteria, Sicily, Mantinea)
- Xenophon, *Hellenica*; *Anabasis*; *Constitution of the Lacedaemonians*; *Hipparchicus*
- Polybius (phalanx vs legion; sieges; Hellenistic war)
- Arrian, *Anabasis of Alexander*
- Diodorus (Epaminondas; Sacred Band; Demetrius)
- Aeneas Tacticus, *Siege Defense*
- Selected **IG** naval and casualty inscriptions (translations in sourcebooks)

### Deep dives by topic
- Phalanx debates: Krentz; Matthew; Konijnendijk; Pritchett volumes
- Boeotia / Leuctra: John **Buckler**
- Cavalry: Gaebel; Xenophon’s cavalry tracts + commentary
- Siege / fortification: Winter; Lawrence; Marsden
- Navy: Morrison/Coates/Rankov; de Souza; Gabrielsen (trierarchy/economy)
- Macedonian army: Heckel; Bosworth; Sekunda Osprey (reconstructive—cross-check)
- Popular myth control: compare **FOLK** (*300*, strategy games) against **LIT** Herodotus/Thucydides

### Sourcebooks
- M. M. **Sage**, *Warfare in Ancient Greece: A Sourcebook*
- Sabin / van Wees / Whitby (eds.), *The Cambridge History of Greek and Roman Warfare*, Vol. 1

---

## 10. Closing notes for the game bible

Ancient Greek warfare for a TD title is a **stack of systems**: civic hoplite honour-cultures, Athenian naval capitalism, Theban experimental shock, and Macedonian professional combined arms—then Hellenistic engines that finally make “tower defence” historically literal. Keep **evidence tags** on the UI tooltip layer; ship **FOLK** content only inside a clearly labelled Myth Mode. Never collapse the Aegean into a single red-cloaked faction.

**Document status.** Research brief for design; not a peer-reviewed article. Numbers in literary sources are often rhetorical—convert to game balance via feel-tests, not faux-precision TO&Es.
