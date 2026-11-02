# GYG Senior SWE — Tek Sayfa Cheat Sheet

Yazdır / ikinci monitöre koy.

## Loop (en olası Senior)

`Recruiter → [OA?] → Live Spring Boot → System Design → 2–3 Behavioral`

Recruiter’a sor: OA? Repo timing? AI policy?

## Live coding (75 dk)

| Dk | İş |
|----|-----|
| 0–3 | Boot + smoke |
| 3–18 | Repro → root-cause → fix |
| 18–28 | Unit tests |
| 28–45 | Feature/endpoint |
| 45–60 | Refactor |
| 60–75 | Edge + observability + sorular |

**Fail pattern’ler:** yanlış null check, testsiz bırakmak, debug’a gömülmek, refactor’u sadece konuşmak.

**Katmanlar:** entity · service · controller · exception handler

**AI:** boilerplate OK; root-cause senin.

## System design (45 dk)

**Prompt beklentisi:** Ticket / booking / Ticketmaster-benzeri

1. FR: view · search · book  
2. NFR: book = consistency; search = availability + low latency; hot event  
3. Entities: Event, Venue, Ticket, Booking, User  
4. API: GET event, GET search, POST reserve, POST confirm  
5. Deep dive: hold TTL, locking, idempotency, search index, cache vs SoT  

**GYG dili:** `isBookable` tek sahip · adapter Connectivity · explicit events · shadow + feature flag

## Java / GC

Measure → reduce allocation → tune flags  
`-Xms=-Xmx`, G1, `MaxGCPauseMillis`, `-Xlog:gc*`  
Prod story hazırla.

## Behavioral — 6 principles

1. Customer-first, not me-first  
2. Curiosity as compass  
3. Aim high & follow through  
4. Navigate with agility  
5. Growth over comfort  
6. Build bridges, not islands  

STAR + metrik + principle adı.

## Engineering compass

Clarity over cleverness · Proven tech · SLOs · Done > perfect · Leave code better

## Kaynaklar (hızlı)

- Guide: `docs/getyourguide-interview/README.md`  
- Ticketmaster: https://www.hellointerview.com/learn/system-design/problem-breakdowns/ticketmaster  
- Medium: https://levelup.gitconnected.com/getyourguide-senior-software-engineer-java-interview-guide-berlin-b3a0c861e218  
- Principles: https://www.getyourguide.careers/guiding-principles  
