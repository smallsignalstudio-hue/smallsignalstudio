package com.getyourguide.reviews.controller;

import com.getyourguide.reviews.model.Review;
import com.getyourguide.reviews.repository.ReviewRepository;
import com.getyourguide.reviews.service.ConflictException;
import com.getyourguide.reviews.service.ForbiddenException;
import com.getyourguide.reviews.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
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

    @GetMapping("/activities")
    public List<Long> relevant(@RequestParam(defaultValue = "relevant") String sort) {
        return reviews.relevant();
    }

    @PostMapping("/reviews")
    public ResponseEntity<Map<String, Object>> create(@RequestHeader("X-Customer-Id") long customerId,
                                                      @RequestBody Map<String, Object> body) {
        return ResponseEntity.status(201).body(reviews.create(customerId,
                ((Number) body.get("bookingId")).longValue(),
                ((Number) body.get("rating")).intValue(),
                String.valueOf(body.get("body"))));
    }

    @PostMapping("/reviews/{id}/hide")
    public void hide(@PathVariable long id) {
        reviews.hide(id);
    }

    @PostMapping("/internal/ratings/rebuild")
    public void rebuild() {
        reviews.rebuild();
    }
}

@RestControllerAdvice
class ReviewErrors {
    @ExceptionHandler(IllegalArgumentException.class)
    ResponseEntity<Void> bad(IllegalArgumentException exception) {
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(ForbiddenException.class)
    ResponseEntity<Void> forbidden(ForbiddenException exception) {
        return ResponseEntity.status(403).build();
    }

    @ExceptionHandler(ConflictException.class)
    ResponseEntity<Void> conflict(ConflictException exception) {
        return ResponseEntity.status(409).build();
    }
}
