package com.getyourguide.availability.repository;

import com.getyourguide.availability.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("""
            select coalesce(sum(b.partySize), 0) from Booking b
            where b.activityId = :activityId and b.slotStart = :start and b.status <> 'CANCELLED'
            """)
    long confirmedPartyAt(@Param("activityId") long activityId, @Param("start") Instant start);

    @Query(value = """
            SELECT coalesce(sum(b.party_size), 0) FROM booking b
            JOIN activity a ON a.id = b.activity_id
            WHERE a.resource_id = :resourceId
              AND b.status <> 'CANCELLED'
              AND b.slot_start < :end
              AND b.slot_start + make_interval(mins => a.duration_minutes) > :start
            """, nativeQuery = true)
    long resourceUsage(@Param("resourceId") long resourceId, @Param("start") Instant start, @Param("end") Instant end);

    @Query(value = "SELECT capacity FROM resource_pool WHERE id = :id", nativeQuery = true)
    int poolCapacity(@Param("id") long id);
}
