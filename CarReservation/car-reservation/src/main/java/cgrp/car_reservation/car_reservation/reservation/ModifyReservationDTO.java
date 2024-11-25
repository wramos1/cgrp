package cgrp.car_reservation.car_reservation.reservation;

import java.time.LocalDate;

/**
 * Class Name: Modify Reservation Data Transfer Object<br>
 * Date of Code: November 20, 2024<br>
 * Programmer's Name: Arthur<br>
 *
 * Description: Modify Reservation Data Transfer Object is used to send modification information pertaining to reservation from front end to back end.<br>
 *
 * Important Functions: Getters and Setters<br>
 *
 * Data Structures: LocalDate, which stores the date necessary to reservation modification<br>
 *
 * Algorithms: N/A<br>
 *
 */
public class ModifyReservationDTO {

    private String customReservationID;

    private LocalDate newStartDate;

    private LocalDate newEndDate;

    public ModifyReservationDTO()
    {

    }

    public ModifyReservationDTO(String customReservationID, LocalDate newStartDate, LocalDate newEndDate)
    {
        this.customReservationID = customReservationID;
        this.newStartDate = newStartDate;
        this.newEndDate = newEndDate;
    }

    public String getCustomReservationID() {
        return customReservationID;
    }

    public void setCustomReservationID(String customReservationID) {
        this.customReservationID = customReservationID;
    }

    public LocalDate getNewStartDate() {
        return newStartDate;
    }

    public void setNewStartDate(LocalDate newStartDate) {
        this.newStartDate = newStartDate;
    }

    public LocalDate getNewEndDate() {
        return newEndDate;
    }

    public void setNewEndDate(LocalDate newEndDate) {
        this.newEndDate = newEndDate;
    }
}
