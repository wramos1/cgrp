package cgrp.car_reservation.car_reservation.managerial;

import cgrp.car_reservation.car_reservation.reservation.Reservation;
import cgrp.car_reservation.car_reservation.reservation.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.List;

/**
 *
 * Module Name: RevenueService.java<br>
 *
 * Date of code: 11/10/2024<br>
 *
 * Programmers Name: Alberto<br>
 *
 * Description: Service class with logic for revenue calculations<br>
 * mostly
 *
 * Functions: calls functions with logic in RevenueCalculator child classes<br>
 *
 * Datastructures: N/A<br>
 *
 */

@Component
public class RevenueService {

    @Autowired
    private ReservationRepository reservationRepository;


    public double thisMonthRevenue(){
        List<Reservation> reservations = reservationRepository.findAll();
        return new MonthRevenueCalculator(LocalDate.now().getMonth(),LocalDate.now().getMonth()).calculateRange(reservations);
    }

    public double lastMonthRevenue(){
        List<Reservation> reservations = reservationRepository.findAll();
        return new MonthRevenueCalculator(LocalDate.now().getMonth().minus(1),LocalDate.now().getMonth().minus(1)).calculateRange(reservations);
    }

    public double specifiedMonthRangeRevenue(Month startMonth, Month endMonth){
        List<Reservation> reservations = reservationRepository.findAll();
        return new MonthRevenueCalculator(startMonth, endMonth).calculateRange(reservations);
    }

    public double thisYearRevenue(){
        List<Reservation> reservations = reservationRepository.findAll();
        return new YearRevenueCalculator(Year.now(), Year.now()).calculateRange(reservations);
    }

    public double lastYearRevenue(){
        List<Reservation> reservations = reservationRepository.findAll();
        return new YearRevenueCalculator(Year.now().minusYears(1), Year.now().minusYears(1)).calculateRange(reservations);
    }

    public double specifiedYearRevenue(Year startYear, Year endYear){
        List<Reservation> reservations = reservationRepository.findAll();
        return new YearRevenueCalculator(startYear, endYear).calculateRange(reservations);
    }



}
