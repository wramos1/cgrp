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

/**
 * Class Name: User <br>
 * Date of Code: October 7, 2024
 * Programmer's Name:
 *
 * Description: Represents a user of the system, either a customer or manager.
 *
 */
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
    private List<Reservation> reservations = new ArrayList<Reservation>();

    //holds references to all reviews the user has left
    @DocumentReference
    private List<Review> reviewsLeft = new ArrayList<Review>();

    /**
     * Default Constructor for a User object
     */
    public User(){}

    // this is a constructor that is just being used right now for testing purposes

    /**
     *
     * Constructs the User object from a username, email, and password.
     *
     * @param username User's username
     * @param password User's password
     * @param email User's Email
     */
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    // changed return value to boolean to see if it is adding in the reservation

    /**
     * Adds a reservation to a user object, using a one-to-many relationship.
     * @param reservation Reservation that the user is making
     * @return true if reservation was successfully added
     */
    public boolean addReservation(Reservation reservation){

        return this.reservations.add(reservation);
    }

    /**
     *  Removes a reservation from a user object.
     * @param reservation Reservation that the user is removing
     */
    public void removeReservation(Reservation reservation){
        this.reservations.remove(reservation);
    }

    /**
     * Checks if user has specific reservation
     *
     * @param reservation Reservation being checked for in User
     * @return true if User has that reservation
     */
    public boolean hasReservation(Reservation reservation)
    {
        return this.reservations.contains(reservation);
    }

    /**
     * Adds a new review to user in which the user left, in a one-to-many relationship
     * @param newReview Review left by User that is being added to User object
     */
    public void addReview(Review newReview)
    {
        reviewsLeft.add(newReview);
    }


}