package com.parking.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.parking.model.enums.PricingPolicy;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;


/**
 * A generic Object that provides a flexible billing configuration
 */

@Entity
@Table
@Getter
@Setter
@ToString
public class BillingConfig {

    public static String PRICE_PER_HOUR = "pricePerHour";
    public static String FIXED_FEE = "fixedFee";

    @Id
    @GeneratedValue
    private long id;

    @JsonIgnore
    @OneToOne
    private Lot lot;

    @NotNull
    private PricingPolicy pricingPolicy;

    @ElementCollection(fetch = FetchType.EAGER)
    Map<String, String> parameters = new HashMap<>();
}
