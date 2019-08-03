package com.parking.repository;

import com.parking.model.entity.BillingConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingConfigRepository extends JpaRepository<BillingConfig, Long> {

    BillingConfig findOneByLotId(long lotId);
}
