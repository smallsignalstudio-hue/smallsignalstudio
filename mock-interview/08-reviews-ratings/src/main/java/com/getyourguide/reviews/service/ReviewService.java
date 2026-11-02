package com.getyourguide.reviews.service;

import com.getyourguide.reviews.model.ActivityRating;
import com.getyourguide.reviews.model.Booking;
import com.getyourguide.reviews.model.Photo;
import com.getyourguide.reviews.model.Review;
import com.getyourguide.reviews.repository.ActivityRatingRepository;
import com.getyourguide.reviews.repository.BookingRepository;
import com.getyourguide.reviews.repository.CustomerRepository;
import com.getyourguide.reviews.repository.ReviewRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReviewService {
    private final ReviewRepository reviews;
    private final BookingRepository bookings;
    private final CustomerRepository customers;
    private final ActivityRatingRepository ratings;

    public ReviewService(ReviewRepository reviews, BookingRepository bookings, CustomerRepository customers,
                         ActivityRatingRepository ratings) {
        this.reviews = reviews;
        this.bookings = bookings;
        this.customers = customers;
        this.ratings = ratings;
    }

    @Transactional(readOnly = true)
    public Map<String, Object> rating(long activityId) {
        ActivityRating row = ratings.findById(activityId).orElseThrow();
        return Map.of("activityId", activityId, "average", Integer.toString(row.getAverageRating()), "count", row.getRatingCount());
    }

    @Transactional(readOnly = true)
    public Map<String, Object> page(long activityId, int limit, String cursor) {
        int page = (cursor == null || cursor.isBlank()) ? 0 : Integer.parseInt(cursor);
        List<Review> rows = reviews.page(activityId, PageRequest.of(page, limit));
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("reviews", rows.stream().map(this::view).toList());
        body.put("nextCursor", Integer.toString(page + 1));
        return body;
    }

    @Transactional
    public Map<String, Object> create(long customerId, long bookingId, int stars, String text) {
        if (stars < 1 || stars > 5) {
            throw new IllegalArgumentException("rating");
        }
        String body = text == null ? "" : text.trim();
        if (body.length() > 20) {
            throw new IllegalArgumentException("body");
        }
        Booking booking = bookings.findById(bookingId).orElseThrow();
        Review review = new Review();
        review.setBookingId(bookingId);
        review.setAuthor(customers.findById(customerId).orElseThrow());
        review.setActivityId(booking.getActivityId());
        review.setRating(stars);
        review.setBody(body);
        review.setCreatedAt(OffsetDateTime.now(ZoneOffset.UTC));
        review.setHidden(false);
        reviews.save(review);
        ActivityRating row = ratings.findById(booking.getActivityId()).orElseThrow();
        int sum = row.getRatingSum();
        int count = row.getRatingCount();
        try {
            Thread.sleep(40);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        row.setRatingSum(sum + stars);
        row.setRatingCount(count + 1);
        row.setAverageRating(row.getRatingSum() / row.getRatingCount());
        ratings.save(row);
        return Map.of("id", review.getId());
    }

    private Map<String, Object> view(Review review) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("id", review.getId());
        body.put("author", review.getAuthor().getName());
        body.put("body", review.getBody());
        body.put("photos", review.getPhotos().stream().map(Photo::getUrl).toList());
        return body;
    }
}
