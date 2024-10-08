package cgrp.car_reservation.car_reservation;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.stream.Stream;

public interface VehicleRepository extends MongoRepository<Vehicle, ObjectId> {
    public List<Vehicle> findByType(String type);
}
