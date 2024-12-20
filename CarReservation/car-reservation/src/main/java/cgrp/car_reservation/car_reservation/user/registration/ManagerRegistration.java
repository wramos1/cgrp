package cgrp.car_reservation.car_reservation.user.registration;

import cgrp.car_reservation.car_reservation.user.User;
import cgrp.car_reservation.car_reservation.user.UserDto;
import cgrp.car_reservation.car_reservation.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Array;

/**
 * Class Name: ManagerRegistration
 * Date of Code: November 9, 2024
 * Programmer's Name: Alberto S
 *
 * Description: Provides registration for manager
 *
 * Important Functions:
 *  -register: registers the manager role in the user class
 *
 * Data Structures: N/A
 *
 * Algorithms: N/A
 *
 */
@Component
public class ManagerRegistration {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    public ManagerRegistration(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public User register(UserDto userDto){

        User newUser = new User(userDto.getUsername(),passwordEncoder.encode(userDto.getPassword()),userDto.getEmail(), new String[]{"ADMIN", "USER"});

        return userRepository.save(newUser);
    }
}
