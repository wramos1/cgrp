package cgrp.car_reservation.car_reservation;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "features")
public class Feature { // will be the features of the vehicle

    @Id
    private ObjectId featureID; // mongodb id object

    private String featureDescription; // this holds the text of what the feature  is

}
