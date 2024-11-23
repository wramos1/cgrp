package cgrp.car_reservation.car_reservation.reservation;

import cgrp.car_reservation.car_reservation.user.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Class Name: ReservationRepository
 * Date of Code: October 7, 2024
 * Programmer's Name: Alberto S
 *
 * Description: Interface for accessing MongoDB repository for Reservations
 *
 * Important Functions:
 *  -findByUser: query database by User for specific reservation
 *  -findByCustomReservationID: query database for specific reservation matching reservation id
 *
 * Data Structures: N/A
 *
 * Algorithms: N/A
 *
 */
public interface ReservationRepository extends MongoRepository<Reservation, ObjectId> {
    Reservation findByUser(User user);

    public Reservation findByCustomReservationID(String customReservationID);

}
