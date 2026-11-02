# 07 — Supplier Connectivity: Follow-up Questions

## A. Timeouts and retries

**A1. The supplier took 3 seconds and the pool filled up. What did you change first?**
- Güçlü: Connect ve read timeout. 504, thread'i bırak. Timeout bir hata sayılır. Retry'ı timeout'un üstüne eklemek havuzu ikinci kez yer.
- Zayıf: "Thread pool'u büyütürüm." Yavaş tedarikçi daha çok thread tutar.

**A2. POST /bookings returned 500. Why is a second POST the wrong fix?**
- Güçlü: 500, "oluşmadı" demek değildir. İkinci POST ikinci rezervasyon açabilir. Sonuç `UNKNOWN`. Aynı idempotency anahtarıyla sor, varsa bağla, yoksa hold'u bırak.
- Zayıf: "İki kere dene, ikincisi 200 ise tamam." İlk çağrı da yazmış olabilir.

**A3. Reconcile finds nothing, and the supplier creates the booking five minutes later. Then what?**
- Güçlü: Hold'u hemen bırakmak yarışır. `UNKNOWN` bir süre daha sorulur (backoff). Süre dolunca hem iptal çağrısı hem "bizde yok" kaydı. Geç gelen booking için tedarikçi iptalinin kendisi de idempotent olmalı.
- Zayıf: "İlk 404'te RELEASED yaparım." Beş dakika sonraki create sahipsiz kapasite veya çift satış olur.

## B. Capacity and cache

**B1. Payment returns 402. Why is the departure sold out for everyone else?**
- Güçlü: Reserve commit oldu, cancel gitmedi. Ödeme ve tedarikçi aynı transaction'da değil. Reddedilince açıkça cancel. Cancel de timeout olursa hold'u reconcile kuyruğuna yaz.
- Zayıf: "Transaction rollback tedarikçiyi de geri alır." HTTP rollback edilmez.

**B2. Availability failed once and stayed empty. Where is the cache key wrong?**
- Güçlü: `computeIfAbsent` exception'ı ve boş listeyi de saklıyor. Negatif cache, tedarikçi düzelince de boş döner. Hata ve boş sonuç cache'lenmez; dolu cevap kısa TTL ile cache'lenir, anahtarda tarih vardır.
- Zayıf: "Cache'i kapatırım." Asıl hata neyin yazıldığı.

## C. Payloads and time

**C1. HTTP 200 and `errorCode: NO_AVAILABILITY`. Why did we confirm?**
- Güçlü: Başarı kodu gövdede. Adapter bunu domain hatasına çevirir, 409, hold bırakılır. Servis `JsonNode`'a bakmamalı.
- Zayıf: "2xx ise tamam." Bu tedarikçi 200 ile ret dönüyor.

**C2. 2026-10-25 02:30 in Amsterdam. Which instant is the cruise?**
- Güçlü: O gece iki offset var, `+02` ve `+01`. `atZone` erken olanı seçer (`00:30Z`). Ürün sonraki anı istiyor (`01:30Z`), `withLaterOffsetAtOverlap`. Belirsiz saatte 409 demek de dürüst bir sözleşmedir; sessiz erken seçmek değildir.
- Zayıf: "Sunucu Europe/Berlin, aynı gün." Berlin de o gece overlapa girer; tur Amsterdam saatindedir.

## D. Isolation

**D1. After three failures you return 503 without a call. What is missing for production?**
- Güçlü: Half-open: bir deneme çağrısı, başarılıysa kapat. Aksi halde tek bir 500 bütün tedarikçiyi akşama kadar kapatır. Sayaç tedarikçi başına. Bulkhead ayrı: eşzamanlı tavan, devre kesici ardışık hata.
- Zayıf: "Üç hatada bir daha hiç çağırma." Sağlıklı tedarikçi de kilitlenir.

**D2. Two pods hit the same sick supplier. Does the in-memory breaker help?**
- Güçlü: Pod başına state. Üç pod üçer hata daha yer. Paylaşılan sayaç (Redis) veya tedarikçi kotası gerekir. Bulkhead da pod başına; gerçek tavan pod sayısı çarpı izin.

## E. Production and AI

**E1. What do you graph?**
- Güçlü: Tedarikçi başına timeout oranı, 504, açık devre, `UNKNOWN` yaşı, cancel hatası, `errorCode` sayısı. Ortalama latency tek başına yetmez.

**E2. JVM flags for a 1 GB container that blocks on supplier HTTP?**
- Güçlü: `MaxRAMPercentage` ~75, `ExitOnOutOfMemoryError`. Asıl sınır heap değil, timeout'suz HTTP ve pool. Timeout olmadan GC ayarı havuzu kurtarmaz.

**E3. What must a prompt include before an assistant rewrites the book method?**
- Güçlü: "POST /bookings retry yok", timeout, `errorCode` başarı değil, ödeme reddinde cancel, overlap sonraki offset, ve bu altıyı kıran testler. EUR benzeri mutlu yol bunları geçirmez.
- Zayıf: "Book akışını sağlamlaştır, test yeşil kalsın." Retry eklenmesi yeşil mutlu yolu bozmaz.
