package cgrp.car_reservation.car_reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/business")
public class BusinessMetricsController {

    @Autowired
    private BusinessMetricsService businessMetricsService;

    @PostMapping("/newmetric")
    public void createMetric(@RequestBody BusinessMetricsDTO businessMetrics)
    {
        businessMetricsService.createNewBusinessMetrics(businessMetrics);
    }

}
