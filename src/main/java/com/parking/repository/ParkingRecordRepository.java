package com.parking.repository;

import com.parking.model.entity.ParkingRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingRecordRepository extends JpaRepository<ParkingRecord, Long> {

    ParkingRecord findOneByVehiclePlateAndEndDateIsNull(String vehiclePlate);
}
