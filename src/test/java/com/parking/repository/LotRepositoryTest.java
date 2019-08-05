/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.parking.repository;

import com.parking.model.entity.Space;
import com.parking.model.enums.VehicleType;
import com.parking.utils.TestDataSetupService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpaceRepositoryTest {

    @Autowired
    SpaceRepository spaceRepository;

    @Autowired
    TestDataSetupService testDataSetupService;

    @Before
    public void before() {
        testDataSetupService.createSimpleLot();
    }

    @After
    public void tearDown() throws Exception {
        testDataSetupService.clearRepositories();
    }

    @Test
    public void testAvailableSpaces() {

        List<Space> res = spaceRepository.findByLotIdAndVehicleTypeAndVehiclePlateIsNull(2L, VehicleType.ELECTRIC_20W);
        assertEquals(10, res.size());
    }
}
