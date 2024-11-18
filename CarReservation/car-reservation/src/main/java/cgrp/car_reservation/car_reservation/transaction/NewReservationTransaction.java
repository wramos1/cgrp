package cgrp.car_reservation.car_reservation.transaction;

import cgrp.car_reservation.car_reservation.payment_card.paymentCard;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;


@Document(collection = "transactons")
public class NewReservationTransaction extends Transaction {

    @DocumentReference
    private paymentCard cardOnTransaction;

}
