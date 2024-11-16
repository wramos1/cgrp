package cgrp.car_reservation.car_reservation.reservation;

import cgrp.car_reservation.car_reservation.transaction.Transaction;
import cgrp.car_reservation.car_reservation.transaction.TransactionService;
import cgrp.car_reservation.car_reservation.user.User;
import cgrp.car_reservation.car_reservation.user.UserRepository;
import cgrp.car_reservation.car_reservation.user.UserService;
import cgrp.car_reservation.car_reservation.vehicle.Vehicle;
import cgrp.car_reservation.car_reservation.vehicle.VehicleNotAvailableException;
import cgrp.car_reservation.car_reservation.vehicle.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ReservationService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private TransactionService transactionService;

    private static final Logger logger = LoggerFactory.getLogger(ReservationService.class);

    public List<Reservation> getAllReservations(){
        return reservationRepository.findAll();
    }

    public void createNewReservation(ReservationDto reservationDto)
    {
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();

        User currentUser = userRepository.findByUsername(currentUserName);

        Vehicle rentingVehicle = vehicleRepository.findByCustomVehicleID(reservationDto.getCustomVehicleId()); // gets the vehicle from the db

        if(rentingVehicle.isCurrentlyRented() == false) // this is the case that it is available to be rented
        {
            String customReservationID = UUID.randomUUID().toString().substring(0,12);

            Reservation newReservation = new Reservation(customReservationID, currentUser, rentingVehicle, reservationDto.getEndDate(), reservationDto.getStartDate(), LocalDate.now());

            // Save the reservation to the reservation repository
            reservationRepository.save(newReservation);

            rentingVehicle.setCurrentlyRented(true);

            vehicleRepository.save(rentingVehicle); // saves the vehiclce with the updated field

            currentUser.addReservation(newReservation); // adds the reservation to the user, the reservation at this point should have an objectID which db will use to refrence it

            userRepository.save(currentUser); // saves the updated user object to the db

            transactionService.createNewRentalTransaction(newReservation, null);

        }


    }


    public Reservation createReservation(ReservationDto reservationDto){
        User user = userRepository.findById(reservationDto.getUserId())
                .orElseThrow(()->new RuntimeException("User not found"));
        Vehicle vehicle = vehicleRepository.findByCustomVehicleID(reservationDto.getCustomVehicleId());
        if (vehicle == null) {
            throw new RuntimeException("Vehicle not found");
        }//checks if vehicle is available this sets reservation fields
        if(!vehicle.isCurrentlyRented()){


            String customReservationID = UUID.randomUUID().toString().substring(0,12);// the UUID will be this long

            //creates reservation with params from user
            Reservation reservation = new Reservation(customReservationID, user, vehicle, reservationDto.getEndDate(), reservationDto.getStartDate(), LocalDate.now());

            //sets vehicle currently rented to true
            vehicle.setCurrentlyRented(true);
            vehicleRepository.save(vehicle);

            //user method for adding reservation to its array
            user.addReservation(reservation); // this is not adding the reservation to the user, which is causing error in the canceling of the reservation

            for(int i = 0; i < user.getReservations().size(); i++)
            {
                System.out.println(user.getReservations().get(i).getCustomReservationID());
            }

            //updates user
            userRepository.save(user);

            reservation = reservationRepository.save(reservation);


            transactionService.createNewRentalTransaction(reservation, null); // will call transaction service to create transaction based on this reservation

            return reservation;
        } else {
            logger.warn("Attempted to reserve an unavailable vehicle: {}", vehicle.getVehicleID());
            throw new VehicleNotAvailableException("The vehicle is currently unavailable for reservation.");
        }
    }
    public Reservation cancelReservation(String reservationID, User user){

        Reservation reservation = reservationRepository.findByCustomReservationID(reservationID); // finds the reservation by the custom reservation ID

        if(userService.checkIfHasReservation(reservationID, user)) // will return true if the user has that
        {
            Vehicle reservedVehicle = reservation.getVehicle();
            reservedVehicle.setCurrentlyRented(false); // because the reservation on it is being cancelled
            vehicleRepository.save(reservedVehicle);


            user.removeReservation(reservation);

            transactionService.createNewTransaction(reservation, "cancel");

            return reservation;
        }
        if(reservationRepository.existsById(reservation.getReservationID()))
            throw new RuntimeException("Reservation does not belong to this user.");
        throw new RuntimeException("Reservation does not exist.");
    }

    public String cancelVehicleReservation(String customReservationID, User user)
    {
        Reservation reservation = reservationRepository.findByCustomReservationID(customReservationID);

        // this conditional is the issue
        if(userService.checkIfHasReservation(customReservationID, user))
        {
            Vehicle vehicle = reservation.getVehicle();
            vehicle.setCurrentlyRented(false);

            vehicleRepository.save(vehicle); // updates that vehicle in the database

            user.removeReservation(reservation);

            userRepository.save(user); // updates that user in the database

            transactionService.createNewTransaction(reservation, "cancel");

            return "Worked";
        }


        return "Something Went Wrong";
    }
}