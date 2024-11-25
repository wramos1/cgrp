package cgrp.car_reservation.car_reservation.payment_card;

import org.bson.types.ObjectId;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

/**
 *
 * Module Name: paymentCard.java<br>*
 * Date of code: 11/14/2024<br>*
 * Programmers Name: Arthur<br>*
 * Description: A class for saving payment information such as card number, CVV, expiration, etc.<br>*
 * Functions:<br>
 * - paymentCard(String nameOnCard, int cvv, LocalDate expirationDate, int cardNumber, ObjectId cardID):
 *   creates a new paymentCard, takes the name on the card, CVV, expiration date, card number, and unique card ID.<br>*
 * - paymentCard(String nameOnCard, int cvv, LocalDate expirationDate, int cardNumber):
 *   creates a new paymentCard without a unique card ID.<br>*
 * - getCardID(): returns the unique ID of the card.<br>*
 * - setCardID(ObjectId cardID): sets the unique ID of the card.<br>*
 * - getCardNumber(): returns the card number.<br>*
 * - setCardNumber(int cardNumber): sets the card number.<br>*
 * - getExpirationDate(): returns the expiration date of the card.<br>*
 * - setExpirationDate(LocalDate expirationDate): sets the expiration date of the card.<br>*
 * - getNameOnCard(): returns the name printed on the card.<br>*
 * - setNameOnCard(String nameOnCard): sets the name printed on the card.<br>*
 * - getCvv(): returns the CVV of the card.<br>*
 * - setCvv(int cvv): sets the CVV of the card.<br>*
 * - isCardExpired(): compares the expiration date of the card with the current date to determine if the card is expired.<br>*
 * Data structures: N/A<br>*
 */
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

    public paymentCard()
    {

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
