package cgrp.car_reservation.car_reservation.business_metrics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
