package cgrp.car_reservation.car_reservation;

import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

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
    public void reviewVerificationEmail(Review review, User reviewLeaver)
    {
        SimpleMailMessage reviewEmail = new SimpleMailMessage();

        //reviewEmail.setTo(reviewLeaver.getEmail());
        reviewEmail.setTo("buffband2020@gmail.com");
        reviewEmail.setFrom("cgrpventures@gmail.com");
        reviewEmail.setSubject("Confirming a review you left on the " + review.getVehicleReviewIsOn().getYear() + " " + review.getVehicleReviewIsOn().getMake() + " " + review.getVehicleReviewIsOn().getModel());
        reviewEmail.setText("Hi " + reviewLeaver.getUsername() + ", \n Thank You for your review, it really means a lot! Below is a confirmation of the review you left. Thank You, CGRP\n\n" + "Rating: "  + review.getReviewRating() + "\nReview Body: " + review.getReviewBody());


        mailSender.send(reviewEmail);

    }

    public void reviewVerificationEmailWithAttachement(Review review, User reviewLeaver)
    {
        try {

            MimeMessage reviewEmail = mailSender.createMimeMessage();
            MimeMessageHelper reviewEmailHelper = new MimeMessageHelper(reviewEmail, true);

            reviewEmailHelper.setFrom("cgrpventures@gmail.com");
            reviewEmailHelper.setTo("buffband2020@gmail.com");
            reviewEmail.setSubject("Confirming a review you left on the " + review.getVehicleReviewIsOn().getYear() + " " + review.getVehicleReviewIsOn().getMake() + " " + review.getVehicleReviewIsOn().getModel());
            reviewEmailHelper.setText("Hi " + reviewLeaver.getUsername() + ", \n Thank You for your review, it really means a lot! Below is a confirmation of the review you left. Thank You, CGRP\n\n" + "Rating: "  + review.getReviewRating() + "\nReview Body: " + review.getReviewBody());

            reviewEmailHelper.addAttachment("2025.png", new File("C:\\Users\\artla\\Downloads\\2025.png"));

            mailSender.send(reviewEmail);

        }
        catch(Exception e)
        {
            e.getMessage();
        }
    }

    // function/method overloading
    public void reviewVerificationEmailWithAttachement(Review review, User reviewLeaver, boolean lowRatedReview)
    {
        try {

            MimeMessage reviewEmail = mailSender.createMimeMessage();
            MimeMessageHelper reviewEmailHelper = new MimeMessageHelper(reviewEmail, true);

            reviewEmailHelper.setFrom("cgrpventures@gmail.com");
            reviewEmailHelper.setTo("buffband2020@gmail.com");
            reviewEmail.setSubject("Confirming the review you left on the " + review.getVehicleReviewIsOn().getYear() + " " + review.getVehicleReviewIsOn().getMake() + " " + review.getVehicleReviewIsOn().getModel());

            if(lowRatedReview)
            {
                reviewEmailHelper.setText("Hi " + reviewLeaver.getUsername() + ", \n\nThank you for your review, it really means a lot! We are sorry to hear that you were not completely satisfied with your rental and we look forward to addressing your concerns. Thank You, CGRP\n\n" + "Rating: "  + review.getReviewRating() + "\nReview Body: " + review.getReviewBody());
            }
            else {
                reviewEmailHelper.setText("Hi " + reviewLeaver.getUsername() + ", \n\nThank You for your review, it really means a lot! Below is a confirmation of the review you left. Thank You, CGRP\n\n" + "Rating: "  + review.getReviewRating() + "\nReview Body: " + review.getReviewBody());
            }

            reviewEmailHelper.addAttachment("2025.png", new File("C:\\Users\\artla\\Downloads\\2025.png"));

            mailSender.send(reviewEmail);

        }
        catch(Exception e)
        {
            e.getMessage();
        }
    }



}
