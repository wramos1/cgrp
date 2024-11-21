package cgrp.car_reservation.car_reservation.review;

import cgrp.car_reservation.car_reservation.vehicle.Vehicle;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document(collection = "reviews") // specifies which colleciton the document will be stored in, in the db
public class Review {

    @Id
    private ObjectId reviewID;

    private String customReviewID; // this will be the custom review ID which is a string so we can access it from the code and from frontend unlike the mongodb id which is treated like a function

    private double reviewRating;

    private String reviewBody;


    private String reviewLeaverUsername;

    @JsonBackReference // this will ommit this from
    @DocumentReference // allows the database schema of the review to refrence this particular vehicle and this annotation allows the compilier and the mongodb driver to do that automatically
    private Vehicle vehicleReviewIsOn;

    public Review(String customReviewID, double reviewRating, String reviewBody, String reviewLeaverUsername, Vehicle vehicleReviewIsOn) {
        this.customReviewID = customReviewID;
        this.reviewRating = reviewRating;
        this.reviewBody = reviewBody;
        this.reviewLeaverUsername = reviewLeaverUsername;
        this.vehicleReviewIsOn = vehicleReviewIsOn;
    }

    public Review()
    {

    }

    public String getReviewLeaverUsername() {
        return reviewLeaverUsername;
    }

    public void setReviewLeaverUsername(String reviewLeaverUsername) {
        this.reviewLeaverUsername = reviewLeaverUsername;
    }


    public ObjectId getReviewID() {
        return reviewID;
    }

    public void setReviewID(ObjectId reviewID) {
        this.reviewID = reviewID;
    }

    public Vehicle getVehicleReviewIsOn() {
        return vehicleReviewIsOn;
    }

    public void setVehicleReviewIsOn(Vehicle vehicleReviewIsOn) {
        this.vehicleReviewIsOn = vehicleReviewIsOn;
    }



    public String getReviewBody() {
        return reviewBody;
    }

    public void setReviewBody(String reviewBody) {
        this.reviewBody = reviewBody;
    }

    public double getReviewRating() {
        return reviewRating;
    }

    public void setReviewRating(double reviewRating) {
        this.reviewRating = reviewRating;
    }

    public String getCustomReviewID() {
        return customReviewID;
    }

    public void setCustomReviewID(String customReviewID) {
        this.customReviewID = customReviewID;
    }
}
