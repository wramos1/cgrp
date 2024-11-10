package cgrp.car_reservation.car_reservation.user;

import cgrp.car_reservation.car_reservation.reservation.Reservation;
import cgrp.car_reservation.car_reservation.review.Review;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.*;

@Getter @Setter @Document(collection = "users")
public class User {

    @Id
    private ObjectId userId;
    private String username;
    private @Setter String password;
    private String email;

    private String[] role;

    public User(String username, String password, String email, String[] role){
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    //holds references to all users reservations
    @DocumentReference
    private List<Reservation> reservations = new ArrayList<>();

    //holds references to all reviews the user has left
    @DocumentReference
    private List<Review> reviewsLeft;

    public void addReservation(Reservation reservation){
        this.reservations.add(reservation);
    }

    public void removeReservation(Reservation reservation){
        this.reservations.remove(reservation);
    }

    public boolean hasReservation(Reservation reservation){
        return this.reservations.contains(reservation);
    }


    public void leaveReview()
    {

    }

    public User(){}

}