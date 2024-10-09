package cgrp.car_reservation.car_reservation;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.time.*;


@Document(collection = "reservations")
@Getter @Setter
public class Reservation {
    @Id
    private ObjectId reservationID;
    private User user;
    private Vehicle vehicle;
    private Date returnDate;
    private Date rentDate;
    private int diffInDays;
    private int chargeAmount;


    public long calculateChargeAmount(){
        return (long)1.0;
        //return ChronoUnit.DAYS.between(rentDate,returnDate)* vehicle.getChargePerDay();
    }
}
