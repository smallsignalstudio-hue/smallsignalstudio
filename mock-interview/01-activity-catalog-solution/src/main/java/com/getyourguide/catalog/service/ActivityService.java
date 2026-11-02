package com.getyourguide.catalog.service;

import com.getyourguide.catalog.dto.ActivityDto;
import com.getyourguide.catalog.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ActivityService {

    static final int MAX_SEARCH_RESULTS = 50;

    private static final Sort LIST_ORDER = Sort.by(
            Sort.Order.desc("rating").nullsLast(),
            Sort.Order.asc("id"));

    private final ActivityRepository activityRepository;
    private final ActivityMapper activityMapper;

    public List<ActivityDto> getActivities(int page, int size) {
        return activityRepository.findPageWithSupplier(PageRequest.of(page, size, LIST_ORDER))
                .map(activityMapper::toDto)
                .getContent();
    }

    public ActivityDto getActivity(long id) {
        return activityRepository.findWithSupplierById(id)
                .map(activityMapper::toDto)
                .orElseThrow(() -> new ActivityNotFoundException(id));
    }

    public List<ActivityDto> searchActivities(String query) {
        String pattern = "%" + escapeLike(query.trim().toLowerCase(Locale.ROOT)) + "%";
        return activityRepository.searchByTitleOrSupplierName(pattern, PageRequest.of(0, MAX_SEARCH_RESULTS))
                .stream()
                .map(activityMapper::toDto)
                .toList();
    }

    static String escapeLike(String value) {
        return value.replace("\\", "\\\\").replace("%", "\\%").replace("_", "\\_");
    }
}
