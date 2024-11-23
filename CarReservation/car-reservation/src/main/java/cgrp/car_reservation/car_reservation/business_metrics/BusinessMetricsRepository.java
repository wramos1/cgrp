package cgrp.car_reservation.car_reservation.business_metrics;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Module Name: BusinessMetricsReposity.java
 *
 * Date of code: 11/8/2024
 *
 * Programmers Name: Arthur
 *
 * Description: Repository class that accesses BusinessMetric
 * objects in the database
 *
 * Functions: N/A
 *
 * Datastructures: N/A
 *
 *  */

public interface BusinessMetricsRepository extends MongoRepository<BusinessMetrics, ObjectId> {


}
