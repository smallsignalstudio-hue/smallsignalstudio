package com.getyourguide.tickethold.repository;

import com.getyourguide.tickethold.model.Seat;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    List<Seat> findByEventIdOrderBySectionAscSeatRowAscSeatNumberAsc(Long eventId);

    List<Seat> findByEventIdAndSectionOrderBySeatRowAscSeatNumberAsc(Long eventId, String section);

    List<Seat> findByHoldId(Long holdId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select s from Seat s where s.id in :ids order by s.id")
    List<Seat> lockByIds(@Param("ids") Collection<Long> ids);

    @Query(value = """
            SELECT * FROM seat
            WHERE event_id = :eventId AND section = :section AND status = 'AVAILABLE'
            ORDER BY seat_row, seat_number
            FOR UPDATE SKIP LOCKED
            """, nativeQuery = true)
    List<Seat> lockAvailableInSection(@Param("eventId") long eventId, @Param("section") String section);

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query(value = """
            UPDATE seat SET status = 'AVAILABLE', hold_id = NULL
            WHERE status = 'HELD'
              AND hold_id IN (SELECT id FROM hold WHERE status = 'EXPIRED')
            """, nativeQuery = true)
    int releaseSeatsOfExpiredHolds();
}
