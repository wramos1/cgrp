package cgrp.car_reservation.car_reservation.review;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReviewRepository extends MongoRepository<Review, ObjectId> {


    public Review findByCustomReviewID(String customReviewID); // will query the db for the review with this custom reviewID

}
