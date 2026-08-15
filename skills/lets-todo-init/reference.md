# Let's Todo Init — Reference

## Epic catalog (default for a GDD-locked TD launch)

Adapt names; drop epics that BUILD deferred.

| ID | Typical epic | First story |
|----|----------------|-------------|
| E00 | Week 0 provisioning | GitHub / Expo / Supabase / RevenueCat names — no Reddit research |
| E01 | Combat core (path, spawn, shoot, lives, gold, pads) | Research: fairness, juice vs readability |
| E02 | Camera, juice, accessibility | Research: KR camera, DG orbit failure, a11y |
| E03 | Tutorial / Act 0 FTUE | Research: 10-minute mastery, no IAP wall |
| E04+ | Each launch civilization | Research: ARCH/LIT/FOLK; then data, maps, Codex |
| E08 | Hub + daily loop | Research: no store takeover |
| E09 | Codex / Museum subset | Research: almanac vs lecture |
| E10 | Cosmetics, rewarded ads, starter pack | Research: P2W allergy |
| E11 | Social Faz A (if in v1) | Research: async vs live toxicity |
| E12 | Skill modes + boss | Research: Iron/CHIMPS goodwill |
| E13 | Telemetry, IAP verify, server boundary | Research: offline campaign vs online meta |
| E14 | Store / TestFlight | Let's Release; no binary unless asked |

Steam/PC client, if listed in BUILD as a late v1 epic, comes **after** mobile launch content epics.

## Sizing

- Too big: “Implement Egypt.” Split: research → tower trim data → one map → Codex cards → FTUE beats for that act.
- Too small: “Rename a label” may attach to a screen story unless it is a localization pass.
- Combat: separate stories for path follower, wave table loader, gold/lives HUD, pad placement, one tower archetype, sell/upgrade.

## OWASP Top 10 (apply per code story)

Map to this product:

1. Access control — no client-granted premium, scores, or civ unlocks.
2. Crypto — TLS; no homemade token crypto.
3. Injection — no eval of map scripts from network without allowlist; sanitize share/guild strings.
4. Insecure design — campaign playable offline; social cannot gate story maps.
5. Misconfig — no debug entitlements in prod.
6. Vulnerable components — pin Expo modules; justify each native add.
7. Auth failures — Apple/Google session handling; no leaked refresh tokens in logs.
8. Integrity — IAP / leaderboard server-authoritative (GDD §16.1).
9. Logging — security events without PII.
10. SSRF — no open URL fetch from player-supplied strings.

## GDPR (apply per code story)

- Combat-only local save: personal data likely none beyond device.
- Accounts / cloud save / social: lawful basis, minimize, retention, export/delete, children (age gate if social).
- Analytics: no precise location; no advertising ID without consent; FTUE does not demand tracking.
- Push: after reward screen, never mid-wave (GDD §10).

## Regression

New civ must not change Act 0 gold, FTUE order, or global T1–T5 stats (GDD §20). Tests should freeze those fixtures.

## Rerun

If docs change, add stories; do not renumber completed IDs. Append `E15` rather than reshuffling `E04`.
