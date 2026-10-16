# Bowlkeep — Genius Ideas Deep Dive (Thermomix / Cookidoo)

**Date:** 2026-10-16  
**Vertical:** Thermomix owners / Cookidoo subscription escape  
**Working title:** Bowlkeep  
**Platform default:** iOS-first (Android week 3–4 if conversion holds)  
**MVP budget:** ~4–5 weeks solo  
**Finalist score:** 76/100  
**Recommendation:** Research homework → then Ship MVP (do **not** reverse-engineer the machine)

**One-line bet:** Cookidoo aboneliği bitince *kendi tariflerini* kaybeden Thermomix sahiplerine — telefon üstünde rehberli pişirme + iptale dayanıklı kişisel tarif kasası; Vorwerk’in $50–65/yıl + ₺1.980/yıl rantının altında, yasal ve solo-buildable.

---

## Phase 0 — Calibrate

| Field | Assumption (full auto) |
|-------|------------------------|
| `{VERTICAL}` | Thermomix / Cookidoo / Bimby sahipleri |
| Platform | iOS-first |
| MVP | 4–5 weeks |
| Monetization | Freemium + annual undercut Cookidoo |
| Mode | Full auto (cloud agent; no interactive gate) |

**Hard legal/product line (non-negotiable):**  
Thermomix cihaz protokolünü kırmak, Cookidoo DRM’ini aşmak, resmi tarif kütüphanesini scrape etmek **yok**. Cihaz manuel modda çalışır; rehberlik telefonda. Bu, hem yasal hem builder-DNA açısından doğru hat.

**Myth check:** Makine Cookidoo olmadan **çalışır** (Vorwerk resmi FAQ). Kilitlenen şey: buluttaki tarifler + cihaz üstü Guided Cooking + “Created Recipes” erişimi.

---

## Phase 1 — Signal mining (show work)

**Minimum:** 15 signals, ≥5 payment intent (Y).

| # | Signal | Source | Quote snippet | Pain type | Pay (Y/N/Weak) | Freq |
|---|--------|--------|---------------|-----------|----------------|------|
| 1 | Annual price EU/UK/US | [r/thermomix costs](https://www.reddit.com/r/thermomix/comments/1saamrp/costs_post_purchase/) | “€60 / year… £50… $65/year” | Price | Y | High |
| 2 | TR price shock ₺1.980 | [Şikayetvar](https://www.sikayetvar.com/vorwerk/tm6-uygulamasi-abonelik-ucretlerinin-asiri-artisi) | “abonelik ücretleri… erişilebilir olmaktan uzak” | Price / loyalty | Y | Med |
| 3 | Official TR pricing | [Thermoaşkı](https://thermoaski.com/cookidoo/) | “yıllık abonelik ücreti 1.980 TL” | Price | Y | High |
| 4 | “Is it worth it?” | [r/thermomix](https://www.reddit.com/r/thermomix/comments/1mkxh71/is_cookidoo_worth_the_price/) | “significant expense on top of an already overpriced toy” | Value doubt | Y | High |
| 5 | Cancel after learning | same thread | “cancelled the subscription after a year” | Churn | Weak | Med |
| 6 | App is terrible | [r/thermomix](https://www.reddit.com/r/thermomix/comments/1meds5l/the_cookidoo_app_is_terrible/) | “I'm cancelling my sub… expensive and underwhelming” | UX | Y | High |
| 7 | No translation | same | “refuse… google translate for an app that charges me” | UX / i18n | Y | Med |
| 8 | Closed system rant | same | “fully closed system and it sucks… live from the monthly fees” | Lock-in | Y | High |
| 9 | Life after sub — lose folders | [r/thermomix](https://www.reddit.com/r/thermomix/comments/1b4lof7/life_after_subscription/) | “won't have access to your folders or created recipes” | Hostage | Y | High |
| 10 | **Own recipes locked** | [r/thermomix](https://www.reddit.com/r/thermomix/comments/1pkmdus/cant_use_my_own_created_recipes_without_cookidoo/) | “can't access MY OWN RECIPES on my TM7” | Hostage / betrayal | Y | **Critical** |
| 11 | Printout workaround | same | “print my own recipe… read it off a paper print out” | Workaround pain | Y | High |
| 12 | Convert-anything demand | [r/thermomix](https://www.reddit.com/r/thermomix/comments/1voo61o/question_for_thermomix_owners_would_you_use_a/) | “would have been willing to pay… \<10 bucks one-time” | Conversion | Y | High |
| 13 | Hate Cookidoo-only push | same thread (synth) | won’t pay Cookidoo just to receive converted recipes | Anti-lock-in | Y | Med |
| 14 | Plant-based cancels year 3 | [r/ThermomixPlantBased](https://www.reddit.com/r/ThermomixPlantBased/comments/1kbu80b/worth_it_for_plant_based_diet_only/) | “barely used cookidoo, so i think im going to cancel” | Retention drop | Weak | Med |
| 15 | AIdoo wants Cookidoo still | [r/thermomix AIdoo](https://www.reddit.com/r/thermomix/comments/1mhpj98/aidoo_i_made_a_thermomix_recipe_converter_app/) | users ask if they still need Cookidoo sub | Complement vs replace | Y | High |
| 16 | Machine works without sub | [Bec’s Table](https://becs-table.com.au/does-the-thermomix-tm6-work-without-cookidoo/) + Vorwerk FAQ | Cookidoo optional; manual + basic cookbook remain | Education gap | N | High |
| 17 | Competitors already monetize | ThermoConvert / MixBuch / ThermoRemix | £14.99/yr · $9.99/yr · ~€40/yr | Proven pay | Y | High |

**4chan:** Direct Thermomix threads thin / noisy — not used as primary evidence (skill rule). Cross-validated via Reddit + App Store + official pricing + Şikayetvar.

**Payment intent summary:** Users already pay €48–€60 / $65 / ₺1.980 / year *or* actively seek cheaper escapes. Converter tools monetize at ~1/3–1/4 of Cookidoo. Strong Y cluster around **hostage recipes** + **convert my food from the internet**.

---

## Phase 2 — Idea generation (5–8)

| # | Job-to-be-done | Killer feature | Local-first? | Builder DNA fit |
|---|----------------|----------------|--------------|-----------------|
| A | Abonelik bitince kendi tariflerimi kaybetmeyeyim | Cancel-proof personal cookbook + phone Cook Mode | Yes (SQLite + optional sync) | Strong — anxiety trigger at renewal |
| B | Her blog/YouTube tarifini TM adımlarına çevir | One-tap URL/photo → TM steps | Needs AI API | Strong but crowded |
| C | TR mutfağı + TR malzeme ile Cookidoo’yu alt et | Turkish-first library + localization | Content heavy | Medium — content ops |
| D | Bu akşam TM ile ne pişireyim (dolaptakiler) | Pantry → 3 TM dinners | Light backend | Medium retention |
| E | Aile defterini dijitalleştir | Handwriting card → TM steps | AI API | Narrow but emotional |
| F | Cookidoo’nun yarısı fiyata haftalık plan | Meal planner + shopping list only | Sync helpful | Weak — feature, not product |
| G | Danışmanlar için müşteri takip CRM | Advisor CRM | Backend | Out of consumer DNA |
| H | Sesli pişirme (eller kirliyken) | Voice-only Cook Mode | Local TTS | Thin alone |

---

## Phase 3 — Market screen (all candidates)

### Competitor baseline

| Competitor | Rating / scale | Pricing | MRR band | Weakness |
|------------|----------------|---------|----------|----------|
| **Cookidoo** (Vorwerk) | Official; 100k+ recipes | US $65/yr · EU ~€48–60 · UK £50 · TR ₺1.980 | $50k+ Verified-ish (Vorwerk scale) | Hostage own recipes; app UX; closed; i18n; “not worth it” churn |
| **ThermoConvert** | Indie web | Free 1 × · packs £2.99/£6.99 · Unlimited £2.99/mo or **£14.99/yr** | $1k–10k Est. | EN-first; web-first; no offline vault story |
| **MixBuch** | App Store IAP | Free core · Premium **$0.99/mo · $9.99/yr · $34.99 life** | $1k–10k Est. | Community/UGC; Cookidoo-alt positioning but DE-heavy |
| **ThermoRemix** | Web | Discovery free · Solo/Annual ~**€39.90/yr** · sends need Cookidoo for device | $1k–10k Est. | Still Cookidoo-dependent for on-device send |
| **AIdoo / Adapt My Recipe** | Reddit-launched | Credits / tiers; users call pricing “insane” | Unknown | Needs Cookidoo account for guided push; ToS grey zone on 3rd-party uploads |

Installed base: **>11M kitchens** worldwide (company statements 2023–25) — huge top of funnel; paying Cookidoo subset smaller but still massive.

### Scores (rubric)

| Idea | Pay (25) | Retention (20) | Diff (20) | Solo (20) | MRR ceil (15) | **Total** | Kill? |
|------|----------|----------------|-----------|-----------|---------------|-----------|-------|
| **A Bowlkeep (cancel-proof + cook mode)** | 22 | 16 | 17 | 16 | 12 | **83** | — |
| **B Generic AI converter** | 20 | 12 | 8 | 14 | 11 | **65** | Crowded |
| **C Turkish-first library** | 18 | 14 | 16 | 8 | 10 | **66** | Content ops |
| **D Pantry dinner picker** | 12 | 15 | 12 | 15 | 8 | **62** | Weaker pay |
| **E Handwriting digitizer** | 14 | 8 | 14 | 14 | 7 | **57** | Borderline |
| **F Planner-only** | 10 | 12 | 6 | 16 | 6 | **50** | **Kill** |
| **G Advisor CRM** | 14 | 10 | 12 | 6 | 10 | **52** | **Kill** |
| **H Voice-only** | 8 | 10 | 10 | 18 | 5 | **51** | **Kill** |

**Hybrid finalist:** Ship **A** as the product spine; fold **B** as the acquisition hook (3 free conversions); use **C** as GTM wedge for TR/locale, not as day-1 content empire.

---

## Phase 4 — Challenge (full auto pick)

### Top 3

1. **Bowlkeep** (83) — Hostage pain is unique and emotionally hot; Cook Mode makes the machine usable without Vorwerk cloud.  
2. **Turkish-first MixBuch-style** (66) — Local price anger (₺1.980) is real; content treadmill kills solo.  
3. **Generic converter** (65) — Proven willingness to pay; ThermoConvert already owns EN messaging at £14.99/yr.

**Why #1 beats #2:** Differentiation is *ownership + cook-beside-the-bowl*, not another recipe dump. Content libraries lose to Vorwerk’s 100k tested recipes; **own-recipe vault** does not.

**Fail modes (each):**  
1. Users still want on-device Guided Cooking — phone mode feels “not real.”  
2. TR content never reaches critical mass.  
3. Converter race to free ChatGPT prompts.

**Variant B (higher monetization):** **Bowlkeep Household** — up to 5 devices / family seats, undercut Cookidoo’s informal “share login with 5 robots” hack with a clean family plan at ~$29.99/yr.

**Assumptions locked for Phase 5:** Proceed with Bowlkeep consumer vault + Cook Mode; AI convert as freemium hook; no device protocol reverse engineering.

---

## Phase 5 — Deep dive (finalist)

# Bowlkeep — Deep Dive Report

**Date:** 2026-10-16  
**Vertical:** Thermomix companion / Cookidoo alternative (legal)  
**Finalist score:** 76/100 (conservative after competitor crowding discount from 83)  
**Recommendation:** Research more (3 homework items) → Ship MVP

---

### 1. Executive summary

Thermomix sahipleri (~11M+ mutfak) cihaz için $1.000–1.600 ödüyor; sonra Cookidoo için **$65 / €48–60 / £50 / ₺1.980 yıllık** isteniyor. Makine aboneliksiz çalışıyor — ama **kullanıcının kendi oluşturduğu tarifler ve Guided Cooking buluta kilitleniyor**. Bu “kendi tarifime erişemiyorum” anı, ödeme niyeti + ihanet duygusu taşıyor.

**Bowlkeep** Cookidoo’yu taklit etmez. **Kişisel tarif kasası + telefonda Cook Mode** sunar: hız / süre / sıcaklık adımları telefon ekranında; kullanıcı TM’de manuel girer. AI ile URL/foto/metin dönüşümü freemium kanca. Fiyat hedefi: **Cookidoo’nun ~1/3–1/4’ü** ($14.99–24.99/yıl veya kredi paketleri). Para burada — ama Vorwerk’i “hack’leyerek” değil, **iptal anındaki boşluğu** doldurarak.

---

### 2. The problem (evidence-backed)

**Who hurts:** TM5/TM6/TM7 (ve TR’de Thermomix) sahibi, deneme bitince yenileme maili gören; kendi puding/çorba rutinini “Created Recipes”a yazmış aile aşçısı.  
**When:** Trial/renewal week; “bugün çocukla puding” anı; aboneliği kestikten sonraki ilk pişirme.  
**How often:** Weekly cooking ritual; acute spike at cancel/renewal.

| Quote | Source |
|-------|--------|
| "can't access MY OWN RECIPES on my TM7" | [Reddit](https://www.reddit.com/r/thermomix/comments/1pkmdus/cant_use_my_own_created_recipes_without_cookidoo/) |
| "I'm cancelling my sub as soon as the free 3 months run out. It's expensive and underwhelming." | [Reddit](https://www.reddit.com/r/thermomix/comments/1meds5l/the_cookidoo_app_is_terrible/) |
| "won't have access to your folders or created recipes" | [Reddit](https://www.reddit.com/r/thermomix/comments/1b4lof7/life_after_subscription/) |
| "abonelik bedelinin oldukça yüksek ve erişilebilir olmaktan uzak" | [Şikayetvar](https://www.sikayetvar.com/vorwerk/tm6-uygulamasi-abonelik-ucretlerinin-asiri-artisi) |

---

### 3. The product

#### Killer feature (one)
**Cancel-proof Cook Mode:** Kendi tariflerin (manuel veya AI-convert) büyük adımlarla telefonda; süre sayacı + hız/sıcaklık chip’leri; tarifler cihazda senin — abonelik bitince kaybolmaz.

#### Supporting features (max 2)
1. **Convert-in** — URL / foto / metin → TM31/TM5/TM6/TM7 adımları (ücretsiz kota).  
2. **Edit & scale** — porsiyon, diyet swap, adım düzenleme; senin “aile versiyonun” kalır.

#### Explicitly NOT building
- Thermomix Wi‑Fi protokolü / firmware reverse engineering  
- Cookidoo hesabına otomatik “push” (ToS riski; AIdoo/ThermoRemix tuzağı)  
- 100k’lık resmi tarif klonu / scrape  
- Sosyal feed, yorum moderasyonu, marketplace  
- Danışman CRM

#### User flow
- **First open (60s):** Model seç (TM6/TM7…) → 1 örnek tarif Cook Mode dene → “kendi tarifini ekle veya fotoğraf çek”.  
- **Day 7:** Haftalık 2–3 pişirme; 3 free convert bitti → paywall.  
- **Conversion moment:** 4. convert **veya** 2. hafta “export/backup & unlimited cook” — renewal anxiety copy: “Cookidoo iptal olsa da tariflerin burada.”

#### Widget / notification
- Widget: “Bu akşam: [tarif] · 28 dk” (opsiyonel).  
- Notification cap: haftada ≤2 (meal reminder); anxiety spam yok.  
- Lock screen: aktif Cook Mode Live Activity (süre + sonraki adım) — **v1.1**, not MVP blocker.

---

### 4. Monetization

| Tier | Price | Limits / unlocks |
|------|-------|------------------|
| Free | $0 | Sınırsız kendi tarif + Cook Mode; **3 AI convert**; offline vault (local) |
| Pro monthly | $2.99/mo | Unlimited convert, scale, diet adapt, iCloud sync, Live Activity |
| Pro annual | **$19.99/yr** (TR launch: **₺499–699/yıl** test) | Same; anchor vs Cookidoo $65 / ₺1.980 |
| Packs (alt) | $2.99 / 25 convert | Subscription-averse users (ThermoConvert playbook) |

- **Trial:** Annual’da 7-gün; veya “3 convert free” soft trial (daha iyi fit).  
- **Rationale:** ThermoConvert £14.99/yr & MixBuch $9.99/yr kanıtlı; Bowlkeep **ownership story** ile $19.99 savunulabilir. Cookidoo’nun altında kal, “yarısı fiyat” iddiası yapma — **1/3 fiyat + senin tariflerin** de.  
- **Expected conversion:** 2–5% free→paid — **Estimated** (utility sub comps).

---

### 5. Competition teardown

| Competitor | Rating | Reviews | Pricing | MRR band | Key weakness |
|------------|--------|---------|---------|----------|--------------|
| Cookidoo | Official | Huge | $65/€48–60/₺1980 yr | $50k+ | Locks *your* recipes; UX hate |
| ThermoConvert | n/a web | Growing | £14.99/yr | $1–10k Est. | Convert≠vault narrative; EN |
| MixBuch | New AS | Low | $9.99/yr | $1–10k Est. | Community/content; not hostage-focused |
| ThermoRemix | n/a | — | ~€40/yr | $1–10k Est. | Device send needs Cookidoo |
| ChatGPT DIY | — | — | $20/mo | — | Bad TM settings; no cook UI |

### Why we're different
Falsifiable wedge: **“We keep cooking when Cookidoo cancels; they monetize the cancel.”** Converters sell transforms; we sell **continuity of your household recipes** with a kitchen-grade Cook Mode. We never claim Vorwerk guided-on-device parity.

---

### 6. Go-to-market (solo realistic)

**ASO keywords (10):** thermomix recipes, cookidoo alternative, thermomix cook mode, convert recipe thermomix, tm6 recipes, tm7 recipes, thermomix without cookidoo, bimby ricette, thermomix tarif, guided cooking phone

**Launch channels:**
- r/thermomix — value posts: “export checklist before you cancel” (no spam).  
- TR: Thermomix Facebook grupları, Şikayetvar-aware landing (“₺1980’e alternatif kişisel kasa”).  
- TikTok/Reels: “aboneliğim bitti, puding yine oldu” Cook Mode screen recording.

**First 100 users:**
1. 10 beta from Reddit “own recipes locked” thread responders.  
2. 40 TR soft launch (TestFlight) via consultant-friendly positioning (*complement*, not attack Vorwerk brand in ads).  
3. 50 ASO + convert-demo landing.

**Content hook:** Weekly “1 family recipe → TM steps” before/after clip.

---

### 7. Build plan (Cursor-friendly)

| Week | Deliverable |
|------|-------------|
| 1 | Expo app shell; recipe model (ingredients, steps with time/temp/speed); local SQLite; manual add/edit |
| 2 | Cook Mode (fullscreen, keep-awake, step timer, large chips); model profiles TM5/6/7 |
| 3 | AI convert (URL + photo OCR) via API; 3-credit gate; paywall scaffolding (RevenueCat) |
| 4 | Polish, onboarding, TR+EN strings, App Store assets; TestFlight |
| 5 buffer | Sync optional; Live Activity; packs IAP |

- **Stack:** React Native + Expo, SQLite (local-first), RevenueCat, one LLM API for convert.  
- **Backend:** Minimal — auth + convert proxy + optional sync. Prefer local-first vault.  
- **Compliance:** No Vorwerk trademarks in name/icon mimicking Cookidoo; “independent, not affiliated” footer; no copyrighted Cookidoo recipe ingestion; AI disclaimer (review before cooking / hot liquids safety).

---

### 8. Financial model (honest ranges) — Estimated

Assumptions: niche ASO + community; ARPU ~$1.60/mo blended; churn 8–12%/mo monthly cohort / better on annual.

| Scenario | Month 6 MRR | Month 12 MRR | Month 24 MRR |
|----------|-------------|--------------|--------------|
| Pessimistic | $400 | $1.5k | $3k |
| Base | $1.5k | $6–12k | $15–25k |
| Optimistic | $4k | $20–35k | $40k+ |

**Ceiling note:** Beating Vorwerk’s Cookidoo P&L is fantasy. Capturing **0.1–0.5%** of angry/churning owners is the real game — still enough for studio-scale MRR.

---

### 9. Kill criteria

| Milestone | Metric | Action if missed |
|-----------|--------|------------------|
| Day 30 | 400 installs · 15% reach Cook Mode 2× | Reposition copy / kill convert quality issues |
| Day 60 | 40 trials or pack purchases | Pivot to packs-only or kill |
| Day 90 | **$500 MRR** | Kill or fold convert into content site |

---

### 10. Alternatives portfolio

### Plan B — TR Locale Blade (score 66)
Turkish-first Thermomix cook app: TR blogs + malzeme lokalizasyonu + ₺499/yıl. Faster emotional GTM in TR; slower global. MRR band $1–10k Est. unless content scales.

### Plan C — Convert Packs Only (score 65)
Ship ThermoConvert-style web in a weekend, undercut with $9.99/yr unlimited. Faster cash test; weaker moat; crowded. MRR band $1–10k Est.

---

## Phase 6 — Force depth

### Devil's advocate — 5 reasons this fails
1. **Phone ≠ machine screen.** Power users will say “if it’s not on the TM display, it’s not guided cooking.”  
2. **ThermoConvert/MixBuch already occupy ASO + Reddit mindshare.** Late mover tax.  
3. **AI convert liability** — wrong temperature → burnt/ruined food → 1-star storms.  
4. **Vorwerk could unlock “created recipes offline”** and erase the hostage wedge overnight.  
5. **Copyright/ToS** — users pasting NYT/blog recipes creates legal grey; sharing must stay private.

### Research homework (do before build)
1. Manually interview 10 people from the “own recipes locked” Reddit thread — would they pay $19.99/yr for phone Cook Mode?  
2. Blind-test 20 AI conversions (TR + EN) against known good TM recipes; measure edit distance.  
3. App Store keyword gap: “thermomix without cookidoo” / “cookidoo alternative” — screenshot top 10, note ratings.

### Next decision
**Research more (48h homework) → Ship MVP** if ≥6/10 interviewees say “I’d pay” and convert quality needs ≤2 edits/recipe average.

### Plan B / C
See §10 above.

---

## Product surface (MVP screens — for later Blueprint)

1. Onboarding — model + “your recipes stay yours”  
2. Library — lists / search  
3. Recipe detail — ingredients + steps  
4. Editor — add/edit step chips (time / °C / speed / mode)  
5. Convert — paste URL / camera / text  
6. Cook Mode — one step, timer, next  
7. Paywall — Free vs Pro vs Packs  
8. Settings — model, units, language (TR/EN), export JSON/PDF

---

## Naming & brand notes

- **Bowlkeep** — working title (vault + bowl).  
- Alternates: Mixkeep, Sidebowl, Bowlside, Kasam (TR).  
- Avoid: Thermo*, Cooki*, Vorwerk lookalikes.

---

## Phase 7 gate

**iterate** or **re-roll**?

- **iterate** — stres testi: rakip feature matrix, fatal flaw, wedge sıkılaştır  
- **re-roll** — Bowlkeep’i reddet, Phase 3’ten başka aday (örn. TR-first veya packs-only)

Hazırsa bir sonraki adım: **Let's Blueprint** → `{SLUG}.md` + Stitch + BUILD paketi (kod yok).
