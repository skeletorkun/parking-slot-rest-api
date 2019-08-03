package com.parking.model.enums;


/**
 * Billing Policies supported by the system
 */
public enum PricingPolicy {

    /**
     *  the customers are charged for each hour spent in the parking (nb hours * hour price)
     */
    COST_PER_HOUR,
    /**
     * a fixed amount + each hour spent in the parking (fixed amount + nb hours * hour price)
     */
    FIXED_PLUS_COST_PER_HOUR
}
