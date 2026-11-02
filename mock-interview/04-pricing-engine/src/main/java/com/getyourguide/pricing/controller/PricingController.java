package com.getyourguide.pricing.controller;

import com.getyourguide.pricing.dto.QuoteRequest;
import com.getyourguide.pricing.service.PricingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.math.BigDecimal;
import java.util.Map;

@RestController
public class PricingController {

    private final PricingService pricing;

    public PricingController(PricingService pricing) {
        this.pricing = pricing;
    }

    @PostMapping("/quotes")
    public Map<String, String> quote(@RequestBody QuoteRequest request) {
        return pricing.quote(request);
    }

    @GetMapping("/price-bands/match")
    public Map<String, String> match(@RequestParam String currency, @RequestParam String amount) {
        return pricing.matchBand(currency, new BigDecimal(amount));
    }
}

@RestControllerAdvice
class ErrorHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    ProblemDetail bad(IllegalArgumentException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
    }
}
