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

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

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
    @GetMapping("/currentuser")
    public String getCurrentUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    /*
     * @PostMapping("/leaveReview/{username}")
     * public Review leaveNewReview(@PathVariable String username, @RequestBody
     * Review review)
     * {
     * return userService.leaveNewReview(username, review);
     * }
     */
}
