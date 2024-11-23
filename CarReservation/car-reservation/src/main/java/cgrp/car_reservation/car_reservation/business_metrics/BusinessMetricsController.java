package cgrp.car_reservation.car_reservation.business_metrics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Module Name: BusinessMetricsController.java
 *
 * Date of code: 11/8/2024
 *
 * Programmers Name: Arthur
 *
 * Description: Controller class with endpoints that
 * return useful business info and stats
 *
 * Functions: N/A
 *
 * Datastructures: N/A
 *  */
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

    @GetMapping("/getcurrentmetrics")
    public BusinessMetrics getCurrentMetrics()
    {
        return businessMetricsService.getCurrentMetrics();
    }


}
