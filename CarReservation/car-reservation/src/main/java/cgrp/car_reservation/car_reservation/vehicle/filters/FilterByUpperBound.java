package cgrp.car_reservation.car_reservation.vehicle.filters;

import cgrp.car_reservation.car_reservation.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;

/*
* Takes a list of vehicles, an upper price bound
* and finds all cars that are cheaper than
* the upper bound
* */

/**
 * Class Name: FilterByUpperBound
 * Date of Code: November 6, 2024
 * Programmer's Name: Alberto S
 *
 * Description: Provides filter method implementation for filtering vehicles with respect to an upper bound
 *
 * Important Functions:
 *  -filter: returns list of vehicles who are less than or equal to the upper bound price value
 *
 *  Data Structures: List of all system vehicles, ArrayList of vehicles who are in the filtering from the upper bound
 *
 *  Algorithms: Simple Linear Search and Comparison of vehicle Price
 *
 */
public class FilterByUpperBound implements VehicleFilter {
    private List<Vehicle> vehicles;
    private double upperBound;
    public FilterByUpperBound(List<Vehicle> vehicles, double upperBound){
        this.vehicles = vehicles;
        this.upperBound = upperBound;
    }

    @Override
    public List<Vehicle> filter() {
        List<Vehicle> vehiclesInPriceRange =  new ArrayList<>(); // create a new ArrayList (like vector in C++) to store the vehicles that are in the price range

        for(Vehicle vehicle : vehicles) // traverse through all vehicles in the db
        {
            if(vehicle.getDailyRentRate() <= upperBound) // if  the price is within the range, then add it to the list of vehicles which are in that range
                vehiclesInPriceRange.add(vehicle);
        }

        return vehiclesInPriceRange; // will return the vehicles in the price range
    }
}
