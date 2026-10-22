package com.getyourguide.reviews.repository;
import com.getyourguide.reviews.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.OffsetDateTime;
import java.util.List;
public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("select r from Review r where r.activityId = :activityId and r.hidden = false order by r.createdAt desc, r.id desc")
    List<Review> page(long activityId, org.springframework.data.domain.Pageable pageable);
    @Query("select r from Review r where r.activityId = :activityId and r.hidden = false and (r.createdAt < :created or (r.createdAt = :created and r.id < :id)) order by r.createdAt desc, r.id desc")
    List<Review> after(long activityId, OffsetDateTime created, long id, org.springframework.data.domain.Pageable pageable);
}
