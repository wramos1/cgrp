package cgrp.car_reservation.car_reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

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
        logger.info("Method Accessed!");
        User user = userRepository.findById(reservationDto.getUserId()).orElseThrow(()->new RuntimeException("User not found"));
        logger.info("User found!");
        Vehicle vehicle = vehicleRepository.findById(reservationDto.getVehicleId()).orElseThrow(()->new RuntimeException("Vehicle not found"));
        logger.info("User and Vehicle found! {}", vehicle.getChargePerDay());
        vehicleRepository.save(vehicle);
        logger.info("Vehicle saved!");
        if(!vehicle.isStatus()){
            logger.warn("Attempted to reserve an unavailable vehicle: {}", vehicle.getVehicleID());
            throw new VehicleNotAvailableException("The vehicle is currently unavailable for reservation.");
        }
        //checks if vehicle is available this sets reservation fields
        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setVehicle(vehicle);
        reservation.setRentDate(reservationDto.getStartDate());
        reservation.setReturnDate(reservationDto.getEndDate());
        reservation.setChargeAmount(reservation.calculateChargeAmount());
        //uploads reservation to mongo, must be done before updating user reservation array
        reservationRepository.save(reservation);
        logger.info("Reservation saved!");
        //updating vehicle status
        vehicle.setStatus(false);
        logger.info("Saving vehicle {}...", vehicle);
        vehicleRepository.save(vehicle);
        logger.info("Vehicle added to MongoDB!: {}", vehicle);
        user.addReservation(reservation);
        userRepository.save(user);
        logger.info("Reservation added to user!: {}", reservation);

        return reservation;
    }

    public boolean cancelReservation(User user, Reservation reservation){

        if(user.hasReservation(reservation)){
            reservation.getVehicle().setStatus(true);
            vehicleRepository.save(reservation.getVehicle());
            user.getReservations().remove(reservation);
            return true;
        }
        return false;
    }
}
