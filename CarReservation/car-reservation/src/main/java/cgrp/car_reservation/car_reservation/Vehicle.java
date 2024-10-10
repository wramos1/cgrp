package cgrp.car_reservation.car_reservation;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Setter
@Getter
@Document(collection = "vehicles")
public class Vehicle {
    @Id
    private ObjectId vehicleID;

    private String make;
    private String model;
    private int year;
    private String type;
    private String color;
    private int dailyRentRate;
    private double vehicleRating = 0.0; // the average rating of the vehicle ; start it at 0.0 b/c it currently has no ratings
    private boolean currentlyRented; // boolean value if it is currently rented or not
    private String description; // quick descriptioon of the vehicle

    @DocumentReference // in the db, the id of each feature will be stored in the Vehcile document so it will be a refrence
    private List<Feature> vehicleFeatures;

    @DocumentReference
    private List<Review> reviewsOfVehicle;

    public Vehicle(){}

   /* private double calculateAverageRating() {
        if (reviewsOfVehicle == null || reviewsOfVehicle.length == 0) {
            return 0.0; // Return 0 or some sensible default
        }
        int sum = 0;
        for (Review review : reviewsOfVehicle) {
            sum += review.getRating();
        }
        return (double) sum / reviewsOfVehicle.length; // Cast to double for a more accurate average
    }*/
}
