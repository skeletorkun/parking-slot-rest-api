package com.parking.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import com.parking.model.enums.VehicleType;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * Parking Space entity that represents the slot for a vehicle in a parking lot.
 */
@Entity
@Table
@Getter
@Setter
@ToString
public class Space implements Serializable {

    @Id
    private long id;

    private String vehiclePlate;

    @NonNull
    private VehicleType vehicleType;

    @JsonIgnore
    @ManyToOne(fetch= FetchType.LAZY)
    private Lot lot;
}
