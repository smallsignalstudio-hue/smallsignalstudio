package com.getyourguide.pricing.service;

import com.getyourguide.pricing.dto.QuoteRequest;
import com.getyourguide.pricing.model.PriceBand;
import com.getyourguide.pricing.model.Promo;
import com.getyourguide.pricing.model.Quote;
import com.getyourguide.pricing.model.Rate;
import com.getyourguide.pricing.repository.PriceBandRepository;
import com.getyourguide.pricing.repository.PromoRepository;
import com.getyourguide.pricing.repository.QuoteRepository;
import com.getyourguide.pricing.repository.RateRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class PricingService {

    private final RateRepository rates;
    private final PromoRepository promos;
    private final PriceBandRepository bands;
    private final QuoteRepository quotes;
    private final QuoteSigner signer;
    private final JdbcTemplate jdbc;
    private final Clock clock;

    public PricingService(RateRepository rates, PromoRepository promos, PriceBandRepository bands,
                          QuoteRepository quotes, QuoteSigner signer, JdbcTemplate jdbc, Clock clock) {
        this.rates = rates;
        this.promos = promos;
        this.bands = bands;
        this.quotes = quotes;
        this.signer = signer;
        this.jdbc = jdbc;
        this.clock = clock;
    }

    @Transactional
    public Map<String, String> quote(QuoteRequest request) {
        String total = price(request).format();
        UUID id = UUID.randomUUID();
        Instant expiresAt = clock.instant().plus(Duration.ofMinutes(15));
        String signature = signer.sign(id, total, request.currency(), expiresAt);
        quotes.save(Quote.create(id, request.customerId(), request.currency(), total, signature, expiresAt, request.promoCode()));
        Map<String, String> body = new LinkedHashMap<>();
        body.put("quoteId", id.toString());
        body.put("total", total);
        body.put("currency", request.currency());
        body.put("signature", signature);
        body.put("expiresAt", expiresAt.toString());
        return body;
    }

    public Map<String, String> matchBand(String currency, BigDecimal amount) {
        PriceBand band = bands.findByCurrency(currency).orElseThrow();
        if (band.getThreshold().compareTo(amount) != 0) {
            throw new IllegalArgumentException("no band");
        }
        return Map.of("flatTotal", band.getFlatTotal().setScale(2).toPlainString());
    }

    @Transactional
    public Map<String, String> checkout(UUID id, String signature) {
        Quote quote = quotes.findById(id).orElseThrow();
        if (quote.isConsumed() || !quote.getExpiresAt().isAfter(clock.instant())) {
            throw new IllegalStateException("quote is not payable");
        }
        if (!signer.matches(quote.getId(), quote.getTotal(), quote.getCurrency(), quote.getExpiresAt(), signature)) {
            throw new IllegalStateException("quote is not payable");
        }
        if (quote.getPromoCode() != null) {
            redeem(quote.getPromoCode(), quote.getCustomerId());
        }
        quote.consume();
        return Map.of("total", quote.getTotal(), "currency", quote.getCurrency());
    }

    private void redeem(String code, String customerId) {
        int updated = jdbc.update(
                "UPDATE promo SET redemptions = redemptions + 1 WHERE code = ? AND redemptions < max_redemptions",
                code);
        if (updated != 1) {
            throw new IllegalStateException("promo exhausted");
        }
        Integer already = jdbc.queryForObject("""
                SELECT count(*) FROM promo_redemption r
                JOIN promo p ON p.id = r.promo_id
                WHERE p.code = ? AND r.customer_id = ?
                """, Integer.class, code, customerId);
        if (already != null && already > 0) {
            throw new IllegalStateException("promo already used");
        }
        jdbc.update("""
                INSERT INTO promo_redemption (promo_id, customer_id)
                SELECT id, ? FROM promo WHERE code = ?
                """, customerId, code);
    }

    private Money price(QuoteRequest request) {
        int adults = request.lines().stream()
                .filter(line -> "ADULT".equals(line.category()))
                .mapToInt(QuoteRequest.Line::quantity)
                .sum();
        Money subtotal = Money.zero(request.currency());
        for (QuoteRequest.Line line : request.lines()) {
            subtotal = subtotal.add(new BaseRate(rates).line(request.currency(), line, adults));
        }
        if (request.promoCode() == null) {
            return subtotal;
        }
        Promo promo = promos.findByCode(request.promoCode()).orElseThrow();
        return new PromoDiscount().apply(subtotal, promo);
    }

    private record BaseRate(RateRepository rates) {
        Money line(String currency, QuoteRequest.Line line, int adults) {
            Rate price = rates.findByCategoryAndCurrency(line.category(), currency).orElseThrow();
            BigDecimal unit = price.getUnitPrice();
            if ("ADULT".equals(line.category()) && price.getGroupMin() != null && adults >= price.getGroupMin()) {
                unit = price.getGroupPrice();
            }
            return Money.of(unit, currency).times(line.quantity());
        }
    }

    private static final class PromoDiscount {
        Money apply(Money subtotal, Promo promo) {
            if (promo.getMinSubtotal().signum() > 0 && subtotal.compareAmount(promo.getMinSubtotal()) < 0) {
                return subtotal;
            }
            return subtotal.percentOff(promo.getPercent()).clampAtZero();
        }
    }
}
