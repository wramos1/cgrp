package cgrp.car_reservation.car_reservation.reservation;

import cgrp.car_reservation.car_reservation.payment_card.paymentCard;
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
    private String customVehicleId;
    private  LocalDate startDate;
    private  LocalDate endDate;
    private paymentCard userCard; // card used to make the payment for the reservation of the user


    public ReservationDto(ObjectId userId, String customVehicleId, LocalDate rentDate, LocalDate returnDate){
        this.userId = userId;
        this.customVehicleId = customVehicleId;
        this.startDate = rentDate;
        this.endDate = returnDate;
    }

    public ReservationDto(String customVehicleId, LocalDate startDate, LocalDate endDate, paymentCard userCard) {
        this.customVehicleId = customVehicleId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.userCard = userCard;
    }

    public ReservationDto()
    {

    }
}