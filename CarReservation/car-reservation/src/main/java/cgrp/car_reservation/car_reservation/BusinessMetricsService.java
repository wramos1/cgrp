package cgrp.car_reservation.car_reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessMetricsService {

    @Autowired
    private BusinessMetricsRepository businessMetricsRepository;

    public void createNewBusinessMetrics(BusinessMetricsDTO businessMetricsDTO)
    {
        BusinessMetrics newMetrics = new BusinessMetrics(businessMetricsDTO.getNumVehiclesCurrentlyRented(), businessMetricsDTO.getTotalRentalRevenue(), businessMetricsDTO.getLifetimeNumRentals(), businessMetricsDTO.getCurrentlyRentedVehicles(), businessMetricsDTO.getLowRatedReviewsToAddress());

        businessMetricsRepository.save(newMetrics); // saves the metrics to the db
    }


}
