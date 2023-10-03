package org.workspace.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.workspace.model.Coupon;

public interface CouponRepo extends JpaRepository<Coupon, Long> {
    Coupon findByCode(String code);
}
