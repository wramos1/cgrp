package cgrp.car_reservation.car_reservation.business_metrics;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Module Name: BusinessMetricsRepository.java<br>
 *
 * Date of code: 11/8/2024<br>
 *
 * Programmers Name: Arthur<br>
 *
 * Description: Repository class that accesses BusinessMetric
 * objects in the database<br>
 *
 * Functions: N/A<br>
 *
 * Datastructures: N/A<br>
 *
 *  */

public interface BusinessMetricsRepository extends MongoRepository<BusinessMetrics, ObjectId> {


}
