package cgrp.car_reservation.car_reservation.reservation;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.Date;

/**
 * Class Name: Reservation Data Transfer Object<br>
 * Date of Code: October 7, 2024<br>
 * Programmer's Name: Alberto S<br>
 *
 * Description: Reservation Data Transfer Object is utilized to transfer reservation data from front end to back end in order to create reservation.<br>
 *
 * Important Functions: Getters and Setters<br>
 *
 * Data Structures:<br>
 * -LocalDate, which stores the date necessary to the reservation<br>
 *
 * Algorithms: N/A<br>
 *
 * @author CodeGerbils<br>
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