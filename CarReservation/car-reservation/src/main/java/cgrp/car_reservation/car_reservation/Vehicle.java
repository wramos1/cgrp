package cgrp.car_reservation.car_reservation;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter @Document(collection = "vehicles")
public class Vehicle {
    @Id
    private ObjectId vehicleID;
    private String make;
    private String model;
    private int year;
    private String type;
    private String color;
    @DBRef
    private Review[] reviews;
    private double rating = calculateAverageRating();
    private int chargePerDay;
    private @Setter boolean status;
    private String description;
    private String[] features;
    public Vehicle(){}

    private double calculateAverageRating() {
        if (reviews == null || reviews.length == 0) {
            return 0.0; // Return 0 or some sensible default
        }

        int sum = 0;
        for (Review review : reviews) {
            sum += review.getRating();
        }

        return (double) sum / reviews.length; // Cast to double for a more accurate average
    }


}
