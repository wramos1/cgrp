package cgrp.car_reservation.car_reservation.payment_card;

import java.time.LocalDate;

/**
 *
 * Module Name: paymentCardDTO.java<br>*
 * Date of code: 11/14/2024<br>*
 * Programmers Name: Arthur<br>*
 * Description: This class acts as a Data Transfer Object (DTO) for transferring payment card data from the front end to the backend.<br>*
 * Functions:<br>
 * - getCardNumber(): Returns the card number.<br>*
 * - setCardNumber(int cardNumber): Sets the card number.<br>*
 * - getYear(): Returns the year of the card's expiration date.<br>*
 * - setYear(int year): Sets the year of the card's expiration date.<br>*
 * - getDay(): Returns the day of the card's expiration date.<br>*
 * - setDay(int day): Sets the day of the card's expiration date.<br>*
 * - getMonth(): Returns the month of the card's expiration date.<br>*
 * - setMonth(int month): Sets the month of the card's expiration date.<br>*
 * - getNameOnCard(): Returns the name on the card.<br>*
 * - setNameOnCard(String nameOnCard): Sets the name on the card.<br>*
 * - getCvv(): Returns the CVV of the card.<br>*
 * - setCvv(int cvv): Sets the CVV of the card.<br>*
 * Data structures: N/A<br>*
 */
public class paymentCardDTO {

    private int cardNumber;

    private int cvv;

    private String nameOnCard;

    // this is what we will be used to construct the Local Date object
    private int month;

    private int day;

    private int year;

   // private LocalDate expirationDate;

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
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
