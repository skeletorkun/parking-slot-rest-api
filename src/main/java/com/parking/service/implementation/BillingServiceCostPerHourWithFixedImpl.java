package com.parking.service.implementation;


import com.parking.model.entity.BillingConfig;
import com.parking.model.entity.ParkingRecord;
import com.parking.service.BillingService;
import com.parking.util.ParkingUtils;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Map;

import static com.parking.model.entity.BillingConfig.FIXED_FEE;
import static com.parking.model.entity.BillingConfig.PRICE_PER_HOUR;

/**
 * Implementation of a Billing Service where
 * the customers are charged for each hour spent in the parking (nb hours * hour price) + a fixed fee
 */
@Service
public class BillingServiceCostPerHourWithFixedImpl implements BillingService {

    private static final Logger LOG = LoggerFactory.getLogger(BillingServiceCostPerHourWithFixedImpl.class);

    @Override
    public BigDecimal calculate(ParkingRecord parkingRecord, @NonNull BillingConfig config) {
        LOG.debug("Calculate cost with {} and {}", parkingRecord, config);

        Duration duration = ParkingUtils.calculateParkingDuration(parkingRecord);

        BigDecimal hours = new BigDecimal(duration.toHours());

        Map<String, String> parameters = config.getParameters();
        BigDecimal pph = new BigDecimal(parameters.get(PRICE_PER_HOUR));
        BigDecimal fixedFee = new BigDecimal(parameters.get(FIXED_FEE));

        BigDecimal cost = pph.multiply(hours).setScale(0, BigDecimal.ROUND_DOWN).add(fixedFee);

        LOG.debug("Cost is {}", cost);
        return cost;
    }

}
