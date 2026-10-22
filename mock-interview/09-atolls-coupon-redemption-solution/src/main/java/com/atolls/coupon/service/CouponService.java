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
import java.time.ZoneId;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
public class CouponService {
    private final CampaignRepository campaigns;
    private final CouponRepository coupons;
    private final RedemptionRepository redemptions;
    private final AttemptLimiter limiter;
    private final Clock clock;

    public CouponService(CampaignRepository campaigns, CouponRepository coupons, RedemptionRepository redemptions,
                         AttemptLimiter limiter, Clock clock) {
        this.campaigns = campaigns;
        this.coupons = coupons;
        this.redemptions = redemptions;
        this.limiter = limiter;
        this.clock = clock;
    }

    public Map<String, Object> campaign(long id) {
        Campaign campaign = campaigns.findById(id).orElseThrow(NotFoundException::new);
        return Map.of("id", campaign.getId(), "name", campaign.getName());
    }

    public List<Long> visible(String market) {
        return campaigns.findAll().stream()
                .filter(campaign -> (market.equals(campaign.getMarket()) || campaign.isGlobalOffer()) && campaign.isActive())
                .filter(campaign -> !LocalDate.now(clock.withZone(ZoneId.of(campaign.getZoneId()))).isAfter(campaign.getValidUntil()))
                .map(Campaign::getId)
                .sorted()
                .toList();
    }

    @Transactional
    public Map<String, Object> redeem(long userId, String rawCode) {
        limiter.check(userId);
        String code = rawCode.trim().toUpperCase(Locale.ROOT);
        Coupon coupon = coupons.findByCode(code).orElseThrow(() -> {
            limiter.miss(userId);
            return new NotFoundException();
        });
        Campaign campaign = campaigns.findById(coupon.getCampaignId()).orElseThrow();
        LocalDate today = LocalDate.now(clock.withZone(ZoneId.of(campaign.getZoneId())));
        if (today.isAfter(campaign.getValidUntil())) {
            throw new ConflictException("expired");
        }
        if (redemptions.countByUserIdAndCampaignId(userId, campaign.getId()) >= campaign.getPerUserLimit()) {
            throw new ConflictException("limit");
        }
        if (coupons.claim(coupon.getId(), userId) == 0) {
            throw new ConflictException("used");
        }
        redemptions.save(Redemption.of(coupon.getId(), userId, campaign.getId()));
        limiter.hit(userId);
        return Map.of("couponId", coupon.getId(), "campaignId", campaign.getId());
    }

    public long redemptions(String code) {
        Coupon coupon = coupons.findByCode(code).orElseThrow(NotFoundException::new);
        return redemptions.countByCouponId(coupon.getId());
    }
}
