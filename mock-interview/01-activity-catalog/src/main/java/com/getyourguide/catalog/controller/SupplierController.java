package com.getyourguide.catalog.controller;

import com.getyourguide.catalog.model.Supplier;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/suppliers")
public class SupplierController {

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping
    @ResponseBody
    @SuppressWarnings("unchecked")
    public ResponseEntity<List<Supplier>> getSuppliers() {
        List<Supplier> suppliers = entityManager
                .createNativeQuery("SELECT * FROM supplier", Supplier.class)
                .getResultList();
        return ResponseEntity.ok(suppliers);
    }

    @GetMapping("/search/{search}")
    @ResponseBody
    @SuppressWarnings("unchecked")
    public ResponseEntity<List<Supplier>> searchSuppliers(@PathVariable String search) {
        List<Supplier> suppliers = entityManager
                .createNativeQuery("SELECT * FROM supplier WHERE name LIKE '%" + search + "%'", Supplier.class)
                .getResultList();
        return ResponseEntity.ok(suppliers);
    }
}
