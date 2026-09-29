# GDD-REDDIT-DEEP — Tower Defense Oyuncu Sentiment Derin Araştırma

**Belge tipi:** Design bible research input (player sentiment)  
**Kapsam:** Reddit + Steam forums + App Store / Metacritic review patterns + tarihsel strategy toplulukları  
**Tarih damgası:** 2026-08 (temalar 2020–2026 arası stabildir; thread URL’leri kayabilir)  
**Dil:** Türkçe başlıklar + İngilizce gövde (ürün ekibi / bilingual GDD uyumu)  
**İlişkili:** `GDD-REDDIT-SENTIMENT.md` (kısa özet), `GDD-COMPETITORS-TD.md`, `GDD-MONETIZATION.md`, `GDD-LIVEOPS-FTUE.md`

---

## 0. Yöntem ve Güven Sınırları

### 0.1 Kaynak katmanları

| Layer | Examples | Weight for product rules |
|-------|----------|--------------------------|
| High-signal TD subs | r/BloonsTD6, r/KingdomRush, r/TowerDefense, r/PlantsVSZombies | Primary for love/hate themes |
| Platform gaming | r/AndroidGaming, r/iosgaming, r/mobilegaming | Monetization & ad fatigue |
| Steam discussions | BTD6, Kingdom Rush 1–5, PvZ ports | Difficulty, balance, PC vs mobile fairness |
| Historical strategy | AoE forums, Total War Center, Steam TW threads, r/totalwar, r/paradoxplaza | Authenticity expectations |
| Press / backlash cases | PvZ2 IAP tests, EA ad removals, NK Legends DLC notes | Dark-pattern case studies |

### 0.2 How to read “evidence-style notes”

This document does **not** claim statistically sampled Reddit scraping. Themes below are **recurrent narrative patterns** that appear across years of community discussion. Treat each theme as:

- **Stable** if it repeats across titles and years (e.g. “hate energy gates”).
- **Title-specific** if it clusters around one franchise (e.g. KR hero paywall on mobile).
- **Rising risk** if older “fair” titles are drifting toward more IAP (e.g. late BTD6 cosmetic/DLC debates).

### 0.3 Design bible use

Product, liveops, narrative, and marketing should treat this file as a **constraint set**: what players reward with loyalty language (“I keep coming back”) vs what they punish with uninstall / review bombs / “greedy” keywords.

---

## 1. TD Oyuncuları Ne Sever / Ne Nefret Eder

### 1.1 Bloons TD 6 (Ninja Kiwi) — love themes

**Theme L-BTD-01 — Skill ceiling without paywalls on core towers**  
Players repeatedly praise that high-end content (CHIMPS, bosses, Contested Territory, Odyssey) is beatable with knowledge and micro, not with a credit card. Evidence-style notes: Steam/Reddit counter-arguments when monetization debates flare often start with “you can still clear everything without buying powers”; CHIMPS culture explicitly bans continues/powers, reinforcing a skill identity.

**Theme L-BTD-02 — Build diversity & path fantasy**  
Three-path towers + heroes create endless theorycrafting. Community content (tier lists, “eliminate a tower” polls, strawpoll tower wars) shows emotional investment in *which* monkey is “meta” vs “micro hell.” Love is not only winning—it is arguing about tools.

**Theme L-BTD-03 — Rotating events as “reason to open today”**  
Boss Bloons, Contested Territory, daily/advanced challenges, collection events. Language: “just one more boss try,” “CT tonight with the team.” Retention without energy gates.

**Theme L-BTD-04 — Long free content runway after a paid purchase**  
Players frame NK as “still updating years later with free maps/paragons.” Even critics of newer IAPs often concede free maps/modes remain the value core. This sets a **reference fairness bar** for any premium historical TD.

**Theme L-BTD-05 — QoL and juice**  
Speed controls, freeplay, sandbox-ish challenge creation, collection UX, satisfying SFX/VFX. “Feel” is cited almost as often as balance.

**Theme L-BTD-06 — Optional social, not forced**  
Co-op and teams exist; many praise that single-player mastery remains first-class. Forced PvP toxicity is a minority complaint, mostly around CT competitiveness—not the existence of co-op.

### 1.2 Bloons TD 6 — hate / friction themes

**Theme H-BTD-01 — Rising “paywall creep” perception**  
Steam threads titled around greed, Legends DLC pricing, licensed skins only for USD, pets that veterans grinded for then bundled. Pattern: **veterans feel previously earnable prestige is being re-sold**. Even if not P2W, identity harm is real.

**Theme H-BTD-02 — Micro-heavy towers**  
Mortar, Heli, Beast Handler generate “I hate micro” discourse. Design lesson: some players love mastery toys; others want set-and-forget. Offer both archetypes in a historical roster (auto-fire ballista vs active-command hero).

**Theme H-BTD-03 — Late-game grind & knowledge sprawl**  
Monkey Knowledge / collection completion can feel like homework. Positive when optional; negative when players feel behind without it.

**Theme H-BTD-04 — Balance windows**  
Temporary broken towers/bosses without fast patches produce loud weeks. Historical TD with many civ units will face the same—need a visible balance cadence.

**Theme H-BTD-05 — Double Cash & powers as soft advantage**  
Not pure P2W in competitive modes that ban them, but casual players sometimes feel “I paid to skip pain.” Keep such boosts **out of ranked/fair modes** if those exist.

### 1.3 Kingdom Rush series (Ironhide) — love themes

**Theme L-KR-01 — Juice, humor, and readable fantasy**  
Cartoon clarity, punchy SFX, hero fantasy, soldier barracks. Players call sessions “addicting” in the positive sense: burn through campaign on veteran, then chase stars.

**Theme L-KR-02 — Hero + barracks hybrid**  
The hero as a movable piece plus lane soldiers creates micro-light agency without BTD-level tower micro. Highly transferable to historical commanders + levy cohorts.

**Theme L-KR-03 — Star rating + Heroic/Iron challenges**  
Post-campaign mastery loop. Community language swings between “gratifying after 38 tries” and “impossible.” The *existence* of optional brutal challenges is loved; **unfair RNG spikes** are hated.

**Theme L-KR-04 — Early-call wave reward**  
Calling waves early for gold is a beloved risk/reward habit. Classic “one more wave” pacing.

**Theme L-KR-05 — Sequel brand refresh**  
New settings (Frontiers, Origins, Vengeance, Alliance, Legends) keep the formula fresh while preserving muscle memory.

### 1.4 Kingdom Rush — hate / friction themes

**Theme H-KR-01 — Mobile hero / content paywalls after paid purchase (“buyium”)**  
Persistent Android/iOS review pattern: “I paid for the game, then heroes cost more than the game.” Steam threads comparing Android hero total (~$40+) vs Steam all-heroes-included. **Cross-platform fairness** is a trust issue.

**Theme H-KR-02 — Difficulty cliffs after updates / new heroes**  
Players accuse balance of being tuned around strong new heroes, making older iron/heroic clears feel broken. Lesson: don’t ship power creep that orphan older strategies without a rebalance plan.

**Theme H-KR-03 — Boss designs that delete towers / disable slots**  
Alliance / Vengeance discussion: mechanics that *take away* placement options are “jet black hatred” for some veterans. Frustration ≠ challenge when agency is removed.

**Theme H-KR-04 — Sequel feeling shorter / less “soul”**  
Subjective but recurrent: Alliance felt shorter, less cathartic climax vs marketing. Historical campaigns must earn act finales.

**Theme H-KR-05 — Star-gating progression**  
Milder than energy gates, but locking maps behind stars can feel punitive if early maps are RNG-heavy.

### 1.5 Plants vs Zombies (esp. PvZ1 love vs PvZ2 hate arc)

**Theme L-PVZ-01 — PvZ1 as the “pure” reference**  
Sun economy, plant fantasy, almanac collectibles, humor, fair difficulty. Still invoked as the moral opposite of late PvZ2 monetization.

**Theme L-PVZ-02 — World fantasy & musical identity**  
Even PvZ2 critics praise art, music, and world themes when separated from store pressure.

**Theme H-PVZ-01 — Artificial difficulty → store**  
Metacritic/user-review pattern: “difficulty feels fake so you buy plants.” Arena / Penny’s Pursuit called unwinnable without premiums.

**Theme H-PVZ-02 — Core mechanic IAP tests (lawnmower backlash)**  
2014 PopCap “small-scale test” charging for lawnmowers after they were free—Reddit framed as changing a core mechanic to extract money. Pulled after backlash. **Never monetize failure-recovery that used to be free.**

**Theme H-PVZ-03 — Ad escalation + paid ad removal after prior purchases**  
EA forum threads: players who bought plants/diamonds or prior ad-free feel betrayed by new monthly ad-removal. Keyword cluster: greedy, sold out, uninstalling after years of daily play.

**Theme H-PVZ-04 — F2P grind path narrowed over time**  
Capping ad gem rewards, lowering event gem payouts, raising plant prices—language of “squeezing F2P.” Retention dies when the honest grind path closes.

**Theme H-PVZ-05 — Pace complaints**  
Some call PvZ2 “too fast for TD”—stress without strategic breathing room. Historical TD should protect readable decision windows.

### 1.6 Generic mobile TD (store sludge / clone market)

**Theme L-GEN-01 — What rare fair titles get praised for**  
No energy, offline play, one-time premium, cosmetic IAP only, honest trailers. r/AndroidGaming upvote pattern rewards “actually a game.”

**Theme H-GEN-01 — Energy / stamina gates**  
Near-universal hate. Language: “can’t play without waiting,” “homework simulator,” “pay or wait.” Instant brand poison for strategy fans coming from BTD/KR PC.

**Theme H-GEN-02 — Forced interstitial ads between every level**  
Especially after deaths. Players describe rage-uninstall loops.

**Theme H-GEN-03 — Fake gameplay ads**  
Misleading trailers (puzzle merge gameplay that isn’t the game). Contaminates category trust; historical TD marketing must show **real** siege/tower footage.

**Theme H-GEN-04 — Gacha / random tower crates as power**  
If RNG decides whether you own the counter to flying units, skill fantasy collapses.

**Theme H-GEN-05 — Notification spam & FOMO timers**  
“Your walls are burning!” push spam. Acceptable light daily reminder ≠ manipulative countdown stacks.

**Theme H-GEN-06 — Battle pass that sells power, not cosmetics**  
FOMO pass with damage multipliers is read as P2W even if “optional.”

**Theme H-GEN-07 — Auto-chess / idle bait-and-switch**  
Apps titled tower defense that are mostly idle clickers. Genre confusion damages review scores.

### 1.7 Cross-title love/hate synthesis (for historical TD)

| Players love | Players hate |
|--------------|--------------|
| Fair skill clears | Energy gates |
| Deep builds / civ identity | Pay-to-skip skill |
| Bosses & weekly modifiers | Ads on every fail |
| Juice + readable UI | Fake store ads |
| Optional brutal challenges | Unfair RNG “gotcha” levels |
| Cosmetics & support IAP | Core mechanic monetization |
| Dev patch communication | Silence during broken balance |
| Collection that is optional prestige | Collection that gates power |
| Cross-save PC ↔ mobile parity | Mobile-only paywalls for same content |

---

## 2. Retention / “Addiction” Dili — Alışkanlık vs Dark Pattern

### 2.1 Positive habit language (keep and design for)

Players voluntarily use addiction vocabulary when the loop feels **earned**:

| Phrase cluster | Underlying need | Healthy design mapping |
|----------------|-----------------|------------------------|
| “one more round / wave” | Session cliffhanger | Early-call bonus; short wave chapters; clear mid-boss beats |
| “just finish this boss” | Mastery chase | Rotating siege bosses; clear HP phases; shareable clear clips |
| “daily done” | Closure ritual | 5–10 min daily that grants cosmetics/currency, not survival keys |
| “3-star this map” | Completionism | Stars as mastery, not hard progression locks |
| “collection bar” | Progress theater | Almanac / unit codex fill; show % without punishing gaps |
| “clan chest fill” | Light social duty | Async guild goals; no kick-for-inactivity cruelty |
| “theorycraft tonight” | Identity play | Loadout sharing; weekly modifiers that change meta |

These create **autotelic** retention: play is the reward.

### 2.2 Dark-pattern language (avoid; review-bomb fuel)

| Phrase cluster | Mechanic smell | Why it fails TD audiences |
|----------------|----------------|---------------------------|
| “can’t play without waiting” | Energy / stamina | Strategy fans expect long sessions |
| “rigged gacha” | Random power boxes | Counters must be learnable, not rolled |
| “pay or suffer” | Artificial difficulty | PvZ2 archetype |
| “homework simulator” | Endless chores for power | BTD knowledge grind if mandatory |
| “I already paid for ad-free” | Broken trust / re-monetize | EA ad-removal backlash |
| “small-scale test” on core systems | Silent IAP experiments | Lawnmower case; community never forgets |
| “FOMO pass ends in 11h” | Aggressive timers | OK for cosmetics; toxic for power |
| “notification hell” | Push spam | Uninstall catalyst |

### 2.3 Ethical retention checklist (product)

1. **Session ownership:** Player decides length; game never soft-locks mid-siege for energy.
2. **Honest failure:** Losing teaches a counter; buying must never be the intended tutor.
3. **Earnable prestige:** Cosmetics and codex entries have a free path; paid is shortcut/support.
4. **Separate modes:** If boosts exist, ban them from “fair/mastery” modes (CHIMPS analogue).
5. **Predictable liveops calendar:** Weekly boss, weekly modifier, monthly civ event—habits without hostage timers.
6. **Refund of dignity:** If an experiment fails, revert loudly (PvZ2 lawnmower pull is the rare good ending—don’t need the experiment).

### 2.4 Addiction framing for store / ethics / PR

- **Internal language:** habit loops, mastery loops, social light loops.  
- **External language:** “deep strategy,” “fair challenge,” “historical campaigns.”  
- **Never market:** “more addictive than…” / slot metaphors / “can’t put down because timers.”  
- Align with age ratings and regional dark-pattern regulations (EU DMA / consumer rules evolving).

### 2.5 Habit loop blueprint (historical TD recommendation)

```
Trigger: daily siege brief / push (opt-in, quiet)
  → Action: 1 short mission OR 1 boss attempt (player choice)
    → Variable reward: star improvement, codex snippet, cosmetic crumb, guild point
      → Investment: loadout saved, wall blueprint, commander XP (non-P2W)
```

Variable rewards should be **narrative or cosmetic variance**, not RNG power.

### 2.6 Session length targets (from sentiment, not analytics)

| Audience segment | Preferred session | Design implication |
|------------------|-------------------|--------------------|
| Mobile commute | 5–12 min | Skirmish / daily node |
| Sofa mobile / tablet | 20–40 min | Full map act |
| PC evening | 45–90 min | Boss + challenge grind |
| Weekend deep | 2h+ | Sandbox, endless siege, co-op |

Do not punish long sessions (energy) or short sessions (huge unskippable ads).

---

## 3. Tarihsel Oyunlarda Otantiklik Şikayetleri

### 3.1 Accuracy vs authenticity (industry vocabulary players already use)

Creative Assembly / BBC coverage (2026): designers prefer **authenticity** (“feel of the period”) over total **accuracy**, because perfect accuracy can make games unfun. Players still police:

- Blatant anachronisms that break immersion.
- Faction identity confusion (wrong culture parts mashed together).
- Timeline scope inconsistency (game promises an era, then adds out-of-era civs without framing).
- Stereotype & myth sold as fact without labeling.

**Product stance for historical TD:** ship **Authenticity Mode** as default fantasy-grounded-in-research, plus optional **Museum Notes** / Codex that admit abstractions. Never gaslight players that horned Vikings are archaeological truth.

### 3.2 Complaint clusters from AoE / Total War / history-adjacent communities

**Theme A-01 — Equipment timeline mashups**  
Example pattern (AoE forums): tricorne + Pickelhaube on same upgrade tier; renaissance kit in industrial age. Players accept soft timelines if **visual eras don’t scream contradiction** in one screen.

**Theme A-02 — Wrong people / wrong models**  
Using assets from unrelated cultures “because they look cool.” Called out as lazy and disrespectful, especially for living cultures’ ancestors.

**Theme A-03 — Faction as meme, not polity**  
AoE IV / TW debates: Templars + Lancastrians as peer “civs,” Joan variants, Three Kingdoms chronologically outside a medieval vision. Lesson: **define your historical frame in UI copy** (“Sieges of the classical Mediterranean, 500 BCE–200 CE”) and stick to it—or clearly mark alt-history/myth chapters.

**Theme A-04 — Technology that shouldn’t exist yet**  
Gunpowder for pure Bronze Age without framing; stirrups on wrong century cavalry. For TD: don’t give Roman republic ballistae medieval counterweight trebuchets without a “late empire tech” tag.

**Theme A-05 — Landscape & vibe wrong**  
TW Pharaoh: “too much sand,” missing fertile Bronze Age feel; collapse vibe not apocalyptic enough. Atmosphere is authenticity.

**Theme A-06 — Immortal characters / plot armor**  
History fans want mortality, logistics, seasons (“warfare in the era of grass”). Even abstracted TD can nod to seasons, supply, disease events as modifiers—not only bigger HP bars.

**Theme A-07 — Myth vs history unlabeled**  
Assassin’s Creed / Troy / Three Kingdoms get more forgiveness when epic-first. Unlabeled cartoon wrongness does not. Horned Viking helmets without a Myth toggle = Reddit dunk fuel (r/historymemes crossover).

**Theme A-08 — Modern politics projected onto ancient map**  
Attila/slavic placement debates show how quickly historical games become culture-war magnets. Stay careful with naming, colors, and “who owns which city” in campaign maps.

### 3.3 What historical TD players will specifically audit

1. **Tower typology:** wall, tower, gate, artillery—do forms match the culture’s engineering?
2. **Unit counters:** cavalry vs spears, arrows vs armor—rock-paper-scissors should rhyme with history, not only arcade balance.
3. **Economy metaphor:** grain, timber, bronze, manpower—not only generic “gold” if claiming simulation flavor.
4. **Enemy composition:** don’t send plate knights at 1200 BCE Egyptians.
5. **Audio-visual language:** instruments, banners, architecture sets per civ.
6. **Codex honesty:** one paragraph on what was abstracted for gameplay.

### 3.4 Forgiveness conditions (when anachronism is OK)

Players forgive when:

- The game **labels** Myth / What-If / Folklore modes.
- Anachronisms are **diegetic jokes** with clear tone (KR fantasy) rather than fake textbook.
- Gameplay abstraction is explained (“towers represent regional defense doctrines”).
- Experts or consultants are credited (AC Baghdad, TW Medieval III advisor pattern).
- Mods or Museum Mode exist for purists.

Players do **not** forgive when:

- Marketing says “historically accurate” and assets are wrong.
- Living cultures are caricatured.
- Monetized skins invent cursed hybrids (Roman samurai) without satire framing.

### 3.5 Authenticity complaint → design system

| Risk | Mitigation |
|------|------------|
| Horned Vikings dunk | Default clean historicity; Myth cosmetic pack labeled |
| Era mash in one map | Campaign acts locked to eras; endless mode can unlock mix with UI warning |
| Unit name wrong | Historian pass + community “errata” patch notes |
| Same towers all civs | Shared chassis + culture skins + 1–2 unique doctrines per civ |
| Lore walls of text | Short battlefield tips + optional deep Codex |
| Offense / stereotype | Sensitivity read; avoid racialized “barbarian” shorthand |

### 3.6 Educational upside (sentiment opportunity)

History-curious players **share** longform codex posts, timeline threads, and “I learned something” comments. That is positive virality distinct from boss clear clips. Build shareable Codex cards (see §5).

---

## 4. Aksiyonel Ürün Kuralları — Do / Don’t Tablosu

### 4.1 Core gameplay & fairness

| DO | DON’T |
|----|-------|
| Make every campaign map clearable with skill on standard difficulty | Gate clears behind premium towers/heroes |
| Provide optional Heroic/Iron analogues (“Last Stand”, “Siege Hardened”) | Make optional challenges the only way to unlock story |
| Ship counters that are learnable via Almanac | Hide counters behind gacha ownership |
| Offer both set-and-forget towers and micro heroes | Force heavy micro on all builds |
| Allow early-wave call with risk/reward | Punish pacing with mandatory long waits |
| Patch broken balance on a published cadence | Leave god-tier IAP units untouched for revenue |

### 4.2 Monetization & platform parity

| DO | DON’T |
|----|-------|
| Prefer premium + cosmetic IAP + optional ad-remove | Energy gates |
| Keep PC and mobile content parity for paid features | Mobile-only hero paywalls after base purchase (KR Frontiers pattern) |
| Sell cosmetics, soundtrack, Codex art books | Sell failure recovery that was free (lawnmowers) |
| If Double-Cash analogues exist, ban them in mastery modes | Sell damage multipliers in battle pass |
| Price DLC as self-contained modes that don’t feed P2W into base | Feed Legends-like rewards into ranked base advantage |
| Be transparent in patch notes about why IAP exists (NK-style letters help some players) | Silent “small-scale tests” on core mechanics |
| One fair grind path for currencies | Cap the only F2P earn path into nothing |

### 4.3 Ads, UX, trust

| DO | DON’T |
|----|-------|
| If F2P: rewarded optional ads for cosmetics/currency | Interstitials after every death |
| Show real gameplay in trailers | Fake merge/puzzle ads |
| Opt-in notifications, quiet defaults | “Your city burns!” spam |
| Remember ad-free purchases forever | Re-introduce ads and sell monthly removal again |
| Offline-capable campaign chunks | Always-online for single-player sieges |

### 4.4 Retention & liveops

| DO | DON’T |
|----|-------|
| Weekly boss siege + weekly modifier | Stacking FOMO timers for power |
| Daily that completes in <10 minutes | Dailies that require 5 ads + energy |
| Async guild goals | Forced toxic PvP for progression |
| Collection events for cosmetics/codex | Collection events that permanently power-creep roster |
| Season narrative tied to historical campaigns | Empty battle pass filler with no theme |

### 4.5 Historical authenticity product rules

| DO | DON’T |
|----|-------|
| Default to researched silhouettes, weapons, fortification types | Market “100% accurate” then ship cartoon wrong |
| Label Myth / Folklore / Alt-History modes | Mix myth into Museum Mode silently |
| Credit consultants; publish short design diaries | Steal cultural assets without context |
| Explain gameplay abstractions in Codex | Argue with players that wrong hats are “fine, it’s a game” without listening |
| Keep civ identity coherent (doctrine + art + music) | Frankenstein unrelated cultures for “cool unit” |
| Allow community errata / patch naming fixes | Double down on known howlers |

### 4.6 Difficulty & boss design

| DO | DON’T |
|----|-------|
| Telegraphed boss phases; readable tells | Random one-shots without tells |
| Challenges that demand planning | Challenges that delete towers / disable all slots as default boss kit |
| Difficulty select that changes HP/count clearly | Secret difficulty spikes after hero sales |
| Shareable clear replays | Soft-lock saves that force IAP continue |

### 4.7 Social & toxicity

| DO | DON’T |
|----|-------|
| Optional co-op; strong solo | Forced PvP ladders for story unlocks |
| Guild async | Kick culture & participation taxes |
| Loadout/code sharing | Paywalled loadout slots that affect power |

### 4.8 Priority rule stack (when conflicts arise)

1. **Trust > ARPU spike**  
2. **Skill fantasy > monetization convenience**  
3. **Labeled authenticity > marketing hype**  
4. **Optional mastery > mandatory grind**  
5. **Parity across platforms > store-specific extraction**

---

## 5. Viral / Paylaşım Davranışları

### 5.1 What TD communities actually share

| Share type | Platforms | Why it spreads | Design hook |
|------------|-----------|----------------|-------------|
| Boss near-fail → clutch clear | Reddit, YouTube, TikTok, Shorts | Tension + catharsis | Phase HP bars, dramatic SFX, instant replay buffer |
| “Is this tower useless?” polls / tier lists | Reddit, Discord | Argument fuel | Distinct tower identities; patch notes that shake meta |
| Challenge contracts / odd constraints | BTD challenge browser culture | Creativity flex | In-game challenge creator + share codes |
| Co-op fails & memes | Discord, Reddit | Social bonding | Friendly fire-free co-op; emotes |
| Before/after 1-star → 3-star | Reddit screenshots | Mastery arc | Clear star criteria UI |
| Codex / “today I learned” cards | Reddit, historymemes adjacent | Identity + education | Exportable Codex cards with art + 2-sentence fact |
| Rage at dark patterns | Reddit, Twitter/X | Outrage virality (bad) | Avoid being the subject |
| Fairness praise posts | r/AndroidGaming | Rare positive mobile stories | Earn “no energy” headlines |

### 5.2 Clip-native features to ship

1. **Last 30–60s replay buffer** with slow-mo and tower highlight.  
2. **Share code** for map + modifier + banned towers.  
3. **Boss attempt card:** hero, civ, time, deaths, difficulty watermark.  
4. **Codex share image:** unit art, era, one verified fact, game watermark.  
5. **Weekly community challenge** seeded by devs; winners get cosmetic titles.  
6. **Ghost ghost** of a friend’s clear path (async), not invasive PvP.

### 5.3 Meme surface area (intentional)

- Enemy types with readable silhouettes (good meme stickers).  
- Commander voice lines that are short and quotable—not cringe spam.  
- Patch notes with light humor (NK/Ironhide style) without mocking players.  
- “Eliminated from the island” community events around underused towers.

Avoid: forced meme fonts in UI; emoji salad; TikTok UI inside the game.

### 5.4 Influencer / guide ecosystem

BTD and KR thrive because **guides remain useful**. Sentiment turns bitter when patches silently invalidate all guides without notes (KR hero balance threads). Provide:

- Public changelog with numeric changes.  
- Practice sandbox for creators.  
- Permission-friendly press kit (towers, bosses, Codex art).

### 5.5 Bad viral (do not optimize for)

- Review bombs about greed.  
- “Fake ad” callout compilations.  
- History-community dunk compilations on wrong helmets.  
- Drama over paywalled heroes on mobile vs PC.

These spread faster than clutch clears and define brand search results.

### 5.6 Share loop tied to retention (ethical)

```
Weekly Boss → Clip worthy clear → Post to Reddit/Discord
  → Friends download share code → Try same modifier
    → Guild async score → Cosmetic title
      → Next week new boss (habit)
```

No energy. No pay-to-enter weekly boss. Optional cosmetic entry skins only.

### 5.7 Platform-specific notes

| Platform | Sentiment habit | Content tip |
|----------|-----------------|-------------|
| Reddit | Long critique + evidence | Dev replies with patch intent earn goodwill |
| YouTube | 10–20m guides; boss tutorials | Stable challenge IDs |
| Shorts/TikTok | 15–45s clutch | Vertical safe UI replay export |
| Steam forums | Difficulty + value debates | Parity messaging PC/mobile |
| Discord | Theorycraft & clubs | Official challenge bot |

### 5.8 Metrics that match sentiment (not vanity)

Track alongside DAU:

- % sessions with voluntary share export  
- Challenge code copies  
- Codex card exports  
- Boss clear clip saves  
- Negative review keywords: greedy, ads, energy, pay to win, inaccurate  
- Positive keywords: fair, satisfying, historical, no energy, polished

---

## 6. Franchise-by-Franchise Sentiment Cards (Quick Reference)

### 6.1 BTD6 card

- **Love:** depth, events, fair skill core, free updates.  
- **Hate:** IAP creep perception, micro towers, occasional greed threads.  
- **Steal:** bosses, challenges, mastery modes that ban pay boosts.  
- **Avoid copying:** late-stage monetization ambiguity without communication.

### 6.2 Kingdom Rush card

- **Love:** juice, heroes, stars, campaign fantasy.  
- **Hate:** mobile paywalls, agency-removing bosses, brutal unfair spikes.  
- **Steal:** hero+soldiers, early call, heroic/iron.  
- **Avoid copying:** buyium heroes after paid install.

### 6.3 PvZ card

- **Love:** PvZ1 purity, plant fantasy, tone.  
- **Hate:** PvZ2 IAP, ads, artificial difficulty, broken ad-free trust.  
- **Steal:** almanac joy, world music identity, readable lanes.  
- **Avoid copying:** store-as-difficulty-designer.

### 6.4 Generic mobile TD card

- **Love:** rare premium fair titles.  
- **Hate:** energy, ads, fake ads, gacha power.  
- **Steal:** nothing from sludge except “what not to do” gallery.  
- **Avoid copying:** almost everything about mid-store F2P TD.

---

## 7. Historical TD Positioning Against Sentiment

### 7.1 Promise statement (player-tested language)

> A skill-first historical tower defense: no energy, no pay-to-win towers, authentic fortifications and doctrines, optional myth layers clearly labeled, weekly sieges worth clipping.

### 7.2 Trust badges to earn in first-week reviews

1. “No energy.”  
2. “Beat it without paying more.”  
3. “Actually researched.”  
4. “Bosses feel fair.”  
5. “Trailer was the real game.”

### 7.3 Risk badges to never earn

1. “Greedy.”  
2. “Ads every level.”  
3. “Paywall heroes.”  
4. “Fake history.”  
5. “Impossible without IAP.”

---

## 8. Evidence Appendix — Kaynak Notları (URL’ler zamanla kayabilir)

### 8.1 Monetization backlash

- MCV / Ars coverage pattern: PopCap PvZ2 lawnmower IAP “small-scale test” pulled after Reddit anger (core mechanic monetized).  
- EA forums: PvZ2 monthly ad removal; players who previously bought ad-free or premiums feel betrayed; gem earn paths capped.  
- Metacritic iOS user reviews: PvZ2 called microtransaction-filled; arena unwinnable without premiums; artificial difficulty.

### 8.2 Kingdom Rush difficulty & mobile value

- Steam KR1: Pandemonium / iron/heroic “impossible vs gratifying” split; updates + heroes change perceived fairness.  
- Steam KR5 Alliance: long critique threads on boss designs that destroy towers/disable pads; sequel “soul” debates.  
- Android reviews / Steam PC vs Android: heroes behind expensive IAP on mobile; Steam includes heroes—parity outrage.

### 8.3 BTD6 monetization tension

- Steam: “paywall,” “greedier,” Legends DLC cost vs base price debates; licensed skins USD-only complaints.  
- NK communications: paid Legends framed as self-contained; claims no P2W into base; explains mobile price ceilings vs perceived value.  
- Community split: free maps/paragons praised vs cosmetic/DLC fatigue.

### 8.4 Authenticity discourse

- BBC Newsbeat (2026): Total War accuracy vs authenticity; seasons/grass logistics example; advisors.  
- AoE forums: anachronistic upgrades, Three Kingdoms scope, model sourcing from wrong peoples.  
- TW Steam: Charlemagne unit kit anachronisms; Pharaoh vibe/scope; faction-as-joke critiques.

### 8.5 Viral content

- YouTube boss rush / BTD6 boss tutorials as evergreen share format.  
- Strawpoll tower elimination events as community engagement templates.  
- Reddit theorycraft + clutch screenshots as organic acquisition.

---

## 9. Uygulama: Design Bible’a Madde Madde Aktarım

### 9.1 Must-have for vertical slice

- [ ] No energy system in any build flavor  
- [ ] One boss with telegraphed phases + replay share  
- [ ] Two civ doctrines with visually distinct towers  
- [ ] Codex entry with abstraction note  
- [ ] Star or medal mastery optional  
- [ ] Trailer footage = build gameplay  

### 9.2 Must-have before liveops launch

- [ ] Weekly boss rota  
- [ ] Weekly modifier  
- [ ] Cosmetic-only pass or no pass  
- [ ] PC ↔ mobile IAP parity policy written  
- [ ] Balance patch SLA (e.g., critical within 72h)  
- [ ] Myth content labeled  

### 9.3 Nice-to-have viral layer

- [ ] Challenge creator + codes  
- [ ] Codex share cards  
- [ ] Guild async  
- [ ] Creator sandbox  

### 9.4 Explicit non-goals (sentiment-driven)

- [ ] Gacha for towers  
- [ ] Interstitial ads on fail  
- [ ] Pay-to-continue mid-map as primary design  
- [ ] Unlabeled anachronism as default  
- [ ] Forced PvP  

---

## 10. Oyuncu Persona Sentiment Haritası

### Persona A — “CHIMPS brain” (BTD migrant)

- Wants: fair hard modes, no payskip, deep builds.  
- Quits if: energy, P2W, shallow maps.  
- Evangelizes if: brutal optional challenges + patch transparency.

### Persona B — “KR juicer” (campaign star hunter)

- Wants: heroes, juice, 3-star chase, readable fantasy.  
- Quits if: mobile paywall after purchase, unfair iron spikes.  
- Evangelizes if: memorable bosses + humor + fair hero unlocks.

### Persona C — “PvZ romantic”

- Wants: charm, almanac, fair adventure.  
- Quits if: EA-like ad/IAP spiral.  
- Evangelizes if: tone + collectible lore without pressure.

### Persona D — “History auditor”

- Wants: authenticity, coherent eras, honest Codex.  
- Quits if: horned helmets as fact, culture mash.  
- Evangelizes if: “I learned something” + labeled myth DLC.

### Persona E — “Mobile casual siege”

- Wants: 10-minute sessions, clear goals, no spam.  
- Quits if: ads, energy, confusing UI.  
- Evangelizes if: “premium feel, offline, fair.”

Design must satisfy A–D without treating E as a whale to trap.

---

## 11. Review Keyword Playbook (Launch Monitoring)

### 11.1 Positive watchlist

`satisfying`, `fair`, `strategy`, `no energy`, `polished`, `historical`, `addictive` (positive), `one more wave`, `boss`, `worth the money`, `dev listens`

### 11.2 Negative watchlist

`greedy`, `pay to win`, `ads`, `energy`, `stamina`, `rigged`, `crash`, `paywall`, `inaccurate`, `ai slop`, `fake ad`, `uninstall`

### 11.3 Response SOP

- Greedy/paywall: clarify earn path; consider price/parity patch; never argue “it’s optional” alone.  
- Inaccurate: ship errata; thank auditor; avoid defensive tone.  
- Ads/energy: if present, remove—non-negotiable for this product thesis.  
- Crash: hotfix priority over content.

---

## 12. Sonuç — Sentiment’ten Tek Sayfa Anayasa

1. **Skill is the product.** Monetization may fund it, never replace it.  
2. **No energy, no fail-ads, no gacha towers.**  
3. **Platform parity is morality, not a feature.**  
4. **Authenticity by default; myth by toggle.**  
5. **Bosses and challenges create healthy addiction language.**  
6. **Share tools turn mastery into acquisition.**  
7. **Trust compounds; “small-scale tests” on core systems destroy years of goodwill.**  
8. **Patch in public.**  
9. **Optional mastery > mandatory homework.**  
10. **If removing a card/border/timer doesn’t hurt play, it was dark pattern chrome—delete it.**

---

## 13. Geniş Tema Kataloğu (Tarama Listesi)

The following catalog expands §1 into granular theme IDs for tagging future Reddit/Steam quotes in research spreadsheets.

### 13.1 Love theme catalog

| ID | Theme | Typical source titles | Product implication |
|----|-------|----------------------|---------------------|
| L01 | Fair skill clears | BTD CHIMPS praise | Mastery mode without IAP tools |
| L02 | Build diversity | Tower polls, tier lists | Non-overlapping tower jobs |
| L03 | Hero fantasy | KR hero threads | Historical commanders with abilities |
| L04 | Juice / SFX | “feels so good” | Combat feedback budget |
| L05 | Boss rotation | Boss bloon events | Monthly/weekly siege bosses |
| L06 | Daily closure | “daily done” | Short daily node |
| L07 | Collection prestige | Almanac, trophies | Cosmetics + Codex |
| L08 | Co-op optional | BTD co-op | Phase 2 co-op |
| L09 | Challenge creator | BTD browser | UGC share codes |
| L10 | Dev communication | Patch note love | Design diaries |
| L11 | Cross progress | PC↔mobile | Account system |
| L12 | Offline respect | AndroidGaming | Offline campaign |
| L13 | Early wave call | KR tactic talk | Risk/reward call |
| L14 | Star mastery | KR 3-star | Optional stars |
| L15 | Readable enemies | PvZ / KR | Silhouette pass |
| L16 | Music identity | PvZ worlds | Per-civ themes |
| L17 | Long support | BTD years of updates | Liveops roadmap honesty |
| L18 | Sandbox practice | Creator tools | Sandbox flag |
| L19 | Theorycraft social | Discord comps | Loadout export |
| L20 | Learning history | Codex shares | Fact cards |

### 13.2 Hate theme catalog

| ID | Theme | Typical source titles | Product implication |
|----|-------|----------------------|---------------------|
| H01 | Energy gates | Mobile TD sludge | Ban stamina |
| H02 | Interstitial ads | PvZ2 / clone reviews | No fail ads |
| H03 | P2W gacha | Arena complaints | No random power |
| H04 | Buyium DLC | KR mobile heroes | Earn or include heroes |
| H05 | Core IAP tests | Lawnmowergate | No silent core monetization |
| H06 | Ad-free betrayal | EA monthly ads | Permanent ad-free SKU |
| H07 | Fake ads | Store category distrust | Real trailers only |
| H08 | Notification spam | Uninstall threads | Opt-in quiet |
| H09 | Power battle pass | FOMO P2W | Cosmetic pass only |
| H10 | Agency-removing bosses | Alliance critiques | Don’t delete all pads |
| H11 | Unfair RNG spikes | Gargoyle luck threads | Limit pure luck fails |
| H12 | Guide-breaking silent patches | KR balance | Changelogs with numbers |
| H13 | Micro forced on all | Mortar hate | Mixed automation |
| H14 | Grind homework | Knowledge mandatory feel | Keep prestige optional |
| H15 | Platform disparity | Steam vs Android price | Parity policy |
| H16 | Short soulless sequel | Alliance length | Earn finales |
| H17 | Artificial difficulty | PvZ2 store pressure | Tutor with design not shop |
| H18 | Always online SP | Live service hate | Offline SP |
| H19 | Toxic forced PvP | CT edge cases | Soft social first |
| H20 | AI slop art | Modern store reviews | Art direction QA |

### 13.3 Authenticity theme catalog

| ID | Theme | Product implication |
|----|-------|---------------------|
| A01 | Anachronistic kit mash | Era locks / tags |
| A02 | Wrong culture models | Reference sheets per civ |
| A03 | Scope inconsistency | Written era frame |
| A04 | Unlabeled myth | Myth toggle |
| A05 | Stereotype shorthand | Sensitivity + research |
| A06 | Vibe wrong (biomes) | Environment authenticity |
| A07 | Logistics ignored | Season/supply modifiers |
| A08 | Modern politics on map | Careful naming |
| A09 | “Accurate” marketing lie | Soften claims to authentic |
| A10 | Community errata ignored | Patch names/stats |

---

## 14. Senaryo Testleri (Sentiment Acceptance Tests)

Use these as QA / design review prompts before shipping a feature.

1. **Energy test:** Can a player fail 10 times in a row and immediately retry without waiting or paying? If no, fail.  
2. **Trailer test:** Does the first 15 seconds of the store video show real tower placement and enemy pathing from the build? If no, fail.  
3. **Parity test:** Is every gameplay hero/tower available on mobile under the same earn-or-buy rules as PC? If no, fail.  
4. **Myth test:** Can a player enable a filter that hides folkloric units from Museum/Codex “history” view? If myth exists and answer is no, fail.  
5. **Boss agency test:** Does the boss remove so many tower slots that the player’s build identity vanishes? If yes, redesign.  
6. **Star gate test:** Can the story continue without 3-starring every map? If no, soften gates.  
7. **Continue test:** Is the primary teach loop “buy continue” rather than “adapt build”? If yes, fail.  
8. **Ad-free test:** If user buys ad-remove, can any future update reintroduce ads without that SKU applying? Must be no.  
9. **Share test:** Can a player export a boss clear or challenge code in <3 taps? If viral is a goal and answer is no, schedule the feature.  
10. **Historian dunk test:** Would a well-known anachronism (horned Viking, wrong pyramid labor myths, Roman legion vs plate knights) appear in default mode? If yes, fix or label.

---

## 15. Liveops Takvimi — Sentiment Uyumlu Örnek

| Cadence | Mode | Reward | Dark-pattern watch |
|---------|------|--------|--------------------|
| Daily | 8-min skirmish | Soft currency + Codex crumb | No streak punish beyond mild |
| Weekly | Boss siege | Cosmetic title + screenshot card | No pay-to-enter |
| Weekly | Modifier map | Badge | Don’t require last season’s P2W unit |
| Monthly | Civ spotlight | Skin + lore drop | Don’t lock civ behind pay |
| Season | Narrative act | Story + cosmetic track | Power track forbidden |

---

## 16. Dil / Tone Rehberi (Community & UI)

### 16.1 Words players reward

- Fair, optional, practice, mastery, doctrine, siege, researched, patch, share code, earnable.

### 16.2 Words that trigger distrust

- Limited energy, exclusive power, random crate, hurry, last chance, exclusive advantage, skip wait, premium ammo.

### 16.3 Dev reply templates (Reddit)

**On authenticity callout:**  
“You’re right about X—we abstracted Y for readability. We’ll fix the helmet reference in 1.1 and add a Codex note. Mythic variant stays in Folklore toggle.”

**On monetization worry:**  
“All campaign towers are earnable. Cosmetics fund liveops. Mastery mode bans paid boosts. Mobile and PC unlock rules match.”

**On difficulty spike:**  
“Iron/Last Stand is optional. We’re tuning wave 7 armor; numbers in the patch notes tonight.”

---

## 17. Rakip Sentiment Skor Kartı (Öznel, Tasarım için)

Scores are relative sentiment health (10 = community trust high), not review averages.

| Title | Fairness | Depth | Juice | Liveops joy | Monetization trust | Authenticity N/A or score |
|-------|----------|-------|-------|-------------|--------------------|---------------------------|
| BTD6 | 8 | 10 | 8 | 9 | 6 (drifting) | N/A cartoon |
| KR PC | 8 | 7 | 10 | 6 | 8 | Fantasy |
| KR Mobile (legacy IAP) | 6 | 7 | 10 | 5 | 3 | Fantasy |
| PvZ1 | 10 | 7 | 9 | 3 | 10 | N/A |
| PvZ2 late | 3 | 7 | 8 | 5 | 2 | N/A |
| Store clone TD | 2 | 3 | 4 | 4 | 1 | 1 if “historical” skin |

**Target for our historical TD:** Fairness 9+, Monetization trust 9+, Authenticity 8+, Depth 8+, Juice 8+, Liveops joy 8+.

---

## 18. Açık Sorular (Araştırma Devamı)

1. Exact energy tolerance on hybrid premium-F2P in 2026 store (hypothesis: still near zero for strategy fans).  
2. Whether challenge UGC moderation cost is worth viral upside at soft launch.  
3. How hard historymemes audiences punish small anachronisms vs major ones (A/B Codex labeling).  
4. Co-op desync tolerance on mobile sieges.  
5. Price anchoring: premium $6.99 vs $14.99 vs $19.99 with cosmetic IAP (NK commentary on mobile ceilings is relevant).  

---

## 19. Özet Bullet’lar (Executive)

- TD Reddit culture worships **fair skill** and **optional mastery**; it punishes **energy, fail-ads, and P2W**.  
- BTD6 shows the ceiling of healthy addiction via bosses/challenges—and the risk of late IAP trust erosion.  
- Kingdom Rush shows juice + heroes win hearts—and mobile paywalls / agency-removing bosses lose them.  
- PvZ shows charm is not durable against store hostility and broken ad-free promises.  
- Historical audiences accept **authenticity over literal accuracy** if you **label abstractions and myths**.  
- Virality comes from **clutch bosses, challenge codes, and Codex learning cards**—not from outrage.  
- Product constitution: skill-first, no energy, parity, labeled history, shareable mastery.

---

## 20. Belge Bakım Notu

- Refresh quarterly from r/BloonsTD6, r/KingdomRush, r/TowerDefense, Steam top threads, and one authenticity forum pass.
- Append new theme IDs rather than rewriting history; mark deprecated themes.
- When shipping a controversial monetization change, add a retrospective section: hypothesis → community reaction → action taken.

---

## 21. Steam / App Store Review Keywords — Derin Madencilik

**Amaç:** Reddit dilini (§1–§2) mağaza review corpus’uyla hizalamak. Mağaza review’leri daha kısa, daha duygusal, daha “1 yıldız / 5 yıldız” ikili; Reddit daha uzun teori ve meta tartışır. Launch sonrası monitoring hem **keyword** hem **theme cluster** bazında yapılmalı.

**Kaynaklar (sentez):** Steam review hubs (BTD6, KR5 Alliance, Defense Grid 2, PvZ GOTY); App Store / Google Play (KR5, BTD6+, PvZ 2); aggregator theme weights (ReviewRescue, VaporLens, Marlvel). Frekanslar **göreli** — absolute % değil. Detaylı title kartları: `GDD-STORE-REVIEWS.md`.

### 21.1 Platform farkı — nasıl okunur

| Boyut | Steam | App Store / Play |
|-------|-------|------------------|
| Uzunluk | Orta–uzun; “hours played” sinyali | Kısa; rating + 1–3 cümle |
| Ana övgü dili | depth, value, hours, updates, co-op | fun, addictive, polished, offline, no ads |
| Ana öfke dili | short campaign, DLC greed, Workshop paywall, balance | IAP paywall, ads, energy, crash, forced update |
| “Worth it” çerçevesi | $/hour + content runway | “I paid once, then they charged again” |
| Tarihsel niş | Az (TD genelde fantasy); authenticity Reddit’te daha güçlü | “historical” kelimesi marketing claim olarak test edilir |

**Ürün kuralı:** Steam’de **content volume + fairness** skoru taşır; mobile’da **ads/IAP trust + session reliability** skoru taşır. Her iki platformda da “greedy” aynı zehir.

### 21.2 Positive keyword lexicon (EN) — watch & amplify

Group A — **Core loop praise** (hedef: review’lerde organik geçsin)

| Keyword / phrase | Typical co-text | Design lever |
|------------------|-----------------|--------------|
| `satisfying` | “so satisfying to place towers” | Juice, SFX, clear kills |
| `addictive` / `hooked` | positive habit (not dark) | Short session cliffs, early-call |
| `one more` / `just one more level` | session extension | Wave chapters, boss phases |
| `strategy` / `tactical` | buildcraft identity | Distinct tower roles |
| `challenging but fair` | mastery without rage | Telegraphed difficulty |
| `replayability` / `hours` | longevity | Modes, modifiers, bosses |
| `worth the money` / `great value` | premium trust | Content runway + free updates |
| `polished` / `made with love` | KR-class juice | Art/audio budget visibility |
| `humor` / `charm` / `Easter eggs` | PvZ/KR praise | Optional wit, not forced memes |
| `build variety` / `so many towers` | BTD path fantasy | Non-overlapping jobs |
| `boss` / `events` | liveops joy | Rotating sieges |
| `co-op` / `with friends` | social optional | Soft co-op, not forced |
| `updates` / `still updating` | trust compound | Honest roadmap |
| `no ads` / `ad-free` | mobile premium love | Permanent SKU |
| `offline` | travel/commute | Offline campaign chunks |
| `no energy` / `no stamina` | AndroidGaming praise | Explicit absence as feature |
| `fair` / `not pay to win` | monetization trust | Earnable roster |
| `dev listens` / `patch notes` | community goodwill | Public numbers |

Group B — **Historical TD specific positives** (hedef keyword’ler — henüz rakiplerde seyrek)

| Keyword / phrase | Why it matters | How to earn it |
|------------------|----------------|----------------|
| `historical` / `authentic` | Differentiator vs cartoon TD | Researched silhouettes + Codex |
| `learned something` / `educational` | Shareable goodwill | Fact cards, Museum Notes |
| `era` / `civilization` / `doctrine` | Identity language | Per-civ audio-visual systems |
| `researched` / `attention to detail` | History auditor praise | Errata patches, consultant credit |

### 21.3 Negative keyword lexicon (EN) — alert & escalate

Group C — **Monetization toxins** (1★ cluster — hotfix / policy)

| Keyword / phrase | Severity | Typical source | Immediate product response |
|------------------|----------|----------------|----------------------------|
| `greedy` / `greed` | Critical | All platforms | Clarify earn path; cut power IAP |
| `pay to win` / `P2W` / `pay2win` | Critical | Mobile > Steam | Ban power from mastery modes |
| `paywall` / `locked` / `have to buy` | Critical | KR mobile pattern | Unlock or earn heroes/towers |
| `IAP` / `microtransaction` (angry) | High | Mobile | Cosmetic-only messaging |
| `ads` / `ad after every` / `unclosable ad` | Critical | PvZ2 pattern | Remove fail interstitials |
| `energy` / `stamina` / `wait to play` | Critical | Clone TD | Never ship |
| `gacha` / `rigged` / `loot box` | Critical | Arena-likes | No random power crates |
| `battle pass` + `power` | High | Liveops hate | Cosmetic track only |
| `bought the game and still` | Critical | KR “buyium” | Parity policy |
| `subscription` / `monthly` (ad-remove) | High | EA backlash | Lifetime ad-free only |
| `small-scale test` | High (memory) | PvZ lawnmower | Never silent core monetize |

Group D — **Content / design toxins**

| Keyword / phrase | Severity | Notes |
|------------------|----------|-------|
| `too short` / `short campaign` / `not enough content` | High (Steam KR5) | Ship modes at launch |
| `soulless` / `worse than previous` | High | Earn finale; protect identity |
| `unbalanced` / `OP` / `nerf` rage | Med–High | Cadence + changelogs |
| `impossible` / `unfair` / `RNG` | Med–High | Optional iron; limit luck fails |
| `disables towers` / `destroys my towers` | High (KR5 bosses) | Agency-preserving bosses |
| `boring` / `same towers` | Med | Role clarity |
| `micro` (hate sense) | Med | Offer set-and-forget options |

Group E — **Technical / access toxins**

| Keyword / phrase | Severity | Notes |
|------------------|----------|-------|
| `crash` / `crashes` / `force close` | Critical | Hotfix > content |
| `lag` / `fps` / `late game lag` | High (BTD freeplay) | Late-wave perf budget |
| `forced update` / `can't play offline` / `always online` | Critical (BTD mobile) | Offline SP contract |
| `lost progress` / `save wiped` | Critical | Cloud + local redundancy |
| `fake ad` / `misleading` / `not the gameplay` | Critical (store trust) | Real trailer only |
| `AI slop` / `AI art` / `generated` | Rising | Art direction QA |
| `copy` / `clone` / `ripoff` | High | Distinct identity |

Group F — **Authenticity toxins** (historical TD risk)

| Keyword / phrase | Severity | Notes |
|------------------|----------|-------|
| `inaccurate` / `anachronistic` / `wrong` | Med–High in niche | Label + errata |
| `horned` / `stereotype` / `lazy` | Med–High | Myth toggle |
| `historically wrong` | Med | Soften “100% accurate” claims |
| `disrespectful` | High if cultural | Consult + fix fast |

### 21.4 Turkish review / community keyword mirror

Türkçe mağaza yorumları ve TR Discord/Telegram TD sohbetlerinde aynı temalar şu kelime kümeleriyle gelir:

**Pozitif (TR):**

| TR keyword | EN equivalent | Ürün notu |
|------------|---------------|-----------|
| `bağımlılık yapıyor` / `bir el daha` | addictive / one more | Positive habit — koru |
| `adil` / `hile yok` | fair / not P2W | Monetization trust |
| `strateji derin` | deep strategy | Build diversity |
| `reklamsız` / `enerji yok` | no ads / no energy | Explicit feature claim |
| `parasına değer` | worth the money | Premium value |
| `tarihi detay` / `gerçekçi his` | historical / authentic | Codex + art |
| `geliştirici dinliyor` | dev listens | Patch culture |

**Negatif (TR):**

| TR keyword | EN equivalent | Escalation |
|------------|---------------|------------|
| `açgözlü` / `para tuzağı` | greedy | Critical |
| `parayla kazanma` / `P2W` | pay to win | Critical |
| `reklam yağmuru` | ad spam | Critical |
| `enerji sistemi` / `bekletiyor` | energy / wait | Critical — ban |
| `kilitli kahraman` / `ekstra ücret` | paywall hero | Parity fix |
| `çöküyor` / `bug` | crash | Hotfix |
| `sahte reklam` | fake ad | Trailer QA |
| `tarihi yanlış` / `uydurma` | inaccurate | Errata + Myth label |
| `AI çöpü` / `yapay zeka görsel` | AI slop | Art QA |

### 21.5 Title-by-title keyword fingerprints

#### Bloons TD 6

| Polarity | Top keywords (göreli) | Platform skew |
|----------|----------------------|---------------|
| + | `depth`, `CHIMPS`, `updates`, `value`, `hundreds of hours`, `towers`, `co-op` | Steam dominant for hours/value |
| + | `no ads`, `fair`, `addictive` | Mobile |
| − | `forced update`, `offline`, `crash`, `lag` round 200+ | Mobile > Steam |
| − | `greedier`, `Double Cash`, `DLC`, `paywall` (cosmetic/power debate) | Steam discussions + reviews |

**Fingerprint lesson:** Depth + free updates = Overwhelmingly Positive; offline/update gates punch above weight.

#### Kingdom Rush 5: Alliance

| Polarity | Top keywords | Platform skew |
|----------|--------------|---------------|
| + | `polish`, `humor`, `heroes`, `Kingdom Rush`, `fun`, `classic` | Both |
| + | `all heroes included` | Steam only |
| − | `IAP`, `paywall`, `heroes cost`, `$50`, `locked maps` | App Store / Play |
| − | `too short`, `not enough maps`, `balance`, `boss destroys towers` | Steam |

**Fingerprint lesson:** Same juice, opposite trust — **platform monetization split** is the review story.

#### Defense Grid 2

| Polarity | Top keywords | Notes |
|----------|--------------|-------|
| + | `classic TD`, `maze`, `fun`, `co-op`, `challenges` | Core loop still loved |
| − | `Workshop`, `pay for maps`, `community content`, `DLC maps` | Dominant hate scar |
| − | `few towers`, `balance`, `short` | Variety thinness |

**Fingerprint lesson:** Never tax UGC — keyword `Workshop` + `pay` is permanent scar tissue.

#### Plants vs Zombies (GOTY vs PvZ 2)

| Title | + keywords | − keywords |
|-------|------------|------------|
| PvZ GOTY (Steam) | `timeless`, `charm`, `soundtrack`, `no MTX`, `perfect`, `modes` | Remaster fear, dated tech wishlist |
| PvZ 2 (App Store) | `fun`, `plants`, `worlds` (when separated from shop) | `ads`, `unclosable`, `subscription`, `paywall plants`, `crash` |

**Fingerprint lesson:** Charm does not survive broken ads + re-monetized ad-free.

### 21.6 Cross-title Top keyword clusters (GDD için)

**Top praise clusters (cross-title):**

1. **Satisfying strategy mastery** — `satisfying`, `strategy`, `challenging`, `hours`  
2. **Presentation juice** — `polished`, `humor`, `music`, `charm`  
3. **Fair premium value** — `worth`, `no ads`, `value`, `updates`  
4. **Long-tail modes** — `endless`, `challenges`, `co-op`, `boss`  
5. **Fresh-but-still-TD twist** — `heroes`, `maze`, `paths`  

**Top complaint clusters (cross-title):**

1. **Monetization distrust** — `greedy`, `IAP`, `paywall`, `ads`, `Workshop pay`  
2. **Thin launch content** — `too short`, `not enough`  
3. **Technical friction** — `crash`, `forced update`, `lag`, `unclosable ad`  
4. **Shallow / skewed balance** — `OP`, `only one tower`, `unfair boss`  
5. **Sequel trust damage** — `worse than`, `soulless`, `sold out`  

### 21.7 Launch monitoring dashboard (operasyonel)

**Daily (first 2 weeks post-launch):**

- Count + spike detect: `crash`, `ads`, `energy`, `paywall`, `greedy`, `fake`  
- Any spike → severity Critical → stop feature work, hotfix / messaging  

**Weekly:**

- Sentiment mix: % reviews containing Group A vs Group C  
- Theme rollup: content volume (`short`) vs fairness (`fair`/`P2W`)  
- TR store: `açgözlü`, `reklam`, `enerji`, `çöküyor`  

**Monthly:**

- Rising risk words: `AI slop`, `inaccurate`, `subscription`, `always online`  
- Compare to competitor fingerprints (BTD/KR) for category drift  
- Feed new phrases into §11 watchlists and this §21 lexicon  

**Alert thresholds (suggested):**

| Signal | Threshold | Action |
|--------|-----------|--------|
| `crash` in ≥15% of new 1★ | Immediate | Perf/hotfix war room |
| `greedy`/`paywall` in ≥20% of new negatives | 48h | Earn-path blog + patch if needed |
| `energy`/`ads` appear at all in our title | Zero-tolerance | Remove mechanic |
| `inaccurate` niche spike | 1 week | Codex errata + Myth label check |
| `fake ad` / misleading | Immediate | Replace store creative |

### 21.8 Store listing copy — keyword hygiene (yazarken)

**Do use in subtitle/description (earned claims only):**

- Fair strategy · No energy systems · Offline campaign · Historical sieges · Cosmetic-only IAP · Continuous free challenges  

**Don’t use (triggers distrust or unearned promises):**

- “100% historically accurate”  
- “More addictive than…” / gambling metaphors  
- “Exclusive powerful hero inside!”  
- Fake UI screenshots / non-gameplay ads  

**ASO note:** Players punish keyword stuffing when gameplay doesn’t match. Prefer honest genre tokens: `tower defense`, `strategy`, `siege`, civilization names — then **earn** `fair` / `authentic` in organic reviews.

### 21.9 Reddit ↔ Store keyword bridge

| Reddit phrase (§2) | Store keyword twin | Shared product rule |
|--------------------|--------------------|---------------------|
| “one more round” | `addictive`, `one more level` | Healthy session cliffs |
| “can’t play without waiting” | `energy`, `stamina` | Ban energy |
| “rigged gacha” | `gacha`, `rigged`, `loot box` | No random power |
| “I already paid for ad-free” | `subscription`, `ads again` | Lifetime ad-free |
| “buyium” / hero paywall talk | `paywall`, `bought the game and` | Platform parity |
| “dev actually patched it” | `dev listens`, `patch` | Public cadence |
| historymemes dunk | `inaccurate`, `stereotype` | Label Myth + errata |
| boss clutch clips | `boss`, `challenging`, `satisfying` | Shareable bosses |

### 21.10 Historical TD — hedef review quote bank (tasarım vizyonu)

Launch sonrası **görmek istediğimiz** örnek cümleler (TR+EN):

- EN: “Finally a TD that feels *fair* — no energy, real strategy, and the Codex actually taught me something.”  
- EN: “Boss sieges are *satisfying*; lost for an hour then clutched it. Dev patch notes are clear.”  
- EN: “Paid once, got the full roster. Cosmetics optional. Offline works on the plane.”  
- TR: “Enerji yok, reklam yok, strateji var. Tarih notları da boş değil.”  
- TR: “Parasına değer; kahramanlar kilitli değil. Bir el daha yapayım deyip saatler geçti.”  

**Görmek istemediğimiz:**

- EN: “Greedy paywall after I already bought the game. Uninstalled.”  
- EN: “Fake ad. Crashes. Ads after every death.”  
- TR: “Açgözlü IAP + enerji. Klasik mobil tuzağı.”  
- TR: “Tarihi diye satılıyor, miğferler tamamen uydurma — ve Myth diye bir etiket yok.”  

### 21.11 Keyword → Do / Don’t hızlı köprü

| If reviews say… | DO | DON’T |
|-----------------|----|-------|
| `satisfying` / `juice` missing | Invest SFX/VFX readability | Add more IAP instead of feel |
| `too short` | Ship challenges/endless/Codex at launch | Promise “liveops will fix length” only |
| `paywall` | Earnable full strategy kit | Argue “optional” while kit is incomplete |
| `crash` / `offline` | Stabilize + offline SP | Ship new hero skin that week |
| `inaccurate` | Errata + labels | Mock the auditor in replies |
| `addictive` (positive) | Protect the loop that earned it | Convert into timers/energy |

### 21.12 İlişki haritası (belgeler)

| Belge | Rol |
|-------|-----|
| `GDD-REDDIT-DEEP.md` §1–§4 | Love/hate + habit language + Do/Don’t anayasa |
| `GDD-REDDIT-DEEP.md` §21 (bu bölüm) | Steam/App Store keyword lexicon + monitoring |
| `GDD-REDDIT-SENTIMENT.md` | Tek sayfa özet |
| `GDD-STORE-REVIEWS.md` | Title-by-title relative frequency mine |
| `GDD-MONETIZATION.md` | Policy that prevents Group C keywords |
| `GDD-RETENTION-VIRAL.md` | Healthy habit loops that earn Group A |

---

## 22. Closing Stamp

*End of GDD-REDDIT-DEEP.md — research input for historical tower defense design bible. No claims of exhaustive scraping; themes triangulated from recurring public community discourse, Steam/App Store review patterns, and press/case studies (2020–2026). §21 appends store keyword mining for launch monitoring.*
