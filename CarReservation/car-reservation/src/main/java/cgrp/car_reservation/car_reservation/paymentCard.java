package cgrp.car_reservation.car_reservation;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "cards")
public class paymentCard {

    @Id
    private ObjectId cardID;

    private int cardNumber;

    private String nameOnCard;

    private Date expirationDate;

}
