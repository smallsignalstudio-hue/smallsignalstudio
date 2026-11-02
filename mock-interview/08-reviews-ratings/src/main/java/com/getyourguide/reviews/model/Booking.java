package com.getyourguide.reviews.model;
import jakarta.persistence.*;
import java.time.OffsetDateTime;
@Entity @Table(name = "booking")
public class Booking {
    @Id private Long id;
    @Column(name = "customer_id") private Long customerId;
    @Column(name = "activity_id") private Long activityId;
    private String status;
    @Column(name = "start_at") private OffsetDateTime startAt;
    public Long getCustomerId() { return customerId; }
    public Long getActivityId() { return activityId; }
    public String getStatus() { return status; }
    public OffsetDateTime getStartAt() { return startAt; }
}
