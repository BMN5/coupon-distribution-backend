package com.example.coupon.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "coupons")
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String code;

    private boolean claimed = false;

    private String claimedByIp;
    private LocalDateTime claimedAt;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isClaimed() {
        return claimed;
    }

    public void setClaimed(boolean claimed) {
        this.claimed = claimed;
    }

    public String getClaimedByIp() {
        return claimedByIp;
    }

    public void setClaimedByIp(String claimedByIp) {
        this.claimedByIp = claimedByIp;
    }

    public LocalDateTime getClaimedAt() {
        return claimedAt;
    }

    public void setClaimedAt(LocalDateTime claimedAt) {
        this.claimedAt = claimedAt;
    }
}
