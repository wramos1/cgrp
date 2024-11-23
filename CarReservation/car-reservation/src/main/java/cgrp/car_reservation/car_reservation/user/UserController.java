package cgrp.car_reservation.car_reservation.user;

import cgrp.car_reservation.car_reservation.user.User;
import cgrp.car_reservation.car_reservation.user.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClasName: UserController
 * Date of Code: October 7, 2024
 * Programmer's Name: Alberto S
 *
 * Description: Provides access to the endpoints that are request from front end for all things pertaining the User class.
 *
 * Important Functions:
 * -getAllUsers: returns all users in the system
 * -getCurrentUser: returns the current logged in user
 *
 * Data Structures: N/A
 *
 * Algorithms: N/A
 *
 */
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Provides an API endpoint for accessing all users of the system.
     *
     * @return all users in the database
     */
    @GetMapping("/homepage")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/")
    public String home() {
        return "CGRP";
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id) {
        ObjectId objectId = new ObjectId(id);
        logger.info("found user!");
        return userService.getUserById(objectId);
    }

    // this returns the login info of the current user

    /**
     *  Provides an API endpoint for accessing the current logged in user.
     *
     * @return current logged in user
     */
    @GetMapping("/currentuser")
    public String getCurrentUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }


}
