package com.atharva.portfolio_backend;

import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Service
public class EmailService {

    private JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void send(ContactRequest request){

        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setTo("rathoreatharva.work@gmail.com");

        mail.setSubject("Portfolio : Enquiry for Atharva Rathore.");

        mail.setReplyTo(request.getEmail());

        mail.setText(
                "Name : " + request.getName() +
                "\nEmail : " + request.getEmail() +
                "\nNumber : " + request.getNumber() +
                "\n\nMessage:\n" +
                request.getMessage()
        );

        mailSender.send(mail);

    }

}