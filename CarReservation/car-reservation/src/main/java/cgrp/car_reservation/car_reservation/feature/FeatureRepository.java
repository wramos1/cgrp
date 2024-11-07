package cgrp.car_reservation.car_reservation.feature;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FeatureRepository extends MongoRepository<Feature, ObjectId> {

}
