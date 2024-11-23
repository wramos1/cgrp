package cgrp.car_reservation.car_reservation.feature;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

/*
 * Module Name: FeatureRespository.java
 *
 * Date of code: 11/21/2024
 *
 * Programmers Name: Arthur
 *
 * Description: Repository class that gets
 * features from the database
 *
 * Functions: N/A
 *
 * Datastructures: N/A
 *
 *  */


public interface FeatureRepository extends MongoRepository<Feature, ObjectId> {

}
