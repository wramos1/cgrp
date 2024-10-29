package cgrp.car_reservation.car_reservation;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDate;
import java.time.Period;


@Document(collection = "reservations")
@Getter @Setter
public class Reservation {
    @Id
    private ObjectId reservationID;

    private String customReservationID; // this will be a custom reservation ID that we will be able to access and use in our logic in the program

    //@DocumentReference // comment out for now because we don't have any way getting user's currently for testing
    private User user;
    @DocumentReference
    private Vehicle vehicle;
    @Setter
    private LocalDate returnDate;
    @Setter
    private LocalDate rentDate;
    private double chargeAmount;

    public Reservation()
    {

    }

    // the constructor is missing the user because for testing reasons currently we can't find the user so there is no need to have it in the constructor
    public Reservation(String customReservationID, Vehicle vehicle, LocalDate rentDate, LocalDate returnDate, double chargeAmount) {
        this.user = null;
        this.customReservationID = customReservationID;
        this.vehicle = vehicle;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
        this.chargeAmount = chargeAmount;
    }

    public void calculateChargeAmount() {
        Period period = Period.between(rentDate, returnDate);
        chargeAmount = period.getDays() * vehicle.getDailyRentRate();
    }


}
