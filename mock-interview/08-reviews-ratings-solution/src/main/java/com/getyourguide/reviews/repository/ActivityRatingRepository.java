package com.getyourguide.reviews.repository;

import com.getyourguide.reviews.model.ActivityRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ActivityRatingRepository extends JpaRepository<ActivityRating, Long> {
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("update ActivityRating r set r.ratingSum = r.ratingSum + :stars, r.ratingCount = r.ratingCount + 1 where r.activityId = :id")
    int add(@Param("id") long id, @Param("stars") int stars);

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("update ActivityRating r set r.ratingSum = r.ratingSum - :stars, r.ratingCount = r.ratingCount - 1 where r.activityId = :id")
    int subtract(@Param("id") long id, @Param("stars") int stars);
}
