# 06 — Payment Webhooks: Follow-up Questions

## A. Delivery

**A1. The provider timed out and sent the same AUTHORISATION again. Why are there two vouchers?**
- Güçlü: At-least-once. `pspReference` + `eventCode` inbox'ta unique değilse ikinci istek ikinci kodu basar. HTTP 200'den önce kayıt commit olmalı. Voucher ayrı ve idempotent.
- Zayıf: "Timeout'u 30 saniyeye çıkarırım." PSP yine retry eder, ikinci pod da işler.

**A2. Why must the handler return before the PDF exists?**
- Güçlü: PSP birkaç saniye içinde 500/timeout sayar ve aynı bildirimi yığar. Render'ı istek thread'inde `sleep` ile yapmak retry fırtınasıdır. Önce inbox, 200, sonra outbox.
- Zayıf: "Async yeter." Commit'ten önce async başlarsa worker satırı göremez.

**A3. CAPTURED then a late AUTHORISATION. What should status be?**
- Güçlü: İleriye dönük sıra. Geç gelen yetki durumu geri sarmaz, inbox'a düşer, 200 döner. Aksi halde voucher ve capture birbirinden kopar.
- Zayıf: "Son gelen kazanır." Gecikmiş paket ödemeyi geri açar.

## B. Signature and money

**B1. Their dashboard says the HMAC is valid. We return 401 when the body contains 10.50. Where do you look?**
- Güçlü: Ham byte ile `readTree` + `writeValueAsString` çıktısını yan yana koy. `10.50` ile `10.5` farklı HMAC'dir. İmza yeniden serialize üstünde.
- Zayıf: "Secret yanlış." Aynı secret ile ham gövde tutuyor.

**B2. Why not `String.equals` on the hex?**
- Güçlü: Erken çıkış süresi gövde hakkında bilgi sızdırır. `MessageDigest.isEqual` sabit süre. Uzunluk farklıysa önce eşitlemeden karşılaştırma da erken döner; uzunluğu da sabit tutmak gerekir.

**B3. 1000 JPY became 10, 1234 BHD became 12.34, 1050 EUR looked fine. What is the rule?**
- Güçlü: Minor unit, `Currency.getDefaultFractionDigits`. JPY 0, BHD 3, EUR 2. Sabit `/100` yalnız 2 basamaklı para birimini şans eseri doğrultur.
- Zayıf: "JPY'yi özel if yaparım." Bir sonraki 3 basamaklı para yine kırılır.

## C. Failure

**C1. REPORT_AVAILABLE returns 500. Why is that worse than ignoring it?**
- Güçlü: PSP aynı bildirimi başa koyup retry eder. Arkadaki capture teslim edilmez. Bilinmeyen olay 200 + inbox, iş yan etkisi yok.

**C2. The voucher service succeeded and the process died before PUBLISHED. What happens on flush?**
- Güçlü: Issuer, booking + psp reference varsa ikinci satır yazmaz. Flush PENDING'i tekrar dener, sonuç hâlâ tek voucher, satır PUBLISHED olur. DLQ yalnız issuer 3 kez fırlatırsa.
- Zayıf: "Yeni voucher basarım." Misafir iki kod görür.

**C3. Two replicas take the same notification. What is the lock?**
- Güçlü: Unique index. Biri insert eder, diğeri constraint yer ve 200 döner. `synchronized` ikinci JVM'de yok.

## D. Production

**D1. How do you watch this in production?**
- Güçlü: Inbox yaşı, PENDING outbox sayısı, DLQ sayısı, imza 401 oranı, eventCode bazında lag. 500 oranı tek başına WH-505'i göstermez; eventCode kırılımı gerekir.

**D2. JVM flags for a 1 GB container that also renders vouchers?**
- Güçlü: `MaxRAMPercentage` ~75, `ExitOnOutOfMemoryError`. Render ayrı havuzda, HTTP havuzu ve JDBC havuzu birbirine karışmaz. Pool boyutu replika ile çarpılır.

## E. AI

**E1. What do you refuse to let an assistant change in this handler?**
- Güçlü: Ham gövde imzası, unique dedup, forward-only status, fraction digits, ve 200-vs-500 bilinmeyen olay. Happy path EUR testi bunları yakalamaz. Diff'te `/100` ve `writeValueAsString` elle aranır.
- Zayıf: "Testler yeşilse imza doğrudur." EUR gövdesi round-trip ettiği için yeşil kalır.
