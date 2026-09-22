# NearHold (working title) — Genius Ideas Deep Dive

**Date:** 2026-09-19  
**Vertical:** Fortress / merge-roguelike tower defense (Fort Guardian adjacent)  
**Finalist score:** 82/100  
**Recommendation:** Ship MVP (prototype fun first, then soft-launch monetization)  
**Mode:** Full auto (user asked for complete category plan; cloud agent defaults)

---

## Phase 0 — Calibrate

| Input | Choice |
|-------|--------|
| `{VERTICAL}` | Fortress / summoning / merge TD (reference: Fort Guardian) |
| Platform | iOS + Android (UA requires both; prototype either) |
| MVP budget | **6 weeks** playable loop + monetization hooks (game override vs utility 4-week default) |
| Monetization | Hybrid **rewarded ads + IAP** (user priority), not subscription-first |
| Art budget | **$0–$50** — geometry / free packs / itch.io cheap packs |
| Phase 4 | Full auto — pick #1 and deep-dive |

**Builder DNA adaptations for games:** still single killer loop, daily reopen, solo-shippable, no realtime multiplayer, no UGC moderation. Backend: none for MVP (local save + ad/IAP SDKs only).

---

## Phase 1 — Signal mining

**Note on 4chan:** `/vmg/` and `/vg/` scans produced noise (FEH merge talk, unrelated mobile drama). Cross-validation relies on Reddit + App Store / Play reviews + Sensor Tower / AppRank estimates — per skill rules, 4chan alone is never enough.

### Evidence table (≥15 signals)

| # | Signal | Source | Quote / finding | Pain type | Payment (Y/N/Weak) | Freq |
|---|--------|--------|-----------------|-----------|--------------------|------|
| 1 | Forced slow speed to sell 2x | [AppRank / FG reviews](https://apprank.io/fort-guardian-8868) | "they cut the normal speed … so you have to pay or watch an ad to have it x2" | Time tax | Y (ad-free / VIP) | High |
| 2 | Perfect-clear loot gate | Same | "one enemy … hit my wall, boom no loot" | Unfair reward | Weak→Y (quit or pay) | High |
| 3 | Crash after ads, no mid-run save | [Play reviews](https://play.google.com/store/apps/details?id=com.xiaomo.bag) | "crashes … after watching ads midgame … no save" | Trust / wasted time | N (uninstall) | High |
| 4 | Content wall ch.200 | [r/VoodooGames](https://www.reddit.com/r/VoodooGames/comments/1lufdmq/fort_guardian/) | "stops at chapter 200 … nothing to do" | Endgame hole | Y (already paid, still stuck) | Med |
| 5 | Broken after updates | Same thread | Black screen / blank battles after update | Trust | Y (refunded ad-free) | Med |
| 6 | RNG auto-loss facilities | AppRank review | "weapon facilities … almost always a guaranteed loss" | Unfair RNG | Weak | High |
| 7 | Addiction despite greed | Punkb25 review | "absolutely love the game" + endgame empty | Hook works | Y | High |
| 8 | Galaxy: ad-gated boss loot | [r/mobilegameology](https://www.reddit.com/r/mobilegameology/comments/1jcutqv/review_of_galaxy_defense_fortress_guard/) | "5 cards… only claim one… other 4 watch an ad" | Predatory ads | Y (SVIP) | High |
| 9 | Galaxy: $50–70 remove ads hate | Same | "almost $70 to remove ads… crazy" | Price anger | Y if cheaper | High |
| 10 | Players still buy VIP | [r/roguedefense](https://www.reddit.com/r/roguedefense/comments/1ssn8og/kinda_like_this_game_but_is_it_worth_to_do_no_ads/) | "time savings… fast forward… worth it if daily" | Time → money | Y | High |
| 11 | Forced ads = uninstall | [r/AndroidGaming](https://www.reddit.com/r/AndroidGaming/comments/1enj816/removed_all_forced_ads_and_we_focused_more_on/) | "Forced ads is a quick uninstall" | Ad format | N forced / Y rewarded | High |
| 12 | Rewarded-only praised | Same | "optional instead… everyone is happy" | Fair monetization | Weak→Y | High |
| 13 | Energy systems hated | [r/RealmDefenseTD](https://www.reddit.com/r/RealmDefenseTD/comments/1brodt3/rd_developer_update_march_2024/) | "Energy is one of those terrible mechanics" | Session gate | N | High |
| 14 | Want classic fair TD | [r/IndieGaming Pure TD](https://www.reddit.com/r/IndieGaming/comments/1uhy2bc/im_making_a_nogrind_classic_tower_defense_for/) | Demand for no-grind / no-P2W | Fairness | Weak (one-time) | Med |
| 15 | Merge+roguelike still fresh | [r/TowerDefense indie](https://www.reddit.com/r/TowerDefense/comments/1u5mj4h/i_made_a_roguelike_merge_tower_defense_12wave/) | Interest in merge + card draft | Curiosity | Weak | Med |
| 16 | FG still monetizes ~$70k/mo Est. | [AppRank](https://apprank.io/fort-guardian-8868) | $70k revenue / 20k downloads Est. | Proven demand | Y | — |
| 17 | Category whales elsewhere | [Sensor Tower Q2’25](https://sensortower.com/blog/2025-q2-android-top-5-tower%20defense%20games-revenue-us-6012932d241bc16eb85e0db8) | Arknights ~$527k/wk US Android peak | Ceiling exists | Y | — |
| 18 | Galaxy ad-heavy revenue mix | [AppGoblin](https://appgoblin.info/apps/6740189002) | ~74% ads / 26% IAP Est. | Ads work | Y | — |

**Payment-intent count with Y:** ≥8. Demand is proven; supply is greedy and buggy.

### Category money map (honest labels)

| Title | Role | Est. money | Tag |
|-------|------|------------|-----|
| Whiteout Survival / Age of Origins | TD-flavored 4X hybrids | $100M–$1B+/yr class | Estimated (industry reports) |
| Arknights | Premium anime TD gacha | ~$355M (2024 est.); $527k/wk US Android peak | Estimated |
| Rush Royale | PvP merge TD | $100M+/lifetime class | Estimated |
| Fort Guardian (Voodoo) | Merge fort roguelike | ~$70k/mo | Estimated (AppRank) |
| Galaxy Defense: Fortress TD | Idle fort TD | <$10k/mo iOS; strong ad mix | Estimated (AppGoblin) |
| Generic merge TD clones | Asset-flip | ~$1k/mo | Estimated |

**Solo-realistic target band:** win the **Fort Guardian / Galaxy Defense sub-niche** first ($50k–150k/mo Estimated with good retention + UA), not Arknights.

---

## Phase 2 — Idea generation (5–8 concepts)

All art-cheap by design.

### A — NearHold (Cliff Fort)
- **Job:** Give me a 2-minute fortress run that always feels like I *almost* won.
- **Killer feature:** Engineered near-miss + **Hold the Line** death choices (ad / gem / gamble).
- **Local-first:** Yes. Offline runs + local meta.
- **DNA:** Single loop, daily reopen, freemium depth.

### B — DrawPath Fort
- **Job:** Let me redraw the enemy maze every wave with my finger.
- **Killer:** Finger-draw path editing mid-run.
- **Local-first:** Yes.
- **DNA:** Strong skill fantasy; art = lines only.

### C — Suika Fort
- **Job:** Drop shapes that merge into towers; enemies walk the lane.
- **Killer:** Suika/2048 drop-merge as tower factory.
- **Local-first:** Yes.
- **DNA:** Viral mechanic; risk of "gimmick only."

### D — FairForge (ethical Fort Guardian)
- **Job:** Same merge-fort fantasy without speed tax / crash / perfect-clear loot.
- **Killer:** Free 3x speed + autosave + pity draft.
- **Local-first:** Yes.
- **DNA:** Clear wedge vs reviews; differentiation is "less evil" not "more fun."

### E — Dice Lane Solo
- **Job:** Random Dice merge lanes without PvP servers.
- **Killer:** Dice-face merge upgrades in a single-player climb.
- **Local-first:** Yes (no multiplayer).
- **DNA:** Proven genre; Random Dice incumbents are strong.

### F — Bounce Ball Fort
- **Job:** Physics balls ricochet and shred waves.
- **Killer:** Trajectory skill shots + auto towers.
- **Local-first:** Yes.
- **DNA:** Hypercasual juice; may feel shallow vs strategy fans.

### G — Swarm Burst Fort
- **Job:** Hold to charge a screen-clear burst between auto-tower waves.
- **Killer:** Timing mini-game for clutch clears.
- **Local-first:** Yes.
- **DNA:** High dopamine; balance risk of becoming a tapper.

### H — Card Wall Roguelike
- **Job:** Slay-the-Spire draft with a wall HP bar.
- **Killer:** Deck-building between waves.
- **Local-first:** Yes.
- **DNA:** Deep; MVP >6 weeks — penalize feasibility.

---

## Phase 3 — Market screen + scores

Weights: Payment 25 · Retention 20 · Diff gap 20 · Solo build 20 · MRR ceiling 15.

| Rank | Idea | Competitors (top) | MRR band | Demand | Incumbent weakness | Monetization that works | Build | Score | Kill? |
|------|------|-------------------|----------|--------|--------------------|-------------------------|-------|-------|-------|
| 1 | **NearHold** | FG, Galaxy Defense, Epic Merge | $10k–50k → $50k+ path | Proven fort TD | Speed tax, crash, unfair loot | Rewarded revive + cheap remove-ads + BP | 6 wks, no BE | **82** | — |
| 2 | DrawPath Fort | Classic maze TD indies, Bloons | $1k–10k | Niche love | Harder UA creative | Ad for undo path / premium maps | 5 wks | **71** | — |
| 3 | FairForge | FG, Galaxy | $10k–50k | Same as FG | Same | Same as FG but fairer | 6–8 wks | **69** | — |
| 4 | Suika Fort | Suika clones, merge TD | $1k–10k | Viral short | Gimmick fatigue | Rewarded drop reroll | 4 wks | **66** | — |
| 5 | Dice Lane Solo | Random Dice / Tower Royale | $10k–50k but crowded | High | Needs polish + content | Gacha dice faces (careful) | 8+ wks | **61** | — |
| 6 | Bounce Ball | Ballz / brick games | $1k–10k | Hypercasual | Shallow retention | Interstitial risk | 3 wks | **58** | — |
| 7 | Swarm Burst | Idle defenders | $1k–10k | Medium | Tapper stigma | Rewarded burst charge | 4 wks | **56** | — |
| 8 | Card Wall | Slay the Spire mobile, Arknights-lite | Unknown high ceiling | High skill | Long build | Season pass | 10+ wks | **49** | **Killed** |

**Why #1 beats #2:** DrawPath is more "pure strategy" but weaker proven monetization and harder TikTok UA than near-miss cliffhangers. NearHold attacks Fort Guardian’s proven revenue loop while fixing the exact 1-star themes that create churn — and turns death into a *wanted* ad moment.

---

## Phase 4 — Challenge (full auto)

### Top 3

| # | Idea | Why it could fail | Variant B (higher monetization) |
|---|------|-------------------|----------------------------------|
| 1 NearHold | If near-miss feels scripted/cheaty, trust dies | **Seasonal Boss Gauntlet** — weekly ranked boss with cosmetic + gem sink |
| 2 DrawPath | UA creatives hard; niche | Sell path "ink" cosmetics + ad for rewind stroke |
| 3 FairForge | Seen as FG clone; Voodoo UA crush | Add light async ghost runs (no realtime MP) |

**Assumptions (full auto):** Maximize fun + voluntary ad density + IAP conversion in the fort-TD sub-niche. Pick **NearHold**. Proceed to Phase 5.

---

## Phase 5 — Deep dive (finalist)

# NearHold — Deep Dive Report

**Finalist score:** 82/100  
**Recommendation:** Ship MVP

---

### 1. Executive summary

**Bet:** The fort-merge TD sub-niche already prints money (Fort Guardian ~$70k/mo Estimated; Galaxy strong on ads), but incumbents monetize by *punishing* players (slow speed, perfect-clear loot, forced mid-run ads, crashes). **NearHold** keeps the proven fantasy (defend the right-side wall, draft upgrades, short escalating waves) and replaces punishment with **engineered near-miss + agency at death**. Art is geometric / free-pack so all budget goes into juice, balance, and UA creatives. Realistic money path: own the midcore-casual fort TD pocket first; do not pretend to out-gacha Arknights on day one.

---

### 2. The problem (evidence-backed)

**Who hurts:** Casual strategy players who like Fort Guardian / Galaxy Defense loops (commute / couch sessions), age ~18–40, Strategy category browsers.

**When it hurts:** Mid-run after an ad crash; after a 25-minute almost-win with 0 loot; when 2x speed is locked; when RNG offers only useless facilities.

**How often:** Daily if hooked; churn within 3–7 days if punished.

| Quote | Source |
|-------|--------|
| "cut the normal speed of the game down by half just so you have to pay or watch an ad to have it x2" | [AppRank FG reviews](https://apprank.io/fort-guardian-8868) |
| "crashes a lot, especially after watching ads midgame… no save" | [Play Store FG](https://play.google.com/store/apps/details?id=com.xiaomo.bag) |
| "other 4 you have to watch an ad for… Someone that payed… gets 2-3times the upgrades" | [Galaxy Defense review thread](https://www.reddit.com/r/mobilegameology/comments/1jcutqv/review_of_galaxy_defense_fortress_guard/) |
| "Forced ads is a quick uninstall from me" | [r/AndroidGaming](https://www.reddit.com/r/AndroidGaming/comments/1enj816/removed_all_forced_ads_and_we_focused_more_on/) |

---

### 3. The product

#### Killer feature (one)
**Hold the Line:** when the wall breaks, the run freezes on a near-miss screen and offers 3 clutch choices — Rewarded Spike (ad), Gem Spike (better), or Gamble Hold (ad → survive + harder next wave + 3× loot).

#### Supporting features (max 2)
1. **90–180s runs at free 3× speed** — respect time; never sell baseline speed.
2. **Pity draft** — between waves pick 1 of 3 upgrades; ban "auto-loss" early weapon spam; soft pity after bad streaks.

#### Explicitly NOT building
- Realtime PvP / clans / chat
- Anime gacha character roster (Arknights trap)
- Energy / stamina gates
- Perfect-clear (0 damage) full-loot requirement
- Forced interstitial ads mid-combat
- Custom 3D pipelines or hand-painted atlases

#### User flow
- **First open (60s):** 10-second tutorial → Wave 1 enemies (red circles) walk left→right → place 2 starter rectangles → win → see upgrade draft → die on wave 7 with Hold the Line → watch first rewarded ad → win → dopamine.
- **Day 7:** Daily Boss Hour + streak chest; player opens for "one more Hold."
- **Conversion moment:** After 3–5 Hold ads in a session, offer **Remove Ads Forever $4.99** (or $9.99 with wall skins + 5×) — framed as time respect, not power.

#### Widget / notification strategy
- Optional daily push: "Boss Hour in 15m — Hold streak at risk" (1/day max).
- No iOS widget required for MVP (game, not utility).

#### Art / asset plan ($0–$50)
| Layer | Approach | Cost |
|-------|----------|------|
| Units / enemies | Circles, triangles, squares with 1 accent color; optional [Kenney](https://kenney.nl/) tiny packs | $0 |
| Towers | Rectangles + simple particle beams (code VFX) | $0 |
| Wall | Thick bar + crack shader (2–3 sprites) | $0 |
| Juice | Screen shake, hitstop, floating damage, juice tween | $0 (code) |
| Optional pack | itch.io "Tiny Swords" / similar if needed for ASO screenshots | $5–$30 |
| Audio | Free SFX (Kenney, OpenGameArt) + 1 loop | $0–$20 |

**Visual brand:** high-contrast flat geometry on atmospheric gradient ground (sand → night), not purple-glow AI default. Readable at TikTok 1× speed.

---

### 4. Monetization

| Tier | Price | Limits / unlocks |
|------|-------|------------------|
| Free | $0 | Full game; rewarded ads for Hold / double loot / extra draft reroll (optional); 1 free Hold token/day |
| Remove Ads | **$4.99** | No rewarded prompts required; keeps optional "watch for bonus" if wanted; free 5× speed |
| Commander Pack | **$9.99** | Remove ads + 3 wall skins + 500 gems + 10 Hold tokens |
| Starter | **$2.99** | Soft early gems (show once, day 1–2) |
| Battle Pass | **$4.99 / 30 days** | Cosmetic trail + exclusive geometric tower tint + gem drip; milestones skill-based (waves survived), not pay-skip |

- **Trial:** None needed; soft paywall after emotional Hold moments.
- **Price rationale:** Undercut Galaxy/FG ad-free ($15–$70). Players explicitly call $50–70 insane — [Galaxy thread](https://www.reddit.com/r/mobilegameology/comments/1jcutqv/review_of_galaxy_defense_fortress_guard/).
- **Expected conversion:** 2–5% IAP of D7 retained (Estimated, casual hybrid comps); rewarded fill rate target 60%+ of sessions with ≥1 voluntary ad (Estimated).
- **Ad rules (non-negotiable):** Zero forced mid-combat interstitials. Ads only on opt-in power moments. Autosave before every ad.

**Why this drives more ads than FG without hate:** death and greed are when willingness-to-watch peaks; FG forces ads for *basic* play. NearHold makes ads feel like clutch plays.

---

### 5. Competition teardown

| Competitor | Rating | Reviews / installs | Pricing | MRR band | Key weakness |
|------------|--------|--------------------|---------|----------|--------------|
| Fort Guardian | ~4.2–4.4 iOS / ~2.9 Play mixed | 1M+ Play; ~5k iOS ratings | Ad-free $15–$25; VIP ~$6; packs | ~$70k/mo Est. | Speed tax, crashes, perfect loot, content wall |
| Galaxy Defense | ~4.7 iOS | 26k ratings | Ad-remove steep; monthly ~$10 | <$10k/mo iOS Est. | Ad-gated loot, P2W feel |
| Siege Arena (Voodoo) | ~4.4 | Small | Hybrid | <$10k/mo Est. | Early / thin |
| Arknights | High | Huge | Gacha | $50k+/mo class | Different game; art+content moat |
| Rush Royale | High | Huge | Gacha PvP | $50k+/mo class | Needs multiplayer backend |

### Why we're different (falsifiable)
- They sell **baseline speed** and **loot completeness**; we give both free.
- They interrupt combat with ads; we only monetize **opt-in clutch** and **post-run double**.
- They soft-lock with RNG trash rolls; we run **pity + banned early trash**.
- They break on ads; we **autosave before every ad** (measurable crash-free Hold rate).

---

### 6. Go-to-market (solo realistic)

**ASO keywords (10):** tower defense, fort defense, merge defense, roguelike td, fortress guard, idle defense, wave defense, strategy game, merge towers, last stand

**Launch channels:**
- r/AndroidGaming + r/iosgaming — "we removed forced ads / free 3× / autosave" value post with GIF (after soft launch data).
- TikTok/Reels UA creatives: **near-miss freeze frame → Hold the Line → spike clear** (3s hook). This is the product.

**First 100 users:** TestFlight/Internal testing → 20 Discord/friends → soft launch 1–2 geos (e.g. PH + CA) → iterate Hold conversion → scale creatives.

**Content hook:** Daily "closest fail" clip (wall at 1% HP) — UGC-friendly without UGC features.

**UA reality check:** Category leaders spend heavily. Solo path = organic + small creative tests → only scale when D1 ≥40%, D7 ≥15%, rewarded ARPDAU healthy (targets Estimated; tune in soft launch).

---

### 7. Build plan (Cursor-friendly)

| Week | Deliverable |
|------|-------------|
| 1 | Core loop: spawn → path → tower shoot → wall HP; geometric art; 3× speed |
| 2 | Wave draft (1 of 3), 10-wave run, lose state, Hold the Line UI (mock ads) |
| 3 | Meta upgrades (3 soft trees), daily Boss Hour, juice (shake/hitstop/SFX) |
| 4 | Real rewarded ads + IAP (remove ads, starter); autosave; analytics events |
| 5 | Pity system, balance spreadsheet, ASO screenshots, soft-launch build |
| 6 | Soft launch fixes, Battle Pass v1, crash-free ad path, store listing |

- **Stack suggestion:** Unity (2D) *or* Godot 4 — both fine for geometric TD; pick what you ship fastest. Local save (JSON/PlayerPrefs). AdMob/LevelPlay + platform IAP.
- **Backend:** None for MVP. Optional later: cloud save / remote config for economy.
- **Compliance:** 13+ fantasy violence; disclose loot-like random draft if needed; ads policy; no gambling mimic beyond clear game RNG.

**Gameplay systems checklist (to beat FG on fun)**
1. Near-miss director (secret HP/pressure curve last 30% of run)
2. Hold choices with clear power fantasy VFX
3. Short runs + instant restart
4. Readable enemy telegraph (color = armor type)
5. Combo meter / overkill juice for dopamine without extra art
6. Fair draft weights + pity
7. Soft meta that never hard-gates skill wins in early chapters
8. Endless after campaign for whales/grinders without empty ch.200 cliff

---

### 8. Financial model (honest ranges)

**Assumptions (Estimated):** Soft launch → gradual UA; ARPDAU from hybrid ads+IAP $0.08–$0.25; D7 12–20% if fun lands.

| Scenario | Month 6 | Month 12 | Month 24 |
|----------|---------|----------|----------|
| Pessimistic | $1–3k MRR | $5–10k | $10–20k |
| Base | $8–15k | $30–60k | $60–120k |
| Optimistic | $25–40k | $80–150k | $150k+ (sub-niche top) |

**Category crown caveat:** Beating Arknights / Whiteout global #1 is not a solo MVP outcome. **Winning Fort Guardian’s pocket** (higher retention + fairer ARPU + cheaper remove-ads conversion) is the falsifiable 12-month goal. Label all figures **Estimated**.

---

### 9. Kill criteria

| Milestone | Metric | Action if missed |
|-----------|--------|------------------|
| Day 14 soft launch | D1 retain <35% or avg session <4 min | Rework near-miss / juice before spend |
| Day 30 | Rewarded opt-in <25% of sessions | Retune Hold offers; check ad fill |
| Day 60 | <$500 net revenue after UA | Kill paid UA; organic-only or pivot to Plan B |
| Day 90 | <$2k MRR and D7 <10% | Kill or full re-roll |

---

### 10. Alternatives portfolio

### Plan B — DrawPath Fort (score 71)
Finger-draw maze each wave; pure skill; $0 art. Monetize undo-stroke ads + map packs. Lower ceiling, higher craft pride. Ship if NearHold near-miss feels manipulative in playtests.

### Plan C — Suika Fort (score 66)
Drop-merge shapes into towers; 4-week MVP; viral UA. Faster ship, faster fatigue. Use as prototype gym for juice/ads if NearHold scope slips.

---

## Phase 6 — Force depth

### Devil's advocate — 5 reasons this fails
1. **Near-miss feels rigged** → 1-star "scripted deaths" reviews; worse than FG RNG hate.
2. **Voodoo-scale UA** outbids you on the same creatives; you never leave soft launch.
3. **Geometric art fails ASO** — players tap prettier FG screenshots; CPI rises.
4. **Hold ads fatigue** — same as revive-ad hell in other genres; eCPM down, churn up.
5. **Category migrates** to 4X-TD hybrids (Whiteout); pure fort TD wallet share shrinks.

### Research homework (do before building week 3+)
1. Play Fort Guardian + Galaxy Defense for 2 hours each; log every ad prompt and every rage moment.
2. Record 10 TikTok-style near-miss prototypes (even greybox) and show 5 people — ask "would you watch an ad to continue?"
3. Price-test remove-ads at $4.99 vs $9.99 in a one-question Discord/TestFlight poll.

### Next decision
**Ship MVP** — but gate UA spend on Day-14 fun metrics above. Fun prototype week 1–2 is the real go/no-go.

### Plan B / C
See §10. Prefer NearHold unless playtests flag manipulation; then DrawPath.

---

## Appendix — Full checklist vs Fort Guardian (what we build to win the pocket)

| FG / Galaxy sin | Our rule |
|-----------------|----------|
| Sell 2× speed | Free 3×; paid 5× only with remove-ads |
| Perfect clear for loot | Partial damage still pays fair loot |
| Crash after ads | Autosave before every ad; resume run |
| Chapter 200 void | Endless + weekly Boss Hour from day one |
| Trash RNG facilities | Pity + early ban list |
| $25–70 ad remove | $4.99–$9.99 |
| Forced mid-fight ads | Opt-in Hold / double only |
| Energy / stamina | None |
| 30-min runs | 90–180s runs, instant retry |

---

## Phase 7 — Gate

**iterate** or **re-roll**?

- **iterate** — stress-test NearHold (fatal flaws, competitor teardown depth, revise killer feature)
- **re-roll** — discard NearHold; next candidate (DrawPath or new vertical)

---

*Generated via workspace skill `genius-ideas`. Research-only — no game code in this deliverable.*
