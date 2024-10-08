package cgrp.car_reservation.car_reservation;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {
    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService){
        this.vehicleService = vehicleService;
    }

    @GetMapping("/home/vehicles")
    public List<Vehicle> getAllVehicles(){
        return vehicleService.getAllVehicles();
    }

    @GetMapping("/home/vehicles/{id}")
    public Vehicle getVehicleById(@PathVariable ObjectId id){
        return vehicleService.getVehicleById(id);
    }

    @GetMapping("/make/{makeName}")
    public List<Vehicle> getVehicleByMake(@PathVariable String makeName)
    {
        return vehicleService.getVehicleByMake(makeName);
    }

    @GetMapping("/year/{yearDay}")
    public List<Vehicle> getVehicleByName(@PathVariable String yearDay)
    {
        return vehicleService.getVehicleByYear(yearDay);
    }

    @GetMapping("/vehicleWelcome")
    public String greetings()
    {
        return "Welcome to Vehicles Page";
    }

}
