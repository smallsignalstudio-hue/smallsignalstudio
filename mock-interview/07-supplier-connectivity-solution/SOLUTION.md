# 07 — Supplier Connectivity: Çözüm

Tedarikçi rezervasyonu. Invariant: yavaş çağrı isteği tutmaz, `POST /bookings` tekrarlanmaz, ödeme düşünce kapasite bırakılır, hata cevabı cache'lenmez, `errorCode` başarı değildir, overlap gecesinde 02:30 sonraki andır.

Kanıt: `regression/BugRegressionTest` görev projesinde 6/6 kırmızı (okuma testi yeşil), burada yeşil. Reconcile ve devre kesici testi yalnız çözümde.

## SUP-501 — 3 saniyelik reserve

| | |
|---|---|
| Semptom | Reserve 3 sn sürer, HTTP cevabı da 3 sn sonra 200 gelir. |
| Root cause | `HttpClient` connect/read timeout'u yok. JDK client varsayılanı sonsuz. |
| Fix | Connect 500 ms, read 1 sn. `ResourceAccessException` → 504. Süre `failure` sayacına yazılır. |
| Test | `slowSupplierFailsFast` |

## SUP-502 — 500 sonrası ikinci POST

| | |
|---|---|
| Root cause | `bookWithRetry` 500'de aynı rezervasyonu ikinci kez `POST /bookings` eder. Tedarikçi ilk denemede de kayıt açmış olabilir. Idempotency header gitmez. |
| Fix | Tek deneme. 500/timeout → status `UNKNOWN`, rezervasyon `HELD` kalır. `POST /internal/reconcile` `GET /bookings?idempotencyKey` ile bağlar veya iptal eder. İkinci POST yok. |
| Test | `bookIsNotRetriedAfterAServerError` |

## SUP-503 — Ödeme reddi, hold duruyor

| | |
|---|---|
| Root cause | `postPayment` 402 fırlatınca sipariş `PAYMENT_FAILED` olur ama `cancel` çağrılmaz. `reservationStatus` `HELD` kalır. |
| Fix | Reddedilince `POST /reservations/{id}/cancellations`, status `RELEASED`. |
| Test | `failedPaymentReleasesTheReservation` |

## SUP-504 — Boş slot cache

| | |
|---|---|
| Root cause | `computeIfAbsent` hata ve boş listeyi de yazar. 500, 10 dakika boş döner. |
| Fix | Availability hatası cache'lenmez. 5xx çağırana 504 olarak çıkar, sonraki çağrı tedarikçiye gider. |
| Test | `failedAvailabilityIsNotCached` |

## SUP-505 — 200 + errorCode

| | |
|---|---|
| Root cause | HTTP 200 yeterli sayılıyor. `errorCode` dolu olsa da status `BOOKED`. |
| Fix | Adapter gövdede `errorCode` görürse iş kuralı hatası. Rezervasyon bırakılır, 409. |
| Test | `httpOkWithErrorCodeIsNotABooking` |

## SUP-506 — Overlap 02:30

| | |
|---|---|
| Nasıl bulunur | `2026-10-25` Amsterdam'da saatler geri alınır. `02:30` için offset `+02` ve `+01`. `LocalDateTime.atZone` erken olanı (`00:30Z`) seçer. |
| Fix | `SlotInstant.resolve` → `withLaterOffsetAtOverlap` → `01:30Z`. |
| Test | `overlapNightUsesTheLaterOffset` |

Kırmızı ringa: `partySize > 8` reddi doğru. Asıl kaçak orada değil.

## SUP-510 — Devre kesici, bulkhead, reconcile

`CircuitBreaker` tedarikçi başına 3 transport hatasında açılır, sonraki çağrı HTTP'ye çıkmadan 503 döner. Başarı sayacı sıfırlar. `Bulkhead` tedarikçi başına 4 izin; birim test 2 izinle üçüncü `tryAcquire` false. Reconcile `UNKNOWN` satırı tedarikçide bulursa `BOOKED` yapar, bulamazsa iptal eder.

Sınır: reconcile'dan sonra tedarikçi geç oluşturursa bu tur onu görmez. Follow-up'ta sorulur. Yarım açık devre (half-open) yok; başarı sayacı sıfırlar, açık devre `success` ile kapanır ama otomatik deneme yok.

## Refactor sırası

1. Timeout ve ham `errorCode` testi.
2. `SupplierPort` + `HttpSupplierAdapter`.
3. Ödeme reddinde cancel, book'ta tek deneme + `UNKNOWN`.
4. `SlotInstant`.
5. Cache'i hata yolundan çıkar.
6. Breaker, bulkhead, reconcile.

Öncelik: SUP-502 (çift booking), SUP-503 (kapasite), SUP-501, SUP-505, SUP-506, SUP-504, feature.
