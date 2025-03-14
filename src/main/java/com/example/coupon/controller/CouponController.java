package com.example.coupon.controller;

import com.example.coupon.model.Coupon;
import com.example.coupon.repository.CouponRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/coupons")
public class CouponController {

    private final CouponRepository couponRepository;

    public CouponController(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    // ✅ Add a new coupon
    @PostMapping("/add")
    public ResponseEntity<?> addCoupon(@RequestBody Coupon coupon) {
        coupon.setClaimed(false); // Ensure new coupon is unclaimed
        couponRepository.save(coupon);
        return ResponseEntity.ok("Coupon added successfully!");
    }

    // ✅ Get all coupons
    @GetMapping("/all")
    public ResponseEntity<List<Coupon>> getAllCoupons() {
        return ResponseEntity.ok(couponRepository.findAll());
    }

    // ✅ Claim a coupon
    @GetMapping("/claim")
    public ResponseEntity<?> claimCoupon(@RequestHeader(value = "X-Forwarded-For", required = false) String clientIp) {
        Optional<Coupon> couponOptional = couponRepository.findFirstByClaimedFalse();

        if (couponOptional.isEmpty()) {
            return ResponseEntity.status(404).body("No coupons available.");
        }

        Coupon coupon = couponOptional.get();
        coupon.setClaimed(true);
        coupon.setClaimedByIp(clientIp != null ? clientIp : "Unknown");
        coupon.setClaimedAt(LocalDateTime.now());

        couponRepository.save(coupon);
        return ResponseEntity.ok(coupon);
    }
}
