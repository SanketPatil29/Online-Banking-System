package com.notification.Service.Impl;


import com.notification.DTO.NotificationDto;
import com.notification.Entities.Notification;
import com.notification.mapper.NotificationMapper;
import com.notification.Repository.NotificationRepository;
import com.notification.Service.NotificationService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final JavaMailSender javaMailSender;

    @Autowired
    public NotificationServiceImpl(NotificationRepository notificationRepository, JavaMailSender javaMailSender) {
        this.notificationRepository = notificationRepository;
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendNotification(NotificationDto notificationDto) {
        Notification notification = NotificationMapper.dtoToNotification(notificationDto);
        notification.setStatus("SENT"); // Set status
        // Set timestamp if needed
        notificationRepository.save(notification);
        System.out.println("Before Sending mail in SendNotification");
        // Send email
        sendEmail(notificationDto);
    }

    private void sendEmail(NotificationDto notificationDto) {
        System.out.println("Before sending actual mail");
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setTo(notificationDto.getRecipientEmail());
            helper.setSubject(notificationDto.getSubject());
            helper.setText(notificationDto.getMessage());

            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            // Handle exception
        }
    }

    @Override
    public List<NotificationDto> getAllNotifications() {
        List<Notification> notifications = notificationRepository.findAll();
        return notifications.stream()
                .map(notification -> NotificationMapper.notificationToDto(notification))
                .collect(Collectors.toList());
    }
}