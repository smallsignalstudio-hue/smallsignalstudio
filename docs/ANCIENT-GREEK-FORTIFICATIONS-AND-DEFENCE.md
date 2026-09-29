# ANCIENT GREEK FORTIFICATIONS AND DEFENCE — Tower-Defence Game Bible

> Dense research notes for **keep / map / layer** design across Bronze Age citadels → Classical poleis → Hellenistic artillery forts in the Greek world (mainland, islands, and major Sicilian colonies).  
> **Method:** Prefer measured archaeology and near-contemporary literature. Flag modern synthesis quality and tourist folklore. **No invented medieval curtain-castle keeps** as the default Greek brand.  
> Companion briefs (same project style): fortification dossiers for Viking / Egyptian / Chinese / Persian / Maya–Aztec packs.

### Evidence tags (use throughout)

| Tag | Meaning |
|---|---|
| **ARCH** | Excavation, survey, architectural energetics, UNESCO / ephoreia / ASCSA–BSA–DAI site documentation |
| **LIT** | Ancient literary / epigraphic testimony (Thucydides, Herodotus, Xenophon, Diodorus, Pausanias, Plutarch, Polybius, Livy, inscriptions) |
| **MOD** | Modern scholarly consensus or active debate (Lawrence, Winter, McNicoll, Conwell, Camp, Iakovidis, Travlos, Korres, etc.) |
| **FOLK** | Popular culture / tourist myth (300 aesthetics, “impregnable Acropolis forever,” Spartan wall-of-men memes without nuance) — flavour only |

**Design rule:** Prefer **ARCH/LIT** for map geometry and layer stacks. Use **MOD** where excavation numbers disagree or secondary handbooks compress ranges. Never let **FOLK** overwrite ditch profiles, wall thicknesses, or siege chronology.

**Chronology note:** Absolute BCE dates for Late Bronze Age citadel phases are soft (± decades). Classical–Hellenistic political years (404, 393, 369, etc.) are hard anchors from **[LIT]** synchronized with **[ARCH]** rebuild phases.

---

## 0. What “Greek fortification” is — and is not

### 0.1 Is
- **Cyclopean citadel circuits** on ridges (Mycenae, Tiryns, Athens Acropolis LH IIIB) **[ARCH]**
- **Polis enceintes** of stone socle + mudbrick (often) or all-stone curtains (increasingly 4th c.) with towers and named gates **[ARCH][LIT]**
- **Long Walls** linking inland *asty* to naval *emporion* / harbour (Athens–Piraeus; also Megara, Corinth analogues) **[LIT][ARCH]**
- **Acropolis as sacred + last refuge**, not always a freestanding “castle keep” in the medieval sense **[MOD]**
- **Artillery-era outworks** (ditches, outworks, catapult platforms) from Dionysius I / 4th–3rd c. onward **[ARCH][LIT]**
- **Pass and isthmus geography** as defence (Thermopylae, Isthmus of Corinth) without requiring a continuous wall **[LIT][ARCH]**

### 0.2 Is not (default TD keep)
- A single generic “Greek castle” identical from Mycenae to Messene — **period and polity dominate form** **[MOD]**
- Treating the **Parthenon alone** as a military keep (it is ideological / treasury / cult apex on a fortified rock) **[ARCH][LIT]**
- Assuming **Classical Sparta** had a stone city circuit like Athens **[LIT]**
- Hollywood **300** choke with fantasy cliff walls replacing the real coastal marsh–pass complex **[FOLK]**
- Renaissance bastions or concentric high-medieval donjons pasted onto 5th-c. Athens **[MOD]**

### 0.3 Geographic / chronological frame
| Horizon | Defence language | Signature sites |
|---|---|---|
| LH IIIB (~1300–1200) | Cyclopean citadel + underground water | Mycenae, Tiryns, Athens Acropolis |
| Archaic–early Classical | Polis wall + harbour wall | Themistoclean Athens; early Piraeus |
| Mid–late 5th c. | Long Walls + naval fortress strategy | Athens–Piraeus–Phaleron system |
| 4th c. / Epaminondas era | Large all-stone circuits, tower science | Messene; Conon’s rebuild; artillery learning |
| Sicilian / Dionysian | Plateau enclosure + fortress–gate complex | Syracuse Epipolae / Euryalus |
| Hellenistic–Roman reuse | Layered citadels | Acrocorinth; Beulé Gate phase on Acropolis approach |

---

## 1. Athens Acropolis — sacred brand keep (measured)

### 1.1 Mycenaean cyclopean circuit (LH IIIB)

| Metric | Value | Evidence |
|---|---|---|
| Circuit length | **~760 m** | **[ARCH]** SETinSTONE / Acropolis Mycenaean wall documentation (comparative energetics project) |
| Thickness | **~3.5–6 m** (W stretch toward ~**6 m**) | **[ARCH]** Iakovidis / Bundgaard / Wright entrance-system studies; W Cyclopean wall noted for exceptional thickness |
| Height (original estimate) | **~10 m** (esp. discussed for western sector) | **[ARCH]** reconstruction estimates; preserved stubs much lower |
| Date band | Built ~**1200 BCE** horizon (LH IIIB); later Classical fabric covers / cuts much of circuit | **[ARCH]** |
| Masonry | Local limestone Cyclopean; clay / small-stone packing in interstices | **[ARCH]** |

**TD map note:** The Mycenaean wall is the **deep time shell** under the Classical sanctuary. Visible Classical monuments sit *inside* or over a citadel logic that already treated the rock as a fortress **[ARCH]**.

### 1.2 Western approach — kill funnel before temples
- Primary vulnerability of the Acropolis is the **west slope**; north/east/south cliffs reduce assault lanes **[ARCH]**
- Mycenaean **bastion** under / near later Athena Nike precinct; west Cyclopean wall as primary western shield **[ARCH]** Wright, “Mycenaean Entrance System”
- Classical **Propylaea** (Mnesicles), Periclean programme from **437 BCE**, unfinished when work interrupted (Peloponnesian War horizon) **[LIT]** Plutarch *Pericles*; **[ARCH]** building fabric
- Propylaea = monumental **gatehouse / processional filter**, not a medieval barbican clone — five doorways in the cross-wall of the central hall; Doric outer orders; optical refinements shared with Parthenon vocabulary **[ARCH]**
- Later **Beulé Gate** (named after 19th-c. excavator Ernest Beulé): Late Roman / post-Classical fortification on the west approach, built largely of reused Classical blocks — **do not** date it to Pericles **[ARCH][MOD]**

### 1.3 Parthenon as ideological keep — not fortress keep alone
| Claim | Status |
|---|---|
| Parthenon is a **cult / treasury / polis brand** building (Athena Parthenos; Delian League wealth display) | **[LIT][ARCH]** |
| Parthenon walls are a **stand-alone bastion** designed for siege artillery | **Reject as default** **[MOD]** — defence is the rock + circuit + gates |
| Acropolis as **last refuge** in Persian sack tradition (480) and later crises | **[LIT]** Herodotus; archaeological burn / rebuild layers **[ARCH]** |
| Erechtheion / older Athena temple zone as **sacred continuity** core | **[ARCH][LIT]** — excellent final “morale totem” room for TD |

**Game bible stance:** Acropolis final room = **temples + cult statues + treasury ideology**. Wall HP belongs to cyclopean/Classical enceinte and Propylaea gate layer, not to the Parthenon’s colonnade fantasy HP bar alone.

### 1.4 Siege water — cistern / well traditions
| Feature | Facts | Tag |
|---|---|---|
| **Mycenaean fountain** (N slope cleft) | Stair of **8** flights from near Arrephorion / NW; terminal rock-cut well **~8 m** deep, **~2 m** wide at top → **~4 m** at base; pottery → late **13th c. BCE**; short use (~30–40 years) then landslide/earthquake fill | **[ARCH]** Hellenic Ministry / Odysseus site card |
| **Klepsydra** (NW slope) | Natural spring at Panathenaic Way × Peripatos; prehistoric use; Classical fountain house ~**470–460 BCE** (Cimon horizon reconstructions); later Roman vaulted works + stair toward Propylaea | **[ARCH][LIT]** Aristophanes, Pausanias, Plutarch mentions |
| Plateau cisterns / rock cuttings | Multiple Classical–later water storage features on and around the rock | **[ARCH]** — treat as siege endurance meters |

**TD use:** Water = **siege timer resource**. Cutting Klepsydra / Mycenaean stair access is a stealth objective; Parthenon burn is ideology damage, not the only win condition.

### 1.5 Acropolis TD reading (single arena)
1. Cliff denial (most of perimeter)  
2. West approach ramp / Panathenaic choke  
3. Beulé (late) or earlier gate works as outer filter  
4. Propylaea five-door filter  
5. Open sanctuary plateau (fragile monuments)  
6. Erechtheion–Parthenon ideology core  
7. Hidden north-slope water stair as optional “bleed” lane  

---

## 2. Athenian Long Walls — military corridor keep

### 2.1 Strategic idea (island-fortress Athens + navy)
- Mid-5th-c. policy: accept temporary loss of Attic countryside; hold **asty + harbours** behind walls; feed and fight via **sea power** **[LIT]** Thucydides (Periclean strategy; Archidamian War narrative)
- Long Walls turn Athens into a **fortified peninsula connected to the sea**, strategically an “island” despite being inland **[LIT][MOD]**
- Without naval supremacy the system fails (final collapse 405–404 after Aegospotami) **[LIT]** Xenophon *Hellenica*

### 2.2 Phases and measured lengths (Conwell synthesis preferred)

**Phase Ia (mid-5th c.; building often placed ~461–457/6):** two legs  
| Wall | Approx. length | Notes |
|---|---|---|
| Athens → **Piraeus** | **~6.18 km** | Northern / Peiraic Long Wall |
| Athens → **Phaleron** | **~5.94 km** | Phaleric wall |
| Enclosed triangle (order) | **~11.86 km²** | Between the two legs + coast |

**[ARCH]/[MOD]** David Conwell, *Connecting a City to the Sea* — best modern monograph metrics; flag as **scholarly synthesis of archaeology + texts**, not a single trench datum.

**Phase Ib (~445 / mid-440s):** **Middle Wall** (also called Southern Wall in some traditions) parallel to the Athens–Piraeus wall  
| Metric | Value | Tag |
|---|---|---|
| Middle Wall length | **~5.98 km** | **[MOD]** Conwell |
| Parallel spacing | **~183 m** (~**180 m** in handbook rounding) | **[MOD]** Conwell; Oxford Classical Dictionary ~180 m |
| Later corridor area (two Piraeus walls only) | **~1.51 km²** | **[MOD]** Conwell |

Common handbook rounding: each Piraeus Long Wall **~6–6.5 km**, pair **~180 m** apart **[MOD]** OCD / travel syntheses — **compatible** with Conwell’s more precise figures.

**Destruction / rebuild:**
| Event | Date | Tag |
|---|---|---|
| Spartan demolition after Athenian surrender (flute-music tradition in later memory) | **404 BCE** | **[LIT]** Xenophon; later anecdotal colour |
| Rebuild under **Conon** with Persian fleet/money after Cnidus | start **~393 BCE** (works continue into early 380s in some accounts) | **[LIT]** Xenophon; Diodorus; **[MOD]** |

### 2.3 Width / height — secondary synthesis (flag quality)
Popular / secondary syntheses (e.g. Electrum Magazine 2024 overview drawing on handbook tradition) give Long Wall order-of-magnitude **width ~5 m**, **height ~10 m**, stone socle + **mudbrick** superstructure, towers, stair-accessed wall-walks, later roofing of walks **[MOD]** — **not** a single excavated type-section published as universal for the whole 6 km.

**Source-quality flag for designers:**
- Use **~5 × ~10 m** as a **playable order of magnitude**, labelled **secondary synthesis**.
- Prefer Conwell’s **lengths / spacing / phasing** over any tourist “exactly 10 m high everywhere” claim.
- Construction = **stone foundations/socles + mudbrick** in Classical norm; later 4th-c. rebuilds include more ambitious stonework phases **[ARCH][MOD]**.

### 2.4 TD corridor grammar
- Dual parallel walls = **lane sandwich**: enemies in the plain vs protected road inside  
- Phaleric wall = early **wide triangle** map; Middle Wall = unlock **narrow corridor** mode  
- 404 breach = campaign catastrophe beat; 393 Conon = rebuild tech tree  
- Main civilian road Athens–Piraeus largely **outside**; interior road more military **[MOD]** OCD note

---

## 3. City circuit of Athens, Piraeus, Munychia

### 3.1 Themistoclean / Cononian city wall (asty)
| Metric | Common synthesis | Tag |
|---|---|---|
| Circuit length | **~8,500 m** (~8.5 km) | **[MOD]** handbook figures tied to excavated gates + Travlos reconstructions |
| Height | **~8–10 m** | **[MOD]** |
| Thickness | **~3 m** | **[MOD]** |
| Gates | **≥13** named / inferred (Dipylon, Sacred Gate, Peiraic, Acharnian, Diomeian, Melitides, etc.) | **[ARCH][LIT]** |
| Build context | Post-480 haste; heavy **spolia** from ruined temples | **[LIT]** Thuc. 1.90–93; **[ARCH]** |
| 404 / 394 | Razed after defeat; repaired under **Conon** with democracy’s restoration context | **[LIT]** |
| Extra screen | **Proteichisma** outwork ahead of main wall in late Classical anti-Macedonian anxiety (~338 horizon) | **[ARCH][MOD]** |

**Dipylon + Sacred Gate / Kerameikos** = best-excavated gate complex for TD gate-funnel reference **[ARCH]**.

### 3.2 Piraeus fortifications
- Peninsula with three harbours: **Kantharos** (main commercial), **Zea**, **Munychia** **[ARCH][LIT]**
- Themistocles initiates serious harbour fortification from **493 BCE**; work resumes after 480 **[LIT]**
- Fortified **moles**; narrowed mouths traditionally closable (chain tradition in later descriptions) **[LIT][MOD]**
- Mid-5th c. **Hippodamian grid** town plan inside the walls **[LIT][ARCH]**
- Ship-sheds / neosoikoi capacity often cited on order **300+** slips at peak navy — treat headcount as **order-of-magnitude LIT/MOD**, not a census tablet **[MOD]**
- Themistoclean trace vs later **Conon** coastal rebuild: Cononian wall generally encloses more aggressively toward the water in places; excavated stretches (e.g. Aphrodision area) show thick curtains and towers **[ARCH]**
- Internal sub-walls around harbour basins and the **Munychia fortress** zone **[ARCH]** Perseus building summary

### 3.3 Munychia
- Hill-fortress controlling the east harbours of the Piraeus peninsula **[ARCH]**
- Political–military flashpoint in late 5th / early 4th c. (Thirty Tyrants / democratic restoration narratives) **[LIT]**
- TD role: **harbour citadel boss node** — separate from Athens Acropolis brand keep

### 3.4 System diagram (Classical peak)
```
Sea lanes → Piraeus moles/harbours → Munychia hill
        ↘ Long Walls corridor ↙
            City circuit (Dipylon etc.)
                → urban streets
                    → west Acropolis ramp → Propylaea → temples
```

---

## 4. Mycenae and Tiryns — Bronze Age citadel TD maps

### 4.1 Mycenae
| Feature | Measured / attested | Tag |
|---|---|---|
| Circuit (greatest extent) | **~900 m** | **[ARCH]** |
| Enclosed area | **~30,000 m²** order | **[ARCH]** |
| Wall thickness | Often **~5–8 m**; N side up to **~7.5 m** in popular excavation summaries | **[ARCH]** |
| Preserved height | Mycenaean sections to **~8.25 m**; local summaries cite nearly **~12 m** in places | **[ARCH]** — preserve range |
| Phases | First major summit fortification ~**1340 BCE**; enlargement ~**1250 BCE** (~+40% area) creating Lion Gate west entrance | **[ARCH]** |
| **Lion Gate** | Conglomerate ashlar gate; relieving triangle with lionesses; approach ramp; outer court **~15 × 7.5 m**; flanking bastion forces attack on unshielded right | **[ARCH]** |
| Postern / sally | N postern (reduced Lion Gate plan); E sally port | **[ARCH]** |
| Underground water | Perseia spring tunnel / underground cistern access in late extension — siege water parallel to Athens Mycenaean fountain logic | **[ARCH]** |

**Lion Gate TD grammar:** bastion + narrow court = **right-side exposure funnel** (classic Mycenaean gate tactics) **[ARCH][MOD]**.

### 4.2 Tiryns
| Feature | Measured / attested | Tag |
|---|---|---|
| Circuit length | Often **~750 m** in site syntheses | **[MOD]/[ARCH]** secondary site figures — flag |
| Typical thickness | **~6 m** average; common range **~4.5–8 m** | **[ARCH]** |
| Thickness at galleries | Up to **~17 m** where in-wall tunnels pass | **[ARCH]** |
| Preserved height | Up to **~7–7.5 m**; original estimates **~9–10 m** | **[ARCH]** |
| Plan | Lower / middle / upper citadel tiers on low limestone ridge (~18–26 m asl) | **[ARCH]** |
| Galleries | Corbelled passages and casemates inside wall mass — refuge + storage + movement | **[ARCH]** |
| Gate evolution | E entrance shifts across three phases; phase-2 gate analogous to Lion Gate (tower + corridor) | **[ARCH]** |

**Pausanias** marvels at Tiryns’ walls as Cyclopean wonder **[LIT]** — flavour text, not a thickness tape-measure.

### 4.3 Bronze Age citadel TD maps — shared stack
1. Plain approach / lower town (often lightly protected or separate)  
2. Ramp + gate bastion funnel  
3. Cyclopean curtain (Tiryns galleries as special wall tiles)  
4. Internal terraces / megaron palace  
5. Underground water stair or in-wall refuge  

**Do not** paste Long Walls into LH IIIB maps.

---

## 5. Sparta — the unwalled Classical city (design implications)

### 5.1 Classical fact pattern
- Classical Sparta is **famously without a continuous stone city circuit** comparable to Athens **[LIT]**  
- Thucydides **1.10.2**: if Sparta were deserted, its modest building stock would make later observers **underestimate** its power — a comment on **monumental emptiness**, widely paired in modern discussion with the city’s lack of showy walls/temples **[LIT][MOD]**  
- Ideological tradition: courage of citizens as wall (**“walls of men”** rhetoric in later moralizing sources) **[LIT]** Plutarch / anecdotal — tag carefully vs Thucydides’ actual sentence  
- Geography: Eurotas valley; periokic / helot system; field army + Peloponnesian League depth substitute for enceinte **[MOD]**

### 5.2 Later walls
| Phase | Notes | Tag |
|---|---|---|
| Temporary works | vs Demetrius Poliorcetes **~296**; vs Pyrrhus **~272** | **[LIT]** Pausanias |
| Nabis | First substantial regular fortification (~**195 BCE** horizon in Livy narrative); stone/brick; large enclosed area (modern estimates often ~**200 ha** order) | **[LIT]** Livy; **[MOD]** |
| Polybius size note | Walls **48 stades** circumference in one Polybian comparison tradition | **[LIT]** |
| After Nabis | Philopoemen / Achaeans dismantle; Romans later allow restoration | **[LIT]** Pausanias |

### 5.3 TD design implications
| Do | Don’t |
|---|---|
| Treat Classical Sparta maps as **open settlement + sanctuary hills + army spawn** | Default a Themistoclean-style circuit in 430 BCE |
| Use **field phalanx / crypteia / perioikoi forts** as defence layers | Claim Thucydides gives a tape-measure wall height |
| Unlock **Nabis walls** as a late “desperate modernization” campaign beat | Show Leonidas-era Sparta with Nabis curtains |

**Anti-keep lesson:** Sparta proves a TD civ can centre on **units and doctrine** rather than masonry — but that is a *different* fantasy than Athens’ wall bible.

---

## 6. Other major sites (select dossier)

### 6.1 Messene (Epaminondas era) — excellent ARCH showcase
| Feature | Value | Tag |
|---|---|---|
| Foundation | **369 BCE** after Leuctra; Messenian liberation from Sparta | **[LIT]** |
| Circuit length | **~9–9.5 km** | **[ARCH]** ephoreia / Pausanias concordance |
| Thickness | Often **~2.5–3 m** (two stone faces + rubble/earth fill) | **[ARCH]** |
| Height | Curtains preserved / restored in stretches; syntheses cite up to **~4.5–7 m** depending on sector and reconstruction | **[ARCH][MOD]** — preserve range |
| Towers | **40+** identified in modern counts (square and round; two-storeyed examples) | **[ARCH]** |
| Material note | All-stone to battlements unusual vs mudbrick norm of many peers — Pausanias stresses this | **[LIT][ARCH]** |
| Gates | **Arcadian Gate** (circular court, twin square towers, double entrance) — textbook Hellenistic gate; Laconian Gate toward Sparta | **[ARCH]** |
| Logic | Encloses city **and** agricultural / pastoral land on Ithome slopes — refuge economy | **[ARCH]** |

**Pausanias** praises Messene’s walls as stronger than those of other strongly fortified cities **[LIT]** — rhetorical, but archaeology backs “best preserved Classical–early Hellenistic circuit.”

**TD use:** Best **pure curtain-and-tower** Greek map; Arcadian Gate = signature funnel.

### 6.2 Syracuse — Epipolae / Euryalus
| Feature | Attested pattern | Tag |
|---|---|---|
| Problem | Athenian siege **415–413** showed plateau vulnerability | **[LIT]** Thucydides books 6–7 |
| Dionysius I response | Fortify Epipolae; fortress at inland tip (**Euryalus / Euryalos**), works **~402–397 BCE** | **[LIT]** Diodorus; **[ARCH]** |
| Northern crash build | Diodorus: **30 stades** N front in ~20 days with huge corvée — archaeological trace of N wall ~matches order (**~18,000+ ft**) | **[LIT][ARCH]** |
| Full Dionysian system length | Common modern figures **~27 km** / ~17 miles class for greater circuit traditions — **ranges disagree**; cite as **MOD range**, not one sacred number | **[MOD]** |
| Euryalus footprint | Fortress zone often cited **~15,000 m²** | **[MOD]** site presentations |
| Outworks | Multiple rock-cut **ditches** (trad. three; outer ones variously filled); galleries/tunnels; later five towers (~**15 m** tradition for catapults) | **[ARCH]** |
| Wall thickness (Dionysian curtains) | Reported **~3.3–5.35 m** in site syntheses; towers incl. **~8.5 × 8.5 m** examples | **[ARCH]/[MOD]** |
| Later | Archimedes-era improvements debated; Roman 212 siege; Byzantine reuse | **[LIT][MOD]** |

**TD use:** Best **artillery / ditch / plateau** Greek map — opposite of sacred Acropolis fantasy.

### 6.3 Corinth — Acrocorinth
- Monolithic citadel rock above Corinth; summit elevation often cited **~575 m** **[ARCH]**
- Controls approaches near the **Isthmus** — strategic “gate of the Peloponnese” logic **[LIT][MOD]**
- Ancient circuit under / within later Byzantine–Frankish–Venetian–Ottoman skins; walking perimeters in modern descriptions **~2–3 km** depending on which enceinte line is measured **[ARCH][MOD]** — **flag multi-period overlay**
- Approach typically from west through **successive gate lines** (three-zone experience in standing castle) **[ARCH]**
- Lower city also used Long Wall-type links in some periods (Corinthian analogues to Athenian Long Walls) **[LIT][MOD]**

**TD use:** Vertical **multi-gate mountain keep**; strip medieval crenellations if packing a strictly Classical skin.

### 6.4 Thebes — Cadmea
- Citadel of Thebes on a multi-summit plateau; Mycenaean palace horizon (~**14th–13th c.**) with Linear B administration **[ARCH]**
- Mycenaean wall stretches on east side toward later Elektra / Proitides gate alignments **[ARCH]**
- Literary **seven gates** (Elektra, Homoloidai, Proitides, etc.) — famous in tragedy; ground truth is patchy under modern city **[LIT][ARCH]**
- Classical–Hellenistic rebuilds at gates (Elektra visible Hellenistic work) **[ARCH]**
- Outer urban wall once below citadel **[ARCH][MOD]**

**TD use:** Mythic gate-names + fragmentary ARCH — good for **legendary layer**, weak for tape-measure curtains.

### 6.5 Plataea
- Boeotian ally of Athens; Persian Wars fame; **Peloponnesian War siege 429–427** detailed by Thucydides **[LIT]**
- Thucydides’ siege narrative = masterclass in **circumvallation, counters, breakouts, starvation** — use as **attacker/defender tutorial text**, not as a fully excavated wall catalogue **[LIT]**
- City walls existed; exact metrology secondary to the literary siege engine for designers **[MOD]**

### 6.6 Thermopylae — choke geography (not a wall)
- 480 BCE pass battle: Greek coalition holds coastal route against Persian host **[LIT]** Herodotus
- Ancient pass narrowed by mountains + **malarial marsh / sea**; modern coastline silted seaward — **do not** copy today’s highway width as 480 BCE geometry **[ARCH][MOD]**
- Defence = **terrain force-multiplier + rotation of hoplite fronts**, not a permanent fortress enceinte
- Phocian wall tradition at the pass appears in Herodotus as a local barrier repair — secondary to the geography itself **[LIT]**

**TD use:** Linear **lane map** with flank goat-path betrayal objective (Ephialtes tradition **[LIT]**); label Anopaea path carefully as literary.

---

## 7. Recommended dual TD centerpiece

### 7.1 Default pairing

| Role | Site / system | Why |
|---|---|---|
| **Sacred brand keep** | **Athens Acropolis** | Global recognition; Propylaea gate drama; Parthenon/Erechtheion ideology; Mycenaean deep time; Klepsydra / Mycenaean fountain siege water |
| **Military fortress system** | **Long Walls corridor + Athens–Piraeus–Munychia** | Unique Greek contribution to fortification strategy; measured km-scale geometry; 404/393 campaign beats; navy linkage |

**Default recommendation:** Ship **Acropolis (sacred)** + **Long Walls / Piraeus system (military)** as the dual centerpiece — one vertical sanctuary arena, one horizontal naval-fortress campaign.

### 7.2 Alternatives — pros / cons

| Candidate | Pros | Cons |
|---|---|---|
| **Messene** | Best-preserved tower circuit; Arcadian Gate; Epaminondas liberation story | Less universal brand than Acropolis |
| **Mycenae** | Lion Gate icon; Cyclopean spectacle; underground water | Bronze Age, not Classical Athens fantasy |
| **Tiryns** | Gallery walls unique mechanic; thickness up to ~17 m | Lower fame; low ridge less “dramatic cliff” |
| **Syracuse Euryalus** | Ditches, tunnels, artillery towers; Athenian siege backstory | Sicilian / Dionysian, not mainland brand core |
| **Acrocorinth** | Triple-gate mountain verticality; isthmus strategy | Heavy medieval overlay risk |
| **Cadmea** | Seven-gate mythos; Sacred Band era flavour | Poor continuous ARCH readability under modern Thebes |
| **Classical Sparta open city** | Doctrine-as-defence twist | Weak masonry TD fantasy |
| **Plataea siege** | Best literary siege tutorial | Map less iconic visually |
| **Thermopylae** | Ultimate choke lane | Not a fortification keep; FOLK overrun risk |

---

## 8. Grounded ~7-layer defence stack (Attic system)

Compose from **attested** Athenian features. Synthetic playable stack for one “Classical Athens” campaign map — **not** a claim every gate existed in every year.

| Layer | Attested feature sources | TD function |
|---|---|---|
| **1. Sea / Piraeus** | Triple harbours; fortified moles; ship-sheds; chainable mouths (tradition) | Naval spawn control; economy; loss = starvation clock |
| **2. Long Walls corridor** | Parallel walls ~6–6.5 km, ~180–183 m apart (Middle Wall phase); stone+mudbrick order ~5×10 m (secondary) | Protected supply lane; dual-wall sandwich defence |
| **3. City wall** | Themistoclean/Cononian circuit ~8.5 km; ~3 m thick; ~8–10 m high; Dipylon complex | Main urban HP ring; gate techs |
| **4. Gates** | Dipylon, Sacred Gate, Peiraic, Acharnian, etc. | Kill funnels; processional vs military entries |
| **5. Urban tissue** | Agora, streets, Kerameikos approaches; refugee crowding in wartime **[LIT]** | Soft HP; plague/morale events (430s) |
| **6. Propylaea** | Mnesicles from 437; five doorways; west-slope filter | Monumental gate boss; unfinished-build state as campaign scar |
| **7. Temples / Erechtheion–Parthenon** | Sacred plateau; treasury ideology; not solo fortress keeps | Final morale / victory totem; Persian-sack memory |

**Explicit non-layers (do not add by default):** Beulé Gate as Periclean; star bastions; concentric medieval donjon; Spartan-style “no walls” pasted onto Athens; Hollywood Acropolis laser statues **[FOLK]**.

---

## 9. Obscure fortification details for gameplay

1. **Mycenaean fountain stair of eight flights** under Acropolis N slope — stealth water lane **[ARCH]**  
2. **Klepsydra** fountain house ~470–460 as NW slope logistics node **[ARCH]**  
3. **West Cyclopean wall ~6 m thick** under Classical west approach — hidden “ancient armour” buff **[ARCH]**  
4. **Propylaea unfinished** — permanent scaffolding / missing wings as map scar **[ARCH]**  
5. **Beulé Gate spolia** — late recycle wall; unlock only in Roman/Byzantine epilogue **[ARCH]**  
6. **Long Walls refugee fill** between corridors in 431 — civilian overcrowding debuff **[LIT]**  
7. **Middle Wall ~183 m offset** — research unlock narrows map from triangle to corridor **[MOD]**  
8. **404 flute demolition** — scripted wall-strip cinematic (anecdotal colour) **[LIT]**  
9. **Conon + Pharnabazus rebuild** — Persian gold as uncomfortable alliance tech **[LIT]**  
10. **Proteichisma** fore-wall — late Classical outwork upgrade vs Macedonian threat **[ARCH]**  
11. **Munychia** as harbour citadel separate win-node **[ARCH][LIT]**  
12. **Lion Gate bastion right-side exposure** — directional defence bonus **[ARCH]**  
13. **Tiryns in-wall galleries** — casemate archer track inside wall thickness **[ARCH]**  
14. **Tiryns wall swelling to ~17 m** at tunnels — special “hollow wall” tile **[ARCH]**  
15. **Messene Arcadian Gate circular court** — classic trap courtyard **[ARCH]**  
16. **Messene all-stone curtains** — mudbrick fire vulnerability removed as civ bonus **[LIT][ARCH]**  
17. **Euryalus triple ditch + tunnels** — artillery fortress mini-game **[ARCH]**  
18. **Diodorus 20-day / 30-stade crash build** — timed construction mission (labour numbers are LIT rhetoric; keep geometry) **[LIT]**  
19. **Acrocorinth successive western gates** — triple loading screen naturalized **[ARCH]**  
20. **Thermopylae coastal reconstruction** — map must use palaeogeography, not modern shoreline **[ARCH][MOD]**  
21. **Sparta Nabis tile stamps** — anti-theft roof tiles as comedy logistics item **[LIT][MOD]**  
22. **Piraeus Hippodamian grid** — orthogonal tower placement buff inside harbour city **[LIT][ARCH]**  
23. **Dipylon double gate** — dual-passage traffic vs assault modes **[ARCH]**  
24. **Parthenon as treasury** — gold sink / tribute victory, not wall HP **[LIT]**  
25. **Cadmea seven-gate roster** — named gate skins from tragedy, ARCH-verified where possible **[LIT][ARCH]**  

---

## 10. Do / Don’t + bibliography

### 10.1 Do
- Dual-ship **Acropolis sacred brand** + **Long Walls/Piraeus military system**
- Prefer **Conwell lengths/spacing** and excavated gate plans over tourist round numbers when they conflict
- Keep **Mycenaean / Classical / Hellenistic / Roman** skins chronologically honest
- Use **Messene** when you need the cleanest tower-curtain ARCH flex
- Use **Euryalus** when you need ditches and artillery
- Tag siege water (Athens fountain; Mycenae cistern; Klepsydra) as first-class mechanics
- Let Sparta be the **anti-masonry** civ fantasy with late Nabis exception

### 10.2 Don’t
- Make the **Parthenon** a freestanding fortress keep with murder holes **[FOLK]**
- Date the **Beulé Gate** to Mnesicles **[ARCH]**
- Quote Long Wall **5×10 m** without labelling **secondary synthesis**
- Put a full Classical **stone circuit** on Thucydidean Sparta
- Use modern Thermopylae highway width as 480 BCE pass width
- Merge Dionysian Syracuse metrics into one false-precision kilometre claim when sources range
- Skin Acrocorinth as purely Classical when standing fabric is multi-period
- Invent Exact Tower Counts for Cadmea from Aeschylus alone

### 10.3 Bibliography (starting set)

**Athens Acropolis / water / gates**
- Iakovidis, Spyros. Acropolis Mycenaean fortification studies.
- Wright, James C. “The Mycenaean Entrance System of the Acropolis at Athens.” *Hesperia*.
- SETinSTONE project papers on Acropolis Mycenaean wall documentation / energetics (~760 m; 3.5–6 m; ~10 m).
- Travlos, John. *Pictorial Dictionary of Ancient Athens*.
- Korres, Manolis — Acropolis architecture / drawing corpora.
- Hellenic Ministry of Culture, Mycenaean Fountain site documentation.
- Parsons / later ASCSA work on Klepsydra; Ephorate restoration reports (court of Klepsydra).
- Plutarch, *Pericles* (Propylaea attribution to Mnesicles) **[LIT]**.

**Long Walls / city / Piraeus**
- Conwell, David H. *Connecting a City to the Sea: The History of the Athenian Long Walls* (Brill) — primary modern synthesis for lengths (~6.18 / 5.94 / 5.98 km), ~183 m spacing, phasing.
- Thucydides; Xenophon *Hellenica*; Diodorus Siculus **[LIT]**.
- Oxford Classical Dictionary, “Long Walls” (~6.5 km; ~180 m apart; 404 / Conon 393 tradition).
- Camp, John M. *The Archaeology of Athens*.
- Perseus / site summaries: Piraeus fortification walls; Munychia.

**Bronze Age citadels**
- Official Mycenae / Tiryns UNESCO and Greek ephoreia materials.
- Iakovidis and standard Argolid excavation reports (Lion Gate; Tiryns galleries; thickness ranges).
- Pausanias on Cyclopean marvels **[LIT]**.

**Messene / others**
- Greek Ephorate of Antiquities of Messenia — Messene walls & Arcadian Gate pages (~9.5 km).
- Müth / scholarly papers on Messene wall historical context (all-stone faces; 40+ towers).
- Pausanias 4.27 on foundation spectacle **[LIT]**.
- Diodorus on Dionysius’ Epipolae works; site presentations of Euryalus (ditches, ~15,000 m² zone).
- Lawrence, A.W. *Greek Aims in Fortification*; Winter, F.E. *Greek Fortifications*; McNicoll, A.W. *Hellenistic Fortifications from the Aegean to the Euphrates* **[MOD]**.
- Livy / Polybius / Pausanias on Sparta’s late walls and Nabis **[LIT]**.
- Herodotus on Thermopylae; geoarchaeology papers on pass palaeogeography **[LIT][ARCH]**.

---

## 11. Quick designer cheat-sheet

| Need | Best evidence pick |
|---|---|
| Sacred final keep | Acropolis temples + Propylaea (not Parthenon-as-donjon) |
| Military system map | Long Walls + Piraeus + Munychia |
| Exact corridor spacing | ~183 m (Conwell) / ~180 m handbook |
| Cyclopean spectacle | Tiryns galleries or Mycenae Lion Gate |
| Best preserved tower circuit | Messene ~9–9.5 km |
| Artillery / ditch fortress | Syracuse Euryalus |
| Mountain multi-gate | Acrocorinth (strip later skins as needed) |
| Unwalled doctrine civ | Classical Sparta → Nabis late unlock |
| Choke lane without castle | Thermopylae palaeo-pass |
| Literary siege tutorial | Plataea 429–427 (Thucydides) |
| Siege water mechanic | Acropolis Mycenaean fountain + Klepsydra |
| Gate funnel textbook | Lion Gate bastion or Messene Arcadian Gate |

---

## 12. Keep recommendation + 7-layer summary (executive)

**Keep recommendation:** Dual centerpiece — **Athens Acropolis (sacred brand)** + **Long Walls corridor / Athens–Piraeus–Munychia fortress system (military)**.

**7-layer Attic stack:**  
**(1)** Sea/Piraeus harbours & moles → **(2)** Long Walls corridor → **(3)** City circuit wall → **(4)** Named gates (Dipylon et al.) → **(5)** Urban tissue → **(6)** Propylaea → **(7)** Erechtheion–Parthenon sacred/ideological core.

---

*End of fortifications brief. Metric claims are tied to ARCH/LIT/MOD tags; where handbooks disagree (Long Wall height; Dionysian total length; Acrocorinth perimeter 2 vs 3 km; Tiryns circuit ~750 m as secondary), ranges are preserved rather than false precision invented.*
