package cgrp.car_reservation.car_reservation.vehicle.filters;

import cgrp.car_reservation.car_reservation.vehicle.Vehicle;

import java.util.List;

/*
* a filter interface to reduce code in vehicle service
* */

public interface VehicleFilter {

    //the only method required to be implemented
    public List<Vehicle> filter();

}
