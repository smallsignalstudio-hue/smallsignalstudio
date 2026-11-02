# 10 — Cashback: Çözüm

Affiliate onayı cüzdana euro cent olarak düşer, ödeme yalnız available bakiyeden çıkar. Invariant: aynı `externalId` tek satın almadır, kur olayın üstündeki `fxRate` ve `HALF_UP`'tır, bakiye `available = available + delta` ile büyür, `PENDING` `CONFIRMED`'ı geri almaz, payout pending'e dokunmaz, iki worker aynı satırı iki kez yazmaz. Ledger append-only kaynaktır; cüzdan projeksiyondur.

Kanıt: regression görevde kırmızı, burada yeşil. Çift kayıt, idempotent payout ve negatif bakiye yalnız çözümde.

## CB-501 — On dolar beş euro oldu

| | |
|---|---|
| Root cause | Kredi `amountCents * fx.current` ile hesaplanıyor. USD için config `0.50`. Olay `0.10` taşıyor ve parametre sadece satıra yazılıyor. `1000 * 0.50 = 500`. |
| Fix | `new BigDecimal(amountCents).multiply(eventRate).setScale(0, HALF_UP)`. Config'teki kur payout anının kuru değildir. |
| Test | `usdUsesTheRateOnTheEvent` |

## CB-502 — On beş cent yedi oldu

| | |
|---|---|
| Root cause | Aynı canlı kur, üstüne `(int)` kesmesi. `15 * 0.50 = 7.5` → `7`. Olay kuruyla `15 * 0.10 = 1.5`; double kesme `1` bırakır, yarım cent yukarı yuvarlanır. |
| Fix | `RoundingMode.HALF_UP`. `1.5` → `2`. |
| Test | `halfUpEuroCent` |

## CB-503 — Aynı onay iki kez

| | |
|---|---|
| Root cause | Her POST yeni satır açar ve tutarın tamamını ekler. İkinci `250` de available'a gider. `external_id` unique değil. |
| Fix | `external_id` unique. Var olan satırda hedef − `applied_cents`. Aynı tutarda delta `0`. |
| Test | `theSameConfirmationCreditsOnce` |

## CB-504 — Pending onayı geri sardı

| | |
|---|---|
| Root cause | Gelen status eskisinin üstüne yazılır ve tutar yeniden eklenir. Sıra yok. `CONFIRMED` sonra `PENDING` bakiyeyi ikiye katlar, status `PENDING` olur. |
| Fix | `PENDING < CONFIRMED < DECLINED`. Daha düşük rank no-op. Status ve `applied_cents` durur. |
| Test | `pendingAfterConfirmedDoesNotRewind` |

## CB-505 — Düzeltme üstüne eklendi

| | |
|---|---|
| Root cause | İkinci `CONFIRMED` `400` önceki `250`'yi unutur, `400` daha ekler. `650`. |
| Fix | Hedef `400`, uygulanmış `250`, delta `150`. Bakiye `400`. |
| Test | `aHigherConfirmationCreditsTheDelta` |

## CB-506 — Pending de ödendi

| | |
|---|---|
| Root cause | `paid = available + pending`, sonra ikisi de sıfırlanır. Shopper 2: `800` gider, pending `0` kalır. |
| Fix | Ödenen yalnız `availableCents`. `pendingCents` aynı satırda durur. |
| Test | `payoutLeavesPendingUntouched` |

## CB-507 — İki onay, tek kredi

| | |
|---|---|
| Root cause | Oku, `Thread.sleep`, yaz. İki transaction `0` görür, ikisi de `100` yazar. Uyku yarışı büyütür; uyku kalkınca delik durur. |
| Fix | `update wallet set available_cents = available_cents + :delta`. İki farklı `externalId` iki delta'dır. |
| Test | `twoConfirmationsBothLand` |

## CB-508 — İki worker aynı satır

| | |
|---|---|
| Root cause | `findTop10ByProcessedFalse` kilit almaz. İkisi de satırı görür, ikisi de `+100` yapar. `processed` bayrağı iş bitince yazılır. |
| Fix | `select … for update skip locked` ile işaretleme aynı transaction'da. İkinci worker satırı atlar. Uygulama yine delta'dır; poll da ledger yazar. |
| Test | `twoPollersCreditTheStoredEventOnce` |

## Sıfır ücret

`networkFeeCents = 0` düşülüyor. Sonuç değişmez. Asıl para hatası kurun kaynağı ve `double` kesmesidir.

## CB-510 — Defter

Her hareket iki bacak: `USER` ve karşı hesap. Komisyonun karşı hesabı `NETWORK`, payout'un karşı hesabı `CASH`. İki bacak toplamı `0`. Available, `USER` bacaklarının toplamıdır.

`Idempotency-Key` payout satırında unique. İkinci çağrı aynı `payoutId` döner, bir daha düşmez. `DECLINED` hedefi `0`'dır; payout'tan sonra delta `-applied`. Bakiye `-1000` olabilir, tabana yapışmaz. `GET /wallets/{id}/ledger` bu satırları döner.

Intake, poll, payout ve `LedgerWriter` ayrı. Cüzdan kolonu projeksiyon: atomik `UPDATE` ledger satırıyla aynı transaction'da. Kaynak defter.

## Sıra

1. Olay kuru ve `HALF_UP`.
2. `external_id` ve delta.
3. Status rank.
4. Atomik bakiye.
5. `SKIP LOCKED`.
6. Payout yalnız available, sonra ledger ve reversal.

Öncelik: CB-508 ve CB-503 (çift para), CB-501, CB-507, CB-506, CB-505, CB-504, CB-502, feature.
