package cgrp.car_reservation.car_reservation;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"cgrp.car_reservation.car_reservation"})
public class CarReservationApplication {
	private static final Logger logger = LoggerFactory.getLogger(CarReservationApplication.class);

	public static void main(String[] args) {
		logger.info("Application starting...");
		SpringApplication.run(CarReservationApplication.class, args);
	}
}
