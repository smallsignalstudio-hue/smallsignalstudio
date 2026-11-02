package com.getyourguide.catalog.controller;

import com.getyourguide.catalog.dto.PageResponse;
import com.getyourguide.catalog.dto.SupplierDto;
import com.getyourguide.catalog.dto.SupplierStatisticsDto;
import com.getyourguide.catalog.service.SupplierService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/suppliers")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService supplierService;

    @GetMapping
    public List<SupplierDto> getSuppliers() {
        return supplierService.getSuppliers();
    }

    @GetMapping("/search/{search}")
    public List<SupplierDto> searchSuppliers(@PathVariable @Size(max = 100) String search) {
        return supplierService.searchSuppliers(search);
    }

    @GetMapping("/statistics")
    public PageResponse<SupplierStatisticsDto> getStatistics(
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "20") @Min(1) @Max(100) int size) {
        return supplierService.getStatistics(page, size);
    }
}
