# 10 — Cashback: Follow-up Questions

## A. One purchase, one credit

**A1. The network retried the same `externalId` and the wallet grew twice. What do you store?**
- Güçlü: `external_id` unique. İkinci mesaj yeni satır açmaz. Kredi `hedef - applied_cents`. Aynı tutarda delta sıfır. Unique ihlali de ikinci insert'i tutar. `synchronized` ikinci instance'ta yok.
- Zayıf: "İkinci isteği 200 dönüp bakiyeye dokunmam." Tutar değiştiyse komisyon kaçar, yarışta ikisi de yokmuş gibi insert eder.

**A2. Two different confirmations left one credit. You removed the sleep and it still happens. Why?**
- Güçlü: İkisi de `0` okuyup kendi sonucunu yazar. Kayıp güncelleme uyku kalkınca da durur. Düzeltme `available = available + :delta`. `SELECT FOR UPDATE` da işe yarar ama satır satır kilitler; atomik update daha dar.
- Zayıf: "Sleep'i sildim, lokalde geçti." İki istek hâlâ aynı okumayı paylaşabilir.

**A3. Two pollers both credited `poll-1`. What is the statement?**
- Güçlü: `SELECT … FOR UPDATE SKIP LOCKED` ve `processed = true` aynı transaction'da. İkinci worker kilitli satırı atlar. Commit'ten sonra satır `processed`. Delta yüzünden ikinci uygulama da `0` ekler. Limit 10 yetmez.
- Zayıf: "`processed` kontrolü var." İkisi de false görür, ikisi de yazar.

## B. Money

**B1. Ten USD at `0.10` became five euros. The config says USD is `0.50`. Which rate is real?**
- Güçlü: Kur, olayın olduğu günün kuru. Config bugünün kuru; payout anında çevirmek komisyonu değiştirir. `BigDecimal` ve `HALF_UP`. `15 * 0.10 = 1.5` → `2`. `(int)` kesmesi `1` bırakır. `double` 0.10'u tam tutmaz.
- Zayıf: "Config'i 0.10 yaparım." Yarınki kur dünün olayını yine bozar.

**B2. Shopper 2 had 300 pending and the payout sent 800. What is payable?**
- Güçlü: Pending henüz confirmed değil, ödenmez. Payout `available` kadar iner, `pending` kalır. İkisini sıfırlamak bir sonraki onayda da eksik bırakır.
- Zayıf: "Kullanıcı toplam bakiyesini ister." Ağ iade ederse o para çoktan gitmiştir.

**B3. A later `PENDING` doubled a confirmed purchase, and a `400` correction became `650`. How do you order events?**
- Güçlü: Rank `PENDING < CONFIRMED < DECLINED`. Düşük rank no-op. Aynı rankta yalnız delta. `DECLINED` hedefi sıfır, yani uygulananın tersi.
- Zayıf: "Son gelen kazanır." Geç pending onayı siler.

## C. Ledger and payout

**C1. After payout the network declines the purchase. The balance is `-1000`. Is that a bug?**
- Güçlü: Para çıktı, komisyon geri alındı. Tabana sıfır yapıştırmak borcu gizler. Çift kayıt: `USER -1000` ve `NETWORK +1000`. Tüm bacaklar `0`. Available, `USER` toplamıdır. Aynı `Idempotency-Key` ikinci kez düşmez; unique key yarışı da ilk satırı döner.
- Zayıf: "Negatifi 0'a çekerim." Sonraki payout borcu yok sayar.

**C2. The wallet row says `0` and the ledger says the shopper owes `1000`. Which do you pay?**
- Güçlü: Kaynak ledger. Projeksiyon `USER` bacaklarından yeniden yazılır, payout o toplamdan çıkar. Tek kolon güncellenip ledger atlanırsa ikisi ayrılır. Tamir aynı transaction'da backfill.
- Zayıf: "Cüzdan kolonu yeter, ledger rapordur." Kolon çoktan kaymışsa ödeme yanlış tutardadır.

## D. Production

**D1. Where do you look when two replicas credit one event in production?**
- Güçlü: Worker id, `external_id` ve transaction süresi loglanır. Aynı id iki `CREDIT` üretmişse kilit yoktur. Idempotency key'i payout'ta aylarca tutarsın; ağ günler sonra tekrarlar. Feature flag ile yeni poller, eski replay'den önce unique index.
- Zayıf: "CPU'ya bakarım." Bu bir kilit hatası, GC değil.

**D2. Where do you refuse an AI suggestion in this service?**
- Güçlü: Kur, yuvarlama, kilit ve payout SQL'ini model yazmasın. Üretilen `synchronized` veya `double` bakiye kabul edilmez. Test, olay kurunu ve iki worker'ı kırmızıya düşürmeden patch birleşmez.
- Zayıf: "Model `SKIP LOCKED` yazdı, geçti." İkinci replica ve delta testi yoksa cümle duruyordur, kilit transaction dışında kalmış olabilir.
