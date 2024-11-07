package cgrp.car_reservation.car_reservation.reservation;

import cgrp.car_reservation.car_reservation.user.User;
import cgrp.car_reservation.car_reservation.vehicle.Vehicle;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDate;
import java.time.Period;


@Document(collection = "reservations")
@Getter @Setter
public class Reservation {
    @Id
    private ObjectId reservationID;
    @DocumentReference
    private User user;
    @DocumentReference
    private Vehicle vehicle;
    @Setter
    private LocalDate returnDate;
    @Setter
    private LocalDate rentDate;
    private double chargeAmount;


    public void calculateChargeAmount(){
        Period period = Period.between(rentDate,returnDate);
        chargeAmount = period.getDays()*vehicle.getDailyRentRate();
    }
}
