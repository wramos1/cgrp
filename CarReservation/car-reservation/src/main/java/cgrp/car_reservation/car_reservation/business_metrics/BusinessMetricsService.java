package cgrp.car_reservation.car_reservation.business_metrics;

import cgrp.car_reservation.car_reservation.reservation.Reservation;
import cgrp.car_reservation.car_reservation.review.Review;
import cgrp.car_reservation.car_reservation.review.ReviewRepository;
import cgrp.car_reservation.car_reservation.vehicle.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Module Name: BusinessMetricsService.java<br>
 *
 * Date of code: 11/13/2024<br>
 *
 * Programmers Name: Arthur<br>
 *
 * Description: Service class with logic for calculating
 * business stats and info<br>
 *
 * Functions:<br>
 *  -isLowReview(): tests if review is low<br>
 *  -addPotentialLowReview(): adds review to array if low<br>
 *  -getCurrentMetrics(): returns all business metric entities<br>
 *  -addNewVehicleReservation(): adds reservation data
 *      to business metric object<br>
 *  -cancelledVehicleReservation(): updates business metric
 *      object if reservation is cancelled<br>
 *  -modifiedVehicleReservation(): updates business metric
 *      object if reservation is modified<br>
 *
 * Datastructures: N/A<br>
 *
 */

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

    // will add the vehicle that is being currently reserved to the list of vehicles that are currently being reserved in order for a manager to see it
    public void addNewVehicleReservation(Reservation reservation)
    {
        List<BusinessMetrics> businessMetrics = businessMetricsRepository.findAll();

        if(businessMetrics.isEmpty() == false) // this means that it has the business metric we want
        {
            int currentLifetimRentals = businessMetrics.get(0).getLifetimeNumRentals();
            double totalRentalRevenue = businessMetrics.get(0).getTotalRentalRevenue();

            currentLifetimRentals++;


            businessMetrics.get(0).setLifetimeNumRentals(currentLifetimRentals); // increments the lifetime number of rentals

            totalRentalRevenue += reservation.getChargeAmount(); // will add to the total rental revenue

            businessMetrics.get(0).setTotalRentalRevenue(totalRentalRevenue);

            businessMetrics.get(0).addReservedVehicles(reservation.getVehicle());

            businessMetricsRepository.save(businessMetrics.get(0));
        }

    }


    // will properly update the business metrics to indicate that the reservation has been cancelled
    public void cancelledVehicleReservation(Reservation cancelledReservation)
    {
        List<BusinessMetrics> businessMetrics = businessMetricsRepository.findAll();

        if(businessMetrics.isEmpty() == false)
        {
            businessMetrics.get(0).removeReservedVehicle(cancelledReservation.getVehicle());

            double preCancelTotalRevenue = businessMetrics.get(0).getTotalRentalRevenue();
            int preCancelLifetimeRentals = businessMetrics.get(0).getLifetimeNumRentals();

            preCancelTotalRevenue -= cancelledReservation.getChargeAmount(); // subtracts the charge amount from the total revenue since it was cancelled it would not be "charged"
            preCancelLifetimeRentals--;

            businessMetrics.get(0).setTotalRentalRevenue(preCancelTotalRevenue); // updates the revenue from the cancellation
            businessMetrics.get(0).setLifetimeNumRentals(preCancelLifetimeRentals);

            businessMetricsRepository.save(businessMetrics.get(0));


        }
    }

    // will modify the total lifetime revenue in the business metrics in order for it
    public void modifiedVehicleReservation(double oldReservationCharge, double modifiedReservationCharge)
    {
        List<BusinessMetrics> businessMetrics = businessMetricsRepository.findAll();

        if(businessMetrics.isEmpty() == false)
        {
            businessMetrics.get(0).setTotalRentalRevenue(businessMetrics.get(0).getTotalRentalRevenue() - oldReservationCharge); // subtracts the older reservation amount from the total lifetime revenue

            businessMetrics.get(0).setTotalRentalRevenue(businessMetrics.get(0).getTotalRentalRevenue()+modifiedReservationCharge); // adds the new modified charge

            businessMetricsRepository.save(businessMetrics.get(0));
        }
    }

    public void checkedBackInVehicleReservation(Vehicle currentReservedVehicle)
    {
        List<BusinessMetrics> businessMetrics = businessMetricsRepository.findAll();

        if(businessMetrics.isEmpty() == false)
        {
            int size = businessMetrics.get(0).getCurrentlyRentedVehicles().size();

            for(int i = 0; i < size; i++)
            {
                if(currentReservedVehicle.equals(businessMetrics.get(0).getCurrentlyRentedVehicles().get(i))) // if the reservation ids are the same, then it will be considered an equal vehicle, so it should be removed
                {
                    businessMetrics.get(0).getCurrentlyRentedVehicles().remove(i); // will remove that reservation at that index
                    break;
                }
            }

            businessMetricsRepository.save(businessMetrics.get(0));

        }

    }


}
