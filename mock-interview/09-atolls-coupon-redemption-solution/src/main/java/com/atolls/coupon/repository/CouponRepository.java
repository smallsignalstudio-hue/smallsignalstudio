package com.atolls.coupon.repository;
import com.atolls.coupon.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;
public interface CouponRepository extends JpaRepository<Coupon, Long> {
    Optional<Coupon> findByCode(String code);
    long countByCampaignId(long campaignId);
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("update Coupon c set c.redeemedBy = :user where c.id = :id and c.redeemedBy is null")
    int claim(@Param("id") long id, @Param("user") long user);
}
