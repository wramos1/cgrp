package cgrp.car_reservation.car_reservation;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    /*@Autowired
    public VehicleService(VehicleRepository vehicleRepository){
        this.vehicleRepository = vehicleRepository;
    }*/

    public List<Vehicle> getAllVehicles(){
        return vehicleRepository.findAll();
    }

    public Vehicle getVehicleById(ObjectId id){
        return vehicleRepository.findById(id).orElse(null);
    }

    public List<Vehicle> getVehicleByType(String type)
    {
        return vehicleRepository.findByType(type);
    }

    public List<Vehicle> getVehicleByMake(String make)
    {
        return vehicleRepository.findByMake(make);
    }

    public List<Vehicle> getVehicleByYear(int year)
    {
        return vehicleRepository.findByYear(year);
    }

    // query based on a price range and has the logic inside of the function/method
    public List<Vehicle> particularPriceRange(double lowerPrice, double upperPrice)
    {
        List<Vehicle> allVehicles = getAllVehicles(); // is returned all the vehicles from the db
        List<Vehicle> vehiclesInPriceRange =  new ArrayList<>(); // create a new ArrayList (like vector in C++) to store the vehicles that are in the price range

        for(Vehicle vehicle : allVehicles) // traverse through all vehicles in the db
        {
            if(vehicle.getDailyRentRate() >= lowerPrice && vehicle.getDailyRentRate() <= upperPrice) // if  the price is within the range, then add it to the list of vehicles which are in that range
                vehiclesInPriceRange.add(vehicle);
        }

        return vehiclesInPriceRange; // will return the vehicles in the price range
    }

}
