package cgrp.car_reservation.car_reservation.reservation;

import cgrp.car_reservation.car_reservation.email.EmailSenderService;
import cgrp.car_reservation.car_reservation.user.User;
import cgrp.car_reservation.car_reservation.user.UserRepository;
import cgrp.car_reservation.car_reservation.vehicle.Vehicle;
import cgrp.car_reservation.car_reservation.vehicle.VehicleNotAvailableException;
import cgrp.car_reservation.car_reservation.vehicle.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private EmailSenderService emailSenderService;

    private static final Logger logger = LoggerFactory.getLogger(ReservationService.class);

    public List<Reservation> getAllReservations(){
        return reservationRepository.findAll();
    }

    public Reservation createReservation(ReservationDto reservationDto){
        User user = userRepository.findById(reservationDto.getUserId())
                .orElseThrow(()->new RuntimeException("User not found"));
        Vehicle vehicle = vehicleRepository.findByCustomVehicleID(reservationDto.getCustomVehicleId());
        if (vehicle == null) {
            throw new RuntimeException("Vehicle not found");
        }//checks if vehicle is available this sets reservation fields
        if(!vehicle.isCurrentlyRented()){


            //creates reservation with params from user
            Reservation reservation = new Reservation(
                    user,
                    vehicle,
                    reservationDto.getEndDate(),
                    reservationDto.getStartDate(),
                    LocalDate.now()
            );

            //sets vehicle currently rented to true
            vehicle.setCurrentlyRented(true);
            vehicleRepository.save(vehicle);

            //user method for adding reservation to its array
            user.addReservation(reservation);

            //updates useruserRepository.save(user);

            logger.info("Users Reservations: {}",user.getReservations());

            logger.info("Vehicle user is currently renting: {}",user.getReservations().get(0).getVehicle().getMake());

            emailSenderService.reservationVerificationEmail(reservation);

            return reservationRepository.save(reservation);
        } else {
            logger.warn("Attempted to reserve an unavailable vehicle: {}", vehicle.getVehicleID());
            throw new VehicleNotAvailableException("The vehicle is currently unavailable for reservation.");
        }
    }
    public Reservation cancelReservation(Reservation reservation, User user){

        if(user.hasReservation(reservation)){
            reservation.getVehicle().setCurrentlyRented(true);
            vehicleRepository.save(reservation.getVehicle());
            user.removeReservation(reservation);
            return reservation;
        }
        if(reservationRepository.existsById(reservation.getReservationID()))
            throw new RuntimeException("Reservation does not belong to this user.");
        throw new RuntimeException("Reservation does not exist.");
    }
}
