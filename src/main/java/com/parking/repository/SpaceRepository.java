package com.parking.repository;

import com.parking.model.entity.Space;
import com.parking.model.enums.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpaceRepository extends JpaRepository<Space, Long> {

    List<Space> findByLotIdAndVehicleTypeAndVehiclePlateIsNull(Long lotId, VehicleType type);
}
