package com.parking.exception;

public class NoAvailableParkingSpaceException extends RuntimeException {

    private static final long serialVersionUID = -7842251223561640341L;

    public NoAvailableParkingSpaceException(String message) {
        super(message);
    }
}
