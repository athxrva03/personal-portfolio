package com.atharva.portfolio_backend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmailService {

    @Value("${brevo.api.key}")
    private String brevoApiKey;

    @Value("${MAIL_USERNAME}")
    private String fromEmail;

    public void send(ContactRequest request) {
        String url = "https://api.brevo.com/v3/smtp/email";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("api-key", brevoApiKey);

        // 1. Admin Notification Email (Tujhe jo message aayega)
        try {
            Map<String, Object> adminBody = new HashMap<>();

            // Admin ke liye Sender (Yahan Brevo ki verified/login email daalna, Gmail nahi)
            Map<String, String> adminSender = new HashMap<>();
            adminSender.put("name", "Portfolio Contact System");
            adminSender.put("email", fromEmail); // Ya Brevo dashboard wali verified email
            adminBody.put("sender", adminSender);

            // Reply-To mein visitor ka email taaki tu seedha reply kar sake
            Map<String, String> replyTo = new HashMap<>();
            replyTo.put("email", request.getEmail());
            replyTo.put("name", request.getName());
            adminBody.put("replyTo", replyTo);

            Map<String, String> recipient = new HashMap<>();
            recipient.put("email", fromEmail); // Teri khud ki email jahan notification chahiye
            adminBody.put("to", List.of(recipient));

            adminBody.put("subject", "Portfolio : Enquiry from " + request.getName());

            String adminText = "Name : " + request.getName() +
                    "\nEmail : " + request.getEmail() +
                    "\nNumber : " + request.getNumber() +
                    "\n\nMessage:\n" + request.getMessage();
            adminBody.put("textContent", adminText);

            HttpEntity<Map<String, Object>> adminRequest = new HttpEntity<>(adminBody, headers);
            restTemplate.postForEntity(url, adminRequest, String.class);

        } catch (Exception e) {
            System.err.println("Failed to send admin notification: " + e.getMessage());
            e.printStackTrace();
        }

        // 2. Auto-Reply Thank You Mail (User ko jo jayegi)
        try {
            Map<String, Object> userBody = new HashMap<>();

            Map<String, String> userSender = new HashMap<>();
            userSender.put("name", "Atharva Rathore");
            userSender.put("email", fromEmail);
            userBody.put("sender", userSender);

            Map<String, String> recipient = new HashMap<>();
            recipient.put("email", request.getEmail()); // User ki email
            userBody.put("to", List.of(recipient));

            userBody.put("subject", "Thanks for reaching out! 🚀 - Atharva Rathore");

            String userText = "Hi " + request.getName() + ",\n\n" +
                    "Thank you for getting in touch through my portfolio website! I have received your message and will get back to you as soon as possible.\n\n" +
                    "Best regards,\n" +
                    "Atharva Rathore\n" +
                    "Software Developer";
            userBody.put("textContent", userText);

            HttpEntity<Map<String, Object>> userRequest = new HttpEntity<>(userBody, headers);
            restTemplate.postForEntity(url, userRequest, String.class);

        } catch (Exception e) {
            System.err.println("Failed to send auto-reply to user: " + e.getMessage());
            e.printStackTrace();
        }
    }
}