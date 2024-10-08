package cgrp.car_reservation.car_reservation;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface VehicleRepository extends MongoRepository<Vehicle, ObjectId> {

    // will search the db for vehicles based on make
    public List<Vehicle> findByMake(String make);

    // will search the db for vehicle based on year
    public List<Vehicle> findByYear(String year);

    // will search the db for vehicle based on type
    public List<Vehicle> findByType(String type);

}
