package cgrp.car_reservation.car_reservation.transaction;

import cgrp.car_reservation.car_reservation.reservation.Reservation;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 
 */

@Document(collection = "transactions")
public class Transaction {

    @Id
    private ObjectId transactionID;

    private String transactionNote; // this is a short description of the type of transaction it was (rent car, cancel rent, etc)

    private LocalDateTime transactionDateTime; // the date and time that this "transaction" occurred

    @DocumentReference
    private Reservation reservationInvolvedInTransaction; // this is the reservation that is involved with the transaction

    public Transaction(String transactionNote, LocalDateTime transactionDateTime, Reservation reservationInvolvedInTransaction) {
        this.transactionNote = transactionNote;
        this.transactionDateTime = transactionDateTime;
        this.reservationInvolvedInTransaction = reservationInvolvedInTransaction;
    }

    public Transaction(String transactionNote, Reservation reservationInvolvedInTransaction) {
        this.transactionNote = transactionNote;
        this.reservationInvolvedInTransaction = reservationInvolvedInTransaction;
    }

    public String getTransactionNote() {
        return transactionNote;
    }

    public void setTransactionNote(String transactionNote) {
        this.transactionNote = transactionNote;
    }

    public Reservation getReservationInvolvedInTransaction() {
        return reservationInvolvedInTransaction;
    }

    public void setReservationInvolvedInTransaction(Reservation reservationInvolvedInTransaction) {
        this.reservationInvolvedInTransaction = reservationInvolvedInTransaction;
    }

    public LocalDateTime getTransactionDateTime() {
        return transactionDateTime;
    }

    public void setTransactionDateTime(LocalDateTime transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }
}
