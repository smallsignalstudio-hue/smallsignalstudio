package com.atolls.coupon.repository;
import com.atolls.coupon.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CampaignRepository extends JpaRepository<Campaign, Long> {}
