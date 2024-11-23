package cgrp.car_reservation.car_reservation.vehicle;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Class Name: VehicleController <br>
 * Date of Code:10/7/2024<br>
 * Programmer's Name: Alberto S<br>
 *
 * Description: Provides access to endpoints from the front end to the back end for logic pertaining to vehicles<br>
 *
 * Important Functions:<br>
 *  -getAllVehicles: returns all vehicles in a list<br>
 *  -getVehicleByPriceRange: returns list of vehicles between specified price range<br>
 *  -getVehiclesCheaperThan: returns list of vehicles cheaper than specified price<br>
 *  -getVehiclePricierThan: returns list of vehicles pricier than specified price<br>
 *  -getVehiclesByKeyword: returns list of vehicles who match that search keyword<br>
 *  -setAllVehiclesToAvailable: makes all vehicles in system available to be rented<br>
 *
 */
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


    @GetMapping("/priceRange/{lowerRange}/{upperRange}")
    public List<Vehicle> getVehicleByPriceRange(@PathVariable double lowerRange, @PathVariable double upperRange) // will return the vehicles to endpoint GET request the vehicles within that certain price range
    {
        return vehicleService.filterByPrice(lowerRange, upperRange);
    }

    @GetMapping("/mostExpensive/{upperRange}")
    public List<Vehicle> getVehicleCheaperThan(@PathVariable double upperRange) // will return the vehicles to endpoint GET request the vehicles within that certain price range
    {
        return vehicleService.filterByPrice(null, upperRange);
    }

    @GetMapping("/cheapest/{lowerRange}")
    public List<Vehicle> getVehiclePricierThan(@PathVariable double lowerRange) // will return the vehicles to endpoint GET request the vehicles within that certain price range
    {
        return vehicleService.filterByPrice(lowerRange, null);
    }

    @GetMapping("/keyword/{keyword}")
    public List<Vehicle> getVehiclesByKeyword(@PathVariable String keyword) // will return the vehicles to endpoint GET request the vehicles within that certain price range
    {
        return vehicleService.filterByKeyword(keyword);
    }

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


    @GetMapping("/reset")
    public void setAllVehiclesToAvailable(){
        vehicleService.setAllVehiclesToAvailable();
    }






}
