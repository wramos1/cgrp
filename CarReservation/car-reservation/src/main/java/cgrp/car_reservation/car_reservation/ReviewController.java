package cgrp.car_reservation.car_reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired(required = true)
    private ReviewService reviewService;


    @PostMapping("/newreview")
    public Review createNewReview(@RequestBody Review newReview)
    {
        reviewService.createReview(newReview);

        return newReview;
    }

    @GetMapping("/getMyReviews/{lastName}")
    public List<Review> getReviews(@PathVariable String lastName)
    {
        return reviewService.userSpecificReviews(lastName);
    }

    @GetMapping("/hello")
    private String hello(){
        return "Hello";
    }

}
