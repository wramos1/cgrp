package cgrp.car_reservation.car_reservation;

import org.bson.types.ObjectId;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

public class paymentCard {

    @Id
    private ObjectId cardID;

    private int cardNumber;

    private int cvv;

    private LocalDate expirationDate;

    private String nameOnCard;

    public paymentCard(String nameOnCard, int cvv, LocalDate expirationDate, int cardNumber, ObjectId cardID) {
        this.nameOnCard = nameOnCard;
        this.cvv = cvv;
        this.expirationDate = expirationDate;
        this.cardNumber = cardNumber;
        this.cardID = cardID;
    }

    public ObjectId getCardID() {
        return cardID;
    }

    public void setCardID(ObjectId cardID) {
        this.cardID = cardID;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }
}
