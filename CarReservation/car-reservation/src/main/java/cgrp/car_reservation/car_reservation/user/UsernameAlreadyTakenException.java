package cgrp.car_reservation.car_reservation.user;

/**
 * Class Name: UsernameAlreadyTakenException
 * Date of Code: October 7, 2024
 * Programmer's Name: Alberto S
 *
 * Description: An exception which is a derived class of the RuntimeException class. It is thrown if the username is already taken by a user.
 *
 * Important Functions: Constructor
 *
 * Data Structures: N/A
 *
 * Algorithms: N/A
 *
 */
public class UsernameAlreadyTakenException extends RuntimeException {

    /**
     * Constructs the UsernameAlreadyTakenException object
     *
     * @param message message when UsernameAlreadyTakenException is thrown
     */
    public UsernameAlreadyTakenException(String message){
        super(message);
    }

}
