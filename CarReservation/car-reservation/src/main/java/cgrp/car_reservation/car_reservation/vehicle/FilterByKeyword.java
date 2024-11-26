package cgrp.car_reservation.car_reservation.vehicle;

import cgrp.car_reservation.car_reservation.vehicle.Vehicle;
import cgrp.car_reservation.car_reservation.vehicle.searchDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
 * Takes a list of vehicles and a keyword(String)
 * and finds all cars that contain the keyword
 * in their search term
 */

/**
 * Class Name: FilterByKeyword<br>
 * Date of Code: November 18, 2024<br>
 * Programmer's Name: Arthur and Alberto S<br>
 *
 * Description: Provides filter method implementation for filtering vehicles based on a search keyword<br>
 *
 * Important Functions:<br>
 * -filter: inputted search keyword, returns a list of vehicles who match that keyword<br>
 *
 * Data Structures: List of all vehicles in the system, ArrayList of vehicles who match that keyword filtering<br>
 *
 * Algorithms: Linearly go through each of the vehicles in the system; for each vehicle check to see if the each keyword matches that vehicle, if so then do nothing, if not then break out loop and do not add that vehicle to subset of filtered vehicles<br>
 *
 */
public class FilterByKeyword {
    private List<Vehicle> vehicles;
    private String[] makeSearchParams;
    private String[] typeSearchParams;
    private String[] keywordSearchParams;

    public FilterByKeyword (List<Vehicle> vehicles, searchDto searchDto){
        this.vehicles = vehicles;
        this.makeSearchParams = searchDto.getMakes();
        this.typeSearchParams = searchDto.getTypes();
        this.keywordSearchParams = searchDto.getKeywords();

    }

    // come up with algorithm that adds all of the keywords, and singles it down
    // make something with true


    public List<Vehicle> filter(List<Vehicle> vehicleSubset, String[] searchParams){
        List<Vehicle> approvedVehicles = new ArrayList<>();

        for(Vehicle vehicle : vehicleSubset){
            if(Arrays.stream(makeSearchParams).anyMatch(vehicle.getVehicleSearchTerm()::contains)){
                approvedVehicles.add(vehicle);
            }
        }
        System.out.println(approvedVehicles);
        return  approvedVehicles;
    }

    public List<Vehicle> filterResult(){

        return filter(filter(filter(vehicles,makeSearchParams),typeSearchParams),keywordSearchParams);
    }



//    @Override
//    public List<Vehicle> filter() {
//        List<Vehicle> approvedVehicles = new ArrayList<>();
//        boolean currentValidSearchedVehicle = true;
//
//        for(Vehicle vehicle : vehicles){
//
//            currentValidSearchedVehicle = true;
//
//            for(String keywordToken : tokenizedKeyword)
//            {
//                if(vehicle.getVehicleSearchTerm().contains(keywordToken))
//                {
//                    // does nothing
//                }
//                else
//                {
//                    currentValidSearchedVehicle = false; // this will set the boolean value to false, so it will make it know that this car
//                    break;
//                }
//            }
//
//            // if it is true, this means that it has all of the keywords, not just one of them
//            if(currentValidSearchedVehicle == true)
//            {
//                approvedVehicles.add(vehicle);
//            }
//
//        }
//        return approvedVehicles;
//    }
}
