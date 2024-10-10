package cgrp.car_reservation.car_reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


}
