package cgrp.car_reservation.car_reservation.review;

import cgrp.car_reservation.car_reservation.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// has the business logic of the reviews
@Service
public class ReviewService {

    @Autowired // this does the dependency injection automatically through the annotation
    private ReviewRepository reviewRepository;

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

}
