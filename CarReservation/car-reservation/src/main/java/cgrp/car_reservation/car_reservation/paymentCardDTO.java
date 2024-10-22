package cgrp.car_reservation.car_reservation;

import java.time.LocalDate;

// this will get the passsed in neccesary info from the user from front end, the data transfer object
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
