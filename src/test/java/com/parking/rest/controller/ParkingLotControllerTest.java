package com.parking.rest.controller;

import com.parking.config.Config;
import com.parking.model.dto.ParkingRecordInputDTO;
import com.parking.model.entity.ParkingRecord;
import com.parking.model.enums.VehicleType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertNotNull;

//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = Config.class)

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ParkingLotControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    public void contextLoads() {

    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
    }


    @Test
    public void testCreateParking() {
        ParkingRecordInputDTO input = new ParkingRecordInputDTO();
        input.setVehiclePlate("123-ABC-99");
        input.setVehicleType(VehicleType.ELECTRIC_20W);

        ResponseEntity<ParkingRecord> postResponse = restTemplate.postForEntity(getRootUrl() + "/lots/1/parkings", input, ParkingRecord.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

}