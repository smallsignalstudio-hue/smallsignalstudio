# GetYourGuide + Atolls — Live Coding Mock Interview Set

Bu repo, GetYourGuide (GYG) ve Atolls'un ikinci teknik aşamasını (paylaşılan repo üzerinde canlı kodlama: **bug fix + feature request + refactor + test**) taklit eden 10 mock mülakat seti içerir.

Gerçek formata dair toplanan tüm raporlar: [`research/LIVE-CODING-REPORTS.md`](research/LIVE-CODING-REPORTS.md).
Seansı nasıl yürüteceğin, süre ve self-rubric: [`PRACTICE-PROTOCOL.md`](PRACTICE-PROTOCOL.md).

## Setler

| # | Görev projesi | Business | Zorluk | Odak |
|---|---------------|----------|--------|------|
| 01 | [`01-activity-catalog`](01-activity-catalog) | GYG katalog + supplier (resmi GYG reposunun kalıbı) | ★★☆☆☆ | NPE root cause, 404/500, JPA, N+1, query'ye taşıma |
| 02 | [`02-ticket-hold-service`](02-ticket-hold-service) | Ticketmaster: koltuk hold / confirm | ★★★★☆ | Eşzamanlılık, transaction sınırları, zaman, yetki |
| 03 | [`03-availability-calendar`](03-availability-calendar) | Slot / kapasite / cutoff | ★★★★☆ | Timezone & DST, aralık sınırları, cache |
| 04 | [`04-pricing-engine`](04-pricing-engine) | Kategori, grup fiyatı, promosyon | ★★★☆☆ | Para hesabı, BigDecimal, yuvarlama, requirement netleştirme |
| 05 | [`05-booking-cancellation-refunds`](05-booking-cancellation-refunds) | İptal politikası, iade | ★★★★☆ | Dual write, idempotency, durum makinesi |
| 06 | [`06-payment-webhooks`](06-payment-webhooks) | PSP webhook'ları | ★★★★★ | At-least-once, sıra dışı olaylar, imza, outbox |
| 07 | [`07-supplier-connectivity`](07-supplier-connectivity) | Supplier API reserve → book → cancel | ★★★★★ | Timeout, retry güvenliği, kompanzasyon, cache |
| 08 | [`08-reviews-ratings`](08-reviews-ratings) | Review & rating | ★★★☆☆ | Aggregate tutarlılığı, pagination, yetki |
| 09 | [`09-atolls-coupon-redemption`](09-atolls-coupon-redemption) | Atolls: kupon / voucher | ★★★★☆ | Unique kullanım, locale, sorgu önceliği |
| 10 | [`10-atolls-cashback-ledger`](10-atolls-cashback-ledger) | Atolls: cashback cüzdan + payout | ★★★★★ | Duplicate olay, ledger, çok replika |

Her set iki klasördür:

```
NN-name/            # Görev: sana "invite" ile gelen repo gibi davran
  INTERVIEW.md      # Business context + setup + biletler + feature + refactor
  docker-compose.yml, Dockerfile, build.gradle, src/ ...
NN-name-solution/   # SPOILER
  SOLUTION.md               # Her bug: semptom, root cause, nasıl bulunur, fix, alternatifler
  FOLLOW-UP-QUESTIONS.md    # Mülakatçının sorabileceği derin sorular + güçlü cevap kriterleri
  src/ ...                  # Düzeltilmiş kod + regression testleri
```

> **Spoiler kuralı:** Bir seti bitirmeden `-solution` klasörünü açma. `FOLLOW-UP-QUESTIONS.md` de bug yerlerini ele verdiği için çözüm klasöründe.

## Hızlı başlangıç (her set için aynı)

Ön koşullar: Java 21, Docker, Docker Compose v2 (Atolls daveti tam olarak bunu istiyor).

```bash
cd 02-ticket-hold-service
docker compose up --build -d      # app + postgres
curl -s localhost:8080/actuator/health
./gradlew test                    # testler Testcontainers kullanır, Docker açık olmalı
```

IDE'den çalıştırmak için: önce `docker compose up -d db`, sonra `./gradlew bootRun` (veya IntelliJ'de `Application` main).

## Önerilen sıra

01 → 04 → 08 → 03 → 02 → 05 → 09 → 06 → 07 → 10

İlk set gerçek GYG reposuna en yakın olandır; ısınma için kullan. Son üç set, senior'ı mid'den ayıran dağıtık sistem konularına (at-least-once, kompanzasyon, ledger) gider.

## Bu repoyu kendi hesabına "invite" gibi kullanmak

Her seti gerçek mülakat gibi yaşamak için tek bir seti ayrı bir private repoya kopyalayıp diğer hesabına invite atabilirsin:

```bash
cp -r 02-ticket-hold-service /tmp/ticket-hold && cd /tmp/ticket-hold
git init && git add . && git commit -m "Initial import"
git remote add origin https://github.com/<hesap>/<repo>.git && git push -u origin main
```
