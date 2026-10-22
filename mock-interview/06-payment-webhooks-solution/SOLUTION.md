# 06 — Payment Webhooks: Çözüm

PSP bildirimi. Invariant: imza ham gövdenin üstündedir, aynı `pspReference` + `eventCode` bir kez işlenir, durum yalnız ileri gider, minor unit para biriminin kendi basamağıdır, bilinmeyen olay 200 döner, voucher isteğin dışında yazılır.

Kanıt: `regression/BugRegressionTest` görev projesinde 6/6 kırmızı (okuma testi yeşil), burada yeşil. Outbox testi yalnız çözümde.

## WH-501 — Aynı bildirim, iki voucher

| | |
|---|---|
| Semptom | Booking 1 iki kez `AUTHORISATION`. İki `voucher` satırı, iki kod. İstek ~2 sn sürer, PSP timeout ile retry eder. |
| Nasıl bulunur | `inbox` tablosunda aynı `dedup_key` iki kez. `voucher` üzerinde unique yok. `VoucherIssuer.issue` her çağrıda `Thread.sleep` ve yeni UUID. |
| Root cause | Dedup kontrolü yok. Render, HTTP thread'inde. |
| Fix | `inbox.dedup_key` unique. Varsa 200 ve çık. Voucher outbox'tan, istek bittikten sonra, tek `psp_reference`. |
| Test | `duplicateAuthorisationIsFastAndIssuesOneVoucher` |

## WH-502 — CAPTURED geri AUTHORISED

| | |
|---|---|
| Root cause | Her olay status'ü doğrudan yazar. Geç gelen `AUTHORISATION`, `CAPTURED` kaydın üstüne `AUTHORISED` basar. |
| Fix | `PaymentStatus` sırası. `canMoveTo` yalnız daha yüksek rank. Eşit veya geri olay inbox'a yazılır, durum değişmez, voucher çıkmaz. |
| Test | `capturedBookingDoesNotMoveBackwards` |

## WH-503 — Geçerli imza 401

| | |
|---|---|
| Nasıl bulunur | Aynı gövdeyi ham string ve `writeValueAsString(readTree(raw))` olarak HMAC'le. `"pspFee":10.50` ikinci halde `10.5` olur. |
| Root cause | İmza yeniden serialize edilmiş JSON üzerinden. `String.equals` ile karşılaştırılıyor. |
| Fix | HMAC ham byte. `MessageDigest.isEqual`. |
| Test | `signatureIsOverTheRawBody` |

## WH-504 — JPY 10, BHD 12.34

| | |
|---|---|
| Root cause | `minor / 100` her para biriminde. JPY'nin minor'u zaten 1 yen (0 basamak). BHD 3 basamak (1000 fils = 1 dinar). EUR 2 basamak olduğu için 1050 → 10.50 tesadüfen doğru görünür. |
| Tuzak | `fee = BigDecimal.ZERO` satırı. Çıkarma tutarı değiştirmez. |
| Fix | `Currency.getDefaultFractionDigits()` ve `movePointLeft`. |
| Test | `yenIsNotDividedByOneHundred`, `bahrainiFilsUseThreeDecimalPlaces` |

## WH-505 — REPORT_AVAILABLE 500

| | |
|---|---|
| Root cause | Bilinmeyen `eventCode` `IllegalStateException`. PSP 500 görünce aynı bildirimi sonsuza kadar tekrarlar, arkadaki capture sırada bekler. |
| Fix | Handler yoksa inbox'a yaz ve 200 dön. Durum değişmez. |
| Test | `unknownEventIsAccepted` |

Kırmızı ringa: `merchantAccount` ve `live` kontrolleri doğru. 401'in nedeni onlar değil, ham gövde ile yeniden yazılmış JSON'un farklı HMAC üretmesi.

## WH-510 — Outbox

`TransactionTemplate` inbox + durum + outbox satırını commit eder, sonra `@Async` publish eder. `VoucherIssuer` aynı booking + psp için ikinci kod yazmaz. `POST /internal/outbox/flush` yalnız `PENDING` satırları yeniden dener. `OutboxMessage.recordAttempt` 3. hatada `DLQ`.

Sınır: publish, commit'ten sonra çağrılmazsa worker satırı göremez. Ölüm, publish ile `PUBLISHED` arasında olursa flush aynı voucher'ı tekrar yazmaz (idempotent issuer) ama satır PENDING kalır. Bunu follow-up sorar.

## Refactor sırası

1. Ham imza testi (`10.50`).
2. `MinorUnits` + `PaymentStatus`.
3. Inbox unique, HTTP'den sleep'i çıkar.
4. Handler başına sınıf.
5. Outbox ve DLQ sayacı.

Öncelik: WH-501 (çift voucher ve timeout), WH-503, WH-504, WH-502, WH-505, feature.
