package com.getyourguide.pricing.service;

import com.getyourguide.pricing.dto.QuoteRequest;
import com.getyourguide.pricing.model.PriceBand;
import com.getyourguide.pricing.model.Promo;
import com.getyourguide.pricing.model.Rate;
import com.getyourguide.pricing.repository.PriceBandRepository;
import com.getyourguide.pricing.repository.PromoRepository;
import com.getyourguide.pricing.repository.RateRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class PricingService {

    private final RateRepository rates;
    private final PromoRepository promos;
    private final PriceBandRepository bands;

    public PricingService(RateRepository rates, PromoRepository promos, PriceBandRepository bands) {
        this.rates = rates;
        this.promos = promos;
        this.bands = bands;
    }

    public Map<String, String> quote(QuoteRequest request) {
        Promo promo = request.promoCode() == null ? null : promos.findByCode(request.promoCode()).orElseThrow();
        double sum = gross(charge(request, promo));
        if (promo != null && promo.getMinSubtotal().signum() > 0 && sum < promo.getMinSubtotal().doubleValue()) {
            sum = gross(charge(request, null));
        }
        return Map.of("total", Double.toString(sum), "currency", request.currency());
    }

    public Map<String, String> matchBand(String currency, BigDecimal amount) {
        PriceBand band = bands.findByCurrency(currency).orElseThrow();
        if (!band.getThreshold().stripTrailingZeros().equals(amount)) {
            throw new IllegalArgumentException("no band");
        }
        return Map.of("flatTotal", band.getFlatTotal().toPlainString());
    }

    private double gross(double net) {
        return net;
    }

    private double charge(QuoteRequest request, Promo promo) {
        int heads = request.lines().stream().mapToInt(QuoteRequest.Line::quantity).sum();
        double rate = promo == null ? 0 : promo.getPercent() / 100.0;
        double sum = 0;
        for (QuoteRequest.Line line : request.lines()) {
            Rate price = rates.findByCategoryAndCurrency(line.category(), request.currency()).orElseThrow();
            double unit = price.getUnitPrice();
            if (price.getGroupMin() != null && heads > price.getGroupMin()) {
                unit = price.getGroupPrice();
            }
            double lineNet = 0;
            for (int i = 0; i < line.quantity(); i++) {
                lineNet += unit;
            }
            lineNet = lineNet * (1 - rate);
            if (promo != null) {
                lineNet = Math.round(lineNet * 100.0) / 100.0;
            }
            sum += lineNet;
        }
        return sum;
    }
}
