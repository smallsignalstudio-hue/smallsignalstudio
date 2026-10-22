package com.atolls.coupon.controller;

import com.atolls.coupon.model.CodeJob;
import com.atolls.coupon.model.Coupon;
import com.atolls.coupon.repository.CodeJobRepository;
import com.atolls.coupon.repository.CouponRepository;
import com.atolls.coupon.service.CodeMint;
import com.atolls.coupon.service.ConflictException;
import com.atolls.coupon.service.CouponService;
import com.atolls.coupon.service.NotFoundException;
import com.atolls.coupon.service.TooManyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class CouponController {
    private final CouponService coupons;
    private final CodeMint mint;
    private final CodeJobRepository jobs;
    private final CouponRepository couponRepository;

    public CouponController(CouponService coupons, CodeMint mint, CodeJobRepository jobs, CouponRepository couponRepository) {
        this.coupons = coupons;
        this.mint = mint;
        this.jobs = jobs;
        this.couponRepository = couponRepository;
    }

    @GetMapping("/campaigns/{id}")
    public Map<String, Object> campaign(@PathVariable long id) { return coupons.campaign(id); }

    @GetMapping("/campaigns")
    public List<Long> visible(@RequestParam String market) { return coupons.visible(market); }

    @PostMapping("/redemptions")
    public ResponseEntity<Map<String, Object>> redeem(@RequestHeader("X-User-Id") long userId, @RequestBody Map<String, String> body) {
        return ResponseEntity.status(201).body(coupons.redeem(userId, body.get("code")));
    }

    @GetMapping("/coupons/{code}/redemptions")
    public Map<String, Long> count(@PathVariable String code) { return Map.of("count", coupons.redemptions(code)); }

    @PostMapping("/campaigns/{id}/codes")
    public Map<String, Object> generate(@PathVariable long id, @RequestBody Map<String, Integer> body) {
        CodeJob job = mint.start(id, body.get("count"));
        mint.run(job.getId());
        return Map.of("jobId", job.getId());
    }

    @GetMapping("/jobs/{id}")
    public Map<String, Object> job(@PathVariable long id) {
        CodeJob job = jobs.findById(id).orElseThrow(NotFoundException::new);
        return Map.of("status", job.getStatus(), "generated", job.getGenerated(), "requested", job.getRequested());
    }

    @GetMapping("/campaigns/{id}/codes")
    public List<String> codes(@PathVariable long id) {
        return couponRepository.findAll().stream().filter(coupon -> coupon.getCampaignId().equals(id) && coupon.getId() >= 1000)
                .map(Coupon::getCode).toList();
    }
}

@RestControllerAdvice
class CouponErrors {
    @ExceptionHandler(ConflictException.class)
    ResponseEntity<Void> conflict(ConflictException exception) { return ResponseEntity.status(409).build(); }
    @ExceptionHandler(NotFoundException.class)
    ResponseEntity<Void> missing(NotFoundException exception) { return ResponseEntity.notFound().build(); }
    @ExceptionHandler(TooManyException.class)
    ResponseEntity<Void> tooMany(TooManyException exception) { return ResponseEntity.status(429).build(); }
}
