package com.getyourguide.reviews.repository;
import com.getyourguide.reviews.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CustomerRepository extends JpaRepository<Customer, Long> {}
