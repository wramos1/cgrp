package cgrp.car_reservation.car_reservation;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    @Autowired // Lets framework know we want to instantiate class below
    private CarRepository carRepository;

    public List<Car> allCars() {

        // Method defined within MongoRepository in CarRepository through extending
        return carRepository.findAll();
    }

    public Optional<Car> singleCar(ObjectId id) {
        return carRepository.findById(id);
    }
}
