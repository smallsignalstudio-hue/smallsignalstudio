package com.atolls.coupon.service;

import com.atolls.coupon.model.Campaign;
import com.atolls.coupon.model.Coupon;
import com.atolls.coupon.model.Redemption;
import com.atolls.coupon.repository.CampaignRepository;
import com.atolls.coupon.repository.CouponRepository;
import com.atolls.coupon.repository.RedemptionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
public class CouponService {
    private final CampaignRepository campaigns;
    private final CouponRepository coupons;
    private final RedemptionRepository redemptions;
    private final Clock clock;

    public CouponService(CampaignRepository campaigns, CouponRepository coupons, RedemptionRepository redemptions, Clock clock) {
        this.campaigns = campaigns;
        this.coupons = coupons;
        this.redemptions = redemptions;
        this.clock = clock;
    }

    public Map<String, Object> campaign(long id) {
        Campaign campaign = campaigns.findById(id).orElseThrow(NotFoundException::new);
        return Map.of("id", campaign.getId(), "name", campaign.getName());
    }

    public List<Long> visible(String market) {
        return campaigns.visible(market).stream().map(Campaign::getId).toList();
    }

    @Transactional
    public Map<String, Object> redeem(long userId, String rawCode) {
        String code = rawCode.toUpperCase(Locale.forLanguageTag("tr"));
        Coupon coupon = coupons.findByCode(code).orElseThrow(NotFoundException::new);
        Campaign campaign = campaigns.findById(coupon.getCampaignId()).orElseThrow();
        LocalDate today = LocalDate.now(clock);
        if (!today.isBefore(campaign.getValidUntil())) {
            throw new ConflictException("expired");
        }
        if (redemptions.countByUserId(userId) >= campaign.getPerUserLimit()) {
            throw new ConflictException("limit");
        }
        if (coupon.getRedeemedBy() != null) {
            throw new ConflictException("used");
        }
        try {
            Thread.sleep(40);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        coupon.setRedeemedBy(userId);
        coupons.save(coupon);
        redemptions.save(Redemption.of(coupon.getId(), userId, campaign.getId()));
        return Map.of("couponId", coupon.getId(), "campaignId", campaign.getId());
    }

    public long redemptions(String code) {
        Coupon coupon = coupons.findByCode(code).orElseThrow(NotFoundException::new);
        return redemptions.countByCouponId(coupon.getId());
    }
}
