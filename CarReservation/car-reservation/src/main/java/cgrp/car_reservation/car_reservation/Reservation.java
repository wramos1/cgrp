package cgrp.car_reservation.car_reservation;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Document(collection = "reservations")
@Getter @Setter
public class Reservation {
    @Id
    private ObjectId reservationID;
    @DBRef
    private User user;
    @DBRef
    private Vehicle vehicle;
    private LocalDate returnDate;
    private LocalDate rentDate;
    private long chargeAmount;

    public long calculateChargeAmount(){
        return ChronoUnit.DAYS.between(rentDate,returnDate)* vehicle.getChargePerDay();
    }
}
