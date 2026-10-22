package com.getyourguide.catalog.repository;

import com.getyourguide.catalog.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

    @Query("select a from Activity a where a.title like %:query%")
    List<Activity> searchByTitle(@Param("query") String query);
}
