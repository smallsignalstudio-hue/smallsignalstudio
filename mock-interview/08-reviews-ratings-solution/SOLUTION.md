# 08 — Reviews: Çözüm

Yorum ve ortalama. Invariant: ortalama bir ondalık, eşzamanlı yazım puan kaybetmez, sayfa aynı satırı iki kez göstermez, emoji kod noktası sayılır, iptal/gelecek/başkasının booking'i yorum yazamaz, liste tek sorguda gelir.

Kanıt: `regression/BugRegressionTest` görev projesinde kırmızı (okuma testi yeşil), burada 8/8 yeşil. Relevance ve hide yalnız çözümde.

## REV-501 — 4 yerine 4.6

| | |
|---|---|
| Semptom | 5+5+4+5+4 = 23, adet 5. Ekranda 4. |
| Root cause | `average_rating` `int`. `sum / count` Java'da tamsayı bölme. 23/5 = 4. Okuma bu kolonu basıyor. |
| Fix | Toplam ve adet durur. Okuma `BigDecimal` scale 1 HALF_UP. 23/5 = 4.6. |
| Test | `averageKeepsOneDecimal` |

## REV-502 — Sekiz yazımdan biri kalıyor

| | |
|---|---|
| Root cause | Satır okunur, toplam bellekte artar, sonra yazılır. İki transaction aynı `count` değerini görür. Aradaki bekleme pencereyi genişletir; bekleme kalksa da kilit yoksa kayıp güncelleme sürer. |
| Fix | `UPDATE activity_rating SET rating_sum = rating_sum + :stars, rating_count = rating_count + 1`. |
| Test | `concurrentReviewsDoNotLoseUpdates` |

## REV-503 — İkinci sayfa aynı yorum

| | |
|---|---|
| Root cause | `nextCursor` bir offset. Araya daha yeni bir satır girince offset kayar, önceki sayfanın satırı tekrar gelir. `created_at` eşit olan 4 ve 5 de tek başına `ORDER BY created_at` ile kararsızdır. |
| Fix | Cursor `epochMilli:id`. Sonraki sayfa `created_at < t OR (created_at = t AND id < id)`. |
| Test | `nextPageDoesNotRepeatTheRowYouJustSaw` |

## REV-504 — On beş 👍 reddedildi

| | |
|---|---|
| Root cause | `String.length()` UTF-16 birimi sayar. 👍 uzunluğu 2. On beş emoji 30 olur, limit 20. Postgres `varchar` karakter sayar; kolon 80, 15 karakter sığar. |
| Fix | `codePointCount`. |
| Test | `emojiBodyCountsCodePoints` |

## REV-505 — Olmaması gereken yorumlar

| | |
|---|---|
| Root cause | `bookingId` var diye yazıyor. Status, başlangıç anı ve `customer_id` bakılmıyor. |
| Fix | Sahip değilse 403. `CONFIRMED` değilse veya başlangıç hâlâ gelecekteyse 409. |
| Test | `cancelledBookingCannotBeReviewed`, `futureBookingCannotBeReviewed`, `travellerCannotReviewSomeoneElsesBooking` |

## REV-506 — Yazar ve fotoğraf başına sorgu

| | |
|---|---|
| Root cause | Liste sorgusu yorumu çeker. `author` ve `photos` LAZY. Sayfa içinde her satır iki sorgu daha atar. `open-in-view` kapalı olsa da metot `@Transactional` olduğu için session açık kalır ve N+1 çalışır. |
| Fix | `join fetch author left join fetch photos`. |
| Test | `reviewListDoesNotQueryPerAuthor` |

Kırmızı ringa: yıldızın 1 ile 5 arasında olması. O kontrol doğru.

## REV-510 — Bayes ve hide

Skor `(sum + 3.5 * 5) / (count + 5)`. Activity 1 (23/5) activity 2'nin tek 5'inin önüne geçer. `hide` satırı gizler ve toplamdan atomik düşer. `rebuild` görünür yorumlardan toplamı baştan yazar. İkisi de projeksiyonu onarır.

Sınır: hide ile yeni yorum aynı satırda çakışırsa atomik `UPDATE` her ikisini de uygular. Rebuild, projeksiyon şüpheliyse gecelik çalışır; istek yolu ona bağlı değildir.

## Refactor sırası

1. Ortalama testi, tamsayı bölmeyi okumadan çıkar.
2. Atomik sayaç.
3. Keyset cursor.
4. Kod noktası ve yetki.
5. Fetch join.
6. Bayes, hide, rebuild.

Öncelik: REV-505 (yetki), REV-502, REV-501, REV-503, REV-504, REV-506, feature.
