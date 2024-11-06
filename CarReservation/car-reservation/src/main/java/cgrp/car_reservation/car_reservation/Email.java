package cgrp.car_reservation.car_reservation;

public class Email {

    private String emailReceiver;

    private String emailSubjectLine;

    private String emailBody;


    public Email(String emailReceiver, String emailSubjectLine, String emailBody) {
        this.emailReceiver = emailReceiver;
        this.emailSubjectLine = emailSubjectLine;
        this.emailBody = emailBody;
    }

    public String getEmailSubjectLine() {
        return emailSubjectLine;
    }

    public void setEmailSubjectLine(String emailSubjectLine) {
        this.emailSubjectLine = emailSubjectLine;
    }

    public String getEmailReceiver() {
        return emailReceiver;
    }

    public void setEmailReceiver(String emailReceiver) {
        this.emailReceiver = emailReceiver;
    }

    public String getEmailBody() {
        return emailBody;
    }

    public void setEmailBody(String emailBody) {
        this.emailBody = emailBody;
    }
}
