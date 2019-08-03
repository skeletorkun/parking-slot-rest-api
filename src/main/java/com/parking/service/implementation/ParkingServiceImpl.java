package com.parking.service.implementation;


import com.parking.repository.ParkingRecordRepository;
import com.parking.repository.SpaceRepository;
import com.parking.service.BillingConfigService;
import com.parking.service.BillingService;
import com.parking.service.LotService;
import com.parking.service.ParkingService;
import lombok.NonNull;
import com.parking.model.dto.ParkingRecordInputDTO;
import com.parking.model.entity.BillingConfig;
import com.parking.model.entity.Lot;
import com.parking.model.entity.ParkingRecord;
import com.parking.model.entity.Space;
import com.parking.model.enums.PricingPolicy;
import com.parking.model.enums.VehicleType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ParkingServiceImpl implements ParkingService {

    private static final Logger LOG = LoggerFactory.getLogger(ParkingServiceImpl.class);

    @Autowired
    private BillingServiceFactory billingServiceFactory;

    @Autowired
    private LotService lotService;

    @Autowired
    private BillingConfigService billingConfigService;

    @Autowired
    ParkingRecordRepository parkingRecordRepository;

    @Autowired
    SpaceRepository spaceRepository;


    public ParkingRecord create(long parkingLotId, ParkingRecordInputDTO inputDTO) throws Exception {

        @NonNull String vehiclePlate = inputDTO.getVehiclePlate();
        @NonNull VehicleType vehicleType = inputDTO.getVehicleType();

        // check if there is an onoing parking for this vehicle
        if (parkingRecordRepository.findOneByVehiclePlateAndEndDateIsNull(vehiclePlate) != null) {
            throw new IllegalArgumentException("A vehicle with this plate is already parked!");
        }

        // get a suitable and available parking space
        List<Space> availableSpaces = spaceRepository.findByLotIdAndVehicleTypeAndVehiclePlateIsNull(parkingLotId, vehicleType);
        if(CollectionUtils.isEmpty(availableSpaces)){
            throw new Exception("No available space found for the given vehicle type");
        }

        // use the first available space
        Space space = availableSpaces.get(0);

        // create a new parking
        ParkingRecord newParking = new ParkingRecord();
        newParking.setLotId(parkingLotId);
        newParking.setSpaceId(space.getId());
        newParking.setStartDate(new Date()); // starting now
        newParking.setVehiclePlate(vehiclePlate);
        newParking.setVehicleType(vehicleType);

        space.setVehicleType(vehicleType);
        Space parkedSpace = spaceRepository.save(space);

        newParking.setSpaceId(parkedSpace.getId());
        return parkingRecordRepository.save(newParking);
    }

    @Override
    public ParkingRecord end(long lotId, long id) {

        // check if the parking lot exists
        Lot parkingLot = lotService.findById(lotId);
        if (parkingLot == null) {
            throw new EntityNotFoundException("Invalid Parking Lot id");
        }

        // check if the parking record exists
        ParkingRecord parkingRecord = parkingRecordRepository.findOne(id);
        if(parkingRecord == null){
            throw new EntityNotFoundException("No parking found with this id " + id);
        }

        // set end date
        parkingRecord.setEndDate(new Date());

        // calculate cost
        @NonNull PricingPolicy pricingPolicy = parkingLot.getPricingPolicy();
        @NonNull BillingConfig billingConfig = parkingLot.getBillingConfig();

        BillingService service = billingServiceFactory.getImplementationForPricing(pricingPolicy);
        BigDecimal cost = service.calculate(parkingRecord, billingConfig);

        parkingRecord.setCost(cost);

        ParkingRecord finishedParking = parkingRecordRepository.save(parkingRecord);
        LOG.debug("Parking ended successfully {}", finishedParking);
        return finishedParking;
    }
}
