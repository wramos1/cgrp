package cgrp.car_reservation.car_reservation;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
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

    //holds references to all users reservations
    @DocumentReference
    private List<Reservation> reservations;

    //holds references to all reviews the user has left
    @DocumentReference
    private List<Review> reviewsLeft;

    // this is a constructor that is just being used right now for testing purposes
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.reservations = new ArrayList<Reservation>();
        this.reviewsLeft = new ArrayList<Review>();
    }

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