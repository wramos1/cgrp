package cgrp.car_reservation.car_reservation;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.Date;

@Getter
public class ReservationDto {
    @Setter
    private ObjectId userId;
    private final ObjectId vehicleId;
    private final LocalDate startDate;
    private final LocalDate endDate;
    public ReservationDto(ObjectId userId, ObjectId vehicleId, LocalDate rentDate, LocalDate returnDate){
        this.userId = userId;
        this.vehicleId = vehicleId;
        this.startDate = rentDate;
        this.endDate = returnDate;
    }
}
