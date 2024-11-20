package cgrp.car_reservation.car_reservation.transaction;

import cgrp.car_reservation.car_reservation.payment_card.paymentCard;
import cgrp.car_reservation.car_reservation.reservation.Reservation;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDateTime;


@Document(collection = "transactons")
public class NewReservationTransaction extends Transaction {


    @DocumentReference
    private paymentCard cardOnTransaction;

    // calls the constructor of the base class before intializing what is in this current class
    public NewReservationTransaction(String transactionNote, Reservation reservationInvolvedInTransaction, paymentCard cardOnTransaction) {
        super(transactionNote, reservationInvolvedInTransaction);
        this.cardOnTransaction = cardOnTransaction;
    }

    public NewReservationTransaction(String transactionNote, LocalDateTime transactionDateTime, Reservation reservationInvolvedInTransaction, paymentCard cardOnTransaction) {
        super(transactionNote, transactionDateTime, reservationInvolvedInTransaction);
        this.cardOnTransaction = cardOnTransaction;
    }
}
