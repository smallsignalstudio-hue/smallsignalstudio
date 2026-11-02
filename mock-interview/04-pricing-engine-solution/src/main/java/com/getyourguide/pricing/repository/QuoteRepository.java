package com.getyourguide.pricing.repository;

import com.getyourguide.pricing.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface QuoteRepository extends JpaRepository<Quote, UUID> {
}
