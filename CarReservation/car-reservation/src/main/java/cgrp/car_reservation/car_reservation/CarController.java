package cgrp.car_reservation.car_reservation;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

// Creates endpoint
@RequestMapping("/api/v1/cars")

public class CarController {
    @Autowired // Lets framework know we want to instantiate class below
    private CarService carService;

    // Function to get all cars
    @GetMapping
    public ResponseEntity<List<Car>> getAllCars() {
        return new ResponseEntity<List<Car>>(carService.allCars(), HttpStatus.OK);
    }

    @GetMapping("/{carId}")
    public ResponseEntity<Optional<Car>> getSingleCar(@PathVariable String carId) {
        return new ResponseEntity<Optional<Car>>(carService.singleCar(carId), HttpStatus.OK);
    }

}
