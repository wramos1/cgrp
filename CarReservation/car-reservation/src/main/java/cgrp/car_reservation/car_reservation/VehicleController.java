package cgrp.car_reservation.car_reservation;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/home")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

   /* @Autowired
    public VehicleController(VehicleService vehicleService){
        this.vehicleService = vehicleService;
    }*/

    @GetMapping("/vehicles")
    public List<Vehicle> getAllVehicles(){

        return vehicleService.getAllVehicles();
    }

    @GetMapping("/home/vehicles/{id}")
    public Vehicle getVehicleById(@PathVariable ObjectId id){
        return vehicleService.getVehicleById(id);
    }

    // Possibly make the types and stuff in all caps so whenever a search is done, it wont matter what the casing is

    // works 
    @GetMapping("/typeSearch/{type}")
    public List<Vehicle> getVehicleByType(@PathVariable String type) // will return to endpoint GET request the vehicles of that specified type
    {
        return vehicleService.getVehicleByType(type);
    }

}
