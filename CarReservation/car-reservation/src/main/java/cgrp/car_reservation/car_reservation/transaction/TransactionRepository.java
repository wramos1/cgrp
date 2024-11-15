package cgrp.car_reservation.car_reservation.transaction;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<Transaction, ObjectId> {
}
