package cgrp.car_reservation.car_reservation.transaction;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Class Name: TransactionRepository <br>
 * Date of Code: November 14, 2024<br>
 * Programmer's Name: Arthur<br>
 *
 * Description: Provides interface to access the MongoDB database for Transaction objects <br>
 *
 * Important Functions: Inherited Methods<br>
 *
 * Data Structures: N/A<br>
 *
 * Algorithms: N/A<br>
 *
 */
public interface TransactionRepository extends MongoRepository<Transaction, ObjectId> {
}
