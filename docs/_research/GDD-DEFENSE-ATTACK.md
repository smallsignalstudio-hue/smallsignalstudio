# Defans ve saldırı mekanikleri (çekirdek combat)

## 1. Temel döngü (60 saniyelik mikro)

1. Wave uyarı (intel strip).
2. Oyuncu kule kurar / upgrade / hero skill.
3. Pathing enemies ilerler; armor/resist etkileşir.
4. Leak → keep HP düşer; perfect block → bonus.
5. Wave clear → gold + star progress + short shop beat.
6. Early-call opsiyonu.

## 2. Harita topolojileri (medeniyet araştırmasından)

| Tip | Örnek | Mekanik |
|-----|-------|---------|
| Straight lane | BTD-like river | klasik |
| Forked path | Persian royal road | split decision |
| Concentric rings | Beaumaris | inner courts unlock |
| Chokepoint gate | Masugata | slow + redirect |
| Multi-keep | Acropolis + Long Walls | resource share |
| Mobile camp | Ordu kurultai | placeable yurt towers |
| Island / moat | Japanese / castle | bridge breakables |
| Siege approach | Babylon processional | staged yards |

## 3. Kule sınıfları (evrensel, civ skin’li)

1. **Arrow / missile** — ucuz DPS, zayıf zırha karşı.
2. **Ballista / heavy bolt** — anti-armor, yavaş.
3. **Splash / oil / fire** — grup, DoT; dost ateşi riski opsiyonel hard mode.
4. **Barracks / spawn** — melee blockers (KR DNA).
5. **Support / buff** — aura (discipline, range, gold).
6. **Special civ** — unique (teppō, mangonel, Greek fire analog **evidence-tagged**, atlatl, etc.).
7. **Hero commander** — aktif skill + pasif aura.

### Upgrade philosophy
- 3 path × 4 tiers (BTD-lite) **veya** 2 path × 5 (daha basit mobil).
- **Öneri launch:** 3 path × 3–4 tier; path mutual exclusion at tier 3+.
- Her path tarihsel rol: örn. Himeji yagura → (A) matchlock nest (B) arrow (C) signal/support.

## 4. Düşman sınıfları

| Tip | Rol | Counter |
|-----|-----|---------|
| Runner | hızlı leak | barracks + slow |
| Armored | tank | ballista / armor shred |
| Swarm | sayı | splash |
| Shield wall | frontal block | flank path / AOE behind |
| Siege engine | kule/keep damage | focus fire |
| Flyer / climber | path ignore partial | dedicated AA tower |
| Support buff enemy | aura | priority kill |
| Boss / general | scripted phases | hero + focus |

### Armor / resist matrix
- Physical / Pierce / Fire / Blunt / Magic(folk)/Gunpowder eras.
- Era gating: gunpowder units sadece Japan/late European/Ming maps.

## 5. Keep / lives sistemi

- **Keep Integrity (0–100)** değil sadece “lives 20”.
- Farklı sızıntılar farklı hasar: runner 1, siege ram 8, boss phase leak 15.
- **Civilian morale** opsiyonel meter: yüksek leak → gold penalty.

### Dual-keep maps
- Shared gold, separate HP.
- Lose either = defeat (Acropolis model) OR lose both (Long Walls atrisyon).

## 6. Kahraman / komutan

- 1 aktif hero per battle (unlock roster).
- Skills: cooldown, mana from kills or time.
- Revive: soft cost or rewarded ad (once).
- **No gacha power heroes** — unlock by campaign stars / achievements.

## 7. Oyuncu aksiyonları (APM soft)

- Place, upgrade, sell (50–70% refund).
- Hero move (grounded) or teleport limited.
- Active abilities (flares, barricade, rally).
- Terrain interact: raise bridge, close gate, flood moat (map script).

## 8. Fog / intel

- Default: see path.
- Hard mode: fog of war; scouts (rewarded or tower).
- Elite wave preview purchasable with rewarded ad.

## 9. Difficulty modifiers

- Iron: limited tower types.
- Heroic: fewer keep HP, stronger elites.
- Historical: “period authentic” unit set only.
- Mythic: FOLK units enabled (optional playlist).

## 10. Balance process

- Telemetry: leak rate by wave, upgrade pick rates, soft sink.
- Weekly hotfix philosophy like BTD.
- Public patch notes; community challenge maps.