package com.atolls.cashback.repository;

import com.atolls.cashback.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("update Wallet w set w.availableCents = w.availableCents + :delta where w.userId = :userId")
    int addAvailable(@Param("userId") long userId, @Param("delta") int delta);
}
