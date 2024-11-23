package cgrp.car_reservation.car_reservation.managerial;

import cgrp.car_reservation.car_reservation.reservation.Reservation;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

/**
 * Module Name: MonthRevenueCalculator.java<br>
 *
 * Date of code: 11/21/2024<br>
 *
 * Programmers Name: Alberto<br>
 *
 * Description: Service class with methods for calculating
 * revenue from reservations made in past month. Extends RevenueCalculator<br>
 *
 * Functions:<br>
 *  -calculateRange(): calculates reservation charges from all reservations
 *  of the past month<br>
 *
 * Datastructures: N/A<br>
 */

public class MonthRevenueCalculator implements RevenueCalculator {
    private final int startMonth;
    private final int endMonth;


    public MonthRevenueCalculator(Month startMonth, Month endMonth) {
        this.endMonth = endMonth.getValue();
        this.startMonth = startMonth.getValue();

    }

    @Override
    public double calculateRange(List<Reservation> reservations) {
        double retval = 0;

        for (Reservation reservation : reservations) {
            int reservationMonth = reservation.getReservationDate().getMonth().getValue();

            // Check if reservationDate is between start and end
            if ((reservationMonth>=startMonth && reservationMonth <= endMonth)) {
                retval += reservation.getChargeAmount();
            }
        }

        return retval;
    }
}

