package cgrp.car_reservation.car_reservation.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emailtest")
public class EmailController {


    // this will send the email
    @Autowired
    private EmailSenderService emailSenderService;

    @PostMapping("/send")
    public void sendEmailTest(@RequestBody Email email)
    {
        emailSenderService.sendVerificationEmail(email);
    }


}
