package cgrp.car_reservation.car_reservation;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ReviewRepository reviewRepository;

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

    // add a review that is being left to a vehicle; will be called from the review service method
    public void addReviewToVehicle(String customVehicleID, Review currentVehicleReview)
    {
        Vehicle currentVehicle = vehicleRepository.findByCustomVehicleID(customVehicleID); // finds the vehicle we want to leave a review on

        currentVehicle.addReview(currentVehicleReview); // adds the review to the current vehicle, this review should now have an associated objectID so it can be refrenced in accordance to the db schema
        currentVehicle.calculateNewVehicleRating(); // this will update the rating of the vehicle based on the new review that is left on the vehicle

        vehicleRepository.save(currentVehicle); // save should instead of creating a new entry in the db, should update this vehicle's document in mongodb

    }


}
