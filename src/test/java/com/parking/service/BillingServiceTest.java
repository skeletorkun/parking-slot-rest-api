/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.parking.service;

import com.parking.model.entity.BillingConfig;
import com.parking.model.entity.ParkingRecord;
import com.parking.service.implementation.BillingServiceCostPerHourImpl;
import com.parking.service.implementation.BillingServiceCostPerHourWithFixedImpl;
import com.parking.utils.TestDataSetupService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;

import static junit.framework.TestCase.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BillingServiceTest {

    @Autowired
    BillingServiceCostPerHourImpl billingServiceCostPerHour;

    @Autowired
    BillingServiceCostPerHourWithFixedImpl billingServiceCostPerHourWithFixed;

    @Autowired
    TestDataSetupService testDataSetupService;

    @Before
    public void before() {
        testDataSetupService.clearRepositories();
        testDataSetupService.createSimpleParking();

    }

    @After
    public void tearDown() throws Exception {
        testDataSetupService.clearRepositories();
    }

    @Test
    public void calculateTest_whenNoEndDate_shouldReturnZero() {

        ParkingRecord p = testDataSetupService.createSimpleParking();
        BillingConfig c = testDataSetupService.createBillingConfig();
        c.getParameters().put(BillingConfig.PRICE_PER_HOUR, "3");

        BigDecimal result = billingServiceCostPerHour.calculate(p, c);
        assertEquals(0, result.intValue());
    }


    @Test
    public void calculateTest_BaseCase() {

        ParkingRecord p = testDataSetupService.createSimpleParking();
        p.setEndDate(new Date());

        BillingConfig c = testDataSetupService.createBillingConfig();
        c.getParameters().put(BillingConfig.PRICE_PER_HOUR, "3");

        BigDecimal result = billingServiceCostPerHour.calculate(p, c);
        assertEquals(144, result.intValue());
    }
}
