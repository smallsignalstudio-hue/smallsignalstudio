# 09 — Coupons: Follow-up Questions

## A. Codes

**A1. The database has `INDIRIM` and the shopper typed ` indirim `. Why 404?**
- Güçlü: Türkçe locale'de `i` üstün `İ` olur. `Locale.ROOT` ve `trim`. Karşılaştırma normalize edilmiş kolonla yapılır, her isteği farklı locale ile büyütmek yetmez.
- Zayıf: "`equalsIgnoreCase` yeter." Türkçe `i` yine ayrılır, boşluk da durur.

**A2. How do you mint a million codes without collisions or ambiguous characters?**
- Güçlü: `SecureRandom`, alfabe `0/O/1/I/L` içermez, unique index, çakışmada yeniden dene, batch insert, job kaldığı `generated` değerinden devam eder. `Random` tahmin edilir.
- Zayıf: "`UUID.randomUUID()` basarım." Kullanıcı o kodu yazamaz, çakışma stratejisi yoktur.

## B. Time and scope

**B1. It is the morning of `valid_until` and redeem returns 409. What is the comparison?**
- Güçlü: `isBefore` son günü dışarıda bırakır. Gün, kampanyanın zone'unda. UTC 22 Ekim iken Honolulu hâlâ 21 Ekim olabilir.
- Zayıf: "Sunucu gününü alırım." Market günü başka.

**B2. The user redeemed a French code and cannot redeem a German one. The limit is 1 per campaign. Why?**
- Güçlü: Sayım `user_id` üzerinden, `campaign_id` yok. Limit kampanyaya aittir.
- Zayıf: "Limiti 2 yaparım." Sonraki kampanya da kapanır.

## C. Concurrency and SQL

**C1. Two requests redeemed `RACE`. What is the statement that makes it one?**
- Güçlü: `UPDATE ... WHERE redeemed_by IS NULL` ve etkilenen satır. Unique index ikinci insert'i de tutar. `synchronized` ikinci instance'ta yok. Okuyup yazmak yetmez.
- Zayıf: "Önce select, null ise save." İkisi de null görür.

**C2. Why does `market = ? OR global AND active` show a closed German row and a 2020 global?**
- Güçlü: `AND` daha sıkı bağlanır. Market eşleşmesi active'i ezmez. Global active satırda tarih yoktur. Doğru şekil `(market OR global) AND active AND validUntil`.
- Zayıf: "Parantez ekledim, bitti." Tarih hâlâ yoksa 2020 görünür.

## D. Production

**D1. Five bad guesses then 429. What do you add before this is real?**
- Güçlü: Sayaç kullanıcı ve IP için, pencereli (dakika), pod'lar arasında paylaşılan. Başarı sıfırlar. Sadece process içi map ikinci pod'da baştan başlar.
- Zayıf: "5'ten sonra kodu silerim." Kaba kuvvet meşru kullanıcıyı da kilitler, pencere yok.

**D2. The job died at 400k of 1M. How do you resume?**
- Güçlü: Unique kodlar duruyor. Job `generated` kadarını atlar, kalanı basar. İkinci tur aynı kodu tekrar insert etmez. Dosyaya `readAllBytes` ile 1M satır almak heap yer; stream ve batch.

**D3. JVM flags for a 1 GB container minting codes?**
- Güçlü: `MaxRAMPercentage` ~75, `ExitOnOutOfMemoryError`. Asıl risk batch'i bellekte büyütmek. `SecureRandom` bloklarsa `/dev/urandom` ve havuz ayrı konuşulur.
