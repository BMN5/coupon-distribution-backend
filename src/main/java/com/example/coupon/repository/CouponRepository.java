package com.example.coupon.repository;

import com.example.coupon.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {
    List<Coupon> findByClaimedFalse(); // Find all unclaimed coupons
    Optional<Coupon> findFirstByClaimedFalse(); // Find first available coupon
}
