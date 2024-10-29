package cgrp.car_reservation.car_reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// has the business logic of the reviews
@Service
public class ReviewService {

    @Autowired // this does the dependency injection automatically through the annotation
    private ReviewRepository reviewRepository;

    @Autowired
    private VehicleRepository vehicleRepository; // this will allow us to gain access to the vehicles in the database so we can make the proper changes to the vehicle

    // calls on the repository layer object to create the document in the database
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
    }

    // leave a review to a specific vehicle
    public Review leaveReview(ReviewDTO reviewDTO)
    {
        Vehicle vehicleReviewIsOn = vehicleRepository.findByCustomVehicleID(reviewDTO.getCustomVehicleID()); // this should return the vehicle that we are leaving the review on

        User tempUser = new User("arthur", "hello", "arthur@csun.edu"); // constructs a temporary user to test this with

        Review newReview = new Review(4.5, "Great car", tempUser, vehicleReviewIsOn); // constructs a review object with that in it

       return reviewRepository.save(newReview); // save the review to the repository

    }

}
