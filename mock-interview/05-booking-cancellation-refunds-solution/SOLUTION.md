# 05 — Booking Cancellation: Çözüm

İptal politikası. Invariant: 24 saat veya daha fazla kala ödenen tutarın tamamı, daha yakınsa ödenenin yarısı, liste fiyatı değil. Para ancak booking CANCELLED olarak duruyorsa hareket eder. Aynı anahtar ikinci kez iade etmez. CANCELLED ve COMPLETED tekrar iptal edilmez. E-posta sessizce kaybolmaz.

Kanıt: `regression/BugRegressionTest` görev projesinde 7/7 kırmızı (happy path yeşil), burada 7/7 yeşil. Slot feature testi yalnızca çözümde.

## CXL-501 — Bir buçuk gün kala yarım iade

| | |
|---|---|
| Semptom | Booking 2, 2026-06-02 12:30 UTC. İptal 2026-06-01 12:00 UTC. İade 50, ödenen 100. |
| Nasıl bulunur | Sabit `Clock` ile `Duration.between(now, start).toHours()` ve `hours > 24` yan yana. 24 saat 30 dakika, `toHours()` 24 döner. `>` eşitliği ve tam saati dışarıda bırakır. |
| Root cause | Süre kesiliyor ve sınır `>=` değil. Üstelik `LocalDateTime.now(clock)` ile zone'suz `start_at` karşılaştırılıyor. |
| Tuzak | Sadece `>=` yazmak. Booking 1 (Tokyo 18:00) hâlâ yanlış tarafta kalır. |
| Fix | `startAt.atZone(activityZone)`. Tam iade: `now` başlangıçtan 24 saat sonrası değilse (`!now.isAfter(start.minus(24h))`). |
| Test | `fullRefundWhenMoreThanTwentyFourHoursRemain`, `cutoffUsesTheActivityZone` |

## CXL-502 — Aynı anahtar iki iade

| | |
|---|---|
| Root cause | `Idempotency-Key` okunmuyor. İkinci istek booking'i tekrar CANCELLED yapıp PSP'yi yine çağırıyor. `refund.idempotency_key` unique değil. |
| Fix | Önce anahtara bak, varsa kayıtlı tutarı dön. Yeni kayıtta unique index. PSP bu satır commit olduktan sonra bir kez çağrılır. |
| Test | `sameIdempotencyKeyRefundsOnce` |

## CXL-503 — Para gitti, booking CONFIRMED

| | |
|---|---|
| Root cause | `psp.refund` `@Transactional` metodun içinde, `saveAndFlush`'tan önce. Not `varchar(40)`. 80 karakterlik not update'i patlatır, transaction rollback olur, PSP çağrısı geri alınamaz. |
| Tuzak | Catch içinde "rollback oldu say" demek. Dış sistem transaction'a dahil değildir. |
| Fix | `TransactionTemplate` ile önce status + refund satırı. Commit olmadan PSP yok. Kayıt patlarsa 500, status CONFIRMED, PSP çağrısı 0. |
| Test | `failedSaveDoesNotMoveMoney` |

## Gizli

| # | Bulgu | Fix | Test |
|---|---|---|---|
| H1 | 24 saatten yakın iade `list_price * 0.5`. Booking 6 liste 100, ödenen 40, iade 50. | Ödenenin yarısı, scale 2 HALF_UP | `partialRefundIsHalfOfTheAmountPaid` |
| H2 | Status kontrolü yok. CANCELLED booking 3 tekrar 200 ve yeni refund. | `BookingStatus.requireCancellable` → 409 | `cancelledBookingCannotBeCancelledAgain` |
| H3 | `@Async` mailer LAZY `customer` yükler, `open-in-view` kapalı, `RuntimeException` yutulur. | E-postayı transaction içinde string olarak geçir, yutma | `travellerIsEmailed` |

Kırmızı ringa: `fee = BigDecimal.ZERO` doğru bir indirim değil, sınırı oynamaz. `currency` kolonu iade matematiğinde kullanılmıyor; asıl hata liste fiyatı.

## CXL-510 — Slot kapanınca toplu iade

Netleştirme: bir booking'in PSP reddi diğerlerini durdurur mu, retry aynı raporu mu döner, kısmi başarıda booking ne olur.

Tasarım: `slot_cancellation(idempotency_key, booking_id, outcome)` unique çift. İlk istek her CONFIRMED booking için ayrı `cancel` çağırır, anahtar `key:bookingId`. RuntimeException `FAILED` yazılır, döngü sürer. Aynı anahtarla ikinci istek kayıtlı satırları döner, PSP'ye gitmez.

Sınır: PSP 500 olursa booking o anda çoktan CANCELLED'dır (önce persist). Rapor `failed` der. Tam kompanzasyon (yeniden CONFIRMED) bu sette yok; follow-up'ta sorulur.

## Refactor sırası

1. Regression testleri, sabit saat.
2. `HoursBeforeStartPolicy` (zone + ödenen tutar).
3. `BookingStatus` geçişleri.
4. Persist ile PSP'yi ayır, unique idempotency.
5. Mailer'a hazır e-posta.
6. Slot raporu ayrı servis.

Öncelik: CXL-503 (para), CXL-502, politika (CXL-501 ve H1), sonra H2, H3, feature.
