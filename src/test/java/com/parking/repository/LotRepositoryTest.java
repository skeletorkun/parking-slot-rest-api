/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.parking.repository;

import com.parking.model.entity.Lot;
import com.parking.model.entity.Space;
import com.parking.model.enums.VehicleType;
import com.parking.utils.TestDataSetupService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LotRepositoryTest {

    @Autowired
    LotRepository lotRepository;

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
    public void testBillingConfigFetch() {

        List<Lot> res = lotRepository.findAll();
        assertNotNull(res.get(0));
        assertNotNull(res.get(0).getBillingConfig());
    }
}
