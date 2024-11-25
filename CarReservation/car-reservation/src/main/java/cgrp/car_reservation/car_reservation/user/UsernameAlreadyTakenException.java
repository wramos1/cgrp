package cgrp.car_reservation.car_reservation.user;

/**
 * Class Name: UsernameAlreadyTakenException<br>
 * Date of Code: October 7, 2024<br>
 * Programmer's Name: Alberto S<br>
 *
 * Description: An exception which is a derived class of the RuntimeException class. It is thrown if the username is already taken by a user.<br>
 *
 * Important Functions: Constructor<br>
 *
 * Data Structures: N/A<br>
 *
 * Algorithms: N/A<br>
 *
 */
public class UsernameAlreadyTakenException extends RuntimeException {

    /**
     * Constructs the UsernameAlreadyTakenException object<br>
     *
     * @param message message when UsernameAlreadyTakenException is thrown<br>
     */
    public UsernameAlreadyTakenException(String message){
        super(message);
    }

}
