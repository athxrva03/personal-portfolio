package com.atharva.portfolio_backend;

import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void send(ContactRequest request){

        // 1. Existing logic: Tujhe notification bhejne ke liye
        SimpleMailMessage adminMail = new SimpleMailMessage();
        adminMail.setTo("rathoreatharva.work@gmail.com");
        adminMail.setSubject("Portfolio : Enquiry for Atharva Rathore.");
        adminMail.setReplyTo(request.getEmail());

        adminMail.setText(
                "Name : " + request.getName() +
                "\nEmail : " + request.getEmail() +
                "\nNumber : " + request.getNumber() +
                "\n\nMessage:\n" +
                request.getMessage()
        );

        mailSender.send(adminMail);

        // 2. NEW LOGIC: User ko auto-generated Thank You mail bhejne ke liye
        SimpleMailMessage autoReply = new SimpleMailMessage();
        autoReply.setTo(request.getEmail());
        autoReply.setSubject("Thanks for reaching out! 🚀 - Atharva Rathore");
        
        autoReply.setText(
                "Hi " + request.getName() + ",\n\n" +
                "Thank you for getting in touch through my portfolio website! I have received your message and will get back to you as soon as possible.\n\n" +
                "Best regards,\n" +
                "Atharva Rathore\n" +
                "Software Developer"
        );

        mailSender.send(autoReply);
    }
}