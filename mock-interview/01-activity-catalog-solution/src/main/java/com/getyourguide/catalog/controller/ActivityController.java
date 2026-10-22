package com.getyourguide.catalog.controller;

import com.getyourguide.catalog.dto.ActivityDto;
import com.getyourguide.catalog.service.ActivityService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/activities")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;

    @GetMapping
    public List<ActivityDto> getActivities(
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "20") @Min(1) @Max(100) int size) {
        return activityService.getActivities(page, size);
    }

    @GetMapping("/{id}")
    public ActivityDto getActivity(@PathVariable long id) {
        return activityService.getActivity(id);
    }

    @GetMapping("/search")
    public List<ActivityDto> searchActivities(@RequestParam("q") @NotBlank @Size(max = 100) String query) {
        return activityService.searchActivities(query);
    }
}
