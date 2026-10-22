package com.getyourguide.pricing.repository;

import com.getyourguide.pricing.model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RateRepository extends JpaRepository<Rate, Long> {

    Optional<Rate> findByCategoryAndCurrency(String category, String currency);
}
