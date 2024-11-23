package cgrp.car_reservation.car_reservation.vehicle.filters;

import cgrp.car_reservation.car_reservation.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;
/*
 * Takes a list of vehicles and a keyword(String)
 * and finds all cars that contain the keyword
 * in their search term
 */

/**
 * Class Name: FilterByKeyword
 * Date of Code: November 18, 2024
 * Programmer's Name: Arthur & Alberto S
 *
 * Description: Provides filter method implementation for filtering vehicles based on a search keyword
 *
 * Important Functions:
 * -filter: inputted search keyword, returns a list of vehicles who match that keyword
 *
 * Data Structures: List of all vehicles in the system, ArrayList of vehicles who match that keyword filtering
 *
 * Algorithms: Linearly go through each of the vehicles in the system; for each vehicle check to see if the each keyword matches that vehicle, if so then do nothing, if not then break out loop and do not add that vehicle to subset of filtered vehicles
 *
 */
public class FilterByKeyword implements VehicleFilter {
    private List<Vehicle> vehicles;
    public String keyword;
    public String[] tokenizedKeyword; // this is the keyword searches tokenized

    public FilterByKeyword (List<Vehicle> vehicles, String keyword){
        this.vehicles = vehicles;
        this.keyword = keyword;
        tokenizedKeyword = keyword.split(" "); // will split the keyword about each space
    }

    // come up with algorithm that adds all of the keywords, and singles it down
    // make something with true
    @Override
    public List<Vehicle> filter() {
        List<Vehicle> approvedVehicles = new ArrayList<>();
        boolean currentValidSearchedVehicle = true;

        for(Vehicle vehicle : vehicles){

            currentValidSearchedVehicle = true;

            for(String keywordToken : tokenizedKeyword)
            {
                if(vehicle.getVehicleSearchTerm().contains(keywordToken))
                {
                    // does nothing
                }
                else
                {
                    currentValidSearchedVehicle = false; // this will set the boolean value to false, so it will make it know that this car
                    break;
                }
            }

            // if it is true, this means that it has all of the keywords, not just one of them
            if(currentValidSearchedVehicle == true)
            {
                approvedVehicles.add(vehicle);
            }

        }
        return approvedVehicles;
    }
}
