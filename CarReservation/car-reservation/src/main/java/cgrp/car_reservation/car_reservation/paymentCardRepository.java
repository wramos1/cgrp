package cgrp.car_reservation.car_reservation;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface paymentCardRepository extends MongoRepository<paymentCard, String> {

    public paymentCard findByNameOnCard(String nameOnCard); 

}
