package cgrp.car_reservation.car_reservation.managerial;

import cgrp.car_reservation.car_reservation.reservation.Reservation;
import cgrp.car_reservation.car_reservation.reservation.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.List;


@Service
public class RevenueService {

    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> reservations = reservationRepository.findAll();

    public double thisMonthRevenue(){
        return new MonthRevenueCalculator(LocalDate.now().getMonth(),LocalDate.now().getMonth()).calculateRange(reservations);
    }

    public double lastMonthRevenue(){
        return new MonthRevenueCalculator(LocalDate.now().getMonth().minus(1),LocalDate.now().getMonth().minus(1)).calculateRange(reservations);
    }

    public double specifiedMonthRangeRevenue(Month startMonth, Month endMonth){
        return new MonthRevenueCalculator(startMonth, endMonth).calculateRange(reservations);
    }

    public double thisYearRevenue(){

        return new YearRevenueCalculator(Year.now(), Year.now()).calculateRange(reservations);
    }

    public double lastYearRevenue(){
        return new YearRevenueCalculator(Year.now().minusYears(1), Year.now().minusYears(1)).calculateRange(reservations);
    }

    public double specifiedYearRevenue(Year startYear, Year endYear){
        return new YearRevenueCalculator(startYear, endYear).calculateRange(reservations);
    }



}
