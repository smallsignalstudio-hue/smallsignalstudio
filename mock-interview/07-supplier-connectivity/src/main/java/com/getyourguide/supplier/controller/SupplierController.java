package com.getyourguide.supplier.controller;

import com.getyourguide.supplier.model.CustomerOrder;
import com.getyourguide.supplier.repository.CustomerOrderRepository;
import com.getyourguide.supplier.service.BookingService;
import com.getyourguide.supplier.service.PaymentDeclined;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;

@RestController
public class SupplierController {
    private final BookingService bookings;
    private final CustomerOrderRepository orders;

    public SupplierController(BookingService bookings, CustomerOrderRepository orders) {
        this.bookings = bookings;
        this.orders = orders;
    }

    @PostMapping("/orders")
    public Map<String, Object> create(@RequestBody Map<String, Object> body,
                                      @RequestHeader("Idempotency-Key") String key) {
        int party = ((Number) body.getOrDefault("partySize", 1)).intValue();
        return bookings.book(((Number) body.get("activityId")).longValue(),
                String.valueOf(body.get("localStart")), party, String.valueOf(body.getOrDefault("pay", "ok")), key);
    }

    @GetMapping("/orders/{id}")
    public Map<String, Object> order(@PathVariable long id) {
        CustomerOrder order = orders.findById(id).orElseThrow();
        return com.getyourguide.supplier.service.OrderView.of(order);
    }

    @GetMapping("/activities/{id}/slots")
    public List<String> slots(@PathVariable long id) {
        return bookings.slots(id);
    }
}

@RestControllerAdvice
class SupplierErrors {
    @ExceptionHandler(PaymentDeclined.class)
    ResponseEntity<Map<String, Object>> payment(PaymentDeclined exception) {
        return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED).body(exception.order());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    ResponseEntity<Void> party(IllegalArgumentException exception) {
        return ResponseEntity.badRequest().build();
    }
}
