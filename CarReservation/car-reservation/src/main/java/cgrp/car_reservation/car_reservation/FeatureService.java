package cgrp.car_reservation.car_reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeatureService {

        @Autowired // allows for automatic dependency injection
        private FeatureRepository featureRepository;

        public Feature createNewFeature(Feature feature)
        {
            featureRepository.save(feature);
            return feature;
        }


}
