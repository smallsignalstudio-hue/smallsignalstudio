package com.atolls.coupon.model;
import jakarta.persistence.*;
@Entity @Table(name="redemption")
public class Redemption {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(name="coupon_id") private Long couponId;
    @Column(name="user_id") private Long userId;
    @Column(name="campaign_id") private Long campaignId;
    public static Redemption of(Long couponId, Long userId, Long campaignId){
        Redemption r=new Redemption(); r.couponId=couponId; r.userId=userId; r.campaignId=campaignId; return r;
    }
}
