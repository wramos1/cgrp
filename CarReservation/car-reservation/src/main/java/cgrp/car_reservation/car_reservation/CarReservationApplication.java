package cgrp.car_reservation.car_reservation;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
		"cgrp.car_reservation.car_reservation.reservation",
		"cgrp.car_reservation.car_reservation.feature",
		"cgrp.car_reservation.car_reservation.user",
		"cgrp.car_reservation.car_reservation.vehicle",
		"cgrp.car_reservation.car_reservation.review",
		"cgrp.car_reservation.car_reservation.payment_card",
		"cgrp.car_reservation.car_reservation.web_security",
		"cgrp.car_reservation.car_reservation.managerial"
})
public class CarReservationApplication {
	private static final Logger logger = LoggerFactory.getLogger(CarReservationApplication.class);

	public static void main(String[] args) {
		logger.info("Application starting...");
		SpringApplication.run(CarReservationApplication.class, args);
	}
}
