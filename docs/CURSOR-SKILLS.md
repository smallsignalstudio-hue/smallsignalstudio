# Cursor Skill'leri — Nasıl Kullanılır?

Bu rehber, `~/Repo` altındaki **tüm projelerde** (Sippin, Batten, Glossio, vb.) Cursor skill'lerini nasıl kullanacağını anlatır.

## Kısa cevap: Evet, her projeden kullanabilirsin

Skill'ler projeye özel değil; **bilgisayarına global kurulur**:

```
~/.cursor/skills/
  ├── app-store-preflight/
  └── genius-ideas/
```

Cursor'da **hangi klasörü açarsan aç** (ör. `~/Repo/sippin`), Agent chat'te bu skill'leri çağırabilirsin.

---

## Bir kez yapılacak kurulum

Terminal'de:

```bash
chmod +x ~/Repo/SmallSignalStudio/scripts/install-cursor-skills.sh
~/Repo/SmallSignalStudio/scripts/install-cursor-skills.sh
```

Bu script:
1. Skill'leri `~/.cursor/skills/` altına kopyalar
2. `~/.zshrc` dosyana ortam değişkenlerini ekler (yoksa)
3. `~/Repo/.cursor-env.sh` oluşturur (yoksa)

**App Store preflight için ek araçlar** (bir kez):

```bash
brew install revylai/tap/greenlight jq
pip3 install pyyaml
```

---

## Cursor'da skill nasıl çağrılır?

Skill'ler **otomatik çalışmaz**. Cursor'da **Agent** sekmesini açıp **normal Türkçe/İngilizce cümle yazarsın** — agent skill'i okur ve uygular.

### App Store'a göndermeden önce kontrol

Sippin (veya başka iOS projesi) açıkken Agent chat'e şunu yaz:

> App Store'a göndermeden önce preflight kontrolü yap, CRITICAL hataları düzelt.

**Ne olur?** Agent, `SmallSignalStudio/compliance/scripts/run-preflight.sh` ile projeyi tarar, Apple red risklerini listeler, kodda düzeltir, tekrar tarar — CRITICAL kalmayana kadar.

### Apple reddettiyse

Agent chat'e Apple'ın gönderdiği red mesajını yapıştır:

> App Store reddetti. Mesaj şu: [buraya Apple'ın e-postasını yapıştır]. Bunu hub'a kaydet ve bir daha yakalanması için kural ekle.

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
5. Değer: SmallSignalStudio'nun **private git clone URL'i**

Bunu sadece Sippin'de CI kullanacaksan yaparsın. **Cursor'dan manuel preflight için gerekmez.**

---

## Hangi dosya nerede?

| Ne | Nerede | Ne işe yarar |
|----|--------|--------------|
| Skill (agent talimatı) | `~/.cursor/skills/` | Cursor her projede okur |
| Kaynak (düzenlediğin) | `~/Repo/SmallSignalStudio/skills/` | Git'te versiyonlanır |
| Kontrol scriptleri | `~/Repo/SmallSignalStudio/compliance/` | Greenlight + öğrenilen kurallar |
| Proje ayarı | `~/Repo/sippin/.greenlight.yml` | Sippin'e özel ignore kuralları |

---

## Sık sorulan sorular

**Her projeye `.greenlight.yml` kopyalamam gerekir mi?**  
Sadece iOS/App Store projeleri için (Sippin, Batten, vb.).

**Skill çalışmıyor gibi görünüyor**  
1. `~/Repo/SmallSignalStudio/scripts/install-cursor-skills.sh` tekrar çalıştır  
2. Cursor'ı yeniden başlat  
3. **Agent** modunda olduğundan emin ol

**GREENLIT = Apple kesin kabul eder mi?**  
Hayır. Red riskini azaltır; Apple yine manuel inceler.
