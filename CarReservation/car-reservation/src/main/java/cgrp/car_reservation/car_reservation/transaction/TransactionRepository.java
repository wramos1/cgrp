package cgrp.car_reservation.car_reservation.transaction;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Class Name: TransactionRepository <br>
 * Date of Code: <br>
 * Programmer's Name: <br>
 *
 * Description: Provides interface to access the MongoDB database for Transaction objects <br>
 *
 */
public interface TransactionRepository extends MongoRepository<Transaction, ObjectId> {
}
