package com.parking.model.dto;


import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import com.parking.model.enums.VehicleType;

/**
 * Object used to receive the ParkingRecord data from the caller
 */
@Getter
@Setter
public class ParkingRecordInputDTO {

    @NonNull
    private String vehiclePlate;

    @NonNull
    private VehicleType vehicleType;

}
