package cgrp.car_reservation.car_reservation;

import org.bson.types.ObjectId;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

// this is the collection that will hold the card documents
@Document(collection = "cards")
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

    public paymentCard(String nameOnCard, int cvv, LocalDate expirationDate, int cardNumber) {
        this.nameOnCard = nameOnCard;
        this.cvv = cvv;
        this.expirationDate = expirationDate;
        this.cardNumber = cardNumber;
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

    // will comapre the expiration date of the card with the current date to see if the card is expired
    public Boolean isCardExpired()
    {
        LocalDate currentDate = LocalDate.now(); // creates a local date object with the current date in it

        if(expirationDate.isBefore(currentDate))
            return true;

        return false;
    }
}