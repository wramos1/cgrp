package cgrp.car_reservation.car_reservation.vehicle;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Class Name: VehicleRepository
 * Date of Code:
 * Programmer's Name: Alberto S
 *
 * Description: Provides an interface to access MongoDB for vehicle objects
 *
 * Data Structures: N/A
 *
 * Algorithms N/A
 *
 */
public interface VehicleRepository extends MongoRepository<Vehicle, ObjectId> {

    public List<Vehicle> findByType(String type); // will query the db for all vehicles of that type

    public List<Vehicle> findByMake(String make); // will query the db for all vehicles of that make

    public List<Vehicle> findByYear(int year); // wll query the db for all vehicles of that year

    public Vehicle findByCustomVehicleID(String customVehicleID); // will query the db for all vehicles of that custom string ID
}
