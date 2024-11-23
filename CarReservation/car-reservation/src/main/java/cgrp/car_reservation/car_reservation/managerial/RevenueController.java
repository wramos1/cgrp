package cgrp.car_reservation.car_reservation.managerial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Month;

/**
 * Module Name: RevenueController.java<br>
 *
 * Date of code: 11/21/2024<br>
 *
 * Programmers Name: Alberto<br>
 *
 * Description: Controller class containing mappings for
 * revenue<br>
 *
 * Functions:N/A<br>
 *
 * Datastructures: N/A<br>
 */
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
