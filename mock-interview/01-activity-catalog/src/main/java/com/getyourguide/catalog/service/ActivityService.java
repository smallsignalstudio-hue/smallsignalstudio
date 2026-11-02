package com.getyourguide.catalog.service;

import com.getyourguide.catalog.controller.SupplierController;
import com.getyourguide.catalog.dto.ActivityDto;
import com.getyourguide.catalog.model.Activity;
import com.getyourguide.catalog.model.Supplier;
import com.getyourguide.catalog.repository.ActivityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ActivityService {

    private static final double SPECIAL_OFFER_DISCOUNT = 0.1;

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private SupplierController supplierController;

    public List<ActivityDto> getActivities(int page, int size) {
        List<ActivityDto> result = new ArrayList<>();
        activityRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "rating")))
                .forEach(activity -> {
                    String supplierName = null;
                    String supplierLocation = null;
                    if (activity.getSupplier() != null) {
                        supplierName = activity.getSupplier().getName();
                        supplierLocation = activity.getSupplier().getCity() + ", " + activity.getSupplier().getCountry();
                    }
                    double finalPrice = activity.isSpecialOffer()
                            ? activity.getPrice() * (1 - SPECIAL_OFFER_DISCOUNT)
                            : activity.getPrice();
                    result.add(new ActivityDto(
                            activity.getId(),
                            activity.getTitle(),
                            activity.getPrice(),
                            finalPrice,
                            activity.getCurrency(),
                            activity.getRating(),
                            activity.isSpecialOffer(),
                            supplierName,
                            supplierLocation));
                });
        return result;
    }

    public ActivityDto getActivity(Long id) {
        Activity activity = activityRepository.findById(id).get();
        double finalPrice = activity.isSpecialOffer()
                ? activity.getPrice() * (1 - SPECIAL_OFFER_DISCOUNT)
                : activity.getPrice();
        return new ActivityDto(
                activity.getId(),
                activity.getTitle(),
                activity.getPrice(),
                finalPrice,
                activity.getCurrency(),
                activity.getRating(),
                activity.isSpecialOffer(),
                activity.getSupplier().getName(),
                activity.getSupplier().getCity() + ", " + activity.getSupplier().getCountry());
    }

    public List<ActivityDto> searchActivities(String query) {
        List<Supplier> suppliers = supplierController.searchSuppliers(query).getBody();
        log.debug("Search '{}' matched suppliers {}", query, suppliers);
        Set<Long> supplierIds = suppliers.stream().map(Supplier::getId).collect(Collectors.toSet());

        List<Activity> matches = new ArrayList<>(activityRepository.searchByTitle(query));
        activityRepository.findAll().forEach(activity -> {
            if (activity.getSupplier() != null && supplierIds.contains(activity.getSupplier().getId())) {
                matches.add(activity);
            }
        });

        List<ActivityDto> result = new ArrayList<>();
        matches.forEach(activity -> {
            double finalPrice = activity.isSpecialOffer()
                    ? activity.getPrice() * (1 - SPECIAL_OFFER_DISCOUNT)
                    : activity.getPrice();
            result.add(new ActivityDto(
                    activity.getId(),
                    activity.getTitle(),
                    activity.getPrice(),
                    finalPrice,
                    activity.getCurrency(),
                    activity.getRating(),
                    activity.isSpecialOffer(),
                    activity.getSupplier() == null ? null : activity.getSupplier().getName(),
                    activity.getSupplier().getCity() + ", " + activity.getSupplier().getCountry()));
        });
        return result;
    }
}
