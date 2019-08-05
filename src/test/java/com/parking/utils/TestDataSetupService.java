
package com.parking.utils;


import com.parking.model.entity.BillingConfig;
import com.parking.model.entity.Lot;
import com.parking.model.entity.Space;
import com.parking.model.enums.PricingPolicy;
import com.parking.model.enums.VehicleType;
import com.parking.repository.BillingConfigRepository;
import com.parking.repository.LotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class DemoDataFactory {

    @Autowired
    private BillingConfigRepository billingConfigRepository;


    @Autowired
    private LotRepository lotRepository;

    public Lot create() {

        BillingConfig c = new BillingConfig();
        c.setPricingPolicy(PricingPolicy.COST_PER_HOUR);
        Map<String, String> params = new HashMap<>();
        params.put(BillingConfig.PRICE_PER_HOUR, "10");
        c.setParameters(params);
        billingConfigRepository.save(c);

        Lot lot = new Lot();
        lot.setName("Place Massena");
        lot.setBillingConfig(c);
        lotRepository.save(lot);

        Set<Space> spaces = new HashSet<>();

        for (int i = 0; i < 30; i++) {
            Space s = new Space();
            s.setVehicleType(VehicleType.values()[i % 3]); // 10 spaces of each vehicle type
            s.setLot(lot);
        }

        return lot;
    }


}
