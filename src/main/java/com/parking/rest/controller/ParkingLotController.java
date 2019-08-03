package com.parking.rest.controller;

import com.parking.service.ParkingService;
import com.parking.model.dto.ParkingRecordInputDTO;
import com.parking.model.entity.ParkingRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/lots/{lotId}")
public class ParkingLotController {

    private static final Logger LOG = LoggerFactory.getLogger(ParkingLotController.class);


    @Autowired
    private ParkingService parkingService;

    @PostMapping("/parkings")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ParkingRecord createParking(@PathVariable long lotId, @RequestBody ParkingRecordInputDTO inputDTO) throws Exception {
        LOG.debug("createParking for lotId {}, inputDTO {}", lotId, inputDTO);
        ParkingRecord parkingRecord = parkingService.create(lotId, inputDTO);
        LOG.debug("createParking result {}", parkingRecord);
        return parkingRecord;
    }

    @PostMapping("/parkings/{id}/end")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ParkingRecord endParking(@PathVariable Long lotId, @PathVariable Long id) throws Exception {
        LOG.debug("endParking for lotId {}, inputDTO {}", lotId, id);

        ParkingRecord parkingRecord = parkingService.end(lotId, id);

        LOG.debug("endParking result {}", parkingRecord);
        return parkingRecord;
    }
}
