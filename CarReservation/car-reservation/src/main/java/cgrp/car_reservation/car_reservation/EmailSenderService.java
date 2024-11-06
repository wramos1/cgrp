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

    // this will send an email to the logged in user; will be dummy for now
    public void reviewVerificationEmail(Review review)
    {
        SimpleMailMessage reviewEmail = new SimpleMailMessage();

        reviewEmail.setTo("buffband2020@gmail.com");
        reviewEmail.setFrom("cgrpventures@gmail.com");
        reviewEmail.setSubject("Confirming a review you left on the " + review.getVehicleReviewIsOn().getYear() + " " + review.getVehicleReviewIsOn().getMake() + " " + review.getVehicleReviewIsOn().getModel());
        reviewEmail.setText("Rating: " + review.getReviewRating() + "\nReview Body: " + review.getReviewBody());


        mailSender.send(reviewEmail);

    }

}
