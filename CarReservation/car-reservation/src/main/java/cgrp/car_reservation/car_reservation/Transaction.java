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

    private double transactionCost; // this is the cost of the transaction

    // Omit these for the time being until we see how we want to go about doing the card process and getting logged in user to work

   /* @DocumentReference
    private paymentCard transactionCard; // card the transaction is processed with*/

    /*private User transactionUser; // the user the transaction is associated with*/

    public Transaction(LocalDateTime transactionDateTime, Reservation reservationPartOfTransaction, double transactionCost) {
        this.transactionDateTime = transactionDateTime;
        this.reservationPartOfTransaction = reservationPartOfTransaction;
        this.transactionCost = transactionCost;
    }

    public Transaction(LocalDateTime transactionDateTime, Reservation reservationPartOfTransaction) {
        this.transactionDateTime = transactionDateTime;
        this.reservationPartOfTransaction = reservationPartOfTransaction;
    }

    public LocalDateTime getTransactionDateTime() {
        return transactionDateTime;
    }

    public void setTransactionDateTime(LocalDateTime transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }

    public Reservation getReservationPartOfTransaction() {
        return reservationPartOfTransaction;
    }

    public void setReservationPartOfTransaction(Reservation reservationPartOfTransaction) {
        this.reservationPartOfTransaction = reservationPartOfTransaction;
    }


}
