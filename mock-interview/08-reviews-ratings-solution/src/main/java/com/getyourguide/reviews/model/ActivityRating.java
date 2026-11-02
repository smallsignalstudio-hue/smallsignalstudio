package com.getyourguide.reviews.model;
import jakarta.persistence.*;
@Entity @Table(name = "activity_rating")
public class ActivityRating {
    @Id @Column(name = "activity_id") private Long activityId;
    @Column(name = "rating_sum") private int ratingSum;
    @Column(name = "rating_count") private int ratingCount;
    @Column(name = "average_rating") private int averageRating;
    public Long getActivityId() { return activityId; }
    public int getRatingSum() { return ratingSum; }
    public void setRatingSum(int ratingSum) { this.ratingSum = ratingSum; }
    public int getRatingCount() { return ratingCount; }
    public void setRatingCount(int ratingCount) { this.ratingCount = ratingCount; }
    public int getAverageRating() { return averageRating; }
    public void setAverageRating(int averageRating) { this.averageRating = averageRating; }
}
