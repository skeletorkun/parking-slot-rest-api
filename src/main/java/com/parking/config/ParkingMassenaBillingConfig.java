package com.parking.config;


import com.parking.model.entity.BillingConfig;
import com.parking.model.enums.PricingPolicy;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotNull;


/**
 * A generic entity that provides a flexible billing configuration
 */
@Getter
@Setter
@ToString
@Configuration
public class ParkingMassenaBillingConfig extends BillingConfig {

    @NotNull
    private PricingPolicy pricingPolicy = PricingPolicy.COST_PER_HOUR;

    @Value("${billing.config.massena.costperhour:10}")
    public int costPerHour;

}
