package cgrp.car_reservation.car_reservation;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Controller;

import java.util.Date;

@Document(collection = "reservations")
@Getter @Setter
public class Reservation {
    @Id
    private ObjectId reservationID;
    @DBRef
    private User user;
    @DBRef
    private Vehicle vehicle;
    private Date returnDate;
    private Date rentDate;
    private int diffInDays;
    private int chargeAmount;


}
