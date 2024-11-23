package cgrp.car_reservation.car_reservation.user;

import lombok.Getter;

/**
 * Class Name: UserDto
 * Date of Code: October 7, 2024
 * Programmer's Name: Alberto S
 *
 * Description: Provides a temporary data transfer object in order to send information from front end to back end, to create a user in the system.
 *
 * Important Functions: Constructor
 *
 * Data Structures: N/A
 *
 * Algorithms: N/A
 *
 */
@Getter
public class UserDto {
    private final String username;
    private final String email;
    private final String password;
    private final String confirmPassword;

    /**
     * Constructs a temporary User data transfer object to send data from front end to back end
     *
     * @param username username for user's account
     * @param email email for user's account
     * @param password password for user's account
     * @param confirmPassword confirmed password for user's account
     */
    public UserDto(String username, String email, String password, String confirmPassword){
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }
}
