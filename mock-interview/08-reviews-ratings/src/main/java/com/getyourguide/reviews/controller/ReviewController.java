package com.getyourguide.reviews.controller;

import com.getyourguide.reviews.model.Review;
import com.getyourguide.reviews.repository.ReviewRepository;
import com.getyourguide.reviews.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class ReviewController {
    private final ReviewService reviews;
    private final ReviewRepository repository;

    public ReviewController(ReviewService reviews, ReviewRepository repository) {
        this.reviews = reviews;
        this.repository = repository;
    }

    @GetMapping("/reviews/{id}")
    public Map<String, Object> one(@PathVariable long id) {
        Review review = repository.findById(id).orElseThrow();
        return Map.of("id", review.getId(), "body", review.getBody());
    }

    @GetMapping("/activities/{id}/rating")
    public Map<String, Object> rating(@PathVariable long id) {
        return reviews.rating(id);
    }

    @GetMapping("/activities/{id}/reviews")
    public Map<String, Object> page(@PathVariable long id, @RequestParam(defaultValue = "10") int limit,
                                    @RequestParam(required = false) String cursor) {
        return reviews.page(id, limit, cursor);
    }

    @PostMapping("/reviews")
    public ResponseEntity<Map<String, Object>> create(@RequestHeader("X-Customer-Id") long customerId,
                                                      @RequestBody Map<String, Object> body) {
        try {
            return ResponseEntity.status(201).body(reviews.create(customerId,
                    ((Number) body.get("bookingId")).longValue(),
                    ((Number) body.get("rating")).intValue(),
                    String.valueOf(body.get("body"))));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        }
    }
}
