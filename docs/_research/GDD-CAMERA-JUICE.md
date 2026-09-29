# GDD — Camera & Presentation Juice (Historical Monumental Keeps)

> Design research for a multi-civ tower-defence where the sellable fantasy is **defending a named monumental keep** (Acropolis, Himeji, Templo Mayor, Weiyang Palace, Etemenanki, and sibling civ packs).  
> Goal: pick **one primary combat camera**, define optional modes, and set juice + accessibility budgets so spectacle never eats readability.

**Status:** Research recommendation (A6 — Camera / presentation).  
**Audience:** GDD authors, art direction, client engineering, UX / a11y.

---

## 0. Verdict (read this first)

| Decision | Choice |
|---|---|
| **PRIMARY combat camera** | **Locked high-oblique ¾ view** (Kingdom Rush / Clash-of-Clans family): fixed yaw + pitch per map (or per keep “face”), **pan + zoom only** during waves |
| **NOT primary** | PvZ side/top lanes · free-orbit 3D (Defense Grid) · always-on free tilt/yaw |
| **Optional modes** | (1) Cinematic orbit for keep intro / victory / defeat only · (2) Strategic top-down overlay for dense pathing · (3) Focus-snap to leak / hero / boss · (4) Accessibility static frame |
| **Juice law** | Feedback must remain diagnosable with **Reduced Motion / Shake 0%** on; replace, don’t delete |
| **Shake budget** | Short positional impulses (~0.12–0.25s); rare boss slams ≤0.35s; **no sustained rotational shake** |

**One-line rationale:** Monumental keeps need **visible height** and **legible routes**. Locked high-oblique shows the keep as a hero silhouette without forcing players to fight the camera—the failure mode of free-orbit 3D on mobile and of side-view lanes for vertical architecture.

---

## 1. What “successful TD cameras” actually optimize

Across hits (PvZ, Kingdom Rush, Bloons TD, Defense Grid, Arknights-style mobile TD, CoC-adjacent strategy presentation), cameras that work share three jobs:

1. **Route clarity before the wave** — player can answer: where from, where to, where the choke is.
2. **Diagnosable failure** — when something leaks, the board (not a cutscene) shows *why*.
3. **Tool readability under pressure** — tower roles, ranges, and priority enemies stay readable when VFX densify.

Spectacle is optional; those three are not. Hybrid action-defense titles (Sanctum, Dungeon Defenders, etc.) routinely fail when the camera “betrays” the player—leaks feel off-screen rather than like planning mistakes. Classic TD succeeds when the camera is **disciplined**.

---

## 2. Comparison matrix

### 2.1 Classic 2D side / top — *Plants vs. Zombies* (and lane cousins)

| Axis | Notes |
|---|---|
| **Camera** | Fixed side (or near-side) view of a lawn grid; lanes = rows; house left / street right. Minimal or no combat camera motion. |
| **Strengths** | Highest lane readability in the genre. Threat → response mapping is instant. Failures are diagnosable (“top lane collapsed”). Extremely accessible for first-time players. Cheap to author and UI-overlay. |
| **Weaknesses** | **Kills monumental architecture as the hero.** A ziggurat, tenshu, or Acropolis hill cannot read as a vertical keep; they become painted backdrop. Multi-path / branching siege maps feel unnatural. Verticality (walls, tiers, causeways) collapses into 2D lanes. |
| **Fit for our keeps** | Poor as primary. Excellent as a **tutorial / mini-game / siege-lane side mode** (e.g. “defend the processional way”) if ever needed. |

**Takeaway:** PvZ wins clarity by sacrificing place-identity. Our product fantasy is *place*.

### 2.2 Isometric / fixed overhead ¾ — *Kingdom Rush* family

| Axis | Notes |
|---|---|
| **Camera** | Fixed high-angle presentation of the whole (or most of the) map. Industry often calls it “isometric”; technically KR classic is closer to **fixed overhead / high oblique** with illustrated 2.5D art—not free orbit. Pan/zoom may exist lightly; yaw/pitch do not change mid-fight. |
| **Strengths** | Chokes, build nodes, and enemy flow readable at a glance. Fixed build spots reduce placement ambiguity so decisions stay *role* (barracks / archers / mage / artillery), not pixel hunting. Proven commercial TD language on mobile and PC. Shows **enough height** for towers, cliffs, and keep silhouettes. Production: art authored for **one viewing cone**. |
| **Weaknesses** | Occlusion if keep mass is oversized and placed poorly. Less “AAA 3D tourist” wow than free orbit. Very tall monuments can silhouette-block rear paths if not art-directed. |
| **Fit for our keeps** | **Best primary.** Acropolis / Himeji / Templo Mayor / Weiyang / Etemenanki all read as *places* while paths stay teachable. |

**Takeaway:** KR’s camera is not flashy; it is the genre’s readability contract. Pair it with keep-forward art direction and we get monumentality *and* strategy.

### 2.3 Free-orbit 3D — *Defense Grid: The Awakening* (and 3D orbit TDs)

| Axis | Notes |
|---|---|
| **Camera** | Fully 3D playspace; player can change zoom levels and (in spirit / free-cam variants) orbit the fortress. DG’s design pitch explicitly used **height, elevated roadways, and tall tower silhouettes** so the third dimension was not wasted. |
| **Strengths** | Best raw showcase for monumental volume, layered elevations, and dramatic lighting. Route control as a *system* can be watched from multiple angles. Distinctive premium look vs Flash-era 2D TD. |
| **Weaknesses** | **Camera is a skill tax.** On mobile, orbit fights placement fingers. Occlusion: keep mass hides paths; players rotate mid-wave and lose temporal awareness. Art must look correct from many angles (cost). Vestibular risk rises with orbit + shake. Hybrid attention split: “where am I needed?” becomes harder. |
| **Fit for our keeps** | Excellent for **cinematics and marketing**, risky as **default combat camera**—especially if mobile-first. |

**Takeaway:** Steal DG’s *vertical map design* (tiers, elevated approaches, tall tower silhouettes). Do **not** steal unconstrained orbit as the default control scheme.

### 2.4 Modern mobile tilt — CoC / Boom Beach–style oblique + pan-zoom (and 3DTD-style rotate)

| Axis | Notes |
|---|---|
| **Camera** | Orthographic or soft-perspective high tilt (~45–50° downward common in CoC-like pipelines), **pan + pinch zoom**; some titles add one-finger rotate. Pre-rendered or realtime 3D assets authored for that cone. |
| **Strengths** | Monumental buildings already sell in this language (keeps, temples, town halls). Familiar mobile gesture grammar. Slightly steeper oblique than classic 2:1 iso can **reduce tile occlusion** while buildings still read tall. Strong App Store / store-page screenshot identity. |
| **Weaknesses** | Free rotate during combat recreates DG’s readability tax. Heavy particle juice + tilt + pan can cause motion discomfort. If zoom defaults too close, whole-map defense awareness dies (base-builder habit ≠ TD habit). |
| **Fit for our keeps** | **Implementation flavor of the primary**, not a separate genre. Use CoC-like *tilt and gesture*, KR-like *locked yaw during waves*. |

**Takeaway:** “Modern mobile tilt” ≠ free camera. It means a **locked high-oblique presentation** with pan/zoom—exactly our primary, executed in a contemporary mobile art pipeline.

---

## 3. Scoring for historical monumental keeps

Rubric (1–5). Higher = better for *this* product.

| Criterion | PvZ side/top | KR fixed ¾ | DG free-orbit | Mobile tilt (locked) | Mobile free-rotate |
|---|---:|---:|---:|---:|---:|
| Keep as hero silhouette | 2 | **5** | 5 | **5** | 5 |
| Path / choke readability | **5** | **5** | 3 | 4 | 2 |
| Diagnosable leaks | **5** | **5** | 3 | 4 | 2 |
| Mobile one-hand / thumb UX | **5** | **5** | 2 | **5** | 2 |
| Art cost / one viewing cone | **5** | **5** | 2 | 4 | 2 |
| Vertical siege fantasy (tiers, walls, processions) | 2 | **4** | **5** | **4** | 4 |
| A11y / vestibular safety | **5** | **5** | 2 | 4 | 2 |
| Marketing “wow” stills | 3 | 4 | **5** | **5** | 5 |
| **Weighted fit (keep-first TD)** | 3.4 | **4.8** | 3.1 | **4.6** | 2.8 |

**Winner:** Locked high-oblique ¾ (KR camera contract + CoC/mobile tilt presentation).  
DG-style orbit and free rotate lose on mobile + readability. PvZ loses on keep identity.

---

## 4. Primary camera specification

### 4.1 Name

**Primary: Locked High-Oblique Keep View (LHOK)**

### 4.2 Hard rules (combat)

1. **Fixed yaw and pitch** while a wave is active (or while the player is in “Defend” state).
2. **Allowed:** pan, pinch/zoom (bounded), optional slight **scripted** push-in on boss spawn (≤8% FOV change, ≤0.4s, ease in/out).
3. **Forbidden in combat:** free orbit, roll, player-driven pitch, first-person, follow-cam on heroes as default.
4. **Framing:** keep mass occupies a stable hero position (usually upper-third or map “crown”); entrances read from map edge; exit / core reads clearly.
5. **Default zoom:** whole critical path visible on phone in portrait *or* landscape (pick one primary orientation early; design maps for it). Zoom-in is for inspecting a tower, not for fighting blind.

### 4.3 Per-keep “face” (art direction)

Each monumental keep ships with a **canonical face**—the angle that sells history and leaves approaches readable:

| Keep | Framing note |
|---|---|
| **Acropolis (Athens)** | Hill mass + Propylaia approach axis; Parthenon as crown, not a blocking wall across mid-path |
| **Himeji** | Tenshu stack reads stepped white silhouette; *ishigaki* and gates as readable choke rings |
| **Templo Mayor** | Twin stair pyramid as vertical climax; processional / causeway approaches stay in foreground |
| **Weiyang** | Axial palace depth; gates and walls as layered rectangles, not a flat stamp |
| **Etemenanki** | Terraced ziggurat tiers as the vertical meter of “progress / threat altitude” |

Maps may offer **2–3 locked facings** (unlocked cosmetically or for alternate layouts), but never free yaw mid-wave.

### 4.4 Implementation notes (engine-agnostic)

- Prefer **orthographic** or very mild perspective so tower ranges don’t foreshorten into lies.
- Author characters / towers for the LHOK cone; use DG’s lesson: **tall distinct silhouettes**.
- Keep mesh uses **see-through / ghosting** when it would occlude path cells under the cursor or under leaking enemies (temporary opacity dip, not permanent glass keep).
- Path ribbon / heat trail optional toggle for new players (on by default in FTUE).

---

## 5. Optional modes (secondary — never replace primary)

| Mode | When | Controls | Must not |
|---|---|---|---|
| **A. Cinematic Orbit** | Keep unlock, mission start tableau, victory, defeat, store trailer capture | Scripted spline or limited orbit; player skip always available | Run during active wave AI decisions |
| **B. Strategic Top-Down Overlay** | Dense late maps, challenge modifiers, “inspect path” button | Instant or 0.2s blend to steeper pitch (~70–85°); same yaw; pan/zoom | Hide keep identity forever; become default |
| **C. Focus-Snap** | Double-tap / hotkey on leak, hero, boss, or alarm ping | Soft pan+zoom to subject, hold ≤1.5s or until player pans | Steal control mid-drag placement |
| **D. Accessibility Static Frame** | Settings: Reduce Motion / Camera Comfort | Disables scripted push-ins, focus-snap motion, cinematics auto-skip to still | Soft-lock content behind motion |

Cinematics may use full DG-like orbit. Combat returns to LHOK with a hard cut or 0.25s blend—player always knows “we are back in defend mode.”

---

## 6. UX readability checklist (camera + presentation)

Ship maps only if designers can answer **yes** under LHOK at default zoom:

1. Can you **trace every entrance → exit** before wave 1 with VFX off?
2. Are **build nodes** visually distinct from decoration?
3. Does the **keep** read as the stake without covering the last 20% of the path?
4. Can you tell **fast / armored / flying / boss** priority without opening a bestiary?
5. When a leak happens, is the failure point **on-screen** without rotating?
6. Do tower **range rings** remain honest under the projection?
7. At peak juice (see §7), can you still see the **line of enemies**?

If (5) or (7) fail, cut VFX density or raise camera—do not add free orbit as a bandage.

### 6.1 UI chrome around the camera

- Resource / wave / lives: stable edges; **no** camera-attached world-space spam that tracks with pan.
- Alarms: edge chevrons + optional focus-snap; never only a screen shake.
- Placement ghost: high-contrast valid/invalid; respect colorblind modes (shape + color).

---

## 7. Juice budget — hit feedback without wrecking the board

### 7.1 Feedback stack (prefer local → global)

Order of operations for a hit / kill / breach:

1. **Local actor juice** — impact flash on target, recoil on tower, death dissolve / ragdoll lite, floating number (toggleable).
2. **Audio** — layered SFX + ducking; critical for Reduced Motion players.
3. **UI tick** — combo / bounty / lives change with micro-animation.
4. **Screen-space accents** — brief vignette, edge flash, chromatic *only* if a11y allows.
5. **Camera** — last resort; smallest amplitude that still “lands.”

If step 5 is required to know a hit connected, steps 1–4 are under-authored. Fix those first.

### 7.2 Screen shake limits

| Event | Max duration | Motion type | Notes |
|---|---|---|---|
| Tower hit / small kill | 0.12–0.18s | Positional, high-freq, spring decay | Per-event; soft-cap stacking |
| Elite / explosion | 0.18–0.25s | Positional + tiny pitch optional | Distance-attenuated from blast |
| Keep damage (lives loss) | 0.20–0.30s | Positional | Pair with red edge + audio stinger |
| Boss slam / keep breach | ≤0.35s | Positional; **avoid roll** | Once per event; never looping |
| Continuous earthquake hazard | Prefer **world props shake**, not camera | — | Or very low amplitude ≤30% slider |

**Stacking rule:** Concurrent shakes use max (or soft blend), not sum. Cap camera offset (e.g. ≤1.5% of screen height).

**Banned by default:** sustained rotational shake, long ( >0.5s) undamped shake, full-screen white strobe, shake that continues while paused menus are open.

### 7.3 Hitstop / time juice

- Micro hitstop (1–3 frames) on chunky hits can sell weight **without** moving the camera.
- Wave slow-mo on boss death: ≤0.6s, skippable, disabled under Reduce Motion (replace with freeze-frame still + fanfare).

### 7.4 VFX vs readability

- Particle budgets scale with quality + “Effects Intensity” slider.
- Never let beams / explosions **paint over path centerline** for >0.2s.
- Status FX use distinct shapes (ice shards vs fire flicker vs poison bubbles), not hue alone.

---

## 8. Accessibility (non-negotiable settings)

Expose in Settings → Comfort / Accessibility (names can localize):

| Setting | Default | Behavior |
|---|---|---|
| **Screen shake intensity** | 100% | Global multiplier 0–100% on all camera shakes |
| **Reduce motion** | Off (respect OS Prefers-Reduced-Motion when present) | Disables orbit cinematics autoplay, focus-snap motion, FOV punches; dampens remaining motion ~20% or replaces with HUD flashes |
| **Flash / photosensitivity** | Full | Off = no strobes; replace with solid color pulses ≤3 Hz equivalent, low contrast |
| **Effects intensity** | High | Scales particles / distortion |
| **Damage numbers** | On | Off or “large only” |
| **Camera assist** | On | Path ribbons, leak chevrons, keep ghosting on occlusion |
| **Colorblind palettes** | Off | Deuteranopia / Protanopia / Tritanopia UI + team colors |
| **UI scale / text** | 100% | Especially for mobile + Steam Deck class devices |

**Principle:** *Replace, don’t remove.* Shake 0% still gets a HUD edge thump + audio. Reduce Motion still gets keep identity via still tableaux.

**Test gate:** Play a late map with Shake 0%, Effects Low, Reduce Motion On. If strategy is unreadable, the design failed—not the player.

---

## 9. Platform notes

| Platform | LHOK guidance |
|---|---|
| **Mobile** | Portrait *or* landscape primary; huge hit targets; pan with one finger, pinch zoom; no orbit gesture in combat |
| **Tablet / foldables** | Same camera; allow wider default framing |
| **PC / console** | Stick/mouse pan; triggers for zoom; optional Strategic Overlay on a button |
| **Store page / trailers** | Use Cinematic Orbit freely; show a clear cut back to LHOK so buyers know the play camera |

---

## 10. Anti-patterns (do not ship)

1. Free orbit as the *only* way to see behind the keep.
2. PvZ side-view as the *campaign* default (monument becomes wallpaper).
3. Juice that communicates critical info only via shake or flash.
4. Default zoom so tight that a second entrance is off-screen.
5. Keep mesh opacity always 100% when blocking path reads.
6. Screen shake on every projectile in a 40-tower board (seasickness simulator).
7. “Cinematic camera” that cannot be skipped on first clear.

---

## 11. Sources & priors (design literature / comps)

- Kingdom Rush — fixed overhead / high-angle combat framing; readability via paths + build nodes (Ironhide; Clemson KR→3D thesis notes fixed overhead as KR’s combat camera).
- Plants vs. Zombies — lane-side clarity and diagnosable failure (Fan / PopCap design commentary; genre readability writeups).
- Defense Grid: The Awakening — 3D verticality, elevated routes, tall tower silhouettes; camera clarity as system-watching (Hidden Path art/design postmortems; Wikipedia feature notes on zoom levels).
- Clash of Clans–style mobile oblique — orthographic / high-tilt presentation (~45–50° art pipelines), pan-zoom grammar; buildings as hero props.
- Tower defense readability — lanes, chokes, diagnosable leaks, camera discipline (e.g. TowerWard readability analysis citing PvZ / KR / DG / BTD6).
- Juice × a11y — short decaying positional shake; global intensity slider; replace-not-remove (StraySpark camera-shake guidance; accessible-juice design notes; reduced-motion as first-class policy).

*Comps are design priors, not IP to copy. Historical keep framing must stay respectful of cultural heritage sites (silhouette homage ≠ vandalism fantasy in default presentation).*

---

## 12. Open decisions for the mega-GDD

1. Primary orientation: **portrait vs landscape** for mobile LHOK maps.
2. Orthographic vs mild perspective (art lead + range-ring honesty test).
3. Whether Strategic Top-Down is a hold-toggle or a full alternate layout per map.
4. How many locked “faces” ship per keep at launch (recommend **1 face + 1 alternate** max).

---

## 13. Summary for the design bible

**Primary camera:** Locked High-Oblique Keep View — fixed yaw/pitch, pan+zoom, keep as crown, paths always diagnosable.  
**Optional:** cinematic orbit (non-combat), strategic top-down overlay, focus-snap, a11y static frame.  
**Juice:** local feedback first; camera shake short, positional, intensity-slid; never the sole signal.  
**Why not the others:** PvZ erases monumental identity; free-orbit 3D taxes mobile UX and leak diagnosis; free mobile rotate repeats that tax. Locked high-oblique is the only option that sells Acropolis / Himeji / Templo Mayor / Weiyang / Etemenanki *and* plays like a fair tower defence.
