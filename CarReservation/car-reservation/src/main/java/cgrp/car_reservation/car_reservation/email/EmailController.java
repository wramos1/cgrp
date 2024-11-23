package cgrp.car_reservation.car_reservation.email;

import cgrp.car_reservation.car_reservation.email.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Module Name: EmailController.java
 *
 * Date of code: 11/5/2024
 *
 * Programmers Name: Arthur
 *
 * Description: Controller class with endpoints that
 * send emails
 *
 * Functions: N/A
 *
 * Datastructures: N/A
 *
 *  */

@RestController
@RequestMapping("/emailtest")
public class EmailController {

    // this will send the email
    @Autowired
    private EmailSenderService emailSenderService;

    @PostMapping("/send")
    public void sendEmailTest(@RequestBody Email email) {
        emailSenderService.sendVerificationEmail(email);
    }

}
