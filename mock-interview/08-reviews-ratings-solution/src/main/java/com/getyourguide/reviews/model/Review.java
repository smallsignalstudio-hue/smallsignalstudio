package com.getyourguide.reviews.model;
import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
@Entity @Table(name = "review")
public class Review {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(name = "booking_id") private Long bookingId;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "customer_id") private Customer author;
    @Column(name = "activity_id") private Long activityId;
    private int rating;
    private String body;
    @Column(name = "created_at") private OffsetDateTime createdAt;
    private boolean hidden;
    @OneToMany(fetch = FetchType.LAZY) @JoinColumn(name = "review_id") private List<Photo> photos = new ArrayList<>();
    public Long getId() { return id; }
    public Customer getAuthor() { return author; }
    public void setAuthor(Customer author) { this.author = author; }
    public Long getActivityId() { return activityId; }
    public void setActivityId(Long activityId) { this.activityId = activityId; }
    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }
    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }
    public OffsetDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }
    public boolean isHidden() { return hidden; }
    public void setHidden(boolean hidden) { this.hidden = hidden; }
    public void setBookingId(Long bookingId) { this.bookingId = bookingId; }
    public List<Photo> getPhotos() { return photos; }
}
