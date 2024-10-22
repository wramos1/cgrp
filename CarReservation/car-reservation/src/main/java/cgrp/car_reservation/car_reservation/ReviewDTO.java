package cgrp.car_reservation.car_reservation;

// this will be a temporary data object that is used to transfer the data from front end to back end
public class ReviewDTO {

    private double reviewRating;

    private String reviewBody;

    public ReviewDTO(double reviewRating, String reviewBody) {
        this.reviewRating = reviewRating;
        this.reviewBody = reviewBody;
    }

    public double getReviewRating() {
        return reviewRating;
    }

    public void setReviewRating(double reviewRating) {
        this.reviewRating = reviewRating;
    }

    public String getReviewBody() {
        return reviewBody;
    }

    public void setReviewBody(String reviewBody) {
        this.reviewBody = reviewBody;
    }
}
