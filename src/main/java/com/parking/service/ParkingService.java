package com.parking.service;


import com.parking.model.dto.ParkingRecordInputDTO;
import com.parking.model.entity.ParkingRecord;

public interface ParkingService {

    ParkingRecord create(long parkingLotId, ParkingRecordInputDTO inputDTO) throws Exception;

    ParkingRecord end(long lotId, long id);
}
