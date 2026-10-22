package com.atolls.coupon.model;
import jakarta.persistence.*;
@Entity @Table(name="coupon")
public class Coupon {
    @Id private Long id;
    @Column(name="campaign_id") private Long campaignId;
    private String code;
    @Column(name="redeemed_by") private Long redeemedBy;
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public Long getCampaignId(){return campaignId;} public void setCampaignId(Long campaignId){this.campaignId=campaignId;}
    public String getCode(){return code;} public void setCode(String code){this.code=code;}
    public Long getRedeemedBy(){return redeemedBy;}
    public void setRedeemedBy(Long redeemedBy){this.redeemedBy=redeemedBy;}
}
