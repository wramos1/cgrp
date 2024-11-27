package cgrp.car_reservation.car_reservation.reservation;

import cgrp.car_reservation.car_reservation.business_metrics.BusinessMetrics;
import cgrp.car_reservation.car_reservation.business_metrics.BusinessMetricsService;
import cgrp.car_reservation.car_reservation.email.EmailSenderService;

import cgrp.car_reservation.car_reservation.transaction.Transaction;
import cgrp.car_reservation.car_reservation.transaction.TransactionService;
import cgrp.car_reservation.car_reservation.payment_card.paymentCardService;
import cgrp.car_reservation.car_reservation.payment_card.InvalidCardException;

import cgrp.car_reservation.car_reservation.user.User;
import cgrp.car_reservation.car_reservation.user.UserRepository;
import cgrp.car_reservation.car_reservation.user.UserService;
import cgrp.car_reservation.car_reservation.vehicle.Vehicle;
import cgrp.car_reservation.car_reservation.vehicle.VehicleNotAvailableException;
import cgrp.car_reservation.car_reservation.vehicle.VehicleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Class Name: ReservationService<br>
 * Date of Code: October 7, 2024 and November 19, 2024<br>
 * Programmer's Name: Arthur and Alberto S<br>
 * <p>
 * Description: Provides the logic for reservation classes.<br>
 * <p>
 * Important Functions:<br>
 * -createReservation: Creates a new reservation for the logged in user based on the vehicle of their choosing<br>
 * -modifyReservation: Modifies existing reservation with a newly specified start and end date for the reservation<br>
 * -cancelReservation: Cancels an existing reservation<br>
 * <p>
 * Data Structures: N/A<br>
 * <p>
 * Algorithms: N/A<br>
 */
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
    private EmailSenderService emailSenderService;

    @Autowired
    private BusinessMetricsService businessMetricsService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private paymentCardService  paymentCardService;


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

            Reservation newReservation = new Reservation(customReservationID, currentUser.getUsername(), rentingVehicle, reservationDto.getEndDate(), reservationDto.getStartDate(), LocalDate.now());

            // Save the reservation to the reservation repository
            reservationRepository.save(newReservation);

            rentingVehicle.setCurrentlyRented(true);

            vehicleRepository.save(rentingVehicle); // saves the vehiclce with the updated field

            currentUser.addReservation(newReservation); // adds the reservation to the user, the reservation at this point should have an objectID which db will use to refrence it

            userRepository.save(currentUser); // saves the updated user object to the db

            transactionService.createNewRentalTransaction(newReservation, null);

        }


    }

    /**
     * Creates new reservation, while also updating vehicle rental status and logging the reservation transaction. Updates business metrics following reservations and sends email verification for new reservation.
     *
     * @param reservationDto Reservation Data Transfer Object that is passed in from the front end to the back end
     */
    public Reservation createReservation(ReservationDto reservationDto){
        User user = userRepository.findById(reservationDto.getUserId())
                .orElseThrow(()->new RuntimeException("User not found"));
        Vehicle vehicle = vehicleRepository.findByCustomVehicleID(reservationDto.getCustomVehicleId());
        if (vehicle == null) {
            throw new RuntimeException("Vehicle not found");
        }//checks if vehicle is available this sets reservation fields
        if(!vehicle.isCurrentlyRented()){

            // checks if the card is valid to proceed with the reservation; if not a valid card, it will throw the exception and the calling method of this method will catch it

            if(paymentCardService.validCard(reservationDto.getUserCard()) == false) // if the card is not valid to make a payment
            {
                throw new InvalidCardException("Payment card is not valid to proceed with reservation. Please try again!"); // creates and throws a new invalid exception
            }


            String customReservationID = UUID.randomUUID().toString().substring(0,12);// the UUID will be this long

            //creates reservation with params from user
            Reservation reservation = new Reservation(customReservationID, user.getUsername(), vehicle, reservationDto.getEndDate(), reservationDto.getStartDate(), LocalDate.now());

            //sets vehicle currently rented to true
            vehicle.setCurrentlyRented(true);
            vehicleRepository.save(vehicle);

            reservation = reservationRepository.save(reservation); // to get the obejct id, to save to user
            //user method for adding reservation to its array
            user.addReservation(reservation); // this is not adding the reservation to the user, which is causing error in the canceling of the reservation



            logger.info("Users Reservations: {}",user.getReservations());

            logger.info("Vehicle user is currently renting: {}",user.getReservations().get(0).getVehicle().getMake());

            emailSenderService.reservationVerificationEmail(reservation);

            businessMetricsService.addNewVehicleReservation(reservation); // will update the business metrics to show this reservation

            // this shows the current
            for(int i = 0; i < user.getReservations().size(); i++)
            {
                System.out.println(user.getReservations().get(i).getCustomReservationID());
            }

            //updates user (this has to be fixed to properly save the user with the reservations as it is above)
            userRepository.save(user);




            transactionService.createNewRentalTransaction(reservation, reservationDto.getUserCard()); // will call transaction service to create transaction based on this reservation

            return reservation;
        } else {
            logger.warn("Attempted to reserve an unavailable vehicle: {}", vehicle.getVehicleID());
            throw new VehicleNotAvailableException("The vehicle is currently unavailable for reservation.");
        }
    }


    public ResponseEntity<String> cancelVehicleReservation(String customReservationID, User user)
    {
        Reservation reservation = reservationRepository.findByCustomReservationID(customReservationID);

        // this conditional is the issue
        if(userService.checkIfHasReservation(customReservationID, user) == true)
        {
            Vehicle vehicle = reservation.getVehicle();
            vehicle.setCurrentlyRented(false);

            vehicleRepository.save(vehicle); // updates that vehicle in the database

            user.removeReservation(reservation);

            userRepository.save(user); // updates that user in the database

            businessMetricsService.checkedBackInVehicleReservation(vehicle); // removes the vehicle from the business metrics

            transactionService.createNewTransaction(reservation, "cancel");

            emailSenderService.cancelReservationVerificationEmail(reservation);

            return ResponseEntity.status(HttpStatus.OK).body("Vehicle Reservation Successfully Cancelled");
        }


        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unable to Cancel Vehicle Reservation. Try Again!");
    }


    /**
     * Modifies an existing reservation with a new start and end rental date. Logs modifying reservation transaction, updates business metrics, and sends verification email.
     *
     * @param modifyReservationDTO Modifying Reservation Data Transfer Object which specifies which reservation to modifies as well as the new start and end rental dates.
     * @return
     */
    public String modifyReservation(ModifyReservationDTO modifyReservationDTO)
    {
        Reservation reservationToBeModified = reservationRepository.findByCustomReservationID(modifyReservationDTO.getCustomReservationID());
        double currentReservationChargeAmount = reservationToBeModified.getChargeAmount();
        double modifiedReservationChargeAmount = 0.0;
        LocalDate oldStartDate = LocalDate.of(reservationToBeModified.getStartDate().getYear(), reservationToBeModified.getStartDate().getMonth(), reservationToBeModified.getStartDate().getDayOfMonth());
        LocalDate oldEndDate = LocalDate.of(reservationToBeModified.getEndDate().getYear(), reservationToBeModified.getEndDate().getMonth(), reservationToBeModified.getEndDate().getDayOfMonth());


        reservationToBeModified.setStartDate(modifyReservationDTO.getNewStartDate());
        reservationToBeModified.setEndDate(modifyReservationDTO.getNewEndDate());
        reservationToBeModified.calculateChargeAmount(); // this will calculate the new amount charge based on the modified reservation
        reservationToBeModified.setReservationDate(LocalDate.now()); // updates the timestamp of the reservation

        modifiedReservationChargeAmount = reservationToBeModified.getChargeAmount();

        reservationToBeModified = reservationRepository.save(reservationToBeModified); // saves the updated reservation with the new dates

        transactionService.createNewTransaction(reservationToBeModified, "modify"); // will create a transaction in the transactions collection in db

        businessMetricsService.modifiedVehicleReservation(currentReservationChargeAmount, modifiedReservationChargeAmount); // will update the business metrics to mark the changes of the modification of the reservation

        emailSenderService.modifiedReservationVerificationEmail(oldStartDate, oldEndDate, reservationToBeModified);

        return (reservationToBeModified == null) ? "Failure" : "Success";
    }

    public List<Reservation> getUserReservations(String username) {

        return reservationRepository.findByUsername(username);
    }

    public void checkVehicleBackIn(String checkin)
    {
        Reservation checkInReservation = reservationRepository.findByCustomReservationID(checkin);

        // throws an exception if the reservation id is entered in improperly
        if(checkInReservation == null)
            throw new InvalidReservationException("Reservation ID is invalid. Try again.");

        Vehicle checkInVehicle = vehicleRepository.findByCustomVehicleID(checkInReservation.getVehicle().getCustomVehicleID());

        User userToReservationBelongs = userRepository.findByUsername(checkInReservation.getUsername()); // gets the user that the reservation belongs to

        emailSenderService.checkBackInVerificationEmail(checkInReservation);

        businessMetricsService.checkedBackInVehicleReservation(checkInVehicle); // should remove it from the list of currently renetd vehicles

        checkInVehicle.setCurrentlyRented(false); // sets it to false so that it is no longer currently being rented and that it can be reserved again



        PrintStream cout = System.out;

        boolean reservationRemovalCheck = userToReservationBelongs.removeReservation(checkInReservation); // removes the reservation from the user

        if(reservationRemovalCheck == true)
            cout.println("It finds that there is a matching reservation in the user");


        for(Reservation reservation : userToReservationBelongs.getReservations())
        {
            System.out.println(reservation.getCustomReservationID());
        }

        vehicleRepository.save(checkInVehicle); // updates that vehicle in the db

        userRepository.save(userToReservationBelongs); // updates the user in the db

        reservationRepository.delete(checkInReservation); // deletes the reservation from the user, which essentially gets rid of it and checks the user back in

        transactionService.createNewTransaction(null, "checkin");

    }

}