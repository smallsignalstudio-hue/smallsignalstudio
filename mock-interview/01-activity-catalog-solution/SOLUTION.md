# 01 — Activity Catalog: Çözüm

> Bu set gerçek GYG `swe-be-coding-interview` reposunun kalıbını takip eder (Activity/Supplier, null supplier, catch-all 500, bellekte filtre, controller→controller bağımlılığı). Isınma setidir ama "senior" sinyali, semptomu değil root cause'u ve **aynı hatanın başka nerede olduğunu** bulmaktan gelir.

Kanıt: `src/test/java/.../regression/BugRegressionTest.java` sadece HTTP üzerinden çalışır. Görev projesine kopyalandığında 13 testin 13'ü kırmızı, burada hepsi yeşil.

## Business invariant'ları (ilk 5 dakikada söylenmesi gerekenler)

1. Bir aktivite, supplier'ı henüz senkronize olmamış olsa bile **satılabilir ve görüntülenebilir** olmalı. İki pipeline bağımsız, schema'da bilerek FK yok.
2. Supplier'sız aktivite (GYG-operated) geçerli bir durum. `null` supplier her zaman bir hata değil.
3. Liste stabil olmalı: mobil uygulama sayfalıyor, aynı ürün iki sayfada görünmemeli ya da kaybolmamalı.
4. Fiyat paradır: müşteriye `58.410000000000004 €` gösterilemez, ödeme ile gösterilen fiyat aynı olmalı.

---

## Part 1 — Biletler

### CAT-101 — Bazı aktivite sayfaları kırık (null supplier)

| | |
|---|---|
| **Semptom** | `/activities/40882` → 500. `search?q=Berlin` → 500. Diğer Berlin turları açılıyor. |
| **Nasıl bulunur** | Log: `Something went wrong: Cannot invoke "Supplier.getName()" because the return value of "Activity.getSupplier()" is null`. Java 21'in helpful NPE mesajı satırı ve ifadeyi söylüyor. Stack trace loglanmadığı için (`e.getMessage()`) satır numarası yok; bu da ayrı bir bulgu. |
| **Root cause** | `activity.supplier_id = 5`, ama supplier 5 tablosunda yok (yetim referans). `@NotFound(IGNORE)` Hibernate'e "bulamazsan null koy" diyor. Mapper detail ve search'te supplier'ı null-safe okumuyor. |
| **Tuzak** | Search'teki mevcut null check (`activity.getSupplier() == null ? null : ...getName()`) sadece **ilk** alanı koruyor; bir sonraki satırdaki `getCity()` yine patlıyor. Medium'daki adayın takıldığı "yanlış null check" tam olarak bu. Controller'a `if (dto == null)` eklemek de yanlış katman. |
| **Fix** | Tek bir `ActivityMapper` ile supplier'ı bir kez okuyup null-safe map'lemek: `supplierName` ve `supplierLocation` null olur. Üç kopya mapping tek yere iner, böylece bug bir daha üç yerde ayrı ayrı yaşamaz. |
| **Test** | `activityWhoseSupplierIsNotSyncedYetIsStillServed`, `activityWithoutSupplierIsServed`, `searchIncludesActivitiesWithMissingSupplier` |

**Alternatifler ve trade-off'ları**

- **Veriyi düzelt** (FK ekle + backfill): ancak iki pipeline bağımsız olduğu için FK, content import'unu supplier sync'e bağlar ve import'u kırar. Doğru yer: veri kalitesi metriği + alarm (yetim aktivite sayısı).
- **Yetim aktiviteyi gizle** (inner join): business'a sorulmalı. Satılabilir bir ürünü saklamak gelir kaybı demek. Varsayılan olarak göster, supplier bilgisini boş bırak.
- **`@NotFound` kaldırıp LAZY yapmak**: yetim referansta proxy initialize edilirken `EntityNotFoundException` gelir. Ayrıca `@NotFound` Hibernate'i ilişkiyi EAGER yüklemeye zorlar; bu, N+1 bulgusunun da sebebi.

### CAT-102 — Bulunmayan aktivite için 500

| | |
|---|---|
| **Root cause** | `findById(id).get()` → `NoSuchElementException`. Catch-all `@ExceptionHandler(Exception.class)` her şeyi 500'e çeviriyor. |
| **Fix** | `orElseThrow(() -> new ActivityNotFoundException(id))` → `@RestControllerAdvice` içinde 404 + `ProblemDetail` (RFC 9457). Handler `ResponseEntityExceptionHandler`'ı extend ediyor, böylece validation ve type mismatch hataları da 400 oluyor. Fallback 500 handler **stack trace'i logluyor** (`log.error("...", e)`). |
| **Kod kokusu** | Controller'daki `if (id == null)` ölü kod: `@PathVariable` null gelemez. Primitive `long` kullanmak bunu ifade eder. |
| **Test** | `unknownActivityIsNotFound` |

Partner'ın gece alarmı da konuşulmalı: 404 cacheable bir cevap; partner tarafında 4xx'e alarm kurulmaz. API kontratını (OpenAPI) güncellemek gerekir.

### CAT-103 — Mobilde arama boş dönüyor

| | |
|---|---|
| **Root cause** | JPQL `like %:query%` case-sensitive (Postgres `LIKE`). Mobil sorguyu lowercase gönderiyor. Supplier araması da (native SQL) case-sensitive. |
| **Fix** | `lower(title) like :pattern` ve `lower(s.name) like :pattern` tek sorguda, pattern servis tarafında `toLowerCase(Locale.ROOT)` ile üretiliyor. `Locale.ROOT` önemli; bkz. Türkçe `I` problemi (set 09). |
| **Test** | `searchIsCaseInsensitive` |

**Alternatifler:** `ILIKE` (Postgres'e özel), `citext` kolon, `pg_trgm` + GIN index (`%x%` aramalarında B-tree index kullanılamaz), ya da gerçek arama motoru (GYG hybrid search blog'u). 300k aktivitede `lower(title) like '%x%'` full scan yapar. Bunu söylemek senior sinyali.

---

## Gizli bulgular (biletlerde yok, kodu okurken görülmeli)

| # | Bulgu | Neden önemli | Fix | Test |
|---|-------|--------------|-----|------|
| H1 | **SQL injection**: `SupplierController.searchSuppliers` string concat ile native SQL. `x' OR 1=1 --` tüm supplier'ları döndürüyor. | Güvenlik. Path variable'dan geliyor, public endpoint. | Spring Data derived query `findByNameContainingIgnoreCase` (parametreli, wildcard'ları da escape eder). | `supplierSearchIsNotInjectable` |
| H2 | **Duplicate sonuç**: title ve supplier adı aynı anda eşleşen aktivite iki kez dönüyor (`Spree`). | GYG Glassdoor Apr 2026 sorusu: "column1'de duplicate dönmesin". | Tek sorgu, `OR` koşulu. `ManyToOne` join'i satır çoğaltmaz. | `searchDoesNotReturnDuplicates` |
| H3 | **LIKE wildcard'ları escape edilmiyor**: `_` her şeyi eşliyor. | Yanlış sonuçlar; `%` ile maliyetli tarama. | `\`, `%`, `_` escape + `escape '\'`. | `searchTreatsWildcardsLiterally` |
| H4 | **Para `double`**: `64.9 * 0.9 = 58.410000000000004`. | Müşteri fiyatı, ödeme tutarı ve muhasebe uyuşmaz. | `NUMERIC(12,2)` + `BigDecimal`, para biriminin ondalık basamağına göre (`Currency.getDefaultFractionDigits`, JPY=0) `HALF_UP`. Flyway migration ile tip değişikliği. | `specialOfferPriceIsRoundedToCurrencyPrecision` |
| H5 | **Negatif page / sınırsız size → 500**. `size=100000` tüm tabloyu çeker. | DoS yüzeyi, yanlış status. | `@Min/@Max` (Spring 6.1 built-in method validation) → 400. | `invalidPagingIsBadRequest` |
| H6 | **NULLS FIRST**: Postgres `DESC` sıralamada NULL'u başa koyar; rating'i olmayan yeni tur listenin tepesinde. | Ranking/gelir. | `Sort.Order.desc("rating").nullsLast()`. | `unratedActivitiesAreNotRankedFirst` |
| H7 | **Unique olmayan sıralama**: sadece `rating`'e göre sıralama + offset pagination. Eşit rating'liler sayfalar arasında kayar: tekrar eden ya da kaybolan ürünler. | Mobil sonsuz scroll. | Tie-breaker `id ASC`. Daha iyisi keyset pagination. | `walkingAllPagesReturnsEveryActivityExactlyOnce` |
| H8 | **N+1**: `@NotFound` ilişkiyi EAGER yapar; her supplier için ayrı select. 20 aktivitelik sayfa 7 query. | Latency, DB yükü. | `left join fetch a.supplier` + ayrı count query. | `listingAPageDoesNotIssueOneQueryPerSupplier` |
| H9 | **Search'te `findAll()`**: supplier eşleşmesi için tüm tablo belleğe çekilip filtreleniyor. | 300k satırda OOM ya da latency. Medium'daki "logic'i repository'ye taşı" feedback'i. | Tek JPQL sorgusu + limit (`MAX_SEARCH_RESULTS`). | (H2 testleri dolaylı) |
| H10 | **Lombok `@Data` ve çift yönlü ilişki**: `toString` sonsuz recursion. Docker'da `LOGGING_LEVEL_COM_GETYOURGUIDE=DEBUG` olduğu için search her çağrıda `StackOverflowError` loglar. SLF4J yutar, response 200 döner ama log ve CPU şişer. IDE'de (INFO) görünmez. | Ortama bağlı hata, entity `equals/hashCode` tuzağı. | Entity'lerde `@Getter/@Setter`, ilişki içermeyen `toString`. Entity'yi DTO'suz JSON'a vermemek. | `EntityToStringTest` |
| H11 | **Stack trace loglanmıyor**: `log.error("...{}", e.getMessage())`. | Prod'da debug imkânsız. | `log.error("Unhandled exception", e)`. | — |
| H12 | **Katman ihlali**: `ActivityService` → `SupplierController` (controller'ı inject etmek, `ResponseEntity.getBody()` ile veri almak). | Test edilemezlik, döngüsel bağımlılık riski. | `SupplierService` + `SupplierRepository`. | — |
| H13 | **Entity JSON'a dışarı açılıyor** (`/suppliers` entity döndürüyor; `@JsonIgnore` ile yamalı). | API kontratı DB şemasına bağlı. | `SupplierDto`. | — |

### Kırmızı ringa (değiştirmeye gerek yok)

- **Schema'da FK yok.** Şüpheli görünüyor ama bilinçli (V1 yorumu). FK eklemek bağımsız import'u kırar. Doğru cevap: "Ekleyelim" değil, "neden yok, onun yerine ne izleriz".
- **`Double rating` (wrapper), `double price` (primitive).** Tutarsız görünüyor ama rating için wrapper doğru: rating'i olmayan aktivite (`NULL`) var. Price için sorun primitive olması değil, `double` olması.

---

## Part 2 — Feature: `GET /suppliers/statistics`

**Sorulması gereken netleştirme soruları**

1. "Best-performing" nedir? Rating mi, aktivite sayısı mı, booking/gelir mi? (Elimizde booking verisi yok → önerim: ortalama rating desc, eşitlikte aktivite sayısı, sonra id. Az aktiviteli supplier'ın tek 5.0'lık turla tepeye çıkması için Bayesian ortalama alternatif olarak önerilir.)
2. Farklı para birimleri olabilir mi? **Evet → asla toplanmaz veya min'i karıştırılmaz.** Para birimi başına en ucuz fiyat döndürülür. Kur dönüşümü bu endpoint'in işi değil.
3. Rating'i olmayan aktiviteler ortalamaya girer mi? Hayır (SQL `avg` NULL'ları atlar), ama sayıya girer.
4. Aktivitesi olmayan supplier listede olsun mu? Evet, en sonda (`activityCount=0`, `averageRating=null`).
5. Yetim aktiviteler (supplier'ı yok)? İstatistiğe girmez; bilinmeyen supplier'a atfedilemez.

**Tasarım**

- Aggregation DB'de: `Supplier LEFT JOIN activities GROUP BY` + `ORDER BY avg desc nulls last, count desc, id` + pagination (count query ayrı).
- Para birimi başına min fiyat **ikinci bir sorguyla**, sadece o sayfadaki supplier id'leri için (`WHERE supplier_id IN (...) GROUP BY supplier_id, currency`). Tek sorguda yapmak satırları para birimi kadar çoğaltır ve sayfalamayı bozar.
- Response: `PageResponse` (`items, page, size, totalElements, totalPages`). Mevcut `/activities` kontratı (düz liste) **bilerek değiştirilmedi**; mobil client'ı kırmamak gerekir.
- Index: `activity(supplier_id)`. Supplier sayısı büyürse istatistikler için materialized view ya da event ile güncellenen read model (rating değişince güncellenen sayaçlar) önerilir.

**Testler:** `SupplierStatisticsTest`: sıralama, NULL rating davranışı, para birimi karışmaması, boş supplier, sayfalama, invalid size.

**Alternatifler**

| Yaklaşım | Artı | Eksi |
|----------|------|------|
| Java'da `findAll` + `groupingBy` | Hızlı yazılır | Tüm tabloyu çeker; sayfalama sonradan |
| JPQL aggregate (seçilen) | DB'nin işi, sayfalanabilir | İki sorgu |
| Native SQL + `FILTER`/`jsonb_agg` | Tek round-trip | Taşınabilirlik, mapping |
| Materialized view / read model | O(1) okuma | Tazelik, invalidation |

---

## Part 3 — Refactor (adım adım, her adımda testler yeşil)

1. **Önce güvenlik ağı:** mevcut davranışı kilitleyen HTTP seviyesinde testler (regression test sınıfı). Refactor'dan önce yazılır.
2. **Constructor injection** (`@RequiredArgsConstructor`, `final` alanlar): bağımlılıklar görünür ve mock'lanabilir olur.
3. **`SupplierRepository` + `SupplierService`**: `EntityManager` ve native SQL controller'dan çıkar (H1 de kapanır). `ActivityService` artık controller'a bağımlı değil (H12).
4. **`ActivityMapper` çıkar**: üç kopya mapping tek fonksiyona iner. CAT-101'in kalıcı fix'i, fiyat hesabı tek yerde.
5. **Filtreleme repository'ye**: search tek sorgu, liste join fetch ile. `findAll()` + bellekte filtre kalkar (H8, H9).
6. **`@Controller + @ResponseBody` → `@RestController`**, `ResponseEntity.ok(...)` gürültüsü kalkar. Entity yerine DTO döndürülür (H13).
7. **Hata modeli**: domain exception + `ProblemDetail`. Catch-all sadece son çare.
8. **`@Transactional(readOnly = true)`** servislerde: Hibernate dirty checking'i atlar, read replica routing'e kapı açar.

> Mülakatta "logic'i repository'ye taşırım" demek yetmiyor (Medium feedback'i). En az 4–5. adımı canlı yapıp testlerin yeşil kaldığını göstermek gerekiyor.

## Zamanın yetmediği durumda öncelik sırası

CAT-101 (gelir kaybı + tüm Berlin araması) → H1 (güvenlik, fix'i 2 dakika) → CAT-102 → CAT-103 → H4 (para) → feature → refactor. Kalanları "prod'a çıkmadan önce" listesi olarak sesli söyle.
