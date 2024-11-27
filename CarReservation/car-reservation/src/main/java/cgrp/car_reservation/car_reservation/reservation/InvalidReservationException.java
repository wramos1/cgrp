package cgrp.car_reservation.car_reservation.reservation;

public class InvalidReservationException extends RuntimeException {
    public InvalidReservationException(String message) {
        super(message);
    }
}
