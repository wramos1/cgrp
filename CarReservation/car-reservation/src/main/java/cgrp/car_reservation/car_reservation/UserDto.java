package cgrp.car_reservation.car_reservation;

import lombok.Getter;

@Getter
public class UserDto {
    private final String username;
    private final String email;
    private final String password;
    private final String confirmPassword;
    public UserDto(String username, String email, String password, String confirmPassword){
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }
}
