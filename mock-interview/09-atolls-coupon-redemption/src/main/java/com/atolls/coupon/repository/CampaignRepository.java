package com.atolls.coupon.repository;
import com.atolls.coupon.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface CampaignRepository extends JpaRepository<Campaign, Long> {
    @Query("select c from Campaign c where c.market = :market or c.globalOffer = true and c.active = true")
    List<Campaign> visible(String market);
}
