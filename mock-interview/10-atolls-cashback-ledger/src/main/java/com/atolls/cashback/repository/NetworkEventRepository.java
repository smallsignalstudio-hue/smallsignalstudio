package com.atolls.cashback.repository;

import com.atolls.cashback.model.NetworkEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NetworkEventRepository extends JpaRepository<NetworkEvent, Long> {
    List<NetworkEvent> findTop10ByProcessedFalseOrderByIdAsc();
}
