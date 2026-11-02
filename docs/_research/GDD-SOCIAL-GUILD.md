# GDD Research — Social, Guilds & Fair Competition (Historical TD)

Dense design bible for **optional social systems** on a historical-civilizations + myth tower defense (Age of Mythology vibe). North star: social is a **habit glue**, never a forced chat dungeon or a P2W ladder. Aligns with `GDD-MONETIZATION.md` (cosmetics / convenience only), `GDD-LIVEOPS-FTUE.md` (soft guild invite D5), `GDD-REDDIT-SENTIMENT.md` (async first, co-op later; hate forced PvP toxicity).

Date snapshot: 2026-08. Retention language: **D7** = weekly habit formation; **D30** = identity + obligation loops that survive novelty decay.

---

## 0. Design principles (non-negotiable)

1. **Opt-in social.** Solo campaign is complete. No guild gate on story acts, stars, or myth unlocks.
2. **Async-first.** Real-time chat and live co-op are Phase 2+. Launch social = guild board + async siege + share cards + friend challenge + fair boards.
3. **Skill brackets, not wallet brackets.** Leaderboards and siege MMR ignore IAP spend, premium seals, and cosmetic loadout. Meta soft-caps apply; hard modes strip most meta (see `GDD-WAVES-ECONOMY.md`).
4. **Historical tone, not trash talk.** Emotes and canned lines use commander / chronicle voice (“The wall holds.”), not modern flame slang.
5. **Anti-toxicity by architecture.** Fewer free-text surfaces → fewer moderation fires. Report + mute still ship for any residual text.
6. **Privacy & minors.** Default: teens+; underage / COPPA paths: no chat, no open invite links, share cards strip PII, parental controls respected.
7. **Measurable retention jobs.** Every feature below names a **D7 job** and a **D30 job**. If it cannot name both, cut or delay.

### Retention vocabulary (use in KPIs)

| Loop type | Example | Horizon |
|-----------|---------|---------|
| Appointment | Daily guild donation, weekly siege window | D7 |
| Obligation | “Don’t leave the fort undefended for clanmates” | D7→D30 |
| Identity | Guild banner on keep, civ rivalry flair | D30 |
| Status | Bracket rank, share-card flex | D7 spike / D30 if fair |
| Reacquisition | Referral returnee, friend challenge ping | D30+ churn reverse |

---

## 1. Guild systems (“Keeps & Cohorts”)

### 1.1 Fantasy frame

Players join a **Cohort** (guild) that holds a shared **Fort Map** — a stylized keep yard themed by season civ (e.g. masugata courtyard, castrum grid, ordu ring). Not a second game; a social hub with light async obligations.

Naming: avoid “Clan War” marketing that implies Clash-style P2W. Prefer **Cohort**, **Legion**, **Banner**, **Watch**.

### 1.2 Structure

| Element | Spec |
|---------|------|
| Size | Soft cap 30; hard cap 40 (anti-zombie mega-clans) |
| Roles | Consul (leader), Prefects (3), Veterans, Recruits |
| Join | Open / Request / Invite-only |
| Activity score | 7-day rolling contributions (siege, donations, challenges) |
| Kick rules | Inactivity 14d auto-flag; Consul confirms; no mass-kick without cooldown |
| Guild XP | From member play **quality** (stars, challenge clears), not spend |
| Guild level | Unlocks cosmetic fort props, emote packs, banner frames — **not** tower DPS |

### 1.3 Member loops (daily / weekly)

1. **Grain tithe (donation):** soft currency or consumable “rations” into guild chest. Cap per day. Rewards cosmetic fragments + soft.
2. **Watch duty:** claim a 1-run “defend the fort” async slot (see §2).
3. **Codex tip of the day:** one historical blurb posted to guild feed (auto-generated from Codex IDs) — low effort presence.
4. **Weekly muster:** Sunday summary — top contributors, siege outcome, next season teaser.

### 1.4 What guilds must NOT do

- Sell power upgrades, exclusive towers, or damage % for dues.
- Require spend to join elite cohorts (moderation + ToS; detect “paywall Discord” patterns via report).
- Force chat before first siege contribution.
- Punish solo players with FOMO that blocks campaign (guild rewards = cosmetics + soft only).

### 1.5 Why this drives D7 / D30

- **D7:** Soft invite on D5 (`GDD-LIVEOPS-FTUE.md`) converts explorers into a weekly appointment (tithe + one watch). Seeing a named fort and 3–5 active names creates “return for us” without toxic voice chat.
- **D30:** Role identity (Prefect), fort cosmetic progress, and seasonal banner history become **identity sunk cost** that survives content drought better than a lone battle pass bar. Churn reverse: returning player still has a seat and a “welcome back rations” chest.

### 1.6 KPI hooks

- % D5 invited → join within 48h.
- Guild DAU / guild MAU; % members with ≥1 contribution / week.
- Solo→guild conversion without campaign drop-off (guardrail).

---

## 2. Async siege (guild fort defense)

### 2.1 Fantasy

Rival cohorts or AI “barbarian seasons” pressure your Fort Map. Members **do not** need to be online together. Each contribution is a short TD defense on a shared fort layout with escalating modifiers across the week.

### 2.2 Cadence

| Window | Length | Player job |
|--------|--------|------------|
| Muster (preview) | 12h | Scout intel (enemy civ, modifiers) |
| Watch | 5 days | Up to N personal defenses / day (e.g. 3) |
| Resolution | Instant on close | Spoils + banner stamp |
| Truce | 1–2 days | Cosmetics craft, recruitment |

Optional monthly **Boss Siege** aligns with live-ops calendar (BTD-like ladder, skill brackets — §6).

### 2.3 Rules of fairness

1. **Loadout lock to campaign progress + skill rating**, not seals spent. Siege uses a **siege-rated power score** derived from cleared acts / challenge medals, then soft-brackets opponents.
2. **No paid “offline loot protection” as power** — if sold (`GDD-MONETIZATION.md`), it only protects **cosmetic fort props / chest appearance**, never HP or DPS. Prefer free baseline protection + cosmetic vanity for IAP.
3. **Contribution scoring:** stars, leaks minimized, early-call skill, modifier clears — **not** damage bought via temporary boosters. Boosters either disabled or capped to cosmetic trail effects.
4. **Anti-carry:** top 3 scores count at full weight; next scores diminishing; idle members still get participation soft but not glory titles.
5. **Attack side (if any):** “raid another fort” = play a mirrored offense scenario against **ghost layout** of last week’s defense — not live grief. Layouts anonymized within bracket.

### 2.4 Session length targets

- Single watch run: 4–8 minutes (mobile bus session).
- Max meaningful contribution: ~15–20 minutes/day — stop homework feel (`GDD-REDDIT-SENTIMENT.md`).

### 2.5 Why this drives D7 / D30

- **D7:** Five-day Watch creates **mid-week appointments** (Tue–Sat). Missed Monday is recoverable; missed whole window is visible on banner — mild social pressure without real-time shame.
- **D30:** Seasonal siege stamps on the fort become chronicle history (“Held the Masugata against three seasons”). Obligation to Prefects and friends’ ghost layouts is the classic healthy retention loop used by guild bosses — without P2W DPS checklists.

### 2.6 Failure modes to avoid

- 30-minute mandatory clears.
- Whale guilds auto-win via paid tower tiers.
- Real-time “defend now or lose” pushes at 3am.
- Public humiliation feed (“X leaked 40”). Use private tips + aggregate “fort integrity %”.

---

## 3. Share cards (chronicle & victory stamps)

### 3.1 What they are

Auto-generated **image + short text** assets for OS share sheets, Discord, Reddit, Instagram Stories:

- Victory stamp (map, civ, stars, time, modifiers).
- Perfect leakless / heroic iron-style variants (“Siege Hardened”).
- Codex discovery card (museum-grade fact + art — Reddit-friendly).
- Guild siege result card (fort integrity, bracket, no player flaming).
- Myth ultimate cinematic still (with “Myth Mode” label when ahistorical).

### 3.2 Design constraints

- **No personal data** on default card (username opt-in; default commander title + guild short tag).
- **QR / deep link** to challenge seed or referral (§4–5), not to paywall.
- Watermark subtle brand; trailer-honest art (no fake gameplay).
- Accessibility: readable contrast; locale-aware numerals.

### 3.3 Surface moments

1. After ★3 first clear.
2. After weekly challenge PB.
3. After guild Watch resolution.
4. After Codex milestone (Act clear).
5. Season end bracket placement (fair tiers only).

### 3.4 Why this drives D7 / D30

- **D7:** Share after early ★3 / first boss creates **external accountability** and mild status (“I posted my Rome clear”). Reopens the app via deep link when friends tap. Organic UA without ad-fraud optics.
- **D30:** Codex and season cards feed community content (historymemes-adjacent authenticity). Players return to **collect stamp sets** and complete “chronicle binder” — collection habit that is social-visible but not P2W.

### 3.5 KPI hooks

- Shares / DAU; share→install or share→return CTR.
- Moderation rate on custom captions (keep captions canned + optional short note).

---

## 4. Referral (“Raise the Banner”)

### 4.1 Goals

Acquire + reacquire without shady multi-account farms. Reward **both** sides with **cosmetics and soft**, never power.

### 4.2 Flow

1. Player generates personal banner code / link (rate-limited).
2. Recruit installs, links account, reaches **quality gate** (e.g. clear Act 0 + one daily) — anti-fraud.
3. Both receive: soft grain, profile stamp, optional emote; referrer gets guild recruitment credit if recruit joins same cohort.
4. Caps: e.g. 5 rewarded recruits / season; diminishing after.

### 4.3 Anti-abuse

- Device / payment / behavior graph signals (standard); do not document exploits in player-facing text.
- No referral progress from same household unlimited farms — soft flag + manual review queue.
- Rewards never include: exclusive DPS towers, siege win tickets that skip skill, paid ladder skips.

### 4.4 Returnee variant

Dormant 30d+ players get a **“recall the legion”** ping from friends (opt-in). Completing one Watch or challenge with a friend grants a returnee cosmetic — stronger D30+ lever than cold push.

### 4.5 Why this drives D7 / D30

- **D7:** New player with a referrer has a **human onboarding buddy** (even async): higher tutorial completion and D1–D7 play. Quality gate ensures referred users are real players, not bots padding metrics.
- **D30:** Referral caps + seasonal refresh create recurring invite moments; returnee path specifically targets **churn reverse**, which pure D7 daily cannot do. Social graph density correlates with long-horizon retention more than another currency sink.

---

## 5. Friend challenge

### 5.1 Modes (async)

| Mode | Description | Fairness |
|------|-------------|----------|
| Ghost race | Same seed / modifiers; compare stars, time, leaks | Identical ruleset |
| Layout duel | Defend friend’s shared fort blueprint | Blueprint sanitized |
| Weekly pact | Both clear same challenge card | Shared modifier deck |
| Teach seed | Mentor sends FTUE-safe map | No ranked impact |

No live spectator flame required. Results via push + share card.

### 5.2 UX

- Friends list = platform friends + in-game codes + guild mates.
- Challenge expires 72h (appointment without eternal backlog guilt).
- Rematch one-tap; trash talk = **canned historical quips only** at launch.

### 5.3 Why this drives D7 / D30

- **D7:** Challenges create **pairwise appointments** independent of guild size — vital for shy players who will not join 30-person cohorts. A single friend is enough to form a weekly habit.
- **D30:** Stable dyads (duo rivals) are among the strongest predictors of long retention in mid-core games. Ghost race avoids toxicity of live PvP while preserving skill expression — Reddit “optional social” preference.

### 5.4 Guardrails

- Block challenge spam (rate limit).
- Optional “focus mode” hides challenges during campaign sessions.
- Ranked ≠ friend challenge (separate rating spaces).

---

## 6. Leaderboards that are fair (skill brackets, not P2W)

### 6.1 Bracket philosophy

**Match and rank by demonstrated skill**, not cumulative spend or raw account level.

Proposed ladder axes:

1. **Campaign medals** — stars / heroic clears (offline-capable board, anti-cheat hashed replays on suspicious PB).
2. **Weekly challenge MMR** — resets or soft-resets; seed-shared.
3. **Boss siege ladder** — monthly; brackets Bronze→Legend by MMR, not VIP tier.
4. **Guild fort integrity** — cohort board within siege bracket.

### 6.2 Explicit anti-P2W rules

| Allowed on boards | Forbidden as rank inputs |
|-------------------|--------------------------|
| Clear time, leaks, stars | Premium currency spent |
| Modifier clears | Temporary damage IAP buffs |
| Fair MMR | Tower rarity paywalls |
| Cosmetic flair on profile | Extra build slots |

Hard mode / siege: **meta soft-cap** or disable (reuse economy doc). Cosmetics may show on the board (flex), but must not change score.

### 6.3 Board UX (toxicity reduction)

- Default view: **your bracket ±10 ranks**, not global whale top-100 as the only screen.
- Global legend board exists but is clearly “top skill,” with verification badges.
- Hide full usernames behind commander titles if reported repeatedly.
- Season rewards: frames, stamps, guild props — **mirror competitive integrity** of cosmetics-only monetization.

### 6.4 Anti-cheat (high level)

- Server-authoritative score submit where possible.
- Replay flags: impossible DPS, speed hacks, modified maps.
- Soft ban from boards ≠ account wipe for first offense; escalate.
- Do not publish exploit steps in patch notes beyond “invalid runs removed.”

### 6.5 Why this drives D7 / D30

- **D7:** Weekly challenge board gives a **clean reason to open the game mid-week** without energy gates. Bracket proximity (±10) makes movement feel achievable — unlike impossible global whale boards that demotivate F2P.
- **D30:** Seasonal MMR memory and verified Legend cosmetics create long-arc goals. Fairness protects review score and “fairness survey” KPI (`GDD-MONETIZATION.md`); unfair boards destroy D30 via 1★ “P2W ladder” reviews even if combat is fair.

### 6.6 KPI / guardrails

- Correlation test: rank vs lifetime spend must stay near zero; alert if spend predicts rank.
- % players who improve bracket position within 14 days of first ranked play.
- Chargeback / review keyword watch: “pay to win leaderboard”.

---

## 7. Anti-toxicity (systems + culture)

### 7.1 Surface inventory (launch)

| Surface | Policy |
|---------|--------|
| Guild feed | Canned posts + reactions; short text optional Phase 1.5 with filter |
| Emotes | Historical / commander pack; no middle-finger analogs |
| Chat | Off by default; guild opt-in later; friend whisper later |
| Voice | Not at launch |
| Leaderboards | No public comments under ranks |
| Share captions | Mostly canned; optional 80-char note filtered |

### 7.2 Moderation toolkit

1. Report (harassment, cheat, spam, hate, underage risk).
2. Mute user / mute guild feed.
3. Block + challenge ban.
4. Consul tools: kick, invite revoke, slow-mode feed.
5. Automod: slurs, doxx patterns, phishing links.
6. Escalation SLA for severe reports.

### 7.3 Social design that prevents toxicity

- **Async > live.** No clutch blame in voice.
- **Aggregate shame, not personal.** Fort integrity %, not “Recruit_42 whiffed.”
- **No hostage mechanics.** Cannot steal another player’s progress; cannot grief build slots.
- **Rivalry framed as chronicle.** “Persia presses the gate” copy, not “destroy noobs.”
- **Sportsmanship stamps.** Optional commend after friend challenge (“worthy rival”) — positive reinforcement.

### 7.4 Why this drives D7 / D30

- **D7:** New social users who are not flamed in week one **stay**. Toxicity is a leading cause of social-feature churn; preventing it is retention engineering, not just trust-safety theater.
- **D30:** Parents / older strategy fans (historical niche) are allergy-sensitive to toxic chat. A calm guild culture keeps the brand premium and review-safe, preserving long-tail D30 in a demographic that hates Clash-style salt.

### 7.5 Policy copy (player-facing tone)

“Cohorts defend history together. Insults, hate, and cheating are siege crimes — reported banners fall.”

---

## 8. Feature × retention matrix (summary)

| Feature | Primary D7 job | Primary D30 job | Monetization adjacency |
|---------|----------------|-----------------|------------------------|
| Guild / Cohort | Weekly tithe + watch habit | Identity + fort cosmetics | Emotes, banners, fort props |
| Async siege | Mid-week appointments | Seasonal chronicle stamps | Convenience ≠ power |
| Share cards | Viral / social proof opens | Codex binder collection | Brand UA, no paywall links |
| Referral | Buddy onboarding | Returnee reacquisition | Soft + stamps only |
| Friend challenge | Dyad appointments | Stable rival identity | None required |
| Fair leaderboards | Weekly challenge pull | Seasonal skill identity | Cosmetic frames only |
| Anti-toxicity | Protect week-one social | Protect brand & niche demo | Trust → willingness to spend cosmetics |

---

## 9. Phased rollout

### Phase A — Launch

- Cohorts (30), tithe, canned feed.
- Async Watch siege (PvE season pressure first).
- Share cards (victory + Codex).
- Referral with quality gate.
- Friend ghost race.
- Weekly challenge brackets + campaign medal board.
- Report / mute / block.

### Phase B — +60–90d

- Guild vs guild ghost raids (bracketed).
- Short guild text with automod.
- Boss siege monthly ladder.
- Returnee referral path.
- Commend / sportsmanship stamps.

### Phase C — Later

- Optional live co-op (shared gold, separate HP — `GDD-DEFENSE-ATTACK.md`) with pause-friendly design.
- Voice only if moderation budget exists — default off.
- Map-seed browser for creators (BTD-like) feeding friend challenges.

---

## 10. Instrumentation checklist (pre-launch)

- [ ] Event: `guild_join`, `guild_contribute`, `siege_watch_submit`, `share_card_out`, `share_card_in`, `referral_qualified`, `friend_challenge_send/complete`, `ladder_submit`, `report_submit`.
- [ ] Retention slices: social vs solo cohorts at D7 / D30.
- [ ] Fairness: rank vs spend correlation dashboard.
- [ ] Toxicity: reports / 1k DAU; mute rate; chat abuse (when enabled).
- [ ] Homework detector: siege time sunk / day alert if >25 min median.
- [ ] FTUE: guild invite soft on D5 does not interrupt Act progress.

---

## 11. Copy deck seeds (historical voice)

Use for emotes / canned challenge lines (localize later):

- “The watch is set.”
- “Grain for the cohort.”
- “The gate held.”
- “Worthy rival — same seed, cleaner leaks.”
- “Raise the banner — a recruit takes the wall.”
- “Siege Hardened.” (heroic clear stamp)
- “Myth night — labeled fantasy.” (myth mode share)

Avoid: “ez”, “trash”, “paywall clearer”, real-world political slurs.

---

## 12. Open questions / FLAG

1. Exact siege-rated power formula vs campaign stars — needs economy sim with `GDD-WAVES-ECONOMY.md`.
2. Whether guild vs guild launches in Phase A or B (recommend B for moderation load).
3. Legal review: referral rewards by region (some stores restrict).
4. Underage mode: full social strip vs guild-as-NPC-only.
5. PC hybrid: share cards vs Steam overlay achievements — keep parity of fair boards.

---

## 13. One-line product thesis

**Give historical TD players a cohort, a weekly wall to hold, a stamp to share, and a ladder that money cannot climb — so D7 is appointment and D30 is identity, without importing PvP toxicity or P2W shame.**
