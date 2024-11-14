package cgrp.car_reservation.car_reservation.user;

import cgrp.car_reservation.car_reservation.review.Review;
import cgrp.car_reservation.car_reservation.review.ReviewRepository;
import cgrp.car_reservation.car_reservation.user.registration.ManagerRegistration;
import cgrp.car_reservation.car_reservation.user.registration.UserRegistration;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private ReviewRepository reviewRepository; // will allow for the creation of the review

    public User registerUser(UserDto userDto){
        if(userRepository.findByUsername(userDto.getUsername())!= null){
            throw new UsernameAlreadyTakenException("Supplied username is already taken!");
        }
        return new UserRegistration(userRepository, passwordEncoder).register(userDto);

    }

    public User registerManager(UserDto userDto){
        if(userRepository.findByUsername(userDto.getUsername())!= null){
            throw new UsernameAlreadyTakenException("Supplied username is already taken!");
        }
        return new ManagerRegistration(userRepository, passwordEncoder).register(userDto);

    }



    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserbyUsername(String username){return userRepository.findByUsername(username);}

    public User getUserById(ObjectId id){
        return userRepository.findById(id).orElse(null);
    }

    public void leaveNewReview(String username, Review review)
    {
        User user = userRepository.findByUsername(username); // will return the user that wants to leave the review

        user.addReview(review); // adds the review to the user, which is the review that the user left

        userRepository.save(user); // saves(updates) the current user object in the db to match the neccesary changess

    }

    // get the User by its username attribute
    public User getUserByUsername(String username)
    {
        return userRepository.findByUsername(username);
    }

}
