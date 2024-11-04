package cgrp.car_reservation.car_reservation;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
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

        reservationDto.setUserId(userService.getUserByUsername(userDetails.getUsername()).getUserId());//this line is fucked
        try {
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
}
