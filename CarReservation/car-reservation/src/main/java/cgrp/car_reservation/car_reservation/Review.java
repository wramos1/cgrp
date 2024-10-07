package cgrp.car_reservation.car_reservation;

import lombok.Getter;
import lombok.Setter;

public class Review {
    private @Getter @Setter int rating;
    private @Getter @Setter String body;
    public Review(){}
    public Review(int rating, String body){
        this.rating = rating;
        this.body = body;
    }

}
