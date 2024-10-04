package cgrp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cars")
public class CarController {

    @GetMapping
    public ResponseEntity<String> allCars() {
        return new ResponseEntity<String>("All cars", HttpStatus.OK);
    }
}
