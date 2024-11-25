package cgrp.car_reservation.car_reservation.vehicle;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Class Name: VehicleRepository<br>
 * Date of Code:10/7/2024<br>
 * Programmer's Name: Alberto S<br>
 *
 * Description: Provides an interface to access MongoDB for vehicle objects<br>
 *
 * Important Functions:<br>
 *  -findByType: query database by attribute of vehicle type, return list of vehicles with matching type<br>
 *  -findByMake; query database by attribute of vehicle make, return list of vehicles with matching make<br>
 *  -findByYear: query database by attribute of vehicle year, return list of vehicles with matching year<br>
 *  -findByCustomvehicleID: query database by attribute of customVehicleID, return list of vehicles with matching vehicle iD<br>
 *
 * Data Structures: N/A<br>
 *
 * Algorithms N/A<br>
 *
 */
public interface VehicleRepository extends MongoRepository<Vehicle, ObjectId> {

    public List<Vehicle> findByType(String type); // will query the db for all vehicles of that type

    public List<Vehicle> findByMake(String make); // will query the db for all vehicles of that make

    public List<Vehicle> findByYear(int year); // wll query the db for all vehicles of that year

    public Vehicle findByCustomVehicleID(String customVehicleID); // will query the db for all vehicles of that custom string ID
}
