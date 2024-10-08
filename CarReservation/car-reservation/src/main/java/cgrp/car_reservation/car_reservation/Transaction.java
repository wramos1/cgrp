package cgrp.car_reservation.car_reservation;


import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "transactions")
public class Transaction {

    @Id
    private ObjectId transactionID;

    private User transactionUser;
    
}
