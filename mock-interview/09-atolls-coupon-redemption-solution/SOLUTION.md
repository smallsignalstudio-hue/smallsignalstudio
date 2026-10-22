# 09 — Kupon: Çözüm

Tek kullanımlık kod. Invariant: kod `trim` + `Locale.ROOT`, son gün kampanyanın zone'unda dahildir, limit kampanya bazındadır, aynı kod iki kez düşmez, liste `(market OR global) AND active AND tarih` dir.

Kanıt: regression görevde 6/6 kırmızı, burada yeşil. Mint ve 429 yalnız çözümde.

## CPN-501 — indirim bulunamadı

| | |
|---|---|
| Root cause | `toUpperCase(Locale.forLanguageTag("tr"))`. Türkçe `i` → `İ`. `indirim` → `İNDİRİM`, kayıt `INDIRIM`. Boşluk da kırpılmıyor. |
| Fix | `trim().toUpperCase(Locale.ROOT)`. |
| Test | `turkishLowerCaseStillFindsTheCode` |

## CPN-502 — Son gün kapalı

| | |
|---|---|
| Root cause | `!today.isBefore(validUntil)`. Eşitlik dışarıda. 22 Ekim sabahı, `valid_until` 22 Ekim, ret. |
| Fix | Market zone'unda `today.isAfter(validUntil)` değilse açık. Eşit gün geçer. |
| Test | `lastValidDayIsInclusive` |

## CPN-503 — Honolulu

| | |
|---|---|
| Root cause | `LocalDate.now(clock)` UTC. Saat 06:00Z iken Honolulu hâlâ 21 Ekim. `valid_until` 21 Ekim. UTC takvimi 22 Ekim olduğu için kod ölü görünür. |
| Fix | `clock.withZone(campaign.zoneId)`. |
| Test | `marketZoneDecidesTheCalendarDay` |

## CPN-504 — Başka kampanyanın limiti

| | |
|---|---|
| Root cause | `countByUserId` bütün kampanyaları sayar. User 7 Fransız kodu kullanmış, Alman limiti 1 olduğu için `FRESH` kapanır. |
| Fix | `countByUserIdAndCampaignId`. |
| Test | `limitIsPerCampaign` |

## CPN-505 — Liste

| | |
|---|---|
| Root cause | `market = :market or globalOffer = true and active = true`. `AND`, `OR`'dan sıkı. İstenen marketin pasif satırı (`Closed`) koşulsuz gelir. Global ve active olan 2020 teklifi de gelir; tarih hiç yok. |
| Fix | `(market = DE OR global) AND active`, sonra zone'lu `validUntil`. |
| Test | `inactiveAndExpiredGlobalsStayHidden` |

## CPN-506 — İki redemption

| | |
|---|---|
| Root cause | Oku, bekle, yaz. İki transaction `redeemed_by` null görür. Bekleme yarışı büyütür; asıl delik koşullu update olmaması. |
| Fix | `UPDATE ... SET redeemed_by = :user WHERE id = :id AND redeemed_by IS NULL`. Etkilenen satır 0 ise 409. Üstüne `redemption(coupon_id)` unique. |
| Test | `oneCodeCannotBeRedeemedTwice` |

## CPN-510 — Mint ve limit

Alfabe `A-Z` ve `2-9`, `I L O U 0 1` yok. `SecureRandom`. Çakışmada başka kod. Job 10'luk değil tek tek ilerler, `generated` artar, bitince `DONE`. Bilinmeyen kodda sayaç; 5'ten sonra 429, başarılı redeem sayacı siler.

CSV refactor bu sette ayrı bir parser değil: mint zaten batch halinde yazıyor. Follow-up streaming'i sorar.

## Sıra

1. `Locale.ROOT` ve trim.
2. Zone + inclusive gün.
3. Limit kampanyada.
4. Koşullu update.
5. Liste parantezi.
6. Mint ve 429.

Öncelik: CPN-506 (çift kullanım), CPN-501, CPN-505, CPN-502, CPN-503, CPN-504, feature.
