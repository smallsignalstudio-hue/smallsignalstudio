package com.getyourguide.reviews.repository;

import com.getyourguide.reviews.model.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.OffsetDateTime;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("select distinct r from Review r join fetch r.author left join fetch r.photos where r.activityId = :activityId and r.hidden = false order by r.createdAt desc, r.id desc")
    List<Review> page(long activityId, Pageable pageable);

    @Query("select distinct r from Review r join fetch r.author left join fetch r.photos where r.activityId = :activityId and r.hidden = false and (r.createdAt < :created or (r.createdAt = :created and r.id < :id)) order by r.createdAt desc, r.id desc")
    List<Review> after(long activityId, OffsetDateTime created, long id, Pageable pageable);

    @Query("select r.activityId, sum(r.rating), count(r) from Review r where r.hidden = false group by r.activityId")
    List<Object[]> totals();
}
