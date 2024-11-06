package cgrp.car_reservation.car_reservation;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/makeSearch/{make}")
    public List<Vehicle> getVehicleByMake(@PathVariable String make) // will return to endpoint GET request the vehicles of that specfied make
    {
        return vehicleService.getVehicleByMake(make);
    }

    @GetMapping("/yearSearch/{year}")
    public List<Vehicle> getVehicleByYear(@PathVariable int year) // will return to endpoint GET request the vehicles of that year
    {
        return vehicleService.getVehicleByYear(year);
    }

    @GetMapping("/priceRange/{lowerRange}/{upperRange}")
    public List<Vehicle> getVehicleByPriceRange(@PathVariable double lowerRange, @PathVariable double upperRange) // will return the vehicles to endpoint GET request the vehicles within that certain price range
    {
        return vehicleService.particularPriceRange(lowerRange, upperRange);
    }

    // do something that is not everything that is rented out in cars


    // create a new car to utilize the UUID
    @PostMapping("/newvehicle")
    public Vehicle newVehicle(@RequestBody VehicleDTO tempVehicle)
    {
        return vehicleService.createNewVehicle(tempVehicle);
    }


    // this will return the vehicle that is associated with that custom UUID that we created
    @GetMapping("/{customVehicleID}")
    public Vehicle getVehicleByCustomVehicleID(@PathVariable String customVehicleID)
    {
        return vehicleService.getVehicleByCustomVehicleID(customVehicleID);
    }






}
