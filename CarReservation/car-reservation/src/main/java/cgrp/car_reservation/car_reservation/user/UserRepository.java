package cgrp.car_reservation.car_reservation.user;
import org.bson.types.ObjectId;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Class Name: UserRepository
 * Date of Code: October 7, 2024
 * Programmer's Name: Alberto S
 *
 * Description: Provides an interface to MongoDB database to access User objects
 *
 * Important Functions:
 * -findByUsername: query database for user based on given username
 * -findByEmail: query database for user based on given email
 * -existsByEmail: return true if user with given email exists in the database
 *
 */
public interface UserRepository extends MongoRepository <User, ObjectId>{
    //Query method to find a user by username

    /**
     * Returns query of database by user's username
     *
     * @param username username to query with
     * @return user with that matching username
     */
    User findByUsername(String username);

    /**
     * Returns list of user's with that email
     * @param email email of user
     * @return list of users with that email
     */
    List<User> findByEmail(String email);

    /**
     * Returns boolean with a user with that email exists
     * @param email email of the user
     * @return true if user with that email address exists
     */
    boolean existsByEmail(String email);


}
