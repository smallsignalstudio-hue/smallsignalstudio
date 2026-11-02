package com.atolls.coupon.service;

import com.atolls.coupon.model.CodeJob;
import com.atolls.coupon.model.Coupon;
import com.atolls.coupon.repository.CodeJobRepository;
import com.atolls.coupon.repository.CouponRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class CodeMint {
    public static final String ALPHABET = "ABCDEFGHJKLMNPQRSTUVWXYZ23456789";
    private final SecureRandom random = new SecureRandom();
    private final CodeJobRepository jobs;
    private final CouponRepository coupons;

    public CodeMint(CodeJobRepository jobs, CouponRepository coupons) {
        this.jobs = jobs;
        this.coupons = coupons;
    }

    public CodeJob start(long campaignId, int requested) {
        return jobs.save(CodeJob.start(campaignId, requested));
    }

    @Async
    public void run(long jobId) {
        CodeJob job = jobs.findById(jobId).orElseThrow();
        long nextId = 1000;
        int made = 0;
        while (made < job.getRequested()) {
            Coupon coupon = new Coupon();
            coupon.setId(nextId++);
            coupon.setCampaignId(job.getCampaignId());
            coupon.setCode(randomCode());
            try {
                coupons.saveAndFlush(coupon);
                made++;
                job.setGenerated(made);
                jobs.save(job);
            } catch (DataIntegrityViolationException ex) {
                // collision, try another code
            }
        }
        job.setStatus("DONE");
        jobs.save(job);
    }

    private String randomCode() {
        StringBuilder code = new StringBuilder(8);
        for (int i = 0; i < 8; i++) {
            code.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));
        }
        return code.toString();
    }
}
