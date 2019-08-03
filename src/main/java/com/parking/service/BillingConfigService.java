package com.parking.service;


import com.parking.model.entity.BillingConfig;

public interface BillingConfigService {
    BillingConfig create(BillingConfig parkingRecord);

    BillingConfig findByLotId(long lotId);
}
