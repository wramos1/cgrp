package cgrp.car_reservation.car_reservation.managerial;

import cgrp.car_reservation.car_reservation.reservation.Reservation;

import java.time.Month;
import java.time.Year;
import java.util.List;

public class YearRevenueCalculator implements RevenueCalculator {
    private final int startYear;
    private final int endYear;


    public YearRevenueCalculator(Year startYear, Year endYear) {
        this.endYear = endYear.getValue();
        this.startYear = startYear.getValue();

    }

    @Override
    public double calculateRange(List<Reservation> reservations) {
        double retval = 0;

        for (Reservation reservation : reservations) {
            int reservationYear = reservation.getReservationDate().getYear();

            // Check if reservationDate is between start and end
            if ((reservationYear>=startYear && reservationYear <= endYear)) {
                retval += reservation.getChargeAmount();
            }
        }

        return retval;
    }
}

