package cgrp.car_reservation.car_reservation.business_metrics;

import cgrp.car_reservation.car_reservation.review.Review;
import cgrp.car_reservation.car_reservation.vehicle.Vehicle;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "businessMetrics")
public class BusinessMetrics {
    @Id
    private ObjectId businessMetricID;

    private int numVehiclesCurrentlyRented; // this attribute could possibly be deleted with the inclusion of the array list object for the vehicles that are currently renetd out

    private double totalRentalRevenue;

    private int lifetimeNumRentals; // numbers of rentals in the entire lifetime of the rental company

    @DocumentReference
    private List<Vehicle> currentlyRentedVehicles = new ArrayList<>(); // will refrence/store the vehicles which are currently rented out

    @DocumentReference
    private List<Review> lowRatedReviewsToAddress = new ArrayList<>(); // reviews with low ratings that need to be addressed and looked over by managment

    public BusinessMetrics()
    {

    }

    public BusinessMetrics(int numVehiclesCurrentlyRented, double totalRentalRevenue, int lifetimeNumRentals, List<Vehicle> currentlyRentedVehicles, List<Review> lowRatedReviewsToAddress) {
        this.numVehiclesCurrentlyRented = numVehiclesCurrentlyRented;
        this.totalRentalRevenue = totalRentalRevenue;
        this.lifetimeNumRentals = lifetimeNumRentals;
        this.currentlyRentedVehicles = new ArrayList<Vehicle>();
        this.lowRatedReviewsToAddress = new ArrayList<Review>();
    }

    public BusinessMetrics(int numVehiclesCurrentlyRented, double totalRentalRevenue, int lifetimeNumRentals) {
        this.numVehiclesCurrentlyRented = numVehiclesCurrentlyRented;
        this.totalRentalRevenue = totalRentalRevenue;
        this.lifetimeNumRentals = lifetimeNumRentals;
    }

    public int getNumVehiclesCurrentlyRented() {
        return numVehiclesCurrentlyRented;
    }

    public void setNumVehiclesCurrentlyRented(int numVehiclesCurrentlyRented) {
        this.numVehiclesCurrentlyRented = numVehiclesCurrentlyRented;
    }

    public ObjectId getBusinessMetricID() {
        return businessMetricID;
    }

    public void setBusinessMetricID(ObjectId businessMetricID) {
        this.businessMetricID = businessMetricID;
    }

    public double getTotalRentalRevenue() {
        return totalRentalRevenue;
    }

    public void setTotalRentalRevenue(double totalRentalRevenue) {
        this.totalRentalRevenue = totalRentalRevenue;
    }

    public List<Vehicle> getCurrentlyRentedVehicles() {
        return currentlyRentedVehicles;
    }

    public void setCurrentlyRentedVehicles(List<Vehicle> currentlyRentedVehicles) {
        this.currentlyRentedVehicles = currentlyRentedVehicles;
    }

    public int getLifetimeNumRentals() {
        return lifetimeNumRentals;
    }

    public void setLifetimeNumRentals(int lifetimeNumRentals) {
        this.lifetimeNumRentals = lifetimeNumRentals;
    }

    public List<Review> getLowRatedReviewsToAddress() {
        return lowRatedReviewsToAddress;
    }

    public void setLowRatedReviewsToAddress(List<Review> lowRatedReviewsToAddress) {
        this.lowRatedReviewsToAddress = lowRatedReviewsToAddress;
    }

    public void addLowRatedReview(Review review) {
        this.getLowRatedReviewsToAddress().add(0, review); // Adds the review at the beginning of the list
    }

}
