package cgrp.car_reservation.car_reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

// this service class will be the one that sends emails out to the user to confirm something they have done
@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendVerificationEmail(Email email)
    {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("cgrpventures@gmail.com");
        message.setTo(email.getEmailReceiver());
        message.setText(email.getEmailBody());
        message.setSubject(email.getEmailSubjectLine());

        mailSender.send(message);



    }

}
