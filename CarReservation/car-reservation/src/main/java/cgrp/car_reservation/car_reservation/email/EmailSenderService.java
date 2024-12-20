package cgrp.car_reservation.car_reservation.email;

import cgrp.car_reservation.car_reservation.email.Email;
import cgrp.car_reservation.car_reservation.reservation.Reservation;
import cgrp.car_reservation.car_reservation.review.Review;
import cgrp.car_reservation.car_reservation.user.User;
import cgrp.car_reservation.car_reservation.user.UserService;
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
import java.text.Normalizer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 *
 * Module Name: EmailSenderService.java<br>
 *
 * Date of code: 11/5/2024<br>
 *
 * Programmers Name: Arthur<br>
 *
 * Description: Service class with logic for
 * sending emails<br>
 *
 * Functions:<br>
 *  -reservationVerificationEmail(): sends email to user
 *      when reservation is made<br>
 *
 * Datastructures: N/A<br>
 *
 */

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserService userService;

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
            reviewEmailHelper.setTo(reviewLeaver.getEmail());
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


           // reviewEmailHelper.addAttachment("2025.png", new File("C:\\Users\\artla\\Downloads\\2025.png"));

            mailSender.send(reviewEmail);

        } catch (Exception e) {
            e.getMessage();
        }
    }


    // sends email whenever, a reservation is made
    public void reservationVerificationEmail(Reservation reservation)
    {
        SimpleMailMessage reservationEmail = new SimpleMailMessage();
        User user = userService.getUserByUsername(reservation.getUsername());

        reservationEmail.setFrom("cgrpventures@gmail.com");
        reservationEmail.setTo(user.getEmail());

        reservationEmail.setSubject("Confirming your reservation from " + reservation.getStartDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)) + " to " + reservation.getEndDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)));

        reservationEmail.setText("Hi " + reservation.getUsername() +
                 ", \n\nThank you for your business, we truly appreciate it. Below is a confirmation of your reservation: \n\n" + "Year: " +
               reservation.getVehicle().getYear() + "\nMake: " + reservation.getVehicle().getMake() + "\nModel: " + reservation.getVehicle().getModel() +
                "\nReservation Duration: " + reservation.getStartDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)) + " - " + reservation.getEndDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)) +
                "\n\nHere is your reservation id, please keep this for when you return in order to check the vehicle back in: " + reservation.getCustomReservationID().toUpperCase() + "\n\nFrom CGRP, we wish you the very best in your travels!");


        mailSender.send(reservationEmail);


    }


    // sends email whenever a reservation is cancelled
    public void cancelReservationVerificationEmail(Reservation reservation)
    {
        SimpleMailMessage cancelReservationEmail = new SimpleMailMessage();
        User user = userService.getUserByUsername(reservation.getUsername());

        cancelReservationEmail.setFrom("cgrpventures@gmail.com");
        cancelReservationEmail.setTo(user.getEmail());

        cancelReservationEmail.setSubject("Confirming your reservation cancellation for " + reservation.getStartDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)) + " to " + reservation.getEndDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)));


        cancelReservationEmail.setText("Hi " + reservation.getUsername() + ", \n\nNo worries in your cancellation. At CGRP we take our customer's priorities seriously and look forward to earning your business in the future! Below is a confirmation of the reservation you cancelled: \n\n"
        + "Year: " + reservation.getVehicle().getYear() + "\nMake: " + reservation.getVehicle().getMake() + "\nModel: " + reservation.getVehicle().getModel() +
                 "\nReservation Duration: " + reservation.getStartDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)) + " - " + reservation.getEndDate().format(DateTimeFormatter.ofLocalizedDate((FormatStyle.FULL))));

        mailSender.send(cancelReservationEmail);

    }

    //sends email confirming when a reservation has been modified
    public void modifiedReservationVerificationEmail(LocalDate oldStartDate, LocalDate oldEndDate, Reservation modifiedReservation)
    {
        SimpleMailMessage modifiedReservationEmail = new SimpleMailMessage();
        User user = userService.getUserByUsername(modifiedReservation.getUsername());

        modifiedReservationEmail.setFrom("cgrpventures@gmail.com");
        modifiedReservationEmail.setTo(user.getEmail());

        modifiedReservationEmail.setSubject("Confirming your reservation modification for the " + modifiedReservation.getVehicle().getYear() + " " + modifiedReservation.getVehicle().getMake() + " " + modifiedReservation.getVehicle().getModel());

        modifiedReservationEmail.setText("Hi " + modifiedReservation.getUsername() + ", \n\nBelow is a confirmation of your modified reservation: \n\n"
                                + "Previous Reservation Duration: " + oldStartDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)) + " - " + oldEndDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)) +
                                "\nNewly Modified Reservation Duration: " + modifiedReservation.getStartDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)) + " - " + modifiedReservation.getEndDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)));

        mailSender.send(modifiedReservationEmail);

    }

    public void checkBackInVerificationEmail(Reservation checkBackReservation)
    {
        SimpleMailMessage checkBackInEmail = new SimpleMailMessage();
        User user = userService.getUserByUsername(checkBackReservation.getUsername());

        checkBackInEmail.setFrom("cgrpventures@gmail.com");
        checkBackInEmail.setTo(user.getEmail());
        checkBackInEmail.setSubject("Confirming your check back in for the " + checkBackReservation.getVehicle().getYear() + " " + checkBackReservation.getVehicle().getMake() + " " +  checkBackReservation.getVehicle().getModel());
        checkBackInEmail.setText("Hi " + checkBackReservation.getUsername() + ", \n\nThank you for your business, we truly appreciate it! This is a confirmation that the "
         + checkBackReservation.getVehicle().getYear() + " " + checkBackReservation.getVehicle().getMake() + " " + checkBackReservation.getVehicle().getModel() + " from " + checkBackReservation.getStartDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)) + " to " + checkBackReservation.getEndDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)) +
         " has been checked back in on our end! We hope to serve your needs again soon!");

        mailSender.send(checkBackInEmail);

    }

}
