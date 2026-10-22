package com.getyourguide.tickethold.repository;

import com.getyourguide.tickethold.model.Hold;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface HoldRepository extends JpaRepository<Hold, Long> {

    Optional<Hold> findFirstByEventIdAndCustomerIdOrderByIdDesc(Long eventId, Long customerId);

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query(value = """
            UPDATE hold SET status = 'EXPIRED'
            WHERE status = 'HELD' AND expires_at < :now
            """, nativeQuery = true)
    int markExpiredHolds(@Param("now") LocalDateTime now);
}
