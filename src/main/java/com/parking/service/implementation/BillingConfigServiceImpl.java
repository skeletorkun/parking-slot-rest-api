package com.parking.service.implementation;


import com.parking.model.entity.BillingConfig;
import com.parking.repository.BillingConfigRepository;
import com.parking.service.BillingConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillingConfigServiceImpl implements BillingConfigService {

    private static final Logger LOG = LoggerFactory.getLogger(BillingConfigServiceImpl.class);

    @Autowired
    BillingConfigRepository billingConfigRepository;

    @Override
    public BillingConfig create(BillingConfig parkingRecord) {
        return null;
    }

    @Override
    public BillingConfig findByLotId(long lotId) {
        return billingConfigRepository.findOneByLotId(lotId);
    }
}
