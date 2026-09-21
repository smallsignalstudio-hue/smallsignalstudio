# PulseQuiet — Ürün & İş Raporu (Tam Detay)

**Tarih:** 2026-09-21  
**Çalışma adı:** PulseQuiet  
**Kategori:** Hipertansiyon / evde tansiyon kaydı (consumer health utility)  
**Platform:** iOS-first (iPhone; iPad secondary)  
**MVP süresi:** ~4 hafta (solo)  
**Skor (Genius Ideas rubric):** 89/100  
**Öneri:** Research homework → Ship MVP  

**Ürün vaadi (tek cümle):**  
Ucuz, aşırı kolay, 55+ dostu tansiyon günlüğü — *bugün ölçtüm mü?* ve *doktora tek PDF*.

---

## 0. Bu rapor ne için?

Bu belge PulseQuiet’in:

1. Pazar ve rakip gerçeğini  
2. Hedef kitle psikolojisini  
3. Yaşa göre kullanım kolaylığı kurallarını  
4. Feature set’i (MVP / v1.1 / asla yapılmayacaklar)  
5. Fiyat, GTM, build planı, kill kriterlerini  

tek yerde toplar. Amaç: **ucuz + hedefe yönelik + aşırı kolay** bir uygulama.

---

## 1. Yönetici özeti

### Bahis
Dünyada ~1.28 milyar hipertansiyon hastası var; evde ölçüm doktorlarca sık isteniyor. App Store’da SmartBP (~$40/yıl, agresif paywall şikayetleri) ve Feeltracker (AI chat, özellik şişkinliği) var. Boşluk: **sessiz, büyük yazı, widget-first, adil fiyatlı Doctor Pack.**

### Ne satmıyoruz
- Kalp sağlığı platformu  
- AI koç / teşhis  
- Bluetooth cuff ekosistemi (MVP)  
- “Market boş” hikâyesi (market dolu; wedge dar)

### Ne satıyoruz
| An | İş |
|----|-----|
| Her sabah/akşam | Lock Screen: bugün ölçüm var mı? |
| Kontrol randevusundan önce | 1 dokunuş Doctor Pack PDF |

### Fiyat pozisyonu
**~$14.99–19.99/yıl** — SmartBP’nin altında, SteadyStat/xBP ücretsizlerinin “adil ücretli” alternatifi.

### Gelir beklentisi (Estimated)
| Senaryo | 12. ay MRR |
|---------|------------|
| Kötümser | ~$1–3K |
| Base | ~$6–15K |
| İyimser | ~$18–40K |

Lider rakip tahminleri: SmartBP ~$10–40K/ay (**Estimated**, kaynaklar çelişkili). PulseQuiet’in hedefi lideri yıkmak değil, **sade dilim**.

---

## 2. Pazar gerçekliği (dürüst)

### 2.1 Pazar katmanları

| Katman | Büyüklük | PulseQuiet ile ilişki |
|--------|----------|------------------------|
| Hipertansiyon hastaları (global) | ~1.28B | Aday havuz |
| BP + ECG mHealth app geliri | ~$362M (2024) | Üst kategori |
| Dar BP measurement app | ~$75M (2025) | Daha yakın |
| Consumer log/diary niche | Küçük dilim | **Asıl oyun alanı** |

### 2.2 Rakipler ve gelir (Estimated / kısmen self-report)

| Rakip | Ölçek | Fiyat | Gelir sinyali | Zayıflık |
|-------|-------|-------|---------------|----------|
| SmartBP | 50K–100K+ rating | ~$15/ay · ~$40/yıl | ~$10–40K/ay Est. | Dark trial, PDF paywall, “rip off” yorumları |
| Feeltracker BP | ~23K rating; suite 55K MAU | ~$30/yıl bandı | Unknown | AI chat, mini-oyun, kalabalık |
| Apple Health | Built-in | Ücretsiz | — | BP iş akışı gömülü; doktor PDF zayıf |
| SteadyStat / xBP | Yeni | $0 forever | $0 | Ücretsiz sade rakip |
| Android junk BP apps | 3.5–3.8★ | Çeşitli | — | Talep + kötü arz (özellikle Android) |

### 2.3 Reklam
- **SmartBP:** Apple Search Ads + Meta + AppLovin (privacy policy / SDK) — UA altyapısı var.  
- **Feeltracker:** Ads/tracking yok iddiası — organik/ASO.  
- **PulseQuiet:** Launch’ta ASO + community; doğrulama sonrası küçük Apple Search Ads.

**ASO only:** Niche’te yavaş büyüme mümkün; SmartBP’yi geçmek değil.

---

## 3. Hedef kitle

### 3.1 Birincil persona — “Ayşe / Mehmet” (55–72)

| Alan | Detay |
|------|--------|
| Yaş | **55–72** (çekirdek) |
| Durum | Hipertansiyon veya “doktor evde takip istedi” |
| Cihaz | iPhone (çoğunlukla SE / 13 / 14; büyük model tercih) |
| Dijital seviye | WhatsApp + kamera bilir; App Store’da kaybolur |
| Mevcut çözüm | Defter, Omron app, Apple Health’e zorla girme, çocuğun kurduğu app |
| Korku | Yanlış ilaç ayarı, doktora “boş elle” gitmek, karmaşık app’te kaybolmak |
| Para | $40/yıl’a kızar; $15–20/yıl “makul” görür |

### 3.2 İkincil persona — “Caregiver çocuk” (28–45)

| Alan | Detay |
|------|--------|
| Rol | Anne/babaya app kurar, PDF’i ister |
| İhtiyaç | Kurulumu 2 dakikada bitirmek; arama destek almamak |
| Satın alma | Sıklıkla yıllık aboneliği o öder |

### 3.3 Üçüncül — “Erken takip” (40–54)

Yeni teşhis, ilaç denemesi, white-coat. Daha teknolojik; yine de **sadelik** ister. İkincil ASO/mesajlaşma.

### 3.4 Bilinçli dışladıklarımız (v1)

- Sporcu / biohacker dashboard isteyenler  
- Sadece Bluetooth Omron ekosistemi arayanlar  
- Teşhis / “tansiyonumu düşür” AI isteyenler  
- Çoklu kronik hastalık all-in-one suite  

---

## 4. Hedef kitle psikolojisi

### 4.1 Duygusal motorlar

| Duygu | Tetik | Ürün cevabı |
|-------|--------|-------------|
| **Belirsizlik kaygısı** | “Bu sabah ölçtüm mü?” | Widget ✓ / ✗ |
| **Randevu stresi** | “Doktora ne göstereceğim?” | Doctor Pack 1 tap |
| **Kontrol ihtiyacı** | İlaç değişince “işe yarıyor mu?” | Basit trend (ortalama), teşhis dili yok |
| **Utanç / yetersizlik** | Karmaşık app’te kaybolmak | Tek ekran, büyük yazı |
| **Alınma** | SmartBP dark pattern | Şeffaf yıllık fiyat, log kilidi yok |
| **Güven** | Sağlık verisi | Local-first, hesap zorunlu değil |

### 4.2 Bilişsel yük kuralları (55+)

1. **Bir ekranda bir iş** — giriş / geçmiş / PDF ayrı, karışık tab bar yok.  
2. **Maksimum 3 birincil aksiyon** ana ekranda: Kaydet · Bugün · Doktor PDF.  
3. **Jargon yok** — “sistolik” yerine “üst (büyük) sayı”; yanında küçük “(SYS)” isteğe bağlı.  
4. **Suçlayan streak yok** — kaçırılan gün kırmızı utanç değil; nazik hatırlatma.  
5. **Onboarding ≤ 60 saniye** — isim/hesap/anket yok (veya tek “sabah + akşam hatırlat?”).  

### 4.3 Güven ve sağlık dili

- Asla: “Hipertansiyonunuz var”, “İlacınızı değiştirin”, “Tehlikeli — acile gidin” (kriz bandında sadece **nötr uyarı + doktora danışın**).  
- Renk kodları: AHA/ACC bilgilendirme bandı; “teşhis değil” disclaimer her kritik yerde.  
- App Store: Medical veya Health & Fitness; tıbbi cihaz iddiası yok.

### 4.4 Fiyat psikolojisi

| Fiyat | Algı (hedef kitle) |
|-------|---------------------|
| Free forever | “Ciddi değil” veya SteadyStat’a kayıp |
| $0.99/hafta | Tuzak / yaşlı kullanıcı nefreti |
| **$14.99–19.99/yıl** | “Makul, doktora gitmekten ucuz” |
| $39.99+/yıl | SmartBP öfkesi |

---

## 5. Yaşa göre kullanım kolaylığı (UX sistemi)

### 5.1 Tipografi ve dokunma

| Öğe | Kural |
|-----|--------|
| Ana sayılar (SYS/DIA) | **≥ 44–56 pt**, bold, yüksek kontrast |
| Etiketler | ≥ 17–20 pt |
| Minimum tap hedefi | **44×44 pt** (tercihen 48+) |
| Stepper / numpad | Büyük +/- veya tekerlek değil, **büyük sayısal klavye** |
| Kontrast | WCAG AA+; saf gri üzerinde ince gri yok |
| Dark mode | Varsayılan opsiyon; gece ölçümü için |

### 5.2 Motor / titreme (parkinson, yaş)

- Kaydır-sil yok (veya onaylı).  
- Kritik silme: “Emin misiniz?” iki adım.  
- Widget’tan tek tap → doğrudan kayıt ekranı (derin menü yok).

### 5.3 Görme

- Dinamic Type destekli (mümkün olan her metin).  
- Renk körlüğü: sadece kırmızı/yeşile güvenme; **ikon + metin** (✓ Bugün tamam / ! Eksik).  
- Renk bantları yanında kısa etiket: “Normal aralık (bilgi)”.

### 5.4 İşitsel / dikkat

- Bildirim günde **max 2** (sabah/akşam).  
- Ses zorunlu değil; sessiz kullanıcı varsayılan.  
- Bildirim metni kısa: “Sabah ölçümü zamanı” — emoji yok veya tek.

### 5.5 Caregiver modu (kolay kurulum)

İlk açılışta opsiyonel:  
**“Bu telefon annem/babam için”** → daha büyük yazı varsayılan + sabah/akşam hatırlatma açık + PDF paylaşımı belirgin.

---

## 6. Ürün farkı (wedge)

| | SmartBP | Feeltracker | Apple Health | PulseQuiet |
|--|---------|-------------|--------------|------------|
| Odak | Platform + sync | AI + scan | Her şey | **2 iş** |
| Widget “bugün?” | Var/karmaşık | Var | Zayıf | **Ana ürün** |
| PDF | Çoğu Pro / pahalı | Var | Zayıf | Free önizleme + Pro tam |
| AI / oyun | — | Var | — | **Yok** |
| Fiyat algısı | Pahalı/tuzak | Orta | Free | **Ucuz ve şeffaf** |
| 55+ UI | Orta | Zayıf (yoğun) | Zayıf | **Tasarım zorunluluğu** |

**Falsifiable fark:** Rakipler dashboard satar; PulseQuiet **mikro-anksiyete (widget) + randevu anı (PDF)** satar.

---

## 7. Feature set

### 7.1 Tasarım ilkesi

> Her feature şu soruyu geçmeli:  
> “Bu, 65 yaşındaki kullanıcının sabah 30 saniyesini veya doktor randevusunu kolaylaştırıyor mu?”  
> Hayır → v1’e girmez.

### 7.2 MVP (Ship) — zorunlu

#### A. Kayıt (çekirdek)
- Üst (SYS), alt (DIA), nabız (opsiyonel ama önerilen)  
- Tarih/saat (otomatik; düzenlenebilir)  
- Opsiyonel kısa not (max ~80 karakter): “ilaç sonrası”, “stres”  
- Opsiyonel etiketler (max 4 hazır): Sabah · Akşam · İlaç sonrası · Diğer  
- Çift ölçüm ortalaması (AHA pratikleri için): “2. ölçümü ekle / ortalama al” — **basit**, bilimsel lab değil  

#### B. Bugün durumu
- Ana ekran: bugünün durumu (tamam / eksik)  
- Son 3 okuma listesi  
- Renk bandı (bilgi): Normal / Yüksek / Evre 1 / Evre 2 / Acil aralık uyarısı (disclaimer ile)  

**AHA/ACC bilgilendirme bantları (referans — UI’da “teşhis değil”):**
| Bant | Üst (SYS) | Alt (DIA) |
|------|-----------|-----------|
| Normal | &lt;120 | ve &lt;80 |
| Elevated | 120–129 | ve &lt;80 |
| Stage 1 | 130–139 | veya 80–89 |
| Stage 2 | ≥140 | veya ≥90 |
| Crisis uyarısı | &gt;180 | ve/veya &gt;120 → “Doktora / acile danışın” metni |

#### C. Widget + bildirim
- Lock Screen / Home: bugün ✓ veya ✗ + son değer  
- Sabah + akşam hatırlatma (kullanıcı saati seçer)  
- “Zaten kaydettiysen bildirim gösterme” (smart skip)  

#### D. Doctor Pack
- Free: son **7 gün** PDF önizleme + watermark  
- Pro: **7 / 14 / 30 gün** seçimi, watermark yok  
- PDF içeriği: ad (opsiyonel), tarih aralığı, okuma tablosu, sabah/akşam ortalaması, basit grafik  
- Paylaş: Share Sheet (Mail, Messages, Files)  

#### E. Gizlilik ve güvenlik
- Hesap zorunlu değil  
- Local SwiftData  
- Opsiyonel Face ID / cihaz kilidi (Pro veya free — tercihen free, güven için)  
- HealthKit: MVP’de **opsiyonel yazma** (kullanıcı açarsa); okuma zorunlu değil  

#### F. Erişilebilirlik
- Large Text varsayılan profil  
- Dynamic Type  
- VoiceOver etiketleri (SYS/DIA alanları)  

#### G. Monetizasyon yüzeyi
- Soft paywall: Doctor Pack tam / uzun geçmiş / 2. profil  
- Log ve widget **asla kitlenmez**  

### 7.3 Free vs Pro matrisi

| Özellik | Free | Pro ($14.99–19.99/yıl) |
|---------|------|-------------------------|
| Sınırsız kayıt | ✓ | ✓ |
| Widget | ✓ | ✓ |
| Hatırlatmalar | ✓ | ✓ |
| Grafik | Son 7 gün | Sınırsız |
| Doctor Pack PDF | 7 gün + watermark | 7/14/30, temiz |
| CSV export | — | ✓ |
| 2. kişi profili (eş/ebeveyn) | — | ✓ |
| Face ID | ✓ | ✓ |
| Reklam | Yok | Yok |

**Aylık:** $1.99–2.99 (annual’ı itmek için).  
**Lifetime (opsiyonel v1.1):** $29.99–39.99.

### 7.4 v1.1 (MVP sonrası, seçici)

1. iCloud sync (aynı Apple ID)  
2. Caregiver: “PDF’i her Pazar çocuğa Mail ile” (local shortcut / paylaşım hatırlatması — sunucu yok)  
3. İlaç adı notu (hatırlatma değil; serbest metin) — DoseSure’a kaymamak için sınırlı  
4. TR / EN / DE lokalizasyon  
5. Apple Watch complication (sadece “bugün ✓/✗”)  

### 7.5 Bilinçli olarak YAPILMAYACAKLAR (v1–v1.5)

- AI sohbet / “kalp koçu”  
- Mini-oyun  
- Sosyal / forum  
- Teşhis, ilaç dozu önerisi  
- Kameradan manşet OCR (Feeltracker kopyası; hata → güven kaybı)  
- Bluetooth cuff sync (destek cehennemi)  
- Şeker, kilo, ECG all-in-one  
- Haftalık abonelik  
- Zorunlu hesap / bulut  

---

## 8. Kullanıcı akışları

### 8.1 İlk açılış (≤ 60 sn)

1. 1 ekran: “Tansiyonunu kaydet. Doktora göster.”  
2. Tipografi: “Büyük yazı kullan?” → Evet varsayılan 55+ için önerilir  
3. Hatırlatma: Sabah __ : __ · Akşam __ : __  
4. Widget ekleme kartı (screenshot + “Ekle”)  
5. İlk kayıt → confetti yok; sakin “Kaydedildi ✓”

### 8.2 Günlük ritüel (10–20 sn)

1. Bildirim veya widget  
2. Üst / alt / nabız  
3. Kaydet  
4. Widget yeşile döner  

### 8.3 Randevu günü (30–60 sn)

1. Doctor Pack  
2. 14 veya 30 gün  
3. PDF paylaş / Yazdır  
4. (Pro değilse) yumuşak paywall — free 7 gün denemiş olsun  

### 8.4 Caregiver kurulumu

1. “Başka biri için kur”  
2. Büyük yazı ON  
3. Hatırlatmalar ON  
4. “PDF’i nasıl paylaşırım?” tek sayfa yardım  

---

## 9. Görsel dil (premium ama sakin)

| Öğe | Yön |
|-----|-----|
| Atmosfer | Açık nötr zemin + yumuşak mavi/teal vurgu (klinik mor / krem AI klişesi yok) |
| Marka | PulseQuiet — “sessiz güven” |
| İkonografi | Kalın, az detay; emoji yok |
| Animasyon | 2–3: kayıt onayı, widget durum geçişi, PDF hazır — abartısız |
| Kartlar | Mümkün olduğunca yok; liste + net bloklar |

---

## 10. Teknik plan

| Katman | Seçim |
|--------|--------|
| UI | SwiftUI |
| Veri | SwiftData (local) |
| Widget | WidgetKit |
| PDF | PDFKit |
| Ödeme | StoreKit 2 |
| Backend | **Yok (MVP)** |
| Sync | Yok → v1.1 iCloud opsiyonel |

### App Store uyumu
- Disclaimer her yerde  
- “Does not measure blood pressure”  
- Crisis copy: panik değil, yönlendirme  
- Privacy Nutrition Labels: tracking yok (SmartBP’den ayrış)

### 4 haftalık build

| Hafta | Çıktı |
|-------|--------|
| 1 | Kayıt + liste + renk bantları + Large Text |
| 2 | Widget + bildirimler + bugün durumu |
| 3 | Doctor Pack PDF + paywall + Free/Pro |
| 4 | Face ID, a11y, TestFlight, ASO metadata |

---

## 11. Go-to-market

### ASO (örnek)
**Title:** PulseQuiet: BP Log & Doctor PDF  
**Subtitle:** Easy blood pressure diary  

Keywords (EN çekirdek): blood pressure log, BP tracker, hypertension diary, blood pressure PDF, doctor report, BP widget, BP reminder, blood pressure journal…

TR store: tansiyon takip, tansiyon defteri, tansiyon PDF, doktor raporu…

### Kanallar
1. r/bloodpressure, r/hypertension — defter→PDF hikâyesi  
2. Caregiver içerik: “Anneme kurduğum 3 ekran”  
3. Sonra: Apple Search Ads (düşük bütçe, brand + “blood pressure log pdf”)

### İlk 100 kullanıcı
- 20 TestFlight (hipertansiyon community)  
- 10 caregiver  
- PDF örneği ile ASO screenshot seti  

---

## 12. Başarı metrikleri ve kill kriterleri

### Ürün metrikleri
| Metrik | Hedef (ilk 60 gün) |
|--------|---------------------|
| D1 kayıt | ≥ %50 install |
| Widget ekleme | ≥ %35 |
| D7 ≥3 kayıt | ≥ %25 |
| Trial / paywall görüntüleme | Randevu veya gün 14 |
| Free→Paid | %2–4 Estimated |

### Kill / pivot
| Gün | Eşik | Aksiyon |
|-----|------|---------|
| 30 | &lt;400 install veya widget &lt;%20 | Onboarding/ASO |
| 60 | &lt;30 paid ve trial zayıf | Fiyat veya PDF wedge testi |
| 90 | &lt;$400 MRR | Kill veya DoseSure pivot |

---

## 13. Riskler ve karşı önlemler

| Risk | Önlem |
|------|--------|
| Feeltracker/SmartBP “yeterince iyi” | Widget + 55+ UI + fiyat; özellik yarışı yok |
| SteadyStat ücretsiz | Pro değeri = uzun geçmiş + temiz PDF + 2. profil; log’u kilitleme |
| Apple Health | “Doktor PDF + bugün durumu” Health’te yok sayılır kadar zayıf |
| Churn (ilaç stabilize) | Yıllık plan; caregiver profili; aşırı genişlemeye gitme |
| Yanlış tıbbi algı | Disclaimer, kriz dili, Review notları |
| Düşük fiyat + ads CPI | Organik önce; Search Ads sonra |

---

## 14. Homework (build öncesi)

1. 15 hedef kullanıcıya: “SmartBP varken $19.99/yıl verir misin?” — ≥5 net evet.  
2. SmartBP + Feeltracker son 50 düşük yıldız — PDF/paywall/complexity sayımı.  
3. 5 kişiye örnek Doctor Pack PDF — “doktora götürür müsün?”  

2/3 yeşil → Ship.

---

## 15. Özet checklist (ürün DNA)

- [x] Tek job: günlük ölçüm netliği + doktor PDF  
- [x] Ucuz: ~$15–20/yıl  
- [x] Aşırı kolay: 55+ tipografi, 3 aksiyon, ≤60 sn onboarding  
- [x] Backend yok  
- [x] Log/widget free  
- [x] AI/oyun/Bluetooth yok  
- [x] Etik paywall  
- [x] Local privacy  

---

*PulseQuiet — Small Signal Studio tarzı: quiet premium, single job, freemium, solo-shippable.*
