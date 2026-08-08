# Cursor Skill'leri — Nasıl Kullanılır?

Bu rehber, `~/Repo` altındaki **tüm projelerde** (Sippin, Batten, Glossio, vb.) Cursor skill'lerini nasıl kullanacağını anlatır.

## Kısa cevap: Evet, her projeden kullanabilirsin

Skill'ler projeye özel değil; **bilgisayarına global kurulur**:

```
~/.cursor/skills/
  ├── lets-blueprint/
  ├── lets-release/
  ├── app-store-preflight/
  └── genius-ideas/
```

Cursor'da **hangi klasörü açarsan aç** (ör. `~/Repo/sippin`), Agent chat'te bu skill'leri çağırabilirsin.

---

## Bir kez yapılacak kurulum

Terminal'de:

```bash
chmod +x ~/Repo/install-cursor-skills.sh
~/Repo/install-cursor-skills.sh
```

Bu script:
1. Skill'leri `~/.cursor/skills/` altına kopyalar
2. `~/.zshrc` dosyana ortam değişkenlerini ekler (yoksa)
3. App Store kontrol araçları için `greenlight` kurulumunu hatırlatır

**App Store preflight için ek araçlar** (bir kez):

```bash
brew install revylai/tap/greenlight jq
pip3 install pyyaml
```

---

## Cursor'da skill nasıl çağrılır?

Skill'ler **çoğunlukla cümleyle** tetiklenir. Cursor'da **Agent** sekmesine normal Türkçe/İngilizce yazarsın — agent skill `description` ile eşleşince okur.

Ayrıca kullanıcı seviyesinde **hook**'lar (`~/.cursor/hooks.json` → `beforeSubmitPrompt`) `lets-release` ve `lets-blueprint` ifadelerinde ilgili skill'i hatırlatır. Asıl iş yine skill'dedir.

### Let's Blueprint (yeni uygulama — md paketi)

1. `~/Repo/<proje-adi>/` klasörünü oluştur
2. Cursor'da o klasörü aç (Agent)
3. Fikri / başka agent'ın proje dump'ını yapıştır:

> Let's Blueprint — bu fikir için tüm md paketini hazırla:
> [fikir veya dump]

**Ne olur?** Agent `lets-blueprint` skill'ini uygular: eksikleri sorar (asla assume etmez) → `{SLUG}.md` + Stitch brief + Build blueprint + `AGENTS.md` (+ `tasks/` / `docs/` stub) yazar → `git init` / docs commit → Week 0 (GitHub, Supabase, RevenueCat, …) adımlarını sana yaptırır. **Uygulama kodu yok.**

### Let's Release (markete / TestFlight yükünden önce)

Herhangi bir `~/Repo` projesi açıkken:

> Let's release a new load to TestFlight

veya:

> Markete göndermeden önce let's release — ASC metadata ve ASO'yu güncelle, yük çıkma.

**Ne olur?** Agent `lets-release` skill'ini uygular: preflight/testler → tüm lokalizasyonlarda ASO/keyword (shipped + upcoming feature) → ASC metadata/pricing (credentials varsa). **Binary/build yok** (sen açıkça istemedikçe). **Screenshot'lara dokunulmaz.**

### App Store'a göndermeden önce kontrol (sadece compliance)

Sippin (veya başka iOS projesi) açıkken Agent chat'e şunu yaz:

> App Store'a göndermeden önce preflight kontrolü yap, CRITICAL hataları düzelt.

veya İngilizce:

> Run app-store preflight and fix all CRITICAL issues.

**Ne olur?** Agent, `SmallSignalStudio/compliance/scripts/run-preflight.sh` ile projeyi tarar, Apple red risklerini listeler, kodda düzeltir, tekrar tarar — CRITICAL kalmayana kadar.

### Apple reddettiyse

Agent chat'e Apple'ın gönderdiği red mesajını yapıştır:

> App Store reddetti. Mesaj şu: [buraya Apple'ın e-postasını yapıştır]. Bunu hub'a kaydet ve bir daha yakalanması için kural ekle.

**Ne olur?** Agent, `SmallSignalStudio/compliance/rejections/` altına kayıt açar; gelecekteki tüm projelerde aynı hata taranır.

### Yeni uygulama fikri araştırması

> genius-ideas skill ile astrolojiyle ilgili bir fikir bul.

---

## "COMPLIANCE_HUB_REPO secret" ne demek?

Bu **Cursor ile ilgili değil** — **GitHub** ayarı.

Sippin'in GitHub Actions'ı (CI), push sırasında otomatik App Store kontrolü yapmak için SmallSignalStudio reposuna erişmeli. Bunun için:

1. GitHub'da **sippin** reposuna git
2. **Settings → Secrets and variables → Actions**
3. **New repository secret**
4. İsim: `COMPLIANCE_HUB_REPO`
5. Değer: SmallSignalStudio'nun **private git clone URL'i** (ör. `https://github.com/kullanici/SmallSignalStudio.git` veya deploy key ile SSH URL)

Bunu sadece Sippin'de CI kullanacaksan yaparsın. **Cursor'dan manuel preflight için gerekmez.**

---

## Hangi dosya nerede?

| Ne | Nerede | Ne işe yarar |
|----|--------|--------------|
| Skill (agent talimatı) | `~/.cursor/skills/` | Cursor her projede okur |
| Kaynak (düzenlediğin) | `~/Repo/SmallSignalStudio/skills/` | Git'te versiyonlanır |
| Kontrol scriptleri | `~/Repo/SmallSignalStudio/compliance/` | Greenlight + öğrenilen kurallar |
| Proje ayarı | `~/Repo/sippin/.greenlight.yml` | Sippin'e özel ignore kuralları |

Skill global; scriptler SmallSignalStudio'da kalır. `APPSTORE_COMPLIANCE_HUB` env değişkeni ikisini birbirine bağlar (kurulum scripti bunu `~/.zshrc`'ye ekler).

---

## Yeni skill eklediğinde

Skill'leri `SmallSignalStudio/skills/` altında tutuyorsun. Güncellemek için:

```bash
cd ~/Repo/SmallSignalStudio && git pull
~/Repo/install-cursor-skills.sh
```

veya SmallSignalStudio'da commit atınca git hook otomatik sync yapar.

---

## Sık sorulan sorular

**Her projeye `.greenlight.yml` kopyalamam gerekir mi?**  
Sadece iOS/App Store projeleri için (Sippin, Batten, vb.). SmallSignalStudio web sitesi gibi projeler için gerekmez.

**Skill çalışmıyor gibi görünüyor**  
1. `~/Repo/install-cursor-skills.sh` tekrar çalıştır  
2. Cursor'ı yeniden başlat  
3. **Agent** modunda olduğundan emin ol (sadece Chat değil)

**GREENLIT = Apple kesin kabul eder mi?**  
Hayır. Red riskini azaltır; Apple yine manuel inceler.
