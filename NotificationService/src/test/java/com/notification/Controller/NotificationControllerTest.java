package com.notification.Controller;

import com.notification.DTO.NotificationDto;
import com.notification.Service.NotificationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NotificationController.class)
class NotificationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotificationService notificationService;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void sendNotification() throws Exception {
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setRecipientEmail("test@example.com");
        notificationDto.setSubject("Test Subject");
        notificationDto.setMessage("Test Message");

        doNothing().when(notificationService).sendNotification(any(NotificationDto.class));

        mockMvc.perform(post("/api/notifications/send")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(notificationDto)))
                .andExpect(status().isOk())
                .andExpect(content().string("Notification sent successfully"));

        verify(notificationService, times(1)).sendNotification(any(NotificationDto.class));
    }

    @Test
    void getAllNotifications() throws Exception {
        NotificationDto notificationDto1 = new NotificationDto();
        notificationDto1.setRecipientEmail("test1@example.com");
        notificationDto1.setSubject("Subject 1");
        notificationDto1.setMessage("Message 1");

        NotificationDto notificationDto2 = new NotificationDto();
        notificationDto2.setRecipientEmail("test2@example.com");
        notificationDto2.setSubject("Subject 2");
        notificationDto2.setMessage("Message 2");

        when(notificationService.getAllNotifications()).thenReturn(Arrays.asList(notificationDto1, notificationDto2));

        mockMvc.perform(get("/api/notifications/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Arrays.asList(notificationDto1, notificationDto2))));

        verify(notificationService, times(1)).getAllNotifications();
    }
}
