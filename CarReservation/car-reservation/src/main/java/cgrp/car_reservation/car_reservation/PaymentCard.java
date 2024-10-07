package cgrp.car_reservation.car_reservation;

import java.util.Date;
import java.time.LocalDate;

public class PaymentCard {
    protected String cardNumber;
    private int cvv;
    private Date expiryDate;
    private String nameOnCard;
    public boolean isExpired(){
        return expiryDate.before(new Date());
    }
}
