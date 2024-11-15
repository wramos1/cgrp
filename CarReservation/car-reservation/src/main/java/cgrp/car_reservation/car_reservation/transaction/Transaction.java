package cgrp.car_reservation.car_reservation.transaction;

import cgrp.car_reservation.car_reservation.reservation.Reservation;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document(collection = "transactions")
public class Transaction {

    @Id
    private ObjectId transactionID;

    private String transactionNote; // this is a short description of the type of transaction it was (rent car, cancel rent, etc)

    @DocumentReference
    private Reservation reservationInvolvedInTransaction; // this is the reservation that is involved with the transaction




}
