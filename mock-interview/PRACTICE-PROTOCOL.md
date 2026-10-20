# Pratik Protokolü

Gerçek mülakat ~60–75 dakika. Her set kasıtlı olarak bu süreden **daha fazla** iş içerir; neyi önce yapacağını seçmek de değerlendirmenin parçasıdır.

## Seans akışı (75 dk)

| Dakika | Aşama | Ne yapıyorsun | Mülakatçının baktığı şey |
|--------|-------|---------------|--------------------------|
| −24 saat | Repo geldi | `docker compose up --build`, testleri çalıştır, README/INTERVIEW oku, domain'i çıkar. Kod üzerinde **değişiklik yapma**, ama okuyup not al. | Hazırlıklı gelmek |
| 0–5 | Setup kontrol | Uygulama ayakta, `requests.http` çalışıyor. Ekranı paylaş. | Ortam hakimiyeti |
| 5–10 | Business'ı anlat | Domain'i kendi cümlelerinle özetle: aktörler, para akışı, invariant'lar (ör. "bir koltuk aynı anda tek kişide olabilir"). | Önce business, sonra kod |
| 10–35 | Bug biletleri | Her bilet için: yeniden üret (curl/test) → hipotez → root cause → **önce failing test** → fix → yeşil. Her ~5 dk sesli özet. | Root cause vs semptom, test disiplini, ~10 dk/bug |
| 35–55 | Feature request | Önce 2–3 netleştirme sorusu, sonra küçük tasarım (veri modeli, endpoint, eşzamanlılık, hata durumları), sonra kod + test. | Trade-off konuşması, edge case'ler |
| 55–70 | Refactor | Neyi neden refactor edeceğini söyle; küçük güvenli adımlar, her adımda testler yeşil. | Davranışı koruyarak tasarımı iyileştirmek |
| 70–75 | Kapanış | Görmüş olup yapamadıklarını listele ("prod'a çıkmadan önce şunlar da var…"). | Senior farkındalık |

## Kurallar

- **Önce oku, sonra yaz.** Gizli buglar biletlerde yazmaz; kodu okurken fark edip söylemen beklenir.
- **Failing test olmadan fix yok.** Eşzamanlılık bug'larında bile (Testcontainers + `CountDownLatch` / `ExecutorService`).
- **AI:** Gerçek mülakatta izinli. Kullanırsan prompt'un spesifik olsun (sınıf, invariant, beklenen test). "Fix this code" değil. Üretilen kodu satır satır açıkla.
- **Bir şeye takıldığında** 5 dakikayı geçirme; sesli olarak hipotezini söyle ve ilerle.
- **Kırmızı ringa:** Her sette şüpheli görünen ama doğru olan en az bir kod parçası var. "Bunu değiştirmeliyiz" demeden önce gerçekten yanlış olduğunu kanıtla.

## Self-rubric (her setten sonra 1–4 puan)

| Boyut | 1 | 4 |
|-------|---|---|
| Business anlama | Kodu business'tan bağımsız okudu | Invariant'ları önce söyledi, bug'ları invariant ihlali olarak tarif etti |
| Debugging | Semptomu yamaladı (null check ekledi) | Root cause'a gitti, neden oluştuğunu ve başka nerede olabileceğini söyledi |
| Test | Test yazmadı | Fix'ten önce failing test, edge case testleri |
| Eşzamanlılık / veri tutarlılığı | Fark etmedi | Race'i gösterdi, DB seviyesinde garanti (constraint/lock) seçti, trade-off'u anlattı |
| Feature tasarımı | Doğrudan kodladı | Netleştirme soruları, veri modeli, hata yolları, ölçek |
| Refactor | Büyük patlama değişiklik | Küçük, test korumalı adımlar; neden önemli olduğunu anlattı |
| İletişim | Sessiz çalıştı | Düzenli özet, mülakatçıyı yönlendirdi |
| Önceliklendirme | Her şeyi yarım bıraktı | En yüksek business riskli işi önce yaptı ve bunu söyledi |

Çözümü açtıktan sonra `FOLLOW-UP-QUESTIONS.md` dosyasındaki soruları yüksek sesle cevapla; "güçlü cevapta olması gerekenler" listesiyle karşılaştır.
