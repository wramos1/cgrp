package cgrp.car_reservation.car_reservation.vehicle;

import cgrp.car_reservation.car_reservation.feature.Feature;
import cgrp.car_reservation.car_reservation.review.Review;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Name: Vehicle <br>
 * Date of Code: 10/7/2024<br>
 * Programmer's Name: Alberto S and Arthur<br>
 *
 * Description: Represents vehicle in the system which the user can rent.<br>
 *
 * Important Functions:<br>
 *  -addReview: adds review to vehicle, input is a review<br>
 *  -calculateNewVehicleRating: calculates new vehicle rating whenever a new review is added; input and output are void<br>
 *
 *  Data Structures: ArrayList of Reviews, ArrayList of Features<br>
 *
 *
 */
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

    @DocumentReference(lazy = true) // in the db, the id of each feature will be stored in the Vehcile document so it will be a refrence
    private List<Feature> vehicleFeatures = new ArrayList<Feature>();

    @DocumentReference(lazy = true) // allows for the document to be refrenced using its unique MongoID; lazy = false will allow for the refrenced data to be loaded in
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


    // will add a review to the list of reviews left on the vehicle

    /**
     * Adds review left on vehicle to vehicle
     * @param newReview review that is left on vehicle
     */
    public void addReview(Review newReview)
    {
        reviewsOfVehicle.add(newReview); // inserts the new review to the reviews that are left on that vehicle
    }

    // will calculate the average rating of the vehicle based on the cumulative ratings left from the review

    /**
     * Updates the average vehicle rating whenever a new review is added to a vehicle
     */
    public void calculateNewVehicleRating()
    {
        double newRating = 0.0; // start the accumulator at zero

        for(Review rev : reviewsOfVehicle)
        {
            newRating += rev.getReviewRating(); // keeps on adding and accumulating each rating or review that is left on the vehicle
        }

        vehicleRating = (newRating/reviewsOfVehicle.size()); // will divide the accumulator of all of the ratings by the number of ratings, to get the avergae rating

    }

    // will compare the unique vehicle ids to make sure that they are the same vehicle
    public boolean equals(Vehicle vehicle)
    {
        if(this.getCustomVehicleID().equals(vehicle.customVehicleID))
            return true;
        else
            return false;
    }

}
