package cgrp.car_reservation.car_reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// has the business logic of the reviews
@Service
public class ReviewService {

    @Autowired // this does the dependency injection automatically through the annotation
    private ReviewRepository reviewRepository;

    @Autowired
    private VehicleRepository vehicleRepository; // this will allow us to gain access to the vehicles in the database so we can make the proper changes to the vehicle

    @Autowired
    private VehicleService vehicleService; // the vehicle service will be called in order to properly make the needed adjustemnts

    @Autowired
    private EmailSenderService emailSenderService; // this will send email confirming a review that has been left

    @Autowired
    private UserService userService;

/*    // calls on the repository layer object to create the document in the database
    public Review createReview(Review review)
    {
        reviewRepository.save(review); // saves the review in the database
        return review;
    }

    // writes the review
    public Review writeReview(ReviewDTO reviewDTO, User currentUser)
    {
        Review newReview = new Review(reviewDTO.getReviewRating(), reviewDTO.getReviewBody(), currentUser);

        return reviewRepository.save(newReview);
    }

    public List<Review> userSpecificReviews(String lastName)
    {
        List<Review> allReviews = reviewRepository.findAll();

        List<Review> userSpecificReviews = new ArrayList<Review>(); // allocate that new array list that will hold this specific user's reviews

        for(Review currentReview : allReviews)
        {
            if(currentReview.getReviewLeaver().getUsername().equals(lastName))
            {
                userSpecificReviews.add(currentReview);
            }

        }

        return userSpecificReviews;
    }*/

    // leave a review; will save the review in the mongodb, and will have a refrence to the vehicle the review is on
    public Review leaveReview(ReviewDTO reviewDTO)
    {
        Vehicle vehicleReviewIsOn = vehicleRepository.findByCustomVehicleID(reviewDTO.getCustomVehicleID()); // this should return the vehicle that we are leaving the review on

        //User tempUser = new User("FASTCARArthur", "hello", "arthur@csun.edu"); // constructs a temporary user to test this with

        // should get the current user who is logged in
        String currentLoggedInUsername = SecurityContextHolder.getContext().getAuthentication().getName();

        User currentUser = userService.getUserByUsername(currentLoggedInUsername);

        String customReviewID = UUID.randomUUID().toString().replace("-" ,""); // will replace the dashes in the UUID with nothing

        Review newReview = new Review(customReviewID, reviewDTO.getReviewRating(), reviewDTO.getReviewBody(), currentUser.getUsername(), vehicleReviewIsOn); // constructs a review object with that in it

        reviewRepository.save(newReview); // saves the review to the repository

        // whatever is causing the issue is on this bottom half of the function/method

        // figure out why two reviews are being saved to the db

        // no such method exception was being thrown because review did not have a default constructor but now it works and should be fine
        Review currentReview = reviewRepository.findByCustomReviewID(customReviewID);



        vehicleService.addReviewToVehicle(reviewDTO.getCustomVehicleID(), currentReview); // this call to a method in vehicle service should add the recently created review as a refrence in the vehicle on which the review is for

        userService.leaveNewReview(currentUser.getUsername(), currentReview); // adds the review to the user

        // test out if this will work with sending some stuff in an email with reviews
        emailSenderService.reviewVerificationEmail(currentReview, currentUser);


        return currentReview;
    }

}
