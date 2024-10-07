package cgrp.car_reservation.car_reservation;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReservationRepository extends MongoRepository<Reservation, ObjectId> {
    Reservation findByUser(User user);
}
