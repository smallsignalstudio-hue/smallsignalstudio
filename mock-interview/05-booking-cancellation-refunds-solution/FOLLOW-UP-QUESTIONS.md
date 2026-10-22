# 05 — Booking Cancellation: Follow-up Questions

## A. Money and time

**A1. Booking 2 is 24h30 before start and the refund is half. Where do you look first?**
- Güçlü: Sabit saatle `Duration.toHours()` ve `hours > 24`. 24h30 → 24, sınır dışarıda kalır. Üstüne `LocalDateTime` zone tutmaz.
- Zayıf: "24 yerine 25 yazarım." Tokyo booking'i hâlâ kayar.

**A2. The app is Europe/Berlin and the activity is Asia/Tokyo. Which instant is "24 hours before start"?**
- Güçlü: `start_at` duvar saati, `zone_id` ile `ZonedDateTime`, karşılaştırma `Instant`. Berlin duvar saati Tokyo turunu kaydırır.
- Zayıf: "Her şeyi Europe/Berlin yaparım." Aktivite kendi şehrinde 18:00'de başlar.

**A3. Why is half of the list price the wrong base?**
- Güçlü: Promo sonrası ödenen 40, liste 100. Yarım iade 20 olmalı. Liste üzerinden 50, tahsil edilmemiş parayı iade eder.

## B. Idempotency and the provider

**B1. The client timed out and retried the same Idempotency-Key. Why two refunds?**
- Güçlü: Anahtar persist edilmeden ve unique index olmadan ikinci istek ikinci PSP çağrısıdır. Kontrol, PSP'den önce ve commit sonrası kısa devre.
- Zayıf: "HTTP timeout'u uzatırım." Çift tıklama ve PSP'nin kendi retry'ı durmaz.

**B2. The note is longer than varchar(40). The provider already refunded and the booking is still CONFIRMED. What happened?**
- Güçlü: Dış çağrı transaction içinde, rollback PSP'yi geri almaz. Önce yerel kayıt, sonra PSP. Kayıt patlarsa para hareket etmez.
- Zayıf: `@Transactional` yeterli sanmak. JDBC rollback HTTP'yi geri saramaz.

**B3. The provider succeeds and the process dies before we store the provider reference. What do you do?**
- Güçlü: Outbox veya "pending refund" satırı commit, PSP, sonra referansı yaz. Crash sonrası reconciliation aynı anahtarla PSP'ye sorar. İkinci para hareketi yok.
- Zayıf: "Log'a bakarım." Para hareketi log ile düzelmez.

## C. State and mail

**C1. Why is cancelling a CANCELLED booking a 409, not another refund?**
- Güçlü: Geçiş yalnız CONFIRMED → CANCELLED. COMPLETED da yasak. 200 dönmek ikinci iade demek.

**C2. The cancellation returned 200 and the traveler got no email. The logs are empty.**
- Güçlü: `@Async` yeni thread, `open-in-view=false`, LAZY customer, exception yutulmuş. E-postayı çağrıdan önce oku, hatayı yutma, notification'ı ölç.
- Zayıf: "try/catch kalsın, mail kritik değil." Sessiz kayıp prod'da görünmez.

## D. Slot cancellation

**D1. One of 200 provider calls returns 500. What does the caller see on retry?**
- Güçlü: Booking bazında sonuç satırı. 500 olan `failed`, diğerleri `refunded`. Aynı Idempotency-Key kayıtlı raporu döner, PSP bir kez. Döngüdeki bir exception diğerlerini kesmez.
- Zayıf: Tüm slotu tek transaction yapıp ilk 500'de hepsini rollback etmek. Yarısı çoktan PSP'de iade olmuştur.

**D2. The report says FAILED but the booking is already CANCELLED. Is that acceptable?**
- Güçlü: Bu çözüm önce persist ediyor. FAILED, "PSP reddetti" demek, "hiç iptal olmadı" demek değil. Ürün kararı: ya yeniden CONFIRMED (kompanzasyon) ya da operasyon kuyruğu. İkisini raporda ayırmak gerekir.

## E. Production

**E1. Two replicas receive the same cancel at the same time. What makes it one refund?**
- Güçlü: Unique index. Biri insert eder, diğeri constraint yer ve mevcut satırı okur. Uygulama içi `synchronized` iki pod'da çalışmaz.

**E2. JVM flags for a 1 GB container that also sends mail async?**
- Güçlü: `MaxRAMPercentage` ~75, container-aware heap, `ExitOnOutOfMemoryError`. Asıl risk async kuyruğunun JDBC pool'u yemesi. Pool boyutu replika ile çarpılır.

## F. AI

**F1. What must the prompt include before an assistant touches this cancel method?**
- Güçlü: Politika (24s, ödenen tutar, aktivite zone'u), "PSP transaction dışında", idempotency unique, status geçişleri, ve bu üçünü kıran testler. Diff'te PSP'nin hâlâ `@Transactional` içinde olup olmadığı elle bakılır.
- Zayıf: "İptali düzelt" deyip yeşil happy path'i kabul etmek.
