package com.parking.repository;

import com.parking.model.entity.Space;
import com.parking.model.enums.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpaceRepository extends JpaRepository<Space, Long> {

    List<Space> findByLotIdAndVehicleTypeAndVehiclePlateIsNull(Long lotId, VehicleType type);


    @Query(" Select s from Space s "
            + " join fetch s.lot as l "
            + " where l.id = :lotId "
            + " and s.vehicleType = :vehicleType "
            + " and s.vehiclePlate is null ")
    List<Space> findEmptySpacesByLotIdAndVehicleType(@Param("lotId") Long lotId, @Param("vehicleType") VehicleType vehicleType);
}
