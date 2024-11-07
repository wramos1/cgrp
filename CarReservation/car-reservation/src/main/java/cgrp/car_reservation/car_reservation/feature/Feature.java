package cgrp.car_reservation.car_reservation.feature;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "features")
public class Feature { // will be the features of the vehicle

    @Id
    private ObjectId featureID; // mongodb id object

    private String featureDescription; // this holds the text of what the feature  is

    public Feature(String featureDescription, ObjectId featureID) {
        this.featureDescription = featureDescription;
        this.featureID = featureID;
    }


    public ObjectId getFeatureID() {
        return featureID;
    }

    public void setFeatureID(ObjectId featureID) {
        this.featureID = featureID;
    }

    public String getFeatureDescription() {
        return featureDescription;
    }

    public void setFeatureDescription(String featureDescription) {
        this.featureDescription = featureDescription;
    }
}
