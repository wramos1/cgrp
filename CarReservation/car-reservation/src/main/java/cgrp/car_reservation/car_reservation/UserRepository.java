package cgrp.car_reservation.car_reservation;
import org.bson.types.ObjectId;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository <User, ObjectId>{
    //Query method to find a user by username
    User findByUsername(String username);

    List<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
