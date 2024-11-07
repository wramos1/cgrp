package cgrp.car_reservation.car_reservation.vehicle.filters;

import cgrp.car_reservation.car_reservation.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;
/*
 * Takes a list of vehicles and a keyword(String)
 * and finds all cars that contain the keyword
 * in their search term
 */

public class FilterByKeyword implements VehicleFilter {
    private List<Vehicle> vehicles;
    public String keyword;

    public FilterByKeyword (List<Vehicle> vehicles, String keyword){
        this.vehicles = vehicles;
        this.keyword = keyword;
    }

    @Override
    public List<Vehicle> filter() {
        List<Vehicle> approvedVehicles = new ArrayList<>();

        for(Vehicle vehicle : vehicles){
            if(vehicle.getVehicleSearchTerm().contains(keyword)){
                approvedVehicles.add(vehicle);
            }
        }
        return approvedVehicles;
    }
}
