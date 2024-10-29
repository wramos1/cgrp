package cgrp.car_reservation.car_reservation;

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

   // @DocumentReference
    private User reviewLeaver;

    @DocumentReference // allows the database schema of the review to refrence this particular vehicle and this annotation allows the compilier and the mongodb driver to do that automatically
    private Vehicle vehicleReviewIsOn;

    public Review(double reviewRating, String reviewBody, User reviewLeaver, Vehicle vehicleReviewIsOn, ObjectId reviewID) {
        this.reviewRating = reviewRating;
        this.reviewBody = reviewBody;
        this.reviewLeaver = reviewLeaver;
        this.vehicleReviewIsOn = vehicleReviewIsOn;
        this.reviewID = reviewID;
    }
    public Review(double reviewRating, String reviewBody, User reviewLeaver, Vehicle vehicleReviewIsOn) {
        this.reviewRating = reviewRating;
        this.reviewBody = reviewBody;
        this.reviewLeaver = reviewLeaver;
        this.vehicleReviewIsOn = vehicleReviewIsOn;
    }

    public Review(double reviewRating, String reviewBody, User reviewLeaver) {
        this.reviewRating = reviewRating;
        this.reviewBody = reviewBody;
        this.reviewLeaver = reviewLeaver;
    }

    public Review(String customReviewID, double reviewRating, String reviewBody, User reviewLeaver, Vehicle vehicleReviewIsOn) {
        this.customReviewID = customReviewID;
        this.reviewRating = reviewRating;
        this.reviewBody = reviewBody;
        this.reviewLeaver = reviewLeaver;
        this.vehicleReviewIsOn = vehicleReviewIsOn;
    }

    public Review()
    {

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

    public User getReviewLeaver() {
        return reviewLeaver;
    }

    public void setReviewLeaver(User reviewLeaver) {
        this.reviewLeaver = reviewLeaver;
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
