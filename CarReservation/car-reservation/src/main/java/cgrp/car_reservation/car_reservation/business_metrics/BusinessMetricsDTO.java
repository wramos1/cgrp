package cgrp.car_reservation.car_reservation.business_metrics;

import cgrp.car_reservation.car_reservation.review.Review;
import cgrp.car_reservation.car_reservation.vehicle.Vehicle;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.ArrayList;
import java.util.List;

/*
 * Module Name: BusinessMetricsDTO.java
 *
 * Date of code: 11/21/2024
 *
 * Programmers Name: Arthur
 *
 * Description: Data transfer object class with
 * appropriate fields needed for creating a
 * businessmetric entity
 *
 * Functions: Constructors for other services classes ease
 * of use
 *
 * Datastructures: Array lists to store all vehicles
 * currently rented and all low reviews on vehicles
 *
 *  */

public class BusinessMetricsDTO {

    private int numVehiclesCurrentlyRented; // this attribute could possibly be deleted with the inclusion of the array list object for the vehicles that are currently renetd out

    private double totalRentalRevenue;

    private int lifetimeNumRentals; // numbers of rentals in the entire lifetime of the rental company

    @DocumentReference
    private List<Vehicle> currentlyRentedVehicles; // will refrence/store the vehicles which are currently rented out

    @DocumentReference
    private List<Review> lowRatedReviewsToAddress; // reviews with low ratings that need to be addressed and looked over by managment

    public BusinessMetricsDTO(double totalRentalRevenue, int lifetimeNumRentals, int numVehiclesCurrentlyRented, List<Vehicle> currentlyRentedVehicles, List<Review> lowRatedReviewsToAddress) {
        this.totalRentalRevenue = totalRentalRevenue;
        this.lifetimeNumRentals = lifetimeNumRentals;
        this.numVehiclesCurrentlyRented = numVehiclesCurrentlyRented;
        this.currentlyRentedVehicles = new ArrayList<Vehicle>();
        this.lowRatedReviewsToAddress = new ArrayList<Review>();
    }

    public int getNumVehiclesCurrentlyRented() {
        return numVehiclesCurrentlyRented;
    }

    public void setNumVehiclesCurrentlyRented(int numVehiclesCurrentlyRented) {
        this.numVehiclesCurrentlyRented = numVehiclesCurrentlyRented;
    }

    public double getTotalRentalRevenue() {
        return totalRentalRevenue;
    }

    public void setTotalRentalRevenue(double totalRentalRevenue) {
        this.totalRentalRevenue = totalRentalRevenue;
    }

    public int getLifetimeNumRentals() {
        return lifetimeNumRentals;
    }

    public void setLifetimeNumRentals(int lifetimeNumRentals) {
        this.lifetimeNumRentals = lifetimeNumRentals;
    }

    public List<Vehicle> getCurrentlyRentedVehicles() {
        return currentlyRentedVehicles;
    }

    public void setCurrentlyRentedVehicles(List<Vehicle> currentlyRentedVehicles) {
        this.currentlyRentedVehicles = currentlyRentedVehicles;
    }

    public List<Review> getLowRatedReviewsToAddress() {
        return lowRatedReviewsToAddress;
    }

    public void setLowRatedReviewsToAddress(List<Review> lowRatedReviewsToAddress) {
        this.lowRatedReviewsToAddress = lowRatedReviewsToAddress;
    }
}
