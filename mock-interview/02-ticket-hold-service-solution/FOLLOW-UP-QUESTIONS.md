# 02 — Ticket Hold: Follow-up Questions

## A. Concurrency

**A1. Two requests read AVAILABLE and both update. Why does the second not fail?**
- Güçlü: READ COMMITTED'da satır kilidi yoksa iki UPDATE de yazar, son yazan kazanır, ikisi de 201. Çözüm `SELECT FOR UPDATE` veya `UPDATE ... WHERE status='AVAILABLE'` ve etkilenen satır sayısı.
- Zayıf: "synchronized eklerim." İki replikada işe yaramaz.

**A2. Why lock seats ORDER BY id?**
- Güçlü: A tutar 1 sonra 2, B tutar 2 sonra 1 → deadlock. Her yerde aynı sıra.

**A3. SKIP LOCKED vs bekleyen FOR UPDATE, on-sale anında?**
- Güçlü: Beklemek tüm kuyruğu bir satıra bağlar. SKIP LOCKED başka koltuğa geçer; hiç koltuk kalmazsa 409. Açlık riskini ve "best"in kilit yüzünden ikinci tercihe düşeceğini söylemek.

**A4. Would optimistic locking (`@Version`) be enough?**
- Güçlü: Çakışmada retry gerekir, 4 koltuklu hold'da kısmi başarı yine transaction ister. Yüksek çakışmada pessimistic daha öngörülebilir.

## B. Transactions

**B1. `reserveSeat` is `@Transactional`. Why did 3 seats stay held?**
- Güçlü: Self-invocation, proxy yok, her `saveAndFlush` ayrı commit. Sınıfın üstüne `@Transactional` koymak da yetmez eğer içeride yakalanıp yutulursa.

**B2. What isolation level do you need for "no double sell"?**
- Güçlü: READ COMMITTED + conditional update veya row lock yeterli. SERIALIZABLE de doğru ama retry zorunlu.

## C. Time

**C1. App is Europe/Berlin, Postgres is UTC. Where is the 10 minutes measured?**
- Güçlü: `LocalDateTime` zone tutmaz. Yazılan duvar saati ile `now() AT TIME ZONE 'UTC'` farklı saatler. `Clock` + tek zone, ya da `timestamptz`.

**C2. What happens on the DST fall-back night?**
- Güçlü: LocalDateTime o gece 2:30'u iki kez yaşar. Instant/OffsetDateTime kullanılmalı. TTL duration olarak Instant'a eklenmeli.

## D. Security and model

**D1. How do you know confirm is not an IDOR?**
- Güçlü: Header'daki müşteri ile `hold.customer_id` karşılaştırılır, 403, koltuk SOLD olmaz. Gateway header'ını loglamak yetmez.

**D2. Should a confirmed hold keep `expires_at`?**
- Güçlü: Evet, audit için. Expiry job status'e bakmak zorunda. Satılmış koltuğu süre ile serbest bırakmak gelir kaybı.

## E. Feature

**E1. Row of 3 available seats, party of 2. What do you return?**
- Güçlü: İkisini de reddet. Hangi ucu alırsan al tek koltuk kalır. Product'a "o zaman 3 mü satalım" diye sormak.

**E2. How do you test the packing rule without a database?**
- Güçlü: Saf fonksiyon, ardışık olmayan numaralar ayrı koşu, kenarda 0 kalan serbest, 1 kalan yasak.

## F. Production

**F1. Two app replicas run the expiry job. What breaks?**
- Güçlü: İkisi de aynı UPDATE'i yaparsa sonuç idempotent olmalı (status şartı). Yarışta çift release zararsızsa sorun yok; side effect (e-posta) varsa outbox.

**F2. JVM flags for a 1 GB container on sale night?**
- Güçlü: `MaxRAMPercentage` ~75, G1 veya ZGC, `ExitOnOutOfMemoryError`. Asıl sorun GC değil, connection pool ve kilit beklemeleri. Pool boyutu replika sayısı ile çarpılır.

## G. AI

**G1. What must the prompt for this fix include?**
- Güçlü: "Tek transaction, koltuklar id sırasında kilitlenecek, 4. koltuk satılmışsa 1–3 de AVAILABLE kalacak, bunu Testcontainers testi kanıtlasın." "Fix the bug" yetmez.
