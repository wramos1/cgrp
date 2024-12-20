package cgrp.car_reservation.car_reservation.reservation;

import cgrp.car_reservation.car_reservation.payment_card.InvalidCardException;
import cgrp.car_reservation.car_reservation.transaction.Transaction;
import cgrp.car_reservation.car_reservation.user.User;
import cgrp.car_reservation.car_reservation.user.UserService;
import cgrp.car_reservation.car_reservation.vehicle.VehicleNotAvailableException;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Name: ReservationController<br>
 * Date of Code: October 7, 2024<br>
 * Programmer's Name: Alberto S<br>
 *
 * Description: Receives the endpoints from front end in regard to reservations .<br>
 *
 * Important Functions:<br>
 *  -createReservation: creates a vehicle rental reservation for the logged in user<br>
 *  -modifyReservation: modifies reservation based on a new start date and end date for the vehicle rental reservation<br>
 *  -cancelReservation: cancels specific reservation<br>
 *
 *
 * Important Data Structures: ReservationService, handles reservation logic by delegation from RestController class<br>
 *                            UserService, handles user logic<br>
 *
 *
 */

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private UserService userService;

    @Autowired
    private ReservationRepository reservationRepository;

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


        List<Reservation> reservations = reservationService.getAllReservations();

        List<Reservation> newRes = new ArrayList<>();

        for(Reservation reservation: reservations){
            if(reservation.getChargeAmount()==0){
            } else{
                newRes.add(reservation);
            }
        }

        return newRes;
    }

    //cancels a users reservation, only needs the reservation object which can be accessed
    //from the users reservation array
    @PostMapping("/cancel/{customReservationID}")
    public ResponseEntity<String> cancelReservation(@PathVariable String customReservationID, @AuthenticationPrincipal UserDetails userDetails){
        User user = userService.getUserbyUsername(userDetails.getUsername());

        System.out.println(user.getUsername());
        System.out.println(customReservationID);

         return reservationService.cancelVehicleReservation(customReservationID, user);

    }

    @PostMapping("/modify")
   public String modifyReservation(@RequestBody ModifyReservationDTO modifyReservationDTO, @AuthenticationPrincipal UserDetails userDetails)
    {

        return reservationService.modifyReservation(modifyReservationDTO);
    }

    @GetMapping("/myReservations")
    public List<Reservation> getUserReservations( @AuthenticationPrincipal UserDetails userDetails){
        return reservationService.getUserReservations(userDetails.getUsername());
    }

    // will be used by the manager to check back in vehicles
    @PostMapping("/{checkin}")
    public ResponseEntity<String> checkVehicleBackIn(@PathVariable String checkin)
    {
        // will catch the exception if thrown by the service object
        try
        {
            reservationService.checkVehicleBackIn(checkin);
            return ResponseEntity.status(HttpStatus.OK).body("Vehicle checked in properly!");
        }
        catch (InvalidReservationException reservationException)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(reservationException.getMessage());
        }

    }

    @GetMapping("/{id}")
    public Reservation getReservationCustomID(@PathVariable String id)
    {
        return reservationRepository.findByCustomReservationID(id);
    }

}
