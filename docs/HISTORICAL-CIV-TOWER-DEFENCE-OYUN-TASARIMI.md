# Historical Civilizations Tower Defence — Kapsamlı Oyun Tasarım Dokümanı (GDD)

**Doküman türü:** Ana Tasarım Kutsal Kitabı (Master Design Bible) — Türkçe, sentezlenmiş  
**Kapsam:** Ürün vizyonu → rakip analizi → oyuncu sentiment → çekirdek döngü → kamera/juice → defans → saldırı/dalga → 11 medeniyet kampanyası (Act 0–10) → meta progresyon → FTUE → retention/viral → sosyal → monetizasyon → live ops → UI/UX → teknik kapsam → risk/SWOT → KPI → açık sorular  
**Kaynak:** `docs/_research/GDD-*.md` (16 araştırma dosyası) + `docs/TOWER-DEFENCE-TARIHSEL-ARASTIRMA-INDEX.md` + 11 medeniyetlik Türkçe/İngilizce tarihsel araştırma kütüphanesi  
**Durum:** Sentez tasarım dokümanı — sayısal dengeler (balance numbers) tasarım *hedefleri*dir, kilitli değildir; playtest ile revize edilecektir.  
**Dil politikası:** Tam Türkçe; teknik/oyun terimleri parantez içinde İngilizce korunur (örn. "erken çağırma (early call)").  
**Kanıt etiketleme kuralı (ZORUNLU, her medeniyet bölümünde):** **ARCH** (arkeoloji/kazı), **LIT** (antik edebi kaynak — Herodot, İncil, Berossus vb.), **FOLK** (turistik efsane, sonradan eklenmiş mit, doğrulanmamış rivayet), **SPN** (dönemin propagandası/kraliyet söylemi), **MOD** (modern akademik tartışma/yeniden inşa aralığı), **CHR** (kronoloji uyarısı). **FOLK hiçbir zaman ARCH'ın üzerine sessizce yazılamaz.**

---

## İçindekiler (bölüm haritası)

0. Yönetici özeti / vizyon / tek cümle pitch
1. Ürün kimliği, fantazi, hedef kitle, platform
2. Rakip analizi ve konumlandırma
3. Oyuncu sentiment (Reddit + 4chan + mağaza) → ürün kuralları
4. Çekirdek döngü (saniye → sezon)
5. Kamera ve sunum / juice / erişilebilirlik
6. Defans mekanikleri (5 fiil, kuleler, kışla, tuzak, duvar, kahraman, kaynaklar)
7. Saldırı / dalga / düşman / boss / ekonomi
8. Medeniyet kampanyası Act 0–10 (Babylon dahil her act detay)
9. Meta progresyon, Codex, zorluk modları
10. FTUE / onboarding (ilk 10 dakika saniye saniye)
11. Retention, habit, viral, bildirimler (etik)
12. Sosyal / lonca / paylaşım
13. Monetizasyon (IAP, reklam, battle pass) — etik kuzey yıldızı
14. Live ops takvimi
15. UI/UX hub, kontroller
16. Teknik/üretim kapsamı, launch kesiti
17. SWOT, riskler, odak öncelikleri
18. KPI'lar ve başarı kriterleri
19. Açık sorular / sonraki adımlar
Ek A. Terimler sözlüğü
Ek B. Do / Don't kontrol listesi (üretim boyunca kullanılacak)
Ek C. Kaynak haritası

---

## 0. Yönetici özeti / vizyon / tek cümle pitch

### 0.1 Tek cümle pitch

> **Age of Mythology'nin medeniyet + tanrısal güç fantazisini, Kingdom Rush'ın sıkı hero/wave juice'u ve Bloons TD 6'nın adil derinliğiyle birleştiren; ama her seviyesi gerçek kale/ordu araştırmasına dayanan, on bir tarihsel medeniyeti tek çatı altında toplayan bir kule savunma (tower defence) oyunu — reskin değil, gacha stamina MMO değil.**

Bu cümle üç rakip kategorisine karşı konumlanır: (1) fantastik KR-klonu kule savunmaları (tema süsleme, mekanik aynı), (2) tek-medeniyet "Egypt/Rome skin" mobil TD'ler (mekanik farksız, tema tek kullanımlık), (3) gacha/stamina midcore hibritler (Watcher of Realms, Whiteout Survival tarzı — P2W güç eğrisi, whale ekonomisi). Pazarda **çok-medeniyetli, sistemsel olarak farklı kule dilbilgisine sahip, adil monetize edilmiş, tarihsel kanıt disiplinine sahip** bir TD **yoktur** — bu boşluk (whitespace) ürünün ticari tezidir (`GDD-COMPETITORS.md` §11).

### 0.2 Vizyon (3 yıllık ufuk)

**Yıl 1:** Babylon (Act 0 tutorial) + 3–4 lansman medeniyeti derinlemesine işlenmiş, adil F2P + premium PC hibrit, haftalık boss + sezonluk medeniyet spotlight'ı ile canlı operasyon (live ops) ritmi kurulmuş bir ürün. Hedef: strateji midcore'da üst-çeyrek retention, "no energy / fair / historical" itibar rozetlerini kazanmak.

**Yıl 2:** Kalan 6–7 medeniyet sezonlarla drip edilir (Mongol, European Castle, Japan, China, Viking, Maya–Mexica, Greece rotasyonu — bkz. §14). Async lonca (guild) sistemi, co-op boss, museum/codex eğitim yan-USP'si olgunlaşır. Attacker-faction kampanyaları (örn. Mongol'un Avrupa kalesine saldırması) opsiyonel mod olarak eklenir.

**Yıl 3+:** Tam roster canlı, endless/roguelike modları, community challenge editor, olası PC/Steam DLC modeli, eğitim/müze ortaklıkları (okul lisansı gibi uzun kuyruk fırsatlar).

### 0.3 Neden şimdi (piyasa penceresi)

- Age of Mythology Remake (2020'ler) medeniyet + tanrı güçleri fantazisine kültürel ilgiyi tazeledi (`GDD-COMPETITORS.md` §11).
- Klasik TD (BTD6, KR) hâlâ eleştiri/goodwill zirvesinde ama tema tekrarına düşüyor (yeni SKU = yeni retention dalgası, live-ops derinliği zayıf).
- Tarihsel temalı mobil TD'ler ("Defense of Egypt", "Grow Empire: Rome") temayı **süsleme** olarak kullanıyor; medeniyet kimliği mekanik olarak fark etmiyor — bu bizim ana farklılaşma eksenimiz.
- Oyuncu sentiment (Reddit, mağaza yorumları) enerji kapıları, agresif reklam ve pay-to-win kahramanlara karşı yorgun; "adil, no-energy, tarihsel açıdan araştırılmış" konumlama şu an **ödül görüyor**, ceza görmüyor (`GDD-REDDIT-DEEP.md` §7).

### 0.4 Ürün tezleri (non-negotiable — tasarım anayasası)

1. **Yetenek ürünüdür (skill is the product).** Parayla asla zafer satın alınmaz; sadece güzellik, kolaylık ve erken erişim satılır.
2. **Enerji kapısı yok.** Kampanyada asla bekleme/stamina duvarı yok — gün 0'dan itibaren.
3. **Kozmetik/kolaylık > P2W güç.** Her IAP "savaşı etkiler mi: evet/hayır" testinden geçmeli; "hayır" olmalı.
4. **Rewarded ads opt-in, forced interstitial yok.** Reklam asla dayatılmaz, her zaman ödüllü ve isteğe bağlıdır.
5. **Kamera: kilitli yüksek-oblik ¾ (locked high-oblique ¾), pan+zoom.** Serbest orbit veya PvZ yan görünüm değil.
6. **Evidence vs FOLK etiketleri.** Her tarihsel iddia ARCH/LIT/FOLK/SPN/MOD/CHR etiketiyle işaretlenir; mit sessizce gerçek gibi satılmaz.
7. **Maya ≠ Aztek.** İki farklı medeniyet, iki farklı panteon, iki farklı 7-katman kale dilbilgisi — asla tek "Mesoamerikan" çorbasına indirilmez.
8. **Lansman kapsamı = tam rosterin alt kümesi, kalanı drip.** 11 medeniyeti günü birde göndermek üretim intiharıdır; Babylon + 2–3 medeniyet derinlemesine, kalan sezonlarla.
9. **Babylon Act 0 = tutorial.** Etemenanki ziggurat'ı, oyunun 7-katman kale dilbilgisini öğretir; sonraki her medeniyet bu dilbilgiyi yeniden karıştırır (remix).

### 0.5 Bu doküman nasıl okunur

Bu GDD, 16 ayrı araştırma dosyasının (`docs/_research/GDD-*.md`) ve 11 medeniyetlik tarihsel araştırma kütüphanesinin **tek bir tutarlı tasarım anlatısına** sentezidir. Her bölüm, hangi araştırma dosyasından/dosyalarından beslendiğini belirtir. Sayısal dengeler (HP, gold, süre) **tasarım hedefleridir**, playtest telemetrisiyle kesinleşecektir. `GDD-4CHAN-FORUM-SENTIMENT.md` deposunda bulunmadı; bu doküman onun yerine `GDD-REDDIT-DEEP.md`, `GDD-REDDIT-SENTIMENT.md` ve `GDD-STORE-REVIEWS.md`'nin geniş örtüşen sentiment sinyaline dayanır — bu bir varsayımdır ve not edilmiştir.

---

## 1. Ürün kimliği, fantazi, hedef kitle, platform

*(Kaynak: `GDD-COMPETITORS.md`, `GDD-COMPETITORS-TD.md`, `GDD-CIV-HOOKS.md`, `GDD-RISKS-SWOT-SCOPE.md`)*

### 1.1 Ürün kimliği bir cümlede

Oyuncu, gerçek bir medeniyetin komutanı/duvar bekçisidir; kale mimarisi araştırmaya dayalı, düşman dalgaları o medeniyetin gerçek düşman doktrinlerini taşır, mit ve efsane ise **etiketli, isteğe bağlı bir katman** olarak var olur — asla varsayılan gerçeklik gibi satılmaz.

### 1.2 Fantazi katmanları

| Katman | Ne | Örnek |
|---|---|---|
| **Yer (Place)** | Anıtsal kale/tapınak siluetiyle "burası gerçek" hissi | Etemenanki, Akropolis, Himeji, Templo Mayor |
| **Doktrin (Doctrine)** | Medeniyetin savunma/saldırı mantığı mekanik olarak farklı | Roma mühendisliği, Moğol sahte ricat, Japon masugata |
| **Kimlik (Identity)** | Oyuncunun "benim Roma'm", "benim Himeji'm" duygusu | Layout hafızası, komutan ismi, mastery yıldızları |
| **Mit (Myth, opsiyonel)** | Etiketli, açılabilir fantastik güç katmanı | Marduk kozmik vuruş, Amaterasu meşruiyet ışığı |
| **Eğitim (Codex)** | "Bugün bir şey öğrendim" paylaşım anı | Kazı vs efsane kartları |

### 1.3 Hedef kitle segmentasyonu

`GDD-REDDIT-DEEP.md` §10'daki persona haritası ürün önceliklerini belirler:

| Persona | İster | Terk eder eğer | Evanjelist olur eğer |
|---|---|---|---|
| **A — "CHIMPS beyni"** (BTD göçmeni) | Adil zor mod, pay-skip yok, derin build | Enerji, P2W, sığ harita | Zorlu opsiyonel challenge + şeffaf patch |
| **B — "KR juicer"** (kampanya yıldız avcısı) | Hero, juice, 3-yıldız kovalama | Mobil paywall, adaletsiz iron spike | Unutulmaz boss + mizah + adil hero unlock |
| **C — "PvZ romantiği"** | Charm, almanac, adil macera | EA tarzı reklam/IAP sarmalı | Ton + baskısız koleksiyon lore'u |
| **D — "Tarih denetçisi"** | Otantiklik, tutarlı çağlar, dürüst Codex | Boynuzlu miğfer gerçek diye satılırsa | "Bir şey öğrendim" + etiketli mit DLC |
| **E — "Mobil kazuel muhasır"** | 10 dakikalık seans, net hedef, spam yok | Reklam, enerji, kafa karıştırıcı UI | "Premium his, offline, adil" |

Ürün, A–D'yi tatmin etmeden E'yi balina gibi kapana kıstırmaya **çalışmaz**. Bu beş persona birbiriyle çelişmez — hepsi "adil + derin + otantik" ürününü ister.

### 1.4 Platform stratejisi

| Yüzey | Model | Rol |
|---|---|---|
| **Mobil (iOS/Android)** | F2P, kozmetik/battle pass ekonomisi | Ana kullanıcı hacmi, viral yayılım |
| **PC/Steam** | Premium (~$14.99) veya "Commander Pack" ile aynı ekonomi | İtibar kalkanı, mid-core/hobbyist topluluk, inceleme güvenilirliği |
| **Tablet/foldable** | Aynı kamera, geniş varsayılan çerçeve | Uzun seans konforu |
| **Konsol (post-launch değerlendirme)** | Aynı ekonomi paritesi ilkesiyle | Kingdom Rush/BTD6 emsali başarılı |

**Paritenin ahlaki statüsü:** `GDD-REDDIT-DEEP.md` Tema H-KR-01 açıkça gösteriyor ki mobil-PC arası hero/tower paywall farkı ("buyium") en büyük güven kırıcı temalardan biri. Bu üründe **platformlar arası içerik paritesi bir özellik değil, bir ahlaki taahhüttür** — aynı kazanma/satın alma kuralları her yerde geçerli.

### 1.5 Ton ve marka sesi

- **Vakayiname (chronicle) tonu:** "Duvar tuttu." değil "ez" veya modern flame slangı.
- **Mizah:** Kingdom Rush'ın sıcak esprisi + tarihsel gurur, alaycı/ırkçı klişe değil.
- **Pazarlama dili:** "Araştırılmış", "adil", "no energy", "gerçek gameplay = fragman" — asla "daha bağımlılık yapıcı" veya kumar metaforu.
- **Kültürel saygı taahhüdü:** Yaşayan kültürlerin atalarını karikatürize etmeyiz; kutsal/ritüel içerik kozmetik özenle ele alınır, şok SKU'su olarak asla monetize edilmez (`GDD-MONETIZATION.md` §16, `GDD-LIVEOPS-FTUE-META.md` §6).

### 1.6 Neden "historical civ TD" ve neden "Babylon'dan başlıyoruz"

Araştırma kütüphanesi 11 medeniyeti derinlemesine kapsıyor (Roma, Pers, Mısır, Çin, Maya–Mexica, Viking, Yunan, Japonya, Moğol, Avrupa Kalesi + Babylon). Babylon'un depoda kendi araştırma dosyası **yoktu** (`GDD-CIV-HOOKS.md`'de "FLAG: needs-doc" olarak işaretliydi) ve bu boşluk `GDD-BABYLON-ACT0.md` ile dolduruldu — kamuya açık arkeoloji/Asurbilim konsensüsüne (Koldewey kazıları, Etemenanki, İştar Kapısı) dayanan, ARCH-öncelikli bir tasarım. Babylon'un **7 katmanlı işlenmiş taş kule** metaforu (klasik/Herodot kaynaklı "7 teras" okuması — LIT/MOD etiketli), oyunun paylaşılan kale dilbilgisini öğretmek için ideal bir tutorial zeminidir: basit, ikonik, mit-tarih ayrımını gösterişli değil doğal şekilde öğretiyor (Cyrus'un çift-anlatımlı fetih efsanesi gibi).

---

## 2. Rakip analizi ve konumlandırma

*(Kaynak: `GDD-COMPETITORS.md`, `GDD-COMPETITORS-TD.md`)*

### 2.1 Neden rakip analizi bu kadar ayrıntılı

Bu tür bir ürün, hem "klasik premium TD" hem "midcore F2P hibrit" hem de "tarihsel skin TD" kategorilerinin kesişiminde yaşıyor. Yanlış rakip setini incelemek yanlış dersler çıkarır (örn. Arknights'ın Sanity mekaniğini kopyalamak, ya da Grow Empire'ın gem-gated endgame'ini kopyalamak — ikisi de bu ürün için toksiktir).

### 2.2 Ana rakip profilleri (derinlemesine)

#### 2.2.1 Kingdom Rush serisi (Ironhide) — cins altın standardı

- **Çekirdek fantazi:** Linirea'yı (ve sonraki settinglerde Frontiers ormanları, Origins ormanları, Vengeance kötü kahraman kampanyası) fantastik ortaçağ kuleleri + kontrol edilebilir kahramanlarla savunma. Mizah, seslendirme, kişilikli bosslar.
- **Kamera:** Sabit izometrik 2.5D; el-boyalı haritalar; serbest 3D orbit yok. Bu ürünün kamera kararının temel emsali (bkz. §5).
- **Monetizasyon:** Klasik olarak premium ($0.99–$5) + opsiyonel hero IAP. Mobil ücretsiz remasterlarda reklam+gem mağazası; "çok reklam izle ya da öde" sürtünmesi eski oyunlarda raporlanıyor.
- **Retention kancaları:** Kampanya yıldızları + Iron/Heroic/Impossible zorluklar; başarımlar, easter egg, boss dövüşleri, hero unlock'ları; sequel kadansı (yeni oyun = yeni retention dalgası) daha çok live-ops'tan.
- **Klonlardan daha iyi olduğu yerler:** Harita başına bir "trick" (choke, çift giriş, uçan düşman, zırhlı diken); kışlaların canlı blokerler olarak kullanımı (sadece DPS kulesi değil); mizah + cila; skill'e saygılı zorluk (Iron modları) zorunlu harcama olmadan.
- **Övgü:** "En iyi gerçek TD"; cilalı; komik; zor ama adil; offline; P2W değil (klasik); heroların mikro getirmesi ama tam RTS'e dönüşmemesi.
- **Şikayet:** Vengeance'ta tower IAP optiği; RNG yetenek tetiklemesi; öncekilere göre az kampanya haritası; mobil ücretsiz sürümlerde reklam yorgunluğu, gem fiyatlandırması, geç seviye ödeme/reklam baskısı; Steam'de Frontiers'ta crash + pahalı DLC hissi; modern live-ops derinliği eksik.
- **Bizim için ders:** KR'nin **harita ustalığı + hero agency + mizah güveni**ni al. Vengeance'ın "kule için öde" optiğinden kaçın. Premium PC + adil mobil, hâlâ çalışan Ironhide yolu.

#### 2.2.2 Bloons TD 6 (Ninja Kiwi) — premium derinlik kralı

- **Çekirdek fantazi:** Maymunlar vs balonlar. Şaka gibi, şiddet-kodlanmamış. Ustalık fantazisi: loadout optimize et, Paragon'lar, CHIMPS, Boss Event'ler. Anime operatör değil, oyuncak koleksiyonu gibi kule/hero.
- **Kamera:** Top-down 2D haritalar; zoom/pan; net path okunabilirliği. Mobil+PC parite; co-op harita bölgelerini paylaşır.
- **Monetizasyon:** Peşin satın alma (~$5–7, dalgalanır) + opsiyonel IAP (Monkey Money, kozmetikler, kolaylık). **Reklam yok.** Ücretsiz oynanabilir (harcamasız tamamlanabilir). Netflix Games dağıtımı kurulumları artırdı.
- **Retention kancaları:** 70+ harita; Monkey Knowledge meta ağacı; hero XP'si; günlük/ileri challenge'lar; Boss Event'ler; Contested Territory; co-op; Paragon endgame hedefleri; Rogue Legends/story expansion'lar ücretli içerik derinliği olarak; yıllar boyunca 45+ büyük güncelleme — içerik kadansı retention ürünü olarak.
- **Klonlardan daha iyi olduğu yerler:** Üç yol upgrade özgünlüğü gerçek build kimliği yaratır; hedefleme önceliği + mikro skill tavanı olarak; toplulukça challenge/creator kültürü PvP toksisitesi olmadan meta'yı canlı tutuyor; adil premium model = inceleme kalkanı.
- **Övgü:** Derinlik; müdahaleci olmayan reklam yok; güncellemeler; co-op; parasına değer; strateji topluluğu kıstası.
- **Şikayet:** Zorunlu online güncelleme kontrolleri "offline" vaadini kırıyor; geç round (200+) lag; ücretli oyun içinde IAP (prensip itirazı); yeni gelenler için karmaşıklık sıçraması; DLC fiyatlandırması karışık.
- **Bizim için ders:** **Güncelleme kadansı + build kimliği + premium güven** tema klonlarını yener. Hibritse: kampanyayı PC'de bir kez sat; mobili adil tut; asla temel kuleleri gacha'nın arkasına kilitleme.

#### 2.2.3 Plants vs. Zombies (1/2/3 yayı) — IP şeridinde TD

- **Çekirdek fantazi:** Banliyö çimeni / dünya turu şeritleri: bitkiler kule, zombiler yaratık. Mizah, koleksiyonlanabilir bitki fantazisi. PvZ1 = premium bulmaca-TD klasiği. PvZ2 = eralarla (Antik Mısır, Korsan Denizleri vb. — **kozmetik tarih, otantik medeniyet sistemi değil**) F2P canlı servis.
- **Kamera:** Yan görünüm şeritler (sol bitki / sağ zombi). Bu ürünün "primary kamera" değil, "tutorial/side-mode" adayı olan emsali (bkz. §5.2).
- **Monetizasyon:** PvZ1 premium (goodwill zirvesi). PvZ2: F2P — coin/gem, Plant Food, premium bitkiler, bitki leveling RPG katmanı, rewarded ads; sonra reklam kaldırma **abonelik** olarak (büyük şikayet). PvZ3: enerji sistemleri, battle pass, loot-box benzeri desenler, tekrarlayan tepki, revizyonlar.
- **Retention kancaları:** Dünya haritası ilerlemesi; günlük Piñata parti; Arena; Penny's Pursuit; sezonluk etkinlikler (Feastivus); bitki koleksiyonu/kostüm/mastery seviyeleme; marka nostaljisi reaktivasyonları.
- **Klonlardan daha iyi olduğu yerler:** Karakter mizahı + okunabilir combat; dünya jimmick'leri (madenci arabaları, kayan bitkiler) sistemleri seviye tasarımıyla öğretiyor; aile-dostu erişim.
- **Övgü:** Çekirdek taktik döngü hâlâ eğlenceli; sanat (özellikle PvZ2); shop görmezden gelinirse büyük ücretsiz içerik; L1 ücretsiz bitkiler için dengelenmiş kampanya iddiası (skill > harcama).
- **Şikayet:** Agresif reklam; bitki seviyelerinden P2W optiği; grind; rekabetçi modlarda hile; durgun güncellemeler; bulut kayıt eksikliği; kamera pan pacing'i sıkıcı.
- **Bizim için ders:** **Tarihi PvZ2'nin "dünya skinleri" olarak KULLANMA.** Mısır/Roma görünürse birimler/taktikler mekanik olarak farklı olmalı. Monetizasyon skill TD'yi cüzdan RPG'sine çevirmemeli. Şerit okunabilirliği altın; abonelik reklam kaldırma inceleme için zehir.

#### 2.2.4 Defense Grid (The Awakening / DG2) — PC labirent-TD referansı

- **Çekirdek fantazi:** Bilim-kurgu üs: uzaylılar **enerji çekirdeklerini** çalıyor. Kuleler yolu yeniden şekillendiriyor (labirent). Harcanmamış kaynağa faiz; verimlilik madalyaları. Anlatıcı AI eşlikçisi. Saf strateji, düşük koleksiyon.
- **Kamera:** Gerçek 3D; izometrik-benzeri, zoom seviyeleriyle; pan-ağırlıklı. DG1: dürbün-merkezli mouse pan garip; sınırlı zoom-out sinir bozucu. DG2: daha iyi WASD/rotasyon QoL. **Bu, serbest orbit kamerayı primary yapmamamız için ana negatif emsal (§5.2).**
- **Monetizasyon:** Premium PC/console (~$20 lansman) + ücretli DLC harita paketleri. Mobil F2P yok. Leaderboard/başarımlar meta olarak.
- **Retention kancaları:** Harita başına challenge modifikatörleri; madalyalar; hız artırma; checkpoint; DLC kampanyaları; "gold no-leak" ustalık koşuları; faiz ekonomisi teorileştirme.
- **Klonlardan daha iyi olduğu yerler:** **Çekirdek-taşıma kaybetme koşulu** (öldükten sonra alma odasında sızma riski) benzersiz gerilim; labirent pathing birincil skill, sadece DPS istiflemesi değil; minimal sayılar (grafik/renk) — okunabilirlik dahil olma için; ilk kule yerleştirilmeden görev başlamıyor → planlama fazı.
- **Övgü:** Stratejik derinlik; yeniden oynanabilirlik; QoL (dalga önizleme, sat, hız); tarihsel olarak lansman sonrası ücretsiz yamalar.
- **Şikayet:** Garip kamera/kontroller; zorluk sıçramaları; deneme-hata pathing; jenerik kule/düşman görselleri; zayıf SFX; eskiyen teknoloji/uyumluluk; kule dengesi aykırı değerleri; bazıları için anlatıcı can sıkıcı.
- **Bizim için ders:** **Labirent agency + planlama fazı + verimlilik puanlaması**nı çal. Kamerayı hibrit için düzelt (PC'de orbit + tam-harita göz atma zorunlu; mobilde kısıtlı pan). Bilim-kurgu görünüm değiştirilebilir; çekirdek-hırsızlığı fantazisi tarihsel ayarlara "kalıntı/standart/idol" olarak mükemmel haritalanır.

#### 2.2.5 Arknights (Hypergryph / Yostar) — TD-bitişik gacha zirvesi

- **Çekirdek fantazi:** Distopik Terra'da grid haritalarda Operatörleri (karakter-olarak-kule) konuşlandır. Blok sayısı, DP maliyeti, yeniden konuşlandırma zamanlayıcıları, yetenekler. Hikaye ağırlıklı Doktor fantazisi. Strateji bulmacası > autofire.
- **Monetizasyon:** F2P gacha (Headhunting); aylık kart; skinler; Originium → Orundum. Görece F2P-dostu itibar: düşük-rarity oynanabilir; pity; sınırlı banner. **Sanity** stamina oturumları kısıtlıyor; malzeme çiftçiliği gerçek vergi.
- **Bizim için ders:** **Karakter fantazisi çalışır eğer birimler mekanik olarak farklı ve F2P-oynanabilirse.** Sanity'yi Batı kazuel-midcore hedefliyorsak kopyalama. Stage bulmaca tasarımı + event modlarını çal; sadece-balina operatörleri atla. Tarihsel hero/tanrılar "operatör" olarak sadece roster kazanılabilirse çalışır.

#### 2.2.6 Iron Marines (+ Invasion) — Ironhide RTS kuzeni

- **Çekirdek fantazi:** Bilim-kurgu gerçek-zamanlı taktik: hero + squad + turret + orbital strike. "Hattı tutmanın" ötesinde görev hedefleri.
- **Bizim için ders:** Oyuncunun **sürdüğü** heroalar (KR/IM) pasif heykellerden daha iyi. PC build'de "mobil ekonomi yara izlerinden" arınmış tut; tablet/PC kamera alanı hibrit için önemli.

#### 2.2.7 Popüler mobil tarihsel/"antik" TD'ler — başarısızlık galerisi

- **Empire Warriors:** KR-klon silueti, F2P reklam+IAP, "tema tek kullanımlık, oyuncular KR dilbilgisini kabul ediyor" sinyali.
- **Defense of Egypt/Greece/Roman Britain serisi (First Games Interactive):** Skin-paket tarih — Mısır vs Roma, Yunan + steampunk mekler. **Sinyal: tarihsel tema tek başına bir USP değil.** Mek-Yunan karışımları temanın süsleme olduğunu kanıtlıyor.
- **Grow Empire: Rome:** Sezar ol — duvar savunma dalgaları + şehir fetih haritası hibriti. Gerçek faksiyon isimleri (Galya, Kartaca, İberya) düşman lezzeti olarak. Reklam ağırlıklı (~%77 reklam geliri tahmini bazı trackerlarda) + gem IAP. **Ders: meta-harita fetih yapışkan; endgame'i gem'in arkasına hapsetme.**
- **Ancient TD (Steam, 2025):** Mitoloji-ilhamlı co-op TD; ucuz premium ($5). **Sinyal: mit + co-op aktif bir indie şerit, çok-medeniyetli tarihsel otantiklik değil.**
- **Song of Slavs / folklor Kingdom-benzerleri:** Slav miti yerleşim savunması; kültürel binalar (hamam). **Tez desteği: kültürel otantiklik satar, mekanikler kültürü ifade ederse.**

### 2.3 Karşılaştırma matrisi (dense)

| Başlık | Fantazi | Kamera | Monetizasyon | Retention | Klonlara karşı hendek | Kopyalama riski |
|---|---|---|---|---|---|---|
| KR serisi | Fantastik mizahlı savunma | Sabit izo 2.5D | Premium+IAP | Yıldız/Iron/sequel'ler | Harita hileleri, VO | Kule IAP optiği |
| BTD6 | Maymun/balon ustalığı | Top-down 2D | Premium+hafif IAP | Güncelleme/boss/co-op | Yol upgrade'leri | Zorunlu online |
| PvZ2 | Bitki vs zombi komedisi | Yan şeritler | F2P reklam+IAP | Etkinlik/arena | IP+jimmick | Güven zararı |
| Defense Grid | Bilim-kurgu çekirdek hırsızlığı | 3D orbit/pan | Premium+DLC | Madalya/challenge | Labirent+faiz | Garip kamera |
| Arknights | Anime operatör taktiği | Grid stage | Gacha F2P | Etkinlik/IS/CC | Bulmaca derinliği | Sanity/farm |
| Iron Marines | Bilim-kurgu RTS hero | Top-down | Premium+hero | Görev/upgrade | Görev çeşitliliği | Grind yara izi |
| Empire Warriors | KR-benzeri fantastik | KR izo | F2P reklam+IAP | Hero/rune | Meta derinlik | Klon stigması |
| Defense of Egypt/Greece | Skin tarih | Standart TD | Ucuz F2P/prem | Seviye sayısı | Sadece tema | Sığ |
| Grow Empire Rome | Sezar fetih | Duvar yan+harita | Reklam+gem | Dalga+şehir | Faksiyon isimleri | Tekrar/gem |
| Clash Royale | PvP şerit düellosu | Arena şeritleri | F2P ilerleme | Sandık/klan | Netlik+PvP | Seviye P2W hissi |

### 2.4 Farklılaşma cümlesi (Türkçe rakip özetinden)

> "Bloons'un adil derinliği + Kingdom Rush'ın hero/wave juice'u + PvZ'nin almanac büyüsü — ama her seviye gerçek kale/ordu araştırmasına dayanan tarihsel medeniyet kuleleri." (`GDD-COMPETITORS-TD.md` §H)

### 2.5 Pazar boşluğu (whitespace) — ürünün ticari tezi

`GDD-COMPETITORS.md` §11'e göre **yoktur**:

1. Rome, Egypt, Persia, China, Maya gibi medeniyetlerin **kozmetik olmayan** kule dilbilgisine sahip **çok-medeniyetli roster** (formasyon bloğu, savaş arabası geçişi, kuşatma mühendisliği, asker dalgası).
2. **Mit modüler bir güç katmanı** olarak tarihsel taban üzerinde (AoM Favor/Tanrı güçleri) — piramit skinli rastgele fantastik ork değil.
3. **Saldırgan medeniyet kimliği** (örn. Avrupa kalesine karşı Moğol akın baskısı) sistemsel olarak, palet değişimi değil.
4. **Hibrit PC+mobil**, KR/BTD cilası **ve** creator/tarih-YouTuber pazarlaması için yeterince araştırılmış tarihsel otantiklikle.
5. Bu nişte adil monetizasyon (premium PC kampanya; medeniyet kulelerini kapatmayan mobil F2P).
6. Edutainment çamuruna dönüşmeyen Codex/eğitim yumuşak USP'si.
7. Co-op mit bossları + solo kampanya ustalığı (BTD boss event'leri × AoM panteonları).

### 2.6 Rakiplerden çal / çalma listesi (özet, `GDD-COMPETITORS.md` §17)

| Rakip | Çal | Çalma |
|---|---|---|
| Kingdom Rush | Harita hileleri, kışla bloklayıcıları, hero mikro, VO mizahı, Iron modları | Ödemeli temel kuleler; reklam-kapılı retry |
| Bloons TD 6 | Upgrade yol kimliği, challenge modları, güncelleme ritüeli, co-op (sonra) | Gün-1 mobilde şişkin UI; zorunlu her-zaman-online |
| PvZ | Siluet komedisi, dünya jimmick öğretimi | Tarih tema-parkı skini; bitki-seviye P2W; reklam abonelikleri |
| Defense Grid | Labirentleme, çekirdek hırsızlığı, faiz/verimlilik puanı, planlama fazı | Kötü pan kamera; kuru bilim-kurgu marka tarihselse |
| Arknights | Konuşlandırma maliyeti, bloklama kuralları, event modları, F2P-oynanabilir kitler | Sanity; zorunlu VN; sadece-gacha mit tanrıları |
| Iron Marines | Görev çeşitliliği, aktif heroler, cila | PC'de mobil ekonomi yara izleri; $7 tek hero paketleri |
| Grow Empire Rome | Fetih meta'sı, kısa dalgalar, gerçek etnonimler | Geç oyunda gem kapısı; saf tekrar duvarı |

---

## 3. Oyuncu sentiment (Reddit + 4chan + mağaza) → ürün kuralları

*(Kaynak: `GDD-REDDIT-SENTIMENT.md`, `GDD-REDDIT-DEEP.md`, `GDD-STORE-REVIEWS.md`. Not: `GDD-4CHAN-FORUM-SENTIMENT.md` depoda mevcut değildi; bu bölüm yerine derin Reddit + mağaza yorumu analizinin geniş örtüşen temalarına dayanır — bu bir açık varsayımdır.)*

### 3.1 Metodoloji ve güven sınırları

Bu bölüm istatistiksel örneklenmiş bir Reddit taraması iddia etmiyor. Temalar, **yıllar boyunca topluluk tartışmalarında tekrar eden anlatı desenleridir**. Her tema şu şekilde okunmalı:

- **Kararlı (stable)** — başlıklar ve yıllar arasında tekrarlıyorsa (örn. "enerji kapılarından nefret ederim").
- **Başlığa özgü (title-specific)** — bir franchise etrafında kümeleniyorsa (örn. KR mobil hero paywall'ı).
- **Yükselen risk (rising risk)** — eski "adil" başlıklar daha fazla IAP'ye kayıyorsa (örn. BTD6'nın geç dönem kozmetik/DLC tartışmaları).

### 3.2 En çok SEVİLENler (yap / koru)

`GDD-REDDIT-SENTIMENT.md` §1 ve `GDD-REDDIT-DEEP.md` §1.7 çapraz-sentezi:

1. **Adil ilerleme** — skill ile zor içerik açılır (BTD6 CHIMPS övgüsü: "hiçbir güç satın almadan her şeyi temizleyebilirsin").
2. **Derinlik + build çeşitliliği** — her koşu farklı strateji; üç-yol upgrade'ler tartışma yakıtı (tier list'ler, "bir kuleyi ele" anketleri).
3. **Polish / juice / SFX** — KR ve BTD6 "feel"i; oyuncular "feel"i dengeden bile daha çok anıyor.
4. **Boss event'ler & dönen challenge'lar** — "neden bugün açtım", "sadece bu boss'u bitireyim".
5. **QoL** — hız kontrolü, freeplay, sandbox-benzeri challenge oluşturma, koleksiyon UX'i.
6. **Co-op / sosyal opsiyonel** — zorla değil; single-player ustalığı birinci sınıf kalıyor.
7. **Geliştirici iletişimi** — patch notları, denge, meme etkileşimi; sessizlik toksik.
8. **No energy** — tekrar tekrar vurgulanır; jenerik mobil TD çamurundan ayrışmanın en net yolu.
9. **Cosmetic-only IAP** — saygı görür.
10. **Map editor / challenge browser** — uzun kuyruk içerik, guide ekosistemi canlı tutar.

### 3.3 En çok ŞİKÂYET (kaçın)

1. **Energy/stamina kapıları** — evrensel nefret; "bekleyemeden oynayamıyorsun", "ev ödevi simülatörü".
2. **Her seviye arası zorunlu (forced) reklamlar** — özellikle ölümden sonra; öfke-uninstall döngüleri.
3. **Skill'i atlatan P2W gear/gacha.**
4. **Ödeme duvarlı (paywall) kampanyalar** (KR mobil eski şikayetleri).
5. **Yanıltıcı soft-launch reklamları** (sahte gameplay fragmanı — puzzle/merge gameplay'i gerçek oyun değilken).
6. **Bildirim spam'i.**
7. **Yırtıcı battle pass FOMO'su** + sadece-güç $100 skinler.
8. **Yamasız haftalarca kırık denge.**
9. **Zorunlu PvP'de toksisite.**
10. **Etiketlenmeden yanlış çizilmiş tarihsel oyunlar** (niş ama eğitimli oyuncu kitlesi için — boynuzlu Viking miğferi gerçek diye sunulursa).

### 3.4 "Addiction" mekanikleri — oyuncuların pozitif vs negatif çerçevesi

`GDD-REDDIT-SENTIMENT.md` §3 ve `GDD-REDDIT-DEEP.md` §2.1–2.2 birleşimi:

**Pozitif çerçeve (sağlıklı habit dili — koru):**
- "bir tur daha" (one more round/wave)
- "sadece bu boss'u bitireyim"
- "günlük tamam" (daily done)
- "bu haritayı 3-yıldızlayayım"
- koleksiyon tamamlama çubuğu
- klan sandığı doldurma

**Negatif çerçeve (dark pattern dili — asla üretme):**
- "bekleyemeden oynayamıyorum"
- "hileli gacha" (rigged gacha)
- "ödeme yap ya da acı çek" (pay or suffer)
- "ev ödevi simülatörü"
- "zaten reklamsız için ödemiştim" (broken trust — EA'nin PvZ2 aylık reklam kaldırma aboneliği örneği)
- "küçük-ölçekli test" temel sistemler üzerinde (PopCap'ın 2014 çim biçme makinesi IAP testi — geri çekildi ama topluluk asla unutmadı)

**Ürün kararı:** İçeride "habit loops / mastery loops / social light loops" dilini kullan; dışarıda "derin strateji", "adil challenge", "tarihsel kampanyalar" dilini kullan. **Asla** "daha bağımlılık yapıcı" veya slot makinesi metaforu pazarla.

### 3.5 Tarihsel oyunlarda otantiklik şikayetleri — derin analiz

`GDD-REDDIT-DEEP.md` §3, Total War/AoE topluluk davranışından türetilmiş:

**Doğruluk vs otantiklik ayrımı:** Creative Assembly / BBC kapsamı (2026) tasarımcıların **otantiklik** ("dönemin hissi") kavramını **doğruluk**a ("tam gerçeklik") tercih ettiğini gösteriyor, çünkü tam doğruluk eğlenceyi öldürebilir. Ama oyuncular hâlâ şunları denetler:

| Şikayet kümesi | Örnek | Ürün kuralı |
|---|---|---|
| **Ekipman zaman çizgisi karışımı** | Tricorne + Pickelhaube aynı upgrade katmanında | Çağ kilitleri (era locks) + etiketler |
| **Yanlış kültür/model** | "Kule gösterişli görünüyor" diye ilgisiz kültür asseti kullanmak | Her medeniyet için referans föyleri |
| **Kapsam tutarsızlığı** | Templar'lar + Lancastrian'lar aynı "civ" seviyesinde | Yazılı çağ çerçevesi UI'da ("500 BCE–200 CE Akdeniz kuşatmaları") |
| **Henüz var olmaması gereken teknoloji** | Saf Bronz Çağı'na barut, yanlış yüzyıl süvarisine üzengi | Anakronizm önleme; "geç imparatorluk teknolojisi" etiketi |
| **Yanlış manzara/atmosfer** | TW Pharaoh: "çok fazla kum", verimli Bronz Çağı hissi eksik | Çevre otantikliği |
| **Ölümsüz karakter/plot armor** | Tarih hayranları ölümlülük, lojistik, mevsim ister | Season/supply/disease modifikatörleri |
| **Etiketlenmemiş mit vs tarih** | Boynuzlu Viking miğferi etiketsizse r/historymemes alay yakıtı | Mit/Folklor/Alt-Tarih modu etiketleri |
| **Antik haritaya modern siyaset yansıması** | Attila/Slav yerleşim tartışmaları kültür savaşı magneti oluyor | İsimlendirme/renk/"kime ait" konusunda dikkat |

**Bağışlama koşulları (ne zaman anakronizm kabul edilir):**
- Oyun **etiketler** Mit/What-If/Folklor modlarını.
- Anakronizmler **diegetic şaka** olarak açık tonla (KR fantazisi), sahte ders kitabı gibi değil.
- Gameplay abstraksiyonu açıklanır ("kuleler bölgesel savunma doktrinlerini temsil eder").
- Uzman/danışman kredilendirilir.
- Mod veya Müze Modu pürist oyuncular için var.

**Bağışlanmayan durumlar:**
- Pazarlama "tarihsel olarak doğru" der ama assetler yanlıştır.
- Yaşayan kültürler karikatürize edilir.
- Monetize edilmiş skinler satir çerçevesi olmadan lanetli hibritler icat eder (Roma-samuray gibi).

### 3.6 Aksiyonel ürün kuralları — Do/Don't tablosu (yoğunlaştırılmış özet)

`GDD-REDDIT-DEEP.md` §4'ün tam tablosu (§ referansları korunmuştur):

| Kategori | DO | DON'T |
|---|---|---|
| **Çekirdek oynanış** | Her kampanya haritası standart zorlukta skill'le temizlenebilir olsun | Premium kule/hero'nun arkasına temizleme kapatma |
| **Monetizasyon/platform** | Premium + kozmetik IAP + opsiyonel reklam kaldırma | Enerji kapıları; mobil-özel hero paywall'ı (KR Frontiers deseni) |
| **Reklam/UX/güven** | F2P ise: kozmetik/para için opsiyonel ödüllü reklam | Her ölümden sonra interstitial |
| **Retention/liveops** | Haftalık boss kuşatması + haftalık modifikatör | Güç için FOMO zamanlayıcıları yığmak |
| **Tarihsel otantiklik** | Varsayılan olarak araştırılmış silüet/silah/tahkimat | "%100 doğru" pazarlayıp karton-yanlış göndermek |
| **Zorluk/boss tasarımı** | Habercili boss fazları; okunabilir işaretler | Habersiz rastgele bir-vuruş ölümleri |
| **Sosyal/toksisite** | Opsiyonel co-op; güçlü solo | Hikaye açmak için zorunlu PvP ladder'ları |

### 3.7 Öncelik yığını (çelişki durumunda)

1. **Güven > ARPU sıçraması**
2. **Skill fantazisi > monetizasyon kolaylığı**
3. **Etiketli otantiklik > pazarlama hype'ı**
4. **Opsiyonel ustalık > zorunlu grind**
5. **Platformlar arası parite > mağazaya özel sömürü**

### 3.8 İnceleme anahtar kelime izleme (lansman sonrası)

| Pozitif izleme listesi | Negatif izleme listesi |
|---|---|
| satisfying, fair, strategy, no energy, polished, historical, addictive (olumlu), one more wave, boss, worth the money, dev listens | greedy, pay to win, ads, energy, stamina, rigged, crash, paywall, inaccurate, ai slop, fake ad, uninstall |

**Yanıt SOP'si:** Greedy/paywall şikayeti → kazanma yolunu açıkla; fiyat/parite yamasını düşün; "opsiyonel" diyerek tek başına savunma yapma. Inaccurate şikayeti → errata gönder, denetçiye teşekkür et, savunmacı olma. Ads/energy şikayeti → varsa kaldır — bu ürün tezi için pazarlık konusu değil. Crash → içerikten önce hotfix önceliği.

### 3.9 Sentiment'ten tek sayfa anayasa (`GDD-REDDIT-DEEP.md` §12)

1. Skill üründür. Monetizasyon onu finanse edebilir, asla yerine geçemez.
2. Enerji yok, başarısızlık-reklamı yok, gacha kule yok.
3. Platform paritesi ahlaktır, özellik değil.
4. Varsayılan olarak otantiklik; anahtarla mit.
5. Boss'lar ve challenge'lar sağlıklı "addiction" dili yaratır.
6. Paylaşım araçları ustalığı edinime çevirir.
7. Güven birikir; temel sistemler üzerinde "küçük-ölçekli testler" yıllarca goodwill'i yok eder.
8. Herkesin gözü önünde yama yap (patch in public).
9. Opsiyonel ustalık > zorunlu ev ödevi.
10. Bir kart/çerçeve/zamanlayıcıyı kaldırmak oyunu bozmuyorsa, o zaten karanlık desen süsüydü — sil.

---

## 4. Çekirdek döngü (saniye / dakika / seans / gün / hafta / sezon)

*(Kaynak: `GDD-DEFENSE-ATTACK.md`, `GDD-WAVES-ECONOMY.md`, `GDD-RETENTION-VIRAL.md`, `GDD-ATTACK-WAVES.md`)*

### 4.1 60 saniyelik mikro-döngü (tek dalga)

Her dalga şu altı adımlık nefes döngüsünü izler:

1. **Dalga uyarısı** — intel şeridi: hangi roller, hangi modifikatörler, kaç düşman geliyor gösterilir (habercili, sürpriz-öldüren değil).
2. **Oyuncu hazırlığı** — kule kur / upgrade et / hero yeteneği hazırla; bu faz duraklatılmıştır, acele ettirilmez.
3. **Pathing** — düşmanlar ilerler; zırh/direnç etkileşimi (Pierce/Splash/Armor-Break vb. etiket dilbilgisiyle) devreye girer.
4. **Sonuç anı** — sızma (leak) → Keep Integrity düşer; mükemmel blok → bonus gold/seal şansı.
5. **Dalga temizleme** — gold + yıldız ilerlemesi + kısa "shop beat" (satın alma/upgrade penceresi).
6. **Erken çağırma (early call) opsiyonu** — bir sonraki dalgayı riskle erken tetikleme; +% gold ödülü.

### 4.2 Dakikalık döngü (harita/seviye içi)

Bir standart kampanya haritası **8–14 dakika**, **12–20 dalga** hedefler (bkz. §7.9 tam tablo). Dalga kompozisyonu bir **cümle**dir:

```
[açılış runner'ları] + [ana kuvvet] + [özel birim] + [opsiyonel mini-boss]
```

Eskalasyon eğrisi: W1–3 öğret (tek tip düşman) → W4–7 zırh karışımı → W8–12 kuşatma motorları belirir → orta-boss → final: general + karışık ordu + scripted olay (kapı kırma / yangın).

### 4.3 Seans döngüsü (15–25 dakika hedef)

`GDD-RETENTION-VIRAL.md` §9'daki günlük döngü şablonu:

```
OPEN
  → Kümülatif günlük ödülü ve streak durumunu al (≤10s)
  → Hub'ı gör: medeniyet anıt ilerlemesi + 3 günlük görev + etkinlik banner'ı
CORE (birini seç)
  A. Kampanya/bölge haritası (2–3 deneme)          [stage ilerlemesi]
  B. Etkinlik görevi (1–2 koşu)                     [live-ops]
  C. Kule ustalık drill'i / endless eki             [meta/skill]
BUILDCRAFT
  → Hurdayı 1 upgrade'e harca VEYA kalıntı (relic) tak
  → Koleksiyon %'sini kontrol et (kule/hero/civ)
SOCIAL (opsiyonel, ≤3 dk)
  → Lonca bağışı / yardım / kuşatma dokunuşu
CLOSE
  → "Yarın: günlük sandık + etkinlik görevi 2/3"
  → Opsiyonel: sandık/etkinlik bitişi için sessiz push zamanlayıcısı
```

**Duygu yayı (emotion arc):** Kimlik (benim medeniyetim) → Yeterlilik (choke'u tuttum) → Büyüme (upgrade/koleksiyon) → Ait olma (lonca) → Beklenti (yarın).

### 4.4 Günlük döngü — sistemler

| Sistem | Açıklama |
|---|---|
| **Kümülatif günlük giriş** | Genshin-tarzı takvim, streak-tax değil (bkz. §11.4) |
| **3–5 günlük görev** | ~20–30 dakikada tamamlanır: 2 stage temizle veya 1 zor stage; 1 upgrade yap; N zırhlı/uçan/kuşatma düşmanı öldür (counterplay öğret); 1 etkinlik jetonu topla; opsiyonel lonca yardımı |
| **Sandık kademelemesi** | Günlük → küçük sandık; 3/3 günlük → orta; haftalık board → büyük + kozmetik şansı |

### 4.5 Haftalık döngü

`GDD-RETENTION-VIRAL.md` §10:

| Gün | Oyuncu odağı | Backend/live-ops |
|---|---|---|
| **Pzt** | Haftalık görev tahtası yenilenir; soft para artışı | Denge yaması notu |
| **Sal–Çar** | Kampanya itmesi + ustalık grind'i | Discord'da UGC vitrini |
| **Per** | Hafta-ortası mini-modifikatör (sis/gece akını) | A/B zorluk testi |
| **Cum** | Etkinlik mağazası vitrini; kozmetik önizleme | Creator kit çıkışı |
| **Cmt** | **Lonca kuşatma hafta sonu** açılır | Topluluk raid sesi |
| **Paz** | Kuşatma ödülleri; haftalık sandık; sıradaki civ etkinliği önizlemesi | Analitik retro |

**Haftalık oyuncu yükümlülükleri:** Haftalık board'u tamamla (4/6); bir lonca katkısı; bir etkinlik dokunuşu; opsiyonel bir build/replay paylaşımı.

### 4.6 Sezon döngüsü (6–8 hafta)

Bir sezon = bir medeniyet spotlight'ı + battle pass + Codex kanadı + haritа skini (bkz. §14 tam takvim). Sezon yapısı: (1) lansman fragmanı/hub devralımı → (2) Track A (ücretsiz): sealler, Codex kartları, 1 kule skini → (3) Track B (pass): hero skini, keep restorasyon katmanı, portre, sadece-Arcade mit-etiketli özel birim → (4) sezon görev zinciri (8–12 düğüm) → (5) final: sezon medeniyetine temalı co-op boss.

### 4.7 Neden bu döngü işliyor — habit bilimi uygulaması

`GDD-RETENTION-VIRAL.md` §3.1 Hook Model'in TD'ye haritalanması:

| Adım | TD haritalaması | Tarihsel-civ lezzeti |
|---|---|---|
| **Tetikleyici (Trigger)** | Push/rozet/Discord/risk altında streak/lonca ping'i | "Duvarına gece akını", "[Civ] Festivali 6 saatte bitiyor" |
| **Aksiyon (Action)** | 1–3 dalga temizle/görev al/kule konuşlandır | *ballista/phalanx/turtle ship* yerleştir — kimlik aksiyonu |
| **Değişken ödül (Variable reward)** | Sandık RNG, görev sandığı, etkinlik mağazası, hero parçası | Kayıp kütüphane kalıntısı, çağ planı, efsanevi komutan |
| **Yatırım (Investment)** | Upgrade'li kuleler, temizlenmiş stage'ler, streak, lonca rütbesi, koleksiyon % | "Benim Roma duvarım", civ ustalık yıldızları |

**"Bir dalga daha" — seans uzatıcı:** TD'nin doğal dopamini **dalga netliği**dir (discrete kazan/kaybet birimleri). Kurallar: ortalama stage **6–12 dakika**'ya sınırlanır; temizleme sonrası **Instant Retry / Sıradaki Dalga Challenge / Elite modifikatör** 1 dokunuşla sunulur (lobi labirenti yok); dalga-sonu **kısmi ödüller** (hurda, civ favor'u) yarıda çıkmayı bile üretken kılar; **endless/survival eki** kampanya sonrası yüksek-skill oyuncular için (BTD6-tarzı uzun kuyruk). **Asla** gün 0–2'de "bir dalga daha"yı enerjinin arkasına kilitleme.

---

## 5. Kamera ve sunum / juice / erişilebilirlik

*(Kaynak: `GDD-CAMERA-JUICE.md` — tam sentez, hard rule olarak zaten kararlaştırılmış)*

### 5.1 Karar (önce bunu oku)

| Karar | Seçim |
|---|---|
| **BİRİNCİL combat kamerası** | **Kilitli yüksek-oblik ¾ görünüm (Locked High-Oblique ¾ View, LHOK)** — Kingdom Rush/Clash-of-Clans ailesi: harita (veya keep "yüzü") başına sabit yaw+pitch, dalga sırasında **sadece pan+zoom** |
| **Birincil DEĞİL** | PvZ yan/top şeritler · serbest-orbit 3D (Defense Grid) · combat'ta her zaman açık serbest tilt/yaw |
| **Opsiyonel modlar** | (1) Keep girişi/zafer/yenilgi için sinematik orbit · (2) Yoğun pathing için stratejik top-down overlay · (3) Sızma/hero/boss'a focus-snap · (4) Erişilebilirlik statik çerçeve |
| **Juice yasası** | Geri bildirim **Reduced Motion / Shake %0** açıkken bile teşhis edilebilir kalmalı; kaldırma, değiştir |
| **Shake bütçesi** | Kısa pozisyonel darbeler (~0.12–0.25s); nadir boss vuruşları ≤0.35s; **sürekli rotasyonel shake yok** |

**Tek satır gerekçe:** Anıtsal keep'ler **görünür yükseklik** ve **okunabilir rotalar** gerektirir. Kilitli yüksek-oblik, keep'i bir kahraman siluet olarak gösterirken oyuncuların kamerayla savaşmasını önler — mobilde serbest-orbit 3D'nin ve dikey mimari için yan-görünümün başarısızlık modu.

### 5.2 Karşılaştırma matrisi (skorlama, 1–5, yüksek = bu ürün için daha iyi)

| Kriter | PvZ yan/top | KR sabit ¾ | DG serbest-orbit | Mobil tilt (kilitli) | Mobil serbest-rotasyon |
|---|---:|---:|---:|---:|---:|
| Keep kahraman siluet | 2 | **5** | 5 | **5** | 5 |
| Yol/choke okunabilirliği | **5** | **5** | 3 | 4 | 2 |
| Teşhis edilebilir sızmalar | **5** | **5** | 3 | 4 | 2 |
| Mobil tek-el/başparmak UX | **5** | **5** | 2 | **5** | 2 |
| Sanat maliyeti/tek görüntüleme konisi | **5** | **5** | 2 | 4 | 2 |
| Dikey kuşatma fantazisi (teraslar, duvarlar, geçitler) | 2 | **4** | **5** | **4** | 4 |
| Erişilebilirlik/vestibüler güvenlik | **5** | **5** | 2 | 4 | 2 |
| Pazarlama "wow" ekran görüntüsü | 3 | 4 | **5** | **5** | 5 |
| **Ağırlıklı uyum (keep-öncelikli TD)** | 3.4 | **4.8** | 3.1 | **4.6** | 2.8 |

**Kazanan:** Kilitli yüksek-oblik ¾ (KR kamera sözleşmesi + CoC/mobil tilt sunumu). DG-tarzı orbit ve serbest rotasyon mobilde ve okunabilirlikte kaybediyor. PvZ keep kimliğinde kaybediyor.

### 5.3 Birincil kamera spesifikasyonu

**İsim:** Kilitli Yüksek-Oblik Keep Görünümü (LHOK)

**Combat sırasında sıkı kurallar:**

1. **Sabit yaw ve pitch** dalga aktifken (veya oyuncu "Savun" durumundayken).
2. **İzin verilen:** pan, pinch/zoom (sınırlı), boss doğuşunda opsiyonel hafif **scripted** ileri itiş (≤%8 FOV değişimi, ≤0.4s, ease in/out).
3. **Combat'ta yasak:** serbest orbit, roll, oyuncu-kontrollü pitch, birinci şahıs, varsayılan olarak hero'yu takip eden follow-cam.
4. **Çerçeveleme:** keep kütlesi sabit kahraman konumunda (genellikle üst-üçte-bir veya harita "tacı"); girişler harita kenarından okunur; çıkış/çekirdek net okunur.
5. **Varsayılan zoom:** kritik yolun tümü telefon dikey *veya* yatay modda görünür (birincil oryantasyonu erkenden seç; haritaları ona göre tasarla).

### 5.4 Her keep için "yüz" (art direction)

Her anıtsal keep, tarihi satan ve yaklaşımları okunabilir tutan bir **kanonik yüz** ile gönderilir:

| Keep | Çerçeveleme notu |
|---|---|
| **Akropolis (Atina)** | Tepe kütlesi + Propylaia yaklaşım ekseni; Parthenon taç olarak, orta-yolu kesen bir duvar değil |
| **Himeji** | Tenshu yığını basamaklı beyaz siluet olarak okunur; *ishigaki* ve kapılar okunabilir choke halkaları olarak |
| **Templo Mayor** | İkiz merdivenli piramit dikey doruk noktası olarak; alay/geçit yaklaşımları ön planda kalır |
| **Weiyang** | Eksenel saray derinliği; kapılar ve duvarlar katmanlı dikdörtgenler olarak, düz damga değil |
| **Etemenanki** | Teraslı ziggurat katmanları "ilerleme/tehdit yüksekliği" dikey ölçeği olarak |

### 5.5 Opsiyonel modlar (ikincil — asla birincili değiştirmez)

| Mod | Ne zaman | Kontroller | Asla yapmaz |
|---|---|---|---|
| **A. Sinematik Orbit** | Keep unlock, görev başlangıç tablosu, zafer, yenilgi, mağaza fragmanı çekimi | Scripted spline veya sınırlı orbit; oyuncu her zaman atlayabilir | Aktif dalga AI kararları sırasında çalışmaz |
| **B. Stratejik Top-Down Overlay** | Yoğun geç haritalar, challenge modifikatörleri, "yolu incele" butonu | Anlık veya 0.2s blend ile daha dik açı (~70–85°); aynı yaw; pan/zoom | Keep kimliğini sonsuza kadar saklamaz; varsayılan olmaz |
| **C. Focus-Snap** | Sızma, hero, boss veya alarm ping'inde çift-dokun/hotkey | Konuya yumuşak pan+zoom, ≤1.5s tut veya oyuncu pan yapana kadar | Yerleştirme sürüklemesi sırasında kontrolü çalmaz |
| **D. Erişilebilirlik Statik Çerçeve** | Ayarlar: Hareketi Azalt / Kamera Konforu | Scripted ileri itişleri, focus-snap hareketini devre dışı bırakır; sinematikler otomatik statik görüntüye atlar | İçeriği hareketin arkasına kilitlemez |

### 5.6 Okunabilirlik kontrol listesi (kamera + sunum)

Haritalar sadece tasarımcılar şu sorulara VFX kapalıyken "evet" diyebiliyorsa gönderilir:

1. Dalga 1'den önce her giriş→çıkışı izleyebiliyor musun?
2. Build node'ları dekorasyondan görsel olarak ayırt edilebiliyor mu?
3. Keep, yolun son %20'sini kapatmadan bahis (stake) olarak okunuyor mu?
4. **Hızlı/zırhlı/uçan/boss** önceliğini bestiary açmadan söyleyebiliyor musun?
5. Sızma olduğunda, başarısızlık noktası döndürmeden ekranda mı?
6. Kule menzil halkaları projeksiyon altında dürüst mü?
7. Zirve juice'ta (§5.7) hâlâ düşman hattını görebiliyor musun?

### 5.7 Juice bütçesi — okunabilirliği bozmadan vuruş geri bildirimi

**Geri bildirim yığını (yerel → global tercih sırası):** (1) Yerel aktör juice'ı — hedefte darbe flaşı, kulede geri tepme, ölüm dissolve, kayan sayı (açılabilir); (2) Ses — katmanlı SFX + ducking, Reduced Motion oyuncuları için kritik; (3) UI tick — combo/ödül/can değişimi mikro-animasyonu; (4) Ekran-uzayı vurguları — kısa vinyet, kenar flaşı, kromatik *sadece* a11y izin veriyorsa; (5) Kamera — son çare, en küçük genlik.

**Shake sınırları:**

| Olay | Maks süre | Hareket türü |
|---|---|---|
| Kule vuruşu / küçük öldürme | 0.12–0.18s | Pozisyonel, yüksek-frekans, yay decay |
| Elit/patlama | 0.18–0.25s | Pozisyonel + minik pitch opsiyonel |
| Keep zararı (can kaybı) | 0.20–0.30s | Pozisyonel; kırmızı kenar + ses vurgusuyla eşleşir |
| Boss vuruşu / keep ihlali | ≤0.35s | Pozisyonel; roll'dan kaçın |
| Sürekli deprem tehlikesi | Kamerada değil, **dünya prop'larında shake** tercih edilir | ≤%30 slider veya çok düşük genlik |

**Yasaklı varsayılanlar:** sürekli rotasyonel shake, uzun (>0.5s) sönümsüz shake, tam-ekran beyaz strobe, menüler açıkken devam eden shake.

### 5.8 Erişilebilirlik (pazarlık konusu olmayan ayarlar)

| Ayar | Varsayılan | Davranış |
|---|---|---|
| **Ekran sarsıntısı yoğunluğu** | %100 | Tüm kamera shake'lerinde global %0–100 çoklayıcı |
| **Hareketi azalt** | Kapalı (OS Prefers-Reduced-Motion'a saygı duyar) | Orbit sinematiklerin otomatik oynatmasını, focus-snap hareketini, FOV darbelerini devre dışı bırakır |
| **Flaş/fotosensitivite** | Tam | Kapalı = strob yok; ≤3 Hz eşdeğeri düşük kontrast solid renk darbeleriyle değiştir |
| **Efekt yoğunluğu** | Yüksek | Parçacık/distorsiyonu ölçekler |
| **Hasar sayıları** | Açık | Kapalı veya "sadece büyük" |
| **Kamera asistanı** | Açık | Yol şeritleri, sızma chevron'ları, oklüzyonda keep hayaletleşmesi |
| **Renk körü paletleri** | Kapalı | Deuteranopi/Protanopi/Tritanopi UI + takım renkleri |
| **UI ölçeği/metin** | %100 | Özellikle mobil + Steam Deck sınıfı cihazlar için |

**İlke:** *Kaldırma, değiştir.* Shake %0 hâlâ bir HUD kenar darbesi + ses alır. Hareketi Azalt hâlâ statik tablo aracılığıyla keep kimliği alır.

**Test kapısı:** Shake %0, Efektler Düşük, Hareketi Azalt açık ile geç bir harita oyna. Strateji okunamıyorsa tasarım başarısız — oyuncu değil.

### 5.9 Anti-desenler (asla göndermeyin)

1. Keep'in arkasını görmenin *tek* yolu olarak serbest orbit.
2. Kampanya varsayılanı olarak PvZ yan-görünüm (anıt duvar kağıdına dönüşür).
3. Kritik bilgiyi sadece shake veya flaş ile iletmek.
4. İkinci girişin ekran dışında kalacağı kadar sıkı varsayılan zoom.
5. Yolları kapattığında keep mesh opaklığının her zaman %100 olması.
6. 40 kuleli bir tahtada her mermide ekran sarsıntısı (deniz tutması simülatörü).
7. İlk temizlemede atlanamayan "sinematik kamera".

---

## 6. Defans mekanikleri (5 fiil, kuleler, kışla, tuzak, duvar, kahraman, kaynaklar)

*(Kaynak: `GDD-DEFENSE-SYSTEMS.md`, `GDD-DEFENSE-ATTACK.md`)*

### 6.1 Tasarım kararı (bir ekran)

Defans **beş fiildir**, beş menü değil:

| Fiil | Sistem | İş |
|---|---|---|
| **Ateş Et (Shoot)** | Kuleler | Sabit veya pad konumlarından şerit DPS/kontrol |
| **Tut (Hold)** | Kışla/garnizon | Blokla, geciktir, emici ol; Ateş Et için zaman yarat |
| **Şekillendir (Shape)** | Duvarlar/kapılar/listeler | Yol uzunluğunu, choke'ları ve ateş koridorlarını zorla |
| **Cezalandır (Punish)** | Tuzaklar/tehlikeler | Zorunlu karolarda (kapı, hendek, köprü) patlama vergisi |
| **Yönlendir (Pivot)** | Kahramanlar | Oyuncu iradesi: sızmaları tıka, sortiler aç, Favor harca |

Kaynaklar, pad'ler, upgrade ağaçları, civ skinleri — bunların tümü bu beş fiili mobilde okunabilir kılmak ve tarihsel kale dilbilgisine sadık kalmak için var — yedi tam eş merkezli avlu çizmeden.

### 6.2 Çekirdek defans direkleri (gönderim kontrol listesi)

1. **Şerit netliği** — oyuncu her zaman düşmanların nereden yürüdüğünü ve ateşin nerede yasal olduğunu bilir.
2. **Choke > halı** — değer geçitlerde, köprülerde, köşelerde toplanır, tekdüze serpiştirmede değil.
3. **Tut + Ateş Et döngüsü** — kışlalar bekleme süresi yaratır; kuleler bu süreyi öldürmeye çevirir.
4. **Katmanlı HP, katmanlı karmaşa değil** — 7 tarihsel katman ≤4 oynanabilir strata + yumuşak olaylara haritalanır.
5. **Paylaşılan arketip, civ skinleri** — 6–8 çekirdek kule işi; benzersizlik 1–3 civ kulesi + hero/tuzak kiti aracılığıyla.
6. **Hibrit yerleştirme** — netlik için pad'ler; tarihin gerektirdiği yerde serbest-yerleştirme (kazıklar, hendekler, saha kampları).
7. **Rol sahibi dört kaynak** — Altın (operasyon), Ahşap (saha/hızlı), Taş (kalıcı HP), Favor (ritüel/hero/mit).
8. **Sinerji sözlüğü** — etiketler (Pierce, Splash, Slow, Block, Armor-Break, Anti-Climb, Naval, Ideology) build'leri birleştirir.
9. **Kapı boss kapısı olarak** — her haritanın duygusal vurgusu isimsiz bir duvar segmenti değil, adlı bir kapı/tuzak-avlusudur.
10. **Keep HP=0'ın ötesinde yenilgi modları** — ideoloji/moral (şapel, tapınak, taht), tedarik kesintisi, garnizon ücreti çöküşü.

### 6.3 Defans nesne taksonomisi

#### 6.3.1 Kuleler (Ateş Et)

**Tanım:** Sabit (veya nadiren yeniden konumlandırılabilir) menzil, hedef kuralı ve upgrade yolu olan yayıcılar.

| Özellik | Kılavuz |
|---|---|
| Yerleştirme | Pad'ler tercih edilir (duvar / mural nokta / iç kale köşesi) |
| Hedefleme | Öncelik önayarları: İlk / Son / Güçlü / Zayıf / Uçan / Kuşatma |
| Yumuşak sınır | Şerit başına aynı arketipte yumuşak sınır (spam-önleme, sert yasak değil) |
| Görsel | Siluet %50 zoom'da okunmalı; civ skini ≠ yeni hitbox |

**Anti-desenler:** Sadece "+%10 hasar aurası, atış yok" olarak var olan kuleler; görünmez menzil; saf istatistik klonu olan yedi kule katmanı.

#### 6.3.2 Kışla/garnizon binaları (Tut)

**Tanım:** Yol-blokçusu veya yakın dövüşte tutucu **bloklayıcılar** (askerler) doğuran/barındıran binalar.

| Rol | Not |
|---|---|
| Şerit bloklayıcı | Klasik KR-tarzı kışla; canlanma zamanlayıcısı; zırh/HP kimliği |
| Duvar garnizonu | Sur segmentini kaplar; zayıf yaylım ateşi eder VEYA bitişik kulelere buff verir |
| Sortie yuvası | Zamanlanmış çıkış açar (umadashi, Roma *eruptio*, şövalye karşı-hücumu) |

**Kurallar:** Choke olmadan kışlalar zayıf hisseder; kışlasız choke'lar gevşek hisseder. Bunları eşleştir. Şerit başına yaşayan bloklayıcı sayısını sınırla, Tut'un Ateş Et'i asla ikame etmesin.

#### 6.3.3 Tuzaklar ve saha tehlikeleri (Cezalandır)

| Aile | Tarihsel işaret | Oynanış |
|---|---|---|
| Hendek/hisar yavaşlatması | Evrensel dış katman | Kalıcı veya onarılabilir Slow + süvari vergisi |
| Kazık/çelik diken alanı | Viking iniş engelleme; saha kampları | Anti-süvari; sapcılar tarafından yakılabilir/temizlenebilir |
| Kapı dikey vergisi | Cinayet delikleri, *ishi-otoshi*, portcullis aşamaları | Sadece kapı segmentinde patlama |
| Tuzak avlusu | Masugata, Messene Arkadya Kapısı, barbikan | Zorunlu 90° dönüş + bekleme + çok-açılı ateş |
| Yangın/zift | Hoardinglar, naphtha gelenekleri (civ-kapılı) | AoE + ahşapta dost ateşi riski |
| Köprü/geçit yıkımı | Yıkılabilir açıklık | Yol yeniden yazımı; maliyetli |

Tuzaklar **serbest-yerleştirilebilir halı mayınları değildir**. Varsayılan = **sahne-yazılı** veya **işaretli karolarda tek-kullanımlık**.

#### 6.3.4 Kahramanlar (Yönlendir)

**Tanım:** Kampanyada bir (skirmiş'te 1–2), üçüncü bir build menüsü değil, bekleme süreleri olan oyuncu-kontrollü birim(ler).

| Mod | Kullanım |
|---|---|
| Aktif mikro-yönetim | Sızmaya sürükle; choke'ta yetenek |
| Otomatik-cast + toplanma | Kazuel/FTUE |
| İdeoloji ult'u | Harita-genelinde bir vuruş için Favor harca (rite, banner, alâmet) |

Kahramanlar tam upgrade'li bir çekirdek kuleyi sonsuza kadar DPS'te geride bırakmamalı; benzersiz şekilde: Tut'u dirilt, zırh kır, ihanet şeritlerini ortaya çıkar, veya dalga-başı Favor pivotları için harca **etmelidir**.

#### 6.3.5 Duvarlar, kapılar, listeler (Şekillendir)

| Öğe | Oynanabilir iş |
|---|---|
| Sur/rampart | Şerit HP; mural kule pad'leri; garnizon için geçit |
| Kapı/kapı kulesi | Çok-aşamalı kilit (kapılar → portcullis → cinayet kutusu); boss kapısı |
| Listeler/öldürme sahası | Dış sur düştükten sonra iç ateşin altında açılan alan |
| Hendek | Slow + kuşatma-motoru parklama engeli |
| Keep/ideoloji çekirdeği | Son sığınak **veya** moral zafer totemi (tapınak/şapel/taht) |

**Onarım ekonomisi:** Taş + zaman; ahşap hoarding için (yüksek buff, yangın zafiyeti). Boş maksimum-HP duvarlar **Garnizon Ücreti** ödenmezse çöker (Avrupa araştırma dersi).

### 6.4 7 tarihsel katman → oynanabilir strata haritalama (anti-karmaşa)

Tarihsel araştırmalar sentetik bir **~7-katmanlı soğan** kullanır (dış→çekirdek). Yedi eş zamanlı etkileşimli avlu göndermek şunlara sebep olur: okunamaz minimap, tutorial yüklenmesi, "bir başka HP çubuğu" yorgunluğu.

**Sıkıştırma kuralı — 4 oynanabilir strata + 3 yumuşak katman:**

| Oynanabilir strata | Emdiği tarihsel katmanlar | Oyuncu fiilleri |
|---|---|---|
| **S1 Yaklaşım** | Dış kasaba, otlak, deniz/fiyort, sahil kazıkları, banliyö | Yavaşlat, ortaya çıkar, opsiyonel yanabilir ekonomi dokusu |
| **S2 Eşik** | Kapı / barbikan / masugata / Dipylon / kardinal kapılar | Tuzak avlusu, portcullis aşamaları, adlı choke boss'u |
| **S3 Kabuk** | Dış+iç surlar, mural kuleler, yagura hattı, rampart | Ana pad halkası; duvar HP; yan ateş |
| **S4 Çekirdek** | Keep / honmaru+tenshu / saray / akropolis tapınakları / şapel-salon | Son HP **veya** ideoloji ölçer; elit garnizon |

| Yumuşak katman (olay/ölçer, tam avlu değil) | Örnekler |
|---|---|
| Ekonomi dokusu | Jōkamachi yanması; sur-dışı fırınlar; mülteci kalabalığı |
| Tedarik göbek bağı | Long Walls koridoru; liman iskeleleri; Denizden Yol rıhtımı |
| İdeoloji/meşruiyet | Şapel, Parthenon, Gümüş Ağaç, Etemenanki, Mandate ritüelleri |

**Pratik kural:** Bir tarihsel katman **pathing'i**, **pad yasal setini** veya **kazan/kaybet ölçerini** değiştirmiyorsa, yumuşaktır — lezzet VFX, opsiyonel hedef veya kampanya izi, yedinci bir build bölgesi değil.

### 6.5 Yerleştirme: pad'ler vs serbest-yerleştirme

| Mod | Nerede | Neden |
|---|---|---|
| **Pad'ler (birincil)** | Sur kuleleri, iç kale köşeleri, kapı kanatları, keep çatısı | Mobil okunabilirlik; tarihsel mural noktalar; denge |
| **Segment inşaat** | Duvarlar, hendekler, kapı upgrade'leri | Şekillendir fiili; Taş/Ahşap harcar |
| **İşaretli tuzak karoları** | Kapı boğazı, köprü, hendek dudağı, tuzak avlusu | Halı olmadan Cezalandır |
| **Serbest-yerleştirme (sınırlı)** | Kazıklar, çelik dikenler, saha paveseleri, Moğol perdesi, geçici kamplar | Tarih + oyuncu ifadesi; sert sayı sınırları |

**Serbest-yerleştirme korkulukları:** Sayı ve kaynakla sınırla, görünmez "yasadışı yeşil balçık" ile değil. Yasak dışı: **tek** yolu tamamen kapatmak (harita "kapanabilir arka kapı" bayrağı taşımadıkça). Yasak dışı: 12 kazık alanını üst üste yığıp stun-kilit yapmak; azalan getiri kullan.

### 6.6 Kaynaklar — Altın / Ahşap / Taş / Favor

| Kaynak | Fantazi | Satın alır | Kıtlık hissi |
|---|---|---|---|
| **Altın** | Sikke, yağma, vergi, haraç | Asker maaşı, tuzak yenileme, paralı Tut, acil onarım | Dalga geliri + opsiyonel yan hedefler |
| **Ahşap** | Kereste, fascine, gemi, hoarding | Hızlı kuleler, kazıklar, geçici kışla, köprüler, yangın mühimmatı | Erken bol; yanar; geç oyunda Taş meta'sına karşı zayıf |
| **Taş** | Kesme taş, tuğla, hangtu, ishigaki | Kalıcı surlar, kapı kuleleri, ağır kuleler, keep upgrade'leri | Yavaş gelir/duvarcılık kanalı; orta-geç gücü tanımlar |
| **Favor** | Tanrılar, atalar, Mandate, kalıntılar, Tengri alâmetleri, kami, azizler | Hero ult'ları, ideoloji kalkanları, alâmet ortaya çıkarma, mit skinleri **buff olarak** | Görev başarımları, ritüeller, opsiyonel risk ritüelleri — **P2W mana kaynağı değil** |

**Dönüşüm ve gerilim:** Ahşap→Taş bir kampanya teknolojisidir (Norman ahşap keep → taş keep), dalga içinde anlık bir değişim düğmesi değil. Yüksek Taş duvarlar + sıfır Altın maaşı → teslim olma olayı (Avrupa dosyası). Favor asla kalıcı olarak Altın DPS'inin yerine geçmez; pivotları ve yumuşak zafer koşullarını **zamanlar**.

### 6.7 Upgrade ağaçları — paylaşılan yapı

```
Katman 0  Temel               (inşa et)
Katman 1  Yükseltme           (iki daldan biri)
Katman 2  Uzmanlaşma          (dal derinleşir)
Katman 3  Ustalık/amblem      (civ-lezzetli doruk; sadece +hasar değil)
```

- Kule başına **maks iki dal** (okunabilirlik).
- Dal isimleri = **fiiller** (Delme vs Yaylım; Zift vs Taşlar; Disiplin vs Fanatik).
- Doruklar **kuralları** değiştirir (sıçrama, zırh kırma, çift-hedef, kapı-aşama atlama) sadece +% tablosundan çok daha fazla.
- Global teknoloji (kampanya) katmanları açar; misyon içi altın/ahşap/taş rütbeleri satın alır.

### 6.8 Çekirdek kule arketipleri (civ'ler arası paylaşılan)

Paylaşılan **işler**; civ'ler sanat, SFX, mühimmat kurgusu ve küçük pasif ayarlamalar sağlar.

| ID | Arketip | Birincil etiketler | Rol |
|---|---|---|---|
| **T1 Ok/mermi** | Yay, sapan, arbalet skini | Pierce/Single | Ucuz güvenilir DPS |
| **T2 Mızrakçı yuvası/nöbet** | Kısa-menzilli savunma | Anti-Süvari, zayıf Splash | Erken Tut yardımı / duvar pad'i |
| **T3 Kışla** | (bina; defans parçası olarak listelenir) | Block | Örs |
| **T4 Taş fırlatma/hafif topçu** | Sapan-mızrağı, mangonel lite | Splash | Kümeleşme temizleme |
| **T5 Ağır motor** | Ballista, mancınık, traksiyon | Anti-Siege / Single büyük | Elit ve motor silme; düşük RoF |
| **T6 Destek/standart** | Banner, boru, sinyal | Aura (küçük), Reveal | Maaş/moral; görüş |
| **T7 Yangın/özel mühimmat** | Zift, naphtha, ateş oku | Burn | Bölge inkarı; harita-riski |
| **T8 Kutsal/ideoloji** | Tapınak, şapel-top, stela | Ideology, Favor havuzu | Yumuşak kalkanlar, anti-korku; düşük ham DPS |

**Lansman kadrosu:** T1–T5 zorunlu; T6–T8 kampanya perdeleri aracılığıyla açılır, FTUE temiz kalsın.

### 6.9 Civ-özel kuleler (medeniyet başına 1–3)

Benzersizler **kararları değiştirmelidir**, sadece T1'i yeniden adlandırmamalıdır. Tam liste §8'de her medeniyet kartında verilmiştir; özet örnekler: Babylon'un İştar Kapısı aslan-atışı, Roma'nın Scorpio bataryası, Japonya'nın *ishi-otoshi* yagurası, Moğol'un mobil perde vagonu.

**Bütçe:** Lansmanda medeniyet başına **1 benzersiz + 1 imza tuzak/kapı modülü**; ikinci benzersiz live-ops'ta gelir.

### 6.10 Sinerji dilbilgisi (etiket sistemi)

**Çekirdek etiketler:** `Pierce` `Splash` `Single` `Slow` `Root` `Block` `Armor-Break` `Anti-Shield` `Anti-Climb` `Anti-Cav` `Anti-Siege` `Flying` `Naval` `Burn` `Reveal` `Ideology` `Wage`

**Amaçlanan kombolar (FTUE'de öğret):**

| Kombo | Parçalar | Kazanç |
|---|---|---|
| **Örs & Çekiç** | Kışla Block + Splash kule | Klasik |
| **Öldürme koridoru** | Slow hendek + Pierce hattı + kapı tuzağı | Choke ustalığı |
| **Kutuyu aç** | Armor-Break hero/tuzak + Single keskin nişancı | Elit silme |
| **Yangın disiplini** | Burn mühimmatı + taş avlusu (yanmaz) | Güvenli yangın bölgesi |
| **İdeoloji desteği** | Çekirdek üzerinde Favor kalkanı + Maaşı ödenmiş garnizon | Boss dalgasında hayatta kal |
| **Yol vergisi** | Labirent/liste + uzun-menzilli mural pad'ler | Japonya/Avrupa uzmanlığı |
| **Deniz tıpası** | Naval etiketli kuleler + zincir/liman | Yunan/Viking/Mısır haritaları |

**Anti-sinerji (dürüstlük):** Ahşap Viking rampart üzerinde tüm-Burn → kendine zarar. Süper-zırhlı bosse karşı saf Splash → başarısız. Altın maaşı olmadan Favor yığmak → güzel tapınaklar, boş duvarlar. Yolu görmeyen pad'lerin arkasında serbest-yerleştirme kazıklar → boşa harcanan Ahşap.

**Aura bütçesi:** Şerit sektörü başına en fazla **bir** güçlü aura. Harita-geneli çorbadan çok kısa-menzilli banner'ları (hero, standart kule) tercih et.

### 6.11 Choke tasarımı

| Tip | Örnek | Tasarım notları |
|---|---|---|
| **Kapı boğazı** | Barbikan, masugata, Dipylon | En iyi öğretici choke; çok-aşamalı |
| **Köprü/geçit** | Nehir haritaları, Long Walls mantığı | Yıkılabilir; yüksek dramatik |
| **Geçit/paleo-choke** | Termopil-tarzı | Arazi, duvar değil; ihanet yan-yol riski |
| **Spiral vergi** | Japon iç kale bağlantıları | Ekstra HP ölçeri olmadan uzunluk |
| **Liste öldürme sahası** | Dış ihlal sonrası | İç ateşin altında geçici açık |
| **Liman ağzı** | Zincir, dalgakıran, kazıklar | Deniz+kara hibrit |

**İyi choke kuralları:** Habercili (bir dalga önce — keşif tozu, davul, boru). Yan yol dürüstlüğü (ihanet yolu varsa, keşif/Favor ortaya çıkarma onu kapatabilir/geciktirebilir). Choke kanatları en iyi pad'leri alır. Şerit başına bir birincil choke — ikincil opsiyonel sızma, eşit ikiz değil (mobilde dikkat bölünmesi cehennemini önler). Boss kapısı kadansı — orta-görev ve final adlı kapılar kullanır, anonim duvar segmentleri değil.

---

## 4. Çekirdek döngü (saniye / dakika / seans / gün / hafta / sezon)

### 4.1 Tasarım tezi

Tower defence'in doğal dopamini **dalga netliğidir**: bir stage içinde kesikli kazanma/kaybetme birimleri. Bu ürün o mikro-zaferleri tarihsel keep fantazisine ve medeniyet kimliğine bağlar. Retention “daha fazla login takvimi” değildir; (1) ilk seans fantazisi, (2) bitmemiş arzu ile biten günlük döngü, (3) sosyal yükümlülük içeren haftalık döngü, (4) stage clear’ı aşan meta progresyon, (5) güç creep yerine civ flavor çeviren live ops’tur (`GDD-RETENTION-VIRAL.md`).

### 4.2 Mikro döngü (~45–90 saniye)

1. **Intel bandı:** Sonraki dalganın rolleri + görünür modifier ikonları (gizli stack yok).
2. **Karar penceresi:** Pad’e kule / upgrade / sat / kahraman / kapı-tuzak.
3. **Çözümleme:** Hold dwell üretir; Shoot öldürür; Shape path uzatır; Punish vergi keser; Pivot sızıntıyı tıkar.
4. **Sızıntı muhasebesi:** Keep Integrity (rol bazlı hasar); isteğe bağlı Moral/Ideology.
5. **Dalga sonu:** Altın + kısmi ödül + nefes; early-call risk/ödül.
6. **Juice beat:** boynuz, coin arc, kısa shake (Reduced Motion alternatifi).

### 4.3 Seans döngüsü

| Mod | Süre | Dalga |
|-----|------|-------|
| Tutorial map | 3–5 dk | 5–8 |
| Standart kampanya | 8–14 dk | 12–20 |
| Boss kuşatması | 12–18 dk | 15 + fazlar |
| Daily challenge | 6–10 dk | 10–14 |
| Endless | açık uçlu | yükselen |

Clear sonrası tek dokunuş: **Sonraki / Elite Retry / Hub**. Lobby labirenti “one more wave”yi öldürür.

### 4.4 Gün / hafta / sezon

- **Günlük:** Daily + lonca tithe/watch (ops.) + BP XP + grace streak.
- **Haftalık:** Challenge seti, hotfix penceresi, lonca muster.
- **Aylık:** Boss Siege (skill bracket).
- **6–8 hafta:** Battle Pass / civ season.
- **Çeyrek:** Yeni act veya major map pack.

### 4.5 Yatırım (ethical sunk cost)

Layout hafızası, isimli komutan, civ mastery, lonca itibarı, kozmetik anıtlar. Streak wipe yok. Meta soft-cap; Iron’da meta strip.

### 4.6 Döngü anti-pattern’leri

- Wave arasında full hub reload
- Enerji ile “bir tur daha”yu kesmek
- Ödülü izletmeden vermemek
- Aynı power creep sezonu sonsuza
- Bildirim spam ile sahte trigger

## 5. Kamera ve sunum / juice / erişilebilirlik

### 5.1 Birincil kamera

**Kilitli yüksek-oblik ¾** (`GDD-CAMERA-JUICE.md`): sabit yaw+pitch; savaşta pan+zoom. Serbest orbit varsayılan değil.

**Gerekçe:** Keep dikey silüeti + path okunabilirliği + mobil başparmak UX. PvZ side keep’i öldürür; DG orbit kamera skill tax’i yaratır.

### 5.2 Skor matrisi (özet)

| Kriter | PvZ | KR ¾ | DG orbit | Kilitli tilt | Serbest rotate |
|--------|-----|------|----------|--------------|----------------|
| Keep silüet | 2 | 5 | 5 | 5 | 5 |
| Path netliği | 5 | 5 | 3 | 4 | 2 |
| Leak diagnosable | 5 | 5 | 3 | 4 | 2 |
| Mobil UX | 5 | 5 | 2 | 5 | 2 |
| Art maliyeti | 5 | 5 | 2 | 4 | 2 |
| A11y | 5 | 5 | 2 | 4 | 2 |

### 5.3 Opsiyonel modlar

- Sinematik orbit (intro/zafer/yenilgi, skippable)
- Stratejik top-down overlay
- Focus-snap leak/hero/boss
- Static frame (Reduced Motion)

### 5.4 Juice yasası

- Reduced Motion: feedback silinmez, replace edilir
- Shake 0.12–0.25s; boss ≤0.35s; rotational sustained yok
- 2–3 intentional motion: banner, horn, victory stamp
- %50 zoom’da kule rolü okunur
- Keep kitlesi arka path’i örtmez
- Ses: civ SFX pack; VO az ama karakterli

### 5.5 Pazarlama görselleri

Full-bleed keep; inset collage varsayılan değil. Trailer = gerçek oynanış.

## 6. Defans mekanikleri

### 6.1 Beş fiil

| Fiil | Sistem | İş |
|------|--------|-----|
| Shoot | Kuleler | DPS/kontrol |
| Hold | Kışla | Blok/stall |
| Shape | Duvar/kapı | Path şekillendir |
| Punish | Tuzak | Choke vergisi |
| Pivot | Kahraman | Anlık müdahale |

### 6.2 On direk

1. Lane clarity
2. Choke > carpet
3. Hold+Shoot loop
4. ≤4 oynanabilir strata (7 katman fantazi)
5. Paylaşılan arketip + civ skin
6. Pad hibrit yerleşim
7. Gold/Wood/Stone/Favor
8. Sinerji etiketleri
9. Gate duygusal beat
10. Keep HP dışı yenilgi modları

### 6.3 Kule arketipleri

1. Missile/ok — ucuz DPS
2. Heavy bolt/ballista — anti-armor
3. Splash/ateş — grup
4. Barracks — Hold
5. Support aura
6. Civ special
7. (Hero ayrı sistem)

Upgrade: 3 path × 3–4 tier; T3+ exclusion. Targeting: First/Last/Strong/Weak/Flying/Siege.

### 6.4 Kışla / tuzak / duvar

Kışla: blocker + revive; wall garrison; sortie nest. Tuzaklar site-authored (masugata, ditch, murder-hole, bridge). Duvar Shape; concentric’te dış hold → iç slot açılır.

### 6.5 Kahraman

1 aktif/battle; gacha yok; unlock yıldız/achieve. DPS’te kuleden kalıcı üstün olamaz; pivot işi yapar. Revive soft veya 1× rewarded (Iron kapalı).

### 6.6 Kaynak tablosu

| Kaynak | Rol |
|--------|-----|
| Gold | Kur/upgrade |
| Wood | Hızlı saha |
| Stone | Kalıcı onarım (pahalı) |
| Favor | Ritüel/myth/hero |

#### 6.x Shoot derinliği

Menzil halkaları terrace/yagura’da yükseklik bandına map edilir.

Aynı arketip soft-cap lane başına.

Sell refund %50–70; ★3’te sell-abuse penceresi.

- Playtest metriği: Shoot derinliği pick rate
- Edge case: Shoot derinliği + dual-keep
- Patch note kategorisi: Shoot derinliği
- Mobil/PC parity: Shoot derinliği hotkey/touch
- A11y: Shoot derinliği feedback non-color
- Monetizasyon etkileşimi: yok (güç satılmaz)

#### 6.x Hold derinliği

Blockers path-block veya leash.

Cap: Hold Shoot’u replace etmesin.

Sortie Favor veya cooldown.

- Playtest metriği: Hold derinliği pick rate
- Edge case: Hold derinliği + dual-keep
- Patch note kategorisi: Hold derinliği
- Mobil/PC parity: Hold derinliği hotkey/touch
- A11y: Hold derinliği feedback non-color
- Monetizasyon etkileşimi: yok (güç satılmaz)

#### 6.x Shape derinliği

Named gates.

List/palisade Wood sink.

Breach event telegraph 2s.

- Playtest metriği: Shape derinliği pick rate
- Edge case: Shape derinliği + dual-keep
- Patch note kategorisi: Shape derinliği
- Mobil/PC parity: Shape derinliği hotkey/touch
- A11y: Shape derinliği feedback non-color
- Monetizasyon etkileşimi: yok (güç satılmaz)

#### 6.x Punish derinliği

Consumable işaretli tile.

Sapper tuzak temizler — counterplay.

Dost ateşi hard mode opsiyon.

- Playtest metriği: Punish derinliği pick rate
- Edge case: Punish derinliği + dual-keep
- Patch note kategorisi: Punish derinliği
- Mobil/PC parity: Punish derinliği hotkey/touch
- A11y: Punish derinliği feedback non-color
- Monetizasyon etkileşimi: yok (güç satılmaz)

#### 6.x Pivot derinliği

Autocast FTUE; mikro veteran.

Ideology ult map beat.

İki hero skirmish only.

- Playtest metriği: Pivot derinliği pick rate
- Edge case: Pivot derinliği + dual-keep
- Patch note kategorisi: Pivot derinliği
- Mobil/PC parity: Pivot derinliği hotkey/touch
- A11y: Pivot derinliği feedback non-color
- Monetizasyon etkileşimi: yok (güç satılmaz)

## 7. Saldırı / dalga / düşman / boss / ekonomi

### 7.1 Tez

Saldırgan musluk değil **doktrin**. Her wave: “bugün duvarda hangi tarihsel baskı var?” (`GDD-ATTACK-WAVES.md`).

### 7.2 Rol taksonomisi

| Rol | İş | Counter |
|-----|-----|---------|
| SWARM | Tek hedef boğma | AoE |
| ARMORED | Delici soak | Bolt/oil/pierce |
| FAST | Leak | Slow/choke/barracks |
| FLYER | Nadir; FOLK tercih | AA |
| SIEGE | Yapı hasarı | Focus/sortie |
| BOSS | Kural değişimi | Faz+hero |
| TRAITOR/SAPPER | İç path/gate | Detection |

### 7.3 Modifier’ler

Banner’da görünür; Normal ≤2 combat +1 narrative. Örnek: ARMOUR_UP, SWARM_TIDE, FORCED_MARCH, FOG, NAVAL_LANDING, FEIGNED_RETREAT (telegraflı), GATE_BRIBE. RNG ilan edilmiş seed.

### 7.4 Dalga grameri

`[opener] + [main] + [special] + [bosslet?]`. W1–3 öğret; W4–7 armor; W8–12 siege; mid mini-boss; final scripted event.

### 7.5 Ekonomi

- Start gold: 2–3 kulelik karar zorunluluğu
- Kill value + clear bonus + early-call %
- Sell %50–70
- Keep repair pahalı
- Interest opsiyonel (merchant support tower)

### 7.6 Keep Integrity

0–100; runner 1, armored leak ~3, ram 5–8, boss leak 10–15. Dual-keep: paylaşılan gold, ayrı HP.

### 7.7 Boss

HP süngeri yasak. Rule-change: aura, faz, lane açma, ihanet. Hazırlık penceresi. Twin-telling (Kiros 539) etiketli UI.

### 7.8 Zorluk

Normal/Hard
Heroic
Iron (kule kısıtı)
Historical (FOLK kapalı)
Mythic playlist
Endless
Hard’da meta strip

### 7.9 Attacker campaigns

Phase 2+; Moğol specialty adayı. Launch blocker değil.

### 7.10 Örnek dalga script’leri (taslak)

**Örnek Wave W1 şablonu:**
- Intel: rol karışımı seviye 1
- Modifier slot: 2 adet görünür ikon
- Spawn yoğunluğu eğrisi: erken seyrek → son 20% spike
- Gold toplamı hedefi: start_gold × 0.17 bandı
- Fail-teach: ilk leak’te tip bazlı tooltip bir kez
- Early-call bonus: +11% (overlap riski)
- Telemetry anahtarı: `wave_1_leak_rate`

**Örnek Wave W2 şablonu:**
- Intel: rol karışımı seviye 2
- Modifier slot: 3 adet görünür ikon
- Spawn yoğunluğu eğrisi: erken seyrek → son 20% spike
- Gold toplamı hedefi: start_gold × 0.19 bandı
- Fail-teach: ilk leak’te tip bazlı tooltip bir kez
- Early-call bonus: +12% (overlap riski)
- Telemetry anahtarı: `wave_2_leak_rate`

**Örnek Wave W3 şablonu:**
- Intel: rol karışımı seviye 3
- Modifier slot: 1 adet görünür ikon
- Spawn yoğunluğu eğrisi: erken seyrek → son 20% spike
- Gold toplamı hedefi: start_gold × 0.21 bandı
- Fail-teach: ilk leak’te tip bazlı tooltip bir kez
- Early-call bonus: +13% (overlap riski)
- Telemetry anahtarı: `wave_3_leak_rate`

**Örnek Wave W4 şablonu:**
- Intel: rol karışımı seviye 4
- Modifier slot: 2 adet görünür ikon
- Spawn yoğunluğu eğrisi: erken seyrek → son 20% spike
- Gold toplamı hedefi: start_gold × 0.23 bandı
- Fail-teach: ilk leak’te tip bazlı tooltip bir kez
- Early-call bonus: +14% (overlap riski)
- Telemetry anahtarı: `wave_4_leak_rate`

**Örnek Wave W5 şablonu:**
- Intel: rol karışımı seviye 5
- Modifier slot: 3 adet görünür ikon
- Spawn yoğunluğu eğrisi: erken seyrek → son 20% spike
- Gold toplamı hedefi: start_gold × 0.25 bandı
- Fail-teach: ilk leak’te tip bazlı tooltip bir kez
- Early-call bonus: +15% (overlap riski)
- Telemetry anahtarı: `wave_5_leak_rate`

**Örnek Wave W6 şablonu:**
- Intel: rol karışımı seviye 6
- Modifier slot: 1 adet görünür ikon
- Spawn yoğunluğu eğrisi: erken seyrek → son 20% spike
- Gold toplamı hedefi: start_gold × 0.27 bandı
- Fail-teach: ilk leak’te tip bazlı tooltip bir kez
- Early-call bonus: +16% (overlap riski)
- Telemetry anahtarı: `wave_6_leak_rate`

**Örnek Wave W7 şablonu:**
- Intel: rol karışımı seviye 7
- Modifier slot: 2 adet görünür ikon
- Spawn yoğunluğu eğrisi: erken seyrek → son 20% spike
- Gold toplamı hedefi: start_gold × 0.29 bandı
- Fail-teach: ilk leak’te tip bazlı tooltip bir kez
- Early-call bonus: +17% (overlap riski)
- Telemetry anahtarı: `wave_7_leak_rate`

**Örnek Wave W8 şablonu:**
- Intel: rol karışımı seviye 8
- Modifier slot: 3 adet görünür ikon
- Spawn yoğunluğu eğrisi: erken seyrek → son 20% spike
- Gold toplamı hedefi: start_gold × 0.31 bandı
- Fail-teach: ilk leak’te tip bazlı tooltip bir kez
- Early-call bonus: +18% (overlap riski)
- Telemetry anahtarı: `wave_8_leak_rate`

**Örnek Wave W9 şablonu:**
- Intel: rol karışımı seviye 9
- Modifier slot: 1 adet görünür ikon
- Spawn yoğunluğu eğrisi: erken seyrek → son 20% spike
- Gold toplamı hedefi: start_gold × 0.33 bandı
- Fail-teach: ilk leak’te tip bazlı tooltip bir kez
- Early-call bonus: +19% (overlap riski)
- Telemetry anahtarı: `wave_9_leak_rate`

**Örnek Wave W10 şablonu:**
- Intel: rol karışımı seviye 10
- Modifier slot: 2 adet görünür ikon
- Spawn yoğunluğu eğrisi: erken seyrek → son 20% spike
- Gold toplamı hedefi: start_gold × 0.35 bandı
- Fail-teach: ilk leak’te tip bazlı tooltip bir kez
- Early-call bonus: +10% (overlap riski)
- Telemetry anahtarı: `wave_10_leak_rate`

**Örnek Wave W11 şablonu:**
- Intel: rol karışımı seviye 11
- Modifier slot: 3 adet görünür ikon
- Spawn yoğunluğu eğrisi: erken seyrek → son 20% spike
- Gold toplamı hedefi: start_gold × 0.37 bandı
- Fail-teach: ilk leak’te tip bazlı tooltip bir kez
- Early-call bonus: +11% (overlap riski)
- Telemetry anahtarı: `wave_11_leak_rate`

**Örnek Wave W12 şablonu:**
- Intel: rol karışımı seviye 12
- Modifier slot: 1 adet görünür ikon
- Spawn yoğunluğu eğrisi: erken seyrek → son 20% spike
- Gold toplamı hedefi: start_gold × 0.39 bandı
- Fail-teach: ilk leak’te tip bazlı tooltip bir kez
- Early-call bonus: +12% (overlap riski)
- Telemetry anahtarı: `wave_12_leak_rate`

**Örnek Wave W13 şablonu:**
- Intel: rol karışımı seviye 13
- Modifier slot: 2 adet görünür ikon
- Spawn yoğunluğu eğrisi: erken seyrek → son 20% spike
- Gold toplamı hedefi: start_gold × 0.41 bandı
- Fail-teach: ilk leak’te tip bazlı tooltip bir kez
- Early-call bonus: +13% (overlap riski)
- Telemetry anahtarı: `wave_13_leak_rate`

**Örnek Wave W14 şablonu:**
- Intel: rol karışımı seviye 14
- Modifier slot: 3 adet görünür ikon
- Spawn yoğunluğu eğrisi: erken seyrek → son 20% spike
- Gold toplamı hedefi: start_gold × 0.43 bandı
- Fail-teach: ilk leak’te tip bazlı tooltip bir kez
- Early-call bonus: +14% (overlap riski)
- Telemetry anahtarı: `wave_14_leak_rate`

**Örnek Wave W15 şablonu:**
- Intel: rol karışımı seviye 15
- Modifier slot: 1 adet görünür ikon
- Spawn yoğunluğu eğrisi: erken seyrek → son 20% spike
- Gold toplamı hedefi: start_gold × 0.45 bandı
- Fail-teach: ilk leak’te tip bazlı tooltip bir kez
- Early-call bonus: +15% (overlap riski)
- Telemetry anahtarı: `wave_15_leak_rate`

## 8. Medeniyet kampanyası Act 0–10

### 8.0 Ortak paket ve sıra

Her act: 2–3 topoloji, 6–10 kule, 1–2 komutan, enemy roster, Codex, 1 boss, opsiyonel FOLK playlist. Anthology; Chronographer haritası. Unlock: 2★ veya daily — premium-only yok.

Sıra: 0 Babylon → 1 Rome → 2 Persia → 3 Egypt → 4 China → 5 Maya–Mexica → 6 Viking → 7 Greece → 8 Japan → 9 Mongol → 10 European Castle.

**Launch:** Act0 + Rome + Persia + Egypt + Greece showcase + boss + daily + cosmetics + rewarded. **90g:** China/Japan + BP + guild. **Yıl:** drip.

### Act 0 — Babylon (Etemenanki)

**Rol:** Tutorial / FTUE act

**Keep:** Etemenanki + Esagila; Processional Way; Ishtar Gate. Koldewey ~kare taban klasik ~91 m — ARCH aralık, turist yüksekliği değil.

**7-katman fantazi:** (1) canal fringe (2) Imgur-Enlil (3) Nemetti-Enlil (4) Processional (5) Ishtar (6) terrace climb (7) cella/Marduk.

**İmza kuleler:** Terrace Archer; Gate Ballista; Processional aura; Canal Punish; Mudbrick Barracks; Signal; Bitumen (etiketli).

**İmza düşmanlar/boss:** Raiders; Assyrian-style drill (MOD framing); Elamite climber; Median/Persian; Boss Cyrus twin-telling.

**Myth ultimates:** Marduk/Ishtar/akītu FOLK-LIT; mušḫuššu guardian language ARCH, ordu FOLK.

**Do-not-assume:** Babel yüksekliği≠ARCH; Hanging Gardens atmosfer; Ecbatana renk transferi yok; kaynar yağ default yok; Nebuchadnezzar wizard değil.

**Pitch:** Climb Marduk’s staged mountain; learn 7-layer grammar.

**Örnek map’ler:** B0-01 Approach; B0-02 Terrace; B0-03 Gate finale 8–12 dk.

- Codex zorunlu kart sayısı: keep + 3 birim + 2 tahkimat + 1 boss + 1 FOLK karşılaştırması
- Yıldız kapıları: ★2 sonraki act soft unlock
- Educational/Arcade: FOLK birimler Arcade’de, Historical’da kapalı
- Sanat: keep hero silhouette + bir gate close-up
- Ses: 1 boynuz stinger + 1 zafer kadansı
- Risk: kültürel/dini saygı checklist imzalı
- Telemetry: act funnel clear rate
- Live ops: bu civ’in season adı rezerv

### Act 1 — Rome

**Rol:** Capitolium / castra

**Keep:** Capitolium IOM; field twin principia.

**7-katman fantazi:** stimuli→lilia→cippi→fossa→vallum→lorica→principia/Capitolium.

**İmza kuleler:** Scorpio; Ballista; Onager; Auxilia barracks; Aqueduct support; Aurelian enfilade.

**İmza düşmanlar/boss:** Gallic; Elephant; Numidian; Parthian; Cataphract; Testudo; Alaric gate twist.

**Myth ultimates:** Jupiter; Aquila; Mars; Sibylline risk card.

**Do-not-assume:** Kaynar yağ default Roma değil; her asker Marian clone değil; Capitolium yanma döngüleri içerik.

**Pitch:** Engineering defense school.

**Örnek map’ler:** Servian Drill; Alesia Edge; Capitol Night.

- Codex zorunlu kart sayısı: keep + 3 birim + 2 tahkimat + 1 boss + 1 FOLK karşılaştırması
- Yıldız kapıları: ★2 sonraki act soft unlock
- Educational/Arcade: FOLK birimler Arcade’de, Historical’da kapalı
- Sanat: keep hero silhouette + bir gate close-up
- Ses: 1 boynuz stinger + 1 zafer kadansı
- Risk: kültürel/dini saygı checklist imzalı
- Telemetry: act funnel clear rate
- Live ops: bu civ’in season adı rezerv

### Act 2 — Persia

**Rol:** Apadāna

**Keep:** Persepolis Apadāna; Gate of All Nations.

**7-katman fantazi:** ditch→mountain curtain→casemate→terrace→gate→Apadāna podium→throne/farr.

**İmza kuleler:** Sparabara; Archer; Immortals HQ; Apple-bearer; Royal Road; Qanat; Scythed glass.

**İmza düşmanlar/boss:** Lydian; urban Babylonian; Hoplite; Trireme; Alexander; betrayal satrap.

**Myth ultimates:** Ahura Mazda; farr; Ameretat fluff etiketi.

**Do-not-assume:** Ecbatana 7 renk ARCH değil; Immortals OP TO&E Yunan merceği; 539 yalnızca nehir değil.

**Pitch:** Terrace empire archery vs spears then Macedonian siege.

**Örnek map’ler:** Apadana Steps; Road Fork; Gate of Nations.

- Codex zorunlu kart sayısı: keep + 3 birim + 2 tahkimat + 1 boss + 1 FOLK karşılaştırması
- Yıldız kapıları: ★2 sonraki act soft unlock
- Educational/Arcade: FOLK birimler Arcade’de, Historical’da kapalı
- Sanat: keep hero silhouette + bir gate close-up
- Ses: 1 boynuz stinger + 1 zafer kadansı
- Risk: kültürel/dini saygı checklist imzalı
- Telemetry: act funnel clear rate
- Live ops: bu civ’in season adı rezerv

### Act 3 — Egypt

**Rol:** Buhen

**Keep:** Buhen citadel; Karnak skin; pyramid backdrop NOT keep.

**7-katman fantazi:** glacis→bastion→rock ditch→caponier→rampart→curtain→gate HQ.

**İmza kuleler:** Loophole; Nubian archer; Composite bow; Chariot platform; Medjay; Slipway.

**İmza düşmanlar/boss:** Hyksos; Canaan; Kadesh debuff boss; Sea Peoples; late Assyria/Persia.

**Myth ultimates:** Amun-Ra; Sekhmet; Maʿat; Uraeus; Apophis FOLK night.

**Do-not-assume:** Pyramid=castle yok; Pelusium kedi FOLK; Buhen catapult abartısı yok.

**Pitch:** Nile fort chain holds Maʿat.

**Örnek map’ler:** Buhen Ditch; Nile Lane; Maʿat Trial.

- Codex zorunlu kart sayısı: keep + 3 birim + 2 tahkimat + 1 boss + 1 FOLK karşılaştırması
- Yıldız kapıları: ★2 sonraki act soft unlock
- Educational/Arcade: FOLK birimler Arcade’de, Historical’da kapalı
- Sanat: keep hero silhouette + bir gate close-up
- Ses: 1 boynuz stinger + 1 zafer kadansı
- Risk: kültürel/dini saygı checklist imzalı
- Telemetry: act funnel clear rate
- Live ops: bu civ’in season adı rezerv

### Act 4 — China

**Rol:** Weiyang / wengcheng

**Keep:** Weiyang Gong; military grammar wengcheng+mamian.

**7-katman fantazi:** moat→yangmacheng→mamian→hangtu→wengcheng→dilou→palace/Mandate.

**İmza kuleler:** Nu crossbow; Mamian; Wengcheng; Fire gate period-label; Cavalry; Mohist; Beacon.

**İmza düşmanlar/boss:** Chariot early; Xiongnu; warlord; steppe; gunpowder late.

**Myth ultimates:** Tianming; flood ult FF risk; Guandi Romance FOLK.

**Do-not-assume:** Tek Wall Qin→Ming yok; Romance≠history; pagoda=castle yok.

**Pitch:** Hangtu cities and urn-gates.

**Örnek map’ler:** Wengcheng Lesson; Beacon Chain; Mandate Break.

- Codex zorunlu kart sayısı: keep + 3 birim + 2 tahkimat + 1 boss + 1 FOLK karşılaştırması
- Yıldız kapıları: ★2 sonraki act soft unlock
- Educational/Arcade: FOLK birimler Arcade’de, Historical’da kapalı
- Sanat: keep hero silhouette + bir gate close-up
- Ses: 1 boynuz stinger + 1 zafer kadansı
- Risk: kültürel/dini saygı checklist imzalı
- Telemetry: act funnel clear rate
- Live ops: bu civ’in season adı rezerv

### Act 5 — Maya–Mexica

**Rol:** Dual campaign

**Keep:** Tikal/Becán → Templo Mayor; asla tek Aztek çorbası.

**7-katman fantazi:** Jungle vs causeway ayrı stack’ler; ayrı Codex ağaçları.

**İmza kuleler:** Atlatl; spear barracks; causeway checkpoint; chinampa (Mexica); temple support.

**İmza düşmanlar/boss:** Skirmisher clouds; jaguar/eagle elites; capture sapper; tlatoani / star-war captain.

**Myth ultimates:** Dini keep saygılı; ucuz shock yok; sensitivity pass.

**Do-not-assume:** Maya≠Aztek; rubber/ballcourt süsleme dikkat; korsan ‘sacrifice spam’ yok.

**Pitch:** Two banners, two grammars.

**Örnek map’ler:** Jungle Choke; Causeway Series; Templo Stand.

- Codex zorunlu kart sayısı: keep + 3 birim + 2 tahkimat + 1 boss + 1 FOLK karşılaştırması
- Yıldız kapıları: ★2 sonraki act soft unlock
- Educational/Arcade: FOLK birimler Arcade’de, Historical’da kapalı
- Sanat: keep hero silhouette + bir gate close-up
- Ses: 1 boynuz stinger + 1 zafer kadansı
- Risk: kültürel/dini saygı checklist imzalı
- Telemetry: act funnel clear rate
- Live ops: bu civ’in season adı rezerv

### Act 6 — Viking

**Rol:** Ringfort + Jelling

**Keep:** Trelleborg circular; Jelling brand; amphibia.

**7-katman fantazi:** Shore stakes→ditch→palisade→gate→ring street→hall→ritual/Jelling twin.

**İmza kuleler:** Shieldhall barracks; Bow; Stake field; Ring watch; Horn signal.

**İmza düşmanlar/boss:** Bondi; Hirdmen; Ship rush; Jarl boss.

**Myth ultimates:** Myth playlist opsiyonel; horned helmet default KAPALI.

**Do-not-assume:** Boynuzlu miğfer FOLK; her Viking berserker değil.

**Pitch:** Hold the ring against the landing.

**Örnek map’ler:** Beach Landing; Ringfort Spin; Jelling Night.

- Codex zorunlu kart sayısı: keep + 3 birim + 2 tahkimat + 1 boss + 1 FOLK karşılaştırması
- Yıldız kapıları: ★2 sonraki act soft unlock
- Educational/Arcade: FOLK birimler Arcade’de, Historical’da kapalı
- Sanat: keep hero silhouette + bir gate close-up
- Ses: 1 boynuz stinger + 1 zafer kadansı
- Risk: kültürel/dini saygı checklist imzalı
- Telemetry: act funnel clear rate
- Live ops: bu civ’in season adı rezerv

### Act 7 — Greece

**Rol:** Acropolis + Long Walls

**Keep:** Dual-keep sacred hill + harbor.

**7-katman fantazi:** Outer dema→long walls corridor→gate courts→acropolis climb→cella; harbor chain parallel.

**İmza kuleler:** Hoplite barracks; Toxotes; Ballista late; Harbor chain; Long Walls relay.

**İmza düşmanlar/boss:** Peltast; Phalanx block enemy; Helepolis; Ephialtes traitor; Polemarch boss.

**Myth ultimates:** God FOLK playlist ayrı.

**Do-not-assume:** Polis≠tek krallık; her Yunan aynı kit değil.

**Pitch:** Two hearts, one treasury.

**Örnek map’ler:** Pass Hot Gates; Long Walls Run; Harbor Chain.

- Codex zorunlu kart sayısı: keep + 3 birim + 2 tahkimat + 1 boss + 1 FOLK karşılaştırması
- Yıldız kapıları: ★2 sonraki act soft unlock
- Educational/Arcade: FOLK birimler Arcade’de, Historical’da kapalı
- Sanat: keep hero silhouette + bir gate close-up
- Ses: 1 boynuz stinger + 1 zafer kadansı
- Risk: kültürel/dini saygı checklist imzalı
- Telemetry: act funnel clear rate
- Live ops: bu civ’in season adı rezerv

### Act 8 — Japan

**Rol:** Himeji

**Keep:** Daitenshu ~46.4 m; masugata.

**7-katman fantazi:** Moat→outer bailey→masugata courts→yagura line→inner→tenshu.

**İmza kuleler:** Yagura arrow; Teppo; Ashigaru barracks; Signal; Ishi-otoshi; Moat tower.

**İmza düşmanlar/boss:** Ashigaru mass; Samurai elite; Miner; Fire attack; Daimyo boss.

**Myth ultimates:** Kami FOLK season opsiyon.

**Do-not-assume:** Ninja ordusu yok; katana-primary mitos FOLK; teppō dönemli.

**Pitch:** Bend the approach through masugata.

**Örnek map’ler:** Masugata Mouth; Bailey Fire; Tenshu Hold.

- Codex zorunlu kart sayısı: keep + 3 birim + 2 tahkimat + 1 boss + 1 FOLK karşılaştırması
- Yıldız kapıları: ★2 sonraki act soft unlock
- Educational/Arcade: FOLK birimler Arcade’de, Historical’da kapalı
- Sanat: keep hero silhouette + bir gate close-up
- Ses: 1 boynuz stinger + 1 zafer kadansı
- Risk: kültürel/dini saygı checklist imzalı
- Telemetry: act funnel clear rate
- Live ops: bu civ’in season adı rezerv

### Act 9 — Mongol

**Rol:** Ordu → Dadu

**Keep:** Mobile ger; Karakorum; Dadu network fantasy.

**7-katman fantazi:** Scout screen→caltrop/fence→ger ring→command ger→ordo keep; city skin Dadu walls.

**İmza kuleler:** Ger archer relocatable; Lance barracks; Captured trebuchet; Remount; Decimal aura.

**İmza düşmanlar/boss:** Horse archer; Heavy lancer late; Engineer line; Feigned retreat; Qaghan boss.

**Myth ultimates:** Tengri FOLK dikkatli.

**Do-not-assume:** Mindless horde yok; lojistik+mühendislik vurgusu.

**Pitch:** The camp is the castle.

**Örnek map’ler:** Ordu Ring; Feigned Wave; Dadu Approaches.

- Codex zorunlu kart sayısı: keep + 3 birim + 2 tahkimat + 1 boss + 1 FOLK karşılaştırması
- Yıldız kapıları: ★2 sonraki act soft unlock
- Educational/Arcade: FOLK birimler Arcade’de, Historical’da kapalı
- Sanat: keep hero silhouette + bir gate close-up
- Ses: 1 boynuz stinger + 1 zafer kadansı
- Risk: kültürel/dini saygı checklist imzalı
- Telemetry: act funnel clear rate
- Live ops: bu civ’in season adı rezerv

### Act 10 — European Castle

**Rol:** Beaumaris / Krak

**Keep:** Concentric final exam.

**7-katman fantazi:** Glacis/moat→outer curtain→towers→gatehouse/barbican→inner ward→tower keep→lord chamber.

**İmza kuleler:** Curtain archer; Murder-hole gatehouse; Barbican; Mangonel/trebuchet; Men-at-arms; Hoarding etiketli.

**İmza düşmanlar/boss:** Levy; Men-at-arms; Ram; Mine; Trebuchet duel; King multi-phase.

**Myth ultimates:** Saint/relic FOLK opsiyon.

**Do-not-assume:** Kaynar yağ abartısı FOLK; konsantrik ARCH.

**Pitch:** Lose the outer ring, earn the inner slots — then hold the keep.

**Örnek map’ler:** Beaumaris Rings; Krak Spur; King’s Final.

- Codex zorunlu kart sayısı: keep + 3 birim + 2 tahkimat + 1 boss + 1 FOLK karşılaştırması
- Yıldız kapıları: ★2 sonraki act soft unlock
- Educational/Arcade: FOLK birimler Arcade’de, Historical’da kapalı
- Sanat: keep hero silhouette + bir gate close-up
- Ses: 1 boynuz stinger + 1 zafer kadansı
- Risk: kültürel/dini saygı checklist imzalı
- Telemetry: act funnel clear rate
- Live ops: bu civ’in season adı rezerv

### Act 0 ekstra — FTUE omurgası ile birleşik

`GDD-BABYLON-ACT0.md`: ≤60s ilk kule; ≤3 dk ilk clear; ≤8 dk soft ★3; ≤10 dk finale+Codex. Komutan tutor sesi; oyuncu adı ilk zaferden sonra. Threat spine eğitim kurgusu; kronoloji soft MOD framing.

Tag legend her kartta: ARCH/LIT/FOLK/SPN/MOD
Cinematic ≤8s skippable
Fail-safe ghost tower keep<50%
Sell %70 sayısal göster
Early-call wave 4+ sonra öğretilir
Cyrus twin-telling: Chronicle Opis vs Herodotean drain — ikisi de etiketli
Hanging Gardens: atmosfer particle only

## 9. Meta progresyon, Codex, zorluk

### 9.1 Katmanlar

Account XP — QoL (2x speed Act I clear VEYA IAP)
Commander level — küçük QoL, ham hasar% değil
Tower mastery — unvan + tiny cap
Civ affinity — VO/skin
Codex % — share flex
Museum slots — keep modelleri

### 9.2 Codex

Almanac DNA. Her giriş kısa + rozet + “oyunda görünür”. Post-victory Did-you-know 2 cümle. Teacher mode sonra.

### 9.3 Yıldız

★1 clear
★2 Keep≥70% veya inner leak yok
★3 Keep≥90% + ek
Heroic/Iron → seals

## 10. FTUE / onboarding (ilk 10 dakika)

**0:00–0:45** Etemenanki cold open; place Watchtower
**0:45–2:00** Wave1; gold/HP/timer; fail-safe tower
**2:00–3:30** Armored; upgrade; sell%70; XP toast
**3:30–5:30** İkinci tip; mini-boss; Codex #1
**5:30–7:00** Civ select (Babylon/Rome/Egypt); arrival 8s
**7:00–7:45** Educational↔Arcade (default Arcade)
**7:45–9:30** Graduation 5 wave; free hero cast
**9:30–10:00** Reward shower; Hub 3 button

Yasak: IAP duvarı, FTUE ads, 8 currency dersi, zorunlu hesap, lore duvarı. Returning commander: Act0 combat skip, Codex gifts kalır.

#### 10.1 Dakika 0 detay checklist

- Görsel netlik kontrolü (dakika 0)
- Input latency bütçesi <100ms his
- Tutorial blocker yalnızca bir aksiyon
- Skip text after first combat (m≥1)
- Analytics: `ftue_minute_0_alive`
- Soft hint max 1 toast
- No shop deep-link
- No energy language anywhere

#### 10.2 Dakika 1 detay checklist

- Görsel netlik kontrolü (dakika 1)
- Input latency bütçesi <100ms his
- Tutorial blocker yalnızca bir aksiyon
- Skip text after first combat (m≥1)
- Analytics: `ftue_minute_1_alive`
- Soft hint max 1 toast
- No shop deep-link
- No energy language anywhere

#### 10.3 Dakika 2 detay checklist

- Görsel netlik kontrolü (dakika 2)
- Input latency bütçesi <100ms his
- Tutorial blocker yalnızca bir aksiyon
- Skip text after first combat (m≥1)
- Analytics: `ftue_minute_2_alive`
- Soft hint max 1 toast
- No shop deep-link
- No energy language anywhere

#### 10.4 Dakika 3 detay checklist

- Görsel netlik kontrolü (dakika 3)
- Input latency bütçesi <100ms his
- Tutorial blocker yalnızca bir aksiyon
- Skip text after first combat (m≥1)
- Analytics: `ftue_minute_3_alive`
- Soft hint max 1 toast
- No shop deep-link
- No energy language anywhere

#### 10.5 Dakika 4 detay checklist

- Görsel netlik kontrolü (dakika 4)
- Input latency bütçesi <100ms his
- Tutorial blocker yalnızca bir aksiyon
- Skip text after first combat (m≥1)
- Analytics: `ftue_minute_4_alive`
- Soft hint max 1 toast
- No shop deep-link
- No energy language anywhere

#### 10.6 Dakika 5 detay checklist

- Görsel netlik kontrolü (dakika 5)
- Input latency bütçesi <100ms his
- Tutorial blocker yalnızca bir aksiyon
- Skip text after first combat (m≥1)
- Analytics: `ftue_minute_5_alive`
- Soft hint max 1 toast
- No shop deep-link
- No energy language anywhere

#### 10.7 Dakika 6 detay checklist

- Görsel netlik kontrolü (dakika 6)
- Input latency bütçesi <100ms his
- Tutorial blocker yalnızca bir aksiyon
- Skip text after first combat (m≥1)
- Analytics: `ftue_minute_6_alive`
- Soft hint max 1 toast
- No shop deep-link
- No energy language anywhere

#### 10.8 Dakika 7 detay checklist

- Görsel netlik kontrolü (dakika 7)
- Input latency bütçesi <100ms his
- Tutorial blocker yalnızca bir aksiyon
- Skip text after first combat (m≥1)
- Analytics: `ftue_minute_7_alive`
- Soft hint max 1 toast
- No shop deep-link
- No energy language anywhere

#### 10.9 Dakika 8 detay checklist

- Görsel netlik kontrolü (dakika 8)
- Input latency bütçesi <100ms his
- Tutorial blocker yalnızca bir aksiyon
- Skip text after first combat (m≥1)
- Analytics: `ftue_minute_8_alive`
- Soft hint max 1 toast
- No shop deep-link
- No energy language anywhere

#### 10.10 Dakika 9 detay checklist

- Görsel netlik kontrolü (dakika 9)
- Input latency bütçesi <100ms his
- Tutorial blocker yalnızca bir aksiyon
- Skip text after first combat (m≥1)
- Analytics: `ftue_minute_9_alive`
- Soft hint max 1 toast
- No shop deep-link
- No energy language anywhere

## 11. Retention, habit, viral, bildirimler (etik)

### 11.1 KPI bantları

| | Floor | Healthy | Stretch |
|--|------|---------|---------|
| D1 | %22 | %28–35 | %38–45 |
| D7 | %5 | %10–15 | %15–22 |
| D30 | %2 | %4–7 | %8–12 |
| D7/D1 | 0.25 | 0.35–0.45 | ≥0.45 |

### 11.2 Hook modeli

| Adım | TD | Civ flavor |
|------|----|------------|
| Trigger | push/badge/streak/lonca | gece baskını / festival |
| Action | 1–3 wave | imza birim |
| Variable reward | chest/shard | relic/blueprint |
| Investment | mastery/layout | benim surum |

### 11.3 One more wave kuralları

6–12 dk ortalama stage
Clear sonrası instant retry
Kısmi wave ödülü
D0–2 energy soft-lock yok
Anti: lobby reload

### 11.4 Viral

Victory/Codex share card
Friend challenge aynı seed
Referral kozmetik
Guild muster
Creator browser phase2
Trailer=gameplay

### 11.5 Bildirim etik

≤2/gün
Sessiz saat
FTUE bitmeden agresif yok
‘Enerji doldu’ metni yok
D3/7/14 reacquisition kozmetik

### 11.6 D0–D14 script
- **D0:** Act0 clear
- **D1:** Daily+Act1
- **D2:** Mini-boss+starter ethical
- **D3:** 3★ chase
- **D4:** Codex milestone
- **D5:** Guild soft invite
- **D6:** Weekly teaser
- **D7:** Weekly summary+BP
- **D8:** Second civ
- **D9:** Boss teaser
- **D10:** Friend challenge
- **D11:** Iron optional
- **D12:** Cosmetic craft
- **D13:** Muster preview
- **D14:** Roadmap mirror

### 11.7 Bağımlılık dili çevirisi

Sevilen: one more round, boss bitir, daily done, 3-star, collection, clan chest. Nefret: beklemeden oynayamama, rigged gacha, homework. Habit loop kur; dark pattern kurma.

## 12. Sosyal / lonca / paylaşım

### 12.1 İlkeler

Opt-in solo eksiksiz
Async-first; co-op phase2
Skill bracket not wallet
Tarihsel emote tonu
Az serbest metin
Minors: chat kapalı
Her özellik D7+D30 işi adlandırır

### 12.2 Cohort

Cap 30/40. Consul/Prefects/Veterans/Recruits. Guild XP kaliteden; harcamadan değil. Level kozmetik fort — DPS değil.

### 12.3 Async fort

Watch duty 1-run slot; haftalık seed doktrin; ortak chest kozmetik+soft. D7 appointment; D30 obligation.

### 12.4 Leaderboard

Rookie/Regular/Veteran/Legend
IAP ignore
Hard meta strip
Seed+time validate; replay later
Vanity frame only

### 12.5 Share/referral

PII yok; referral sticker; friend same seed. historymemes uyumlu fact+keep görseli.

### 12.6 Anti-toksisite mimarisi

Hazır emote havuzu
Report/mute
Kick cooldown
No pay-to-win dues
No forced voice chat
Moderation queue phase2 text
Privacy defaults teens+

## 13. Monetizasyon (IAP, reklam, battle pass)

### 13.1 Kuzey yıldızı

Skill kampanyayı geçer; para güzellik/kolaylık/erken erişim alır; reklam ödül verir, cezalandırmaz. CAPS$: C birincil, A zaman kaydırmalı, P yasak, S kozmetik, $ kumar yok.

### 13.2 Currencies

| Birim | Satın? |
|-------|--------|
| Grain/Silver | evet küçük |
| Obsidian Seal | evet |
| Civ Tokens | hayır/sınırlı |
| Energy | **YOK** |

### 13.3 Katalog

Starter 48s $2.99–4.99
Cosmetics %80–90 earnable; 0 combat stat
2x speed clear VEYA IAP
BP 6–8hf $7.99–9.99; XP satın alma sınırlı; archive shop
Civ packs kozmetik+VO
Remove ads tek sefer; rewarded kalabilir
PC premium/parity pack
Extra build slot YOK
Güçlü kule YOK

### 13.4 Ads

Allowed: double soft; 1 continue; daily chest; farm ticket; intel preview
Forbidden: interstitial; ad-gated level; banner on build UI; claim-earned-via-ad; remove-ads yalanı
Cap ~6 prompt/gün; FTUE yok
Mix ~70–80 IAP / 20–30 ads

### 13.5 Checklist

Whale skill’siz hard alamaz
Non-spender ≥%70 kampanya
Soft sink craft
Keyword alarm greedy/ads/P2W
Iron’da continue ad kapalı

### 13.6 Tuzak listesi
- ❌ PvZ2 energy
- ❌ KR paywall towers
- ❌ Revive-rigged spike
- ❌ Idle disguise
- ❌ BP power creep
- ❌ Lootbox towers
- ❌ VIP damage%
- ❌ Pay-rank boards
- ❌ Ad after every death
- ❌ Fake limited offer
- ❌ Sub hâlâ forced video
- ❌ FTUE IAP wall

## 14. Live ops takvimi

| Cadence | İçerik |
|---------|--------|
| Weekly | daily set; hotfix |
| Biweekly | mini skirmish |
| Monthly | Boss Siege |
| 6–8w | BP season |
| Quarterly | civ act / map pack |
| Yearly | anniversary |

Collection event
Boss ladder
Guild fort
Double star dikkatli
Anniversary/museum fantasy

Sezon adları: Masugata, Immortals, Ringfort, Wengcheng, Processional Way.

### 14.1 90 gün özet
- Hafta 1: live ops maddesi #1 (daily/boss/BP/civ drip/balance/guild/share) — detay operasyon board’unda.
- Hafta 2: live ops maddesi #2 (daily/boss/BP/civ drip/balance/guild/share) — detay operasyon board’unda.
- Hafta 3: live ops maddesi #3 (daily/boss/BP/civ drip/balance/guild/share) — detay operasyon board’unda.
- Hafta 4: live ops maddesi #4 (daily/boss/BP/civ drip/balance/guild/share) — detay operasyon board’unda.
- Hafta 5: live ops maddesi #5 (daily/boss/BP/civ drip/balance/guild/share) — detay operasyon board’unda.
- Hafta 6: live ops maddesi #6 (daily/boss/BP/civ drip/balance/guild/share) — detay operasyon board’unda.
- Hafta 7: live ops maddesi #7 (daily/boss/BP/civ drip/balance/guild/share) — detay operasyon board’unda.
- Hafta 8: live ops maddesi #8 (daily/boss/BP/civ drip/balance/guild/share) — detay operasyon board’unda.
- Hafta 9: live ops maddesi #9 (daily/boss/BP/civ drip/balance/guild/share) — detay operasyon board’unda.
- Hafta 10: live ops maddesi #10 (daily/boss/BP/civ drip/balance/guild/share) — detay operasyon board’unda.
- Hafta 11: live ops maddesi #11 (daily/boss/BP/civ drip/balance/guild/share) — detay operasyon board’unda.
- Hafta 12: live ops maddesi #12 (daily/boss/BP/civ drip/balance/guild/share) — detay operasyon board’unda.
- Hafta 13: live ops maddesi #13 (daily/boss/BP/civ drip/balance/guild/share) — detay operasyon board’unda.

## 15. UI/UX hub, kontroller

Hub: Chronicle, Armory, Codex, Events, Cohort, Shop(cosmetics first), Settings
HUD: build bar, wave banner, Keep Integrity, resources, hero, early-call, speed, pause
PC hotkeys 1–0/Q/E/Space
Colorblind shapes
Shake slider
One-hand
Subtitles

## 16. Teknik/üretim kapsamı, launch kesiti

Scope canavarı mitigasyonu: ortak kule kit + trim sheets; launch Act0–III+; drip rest. Sanat tek viewing cone. Telemetry leak/pick/FTUE/ad/IAP. M0 vertical slice Babylon Map1+3 kule+barracks+8 wave.

M0 Vertical slice
M1 FTUE+Codex10
M2 Rome+daily
M3 Soft launch 4 act
M4 BP+guild+boss
M5 Civ drip

## 17. SWOT, riskler, odak

**S:** 11 civ araştırma; keep USP; fair F2P; unique map verbs; edu yan pazar. **W:** scope; authenticity tension; cultural sensitivity; discovery cost; polish risk. **O:** fair TD gap; history creators; season drip; guild viral; museum. **T:** NK/Ironhide; clones+fake ads; policy; review bomb; AI-art backlash.

| ID | Risk | Mitigasyon |
|----|------|------------|
| R1 | Scope | Launch kesiti |
| R2 | P2W algı | Cosmetics-only bible |
| R3 | Misrep | Sensitivity+FOLK |
| R4 | D7 flat | Boss/weekly/guild |
| R5 | Ad fatigue | Rewarded+remove |
| R6 | Balance debt | Weekly patch |
| R7 | Art cost | Modular kits |
| R8 | Offline bugs | Campaign offline first |
| R9 | Cheats | Brackets+replay |
| R10 | Legal | PD+original audio |
| R11 | Fake UA | Real trailer policy |
| R12 | FTUE churn | 10dk script |

**P0** combat/fair/FTUE/Act0
**P1** daily/boss/Codex/BP
**P2** share/guild/challenges
**P3** civ/co-op/editor

## 18. KPI’lar ve başarı kriterleri

| KPI | Healthy |
|-----|---------|
| D1 | %28–35 |
| D7 | %10–15 |
| D30 | %4–7 |
| FTUE complete | %55–65 |
| Store rating | ≥4.5 |
| Non-spender campaign | ≥%70 maps |

Ship gates: no energy; no FTUE ads/IAP; Iron no continue-ad; Codex tags; Maya≠Aztek UI; real trailer; reduced motion; telemetry

## 19. Açık sorular / sonraki adımlar

Motor seçimi
PC premium vs parity
Arcade vs Educational A/B
Co-op zamanı
Babylon master doc FLAG kapat
Sensitivity calendar
M0 kilidi
UA compliance
Async server maliyet
Map editor moderation

## Ek A — Terimler sözlüğü
| **Keep** | Savunulacak kalp yapı |
| **Pad** | Sabit kule yuvası |
| **Hold/Shoot/Shape/Punish/Pivot** | Beş fiil |
| **Favor** | Ritüel kaynak |
| **Keep Integrity** | Can yerine bütünlük |
| **Early-call** | Erken dalga |
| **Codex** | Almanak |
| **ARCH/LIT/FOLK/MOD/SPN/CHR** | Kanıt etiketleri |
| **Iron/Heroic** | Zor madalya |
| **Cohort** | Lonca |
| **Watch duty** | Async savunma |
| **Obsidian Seal** | Premium currency |
| **Masugata** | 90° kapı avlusu |
| **Wengcheng** | Urn kapı avlusu |
| **Feigned retreat** | Telegraflı dönüş |
| **Educational/Arcade** | Modlar |
| **Parity pack** | Reklam/ticket kaldırma |
| **Rule-change boss** | Mekanik değiştiren boss |

## Ek B — Do / Don't

✅ Enerjisiz kampanya
✅ Rewarded opt-in
✅ Kozmetik güçsüz
✅ Early-call
✅ FOLK etiket
✅ Offline kampanya
✅ Real trailer
✅ Skill bracket
✅ Act0 graduation
✅ Complete free kit
❌ Energy
❌ Forced interstitial
❌ Paywalled necessary towers
❌ Gacha power
❌ Fake ads
❌ Horned viking default
❌ Maya=Aztek
❌ Free orbit combat
❌ VIP damage%
❌ FTUE IAP wall

## Ek C — Kaynak haritası
- `docs/_research/GDD-ATTACK-WAVES.md`
- `docs/_research/GDD-BABYLON-ACT0.md`
- `docs/_research/GDD-CAMERA-JUICE.md`
- `docs/_research/GDD-CIV-HOOKS.md`
- `docs/_research/GDD-COMPETITORS-TD.md`
- `docs/_research/GDD-COMPETITORS.md`
- `docs/_research/GDD-DEFENSE-ATTACK.md`
- `docs/_research/GDD-DEFENSE-SYSTEMS.md`
- `docs/_research/GDD-LIVEOPS-FTUE-META.md`
- `docs/_research/GDD-LIVEOPS-FTUE.md`
- `docs/_research/GDD-MONETIZATION.md`
- `docs/_research/GDD-REDDIT-DEEP.md`
- `docs/_research/GDD-REDDIT-SENTIMENT.md`
- `docs/_research/GDD-RETENTION-VIRAL.md`
- `docs/_research/GDD-RISKS-SWOT-SCOPE.md`
- `docs/_research/GDD-SOCIAL-GUILD.md`
- `docs/_research/GDD-STORE-REVIEWS.md`
- `docs/_research/GDD-WAVES-ECONOMY.md`
- `docs/TOWER-DEFENCE-TARIHSEL-ARASTIRMA-INDEX.md`
- Tüm medeniyet `*-TARIHSEL-ARASTIRMA.md` master’ları

## Ek D — Medeniyet kule / düşman matrisi

| Civ | Swarm skin | Armored skin | Fast skin | Siege skin | Boss hinge |
|-----|---|---|---|---|---|
| Babylon | levy/skirmish | elite block | cavalry/ship | engines/ladders | named ruler |
| Rome | levy/skirmish | elite block | cavalry/ship | engines/ladders | named ruler |
| Persia | levy/skirmish | elite block | cavalry/ship | engines/ladders | named ruler |
| Egypt | levy/skirmish | elite block | cavalry/ship | engines/ladders | named ruler |
| China | levy/skirmish | elite block | cavalry/ship | engines/ladders | named ruler |
| Maya | levy/skirmish | elite block | cavalry/ship | engines/ladders | named ruler |
| Mexica | levy/skirmish | elite block | cavalry/ship | engines/ladders | named ruler |
| Viking | levy/skirmish | elite block | cavalry/ship | engines/ladders | named ruler |
| Greece | levy/skirmish | elite block | cavalry/ship | engines/ladders | named ruler |
| Japan | levy/skirmish | elite block | cavalry/ship | engines/ladders | named ruler |
| Mongol | levy/skirmish | elite block | cavalry/ship | engines/ladders | named ruler |
| EuroCastle | levy/skirmish | elite block | cavalry/ship | engines/ladders | named ruler |

## Ek E — Örnek map brief’leri

| ID | Civ | Öğreti |
|----|-----|--------|
| B0-01 | Babylon | place+upgrade |
| B0-02 | Babylon | terrace range |
| B0-03 | Babylon | early-call+boss |
| R1-01 | Rome | traps |
| R1-02 | Rome | elephant |
| P2-01 | Persia | fork path |
| E3-01 | Egypt | ditch+nile |
| C4-01 | China | wengcheng |
| G7-01 | Greece | dual-keep |
| J8-01 | Japan | masugata |
| M9-01 | Mongol | ger+feign |
| EU10-01 | Euro | concentric slots |

## Ek F — 90 günlük takvim (özet satırlar)
- Gün 1: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 2: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 3: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 4: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 5: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 6: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 7: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 8: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 9: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 10: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 11: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 12: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 13: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 14: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 15: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 16: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 17: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 18: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 19: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 20: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 21: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 22: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 23: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 24: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 25: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 26: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 27: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 28: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 29: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 30: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 31: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 32: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 33: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 34: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 35: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 36: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 37: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 38: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 39: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 40: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 41: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 42: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 43: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 44: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 45: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 46: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 47: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 48: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 49: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 50: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 51: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 52: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 53: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 54: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 55: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 56: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 57: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 58: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 59: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 60: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 61: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 62: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 63: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 64: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 65: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 66: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 67: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 68: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 69: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 70: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 71: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 72: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 73: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 74: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 75: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 76: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 77: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 78: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 79: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 80: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 81: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 82: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 83: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 84: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 85: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 86: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 87: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 88: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 89: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.
- Gün 90: operasyon notu — daily/content/BP/guild/analytics kontrol listesi maddesi.

## Ek G — Kodlamaya ilk 20 görev
1. Kamera rig
2. Path+roles
3. Pad place/upgrade/sell
4. Keep Integrity
5. Gold+rewards
6. Barracks AI
7. Targeting
8. Wave director
9. Early-call
10. Hero+skill
11. B0-01 blockout
12. FTUE director
13. Hub shell
14. Codex schema
15. Analytics schema
16. A11y settings
17. Rewarded stub
18. IAP cosmetic flag
19. Daily seed
20. Share card

## Ek H — 4chan / anon litmus (kısa)

`GDD-4CHAN-FORUM-SENTIMENT.md`: skill-based/offline/mods/juice övülür; energy/forced ads/power pass/fake trailer/AI slop/NFT reddedilir. Review-bomb litmus = aynı adil vaat.

## Ek I — Store review temalarından dersler

KR5: polish övgü, mobil paywall/kısa içerik yergi. BTD6: derinlik+fair övgü, forced online yergi. PvZ1 goodwill; PvZ2 F2P yergi. Ders: eksiksiz kit + hacim + offline + adil monetizasyon.

## 20. Kapanış

Bu GDD, tarihsel araştırma paketinin oyunlaştırılmış tek ürün anayasasıdır. **Yoğunlaş:** Etemenanki’de 7-katman grameri öğret; adil mid-core döngüyle birleştir; keep’i marka yap; parayı güce değil güzelliğe bağla; her gün “bir kuşatma daha” dedirt.

*— Son / v1.0 sentez —*


## Ek J — Derin operasyonel notlar (kod öncesi)

### J.1 Targeting ve mikro
First/Last/Strong/Weak/Siege/Flying her kuleden erişilir. Mobil varsayılan Strong; uzun basış advanced panel. Kingdom Rush oyuncuları hero micro ister; Bloons oyuncuları targeting ister — ikisi de opsiyonel tutulur, zorunlu mikro dayatılmaz.

### J.2 Pathing kenar durumları
Dual path’te düşman dağılımı seed’lidir. Traitor lane açılınca 2 saniye telegraph + minimap pulse. Bridge destroy path rewrite pahalı Stone/Favor ister. Naval path_layer ayrıdır; harbor kuleleri counter’dır, “uçan birim”e kaçmaz.

### J.3 Sell abuse
★3 koşullarında aşırı sell-rebuy penceresi kısıtlanabilir. Iron’da sell restricted opsiyonel kural olarak sunulur. Amaç skill’i cezalandırmak değil, exploit’i kapatmak.

### J.4 Fog ve intel
Hard mode’da fog; scout kulesi veya rewarded intel. Elite composition tamamen kör bırakılmaz — adalet hissi retention’dır.

### J.5 Naval ve kıyı
Yunan / Mısır / Viking / Japonya kıyı map’lerinde naval lane birincil flyer yerine geçer. Telegraphed landing “adil baskı”dır.

### J.6 Moral / ideology
Opsiyonel sayaçlar: yüksek leak gold penalty; Roma tapınak yangını; Çin Mandate; Mısır Maʿat. Bunlar keep HP’nin yan lose-condition’larıdır — UI’da net anlatılır.

### J.7 Zorluk matematiği
Kör DPS çarpanı yerine: HP ×1.0/1.15/1.3; count ×1.0/1.1; rol karışımı zenginleşir. Boss’lar her zorlukta rule-change korur.

### J.8 Erişilebilirlik checkpoint
Uzun kuşatmalarda her 5 dalgada checkpoint (Iron’da kapatılabilir). Bu “kolay mod” değil, motor engeli / seans kesintisi için yaşam kalitesidir.

### J.9 Creator / spectator (phase 2)
Seed paylaşımı; moderation kuyruğu; ranked ayrı havuz. Launch blocker değildir.

### J.10 Yerelleştirme
Türkçe + İngilizce öncelik. Civ isimleri yerel okunuş. FOLK notları çeviride korunur — mitin “gerçek”e kayması çeviri hatası olmasın.

### J.11 Analytics etiği
Kids + behavioral ads yok. Privacy manifest. Hesap silme yolu. Telemetry balance içindir, karanlık pattern optimize etmek için değil.

### J.12 Destek ve iade
Kozmetik iade politikası net. Restore purchases. FAQ: “hasar satılmaz”.

### J.13 Topluluk incili
Bu GDD’nin kısa oyuncu özeti blog olarak yayınlanabilir — goodwill marketing.

### J.14 Esports
Launch’ta yok. Async bracket yeter. Real-time toksik risk / maliyet.

### J.15 Speedrun kültürü
Opsiyonel timer; share card süre; pay-to-skip yok.

### J.16 Mevsimsel FX
Kozmetik hava durumu gameplay fog modifier’dan ayrıdır.

### J.17 Arkadaş listesi
Platform native + hafif in-game; spam invite yok.

### J.18 AFK
Pause var; reklam için auto-clear yok.

### J.19 Bulut kayıt
Guest local + link; çakışmada timestamp.

### J.20 Hile raporu
Share card akışında soft report.

### J.21 Örnek düşman stat iskeleti (normalize, kilitli değil)

| Rol | HP | Armor | Speed | Keep dmg | Gold |
|-----|----|-------|-------|----------|------|
| SWARM | 30 | 0 | 1.2 | 1 | 3 |
| ARMORED | 120 | 8 | 0.7 | 3 | 12 |
| FAST | 40 | 0 | 1.8 | 2 | 6 |
| SIEGE | 200 | 5 | 0.45 | 0 (yapı) | 20 |
| BOSS | 3000 | 10 | 0.5 | 15 | 100 |
| SAPPER | 80 | 2 | 1.0 | 2 | 15 |

### J.22 Medeniyet ses mikro-kopya şablonları

Her medeniyet için aynı iskelet, farklı VO:


#### Babylon
- Wave start: “Babylon nöbeti — duvarlar hazır.”
- Early-call: “Boynuz çalındı.”
- Leak: “Keep bütünlüğü sarsıldı.”
- Victory: “Babylon keep’i dayandı.”
- Defeat: “Sur çatladı — Codex’e not.”
- Shop: “Süsleme — savaş gücüne etkisi yok.”
- FOLK: “Mit katmanı açık — rozetleri izle.”

#### Rome
- Wave start: “Rome nöbeti — duvarlar hazır.”
- Early-call: “Boynuz çalındı.”
- Leak: “Keep bütünlüğü sarsıldı.”
- Victory: “Rome keep’i dayandı.”
- Defeat: “Sur çatladı — Codex’e not.”
- Shop: “Süsleme — savaş gücüne etkisi yok.”
- FOLK: “Mit katmanı açık — rozetleri izle.”

#### Persia
- Wave start: “Persia nöbeti — duvarlar hazır.”
- Early-call: “Boynuz çalındı.”
- Leak: “Keep bütünlüğü sarsıldı.”
- Victory: “Persia keep’i dayandı.”
- Defeat: “Sur çatladı — Codex’e not.”
- Shop: “Süsleme — savaş gücüne etkisi yok.”
- FOLK: “Mit katmanı açık — rozetleri izle.”

#### Egypt
- Wave start: “Egypt nöbeti — duvarlar hazır.”
- Early-call: “Boynuz çalındı.”
- Leak: “Keep bütünlüğü sarsıldı.”
- Victory: “Egypt keep’i dayandı.”
- Defeat: “Sur çatladı — Codex’e not.”
- Shop: “Süsleme — savaş gücüne etkisi yok.”
- FOLK: “Mit katmanı açık — rozetleri izle.”

#### China
- Wave start: “China nöbeti — duvarlar hazır.”
- Early-call: “Boynuz çalındı.”
- Leak: “Keep bütünlüğü sarsıldı.”
- Victory: “China keep’i dayandı.”
- Defeat: “Sur çatladı — Codex’e not.”
- Shop: “Süsleme — savaş gücüne etkisi yok.”
- FOLK: “Mit katmanı açık — rozetleri izle.”

#### Maya
- Wave start: “Maya nöbeti — duvarlar hazır.”
- Early-call: “Boynuz çalındı.”
- Leak: “Keep bütünlüğü sarsıldı.”
- Victory: “Maya keep’i dayandı.”
- Defeat: “Sur çatladı — Codex’e not.”
- Shop: “Süsleme — savaş gücüne etkisi yok.”
- FOLK: “Mit katmanı açık — rozetleri izle.”

#### Mexica
- Wave start: “Mexica nöbeti — duvarlar hazır.”
- Early-call: “Boynuz çalındı.”
- Leak: “Keep bütünlüğü sarsıldı.”
- Victory: “Mexica keep’i dayandı.”
- Defeat: “Sur çatladı — Codex’e not.”
- Shop: “Süsleme — savaş gücüne etkisi yok.”
- FOLK: “Mit katmanı açık — rozetleri izle.”

#### Viking
- Wave start: “Viking nöbeti — duvarlar hazır.”
- Early-call: “Boynuz çalındı.”
- Leak: “Keep bütünlüğü sarsıldı.”
- Victory: “Viking keep’i dayandı.”
- Defeat: “Sur çatladı — Codex’e not.”
- Shop: “Süsleme — savaş gücüne etkisi yok.”
- FOLK: “Mit katmanı açık — rozetleri izle.”

#### Greece
- Wave start: “Greece nöbeti — duvarlar hazır.”
- Early-call: “Boynuz çalındı.”
- Leak: “Keep bütünlüğü sarsıldı.”
- Victory: “Greece keep’i dayandı.”
- Defeat: “Sur çatladı — Codex’e not.”
- Shop: “Süsleme — savaş gücüne etkisi yok.”
- FOLK: “Mit katmanı açık — rozetleri izle.”

#### Japan
- Wave start: “Japan nöbeti — duvarlar hazır.”
- Early-call: “Boynuz çalındı.”
- Leak: “Keep bütünlüğü sarsıldı.”
- Victory: “Japan keep’i dayandı.”
- Defeat: “Sur çatladı — Codex’e not.”
- Shop: “Süsleme — savaş gücüne etkisi yok.”
- FOLK: “Mit katmanı açık — rozetleri izle.”

#### Mongol
- Wave start: “Mongol nöbeti — duvarlar hazır.”
- Early-call: “Boynuz çalındı.”
- Leak: “Keep bütünlüğü sarsıldı.”
- Victory: “Mongol keep’i dayandı.”
- Defeat: “Sur çatladı — Codex’e not.”
- Shop: “Süsleme — savaş gücüne etkisi yok.”
- FOLK: “Mit katmanı açık — rozetleri izle.”

#### European Castle
- Wave start: “European Castle nöbeti — duvarlar hazır.”
- Early-call: “Boynuz çalındı.”
- Leak: “Keep bütünlüğü sarsıldı.”
- Victory: “European Castle keep’i dayandı.”
- Defeat: “Sur çatladı — Codex’e not.”
- Shop: “Süsleme — savaş gücüne etkisi yok.”
- FOLK: “Mit katmanı açık — rozetleri izle.”


### J.23 Adalet beyanı (mağaza / ayarlar metni)

> Bu oyunda kampanyayı bitirmek için gerçek para gerekmez. Reklamlar isteğe bağlı ödüldür. Satın alımlar görünüm, konfor ve destek içindir; hasar çarpanı satılmaz. Mitolojik içerik FOLK olarak işaretlenir. Tarihsel modda kanıt rozetleri açıktır.

### J.24 Yoğunlaşma özeti (tek sayfa)

1. Combat feel + juice (P0)
2. Fair economy / no energy (P0)
3. FTUE 10 dk (P0)
4. Act0 Babylon polish (P0)
5. Daily/weekly/boss (P1)
6. Codex collection (P1)
7. Cosmetics BP (P1)
8. Share + guild async (P2)
9. Civ drip (P3)
10. Co-op / editor (P3)

*— Ek J sonu —*

## Ek K — Sürüm geçmişi

| Sürüm | Not |
|-------|-----|
| 1.0 | İlk tam sentez GDD; 10+ araştırma ajanı + medeniyet paketi |
| 1.0.1 | Ek J operasyonel notlar; 4chan litmus; index link |

