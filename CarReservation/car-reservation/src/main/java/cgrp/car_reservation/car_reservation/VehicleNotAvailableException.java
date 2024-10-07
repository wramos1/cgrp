package cgrp.car_reservation.car_reservation;

public class VehicleNotAvailableException extends RuntimeException{
    public VehicleNotAvailableException(String message){
        super(message);
    }
}
