# GDD Research — Retention, Habit Loops & Viral Growth

**Product:** Historical Civilization Tower Defence (mobile F2P / hybrid midcore)  
**Scope:** D1/D7/D30 benchmarks, daily habit systems, live-ops, social/viral, notifications, churn  
**Audience:** Design, live-ops, product, UA  
**Status:** Research brief for GDD synthesis  
**Last updated:** 2026-08-13

---

## 1. Executive verdict

Retention for a historical-civ TD is not “more login calendars.” It is:

1. **A crisp first-session fantasy** (defend *your* city with *your* civ’s signature tools).
2. **A daily loop that ends with unfinished desire** (“one more wave” / incomplete collection / guild duty).
3. **A weekly loop that creates social obligation and FOMO without punishing life**.
4. **Meta progression that outlives stage clears** so D30+ players still have goals.
5. **Live-ops that rotate civ flavor**, not only power creep.

**Internal targets (soft launch → scale):** aim for **top-quartile strategy**, not folklore 40/20/10.

| Horizon | Floor (kill / rethink UA) | Healthy (strategy midcore) | Stretch (top TD / hobbyist) |
| --- | ---: | ---: | ---: |
| **D1** | <22% | 28–35% | 38–45% |
| **D7** | <5% | 10–15% | 15–22% |
| **D30** | <2% | 4–7% | 8–12% |
| **D7/D1** | <0.25 | 0.35–0.45 | ≥0.45 |
| **D30/D7** | <0.30 | 0.40–0.55 | ≥0.55 |

Ratios matter more than absolute vanity: strong D1 with collapsing D7 = FTUE sugar, weak core loop.

---

## 2. Benchmarks — what the market actually measures

### 2.1 Whole-market distribution (GameAnalytics 2025, ~11.6k games)

| Band | D1 | D7 | D30 |
| --- | ---: | ---: | ---: |
| Median (P50) | ~22% | 3.4–3.9% | ~0.7–0.8% |
| Top quartile (P75) | ~25–27% Android / ~31–33% iOS | ~7–8% | (not always published) |
| Top decile (P90) | ~40% | ~12% (approx.) | ~4% (approx.) |
| Extreme (P99) | ~64–68% | ≥25% | ~13–15% |

Sources: GameAnalytics 2025 summaries via industry write-ups (GGA / InvestGame 2026).  
**Takeaway:** the meme target **40/20/10** is elite folklore, not median reality. A realistic “good product” band for midcore is closer to **35/15/5** as an *aspiration*, with honesty that most titles never get there.

### 2.2 Genre context (treat as directional; many tables reprint AppsFlyer Q3 2022)

| Genre | D1 | D7 | D30 |
| --- | ---: | ---: | ---: |
| Match / Puzzle | ~32% / ~32% | ~14% / ~12% | ~7% / ~5% |
| RPG | ~31% | ~10% | ~3.5% |
| Simulation | ~30% | ~9% | ~3% |
| **Strategy** | **~25%** | **~8%** | **~3%** |
| Hyper-casual | ~29% | ~6% | ~1.4% |

Strategy/TD starts slower: depth hooks later. That is why **session-1 clarity** and **day-0 “I get this civ”** matter more than for match-3.

### 2.3 Tower defence genre snapshots (older GA genre reports)

Top TD titles historically reported roughly **~40% D1 / ~15% D7** with very high session length in engaged cohorts (order of **~2h/day** for top titles — hobbyist behavior, not mass F2P). Use as *ceiling evidence*, not launch KPI.

### 2.4 What “good” means for *this* game

| Player intent | Expected retention shape | Design implication |
| --- | --- | --- |
| Casual passerby | High D0 churn; thin D7 | Soft FTUE, no energy wall Day 0 |
| Strategy hobbyist | Lower D1 than puzzle; stronger D30 if hooked | Depth, buildcraft, collection |
| Whale / collector | Tiny share; huge LTV | Battle pass + limited cosmetics/civ relics — not P2W walls |
| Social / guild | Stabilizes D30+ | Light clan duties, not mandatory hardcore raids |

---

## 3. Habit science applied to TD

### 3.1 Hook model (simplified)

| Step | TD mapping | Historical-civ flavor |
| --- | --- | --- |
| **Trigger** | Push / icon badge / Discord / streak at risk / guild ping | “Night raid on your wall,” “Festival of [Civ] ends in 6h” |
| **Action** | Clear 1–3 waves / claim quest / deploy towers | Place *ballista / phalanx / turtle ship* — identity action |
| **Variable reward** | Chest RNG, quest chest, event shop roll, hero shard | Relic of lost library, era blueprint, legendary commander |
| **Investment** | Upgraded towers, cleared stages, streak, guild rank, collection % | “My Rome wall,” civ mastery stars |

### 3.2 “One more wave” — the session extender

TD’s natural dopamine is **wave clarity**: discrete win/loss units inside a stage.

**Design rules:**

- Cap average stage at **6–12 minutes**; waves should feel like chapters, not a slog.
- After a clear, offer **Instant Retry / Next Wave Challenge / Elite modifier** within 1 tap (no lobby maze).
- End-of-wave **partial rewards** (scrap, civ favor) so aborting mid-stage still feels productive — reduces rage-quit churn.
- **Optional endless / survival appendix** after campaign clear for high-skill players (BTD6-style long-tail).
- Never soft-lock “one more” behind energy on Day 0–2.

**Anti-pattern:** forcing a full lobby reload between waves; killing the loop.

### 3.3 Investment & sunk cost (ethical)

Players stay when they own:

1. **Layout memory** (my choke points),  
2. **Named heroes/commanders**,  
3. **Civ mastery**,  
4. **Guild reputation**,  
5. **Cosmetics / monuments**.

Avoid: streak systems that *wipe* progress so hard that missing one day causes uninstall (streak tax). Prefer **grace days**, **freeze tokens**, or **cumulative calendars**.

---

## 4. Daily systems

### 4.1 Daily login

| Pattern | Pros | Cons | Recommendation |
| --- | --- | --- | --- |
| **7-day escalating streak** | Strong D1–D7 habit | Punishes casuals; churn on break | Soft version only + freeze |
| **Cumulative monthly calendar** | Fair; Genshin-like | Weaker FOMO | **Primary** for soft launch |
| **Milestone login (Day 1/3/7/14/30)** | Onboards without grind | Less daily pull | Pair with calendar |
| **Premium daily track ($4–6/mo)** | Stable ARPU | Needs trust | Add after D7 product-market fit |

**Reward mix (free track):** soft currency, tower XP, 1× blueprint fragment, cosmetic dust, occasional rare hero shard (low rate).  
**Never:** make the *only* way to get a core tower a login day-28 cliff.

### 4.2 Streaks

Use streaks for **engagement cosmetics / small multipliers**, not core power.

| Streak length | Reward idea | Safety |
| --- | --- | --- |
| 3 days | +5% daily scrap | — |
| 7 days | Monument skin fragment | 1 free freeze / week |
| 14 days | Title + guild contribution boost | Grace: miss 1 day keeps streak |
| 30 days | Unique wall banner | Reset to 7, not 0 |

### 4.3 Quests (daily / weekly)

**Daily quest set (3–5, complete in ~20–30 min):**

1. Clear **2 stages** or **1 hard stage**.  
2. Upgrade any tower **once** OR spend X scrap (buildcraft).  
3. Defeat **N armored / flying / siege** enemies (teaches counterplay).  
4. Collect **1 event token** (ties to live-ops).  
5. Optional: **Guild assist** (donate / share defense replay).

**Weekly quests (4–6):**

- Clear a **boss chapter**.  
- Reach **X stars** on any region.  
- Fully upgrade **one tower line** to tier N.  
- Participate in **limited event** once.  
- Contribute **Y** to guild siege / shared wall.

**Chest pacing:** Daily → small chest; 3/3 dailies → medium; weekly board → large + cosmetic chance.

### 4.4 Collection loops (towers / heroes / civs)

Collection is the D30+ battery when stage map is “done.”

| Layer | What players collect | Cadence |
| --- | --- | --- |
| **Towers** | Archetypes + civ skins + mastery stars | Continuous |
| **Heroes / commanders** | Roster with roles (buffer, sniper, wall, AOE) | Weekly banners / event |
| **Civilizations** | Unlock + mastery path + unique monument | Soft launch: 2–3; scale to full roster |
| **Relics / blueprints** | Passive bonuses + cosmetics | Event shops |
| **Codex entries** | Lore cards from clears | Passive retention for history fans |

**Pity / fairness:** hard pity on featured commander shards; duplicate → universal fragments.  
**Show % complete** on home: “Rome mastery 42%” is a stronger return hook than raw gold.

### 4.5 Meta progression vs stage progression

| Axis | Stage progression | Meta progression |
| --- | --- | --- |
| **What it is** | Campaign map, stars, chapters | Account level, tower mastery, civ tech tree, monuments, battle pass |
| **Wins D1–D7** | Yes — novelty of maps | Soft — unlock slots / second civ |
| **Wins D30+** | Only if endless / modifiers | **Primary** |
| **Risk** | Content cliff | Power creep / P2W perception |

**Recommended split:**

- **Stages** teach systems and sell fantasy.  
- **Meta** sells longevity: mastery XP from *any* mode, civ tech nodes that unlock *tactics* (not raw +50% damage), monument cosmetics.  
- **Battle pass** tracks both: stage stars + meta XP.

Power rule: **new content > new multipliers**. Prefer new enemy modifiers, map rules, and tower interactions over flat ATK inflation.

---

## 5. Limited events & live-ops psychology

### 5.1 Event types that fit historical TD

| Event | Length | Purpose | Example |
| --- | --- | --- | --- |
| **Civ spotlight** | 7–14d | Rotate fantasy + collection | “Persian Royal Road Defense” |
| **Siege weekend** | 48–72h | Spike DAU | Guild shared boss HP |
| **Roguelike tower run** | Always-on weekly seed | Skill expression | Random blueprint draft |
| **Boss rush** | 7d | Midcore depth | Multi-phase war elephant / siege tower |
| **Login festival** | 7–10d | Soft re-engage | Calendar before big drop |
| **Cosmetic monument race** | 14d | Whale + social flex | Guild ranking → city skin |

### 5.2 FOMO without cruelty

- Show **time remaining** clearly.  
- Allow **catch-up tokens** for returning players (48–72h away).  
- Archive strong event modes into **rotating challenge playlist** after premiere.  
- Never delete player-earned cosmetics.

---

## 6. Social systems — guilds, share, Discord

### 6.1 Guilds / clans (light midcore)

**Ship when:** soft-launch D7 ≥ ~10% and session length supports multiplayer duties.  
**MVP guild features:**

1. **Shared wall** — guild contributes materials to a communal fort (cosmetic + small weekly chest).  
2. **Assist tickets** — help clear a friend’s failed wave (async; no real-time required).  
3. **Weekly siege** — fixed boss with contribution score.  
4. **Chat + Discord deep link**.  
5. **Roles** (legate / quartermaster) for social status, not power.

**Avoid Day 0 mandatory guilds.** Soft prompt after first region clear.

### 6.2 Share / invite (viral)

| Hook | Mechanic | Viral quality |
| --- | --- | --- |
| **Invite code** | Soft currency + exclusive monument brick | Medium |
| **Defense replay card** | Shareable image: “My Spartan choke held 47 waves” | High (ego + craft) |
| **Civ mastery badge** | Story-ready share sheet | Medium |
| **Co-op assist link** | Friend opens your failed stage as helper | High retention, medium viral |
| **Guild QR / deep link** | Join clan from Discord/IG | Medium |
| **Weekly challenge seed** | Same RNG seed → compare score | High among hobbyists |

**Store-safe:** no “spam 20 friends for energy” dark patterns; one meaningful invite reward tier is enough.

### 6.3 Discord

Treat Discord as **retention infrastructure**, not a dump channel.

| Channel | Content |
| --- | --- |
| `#patch-notes` | Short, visual |
| `#weekly-challenge` | Seed + leaderboard screenshots |
| `#civ-lore` | History crumbs → drive codex completion |
| `#buildcraft` | Tower layouts (UGC gold) |
| `#bug-reports` | Trust |
| Events | Voice “siege night” synced to in-game weekend |

**In-game:** one-tap Discord join reward (title / banner) once; then stop nagging.

---

## 7. Notification strategy (non-spammy)

### 7.1 Principles

1. **Push amplifies intent; it does not create love.**  
2. Ask permission **after value** (first stage clear / first daily chest), never at cold install.  
3. Prefer **contextual** over broadcast (open rates often ~3× higher).  
4. Global cap: **≤1/day** for most users; **≤4/week** marketing; social can be separate but user-toggleable.  
5. Quiet hours + category toggles in settings.  
6. If player ignores 3 consecutive pushes → **auto-throttle** 7 days.

### 7.2 Allowed push taxonomy

| Priority | Example | Frequency |
| --- | --- | --- |
| **P0 Social** | Guild siege starts / assist requested | Event-driven |
| **P1 Streak / claim** | “Daily chest ready” / “Freeze available — streak safe” | 1× day max |
| **P2 Live-ops** | “Persian event ends in 6h — 2 quests left” | Only if incomplete |
| **P3 Re-engage** | “New civ mastery chapter unlocked” after 3–7d lapse | Cap 2× week |
| **P4 Promo** | Battle pass ending | Rare; never Day 0–2 |

### 7.3 Copy tone

- Specific > generic: “Your ballista mastery is 1 star from III” beats “Come back!”  
- No guilt: never “You abandoned your city.”  
- Localize time to player play window (learned from session history).

---

## 8. Why players churn (TD / strategy mobiles)

| Churn moment | Cause | Fix |
| --- | --- | --- |
| **Minute 0–5** | Tutorial wall; unclear fantasy | Teach by playing: place 2 towers, win 1 wave |
| **Day 0 evening** | No reason to return | Soft daily + unfinished wave / chest timer |
| **Day 2–3** | Difficulty spike / unfair leak | Dynamic assist, clearer counters, retry juice |
| **Week 1** | Content thin; repetitive maps | Modifiers, second civ tease, event |
| **Week 2–4** | Energy / paywall / ad fatigue | Cap ads; generous soft currency; cosmetic IAP focus |
| **Post-clear** | No meta goals | Collection %, mastery, endless, guild |
| **Social void** | Playing alone forever | Light guild + share cards |
| **Live-ops drought** | Same mode 6 weeks | 90-day calendar (below) |
| **Trust break** | P2W, bait banners, spam push | Fair pity, notification hygiene |
| **Identity miss** | Generic fantasy TD | Civ-authentic towers & enemies |

**Instrumentation:** funnel by stage ID, fail reason (leak type), session length, quest completion %, push opt-out, guild join day. Spikes at a *level* = difficulty; spikes at *time* = habit/reward design.

---

## 9. Daily loop — Historical Civ TD

**Target session:** 15–25 minutes core; optional +10 for “one more.”

```
OPEN
  → Claim cumulative daily + streak status (≤10s)
  → See Home: civ monument progress + 3 daily quests + event banner
CORE (choose 1 primary)
  A. Campaign / region stage (2–3 attempts)     [stage progression]
  B. Event mission (1–2 runs)                   [live-ops]
  C. Tower mastery drill / endless appendix     [meta / skill]
BUILDCRAFT
  → Spend scrap on 1 upgrade OR equip relic
  → Check collection % (towers/heroes/civ)
SOCIAL (optional, ≤3 min)
  → Guild donate / assist / siege tap
CLOSE
  → “Tomorrow: daily chest + event quest 2/3”
  → Optional: schedule quiet push for chest / event expiry
```

**Emotion arc:** Identity (my civ) → Competence (held the choke) → Growth (upgrade/collection) → Belonging (guild) → Anticipation (tomorrow).

---

## 10. Weekly loop

| Day | Player-facing focus | Backend / live-ops |
| --- | --- | --- |
| **Mon** | Weekly quest board refresh; soft currency boost | Patch micro / balance note |
| **Tue–Wed** | Campaign push + mastery grind | UGC spotlight in Discord |
| **Thu** | Mid-week mini-modifier (fog / night raid) | A/B difficulty |
| **Fri** | Event shop spotlight; cosmetic preview | Creator kit drop |
| **Sat** | **Guild siege weekend** opens | Community raid voice |
| **Sun** | Siege rewards; weekly chest; preview next civ event | Analytics retro |

**Weekly obligations (player):**

1. Complete weekly board (4/6).  
2. One guild contribution.  
3. One event touch.  
4. Optional: post one build/replay.

---

## 11. Viral hooks (designed for this IP)

1. **Replay glory cards** — auto-generated share image: civ art + waves held + MVP tower.  
2. **Same-seed weekly challenge** — friends compare scores; Discord weekly winner title.  
3. **Civ identity quiz → starter civ** — TikTok/IG funnel into FTUE.  
4. **Guild monument time-lapse** — shareable as guild builds the shared wall.  
5. **Assist links** — “Help me hold Thermopylae Wave 9” deep link.  
6. **Codex lore shorts** — 20s history → “Play this defense” CTA.  
7. **Creator blueprint codes** — import tower loadouts (BTD6-like social tech).  
8. **Seasonal real-world calendar** — solstice / New Year / national history months as civ spotlights (tasteful, researched).

---

## 12. 90-day live-ops skeleton

Assumes soft launch → global with **Babylon + 2–3 starter civs**, then rotating spotlights toward full roster (Rome, Persia, Egypt, China, Maya–Mexica, Viking, Greece, Japan, Mongol, European Castle, …).

### Phase A — Days 0–30: Habit install

| Week | Theme | Content drops | Goals |
| --- | --- | --- | --- |
| **W1** | Foundations | FTUE polish; daily calendar; 3 dailies; soft streak | D1 ≥28%, tutorial complete ≥70% |
| **W2** | Second fantasy | Unlock civ #2 path; first 7-day login festival | D7 ≥10%; second-civ try rate ≥40% of D7 |
| **W3** | Buildcraft | Tower mastery UI; first weekly modifier | Avg session ≥18m; upgrade actions/DAU ↑ |
| **W4** | Social soft launch | Guilds MVP + Discord reward; first weekend siege | Guild join ≥25% of D14 retained |

**Events:** 1× login festival, 1× boss weekend, continuous weekly challenge seed.

### Phase B — Days 31–60: Depth & identity

| Week | Theme | Content drops | Goals |
| --- | --- | --- | --- |
| **W5** | Civ spotlight A | 14d event map + event shop + featured commander | Event participation ≥45% DAU |
| **W6** | Mid spotlight | New enemy modifier set; balance patch | D30 ≥4%; complain-rate on P2W monitored |
| **W7** | Collection surge | Relic dungeon; pity visible; codex batch | Collection screen visits ↑; IAP cosmetic test |
| **W8** | Guild escalation | Siege tier 2; guild ranks; assist links | D30/D7 ≥0.4; share card CTR test |

### Phase C — Days 61–90: Scale fantasy & viral

| Week | Theme | Content drops | Goals |
| --- | --- | --- | --- |
| **W9** | Civ spotlight B | New civ event (different fantasy axis) | Reactivation of 7–14d lapsed |
| **W10** | Battle pass S1 end / S2 tease | Fair free track climax; monument cosmetic | BP attach rate; no trust incidents |
| **W11** | Roguelike / endless season | Draft mode; leaderboards | Hobbyist DAU share ↑ |
| **W12** | Anniversary-lite / roadmap | Community civ vote; creator blueprint codes | Organic share ↑; roadmap trust |

### Cadence summary

| Cadence | Ship |
| --- | --- |
| **Daily** | Quests, calendar tick, chest |
| **Weekly** | Modifier, challenge seed, guild siege window, Discord spotlight |
| **Bi-weekly** | Content patch (enemies/towers/QoL) |
| **Monthly** | Civ or major mode spotlight (14d) |
| **Quarterly** | Battle pass season + major civ or system |

### Resourcing note

Prefer **reskins + modifiers + map rules** over net-new 3D towers every week. Authentic civ fantasy comes from **enemy composition, fortification props, commander kits, and lore**, not endless mesh production.

---

## 13. KPI dashboard (minimum)

| KPI | Why |
| --- | --- |
| D1 / D7 / D30 + ratios | North stars |
| Tutorial complete %, time-to-first-clear | FTUE health |
| Dailies 3/3 rate | Habit fit |
| Event touch rate | Live-ops relevance |
| Collection screen DAU% | Meta hunger |
| Guild join & weekly contribute | Social stickiness |
| Push opt-in, opt-out, CTR by category | Spam risk |
| Share card sends & installs attributed | Viral |
| Churn reasons tagged by stage | Content vs difficulty |
| Soft currency sink/faucet | Economy fairness |

---

## 14. Design do / don’t (checklist)

**Do**

- Teach counters via quests (“kill 20 siege engines”).  
- Make civ identity visible on home and shares.  
- Use cumulative logins + streak *grace*.  
- End sessions with a clear tomorrow hook.  
- Rotate fantasy with research-backed civ events.

**Don’t**

- Energy-gate the first 48 hours.  
- Hard-reset streaks with no freeze.  
- Spam 5+ pushes/day.  
- Hide all power behind gacha with no pity.  
- Ship guilds before the solo loop is fun.  
- Inflate ATK every event until difficulty is meaningless.

---

## 15. Sources & further reading (non-exhaustive)

- GameAnalytics retention distributions (2025) summarized in GGA “Mobile Game Retention / KPIs 2026” and InvestGame “Mobile retention benchmarks 2026.”  
- AppsFlyer genre retention tables (Q3 2022) — directional only; often reprinted undated.  
- GameAnalytics historical Tower Defense genre snapshots (top-title D1/D7/session length).  
- Naavik — *Arknights: Tower Defense Redefined* (live-ops + retention ratios after onboard).  
- Industry notes on cumulative vs streak daily rewards (e.g. analyses contrasting Genshin-style calendars vs streak-tax systems).  
- Push benchmarks: contextual vs generic open rates (Batch 2025 CRM/push studies); opt-in timing best practices (pre-permission → OS prompt).  
- Churn literature: early permission asks, ad/paywall fatigue, difficulty spikes (mobile F2P product write-ups).

---

## 16. One-page return package (for parent GDD)

### Daily loop
Claim calendar → 2–3 stage/event runs → one upgrade → optional guild tap → leave with incomplete quest/event/collection hook.

### Weekly loop
Monday board → midweek modifier → weekend guild siege → Sunday chest + next-civ teaser.

### Viral hooks
Replay glory cards, same-seed weekly challenges, assist deep links, guild monument shares, creator blueprint codes, civ-identity social funnels.

### 90-day spine
W1–4 habit + guild MVP → W5–8 civ spotlight + collection → W9–12 second spotlight + BP season + endless/roguelike + community vote.
