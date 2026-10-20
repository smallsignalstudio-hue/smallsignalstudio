# 01 — Activity Catalog: Follow-up Questions

Sorular mülakatta soruldukları dilde (İngilizce). Altında **güçlü cevapta olması gerekenler** ve **zayıf cevap kokusu** var.

---

## A. Debugging ve root cause

**A1. "You fixed the NPE in the detail endpoint. How did you make sure the same bug doesn't exist elsewhere?"**
- Güçlü: Mapping'in 3 kopyası olduğunu fark edip hepsini tek mapper'a indirmek; search'in de patladığını testle göstermek; `@NotFound(IGNORE)` olan her ilişkinin nullable olduğunu söylemek; "null'ın anlamı ne?" sorusunu sormak (GYG-operated mı, sync gecikmesi mi?).
- Zayıf: Sadece `if (supplier != null)` eklemek; search'i test etmemek.

**A2. "The logs only said 'Something went wrong: null' for some errors. What would you change in the logging?"**
- Güçlü: Exception'ı ikinci argüman olarak loglamak (stack trace); correlation/trace id (MDC, OpenTelemetry); log seviyeleri; PII loglamamak; ERROR'u alarmla bağlamak.
- Zayıf: `e.printStackTrace()`.

**A3. "In Docker the search logs a StackOverflowError but the response is 200. Locally you don't see it. Why?"**
- Güçlü: Compose'da `LOGGING_LEVEL_COM_GETYOURGUIDE=DEBUG`; `@Data` → `toString` → lazy collection open-in-view sayesinde yükleniyor → karşılıklı recursion; SLF4J formatlama hatasını yutuyor. Fix: entity'de `@Data` kullanmamak, `toString`'den ilişkileri çıkarmak; JPA entity'de `equals/hashCode`'un id tabanlı ya da hiç override edilmemesi gerektiği.
- Zayıf: "Log seviyesini INFO yapalım."

**A4. "What is `spring.jpa.open-in-view` and would you keep it enabled?"**
- Güçlü: Request boyunca session açık → controller/serialization'da lazy load (gizli N+1, uzun connection tutma). Varsayılan true, Spring uyarı loglar. Kapatıp sınırları servis katmanında belirlemek.

## B. JPA / SQL / performans

**B1. "Your list endpoint now does 2 queries. Walk me through why it did 7 before."**
- Güçlü: `@ManyToOne` varsayılan EAGER; `@NotFound` LAZY'yi de EAGER'a zorlar; JPQL/`findAll` ilişkiyi join'lemez → her farklı supplier için ayrı select (persistence context aynı id'yi tekrar çekmez). Fix: `join fetch` / `@EntityGraph`. Count query'nin ayrıca çalıştığını bilmek.
- Zayıf: "Hibernate yavaş."

**B2. "Search uses `lower(title) like '%x%'`. What happens at 300k rows and how would you index it?"**
- Güçlü: Önde `%` olduğu için B-tree kullanılamaz → seq scan. `pg_trgm` GIN index (`gin_trgm_ops`) ya da full-text (`tsvector`) ya da arama motoru. `EXPLAIN (ANALYZE, BUFFERS)` ile doğrulamak. Prefix aramada `text_pattern_ops`.

**B3. "A query is slow in production. How do you investigate?" (Glassdoor Apr 2026)**
- Güçlü: Önce ölçüm (APM, slow query log, `pg_stat_statements`), sonra `EXPLAIN ANALYZE`, index/selectivity/stale statistics, N+1 mi tek query mi, connection pool beklemesi mi; fix sonrası tekrar ölçmek; prod'da `CREATE INDEX CONCURRENTLY`.

**B4. "Offset pagination vs keyset pagination — when would you switch?"**
- Güçlü: Offset derin sayfalarda O(offset), eşzamanlı insert'te kayma; keyset `(rating, id) < (?, ?)` stabil ve index dostu ama "sayfa 37'ye atla" desteklemez. Mobil sonsuz scroll için keyset.

**B5. "`@Transactional(readOnly = true)` — what does it actually do?"**
- Güçlü: Hibernate flush mode MANUAL, dirty checking yok, snapshot tutulmaz; JDBC `setReadOnly` hint'i; replica routing'e kapı. Güvenlik garantisi değil.

**B6. "Why did you change `price` to `NUMERIC` and not just round in Java?"**
- Güçlü: Kaynakta doğruluk; `double` ile toplama/karşılaştırma yanlış; DB aggregate'leri (`min`, `sum`) de doğru olmalı. Migration: `ALTER COLUMN ... USING ROUND(price::numeric, 2)`, büyük tabloda lock süresi ve expand/contract (yeni kolon + backfill + switch).

## C. API tasarımı

**C1. "Why didn't you change `/activities` to return a page object like the statistics endpoint?"**
- Güçlü: Mevcut client'lar (mobil) kırılır. Versiyonlama ya da additive değişiklik (`Link`/`X-Total-Count` header). Kontrat testleri.

**C2. "404 vs 410 vs 200-with-empty for removed activities?"**
- Güçlü: Kalıcı kaldırma için 410 anlamlı; partner'la kontrat; cache header'ları; soft-delete.

**C3. "How do you protect the API from `size=1000000`?"**
- Güçlü: Validation (400), sunucu tarafı max, rate limiting, timeout.

## D. Feature (statistics)

**D1. "A supplier with one 5.0 activity ranks above one with 200 activities at 4.8. Is that what product wants?"**
- Güçlü: Bayesian ortalama `(C*m + Σr) / (C + n)`; minimum review sayısı; booking/gelir verisiyle ranking; A/B test.

**D2. "This endpoint is called by a dashboard every 10 seconds for 5k suppliers. What changes?"**
- Güçlü: Materialized view (`REFRESH CONCURRENTLY`), read model, event-driven sayaç güncellemesi, HTTP cache/ETag; tazelik gereksinimini product'a sormak.

**D3. "Why are prices per currency and not converted to EUR?"**
- Güçlü: Kur zamana bağlı, dönüşüm sorumluluğu ayrı; yanlış "en ucuz" sonucu; gösterim katmanında dönüştürülebilir.

## E. Refactor ve test

**E1. "What did you test first before refactoring and why at HTTP level?"**
- Güçlü: Davranışı kilitlemek; iç yapıya bağlı olmayan testler refactor'u serbest bırakır. Unit testler mapper/escape fonksiyonları için.

**E2. "`@WebMvcTest` vs `@SpringBootTest` + Testcontainers — when which?"** (GYG cheat sheet'inde var)
- Güçlü: WebMvcTest hızlı, sadece web katmanı, servisler mock. SQL/JPA davranışı (NULLS FIRST, LIKE, N+1) için gerçek Postgres şart; H2 farklı davranır (NULL sıralaması, case sensitivity).

**E3. "Why constructor injection?"**
- Güçlü: Immutability, zorunlu bağımlılık, test kolaylığı, döngüsel bağımlılığı startup'ta yakalamak.

## F. JVM / GC (arkadaş raporu — GYG senior turunda sorulmuş)

**F1. "This service runs in a 1 GB container. Which JVM flags would you set?"**
- Güçlü: `-XX:MaxRAMPercentage=75` (container-aware, `-Xmx` sabitlemek yerine), `-XX:+UseG1GC` (varsayılan) ya da düşük latency için `-XX:+UseZGC -XX:+ZGenerational` (Java 21), `-XX:+ExitOnOutOfMemoryError`, `-XX:+HeapDumpOnOutOfMemoryError`, `-Xlog:gc*:file=...`. Metaspace ve thread stack'leri heap dışında; container limitinin tamamını heap'e vermemek.

**F2. "How would you approach GC optimization for this service?"**
- Güçlü: Önce ölçmek (GC log, JFR, p99 latency, allocation rate); hedef (throughput mu pause mu); allocation'ı azaltmak (ör. `findAll()` ile tüm tabloyu çekmek → kısa ömürlü dev nesneler); collector seçimi; heap boyutu; sonra flag tuning. "Flag'lerle başlamam."

**F3. "Could the StackOverflowError have been a GC problem?"**
- Güçlü: Hayır. Thread stack (`-Xss`) taşması, heap değil. Recursion derinliği. OOM ile ayırt etmek.

## G. AI kullanımı (Glassdoor Apr 2026)

**G1. "Use AI to improve this controller. Show me your prompt."**
- Güçlü: Bağlam + kısıt + kabul kriteri: "Refactor `SupplierController` to delegate to a new `SupplierService` and `SupplierRepository` using Spring Data derived queries; keep the existing URL contract; no native SQL; add MockMvc tests for case-insensitive search and SQL injection payloads." Çıktıyı satır satır okumak, testleri çalıştırmak.
- Zayıf: "Improve this code."

**G2. "For which tasks should an engineer not use AI?"**
- Güçlü: Gizli/PII veri ve sırlar; doğrulanamayan güvenlik-kritik kod; ekibin anlamadığı büyük değişiklikler; root cause analizi yerine semptom yaması; lisans riski.
