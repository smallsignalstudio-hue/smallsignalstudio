# Canlı Kodlama Aşaması — Toplanmış Raporlar (GetYourGuide + Atolls)

> Derleme: 2026-10-20. Kaynaklar: Glassdoor (UK/US/DE), Blind, Reddit, Medium, JobMentis, Dataford, PracHub, GitHub (resmi GYG repoları ve public kopyaları), getyourguide.careers, atolls.com.
>
> Güven seviyeleri: **BİRİNCİ EL** (aday kendi yazmış / resmi artifact), **İKİNCİ EL** (başkasından aktarım, solution reposu), **DÜŞÜK** (aggregator, AI özet, tek kaynak).

---

## 1. GetYourGuide — format özeti

| Konu | Bulgu | Güven |
|------|-------|-------|
| Repo ne zaman gelir | Mülakattan ~24 saat önce (arkadaş raporu + Glassdoor Feb 2025 "set the repo up beforehand"). Son dönemde mülakat anında açıldığı duyumu var. | BİRİNCİ EL |
| Kim var | 2 kişi: bir peer engineer + bir technical manager | BİRİNCİ EL (Medium, Mar 2026) |
| Görev seti | Bug fix + feature request + refactor (+ unit test) | BİRİNCİ EL (arkadaş, Medium, Reddit) |
| Stack | Java 21, Spring Boot 3.4, Gradle, H2 + Flyway, Lombok, `:8080`, IntelliJ + debugger | BİRİNCİ EL (repo kopyası) |
| AI | İzinli. Glassdoor Apr 2026: "controller'ı AI ile iyileştir — prompt'ların generic olmasın, her şeye test ekle" | BİRİNCİ EL |
| Süre sinyali | Debug görevi için ~10 dk bekleniyor; uzarsa feature/refactor yarım kalıyor | BİRİNCİ EL (Medium feedback) |
| Sonraki tur | System design (ticket service / Ticketmaster benzeri) | BİRİNCİ EL + İKİNCİ EL |

## 2. GetYourGuide — resmi coding interview reposu (`swe-be-coding-interview`)

Resmi repo (`github.com/getyourguide/swe-be-coding-interview`) artık 404/private. İki public kopya üzerinden doğrulandı:

- [Sandip1509/swe-be-coding-interview](https://github.com/Sandip1509/swe-be-coding-interview) — Şubat 2025 snapshot
- [raghava219/swe-be-coding-interview-solution](https://github.com/raghava219/swe-be-coding-interview-solution) — Mart 2026, aday çözümü + `cheat-sheet.html`

**Domain:** `Activity` (title, price, currency, rating, specialOffer, supplier) ve `Supplier`; seed: Berlin turları.
**Endpoint'ler:** `/activities`, `/activities/{id}`, `/activities/search/{search}`, `/suppliers`, `/suppliers/search/{search}`.

**Kodda doğrulanan gömülü sorunlar:**

1. **Null supplier** — Activity 40882 `supplier_id=5`, supplier 5 yok. `@NotFound(IGNORE)` ile supplier `null` olur → `getActivities(id)` ve `searchActivities` NPE. Sadece liste endpoint'i null check yapıyor. (Medium "null supplier" + Glassdoor Feb 2025 "an api was giving null pointer" ile örtüşüyor.)
2. **Missing id → 500** — `result.get(0)` boş listede patlar, catch-all `@ExceptionHandler(Exception)` her şeyi 500 "Something went wrong" yapar.
3. **findAll + bellekte filtre** — Medium feedback'teki "logic'i repository'ye taşı" refactor'u.
4. **Kopyala-yapıştır DTO mapping** — `forEach` içinde side-effect ile üç kez.
5. **Katman ihlali** — `ActivityService`, `SupplierController` inject ediyor.
6. **Controller'da data access** — `SupplierController` `EntityManager` + native `SELECT *`.
7. **Supplier search** — case-sensitive, ilk eşleşmeyi döndürüyor, eşleşme yoksa *tüm* supplier'ları döndürüyor.
8. **Anotasyon/tip** — `@Controller` vs `@RestController`; şemada `FLOAT` fiyat, entity'de `int`.
9. **Recursive toString** — çift yönlü `@Data` entity.
10. **Zayıf testler** — `SupplierControllerTest` tamamen boş.

**2026 versiyonunda (ikinci el) eklenenler:**
- `StatisticsController.getSupplierStats()` stub (`SELECT *` → `Object[]`). Muhtemel feature: supplier başına count, sum(price), avg(rating) — JPQL `GROUP BY` → DTO.
- `searchActivities` `activitiesDto`'yu dolduruyor ama boş `result` döndürüyor (copy-paste bug).
- Checked `ActivityNotFoundException` lambda içinde fırlatılamıyor.
- `@PathVariable` üzerinde ölü null check.
- Cheat sheet'teki follow-up'lar: N+1 → `LEFT JOIN FETCH`, `@Transactional(readOnly=true)`, `@WebMvcTest` vs `@SpringBootTest`, `MockMvc` vs controller'ı doğrudan çağırmak, `GenerationType.AUTO` vs `IDENTITY`, primitive vs wrapper.

## 3. GetYourGuide — aday raporları (kronolojik)

| Tarih | Seviye | Aşama | Sorulan / yaşanan | Güven | Kaynak |
|-------|--------|-------|-------------------|-------|--------|
| Dec 2022 | Backend Engineer | Codility → live coding | Codility sorting; live coding: "Code refactoring, SOLID, OOP" | BİRİNCİ EL | [Glassdoor Backend UK](https://www.glassdoor.co.uk/Interview/GetYourGuide-Backend-Engineer-Interview-Questions-EI_IE695237.0,12_KO13,29.htm) |
| Nov 2022 | Mid Backend | Codility → technical | "They concentrate on code refactoring" | BİRİNCİ EL (özet) | [Blind](https://www.teamblind.com/company/GetYourGuide/posts) |
| Sep 2023 | Senior SWE | Take-home → tech | "The take home challenge can be found on their GitHub page" | BİRİNCİ EL | [Glassdoor Senior p3](https://www.glassdoor.co.uk/Interview/GetYourGuide-Senior-Software-Engineer-Interview-Questions-EI_IE695237.0,12_KO13,37_IP3.htm) |
| Feb 2024 | Senior SWE | Take-home | "Build a dockerized fullstack app from scratch, write unit tests for everything and also add e2e tests. Timeline: 7 days." | BİRİNCİ EL | Glassdoor Senior p3 |
| Jan 2025 | SWE | Build + present | ~5 saat backend+frontend app, sonra sunum + canlı değişiklik | BİRİNCİ EL | [Glassdoor SWE p2](https://www.glassdoor.co.uk/Interview/GetYourGuide-Software-Engineer-Interview-Questions-EI_IE695237.0,12_KO13,30_IP2.htm) |
| Feb 2025 | Senior SWE | Live coding (repo) | Repo'yu önceden kurdu; NPE fix; "another question where I had to use maps"; "Test cases were also important" | BİRİNCİ EL | Glassdoor Senior |
| Mar 2025 | Senior SWE | Live coding (repo) | Repo "can be found on their Github page"; Java Streams, Lambdas, Optional, HashSet, Maps, Spring Boot, DB indexing, query performance | BİRİNCİ EL | Glassdoor Senior |
| Apr 2025 | Senior SWE | Codility | "String operations and manipulation" — skorla elendi | BİRİNCİ EL | [Glassdoor Senior p2](https://www.glassdoor.co.uk/Interview/GetYourGuide-Senior-Software-Engineer-Interview-Questions-EI_IE695237.0,12_KO13,37_IP2.htm) |
| ~May 2025 | Senior SWE | Live coding (repo) | Repo 1 gün önce; bug fix + feature request + refactor; sonra GC optimizasyonu ve JVM argümanları sohbeti | BİRİNCİ EL | Arkadaş notları |
| ~May 2025 | Senior SWE | System design | High-level, Ticketmaster benzeri booking | BİRİNCİ EL | Arkadaş notları |
| Aug 2025 | Java SWE | Live coding (repo) | "fixing a bug and discussing the solution… controller, service, and repository… you can use AI" | BİRİNCİ EL | [Reddit](https://www.reddit.com/r/cscareerquestionsEU/comments/1mwmeij/getyourguide_java_interview_experience/) |
| Jan 2026 | SWE | Pair programming | "implement some features in java repo"; iletişim ve soru sorma puanlanıyor | BİRİNCİ EL | Glassdoor SWE |
| Feb 2026 | Senior SWE | Coding assessment | Süreç onayı, soru metni yok | BİRİNCİ EL | Blind |
| Mar 2026 | Senior SWE Java (Berlin) | HackerRank OA | Event Registration System | BİRİNCİ EL | [Medium — Veenarao](https://levelup.gitconnected.com/getyourguide-senior-software-engineer-java-interview-guide-berlin-b3a0c861e218) |
| Mar 2026 | Senior SWE Java (Berlin) | Live coding (repo) | Issue bul + refactor + unit test + yeni endpoint. Bug'lar entity/service/controller/exception handler'da. **Null supplier** — aday yanlış null check'e takıldı. Refactor'u sadece "repository'ye taşı" demek yetmedi, detaylı implementation istendi. | BİRİNCİ EL | Medium |
| Apr 2026 | Senior SWE | HackerRank (7 gün) | "Create an Event Registration System. Implement methods addEvent(eventId, eventType, capacity, requirements), registerParticipant(eventId, participantId, email, skillLevel), cancelRegistration(eventId, participantId), and findRegisteredEvents(participantId)." | BİRİNCİ EL | [Glassdoor Senior](https://www.glassdoor.co.uk/Interview/GetYourGuide-Senior-Software-Engineer-Interview-Questions-EI_IE695237.0,12_KO13,37.htm) |
| Apr 2026 | Senior SWE | Live coding (repo) | "Suppose a query is not performing well in production. How would you investigate, troubleshoot & fix it?" · "Hit an endpoint of a REST Api, you get 500 error, Troubleshoot & fix it." · "Use some AI, to improve the code of the controller class (… prompts not too generic … always add test cases)" · "Modify the code, so that this method does not return rows with duplicate value in column1." · AI soruları: "For which tasks an engineer should not use AI?", "mentored a colleague regarding the use of AI?" | BİRİNCİ EL | Glassdoor Senior |
| May 2026 | Associate SWE | Live coding (repo) | "shared a frontend javascript/vue codebase with bugs… implementing a feature or fixing a bug" | BİRİNCİ EL | [Reddit Associate](https://www.reddit.com/r/cscareerquestionsEU/comments/1t9aev9/getyourguide_associate_software_engineer/) |
| ? | — | — | "Refactor this piece of code, which handles booking cancellations… within policy, outside policy, partial refunds" | DÜŞÜK | [JobMentis](https://www.jobmentis.com/fr/interviews/getyourguide/swe) |
| ? | Senior | Coding | EventEmitter `on/emit/off` | DÜŞÜK | AmbitionBox |

**Diğer roller:**
- **Android / iOS** — resmi [android-challenge](https://github.com/getyourguide/android-challenge) ve [ios-challenge-refactor](https://github.com/getyourguide/ios-challenge-refactor): "There is a simple app that isn't made with best practices in mind and works perfectly in the happy path… pair programming session… improve and fix some issues… *As a potential traveler, I want to be able to fetch reviews for one of our most popular Berlin tours.*" Kod kokuları: `GlobalScope.launch`, ViewModel/error state/pagination yok, hardcoded `offset=0&limit=60`, kırılgan `LocalDateTime.parse`.
- **Frontend** — Blind (Feb 2025) bir GYG çalışanı: "We never post pure frontend roles" → full-stack beklenti.
- **Data Engineer** (Feb 2024, Mar 2025) — SQL take-home, data modeling, pipeline, data quality.
- **Engineering Manager** (Jan 2025) — system design + CTO; coding yok.
- **Staff** — rapor bulunamadı.
- **Resmi süreç sayfaları:** [How we hire](https://getyourguide.careers/how-we-hire), [Recruiting 101](https://www.getyourguide.careers/posts/recruiting-101-our-recruiting-process-for-engineers) ("codility or codeshare"), [Top 15 questions](https://getyourguide.careers/posts/the-top-15-questions-candidates-ask-during-the-hiring-process) ("Take-home task → CodeLive tech interview → Technical screen → Pool owner screen").

**GC / JVM:** Public bir rapor yok; tek kaynak arkadaş notu. Yine de her follow-up dosyasında bu eksen var.

## 4. Atolls (eski Global Savings Group)

| Tarih | Seviye | Aşama | Bulgu | Güven | Kaynak |
|-------|--------|-------|-------|-------|--------|
| 2026 (senin davetin) | Senior SWE | Technical (live case) | "pair programming session on a shared codebase… The team will share a project with you on GitHub (please be ready to receive an invite…) … have Java21, docker and docker-compose already set up in advance." | BİRİNCİ EL | Davet e-postası |
| Resmi | — | Süreç | TA call → "Technical Round… (Through a Live Case), and meet your potential manager" → final (1–3 görüşme). "no need for prep or polished presentations" | BİRİNCİ EL | [atolls.com/how-we-hire](https://atolls.com/careers/how-we-hire/) |
| Aug 2023 | Software Developer (Munich) | Live task | "why this react components are rerendering each time" — bilerek dağınık kod, yanıltıcı ipuçları | BİRİNCİ EL | [Glassdoor Atolls SE](https://www.glassdoor.co.uk/Interview/atolls-Software-Engineer-Interview-Questions-EI_IE1790973.0,6_KO7,24.htm) |
| Feb 2023 | SWE II (Paris) | Take-home + live | "simple rest API coding task at home", sonra 1 saat teknik sohbet, culture fit | BİRİNCİ EL | Glassdoor |
| Aug 2021 | SE (Munich) | Tech lead | Basit → ileri sorular + coding challenge; "how/why it works" | BİRİNCİ EL | Glassdoor |
| Oct 2024 | London | 3 teknik tur | Detay yok | BİRİNCİ EL | Glassdoor |
| May 2026 | Senior SWE (Munich, offer) | — | HR, system design, behavioural | BİRİNCİ EL | Glassdoor |
| Jul 2026 | Senior Frontend | — | "so many css related questions" | BİRİNCİ EL | Glassdoor |
| ? | — | — | Performance bottleneck debug, karmaşık JSON'dan alan çıkarma, LRU cache | DÜŞÜK | PracHub |

**Stack (iş ilanları):** Java 17+/21, Spring Boot (WebFlux), GraphQL (federation/supergraph), MySQL + DynamoDB/MongoDB, AWS (EventBridge, SQS/SNS, Elasticsearch), Kubernetes, event-driven. Bir kısım ekipte TypeScript/NestJS.
**Business:** Kupon, cashback, deals, voucher topluluğu — mydealz, hotukdeals, Dealabs, iGraal, Shoop, Cuponation, Pouch. 20+ market.
**Repo/docker:** `atolls` ve `global-savings-group` GitHub org'larında public repo yok. Docker ile çalıştırılan repo görevine dair public sızıntı yok → bu setteki 09 ve 10 projeleri domain'den türetilmiştir.

## 5. Tekrarlayan kalıplar (setin tasarımı bunlara göre)

**Bug kategorileri**
- Nullable / yetim ilişki → NPE (favori). Aday yanlış katmanda null check ekleyip root cause'u kaçırıyor.
- "Not found" → catch-all handler ile 500.
- Copy-paste / yanlış değişken döndürme.
- Case-sensitive arama, yanlış fallback.
- Lombok `@Data` / `equals` / `toString` recursion.
- Tip uyumsuzluğu (FLOAT vs int, para için double).
- Bellekte filtreleme, N+1, index eksikliği.

**Feature tipleri**
- Yeni endpoint (istatistik, arama), `GROUP BY` → DTO.
- Duplicate eleme ("column1'de duplicate olmasın").
- Map ile gruplama.
- Fix + testi.

**Refactor tipleri**
- Filtreyi repository/JPQL'e taşı (`findAll` yok).
- Controller→service/controller coupling'i kaldır.
- Mapper çıkar, specific exception handler + doğru status kodları.
- RESTful controller, SOLID.

**Sohbet / follow-up**
- Log'dan debug, yavaş query + index, N+1, `@Transactional(readOnly)`.
- Streams, Optional, collections.
- AI ile prompt yazma + test; AI ne zaman kullanılmaz.
- GC / JVM flag'leri.
- Zaman yönetimi.

## 6. GYG iş alanları (engineering blog) → set konuları

| Alan | Kaynak | Setteki proje |
|------|--------|---------------|
| Katalog, supplier | resmi coding repo | 01-activity-catalog |
| Booking / ticket / cart (BoxOffice) | [BoxOffice migration](https://getyourguide.careers/posts/boxoffice-migration) | 02-ticket-hold-service |
| Inventory & availability | [Kafka Streams inventory](https://www.getyourguide.careers/posts/scaling-the-inventory-parts-of-our-system-using-kafka-streams), [microservice boundaries](https://getyourguide.careers/posts/from-distributed-monolith-to-clear-boundaries-redesigning-microservices-at-getyourguide) | 03-availability-calendar |
| Pricing (tiered, kategori) | [Supplier API](https://integrator.getyourguide.com/documentation/overview) | 04-pricing-engine |
| Cancellation / refund | JobMentis sinyali, Supplier API cancel | 05-booking-cancellation-refunds |
| Payments | [Payments department](https://www.getyourguide.careers/posts/a-journey-through-the-payments-department-at-getyourguide-2) | 06-payment-webhooks |
| Supplier connectivity (reserve/book/cancel) | [Supplier API](https://integrator.getyourguide.com/documentation/overview) | 07-supplier-connectivity |
| Reviews | android/ios challenge | 08-reviews-ratings |
| Kupon / voucher (Atolls) | atolls.com | 09-atolls-coupon-redemption |
| Cashback (Atolls) | atolls.com | 10-atolls-cashback-ledger |
