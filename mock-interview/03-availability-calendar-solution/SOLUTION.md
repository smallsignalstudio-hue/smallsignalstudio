# 03 — Availability Calendar: Çözüm

Slotlar aktivitenin zone'unda üretilir. `from`/`to` inclusive, kalkış saati DST'de kaymaz, gece yarısını geçen tur başlangıç gününe yazılır, `CANCELLED` kapasite yemez, cutoff aktivite zone'undadır, booking cache'i düşürür.

Kanıt: `regression/BugRegressionTest` görev projesinde 7/7 kırmızı, burada yeşil.

## AVL-301 — Tek gün boş, aralığın son günü yok

| | |
|---|---|
| Semptom | `from=to=2026-06-01` boş. `to=2026-03-30` o günü içermez. |
| Nasıl bulunur | Debugger'da `cursor` ve `end`. `end = to.atStartOfDay(server zone)`, döngü `isBefore`. |
| Root cause | Bitiş günün başı, sunucu zone'unda. Kalkış o andan sonraysa döngü hiç girmez. Son gün exclusive. |
| Tuzak | Sadece `to.plusDays(1)` eklemek. Zone hâlâ sunucu zone'u; Tokyo kayar. |
| Fix | `for (day = from; !day.isAfter(to); day++)` ve `ZonedDateTime.of(day, localTime, activityZone)`. |
| Test | `singleDayAndRangeEndAreInclusive` |

## AVL-302 — 29 Mart kalkışı 11:00

| | |
|---|---|
| Root cause | Gün adımı `Instant.plus(24h)`. Berlin 2026-03-29 ilkbahar geçişinde 24 saat sonrası yerel saat 11:00 olur. |
| Fix | Her takvim günü için yerel saati yeniden kur. `ZonedDateTime` geçişi kendisi çözer; 02:30 gibi olmayan saatler ayrı ele alınır. |
| Test | `departureStaysAtTenOnDstSpringForward` |

## AVL-303 — Tokyo yürüyüşü ertesi gün, iptal hâlâ dolu

| | |
|---|---|
| Root cause | Bitişin yerel tarihi ile başlangıç saati birleştiriliyor. 22:00+4s = ertesi gün 02:00, slot 2 Haziran 22:00 diye etiketleniyor. Kapasite `sum(partySize)` ve status filtresi yok; CANCELLED 4 kişiyi düşüyor. |
| Fix | Slot başlangıç gününde kalır. Toplam sadece `status <> 'CANCELLED'`. |
| Test | `overnightTourStaysOnTheStartDate`, `cancelledBookingDoesNotConsumeCapacity` |

## Gizli

| # | Bulgu | Fix | Test |
|---|---|---|---|
| H1 | `@Cacheable` key yalnızca `activityId`. Temmuz cevabı Ağustos isteğine gider. | Key `activityId + from + to` | `differentRangesAreNotServedFromTheSameCacheEntry` |
| H2 | Booking cache evict etmiyor. Kalan koltuk eski kalır. | `@CacheEvict(allEntries = true)` (havuz paylaşımı da bunu gerektirir) | `bookingChangesTheRemainingCount` |
| H3 | Cutoff, aktivite yerel duvar saatini `ZoneId.systemDefault()` ile yeniden yorumluyor. Tokyo 22:00, UTC sunucuda 22:00Z sanılıyor. | `slotStart` bir `Instant`; cutoff `start.minus(cutoffHours)`. | `tokyoCutoffUsesTheActivityZone` |

Kırmızı ringa: cutoff karşılaştırması `isAfter` (tam sınırda hâlâ açık). Kural "kalkıştan önce kapanır"; eşitlik anı bilinçli olarak kabul. Seed'deki `+02` de doğru: Haziran'da Berlin CEST.

## AVL-310 — Paylaşılan tekne

Netleştirme: havuz mu aktivite kapasitesi mi bağlar, çakışma kapalı aralık mı, iptal havuzu geri verir mi, cache diğer aktiviteyi de düşürür mü.

Tasarım: aktivite kapasitesi ve `resource_pool` ayrı. Çakışan CONFIRMED booking'lerin party toplamı havuzdan düşülür. Kalan = `min(aktivite kalan, havuz kalan)`. Aralık `[start, end)` örtüşmesi. Booking tüm `slots` cache'ini siler; aksi halde kardeş aktivite eski kalanı gösterir.

Alternatif: slot bazında materialized occupancy (hızlı okuma, yazmada kilit), Redis (TTL ile kayıp riski).

## Refactor

1. Regression testleri sabitle.
2. Üretimi `ScheduleExpander.departures(activity, from, to)` saf fonksiyonuna taşı. DST, inclusive, overnight burada.
3. "Şimdi" yalnızca `Clock`.
4. Kapasiteyi `confirmedPartyAt` + `resourceUsage` sorgularına indir.

Öncelik: AVL-303 kapasite ve H2 (yanlış stok), sonra AVL-301/302, H3, feature.
