package com.getyourguide.webhooks.repository;

import com.getyourguide.webhooks.model.Inbox;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InboxRepository extends JpaRepository<Inbox, Long> {
    Optional<Inbox> findByDedupKey(String dedupKey);
}
