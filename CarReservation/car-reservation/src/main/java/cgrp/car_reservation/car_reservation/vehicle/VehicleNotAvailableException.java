package cgrp.car_reservation.car_reservation.vehicle;

public class VehicleNotAvailableException extends RuntimeException{
    public VehicleNotAvailableException(String message){
        super(message);
    }
}
