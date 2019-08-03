package com.parking.service;


import lombok.NonNull;
import com.parking.model.entity.BillingConfig;
import com.parking.model.entity.ParkingRecord;

import java.math.BigDecimal;

public interface BillingService {
    BigDecimal calculate(ParkingRecord parkingRecord, @NonNull BillingConfig billingConfig);
}
