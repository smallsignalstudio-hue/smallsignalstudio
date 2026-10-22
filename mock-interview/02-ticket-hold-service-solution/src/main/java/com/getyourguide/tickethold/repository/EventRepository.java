package com.getyourguide.tickethold.repository;

import com.getyourguide.tickethold.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
