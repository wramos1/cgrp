package cgrp.car_reservation.car_reservation.user;

import cgrp.car_reservation.car_reservation.reservation.Reservation;
import cgrp.car_reservation.car_reservation.reservation.ReservationRepository;
import cgrp.car_reservation.car_reservation.review.Review;
import cgrp.car_reservation.car_reservation.review.ReviewRepository;
import cgrp.car_reservation.car_reservation.user.registration.ManagerRegistration;
import cgrp.car_reservation.car_reservation.user.registration.UserRegistration;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

/**
 * Class Name: UserService
 * Date of Code: October 7, 2024
 * Programmer's Name:
 *
 * Description: Provides all of the neccesary logic pertaining to the User class. <br>
 *
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private ReviewRepository reviewRepository; // will allow for the creation of the review

    /**
     * Registers a user to be available in the system and databse
     *
     * @param userDto Temporary Data Transfer object for the User.
     * @return User created for use in the system
     */
    public User registerUser(UserDto userDto){
        if(userRepository.findByUsername(userDto.getUsername())!= null){
            throw new UsernameAlreadyTakenException("Supplied username is already taken!");
        }
        return new UserRegistration(userRepository, passwordEncoder).register(userDto);

    }

    /**
     * Registers a manager to be available in the system and database.
     * @param userDto Temporary Data Transfer object for the Manager.
     * @return User class with manager role accessibility for user in the system.
     */
    public User registerManager(UserDto userDto){
        if(userRepository.findByUsername(userDto.getUsername())!= null){
            throw new UsernameAlreadyTakenException("Supplied username is already taken!");
        }
        return new ManagerRegistration(userRepository, passwordEncoder).register(userDto);

    }

    /**
     * Returns all users present in the system
     *
     * @return list of users in the system.
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Returns specific user based on query by username.
     * @param username user username to query on
     * @return User object which matches that username
     */
    public User getUserbyUsername(String username){return userRepository.findByUsername(username);}

    /**
     * Returns specific user based on query by MongoDB ObjectId
     * @param id objectId to query on
     * @return User object which matches that objectid
     */
    public User getUserById(ObjectId id){
        return userRepository.findById(id).orElse(null);
    }

    /**
     * Adds a review to the user, a review which the user has left on specific vehicle
     *
     * @param username username of the user
     * @param review review left by the user
     */
    public void leaveNewReview(String username, Review review)
    {
        User user = userRepository.findByUsername(username); // will return the user that wants to leave the review

        user.addReview(review); // adds the review to the user, which is the review that the user left

        userRepository.save(user); // saves(updates) the current user object in the db to match the neccesary changess

    }

    // get the User by its username attribute
    /**
     * Returns specific user based on query by username.
     * @param username user username to query on
     * @return User object which matches that username
     */
    public User getUserByUsername(String username)
    {
        return userRepository.findByUsername(username);
    }

    // checks if the user has that reservation
    /**
     * Returns boolean value if the user has that reservation present in its list of reservations.
     *
     * @param reservationID custom reservation id of that specific reservation
     * @param user user being checked if it has reservation
     * @return true if user has that reservation in its list of reservations
     */
    public boolean checkIfHasReservation(String reservationID, User user)
    {
        for(Reservation reservation : user.getReservations())
        {
            if(reservation.getCustomReservationID().equals(reservationID))
                return true; // will return true if user has that reservation
        }


        System.out.println("It is not finding this reservation");

        return false;
    }

}
