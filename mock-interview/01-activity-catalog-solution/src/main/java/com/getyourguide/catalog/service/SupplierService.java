package com.getyourguide.catalog.service;

import com.getyourguide.catalog.dto.PageResponse;
import com.getyourguide.catalog.dto.SupplierDto;
import com.getyourguide.catalog.dto.SupplierStatisticsDto;
import com.getyourguide.catalog.model.Supplier;
import com.getyourguide.catalog.repository.ActivityRepository;
import com.getyourguide.catalog.repository.CheapestPriceRow;
import com.getyourguide.catalog.repository.SupplierRepository;
import com.getyourguide.catalog.repository.SupplierStatisticsRow;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SupplierService {

    private final SupplierRepository supplierRepository;
    private final ActivityRepository activityRepository;

    public List<SupplierDto> getSuppliers() {
        return supplierRepository.findAllByOrderByNameAsc().stream().map(SupplierService::toDto).toList();
    }

    public List<SupplierDto> searchSuppliers(String name) {
        return supplierRepository.findByNameContainingIgnoreCaseOrderByNameAsc(name.trim()).stream()
                .map(SupplierService::toDto)
                .toList();
    }

    public PageResponse<SupplierStatisticsDto> getStatistics(int page, int size) {
        Page<SupplierStatisticsRow> rows = supplierRepository.findSupplierStatistics(PageRequest.of(page, size));
        List<Long> supplierIds = rows.getContent().stream().map(SupplierStatisticsRow::supplierId).toList();

        Map<Long, List<SupplierStatisticsDto.Price>> cheapestBySupplier = supplierIds.isEmpty()
                ? Map.of()
                : activityRepository.findCheapestPrices(supplierIds).stream()
                .collect(Collectors.groupingBy(
                        CheapestPriceRow::supplierId,
                        Collectors.mapping(row -> new SupplierStatisticsDto.Price(row.currency(), row.amount()),
                                Collectors.toList())));

        return PageResponse.of(rows, row -> new SupplierStatisticsDto(
                row.supplierId(),
                row.supplierName(),
                row.activityCount(),
                round(row.averageRating()),
                cheapestBySupplier.getOrDefault(row.supplierId(), List.of())));
    }

    private static Double round(Double value) {
        return value == null ? null : BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    private static SupplierDto toDto(Supplier supplier) {
        return new SupplierDto(supplier.getId(), supplier.getName(), supplier.getAddress(), supplier.getZip(),
                supplier.getCity(), supplier.getCountry());
    }
}
