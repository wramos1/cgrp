package cgrp.car_reservation.car_reservation.user;
import org.bson.types.ObjectId;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Class Name: UserRepository<br>
 * Date of Code: October 7, 2024<br>
 * Programmer's Name: Alberto S<br>
 *
 * Description: Provides an interface to MongoDB database to access User objects<br>
 *
 * Important Functions:<br>
 * -findByUsername: query database for user based on given username<br>
 * -findByEmail: query database for user based on given email<br>
 * -existsByEmail: return true if user with given email exists in the database<br>
 *
 */
public interface UserRepository extends MongoRepository <User, ObjectId>{
    //Query method to find a user by username

    /**
     * Returns query of database by user's username<br>
     *
     * @param username username to query with<br>
     * @return user with that matching username<br>
     */
    User findByUsername(String username);

    /**
     * Returns list of user's with that email<br>
     * @param email email of user<br>
     * @return list of users with that email<br>
     */
    List<User> findByEmail(String email);

    /**
     * Returns boolean with a user with that email exists<br>
     * @param email email of the user<br>
     * @return true if user with that email address exists<br>
     */
    boolean existsByEmail(String email);


}
