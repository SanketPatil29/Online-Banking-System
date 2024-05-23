package com.notification.Service.Impl;

import com.notification.DTO.NotificationDto;
import com.notification.Entities.Notification;
import com.notification.Repository.NotificationRepository;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class NotificationServiceImplTest {

    @Mock
    private NotificationRepository notificationRepository;

    @Mock
    private JavaMailSender javaMailSender;

    @InjectMocks
    private NotificationServiceImpl notificationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void sendNotification() {
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setRecipientEmail("test@example.com");
        notificationDto.setSubject("Test Subject");
        notificationDto.setMessage("Test Message");

        // Mocking the MimeMessage and its helper
        MimeMessage mimeMessage = mock(MimeMessage.class);
        MimeMessageHelper mimeMessageHelper = mock(MimeMessageHelper.class);

        when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);

        doNothing().when(javaMailSender).send(any(MimeMessage.class));
        when(notificationRepository.save(any(Notification.class))).thenReturn(new Notification());

        notificationService.sendNotification(notificationDto);

        verify(notificationRepository, times(1)).save(any(Notification.class));
        verify(javaMailSender, times(1)).send(any(MimeMessage.class));
    }

    @Test
    void getAllNotifications() {
        Notification notification1 = new Notification();
        notification1.setRecipientEmail("test1@example.com");
        notification1.setSubject("Subject 1");
        notification1.setMessage("Message 1");

        Notification notification2 = new Notification();
        notification2.setRecipientEmail("test2@example.com");
        notification2.setSubject("Subject 2");
        notification2.setMessage("Message 2");

        when(notificationRepository.findAll()).thenReturn(Arrays.asList(notification1, notification2));

        List<NotificationDto> notifications = notificationService.getAllNotifications();

        assertEquals(2, notifications.size());
        verify(notificationRepository, times(1)).findAll();
    }
}
