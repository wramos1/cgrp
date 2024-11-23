package cgrp.car_reservation.car_reservation.feature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Module Name: FeatureController.java
 *
 * Date of code: 10/9/2024
 *
 * Programmers Name: Arthur
 *
 * Description: Controller class with endpoints to
 * create and features and get all features
 *
 * Functions: N/A
 *
 * Datastructures: N/A
 *
 */


@RestController
@RequestMapping("/features")
public class FeatureController {

    @Autowired
    private FeatureService featureService;

    @PostMapping("/newfeature")
    public Feature createNewFeature(@RequestBody Feature feature)
    {
        featureService.createNewFeature(feature);

        return feature;
    }

    @GetMapping("/allfeatures")
    public List<Feature> getAllFeatures()
    {
        return featureService.getAllFeatures();
    }

    public String message()
    {
        return "This is a test to see if it works";
    }



}
