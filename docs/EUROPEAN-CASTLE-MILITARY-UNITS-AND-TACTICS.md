# EUROPEAN CASTLE MILITARY UNITS AND TACTICS — Research Brief (Tower-Defence Game Doc)

> English research notes for a tower-defence game bible (same project family as Viking / Greek / Persian / Egyptian TD research).  
> **Scope:** **Medieval Western and Central European castle warfare, roughly 1000–1500 CE**—Angevin / Capetian / Imperial / Iberian Christian / Italian urban / English–Welsh–Scottish theatres as they intersect **castles, sieges, and feudal–mercenary recruitment**. This is **not** “all Europe forever,” not Byzantine primary doctrine, not Ottoman field armies, not early modern pike-and-shot as a whole (only late medieval pike / early gunpowder as they touch castle war).  
> **Method:** Prefer **archaeology of castles and arms** + **contemporary chronicles / administrative records** over Hollywood chivalry. Flag every claim with an evidence class. Do **not** invent TO&Es, company strengths, or “standard medieval kit lists.”  
> **Hard myths:** **Plate armour on every 11th-c. peasant** = **FOLK**; **boiling oil as default defence** = overplayed / often **FOLK**; **knights always charging in shining full plate** across five centuries = anachronistic mash-up; **castles as passive stone boxes** ignore sorties, logistics, and negotiation.

### Evidence tags (use throughout)
| Tag | Meaning |
|---|---|
| **ARCH** | Archaeology (castles, siege works, weapon finds, armour, mining galleries, artillery stones) |
| **CHR** | Contemporary or near-contemporary chronicles, letters, administrative rolls, ordinances, chivalric narrative with dates (Froissart, Joinville, Villehardouin, Matthew Paris, Galbert of Bruges, Welsh/English royal records, etc.—**bias flagged**) |
| **MOD** | Modern scholarship (Contamine, Keen, Prestwich, Strickland, Bradbury, Purton, France, Rogers, DeVries, Ayton, Nicholson, Gravett, Kenyon, Higham, etc.) |
| **FOLK** | Hollywood knights, Victorian romance, games treating all centuries as one kit, “boiling oil everywhere,” peasant plate armies |

### Core primary / near-primary sources (bias table)
| Source | Nature / caution |
|---|---|
| **Galbert of Bruges**, *De multro…* (1127–28) | Rare close account of urban–castle politics and siege around Bruges / Count of Flanders murder crisis. **CHR** |
| **Orderic Vitalis**, *Historia Ecclesiastica* | Norman / Anglo-Norman warfare, castles, rebellions; monastic framing. **CHR** |
| **William of Tyre** / Crusade chroniclers | Levantine sieges often better documented than western ones—use cautiously as **transferable technique**, not western TO&E. **CHR** |
| **Joinville**, *Life of Saint Louis* | Mid-13th-c. aristocratic eyewitness (esp. crusade); courtly filter. **CHR** |
| **Matthew Paris**, *Chronica Majora* | English 13th-c. politics, sieges, illustrations; partisan. **CHR** |
| **Jean Froissart**, *Chronicles* | Hundred Years’ War colour; chivalric staging, numbers inflated, tournaments and “honour” bias. **CHR** |
| ***Gesta Henrici Quinti*** / English campaign narratives | Agincourt–Harfleur era English propaganda + logistics hints. **CHR** |
| **Villehardouin**, *Conquest of Constantinople* | Early 13th-c. crusade/siege narrative; baron viewpoint. **CHR** |
| **Royal / civic ordinances** (e.g. English Assize of Arms traditions; French *ordonnances*; Italian city statutes; Genoese contracts) | Recruitment obligations, crossbow regulation, pay—**not** battlefield photos. **CHR**/admin |
| **Indentures / muster rolls / account books** (esp. later HYW) | Best quantitative grip on paid men-at-arms, archers, retinues—still incomplete. **CHR**/admin + **MOD** |
| **Chansons de geste / romances** | Cultural ideal of knighthood; poor TO&E source. Literary / **FOLK**-adjacent if treated as drill |

### Core modern scholarship (entry points)
Philippe **Contamine** (*War in the Middle Ages*); Maurice **Keen** (*Chivalry*; *Medieval Warfare*); Michael **Prestwich**; Matthew **Strickland**; Jim **Bradbury** (*The Medieval Siege*); Peter **Purton** (*A History of the Early Medieval Siege* / *Late Medieval Siege*); John **France**; Clifford J. **Rogers**; Kelly **DeVries**; Andrew **Ayton**; Helen **Nicholson**; Christopher **Gravett**; John R. **Kenyon**; Robert **Higham** / Philip **Barker** (timber castles); R. Allen **Brown**; Charles **Oman** (outdated in places—use carefully); Michael **Prestwich** / Anne **Curry** (HYW armies); Kelly DeVries / Robert D. Smith on early gunpowder; Thom **Richardson** / Royal Armouries on armour; David **Nicolle** (wide but verify); Sean **McGlynn**; Stephen **Morillo**; John **Gillingham**; Michael **Wolfe** (ed. *Medieval Europe from Another Angle* essays vary).

---

## 0. Framing: castle warfare is not “knights forever”

**Geographic / political patchwork.** “Western/Central Europe” here means roughly the Latin Christian zone of France, England, Low Countries, German lands of the Empire, northern Italy’s communes and signorie, Christian Iberia, and adjacent marcher zones—**linked by castle technology and feudal–contract military culture**, not by a single army. Polish–Hungarian–Bohemian theatres share much castle grammar late; treat as optional Central European colour, not a second bible. **MOD**

**Periodization for game eras (not rigid TO&Es).**
| Era (broad) | Approx. | Military / castle character |
|---|---|---|
| Motte-and-bailey / early stone | c. 1000–1150 | Timber castles, early keeps; knightly households; crossbow rising; siege craft still Classical–Byzantine–Islamic transfer + local |
| High medieval concentric / crusader feedback | c. 1150–1300 | Greater stone sophistication; professional crossbowmen; mining + trebuchet maturity; garrison ordinances |
| Later medieval paid retinues | c. 1300–1415 | Indentured men-at-arms + archers (esp. English); Genoese crossbow contracts; chevauchée + siege mix |
| Gunpowder hinge | c. 1415–1500 | Early cannon reshape siege timelines; Swiss pike fame in field; plate armour peak for elites; bastion thinking begins *after* this brief’s heart |

**What a “castle” does.** Administrative centre, lordly residence, prison, treasury, **force multiplier for a small garrison**, road/river control, and negotiation chip—not only a TD “keep sprite.” **ARCH** + **CHR** + **MOD**

**Knight ≠ army.** Mounted aristocratic warriors (and later broader “men-at-arms”) are **prestige shock and political leadership**, not the numerical bulk of sieges. Diggers, carpenters, crossbowmen, spearmen, labourers, and paid specialists win or lose walls. **CHR** + **MOD**

**Garrison vs field army (core TD distinction).**
| Mode | Typical composition emphasis | Goal |
|---|---|---|
| **Garrison** | Small permanent or short-term ward: constable, few knights/men-at-arms, crossbowmen/archers, spearmen, servants; local militia surge | Hold, delay, sortie, signal relief, negotiate |
| **Field army / relief host** | Larger temporary concentration: retinues, levies, mercenaries, baggage | Battle, blockade relief, chevauchée, or formal siege train |
| **Siege camp** | Mixed: combatants + massive non-combat labour, engineers, foragers | Invest, starve, batter, mine, storm |

Do **not** staff every castle like a field host. Small garrisons holding large works are historically normal. **CHR** + **MOD** (Contamine, Prestwich)

**Evidence quality (framing).**
| Topic | Quality |
|---|---|
| Castles as political–military nodes | **High** (**ARCH** + **CHR**) |
| Crossbow ubiquity in continental siege defence | **High** |
| Exact garrison lists as universal template | **Low** — local & chronological |
| Longbow as *the* medieval European bow | **False if universalized** — English/Welsh specialty fame |
| Boiling oil as standard | **Weak / overstated** (**FOLK** bias) |
| Full plate on 11th-c. infantry | **False** (**FOLK**) |

**Game-use potential:** Faction = **lordship / crown / commune / mercenary market**, not “Kingdom of Knights.” Age track: motte timber → stone keep → concentric + trebuchet → indentured archer armies → cannon hinge. Defender win conditions include **relief army arrival**, **starve-out failure**, **negotiated surrender with terms**—not only last-keep HP.

---

## 1. Soldier types (castle-relevant roster)

### 1.1 Knights and men-at-arms

**What.** *Miles* / knight begins as a **mounted warrior of recognized status** bound by tenure, household service, or pay; by the later Middle Ages “**man-at-arms**” (*homme d’armes*) often denotes a fully equipped heavy cavalryman/heavy combatant who may be knighted or not. **CHR** + **MOD** (Keen, Contamine, Ayton)

**Equipment trajectory (do not flatten centuries).**
| Phase | Typical elite protection (broad) | Notes |
|---|---|---|
| 11th–12th c. | Mail hauberk, conical/nasal helm, kite shield, lance, sword | Mail expensive; not peasant kit. **ARCH** + **CHR** |
| 13th c. | Mail + early coat-of-plates / reinforced points; helms evolving (great helm for tourney/war contexts) | Transitional plate elements appear. **ARCH** |
| 14th–15th c. | Progressive plate harness for those who can pay; mail at gaps | Full “white harness” = **elite / well-funded**, not levy default. **ARCH** + **MOD** |

**Tactical role around castles.**
- **Field:** shock, pursuit, intimidation, command.
- **Siege:** often **dismount** for assault parties, hold perimeter against sorties, fight in gates/breaches; horses vulnerable and logistically costly in long investments. **CHR** + **MOD**
- **Garrison:** few knights as leadership + heavy close fighters; prestige out of proportion to numbers.

**Controversies / cautions.**
1. **Chivalry as soft combat rules:** tournaments and ransom culture real among elites (**CHR** Froissart; **MOD** Keen)—not a Geneva Convention; towns and peasantry often outside the courtesy circle.
2. **“Knightly charge wins sieges”:** walls neutralize pure cavalry; siege is engineering + attrition. **MOD**
3. **Hollywood lonely knight hero clearing battlements:** **FOLK** relative to labour and missile dominance on walls.

**Game-use:** Expensive elite; aura/command; strong in melee breaches and counter-sorties; weak as mass lane-filler; upgrade tree must **age-gate armour** (mail → transitional → plate).

### 1.2 Sergeants (*servientes*, serjeants)

**What.** Broad, slippery category: **non-knightly professional or semi-professional fighters** serving lords—mounted sergeants, foot sergeants, castle sergeants. Status below knight, above anonymous levy. **CHR**/admin + **MOD**

**Role.** Backbone of many garrisons and retinues; can be crossbow-armed or melee; cheaper heavy-ish infantry/cavalry substitute. Do **not** invent a single universal kit. **MOD**

**Game-use:** Mid-cost durable garrison infantry; buildable from barracks/sergeantry; good default wall defenders when knights are scarce.

### 1.3 Crossbowmen (*balistarii*)

**What.** Specialists with spanning devices (belt claw, goat’s foot, later cranequin/windlass for heavier bows). Dominant **missile system of continental castle defence and many Italian / French / Iberian forces**. **CHR** + **ARCH** + **MOD**

**Why castles love them.** Mechanical draw stores energy; usable in confined wall-walks and loops; less training time than war longbow culture; bolts hit hard against mail and early plate at combat ranges (exact penetration debates—**MOD**). Rate of fire lower than longbow—mitigated by **pavises**, rotating shooters, and covered galleries. **MOD**

**Genoese crossbowmen.** Famous contracted specialists (e.g. Crécy tradition of Genoese in French service—**CHR** Froissart; performance narratives contested). Represent **mercenary missile quality**, rain-sensitive spanning debates in chronicles, and pavise tactics. **CHR** + **MOD**

**Church bans / moral panic.** Periodic conciliar condemnations of crossbows against Christians (Second Lateran 1139 often cited)—**enforcement uneven**; weapon remains ubiquitous. **CHR** + **MOD**—do not treat ban as disappearance.

**Game-use:** Core tower unit for continental defender; slow RoF / high damage; pavise upgrade; Genoese = premium mercenary tower crew.

### 1.4 English (and Welsh) longbowmen

**What.** War bow culture peaking in English armies of the 14th–15th c.; Welsh origins/importance in English recruitment narratives. Requires **years of practice**; not a drop-in peasant overnight unlock for all Europe. **CHR** + **MOD** (Strickland & Hardy; Prestwich; Curry)

**Role vs castles.** Devastating in **field** and in **defence of English-held places**; useful on walls and in sorties; less a “universal medieval unit” than a **faction specialty**. Siege offence: suppress wall loops, cover approaches—still need engineers for stone. **MOD**

**Agincourt/Crécy myth overload.** Real tactical successes (**CHR**) became **FOLK** “longbow beats everything forever.” Mud, stakes, discipline, French command failures, and dismounted men-at-arms matter. **MOD**

**Game-use:** English/Welsh faction unique: high RoF tower/garrison archers; stake field deployable; not the default for Capetian or Imperial castles.

### 1.5 Spearmen and late pikemen

**What.** Spear is the **default infantry weapon** across the period for urban militias, levies, and garrison foot. **Pike** (very long spear) becomes famous with **Flemish** (e.g. Courtrai 1302 narratives), **Scottish** schiltrons, and especially **Swiss** cantonal armies in the 15th c.—primarily a **field** anti-cavalry system that reshapes late medieval battle, with secondary relevance to relieving armies and open approaches. **CHR** + **MOD**

**Castle relevance.** Spears for gate fighting, wall-top thrusting, anti-horse on approaches; deep pike blocks rarely “fit” on wall-walks—use in **bailey courtyards, relief battles, and camp defence**. **MOD**

**Game-use:** Cheap lane blockers; anti-cavalry aura on open ground; Swiss pike = late mercenary field unlock, not early keep garrison.

### 1.6 Mercenaries (brief: Genoese, Swiss, and the market)

**What.** Paid professionals under contract (*condotta* in Italy; indenture in England; *routiers* / Free Companies in France during HYW chaos). Castles are **payroll nodes** and **prey**. **CHR** + **MOD** (Contamine, Mallett, Fowler)

| Type | Niche | TD hook |
|---|---|---|
| **Genoese / Italian crossbow** | Premium missile | Hireable tower crew; costly upkeep |
| **Swiss pike** (late) | Field shock / anti-cavalry | Relief-army unit; scary in open lanes |
| **Free Companies** | Predatory veterans | Neutral hostile waves; can be bribed or hired |
| **German / Brabantine / Gascon specialists** | Varied | Flavour contracts |

**Do not** make mercenaries morally “OOC villains” only—crowns and communes depended on them. **MOD**

### 1.7 Garrison specialists and non-roster labour

**Constable / castellan**, watch rotations, porters, smiths, carpenters, miners (defence countermines), chaplains, servants—**ARCH**/accounts + **CHR**. Labourers and pressed peasants dig approaches and fill ditches for attackers. **Game-use:** economy units, build speed, repair, and “population morale” meters—not joke comic relief.

### 1.8 Urban militia and communal troops

Italian, Flemish, and some Imperial cities field **militia** with crossbow statutes, banners, and guild obligations. Castles and town walls blur. **CHR**/statutes + **MOD**. **Game-use:** cheap surge defenders; political loyalty mechanic.

---

## 2. Weapons and protection (age-gated, class-gated)

### 2.1 Lance, sword, dagger, mace, pollaxe

- **Lance:** cavalry primary; broken/replaced often. **CHR** + **ARCH**
- **Sword:** status + sidearm; not always the battlefield primary for infantry. **MOD**
- **Pollaxe / poleaxes (late):** elite dismounted murder weapons in plate era breaches and melees. **ARCH** + **MOD**
- **Maces/war hammers:** anti-armour percussion rising with plate. **ARCH**

### 2.2 Bows and crossbows (see §1.3–1.4)

Hand bows exist widely (hunting + war); **English longbow institutional culture** is the outlier fame case. Short bows / hunting bows ≠ Crécy tactics. **MOD**

### 2.3 Armour myths to kill early

| Claim | Reality | Tag |
|---|---|---|
| Plate on every 11th-c. peasant | False; mail itself elite-expensive | **FOLK** |
| Knights too heavy to mount / swim / stand | Largely Victorian joke; trained men mobile; heat and endurance still limit | **FOLK** vs **MOD** |
| Leather “studded” Hollywood armour as standard | Mostly costume language | **FOLK** |
| Identical kit from Hastings to Bosworth | Five centuries of change | **FOLK** if flattened |

**Game-use:** Visual armour **must** follow era slider; peasant/levy = spear + gambeson/shield at best for much of the period.

---

## 3. Garrison vs field army — operational contrast

### 3.1 Garrison logic

**Small numbers, high leverage.** A few dozen to a few hundred can hold a strong site against larger hosts if walls, stores, and morale hold—until mining, artillery, treachery, or starvation. **CHR** + **MOD**

**Watch and signal.** Beacons, messengers, church bells, smoke—relief is a **timer mechanic**. **CHR**

**Terms of surrender (*composition*).** Honourable exits with horse and arms appear in chivalric–legal practice when no relief comes—**CHR**; massacre after refused storm warnings also appears. Design both. **MOD**

**Sorties.** Sudden gated attacks to burn engines, take prisoners, wreck trenches—**central** to active defence, not optional flavour. **CHR** (many siege narratives) + **MOD** Bradbury / Purton

### 3.2 Field army logic

**Chevauchée:** destructive mounted raid to force battle, wreck tax base, or shame a ruler (HYW English practice)—**CHR** + **MOD** Rogers. Interacts with castles as **islands of refusal**: invaders burn countryside while strongpoints hold.

**Relief battles:** attacker may have to fight a covering army (e.g. classic pattern: invest castle → defender’s lord raises host → battle or negotiation).

**Logistics:** fodder, dysentery, desertion, pay arrears—sieges fail from **camp rot** as often as from heroics. **CHR** + **MOD**

**Game-use:** Attacker “siege stamina” meter; defender “relief ETA”; bribe/negotiate buttons; chevauchée as alternate win path that ignores some towers but raises partisan spawns.

---

## 4. Siege attack methods (castle offence)

### 4.1 Blockade and starvation

**Default mature option.** Seal gates, control water, wait. Faster if treachery or disease. **CHR** + **MOD**. In TD: slow damage to keep “stores”; counterplay = sorties and smuggling events.

### 4.2 Direct assault / escalade

Ladders, grapples, rush gates after suppression. Extremely bloody against prepared loops and hoardings. **CHR** + **MOD**. TD: high-risk wave type; needs missile suppression buffs.

### 4.3 Battering rams

Covered rams (*testudines*/sow analogues in medieval vocabulary) vs gates and weak wall sections. Fire and dropped stones counter them. **CHR** + **ARCH**/depictions + **MOD**. TD: gated boss tool; vulnerable to fire towers and sorties.

### 4.4 Siege towers (*belfries*)

Mobile towers to overtop walls; terrain grading and ditch-filling required. Burnable; slow. **CHR** + **MOD**. TD: lane vehicle with high HP, fire weakness, arrives late in siege tech tree.

### 4.5 Mining and countermining

Dig under wall → prop with timber → burn props → collapse (or create breach chambers). Defenders dig counters, listen with bowls of water/bronze (period listening lore—treat technique claims carefully), fight underground. **CHR** + **ARCH** (known mine galleries at some sites) + **MOD** Purton. **Among the best historical answers to high stone walls before reliable heavy cannon.**

**Game-use:** subterranean mini-lane; audio warning mini-game; collapse removes a tower segment.

### 4.6 Trebuchet and mechanical artillery

Traction → **counterweight trebuchet** maturity (12th–13th c. debates on diffusion—**MOD**). Throws stones to batter wall crowns, smash hoardings, demoralize, sometimes carcass/incendiary loads (frequency debated). **CHR** + **ARCH** (stone shot finds) + **MOD**. TD: long-range siege battery unlocking wall HP damage; inaccurate but terrifying.

### 4.7 Early cannon (*gunpowder hinge*)

From 14th c. increasingly recorded in sieges; 15th c. becomes decisive against **tall thin medieval walls** in many theatres (e.g. narratives around Byzantine 1453 are famous but **out of western-castle scope**—use western examples: French bureau of artillery, Wars of the Roses strongholds, Granada war as late Iberian hinge). Loud, slow, logistical nightmare early; later siege-breaking. **CHR** + **ARCH** + **MOD** DeVries / Smith.

**Game-use:** late-age wall shredder; reshapes map from vertical walls to lower angled works (tease bastion DLC beyond 1500).

### 4.8 Psychological / political tools

Display of prisoners, threatened storm, bribery of porter, false relief rumours, papal interdicts—**CHR**. TD: status effects and faction diplomacy.

### 4.9 Best historical attack package (for TD ranking)

**Before mature cannon:** combine **blockade** + **mining** + **trebuchet suppression of hoardings** + **ram/tower at a created breach**, screened against sorties. Pure ladder spam is the sucker’s bet. **MOD** reading of Bradbury/Purton.

**After cannon mature:** artillery breach + assault; mining still useful; timelines shorten.

---

## 5. Castle defence methods

### 5.1 Passive architecture (force multipliers)

| Feature | Function | Evidence |
|---|---|---|
| **Ditch / wet moat** | Stop rams/towers; expose attackers | **ARCH** |
| **Curtain + towers** | Flanking fire along walls | **ARCH** |
| **Gatehouse / barbican** | Kill zones at entry | **ARCH** + **MOD** |
| **Murder holes** (*meurtrières*) | Vertical attack in gate passages | **ARCH** (interpret openings carefully—some are for water/sand/refuse) + **MOD** |
| **Arrow loops / crosslet loops** | Protected shooting | **ARCH** |
| **Hoarding / brattices** (timber galleries) | Project defence outward; drop stones; cover wall foot | **CHR**/accounts + **ARCH** (putlog holes) + **MOD** |
| **Machicolations** (stone) | Permanent stone version of dropping gallery | **ARCH** late hardening |
| **Concentric plans** | Fallback lines | **ARCH** |
| **Keep / last refuge** | Final hold + symbol | **ARCH** |

### 5.2 Active defence

**Sorties** (see §3.1)—burn engines, spike cannon, capture engineers.  
**Countermining.**  
**Repair crews** under fire.  
**Water and fire discipline** — extinguishing flaming arrows/towers.  
**Staged retreats** from outer bailey to inner ward.

### 5.3 “Boiling liquids” debate

| Substance | Historicity | Tag |
|---|---|---|
| **Boiling water** | Plausible, cheap | **CHR**/occasional + **MOD** cautious |
| **Hot sand / quicklime dust** | Discussed in military lit; nasty in confined gateways | **MOD** + some **CHR** |
| **Boiling oil / tar** | Oil expensive; dramatic in romance and film; **over-represented** in popular image | often **FOLK** as *default*; rare/expensive if real |
| **Greek fire clones** | Not standard western castle kit | **FOLK** if ubiquitous |

**Design rule:** prefer **stones, bolts, heated sand/water, fire against timber engines**; treat cinematic oil cauldrons as rare luxury or stylized VFX with a truth tooltip. **MOD**

### 5.4 Best historical defence package (for TD ranking)

1. **Deny approaches** (ditches, cleared fields of fire).  
2. **Missile dominance** from loops + hoardings (crossbow core; longbow if English).  
3. **Active sorties** against engines.  
4. **Countermine** readiness.  
5. **Stores + morale + relief timer**.  
6. **Negotiated surrender** as non-fail state when relief is impossible—historical, not “player cowardice.” **CHR** + **MOD**

---

## 6. Best attack / defence for TD (designer ranking)

### 6.1 Best attack methods (western castle TD)

1. **Starvation blockade** with forage denial — slow, authentic win. **CHR** + **MOD**  
2. **Mining** under a chosen tower — removes map segments. **ARCH** + **CHR**  
3. **Trebuchet suppression** of hoardings and wall-top crews. **CHR** + **ARCH**  
4. **Covered ram** on gate after ditch fill. **CHR**  
5. **Siege tower** on prepared lane — late, expensive. **CHR**  
6. **Early cannon battery** (late age) — meta shift. **CHR** + **MOD**  
7. **Treachery / bribed porter** event — historically decisive more than heroes admit. **CHR**  
8. **Escalade** only after suppression — high casualty gamble. **MOD**  
9. **Chevauchée pressure** to force field battle / pull garrison thin. **CHR** + **MOD** Rogers  
10. **Free Company hire** to add veteran stormers. **CHR**

### 6.2 Best defence methods (against medieval siege)

1. **Hoarding + loop crossbow fire** into ditch dead ground. **ARCH** + **MOD**  
2. **Sortie teams** (sergeants + a few men-at-arms) timed to engine builds. **CHR**  
3. **Countermine minigame** vs attacker tunnels. **CHR** + **ARCH**  
4. **Gatehouse murder-holes + multiple barriers** (doors, portcullis). **ARCH**  
5. **Wet moat / flooded ditch** vs mining and towers. **ARCH**  
6. **Staged concentric fallback** — lose outer bailey without lose game. **ARCH**  
7. **Relief army spawn** on strategic timer. **CHR**  
8. **English longbow stakes** on open relief approaches (faction). **CHR** + **MOD**  
9. **Pavise crossbow rotating teams** on wall. **MOD**  
10. **Honourable composition** — preserve army for next scenario. **CHR**

### 6.3 TD lane translation (concise)
| Historical dynamic | TD mapping |
|---|---|
| Wall-walk + loops | Elevated tower slots |
| Hoarding | Temporary wooden tower upgrade (burnable) |
| Gatehouse | Chokepoint with vertical traps |
| Mining | Underground lane / segment destruction |
| Trebuchet | Attacker artillery that damages towers |
| Cannon (late) | Wall-HP shredder; forces rebuild tech |
| Sortie | Defender limited-duration counter-wave |
| Starvation | Keep stores meter |
| Chevauchée | Side-map raid ignoring some walls |
| Mercenary contracts | Shop units with upkeep crisis |
| Relief host | Timed allied wave |
| Composition surrender | Optional non-wipe exit |

---

## 7. Obscure / gameable details (20+)

Each item tagged; contested items marked.

1. **Putlog holes.** Rows of square sockets in curtain walls marking former **hoardings**—archaeology of vanished timber defence. **ARCH**  
2. **Hoarding fire vulnerability.** Timber galleries are force multipliers *and* fire traps; attackers aim incendiaries at them. **CHR** + **MOD**  
3. **Portcullis + dual doors.** Layered gate killing box; attackers trapped mid-passage. **ARCH**  
4. **Murder-hole ambiguity.** Not every ceiling slot is a weapon port—some serve drainage or domestic functions; interpret per site. **ARCH** + **MOD**  
5. **Listening pots / bowl lore for countermining.** Period and later technical literature describe acoustic detection—use as flavour with uncertainty. **CHR**/tech lit + **MOD** caution  
6. **Springalds / espringals.** Tension/torsion bolt-throwers on walls—medieval artillery beyond trebuchet. **CHR** + **MOD**  
7. **Mangonel vocabulary mess.** Medieval Latin/vernacular names for engines overlap and confuse; designers should pick glossary carefully. **MOD**  
8. **Carcass / dead animals over walls.** Disease and morale warfare claims appear in narratives—efficacy debated. **CHR** + **MOD**  
9. **Assize of Arms / equipment obligations.** English (and analogues elsewhere) legal floors for free men’s weapons—**not** proof everyone owned warhorse + mail. **CHR**/admin  
10. **Indenture retinues.** Late English captains contract mixed men-at-arms + archers as paid packages—roster builder model. **CHR** + **MOD** Ayton  
11. **Pavise shields.** Large mobile covers for crossbowmen reloading—Italian/continental visual language. **ARCH**/art + **CHR**  
12. **Cranequin vs windlass.** Spanning tech tiers for heavier crossbows—upgrade tree with RoF tradeoffs. **ARCH** + **MOD**  
13. **Gambeson / aketon.** Padded jackets as real protection and under-armour; peasant “plate” substitute myth ignores this. **ARCH** + **MOD**  
14. **Coat of plates / brigandine.** Transitional and later torso defence—internal plates riveted; looks “studded” from outside → Hollywood confusion. **ARCH**  
15. **Great helm vs bascinet.** Vision/heat tradeoffs; parade vs war use nuance. **ARCH** + **MOD**  
16. **Castellum / peels / tower houses.** Smaller fortified dwellings on marches—mini-keeps for map variety. **ARCH**  
17. **Chemin de ronde.** Wall-walk circulation critical to shifting defenders between towers. **ARCH**  
18. **Postern gates.** Small exits for sorties and secret resupply—defender special unlock. **ARCH** + **CHR**  
19. **Barbicans as sacrificial foreworks.** Delay and canalize; can be given up. **ARCH** + **MOD**  
20. **Siege camps as anti-castles.** Attackers dig their own ditches against sorties (circumvallation ideas persist from Classical practice). **CHR** + **MOD**  
21. **Ransom economy.** Captured elites worth more alive—affects AI kill vs capture. **CHR** + **MOD** Keen  
22. **Free Companies’ “bastides” and garrison extortion.** Predatory logistics in HYW France. **CHR** + **MOD** Fowler  
23. **Swiss pike push in open relief battles.** Late meta vs knightly charges—doesn’t obsolete walls overnight. **CHR** + **MOD**  
24. **Genoese at Crécy wet-string narrative.** Chronicle blame story; historians argue command and tactical context. **CHR** Froissart vs **MOD** revision  
25. **Women in sieges.** Commanders’ wives holding castles appear in chronicles (e.g. famous hold-out stories)—rare leadership episodes ≠ mass female infantry. **CHR** + **MOD**  
26. **Mining “camouflet” counter-blasts.** Explosive countermine ideas grow with gunpowder age—late period spice. **MOD**  
27. **Stone shot calibres.** Heaps of carved balls at sites = trebuchet/cannon archaeology. **ARCH**  
28. **Keep as lordly hall first.** Domestic planning (hall, chamber, chapel) shapes “military” plans—castles are homes. **ARCH** + **MOD**  
29. **Timber motte castles raised in weeks.** Speed-build offensive fortification (Norman model narratives)—attacker can drop a counter-castle. **CHR** + **ARCH** + **MOD** Higham/Barker  
30. **Counter-castle / siege fort (*malvoisin* tradition).** Besiegers build their own tower-fort to dominate a gate. **CHR** + **MOD**  
31. **Boiling oil cost.** Rendered fat/oil as scarce calorie and craft resource—why water/sand win the realism contest. **MOD** / economic logic vs **FOLK**  
32. **Arrowstorm vs bolt discipline.** Two missile cultures: volume (longbow) vs punch/ease of wall use (crossbow). **MOD**

---

## 8. Designer do / don’t

### Do
- Age-gate kit: **mail world ≠ plate world**.  
- Make **crossbows the continental wall default**; longbow a regional specialty.  
- Centre gameplay on **siege engineering + stores + relief timers**, not only DPS knights.  
- Use **sorties** as a core defender verb.  
- Show **garrison smallness** vs field-host scale.  
- Include **negotiation / composition** endings.  
- Differentiate **sergeants** from knights without making them comic levies.  
- Let **mining and trebuchets** matter before cannon.  
- Treat **mercenaries** as unpaid-risk upkeep politics.  
- Flag **FOLK** tropes in tooltips when you stylize (oil, lone knight, peasant plate).

### Don’t
- Put **full plate on every 11th-century peasant** (or every spearman in 1200). **FOLK**  
- Make **boiling oil** the primary defence VFX.  
- Pretend **one “medieval European army”** covers Toledo to Riga across five centuries.  
- Let **cavalry charges** ignore ditches, stakes, and loops.  
- Equate **Froissart pageantry** with logistics reality.  
- Invent precise **regimental TO&Es** for “the castle garrison.”  
- Erase **labourers and carpenters** from siege success.  
- Use **Swiss pike** as an early-game keep unit.  
- Flatten **Genoese crossbowmen** into generic archers.  
- Treat **castles as empty monster forts** without domestic/administrative function.  
- Assume **church crossbow bans** removed the weapon from war.  
- Copy **Levantine crusader castle plans** wholesale onto every western manor without comment.

---

## 9. Quick roster index (game bible — labelled)

| Unit / element | Historicity label | Role |
|---|---|---|
| Knight / early *miles* (mail) | **Strong** | Elite command / shock (field); breach melee |
| Man-at-arms (late, plate possible) | **Strong** (funded elites) | Durable late melee |
| Mounted / foot sergeant | **Strong** | Mid garrison backbone |
| Crossbowman | **Strong** | Default wall missile (continental) |
| Genoese crossbow mercenary | **Strong** (as hired specialists) | Premium missile |
| English/Welsh longbowman | **Strong** (regional) | High RoF specialty |
| Spearman / militia | **Strong** | Cheap blocker |
| Swiss pikeman (late) | **Strong** (late field) | Anti-cav relief unit |
| Free Company veteran | **Strong** (14th–15th c.) | Hireable stormer / threat |
| Miner / sapper | **Strong** | Breach specialist |
| Trebuchet crew | **Strong** | Siege battery |
| Early gun crew | **Medium–strong** (late) | Wall shredder |
| Castellan hero | **Strong** as role | Defence aura / events |
| Levy labourer | **Strong** (non-glamour) | Ditch fill / repair |
| Peasant in full plate | **False** | Do not roster |

---

## 10. Bibliography entry points (suggested reading order)

### Start here (modern syntheses)
1. Philippe **Contamine**, *War in the Middle Ages* — recruitment, society, change over time. **MOD**  
2. Jim **Bradbury**, *The Medieval Siege* — attack/defence catalogue with chronicle grounding. **MOD**  
3. Peter **Purton**, *A History of the Early Medieval Siege* and *A History of the Late Medieval Siege* — denser siege science. **MOD**  
4. Maurice **Keen**, *Chivalry* — culture of the knightly class (not a drill book). **MOD**  
5. Michael **Prestwich**, *Armies and Warfare in the Middle Ages: The English Experience* — administration meets war. **MOD**

### Castles and architecture
6. R. Allen **Brown**, *English Castles* — classic architectural–military intro. **MOD**  
7. Robert **Higham** & Philip **Barker**, *Timber Castles* — motte-and-bailey reality. **MOD**/**ARCH**  
8. John R. **Kenyon**, *Medieval Fortifications* — survey. **MOD**  
9. Site reports / English Heritage / Cadw / Castles Studies Group summaries for **putlogs, mines, gatehouses**. **ARCH**

### Arms, armour, missiles
10. Thom **Richardson** / Royal Armouries publications — armour chronology. **MOD**/**ARCH**  
11. Matthew **Strickland** & Robert **Hardy**, *The Great Warbow* — longbow evidence and limits. **MOD**  
12. Kelly **DeVries** (works on medieval military technology; with Robert D. **Smith** on gunpowder). **MOD**

### Hundred Years’ War / paid armies
13. Anne **Curry** — Agincourt / HYW armies and sources. **MOD**  
14. Andrew **Ayton** — indentures, retinues, horses. **MOD**  
15. Clifford J. **Rogers** — chevauchée strategy debates. **MOD**  
16. Kenneth **Fowler**, *Medieval Mercenaries* / Free Companies studies. **MOD**

### Primary / translated entry points
17. **Galbert of Bruges** — urban–castle crisis microhistory. **CHR**  
18. **Joinville**, *Life of Saint Louis* — aristocratic war culture. **CHR**  
19. **Froissart**, *Chronicles* (selections) — read *with* modern caution. **CHR**  
20. English administrative samples: muster rolls / ordinances in translation anthologies. **CHR**/admin  
21. **Villehardouin** — large-scale siege narrative craft (transfer techniques carefully). **CHR**

### Mercenaries & late infantry
22. Michael **Mallett**, *Mercenaries and their Masters* (Italy). **MOD**  
23. Short Swiss military histories / **Oman** (partially outdated) cross-checked with recent **MOD** on pike tactics.

### Folklore control
24. Any reputable “myths of the Middle Ages” essays on armour weight, chastity belts, boiling oil—use to train designers against **FOLK**. **MOD**

---

## 11. Cross-cutting controversies checklist (lock design only after reading)

| Claim | Status |
|---|---|
| Plate armour as 11th-c. common kit | **False** — **FOLK** |
| Boiling oil as standard defence | **Overstated** — treat as rare/luxury or **FOLK** default |
| Longbow = universal European missile | **False** — English/Welsh specialty fame |
| Crossbow banned ⇒ unused | **False** — ban uneven; weapon ubiquitous |
| Knights alone win sieges | **False** — engineering + labour + missiles |
| Murder holes = every ceiling hole | **Over-read** — site-specific **ARCH** |
| Froissart numbers literal | **Unreliable** — **CHR** inflation |
| Swiss pike obsolete castles in 1400 | **Overstated** — field meta ≠ wall disappearance |
| Genoese “cowards at Crécy” as simple truth | **Contested** — **CHR** vs **MOD** |
| Castles only military | **False** — domestic/admin **ARCH** |
| Peasant plate spear line | **False** — **FOLK** |
| Church chivalry = soft war for all | **False** — selective elite customs |

---

## 12. One-line TD pitch (for the bible cover sheet)

**Medieval western castle TD is a stores-and-engineering war of small garrisons, crossbow loops, sorties, mines, and trebuchets—escalating to cannon—where knights lead but do not outnumber the labourers, and Hollywood plate-and-oil fantasies fail the chronology test.**

---

*Document status: research brief for game bible. Evidence tags mandatory for downstream design claims. Scope locked to ~1000–1500 western/central European castle warfare. No TO&Es invented.*
