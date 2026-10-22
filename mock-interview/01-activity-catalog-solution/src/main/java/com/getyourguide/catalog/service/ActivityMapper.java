package com.getyourguide.catalog.service;

import com.getyourguide.catalog.dto.ActivityDto;
import com.getyourguide.catalog.model.Activity;
import com.getyourguide.catalog.model.Supplier;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class ActivityMapper {

    static final BigDecimal SPECIAL_OFFER_MULTIPLIER = new BigDecimal("0.90");

    public ActivityDto toDto(Activity activity) {
        Supplier supplier = activity.getSupplier();
        return new ActivityDto(
                activity.getId(),
                activity.getTitle(),
                activity.getPrice(),
                finalPrice(activity),
                activity.getCurrency(),
                activity.getRating(),
                activity.isSpecialOffer(),
                supplier == null ? null : supplier.getName(),
                supplier == null ? null : location(supplier));
    }

    BigDecimal finalPrice(Activity activity) {
        if (!activity.isSpecialOffer()) {
            return activity.getPrice();
        }
        int fractionDigits = Currency.getInstance(activity.getCurrency()).getDefaultFractionDigits();
        return activity.getPrice()
                .multiply(SPECIAL_OFFER_MULTIPLIER)
                .setScale(fractionDigits, RoundingMode.HALF_UP);
    }

    private String location(Supplier supplier) {
        String location = Stream.of(supplier.getCity(), supplier.getCountry())
                .filter(Objects::nonNull)
                .collect(Collectors.joining(", "));
        return location.isEmpty() ? null : location;
    }
}
