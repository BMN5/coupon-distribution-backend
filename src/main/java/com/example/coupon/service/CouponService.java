package com.example.coupon.service;

import com.example.coupon.model.Coupon;
import com.example.coupon.repository.CouponRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CouponService {
    private final CouponRepository couponRepository;

    public CouponService(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    public Optional<Coupon> claimCoupon(String clientIp) {
        Optional<Coupon> optionalCoupon = couponRepository.findFirstByClaimedFalse();

        if (optionalCoupon.isPresent()) {
            Coupon coupon = optionalCoupon.get();
            coupon.setClaimed(true);
            coupon.setClaimedByIp(clientIp);
            coupon.setClaimedAt(LocalDateTime.now());

            couponRepository.save(coupon);
            return Optional.of(coupon);
        }
        return Optional.empty();
    }

    public Coupon addCoupon(Coupon coupon) {
        coupon.setClaimed(false);
        return couponRepository.save(coupon);
    }
}
