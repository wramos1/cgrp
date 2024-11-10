package cgrp.car_reservation.car_reservation.user;

public class UsernameAlreadyTakenException extends RuntimeException {
    public UsernameAlreadyTakenException(String message){
        super(message);
    }

}
