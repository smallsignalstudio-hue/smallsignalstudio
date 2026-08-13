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

## 4. Çekirdek döngü (saniye → sezon)

*(Kaynak: `GDD-WAVES-ECONOMY.md`, `GDD-DEFENSE-SYSTEMS.md` §0–§1, `GDD-LIVEOPS-FTUE-META.md` §1 ve §9, `GDD-RETENTION-VIRAL.md` §3–§4 ve §9–§10, `GDD-ATTACK-WAVES.md` §0)*

### 4.0 Neden "döngü" tek bir bölüm

Bu oyun yedi farklı zaman ölçeğinde eşzamanlı çalışır: saniye (bir kulenin ateş etmesi), dalga (bir wave'in gelişi), harita (bir seviyenin baştan sona oynanışı), gün, hafta, sezon (~6–7 hafta) ve yıl. Her ölçek, üstündeki ölçeğe **motivasyon** ve altındaki ölçeğe **bağlam** verir. Bir tasarımcı "neden bu buton var" sorusuna hangi ölçekte cevap verdiğini bilmeli — aksi halde döngüler birbirini yer (örn. günlük görev sistemi kampanya haritasının kendi ritmini bastırırsa).

### 4.1 Yedi ölçek — tek tablo

| Ölçek | Süre | Birim eylem | Ürettiği duygu | Bir üstüne besler |
|---|---|---|---|---|
| **Saniye** | 0.1–3s | Kule ateşi, hero cast, hasar sayısı, hit-stop | Anlık tatmin (juice) | Dalga okunabilirliği |
| **Dalga (wave)** | 15–90s | Bir düşman grubunun gelişi + temizlenmesi | "Tuttu" / "Sızdı" mikro-zafer | Harita gerilim eğrisi |
| **Harita (mission)** | 3–18 dk | Tam bir seviyenin baştan zafere/yenilgiye | "Bir tur daha" dürtüsü | Kampanya/act ilerlemesi |
| **Gün** | ~15–25 dk oturum | Günlük görevler + 1–3 harita denemesi + lonca dokunuşu | Alışkanlık (habit) tamamlanma | Haftalık taahhüt |
| **Hafta** | 7 gün | Haftalık görev tahtası + orta-hafta modifikatör + hafta sonu kuşatma | Sosyal yükümlülük + FOMO'suz aciliyet | Sezon ilerlemesi |
| **Sezon** | 6–7 hafta | Battle pass + spotlight medeniyet + Codex kanadı + finale co-op boss | Medeniyet kimliği + koleksiyon | Yıllık takvim |
| **Yıl** | 8 sezon (~12 ay) | Roster genişlemesi (drip), meta sistem evrimi, topluluk oyu | Marka bağlılığı, "bu oyunun tarihi" | Ürün vizyonu (§0.2) |

**Tasarım kuralı:** Her yeni özellik önerisi şu soruyu cevaplamalı: "Hangi ölçekte yaşıyor, ve bir üst ölçeğe ne besliyor?" Cevapsızsa özellik askıya alınır (bkz. §3.9 madde 10 — kaldırılınca oyun bozulmuyorsa süstür).

### 4.2 Saniye ölçeği — beş fiil zinciri

`GDD-DEFENSE-SYSTEMS.md` §0'daki beş fiil (**Shoot / Hold / Shape / Punish / Pivot**) saniye ölçeğinde şu sırayla zincirlenir:

```
Düşman path'e girer
  → Shape (duvar/hendek) yol uzunluğunu ve hızını belirler
    → Punish (tuzak/hazard) forced tile'da burst hasar uygular
      → Hold (kışla blokeri) melee'de tutar, dwell time yaratır
        → Shoot (kule) dwell time'ı DPS'e çevirir
          → Pivot (hero) leak anında veya elit hedefte oyuncu ajansı devreye girer
```

Bu zincir her saniye binlerce kez çalışır ama **oyuncuya görünen kısmı** üç şeydir: (1) yerleştirme kararı (build phase), (2) izleme + micro-adjust (dalga sırasında), (3) sonuç okuması (dalga sonu banner). §5'teki kamera ve §7'deki juice bütçesi bu üç görünür anı okunabilir tutmak için var.

### 4.3 Dalga ölçeği — "dalga bir cümledir"

`GDD-WAVES-ECONOMY.md` §2 ve `GDD-ATTACK-WAVES.md` §1 sentezi: her dalga şu gramerle yazılır:

```
[açılış koşucuları] + [ana kuvvet] + [özel rol] + [opsiyonel mini-boss]
```

| Bileşen | Job | Örnek |
|---|---|---|
| Açılış koşucuları (opener runners) | Tempo öğret, ilk temas | SWARM rolü, düşük HP |
| Ana kuvvet (main force) | Asıl DPS testi | ARMORED veya karışık rol |
| Özel rol (special) | O haritanın "bugünkü ders"i | FAST leak, SIEGE ENGINE, TRAITOR/SAPPER |
| Opsiyonel mini-boss (bosslet) | Dalga sonu iğne | Named unit, kural değişimi yok ama tehdit yüksek |

Dalga içi gerilim eğrisi: **W1–3 öğret (tek tip) → W4–7 zırh karışımı → W8–12 kuşatma motorları belirir → orta mini-boss → final: general + karışık ordu + senaryo olayı (kapı kırılması / yangın)** (`GDD-WAVES-ECONOMY.md` §2).

### 4.4 Ekonomi — dört kaynak, tek disiplin

`GDD-DEFENSE-SYSTEMS.md` §5 dört kaynağı **rol** olarak tanımlar, dört gri para değil:

| Kaynak | Fantazi | Ne alır | Kıtlık hissi |
|---|---|---|---|
| **Gold (Altın)** | Nakit, yağma, haraç | Asker maaşı, tuzak yenileme, paralı Hold, acil tamir | Dalga geliri + yan hedefler |
| **Wood (Ahşap)** | Kereste, sepetli sal, gemi, hoarding | Hızlı kuleler, kazık, geçici kışla, köprü, ateş cephanesi | Erken bol; yanar; geç oyunda Stone'a göre zayıf |
| **Stone (Taş)** | Kesme taş, tuğla, hangtu, ishigaki | Kalıcı perde duvar, kapı evi, ağır kule, keep yükseltmesi | Yavaş gelir; orta-geç güç eğrisini tanımlar |
| **Favor (İtibar/Lütuf)** | Tanrılar, atalar, Mandate, kutsal emanetler | Hero ult'ları, ideoloji kalkanı, kehanet açığa çıkarma | Görev/ritüel kazanımı — **asla P2W mana çeşmesi değil** |

**Dönüşüm gerginliği (`GDD-DEFENSE-SYSTEMS.md` §5.2):** Wood→Stone anlık buton değil, kampanya teknolojisidir (Norman ahşap kale → taş keep gibi). Yüksek Stone duvar + sıfır Gold maaşı = teslim olay tetikleyicisi (Avrupa dosyası dersi — "Garrison Wage"). Favor kalıcı olarak Gold DPS'in yerine geçmez; sadece pivotları ve yumuşak zafer koşullarını **zamanlar**.

### 4.5 Altın döngüsü kaynak/gider tablosu

`GDD-WAVES-ECONOMY.md` §3 birleşimi:

| Kaynak yönü | Kanal |
|---|---|
| **Sources** | Başlangıç altını (harita script'i) · kill başı (düşman değerine göre ölçekli) · dalga temizleme bonusu · erken çağırma (early call) bonusu (azalan) · faiz (opsiyonel sert haritalarda, "kervan" destek kulesi ile) |
| **Sinks** | Kule yerleştirme/yükseltme · hero yetenek malzemesi (nadir) · barikat sarf malzemesi · keep tamiri (pahalı, caydırıcı) |

**Başlangıç altını felsefesi:** 2–3 temel kuleye yetecek kadar — tam tahtaya değil. Zorunlu erken karar = katılım (engagement) yaratır.

### 4.6 Erken çağırma (early call) — Kingdom Rush DNA'sı

Buton: bir sonraki dalgayı erken çağır. Ödül: +%X dalga altını + mükemmel temizlemede küçük mühür (seal) şansı. Risk: kötü zamanlanırsa dalgalar üst üste biner. `GDD-WAVES-ECONOMY.md` §4 bunu **"olmalı" (must have)** olarak işaretler; Babylon Act 0'da M3'te güvenlik duraklatmasıyla (safety pause) öğretilir (§8.1).

### 4.7 Yıldız (star) ekonomisi

| Yıldız | Koşul |
|---|---|
| ★1 | Temizleme (clear) |
| ★2 | Keep HP ≥ %70 VEYA iç surun (inner ward) arkasına sızma yok |
| ★3 | Keep HP ≥ %90 + hız temizleme VEYA satış-istismarı (sell-abuse) yok |

Heroic/Iron ayrı madalyalar → Codex mühürlerine dönüşür. Bu, harita ölçeğini gün/hafta ölçeğine bağlayan birincil köprüdür: yıldızlar Codex'i besler, Codex sezonu besler (§9).

### 4.8 Harita ölçeği — süre hedefleri

`GDD-WAVES-ECONOMY.md` §1 ve `GDD-BABYLON-ACT0.md` §1 birleşimi:

| Mod | Süre | Dalga sayısı |
|---|---|---|
| Tutorial | 3–5 dk | 5–8 |
| Standart kampanya | 8–14 dk | 12–20 |
| Boss kuşatması | 12–18 dk | 15 + fazlar |
| Günlük challenge | 6–10 dk | 10–14 |
| Endless / Survival | sınırsız | tırmanan |

### 4.9 Gün ölçeği — günlük döngü

`GDD-RETENTION-VIRAL.md` §9'daki döngü, bu ürüne göre uyarlanmıştır:

```
AÇILIŞ
  → Kümülatif günlük ödülü + streak durumu (≤10s)
  → Ana ekran: medeniyet anıtı ilerlemesi + 3 günlük görev + etkinlik banner'ı
ÇEKİRDEK (birini seç)
  A. Kampanya/bölge haritası (2–3 deneme)     [harita ilerlemesi]
  B. Etkinlik görevi (1–2 koşu)                [live ops]
  C. Kule ustalığı drill / endless eki          [meta / skill]
İNŞA (buildcraft)
  → 1 yükseltmeye hurda/altın harca VEYA emanet (relic) tak
  → Koleksiyon %'sini kontrol et (kule/hero/medeniyet)
SOSYAL (opsiyonel, ≤3 dk)
  → Lonca bağışı / yardım / kuşatma dokunuşu
KAPANIŞ
  → "Yarın: günlük sandık + etkinlik görevi 2/3"
  → Opsiyonel: sandık/etkinlik bitişi için sessiz push zamanla
```

**Duygu yayı (emotion arc):** Kimlik (benim medeniyetim) → Yeterlilik (choke'u tuttum) → Büyüme (yükseltme/koleksiyon) → Ait olma (lonca) → Beklenti (yarın).

### 4.10 Hafta ölçeği — haftalık döngü

| Gün | Oyuncuya görünen odak | Arka plan / live-ops |
|---|---|---|
| Pazartesi | Haftalık görev tahtası yenilenir; soft para artışı | Denge yaması notu |
| Salı–Çarşamba | Kampanya itme + ustalık grind'i | Discord'da UGC spotlight |
| Perşembe | Hafta ortası mini-modifikatör (sis/gece baskını) | A/B zorluk testi |
| Cuma | Etkinlik mağazası vitrini; kozmetik önizleme | Yaratıcı kit çıkışı |
| Cumartesi | **Lonca kuşatma hafta sonu** açılır | Topluluk raid sesi |
| Pazar | Kuşatma ödülleri; haftalık sandık; sıradaki medeniyet önizlemesi | Analitik retrospektif |

Haftalık oyuncu yükümlülükleri: (1) haftalık tahtayı tamamla (4/6), (2) bir lonca katkısı, (3) bir etkinlik dokunuşu, (4) opsiyonel: bir build/replay paylaş.

### 4.11 Sezon ölçeği — 6–7 haftalık ritim

Sezon yapısı (`GDD-LIVEOPS-FTUE-META.md` §3.2, ayrıntı §14):

1. Lansman fragmanı / hub ele geçirme — öne çıkan keep tam ekran.
2. Track A (ücretsiz): mühürler, Codex kartları, 1 kule kozmetiği.
3. Track B (pass): hero kozmetiği, keep "restorasyon" katmanı, portre, Arcade-özel mit-etiketli birim.
4. Sezon görev zinciri (8–12 düğüm) — belgelenmiş kampanyalara dayalı hafif anlatı; her düğümde doğruluk etiketi.
5. Final: sezon medeniyetine (veya rakip medeniyete) temalı co-op boss.

### 4.12 Yıl ölçeği — ufuk

§0.2'deki 3-yıllık vizyonun döngü karşılığı: **Yıl 1** = Babylon + 3–4 medeniyet derin + 8 sezon; **Yıl 2** = kalan medeniyetler sezonlarla drip + async lonca olgunlaşması; **Yıl 3+** = tam roster, endless/roguelike, community challenge editor.

### 4.13 Döngüler arası anti-pattern kontrol listesi

1. Günlük görev sistemi kampanya haritasının kendi ritmini **bastırmasın** — görev her zaman "oynadığın haritayı say", ayrı bir grind haritası zorunlu kılma.
2. Erken çağırma riski gizli RNG olmasın — oyuncu overlap riskini görsün (§7 juice kuralı ile paralel: sinyal gizli olmaz).
3. Sezon geçişleri kampanya ilerlemesini sıfırlamaz; sadece kozmetik/pass track'i döner.
4. Haftalık kuşatma "ödev simülatörü" olacak kadar uzatılmaz (tek koşu 4–8 dk, `GDD-SOCIAL-GUILD.md` §2.4).
5. Saniye ölçeğindeki juice, dalga ölçeğindeki okunabilirliği asla yemez (bkz. §5.7).

---

## 5. Kamera ve sunum / juice / erişilebilirlik

*(Kaynak: `GDD-CAMERA-JUICE.md` — tam dosya)*

### 5.1 Karar (en başta okunması gereken)

| Karar | Seçim |
|---|---|
| **BİRİNCİL (primary) savaş kamerası** | **Kilitli yüksek-oblik ¾ görünüm (Locked High-Oblique Keep View — LHOK)** (Kingdom Rush / Clash-of-Clans ailesi): harita (veya keep "yüzü") başına sabit yaw + pitch, dalga sırasında **sadece pan + zoom** |
| **Birincil DEĞİL** | PvZ yan/üst şerit · serbest orbit 3D (Defense Grid) · her zaman açık serbest tilt/yaw |
| **Opsiyonel modlar** | (1) Keep tanıtımı/zafer/yenilgi için sinematik orbit · (2) Yoğun pathing için stratejik üstten bakış overlay'i · (3) Sızma/hero/boss'a odak-anlık (focus-snap) · (4) Erişilebilirlik statik çerçeve |
| **Juice yasası** | Geri bildirim, **Reduced Motion / Shake %0** açıkken hâlâ teşhis edilebilir olmalı; kaldırma, değiştir |
| **Sarsıntı (shake) bütçesi** | Kısa pozisyonel darbeler (~0.12–0.25s); nadir boss vuruşları ≤0.35s; **sürekli rotasyonel sarsıntı yok** |

**Tek satır gerekçe:** Anıtsal keep'ler **görünür yükseklik** ve **okunabilir güzergâh** ister. Kilitli yüksek-oblik, keep'i bir kahraman silüeti olarak gösterirken oyuncuyu kamerayla savaşmaya zorlamaz — bu, mobilde serbest-orbit 3D'nin ve dikey mimari için yan-görünüm şeritlerin başarısızlık modudur.

### 5.2 "Başarılı TD kameraları" ne optimize eder

Üç iş, spectacle'dan önemlidir (`GDD-CAMERA-JUICE.md` §1):

1. **Dalga öncesi güzergâh netliği** — oyuncu "nereden, nereye, choke nerede" sorusunu cevaplayabilir.
2. **Teşhis edilebilir başarısızlık** — bir şey sızdığında tahta (cutscene değil) **nedenini** gösterir.
3. **Baskı altında araç okunabilirliği** — VFX yoğunlaştığında kule rolleri, menzilleri ve öncelik hedefleri okunabilir kalır.

Spectacle opsiyoneldir; bu üçü değildir. Hibrit aksiyon-defans oyunları (Sanctum, Dungeon Defenders vb.) kamera oyuncuya "ihanet ettiğinde" rutin olarak başarısız olur — sızmalar plan hatası gibi değil, ekran dışı gibi hissettirir.

### 5.3 Karşılaştırma matrisi (kamera aileleri)

| Aile | Örnek | Güç | Zayıflık | Bu ürün için uygunluk |
|---|---|---|---|---|
| **Klasik 2D yan/üst** | Plants vs. Zombies | En yüksek şerit okunabilirliği; ucuz üretim | **Anıtsal mimariyi kahraman olmaktan çıkarır**; dikeylik 2D'ye çöker | Zayıf birincil; tutorial/yan-mod adayı |
| **İzometrik/sabit üstten ¾** | Kingdom Rush ailesi | Choke/build/akış tek bakışta okunur; sabit build noktaları; kule/uçurum/keep silüeti için yeterli yükseklik | Kule kütlesi kötü yerleştirilirse occlusion | **En iyi birincil** |
| **Serbest-orbit 3D** | Defense Grid | Anıtsal hacim için en iyi ham vitrin; sistem izleme çoklu açıdan | **Kamera bir skill vergisi**; mobilde yerleştirme parmaklarıyla savaşır; occlusion; vestibüler risk | Sinematik/pazarlama için mükemmel, birincil savaş kamerası için riskli |
| **Modern mobil tilt (kilitli)** | CoC/Boom Beach tarzı yüksek-oblik + pan-zoom | Anıtsal binalar zaten bu dilde satılır; tanıdık mobil jest grameri | Savaş sırasında serbest rotasyon DG'nin okunabilirlik vergisini tekrarlar | **Birincilin uygulama lezzeti**, ayrı bir tür değil |

### 5.4 Puanlama rubriği (1–5, yüksek = bu ürün için daha iyi)

| Kriter | PvZ yan/üst | KR sabit ¾ | DG serbest-orbit | Mobil tilt (kilitli) | Mobil serbest-rotasyon |
|---|---:|---:|---:|---:|---:|
| Keep kahraman silüeti | 2 | **5** | 5 | **5** | 5 |
| Yol/choke okunabilirliği | **5** | **5** | 3 | 4 | 2 |
| Teşhis edilebilir sızmalar | **5** | **5** | 3 | 4 | 2 |
| Mobil tek-el/başparmak UX | **5** | **5** | 2 | **5** | 2 |
| Sanat maliyeti / tek görüş konisi | **5** | **5** | 2 | 4 | 2 |
| Dikey kuşatma fantazisi (teraslar, duvarlar, alaylar) | 2 | **4** | **5** | **4** | 4 |
| Erişilebilirlik/vestibüler güvenlik | **5** | **5** | 2 | 4 | 2 |
| Pazarlama "vay" görüntüleri | 3 | 4 | **5** | **5** | 5 |
| **Ağırlıklı uyum (keep-önce TD)** | 3.4 | **4.8** | 3.1 | **4.6** | 2.8 |

**Kazanan:** Kilitli yüksek-oblik ¾ (KR kamera sözleşmesi + CoC/mobil tilt sunumu). DG-tarzı orbit ve serbest rotasyon mobilde ve okunabilirlikte kaybeder. PvZ keep kimliğinde kaybeder.

### 5.5 Birincil kamera özellikleri (LHOK)

**Sıkı kurallar (savaş sırasında):**

1. Dalga aktifken (veya oyuncu "Defend" durumundayken) **sabit yaw ve pitch**.
2. **İzin verilen:** pan, pinch/zoom (sınırlı), boss belirişinde opsiyonel hafif **senaryolu** yakınlaşma (≤%8 FOV değişimi, ≤0.4s, ease in/out).
3. **Savaşta yasak:** serbest orbit, roll, oyuncu-güdümlü pitch, birinci şahıs, heroların varsayılan olarak takip-kamerası.
4. **Çerçeveleme:** keep kütlesi sabit bir kahraman konumunda (genelde üst-üçte-bir veya harita "tacı"); girişler harita kenarından okunur; çıkış/çekirdek net okunur.
5. **Varsayılan zoom:** kritik yolun tamamı telefonda dikey *veya* yatay görünür (birincil oryantasyonu erken seç, haritaları ona göre tasarla). Zoom-in bir kuleyi incelemek içindir, kör savaşmak için değil.

### 5.6 Medeniyet başına "yüz" (canonical face) — sanat yönetimi

Her anıtsal keep, tarihi satan ve yaklaşımları okunabilir bırakan **kanonik açıyla** gönderilir:

| Keep | Çerçeveleme notu |
|---|---|
| **Akropolis (Atina)** | Tepe kütlesi + Propylaia yaklaşım ekseni; Parthenon taç olarak, yol ortasını bloklayan bir duvar değil |
| **Himeji** | Tenshu yığını basamaklı beyaz silüet olarak okunur; *ishigaki* ve kapılar okunabilir choke halkaları olarak |
| **Templo Mayor** | İkiz merdivenli piramit dikey doruk noktası; alay/geçit yaklaşımları ön planda kalır |
| **Weiyang** | Eksenel saray derinliği; kapılar ve duvarlar katmanlı dikdörtgenler olarak, düz bir damga değil |
| **Etemenanki** | Terasli ziggurat katmanları "ilerleme/tehdit irtifası"nın dikey göstergesi olarak |

Haritalar **2–3 kilitli yüz** sunabilir (kozmetik olarak veya alternatif düzen için açılan), ama dalga sırasında asla serbest yaw yoktur.

### 5.7 Uygulama notları (motor-agnostik)

- **Ortografik** veya çok hafif perspektif tercih edilir, böylece kule menzilleri yalana dönüşecek şekilde kısalmaz (foreshorten).
- Karakterler/kuleler LHOK konisi için tasarlanır; DG'nin dersi: **uzun, ayırt edici silüetler**.
- Keep mesh'i, imleç altındaki veya sızan düşmanların altındaki yol hücrelerini bloklarsa **görünür-kılma/hayaletleştirme (ghosting)** kullanır (geçici opaklık düşüşü, kalıcı cam-keep değil).
- Yol şeridi/ısı izi (path ribbon/heat trail) opsiyonel geçiş (toggle); FTUE'de varsayılan açık.

### 5.8 Opsiyonel modlar (ikincil — asla birincili değiştirmez)

| Mod | Ne zaman | Kontroller | Yapmaması gereken |
|---|---|---|---|
| **A. Sinematik Orbit** | Keep unlock, görev başlangıç tablosu, zafer, yenilgi, mağaza fragmanı çekimi | Senaryolu spline veya sınırlı orbit; oyuncu her zaman atlayabilir | Aktif dalga AI kararları sırasında çalışmaz |
| **B. Stratejik Üstten Bakış Overlay'i** | Yoğun geç haritalar, challenge modifikatörleri, "yolu incele" butonu | Anlık veya 0.2s geçiş ile daha dik pitch (~70–85°); aynı yaw; pan/zoom | Keep kimliğini sonsuza dek saklamaz; varsayılan olmaz |
| **C. Odak-Anlık (Focus-Snap)** | Sızma/hero/boss/alarm ping'inde çift dokunma/hotkey | Öznenin üzerine yumuşak pan+zoom, ≤1.5s tut veya oyuncu pan yapana dek | Sürükleyerek yerleştirme sırasında kontrolü çalmaz |
| **D. Erişilebilirlik Statik Çerçeve** | Ayarlar: Reduce Motion / Camera Comfort | Senaryolu yakınlaşmaları, focus-snap hareketini devre dışı bırakır; sinematikler otomatik statik kareye atlar | İçeriği hareketin arkasına yumuşak-kilitlemez |

### 5.9 UX okunabilirlik kontrol listesi

Tasarımcılar LHOK varsayılan zoom'da şu sorulara **evet** diyebiliyorsa haritayı gönder:

1. VFX kapalıyken dalga 1'den önce **her giriş → çıkışı** takip edebiliyor musun?
2. **Build node'ları** dekorasyondan görsel olarak ayırt edilebiliyor mu?
3. Keep, yolun son %20'sini kapatmadan bahis (stake) olarak okunuyor mu?
4. Bestiary açmadan **hızlı/zırhlı/uçan/boss** önceliğini söyleyebiliyor musun?
5. Bir sızma olduğunda, hata noktası döndürmeden **ekranda** mı?
6. Kule **menzil halkaları** projeksiyon altında dürüst kalıyor mu?
7. Zirve juice'ta (bkz. §5.10) **düşman hattını** hâlâ görebiliyor musun?

(5) veya (7) başarısızsa VFX yoğunluğunu kes — bandaj olarak serbest orbit ekleme.

### 5.10 Juice bütçesi — tahtayı mahvetmeden vuruş geri bildirimi

**Geri bildirim yığını (yerelden → küresele tercih et):**

1. **Yerel aktör juice'ı** — hedefte darbe flaşı, kulede geri tepme, ölüm dissolve/ragdoll-lite, kayan sayı (açılıp kapanabilir).
2. **Ses** — katmanlı SFX + ducking; Reduced Motion oyuncuları için kritik.
3. **UI tık** — kombo/ödül/can değişimi mikro-animasyonla.
4. **Ekran-uzayı vurguları** — kısa vinyet, kenar flaşı, sadece a11y izin veriyorsa kromatik.
5. **Kamera** — son çare; "oturduğunu" hâlâ hissettiren en küçük genlik.

5. adım gerekiyorsa 1–4 yeterince tasarlanmamıştır. Önce onları düzelt.

### 5.11 Ekran sarsıntısı sınırları

| Olay | Maks. süre | Hareket türü | Not |
|---|---|---|---|
| Kule vuruşu/küçük öldürme | 0.12–0.18s | Pozisyonel, yüksek frekans, yay sönümü (spring decay) | Olay başına; yığılmaya yumuşak-sınır |
| Elit/patlama | 0.18–0.25s | Pozisyonel + opsiyonel minik pitch | Patlamadan uzaklıkla azalır |
| Keep hasarı (can kaybı) | 0.20–0.30s | Pozisyonel | Kırmızı kenar + ses vuruşuyla eşleştir |
| Boss vuruşu/keep gediği | ≤0.35s | Pozisyonel; **roll'dan kaçın** | Olay başına bir kez; asla döngüsel değil |
| Sürekli deprem tehlikesi | Kamera değil, tercihen **dünya prop'ları sarsılır** | — | Veya çok düşük genlik ≤%30 |

**Yığılma kuralı:** Eşzamanlı sarsıntılar toplanmaz, max (veya yumuşak karışım) kullanır. Kamera ofsetini sınırla (örn. ekran yüksekliğinin ≤%1.5'i).

**Varsayılan olarak yasak:** sürekli rotasyonel sarsıntı, uzun (>0.5s) sönümlenmemiş sarsıntı, tam ekran beyaz strobe, menüler açıkken duraklama sırasında devam eden sarsıntı.

### 5.12 Hitstop / zaman juice'ı

- Ağır vuruşlarda mikro hitstop (1–3 kare) kamerayı hareket ettirmeden ağırlık satabilir.
- Boss ölümünde dalga slow-mo: ≤0.6s, atlanabilir, Reduce Motion altında devre dışı (donmuş kare + fanfar ile değiştirilir).

### 5.13 VFX vs okunabilirlik

- Parçacık bütçeleri kaliteye + "Efekt Yoğunluğu" kaydırıcısına göre ölçeklenir.
- Işınlar/patlamalar **yol merkez hattını** 0.2 saniyeden fazla asla boyamaz.
- Durum efektleri (status FX) sadece renk tonuyla değil, farklı şekillerle ayırt edilir (buz parçaları vs ateş titreşimi vs zehir baloncukları).

### 5.14 Erişilebilirlik (pazarlık konusu olmayan ayarlar)

| Ayar | Varsayılan | Davranış |
|---|---|---|
| **Ekran sarsıntısı yoğunluğu** | %100 | Tüm kamera sarsıntılarında küresel çarpan %0–100 |
| **Hareketi azalt (reduce motion)** | Kapalı (mevcutsa OS Prefers-Reduced-Motion'a uyar) | Orbit sinematiklerin otomatik oynatmasını, focus-snap hareketini, FOV vuruşlarını devre dışı bırakır; kalan hareketi ~%20 söndürür veya HUD flaşlarıyla değiştirir |
| **Flaş/fotosensitivite** | Tam | Kapalı = strobe yok; ≤3 Hz eşdeğeri, düşük kontrastlı düz renk atımlarıyla değiştir |
| **Efekt yoğunluğu** | Yüksek | Parçacıkları/distorsiyonu ölçekler |
| **Hasar sayıları** | Açık | Kapalı veya "sadece büyük" |
| **Kamera asistanı** | Açık | Yol şeritleri, sızma chevron'ları, occlusion'da keep hayaletleştirme |
| **Renk körlüğü paletleri** | Kapalı | Deuteranopi/Protanopi/Tritanopi UI + takım renkleri |
| **UI ölçeği/metin** | %100 | Özellikle mobil + Steam Deck sınıfı cihazlar için |

**İlke:** *Kaldırma, değiştir.* Sarsıntı %0'da bile HUD kenar vuruşu + ses var. Reduce Motion'da bile keep kimliği statik tablolar aracılığıyla var.

**Test kapısı:** Sarsıntı %0, Efektler Düşük, Reduce Motion Açık ile geç bir haritayı oyna. Strateji okunmazsa tasarım başarısız olmuştur — oyuncu değil.

### 5.15 Platform notları

| Platform | LHOK yönergesi |
|---|---|
| **Mobil** | Dikey *veya* yatay birincil; büyük dokunma hedefleri; tek parmakla pan, pinch zoom; savaşta orbit jesti yok |
| **Tablet/foldable** | Aynı kamera; daha geniş varsayılan çerçeveleme |
| **PC/konsol** | Çubuk/mouse pan; zoom için tetikler; butonla opsiyonel Stratejik Overlay |
| **Mağaza sayfası/fragmanlar** | Sinematik Orbit'i serbestçe kullan; alıcıların oynanış kamerasını bilmesi için LHOK'a net bir kesme göster |

### 5.16 Anti-pattern'ler (asla göndermeyin)

1. Keep'in arkasını görmenin *tek* yolu olarak serbest orbit.
2. Kampanya varsayılanı olarak PvZ yan-görünüm (anıt duvar kâğıdına döner).
3. Kritik bilgiyi sadece sarsıntı veya flaş üzerinden ileten juice.
4. İkinci girişi ekran dışında bırakacak kadar sıkı varsayılan zoom.
5. Yol okumasını blokluyorsa keep mesh opaklığının her zaman %100 olması.
6. 40 kuleli bir tahtada her mermide ekran sarsıntısı (deniz tutması simülatörü).
7. İlk temizlemede atlanamayan "sinematik kamera."

**Kaynak notu:** Karşılaştırmalar tasarım öncülleridir, kopyalanacak IP değil. Anıtsal keep çerçevelemesi kültürel mirasa saygılı kalmalı (silüet saygısı ≠ varsayılan sunumda vandalizm fantazisi).

---

## 6. Defans mekanikleri (5 fiil, kuleler, kışla, tuzak, duvar, kahraman, kaynaklar)

*(Kaynak: `GDD-DEFENSE-SYSTEMS.md` — tam dosya)*

### 6.1 Tasarım kararı (bir ekranda)

Defans **beş menü değil, beş fiildir**:

| Fiil | Sistem | İş |
|---|---|---|
| **Shoot (Ateş et)** | Kuleler | Sabit veya pad pozisyonlarından şerit DPS'i / kontrolü |
| **Hold (Tut)** | Kışla / garnizon | Blokla, geciktir, emici ol; Shoot için zaman yarat |
| **Shape (Şekillendir)** | Duvarlar / kapılar / palisatlar | Yol uzunluğunu, choke'ları ve ateş koridorlarını zorla |
| **Punish (Cezalandır)** | Tuzaklar / hazardlar | Zorunlu karolarda (kapı, hendek, köprü) burst vergisi |
| **Pivot (Yönlendir)** | Kahramanlar | Oyuncu ajansı: sızmaları tıka, çıkış yap, Favor harca |

Kaynaklar, pad'ler, upgrade ağaçları ve civ skinleri bu beş fiili telefon ekranında okunabilir ve tarihi kale grameline sadık kılmak için var — yedi tam eşmerkezli halkayı UI süsü olarak çizmeden.

### 6.2 Çekirdek defans direkleri (gönderim kontrol listesi)

1. **Yol netliği** — oyuncu her zaman düşmanların nereden yürüdüğünü ve ateşin nerede yasal olduğunu bilir.
2. **Choke > halı (carpet)** — değer kapılarda, köprülerde, köşelerde yoğunlaşır, tekdüze serpiştirmede değil.
3. **Hold + Shoot döngüsü** — kışlalar bekleme süresi (dwell time) yaratır, kuleler bunu öldürmeye çevirir.
4. **Katmanlı HP, katmanlı karmaşa değil** — 7 tarihsel katman ≤4 oynanabilir stratada eritilir + yumuşak olaylar.
5. **Paylaşılan arketipler, civ skinleri** — 6–8 çekirdek kule işi; benzersizlik 1–3 civ kulesi + hero/tuzak kiti ile.
6. **Hibrit yerleştirme** — netlik için pad'ler; sadece tarih gerektirdiğinde serbest yerleştirme (kazık, hendek, saha kampı).
7. **Rol taşıyan dört kaynak** — Gold (operasyon), Wood (saha/hızlı), Stone (kalıcı HP), Favor (ritüel/hero/mit).
8. **Sinerji dilbilgisi** — etiketler (Pierce, Splash, Slow, Block, Armor-Break, Anti-Climb, Naval, Ideology) build'leri birleştirir.
9. **Boss kapısı olarak kapı** — her haritanın duygusal vuruşu isimsiz bir duvar segmenti değil, adlandırılmış bir kapı/tuzak-avlusu.
10. **"Keep HP = 0" ötesinde yenilgi modları** — ideoloji/moral (şapel, tapınak, taht), tedarik kesintisi, garnizon maaş çöküşü.

### 6.3 Defans nesne taksonomisi

#### 6.3.1 Kuleler (Shoot)

Hareketsiz (veya nadiren taşınabilir) yayıcılar; menzil, hedef kuralı ve upgrade yolu vardır. Yerleştirme **pad'lerde** (duvar/mural noktaları/bailey köşeleri) tercih edilir. Hedefleme öncelikleri: İlk / Son / Güçlü / Zayıf / Uçan / Kuşatma. Silüet %50 zoom'da okunmalı; civ skini yeni bir hitbox anlamına gelmez.

**Anti-pattern'ler:** Sadece "+%10 hasar aurası, atış yok" olarak var olan kuleler; görünmez menzil; yedi kule katmanının salt istatistik klonu olması.

#### 6.3.2 Kışla / garnizon binaları (Hold)

**Blokerler** (askerler) üretir/barındırır — yol-blokla veya melee'de leşleme yapar.

| Rol | Not |
|---|---|
| Şerit blokeri | Klasik KR-tarzı kışla; canlanma zamanlayıcısı; zırh/HP kimliği |
| Duvar garnizonu | Perde segmentini işgal eder; zayıf yaylım ateşi VEYA bitişik kuleleri buff'lar |
| Çıkış yuvası (sortie nest) | Zamanlı bir sortie'yi açar (umadashi, Roma *eruptio*, şövalye karşı-hücumu) |

Choke'suz kışla zayıf hisseder; kışlasız choke'lar gevrek hisseder — **eşleştirin**. Şerit başına eşzamanlı canlı bloker sayısını sınırla ki Hold, Shoot'un yerine hiç geçmesin.

#### 6.3.3 Tuzaklar ve saha tehlikeleri (Punish)

Karo veya segment-bağlı efektler, genelde tek kullanımlık/yeniden dolan/sarf malzemesi:

| Aile | Tarihsel ipucu | Oynanış |
|---|---|---|
| Hendek/moat yavaşlatma | Evrensel dış katman | Kalıcı veya tamir edilebilir Slow + süvari vergisi |
| Kazık/dikenli alan | Viking sahil savunması; saha kampları | Anti-süvari; sapper'lar tarafından yakılabilir/kaldırılabilir |
| Kapı dikey vergisi | Öldürme delikleri, *ishi-otoshi*, portcullis aşamaları | Sadece kapı segmentinde burst |
| Tuzak avlusu | Masugata, Messene Arcadian Gate, barbican | Zorunlu 90° dönüş + bekleme + çok açılı ateş |
| Ateş/zift | Hoarding'ler, naphtha gelenekleri (civ-kilitli) | AoE + ahşapta dostane ateş riski |
| Köprü/geçit yıkımı | Yok edilebilir açıklık | Yol yeniden yazımı; pahalı |

Tuzaklar **serbest yerleştirilen halı mayınları değildir**. Varsayılan = **saha-tasarımlı** veya **işaretli karolarda sarf malzemesi**.

#### 6.3.4 Kahramanlar (Pivot)

Bilişsizim (kampanya) veya 1–2 (skirmish) oyuncu kontrollü birim, cooldown'lu — üçüncü bir build menüsü değil.

| Mod | Kullanım |
|---|---|
| Aktif mikro-yönetim | Sızmaya sürükle; choke'ta yetenek |
| Otomatik-cast + toplanma | Kazuel/FTUE |
| İdeoloji ult | Harita-çapında vuruş için Favor harca (ayin, sancak, alamet) |

Kahramanlar tam güçlendirilmiş bir çekirdek kuleyi sonsuza dek geride bırakmamalı; **benzersiz** olarak: Hold'u canlandırmalı, zırh kırmalı, ihanet şeritlerini açığa çıkarmalı veya dalga başına bir kez Favor harcayarak pivot yapmalı.

#### 6.3.5 Duvarlar, kapılar, listeler (Shape)

| Öge | Oynanabilir iş |
|---|---|
| Perde/rampart | Şerit HP'si; mural kule pad'leri; garnizon için yürüyüş yolu |
| Kapı/kapı evi | Çok aşamalı kilit (kapılar → portcullis → öldürme kutusu); boss kapısı |
| Listeler/öldürme sahası | Dış perde düştükten sonra iç ateşin altında açık alan |
| Hendek/moat | Slow + kuşatma-motoru park etme reddi |
| Keep/ideoloji çekirdeği | Son sığınak **veya** moral zafer totemi (tapınak/şapel/taht) |

**Tamir ekonomisi:** Stone + zaman; ahşap hoarding için Wood (yüksek buff, ateşe açık). Boş max-HP duvarlar, Garnizon Maaşı ödenmezse çöker (Avrupa araştırması dersi).

### 6.4 7 tarihsel katman → 4 oynanabilir strata (anti-karmaşa)

Yedi eşzamanlı etkileşimli yard göndermek okunmaz minimap, tutorial yorgunluğu ve "bir HP çubuğu daha" bıkkınlığı yaratır.

| Oynanabilir strata | Emdiği tarihsel katmanlar | Oyuncu fiilleri |
|---|---|---|
| **S1 Yaklaşım** | Dış kasaba, otlak, deniz/fiyort, sahil kazıkları, banliyö | Yavaşlat, açığa çıkar, opsiyonel yanabilir ekonomi dokusu |
| **S2 Eşik** | Kapı/barbican/masugata/Dipylon/kardinal kapılar | Tuzak avlusu, portcullis aşamaları, adlandırılmış choke boss'u |
| **S3 Kabuk** | Dış+iç perdeler, mural kuleler, yagura hattı, rampart | Ana pad halkası; duvar HP'si; yan ateş |
| **S4 Çekirdek** | Keep/honmaru+tenshu/saray/akropolis tapınakları/şapel-hol | Son HP **veya** ideoloji ölçer; elit garnizon |

| Yumuşak katman (olay/ölçer, tam yard değil) | Örnek |
|---|---|
| Ekonomi dokusu | Jōkamachi yanması; ekstramural fırınlar; mülteci yığılması |
| Tedarik göbek bağı | Long Walls koridoru; liman molaları; Deniz-Yolu iskelesi |
| İdeoloji/meşruiyet | Şapel, Parthenon, Gümüş Ağaç, Etemenanki, Mandate ayinleri |

**Kestirme kural:** Bir tarihsel katman **pathing**'i, **pad yasal setini** veya **kazan/kaybet ölçerini** değiştirmiyorsa, o yumuşaktır — süs VFX, opsiyonel hedef veya kampanya izi, yedinci bir build bölgesi değil.

#### 6.4.1 Civ istifi → strata hile sayfası

| Civ | Tarihsel 7 (kısaltılmış) | Oynanabilir odak |
|---|---|---|
| **Avrupa Kalesi** | hendek → kapı/barbican → dış perde → listeler → iç perde → keep → şapel/hol | S2 kapı evi yıldızı; S3 eşmerkezli çift perde; S4 keep **veya** kapı evi-keep; ideoloji yumuşak |
| **Japonya** | kasaba+moat → masugata → sannomaru → ninomaru labirenti → yagura → honmaru → tenshu | S2 masugata bulmacası; S3 spiral yol-vergisi; S4 tenshu-gun markası |
| **Yunanistan (Attika)** | Piraeus → Long Walls → şehir duvarı → kapılar → kentsel doku → Propylaea → tapınaklar | Yumuşak tedarik koridoru; S2 Dipylon; S4 kutsal çekirdek (moral) |
| **Viking** | deniz → sahil kazıkları → hendek → çim-ahşap rampart → kapılar → iç ızgara → hol/tapınak | S1 deniz+kazık serbest yerleştirme; S3 ahşap yangın riski; S4 hol |
| **Moğol (başkent savunması)** | otlak → banliyö → hangtu duvarı → kapılar → mahalle labirenti → saray sınırı → taht | S1 remount perdesi; hafif S3 (aşırı tahkim fantazisinden kaçın); S4 tuğla saray |
| **Çin** | (Çin dosyalarından wengcheng/çok-kapı grameri) | S2 tuzak-avlusu ustalığı; uzun kabuk; geç gunpowder coda |
| **Roma** | yürüyüş kampı → duvarlar → kapılar → principia grameri | Hızlı Wood castra; Stone upgrade; doktrin pad'leri |
| **Mısır** | Nil/yaklaşım → Buhen-sınıfı kaleler → kapılar → iç → tapınak ideolojisi | Nehir S1; mudbrick yumuşak HP; taş prestiji |
| **Persia** | geçit/yaklaşım → duvarlar → kapılar → saray → Apadana ideolojisi | Geniş öldürme sahaları; satrap takviyesi Hold olarak |
| **Maya–Mexica** | arazi/causeway → toprak işleri → kapılar → kutsal alan → tapınak-piramit | Causeway choke'ları; capture-öncelikli düşmanlar vs saf DPS |
| **Babylon** | processional → duvarlar → kapılar (İştar) → processional avlusu → ziggurat çekirdeği | Kapı markası; Favor-ağır ideoloji çekirdeği |

#### 6.4.2 UI karmaşa azaltma teknikleri

1. **Tek HP şeridi** Kabuk için (dış/iç perdeler ayrı ölçer değil, upgrade durumu veya gedik aşamasıdır).
2. **Çok aşamalı nesne olarak kapı** (3 kilit) üç bina yerine.
3. **Yol ısı overlay'i** (FTUE/foto modu) her bailey'i etiketlemek yerine.
4. **Gedik tiyatrosu:** S2'yi kaybetmek yolu otomatik olarak S3 listelerine çöker — ek widget'sız drama.
5. **Uzun-basmada civ sözlüğü** — "masugata" bir kez öğretilir; günlük oyun ikon + Slow/Turn etiketi görür.

### 6.5 Yerleştirme: pad'ler vs serbest-yerleştirme

| Mod | Nerede | Neden |
|---|---|---|
| **Pad'ler (birincil)** | Perde kuleleri, bailey köşeleri, kapı kanatları, keep çatısı | Mobil okunabilirlik; tarihsel mural noktaları; denge |
| **Segment inşası** | Duvarlar, hendekler, kapı upgrade'leri | Shape fiili; Stone/Wood harcar |
| **İşaretli tuzak karoları** | Kapı boğazı, köprü ucu, hendek kenarı, tuzak avlusu | Halı olmadan Punish |
| **Serbest yerleştirme (sınırlı)** | Kazıklar, dikenler, saha paveseleri, Moğol perdesi, geçici kamplar | Tarih + oyuncu ifadesi; sıkı sayım sınırları |

**Serbest-yerleştirme korumaları:** Sınır **sayıya** ve **kaynağa** göre, görünmez "yasak yeşil çamur"a göre değil. Yasak: **tek** yolu tamamen bloklamak (harita "kapatılabilir arka çıkış" işaretlemedikçe). Yasak: 12 kazık alanını üst üste yığıp stun-kilit yapmak; azalan getiri kullan. Civ geçişleri: Viking/Moğol daha özgür, Edwardian eşmerkezli daha pad-katı.

**Kingdom Rush dersi, tarihsel bükümle:** Pad'ler adaleti öğretir; tarihi kaleler **zorlanmış geometriyi** öğretir. Pad'leri rastgele çim noktalarında değil, tarihsel olarak kanıtlanmış mural ritimlerde (Beaumaris kule kadansı, yagura hatları) kullan.

### 6.6 Kaynaklar — Altın / Ahşap / Taş / Favor (detay)

§4.4'teki dört rolün ötesinde, gelir kanalları:

- Pasif dalga ödeneği (Gold).
- Tutulan pazarlar/limanlar/otlaklar (S1'deki harita binaları).
- Gedilmiş kuşatma motorlarından hurda (Wood/Metal → Gold).
- Ustabaşı ilerlemesi mini-kanalı (dalgalar arası Stone).
- Liturjik eylemler/emanetler/alametler (Favor).

**Civ ağırlıkları:** Mısır/Babylon/Yunanistan Favor-zengin ideoloji; Roma/Çin Gold+Stone idaresi; Viking Wood-öncelikli; Moğol Gold/Favor (yağma + gök) ödünç Stone motorlarıyla.

### 6.7 Upgrade ağaçları — paylaşılan yapı

```
Kademe 0  Temel              (inşa)
Kademe 1  Alım-satım         (iki daldan biri)
Kademe 2  Uzmanlaşma         (dal derinleşir)
Kademe 3  Ustalık / amblem   (civ-lezzetli doruk; salt +hasar değil)
```

- Kule başına **en fazla iki dal** (okunabilirlik).
- Dal adları **fiillerdir** (Pierce vs Volley; Yağ vs Taş; Disiplin vs Fanatik).
- Doruklar +% tablolarından çok **kuralları** değiştirir (sekme, zırh-kırma, çift-hedef, kapı-aşama atlama).
- Küresel teknoloji (kampanya) kademeleri açar; misyon içi gold/wood/stone rütbe satın alır.

**Kışla ağacı:** Yol A: **İnatçı** (HP, zırh, canlanma). Yol B: **Keskin** (hasar, özel anti-tip).
**Kapı/duvar ağacı:** Kapı: ekstra kilit → öldürme-deliği tuzağı → kaynar/kum/taş dikey (civ-doğru; varsayılan kaynar yağ FOLK'u işaretlenmedikçe yok). Duvar: yürüyüş yolu → hoarding (Wood, yangın riski) → talus/anti-mayın (Stone) → top delikleri (sadece geç coda civ'leri).
**Hero ağacı (meta, hafif):** Yetenek sayfaları: Pasif şerit auraası · Aktif açık-kapatıcı · Favor ult'u. Lansman için 20-düğüm ağlardan kaçın.
**Çağ/kronoloji kilitleri:** Upgrade erişilebilirliği civ kronoloji zirvelerini takip eder (taş çağı florescence, teppō çağı, gunpowder alacakaranlığı) — ağaç jenerik RPG seviyeleri değil **tarih öğretir**.

### 6.8 Çekirdek kule arketipleri (civ'ler arası paylaşılan)

Paylaşılan **işler**; civ'ler sanat, SFX, cephane fiction'ı ve küçük pasif ayarlar sağlar.

| ID | Arketip | Birincil etiketler | Rol |
|---|---|---|---|
| **T1 Okçu/mızrak** | Yay, sapan, tatar yayı skini | Pierce/Single | Ucuz güvenilir DPS |
| **T2 Mızraklı yuva/nöbet** | Kısa menzilli savunma | Anti-Cav, zayıf Splash | Erken Hold desteği/duvar pad'i |
| **T3 Kışla** | (bina; defans parçası olarak listelenir) | Block | Anvil |
| **T4 Taş fırlatma/hafif topçu** | Sapan-değneği, mangonel-lite | Splash | Yığın temizleme |
| **T5 Ağır motor** | Balista, mancınık, traksiyon | Anti-Siege/Single büyük | Elit & motor imhası; yavaş atış hızı |
| **T6 Destek/sancak** | Sancak, boru, sinyal | Aura (küçük), Reveal | Maaş/moral; görüş |
| **T7 Ateş/özel cephane** | Zift, naphtha, ateş oku | Burn | Bölge reddi; harita-riski |
| **T8 Kutsal/ideoloji** | Türbe, şapel-silahı, stel | Ideology, Favor emici | Yumuşak kalkanlar, korku-karşıtı; düşük ham DPS |

**Lansman yükü:** T1–T5 zorunlu; T6–T8 kampanya action'larıyla açılır, FTUE temiz kalsın.

**Dal örnekleri (paylaşılan):** T1: Volley (atış hızı) vs Bodkin (zırh-kırma). T4: Geniş Splash vs Stun çakıl (kısa Root). T5: Cıvata (Pierce hattı) vs Ezme (kuşatma bonusu). T7: Yer ateşi vs Yapışkan zift (Slow+Burn). T8: Muhafız (Core'u kalkanla) vs Gazap (Favor nükü, uzun CD).

### 6.9 Civ-özel kuleler (medeniyet başına 1–3)

Benzersizler **kararları değiştirmeli**, sadece T1'i yeniden adlandırmamalı.

| Civ | Benzersiz kule fikri | Kanca |
|---|---|---|
| **Babylon** | İştar Kapısı aslan-atışı; ziggurat feneri (Favor görüşü) | Processional öldürme kutusu + ideoloji |
| **Roma** | Scorpio bataryası; *testudo* tatbikat sahası (mobil-ish Hold buff'ı) | Doktrin + motor bilimi |
| **Persia** | Sparabara perdesi kulesi; satrap rölesi (takviye feneri) | Yumuşak Block + imparatorluk çağrısı |
| **Mısır** | Nil taşkın kapağı (yol Slow); tapınak piloni nişancısı | Nehir kontrolü + kutsal eksen ateşi |
| **Çin** | Wengcheng gözetleme; traksiyon mancınığı; opsiyonel erken gunpowder coda | Tuzak-avlusu + motor derinliği |
| **Maya** | Atlatl galerisi; orman gizlisi (Reveal/pusu pad'i) | Çarpışma tempo |
| **Mexica** | Esir-öncelikli türbe kulesi (elitlerde öldürücü-olmayan bonus); dikey tapınak okçusu | Yakalama ekonomisi vs saf öldürme |
| **Viking** | Gemi-zinciri/liman kirişi; kazık-avlusu serbest-yerleştirme merkezi | Deniz S1 kimliği |
| **Yunanistan** | Long-Wall bataryası (koridor çift-ateş); gastraphetes pad'i | Tedarik koridoru + tech yay |
| **Japonya** | *ishi-otoshi*'li yagura; geç teppō yaylım yagurası | Anti-tırmanma + silah çağı kilidi |
| **Moğol** | Mobil perde vagonu (taşınabilir pad-lite); ele geçirilmiş Çin motor sahası | Saldırgan-savunmacı ikiliği |
| **Avrupa Kalesi** | Yan mural kule (çapraz ateş zorunluluğu); hoarding modülü; springald | Temellendirilmiş eşmerkezli çapraz ateş fantazisi |

**Bütçe:** Lansman **1 benzersiz + 1 imza tuzak/kapı modülü** per civ; ikinci benzersiz live ops'ta.

### 6.10 Sinerji dilbilgisi (etiket sistemi)

**Çekirdek etiketler:** `Pierce` `Splash` `Single` `Slow` `Root` `Block` `Armor-Break` `Anti-Shield` `Anti-Climb` `Anti-Cav` `Anti-Siege` `Flying` `Naval` `Burn` `Reveal` `Ideology` `Wage`

| Kombo | Parçalar | Ödül |
|---|---|---|
| **Örs & Çekiç** | Kışla Block + Splash kule | Klasik |
| **Öldürme koridoru** | Slow hendek + Pierce hattı + kapı tuzağı | Choke ustalığı |
| **Konserve açma** | Armor-Break hero/tuzak + Single nişancı | Elit silme |
| **Ateş disiplini** | Burn cephanesi + taş avlu (yanmaz) | Güvenli yanma bölgesi |
| **İdeoloji desteği** | Çekirdekte Favor kalkanı + Wage-ödenmiş garnizon | Boss dalgasını hayatta kal |
| **Yol vergisi** | Labirent/liste + uzun menzilli mural pad'ler | Japonya/Avrupa uzmanlığı |
| **Deniz tapası** | Naval etiketli kuleler + zincir/liman | Yunanistan/Viking/Mısır haritaları |

**Anti-sinerji (dürüstlük):** Ahşap Viking rampartında tüm-Burn → kendine zarar. Tek süper-zırhlı boss'a saf Splash → başarısızlık. Gold maaşı olmadan Favor yığma → güzel tapınaklar, boş duvarlar. Yolu hiç görmeyen pad'lerin arkasına serbest-yerleştirme kazıkları → boşa Wood.

**Aura bütçesi:** Şerit sektörü başında en fazla **bir** güçlü aura. Harita-çapında bulanıklık yerine kısa-menzilli sancakları (hero, standart kule) tercih et.

### 6.11 Choke tasarımı

| Tür | Örnek | Tasarım notu |
|---|---|---|
| **Kapı boğazı** | Barbican, masugata, Dipylon | En iyi öğretici choke; çok aşamalı |
| **Köprü/geçit** | Nehir haritaları, Long Walls mantığı | Yok edilebilir; yüksek drama |
| **Geçit/paleo-choke** | Thermopylae-tarzı | Arazi, duvar değil; ihanet yan-yol riski |
| **Spiral vergisi** | Japon bailey bağlantıları | Ekstra HP ölçeri olmadan uzunluk |
| **Liste öldürme sahası** | Dış gedikten sonra | Geçici açık, iç ateş altında |
| **Liman ağzı** | Zincir, molalar, kazıklar | Deniz + kara hibrit |

**İyi choke kuralları:** (1) Bir dalga önceden habercili (keşif tozu, davul, boru). (2) Yan dürüstlüğü — bir ihanet yolu varsa keşif/Favor açığa çıkarma onu kapatabilir/geciktirebilir. (3) Pad sahipliği — choke kanatları en iyi pad'leri alır; çim pad'leri daha zayıftır. (4) Sektör başına bir birincil choke — ikincil opsiyonel sızmadır, eşit ikiz değil (mobilde bölünmüş-dikkat cehennemini önler). (5) Boss kapısı kadansı — orta-misyon ve final adlandırılmış kapılar kullanır, isimsiz duvar segmentleri değil.

**Choke skorlaması (tasarım QA):** Bir choke "sağlıklı"dır eğer orta-skilli bir oyuncu Örs&Çekiç ile temizleyebiliyorsa, ve sadece-splash veya sadece-kışla build'i görünür şekilde zorlanıyorsa.

### 6.12 Misyon föyü şablonu (level tasarımcıları için)

```
Harita:
Civ skini:
Kullanılan oynanabilir strata: S1 / S2 / S3 / S4
Yumuşak katmanlar: (ekonomi / tedarik / ideoloji)
Birincil choke:
İhanet / sızma yolu: var-yok + açığa çıkarma yöntemi
Strata başına pad sayısı:
Serbest-yerleştirme sınırları:
Başlangıç kaynakları G/W/S/F:
Zorunlu tutorial kombosu:
Boss kapısı adı:
Yenilgi koşulları: Çekirdek HP / İdeoloji / Tedarik zamanlayıcısı / Maaş çöküşü
```

### 6.13 Riskler ve non-goal'lar (defans sistemleri)

| Risk | Azaltma |
|---|---|
| 11 civ × 12 kule = üretim intiharı | Paylaşılan T1–T8 mesh; lansmanda 1 benzersiz |
| 7 yardlık UI | 4 strata sıkıştırma |
| Mit lazerleri Favor DPS'i olarak | Favor = pivot/kalkan/açığa çıkarma; fantazi skinleri için FOLK geçişi |
| Her yerde kaynar yağ | Civ-doğru dikey cephane; FOLK işaretle |
| Küçük ekranlarda serbest-yerleştirme kaosu | Sınırlar + işaretli karolar |
| KR-klonu suçlaması | Tarihsel choke grameri, dört kaynak, ideoloji yenilgisi, kronoloji-kilitli ağaçlar |

**v1 için non-goal'lar:** tam base-builder simülasyonu; her haritada oyuncunun çizdiği keyfi labirentler; ayrı tasarım geçişi olmadan PvP kule aynası.

---

## 7. Saldırı / dalga / düşman / boss / ekonomi

*(Kaynak: `GDD-ATTACK-WAVES.md` — tam dosya, `GDD-WAVES-ECONOMY.md` çapraz referans)*

### 7.1 Tasarım tezi

Klasik TD, saldırganı **senaryolu bir musluk** olarak ele alır. Bu oyun saldırganı bir **doktrin** olarak ele alır.

| Eksen | Klasik mobil TD | Historical Civ TD |
|---|---|---|
| Yol | 1–2 sabit şerit | Çok-şerit + deniz + hain kapılar + arazi olayları |
| Düşman anlamı | Renk/HP skini | Kule işlerini karşılayan **roller** |
| Eskalasyon | HP ×1.1/dalga | Doktrin paketleri + çağ teknolojisi + kuşatma dayanıklılığı |
| Boss | Büyük HP süngeri | **Kural değişimi** olan isimli figür/kampanya menteşesi |
| RNG | Gizli spawn şansı | **Deklare edilmiş** desteler, seed'ler, FOLK-etiketli mucizeler |
| Oyuncu rolü | Her zaman savunmacı | Savunmacı **veya** saldırgan-medeniyet kampanyaları |

**Kutup yıldızı:** Her dalga şu soruyu cevaplamalı: *bugün duvarda hangi tarihsel baskı var?* — sadece *ne kadar HP* değil.

### 7.2 Düşman rol taksonomisi (evrensel)

Roller **mekanik işlerdir**. Civ skinleri rollere binilir; paralel kural setleri icat etmez.

| Rol ID | Savunmaya karşı iş | Tipik karşı-önlem | Fazla kullanılırsa başarısızlık |
|---|---|---|---|
| **SWARM** | Tek-hedef DPS'i boğ; choke'u tıka | AoE, splash, ateş, liste öldürme sahaları | Harita spam bulamacına döner; okunabilir tehdit yok |
| **ARMORED** | Delici hasarı emer; zırh-delme/ateş/kuşatma zorlar | Cıvata, yağ, ağır cıvata, hero delme | Zırh meta'sı erken action'larda donar |
| **FAST** | Yavaş kuleleri geçer; kötü yerleştirmeyi cezalandırır | Slow, root, choke noktaları, süvari tepkisi | Spawn RNG'si üst üste yığılırsa adaletsiz hissettirir |
| **FLYER** | Yer yolunu/duvarları görmezden gelir (tarihsel olarak makul yerde) | Özel AA, ağlar, deniz AA | Anakronistik "hep uçan birimler" |
| **SIEGE ENGINE** | Kule/kapı/duvar hasarı; yavaş ama varoluşsal | Odaklı ateş, sortie, kazı karşı-önlemi, yapışkan slow | Çok tanksa her dalgada zorunlu hale gelir |
| **BOSS** | Kural-değişim olayı (aura, faz, şerit açılışı, politika) | Hazırlık penceresi, çok-fazlı plan, hero zamanlaması | Saf HP süngeri sıkıcıdır |
| **TRAITOR/SAPPER** | İç yolu açar, duvarları kazar, kapıyı çevirir, içeride spawn olur | Tespit, sadakat ölçeri, iç devriyeler, öldürme-avluları | Sonsuza dek görünmezse softlock yapar |

**§1.2 rol fiil sözleşmesi (uygulama):** SWARM = yüksek sayı, düşük HP/zırh, sızma skoru × sayı. ARMORED = zırh eşik üstü; yavaş; sızarsa yüksek keep hasarı. FAST = yüksek hareket hızı; açık şeritleri tercih eder; yavaşlatma alanlarına zayıf. FLYER = hava path katmanı; duvarları görmezden gelir; AA etiketlerine ölür. SIEGE = YAPIYI hedefler; yapı HP'si düşene kadar düşük sızma önceliği. BOSS = olay ID + faz dizisi; eskort spawn edebilir; benzersiz kazan/kaybet metni. SAPPER: şerit çevirebilir | duvar segmentine hasar verebilir | içeride spawn olabilir. TRAITOR: sadakat ≤0'da taraf değiştirir; kuleyi düşman spawn'ına çevirir.

### 7.3 Rol → medeniyet skinleri (örnekler, tam TO&E değil)

| Rol | Roma | Persia | Mısır | Çin | Maya–Mexica | Viking | Yunanistan | Japonya | Moğol | Avrupa Kalesi |
|---|---|---|---|---|---|---|---|---|---|---|
| Swarm | Yardımcı levy, hafif piyade | Tabi levy yığını | Conscript/Nubian hafif | Tatar yayı sıraları, köylü levy | Skirmisher bulutları | Bondi/akıncı grupları | Hafif peltast, kürekçi | Ashigaru yığını | Ondalık hafif süvari paketleri | Piyade serjan, levy |
| Armored | Lejyoner/testudo paketleri | Ölümsüzler/ağır mızrak | Savaş arabalı asiller (elit emici) | Cataphract/ağır tatar yayı hattı | Jaguar/kartal elitleri | Hirdmen/huscarl'lar | Hoplite/phalanx bloğu | Samuray/ō-yoroi elitleri | Ağır mızraklı süvari (geç) | Şövalye/plate (geç) |
| Fast | Equites/Numidian | Okçu süvari perdeleri | Savaş arabası koşucuları | Hafif süvari, bozkır müttefikleri | Hızlı yakalama takımları | Gemiden-karaya hücum | Prodromoi/hafif süvari | Süvari samuray | **Okçu süvari çekirdeği** | Hobelar/chevauchée binicileri |
| Flyer* | Nadir | Nadir | Solar-mavna **FOLK** gece baskını | Nadir/roket **geç FOLK** | Nadir | Nadir | Nadir | Fırtına kami **FOLK** | Nadir | Nadir |
| Siege | Onager, koç, kazı | Kuşatma trenleri (geç) | Megiddo-tarzı zafer sonrası | *Pao*, koç, geç gunpowder | Sınırlı (merdiven, ateş) | Nadir motor; ateş+merdiven | Helepolis, koç, causeway | Ateş, kazı, moat-doldurma | **Ele geçirilmiş mühendisler** + mancınık hattı | Mancınık, koç, mayın, geç bombard |
| Boss | Konsül/barbar kral | Büyük Kral/satrap | Firavun/Apophis **FOLK** | Savaş lordu/hanedan menteşesi | Tlatoani/yıldız-savaş kaptanı | Jarl/savunmacı Alfred | Polemarch/kral/Demetrius | Daimyō/lig abbotu | Kağan/Subutai kolordusu | Kral/Saladin/Llywelyn |
| Traitor/Sapper | Kapı rüşveti, mayın | Ayrılan satraplık | Tapınak politikası/Delta çatlaması | Fener yalancı alarmı, An Lushan çevirmesi | Elit yakalama takımları | Yerel işbirlikçi | Ephialtes yolu | *Kuchiyaku*/ihanet çevirmesi | Tabi zanaatkarlar + iç ajanlar | Kazı ekipleri, kapı rüşveti, Güller çevirmesi |

\*Flyer **en nadir** roldür. FOLK etiketlenmedikçe fantazi hava birimlerine göre deniz-baypası, uçurum yolları ve sapper kapılarını tercih edin.

### 7.4 Modifikatörler (dalga & harita)

Modifikatörler dalga banner'ında **görünür şekilde** yığılır. Sınır: Normal'de **2 combat + 1 anlatı**; daha yüksek zorluklar sınırı yükseltir (§7.7).

| ID | Combat modifikatörü | Tarihsel lezzet |
|---|---|---|
| `ARMOUR_UP` | Armored/Boss'ta +zırh | Plate çağları, testudo, Ölümsüzler |
| `SWARM_TIDE` | +sayı, −hafif HP | Levy taslakları, ashigaru, bondi |
| `FORCED_MARCH` | +hız, −HP | Chevauchée, Moğol remount |
| `NIGHT` | Görüş düşer; sapper buff'ı | Gece escalade, Apophis gece baskını **FOLK** |
| `WINTER` | Adapte-olmayanı yavaşlat; nehir şeritlerini dondur | Rus kış kampanyaları **CHR** |
| `HEAT_JUNGLE` | Süvari tavanı; saldırgan kampanyasında hastalık DoT'u | Vietnam/Champa sınırı **CHR** |
| `FAMINE` | Savunmacı spawn/tamir tavanları düşer | Büyük Kıtlık destesi **CHR** |
| `PLAGUE` | Periyodik sivil/kule mürettebatı kaybı | Atina vebası **LIT** |
| `FIRE` | Yapılar DoT alır; ahşap keep'ler açık | Japon tenshu, Viking longphort |
| `GUNPOWDER` | Kuşatma motorları dikey zırhı görmezden gelir | Castillon/geç HYW **CHR** |
| `STORM_SEA` | Deniz şeridi tehlikesi; batma şansı deklare edilir | Japonya 1274/81 — **otomatik-galibiyet değil**; FOLK/MOD etiketli |

| ID | Anlatı/politik modifikatörü | Not |
|---|---|---|
| `SUCCESSION` | Orta-harita dondurma veya sivil spawn | Moğol kağan ölümü; Viking kral ölümü |
| `REVOLT` | İç spawn'lar | Jacquerie; taşra Japon isyanları |
| `BETRAYAL_FOG` | Müttefik DPS güvenilmez; geciktirilmiş kanat | Sekigahara **LIT/MOD** |
| `RELIGIOUS_ZEAL` | Moral aurası; bazı korkuyu görmezden gelir | Ikkō ligleri; haçlı yeminleri |
| `TERROR_CLEMENCY` | Saldırgan teslim kartı sunar | Moğol "şimdi teslim ol" doktrini **CHR** |
| `DANEGELD` | Dalgayı atlamak için gold öde → sonra enflasyon | Viking İngilteresi **CHR** |
| `TRIBUTE_REVOLT` | Tiranlık yüksekse tabi şeritler çevirir | Delos Birliği; Yuan |

**Modifikatör kuralları:** (1) İnşa fazından önce deklare edilir (Betrayal Fog bir dalga önce hariç). (2) Forced March + Swarm Tide, habercili ikon olmadan gizli spawn şansıyla asla yığılmaz. (3) FOLK modifikatörleri (ilahi rüzgar, kami, Apophis) ekranda **FOLK** çipi gerektirir. (4) Saldırgan-kampanya modifikatörleri tersine döner: savunmacı AI Famine/Revolt alır; oyuncu Terror/Clemency araçları alır.

### 7.5 Çok-şerit mimarisi

| Şerit türü | Ne olduğu | Tasarım işi |
|---|---|---|
| **Yaklaşım** | Ana yol/vadi | Huni + AoE öğret |
| **Kapı/barbican** | Öldürme-avlusu bulmacası | Sapper vs tuzak avlusu |
| **Duvar-yürüyüşü/liste** | Gedikten sonra paralel yol | Katmanlı savunmayı ödüllendir |
| **Postern/hain** | Koşullu iç spawn | Sadakat/tespit meta'sı |
| **Kaynak/chevauchée** | Ekonomiyi yakan yan şerit | Keep HP'sine dokunmadan kazan |
| **Deniz** | Nehir/liman/sahil | §7.6 |
| **Rahatlama (relief)** | Müttefik ordu yolu (zamanlayıcı) | Yarış: rahatlamaya kadar tut |

Haritalar action başına **aktif şeritleri** deklare eder. Eşmerkezli keep'ler (Avrupa, Çin wengcheng, Japon masugata) iç şeritleri sadece dış HP eşiklerinden sonra açar.

| Desen | Dalga script'i | Civ örnekleri |
|---|---|---|
| **Sabitle & kanat** | Ön Swarm + yan Fast | Moğol çok-kolon; Salamis'te Yunan sahte ricat (deniz) |
| **Kuşatma odağı** | Kapıda kuşatma motorları + Swarm emici | Avrupa kalesi; Xiangyang |
| **Ekonomi baskını** | Keep sessizken kaynak şeridinde Fast | HYW chevauchée; Viking sahil |
| **İç ihanet** | Boss ortasında postern açılır | Thermopylae yolu; Sekigahara; Güller |
| **Kademeli kolordu** | A şeridi sonra B geciktirilmiş | Mohi şafak çok-kolordu **CHR** |

**Şerit adaleti:** Oyuncu bu dalga aktive olacak **tüm spawn portallarını** görebilir (sis sadece Betrayal Fog'un *hangi* müttefiğin çevireceği için, *çevrilip çevrilmeyeceği* için değil). Eşzamanlı 4-şerit açılışları sadece Hard+ veya boss fazlarındadır. Softlock yasağı: her şerit için en az bir yasal kule ayak izi o çağın teknolojisiyle mevcuttur.

### 7.6 Deniz şeritleri

Deniz bir skin değil, **birinci sınıf bir yol katmanıdır**.

| Deniz işi | Role haritalanır | Davranış |
|---|---|---|
| İniş teknesi/uzun gemi | Swarm/Fast teslimatı | Sahile ulaş → kara paketi spawn et |
| Savaş kalyonu/trireme | Armored (yüzen) | Limanı tartışır; kuleleri vurur |
| Ateş gemisi | Siege (anti-filo/anti-liman) | Liman HP'sini tek vuruşla götürür veya zincirleme yakar |
| Taşıma elitesi | Boss eskortu | Boss inmeden önce ölmeli |
| Liman sapper'ı | Sapper | Zincir/köprü kulesini keser |

| Civ | Deniz imzası | Adil-oyun notu |
|---|---|---|
| **Viking** | Nehir başkentleri, longphort kışlaması, köprü kuleleri (Paris 885–86) | Gemi geri çekilmesi = saldırgan için alternatif zafer |
| **Yunanistan** | Trireme daraltıcıları (Salamis), Long Walls keep-bağlantısı | Kara başarısızlığı deniz geri çekilmesine zorlayabilir (bağlı haritalar) |
| **Mısır** | Nil koridoru, Delta filoları, Ptolemaic zirvesi | Nehir = ana lojistik şeridi |
| **Çin** | Kızıl Kayalıklar ateş donanması; Yamen son direnişi | Ateş-gemisi deklare edilmiş tehlikedir, RNG silme değil |
| **Japonya** | Dannoura gelgit arenası; Mongol'a karşı Hakata sahili; Imjin reddi | Fırtına = ağırlıklı mevsimsel tablo, FOLK-etiketli |
| **Moğol** | Japonya amfibi **başarısızlık uzmanlığı**; Song nehir savaşı | Filo tedarik ölçeri; kıyı kaleleri güçlü |
| **Avrupa** | Liman kuşatmaları (Acre, Calais), nehir Seine kilitleri | Deniz aç bırakma saatlerini destekler |
| **Persia** | Amfibi inişler (savunmacı görüşüyle Marathon) | Sahil savunması tutorial'ı |
| **Roma** | Akdeniz projeksiyonu; nadir çekirdek döngü | Opsiyonel action, her haritada varsayılan değil |
| **Maya–Mexica** | Kano/göl causeway'i (Tenochtitlan) | Causeway kırılması = şerit silinmesi |

**Deniz kuralları:** (1) Sahil inişleri **geçici kara portalları** yaratır — gemiler önce yanarsa yok edilebilir. (2) Zincir kirişler/tahkimli köprüler **inşa edilebilir**, lore metni değil. (3) Tayfun/fırtına atışları **kamuya açık ağırlıklar** kullanır (§7.9). Asla "kamikaze butonu kampanyayı kazandırır" değil.

### 7.7 İmza olaylar & Moğol sahte ricat

**Sahte ricat (Moğol imza olayı):** Açık-alan doktrini Avrasya'da gözlenmiş; İç Asya araç kutusu, bu rosterde Moğol kampanya markası olarak birçok **CHR/MOD** kaydında — Moğol telifi değil ama bu ekipteki imzaları.

| Faz | Oyuncu ne görür | Kurallar |
|---|---|---|
| 0 Telegraf | Banner: "Düşman kırılıyor — kovala?" | Normal+'da 3s karar penceresi |
| 1 Sahte kaçış | Fast/Armored paketleri harita kenarına kaçar | Kuleler hâlâ ateş eder; **sortie birimleri** kovalayabilir |
| 2 Pusu | Gizli rezervler yan şeritlerden girer | Oyuncu **tuzak hattını** geçerse pusu +hasarla spawn olur |
| 3 Çözüm | Oyuncu tuzak hattını tuttuysa: pusu zayıflar/gold bonusu | Fazla kovalarsa: sızma zirvesi + yapı kırpması |

**Öğretici haritalar:** Kalka (tutorial boss'u), Liegnitz (şövalye-hücumu cezası), Mohi (köprü kontrolü + çok-kolordu). **Karşı-teknoloji:** ʿAyn Jālūt'ta "hileyi öğrenen" Memluk gibi geç düşman faksiyonları `IGNORE_FEIGNED` kazanabilir (yem yemez). **Saldırgan kampanyası:** Oyuncu sahte ricatı cooldown + remount maliyetiyle bir yetenek olarak **tetikler**.

| Diğer evrensel olay şablonu | Tetikleyici | Etki |
|---|---|---|
| `SUCCESSION_FREEZE` | Boss/kral ölümü veya takvim | Ordu N saniye dondurma; sivil çatallanma |
| `RELIEF_ARMY` | Zamanlayıcı | Müttefik şerit açılır; keep önce ölürse başarısız |
| `STARVE_CLOCK` | Uzun kuşatma haritaları | Duvarlar dursa da erzak = 0 olunca kazan |
| `MOAT_FILL` | Saldırgan ilerlemesi | Kuşatma-motoru şeridini açar |
| `GATE_BRIBE` | Hain ölçeri | Anında liste/keep şeridi |
| `ENGINEER_CAPTURE` | Sapper elit öldürme | Bir sonraki-çağ kuşatma teknolojisini açar (saldırgan) |
| `SURRENDER_OFFER` | Terror ölçeri yüksek | Tiranlık maliyetine kalan dalgaları atla |

### 7.8 Zorluk modları

| Mod | HP/Sayı | Şeritler | Modifikatörler | Telegraf | Not |
|---|---|---|---|---|---|
| **Story** | 0.85 | 1–2 | 0–1 | Tam | Rolleri öğretir; sahte ricat otomatik ipucu verir |
| **Normal** | 1.0 | 2–3 | ≤2+1 | Tam | Varsayılan |
| **Hard** | 1.2 | 3–4 | ≤3+1 | Kısa | Eşzamanlı çok-şerit izinli |
| **Legend** | 1.35 | Tam harita kiti | Sert desteler | Minimal | Opsiyonel ironman |
| **Historical** | Değişken | Çağ-kilitli | Sadece çağ desteleri | Tam | Anakronistik gunpowder erken yok |
| **Myth** | Kozmetik FOLK açık | Normal ile aynı | FOLK boss'lar izinli | Tam | Kami/Apophis/oni sezonları |

**Historical mod yasası:** Birim çağları kampanya yılını takip eder. Hastings'te plate yok; Genpei'de teppō yok; Krak'ın ilk action'ında bombard yok.

### 7.9 Endless / Horde

Endless bir **post-kampanya laboratuvarı**, çekirdek fantazi değil.

1. §7.4'ten **seed'li haftalık mutatörler** (kamuya açık seed).
2. Eskalasyon: her 5 dalgada bir rol ağırlığı ekle; her 10 dalgada bir civ destesinden Boss olayı ekle.
3. **Doktrin kırılmaları** her 25 dalgada (sahte ricat, aç bırakma saati, deniz inişi, isyan).
4. Skorlama: sızmalar, keep HP'si, gold verimliliği, Historical Endless'ta FOLK birim yoksa "tarihsel doğruluk" bonusu.
5. Yumuşak pity: 3 acımasız modifikatör atışından sonra, sıradaki atış `FAMINE+PLAGUE` çiftini dışlar.

Oyuncular bir **savunmacı civ** seçer; saldırgan destesi döner veya seçilir (Moğol Ordası, Büyük Kâfir Ordusu, Helenistik Kuşatma Çağı vb.). Her 10. dalgadaki boss o destenin imza boss tablosundan (§7.11) çekilir.

### 7.10 Adil RNG

| İlke | Uygulama |
|---|---|
| **Sessiz silme RNG'si yok** | Fırtınalar, vebalar, ihanetler olasılıkları gösterir |
| **Seed'li koşular** | Paylaşılabilir seed; ranked sunucu seed'i kullanır |
| **Pity/anti-sıçrama** | Story/Normal'de habercisiz 3× Fast sızma paketi arka arkaya yok |
| **Deklare edilmiş tablolar** | Hakata için fırtına mevsim tablosu; HYW için kıtlık deste ağırlıkları |
| **FOLK etiketli** | İlahi rüzgar ≠ fizik; çip gerekli |
| **Saldırgan yetenekleri ≠ zar** | Sahte ricat bir skill'dir, yazı-tura değil |

**Örnek — Japonya istila fırtına tablosu (kamuya açık):** Yaz/sonbahar yüksek ağırlık → hafif fırtına, −%10 deniz doğruluğu. Zirve tayfun penceresi → ağır, **korumasız** gemilerin %10–25'i batar (bir kez atılır, gösterilir). Sezon dışı → %0. Savunmacı hâlâ sahil kulelerine ihtiyaç duyar; fırtına inen orduyu asla otomatik temizlemez.

**Spawn kompozisyonu RNG'si:** Dalga tarifleri tam bestiary'den saf ağırlıklı rastgele değil, ±%10 sayı titremesiyle **yazılmış karışımlardır**. Boss eskortları sabit listelerdir.

### 7.11 PvP kule offense'i (opsiyonel mod)

**Öneri:** Lansmanda gerçek-zamanlı 1v1 şerit spam değil, **async Attack-the-Base** (Kingdom Rush/Clash-benzeri) olarak gönder.

**Mod: Kuşatma Kartları (async):** (1) Oyuncu A bütçe + çağ tavanıyla bir savunma düzeni inşa eder. (2) Oyuncu B rol kartlarına + 1 olaya bir **saldırı bütçesi** harcar. (3) Simülasyon **paylaşılan seed** ile çalışır; her ikisi de replay'i görür. (4) Skorlama: sızmalar + yapı hasarı + gediğe-kadar-süre.

**İzin verilen saldırı kartları:** Rol paketleri (Swarm, Armored, Fast, Siege, Sapper — sınırlı); Bir Olay kartı (Sahte Ricat, Kapı Rüşveti, Deniz İnişi, Aç Bırakma Baskısı); Boss kartları (sadece ranked/sezonluk; anti-whale: tek boss slotu).

**Yasak listesi (adalet/kültür):** Anlık FOLK silme (tam kamikaze temizleme); görünmez kalıcı hainler; oyun listesi dışı anakronistik teknoloji; savunmacı duvarları paralı atlama.

**Neden v1'de gerçek-zamanlı değil:** Gerçek-zamanlı netcode, bağlantı-kesme kuralları ve toksik spawn-mikro gerektirir. Async, tarihsel bulmaca fantazisini ve tasarım QA replay'lerini korur.

### 7.12 Saldırgan-medeniyet kampanyaları

**Fantazi:** Oyuncu duvar değil, **kuşatmacı/istilacı doktrindir**. Örnek başlık: **Moğol olarak bir Avrupa kalesini kuşat** (Liegnitz–Mohi tiyatrosu veya jenerik eşmerkezli keep + Avrupa savunma AI'sı).

| Faz | Oyuncu fiilleri | Kazanma metrikleri |
|---|---|---|
| Keşif | Keşif kampları yerleştir; şeritleri açığa çıkar | Görüş |
| Yatırım | Rol paketlerini "yazdığın dalgalar" olarak konuşlandır | Baskı |
| Mühendislik | Ele geçirilmiş zanaatkarlarla kuşatmayı aç | Kapı/duvar HP'si |
| Doktrin | Sahte ricat, çok-kolon zamanlaması, terror/clemency | Moral kırma/gedik |
| Politika | Ardıllık riski; müttefik tümenler | Dondurmaya otomatik kaybetme |

**Tersine çevirme:** Savunmacı, civ başına kule inşa kurallı bir AI'dır. Oyuncunun "canları" = **remount/kolordu uyumu/kağan mandate**'i. Mandate kırılırsa veya kuşatma dayanıklılığı 0'a düşerse kaybet.

**Set-piece tasarımı — Moğol → Avrupa kalesi:** Harita kiti: Eşmerkezli keep (Beaumaris-benzeri diyagram) + açık alan yaklaşımları + opsiyonel donmuş nehir. Oyuncu araçları: Okçu-süvari Swarm/Fast, sahte ricat olayı, ilk dış-kasaba düşüşünden sonra mühendis açılışı, kış buff'ı. Savunmacı AI: Barbican tuzakları, liste öldürme sahası, sortie butonu, rahatlama-ordusu zamanlayıcısı (Macar/Polonya koalisyon lezzeti). Öğret: Şövalyeleri tuzak hattına çekmek; dış bailey çatlayana kadar cepheden kuşatma-spam'i yapmamak; 1241–42 geri çağırma olayını opsiyonel zor-başarısızlık dalı olarak izlemek.

| Saldırgan oyuncu | Savunmacı AI | İmza ders |
|---|---|---|
| Moğol | Avrupa Kalesi/Jin/Song/Japonya kıyısı | Sahte ricat; mühendis yakalama; deniz sınırı |
| Viking | Frank/Wessex burh'ları | Baskın → kışlama → Alfred rahatlama yarışı |
| Roma | Helenistik/Gal oppida'ları | Mühendislik grind'i; disiplin vs acele |
| Persia | Yunan polisleri | Çok-etnik swarm + sahil inişleri; koalisyon kırılganlığı |
| Yunanistan (Atina) | Tabi adalar/Syracuse | Deniz imparatorluğu; denizaşırı tedarik ölümü |
| Yunanistan (Makedon) | Tyre/Asya keep'leri | Causeway + kuşatma treni |
| Çin (Qin/Han/Tang) | Rakip *guo*/bozkır | Kota savaşı; süvariye karşı duvarlar |
| Mısır | Levant kaleleri | Megiddo sonrası takip disiplini; hendek kuşatması |
| Japonya (Sengoku) | Rakip kaleler | Ateş, ihanet, Osaka moat-doldurma |
| Avrupa (Plantagenet/Capet) | Rakip keep'ler | Kazı + mancınık + aç bırakma; chevauchée yan-zafer |
| Maya–Mexica | Rakip şehir | Yakalama-öncelikli "canlı alma" skorlaması |
| Babylon | Rakip Mezopotamya/Elam | Emek lojistiği; nehir şeritleri |

**Yakalama vs öldürme (Mexica özeli):** Saldırgan dalgaları `CAPTURE_PREF` etiketleyebilir — skorlama prestij ekonomisi için canlı almaları tercih eder. Bu, optimal kuleyi (öldürücü-olmayan ağlar/stun) ve boss kurallarını (tlatoani yakalama ≠ ceset) değiştirir.

### 7.13 Dalga yazım hattı (pratik)

1. **Savunmacı civ + action yılı** seç → çağ teknolojisini kilitle.
2. **Saldırgan doktrin destesi** seç (genelde başka bir civ).
3. 8–12 dalga yaz: rolü öğret → karıştır → kuşatma baskısı → olay → boss.
4. Modifikatör sınırını ata; telegraf ikonları.
5. Playtest sızma nedenlerini: Fast açıklanmamışsa yerleştirme araçlarını düzelt — HP'yi buff'lama.
6. Her FOLK vuruşunu etiketle.
7. Endless'ta yeniden kullanım için seed + tarif JSON'unu export et.

**Minimum öğretim sırası (FTUE):** (1) Sadece Swarm (2) Armored girişi (3) Fast sızma (4) Çok-şerit sabitle & kanat (5) Kapıya karşı kuşatma motoru (6) Sapper tespiti (7) Boss + olay (sahte ricat veya ihanet — civ-uygun) (8) Deniz inişi (civ'in denizi varsa).

### 7.14 Anti-pattern'ler (asla göndermeyin)

1. Kural değişimi olmayan HP süngeri boss.
2. Tespit oynanışı olmadan görünmez hainler.
3. Ödenmemiş kazanma butonu olarak ilahi rüzgar/mucize.
4. Story'de dört şerit + Forced March + Swarm Tide.
5. FOLK etiketi olmadan Tunç/Demir Çağı'nda Flyer spam.
6. Tüm bozkır/tüm "Doğulu" saldırganları bir desteye eşitlemek.
7. PvP pay-to-win boss kartları.
8. Sadece "spawner yerleştirme", mühendislik/politika olmayan saldırgan kampanyası.
9. Sessiz anakronizm (Krak'ın I. action'ında bombard).
10. UI'da seed'lenemeyen veya açıklanamayan RNG.

---

## 8. Medeniyet kampanyası Act 0–10 (Babylon dahil her act detay)

*(Kaynak: `GDD-CIV-HOOKS.md` — tam dosya, `GDD-BABYLON-ACT0.md` — tam dosya, Act 0 için)*

### 8.0 Ortak paket ve sıra

| Act | Civ | Keep (tek satır) |
|---|---|---|
| 0 | Babylon | Etemenanki ziggurat'ı |
| 1 | Roma | Capitolium / Jupiter Optimus Maximus |
| 2 | Persia | Persepolis Apadāna'sı |
| 3 | Mısır | Buhen kalesi (+ Karnak skini) |
| 4 | Çin | Weiyang Gong (+ wengcheng grameri) |
| 5 | Maya–Mexica | İkiz: Tikal I / Becán → Templo Mayor |
| 6 | Viking | İkiz: Trelleborg ringfort + Jelling |
| 7 | Yunanistan | İkiz: Akropolis + Long Walls / Piraeus |
| 8 | Japonya | Himeji daitenshu (~46.4 m) |
| 9 | Moğol | İkiz: Ordu/Karakorum → Yuan Dadu |
| 10 | Avrupa Kalesi | İkiz: Beaumaris + Krak des Chevaliers |

**Çapraz-civ tasarım kuralları** (kampanyanın tamamına uygulanır):

1. Her civ **aynı 7-katman keep dilbilgisini** ARCH-öncelikli istiflerle yeniden karıştırır (remix); mit katman 7'de/ultimate'lerde oturur, sahte duvar kalınlıklarında değil.
2. Dokümanlar bunu öngördüğünde **ikiz keep'i** tercih et (askeri diyagram + ideoloji markası).
3. **FOLK/LIT/SAGA/SPN** içeriğini etiketle; hiçbirinin sessizce ARCH kronolojisinin üzerine yazmasına izin verme.
4. İmza düşmanlar genelde **başka roster civ'leri** + çağa özel boss'lar olmalı (paylaşılan asset, rekabet action'ları sağlar).
5. Babylon Act 0 dilbilgiyi öğretir; sayılar kilitlenmeden önce **özel araştırma dosyası gerekiyordu** — bu boşluk `GDD-BABYLON-ACT0.md` ile dolduruldu (bu bölümün §8.1'i).

---

### 8.1 Act 0 — Babylon (Etemenanki) — tutorial act, en detaylı işlenen

**Tek satır pitch:** *Marduk'un yedi katlı dağına tırman ve tut — sonra dünya sana her diğer duvarı öğretir.*

#### 8.1.1 Keep ve rol

**Etemenanki** ("Göğün ve Yerin Temeli Evi") — Babil'de Marduk'un katlı tapınak-kulesi; Esagila tapınak kompleksi kutsal zemin katı olarak. II. Nebukadnezar restorasyonları geç Yeni-Babil dönemi varsayılan yüzüdür (**SPN/ARCH**).

#### 8.1.2 7-katman fantazi (oynanabilir istif)

Klasik/Herodot okuması genelde **7 teras** + zirve türbesi olarak okunur (**LIT/MOD**, tape ölçümü değil). Oyun istifi:

| Katman | Dünya-içi ad | Topoloji fonksiyonu | Öğretim vuruşu |
|---|---|---|---|
| L1 | Dış yaklaşım / kanal kenarı | Geniş yaklaşım şeridi + opsiyonel kanal yan-yolu | İlk kuleyi yerleştir; yolu gör |
| L2 | **Imgur-Enlil** dış duvarı | Kule-aralıklı build parselli perde | Perde DPS'i / ucuz okçular |
| L3 | **Nemetti-Enlil** iç duvarı | İkinci perde; daha kısa öldürme bölgesi | İkinci hat / sat-ve-yeniden-inşa et |
| L4 | Processional Way (*Ay-ibur-šabu*) yaklaşımı | Yan parselli uzun düz "aslan yolu" | Yaylım ateşi / destek aurası |
| L5 | **İştar Kapısı** choke'u | Tek choke + kapı HP script'i | Kışla blokajı + kapı etkileşimi |
| L6 | Ziggurat teras tırmanışı | Zigzag/teras halkaları; tırmanıcılar bazı rayları görmezden gelir | Anti-tırmanıcı / odaklı ateş |
| L7 | Zirve cella / Marduk huzuru | Keep çekirdeği | Integrity, tamir maliyeti öğretimi (caydırıcı) |

**Kanıt notları:** İkiz-duvar adları Imgur-Enlil/Nemetti-Enlil → **ARCH/SPN** (yazıt + kazı geleneği). Processional Way + İştar Kapısı sırlı-tuğla programı → **ARCH** (Koldewey; Pergamon Müzesi rekonstrüksiyonu görsel referans, "kapı oyunda Berlin'de" değil). Etemenanki'nin pişmiş-tuğla çekirdeği, ~kare tabanı klasik olarak ~91 m → **ARCH/MOD** aralıkları; UI "~90 m sınıfı" gösterir, turist Babel kilometresi değil. Klasik/Herodot **7 teras** → **LIT/MOD oynanabilir istif** olarak muamele; Codex teras sayısının rekonstrüksiyon olduğunu, şerit ölçümü olmadığını kabul eder.

#### 8.1.3 Harita seti (FTUE sırası)

| Harita ID | Ad | Açık katmanlar | Topoloji | Süre |
|---|---|---|---|---|
| B0-M1 | **Kanal Kenarı** | L1–L2 | Tek şerit + kanal köprüsünde yumuşak çatal | 3–4 dk |
| B0-M2 | **Çift Perde** | L1–L3 | Dış→iç duvar yardları; ilk satış öğretimi | 4–5 dk |
| B0-M3 | **Aslan Yolu** | L2–L5 | Processional düzlük + İştar Kapısı choke'u koşu-ortası açılır | 5–7 dk |
| B0-M4 | **Teras Tatbikatı** | L4–L6 | Zigzag tırmanış; tırmanıcı düşmanlar | 5–7 dk |
| B0-M5 | **Göğün Temeli** | L1–L7 tam istif | Yardlar → kapı → tırmanış → cella; boss finali | 8–12 dk |

#### 8.1.4 Anlatı çatısı (atlanabilir, kısa)

**Soğuk açılış sineması (≤8s, atlanabilir):** Alacakaranlıkta Etemenanki'nin pişmiş-tuğla silüetine toz çöker; Processional Way aslanları bir kez flaşlanır; başlık kartı *Etemenanki*. Lore duvarı yok.

**Oyuncu rolü:** İsimli bir komutan öğretmenin (ses-üzeri) altında genç bir *šaknu*/duvar bekçisi. Komutan oyuncuyu ilk zaferden sonra adlandırır (FTUE vuruşu).

**Tehdit iskeleti (tutorial fiction, kronoloji yumuşak):** (1) Çöl/kanal kenarı yağmacıları (jenerik koşucular) — temelleri öğret. (2) Assur-tarzı kuşatma baskısı (**erken antagonist kiti**, tatbikat hafızası/flashback olarak etiketli — **MOD çerçeveleme**, Nebukadnezar'ın Asurbanipal ile aynı Salı günü savaşmadığını iddia etmemek için). (3) Elam dağlık akını — zırh + tırmanma dozu. (4) Med/Pers geç baskısı → **Boss: Kapıdaki Kyros**, ikiz-anlatım UI'sıyla.

**Ahlaki kaybetme koşulu:** Zirve **cella/Marduk huzuru** Integrity'si 0'a düşer. Lezzet: "göğün ve yerin temeli çatlıyor." "Canlar = 20 balon" değil.

#### 8.1.5 Kuleler (Act 0 açılış merdiveni)

| Kule | Sınıf | Rol | Kanıt | Not |
|---|---|---|---|---|
| **Bow Gallery (Yay Galerisi)** | Ok/mızrak | Ucuz DPS | **ARCH** okçuluk yaygın; kabartma dili | Ghost-hand ilk yerleştirme |
| **Spear Barracks (Mızraklı Kışla)** | Kışla/spawn | Melee blokerler | **ARCH** piyade; garnizon parsel binası olarak kurgulanmış | M1'de zorunlu öğretim |
| **Mudbrick Curtain Post (Kerpiç Perde Direği)** | Ok desteği | Kısa menzil, ucuz duvar-slotu | **ARCH** kerpiç + kule aralıkları | L2'de otomatik vurgulanır |
| **Sling Battery (Sapan Bataryası)** | Hafif splash | Swarm'a karşı yumuşak AoE | **ARCH/MOD** Yakın Doğu'da sapan; Babil'e özel değil | Zırh/swarm öğretimi |
| **Processional Flank (Processional Kanadı)** | Destek/buff | Düz şeritte +menzil aurası | **MOD** tasarım; yol **ARCH** | Aslan-tuğla VFX'i **ARCH** |
| **Ishtar Gate Ward (İştar Kapısı Muhafızı)** | Özel civ/kapı | Kapı HP tamponu + choke'ta slow | Kapı **ARCH**; buff sayıları **MOD** | Harita başına bir tane |
| **Terrace Archer Platform (Teras Okçu Platformu)** | Ok (yükseltilmiş) | Tırmanıcılara bonus | Teraslar **LIT/MOD**; okçuluk **ARCH** | M4 açılışı |
| **Esagila Precinct Ward (Esagila Bölge Muhafızı)** | Destek | Keep yakınında minik regen aurası | Tapınak kompleksi **ARCH**; regen **MOD** | Keep-tamiri emiciliğini caydırır |
| **Glazed Brick Ballista (Sırlı Tuğla Balistası)** | Ağır cıvata | Zırha karşı | Ağır cıvata = evrensel sınıf; sırlı tuğla = **ARCH** görsel | Pacing izin verirse opsiyonel |
| **Mušḫuššu Relief (Mušḫuššu Kabartması)** | Özel/Mitik | Korku darbesi/taunt | Kabartma yaratık **ARCH**; savaş eviciliği **FOLK** | Historical modda sadece kozmetik |

**Upgrade felsefesi (Act 0):** Yol A: **Hız/yaylım** (DPS istiflemeyi öğret). Yol B: **Fayda** (slow, zırh yırtma veya bloker HP'si). Act 1'e kadar karşılıklı dışlama zorunlu değil. Satış geri ödemesi tutorial'da %60 (bağışlayıcı); Act 0'dan sonra %50.

**Hero (öğretmen → oyuncu):** Öğretmen Komutan (NPC) — Rally: 5s +blok, M3'te bir kez pasif kullanım. Oyuncu Komutanı (M3 sonrası açılır) — Flare: açığa çıkarma + küçük slow, aktif yetenek slotu. Opsiyonel skin: *šangu* rahibi — Akītu şerit susturması, sadece Mitik. Gacha yok; yıldız/harita temizlemeleriyle açılır.

#### 8.1.6 Düşmanlar (Act 0 dalga grameri)

Ekonomi dokümanından dalga cümlesi: `[açılış koşucuları] + [ana kuvvet] + [özel] + [opsiyonel bosslet]`

| Düşman | Rol | Karşı-önlem öğretimi | Kronoloji/etiket |
|---|---|---|---|
| **Kanal Koşucusu** | Koşucu/sızma | Kışla + slow | Jenerik **MOD** |
| **Levy Mızrakçısı** | Ana kuvvet | Yay galerileri | Yeni-Babil levy lezzeti **MOD** |
| **Kalkan Çifti** | Kalkan duvarı | Kanat parselleri/arkadan AoE | **MOD** taktikler |
| **Elam Dağlısı** | Zırhlı/kısmi tırmanıcı | Sapan/ağır/teras | Elam baskısı **ARCH** rekabet çağı (yumuşak) |
| **Assur Sapper'ı** | Perdeye karşı kuşatma | Odaklı ateş | Assur kuşatma sanatı **ARCH**; tatbikat antagonisti **MOD** |
| **Kuşatma Koçu Ekibi** | Kuşatma motoru → kapı/keep hasarı | Kapı kapatma + odak | Yakın Doğu kuşatması **ARCH** |
| **Med Süvari Keşifçisi** | Hızlı çatal kanadı | Erken çağırma disiplini | Med/Pers prelüdü **ARCH** yumuşak |
| **Pers Ölümsüzü (teaser)** | Elit zırhlı | Zırh yırtma | Yunan merceği ismi → Codex **LIT** vs Pers öz-terimleri **MOD** |
| **Kayık Akıncısı (opsiyonel)** | Kanal spawn'ı orta-yolda | 2s önceden duyuru | Kanal savaşı **MOD**; adalet kuralı dalga dokümanından |

| Harita | Dalga | Kompozisyon vuruşu |
|---|---|---|
| M1 | 5 | W1–3 tek tip; W4 karışık; W5 minik elit |
| M2 | 6 | Zırh girişi W4; satış penceresi W2–W3 arası |
| M3 | 8 | Kapı koçu W5; W4 sonrası erken çağırma; Ölümsüz teaser W8 |
| M4 | 8 | Tırmanıcılar W3+; teras odağı |
| M5 | 12–15 + boss | Tam karışım; script'li kapı vuruşu ortada; Kyros fazları |

Sızma hasarı (Integrity): koşucu 1, zırhlı 2, koç vuruşu 8, boss faz sızması 15.

#### 8.1.7 Boss — Kapıdaki Kyros (M5)

**İkiz-anlatım tasarımı (zorunlu etiketler):**

| Anlatım | Kanca | Etiket | Oyun ifadesi |
|---|---|---|---|
| **A — Opis/Nabonidus Chronicle** | Opis muharebesi, sonra Babil'e giriş | **ARCH/SPN** (chronicle) | Faz 1 tarla ordusu; Faz 2 "şehir açılıyor" moral şoku |
| **B — Herodot'un nehir-akıtma anlatımı** | Fırat'ı yönlendir, nehir yatağından gir | **LIT** | Opsiyonel faz modifikatörü: kanal şeridi boşalır, yeni kuru yol açılır |

**UI kuralı:** Finalden önce, Codex kartı: "İki antik hikaye — istediğiniz anlatımı oynayabilirsiniz. Hiçbiri sessiz gerçek değildir." Varsayılan Historical playlist = **Anlatım A**. Anlatım B = opsiyonel modifikatör (yine LIT etiketli, FOLK karikatür girdap değil).

| Faz | Script | Oyuncu öğretimi | Juice |
|---|---|---|---|
| P1 | Karışık ordu + İştar Kapısı'nda koç | Odak ateşi + kapı etkileşimi | Koçta kamera sarsıntısı |
| P2 | "Şehir girişi" — spawn noktaları iç avluya döner | İç parselleri yeniden inşa et; gerekirse dışı sat | Boru SFX'i; Integrity uyarısı |
| P3 | Elit muhafız cella'ya iter | Hero yeteneği + Esagila muhafızı | Zirve kamerası |
| P3alt (LIT B) | Kanal kuru-yolu açılır | Acil teras/kanat kuleleri | Su VFX'i drenajı — **LIT** etiketli |

**Kazan/kaybet:** Zafer: Akatça-lezzetli kısa banner ("Temel tuttu"); yıldızlar; Codex sayfası; komutan adlandırma. Yenilgi: keep çatlar, ipucu ("Kapıda kışla"), küçük gold bonuslu ücretsiz retry — şerefsiz reklam yok.

#### 8.1.8 Ekonomi öğretim anları

| An | Harita | Mekanik | Ders |
|---|---|---|---|
| E1 Başlangıç kesesi | M1 | Tam 2 kule için gold | "2-3 temel kuleye yeter; tam tahtaya değil" |
| E2 Öldürme tikleri | M1 W1 | Yüzen +gold | Öldürme başına değer |
| E3 Temizleme bonusu | M1 sonu | Banner bonusu | Temizleme ≠ sadece öldürmeler |
| E4 Upgrade fırsat maliyeti | M1–M2 | Zorunlu upgrade, sonra sıkı sonraki dalga | Upgrade vs yeni yerleştirme |
| E5 Satış geri ödemesi | M2 | Yanlış yerleştirilmiş direği sat | Geri ödeme %'si; panikten çok planlama |
| E6 Erken çağırma | M3, W4 sonrası | Buton + ilk kez duraklatma | Overlap riski vs +% gold/mühür şansı |
| E7 Kapı tamiri cazibesi | M3 | Pahalı kapı tamiri | Emiciler: tamir caydırılır |
| E8 Faiz dozu (opsiyonel) | Sadece M5 | "Kervan" destek parseli küçük faiz sunar | Opsiyonel sert-harita DNA'sı; ilk temizlemede kapalı |
| E9 Yıldız ekonomisi | Her haritadan sonra | ★1 temizleme / ★2 Integrity / ★3 hız veya satış-istismarı yok | Yıldızlar → sonraki Codex mühürleri |
| E10 Yumuşak ödül duşu | M5 sonrası | 1 soft para + 1 kozmetik tuğla + Codex | 50 para değil |

#### 8.1.9 Kanıt vs FOLK — içerik kartları (Codex tohumları)

**ARCH-öncelikli kartlar (her zaman açık):** Etemenanki adı & rolü; Koldewey kazısı; Imgur-Enlil/Nemetti-Enlil adlı çift duvarlar; Processional Way; İştar Kapısı; Kanal/Fırat kentsel suyu; Nebukadnezar II restorasyon sesi (SPN yazıtları, çapraz-kontrollü lezzet olarak).

**LIT kartlar (etiketli):** Klasik katlı kule/teras okumaları (Herodot-bitişik gelenek dahil); Herodot'un nehir-akıtma yoluyla fetih; İncil'deki Babil Kulesi bir **edebiyat** paraleli olarak — "ölçülmüş bir plan değil."

**FOLK kartlar (sadece Mitik/kozmetik):** Turist sonsuz-yükseklik Babel; Asma Bahçeler onaylı bir harita harikası olarak (tartışmalı → en fazla atmosfer); Ecbatana yedi-renk boyasının Babil'e aktarılması; kaynar yağın "Babil özelliği" olması; büyücü-kral Nebukadnezar'ın Enuma Elish lazerleri fırlatması; yaşayan mušḫuššu'lardan oluşan oynanabilir ordu.

**Yasak-varsayım tuzakları (sert):** Babel İncil hikayesi ≠ Koldewey irtifası. Asma Bahçeler'in varlığı/konumu tartışmalı → atmosfer only (**FOLK/MOD**). "Ecbatana gibi 7 renk" Babil arkeolojisi **değildir** (**LIT** Pers transferi — ARCH boyası olarak reddedilir). Kaynar yağ/boiling oil varsayılan Babil kiti olarak = zayıf. Nebukadnezar ≠ çizgi film büyücü-kral. Mušḫuššu'nun kitle ordu birimi olması = **FOLK** gerginlik; muhafız kabartma dili **ARCH**'tır.

#### 8.1.10 Yıldız & madalyalar

| Yıldız | Koşul |
|---|---|
| ★1 | Temizleme |
| ★2 | Keep Integrity ≥ %70 VEYA Nemetti-Enlil'in (iç duvar) arkasına sızma yok |
| ★3 | Integrity ≥ %90 + hız temizleme VEYA satış-istismarı yok (M2+) |

**Codex mühürleri (önizleme):** Heroic/Iron madalyaları zafer ipucunda bahsedilir ama tam modlar Act 0'dan sonra açılır — FTUE aşırı yüklemesini önler.

#### 8.1.11 Kapanış mesajı ve sonraki action'lara teslim

**Mağaza/fragman pitch satırı (sadece Act 0):** *Processional Way'i tut. İştar Kapısı'nı kapat. Marduk'un dağını ayakta tut — sonra dünya sana her diğer duvarı öğretir.*

| Açılan kavram | Yeniden kullanıldığı yer |
|---|---|
| 7-katman dilbilgisi | Tüm civ'ler |
| Kapı choke etkileşimi | Japonya masugata, Çin wengcheng, Avrupa kapı evi |
| Katmanlı kuşatma yardları | Persia terası, Kale listeleri |
| İkiz-kaynak etiketleme | Persia 539, Yunanistan Arşimet FOLK geçişi vb. |
| Integrity keep modeli | Tüm action'lar |
| Erken çağırma + yıldızlar | Live ops / günlükler |

---

### 8.2 Act 1 — Roma

**Keep:** **Capitolium / Templum Iovis Optimi Maximi** — üç *cella* (Jüpiter/Juno/Minerva); yenilgi = aquila/tapınak kaybı. Castrum *principia*/*aedes* saha-haritası ikizidir.

**7-katman fantazi:** Alesia + castrum istifi dıştan içe: stimuli → lilia → cippi → fossa → berm + vallum → lorica/pinnae + mural kuleler → principia/Capitolium. Şehir skinleri: erken Servian / geç Aurelian çift perde.

| Alan | Kanca |
|---|---|
| **İmza kuleler** | Scorpio nişancısı; balista; onager bataryası; Aurelian ikiz-kule çapraz ateşi; Juno'nun kazları gizli tespit; *horrea*/su kemeri destekleri |
| **İmza düşmanlar** | Gal savaş grupları; Pyrrhus filleri; Hannibal (filler + Numidia süvarisi); Part okçu-süvarisi + cataphract; Alaric "kapı açıldı" bükümlü boss'u; oyuncu savunduğunda ters-çevrilmiş Roma kuşatma kiti (*aries*, *turris*, *testudo*) |
| **Mit ultimate'ler** | **Jüpiter** zafer alayı şimşeği/restore-yeniden-adama; **Aquila** lejyon moral bağı; **Mars** sortie gazabı; Sibylline tek-atım strateji kartı (risk: tapınak yangını desteyi yakar) |
| **Yasak-varsayım tuzakları** | "Her zaman kaynar yağ" Roma varsayılanı olarak; her Cumhuriyet askerinin Marian klonu olması; Capitolium'un asla yanmaması (yandı — yeniden inşa döngüleri içeriktir); auxilia'yı görmezden gelmek; her Germen düşmanı aynı yığın gibi ele almak |
| **Kampanya action pitch'i** | **Act I:** Servian kale tatbikatlarından Alesia mühendislik okuluna, Aurelian alacakaranlığına — Capitol'ü tutarken düşmanlar Roma'nın kendi kuşatma kitabını ödünç alır |

**Tek satır pitch:** Tuzak-alanlı castra ve Jüpiter'in Capitol'ü, filler, Partlar ve açılan kapılara karşı mühendislik savunmasını öğretir.

---

### 8.3 Act 2 — Persia (Achaemenid → opsiyonel Part/Sasani açılışları)

**Keep:** **Persepolis Apadāna'sı** (72 sütunlu salon + 4 köşe kulesi); **Tüm Milletlerin Kapısı** boss kapısı olarak. Ecbatana 7-renk halkaları = **LIT/Herodot kozmetiği**, ARCH gerçeği değil.

**7-katman fantazi:** (1) hendek/drenaj → (2) Kuh-e Raḥmat dağ perdesi → (3) kazamat hattı → (4) taş teras → (5) Tüm Ülkelerin Kapısı → (6) Apadāna kaidesi + köşe kuleleri → (7) taht salonu/*farr*. Opsiyonel skin: Ecbatana beyaz→altın mazgal renkleri.

| Alan | Kanca |
|---|---|
| **İmza kuleler** | Okçu platformları; *sparabara* pavise ileri karakolu; Ölümsüzler HQ'su (regen süsü — Yunan merceği olarak etiketli); Elma-taşıyıcı muhafız; tırpanlı-savaş arabası rampası (cam top); Kral Yolu istasyonu; qanat düğümü; Part-atışı/cataphract/nafta açılışları |
| **İmza düşmanlar** | Erken Lidya; orta Babil kentsel; Yunan hoplit + trireme; geç İskender'in tam kuşatması; Pers Kapısı ihanet bükümü; Part/Sasani kardeş çağlar olarak (klon-yapıştırma değil) |
| **Mit ultimate'ler** | **Ahura Mazda** kozmik lütuf; ***farr*** kral-aurası (Issus/Gaugamela vuruşlarında kırılır); Ölümsüzler "Ameretat yenilemesi"; Sasani ateş-tapınağı kaybetme-koşulu bükümü |
| **Yasak-varsayım tuzakları** | Ecbatana'nın 7 renginin kazıyla doğrulanması (doğrulanmadı); Ölümsüzler'in aşırı-güçlü altın-elma TO&E'sinin Pers öz-adı olması; 539'un sadece nehir-akıtması olması (Chronicle = Opis + giriş); kaynar yağın Achaemenid varsayılanı olması; tam Avesta panteonunun her tuğlaya damgalanması; Cyropaedia'nın düz tarih olması |
| **Kampanya action pitch'i** | **Act II:** Apadāna terasını tutan bir imparatorluk — okçuluk doktrini ve Kral Yolu lojistiği Yunan mızraklarına karşı, sonra Makedon kuşatma çağından sağ kal |

**Tek satır pitch:** Apadāna terası, Ölümsüzler ve *farr* aurası, Yunan mızraklarına ve İskender'in kuşatma trenine karşı.

---

### 8.4 Act 3 — Mısır

**Keep:** **Buhen** kalesi (en iyi çok-katmanlı askeri makine); **Karnak/Medinet Habu** kutsal marka skinleri olarak. Piramit arka plan anıtıdır, keep **değil**.

**7-katman fantazi:** Buhen ARCH istifi: (1) glacis + dış tepe → (2) dış çevre + yarım-daire tabyalar → (3) kaya hendeği → (4) hendek/caponier ateşi → (5) alt tuğla rampart + mazgallar → (6) kale perdesi → (7) kapı-kulesi + HQ çekirdeği (asma köprü/çok-kapı).

| Alan | Kanca |
|---|---|
| **İmza kuleler** | Mazgallı tabyalar; Nubian okçu karakolları; kompozit-yay bataryaları; savaş arabası platformları (Yeni Krallık); Medjay/Sherden elitleri; Mirgissa kızak nehir teknolojisi; Horus Yolları rölesi |
| **İmza düşmanlar** | Hyksos savaş arabası hücumu; Kenan koalisyonları; **Kadeş** istihbarat-eksilten boss'u; Deniz Kavimleri su şeridi; Assur/Pers geç kıyamet dalgaları; anti-Mısır güney faksiyonu olarak Kerma |
| **Mit ultimate'ler** | **Amun-Ra** Karnak küresel buff'ı; **Sekhmet** gazap AoE'si; **Maʿat** düzen ölçeri (kaos kaybettirir); **Uraeus** firavun pasifi; **Apophis** tutulma gece boss'u |
| **Yasak-varsayım tuzakları** | Piramidin kale olması; kaynar yağın firavun varsayılanı olması; Kadeş'in kesin bir Mısır galibiyeti olması; Pelusium "kediler" hikayesi; Pelusium çok-kule planının firavun-dönemi olması (sık sık Bizans'a ait); Buhen mancınık iddiaları; Yeni Krallık'ta demirin yaygın olması; Pharos'un Orta Krallık haritalarında olması |
| **Kampanya action pitch'i** | **Act III:** Nubian kale zincirini inşa edip tut — Nil şeritleri, savaş arabası açılışları ve Hyksos'tan Deniz Kavimleri'ne kadar Maʿat ölçeri |

**Tek satır pitch:** Buhen'in kaya-hendekli kale zinciri ve Nil şeritleri, Hyksos savaş arabalarına ve Deniz Kavimleri'ne karşı Maʿat'ı tutar.

---

### 8.5 Act 4 — Çin

**Keep:** **Weiyang Gong** (Han Chang'an ön salonu) saray kazan/kaybet olarak; askeri gramer = **wengcheng** + **mamian**. Büyük Duvar fener zinciri şebeke/roguelike haritalarıdır, keep değil.

**7-katman fantazi:** (1) *huchenghe* moat → (2) *yangmacheng* ön-iş → (3) *mamian* yanlama çıkıntıları → (4) *hangtu* perde → (5) **wengcheng** kavanoz-avlusu tuzağı → (6) *dilou*/*jiaolou* ok kuleleri → (7) saray/yamen/Mandate çekirdeği.

| Alan | Kanca |
|---|---|
| **İmza kuleler** | **Nu** tatar-yayı kulesi (imza DPS); mamian yanlama modülleri; ateş/yağ kapısı (döneme etiketli); wengcheng kompleksi; süvari karakolu; Mohist atölyesi; Song gunpowder/Ming fener açılışları |
| **İmza düşmanlar** | Erken savaş arabalı aristokrasi; Xiongnu süvarisi; Üç Krallık kahraman savaşları; bozkır konfederasyonları; geç Jurchen/Mançu; çift-kenarlı düşman araçları olarak sel/ateş |
| **Mit ultimate'ler** | **Tianming** (Mandate) ölçer kırılması/restorasyonu; sel kapağı ultimate'i (dostane ateş riski); **Guandi** sadakat ult'u (**FOLK/Romance**-etiketli); fener rölesi görüşü |
| **Yasak-varsayım tuzakları** | Tek Duvar'ın Qin→Ming olması; *Romance*'ın Üç Krallık tarihi olması; Zhuge Liang'ın tekrarlayan tatar yayını icat etmesi; pagodanın kale olması; kaynar yağın ana imza olması; erken açılışlarda olgun wengcheng; Mandate'in gerçek büyü olması; Qing'in birincil çağ olması |
| **Kampanya action pitch'i** | **Act IV:** Hangtu şehirleri ve kavanoz-kapılar — Savaşan Devletler'den bozkır baskısına kadar tatar-yayı doktrini ve Mandate politikası |

**Tek satır pitch:** Tatar yayları, mamian yanlaması ve wengcheng öldürme-kutuları, çatlayan bir Mandate altında Weiyang'ı savunur.

---

### 8.6 Act 5 — Maya–Mexica (ikiz civ, ikiz action iskeleti)

**Keep:** **Act I:** **Tikal I Tapınağı** markası + **Becán** askeri (kuru hendek + 7 kapı). **Act II:** **Huēyi Teōcalli (Templo Mayor)** ikiz türbeler + causeway/göl kontrolü.

**7-katman fantazi:** **Maya:** bajo → toprak işi → Becán kuru hendeği → eşmerkezli moloz → Mayapan-tarzı çevre → bileşikler → piramit redoubt'u. **Mexica:** Texcoco gölü → kaldırılabilir köprüler → kapı evleri → mahalle/kanal → kutsal platform → ikiz zirve (Tlaloc K / Huitzilopochtli G) — artı L0 su kemeri açığı.

| Alan | Kanca |
|---|---|
| **İmza kuleler** | Atlatl platformları; mızrak/obsidyen bataryası; Becán kapı boss'ları; kano/göl kuleleri (Mexica); çekme-köprü kontrolleri; calmecac/telpochcalli elit spawn binaları |
| **İmza düşmanlar** | Calakmul *Kaan* yıldız-savaş sezonları; Teotihuacan *entrada*'sı; Dos Pilas ihanet yayları; Tarascan'a karşı Mexica çiçek-savaşı elitleri; **1521** brigantinleri + yerli müttefikler (sadece "az sayıda İspanyol" değil) |
| **Mit ultimate'ler** | **Maya:** Chaak fırtınası, Kahraman İkizler yeraltı bükümü, yıldız-savaş takvim ult'u (opsiyonel Venüs kilidi geçişi). **Mexica:** Huitzilopochtli savaş güneşi, Tlaloc yağmur/sel şeridi, Tezcatlipoca kaos aynası — **ayrı panteonlar** |
| **Yasak-varsayım tuzakları** | **Maya = Aztek** (asla — iki farklı panteon, iki farklı 7-katman kale dilbilgisi); barışçıl Maya miti; Terminal Klasik'te "insanlar kayboldu"; Becán ıslak hendeği; Templo Mayor "60 m"; coatepantli'nin şehir Büyük Duvarı olması; her Klasik savaşçının macuahuitl taşıması; Kukulkan = Quetzalcoatl = Q'uq'umatz'ın tek kart olması; Klasik Maya'nın omurgası olarak yay; Cortés'in-Quetzalcoatl-olarak-görülmesi |
| **Kampanya action pitch'i** | **Act V:** Klasik Maya hegemonya savaşları (Tikal↔Calakmul) içinden Mexica göl-causeway savunmasına, 1521'in brigantine kıyametinde biterek |

**Tek satır pitch:** İkiz Mezoamerikan action'lar — Becán'ın kuru-hendekli piramitleri, sonra Tenochtitlan'ın çekme-köprülü göl savaşı.

---

### 8.7 Act 6 — Viking / Norse

**Keep:** **Askeri:** Trelleborg-tipi **ringfort** (4 kapı; Aggersborg maksimum ölçek). **İdeoloji:** **Jelling** höyükleri + Hristiyan taşı + hol (dönüşüm ölçeri).

**7-katman fantazi:** Deniz/fiyort yaklaşımı → kazık iniş reddi → kuru V-hendeği → çim-ahşap rampart → kardinal kapılar → çeyrek kışla sokakları → Jelling/hol ideoloji çekirdeği.

| Alan | Kanca |
|---|---|
| **İmza kuleler** | Kapı-tüneli öldürme hunileri; kazık alanları; uzunev ekonomi blokları; gemi-spawn iskeleleri; Danevirke doğrusal duvar haritaları; Paris köprü-kulesi **saldırgan** senaryoları; Alfred *burh* ayna kiti |
| **İmza düşmanlar** | Frank/Sakson tarla ordusu; Alfred burh şebekesi; doğu rotalarında Peçenek/bozkır; Büyük Ordu kışlama baskısı; kral-ölümünde ardıllık iç-savaş dalgaları |
| **Mit ultimate'ler** | **Þórr**/Mjǫllnir muhafızı (**ARCH** tılsımlar); **Óðinn** maliyetli öngörü; **Loki** etiketli edebi kit; **Ragnarök** kampanya finali geçişi; Bluetooth dönüşüm diplomasisi |
| **Yasak-varsayım tuzakları** | Boynuzlu miğferler; MCU/Wagner kozmolojisinin kült gerçeği olması; steroidli berserker varsayılan birim; "kanlı kartal"ın standart yasa olması; her geminin *dreki* TO&E'si olması; ringfort'ların Cnut İngiltere kışlası olması; Abbo'nun Paris sayılarının simülasyon gerçeği olması |
| **Kampanya action pitch'i** | **Act VI:** Baskın → kışlama → ringfort devlet yönetimi — dairesel arenalar ve Jelling meşruiyeti, Hristiyanlaştırma baskısı altında |

**Tek satır pitch:** Dört-kapılı ringfort'lar ve longship şeritleri — baskın yap, kışla, sonra Jelling'in dönüşüm taşını tut.

---

### 8.8 Act 7 — Antik Yunan

**Keep:** **Marka:** Atina **Akropolis'i** (Propylaia → tapınak ideolojisi). **Askeri sistem:** **Long Walls** koridoru + Piraeus/Munychia. Prolog: Mycenae/Tiryns Aslan Kapısı.

**7-katman fantazi:** Piraeus limanları → Long Walls sandviçi → şehir çevresi → kapılar (Dipylon vb.) → kentsel yumuşak doku → Propylaia → Parthenon/Erechtheion moral totemi.

| Alan | Kanca |
|---|---|
| **İmza kuleler** | Perde mural kuleleri (Messene vitrini); Propylaia kapı boss'u; Euryalos topçu/hendek çağı; trireme *diekplous* deniz katmanı; hoplit kalkan-duvarı blokerleri; peltast taciz; Makedon sarissa açılışı |
| **İmza düşmanlar** | Achaemenid çok-cepheli; Spartan/Theban rakip polisler; Sicilya felaketi; Filip/İskender meta-kırılması; Demetrios *helepolis*'i; Thermopylae-tarzı choke'larda ihanet yolu |
| **Mit ultimate'ler** | **Athena** şehir aurası (**sadece Atina için varsayılan**); **Zeus** yemin/ittifak kilidi + ateşkes duraklaması; Delphi'nin belirsiz kehanet kartı; Arşimet motor savunması (**ayna ölüm-ışını = FOLK geçişi**) |
| **Yasak-varsayım tuzakları** | Yunanistan'ın tek bir krallık olması; Athena'nın her polisi buff'laması; Homeros'un LBA arşivi olması; *300*/Percy Jackson kronolojisi; Sparta'nın duvarsız etosunun Atina'ya yapıştırılması; Beulé Kapısı'nın Perikles dönemi olması; Arşimet ısı-ışınının varsayılan gerçek olması |
| **Kampanya action pitch'i** | **Act VII:** Pers Savaşları'ndan Long Walls kaplumbağasına, sonra Makedon birleşik-silah yeniden yazımına kadar polis koalisyon TD'si |

**Tek satır pitch:** Akropolis ideolojisi ve Long Walls deniz-köprüsü — hoplitler, tiremler, sonra Makedon meta-kırılması.

---

### 8.9 Act 8 — Japonya

**Keep:** **Himeji-jō daitenshu'su** (~46.4 m) + *tenshu-gun* (3 *kotenshu*). Alternatifler: Azuchi kökeni, Osaka kuşatması action'ı, Matsumoto siyah skini.

**7-katman fantazi:** Jōkamachi + dış moat → **masugata**/koguchi tuzakları (+ umadashi) → sannomaru → ninomaru spirali → yagura + ishigaki → honmaru → tenshu-gun.

| Alan | Kanca |
|---|---|
| **İmza kuleler** | **Yagura** dağıtılmış DPS; masugata avlusu; ishigaki tırmanma-cehennemi; teppō mazgalları; ashigaru yari/yumi hatları; atakebune deniz coda'sı |
| **İmza düşmanlar** | Rakip daimyō koalisyonları; Ikkō-ikki; sōhei; Moğol 1274/1281 amfibi action'ları; Imjin Kore/Ming karşı-saldırısı; Sekigahara ihanet dalgaları; Osaka kışın "ateşkes" hendek-doldurması |
| **Mit ultimate'ler** | **Hachiman** savaş-koruyucu toplanması (kült, anime DPS'i değil); **Amaterasu** meşruiyet ışığı (ideoloji); kamikaze fırtınası **etiketli** hava olayı ve sonra FOLK hafızası olarak — ücretsiz kazanma aurası değil; Myōō tapınak buff'ları etiketli |
| **Yasak-varsayım tuzakları** | Zamansız Samuray İmparatorluğu yığını; katana'nın birincil savaş silahı olması; ninja kitle ordusu/shuriken meta'sı; Nagashino'nun kusursuz-dönen-yaylım miti; bushidō'nun ölüm-arayışının TO&E olması; ateşli silahların "onursuz" olması; oni/yōkai'nin tarihsel birim sınıfı olması; sakoku'nun kesin hermetik mühür olması |
| **Kampanya action pitch'i** | **Act VIII:** Genpei'den Sengoku'ya kadar dönem-kilitli açılışlar — beyaz balıkçıl keep'in etrafında masugata labirentleri ve teppō kazıkları |

**Tek satır pitch:** Masugata öldürme-kutuları ve yagura çapraz ateşi, teppō baskısı altında Himeji'nin beyaz tenshu'suna tırmanır.

---

### 8.10 Act 9 — Moğol

**Keep:** **Erken kimlik:** mobil **ordu** + mütevazı **Karakorum** (ideoloji > taş HP). **Geç dişler:** **Yuan Dadu** (~11-kapılı Çin imparatorluk grameri). En yüksek tavan genelde **saldırgan** kuşatma-kampı uzmanlığıdır.

**7-katman fantazi:** **Savunma (ordu/Karakorum hibriti):** remount sürüleri → ger halkaları → pazar/kapı şeritleri → saray/çadır HQ'su → quriltai meşruiyeti. **Saldırı (yerleşik keep'lere karşı):** keşif perdesi → sahte-ricat cebi → mühendis parkı → aç-bırakma zamanlayıcısı → ele geçirilmiş *huihui pao*/Çin mürettebatı → gedik → yağma/idare. Tek bir "Moğol taş kalesi"ne asla ezme.

| Alan | Kanca |
|---|---|
| **İmza kuleler** | Okçu-süvari kiting karakolları; remount kral ekonomisi; kuşatma-kampı atölyeleri; ele geçirilmiş mancınık/pao bataryaları; Yam/paiza lojistik düğümleri; Dadu kapı-şeridi kitabı (geç) |
| **İmza düşmanlar** | Jin Zhongdu açlık düşüşü; Xi Xia; Harzemşah; Avrupa eşmerkezli keep'leri; Japon kaleleri + fırtınalar; Memluk Ain Jalut; Ming 1368; han-ölümünde ardıllık krizi dalgaları |
| **Mit ultimate'ler** | **Tengri** mandate/alamet ölçeri (**yeni-Tengricilik değil**); ongon kamp muhafızları; şaman kriz temizliği (Hollywood şimşeği = FOLK); geç Yuan Budist öğretmen kalkanı; batı ulus İslami diplomasi bükümü |
| **Yasak-varsayım tuzakları** | Sonsuz düşüncesiz sürü; 1206'nın anlık kale-yiyicisi olması; tüm Moğolların sonsuza dek zırhsız olması; Gizli Tarih'in bir GPS günlüğü olması; Japonya'nın sadece kamikaze'ye kaybetmesi; yeni-Tengriciliğin 13. yy dogması olması; Bağdat 1258'in ebedi Moğol-Müslüman kozmik savaşı olması; Kubilay Yuan'ının "Çin basitçe Moğolistan oldu" olması |
| **Kampanya action pitch'i** | **Act IX:** Ordu hareketliliğinden Avrasya kuşatma ustalığına — sonra ardıllık ve Ming baskısı ulusu çatlatırken Dadu'yu savun |

**Tek satır pitch:** Ordu ruhu ve kuşatma-kampı dişleri — dünyanın kale'lerine karşı sahte ricatlar ve ödünç alınmış motorlar.

---

### 8.11 Act 10 — Avrupa Kalesi (~1000–1500)

**Keep:** **Diyagram:** **Beaumaris** eşmerkezli arenası (ofsetli kapılar; bitmemiş yükseklik kampanya durumu olarak). **Efsane:** **Krak des Chevaliers**. Köprü düğümü: **Château Gaillard**. Erken: motte → White Tower donjon'u.

**7-katman fantazi:** Dış bailey/moat → kapı evi/barbican → dış perde → listeler → iç perde (+ talus) → keep/donjon → şapel/lord holü ideolojisi.

| Alan | Kanca |
|---|---|
| **İmza kuleler** | Çift kapı evleri + portcullis yığınları; mural D-kuleler; hoarding (yanabilir geçici upgrade); machicolation (Gaillard açılışı); varsayılan tatar yayı; İngiliz uzun-yayı uzmanlığı (bölgesel, tüm-Avrupa'ya bir gecede değil); öldürme delikleri |
| **İmza düşmanlar** | Rakip Hristiyan politikalar; Galli/İskoç sınırı; Outremer Müslüman karşı-kuşatmaları (Selahaddin/Baybars); Yüzyıl Savaşları kuşatma ekonomisi; gunpowder coda'sı (yavaş eğri); ihanet/kazı ekipleri |
| **Mit ultimate'ler** | Emanet ahlaki aurası; Oriflamme/aziz-sancağı toplanması; şapel regen çapı; papalık endüljansı yabancı askeri (yurt içi siyasi spawn tepkisi); Arthur/Grail = **sadece etiketli FOLK skini** — kutsal lazer yok |
| **Yasak-varsayım tuzakları** | Kaynar yağın varsayılan olması; 11. yy köylüsünün tam plate zırhı; garnizonun tarla ordusu olması; tek bir "Ortaçağ Avrupa kralı" yığını; Krak'ın eşmerkezli olgunluğunun 1142'ye tarihlendirilmesi; uzun-yayının her şeyi sonsuza dek kazanması; Excalibur/melek topçusu; Disney/Viktorya silüetinin kronoloji olması |
| **Kampanya action pitch'i** | **Act X finali:** Motte fethinden Haçlı laboratuvarına (Krak), Capet–Plantagenet taş yarışına (Gaillard), Edward demir halkasına (Beaumaris), Yüzyıl Savaşları kuşatma ekonomisine, gunpowder alacakaranlığına |

**Tek satır pitch:** Duvarlar içinde duvarlar — Beaumaris diyagramları ve Krak efsanesi, kapı evi öldürme delikleri ve gunpowder alacakaranlığı altında.

---

### 8.12 Kaynak haritası (civ → araştırma dosyaları)

| Civ | Birincil Türkçe usta | İngilizce yardımcılar |
|---|---|---|
| Babylon | `GDD-BABYLON-ACT0.md` (bu doküman havuzunda dedike) | Kamuya açık ARCH (Koldewey/Etemenanki; Imgur-Enlil; İştar Kapısı) |
| Roma | `ROMAN-TOWER-DEFENCE-TARIHSEL-ARASTIRMA.md` | (pakette sadece TR) |
| Persia | `PERSIAN-TOWER-DEFENCE-TARIHSEL-ARASTIRMA.md` + `PERS-IMPARATORLUGU-…MASTER…` | `ACHAEMENID-…`, `PERSIAN-IMPERIAL-…` |
| Mısır | `EGYPTIAN-TOWER-DEFENCE-TARIHSEL-ARASTIRMA.md` | `ANCIENT-EGYPTIAN-…`, `EGYPTIAN-*-RESEARCH.md` |
| Çin | `CHINESE-TOWER-DEFENCE-TARIHSEL-ARASTIRMA.md` | `ANCIENT-CHINESE-…`, `CHINESE-IMPERIAL-…` |
| Maya–Mexica | `MAYA-AZTEC-TOWER-DEFENCE-TARIHSEL-ARASTIRMA.md` | Maya/Aztek birim + araştırma föyleri |
| Viking | `VIKING-TOWER-DEFENCE-TARIHSEL-ARASTIRMA.md` | Tahkimat + birim + araştırma |
| Yunanistan | `ANCIENT-GREEK-TOWER-DEFENCE-TARIHSEL-ARASTIRMA.md` | Tahkimat + birim + araştırma |
| Japonya | `JAPANESE-TOWER-DEFENCE-TARIHSEL-ARASTIRMA.md` | Kaleler + birim + araştırma |
| Moğol | `MONGOL-TOWER-DEFENCE-TARIHSEL-ARASTIRMA.md` | Kamplar + birim + araştırma |
| Avrupa Kalesi | `EUROPEAN-CASTLE-TOWER-DEFENCE-TARIHSEL-ARASTIRMA.md` | Kaleler + birim + araştırma |

---

## 9. Meta progresyon, Codex, zorluk modları

*(Kaynak: `GDD-LIVEOPS-FTUE-META.md` §2, `GDD-WAVES-ECONOMY.md` §6, `GDD-ATTACK-WAVES.md` §6–§7)*

### 9.1 Meta katmanları — tek tablo

| Katman | Ne | Bağımsız mı? |
|---|---|---|
| **Account Level (Hesap Seviyesi)** | Küresel ilerleme: kozmetikler, museum kapasitesi, sosyal açılışlar, yumuşak güç tavanları | Kampanya ilerlemesinden bağımsız |
| **Civilization Mastery (Medeniyet Ustalığı)** | Civ başına I–X track'i | Account Level'dan bağımsız |
| **Museum / Codex** | Açılan eserler/keep'ler/birim diyoramalarının 3D/2.5D hub'ı | Play + Read + Quiz + Exhibit döngüsüyle beslenir |
| **Currencies (Para birimleri)** | Gold (run-only) · Legacy Seals (kalıcı) · Season Tokens (sezonluk) · Clan Marks (sosyal) | Her biri tek bir role bağlı |
| **Zorluk modları** | Story/Normal/Hard/Legend/Historical/Myth (bkz. §7.8) + Heroic/Iron madalyaları | Kampanya ilerlemesinin üstüne biner |

### 9.2 Account XP & seviyeler

**Account Level** = küresel ilerleme (kozmetikler, museum kapasitesi, sosyal açılışlar, yumuşak güç tavanları). XP kaynakları: misyon temizleme, günlük/haftalık görevler, sezon pass track'i, co-op/async katılımı, **Codex çalışması** (okuma + opsiyonel quiz). Eğri: L10'a hızlı (habit), sonra yavaşlayan; prestij Yıl-1'de **zorunlu değil**.

| Yumuşak kapı (örnekler) | Açılan |
|---|---|
| L3 | İkinci civ deneme açılışı |
| L5 | Lonca'ya (Clan) katılım |
| L8 | Async Raid |
| L12 | Co-op Boss kuyruğu |
| L15 | Clan War lite |

**Kural:** Civ *sahipliğini* kilitleyen ödeyerek-atlama hesap seviyesi yok; battle pass sezon tavanı içinde XP'yi hızlandırabilir.

### 9.3 Medeniyet ustalığı (Civilization Mastery)

Her civ, Account Level'dan bağımsız bir **Mastery track'ine** (I–X) sahiptir.

| Mastery | Açılan örnekler |
|---|---|
| I | Başlangıç temizleme / FTUE |
| II–III | Alternatif kule skinleri, ses paketi |
| IV–V | Elit birim planı; tarihsel loadout ön ayarı |
| VI–VII | Keep "restorasyon" görsel katmanı; o civ için Museum kanadı |
| VIII–IX | Ranked/Arcade için imza modifikatör; unvan |
| X | Museum'da Mastery heykeli + sezon portre çerçevesi |

Mastery'ye XP: o civ'le kazanılan zaferler, o civ'e etiketli challenge'lar, sezon öne-çıkarma bonusları (sezon civ'le eşleştiğinde +%25). **Eğitim yan-USP'si:** Mastery IV+ **açıklamalı diyagramlar** açabilir (castrum katmanları, wengcheng, ringfort) — Arcade oyuncuları bunları kozmetik alır, EDU oyuncuları Museum tamamlama %'sine quiz/kredi olarak alır.

### 9.4 Museum / Codex (eğitim yan-USP'si)

**Museum** = açılan eserlerin, keep'lerin ve birim diyoramalarının 3D/2.5D hub'ı. **Codex** = Museum'u besleyen kart/veritabanı katmanı.

**Açılış döngüsü:** (1) **Oyna** → Codex parçası düşür (misyon ilk-temizlemesi garantili; tekrarlar RNG + pity ile). (2) **Oku** → kartı "çalışıldı" işaretle (+Account XP). (3) **Quiz** (opsiyonel, EDU'da varsayılan açık) → pin/XP/mastery kırıntısı. (4) **Sergile** → eseri bir Museum slotuna yerleştir (kapasite Account Level'la ölçeklenir).

**Kart taksonomisi:** Keep/Tahkimat · Birim/Asker · Taktik/Doktrin · Mit/Efsane (her zaman etiketli) · Site/Kampanya (sezonluk).

**Yumuşak USP mesajlaşması:** "Savunduğun medeniyetlerin bir müzesini kur." — "tarih ödevi" değil, **koleksiyon + kimlik**. Ortaklık potansiyeli: müze bağışçılığı kozmetikleri, sınıf EDU playlist'i (reklamsız).

**Doğruluk etiketleri (her iki modda zorunlu):** Her Codex kartı, birim tooltip'i ve sezon düğümü şunlardan birini taşır:

| Etiket | Anlam |
|---|---|
| **Documented (Belgelenmiş)** | Araştırma kitaplarında kullanılan birincil/ikincil tarihsel veya arkeolojik konsensüse dayalı |
| **Interpreted (Yorumlanmış)** | Oynanış için makul rekonstrüksiyon/kompozit (örn. "7-katman savunma" pedagojisi) |
| **Mythic (Mitik)** | Folklor, destan veya icat edilmiş fantazi — eğlence, gerçek değil |
| **Anachronistic (Anakronistik)** | Kasıtlı çağlar-arası karışım, etkinlik için (nadir ve işaretli olmalı) |

**UI:** renk-körlüğü güvenli ikon + metin; herhangi bir birime uzun-basma → etiket + 1 satır "Neden?". **Mağaza etiği:** Mitik içerik asla gerçek tarih olarak pazarlanmaz; EDU mod mağaza filtreleri Mitik IAP'leri saklayabilir.

### 9.5 Currencies (meta-yüzü)

| Para birimi | Kazanım | Emici (sink) |
|---|---|---|
| **Gold** | Misyonlar | Koşu-içi upgrade'ler (sadece o koşuda) |
| **Legacy Seals (Kalıcı Mühürler)** | Hesap/meta | Kalıcı kule rütbeleri, Museum slotları |
| **Season Tokens (Sezon Jetonları)** | Sezon track'i/etkinlikler | Sezon kozmetikleri, öne-çıkan civ boost'ları |
| **Clan Marks (Lonca Nişanları)** | Sosyal modlar | Lonca kozmetikleri, savaş sancakları |

FTUE'de dördüncü bir premium soft-para karışıklığından kaçın; Season Tokens'ı ilk sezon kapısında tanıt.

### 9.6 Eğitim (Educational) vs Arcade modu — meta ile kesişim

Ayar konumu: Ayarlar + FTUE M5 + Museum başlığı. Kapsam: hesap varsayılanı; oturum başına/parti host'u başına geçersiz kılınabilir. HUD rozeti her zaman görünür (`EDU`/`ARC`).

| Sistem | Educational | Arcade |
|---|---|---|
| Mit/efsanevi birimler | Kapalı veya Museum'da **sadece-göster**; loadout'ta değil | Açık, dengeli |
| Düşman isimleri | Belgelenmiş asker tiplerini tercih et | Fantazi isimler OK |
| Misyon metinleri | Kısa + kaynak-katmanı etiketi | Önce lezzet |
| Quiz'ler | Opt-out mevcut; varsayılan nazik prompt | Museum açılmadıkça gizli |
| Co-op boss telegrafları | Yorumlanmış taktikleri tercih et | Tam gösteri |
| Monetizasyon | Aynı kozmetikler; EDU paketleri Codex/Museum'a yaslanır | Aynı; mit skinleri vurgulanır |

### 9.7 Zorluk modları ve madalyalar (meta katmanı)

§7.8'deki altı mod (Story/Normal/Hard/Legend/Historical/Myth) **misyon zorluğunu** tanımlar; bu bölümdeki katman onun **üstüne biner**:

- **Heroic madalyası:** Zırh/HP artışıyla temizleme, standart araç setiyle.
- **Iron madalyası:** Satış yok, sınırlı retry, mükemmele yakın performans.
- Her ikisi de **Codex mühürlerine** dönüşür (§4.7), Museum tamamlama %'sini besler.
- **Anti-P2W kilidi:** Hard mode ve üstü, meta bonuslarının çoğunu devre dışı bırakır veya yumuşak-tavana çeker (Mastery pasiflerinin bir kısmı hariç, kozmetik/QoL sınırlı) — böylece "zor mod" gerçekten skill testi olarak kalır, cüzdan testi olmaz.

### 9.8 Meta ilerleme vs harita ilerlemesi (neden ikisi de gerekli)

| Eksen | Harita (stage) ilerlemesi | Meta ilerlemesi |
|---|---|---|
| **Ne olduğu** | Kampanya haritası, yıldızlar, action'lar | Hesap seviyesi, kule ustalığı, civ teknoloji ağacı, anıtlar, battle pass |
| **D1–D7'yi kazanan** | Evet — harita yeniliği | Yumuşak — slot/ikinci civ açılışı |
| **D30+'ı kazanan** | Sadece endless/modifikatörlerle | **Birincil** |
| **Risk** | İçerik uçurumu | Güç şişmesi/P2W algısı |

**Önerilen bölünme:** Haritalar sistemleri öğretir ve fantaziyi satar. Meta uzun ömürlülüğü satar: herhangi bir moddan gelen ustalık XP'si, +%50 ham hasar değil **taktik** açan civ teknoloji düğümleri, anıt kozmetikleri. Battle pass her ikisini de takip eder: harita yıldızları + meta XP. **Güç kuralı:** yeni içerik > yeni çarpanlar. Düz ATK şişmesi yerine yeni düşman modifikatörlerini, harita kurallarını ve kule etkileşimlerini tercih et.

---

## 10. FTUE / onboarding (ilk 10 dakika saniye saniye)

*(Kaynak: `GDD-LIVEOPS-FTUE-META.md` §1, `GDD-BABYLON-ACT0.md` §4 çapraz referans)*

### 10.0 İki kaynağın uzlaştırılması (tasarım kararı)

Araştırma tabanında iki FTUE script'i var: `GDD-BABYLON-ACT0.md` Babylon'un **tam** Act 0'ını (B0-M1→M5, ~25–30 dk) saniye saniye anlatır; `GDD-LIVEOPS-FTUE-META.md` ise **hesap düzeyinde** ilk-10-dakika akışını (soğuk açılış → civ seçimi → mod geçişi → graduation → meta sundurma) tanımlar. Bu GDD bu ikisini şöyle uzlaştırır: **§0.4 madde 9 hard rule'u** ("Babylon Act 0 = tutorial") nedeniyle, ilk oturumun **ilk 3.5 dakikası** literal olarak Babylon'un **B0-M1 (Kanal Kenarı)** haritasıdır — bu, aşağıdaki M0–M2 beat'lerinin içeriğidir. **M4'te (5:30)** oyuncu, Act 0'ın *hikaye/marka* Babylon'da kalmasına rağmen, Act 1'den itibaren hangi medeniyetle devam edeceğini seçer (Roma/Mısır/Persia lansman havuzundan — bkz. §0.2 ve §17). Böylece Babylon her oyuncu için paylaşılan dilbilgi öğretmeni kalır; civ seçimi asla o paylaşılan öğretmeni değiştirmez, sadece bir sonraki action'ı belirler. Babylon'un tam B0-M2→M5 haritaları (§8.1.3), ilk oturumdan sonra "Act 0'ı bitir" olarak devam eder.

### 10.1 Hedefler (ölçülebilir)

| Dakika | Oyuncu ne yapmalı |
|---|---|
| 0–1 | "Kutsal keep'i savun" fantazisini anla + bir kule yerleştir |
| 1–3 | Dalga 1–2'yi temizle; yolu, satış/upgrade'i, bir düşman tipini öğren |
| 3–6 | İkinci kule tipini aç; tutorial misyonunu bitir; ilk **Codex** kartını gör |
| 6–8 | **Başlangıç civ'ini** seç (Roma veya Babylon yumuşak-önerilir); Educational ↔ Arcade geçişini bir kez gör |
| 8–10 | "Graduation" skirmish'ini tamamla; Hesap XP'si + ilk Museum slotu kazan; günlük giriş/push izni için yumuşak prompt |

**Sert kurallar:** Dakika 10'dan önce IAP duvarı yok; FTUE'de reklam yok; ilk savaş vuruşundan sonra metin atlanabilir; ölüm = bir ücretsiz ipucuyla retry, paywall değil.

### 10.2 Script anlatımı (vuruş-vuruş)

#### M0 — Soğuk açılış (0:00–0:45)

**Görsel:** Kuşatma altında tam-ekran keep (Babylon ziggurat silüeti — başlangıç civ'i seçildikten sonra sanat başlangıç civ'ine göre değişir, ama Act 0 marka anı her zaman Babylon'dur). **Ses/metin (1 satır):** "Bir şehir duruyor. Kalbi tut." **Aksiyon:** Vurgulanmış pad'e **Bow Gallery/Watchtower** yerleştirmek için dokun. Dalga 1 otomatik başlar. **Atla:** "Girişi atla" 3s sonra belirir (aynı yerleştirme prompt'una döner).

#### M1 — İlk kan (0:45–2:00)

Dalga 1: 8–12 hafif piyade, tek şerit. Öğretim: **öldürme başına gold**, **keep HP'si**, **dalga zamanlayıcısı**. Güvenlik ağı: keep HP <%50 ise ücretsiz ikinci kuleyi ghost-place et. Kapanış kartı: *"Kule hasarı yolu durdurur."* — lore dökümü yok.

#### M2 — Upgrade & choke (2:00–3:30)

Dalga 2: karışım + bir **kalkanlı/zırhlı** birim. Öğretim: bir kuleyi bir kez upgrade et; opsiyonel **satış** %70 geri ödemeyle (sayıyı göster). Mikro-ödül: **Hesap XP +15**, "Çırak Savunmacı" toast'ı.

#### M3 — İkinci kule & hero dozu (3:30–5:30)

**Barricade/Spike** veya civ-lezzetli tuzağı (Roma *vallum*, Babylon kanal kapısı — FTUE'de sadece kozmetik) aç. İkinci tipi yerleştir; Dalga 3 mini-boss'unu temizle (adlı ama *henüz* tarihsel bir figür değil — örn. "Akıncı Kaptan"). **Codex açılışı #1:** "Keep nedir?" — 40 kelimelik düz dil + **Documented** doğruluk rozeti. Yumuşak USP vuruşu: museum kapı ikonu animasyonlanır; henüz Museum UI'sını zorlama.

#### M4 — Civ seçimi (5:30–7:00)

Ekran: **Medeniyetini seç** — 3 öne çıkan (Babylon, Roma, Mısır) + kilitli civ'ler için "Sonra daha fazlası" karuseli hayaletleri. Her kart: keep sanatı, 1 imza kule, 1 imza birim, 1-cümle kanca. Varsayılan vurgu: Roma (geniş aşinalık) veya Babylon (marka miti) A/B testi. Onayda: civ skinini yükle; 8s "geliş" hareketi oynat (keep yükselir/sancak açılır).

#### M5 — Mod geçişi ifşası (7:00–7:45)

Modal (bir kez): **Tarihi nasıl istersin?** — **Educational** (doğruluk etiketleri açık; mit birimleri kapalı veya açıkça etiketli; Codex quiz'leri opsiyonel) veya **Arcade** (mit/hero birimleri açık; denge-öncelikli; etiketler hover/uzun-basmada hâlâ görünür). Varsayılan: D0 dönüşümü için **Arcade**; Educational bir dokunuş ötede. Tercih hesapta saklanır. Metin: "Bunu istediğin zaman Ayarlar'dan değiştirebilirsin."

#### M6 — Graduation skirmish'i (7:45–9:30)

Misyon: **"İlk Nöbet"** — 5 dalga, 2 şerit, habercili bir uçan veya kuşatma dozu. Misyon-ortası ipucu: hero yetenek butonu bir ücretsiz atım için açılır (civ başlangıç hero'su). Kazan → **Hesap Seviye 2**, **Civ Mastery I (başlangıç)**, **Museum Slotu: Keep Modeli** + o keep için Codex girişi. Kaybet → önerilen build'le retry; XP cezası yok.

#### M7 — Meta sundurma (9:30–10:00)

Hub ifşası (tek kompozisyon): Keep diyoraması, **Oyna**, **Museum**, **Sezon** (kilitli teaser), **Lonca** (kilitli teaser). Yumuşak prompt'lar (birini seç, hepsini değil): (1) Günlük görev: "1 savaş kazan" (+ streak tohumu). (2) Push izni (OS) — ödül ekranından sonra, asla savaş sırasında değil. (3) Opsiyonel kısa quiz (sadece Educational): Codex #1'den 1 soru — ödül kozmetik pin, atlanabilir. Bitiş durumu: oyuncu Misyon 2'yi veya menüye dönmeyi seçebilir. FTUE tamamlanmış olarak işaretlenir → analitik `ftue_complete_10m`.

### 10.3 FTUE analitik kancaları

`ftue_place_first_tower` · `ftue_wave1_clear` · `ftue_civ_select` · `ftue_mode_select` · `ftue_grad_win` / `ftue_grad_lose` · `ftue_museum_open` · `ftue_complete_10m` · her vuruşa kadar geçen süre.

### 10.4 Düşüş (drop-off) azaltımları

| Risk | Düzeltme |
|---|---|
| Lore duvarları | Museum'a kadar en fazla 1 cümle; Codex çekme (pull), itme (push) değil |
| Karmaşıklık | Seviye 3'e kadar kule tipini 2'ye sınırla |
| Mod karışıklığı | HUD'da kalıcı küçük rozet: EDU/ARC |
| Civ pişmanlığı | Hesap Seviye 5'ten önce bir ücretsiz civ respec'i |

### 10.5 Erişilebilirlik FTUE (Babylon Act 0'dan genellenmiş, `GDD-BABYLON-ACT0.md` §4.3)

- Renk-körlüğü ayırt edici kule silüetleri (yay/mızrak/sapan/kapı).
- Hız 1× varsayılan; 2× M3 temizlemesinden sonra açılır.
- Tek-el alt inşa çubuğu.
- Disleksi fontu = daha sonraki ayarlar, Codex altbilgisinde bir kez bahsedilir.

### 10.6 Act 0 anti-pattern'leri (FTUE'ye özel)

1. 8-para dökümü yok.
2. İlk kuleden önce Enuma Elish denemesi yok.
3. Tutorial'da kaynar-yağ "imza" açılışı yok.
4. Zorunlu Mitik mušḫuššu lazeri yok.
5. Retry'de enerji kapısı yok.
6. **Geri dönen oyuncu:** "Komutan geri döndü" → Act 0 savaşını atla, Codex hediyelerini koru, Act 1'e loadout yardımıyla düş (`GDD-LIVEOPS-FTUE-META.md` §1.4).

---

## 11. Retention, habit, viral, bildirimler (etik)

*(Kaynak: `GDD-RETENTION-VIRAL.md` — tam dosya)*

### 11.1 Yönetici hükmü

Tarihsel-civ TD'de retention "daha fazla giriş takvimi" değildir. Şudur:

1. **Net bir ilk-oturum fantazisi** (*senin* civ'inin imza araçlarıyla *senin* şehrini savun).
2. **Bitmemiş bir istekle sona eren günlük döngü** ("bir dalga daha" / eksik koleksiyon / lonca görevi).
3. **Sosyal yükümlülük ve FOMO yaratan ama hayatı cezalandırmayan haftalık döngü**.
4. **Stage temizlemesinin ötesinde yaşayan meta ilerleme** — D30+ oyuncuların hâlâ hedefleri olsun.
5. **Sadece güç şişmesi değil, civ lezzetini döndüren live-ops**.

### 11.2 Hedef bantlar (yumuşak lansman → ölçek)

| Ufuk | Taban (öldür/UA'yı düşün) | Sağlıklı (strateji midcore) | Gerilme (üst TD/hobbyist) |
|---|---:|---:|---:|
| **D1** | <%22 | %28–35 | %38–45 |
| **D7** | <%5 | %10–15 | %15–22 |
| **D30** | <%2 | %4–7 | %8–12 |
| **D7/D1** | <0.25 | 0.35–0.45 | ≥0.45 |
| **D30/D7** | <0.30 | 0.40–0.55 | ≥0.55 |

**Not:** Efsanevi "40/20/10" hedefi medyan gerçeklik değil, elit bir folklordür (GameAnalytics 2025 dağılımı: medyan D1 ~%22, D7 %3.4–3.9, D30 ~%0.7–0.8). Strateji/TD türü match-3'ten daha yavaş başlar — bu yüzden oturum-1 netliği ve gün-0 "bu civ'i anlıyorum" hissi kritik. Oranlar mutlak vanity sayılarından daha önemlidir: güçlü D1 + çöken D7 = FTUE şekeri, zayıf çekirdek döngü demektir.

### 11.3 Habit bilimi — Hook modeli

| Adım | TD karşılığı | Tarihsel-civ lezzeti |
|---|---|---|
| **Tetikleyici (Trigger)** | Push/ikon rozeti/streak riski/lonca ping'i | "Duvarına gece baskını", "[Civ] Festivali 6 saat içinde bitiyor" |
| **Aksiyon** | 1–3 dalga temizle/görev talep et/kule konuşlandır | *Balista/phalanx/kaplumbağa gemisi* yerleştir — kimlik eylemi |
| **Değişken ödül** | Sandık RNG'si, görev sandığı, etkinlik mağaza atışı, hero parçası | Kayıp kütüphane emaneti, çağ planı, efsanevi komutan |
| **Yatırım** | Yükseltilmiş kuleler, temizlenen stage'ler, streak, lonca rütbesi, koleksiyon % | "Benim Roma'm duvarı", civ ustalık yıldızları |

### 11.4 "Bir dalga daha" — oturum uzatıcı

TD'nin doğal dopamini **dalga netliğidir**: ayrık kazan/kaybet birimleri bir stage içinde. **Tasarım kuralları:** Ortalama stage'i **6–12 dakikaya** sınırla. Temizlemeden sonra 1 dokunuşta **Anında Retry/Sıradaki Dalga Challenge'ı/Elit modifikatör** sun (lobi labirenti yok). Dalga-sonu **kısmi ödüller** (hurda, civ lütfu) ver, böylece stage ortasında bırakma bile verimli hissettirsin. **Opsiyonel endless/survival eki** kampanya temizlemesinden sonra yüksek-skilli oyuncular için. "Bir tur daha"yı asla D0–2'de enerjinin arkasına yumuşak-kilitleme.

**Anti-pattern:** Dalgalar arasında zorunlu tam lobi yeniden yüklemesi — döngüyü öldürür.

### 11.5 Yatırım & sunk cost (etik)

Oyuncular şunlara sahip olduklarında kalırlar: (1) **Düzen hafızası** (benim choke noktalarım), (2) **İsimli hero/komutanlar**, (3) **Civ ustalığı**, (4) **Lonca reputasyonu**, (5) **Kozmetikler/anıtlar**. Kaçınılacak: bir günü kaçırmanın uninstall'a sebep olacak kadar sert ilerleme sildiği streak sistemleri. **Grace günleri**, **dondurma jetonları** veya **kümülatif takvimler** tercih edilir.

### 11.6 Günlük sistemler

| Desen | Artı | Eksi | Öneri |
|---|---|---|---|
| **7-günlük tırmanan streak** | Güçlü D1–D7 habit'i | Kazuelleri cezalandırır; kırılmada churn | Sadece yumuşak versiyon + dondurma |
| **Kümülatif aylık takvim** | Adil; Genshin-benzeri | Daha zayıf FOMO | **Birincil** yumuşak lansman için |
| **Kilometre taşı girişi (Gün 1/3/7/14/30)** | Grind'siz onboarding | Daha az günlük çekiş | Takvimle eşleştir |
| **Premium günlük track ($4–6/ay)** | Kararlı ARPU | Güven gerektirir | D7 ürün-pazar uyumundan sonra ekle |

**Ödül karışımı (ücretsiz track):** soft para, kule XP'si, 1× plan parçası, kozmetik toz, ara sıra düşük oranlı nadir hero parçası. **Asla:** çekirdek bir kuleyi elde etmenin *tek* yolunu Gün-28 uçurumuna bağlama.

| Streak uzunluğu | Ödül fikri | Güvenlik |
|---|---|---|
| 3 gün | Günlük hurda +%5 | — |
| 7 gün | Anıt skini parçası | Haftada 1 ücretsiz dondurma |
| 14 gün | Unvan + lonca katkı bonusu | Grace: 1 gün kaçırma streak'i korur |
| 30 gün | Benzersiz duvar sancağı | 7'ye sıfırla, 0'a değil |

### 11.7 Görevler (günlük/haftalık)

**Günlük görev seti (3–5, ~20–30 dk'da tamamlanır):** (1) **2 stage** veya **1 zor stage** temizle. (2) Herhangi bir kuleyi **bir kez** yükselt VEYA X hurda harca. (3) **N zırhlı/uçan/kuşatma** düşmanı yen (karşı-oyunu öğretir). (4) **1 etkinlik jetonu** topla (live-ops'a bağlar). (5) Opsiyonel: **Lonca yardımı** (bağışla/paylaş). **Haftalık görevler (4–6):** bir boss action'ını temizle; herhangi bir bölgede X yıldıza ulaş; bir kule hattını tam N kademeye yükselt; sınırlı bir etkinliğe bir kez katıl; lonca kuşatmasına/paylaşılan duvara Y katkıda bulun.

### 11.8 Koleksiyon döngüleri (kule/hero/civ)

Stage haritası "bittiğinde" D30+'ın pili koleksiyondur.

| Katman | Ne toplanır | Kadans |
|---|---|---|
| **Kuleler** | Arketipler + civ skinleri + ustalık yıldızları | Sürekli |
| **Hero/komutanlar** | Roller: buffer, nişancı, duvar, AoE | Haftalık banner/etkinlik |
| **Medeniyetler** | Açılış + ustalık yolu + benzersiz anıt | Yumuşak lansman: 2–3; ölçekte tam roster |
| **Emanet/planlar** | Pasif bonuslar + kozmetikler | Etkinlik mağazaları |
| **Codex girdileri** | Temizlemelerden lore kartları | Tarih meraklıları için pasif retention |

**Pity/adalet:** Öne çıkan komutan parçalarında sabit pity; kopya → evrensel parça. **% tamamlama'yı göster:** "Roma ustalığı %42" ana ekranda ham gold'dan daha güçlü bir dönüş kancasıdır.

### 11.9 Limitli etkinlikler ve live-ops psikolojisi

| Etkinlik | Süre | Amaç | Örnek |
|---|---|---|---|
| **Civ spotlight'ı** | 7–14g | Fantazi + koleksiyon döndür | "Pers Kral Yolu Savunması" |
| **Kuşatma hafta sonu** | 48–72s | DAU zıplat | Lonca paylaşımlı boss HP'si |
| **Roguelike kule koşusu** | Her zaman-açık haftalık seed | Skill ifadesi | Rastgele plan taslağı |
| **Boss rush** | 7g | Midcore derinlik | Çok-fazlı savaş fili/kuşatma kulesi |
| **Giriş festivali** | 7–10g | Yumuşak yeniden-katılım | Büyük düşüşten önce takvim |
| **Kozmetik anıt yarışı** | 14g | Balina + sosyal flex | Lonca sıralaması → şehir skini |

**Zulümsüz FOMO:** Kalan zamanı **açıkça göster**. Geri dönen oyuncular için **yakalama jetonları** izin ver (48–72s uzakta). Premiyer sonrası güçlü etkinlik modlarını **dönen challenge playlist'ine** arşivle. Oyuncu-kazanılmış kozmetikleri asla silme.

### 11.10 Bildirim stratejisi (spam'siz)

**İlkeler:** Push niyeti güçlendirir, aşkı yaratmaz. İzin **değerden sonra** iste (ilk stage temizlemesi/ilk günlük sandık), asla soğuk kurulumda. **Bağlamsal**'ı yayına tercih et (açılma oranları genelde ~3× daha yüksek). Küresel tavan: çoğu kullanıcı için **≤1/gün**; pazarlama **≤4/hafta**; sosyal ayrı ama kullanıcı-geçişli olabilir. Sessiz saatler + kategori geçişleri ayarlarda. Oyuncu art arda 3 push'u görmezden gelirse → **otomatik-yavaşlatma** 7 gün.

| Öncelik | Örnek | Sıklık |
|---|---|---|
| **P0 Sosyal** | Lonca kuşatması başlıyor/yardım istendi | Olay-güdümlü |
| **P1 Streak/talep** | "Günlük sandık hazır"/"Dondurma mevcut — streak güvende" | Günde en fazla 1× |
| **P2 Live-ops** | "Pers etkinliği 6 saat içinde bitiyor — 2 görev kaldı" | Sadece tamamlanmamışsa |
| **P3 Yeniden-katılım** | 3–7g durgunluktan sonra "Yeni civ ustalık bölümü açıldı" | Haftada 2× tavan |
| **P4 Promo** | Battle pass bitiyor | Nadir; asla Gün 0–2 |

**Kopya tonu:** Genel yerine spesifik ("Balista ustalığın III'e 1 yıldız uzakta" > "Geri gel!"). Suçluluk yok: asla "şehrini terk ettin." Oynama penceresine yerelleştirilmiş zamanlama (oturum geçmişinden öğrenilir).

### 11.11 Churn nedenleri (TD/strateji mobilleri)

| Churn anı | Sebep | Düzeltme |
|---|---|---|
| **Dakika 0–5** | Tutorial duvarı; belirsiz fantazi | Oynayarak öğret: 2 kule yerleştir, 1 dalga kazan |
| **Gün 0 akşamı** | Geri dönme sebebi yok | Yumuşak günlük + bitmemiş dalga/sandık zamanlayıcısı |
| **Gün 2–3** | Zorluk sıçraması/adaletsiz sızma | Dinamik yardım, daha net karşı-önlemler, retry juice'ı |
| **Hafta 1** | İnce içerik; tekrarlayan haritalar | Modifikatörler, ikinci civ dozu, etkinlik |
| **Hafta 2–4** | Enerji/paywall/reklam yorgunluğu | Reklam tavanı; cömert soft para; kozmetik IAP odağı |
| **Temizleme-sonrası** | Meta hedef yok | Koleksiyon %, ustalık, endless, lonca |
| **Sosyal boşluk** | Sonsuza dek tek başına oynama | Hafif lonca + paylaşım kartları |
| **Live-ops kuraklığı** | 6 hafta boyunca aynı mod | 90-günlük takvim (§14) |
| **Güven kırılması** | P2W, yem banner'ları, spam push | Adil pity, bildirim hijyeni |
| **Kimlik kaçırma** | Jenerik fantazi TD | Civ-otantik kuleler & düşmanlar |

### 11.12 Viral kancalar (bu IP için tasarlanmış)

1. **Replay şan kartları** — otomatik-üretilen paylaşım görseli: civ sanatı + tutulan dalgalar + MVP kule.
2. **Aynı-seed haftalık challenge** — arkadaşlar skor karşılaştırır; Discord haftalık kazanan unvanı.
3. **Civ kimlik testi → başlangıç civ'i** — TikTok/IG hunisi FTUE'ye.
4. **Lonca anıt zaman-atlaması** — lonca paylaşılan duvarı inşa ederken paylaşılabilir.
5. **Yardım linkleri** — "Thermopylae Dalga 9'u tutmama yardım et" deep link'i.
6. **Codex lore kısaları** — 20s tarih → "Bu savunmayı oyna" CTA'sı.
7. **Yaratıcı plan kodları** — kule düzenlerini içe aktar (BTD6-benzeri sosyal teknoloji).
8. **Sezonsal gerçek-dünya takvimi** — gündönümü/Yeni Yıl/ulusal tarih ayları civ spotlight'ları olarak (zevkli, araştırılmış).

### 11.13 Neden bildirim/retention dili önemli (bağımlılık dili çevirisi)

Sevilen dil (koru): "bir tur daha", "sadece bu boss'u bitireyim", "günlük tamam", "3-yıldızlayayım", koleksiyon tamamlama çubuğu, klan sandığı doldurma. Nefret edilen dil (asla üretme): "bekleyemeden oynayamıyorum", "hileli gacha", "ödeme yap ya da acı çek", "ev ödevi simülatörü", "zaten reklamsız için ödemiştim". **Ürün kararı:** içeride "habit loop/mastery loop/social light loop" dili kullan; dışarıda "derin strateji", "adil challenge", "tarihsel kampanyalar" dili kullan.

### 11.14 Günlük & haftalık döngü (retention özeti, §4.9–§4.10 ile çapraz-referans)

Bu bölümdeki tüm sistemler §4.9'daki günlük döngü şablonuna ve §4.10'daki haftalık takvime **eklenir**, onları değiştirmez — retention bir *ayrı oyun* değil, çekirdek döngünün üstüne bindirilmiş bir ödül/sosyal katmandır.

---

## 12. Sosyal / lonca / paylaşım

*(Kaynak: `GDD-SOCIAL-GUILD.md` — tam dosya)*

### 12.1 Tasarım ilkeleri (pazarlık konusu olmayan)

1. **Opt-in sosyal.** Solo kampanya eksiksizdir. Story action'larında, yıldızlarda veya mit açılışlarında lonca kapısı yok.
2. **Async-öncelikli.** Gerçek-zamanlı sohbet ve canlı co-op Faz 2+'dır. Lansman sosyali = lonca tahtası + async kuşatma + paylaşım kartları + arkadaş challenge'ı + adil tahtalar.
3. **Skill parantezleri, cüzdan parantezleri değil.** Liderlik tahtaları ve kuşatma MMR'ı IAP harcamasını, premium mühürleri ve kozmetik loadout'u görmezden gelir.
4. **Tarihsel ton, çöp konuşması değil.** Emoteler ve hazır replikalar komutan/vakayiname sesi kullanır ("Duvar tuttu."), modern flame slangı değil.
5. **Mimariyle anti-toksisite.** Daha az serbest-metin yüzeyi → daha az moderasyon yangını.
6. **Gizlilik & çocuklar.** Varsayılan: teens+; reşit-olmayan/COPPA yolları: sohbet yok, açık davet linki yok, paylaşım kartları PII'yi çıkarır.
7. **Ölçülebilir retention işleri.** Aşağıdaki her özellik bir **D7 işi** ve bir **D30 işi** adlandırır.

### 12.2 Lonca sistemleri ("Keeps & Cohorts")

**Fantazi çatısı:** Oyuncular sezon civ'iyle temalı paylaşılan bir **Fort Map** tutan bir **Cohort**'a (lonca) katılır (örn. masugata avlusu, castrum ızgarası, ordu halkası). İsimlendirme: Clash-tarzı P2W çağrıştıran "Clan War" pazarlamasından kaçın; **Cohort**, **Legion**, **Banner**, **Watch** tercih edilir.

| Öge | Spesifikasyon |
|---|---|
| Boyut | Yumuşak tavan 30; sert tavan 40 (anti-zombi mega-lonca) |
| Roller | Consul (lider), Prefect'ler (3), Veteranlar, Recruitler |
| Katılım | Açık/İstek/Sadece-davet |
| Aktivite skoru | 7-günlük yuvarlanan katkı (kuşatma, bağış, challenge) |
| Kick kuralları | 14g etkisizlik otomatik-işaretler; Consul onaylar; soğuma süresiz toplu-kick yok |
| Lonca XP'si | Üye oyun **kalitesinden** (yıldızlar, challenge temizlemeleri), harcamadan değil |
| Lonca seviyesi | Kozmetik fort prop'ları, emote paketleri, sancak çerçeveleri açar — **DPS değil** |

**Üye döngüleri:** (1) **Tahıl bağışı (tithe):** soft para/sarf malzemesi ("erzak") lonca sandığına, günlük tavanla. (2) **Nöbet görevi (Watch duty):** 1-koşu async "fort'u savun" slotunu talep et. (3) **Günün Codex ipucu'su:** lonca akışına gönderilen bir tarihsel kırıntı. (4) **Haftalık toplanma:** Pazar özeti — en iyi katılımcılar, kuşatma sonucu, sıradaki sezon teaser'ı.

**Loncalar ASLA:** güç upgrade'i, özel kule veya aidat için hasar % satamaz; elit cohort'lara katılım için harcama gerektiremez; ilk kuşatma katkısından önce sohbeti zorlayamaz; solo oyuncuları kampanyayı bloklayan FOMO ile cezalandıramaz (lonca ödülleri = sadece kozmetik + soft).

### 12.3 Async kuşatma (lonca fort savunması)

**Fantazi:** Rakip loncalar veya AI "barbar sezonları" Fort Map'ini baskılar. Üyeler **eş zamanlı** olmak zorunda değildir.

| Pencere | Süre | Oyuncu işi |
|---|---|---|
| Muster (önizleme) | 12s | İstihbarat keşfi (düşman civ, modifikatörler) |
| Watch | 5 gün | Günde N kişisel savunma (örn. 3) |
| Çözüm | Kapanışta anlık | Ganimet + sancak damgası |
| Ateşkes | 1–2 gün | Kozmetik zanaat, işe alım |

**Adalet kuralları:** (1) Loadout, kampanya ilerlemesine + skill rating'ine kilitli, harcanan mühüre değil. (2) Ücretli "çevrimdışı yağma koruması" sadece kozmetik prop/sandık görünümünü korur, asla HP/DPS'i. (3) Katkı skorlaması: yıldızlar, minimize sızmalar, erken-çağırma skill'i — satın alınan geçici boost'lar değil. (4) Anti-carry: en iyi 3 skor tam ağırlıkta sayılır; sonraki skorlar azalan; boşta üyeler katılım alır ama zafer unvanı almaz. (5) Saldırı tarafı (varsa): geçen haftanın **hayalet düzenine** karşı ayna-offense senaryosu — canlı grief değil.

**Oturum uzunluğu hedefleri:** tekil Watch koşusu 4–8 dk; günlük maksimum anlamlı katkı ~15–20 dk — ev ödevi hissini durdur.

### 12.4 Paylaşım kartları (chronicle & zafer damgaları)

Otomatik-üretilen **görsel + kısa metin** varlıkları OS paylaşım sayfaları, Discord, Reddit, Instagram Stories için: zafer damgası, mükemmel-sızmasız/heroic-stil varyantlar ("Siege Hardened"), Codex keşif kartı, lonca kuşatma sonuç kartı (oyuncu flame'i olmadan), mit ultimate sinematik karesi ("Myth Mode" etiketiyle ahistorikse).

**Tasarım kısıtları:** Varsayılan kartta **kişisel veri yok** (kullanıcı adı opt-in; varsayılan komutan unvanı + lonca kısa etiketi). Ödeme duvarına değil, challenge seed'ine/referrala **QR/deep link**. İnce marka damgası; fragman-dürüst sanat (sahte gameplay yok).

### 12.5 Referral ("Sancağı Yükselt") ve arkadaş challenge'ı

**Referral akışı:** Oyuncu kişisel banner kodu üretir (rate-limited) → yeni oyuncu kurar, hesabı bağlar, **kalite kapısından** geçer (örn. Act 0'ı + bir günlüğü temizle) → her ikisi de soft/kozmetik alır, güç asla. Kapaklar: sezon başına örn. 5 ödüllü davet. **Geri dönen varyant:** 30g+ durgun oyuncular arkadaşlardan "lejyonu geri çağır" ping'i alır (opt-in); bir Watch/challenge'ı bir arkadaşla tamamlamak geri dönen kozmetiği verir.

**Arkadaş challenge'ı (async):** Hayalet yarış (aynı seed/modifikatör, yıldız/süre/sızma karşılaştır), Düzen düellosu (arkadaşın paylaşılan fort planını savun), Haftalık pakt (aynı challenge kartını temizle), Öğretme seed'i (FTUE-güvenli harita gönder, ranked etkisi yok). Challenge 72s'de sona erer (sonsuz backlog suçluluğu olmadan randevu).

### 12.6 Adil liderlik tahtaları (skill parantezleri, P2W değil)

**Braket felsefesi:** **Kanıtlanmış skille** eşleştir ve sırala, kümülatif harcama veya ham hesap seviyesi değil.

| Tahtada izinli | Sıralama girdisi olarak yasak |
|---|---|
| Temizleme süresi, sızmalar, yıldızlar | Harcanan premium para |
| Modifikatör temizlemeleri | Geçici hasar IAP boost'ları |
| Adil MMR | Kule nadirlik ödeme duvarları |
| Profilde kozmetik flair | Ekstra build slotu |

**Tahta UX'i:** Varsayılan görünüm **braketin ±10 rütbesi**, sadece küresel balina top-100'ü değil. Küresel efsane tahtası var ama açıkça "üst skill" olarak, doğrulama rozetleriyle. **Anti-cheat:** Sunucu-yetkili skor gönderimi; imkansız DPS/hız hackleri için replay bayrakları; ilk suçta tahtalardan yumuşak-ban ≠ hesap silme.

### 12.7 Anti-toksisite mimarisi

| Yüzey | Politika |
|---|---|
| Lonca akışı | Hazır gönderiler + reaksiyonlar; kısa metin opsiyonel Faz 1.5, filtreli |
| Emoteler | Tarihsel/komutan paketi; orta-parmak analogları yok |
| Sohbet | Varsayılan kapalı; lonca opt-in sonra; arkadaş fısıltısı sonra |
| Ses | Lansmanda yok |
| Liderlik tahtaları | Rütbelerin altında herkese açık yorum yok |

**Moderasyon araç seti:** Report (taciz, hile, spam, nefret, reşit-olmayan risk); mute kullanıcı/lonca akışı; blok + challenge banı; Consul araçları (kick, davet iptali, akış yavaş-modu); automod (hakaret, doxx desenleri, phishing linkleri); ciddi raporlar için eskalasyon SLA'sı.

**Politika kopyası (oyuncuya-dönük ton):** "Cohort'lar tarihi birlikte savunur. Hakaret, nefret ve hile kuşatma cürümüdür — bildirilen sancaklar düşer."

### 12.8 Özellik × retention matrisi (özet)

| Özellik | Birincil D7 işi | Birincil D30 işi | Monetizasyon bitişikliği |
|---|---|---|---|
| Lonca/Cohort | Haftalık tahıl+watch alışkanlığı | Kimlik + fort kozmetikleri | Emote, sancak, fort prop'ları |
| Async kuşatma | Hafta-ortası randevular | Sezonluk chronicle damgaları | Kolaylık ≠ güç |
| Paylaşım kartları | Viral/sosyal kanıt açılışları | Codex cilt koleksiyonu | Marka UA, ödeme duvarı linki yok |
| Referral | Arkadaş onboarding'i | Geri dönen yeniden-kazanım | Sadece soft + damga |
| Arkadaş challenge'ı | İkili randevular | Kararlı rakip kimliği | Gerekli değil |
| Adil liderlik tahtaları | Haftalık challenge çekişi | Sezonluk skill kimliği | Sadece kozmetik çerçeveler |
| Anti-toksisite | Hafta-bir sosyalini koru | Marka & niş demografiyi koru | Güven → kozmetik harcama isteği |

### 12.9 Aşamalı yayılım

**Faz A (lansman):** Cohort'lar (30), tithe, hazır akış · Async Watch kuşatması (önce PvE sezon baskısı) · Paylaşım kartları (zafer + Codex) · Kalite-kapılı referral · Arkadaş hayalet yarışı · Haftalık challenge braketleri + kampanya madalya tahtası · Report/mute/block.

**Faz B (+60–90g):** Lonca-vs-lonca hayalet raid'leri (braketli) · Automod'lu kısa lonca metni · Aylık boss kuşatma tahtası · Geri dönen referral yolu · Sportsmanship damgaları.

**Faz C (sonra):** Opsiyonel canlı co-op (paylaşılan gold, ayrı HP) duraklama-dostu tasarımla · Moderasyon bütçesi varsa sadece ses — varsayılan kapalı · Arkadaş challenge'larını besleyen yaratıcı map-seed tarayıcısı.

### 12.10 Tek satır ürün tezi

**Tarihsel TD oyuncularına bir cohort, tutulacak haftalık bir duvar, paylaşılacak bir damga ve paranın tırmanamayacağı bir merdiven ver — böylece D7 randevu, D30 kimlik olsun, PvP toksisitesi veya P2W ayıbı ithal etmeden.**

---

## 13. Monetizasyon (IAP, reklam, battle pass) — etik kuzey yıldızı

*(Kaynak: `GDD-MONETIZATION.md` — tam dosya)*

### 13.1 Tek satır yasa

> **Güzellik ve daha erken bölümler satılıktır; zafer satılık değildir.**

### 13.2 Tasarım tezi — CAPS$ türe uygulanmış

| Motivasyon | TD'ye uygunluk | İyi niyet riski | Bu oyunda kullanım |
|---|---|---|---|
| **C — Kozmetik (Cosmetics)** | Kule/hero/anıt skini, zafer sancağı, harita hava FX | Düşük — kazanılabilir yol varsa | **Birincil** |
| **A — Erişim (Access)** | Civ paketleri, harita paketleri, sezon arşivi, erken açılış | Orta — içerik kalıcı kilitli hissederse | **Birincil** (zaman-kaydırmalı, sonsuza dek kilitli değil) |
| **P — Güç (Power)** | Kesin daha iyi kuleler, ücretli hasar, ücretli can | **Çok yüksek** — review bombing | **Kalıcı avantaj olarak kaçınılır** |
| **S — Statü (Status)** | Sezon rozeti, liderlik çerçeveleri, lonca anıtları | Orta — PvP güç-satın alımı toksikleştirir | **Sadece kozmetik statü** |
| **$ — Gerçek para (kumar/RMT)** | — | Regülasyon + marka ölümü | **Kapsam dışı** |

**Sonuç:** Saf kozmetiğin **görünürlüğe** ihtiyacı var (replay paylaşımı, co-op, profil, zafer ekranı). Bu olmadan kozmetikler yetersiz kazanır ve takımlar Güç monetizasyonuna kayar. Görünürlüğü erken inşa et (zafer replay kartı, co-op skinleri, profil anıtı) — bkz. §12.4 paylaşım kartları.

### 13.3 Rakip kıyaslaması — güven mi nefret mi kazandırır

**Güven çapaları:** Bloons TD 6 (peşin satın alım + opsiyonel IAP; enerji yok; çekirdek IAP'siz temizlenebilir; en zor modlar mağaza güçlerini yasaklar; cömert kazanılabilir para). Klasik Kingdom Rush 1–3 (peşin/hafif IAP; tam kule seti kazanılabilir; paralı heroler uzun süre opsiyonel hissettirdi). Defense Grid / klasik PC TD (tek seferlik satın alım; sıfır oturum kapısı; "adil"in topluluk altın standardı).

**Güven yıkıcıları (mağaza 1★ desenleri):** Her dalgada/seviyede zorunlu interstitial → "reklam duvarı". Revive IAP için mühendislik edilmiş zorluk sıçraması → "hileli/P2W". Reklam kaldır alındıktan sonra hâlâ zorunlu rewarded ads → "dolandırıcılık". Geç haritalar için gerekli ödeme-duvarlı kuleler → "cash grab". Oturum ortasında boşalan enerji → "kanser" (özellikle PC/anonim kitlede). Asla dolmayan "reklam izle ödül al" butonları → "sahte ücretsiz yol". Kule için loot/gacha → "slot makinesi".

**Kural:** Medyan yetenekli bir F2P oyuncu kampanyanın **≥%70'ini** IAP veya reklamsız temizleyemiyorsa, ARPDAU'dan bağımsız olarak puan çöküşü bekle.

### 13.4 Enerji politikası (§0 kuralının operasyonelleştirilmesi)

| Yaklaşım | Oturum etkisi | Monetizasyon | İyi niyet | Bu oyun için hüküm |
|---|---|---|---|---|
| **Sert enerji** (başlangıç stamina harcar) | Habit'in ortasında oynamayı durdurur | Yüksek kısa-vadeli ARPDAU | Midcore TD için **toksik** | **Reddedildi** |
| **Can/başarısızlık-kapılı** | Başarısızlığı beklemeyle cezalandırır | Revive satar | Zorluk sıçratılırsa yüksek öfke | Sadece yumuşak, opsiyonel kullan |
| **Yumuşak enerji** (sadece opsiyonel modlar) | Sadece *bonus* çiftlikleri sınırlar (raid, endless jeton) | Hafif kolaylık IAP + reklam | Kampanya sınırsızsa kabul edilebilir | **Opsiyonel** |
| **Enerji yok** | Uzun oturumlar; BTD6-benzeri | Battle pass + kozmetik + paket | PC + Steam/Reddit/anonim için en iyi | **Varsayılan** |

**Kampanya/hikaye/ranked skill modlarında asla enerji yok.** Sadece *tekrarlanan çiftlik döngüleri* için (örn. kozmetik zanaat malzemesi için "Kuşatma İkmali koşuları") opsiyonel yumuşak jetonlar — günde 2–3 bonus oturuma yetecek kadar cömert yenilenme. Sıradaki hikaye haritasını asla enerjinin arkasına kilitleme. **PC/Steam:** tüm yumuşak jetonları + reklam istemlerini kaldıran bir **parite paketi** (veya native premium SKU) gönder — çift-platform itibar kalkanı.

### 13.5 Reklam-ödüllü tasarım (rewarded ads)

**İzinli (sadece opt-in):** Zafer sonrası **çift soft para** (1×/temizleme; asla zorunlu değil). Yenilgi ekranında **devam/revive** (haritada maks 1 devam; Iron/CHIMPS-benzeri modlarda devre dışı). Meta hub'da **günlük sandık**/kozmetik parça (günlük tavan, atlanabilir). Boş yumuşak jeton için **+1 jeton** (sadece opsiyonel çiftlik modlarında).

**Yasak:** Seviye başlamadan önce veya dalga ortasında interstitial. Her temizlemeden sonra zorunlu video. "Zaten kazanılmış ödülü almak için reklam izle." İlerleme için hâlâ rewarded video zorunlu kılan reklam-kaldır IAP'i. Oynanış sırasında build UI'ını kapatan banner.

**Platform politikası notları:** Apple 2.5.18 — interstitial'lar açıkça reklam olmalı, kolay kapatma/atlama. Apple 3.1.1 — rastgele ödenmiş öğelerin ("loot box") oranları satın almadan **önce** açıklanmalı. Google Play — oyun sırasında veya seviye başında beklenmedik tam ekran reklam yok; rewarded açık opt-in olmalı. AdMob — eylem+ödül açık beyanı, onaylı opt-in, atla/kapat normal oyunu bozmamalı. **İyi niyet reklam karışımı:** midcore TD için hedef **~%70–80 IAP / %20–30 reklam** (reklam = ödeme yapmayanın katkısı, birincil kaldıraç değil).

### 13.6 Kozmetik IAP

Oyuncular saatlerce kulelere bakar → **kule skinleri, mermi FX'i, hero sancakları, kale/anıt giysisi, zafer fanfarı** avatar-only kozmetiklerden daha iyi satar.

**Katalog kuralları:** (1) **%80–90 kazanılabilir** oyun/battle pass ücretsiz track/etkinlik yoluyla. (2) **%10–20 ücretli özel** (sezon prestiji, collab, sınırlı civ zanaatkâr skini) — statü, güç değil. (3) Hiçbir kozmetikte savaş istatistiği yok (UI'da belgelenir). (4) Civ estetiğine göre paketle (örn. "Roma Zaferi VFX paketi"). (5) İlk sezon kozmetiklerinde saf FOMO zamanlayıcısından kaçın; daha yüksek fiyatla sonradan alınabilen "sezon arşivi" kullan.

| SKU | Fiyat | Rol |
|---|---|---|
| Tek kule skini | $1.99–$3.99 | Dürtüsel |
| Civ kozmetik paketi (4–6 öğe) | $4.99–$9.99 | Dolphin |
| Premium anıt/kale seti | $7.99–$14.99 | Whale gösterişi |
| Sadece para birimi | Birincil olarak tercih edilmez — *isimli içerik* sat | Şeffaflık |

### 13.7 Battle pass / sezon pass'i

**Yapı:** Uzunluk 6–8 hafta (bir civ spotlight'ıyla hizalı). Track'ler: Ücretsiz + Premium ($7.99–$9.99). Ücretsiz track değeri premium *hacminin* **≥%60–70'i** (para, commonlar, 1–2 kozmetik); premium prestij skinleri, erken civ jetonu, sancak alır. Premium ödülleri: kozmetikler, kozmetik zanaat malzemesi, soft para, **bir erken-erişim kule skini** — asla benzersiz daha güçlü bir kule. Max kademe sonrası dönüştürülebilir toz kazanılır (güç şişmesi olmadan whale hayal kırıklığı önleme).

**Yapılmayacaklar:** Gerçek içerik güncellemelerini pass dolgularıyla değiştirme. Kampanya haritalarını pass'in arkasına kilitleme. Temel kuleleri sadece premium track'e koyma. İkinci bir kira gibi hissettiren üst-üste ücretli pass'ler çalıştırma.

**Sezon kadansı — "tarih takvimi" hissi (rastgele mağaza spam'i değil):**

| Sezon teması | Öne çıkan civ | Pass lezzeti |
|---|---|---|
| Sel & Tuğla | Babylon | Ziggurat skinleri, sel FX'i |
| Kartal & Yol | Rome | Lejyon standartları, yol tozu |
| Ölümsüzler | Persia | Immortal zırh kozmetikleri |
| İki Toprak | Egypt | Nil sezon sancakları |
| Mandate | China | Hanedan kale giysisi |
| Çiçek Savaşları | Maya–Mexica | Tüylü sancaklar (hassasiyet filtreli) |
| Uzun Gemi | Viking | Rün zafer kartları |
| Polis | Greece | Bronz hoplit FX'i |
| Kale & Avlu | Japan | Kale-tutucu kitleri |
| Orda | Mongol (saldırgan sezonu) | Horde sancak kozmetikleri |
| Kale & Perde | European Castle | Hanedanlık çerçeveleri |

### 13.8 Civ açılış paketleri ve içerik erişimi

**Problem:** 11 civ, ya (a) devasa sanat maliyeti ya da (b) boş kabuklarla lansmanda gönderilemez. **Çözüm:** kazanma-yolu olan **Erişim monetizasyonu** — Güç değil.

| Civ | Açılış yolu | Ücretli kısayol |
|---|---|---|
| Babylon | Tutorial / her zaman ücretsiz | — |
| Rome | Kampanya bölüm 2 ücretsiz | Başlangıç paketi içerir |
| Egypt / Greece / Persia | Kampanya yıldızlarıyla kazan **veya** $4.99–$6.99 civ paketi | Paket = erken erişim + kozmetik |
| China / Viking / Japan | Sezon spotlight'ı → sonra ücretsiz kazan | Sezon pass erken jetonu |
| Maya–Mexica / Mongol / European Castle | Lansman-sonrası sezonlar | Civ paketi + pass |

**Kurallar:** (1) Her civ nihayetinde o sezonun penceresinde F2P kazanılabilir (yıldız/kuşatma madalyası). (2) Ücretli paket **2–4 hafta erken erişim** + özel kozmetik verebilir — sonra ücretsiz havuza katılır. (3) Paketler **asla** kalıcı olarak ödeyenlere kilitli özel daha güçlü birim ağaçları içermez. (4) Steam DLC civ paketlerini net değerli premium içerik olarak yansıtabilir (harita + kozmetik + Codex).

### 13.9 Başlangıç paketleri (dönüşüm motoru)

İlk satın alma herhangi bir whale SKU'sundan daha iyi dönüşür. **Bir kez** sun, FTUE başarısından sonra zamanlanmış (harita 3–5 temizleme sonrası), ~48–72 saat pencere, sonra emekliye ayrılır.

| Paket | Fiyat | İçerik | Hedef |
|---|---|---|---|
| **Bronz Başlangıç** | $2.99 | İnterstitial reklamları kaldır (varsa) + küçük gold + 1 ortak skin | Minnow → payer |
| **Gümüş Lejyon** | $4.99 | Rome civ erken (açık değilse) + 3 skin + 1 battle-pass indirim jetonu | Dolphin tohumu |
| **Altın Arşiv** | $9.99 | Sezon pass + başlangıç kozmetikleri + 2× günlük jeton yenileme kalıcı küçük artış | Yüksek-niyet |

**Asla** başlangıç paketine özel güç koyma. Değer "ödediğimden fazlasını aldım" hissetmeli, ekonomiyi kırmadan.

### 13.10 Whale / dolphin / minnow tasarımı

| Kohort | Oyuncu payı (yaklaşık) | Tipik harcama | Burada ne satın alır |
|---|---|---|---|
| **Ödeme yapmayan** | ~%95–98 | $0 (+ reklam) | Rewarded çift gold, ücretsiz pass track |
| **Minnow** | Ödeyenlerin çoğu | $1–$20 ömür boyu | Başlangıç, tekli skin, 1 pass |
| **Dolphin** | Orta ödeyen | $20–$200/yıl | Her sezon pass + 1–2 civ paketi + bundle |
| **Whale** | ~%1–2 oyuncu | $200–binlerce $/yıl | Tüm kozmetikler, sınırlı prestij, tam arşiv, hediye paketleri |

**Etik whale tasarımı:** Yüksek-AOV **gösteriş** (animasyonlu kale, özel anıt VFX'i, isim levhası). Tam kozmetik koleksiyonları/ustalık çerçeveleri. PvP veya kampanya kapılarında **ödeme-ile-kazanma yok**. Hesap güvenliği desteği; harcama tavanları/oturum hatırlatıcıları jurisdiction gerektirdiğinde. **Dolphin tasarımı (gelir belkemiği):** Güvenilir $7.99–$9.99 sezon pass değeri; net içerikli civ paketleri; sezon lansmanında bundle indirimleri. **Minnow tasarımı:** $2.99–$4.99 ilk satın alma mükemmelliği; onur yolu olarak reklam (opt-in ödüller).

### 13.11 Loot box / gacha — politika

**Platform tabanı:** Apple 3.1.1 ve Google Play — her öğe türünün oranları satın almadan önce açıklanmalı. Bazı jurisdictionlar (BE/NL vb.) ücretli rastgele ödülleri kumar sayabilir — bu mağazalarda gacha göndermeden önce hukuki inceleme.

| Yaklaşım | Kullan? |
|---|---|
| Ücretli rastgele **güç** (kule nadirliği, hasar atışı) | **Hayır** |
| Pity + kamu oranlarıyla ücretli rastgele **kozmetik** | Opsiyonel, sonra; lansman-kritik değil |
| Oyundan kazanılabilir kozmetik sandıkları | Evet |
| Deterministik mağaza (gördüğün skini satın al) | **Tercih edilir** |

**Lansman varsayılanı:** Ücretli loot box yok. Deterministik kozmetik mağazası + battle pass. Sandıklar sonra gelirse: sadece kozmetik, yayınlanmış oranlar, pity, güç yok.

### 13.12 1-yıldız incelemeye neden olan liste (ship blocker gate)

Aşağıdakilerden herhangi biri "evet" ise gönderim durdurulur: (1) Ödemeden veya sonsuz reklamsız ilerleme yapılamıyor. (2) Keyifli bir oturumda enerji boşalıyor. (3) Zorunlu reklamlar oyun ortasında/her temizlemede. (4) Reklam-kaldır satın alımı yalan (rewarded hâlâ zorunlu). (5) Adil temizleme için ödeme-duvarlı kuleler gerekli. (6) Revive treadmill zorluk sıçramaları. (7) Bozuk rewarded reklamlar ("no fill" ama para biriminin tek yolu). (8) Oran olmadan/güçle loot box. (9) Harcama sonrası hesap kaybı/bulut kayıt hatası. (10) Kültürel saygısızlık monetize "edgy" paket olarak çerçevelenmiş (özellikle kutsal/ritüel içerik). (11) PC portu mobil F2P açgözlülüğüne göre nerflenmiş. (12) Yanıltıcı mağaza ekran görüntüleri (IAP'nin arkasındaki özellikler açıklanmamış).

**Oyun içi yayınlanan anti-1★ sözleşme:** *Kampanya ücretsiz tamamlanabilir; reklamlar opsiyonel; kozmetikler asla hasarı değiştirmez; skill modları devam'ı yasaklar.*

### 13.13 Mobil vs PC monetizasyon ayrımı

| Yüzey | Mobil F2P | PC/Steam |
|---|---|---|
| Giriş | Ücretsiz indirme | Premium (~$14.99) **veya** ücretsiz + aynı ekonomi + "Komutan Paketi" |
| Reklam | Sadece rewarded | Yok (veya varsayılan kapalı) |
| Enerji | Kampanyada yok | Yok |
| Sezon pass'i | Var | Var (paylaşılan hesap idealdir) |
| Civ paketleri | IAP | DLC, paketleri yansıtır |
| Kozmetikler | IAP + kazan | Aynı katalog |
| Skill modları | Devam devre dışı | Aynı |

Çapraz-kayıt + paylaşılan satın almalar (mağaza politikası izin verdiğinde) iyi niyeti muazzam artırır (§1.4'teki parite ahlaki taahhüdüyle çapraz-referans).

### 13.14 Önerilen monetizasyon matrisi (kararlı durum)

| Stream | Gelir payı | Sahip kohort |
|---|---|---|
| Sezon pass'leri | %35–45 | Dolphin'ler |
| Civ/içerik paketleri | %20–30 | Dolphin + whale |
| Kozmetik/prestij | %15–25 | Whale + dolphin |
| Başlangıç/küçük IAP | %5–10 | Minnow'lar |
| Reklam (rewarded) | %10–20 | Ödeme yapmayanlar |

**Lansman fazlaması:** *Yumuşak lansman* — enerji yok; rewarded çift gold; başlangıç paketi; 2–3 kozmetik; **henüz** pass yok (temizleme oranlarını ölç). *Global lansman* — başlangıç + kozmetik mağazası + ilk sezon pass'i + Rome/Egypt paketleri. *Sezon 2+* — tam civ paketi merdiveni; prestij anıtları; puanlar ≥4.3 ise opsiyonel kozmetik sandıkları.

### 13.15 Uygulama ilkeleri (mühendislik + tasarım)

1. Her SKU için "savaşı etkiler mi: evet/hayır" **tek doğruluk kaynağı**.
2. **Skill mode bayrakları** devam'ı, jetonları ve ücretli güçleri devre dışı bırakır.
3. **Telemetri:** ödeyen dönüşümü, reklam opt-in oranı, IAP durumuna göre temizleme oranı, inceleme-teması etiketleme.
4. 1★ bahsi sıçramasında herhangi bir teklif için **kill switch**.
5. **Mağaza listesi dürüstlüğü:** opsiyonel IAP'yi açıkla; kilitli civ'leri sahiplenilmiş gibi gösterme.
6. Din/kurban içeren monetize paketlerde **kültürel inceleme** — şok SKU'su değil, kozmetik özen.

---

## 14. Live ops takvimi

*(Kaynak: `GDD-LIVEOPS-FTUE-META.md` §3–§5, `GDD-MONETIZATION.md` §6)*

### 14.1 Kadans katmanları

| Katman | Uzunluk | İçerik |
|---|---|---|
| **Sezon** | ~6–7 hafta (Yıl-1: kısa aralarla ~8 sezon ≈ 12 ay) | Öne çıkan civ, battle pass, özel Codex kanadı, harita skini |
| **Etkinlik haftası** | Sezon-ortası 7–10 gün | Modifikatör playlist'i, boss önizlemesi, 2× ustalık |
| **Hafta sonu raid'i** | Cuma–Pazartesi | Async Raid hedefi yenilenmesi |
| **Günlük** | 24 saat | 3 görev + giriş mührü |

**Sezonlar arası kesinti haftası (1 hafta):** yakalama XP'si, Museum spotlight'ı, yeni pass gerekmez.

### 14.2 Sezon yapısı (şablon)

1. **Lansman fragmanı / hub ele geçirme** — öne çıkan kale tam ekran.
2. **Track A (ücretsiz):** mühürler, Codex kartları, 1 kule skini.
3. **Track B (pass):** hero skini, kale restorasyon kademesi, portre, mit-etiketli özel birim *sadece Arcade'de* (EDU açıksa "sadece-göster" istisnası).
4. **Sezon görev zinciri** (8–12 düğüm) — belgelenmiş kampanyalara hafifçe dayalı anlatı; düğüm başına doğruluk etiketi.
5. **Final:** Sezon civ'ine (veya rakip civ antagonist olarak) temalı Co-op Boss.

### 14.3 Yıl-1 sezon temaları

| # | Pencere (gösterge) | Tema başlığı | Öne çıkan civ | Antagonist/baskı | İmza içerik |
|---|---|---|---|---|---|
| **S1** | Lansman + 6h | **Etemenanki Yükseliyor** | Babylon | Kabile/Elam-stilize dalgalar | Ziggurat kale kademeleri; kanal tuzakları; sezon UX'ini öğret |
| **S2** | +6h | **Capitoline Gök Gürültüsü** | Rome | Gallia/Germen akın fantazisi | Castrum katmanları; *testudo* düşman modifikatörü; Jüpiter kalesi |
| **S3** | +6h | **Nil Nöbeti** | Egypt | Deniz Halkları-esinli baskı | Buhen estetiği; savaş arabası şeridi; sel-kapısı mekaniği |
| **S4** | +6h | **Duvar & İşaret Ateşi** | China | Bozkır süvari baskısı | Wengcheng choke tutorial'ı; işaret-kulesi görüşü |
| **S5** | Yıl ortası | **Yeşim & Obsidyen** | Maya–Mexica | Rakip şehir-devlet akınları | İkili civ seçici haftası; top sahası FX'i; saygılı ritüel çerçevesi (bkz. §17 riskler) |
| **S6** | +6h | **Uzun Gemi Ufku** | Viking | Frank/Anglo kale savunması tersine çevrilmiş | Ringfort kalesi; kıyısal ikili şerit; kalkan-duvarı düşmanları |
| **S7** | +6h | **Kuşatma Altında Polis** | Greece | Persia-temalı fantazi baskı | Akropolis kalesi; falanks bloğu; trireme yan-hedefi |
| **S8** | Yıl sonu | **Doğu Kapıları** | Persia *veya* **Japan** (metrikle seç) | Çapraz-civ "İpek & Çelik" festivali | Persia ise: immortal muhafız mit-etiket kuralları; Japan ise: masugata patika haritası. **Mongol** + **European Castle**, Yıl-2 S1–S2 başlıkları olarak tutulur, S4/S6/S8'de sezon-ortası etkinlik cameo'larıyla. |

**Yıl-1 cameo planı (başlık-olmayan civ'ler):** Mongol → S4 etkinlik haftası "Yürüyüşteki Orda" (saldırgan-lezzetli modifikatörler). European Castle → S2 veya S8 hafta sonu "Concentric Duvarlar" harita mutator'ı. Persia/Japan'dan S8 olmayan, 10 günlük uydu etkinliği alır.

### 14.4 Sezon başına live-ops içerik bütçesi

1 kale skini + 2 kule skini + 1 hero skini · 8–12 Codex kartı (Belgelenmiş/Yorumlanmış/Mitik karışımı) · 1 co-op boss + 1 async raid rosteri yenilemesi · 1 lonca savaşı harita skini · sezon-ortası (hafta 3–4) balans yaması.

### 14.5 Sosyal live-ops özellikleri (hafif; detay §12)

| Özellik | Oyuncu | Yapı | Adalet |
|---|---|---|---|
| **Co-op boss** | 2 (performansa göre opsiyonel 3) | Paylaşılan kale HP'si; bölünmüş build bütçesi; ping tekerleği | Güç bandı hesap seviyesi ± sezon pass kademesi yumuşak; özel davet her zaman var |
| **Async raid** | Solo saldırgan vs hayalet düzen | Yayınlanan Defans Düzeni'ne (opt-in) karşı dalga bütçesi koşusu | Yayında anlık görüntü; sonsuz stall-cheese yasak; EDU modu mit kulelerini "Tarihsel Challenge" rozetiyle devre dışı bırakabilir |
| **Lonca savaşı lite** | 5–15 üye, asenkron | 48–72s pencere; üye başına 1–2 atak; Savaş Haritası düğümlerinde kale HP'si/süreyle yıldız | Zorunlu ses yok, eşzamanlı giriş yok; asla özel civ açılışı ödülü |

**Kural:** Solo oyuncu üçünü de sonsuza dek görmezden gelebilir, PvE kampanyasını kaybetmeden. Hub sosyali L5'e kadar merkezi CTA değil **opsiyonel kanat** olarak gösterir.

### 14.6 Educational Mode vs Arcade Mode (live-ops'a bağlı)

| Sistem | Educational | Arcade |
|---|---|---|
| Mit/efsanevi birimler | Kapalı, veya Museum'da **sadece-göster** | Açık, balans-ayarlı |
| Düşman adları | Belgelenmiş birlik türleri tercih | Fantastik adlar OK |
| Görev metinleri | Kısa + kaynak-kademe etiketi | Önce lezzet |
| Co-op boss telegraph'ları | Yorumlanmış taktikler tercih | Tam gösteri |
| Monetizasyon | Aynı kozmetikler; EDU paketleri Codex/Museum'a yaslanır | Aynı; mit skinleri öne çıkar |

**Doğruluk etiketleri (her ikisinde de zorunlu):** **Belgelenmiş** (birincil/ikincil kaynak konsensüsü) · **Yorumlanmış** (oynanış için mantıklı yeniden inşa) · **Mitik** (folklor/destan/kurgu — eğlence, gerçek değil) · **Zaman-dışı** (bilinçli çağlar-arası karışım etkinliği; nadir ve etiketli olmalı). **Mağaza etiği:** Mitik içerik asla gerçek tarih olarak pazarlanmaz; EDU mod mağaza filtreleri Mitik IAP'leri gizleyebilir.

### 14.7 Riskler ve açık sorular (live-ops'a özel; §17 ile çapraz-referans)

| Risk | Azaltım |
|---|---|
| Kültürel hassasiyet (özellikle Maya–Mexica ritüel çerçevesi, kutsal alanlar) | Araştırma dosyalarına danış; kutsal şiddeti gore-juice olarak kullanma; tahkimat ve zanaat öne çık |
| Kapsam: 11 civ × sezonlar | Yıl-1 başlıkları 7–8; kalanlar cameo; sistemleri yeniden kullan, sanat kitini değiştir |
| EDU cezalandırıcı hissediyor | Arcade'e göre aynı temizleme için asla ödülü azaltma; sadece içerik filtreleri |
| Async raid toksisitesi | Tavanlar, anlık görüntüler, opt-in defans yayını |
| Jenerik TD klonu hissi | Museum + etiketler + civ kaleleri kalıcı hendek olarak |

**Açık sorular:** S8 başlığı Persia mı Japan mı (S3 retention'ına göre civ bazında karar). Co-op 2 mi 3 mü oyuncu (mobil ısı bütçesi). Belgelenmiş quizler asla güç vermez mi (öneri: sadece kozmetik/XP).

---

## 15. UI/UX hub, kontroller

*(Kaynak: `GDD-CAMERA-JUICE.md` §6–§8, `GDD-LIVEOPS-FTUE-META.md` §5, §9 ve §10 ile çapraz-referans)*

### 15.1 HUD kimyası — kamera etrafında

Kaynak/dalga/can sayaçları **sabit kenarlarda**; kamera pan'iyle birlikte hareket eden dünya-uzayı world-space spam **yok**. Alarmlar kenar chevron'ları + opsiyonel focus-snap ile verilir — **asla sadece** ekran sarsıntısıyla. Yerleştirme hayaleti (placement ghost) yüksek-kontrast geçerli/geçersiz durumları gösterir; renk-körlüğü modlarına saygı duyar (şekil + renk, sadece renk değil).

**Doğrulama kapısı (her harita bu 7 soruyu VFX kapalıyken geçmeli):** (1) Dalga 1'den önce her giriş → çıkışı izleyebiliyor musun? (2) Build node'ları dekorasyondan görsel olarak ayırt edilebiliyor mu? (3) Kale, yolun son %20'sini kapatmadan bahis olarak okunuyor mu? (4) Bestiary açmadan hızlı/zırhlı/uçan/boss önceliğini söyleyebiliyor musun? (5) Sızma olduğunda hata noktası döndürmeden ekranda mı? (6) Kule menzil halkaları projeksiyon altında dürüst mü? (7) Zirve juice'ında hâlâ düşman **hattını** görebiliyor musun? **(5) veya (7) başarısız olursa VFX yoğunluğunu kes veya kamerayı yükselt — bandaj olarak serbest orbit ekleme.**

### 15.2 Kontroller — dokunuş/mouse/gamepad

| Eylem | Mobil (dokunuş) | PC (mouse+klavye) | Gamepad (Steam Deck sınıfı) |
|---|---|---|---|
| Kule yerleştir | Slot'a dokun → seç → onayla | Sürükle-bırak veya tıkla-yerleştir | D-pad slot gezinme + A onay |
| Kule yükselt/sat | Kuleye dokun → radiyal menü | Sağ-tık radiyal menü | Kuleye odaklan → Y/X kısayolları |
| Kamera pan (kilitli açıda) | İki-parmak sürükle (sınırlı alan) | Kenar-pan veya orta-tık sürükle | Sağ analog, sınırlı alan |
| Hız kontrolü (1×/2×/3×) | Üst-bar buton | Boşluk/sayı tuşları | Omuz tuşları döngü |
| Duraklat/menü | Üst-sol köşe | Esc | Start |
| Focus-snap (sızma/boss) | Alarm chevron'ına dokun | Tıkla veya otomatik-önerilen tuş | Sağ tetik tut |

**İlke:** Kilitli yüksek-oblik kamera (§5) serbest 3D rotasyon sunmaz; tüm platformlarda **aynı taktik bilgi** aynı girdi karmaşıklığıyla ulaşılabilir olmalı — mobilin "basitleştirilmiş", PC'nin "gerçek" versiyon olduğu hissi yasak (§1.4 parite ahlaki taahhüdü).

### 15.3 Hub navigasyonu (meta ekranı)

Ana hub şu sırayla önceliklendirilir: (1) **Kampanya haritası** (birincil CTA, her zaman merkezde). (2) **Günlük/haftalık görevler** (üst-bar rozeti). (3) **Museum/Codex** (yan sekme). (4) **Mağaza** (kozmetik + pass, asla ekranı açılışta ele geçirmez). (5) **Sosyal** (L5'e kadar gizli/küçük rozet, §12.1). EDU/ARC rozeti her zaman üst-köşede görünür (§9.6/§14.6).

**Yasak desenler:** Açılışta tam-ekran mağaza takeover'ı. Kampanya CTA'sını gölgeleyen animasyonlu "sınırlı teklif" pop-up'ı girişte. Sosyal özellikleri zorunlu ilk-oturum modal'ı olarak gösterme.

### 15.4 Erişilebilirlik (pazarlık konusu olmayan ayarlar)

| Ayar | Varsayılan | Davranış |
|---|---|---|
| **Ekran sarsıntısı yoğunluğu** | %100 | Tüm kamera sarsıntılarında küresel çarpan 0–%100 |
| **Hareketi azalt (Reduce motion)** | Kapalı (OS Prefers-Reduced-Motion varsa saygı duyar) | Orbit sinematiklerinin otomatik oynatımını, focus-snap hareketini, FOV vuruşlarını devre dışı bırakır; kalan hareketi ~%20 söndürür veya HUD flaşlarıyla değiştirir |
| **Flaş/fotosensitivite** | Tam | Kapalı = strobe yok; ≤3 Hz eşdeğeri düz renk darbeleriyle değiştirilir, düşük kontrast |
| **Efekt yoğunluğu** | Yüksek | Parçacık/distorsiyon ölçekler |
| **Hasar sayıları** | Açık | Kapalı veya "sadece büyük" |
| **Kamera asistanı** | Açık | Yol şeritleri, sızma chevron'ları, tıkanmada kale hayaletlemesi |
| **Renk körlüğü paletleri** | Kapalı | Deuteranopi/Protanopi/Tritanopi UI + takım renkleri |
| **UI ölçeği/metin** | %100 | Özellikle mobil + Steam Deck sınıfı cihazlar için |

**İlke:** *Değiştir, silme.* Sarsıntı %0 hâlâ bir HUD kenar vuruşu + ses alır. Reduce Motion hâlâ kale kimliğini sabit tabloylarla verir. **Test kapısı:** Sarsıntı %0, Efektler Düşük, Reduce Motion Açık ile geç bir haritayı oyna — strateji okunmazsa tasarım başarısız oldu, oyuncu değil.

### 15.5 Juice bütçesi — vuruş geri bildirimi tahtayı bozmadan

**Geri bildirim yığını (yerelden küresele):** (1) Yerel aktör juice'ı — hedefte çarpma flaşı, kulede geri tepme, ölüm dissolve'u, yüzen sayı (aç/kapa). (2) Ses — katmanlı SFX + ducking (Reduce Motion oyuncuları için kritik). (3) UI tik — combo/ödül/can değişimi mikro-animasyonla. (4) Ekran-uzayı aksanları — kısa vinyet, kenar flaşı, kromatik *sadece* a11y izin veriyorsa. (5) Kamera — son çare, en küçük genlik.

**Ekran sarsıntısı sınırları:** Kule vuruşu/küçük öldürme ≤0.12–0.18s; elit/patlama ≤0.18–0.25s; kale hasarı (can kaybı) ≤0.20–0.30s (kırmızı kenar + ses stinger'ıyla eşleştir); boss slam/kale ihlali ≤0.35s (rotasyondan kaçın, olay başına bir kez). **Yığılma kuralı:** eşzamanlı sarsıntılar toplanmaz, maksimumu alır (yumuşak harman). **Varsayılan yasak:** sürekli rotasyonel sarsıntı, >0.5s sönümsüz sarsıntı, tam-ekran beyaz strobe, duraklatma menüsü açıkken devam eden sarsıntı.

---

## 16. Teknik/üretim kapsamı, launch kesiti

*(Kaynak: `GDD-RISKS-SWOT-SCOPE.md`, §1.4/§8/§9/§12 ile çapraz-referans)*

### 16.1 Platform ve mimari ilkeleri

| İlke | Uygulama |
|---|---|
| **Kampanya önce-offline** | Story/skill modları internet olmadan tam oynanabilir; sosyal/live-ops/leaderboard online gerektirir, kampanyayı bloklamaz |
| **Çapraz-platform parite** | Mobil (iOS/Android) + PC/Steam aynı kazanma/satın alma kuralları (§1.4, §13.13) |
| **Çapraz-kayıt** | Hesap-bağlı kayıt (Apple/Google/Steam/e-posta) bulut senkronize; cihaz kaybı asla ilerleme kaybı olmamalı |
| **Modüler kule kiti** | Sanat + kod, medeniyet başına "trim sheet" değişimiyle temel arketip iskeletlerini yeniden kullanır (R7 azaltımı, bkz. §17) |
| **Sunucu-yetkili kritik yollar** | Leaderboard skorları, kuşatma/raid sonuçları, IAP doğrulaması — client-authoritative değil |
| **Telemetri gün-1'den** | Temizleme oranı, IAP durumuna göre temizleme oranı, inceleme-teması etiketleme, ad opt-in oranı (§13.15) |

### 16.2 Launch kesiti (neyin gemiye bindiği)

**Ship (lansman):** Tutorial Babylon (Act 0, tam FTUE) + Egypt + Persia + Greece (dual-keep showcase civ'i) + Rome teaser haritası + 1 boss action'ı + günlük döngü + kozmetik mağazası + rewarded reklamlar. Skill modları (Story/Normal/Hard/Legend/Historical/Myth §7.8) tam launch setinde aktif. Sosyal Faz A (§12.10): cohort'lar, tithe, async Watch kuşatması, paylaşım kartları, referral, arkadaş challenge'ı, adil haftalık bracket'ler. Monetizasyon yumuşak-lansman fazı (§13.14): enerji yok, rewarded çift gold, başlangıç paketi, 2–3 kozmetik — **henüz sezon pass'i yok** (temizleme oranlarını ölçmek için).

**+90 gün:** China veya Japan act'i (metrik-güdümlü seçim) + Battle Pass Sezon 1 + Lonca async genişlemesi (Faz B, §12.10) + ilk civ paketleri (Rome/Egypt).

**Yıl 1:** Kalan civ'ler sezon spotlight'larıyla drip (§14.3 takvimi) — Maya–Mexica, Viking, China, Japan, ardından Persia/Japan hangisi S8'de değilse. Mongol ve European Castle Yıl-2 başlıkları olarak cameo'larla (§14.3).

### 16.3 Kapsam disiplini — neden bu sıralama

11 medeniyetin tamamı gün-1'de göndermek **kapsam canavarıdır** (R1, §17.2). Launch kesiti şu kriterlerle seçildi: (1) **Fantazi çeşitliliği** — Babylon (nehir/sulama), Egypt (Nil/mumyalama tuzağı benzetmesi), Persia (Kral Yolu/ölümsüzler), Greece (dual-keep/falanks) dört farklı savunma dilbilgisini gün-1'de gösterir. (2) **Üretim tekrarı en aza indirildi** — dört civ, ortak kule arketiplerini (§6) farklı deri/animasyonla yeniden kullanır. (3) **Rome teaser** tam act olmadan Sezon 2 hazırlığı kurar (erken pazarlama hook'u). (4) **Her yeni civ bir sezon teması olur** — rastgele içerik damlası değil, §14.3'teki "tarih takvimi" hissi.

### 16.4 Üretim boru hattı (kule/civ başına)

| Aşama | Çıktı | Tekrar kullanılabilirlik |
|---|---|---|
| Arketip tasarımı (§6.3) | 8–10 çekirdek kule fiili (menzil/AoE/yavaşlatma/kontrol vb.) | Tüm civ'lerde paylaşılır |
| Civ deri kiti | Model + doku + VFX + ses paleti | Civ'e özel, arketip iskeletine takılır |
| Balans geçişi | Hasar/maliyet/upgrade eğrileri | Playtest telemetrisiyle revize edilir, kilitli değil |
| Codex/Museum varlığı | Kart metni + doğruluk etiketi + 3D diyorama | Civ + kule başına bir kez üretilir, sonsuza dek yeniden kullanılır |
| Sensitivity/FOLK geçişi | ARCH/LIT/FOLK/SPN/MOD/CHR etiket denetimi | Her civ'in lansmanından önce zorunlu (§0 kanıt disiplini) |

### 16.5 Teknik risk azaltımı (mühendislik)

| Risk | Azaltım |
|---|---|
| Offline/online bölünme hataları | Kampanya önce-offline mimarisiyle tasarlanır; sosyal/senkron katman ayrı servis sınırı |
| Hile liderlik tabloları | Sunucu-yetkili skor gönderimi + imkânsız DPS/hız için replay bayrakları (§12.6) |
| Sanat maliyeti aşımı | Modüler kule kiti + civ trim sheet'leri (§16.4) |
| Balans borcu | Telemetri + haftalık yama ritüeli (§13.15, §17.2 R6) |
| Platform politika ihlali (reklam/IAP/kids) | §13.5 platform politika notları launch-öncesi denetim listesi olarak kullanılır |

---

## 17. SWOT, riskler, odak öncelikleri

*(Kaynak: `GDD-RISKS-SWOT-SCOPE.md`, tüm bölümlerle çapraz-referans)*

### 17.1 SWOT

**Güçlü yönler (iç):**

1. 11 medeniyetlik kanıta-dayalı araştırma kütüphanesi — Babylon Act 0 dahil, her civ için kaynak taranmış (§8).
2. Net farklılaşma: tarihsel kale mimarisi (dual-keep, masugata, concentric — §6.7) + eğitici Codex (§9.3), rakiplerin sahip olmadığı bir "kanıt katmanı."
3. Adil F2P tasarım ilkeleri — rakip acı noktalarından (§2, §3) doğrudan türetilmiş kurallar (enerji yok, cosmetics-only power).
4. Benzersiz harita fiilleri: her civ'in kalesi farklı bir savunma bilmecesi sunar, jenerik "tek düz yol" TD klonundan ayrışır.
5. Eğitim/serious-game yan pazar potansiyeli — Museum/Codex sistemi (§9.3) okul/kurum lisanslarına açılabilir (uzun vade, kanıtlanmamış).

**Zayıf yönler (iç):**

1. **Kapsam canavarı** — 11 act × kule seti × sanat = üretim patlaması riski (bkz. R1 aşağıda).
2. Tarihsel doğruluk + eğlence gerilimi: FOLK etiket disiplini (§0.4, §8) şart, aksi halde ya kuru ders kitabı ya da tarihsel yanlış bilgi olur.
3. Maya–Mexica hassasiyeti ve "Maya ≠ Aztec" ayrımı (§0.4, §8.9) — kültürel saygı maliyeti, hassasiyet danışmanlığı gerektirir.
4. TD pazarı doygun (BTD6, Kingdom Rush serisi köklü) — kullanıcı kazanımı (UA) pahalı, keşif zor.
5. Küçük stüdyo ölçeğindeyse mid-core polish eksikliği riski — juice/kamera/balans kalitesi (§5, §7) rakip seviyesine ulaşmayabilir.

**Fırsatlar (dış):**

1. BTD6/Kingdom Rush oyuncuları "yeni adil TD" arıyor — store review sentiment'i (§3) bunu doğruluyor (P2W'den bıkkınlık).
2. Tarih podcast/YouTube collab potansiyeli — Codex'in doğal pazarlama uzantısı (§11.7 viral kancalar).
3. Sezon = yeni civ damlası (live-ops) — hem içerik hem pazarlama olayı (§14.3).
4. Co-op/lonca async viral etkisi — düşük geliştirme riskiyle yüksek paylaşım potansiyeli (§12.4, §11.7).
5. Museum/edu lisans hayalleri — uzun vadeli, kanıtlanmamış ama opsiyonel B2B geliri.

**Tehditler (dış):**

1. Ninja Kiwi (BTD6) / Ironhide (Kingdom Rush) yeni içerik/güncelleme çıkarırsa pazar payı baskısı.
2. Klon TD + sahte reklam mağaza kirliliği — store keşif algoritmalarını bozan düşük kaliteli taklitler.
3. Platform politikası (reklam, çocuk hedefli içerik kuralları, loot box düzenlemesi) — özellikle AB/Kore loot box şeffaflık yasaları (§13.12).
4. Monetizasyon kayarsa (P2W algısı sızarsa) review-bombing riski — tek bir kötü güncelleme itibarı yıllarca lekeler.
5. AI-üretilen sanat backlash'i — sanat pipeline'ında AI araçları kullanılırsa şeffaflık ve topluluk tepkisi riski.

### 17.2 Risk kayıt defteri

| ID | Risk | Etki | Olasılık | Mitigasyon |
|---|---|---|---|---|
| **R1** | Kapsam sürünmesi — 11 civ gün-1 lansmanı | Kritik | Yüksek | Launch: Act 0–III + 1 showcase civ; kalanı damla (§16.2) |
| **R2** | P2W algısı | Kritik | Orta | Cosmetics-only power; kamuya açık tasarım kitabı/manifesto (§13.1) |
| **R3** | Kültürel yanlış temsil | Yüksek | Orta | Hassasiyet geçişi; FOLK etiketleri; danışman incelemesi (§0.4, §8.9) |
| **R4** | D7 retention'ının düz kalması | Yüksek | Orta | Boss/haftalık/lonca yol haritası launch-öncesi hazır (§11, §14) |
| **R5** | Reklam yorgunluğu | Orta | Yüksek | Sadece rewarded; reklam-kaldır IAP seçeneği (§13.4) |
| **R6** | Balans borcu | Yüksek | Yüksek | Telemetri + haftalık yama ritüeli (§16.5) |
| **R7** | Sanat maliyeti aşımı | Yüksek | Yüksek | Modüler kule kiti + civ trim sheet'leri (§16.4) |
| **R8** | Offline/online bölünme hataları | Orta | Orta | Kampanya önce-offline mimarisi (§16.1) |
| **R9** | Hile liderlik tabloları | Orta | Orta | Skill bracket'leri + ileride replay doğrulama (§12.6) |
| **R10** | Yasal (müzik/tarih IP) | Orta | Düşük | Kamuya açık tarih malzemesi; özgün ses tasarımı |
| **R11** | Sezon içeriği tükenmesi (live-ops kuraklığı) | Yüksek | Orta | §14.2'deki 6–7 haftalık kadans + rezerv içerik tamponu |
| **R12** | Lonca özelliği çok erken/zorunlu gönderilirse solo döngü zedelenir | Orta | Orta | Lonca L5 sonrası opsiyonel, Faz A/B/C kademeli rollout (§12.10) |

### 17.3 Odak önceliği (neden — RICE benzeri sıralama)

**P0 — oyunu "bir kez daha" yaptıran (launch blocker):**

1. Combat hissi + juice + net karşı-oyun okunabilirliği (§5, §6).
2. Adil ekonomi / enerji yok (§7.6, §13.2).
3. FTUE 10 dakikalık ustalık kıvılcımı (§10).
4. Act 0 Babylon cilası — markanın ilk izlenimi (§8.2).

**P1 — geri getiren (D7–D30 kritik):**

5. Günlük/haftalık döngü + boss kuşatması (§11.4, §14.5).
6. Codex koleksiyonu (§9.3).
7. 3-yıldız / Heroic modları (§9.5).
8. Battle pass kozmetikleri (§13.7).

**P2 — yayan (viral/paylaşım):**

9. Paylaşılabilir zafer/Codex kartları (§12.5, §11.7).
10. Lonca async (§12.4).
11. Challenge tarayıcısı / haftalık tohum modu (§11.7).

**P3 — genişleten (Yıl 1+ ölçek):**

12. Yeni civ act damlası (§14.3).
13. Co-op modu (açık soru, §19).
14. Harita editörü (açık soru, §19).

**Sıralama mantığı:** P0 olmadan oyun "bir kez daha" hissi vermez — hiçbir live-ops veya monetizasyon bunu telafi edemez. P1, ilk haftadan sonra geri gelme sebebidir. P2 organik büyümeyi (ücretsiz UA) besler. P3 yatırım gerektirir ama sadece sağlam bir P0–P1–P2 üzerine anlamlıdır — sıralamayı tersine çevirmek (örn. co-op'u P0'dan önce inşa etmek) kaynakları temel deneyimden çeker.

### 17.4 Launch scope kesiti — özet (§16.2 ile çapraz-referans)

**Ship:** Tutorial Babylon + Egypt + Persia + Greece (dual-keep showcase) + Rome teaser haritası + 1 boss + günlük döngü + kozmetik mağazası + rewarded reklamlar. **90 gün:** China veya Japan act'i + BP Sezon 1 + lonca async. **Yıl 1:** Kalan civ'ler sezonlarla (§14.3).

### 17.5 Risk × öncelik matrisi — hangi risk hangi önceliği tehdit eder

| Öncelik katmanı | En büyük tehdit eden risk | Neden |
|---|---|---|
| P0 (bir kez daha) | R6 Balans borcu | Kötü balans ilk-oturum juice'ını ve adaleti bozar |
| P1 (geri getiren) | R4 Retention düz D7 | Günlük/haftalık/boss sistemleri zamanında gelmezse D7 çöker |
| P2 (yayan) | R9 Hile liderlik tabloları | Adaletsiz tablo paylaşım/viral güvenini zehirler |
| P3 (genişleten) | R1 + R7 Scope creep + sanat maliyeti | Her yeni civ üretim borusunu (§16.4) test eder |

---

## 18. KPI'lar ve başarı kriterleri

*(Kaynak: `GDD-RETENTION-VIRAL.md` §2, `GDD-MONETIZATION.md` §9/§13, §11/§13/§17 ile çapraz-referans)*

### 18.1 Retention KPI'ları (§11.2 hedef bantlarının özeti)

| Ufuk | Taban (kritik eşik) | Sağlıklı (strateji midcore) | Gerilme (üst TD/hobbyist) |
|---|---:|---:|---:|
| **D1** | <%22 | %28–35 | %38–45 |
| **D7** | <%5 | %10–15 | %15–22 |
| **D30** | <%2 | %4–7 | %8–12 |
| **D7/D1 oranı** | <0.25 | 0.35–0.45 | ≥0.45 |
| **D30/D7 oranı** | <0.30 | 0.40–0.55 | ≥0.55 |

**Yorumlama kuralı (§11.2):** Oranlar mutlak vanity sayılarından daha önemlidir. Güçlü D1 + çöken D7 = FTUE şekeri, zayıf çekirdek döngü demektir — bu bir kırmızı bayrak, bir zafer değil.

### 18.2 Monetizasyon KPI'ları

| Metrik | Taban | Sağlıklı | Not |
|---|---|---|---|
| **IAP/reklam geliri karışımı** | — | %70–80 IAP / %20–30 reklam | Midcore TD hedefi (§13.5) |
| **Ödeyen dönüşüm oranı** | <%1.5 | %2–4 | Minnow/dolphin havuzu sağlığı |
| **Sezon pass alım oranı (satın alanlar arası)** | — | Ödeyenlerin %30–50'si | Dolphin geri gelme sinyali |
| **F2P kampanya temizleme oranı** | <%70 | ≥%70 | §13.3 kuralı: altına düşerse puan çöküşü riski |
| **Gelir payı — sezon pass'i** | — | %35–45 | §13.14 hedef dağılımı |
| **Gelir payı — civ/içerik paketleri** | — | %20–30 | |
| **Gelir payı — kozmetik/prestij** | — | %15–25 | |
| **Gelir payı — başlangıç/küçük IAP** | — | %5–10 | |
| **Gelir payı — reklam (rewarded)** | — | %10–20 | |

### 18.3 Mağaza/itibar KPI'ları

| Metrik | Taban | Sağlıklı | Aksiyon eşiği |
|---|---|---|---|
| **Mağaza puanı (iOS/Android ortalaması)** | <4.0 | ≥4.3 | <4.3 ise kozmetik sandık gibi riskli özellikler ertelenir (§13.11) |
| **1★ inceleme teması dağılımı** | — | §13.12 listesindeki 12 desenden hiçbiri baskın değil | Herhangi biri sıçrarsa kill switch (§13.15) |
| **Reklam şikayeti oranı (destek bileti)** | — | Düşük ve düz | Rewarded-only politikasının kanıtı |
| **Hesap/bulut kayıt hata oranı** | ~0 | ~0 | §13.12 madde 9 — ship blocker |

### 18.4 Sosyal/viral KPI'ları

| Metrik | Sağlıklı hedef | Bağlı özellik |
|---|---|---|
| **Lonca katılım oranı (L5+ hesaplar arası)** | %30–50 | §12.2 (opt-in olduğu için %100 hedeflenmez) |
| **Async kuşatma haftalık katılım (lonca üyeleri arası)** | %60+ | §12.3 |
| **Paylaşım kartı → geri-dönüş deep-link oranı** | Ölçülür, hedef büyütülür | §12.4, §11.12 |
| **Referral kalite-kapısından geçen davetli oranı** | Ölçülür | §12.5 |
| **Report/mute kullanım oranı (aktif kullanıcı başına)** | Düşük ve düz, artmıyor | §12.7 anti-toksisite kanıtı |

### 18.5 Teknik/üretim KPI'ları

| Metrik | Hedef | Bağlı risk |
|---|---|---|
| **Haftalık balans yaması ritmi** | Kaçırılmaz kadans | R6 (§17.2) |
| **Civ üretim süresi (arketip → lansmana hazır)** | Trim-sheet tekrar kullanımıyla düşen eğri | R7 (§17.2) |
| **Sunucu-yetkili doğrulama kapsamı (leaderboard/kuşatma/IAP)** | %100 | R9 (§17.2) |
| **Offline kampanya oynanabilirlik oranı (internet kesildiğinde)** | %100 | R8 (§17.2) |

### 18.6 Kuzey yıldızı tek metrik (varsa)

Eğer tek bir "sağlık" metriği seçilecekse: **D7/D1 oranı × F2P kampanya temizleme oranı.** Birincisi habit'in tuttuğunu, ikincisi adaletin gerçek olduğunu kanıtlar; ikisi birlikte düşerse hiçbir gelir metriği onu telafi edemez (§11.2, §13.3 çapraz-referansı).

---

## 19. Açık sorular / sonraki adımlar

*(Kaynak: tüm araştırma dosyalarından toplanan açık sorular; tek yerde konsolide)*

### 19.1 İçerik/sezon kararları

1. **S8 başlığı: Persia mı Japan mı?** S3 retention verisine göre civ-bazlı karar (§14.3, §14.7). Metrik: hangi civ spotlight'ı sırasında daha yüksek D7/D30 elde ediliyor.
2. **Mongol ve European Castle'ın Yıl-2 başlık sırası** — cameo performansına göre (§14.3 Yıl-1 cameo planı) hangisi önce başlık olur.
3. **Maya–Mexica ritüel çerçevesi son onayı** — hassasiyet danışmanlığı tamamlanana kadar açık (§8, §14.7, §17.2 R3).

### 19.2 Sosyal/co-op kararları

4. **Co-op boss 2 mi 3 oyunculu mu?** Mobil performans/ısı bütçesine bağlı (§14.5, §14.7). Karar: performans profillemesi sonrası.
5. **Gerçek-zamanlı sohbet ne zaman açılır (Faz C)?** Sadece moderasyon bütçesi varsa (§12.10). Bütçe onayı bekliyor.
6. **Lonca-vs-lonca hayalet raid'leri bracket algoritması** — Faz B'de detaylandırılacak (§12.10).

### 19.3 Monetizasyon kararları

7. **Kozmetik sandıklar (crates) ne zaman gemiye biner?** Sadece mağaza puanı ≥4.3 ise (§13.14, §18.3). Karar: launch-sonrası metrik kapısı.
8. **Belgelenmiş (Documented) quizler asla güç vermemeli mi?** Öneri: sadece kozmetik/XP (§14.7) — tasarım ekibi son onayı bekliyor.
9. **Premium günlük track ($4–6/ay) ne zaman eklenir?** Sadece D7 ürün-pazar uyumundan sonra (§11.6).

### 19.4 Teknik/üretim kararları

10. **Hangi civ'ler Yıl-1'de tam act, hangileri sadece sezon-spotlight cameo'su olarak kalır?** §14.3 taslağı var ama son roster onayı playtest + pazarlama verisiyle kilitlenecek.
11. **Loot box/gacha hukuki incelemesi** — BE/NL gibi jurisdiction'larda gacha göndermeden önce (§13.11) zorunlu; henüz tamamlanmadı.
12. **Cross-save/paylaşılan satın alma mağaza politikası desteği** — Apple/Google/Steam'in izin verdiği kapsam netleşene kadar açık (§16.1).

### 19.5 Sonraki adımlar (öneri sırası)

1. Playtest ile §4/§6/§7'deki sayısal dengeleri (balance numbers) kilitle — bunlar tasarım *hedefleri*, sabit değil (§0 doküman durumu notu).
2. Act 0 Babylon'u (§8, §10) prototipe dönüştür; FTUE 10-dakikalık ustalık kıvılcımını gerçek oyuncularla doğrula.
3. Modüler kule kitini (§16.4) inşa et, ikinci civ'e (Egypt) uygula; üretim süresini ölç.
4. Hassasiyet/FOLK denetim sürecini (§0, §16.4) Maya–Mexica'dan önce resmileştir.
5. §18'deki KPI panosunu telemetri altyapısıyla birlikte kur — gün-1'den itibaren veri topla.

### 19.6 Doküman bakım kuralı

Bu GDD **canlı bir belgedir**, tek seferlik bir teslimat değil. Her playtest turu §4/§6/§7/§9'daki sayısal dengeleri güncellemeli; her live-ops fazı sonunda §14/§18 gerçek telemetriyle yeniden kalibre edilmeli; her yeni civ eklendiğinde §8 civ profili + §16.4 üretim boru hattı kaydı eklenmeli. Sürüm geçmişi (changelog) tutulması önerilir: hangi bölüm, hangi playtest/telemetri bulgusuyla, hangi tarihte değişti.

---

## Ek A. Terimler sözlüğü

*(Alfabetik; her terim ilk geçtiği bölüme referans verir)*

| Terim | Tanım |
|---|---|
| **Act** | Bir medeniyetin kampanya bölümü (Act 0 = Babylon tutorial, Act I–X = diğer 10 civ). Her act kendi haritalarını, düşman rosterini ve boss'unu içerir (§8). |
| **ARCH / LIT / FOLK / SPN / MOD / CHR** | Kanıt etiketleme sistemi: Arkeoloji, antik edebi kaynak, folklor/turistik efsane, dönem propagandası, modern akademik yorum, kronoloji uyarısı (§0.4). |
| **Arcade Mode** | Mit birimlerinin açık olduğu, balans-öncelikli oyun modu; Educational Mode'un karşıtı (§9.6). |
| **Async kuşatma (Watch Siege)** | Gerçek-zamanlı olmayan, replay/skor tabanlı lonca kuşatma etkinliği (§12.3). |
| **Battle Pass (BP)** | Sezonluk, ücretsiz + premium izlek; ilerleme stage-yıldızı ve meta XP'sinden beslenir (§13.7). |
| **CAPS$ çerçevesi** | Monetizasyon değerlendirme modeli: Cosmetics, Access, Power, Status, Real-money (§13.1). |
| **Civ Mastery** | Bir medeniyete özel ilerleme yolu; yıldız/XP biriktirerek kozmetik ve taktik (güç değil) açar (§9.2). |
| **Codex / Museum** | Oyun-içi tarihsel ansiklopedi; kart + doğruluk etiketi + 3D diyorama içerir (§9.3). |
| **Early call (erken çağırma)** | Bir sonraki dalgayı vaktinden önce tetikleme mekaniği; ekonomi/risk dengesi öğretir (§7.6). |
| **Educational Mode** | Doğruluk etiketleri açık, mit birimleri kapalı/etiketli oyun modu (§9.6). |
| **Favor (İnanç/Onay kaynağı)** | Dört kaynaktan biri; mit güçleri ve civ-özel ultimate'ları besler (§6.6). |
| **FTUE** | First-Time User Experience — ilk oturum onboarding akışı (§10). |
| **Hook Model** | Tetikleyici → Eylem → Değişken Ödül → Yatırım döngüsü; retention tasarımının temeli (§11.1). |
| **Keep (Kale)** | Her medeniyetin savunma merkezi yapısı; 7 katmanlı tarihsel mimariden 4 oynanabilir strataya indirgenir (§6.4). |
| **LHOK (Locked High-Oblique Keep) kamera** | Sabit yaw/pitch açılı, sadece pan+zoom izin veren kamera sistemi (§5.1). |
| **Maya ≠ Aztec kuralı** | Maya ve Mexica (Aztek) medeniyetlerinin ayrı, birbirine indirgenmeyen kültürler olarak ele alınması zorunluluğu (§0.4, §8.9). |
| **Rewarded ads (ödüllü reklamlar)** | Sadece oyuncu isteğiyle (opt-in) izlenen, zorunlu olmayan reklam formatı; forced interstitial'ın karşıtı (§13.4). |
| **Sensitivity pass (hassasiyet geçişi)** | Bir medeniyet içeriğinin kültürel danışman/uzman incelemesinden geçirilmesi süreci (§8.9, §16.4). |
| **Skill bracket** | Liderlik tablosu segmentasyonu; cüzdan gücüne göre değil, oyuncu becerisine göre gruplama (§12.6). |
| **Star economy (yıldız ekonomisi)** | Harita başarım ölçütü (1–3 yıldız); zorluk moduna ve performansa bağlı (§7.7, §9.5). |
| **Trim sheet** | Bir civ'e özel doku/malzeme paleti; ortak kule arketip iskeletine takılan sanat katmanı (§16.4). |
| **Verb (fiil) — 5 savunma fiili** | Shoot (Vur), Hold (Tut), Shape (Şekillendir), Punish (Cezalandır), Pivot (Dönüştür) — tüm defans mekaniklerinin temel taksonomisi (§6.1). |

---

## Ek B. Do / Don't kontrol listesi (üretim boyunca kullanılacak)

*(Her madde ilgili bölüme referans verir; bu liste her feature review'de kontrol edilmelidir)*

### Yap (Do)

1. Her civ act'i için önce fiil/fantazi/mimari üçlüsünü tasarla, sonra kule listesini doldur (§6, §8).
2. Her tarihsel iddiayı ARCH/LIT/FOLK/SPN/MOD/CHR etiketle; FOLK'u asla ARCH gibi sunma (§0.4).
3. Kampanyayı tamamen offline oynanabilir tasarla (§16.1).
4. Enerji sistemini kampanyanın ilk 48 saatinden tamamen çıkar (§13.2, §11.8).
5. Reklamları sadece rewarded/opt-in olarak sun; asla zorunlu interstitial ekleme (§13.4).
6. Güç veren her şeyi playtest/skill ile kazandır; parayla satılan her şeyi kozmetik/convenience ile sınırla (§13.1–§13.2).
7. FTUE'nin ilk 10 dakikasını saniye-saniye script'e sadık kal; her adımı ölçülebilir bir metrikle bağla (§10).
8. Kamerayı kilitli yüksek-oblik açıda tut; sadece pan+zoom sağla, serbest rotasyon ekleme (§5.1).
9. Erişilebilirlik ayarlarını (sarsıntı, hareket azaltma, renk körlüğü) gün-1'den itibaren gemiye bindir (§5.8, §15.4).
10. Her sosyal özelliği opt-in yap; guild/PvP'yi asla zorunlu ilerleme kapısı olarak kullanma (§12.1).
11. Launch kesitini kapsam disiplinine göre sınırla — 4 civ + 1 teaser ile başla, kalanı damla (§16.2–§16.3).
12. Balans değişikliklerini haftalık ritüel olarak telemetriyle besle (§16.5, §17.2 R6).
13. Her yeni civ'i sensitivity pass'ten geçirmeden gemiye bindirme (§8.9, §16.4).
14. Mağaza puanını (§18.3) canlı bir kill-switch tetikleyicisi olarak izle.

### Yapma (Don't)

1. Kampanyaya enerji/bekleme duvarı ekleme (§13.2, §11.8 anti-pattern).
2. Zorunlu, kaçınılamaz interstitial reklam gösterme (§13.4).
3. Güç veren gacha/loot box satma — sadece kozmetik RNG kabul edilebilir, o da şeffaf oranlarla (§13.11).
4. FOLK/turistik efsaneyi ARCH kanıtıyla aynı görsel ağırlıkta sessizce sunma (§0.4).
5. Maya ve Mexica (Aztek) kültürlerini birbirine indirgeme veya karıştırma (§0.4, §8.9).
6. Kamerayı serbest 3D rotasyona açma veya "bandaj" olarak orbit ekleme (§5.1, §15.4).
7. Sosyal özellikleri (lonca, PvP) gün-0 zorunlu modal olarak dayatma (§12.1, §15.3).
8. 11 civ'in tamamını gün-1'de göndermeye çalışma — kapsam canavarını besleme (§16.3, §17.2 R1).
9. Hub'ı açılışta tam-ekran mağaza takeover'ıyla karşılama (§15.3, §15.8).
10. Streak sistemlerini "tax" (bir gün kaçırınca sıfırlama, af yok) olarak tasarlama (§11.5).
11. Reklam/IAP/kids platform politikalarını launch-öncesi denetim listesinden geçirmeden gönderme (§13.5, §16.5).
12. Balans değişikliklerini telemetri kanıtı olmadan "hissi öyle" diye yapma (§16.5, §17.2 R6).
13. Erken-çağırma butonu gibi kas-hafızası pozisyonlarını sezondan sezona değiştirme (§15.6, §15.8).
14. Hile/imkânsız skorları sunucu-taraflı doğrulama olmadan liderlik tablosunda gösterme (§12.6, §17.2 R9).

---

## Ek C. Kaynak haritası

*(Bu GDD'nin sentezlendiği araştırma dosyaları ve hangi bölüm(ler)i besledikleri)*

| Araştırma dosyası | Beslediği bölüm(ler) |
|---|---|
| `GDD-COMPETITORS.md`, `GDD-COMPETITORS-TD.md` | §2 (Rakip analizi ve konumlandırma) |
| `GDD-RETENTION-VIRAL.md` | §11 (Retention/habit/viral/bildirimler), §18.1 (Retention KPI'ları) |
| `GDD-REDDIT-SENTIMENT.md`, `GDD-REDDIT-DEEP.md`, `GDD-4CHAN-FORUM-SENTIMENT.md`, `GDD-STORE-REVIEWS.md` | §3 (Oyuncu sentiment → ürün kuralları) |
| `GDD-MONETIZATION.md` | §13 (Monetizasyon), §18.2–§18.3 (Monetizasyon/itibar KPI'ları) |
| `GDD-CAMERA-JUICE.md` | §5 (Kamera/sunum/juice/erişilebilirlik), §15 (UI/UX) |
| `GDD-DEFENSE-SYSTEMS.md` | §6 (Defans mekanikleri) |
| `GDD-DEFENSE-ATTACK.md` | §6–§7 (Defans + Saldırı çekirdek döngüsü), §15 |
| `GDD-ATTACK-WAVES.md` | §7 (Saldırı/dalga/düşman/boss) |
| `GDD-WAVES-ECONOMY.md` | §7 (Ekonomi/dalga bileşimi), §4 (Çekirdek döngü) |
| `GDD-CIV-HOOKS.md` | §8 (Medeniyet kampanyası — genel civ profilleri) |
| `GDD-BABYLON-ACT0.md` | §8.2 (Act 0 Babylon detayı), §10 (FTUE) |
| `GDD-LIVEOPS-FTUE-META.md`, `GDD-LIVEOPS-FTUE.md` | §9 (Meta progresyon), §10 (FTUE), §14 (Live ops takvimi), §15 (UI/UX hub) |
| `GDD-SOCIAL-GUILD.md` | §12 (Sosyal/lonca/paylaşım) |
| `GDD-RISKS-SWOT-SCOPE.md` | §16 (Teknik/üretim kapsamı), §17 (SWOT/riskler/öncelikler) |
| `TOWER-DEFENCE-TARIHSEL-ARASTIRMA-INDEX.md` | §8 (11 medeniyet listesi ve her civ'in ayrıntılı tarihsel araştırma dosyalarına yönlendirme) |

**Not:** Tüm araştırma dosyaları mevcuttu ve tam olarak sentezlendi; eksik dosya nedeniyle varsayım yapılmasını gerektiren bir durum oluşmadı.

---
