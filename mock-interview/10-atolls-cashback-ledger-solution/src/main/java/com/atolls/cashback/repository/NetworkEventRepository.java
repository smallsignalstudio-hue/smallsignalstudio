package com.atolls.cashback.repository;

import com.atolls.cashback.model.NetworkEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface NetworkEventRepository extends JpaRepository<NetworkEvent, Long> {
    Optional<NetworkEvent> findByExternalId(String externalId);

    @Query(value = "select id from network_event where processed = false order by id for update skip locked limit 10", nativeQuery = true)
    List<Number> lockUnprocessed();
}
