package cgrp.car_reservation.car_reservation.transaction;

import cgrp.car_reservation.car_reservation.reservation.Reservation;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Class Name: Transaction <br>
 * Date of Code: <br>
 * Programmer's Name: <br>
 *
 * Description: Represents a transaction in the system whenever the user initiates a vehicle reservation. <br>
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

    /**
     * Constructs the Transaction object with the date and time of the transaction occurring.
     *
     * @param transactionNote The type of transaction it is (New, Modify, Cancel).
     * @param transactionDateTime Time and date stamp of when transaction happened.
     * @param reservationInvolvedInTransaction Reservation which transaction is based upon.
     */
    public Transaction(String transactionNote, LocalDateTime transactionDateTime, Reservation reservationInvolvedInTransaction) {
        this.transactionNote = transactionNote;
        this.transactionDateTime = transactionDateTime;
        this.reservationInvolvedInTransaction = reservationInvolvedInTransaction;
    }


    /**
     * Constructs the Transaction object without the date and time of the transaction occurring.
     *
     * @param transactionNote  The type of transaction it is (New, Modify, Cancel).
     * @param reservationInvolvedInTransaction Reservation which transaction is based upon.
     */
    public Transaction(String transactionNote, Reservation reservationInvolvedInTransaction) {
        this.transactionNote = transactionNote;
        this.reservationInvolvedInTransaction = reservationInvolvedInTransaction;
    }

    /**
     * Returns the type of transaction.
     *
     * @return The type of transaction it is (New, Modify, Cancel).
     */
    public String getTransactionNote() {
        return transactionNote;
    }

    /**
     * Sets the type of transaction.
     *
     * @param transactionNote The type of transaction it is (New, Modify, Cancel).
     */
    public void setTransactionNote(String transactionNote) {
        this.transactionNote = transactionNote;
    }

    /**
     * Returns the reservation of the transaction.
     *
     * @return reservation of the transaction
     */
    public Reservation getReservationInvolvedInTransaction() {
        return reservationInvolvedInTransaction;
    }

    /**
     * Sets the reservation involved in the transaction.
     *
     * @param reservationInvolvedInTransaction reservation of the transaction
     */
    public void setReservationInvolvedInTransaction(Reservation reservationInvolvedInTransaction) {
        this.reservationInvolvedInTransaction = reservationInvolvedInTransaction;
    }

    /**
     * Returns the Date and Time of the transaction
     *
     * @return the date and time of transaction
     */
    public LocalDateTime getTransactionDateTime() {
        return transactionDateTime;
    }

    /**
     *  Sets the Date and Time of the transaction.
     *
     * @param transactionDateTime the date and time of transaction
     */
    public void setTransactionDateTime(LocalDateTime transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }
}
