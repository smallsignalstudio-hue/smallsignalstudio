# 08 — Reviews: Follow-up Questions

## A. Numbers and races

**A1. Five reviews sum to 23 and the page shows 4. Where do you look?**
- Güçlü: Saklanan ortalama `int` ve Java `sum / count`. 23/5 = 4. Okuma `BigDecimal` scale 1. Yazarken de tamsayıya yuvarlayıp saklamak bir sonraki okumayı yine bozar.
- Zayıf: "Formatı %.1f yaparım." 4.0 yine 4.6 olmaz.

**A2. Eight parallel posts and the count is short. Why did a lock annotation not appear?**
- Güçlü: Read-modify-write. `SELECT` kilitsiz, iki transaction aynı count'u yazar. Düzeltme `UPDATE SET count = count + 1`. `synchronized` ikinci pod'da yok. Beklemeyi silmek yarışı küçültür, bitirmez.
- Zayıf: "`@Version` eklerim, biri 500 yer." Kaybolan puan 500 ile de kayıptır; retry yoksa toplam yine eksik.

## B. Pages and text

**B1. I passed the cursor and saw the same review. What is the cursor really?**
- Güçlü: Offset. Araya yeni satır girince pencere kayar. Cursor son görülen `(created_at, id)` olmalı. `created_at` tek başına eşit zaman damgalarında kararsız.
- Zayıf: "OFFSET'i 1 artırırım." Yeni satır hâlâ kaydırır.

**B2. Fifteen emoji is under 20 and the API says 400. Postgres would have stored it. Why?**
- Güçlü: `String.length()` UTF-16. 👍 iki birim. `codePointCount` ile kolonun karakter sayımı aynı sözleşmede. Limit 20 kod noktası, kolon 80.
- Zayıf: "Emoji'yi yasaklarım." Ürün metni bu.

## C. Who may write

**C1. Customer 1 reviewed customer 2's booking and got 201. What did you check?**
- Güçlü: Header'daki müşteri `booking.customer_id` ile aynı değilse 403, satır yazılmaz. Gateway'in header'ı loglamak yetmez.
- Zayıf: "ID'yi path'ten aldım, demek ki onun." Path'teki booking başkasına ait.

**C2. Why is a future confirmed booking not reviewable, but a past cancelled one also not?**
- Güçlü: İkisi ayrı kural. Başlamamış tur deneyim değil. `CANCELLED` hiç gerçekleşmedi. İkisi de 409, sebep farklı. Saat `Instant` ile, sunucu duvar saati ile değil.

## D. Reads

**D1. The page is correct and the database CPU climbs with page size. What is the query shape?**
- Güçlü: LAZY author ve photo, transaction açık, N+1. `join fetch` veya batch size. İki koleksiyonu aynı anda fetch etmek satırı katlar; photo tek koleksiyon, author many-to-one.
- Zayıf: "Cache koyarım." İlk istek hâlâ N+1.

**E1. A single 5 ranks above a long 4.6. What did you change?**
- Güçlü: Bayes, prior 3.5 ağırlık 5. Ham ortalama az sayıya fazla güvenir. Hide, toplamdan düşmeden listeyi gizlerse Bayes de yalan söyler. Rebuild, projeksiyonu görünür satırlardan yazar.
- Zayıf: "Ortalamaya göre sıralarım." Tek 5 yine öne çıkar.

**E2. JVM flags for a 1 GB container serving this page?**
- Güçlü: `MaxRAMPercentage` ~75, `ExitOnOutOfMemoryError`. Asıl maliyet N+1 ve connection pool. Pool boyutu replika ile çarpılır.

**E3. What do you refuse to let an assistant "simplify" here?**
- Güçlü: Tamsayı bölme, offset cursor, `length()`, sahip kontrolü, atomik sayaç. Mutlu yol tek yorumu okur; ortalama ve yarışı görmez.
- Zayıf: "Test yeşil, ortalama kolonu kalsın." Kolon zaten 4 tutuyor.
