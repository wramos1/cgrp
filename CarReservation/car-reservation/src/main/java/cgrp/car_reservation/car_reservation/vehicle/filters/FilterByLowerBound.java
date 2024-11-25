package cgrp.car_reservation.car_reservation.vehicle.filters;

import cgrp.car_reservation.car_reservation.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;
/*
 * Takes a list of vehicles and a lower price bound
 * and finds all cars that are more expensive
 * than the lower bound
 */

/**
 * Class Name: FilterByLowerBound<br>
 * Date of Code: November 6, 2024<br>
 * Programmer's Name: Alberto S<br>
 *
 * Description: Provides filter implementation for filtering vehicles with respect to lower bound in price.<br>
 *
 * Data Structures: List of all system Vehicles, ArrayList of vehicles who are in the filtering subset<br>
 *
 * Algorithms: Simple Linear Search and Comparison of vehicle prices<br>
 *
 */
public class FilterByLowerBound implements VehicleFilter {
    private List<Vehicle> vehicles;
    private double lowerBound;
    public FilterByLowerBound(List<Vehicle> vehicles, double lowerBound){
        this.vehicles = vehicles;
        this.lowerBound = lowerBound;
    }

    @Override
    public List<Vehicle> filter() {
        List<Vehicle> vehiclesInPriceRange =  new ArrayList<>(); // create a new ArrayList (like vector in C++) to store the vehicles that are in the price range

        for(Vehicle vehicle : vehicles) // traverse through all vehicles in the db
        {
            if(vehicle.getDailyRentRate() >= lowerBound) // if  the price is within the range, then add it to the list of vehicles which are in that range
                vehiclesInPriceRange.add(vehicle);
        }

        return vehiclesInPriceRange; // will return the vehicles in the price range
    }
}
