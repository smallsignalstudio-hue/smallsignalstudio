package com.atolls.cashback.repository;

import com.atolls.cashback.model.Payout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PayoutRepository extends JpaRepository<Payout, Long> {
    Optional<Payout> findByIdempotencyKey(String idempotencyKey);
}
