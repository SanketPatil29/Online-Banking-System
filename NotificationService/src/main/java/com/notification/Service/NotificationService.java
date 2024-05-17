package com.notification.Service;



import com.notification.DTO.NotificationDto;

import java.util.List;

public interface NotificationService {
    void sendNotification(NotificationDto notificationDto);
    List<NotificationDto> getAllNotifications();
}