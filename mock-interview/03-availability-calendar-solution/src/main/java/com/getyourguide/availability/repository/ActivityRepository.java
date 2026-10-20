package com.getyourguide.availability.repository;

import com.getyourguide.availability.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
}
