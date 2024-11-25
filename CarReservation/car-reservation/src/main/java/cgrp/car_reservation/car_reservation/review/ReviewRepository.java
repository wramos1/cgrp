package cgrp.car_reservation.car_reservation.review;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Class Name: ReviewRepository<br>
 * Date of Code: October 19, 2024<br>
 * Programmer's Name: Arthur<br>
 *
 * Description: Interface for accessing the MongoDB repository for Reviews<br>
 *
 * Important Functions:<br>
 *  -findByCustomReviewID: query the database for review with specified id which is passed as a parameter, and returns corresponding review<br>
 *
 * Data Structures: N/A<br>
 *
 * Algorithms: N/A<br>
 *
 */
public interface ReviewRepository extends MongoRepository<Review, ObjectId> {


    public Review findByCustomReviewID(String customReviewID); // will query the db for the review with this custom reviewID

    List<Review> findByReviewLeaverUsername(String username);
}
