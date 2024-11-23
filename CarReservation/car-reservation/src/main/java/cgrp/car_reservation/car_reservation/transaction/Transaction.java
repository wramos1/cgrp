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
 * Date of Code: November 14, 2024<br>
 * Programmer's Name: Arthur <br>
 *
 * Description: Represents a transaction in the system whenever the user initiates a vehicle reservation. <br>
 *
 * Important Functions: Getters and Setters<br>
 *
 * Data Structures: N/A<br>
 *
 * Algorithms: N/A<br>
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
     * Constructs the Transaction object with the date and time of the transaction occurring.<br>
     *
     * @param transactionNote The type of transaction it is (New, Modify, Cancel).<br>
     * @param transactionDateTime Time and date stamp of when transaction happened.<br>
     * @param reservationInvolvedInTransaction Reservation which transaction is based upon.<br>
     */
    public Transaction(String transactionNote, LocalDateTime transactionDateTime, Reservation reservationInvolvedInTransaction) {
        this.transactionNote = transactionNote;
        this.transactionDateTime = transactionDateTime;
        this.reservationInvolvedInTransaction = reservationInvolvedInTransaction;
    }


    /**
     * Constructs the Transaction object without the date and time of the transaction occurring.<br>
     *
     * @param transactionNote  The type of transaction it is (New, Modify, Cancel).<br>
     * @param reservationInvolvedInTransaction Reservation which transaction is based upon.<br>
     */
    public Transaction(String transactionNote, Reservation reservationInvolvedInTransaction) {
        this.transactionNote = transactionNote;
        this.reservationInvolvedInTransaction = reservationInvolvedInTransaction;
    }

    /**
     * Returns the type of transaction.<br>
     *
     * @return The type of transaction it is (New, Modify, Cancel).<br>
     */
    public String getTransactionNote() {
        return transactionNote;
    }

    /**
     * Sets the type of transaction.<br>
     *
     * @param transactionNote The type of transaction it is (New, Modify, Cancel).<br>
     */
    public void setTransactionNote(String transactionNote) {
        this.transactionNote = transactionNote;
    }

    /**
     * Returns the reservation of the transaction.<br>
     *
     * @return reservation of the transaction<br>
     */
    public Reservation getReservationInvolvedInTransaction() {
        return reservationInvolvedInTransaction;
    }

    /**
     * Sets the reservation involved in the transaction.<br>
     *
     * @param reservationInvolvedInTransaction reservation of the transaction<br>
     */
    public void setReservationInvolvedInTransaction(Reservation reservationInvolvedInTransaction) {
        this.reservationInvolvedInTransaction = reservationInvolvedInTransaction;
    }

    /**
     * Returns the Date and Time of the transaction<br>
     *
     * @return the date and time of transaction<br>
     */
    public LocalDateTime getTransactionDateTime() {
        return transactionDateTime;
    }

    /**
     *  Sets the Date and Time of the transaction.<br>
     *
     * @param transactionDateTime the date and time of transaction<br>
     */
    public void setTransactionDateTime(LocalDateTime transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }
}
