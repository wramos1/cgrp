package cgrp.car_reservation.car_reservation;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Getter @Setter @Document(collection = "users")
public class User {

    @Id
    private ObjectId userId;
    private String username;
    private @Setter String password;
    private String email;

    //holds references to all users reservations
    @DBRef
    private List<Reservation> reservations = new ArrayList<>();

    //holds references to all reviews the user has left
    @DBRef
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

    public User(){}
}