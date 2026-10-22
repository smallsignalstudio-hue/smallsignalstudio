package com.atolls.cashback.repository;

import com.atolls.cashback.model.Payout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayoutRepository extends JpaRepository<Payout, Long> {
}
