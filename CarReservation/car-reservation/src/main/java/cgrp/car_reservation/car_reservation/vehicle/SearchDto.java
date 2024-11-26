package cgrp.car_reservation.car_reservation.vehicle;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SearchDto {
    private String[] makes;

    private String[] types;

    private String[] keywords;



}
