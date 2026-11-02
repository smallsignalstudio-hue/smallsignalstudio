# GetYourGuide — Çıkmış Teknik Mülakat Soruları (Tarih × Seviye)

> **Amaç:** Bu dokümanda **yalnızca** adayların/resmi kaynakların bildirdiği teknik sorular ve repo görevleri var.  
> Hazırlık tavsiyesi, behavioral hikâye şablonları veya genel teori **yok**.  
> **Derleme:** 2026-10-09

---

## Nasıl okunmalı?

| Sembol | Anlam |
|--------|--------|
| **DOĞRULANMIŞ** | Birinci el aday raporu veya resmi GYG artifact |
| **İNDİREKT** | Aday “Glassdoor’da gördüm” diyor / ikinci el |
| **DÜŞÜK GÜVEN** | Aggregator (JobMentis, Dataford “likely”, AmbitionBox az sample) — ezberleme |

Her kayıt: **Tarih → Seviye → Aşama → Sorulanlar → Kaynak**

---

## 1. Hızlı kronoloji (teknik odaklı)

| # | Tarih (yaklaşık) | Seviye | Teknik aşama | Ne soruldu (özet) | Güven | Kaynak |
|---|------------------|--------|--------------|-------------------|-------|--------|
| 1 | ~2019–2022 bandı (Glassdoor, tarih belirsiz) | Backend Engineer (Mid band) | Codility OA | Sorting problemi | DOĞRULANMIŞ | Glassdoor Backend UK |
| 2 | Aynı rapor | Backend Engineer | Live coding | Code refactoring, SOLID, OOP | DOĞRULANMIŞ | Glassdoor Backend UK |
| 3 | 2025-01-15 (repo created) | Backend SWE (resmi artifact) | Live coding repo | JDK 21 Spring Boot app, `:8080`, IntelliJ/debug | DOĞRULANMIŞ (artifact) | github.com/getyourguide/swe-be-coding-interview |
| 4 | ~2025-05 | **Senior** | Live coding (repo) | Bug fix + feature request + refactor (Java/Spring Boot) | DOĞRULANMIŞ | Arkadaş notları |
| 5 | ~2025-05 | **Senior** | Java sohbet | GC optimization nasıl? Hangi JVM argümanları? | DOĞRULANMIŞ | Arkadaş notları |
| 6 | ~2025-05 | **Senior** | System design | High-level; Ticketmaster-benzeri booking | DOĞRULANMIŞ | Arkadaş notları |
| 7 | 2025-08-21 (post) / deneyim “some time ago” | Java SWE (seviye belirtilmemiş, Spring) | Live coding (repo) | Spring project: bug fix + solution discussion; controller/service/repository; AI kullanımına açık | DOĞRULANMIŞ | Reddit r/cscareerquestionsEU |
| 8 | Blind (tarih belirsiz, Mid post) | Mid-level Backend | Codility → Technical | Codility geçildi; sonraki tur **code refactoring** odaklı | DOĞRULANMIŞ (özet) | Blind company posts |
| 9 | 2026-02-07 | SSWE (Senior) | Coding assessment | “coding challenge round done” — soru metni yok | DOĞRULANMIŞ (süreç) | Blind |
| 10 | 2026-03-16 (yayın) | **Senior SWE Java — Berlin** | HackerRank OA | **Event Registration System** (Java use-case program) | DOĞRULANMIŞ | Medium — Veenarao |
| 11 | 2026-03 (aynı süreç) | **Senior SWE Java** | Live coding (repo) | Issue identify + refactor + unit tests + new endpoint; bugs in entity/service/controller/exception handler; **null supplier** root-cause | DOĞRULANMIŞ | Medium — Veenarao |
| 12 | 2026-03 (aday SD’ye geçemedi) | **Senior SWE Java** | System design (beklenen) | Glassdoor’da görülen: **“design ticket service”** | İNDİREKT | Medium → Glassdoor |
| 13 | AmbitionBox (Jul 2024–Apr 2026 bandı) | Senior SWE (özet) | Coding | EventEmitter: `on` / `emit` / `off` | DÜŞÜK GÜVEN | AmbitionBox / aggregator özet |
| 14 | 2026-05-10 | **Associate SWE** | Live coding (repo) | Frontend **JavaScript/Vue** codebase with bugs; feature implement / bug fix bekleniyor | DOĞRULANMIŞ | Reddit Associate thread |
| 15 | Resmi Android challenge (public) | Android Engineer | Pair programming repo | “Fetch reviews for one of our most popular Berlin tours” — happy-path app’i düzelt/iyileştir | DOĞRULANMIŞ (artifact) | github.com/getyourguide/android-challenge |
| 16 | 2025-03 | Data Engineer | Take-home + technical | SQL take-home; data modeling & pipeline design; data quality / query optimization (algoritma değil) | DOĞRULANMIŞ (ikinci el) | Reddit Data Eng thread |

---

## 2. REPO LIVE-CODING — en detaylı derleme

Bu bölüm Senior hazırlığının kalbi. Üç bağımsız kaynak aynı formatı doğruluyor.

### 2.A — Arkadaş (Senior, ~Mayıs 2025) — DOĞRULANMIŞ

| Alan | Detay |
|------|--------|
| **Seviye** | Senior Software Engineer |
| **Aşama** | Technical Interview (loop’un 1. teknik turu) |
| **Dil/stack** | Java, Spring Boot |
| **Repo timing** | Mülakattan **1 gün önce** gönderildi (duyum: artık anında açılıyor olabilir) |
| **OA** | Yok (recruiter reach-out) |
| **Görevler (aynı turda)** | 1) **Bug fix** 2) **Feature request** 3) **Refactor** |
| **Ek teknik sohbet** | GC optimizasyonu nasıl yapılır? Hangi JVM argümanları kullanılır? (interviewer’a bağlı, follow-up’lı sohbet) |
| **Sonraki teknik tur** | System Design — high-level, Ticketmaster’a yakın ([Hello Interview Ticketmaster](https://www.hellointerview.com/learn/system-design/problem-breakdowns/ticketmaster)) |

**Soru metinleri (arkadaşın tarif ettiği kadarıyla):**

1. Verilen Spring Boot repo’da bir bug’ı bul ve düzelt.  
2. Bir feature request’i implement et.  
3. Bir bölümü refactor et.  
4. (Sohbet) GC’yi nasıl optimize edersin? Hangi JVM flag’lerini kullanırsın?

---

### 2.B — Medium Senior Java Berlin (yayın: 16 Mar 2026) — DOĞRULANMIŞ

Kaynak: https://levelup.gitconnected.com/getyourguide-senior-software-engineer-java-interview-guide-berlin-b3a0c861e218

| Alan | Detay |
|------|--------|
| **Seviye** | Senior Software Engineer — Java (Berlin) |
| **Önceki aşama** | HackerRank: **Event Registration System** (Java use-case) |
| **Live coding interviewers** | 2 kişi: peer (~experience) + Technical Manager |
| **Format** | Projeyi anlatıyorlar → sen issue’ları buluyorsun → think out loud |
| **Açıkça sorulan işler** | Refactor code · Write unit tests · Write entire new endpoint |
| **Bug yerleşimi** | **Entity, Service, Controller, Exception handler** katmanlarının her birinde bilerek issue |
| **Bilinen somut bug** | **Null supplier** — aday yanlış null check’e takıldı, root-cause’u kaçırdı (resmi feedback) |
| **Beklenen süre sinyali** | Debug görevi için “~10 dakika beklenen” ifadesi feedback’te geçiyor; uzayınca diğer task’lara zaman kalmıyor |
| **Refactor beklentisi** | Sadece “logic’i repository’ye taşı” demek yetmiyor — **detaylı implementation** isteniyor |
| **Stack vurgusu** | Core Java, Spring Boot, Maven/Gradle, unit tests, production mindset |
| **AI** | Bu yazıda AI politikası yok; Reddit’te ayrı rapor var |

**Feedback’ten çıkarılan teknik checklist (onlar puanlamış):**

| İyi sinyal | Kötü sinyal |
|------------|-------------|
| Thought process anlatmak | Yanlış null check’e takılmak |
| Log analizi ile satıra inmek | Testleri tamamlayamamak |
| Fix sonrası test yazmaya girişmek | Debug’a fazla süre harcayıp feature/refactor’u yarım bırakmak |
| Guidance ile doğru çözüme varmak | Refactor’u sadece high-level önermek |

**Adayın ulaşamadığı ama beklediği SD sorusu (İNDİREKT):**  
“design ticket service” (Glassdoor’da görüldüğü söyleniyor)

---

### 2.C — Reddit Java Spring (post: 21 Aug 2025) — DOĞRULANMIŞ

Kaynak: https://www.reddit.com/r/cscareerquestionsEU/comments/1mwmeij/getyourguide_java_interview_experience/

| Alan | Detay |
|------|--------|
| **Seviye** | Belirtilmemiş (Java/Spring turu) |
| **Format** | Spring project açılıyor → bug fix + solution discuss |
| **Mimari** | Controller → Service → Repository (straightforward) |
| **AI** | Recruiter: “don’t use it only use it if you think you’re using it right”. Aday cevabı: AI’ya açıktılar; pratikte yavaşlattı |

**Soru tipi:** Mevcut Spring kodda bug bul/düzelt ve tartış — LeetCode değil.

---

### 2.D — Associate Frontend repo (10 May 2026) — DOĞRULANMIŞ

Kaynak: https://www.reddit.com/r/cscareerquestionsEU/comments/1t9aev9/getyourguide_associate_software_engineer/

| Alan | Detay |
|------|--------|
| **Seviye** | Associate Software Engineer (Berlin) |
| **Repo** | Frontend **JavaScript / Vue** codebase |
| **İçerik** | Bugs “and stuff to go over” |
| **Adayın beklediği görevler** | Implementing a feature **veya** fixing a bug |
| **Açık soru** | Codebase dışı ek soru var mı? (thread’de cevap yok) |

**Senior için not:** Aynı “paylaşılan bozuk codebase” kalıbı; stack Vue vs Java farkı.

---

### 2.E — Resmi Backend coding interview repo — DOĞRULANMIŞ (artifact)

| Alan | Detay |
|------|--------|
| **Repo** | `getyourguide/swe-be-coding-interview` |
| **Created** | 2025-01-15 |
| **Last push (indeks)** | 2026-03-30 |
| **Dil** | Java |
| **Requirement** | **JDK 21** |
| **Entry** | `GetYourGuideApplication.java` |
| **Run** | IntelliJ run **veya** `./gradlew bootRun` |
| **URL** | `http://localhost:8080/` |
| **IDE notu** | IntelliJ + debugger önerilir; başka IDE OK ama interviewer destekleyemeyebilir |
| **Erişim (2026-10)** | Public arama indeksinde README var; clone/API çoğu zaman **404/private** |

Link (indeks): https://github.com/getyourguide/swe-be-coding-interview

**Bu repo’nun kanıtladığı şey:** Backend live coding = **çalışan Spring Boot uygulaması + endpoint + debugger**, whiteboard LeetCode değil.

---

### 2.F — Resmi Android challenge repo — DOĞRULANMIŞ (artifact)

Kaynak: https://github.com/getyourguide/android-challenge

**Requirement (user-case):**

> As a potential traveler, I want to be able to fetch reviews for one of our most popular Berlin tours.

| Alan | Detay |
|------|--------|
| **Format** | Pair programming |
| **App durumu** | Best practices değil; happy path çalışıyor |
| **Görev** | Improve and fix issues |
| **Değerlendirme** | Platform, architecture, testing, problem-solving |
| **Tooling** | Java 21 + Android Studio |

**Kalıp eşleşmesi:** “Bozuk/ama çalışan app + pair ile düzelt” = backend Spring repo ile aynı felsefe.

---

## 3. ONLINE ASSESSMENT — çıkmış sorular

### 3.1 HackerRank — Event Registration System

| | |
|--|--|
| **Tarih** | 2026-03 civarı (Medium) |
| **Seviye** | Senior SWE Java |
| **Platform** | HackerRank |
| **Görev** | **Event Registration System** build et |
| **Tip** | Java use-case specific program (klasik domain model + API/logic; “rocket science” değil) |
| **Güven** | DOĞRULANMIŞ |
| **Kaynak** | Medium Veenarao |

### 3.2 Codility — Sorting problem

| | |
|--|--|
| **Tarih** | Glassdoor Backend Engineer raporu (tarih belirsiz) |
| **Seviye** | Backend Engineer |
| **Platform** | Codility |
| **Görev** | **Sorting problem** |
| **Sonraki tur** | Live coding: refactoring, SOLID, OOP |
| **Güven** | DOĞRULANMIŞ |
| **Kaynak** | https://www.glassdoor.co.uk/Interview/GetYourGuide-Backend-Engineer-Interview-Questions-EI_IE695237.0,12_KO13,29.htm |

### 3.3 Blind Mid — Codility + refactoring assessment

| | |
|--|--|
| **Seviye** | Mid-level Backend Engineer |
| **Akış** | Codility cleared → technical assessment (**code refactoring** concentrate) |
| **Güven** | DOĞRULANMIŞ (post özeti; soru metni yok) |
| **Kaynak** | https://www.teamblind.com/company/GetYourGuide/posts |

### 3.4 AmbitionBox — EventEmitter (DÜŞÜK GÜVEN)

Raporlanan görev özeti:

- `EventEmitter` class  
- `on(event, listener)`  
- `emit(event, args)`  
- `off(event, listener)`  
- Map/dictionary + listener arrays  

**Uyarı:** Az sample / aggregator yolu; Senior GYG ana sinyali Spring repo. Pratik için faydalı olabilir, “kesin çıkar” deme.

---

## 4. SYSTEM DESIGN — çıkmış / raporlanan prompt’lar

| Tarih | Seviye | Prompt | Güven | Kaynak |
|-------|--------|--------|-------|--------|
| ~2025-05 | Senior | High-level design; Ticketmaster-benzeri ticket/booking sistemi | DOĞRULANMIŞ | Arkadaş + Hello Interview linki |
| 2026-03 | Senior | “design ticket service” | İNDİREKT (Glassdoor via Medium) | Medium |
| — | — | Somut “URL shortener” vb. klasik FAANG prompt’u GYG için **doğrulanmadı** | — | — |

**Arkadaşın kullandığı referans:**  
https://www.hellointerview.com/learn/system-design/problem-breakdowns/ticketmaster

Ticketmaster FR’leri (referans — GYG’de birebir sorulmuş metin değil, arkadaşın çizdiği yakın sistem):

1. View events  
2. Search events  
3. Book tickets (no double booking)

---

## 5. JAVA / JVM SOHBET — çıkmış sorular

| Tarih | Seviye | Soru | Güven | Kaynak |
|-------|--------|------|-------|--------|
| ~2025-05 | Senior | GC optimizasyonunu nasıl yaparsın? | DOĞRULANMIŞ | Arkadaş |
| ~2025-05 | Senior | Hangi JVM argümanlarını kullanabilirsin? | DOĞRULANMIŞ | Arkadaş |
| 2026-03 | Senior | Core Java + Spring Boot production knowledge (live coding bağlamında) | DOĞRULANMIŞ | Medium |

---

## 6. GLASSDOOR BACKEND — teknik + teknik-adjacent soru listesi

Kaynak: https://www.glassdoor.co.uk/Interview/GetYourGuide-Backend-Engineer-Interview-Questions-EI_IE695237.0,12_KO13,29.htm

### Teknik (doğrudan)

1. Codility: **sorting problem**  
2. Live coding: **code refactoring**  
3. Live coding: **SOLID**  
4. Live coding: **OOP**

### Aynı raporlarda geçen diğer sorular (behavioral ama loop’ta)

5. Past mistakes  
6. Feedback given to your supervisor + aftermath  
7. Non-technical process you implemented  
8. Most successful / proud project  
9. What you’re looking for in next job; success/failure experiences  
10. CTO: team-context situations, ownership  

### Başka Backend Glassdoor notu

11. “Mixture of questions about past behaviour and technical questions.” (spesifik teknik metin yok)

---

## 7. DATAFORD / JOBMENTIS — “likely” listeler (DÜŞÜK GÜVEN, ayrı tut)

Bunlar **aday transcript’i gibi doğrulanmadı**. Ezberleme; pattern olarak oku.

### Dataford Backend (https://dataford.io/interview-guides/getyourguide/backend-engineer)

- Refactor this poorly written REST service to improve readability and performance.  
- How would you approach modifying a legacy codebase while ensuring zero downtime?  
- Explain/implement Factory or Adapter in a real-world scenario.  
- Data grouping and sorting for large inputs.  
- Parsing/validating input with regular expressions.  
- Design a service for [business logic] with high availability.  
- A/B testing impact on backend architecture.  
- Balance features vs legacy tech debt.  
- Optimize a slow SQL query.  
- Debug and Optimize Slow SQL (PostgreSQL reporting query — “Medium”)  
- Topic tags: code refactoring, REST APIs, AI assistance in coding, algorithmic PS, live coding/pair  

### JobMentis SWE (https://www.jobmentis.com/en/interviews/getyourguide/swe) — sentetik ihtimal yüksek

- k closest cities (Haversine + heap)  
- Max non-overlapping tours by date intervals  
- Personalized tour recommendation system design  
- Real-time notification system for availability/offers  
- Fraud detection for bookings/reviews  
- Top add-on activities from booking data  

**Bu listedeki hiçbir maddeyi “GYG’de kesin çıktı” diye işaretleme.**

---

## 8. Seviyeye göre “ne çıktı?” — sadece teknik

### Associate / Junior

| Çıkan teknik görev | Kaynak |
|--------------------|--------|
| Vue/JS paylaşılan codebase: bug + feature | Reddit May 2026 |
| (Android track) Berlin tour reviews fetch — pair fix/improve | Resmi android-challenge |

### Mid / Backend Engineer

| Çıkan teknik görev | Kaynak |
|--------------------|--------|
| Codility sorting | Glassdoor |
| Live refactor + SOLID + OOP | Glassdoor |
| Codility → refactoring technical assessment | Blind Mid |

### Senior (Java / Backend) — senin hedef

| Çıkan teknik görev | Kaynak |
|--------------------|--------|
| HackerRank Event Registration System (bazı track’lerde) | Medium 2026 |
| Spring Boot repo: bug + feature + refactor (+ tests + new endpoint) | Arkadaş + Medium + Reddit |
| Katmanlı bug’lar (entity/service/controller/exception) | Medium |
| Somut bug örneği: null supplier | Medium feedback |
| GC optimization + JVM args sohbet | Arkadaş |
| System design: Ticketmaster / ticket service | Arkadaş + Medium/Glassdoor |

---

## 9. Repo turu için “bilinen görev paketi” (sentez — ezber kartı)

Üç DOĞRULANMIŞ kaynaktan birleşik paket (soru bankası kartı):

```
[REPO LIVE CODING — Senior Java/Spring]
1. DEBUG: Log/stack ile root-cause bul; düzelt.
   - Bilinen örnek root-cause: null supplier (yanlış null check'e takılma)
2. TEST: Fix için unit test yaz (happy + edge)
3. FEATURE: Yeni endpoint'i uçtan uca yaz
   (controller → service → entity/repo → exception handler)
4. REFACTOR: Bir bölümü katmana taşı / SOLID uygula
   (sadece verbal değil, kodla göster)
5. (Opsiyonel sohbet) GC optimize + JVM flags
```

```
[OA — track'e bağlı]
- HackerRank: Event Registration System (Senior Java, 2026)
- Codility: Sorting (Backend/Mid, Glassdoor)
```

```
[SYSTEM DESIGN — Senior]
- Ticketmaster-like / "design ticket service"
  (view, search, book; double-booking; consistency)
```

---

## 10. Kaynak linkleri (bu PDF’teki her DOĞRULANMIŞ madde)

1. Arkadaş notları (Mayıs 2025 Senior süreci) — bu derlemenin birincil kaynağı  
2. Medium Senior Java: https://levelup.gitconnected.com/getyourguide-senior-software-engineer-java-interview-guide-berlin-b3a0c861e218  
3. Reddit Java Spring: https://www.reddit.com/r/cscareerquestionsEU/comments/1mwmeij/getyourguide_java_interview_experience/  
4. Reddit Associate Vue: https://www.reddit.com/r/cscareerquestionsEU/comments/1t9aev9/getyourguide_associate_software_engineer/  
5. Glassdoor Backend UK: https://www.glassdoor.co.uk/Interview/GetYourGuide-Backend-Engineer-Interview-Questions-EI_IE695237.0,12_KO13,29.htm  
6. Glassdoor hub: https://www.glassdoor.com/Interview/GetYourGuide-Interview-Questions-E695237.htm  
7. Blind SSWE: https://www.teamblind.com/post/getyourguide-interview-x5gsvpae  
8. Blind company posts (Mid Codility/refactor): https://www.teamblind.com/company/GetYourGuide/posts  
9. Official BE interview repo: https://github.com/getyourguide/swe-be-coding-interview  
10. Official Android challenge: https://github.com/getyourguide/android-challenge  
11. Ticketmaster SD referans: https://www.hellointerview.com/learn/system-design/problem-breakdowns/ticketmaster  
12. Reddit Data Eng: https://www.reddit.com/r/cscareerquestionsEU/comments/1jaz9pi/did_anyone_attended_interview_for_data_engineer/  

**Erişim notu:** Glassdoor tam sayfalar login-wall olabiliyor; Backend UK snippet’leri bu derlemede kullanıldı. `swe-be-coding-interview` sık private/404.

---

## 11. Bilinçli olarak EKLENMEYENLER

- JobMentis “k-closest cities” vb. → tarihli aday raporu yok  
- Genel “LeetCode medium grind” iddiaları → GYG saha raporlarıyla çelişiyor  
- Behavioral STAR şablonları → bu PDF’in kapsamı dışı  
- 4chan / HN → GYG teknik soru dump’ı bulunamadı  

---

*Yeni Glassdoor/Reddit raporu bulursan: tarih + seviye + aşama + soru metni + link ile bu tabloya satır ekle.*
