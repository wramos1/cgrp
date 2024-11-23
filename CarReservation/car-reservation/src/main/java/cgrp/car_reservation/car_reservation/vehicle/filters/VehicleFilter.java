package cgrp.car_reservation.car_reservation.vehicle.filters;

import cgrp.car_reservation.car_reservation.vehicle.Vehicle;

import java.util.List;

/*
* a filter interface to reduce code in vehicle service
* */

/**
 * Interface Name: VehicleFilter
 * Date of Code: November 6, 2024
 * Programmer's Name: Alberto S
 *
 * Description: An interface to help create a guideline of what methods filters should implement.
 *
 * Important Functions:
 * -filter: returns a list of vehicles, each implementation of interface will have their own filter method
 *
 */
public interface VehicleFilter {

    //the only method required to be implemented
    public List<Vehicle> filter();

}
