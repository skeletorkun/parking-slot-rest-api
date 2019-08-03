package com.parking.model.entity;


import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import com.parking.model.enums.VehicleType;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Parking record to capture the entrance and exit of a vehicle
 */
@Entity
@Table
@Getter
@Setter
@ToString
public class ParkingRecord {

    @Id
    private long id;

    private long lotId;

    private long spaceId;

    @NonNull
    private String vehiclePlate;

    @NonNull
    private VehicleType vehicleType;

    @NonNull
    private Date startDate;

    private Date endDate;

    private BigDecimal cost;


}
