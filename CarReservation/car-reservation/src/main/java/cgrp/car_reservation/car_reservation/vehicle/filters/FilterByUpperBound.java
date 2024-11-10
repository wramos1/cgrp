package cgrp.car_reservation.car_reservation.vehicle.filters;

import cgrp.car_reservation.car_reservation.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;

/*
* Takes a list of vehicles, an upper price bound
* and finds all cars that are cheaper than
* the upper bound
* */

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