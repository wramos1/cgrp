package cgrp.car_reservation.car_reservation.review;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Class Name: ReviewRepository
 * Date of Code: October 19, 2024
 * Programmer's Name: Arthur
 *
 * Description: Interface for accessing the MongoDB repository for Reviews
 *
 * Important Functions:
 *  -findByCustomReviewID: query the database for review with specified id which is passed as a parameter, and returns corresponding review
 *
 * Data Structures: N/A
 *
 * Algorithms: N/A
 *
 */
public interface ReviewRepository extends MongoRepository<Review, ObjectId> {


    public Review findByCustomReviewID(String customReviewID); // will query the db for the review with this custom reviewID

}
