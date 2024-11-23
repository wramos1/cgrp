package cgrp.car_reservation.car_reservation.feature;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Module Name: FeatureRespository.java<br>
 *
 * Date of code: 10/9/2024<br>
 *
 * Programmers Name: Arthur<br>
 *
 * Description: Repository class that gets<br>
 * features from the database<br>
 *
 * Functions: N/A<br>
 *
 * Datastructures: N/A<br>
 *
 *  */


public interface FeatureRepository extends MongoRepository<Feature, ObjectId> {

}
