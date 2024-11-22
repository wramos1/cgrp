package cgrp.car_reservation.car_reservation.transaction;

import cgrp.car_reservation.car_reservation.payment_card.paymentCard;
import cgrp.car_reservation.car_reservation.reservation.Reservation;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDateTime;

/**
 * Class Name: NewReservationTransaction <br>
 * Date of Code: <br>
 * Programmer's Name: <br>
 *
 * Description: A child class of the Transaction class, in which this class is used for new transactions that involve a new payment card. <br>
 *
 * Important Functions: <br>
 *
 */
@Document(collection = "transactons")
public class NewReservationTransaction extends Transaction {


    @DocumentReference
    private paymentCard cardOnTransaction;

    // calls the constructor of the base class before intializing what is in this current class

    /**
     * Constructs a NewReservationTransaction object without the time and date stamp of the current transaction.
     *
     * @param transactionNote The type of transaction it is (New, Modify, Cancel).
     * @param reservationInvolvedInTransaction Reservation which transaction is based upon.
     * @param cardOnTransaction PaymentCard used for the reservation transaction.
     */
    public NewReservationTransaction(String transactionNote, Reservation reservationInvolvedInTransaction, paymentCard cardOnTransaction) {
        super(transactionNote, reservationInvolvedInTransaction);
        this.cardOnTransaction = cardOnTransaction;
    }

    /**
     *
     * Constructs a NewReservationTransaction object with the time and date stamp of the current transaction.
     *
     * @param transactionNote The type of transaction it is (New, Modify, Cancel).
     * @param transactionDateTime Time and date stamp of when transaction happened.
     * @param reservationInvolvedInTransaction  Reservation which transaction is based upon.
     * @param cardOnTransaction PaymentCard used for the reservation transaction.
     */
    public NewReservationTransaction(String transactionNote, LocalDateTime transactionDateTime, Reservation reservationInvolvedInTransaction, paymentCard cardOnTransaction) {
        super(transactionNote, transactionDateTime, reservationInvolvedInTransaction);
        this.cardOnTransaction = cardOnTransaction;
    }
}
