package cgrp.car_reservation.car_reservation.reservation;

import cgrp.car_reservation.car_reservation.business_metrics.BusinessMetricsService;
import cgrp.car_reservation.car_reservation.email.EmailSenderService;
import cgrp.car_reservation.car_reservation.transaction.TransactionService;
import cgrp.car_reservation.car_reservation.user.User;
import cgrp.car_reservation.car_reservation.user.UserRepository;
import cgrp.car_reservation.car_reservation.user.UserService;
import cgrp.car_reservation.car_reservation.vehicle.Vehicle;
import cgrp.car_reservation.car_reservation.vehicle.VehicleNotAvailableException;
import cgrp.car_reservation.car_reservation.vehicle.VehicleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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


    private static final Logger logger = LoggerFactory.getLogger(ReservationService.class);

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    /**
     * Creates new reservation, while also updating vehicle rental status and logging the reservation transaction. Updates business metrics following reservations and sends email verification for new reservation.
     *
     * @param reservationDto Reservation Data Transfer Object that is passed in from the front end to the back end
     */
    public Reservation createReservation(ReservationDto reservationDto) {
        User user = userRepository.findById(reservationDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Vehicle vehicle = vehicleRepository.findByCustomVehicleID(reservationDto.getCustomVehicleId());
        if (vehicle == null) {
            throw new RuntimeException("Vehicle not found");
        }//checks if vehicle is available this sets reservation fields
        if (!vehicle.isCurrentlyRented()) {


            String customReservationID = UUID.randomUUID().toString().substring(0, 12);// the UUID will be this long

            //creates reservation with params from user
            Reservation reservation = new Reservation(customReservationID, user.getUsername(), vehicle, reservationDto.getEndDate(), reservationDto.getStartDate(), LocalDate.now());

            //sets vehicle currently rented to true
            vehicle.setCurrentlyRented(true);
            vehicleRepository.save(vehicle);

            //user method for adding reservation to its array
            user.addReservation(reservation); // this is not adding the reservation to the user, which is causing error in the canceling of the reservation


            logger.info("Users Reservations: {}", user.getReservations());

            logger.info("Vehicle user is currently renting: {}", user.getReservations().get(0).getVehicle().getMake());

            emailSenderService.reservationVerificationEmail(reservation);

            businessMetricsService.addNewVehicleReservation(reservation); // will update the business metrics to show this reservation

            // this shows the current
            for (int i = 0; i < user.getReservations().size(); i++) {
                System.out.println(user.getReservations().get(i).getCustomReservationID());
            }

            //updates user (this has to be fixed to properly save the user with the reservations as it is above)
            userRepository.save(user);


            reservation = reservationRepository.save(reservation);


            transactionService.createNewRentalTransaction(reservation, null); // will call transaction service to create transaction based on this reservation

            return reservation;
        } else {
            logger.warn("Attempted to reserve an unavailable vehicle: {}", vehicle.getVehicleID());
            throw new VehicleNotAvailableException("The vehicle is currently unavailable for reservation.");
        }
    }


    public String cancelVehicleReservation(String customReservationID, User user) {
        Reservation reservation = reservationRepository.findByCustomReservationID(customReservationID);

        // this conditional is the issue
        if (userService.checkIfHasReservation(customReservationID, user)) {
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

    public List<Reservation> getUserReservations(String username) {

        return reservationRepository.findByUsername(username);
    }
}
