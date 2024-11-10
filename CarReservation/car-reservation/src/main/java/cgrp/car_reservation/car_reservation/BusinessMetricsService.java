package cgrp.car_reservation.car_reservation;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessMetricsService {

    @Autowired
    private BusinessMetricsRepository businessMetricsRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    public void createNewBusinessMetrics(BusinessMetricsDTO businessMetricsDTO)
    {
        BusinessMetrics newMetrics = new BusinessMetrics(businessMetricsDTO.getNumVehiclesCurrentlyRented(), businessMetricsDTO.getTotalRentalRevenue(), businessMetricsDTO.getLifetimeNumRentals(), businessMetricsDTO.getCurrentlyRentedVehicles(), businessMetricsDTO.getLowRatedReviewsToAddress());

        businessMetricsRepository.save(newMetrics); // saves the metrics to the db
    }

    // checks if the review is considered to be a low rating
    public boolean isLowReview(Review review)
    {
        if(review.getReviewRating() < 3.0)
            return true;
        else
            return false;
    }

    // will only add this review to a business metric to see if anything needs to be addressed due to a low review rating
    public void addPotentialLowReview(Review review)
    {
        List<BusinessMetrics> businessMetrics = businessMetricsRepository.findAll();

        if(isLowReview(review))
        {
            businessMetrics.getFirst().addLowRatedReview(review); // something is wrong with this method that is not adding in the new review

            //print out all of the reviews that are in the business metrics right nwow
            for(Review nowReview : businessMetrics.getFirst().getLowRatedReviewsToAddress())
            {
                System.out.println(nowReview.getReviewID());
            }

            businessMetricsRepository.save(businessMetrics.getFirst()); // it will now update the business metrics object in the database

            System.out.println("low review added to " + businessMetrics.getFirst().getBusinessMetricID());

        }
        else
            return;

    }

    public BusinessMetrics getCurrenMetrics()
    {
        return businessMetricsRepository.findAll().getFirst();
    }




}
