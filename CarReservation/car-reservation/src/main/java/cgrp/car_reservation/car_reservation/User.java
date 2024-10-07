package cgrp.car_reservation.car_reservation;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Getter @Setter @Document(collection = "users")
public class User {
    @Id
    private ObjectId userId;
    private String username;
    private @Setter String password;
    private String email;
    private PaymentCard card;
    private List<Reservation> reservations = new ArrayList<Reservation>();
    private List<Review> reviews = new ArrayList<Review>();

    public User(){}
}