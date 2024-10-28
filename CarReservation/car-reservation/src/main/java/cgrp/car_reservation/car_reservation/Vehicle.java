package cgrp.car_reservation.car_reservation;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Document(collection = "vehicles")
public class Vehicle {
    @Id
    private ObjectId vehicleID; // this is the ID for the database that will use in itself

    private String customVehicleID; // this is the ID we will use to get back from the front end; will be assigned with UUID java library
    private String make;
    private String model;
    private int year;
    private String type; // coupe, suv, etc
    private String color;
    private double dailyRentRate;
    private double vehicleRating = 0.0; // the average rating of the vehicle ; start it at 0.0 b/c it currently has no ratings
    private boolean currentlyRented; // boolean value if it is currently rented or not
    private String description; // quick descriptioon of the vehicle
    private String vehicleSearchTerm; // this will be a search term for the vehicle that will be used in a way that is like fuzzy search
    private String vehicleImageHostingURL; // this is a URL to cloudinary for this specific vehicle which is where the vehicle images are being hosted

    @DocumentReference(lazy = false) // in the db, the id of each feature will be stored in the Vehcile document so it will be a refrence
    private List<Feature> vehicleFeatures = new ArrayList<Feature>();

    @DBRef(lazy = false) // allows for the document to be refrenced using its unique MongoID; lazy = false will allow for the refrenced data to be loaded in
    private List<Review> reviewsOfVehicle = new ArrayList<Review>();

    public Vehicle(){}

    public Vehicle(String customVehicleID, String make, String model, int year, String type, String color, double dailyRentRate, double vehicleRating, boolean currentlyRented, String description, List<Feature> vehicleFeatures, List<Review> reviewsOfVehicle) {
        this.customVehicleID = customVehicleID;
        this.make = make;
        this.model = model;
        this.year = year;
        this.type = type;
        this.color = color;
        this.dailyRentRate = dailyRentRate;
        this.vehicleRating = vehicleRating;
        this.currentlyRented = currentlyRented;
        this.description = description;
        this.vehicleFeatures = vehicleFeatures;
        this.reviewsOfVehicle = reviewsOfVehicle;
    }

    public Vehicle( String customVehicleID, String make, String model, int year, String type, String color, double dailyRentRate, double vehicleRating, boolean currentlyRented, String description, String vehicleSearchTerm, String vehicleImageHostingURL, List<Feature> vehicleFeatures, List<Review> reviewsOfVehicle) {
        this.customVehicleID = customVehicleID;
        this.make = make;
        this.model = model;
        this.year = year;
        this.type = type;
        this.color = color;
        this.dailyRentRate = dailyRentRate;
        this.vehicleRating = vehicleRating;
        this.currentlyRented = currentlyRented;
        this.description = description;
        this.vehicleSearchTerm = vehicleSearchTerm;
        this.vehicleImageHostingURL = vehicleImageHostingURL;
        this.vehicleFeatures = vehicleFeatures;
        this.reviewsOfVehicle = reviewsOfVehicle;
    }

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

    public void leaveReviewOnVehicle(Review review)
    {

    }

}
