package cgrp.car_reservation.car_reservation.reservation;

import cgrp.car_reservation.car_reservation.user.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Class Name: ReservationRepository
 * Date of Code:
 * Programmer's Name:
 *
 * Description: Interface for accessing MongoDB repository for Reservations
 *
 */
public interface ReservationRepository extends MongoRepository<Reservation, ObjectId> {
    Reservation findByUser(User user);

    public Reservation findByCustomReservationID(String customReservationID);

}
