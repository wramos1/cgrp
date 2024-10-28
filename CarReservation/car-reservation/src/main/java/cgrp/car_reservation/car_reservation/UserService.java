package cgrp.car_reservation.car_reservation;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    private ReviewRepository reviewRepository; // will allow for the creation of the review

    public User registerUser(UserDto userDto){
        logger.info("Registering user: {}", userDto.getUsername());

        User newUser = new User();

        newUser.setUsername(userDto.getUsername());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        newUser.setEmail(userDto.getEmail());
        return userRepository.save(newUser);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(ObjectId id){
        return userRepository.findById(id).orElse(null);
    }

    public Review leaveNewReview(String username, Review review)
    {
        User user = userRepository.findByUsername(username); // will return the user that wants to leave the review

        reviewRepository.save(review); // saves the review for now in the db


        return review;
    }

    // get the User by its username attribute
    public User getUserByUsername(String username)
    {
        return userRepository.findByUsername(username);
    }


}
