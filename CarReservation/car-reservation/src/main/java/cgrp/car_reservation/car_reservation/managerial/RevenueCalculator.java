package cgrp.car_reservation.car_reservation.managerial;

import cgrp.car_reservation.car_reservation.reservation.Reservation;

import java.util.List;

public interface RevenueCalculator {
    public double calculateRange(List<Reservation> reservations);
}
