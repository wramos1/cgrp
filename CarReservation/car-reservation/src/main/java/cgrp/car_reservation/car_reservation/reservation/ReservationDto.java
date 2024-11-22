package cgrp.car_reservation.car_reservation.reservation;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.Date;

/**
 * Class Name: Reservation Data Transfer Object
 * Date of Code:
 * Programmer's Name:
 *
 * Function: Reservation Data Transfer Object is utilized to transfer reservation data from front end to back end in order to create reservation.
 *
 * Important Data Structures: LocalDate, which stores the date necessary to the reservation
 *
 *
 *
 * @author CodeGerbils
 */

@Getter
public class ReservationDto {
    @Setter
    private ObjectId userId;
    private final String customVehicleId;
    private final LocalDate startDate;
    private final LocalDate endDate;
    public ReservationDto(ObjectId userId, String customVehicleId, LocalDate rentDate, LocalDate returnDate){
        this.userId = userId;
        this.customVehicleId = customVehicleId;
        this.startDate = rentDate;
        this.endDate = returnDate;
    }


}