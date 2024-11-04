package cgrp.car_reservation.car_reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    private static final Logger logger = LoggerFactory.getLogger(ReservationService.class);


    public Reservation getReservationByUser(User user){
        return reservationRepository.findByUser(user);
    }

    public List<Reservation> getAllReservations(){
        return reservationRepository.findAll();
    }

    public Reservation createReservation(ReservationDto reservationDto){
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


            userReservations.add(reservation);

            user.setReservations(userReservations);

            userRepository.save(user);

            return reservationRepository.save(reservation);
        } else {
            logger.warn("Attempted to reserve an unavailable vehicle: {}", vehicle.getVehicleID());
            throw new VehicleNotAvailableException("The vehicle is currently unavailable for reservation.");
        }
    }
    public boolean cancelReservation(User user, Reservation reservation){

        if(user.hasReservation(reservation)){
            reservation.getVehicle().setCurrentlyRented(true);
            vehicleRepository.save(reservation.getVehicle());
            user.removeReservation(reservation);
            return true;
        }
        return false;
    }
}
