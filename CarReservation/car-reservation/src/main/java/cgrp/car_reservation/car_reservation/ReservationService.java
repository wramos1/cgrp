package cgrp.car_reservation.car_reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class ReservationService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private TransactionService transactionService;


    private static final Logger logger = LoggerFactory.getLogger(ReservationService.class);


    public Reservation getReservationByUser(User user){
        return reservationRepository.findByUser(user);
    }

    public List<Reservation> getAllReservations(){
        return reservationRepository.findAll();
    }

/*    public Reservation createReservation(ReservationDto reservationDto){
        User user = userRepository.findById(reservationDto.getUserId()).orElseThrow(()->new RuntimeException("User not found"));
        logger.info("User Found!");
        Vehicle vehicle = vehicleRepository.findById(reservationDto.getVehicleId()).orElseThrow(()->new RuntimeException("Vehicle not found"));
        logger.info("Vehicle Found!");
        //checks if vehicle is available this sets reservation fields
        if(!vehicle.isCurrentlyRented()){
            logger.info("Vehicle is available!");
            Reservation reservation = new Reservation();
            logger.info("Reservation Created!");
            reservation.setUser(user);
            logger.info("User Set!");
            reservation.setVehicle(vehicle);
            logger.info("Vehicle Set!");
            reservation.setRentDate(reservationDto.getStartDate());
            reservation.setReturnDate(reservationDto.getEndDate());
            logger.info("Dates Set!");

            reservation.calculateChargeAmount();

            vehicle.setCurrentlyRented(true);
            vehicleRepository.save(vehicle);

            ArrayList<Reservation> userReservations = (ArrayList<Reservation>) user.getReservations();
            logger.info("Current userReservations: {}", userReservations);


            userReservations.add(reservation);
            logger.info("Updated userReservations: {}", userReservations);

            user.setReservations(userReservations);

            userRepository.save(user);
            logger.info("Reservation added to user!: {}", reservation);

            return reservationRepository.save(reservation);
        } else {
            logger.warn("Attempted to reserve an unavailable vehicle: {}", vehicle.getVehicleID());
            throw new VehicleNotAvailableException("The vehicle is currently unavailable for reservation.");
        }
    }*/
    public boolean cancelReservation(User user, Reservation reservation){

        if(user.hasReservation(reservation)){
            reservation.getVehicle().setCurrentlyRented(true);
            vehicleRepository.save(reservation.getVehicle());
            user.removeReservation(reservation);
            return true;
        }
        return false;
    }

    // this method will be used to calculate the amount that the reservation charge will be
    public double calculateReservationChargeAmount(ReservationDto reservationDto, Vehicle rentingVehicle)
    {
        double reservationCharge = 0; // the amount of money that renting the vehicle will cost for that many days

        Period period = Period.between(reservationDto.getStartDate(), reservationDto.getEndDate()); // calculates the period of days, months, and year between the local date objects, we only need the days since people only rent vehicles days at a time

        reservationCharge = period.getDays() * rentingVehicle.getDailyRentRate(); // this will multiply the number of days that the vehicle is being rented out for by its daily rate

        return reservationCharge;
    }

    // this will create the new reservation for the car that we are renting out and will also begin the transaction creation
    public Reservation createNewReservation(ReservationDto reservationDto)
    {
        Vehicle rentingVehicle = vehicleRepository.findByCustomVehicleID(reservationDto.getVehicleID()); // will find the vehicle that the reservation is for from the custom vehicle ID which is a string

        String customReservationID = UUID.randomUUID().toString().substring(0,7); // the customReservationID will only be 8 digits long to make it unique from the other entitys that are also using UUID

        Reservation currentResveration = new Reservation(customReservationID, rentingVehicle, reservationDto.getStartDate(), reservationDto.getEndDate(), calculateReservationChargeAmount(reservationDto, rentingVehicle)); // this will create a reservation with the way that we have temporarly specifeid

        reservationRepository.save(currentResveration); // saves the reservation into the db

        Reservation existingReservation = reservationRepository.findByCustomReservationID(customReservationID); // now this version of the resevration once it has been created in mongodb will have the objectID, so then we can use it to be refrenced in the trasnactions

        transactionService.logTransaction(existingReservation); // this will delegate to the transaction service mthod log transaction which will create the transaction in the database

        return existingReservation;
    }


}
