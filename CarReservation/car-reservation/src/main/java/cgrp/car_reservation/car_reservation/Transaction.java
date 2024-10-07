package cgrp.car_reservation.car_reservation;

public class Transaction {
    private final int chargeAmount;
    private final PaymentCard card;
    public Transaction(int chargeAmount, PaymentCard card){
        this.chargeAmount = chargeAmount;
        this.card = card;
    }
    //returns false if charge is unsuccessful
    public boolean charge(){
        if(chargeAmount >= 0){
            return verifyPayment();
        } else{
            return false;
        }
    }
    private boolean verifyPayment(){
        int nDigits = card.cardNumber.length();
        int nSum = 0;
        boolean isSecond = false;
        for (int i = nDigits - 1; i >= 0; i--)
        {

            int d = card.cardNumber.charAt(i) - '0';

            if (isSecond)
                d = d * 2;

            // We add two digits to handle
            // cases that make two digits
            // after doubling
            nSum += d / 10;
            nSum += d % 10;

            isSecond = !isSecond;
        }
        return (nSum % 10 == 0);
    }
}
