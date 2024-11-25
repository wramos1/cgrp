package cgrp.car_reservation.car_reservation.reservation;

import cgrp.car_reservation.car_reservation.user.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Class Name: ReservationRepository<br>
 * Date of Code: October 7, 2024<br>
 * Programmer's Name: Alberto S<br>
 *
 * Description: Interface for accessing MongoDB repository for Reservations<br>
 *
 * Important Functions:<br>
 *  -findByUser: query database by User for specific reservation<br>
 *  -findByCustomReservationID: query database for specific reservation matching reservation id<br>
 *
 * Data Structures: N/A<br>
 *
 * Algorithms: N/A<br>
 *
 */
public interface ReservationRepository extends MongoRepository<Reservation, ObjectId> {
    List<Reservation> findByUser(User user);

    public Reservation findByCustomReservationID(String customReservationID);

}
