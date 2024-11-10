package cgrp.car_reservation.car_reservation.reservation;

import cgrp.car_reservation.car_reservation.user.User;
import cgrp.car_reservation.car_reservation.user.UserService;
import cgrp.car_reservation.car_reservation.vehicle.VehicleNotAvailableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(ReservationController.class);
    //creating a new reservation
    @PostMapping("/reserve")
    public ResponseEntity<String> createReservation(@RequestBody ReservationDto reservationDto, @AuthenticationPrincipal UserDetails userDetails){
        //finds the logged-in user
        reservationDto.setUserId(userService.getUserByUsername(userDetails.getUsername()).getUserId());//this line is fucked


        try {

            //attempts to create the reservation given the user input
            Reservation reservation = reservationService.createReservation(reservationDto);

            return ResponseEntity.ok("Vehicle reserved successfully!");
        } catch (VehicleNotAvailableException e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (RuntimeException e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @GetMapping("/reserve")
    public List<Reservation> getAllReservations(){
        return reservationService.getAllReservations();
    }

    //cancels a users reservation, only needs the reservation object which can be accessed
    //from the users reservation array
    public Reservation cancelReservation(Reservation reservation, @AuthenticationPrincipal UserDetails userDetails){
        User user = userService.getUserbyUsername(userDetails.getUsername());
        return reservationService.cancelReservation(reservation, user);
    }
}