package com.parking.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


/**
 * Lot entity that represents the building that is the a collection of parking spaces
 * e.g. Parking Lot of Place Massena, Cours Saleya ..
 */
@Entity
@Table
@Getter
@Setter
@ToString
public class Lot implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    @NonNull
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "lot")
    private Set<Space> spaces = new HashSet<>();

    @NonNull
    @OneToOne(mappedBy = "lot")
    private BillingConfig billingConfig;

}
