package cgrp.car_reservation.car_reservation.managerial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Month;

@RestController
@RequestMapping("/admin")
public class RevenueController {
    @Autowired
    private RevenueService revenueService;

    @GetMapping("/monthly-revenue")
    public double getMonthlyRevenue(){
        return revenueService.thisMonthRevenue();
    }

    @PostMapping("/monthly-revenue/{startMonth}/{endMonth}")
    public double getMonthRangeRevenue(@PathVariable Month startMonth, @PathVariable Month endMonth){
        return revenueService.specifiedMonthRangeRevenue(startMonth,endMonth);
    }
}
