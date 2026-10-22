package com.getyourguide.catalog.controller;

import com.getyourguide.catalog.dto.ActivityDto;
import com.getyourguide.catalog.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/activities")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @GetMapping
    public ResponseEntity<List<ActivityDto>> getActivities(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.ok(activityService.getActivities(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActivityDto> getActivity(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(activityService.getActivity(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ActivityDto>> searchActivities(@RequestParam("q") String query) {
        return ResponseEntity.ok(activityService.searchActivities(query));
    }
}
