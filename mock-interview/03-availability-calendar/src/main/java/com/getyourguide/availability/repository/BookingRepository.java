package com.getyourguide.availability.repository;

import com.getyourguide.availability.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("select coalesce(sum(b.partySize), 0) from Booking b where b.activityId = :activityId and b.slotStart = :start")
    long partySizeAt(@Param("activityId") long activityId, @Param("start") Instant start);
}
