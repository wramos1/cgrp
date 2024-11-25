package cgrp.car_reservation.car_reservation.review;

/**
 * Class Name: ReviewDTO<br>
 * Date of Code: October 19, 2024<br>
 * Programmer's Name: Arthur<br>
 *
 * Description: Provides a data transfer object to transfer necessary review data from front end to back end to create a review<br>
 *
 * Important Functions: Getters and Setters<br>
 *
 * Data Structures: N/A<br>
 *
 * Algorithms: N/A<br>
 *
 */
public class ReviewDTO {

    // String customReviewID; // this will be a custom ID that we can use to lookup this exact particular review witout using the object id that is mongodb specific

    private int reviewRating;

    private String reviewBody;

    private String customVehicleID; // this is the vehicle that the review is going to be left on

    public ReviewDTO()
    {

    }

    public ReviewDTO(int reviewRating, String reviewBody, String customVehicleID) {

        this.reviewRating = reviewRating;
        this.reviewBody = reviewBody;
        this.customVehicleID = customVehicleID;
    }

    public ReviewDTO(int reviewRating, String reviewBody) {
        this.reviewRating = reviewRating;
        this.reviewBody = reviewBody;
    }

    public double getReviewRating() {
        return reviewRating;
    }

    public void setReviewRating(int reviewRating) {
        this.reviewRating = reviewRating;
    }

    public String getReviewBody() {
        return reviewBody;
    }

    public void setReviewBody(String reviewBody) {
        this.reviewBody = reviewBody;
    }

    public String getCustomVehicleID() {
        return customVehicleID;
    }

    public void setCustomVehicleID(String customVehicleID) {
        this.customVehicleID = customVehicleID;
    }


}
