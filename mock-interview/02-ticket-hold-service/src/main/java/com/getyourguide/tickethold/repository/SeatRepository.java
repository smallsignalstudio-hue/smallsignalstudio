package com.getyourguide.tickethold.repository;

import com.getyourguide.tickethold.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    List<Seat> findByEventIdOrderBySectionAscSeatRowAscSeatNumberAsc(Long eventId);

    List<Seat> findByEventIdAndSectionOrderBySeatRowAscSeatNumberAsc(Long eventId, String section);

    List<Seat> findByHoldId(Long holdId);

    @Modifying
    @Query(value = """
            UPDATE seat SET status = 'AVAILABLE', hold_id = NULL
            WHERE hold_id IN (SELECT id FROM hold WHERE expires_at < now() AT TIME ZONE 'UTC')
            """, nativeQuery = true)
    int releaseSeatsOfExpiredHolds();
}
