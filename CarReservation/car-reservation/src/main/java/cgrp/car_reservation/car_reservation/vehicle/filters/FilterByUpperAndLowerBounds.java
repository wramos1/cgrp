package cgrp.car_reservation.car_reservation.vehicle.filters;

import cgrp.car_reservation.car_reservation.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;
/*
 * Takes a list of vehicles, an upper price bound,
 * a lower price bound, and finds all cars that
 * are cheaper than the upper bound and more expensive
 * than the lower bound
 * */
public class FilterByUpperAndLowerBounds implements VehicleFilter {
    private List<Vehicle> vehicles;
    private double upperBound;
    private double lowerBound;
    public FilterByUpperAndLowerBounds(List<Vehicle> vehicles, double upperBound, double lowerBound){
        this.vehicles = vehicles;
        this.upperBound = upperBound;
        this.lowerBound = lowerBound;
    }

    @Override
    public List<Vehicle> filter() {
        List<Vehicle> vehiclesInPriceRange =  new ArrayList<>(); // create a new ArrayList (like vector in C++) to store the vehicles that are in the price range

        for(Vehicle vehicle : vehicles) // traverse through all vehicles in the db
        {
            if(vehicle.getDailyRentRate() <= upperBound && vehicle.getDailyRentRate() >= lowerBound) // if  the price is within the range, then add it to the list of vehicles which are in that range
                vehiclesInPriceRange.add(vehicle);
        }

        return vehiclesInPriceRange; // will return the vehicles in the price range
    }
}
