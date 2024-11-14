package cgrp.car_reservation.car_reservation.business_metrics;

import cgrp.car_reservation.car_reservation.review.Review;
import cgrp.car_reservation.car_reservation.review.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessMetricsService {

    @Autowired
    private BusinessMetricsRepository businessMetricsRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    public void createNewBusinessMetrics(BusinessMetricsDTO businessMetricsDTO) {
        BusinessMetrics newMetrics = new BusinessMetrics(
                businessMetricsDTO.getNumVehiclesCurrentlyRented(),
                businessMetricsDTO.getTotalRentalRevenue(),
                businessMetricsDTO.getLifetimeNumRentals(),
                businessMetricsDTO.getCurrentlyRentedVehicles(),
                businessMetricsDTO.getLowRatedReviewsToAddress()
        );
        businessMetricsRepository.save(newMetrics); // saves the metrics to the db
    }

    // checks if the review is considered to be a low rating
    public boolean isLowReview(Review review) {
        return review.getReviewRating() < 3.0;
    }

    // will only add this review to a business metric to see if anything needs to be addressed due to a low review rating
    public void addPotentialLowReview(Review review) {
        List<BusinessMetrics> businessMetrics = businessMetricsRepository.findAll();

        if (!businessMetrics.isEmpty() && isLowReview(review)) {
            businessMetrics.get(0).addLowRatedReview(review); // use get(0) to retrieve the first element

            // print out all of the reviews that are in the business metrics right now
            for (Review nowReview : businessMetrics.get(0).getLowRatedReviewsToAddress()) {
                System.out.println(nowReview.getReviewID());
            }

            businessMetricsRepository.save(businessMetrics.get(0)); // it will now update the business metrics object in the database
            System.out.println("low review added to " + businessMetrics.get(0).getBusinessMetricID());
        }
    }

    public BusinessMetrics getCurrentMetrics() {
        List<BusinessMetrics> businessMetrics = businessMetricsRepository.findAll();
        return businessMetrics.isEmpty() ? null : businessMetrics.get(0);
    }
}
