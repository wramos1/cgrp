package cgrp.car_reservation.car_reservation;

public class OurDate {

    private String month;
    private String day;
    private String year;

    public OurDate(String year, String day, String month) {
        this.year = year;
        this.day = day;
        this.month = month;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
