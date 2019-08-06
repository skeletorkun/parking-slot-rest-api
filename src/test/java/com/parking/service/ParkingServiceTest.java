/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.parking.service;

import com.parking.model.dto.ParkingRecordInputDTO;
import com.parking.model.entity.ParkingRecord;
import com.parking.model.entity.Space;
import com.parking.model.enums.VehicleType;
import com.parking.repository.SpaceRepository;
import com.parking.utils.TestDataSetupService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ParkingServiceTest {

    @Autowired
    ParkingService parkingService;

    @Autowired
    SpaceRepository spaceRepository;

    @Autowired
    private TestDataSetupService testDataSetupService;

    @Before
    public void before() {
        testDataSetupService.clearRepositories();
        testDataSetupService.createSimpleLot();
    }

    @After
    public void tearDown() throws Exception {
        testDataSetupService.clearRepositories();
    }

    @Test
    public void contextLoadsTest() {
        Assert.notNull(parkingService);
    }


    @Test
    public void createParkingTest() throws Exception {

        ParkingRecordInputDTO dto = new ParkingRecordInputDTO();
        dto.setVehicleType(VehicleType.ELECTRIC_50W);
        dto.setVehiclePlate("987-ABC");

        ParkingRecord res = parkingService.create(testDataSetupService.getLotId(), dto);

        // check end date & cost
        assertNull(res.getEndDate());
        assertNull(res.getCost());

        // check the space is now occupied
        Optional<Space> space = spaceRepository.findById(res.getSpaceId());
        assertEquals("987-ABC", space.get().getVehiclePlate());
    }

    @Test
    public void endParkingTest() {
        ParkingRecord p = testDataSetupService.createSimpleParking();

        ParkingRecord res = parkingService.end(testDataSetupService.getLotId(), p.getId());

        // check end date & cost
        assertTrue(res.getEndDate().before(new Date()));
        assertEquals("480", res.getCost().toPlainString());

        // check the space is now free
        Optional<Space> space = spaceRepository.findById(p.getSpaceId());
        assertNull(space.get().getVehiclePlate());
    }
}
