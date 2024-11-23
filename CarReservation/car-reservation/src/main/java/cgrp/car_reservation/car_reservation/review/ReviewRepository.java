package cgrp.car_reservation.car_reservation.review;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Class Name: ReviewRepository
 * Date of Code:
 * Programmer's Name:
 *
 * Description: Interface for accessing the MongoDB repository for Reviews
 *
 */
public interface ReviewRepository extends MongoRepository<Review, ObjectId> {


    public Review findByCustomReviewID(String customReviewID); // will query the db for the review with this custom reviewID

}
