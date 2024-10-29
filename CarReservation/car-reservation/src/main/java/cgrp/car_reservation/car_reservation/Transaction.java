package cgrp.car_reservation.car_reservation;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDateTime;

// this transaction entity class will be used as a history marker for when a "transaction"/renting a car/reservation occured
@Document(collection = "transactions")
public class Transaction {

    @Id
    private ObjectId transactionID;

    private LocalDateTime transactionDateTime; // the date and exact time of the transaction being processed

    @DocumentReference
    private Reservation reservationPartOfTransaction; // this is the reservation which is a part of the transaction; through this will get the vehicle that is a part of the transaction

    @DocumentReference
    private paymentCard transactionCard; // card the transaction is processed with

    private User transactionUser; // the user the transaction is associated with


}
