
package com.parking.utils;


import com.parking.model.entity.BillingConfig;
import com.parking.model.entity.Lot;
import com.parking.model.entity.ParkingRecord;
import com.parking.model.entity.Space;
import com.parking.model.enums.PricingPolicy;
import com.parking.model.enums.VehicleType;
import com.parking.repository.BillingConfigRepository;
import com.parking.repository.LotRepository;
import com.parking.repository.ParkingRecordRepository;
import com.parking.repository.SpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@Transactional
public class TestDataSetupService {

    @Autowired
    private BillingConfigRepository billingConfigRepository;


    @Autowired
    private LotRepository lotRepository;

    @Autowired
    private ParkingRecordRepository parkingRecordRepository;

    @Autowired
    private SpaceRepository spaceRepository;

    public ParkingRecord createSimpleParking() {
        ParkingRecord parkingRecord = new ParkingRecord();
        parkingRecord.setSpaceId(getFreeSpace(VehicleType.ELECTRIC_20W));
        parkingRecord.setLotId(getLotId());
        parkingRecord.setStartDate(Date.from(Instant.now().minus(2, ChronoUnit.DAYS)));
        parkingRecord.setVehiclePlate("123-ABC-QQ");
        parkingRecord.setVehicleType(VehicleType.ELECTRIC_20W);
        return parkingRecordRepository.save(parkingRecord);
    }

    private long getFreeSpace(VehicleType type) {
        List<Space> spaces = spaceRepository.findEmptySpacesByLotIdAndVehicleType(getLotId(), type);
        if(spaces.isEmpty())
            return 0;
        return spaces.get(0).getId();
    }

    public long getLotId() {
        List<Lot> all = lotRepository.findAll();
        if (all.isEmpty()) {
            return 0;
        }
        return all.get(0).getId();
    }

    public Lot createSimpleLot() {

        // lot
        Lot lot = new Lot();
        lot.setName("Place Massena");
        Lot savedLot = lotRepository.save(lot);

        // billing config
        BillingConfig c = createBillingConfig();
        c.setLot(savedLot);
        billingConfigRepository.save(c);

        // spaces
        Set<Space> spaces = new HashSet<>();
        for (int i = 0; i < 30; i++) {
            Space s = new Space();
            s.setVehicleType(VehicleType.values()[i % 3]); // 10 spaces of each vehicle type
            s.setLot(lot);
            spaceRepository.save(s);
            spaces.add(s);
        }

        lot.setSpaces(spaces);

        return lot;
    }

    public BillingConfig createBillingConfig() {
        BillingConfig c = new BillingConfig();
        c.setPricingPolicy(PricingPolicy.COST_PER_HOUR);
        Map<String, String> params = new HashMap<>();
        params.put(BillingConfig.PRICE_PER_HOUR, "10");
        c.setParameters(params);
        return c;
    }

    public void deleteAllSpaces() {
        spaceRepository.deleteAll();
    }

    public void clearRepositories() {
        parkingRecordRepository.deleteAll();
        spaceRepository.deleteAll();
        billingConfigRepository.deleteAll();
        lotRepository.deleteAll();
    }


}
