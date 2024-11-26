package cgrp.car_reservation.car_reservation.vehicle;

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
    private final List<Vehicle> vehicles;
    private final String[] makeSearchParams;
    private final String[] typeSearchParams;
    private final String[] keywordSearchParams;

    public FilterByKeyword (List<Vehicle> vehicles, SearchDto searchDto){
        this.vehicles = vehicles;
        this.makeSearchParams = searchDto.getMakes();
        this.typeSearchParams = searchDto.getTypes();
        this.keywordSearchParams = searchDto.getKeywords();

    }

    /**
     * Returns specific user based on query by username.<br>
     * @param vehicleSubset an array of vehicle objects<br>
     * @param searchParams an array of strings that you want the vehicles to include<br>
     * @return an array of vehicles that matched at least one of the search params<br>
     */
    public List<Vehicle> filter(List<Vehicle> vehicleSubset, String[] searchParams){

        if(searchParams.length==0)
            return vehicleSubset;

        List<Vehicle> approvedVehicles = new ArrayList<>();

        for(Vehicle vehicle : vehicleSubset){
            if(Arrays.stream(searchParams).anyMatch(vehicle.getVehicleSearchTerm()::contains)){
                approvedVehicles.add(vehicle);
            }
        }

        return  approvedVehicles;
    }

    /**
     * uses filter to filter vehicles for CGRP<br>
     * @return List of vehicles that matched the searchParams
     * of make, type, and keyword specified in searchDto<br>
     */
    public List<Vehicle> filterResult(){

        return filter(filter(filter(vehicles,makeSearchParams),typeSearchParams),keywordSearchParams);
    }
}
