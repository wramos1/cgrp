package cgrp.car_reservation.car_reservation.review;

import cgrp.car_reservation.car_reservation.user.User;
import cgrp.car_reservation.car_reservation.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired(required = true)
    private ReviewService reviewService;

    @Autowired // this will cause the auto injection dependcy
    private UserService userService;


/*    @PostMapping("/newreview")
    public Review createNewReview(@RequestBody Review newReview)
    {
        reviewService.createReview(newReview);

        return newReview;
    }

    //
    @PostMapping("/writereview")
    public Review writeReview(@RequestBody ReviewDTO reviewDTO)
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName(); // this gets the current logged in user, username

        User currentUser = userService.getUserByUsername(username);


        return reviewService.writeReview(reviewDTO, currentUser);
    }*/

    // this is to write a review to a specific vehicle which is specified by its customVehicleID attribute
    @PostMapping("/leavereview")
    public Review leaveReview(@RequestBody ReviewDTO reviewDTO) // this is going to get the review DTO object and go from there
    {
        return reviewService.leaveReview(reviewDTO);
    }

   /* @GetMapping("/getMyReviews/{lastName}")
    public List<Review> getReviews(@PathVariable String lastName)
    {
        return reviewService.userSpecificReviews(lastName);
    }*/

    @GetMapping("/hello")
    private String hello(){
        return "Hello";
    }

}
