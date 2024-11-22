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

/**
 * Reservation class represents car rental reservation made by the user.
 *
 * @author CodeGerbils
 */
@Document(collection = "reservations")
@Getter @Setter
public class Reservation {
    @Id
    private ObjectId reservationID;

    private String customReservationID; // custom string ID for reservation

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

    public Reservation()
    {

    }


    /**
     *
     * Constructs the Reservation object
     *
     * @param user User making reservation
     * @param vehicle Vehicle reservation is on
     * @param endDate End date of reservation
     * @param startDate Start date of reservation
     * @param reservationDate Timestamp of when the reservation was made
     */
    public Reservation(User user, Vehicle vehicle, LocalDate endDate, LocalDate startDate, LocalDate reservationDate){
        this.user = user;
        this.vehicle = vehicle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reservationDate = reservationDate;
        calculateChargeAmount();
    }


    /**
     *
     * Constructs the Reservation object
     *
     * @param user User making reservation
     * @param vehicle Vehicle reservation is on
     * @param endDate End date of reservation
     * @param startDate Start date of reservation
     * @param reservationDate Timestamp of when the reservation was made
     */
    public Reservation(String customReservationID, User user, Vehicle vehicle, LocalDate endDate, LocalDate startDate, LocalDate reservationDate) {
        this.customReservationID = customReservationID;
        this.user = user;
        this.vehicle = vehicle;
        this.endDate = endDate;
        this.startDate = startDate;
        this.reservationDate = reservationDate;
        calculateChargeAmount();
    }


    /**
     * Calculates the charge amount for the reservation, and updates the reservation object accordingly. 
     */
    public void calculateChargeAmount(){
        Period period = Period.between(startDate, endDate);
        int days = period.getDays()+1;
        chargeAmount = days*vehicle.getDailyRentRate();
    }



}
