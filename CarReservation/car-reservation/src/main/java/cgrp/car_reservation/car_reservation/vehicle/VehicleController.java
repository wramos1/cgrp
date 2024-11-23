package cgrp.car_reservation.car_reservation.vehicle;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Class Name: VehicleController <br>
 * Date of Code:
 * Programmer's Name:
 *
 * Description: Provides endpoints to access
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
