package cgrp.car_reservation.car_reservation.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class AuthController {
    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/register-user")
    public ResponseEntity<String> registerUser(@RequestBody UserDto userDto){
        logger.info("password {}",userDto.getPassword());
        logger.info("confirm password {}",userDto.getConfirmPassword());
        if (userDto.getPassword().equals(userDto.getConfirmPassword())){
            User newUser = userService.registerUser(userDto);
            logger.info("User registered successfully!: {}", newUser);
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
        } else {
            logger.info("Password mismatch for user: {}", userDto.getUsername());
            return ResponseEntity.badRequest().body("Password mismatch");
        }
    }

    @PostMapping("/register-manager")
    public ResponseEntity<String> registerManager(@RequestBody UserDto userDto){
        if (userDto.getPassword().equals(userDto.getConfirmPassword())){
            User newUser = userService.registerManager(userDto);
            logger.info("User registered successfully!: {}", newUser);
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
        } else {
            logger.info("Password mismatch for user: {}", userDto.getUsername());
            return ResponseEntity.badRequest().body("Password mismatch");
        }
    }
}
