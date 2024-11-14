package cgrp.car_reservation.car_reservation.business_metrics;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BusinessMetricsRepository extends MongoRepository<BusinessMetrics, ObjectId> {


}
