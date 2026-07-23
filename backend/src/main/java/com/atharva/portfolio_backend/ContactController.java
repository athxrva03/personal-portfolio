package com.atharva.portfolio_backend;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {
    "http://127.0.0.1:5500",
    "https://athxrva03.github.io"
})
public class ContactController {

    @Value("${MAIL_USERNAME}")
    private String username;

    private final EmailService emailService;

    public ContactController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/test")
    public String test() {
        return username;
    }

    @PostMapping("/contact")
    public String send(@RequestBody ContactRequest request){
        emailService.send(request);
        return "Message Sent Successfully";
    }
}
