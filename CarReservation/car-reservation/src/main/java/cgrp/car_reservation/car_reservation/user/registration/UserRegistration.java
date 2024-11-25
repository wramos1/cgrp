package cgrp.car_reservation.car_reservation.user.registration;

import cgrp.car_reservation.car_reservation.user.User;
import cgrp.car_reservation.car_reservation.user.UserDto;
import cgrp.car_reservation.car_reservation.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Class Name: UserRegistration
 * Date of Code: November 9, 2024
 * Programmer's Name: Alberto S
 *
 * Description: Provides regisrtation for the user
 *
 * Important Functions:
 * -register: returns user object and takes in user data transfer object
 *
 * Data Structures: N/A
 *
 * Algorithms: N/A
 *
 *
 */
@Component
public class UserRegistration {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    public UserRegistration(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public User register(UserDto userDto){

        User newUser = new User(userDto.getUsername(),passwordEncoder.encode(userDto.getPassword()),userDto.getEmail(),new String[]{"USER"});

        return userRepository.save(newUser);
    }
}
