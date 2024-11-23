package cgrp.car_reservation.car_reservation.user.registration;

import cgrp.car_reservation.car_reservation.user.User;
import cgrp.car_reservation.car_reservation.user.UserDto;

/**
 * Interface Name: Registration
 * Date of Code: November 9, 2024
 * Programmer's Name: Alberto S
 *
 * Description: Provides interface for more specific registration classes to implement
 *
 * Important Functions:
 * -register: will return a user object and each implementation of interface will implement in different manner
 *
 * Data Structures: N/A
 *
 * Algorithms: N/A
 *
 */
public interface Registration {
    public User register(UserDto userDto);
}
