package com.getyourguide.pricing.repository;

import com.getyourguide.pricing.model.PriceBand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PriceBandRepository extends JpaRepository<PriceBand, Long> {

    Optional<PriceBand> findByCurrency(String currency);
}
