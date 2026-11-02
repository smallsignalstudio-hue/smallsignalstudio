package com.getyourguide.supplier.repository;

import com.getyourguide.supplier.model.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {
    Optional<CustomerOrder> findByIdempotencyKey(String idempotencyKey);
    List<CustomerOrder> findByStatus(String status);
}
