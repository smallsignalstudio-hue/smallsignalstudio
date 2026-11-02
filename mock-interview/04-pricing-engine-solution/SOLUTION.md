# 04 — Pricing Engine: Çözüm

Para `BigDecimal` + currency scale. Grup eşiği `>=`. İndirim ara toplam üzerinden, sonuç 0'ın altına inmez. Çocuk grup sayısına girmez. Quote 15 dakika imzalı kilitli.

Kanıt: `regression/BugRegressionTest` görev projesinde 7/7 kırmızı, burada yeşil.

## PRC-401 — 8 × 0.10 uzun kesir

| | |
|---|---|
| Semptom | Total `0.7999999999999999`. |
| Nasıl bulunur | Birim fiyat `double precision`. Döngüde `+= 0.1` sekiz kez. |
| Root cause | İkili kayan nokta 0.1'i temsil edemez. |
| Tuzak | `String.format("%.2f")` sadece gizler; ara toplam hâlâ kirli. |
| Fix | `numeric` kolon ve `Money` (`BigDecimal`). |
| Test | `eightAddOnsAreEightyCents` |

## PRC-402 — 10 yetişkin tam fiyat

| | |
|---|---|
| Root cause | `heads > groupMin`. 10, 10'dan büyük değil. |
| Fix | `adults >= groupMin`. |
| Test | `partyOfTenGetsTheGroupRate` |

## PRC-403 — JPY kesirli

| | |
|---|---|
| Root cause | İndirim satır satır `Math.round(x * 100) / 100` ile 2 haneye yuvarlanıyor. JPY 0 hane. 333×3×0.9 = 899.1, yarım yukarı 899 olmalı. |
| Fix | Önce topla, bir kez `Currency.getDefaultFractionDigits()` + `HALF_UP`. |
| Test | `yenDiscountHasNoFractionDigits` |

## Gizli

| # | Bulgu | Fix | Test |
|---|---|---|---|
| H1 | Band eşiği `stripTrailingZeros().equals`. `10.0` ile `10.00` eşit değil, 10 EUR bandı kaçıyor. | `compareTo == 0` | `priceBandMatchesRegardlessOfScale` |
| H2 | `%150` promo toplamı -10 yapıyor. | `clampAtZero` | `discountDoesNotGoBelowZero` |
| H3 | Min-order indirimden sonra bakılıyor. 100 EUR, min 100, %20 düşünce 80 < 100 diye promo düşüyor. | Eşiği ara toplama uygula | `minimumIsCheckedBeforeTheDiscount` |
| H4 | Çocuklar `heads` içinde. 9 yetişkin + 2 çocuk grup fiyatını açıyor. Ürün yazılmamıştı; netleştirme: sadece ADULT. | Sayacı kategoriye bağla | `childrenDoNotCountTowardTheGroup` |

Kırmızı ringa: `gross()` tutarı değiştirmez. Katalog KDV dahildir; %19 düşmek çift vergi olur.

## PRC-410 — Limit ve kilitli quote

Netleştirme: limit quote anında mı checkout'ta mı, süre dolunca yeni quote mu, imza hangi alanları bağlıyor.

Tasarım: checkout'ta `UPDATE promo SET redemptions = redemptions + 1 WHERE redemptions < max` (satır kilidi). Aynı müşteri `promo_redemption` PK. Quote total + expiry HMAC-SHA256. Checkout imzayı `MessageDigest.isEqual` ile doğrular, katalog değişse de kayıtlı total'ı çeker. Süre `Clock` + 15 dk.

Alternatif: Redis sayaç (crash'te kaçar), serializable isolation (retry şart), fiyatı yeniden hesaplayıp farkı göstermek (ürün "kilitli" dedi).

## Refactor

1. Regression.
2. `Money` (add, times, percentOff, clamp, format).
3. `BaseRate` ve `PromoDiscount` ayrı kurallar.
4. Quote tablosu + `QuoteSigner`.

Öncelik: PRC-401 ve PRC-403 (yanlış tahsilat), PRC-402, H3, feature.
