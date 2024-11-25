package cgrp.car_reservation.car_reservation.review;

import cgrp.car_reservation.car_reservation.user.User;
import cgrp.car_reservation.car_reservation.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

/**
 * Class Name: ReviewController<br>
 * Date of Code: October 19, 2024<br>
 * Programmer's Name: Arthur
 *
 * Description: Provides the endpoints for front end for all things in regard to review.<br>
 *
 * Important Functions:<br>
 * -leaveReview: passed in a review data transfer object from the front end and creates a review in the database<br>
 *
 * Data Structures: N/A<br>
 *
 * Algorithms: N/A<br>
 *
 */

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired(required = true)
    private ReviewService reviewService;

    @Autowired // this will cause the auto injection dependcy
    private UserService userService;

    // this is to write a review to a specific vehicle which is specified by its customVehicleID attribute
    @PostMapping("/leavereview")
    public Review leaveReview(@RequestBody ReviewDTO reviewDTO) // this is going to get the review DTO object and go from there
    {
        return reviewService.leaveReview(reviewDTO);
    }

    // returns an array of reviews that a user has left
    //takes no parameters, just requires that a user is
    //logged in
    @GetMapping("/getMyReviews")
    public List<Review> getReviews( @AuthenticationPrincipal UserDetails userDetails)
    {
        return reviewService.userSpecificReviews(userDetails.getUsername());
    }

}
