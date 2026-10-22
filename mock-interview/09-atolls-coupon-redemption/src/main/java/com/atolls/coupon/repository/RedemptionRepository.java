package com.atolls.coupon.repository;
import com.atolls.coupon.model.Redemption;
import org.springframework.data.jpa.repository.JpaRepository;
public interface RedemptionRepository extends JpaRepository<Redemption, Long> {
    long countByUserId(long userId);
    long countByUserIdAndCampaignId(long userId, long campaignId);
    long countByCouponId(long couponId);
}
