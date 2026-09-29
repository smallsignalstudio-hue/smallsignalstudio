# GDD Research — Monetization (Goodwill-First Historical Civ TD)

**Scope:** Mobile + PC tower defense revenue that does **not** torch store ratings or community trust.  
**Product:** Historical civilization TD — roster Babylon → Japan (+ Mongol / European Castle as live-ops).  
**North star:** *Skill clears the campaign; money buys beauty, convenience, and earlier access — never mandatory power.*

---

## 1. Design thesis (CAPS$ applied to TD)

Player spend motivations (Mobile Game Doctor CAPS$ hierarchy), mapped to this genre:

| Motive | TD fit | Goodwill risk | Use here? |
|--------|--------|---------------|-----------|
| **C — Cosmetics** | Tower/hero/monument skins, victory banners, map weather FX | Low — if earnable path exists | **Primary** |
| **A — Access** | Civ packs, map packs, season archive, early unlock | Medium — if content feels permanently gated | **Primary** (time-shifted, not forever-locked) |
| **P — Power** | Strictly better towers, paid damage, paid lives | **Very high** — review bombs | **Avoid** as permanent advantage |
| **S — Status** | Season badge, leaderboard frames, guild monuments | Medium — toxic if PvP power-buys | **Cosmetic status only** |
| **$ — Real money** | Gambling / RMT | Regulatory + brand death | **Out of scope** |

**Implication:** Pure cosmetics need *visibility* (replay share, co-op, profile, victory screen). Without that, cosmetics under-earn and teams slide into Power monetization. Build visibility early (victory replay card, co-op skins, profile monument).

---

## 2. Competitor benchmarks (what earns trust vs hate)

### Trust anchors
| Title | Model | Why players tolerate spend |
|-------|-------|----------------------------|
| **Bloons TD 6** | Premium buy-in + optional IAP | No energy; core clearable without IAP; hardest modes ban shop powers; generous earnable currency |
| **Classic Kingdom Rush 1–3** | Premium / light IAP | Full tower set earnable; humor + puzzle clarity; paid heroes felt optional for long stretches |
| **Defense Grid / classic PC TD** | One-time purchase | Zero session gates; community gold standard for “fair” |

### Trust destroyers (store 1★ patterns)
| Pattern | Seen in | Player framing |
|---------|---------|----------------|
| Forced interstitial every wave/level | Raid Rush / hypercas TD clones | “Ad wall” |
| Difficulty spike engineered for revive IAP | Card-draw TDs | “Rigged / P2W” |
| Paid remove-ads then still forced rewarded ads | Multiple mid-tier TDs | “Scam” |
| Paywalled towers required for late maps | KR Alliance reviews | “Sweaty cash grab” |
| Energy empty mid-session | Casual F2P TD | “Cancer” (esp. PC/anon audiences) |
| Ad-for-reward buttons that never fill | Several Google Play TDs | “Fake free path → buy coins” |
| Loot / gacha for towers | Midcore collectors | “Slot machine” |

**Rule of thumb:** If a F2P player with median skill cannot clear ≥70% of campaign without IAP or ads, expect rating collapse regardless of ARPDAU.

---

## 3. Soft energy vs no energy

| Approach | Session effect | Monetization | Goodwill | Verdict for this game |
|----------|----------------|--------------|----------|------------------------|
| **Hard energy** (start costs stamina) | Stops play mid-habit | High short-term ARPDAU | **Toxic** for midcore TD | **Reject** |
| **Lives / fail-gated** | Punishes failure with wait | Revive sells | High rage if difficulty spiked | Use only soft, optional |
| **Soft energy** (optional modes only) | Caps *bonus* farms (raids, endless tickets) | Mild convenience IAP + ads | Acceptable if campaign unlimited | **Optional** |
| **No energy** | Long sessions; BTD6-like | Battle pass + cosmetics + packs | Best for Steam + Reddit/anon | **Default** |

**Recommendation:** **No energy on campaign / story / ranked skill modes.** Optional soft tickets only for *repeatable farm loops* (e.g. “Siege Supply runs” for cosmetic crafting mats), regenerating enough for 2–3 bonus sessions/day. Never gate the next story map behind energy.

**PC/Steam:** Ship **parity pack** (or native premium SKU) that removes all soft tickets + ad prompts. Reputation shield for dual-platform.

---

## 4. Ad-rewarded design (double gold, continue, revive)

### Allowed (opt-in only)
| Placement | Reward | Caps / rules |
|-----------|--------|--------------|
| Post-victory | **Double soft currency** (gold/dust) | 1× per clear; never required |
| Defeat screen | **Continue / revive** once | Max 1 continue/map; disabled in Iron/CHIMPS-like modes |
| Meta hub | **Daily chest** / cosmetic shard | Daily cap; skippable |
| Soft ticket empty | **+1 ticket** | Only on optional farm modes |

### Forbidden
- Interstitial before level start or mid-wave
- Forced video after every clear
- “Watch ad to claim *already earned* reward”
- Remove-ads IAP that still forces rewarded videos for progression
- Banner covering build UI during play

### Policy notes (Apple / Google / AdMob)
- **Apple 2.5.18:** Interstitials must be clearly ads, not trick taps; easy close/skip; report inappropriate ads; no behavioral ads from Kids Category / sensitive data.
- **Apple 3.1.1:** Randomized paid items (“loot boxes”) → **disclose odds before purchase**.
- **Google Play Ads:** No unexpected full-screen ads during gameplay or at level start; interstitials closeable ≤15s (opt-in / post-score exempt); rewarded must be explicit opt-in.
- **AdMob Rewarded:** Clear disclosure of action + reward; affirmative opt-in; skip/dismiss must not break normal play; deliver promised reward.
- **Families / kids audiences:** Stricter (self-certified SDKs, no deceptive monetization, rewarded closeable after 5s when interfering). Prefer **12+ / Teen**, not Kids Category, if using standard ad stacks + IAP.

**Goodwill ads mix:** Target **~70–80% IAP / 20–30% ads** for midcore TD (ads = non-payer contribution, not primary lever).

---

## 5. Cosmetics IAP

### What sells in TD
Players stare at towers for hours → **tower skins, projectile FX, hero banners, keep/monument dress, victory fanfare** outperform avatar-only cosmetics.

### Catalog rules
1. **80–90% earnable** via play / battle pass free track / events.
2. **10–20% paid exclusives** (season prestige, collab, limited civ artisan skins) — status, not power.
3. **Zero combat stats** on any cosmetic (documented in UI).
4. Bundle by civ aesthetic (e.g. “Roman Triumph VFX pack”) for dolphin AOV.
5. Avoid pure FOMO timers on first-season cosmetics; use “season archive” buy-later at higher price.

### Pricing bands (USD guide)
| SKU | Price | Role |
|-----|-------|------|
| Single tower skin | $1.99–$3.99 | Impulse |
| Civ cosmetic bundle (4–6 items) | $4.99–$9.99 | Dolphin |
| Premium monument / keep set | $7.99–$14.99 | Whale vanity |
| Currency only | Prefer avoid as primary — sell *named content* | Transparency |

---

## 6. Battle pass & season pass

Treat **battle pass = season pass** for this product (one track per live-ops season).

### Structure
- **Length:** 6–8 weeks (align with one civ spotlight or dual-civ event).
- **Tracks:** Free + Premium ($7.99–$9.99).
- **Free track value:** Aim **≥60–70%** of premium *volume* (currency, commons, 1–2 cosmetics); premium gets prestige skins, early civ token, banner.
- **Premium rewards:** Cosmetics, cosmetic crafting mats, soft currency, **one early-access tower skin**, never a unique stronger tower.
- **Overflow:** After max tier, earn convertible dust (anti-whale-frustration without power creep).

### What not to do
- Replace real content updates with pass fillers.
- Gate campaign maps behind pass.
- Put essential towers only on premium track.
- Run overlapping paid passes that feel like a second rent.

### Season cadence (civ roster)
Rotate spotlight civs so monetization feels like *history calendar*, not random shop spam:

| Season theme examples | Spotlight | Pass flavor |
|-----------------------|-----------|-------------|
| Flood & Brick | Babylon | Ziggurat skins, flood VFX |
| Eagle & Road | Rome | Legion standards, road dust |
| Immortals | Persia | Immortal armor cosmetics |
| Two Lands | Egypt | Nile season banners |
| Mandate | China | Dynastic keep dress |
| Flower Wars | Maya–Mexica | Feathered banners (sensitive content filters) |
| Longship | Viking | Rune victory cards |
| Polis | Greece | Bronze hoplite FX |
| Castle & Court | Japan | Castle keep kits |
| Orda | Mongol (attacker season) | Horde banner cosmetics |
| Keep & Curtain | European Castle | Heraldry frames |

---

## 7. Civ unlock packs & content access

**Problem:** 11 civs cannot all ship day-1 without either (a) enormous art cost or (b) empty shells.  
**Solution:** **Access monetization with earn path** — not Power.

### Unlock matrix
| Civ | Unlock path | Paid shortcut |
|-----|-------------|---------------|
| Babylon | Tutorial / free always | — |
| Rome | Campaign chapter 2 free | Starter pack includes |
| Egypt / Greece / Persia | Earn via campaign stars **or** $4.99–$6.99 civ pack | Pack = early access + cosmetics |
| China / Viking / Japan | Season spotlight → later free earn | Season pass early token |
| Maya–Mexica / Mongol / European Castle | Post-launch seasons | Civ pack + pass |

**Rules:**
1. Every civ eventually earnable F2P within that season’s window (stars / siege medals).
2. Paid pack may grant **2–4 weeks early access** + exclusive cosmetic — then joins free pool.
3. Packs **never** include exclusive stronger unit trees permanently locked to payers.
4. Steam DLC can mirror civ packs as premium content with clear value (maps + cosmetics + codex).

---

## 8. Starter packs (conversion engine)

First purchase converts better than any whale SKU. Offer **once**, timed after FTUE success (post–map 3–5 clear), ~48–72h window, then retire.

| Pack | Price | Contents | Goal |
|------|-------|----------|------|
| **Bronze Starter** | $2.99 | Remove interstitial ads (if any) + small gold + 1 common skin | Minnow → payer |
| **Silver Legion** | $4.99 | Rome civ early (if not unlocked) + 3 skins + 1 battle-pass discount token | Dolphin seed |
| **Gold Archive** | $9.99 | Season pass + starter cosmetics + 2× daily ticket refill permanent small bump | High-intent |

**Never** put exclusive power in starter. Value should feel “I got more than I paid” without breaking economy.

---

## 9. Whale vs dolphin vs minnow (who pays for what)

Industry pattern (GameAnalytics-style cohorts): few **whales** drive majority of IAP revenue; most payers are **minnows/dolphins**. Design for *healthy dolphin base* so the game does not depend on predatory whale traps.

| Cohort | Approx. share of players | Typical spend | What they buy here |
|--------|--------------------------|---------------|--------------------|
| **Non-payers** | ~95–98% | $0 (+ ads) | Rewarded double gold, free pass track |
| **Minnows** | Majority of payers | $1–$20 lifetime | Starter, single skins, 1 pass |
| **Dolphins** | Mid payers | $20–$200 / year | Every season pass + 1–2 civ packs + bundles |
| **Whales** | ~1–2% of players | $200–$1000s / year | All cosmetics, limited prestige, complete archive, gift packs |

**Whale design (ethical):**
- High-AOV **vanity** (animated keep, exclusive monument VFX, nameplate).
- Complete cosmetic collections / mastery frames.
- **No** pay-only winrate in PvP or campaign gates.
- Support + account security; spending caps / session reminders where jurisdictions expect them.

**Dolphin design (revenue backbone):**
- Reliable $7.99–$9.99 season pass value.
- Civ packs with clear content (maps + cosmetics + codex).
- Bundle discounts during season launch.

**Minnow design:**
- $2.99–$4.99 first purchase excellence.
- Ads as dignity path (opt-in rewards).

---

## 10. Loot boxes / gacha — policy & recommendation

### Platform floor
- **Apple App Store Review Guidelines 3.1.1:** Odds of each item type disclosed **before** purchase.
- **Google Play Monetization:** Same — disclose odds in advance of purchase.
- Console/ESA ecosystem same disclosure expectation.
- Jurisdictions (BE/NL/etc.) may treat paid random rewards as gambling — legal review before shipping gacha in those stores.

### Product recommendation
| Approach | Use? |
|----------|------|
| Paid random **power** (tower rarity, damage rolls) | **No** |
| Paid random **cosmetics** with pity + public odds | Optional late; not launch-critical |
| Earnable cosmetic crates from play | Yes |
| Deterministic shop (buy the skin you see) | **Preferred** |

**Launch default:** No paid loot boxes. Deterministic cosmetic shop + battle pass. If crates ship later, cosmetics-only, published odds, pity, and no paid power.

---

## 11. What causes 1-star reviews (checklist)

Design review gate — any “yes” is a ship blocker:

1. **Cannot progress** without pay or endless ads  
2. **Energy** empty during a fun session  
3. **Forced ads** mid-run / every clear  
4. **Remove-ads purchase lies** (rewarded still required)  
5. **Paywalled towers** required for fair clear  
6. **Revive treadmill** difficulty spikes  
7. **Broken rewarded ads** (“no fill” but only path to currency)  
8. **Loot box** without odds / with power  
9. **Account loss** / cloud save fails after spend  
10. **Cultural disrespect** framed as monetized “edgy” packs (esp. sacred/ritual content)  
11. **PC port nerfed** vs mobile F2P greed  
12. **Misleading store screenshots** (features behind IAP not disclosed)

Historical Civ TD **anti-1★ contract** published in-game: *Campaign completable free; ads optional; cosmetics never change damage; skill modes ban continues.*

---

## 12. Mobile vs PC monetization split

| Surface | Mobile F2P | PC / Steam |
|---------|------------|------------|
| Entry | Free download | Premium (~$14.99) **or** free + same economy with “Commander Pack” |
| Ads | Rewarded only | None (or disabled by default) |
| Energy | None on campaign | None |
| Season pass | Yes | Yes (shared account ideal) |
| Civ packs | IAP | DLC mirroring packs |
| Cosmetics | IAP + earn | Same catalog |
| Skill modes | Continues disabled | Same |

Cross-save + shared purchases (where store policy allows) massively improves goodwill.

---

## 13. Recommended monetization matrix (Babylon → Japan roster)

**Legend:** ✅ Ship · ◐ Soft / limited · ❌ Do not ship

### Pillar matrix

| Pillar | Mobile | PC | Goodwill | Revenue role | Notes |
|--------|--------|----|----------|--------------|-------|
| Cosmetic IAP (towers/keeps/FX) | ✅ | ✅ | High | Primary vanity | 80%+ earnable |
| Battle / season pass ($7.99–$9.99) | ✅ | ✅ | High if fair free track | Recurring dolphin | Cosmetics + currency only |
| Civ unlock packs (early access) | ✅ | ✅ DLC | Medium→High if earnable later | Access | Never permanent power lock |
| Starter pack ($2.99–$4.99) | ✅ | ◐ | High | Conversion | One-time |
| Rewarded: double gold | ✅ | ❌ | High | Non-payer ARPDAU | Cap 1×/clear |
| Rewarded: continue/revive | ◐ | ❌ | Medium | Safety net | 1×; off in skill modes |
| Rewarded: ticket refill | ◐ | ❌ | Medium | Soft farm only | Optional modes |
| Interstitial / banner mid-play | ❌ | ❌ | Toxic | — | Policy + reviews |
| Hard energy on campaign | ❌ | ❌ | Toxic | — | — |
| Soft tickets on farm modes | ◐ | ❌ / buyout | OK | Mild | Generous regen |
| Paid power towers | ❌ | ❌ | Toxic | — | — |
| Paid loot / gacha power | ❌ | ❌ | Toxic + policy | — | — |
| Cosmetic crates (odds shown) | ◐ late | ◐ late | OK if pity | Whale vanity | Post-launch |
| Remove-ads SKU | ◐ | N/A | High if honest | Minnow | Only if any non-rewarded ads exist |
| Premium currency packs | ◐ | ◐ | Medium | Convenience | Prefer named content first |

### SKU → civ roster mapping

| SKU | Price | Unlocks / contains | Targets |
|-----|-------|--------------------|---------|
| Core game (Babylon + Rome + shared grammar) | Free (mobile) / $14.99 (Steam) | Campaign start, 2 civs, skill modes | All |
| Bronze Starter | $2.99 | Gold + 1 skin + ad comfort | Minnow |
| Silver Legion | $4.99 | Rome cosmetics + currency + pass −20% | Dolphin seed |
| Civ Pack: Persia | $5.99 | Early Persia + 1 map + 2 skins | Access |
| Civ Pack: Egypt | $5.99 | Early Egypt + Nile map + skins | Access |
| Civ Pack: China | $6.99 | Early China + keep kit | Access |
| Civ Pack: Maya–Mexica | $6.99 | Early unlock + feathered banners | Access |
| Civ Pack: Viking | $5.99 | Early unlock + longship FX | Access |
| Civ Pack: Greece | $5.99 | Early unlock + polis banners | Access |
| Civ Pack: Japan | $6.99 | Early unlock + castle keep set | Access |
| Season Pass (rotating) | $8.99 | Premium track cosmetics + dust | Dolphin |
| Prestige Monument (limited) | $12.99–$14.99 | Ultra vanity keep | Whale |
| Commander / Parity Pack | $9.99 | No ads, ticket buyout, small cosmetics | Mobile→PC feel |

**Babylon** always free (FTUE identity). **Japan** as late roster prestige civ works well as a high-desire pack/season finale without being day-1 paywall.

### Economy split target (steady state)
| Stream | Share of revenue | Owner cohort |
|--------|------------------|--------------|
| Season passes | 35–45% | Dolphins |
| Civ / content packs | 20–30% | Dolphins + whales |
| Cosmetics / prestige | 15–25% | Whales + dolphins |
| Starters / small IAP | 5–10% | Minnows |
| Ads (rewarded) | 10–20% | Non-payers |

### Launch vs live-ops
| Phase | Monetization live |
|-------|-------------------|
| **Soft launch** | No energy; rewarded double gold; starter; 2–3 cosmetics; **no** pass yet (measure clear rates) |
| **Global launch** | Starter + cosmetics shop + first season pass + Rome/Egypt packs |
| **Season 2+** | Full civ pack ladder; prestige monuments; optional cosmetic crates if ratings ≥4.3 |

---

## 14. Implementation principles (engineering + design)

1. **Single source of truth** for “affects combat? yes/no” on every SKU.  
2. **Skill mode flags** disable continues, tickets, and paid powers.  
3. **Telemetry:** payer conversion, ad opt-in rate, clear rate by IAP status, review-theme tagging.  
4. **Kill switches** for any offer with spike in 1★ mentions.  
5. **Store listing honesty:** disclose optional IAP; don’t show locked civs as if owned.  
6. **Cultural review** on monetized packs involving religion/sacrifice — cosmetic care, not shock SKUs.

---

## 15. Sources & grounding

- CAPS$ spend motivations (Mobile Game Doctor, 2025).  
- Apple App Store Review Guidelines 2.5.18 (ads), 3.1.1 (IAP / loot odds), Kids Category constraints.  
- Google Play Ads policy (unexpected interstitials; 15s close); Families policies; AdMob rewarded policies.  
- BTD6 premium + optional IAP / no-energy reputation; KR Alliance paywall tower review themes.  
- Negative review corpora: forced ads, revive P2W, fake remove-ads (Raid Rush / clone TD patterns).  
- Midcore hybrid monetization norms: IAP-led + rewarded ads for non-payers; soft pacing over hard energy.

---

## 16. One-line GDD law

> **Beauty and earlier chapters are for sale; victory is not.**
