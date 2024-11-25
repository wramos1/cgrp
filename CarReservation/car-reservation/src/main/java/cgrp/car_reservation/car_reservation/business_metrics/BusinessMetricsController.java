package cgrp.car_reservation.car_reservation.business_metrics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Module Name: BusinessMetricsController.java<br>
 *
 * Date of code: 11/8/2024<br>
 *
 * Programmers Name: Arthur<br>
 *
 * Description: Controller class with endpoints that<br>
 * return useful business info and stats<br>
 *
 * Functions: N/A<br>
 *
 * Datastructures: N/A<br>
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
