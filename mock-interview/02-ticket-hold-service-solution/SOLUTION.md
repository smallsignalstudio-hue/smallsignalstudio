# 02 — Ticket Hold: Çözüm

Koltuk satışı. Invariant: bir koltuk aynı anda tek hold'da, hold ya hep ya hiç, confirm sadece sahibi, satılmış koltuk expiry ile geri açılmaz, 10 dakika UTC anına göredir.

Kanıt: `regression/BugRegressionTest` görev projesinde 5/5 kırmızı, burada yeşil.

## BOX-201 — 20 dk önceki hold hâlâ duruyor

| | |
|---|---|
| Semptom | Seat 17 (hold 51) `POST /internal/expire` sonrası HELD. |
| Nasıl bulunur | `expires_at` ile `SELECT now(), now() AT TIME ZONE 'UTC'` yan yana. Seed Berlin duvar saati, karşılaştırma UTC. |
| Root cause | `LocalDateTime.now()` JVM zone'u (`TZ=Europe/Berlin`) yazar. Sweeper `expires_at < now() AT TIME ZONE 'UTC'` kullanır. Berlin, UTC'den 1–2 saat ileride olduğu için süre dolmuş hold hâlâ "gelecek" görünür. `Clock` bean'i duruyor ama kullanılmıyor. |
| Tuzak | TTL'i 10'dan 5'e düşürmek. Zone düzelmeden kayma sürer. |
| Fix | `Clock.systemUTC()` ile yaz ve aynı `LocalDateTime.now(clock)` ile karşılaştır. |
| Test | `holdExpiresTenMinutesAfterItWasCreatedRegardlessOfZone` |

Alternatif: kolon `timestamptz`. Daha doğru model. `LocalDateTime` seçildiyse tek zone sözleşmesi şart.

## BOX-202 — Dün satılan koltuk yine satışta

| | |
|---|---|
| Root cause | `releaseSeatsOfExpiredHolds` sadece `expires_at`'e bakar, `hold.status = 'HELD'` şartı yok. Confirm edilmiş hold'un `expires_at`'i geçmişte kalır. `markExpiredHolds` status filtreler, ama koltuk güncellemesi ondan önce ve daha geniş çalışır. |
| Fix | Önce sadece HELD ve süresi dolmuş hold'ları EXPIRED yap, sonra yalnızca status'ü EXPIRED olan hold'ların HELD koltuklarını bırak. |
| Test | `expiryDoesNotReleaseSeatsOfAConfirmedBooking` |

## BOX-203 — 4 koltuktan 3'ü yapışık kalıyor

| | |
|---|---|
| Root cause | `reserveSeat` `@Transactional` ama aynı sınıftan `this` ile çağrılıyor; proxy devreye girmez. Her `saveAndFlush` kendi transaction'ında commit olur. 4. koltuk SOLD olunca exception gelir, ilk üç koltuk HELD kalır. |
| Tuzak | Sadece catch içinde tek tek geri almak. O da yarışta kaybeder. |
| Fix | Tek `@Transactional` metot. Önce `SELECT ... FOR UPDATE ORDER BY id`, hepsi AVAILABLE değilse exception (rollback). |
| Test | `failedGroupHoldRollsBackEverySeat` |

## Gizli

| # | Bulgu | Fix | Test |
|---|---|---|---|
| H1 | İki istek aynı koltuğu AVAILABLE görüp ikisi de 201 döner. Satır kilidi veya `UPDATE ... WHERE status='AVAILABLE'` yok. | `PESSIMISTIC_WRITE` + id sırası (deadlock yok) | `oneSeatIsHeldByExactlyOneCustomer` |
| H2 | `confirm` `customerId` kullanmıyor. Başkası hold'u satın alabilir. | 403 | `onlyTheOwningCustomerCanConfirm` |

Kırmızı ringa: `maxSeatsPerOrder` kontrolü doğru. Schema'daki `hold_id` FK'si de doğru; asıl delik status kontrolünün olmaması.

## BOX-210 — Best available

Netleştirme: aynı sıra mı, bölüm yeterli mi, tek koltuk yasağı iki yana da mı, hiç pencere yoksa 409 mi, kilit altında mı.

Tasarım: section'da AVAILABLE satırları `FOR UPDATE SKIP LOCKED` (başkasının kilitlediği koltuk bu isteği bloklamaz). `BestSeatPicker` ardışık koşularda, solda veya sağda tam 1 koltuk bırakmayan ilk pencereyi seçer. Pencere yoksa kimse hold edilmez. Seçilen koltuklar aynı transaction'da hold'a bağlanır.

Alternatif: tüm sırayı kilitlemek (adil ama yavaş), Redis lock (TTL kaçarsa çift satış), optimistic version (çok retry).

## Refactor sırası

1. Regression testleri yaz.
2. `createHold` tek transaction + kilit.
3. Confirm'de sahip kontrolü.
4. Expiry'yi status + `Clock`'a bağla.
5. Packing'i saf `BestSeatPicker`'a çıkar, HTTP'siz test et.

Öncelik: H1 ve BOX-203 (çift satış / takılı envanter), sonra BOX-202, BOX-201, feature.
