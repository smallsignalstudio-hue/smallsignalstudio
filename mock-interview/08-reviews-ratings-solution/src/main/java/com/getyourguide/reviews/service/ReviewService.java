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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReviewService {
    private static final BigDecimal PRIOR_MEAN = new BigDecimal("3.5");
    private static final BigDecimal PRIOR_WEIGHT = new BigDecimal("5");

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
        BigDecimal average = BigDecimal.valueOf(row.getRatingSum())
                .divide(BigDecimal.valueOf(row.getRatingCount()), 1, RoundingMode.HALF_UP);
        return Map.of("activityId", activityId, "average", average.toPlainString(), "count", row.getRatingCount());
    }

    @Transactional(readOnly = true)
    public Map<String, Object> page(long activityId, int limit, String cursor) {
        List<Review> rows;
        if (cursor == null || cursor.isBlank()) {
            rows = reviews.page(activityId, PageRequest.of(0, limit));
        } else {
            String[] parts = cursor.split(":");
            OffsetDateTime created = OffsetDateTime.ofInstant(Instant.ofEpochMilli(Long.parseLong(parts[0])), ZoneOffset.UTC);
            rows = reviews.after(activityId, created, Long.parseLong(parts[1]), PageRequest.of(0, limit));
        }
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("reviews", rows.stream().map(this::view).toList());
        body.put("nextCursor", rows.isEmpty() ? null : rows.get(rows.size() - 1).getCreatedAt().toInstant().toEpochMilli()
                + ":" + rows.get(rows.size() - 1).getId());
        return body;
    }

    @Transactional
    public Map<String, Object> create(long customerId, long bookingId, int stars, String text) {
        if (stars < 1 || stars > 5) {
            throw new IllegalArgumentException("rating");
        }
        String body = text == null ? "" : text.trim();
        if (body.codePointCount(0, body.length()) > 20) {
            throw new IllegalArgumentException("body");
        }
        Booking booking = bookings.findById(bookingId).orElseThrow();
        if (!booking.getCustomerId().equals(customerId)) {
            throw new ForbiddenException();
        }
        if (!"CONFIRMED".equals(booking.getStatus()) || !booking.getStartAt().toInstant().isBefore(Instant.now())) {
            throw new ConflictException();
        }
        Review review = new Review();
        review.setBookingId(bookingId);
        review.setAuthor(customers.findById(customerId).orElseThrow());
        review.setActivityId(booking.getActivityId());
        review.setRating(stars);
        review.setBody(body);
        review.setCreatedAt(OffsetDateTime.now(ZoneOffset.UTC));
        review.setHidden(false);
        reviews.save(review);
        ratings.add(booking.getActivityId(), stars);
        return Map.of("id", review.getId());
    }

    @Transactional
    public void hide(long reviewId) {
        Review review = reviews.findById(reviewId).orElseThrow();
        if (review.isHidden()) {
            return;
        }
        review.setHidden(true);
        reviews.save(review);
        ratings.subtract(review.getActivityId(), review.getRating());
    }

    @Transactional
    public void rebuild() {
        for (Object[] row : reviews.totals()) {
            long activityId = ((Number) row[0]).longValue();
            int sum = ((Number) row[1]).intValue();
            int count = ((Number) row[2]).intValue();
            ActivityRating rating = ratings.findById(activityId).orElseThrow();
            rating.setRatingSum(sum);
            rating.setRatingCount(count);
            rating.setAverageRating(count == 0 ? 0 : sum / count);
            ratings.save(rating);
        }
    }

    @Transactional(readOnly = true)
    public List<Long> relevant() {
        List<ActivityRating> rows = ratings.findAll();
        rows.sort(Comparator.comparing(this::bayesian).reversed());
        List<Long> ids = new ArrayList<>();
        for (ActivityRating row : rows) {
            ids.add(row.getActivityId());
        }
        return ids;
    }

    private BigDecimal bayesian(ActivityRating row) {
        BigDecimal sum = BigDecimal.valueOf(row.getRatingSum()).add(PRIOR_MEAN.multiply(PRIOR_WEIGHT));
        BigDecimal weight = BigDecimal.valueOf(row.getRatingCount()).add(PRIOR_WEIGHT);
        return sum.divide(weight, 4, RoundingMode.HALF_UP);
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
