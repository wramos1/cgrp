package cgrp.car_reservation.car_reservation;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Review createReview(String reviewBody, String id) {
        Review review = reviewRepository.insert(new Review(reviewBody));

        mongoTemplate.update(Car.class)
                .matching(Criteria.where("carId").is(id))
                .apply(new Update().push("reviewIds").value(review))
                .first();

        return review;
    }
}
