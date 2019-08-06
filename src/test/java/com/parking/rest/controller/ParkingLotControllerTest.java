package com.parking.rest.controller;

import com.parking.model.dto.ParkingRecordInputDTO;
import com.parking.model.entity.Lot;
import com.parking.model.entity.ParkingRecord;
import com.parking.model.enums.VehicleType;
import com.parking.utils.TestDataSetupService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ParkingLotControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private TestDataSetupService testDataSetupService;

    @LocalServerPort
    private int port;

    private long id;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    public void contextLoads() {

    }

    @Before
    public void setUp() {
        Lot simpleLot = testDataSetupService.createSimpleLot();
        id = simpleLot.getId();
    }

    @After
    public void tearDown() {
        testDataSetupService.clearRepositories();
    }


    @Test
    public void testCreateParking() {

        // prep input
        ParkingRecordInputDTO input = new ParkingRecordInputDTO();
        input.setVehiclePlate("123-ABC-99");
        input.setVehicleType(VehicleType.ELECTRIC_20W);

        long id = testDataSetupService.getLotId();

        // act
        ResponseEntity<ParkingRecord> postResponse = restTemplate.postForEntity(getRootUrl() + "/lots/" + id + "/parkings", input, ParkingRecord.class);
        ParkingRecord parkingRecord = postResponse.getBody();

        // assert
        assertNotNull(parkingRecord);
        assertTrue(parkingRecord.getStartDate().before(new Date()));
        assertEquals(input.getVehiclePlate(), parkingRecord.getVehiclePlate());
    }


    @Test
    public void testCreateParkingRefusal() {

        // prep input
        ParkingRecordInputDTO input = new ParkingRecordInputDTO();
        input.setVehiclePlate("123-ABC-99");
        input.setVehicleType(VehicleType.ELECTRIC_20W);

        // no spaces!
        testDataSetupService.deleteAllSpaces();

        // act
        ResponseEntity<Object> postResponse = restTemplate.postForEntity(getRootUrl() + "/lots/" + id + "/parkings", input, Object.class);
        HttpStatus res = postResponse.getStatusCode();

        // assert
        assertEquals(406, res.value());
        assertEquals("Not Acceptable", res.getReasonPhrase());
    }


    @Test
    public void testEndParking() {

        // prepare
        ParkingRecord p = testDataSetupService.createSimpleParking();

        // act
        ResponseEntity<ParkingRecord> postResponse = restTemplate.postForEntity(getRootUrl() + "/lots/" + id + "/parkings/" + p.getId() + "/end", null, ParkingRecord.class);
        ParkingRecord parkingRecord = postResponse.getBody();

        // assert
        assertNotNull(parkingRecord);
        assertTrue(parkingRecord.getEndDate().before(new Date()));
        assertEquals(new BigDecimal("480"), parkingRecord.getCost());
    }

}