package cgrp.car_reservation.car_reservation.vehicle;

import cgrp.car_reservation.car_reservation.review.Review;
import cgrp.car_reservation.car_reservation.review.ReviewRepository;
import cgrp.car_reservation.car_reservation.vehicle.filters.*;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

import java.util.List;

/**
 * Class Name: VehicleService<br>
 * Date of Code:10/7/2024<br>
 * Programmer's Name: Alberto S and Arthur <br>
 *
 * Description: Provides necessary logic for all functionality pertaining to vehicle class.<br>
 *
 * Important Functions:<br>
 *  -filterByPrice: is inputted an upper and lower bound for price of vehicle, returns list of vehicles within those bounds<br>
 *  -filterByKeyword: is inputted a search keyword, returns list of vehicles who match that search keyword<br>
 *  -addReviewToVehicle: is inputted a review and adds the review to the vehicle<br>
 *  -setAllVehiclesToAvailable: sets all vehicles in the system to be available to be rented<br>
 *
 */
@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ReviewRepository reviewRepository;


    public List<Vehicle> getAllVehicles(){
        return vehicleRepository.findAll();
    }

    public Vehicle getVehicleById(ObjectId id){
        return vehicleRepository.findById(id).orElse(null);
    }

    // query based on a price range and has the logic inside of the function/method
    public List<Vehicle> filterByPrice(Double lowerBound, Double upperBound)
    {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        if (lowerBound == null && upperBound == null) {
            return vehicles;
        } else if (lowerBound == null) {
            return new FilterByUpperBound(vehicles, upperBound).filter();
        } else if (upperBound == null) {
            return new FilterByLowerBound(vehicles, lowerBound).filter();
        } else {
            return new FilterByUpperAndLowerBounds(vehicles, lowerBound, upperBound).filter();
        }
    }

    public List<Vehicle> filterByKeyword(String keyword){
        List<Vehicle> vehicles = vehicleRepository.findAll();
        return new FilterByKeyword(vehicles, keyword).filter();
    }

    // this will be used by us to create the new vehicle and generate the uniqueID for it
    public Vehicle createNewVehicle(VehicleDTO tempVehicle)
    {
        String uniqueID = UUID.randomUUID().toString(); // creates a unique ID that is converted to a string

        // creates new vehicle with the uniqueID
        Vehicle newVehicle = new Vehicle(uniqueID, tempVehicle.getMake(), tempVehicle.getModel(), tempVehicle.getYear(), tempVehicle.getType(), tempVehicle.getColor(), tempVehicle.getDailyRentRate(), tempVehicle.getVehicleRating(), tempVehicle.isCurrentlyRented(), tempVehicle.getDescription(), tempVehicle.getVehicleSearchTerm(), tempVehicle.getVehicleImageHostingURL(),tempVehicle.getVehicleFeatures(), tempVehicle.getReviewsOfVehicle());

        return vehicleRepository.save(newVehicle);

    }

    public Vehicle getVehicleByCustomVehicleID(String customVehicleID)
    {
        return vehicleRepository.findByCustomVehicleID(customVehicleID);
    }

    // add a review that is being left to a vehicle; will be called from the review service method
    public void addReviewToVehicle(String customVehicleID, Review currentVehicleReview)
    {
        Vehicle currentVehicle = vehicleRepository.findByCustomVehicleID(customVehicleID); // finds the vehicle we want to leave a review on

        currentVehicle.addReview(currentVehicleReview); // adds the review to the current vehicle, this review should now have an associated objectID so it can be refrenced in accordance to the db schema
        currentVehicle.calculateNewVehicleRating(); // this will update the rating of the vehicle based on the new review that is left on the vehicle

        vehicleRepository.save(currentVehicle); // save should instead of creating a new entry in the db, should update this vehicle's document in mongodb

    }

    public void setAllVehiclesToAvailable(){
        List<Vehicle> vehicles = vehicleRepository.findAll();
        for(Vehicle vehicle : vehicles) {
            vehicle.setCurrentlyRented(false);
            vehicleRepository.save(vehicle);
        }
    }


}
