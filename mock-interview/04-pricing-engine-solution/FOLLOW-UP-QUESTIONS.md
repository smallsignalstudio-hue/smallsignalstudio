# 04 — Pricing Engine: Follow-up Questions

## A. Money

**A1. Why is 8 × 0.10 not 0.80?**
- Güçlü: `double` 0.1'i sonsuz kesir tutar. Toplam `BigDecimal`, kolon `numeric`. Format en sonda.
- Zayıf: "`%.2f` yeter."

**A2. `BigDecimal.equals` vs `compareTo`?**
- Güçlü: `equals` scale bakar. `10.0` ≠ `10.00`. Eşik için `compareTo`.
- Zayıf: "`setScale` her yerde çağırırım" deyip bir yolu unutmak.

**A3. Where do you round, and how many digits for JPY?**
- Güçlü: Satır değil toplam, bir kez, `HALF_UP`, `Currency.getDefaultFractionDigits()`. JPY 0, BHD 3. `Math.round(x*100)` JPY'yi bozar.
- Zayıf: Her para birimine 2 hane.

**A4. A 150% promo. What is the total?**
- Güçlü: 0. Negatif tahsilat PSP'yi ve defteri bozar. Clamp, promo tanımında üst sınır da olabilir.
- Zayıf: "Negatif indirim iade olsun."

## B. Rules

**B1. Party of 10 pays 200. Which comparison?**
- Güçlü: `>` yerine `>=`. Sınır örnekleri testte: 9, 10, 11.
- Zayıf: Eşiği 9 yapmak. Veri kuralı gizler.

**B2. Do children count toward the group?**
- Güçlü: Spec susuyor, sorarım. Bu çözümde sadece ADULT. Çocuk kendi fiyatında kalır.
- Zayıf: Sessizce herkesi saymak.

**B3. Min order 100, subtotal 100, 20% off. Promo applies?**
- Güçlü: Evet. Eşik indirimden önce. Sonra bakınca 80 yüzünden promo kaybolur.
- Zayıf: "Müşteri 80 ödüyor, min'i geçemedi."

## C. Promo and quote

**C1. How do two checkouts not both take the last redemption?**
- Güçlü: Tek `UPDATE ... WHERE redemptions < max`. Postgres satırı kilitler. Uygulama sayacı iki replikada kaçırır.
- Zayıf: "`synchronized`."

**C2. Once per customer and a retry of the same checkout?**
- Güçlü: `(promo_id, customer_id)` PK. Aynı quote ikinci kez `consumed`. İkisi de 409, sayaç rollback ile geri gelir.
- Zayıf: Sadece UI'da butonu kapatmak.

**C3. What is inside the signature?**
- Güçlü: id, total, currency, expiry epoch. HMAC sunucu sırrı. Karşılaştırma `MessageDigest.isEqual`. Katalog değişince checkout kayıtlı total.
- Zayıf: Total'ı yeniden hesaplamak. Kilit bozulur.

**C4. Clock moves 16 minutes. What do you assert?**
- Güçlü: `expires_at` `Clock`'tan yazılır. Süre dolunca 409, yeni quote. Test `expires_at`'i geri alabilir veya `Clock` sabitlenir.
- Zayıf: `LocalDateTime.now()` ve zone'u umursamamak.

## D. Production

**D1. Partner settles in another currency. Where does FX live?**
- Güçlü: Quote anındaki kur, total ile birlikte kilitlenir. Payout anı ayrı bir bug sınıfı (ledger setinde).
- Zayıf: Checkout'ta günün kuru.

**D2. How do you roll a new price rule out?**
- Güçlü: Kural zincirine bir sınıf, feature flag, eski quote'lar imzalı kaldığı için etkilenmez.
- Zayıf: `PricingService` ortasına if.

**D3. JVM flags and money?**
- Güçlü: Para için GC'den çok `numeric` ve test. Yine de container'da `MaxRAMPercentage`. Yuvarlama hatası heap ile düzelmez.
- Zayıf: "ZGC kesin hesap yapar."

**D4. Where do you not let an AI change pricing?**
- Güçlü: Scale, `equals`, eşitsizlik yönü, hangi satırın yuvarlandığı. Üretilen kodu sınır değerleriyle ben koşarım.
- Zayıf: "Test yeşil, 10 kişilik parti yoktu ama olsun."
