package cgrp.car_reservation.car_reservation;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface VehicleRepository extends MongoRepository<Vehicle, ObjectId> {

    public List<Vehicle> findByType(String type); // will query the db for all vehicles of that type

    public List<Vehicle> findByMake(String make); // will query the db for all vehicles of that make

    public List<Vehicle> findByYear(int year); // wll query the db for all vehicles of that year

}
