package cgrp.car_reservation.car_reservation.user.registration;

import cgrp.car_reservation.car_reservation.user.User;
import cgrp.car_reservation.car_reservation.user.UserDto;
import cgrp.car_reservation.car_reservation.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserRegistration {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserRegistration(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public User register(UserDto userDto){

        User newUser = new User(userDto.getUsername(),passwordEncoder.encode(userDto.getPassword()),userDto.getEmail(),"USER");

        return userRepository.save(newUser);
    }
}
