package cgrp.car_reservation.car_reservation;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

// repository accesses the database and is the database layer
public interface paymentCardRepository extends MongoRepository<paymentCard, ObjectId> {

    public paymentCard findByNameOnCard(String nameOnCard); 

}
