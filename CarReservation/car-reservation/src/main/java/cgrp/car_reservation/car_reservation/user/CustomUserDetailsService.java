package cgrp.car_reservation.car_reservation.user;

import cgrp.car_reservation.car_reservation.user.User;
import cgrp.car_reservation.car_reservation.user.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * ClassName: CustomerUserDetailsService
 * Date of Code: October 7, 2024
 * Programmer's Name: Alberto S
 *
 * Description: Adds appropriate fields to user for Spring Security
 *
 * Important Functions:
 * -loadByUsername: returns userDetails for user based on passed in parameter username of a user object
 *
 * Data Structures: N/A
 *
 * Algorithms: N/A
 *
 *
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    public CustomUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }
        System.out.println("User found: " + username);
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getRole())  // You can customize roles/authorities here
                .build();
    }

}
