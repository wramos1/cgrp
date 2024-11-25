package cgrp.car_reservation.car_reservation.vehicle;

import cgrp.car_reservation.car_reservation.feature.Feature;
import cgrp.car_reservation.car_reservation.review.Review;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.ArrayList;
import java.util.List;

// this will be used temporarily whenever we are creating the objects in our intial phase

/**
 * ClassName: VehicleDTO<br>
 * Date of Code: 10/23/2024<br>
 * Programmer's Name: Arthur<br>
 *
 * Description: Temporary Data Transfer Object that was used to create the vehicles in our system<br>
 *
 * Important Functions: Getters and Setters<br>
 *
 * Data Structures: ArrayList for vehicle Features, ArrayList for reviews on the vehicle<br>
 *
 * Algorithms: N/A<br>
 *
 */
public class VehicleDTO {
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

    public VehicleDTO()
    {

    }

    public VehicleDTO(String make, String model, int year, String type, String color, double dailyRentRate, double vehicleRating, boolean currentlyRented, String description, List<Feature> vehicleFeatures, List<Review> reviewsOfVehicle) {
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

    public VehicleDTO(String make, String model, int year, String type, String color, double dailyRentRate, double vehicleRating, boolean currentlyRented, String description, String vehicleSearchTerm, String vehicleImageHostingURL, List<Feature> vehicleFeatures, List<Review> reviewsOfVehicle) {
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

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getDailyRentRate() {
        return dailyRentRate;
    }

    public void setDailyRentRate(double dailyRentRate) {
        this.dailyRentRate = dailyRentRate;
    }

    public double getVehicleRating() {
        return vehicleRating;
    }

    public void setVehicleRating(double vehicleRating) {
        this.vehicleRating = vehicleRating;
    }

    public boolean isCurrentlyRented() {
        return currentlyRented;
    }

    public void setCurrentlyRented(boolean currentlyRented) {
        this.currentlyRented = currentlyRented;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Feature> getVehicleFeatures() {
        return vehicleFeatures;
    }

    public void setVehicleFeatures(List<Feature> vehicleFeatures) {
        this.vehicleFeatures = vehicleFeatures;
    }

    public List<Review> getReviewsOfVehicle() {
        return reviewsOfVehicle;
    }

    public void setReviewsOfVehicle(List<Review> reviewsOfVehicle) {
        this.reviewsOfVehicle = reviewsOfVehicle;
    }

    public void setVehicleSearchTerm(String vehicleSearchTerm)
    {
        this.vehicleSearchTerm = vehicleSearchTerm;
    }

    public void setVehicleImageHostingURL(String vehicleImageHostingURL)
    {
        this.vehicleImageHostingURL = vehicleImageHostingURL;
    }

    public String getVehicleSearchTerm()
    {
        return this.vehicleSearchTerm;
    }

    public String getVehicleImageHostingURL()
    {
        return this.vehicleImageHostingURL;
    }

}
