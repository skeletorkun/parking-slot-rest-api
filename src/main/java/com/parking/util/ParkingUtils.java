package com.parking.util;

import com.parking.model.entity.ParkingRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;


public class ParkingUtils {

    private static final Logger LOG = LoggerFactory.getLogger(ParkingUtils.class);

    /**
     * Utility method to calculate the duration of a parking
     * Returns 0 if the parking record is incomplete
     *
     * @param parkingRecord
     * @return
     */
    public static Duration calculateParkingDuration(ParkingRecord parkingRecord) {

        Duration result = null;
        if (parkingRecord == null || parkingRecord.getStartDate() == null || parkingRecord.getEndDate() == null) {
            result = Duration.ofMillis(0);
        } else {
            result = Duration.ofMillis(parkingRecord.getEndDate().getTime() - parkingRecord.getStartDate().getTime());
        }

        LOG.debug("Duration is {}", result);

        return result;
    }
}
