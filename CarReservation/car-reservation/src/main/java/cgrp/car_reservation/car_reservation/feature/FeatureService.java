package cgrp.car_reservation.car_reservation.feature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Module Name: Email.java<br>
 *
 * Date of code: 10/9/2024<br>
 *
 * Programmers Name: Arthur<br>
 *
 * Description: Service class with logic for features<br>
 *
 * Functions:<br>
 *  -createNewFeature(): creates a new feature, takes a feature object<br>
 *  -getAllFeatures(): returns all feature objects<br>
 *
 * Datastructures: N/A<br>
 *
 */


@Service
public class FeatureService {

        @Autowired // allows for automatic dependency injection
        private FeatureRepository featureRepository;

        public Feature createNewFeature(Feature feature)
        {
            featureRepository.save(feature);
            return feature;
        }

        public List<Feature> getAllFeatures()
        {
            return featureRepository.findAll();
        }


}
