package com.parking.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ParkingExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ParkingExceptionHandler.class);

    @ExceptionHandler(NoAvailableParkingSpaceException.class)
    protected ResponseEntity<Object> handleNoAvailableParkingSpaceException(NoAvailableParkingSpaceException ex) {

        LOG.warn("Handling NoAvailableParkingSpaceException [{}]", ex.getMessage());
        Map<String, String> map = new HashMap<>();
        map.put("error", "NoAvailableParkingSpaceException");
        map.put("message", ex.getMessage());
        return new ResponseEntity<>(
                map, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);
    }
}
