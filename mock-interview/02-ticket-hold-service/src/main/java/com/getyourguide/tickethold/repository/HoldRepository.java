package com.getyourguide.tickethold.repository;

import com.getyourguide.tickethold.model.Hold;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface HoldRepository extends JpaRepository<Hold, Long> {

    @Modifying
    @Query(value = """
            UPDATE hold SET status = 'EXPIRED'
            WHERE status = 'HELD' AND expires_at < now() AT TIME ZONE 'UTC'
            """, nativeQuery = true)
    int markExpiredHolds();
}
