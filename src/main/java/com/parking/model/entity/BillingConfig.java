package com.parking.model.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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

    @Id
    private long id;

    @OneToOne
    private Lot lot;

//    Map<String, String> parameters = new HashMap<>();
}
