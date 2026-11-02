package com.atolls.coupon.repository;
import com.atolls.coupon.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface CouponRepository extends JpaRepository<Coupon, Long> {
    Optional<Coupon> findByCode(String code);
    long countByCampaignId(long campaignId);
}
