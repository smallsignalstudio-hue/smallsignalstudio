package com.atolls.coupon.controller;

import com.atolls.coupon.service.ConflictException;
import com.atolls.coupon.service.CouponService;
import com.atolls.coupon.service.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class CouponController {
    private final CouponService coupons;
    public CouponController(CouponService coupons) { this.coupons = coupons; }

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
}

@RestControllerAdvice
class CouponErrors {
    @ExceptionHandler(ConflictException.class)
    ResponseEntity<Void> conflict(ConflictException exception) { return ResponseEntity.status(409).build(); }
    @ExceptionHandler(NotFoundException.class)
    ResponseEntity<Void> missing(NotFoundException exception) { return ResponseEntity.notFound().build(); }
}
