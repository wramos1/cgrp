package cgrp.car_reservation.car_reservation.reservation;

import cgrp.car_reservation.car_reservation.user.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReservationRepository extends MongoRepository<Reservation, ObjectId> {
    List<Reservation> findByUser(User user);
}
