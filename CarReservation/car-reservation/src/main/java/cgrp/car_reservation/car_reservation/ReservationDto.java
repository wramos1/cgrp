package cgrp.car_reservation.car_reservation;

import lombok.Getter;
import org.bson.types.ObjectId;
import java.util.Date;

@Getter
public class ReservationDto {
    private final ObjectId userId;
    private final ObjectId vehicleId;
    private final Date startDate;
    private final Date endDate;
    public ReservationDto(ObjectId userId, ObjectId vehicleId, Date rentDate, Date returnDate){
        this.userId = userId;
        this.vehicleId = vehicleId        ;
        this.startDate = rentDate;
        this.endDate = returnDate;
    }
}
