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
    private LocalDate endDate;
    @Setter
    private LocalDate startDate;

    private LocalDate reservationDate;
    private double chargeAmount;

    //reservation constructor for reservation service
    public Reservation(User user, Vehicle vehicle, LocalDate returnDate, LocalDate rentDate, LocalDate reservationDate){
        this.user = user;
        this.vehicle = vehicle;
        this.startDate = rentDate;
        this.endDate = returnDate;
        this.reservationDate = reservationDate;
    }


    public void calculateChargeAmount(){
        Period period = Period.between(startDate, endDate);
        chargeAmount = period.getDays()*vehicle.getDailyRentRate();
    }
}
