package cgrp.car_reservation.car_reservation.payment_card;

public class InvalidCardException extends RuntimeException {
    public InvalidCardException(String message) {
        super(message);
    }
}
