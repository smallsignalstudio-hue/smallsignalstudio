package com.atolls.coupon.model;
import jakarta.persistence.*;
@Entity @Table(name="code_job")
public class CodeJob {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(name="campaign_id") private Long campaignId;
    private int requested;
    private int generated;
    private String status;
    public static CodeJob start(Long campaignId, int requested){
        CodeJob j=new CodeJob(); j.campaignId=campaignId; j.requested=requested; j.generated=0; j.status="RUNNING"; return j;
    }
    public Long getId(){return id;} public int getGenerated(){return generated;} public String getStatus(){return status;}
    public int getRequested(){return requested;} public Long getCampaignId(){return campaignId;}
    public void setGenerated(int generated){this.generated=generated;} public void setStatus(String status){this.status=status;}
}
