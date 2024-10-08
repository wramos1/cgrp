package cgrp.car_reservation.car_reservation;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "vehicles")
public class Vehicle {
    @Id
    private ObjectId vehicleID;
    private String make;
    private String model;
    private int year;
    private String type;
    private String color;
    private double averageRating;
    private int dailyRentRate;
    private Boolean currentlyRented;
    private String vehicleDescription;
    private List<Review> reviewsOfVehicle;
    private List<String> vehicleFeatures;

    public Vehicle(List<String> vehicleFeatures, List<Review> reviewsOfVehicle, String vehicleDescription, Boolean currentlyRented, double averageRating, int dailyRentRate, String color, String type, int year, String model, String make, ObjectId vehicleID) {
        this.vehicleFeatures = vehicleFeatures;
        this.reviewsOfVehicle = reviewsOfVehicle;
        this.vehicleDescription = vehicleDescription;
        this.currentlyRented = currentlyRented;
        this.averageRating = averageRating;
        this.dailyRentRate = dailyRentRate;
        this.color = color;
        this.type = type;
        this.year = year;
        this.model = model;
        this.make = make;
        this.vehicleID = vehicleID;
    }

    public ObjectId getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(ObjectId vehicleID) {
        this.vehicleID = vehicleID;
    }

    public List<String> getVehicleFeatures() {
        return vehicleFeatures;
    }

    public void setVehicleFeatures(List<String> vehicleFeatures) {
        this.vehicleFeatures = vehicleFeatures;
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

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public int getDailyRentRate() {
        return dailyRentRate;
    }

    public void setDailyRentRate(int dailyRentRate) {
        this.dailyRentRate = dailyRentRate;
    }

    public Boolean getCurrentlyRented() {
        return currentlyRented;
    }

    public void setCurrentlyRented(Boolean currentlyRented) {
        this.currentlyRented = currentlyRented;
    }

    public String getVehicleDescription() {
        return vehicleDescription;
    }

    public void setVehicleDescription(String vehicleDescription) {
        this.vehicleDescription = vehicleDescription;
    }

    public List<Review> getReviewsOfVehicle() {
        return reviewsOfVehicle;
    }

    public void setReviewsOfVehicle(List<Review> reviewsOfVehicle) {
        this.reviewsOfVehicle = reviewsOfVehicle;
    }
}
