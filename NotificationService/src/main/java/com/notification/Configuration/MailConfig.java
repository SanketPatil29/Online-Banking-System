package com.notification.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        // Configure mail sender properties
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587); // or your port number
        mailSender.setUsername("manosarthihealthscheme@gmail.com");
        mailSender.setPassword("ehch cfbv jtwd mnrf");

        // Additional properties can be set here
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true"); // Enable debug mode for troubleshooting

        return mailSender;
    }
}