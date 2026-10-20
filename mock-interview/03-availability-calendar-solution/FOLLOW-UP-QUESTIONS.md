# 03 — Availability Calendar: Follow-up Questions

## A. Time zones

**A1. Why did 10:00 become 11:00 on 29 March 2026?**
- Güçlü: Europe/Berlin o gün 02:00'de 03:00'e atlar. `Instant + 24h` yerel saati korumaz. Her tarih için `LocalTime` yeniden kurulur.
- Zayıf: "24 saat eklemek her zaman bir gündür." DST gününde değildir.

**A2. The tour is 22:00–02:00. Which date owns it?**
- Güçlü: Başladığı yerel gün. Bitişin tarihini başlangıca yapıştırmak ertesi günü üretir.
- Zayıf: "Bitiş gününe yazalım, gece yarısı daha doğal." Ürün kuralı başlangıç günü.

**A3. Cutoff for a Tokyo activity is wrong on a UTC server. Where do you measure it?**
- Güçlü: `Instant` farkı zone'dan bağımsızdır. Duvar saatini `systemDefault` ile tekrar zone'lamak Tokyo 22:00'ı UTC 22:00 yapar.
- Zayıf: "Her şeyi Berlin'e çeviririm." Aktivite kendi zone'unda yaşar.

**A4. `from` and `to` are dates. Inclusive or exclusive?**
- Güçlü: İkisi de inclusive, aktivite zone'undaki takvim günü. `isBefore(to.atStartOfDay())` son günü ve tek günü düşürür.
- Zayıf: "SQL BETWEEN gibi" deyip zone söylememek.

## B. Capacity and cache

**B1. A cancelled party of 4 still blocks seats. What did you query?**
- Güçlü: `sum` status bakmıyor. Filtre `CONFIRMED` (veya `<> 'CANCELLED'`). İptal havuzu da geri vermeli.
- Zayıf: Seed satırını silmek.

**B2. Why did August return July's slots?**
- Güçlü: Cache key sadece `activityId`. Aralık key'de olmalı. Booking'den sonra entry yaşamamalı.
- Zayıf: "Cache'i kapatırız." On-sale'de asıl soru neyin invalidate edildiği.

**B3. Two activities share one boat. What is `remaining`?**
- Güçlü: `min(aktivite kapasitesi - kendi confirmed, havuz - örtüşen confirmed)`. Örtüşme `[start, end)`. Kardeş aktivitenin cache'i de düşer.
- Zayıf: Sadece kendi `activity_id` toplamı.

**B4. How stale may the calendar be during on-sale?**
- Güçlü: Kalan koltuk cache'leniyorsa yazma anında evict, yoksa kısa TTL + tek kaynak DB. Tercihen cache'lenen şey kapasite değil katalog metadata'sıdır.
- Zayıf: "5 dakika TTL yeter" deyip çift satış senaryosunu geçmek.

## C. Data model

**C1. Why store `slot_start` as `timestamptz`?**
- Güçlü: Mutlak an. Yerel saat aktivite satırında. DST gecesi tekrarlanan yerel saat iki `Instant` olur.
- Zayıf: `timestamp without time zone` ve sunucu zone'una güvenmek.

**C2. How do you index the overlap query?**
- Güçlü: `(resource_id, slot_start)` ve aktivite süresi. `EXPLAIN` ile örtüşen aralık. Çok yazmada slot satırı + `FOR UPDATE`.
- Zayıf: Her istekte tüm booking tablosunu taramak.

## D. Refactor and tests

**D1. What is pure in `ScheduleExpander`?**
- Güçlü: Girdi `(activity, from, to)`, çıktı `List<ZonedDateTime>`. Clock ve DB yok. DST ve inclusive parametrik test.
- Zayıf: Expander'ın içinde repository çağırmak.

**D2. The cutoff uses `isAfter`, so the exact cutoff instant is still open. Bug?**
- Güçlü: Hayır. Kural kalkıştan *önce* kapanmak; eşitlik kabul. Asıl bug zone'u yeniden yorumlamak.
- Zayıf: Eşitsizliği çevirip zone'u bırakmak.

## E. Production

**E1. Two app replicas, one cache. What breaks?**
- Güçlü: Local cache diğer JVM'i güncellemez. Paylaşılan cache (Redis) ve eviction yayını, ya da cache'i kalan koltuk için kullanmamak.
- Zayıf: "Her node'un kendi cache'i tutarlıdır."

**E2. Which JVM flags in a 1 GB container?**
- Güçlü: Container-aware heap (`-XX:MaxRAMPercentage`), G1 varsayılan, OOM'da heap dump, timezone data güncel (`tzdb`) çünkü DST kuralı JDK ile gelir.
- Zayıf: Sabit `-Xmx4g` ve TZ'yi umursamamak.

**E3. Where do you not trust an AI edit here?**
- Güçlü: Zone ve cache key'i. Üretilen testi DST günü ve iptal satırıyla kendim koşarım. AI `plusHours(24)` önerebilir.
- Zayıf: "Derleniyorsa doğrudur."
