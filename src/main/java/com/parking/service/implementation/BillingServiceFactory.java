package com.parking.service.implementation;


import com.parking.model.enums.PricingPolicy;
import com.parking.service.BillingService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Factory class that matches a PricingPolicy with a BillingServiceImplementation
 */
@Service
public class BillingServiceFactory {

    @Autowired
    BillingServiceCostPerHourImpl billingServiceCostPerHour;

    public BillingService getImplementationForPricing(@NonNull PricingPolicy pricingPolicy) {

        switch (pricingPolicy) {
            case COST_PER_HOUR:
                return billingServiceCostPerHour;
            case FIXED_PLUS_COST_PER_HOUR:
            default:
                return null;
        }
    }
}
