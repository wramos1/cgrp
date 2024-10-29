package cgrp.car_reservation.car_reservation;

import lombok.Getter;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.Date;


public class ReservationDto {
    //private final ObjectId userId; // omit the user for now since we don't have the user part working

    private String vehicleID; // this is the vehicle that the resevation is for/rented

    private LocalDate startDate; // date vehicle is renetd

    private LocalDate endDate; // date vehicle needs to be returned so therefore the end of the retrun date

    public ReservationDto(LocalDate startDate, LocalDate endDate, String vehicleID) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.vehicleID = vehicleID;
    }

    public String getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
