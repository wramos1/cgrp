package cgrp.car_reservation.car_reservation.managerial;

import cgrp.car_reservation.car_reservation.reservation.Reservation;

import java.util.List;

/**
 * Module Name: RevenueCalculator.java<br>
 *
 * Date of code: 11/10/2024<br>
 *
 * Programmers Name: Alberto<br>
 *
 * Description: Interface class with methods for calculating
 * revenue<br>
 *
 * Functions:<br>
 *  -calculateRange(): calculates reservation charges from specified time<br>
 *
 * Datastructures: N/A<br>
 */


public interface RevenueCalculator {
    public double calculateRange(List<Reservation> reservations);
}
