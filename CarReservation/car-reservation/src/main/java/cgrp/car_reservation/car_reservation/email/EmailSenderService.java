package cgrp.car_reservation.car_reservation.email;

import cgrp.car_reservation.car_reservation.email.Email;
import cgrp.car_reservation.car_reservation.reservation.Reservation;
import cgrp.car_reservation.car_reservation.review.Review;
import cgrp.car_reservation.car_reservation.user.User;
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.task.TaskExecutionProperties;
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

    public void sendVerificationEmail(Email email) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("cgrpventures@gmail.com");
        message.setTo(email.getEmailReceiver());
        message.setText(email.getEmailBody());
        message.setSubject(email.getEmailSubjectLine());

        mailSender.send(message);

    }

    // this will send an email to the logged in user; will be dummy for now
    public void reviewVerificationEmail(Review review, User reviewLeaver) {
        SimpleMailMessage reviewEmail = new SimpleMailMessage();

        // reviewEmail.setTo(reviewLeaver.getEmail());
        reviewEmail.setTo("buffband2020@gmail.com");
        reviewEmail.setFrom("cgrpventures@gmail.com");
        reviewEmail.setSubject("Confirming a review you left on the " + review.getVehicleReviewIsOn().getYear() + " "
                + review.getVehicleReviewIsOn().getMake() + " " + review.getVehicleReviewIsOn().getModel());
        reviewEmail.setText("Hi " + reviewLeaver.getUsername()
                + ", \n Thank You for your review, it really means a lot! Below is a confirmation of the review you left. Thank You, CGRP\n\n"
                + "Rating: " + review.getReviewRating() + "\nReview Body: " + review.getReviewBody());

        mailSender.send(reviewEmail);

    }

    public void reviewVerificationEmailWithAttachement(Review review, User reviewLeaver) {
        try {

            MimeMessage reviewEmail = mailSender.createMimeMessage();
            MimeMessageHelper reviewEmailHelper = new MimeMessageHelper(reviewEmail, true);

            reviewEmailHelper.setFrom("cgrpventures@gmail.com");
            reviewEmailHelper.setTo("buffband2020@gmail.com");
            reviewEmail.setSubject("Confirming a review you left on the " + review.getVehicleReviewIsOn().getYear()
                    + " " + review.getVehicleReviewIsOn().getMake() + " " + review.getVehicleReviewIsOn().getModel());
            reviewEmailHelper.setText("Hi " + reviewLeaver.getUsername()
                    + ", \n Thank You for your review, it really means a lot! Below is a confirmation of the review you left. Thank You, CGRP\n\n"
                    + "Rating: " + review.getReviewRating() + "\nReview Body: " + review.getReviewBody());

            reviewEmailHelper.addAttachment("2025.png", new File("C:\\Users\\artla\\Downloads\\2025.png"));

            mailSender.send(reviewEmail);

        } catch (Exception e) {
            e.getMessage();
        }
    }

    // function/method overloading
    public void reviewVerificationEmailWithAttachement(Review review, User reviewLeaver, boolean lowRatedReview) {
        try {

            MimeMessage reviewEmail = mailSender.createMimeMessage();
            MimeMessageHelper reviewEmailHelper = new MimeMessageHelper(reviewEmail, true);

            reviewEmailHelper.setFrom("cgrpventures@gmail.com");
            reviewEmailHelper.setTo("buffband2020@gmail.com");
            reviewEmail.setSubject("Confirming the review you left on the " + review.getVehicleReviewIsOn().getYear()
                    + " " + review.getVehicleReviewIsOn().getMake() + " " + review.getVehicleReviewIsOn().getModel());

            if (lowRatedReview) {
                reviewEmailHelper.setText("Hi " + reviewLeaver.getUsername()
                        + ", \n\nThank you for your review, it really means a lot! We are sorry to hear that you were not completely satisfied with your rental and we look forward to addressing your concerns. Thank You, CGRP\n\n"
                        + "Rating: " + review.getReviewRating() + "\nReview Body: " + review.getReviewBody());
            } else {
                reviewEmailHelper.setText("Hi " + reviewLeaver.getUsername()
                        + ", \n\nThank You for your review, it really means a lot! Below is a confirmation of the review you left. Thank You, CGRP\n\n"
                        + "Rating: " + review.getReviewRating() + "\nReview Body: " + review.getReviewBody());
            }

            reviewEmailHelper.addAttachment("2025.png", new File("C:\\Users\\artla\\Downloads\\2025.png"));

            mailSender.send(reviewEmail);

        } catch (Exception e) {
            e.getMessage();
        }
    }


    // sends email whenever, a reservation is made
    public void reservationVerificationEmail(Reservation reservation)
    {
        SimpleMailMessage reservationEmail = new SimpleMailMessage();

        reservationEmail.setFrom("cgrpventures@gmail.com");
        reservationEmail.setTo("buffband2020@gmail.com");

        reservationEmail.setSubject("Confirming your reservation from " + reservation.getStartDate().toString() + " to " + reservation.getEndDate().toString());

        reservationEmail.setText("Hi " + reservation.getUser().getUsername() +
                 ", \n\nThank you for your business, we truly appreciate it. Below is a confirmation of your reservation: \n\n" + "Year: " +
               reservation.getVehicle().getYear() + "\nMake: " + reservation.getVehicle().getMake() + "\nModel: " + reservation.getVehicle().getModel() +
                "\nReservation Duration: " + reservation.getStartDate().toString() + " - " + reservation.getEndDate().toString() + "\n\nFrom CGRP, we wish you the very best in your travels!");

        mailSender.send(reservationEmail);


    }


    // sends email whenever a reservation is cancelled
    public void cancelReservationVerificationEmail(Reservation reservation)
    {
        SimpleMailMessage cancelReservationEmail = new SimpleMailMessage();

        cancelReservationEmail.setFrom("cgrpventures@gmail.com");
        cancelReservationEmail.setTo("buffband2020@gmail.com");

        cancelReservationEmail.setSubject("Confirming your reservation cancellation for " + reservation.getStartDate().toString() + " to " + reservation.getEndDate().toString());


        cancelReservationEmail.setText("Hi " + reservation.getUser().getUsername() + ", \n\nNo worries in your cancellation. At CGRP we take our customer's priorities seriously and look forward to earning your business in the future! Below is a confirmation of the reservation you cancelled: \n\n"
        + "Year: " + reservation.getVehicle().getYear() + "\nMake: " + reservation.getVehicle().getMake() + "\nModel: " + reservation.getVehicle().getModel() +
                 "\nReservation Duration: " + reservation.getStartDate().toString() + " - " + reservation.getEndDate().toString());

        mailSender.send(cancelReservationEmail);

    }

}
