package cgrp.car_reservation.car_reservation.vehicle;

import cgrp.car_reservation.car_reservation.review.ReviewRepository;
import cgrp.car_reservation.car_reservation.vehicle.filters.*;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

import java.util.List;

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


   /* public Review leaveNewRating(ReviewDTO review)
    {
        Review actualReview = new Review()
    }*/

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

}
