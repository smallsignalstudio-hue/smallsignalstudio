package com.getyourguide.pricing.repository;

import com.getyourguide.pricing.model.Promo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PromoRepository extends JpaRepository<Promo, Long> {

    Optional<Promo> findByCode(String code);
}
