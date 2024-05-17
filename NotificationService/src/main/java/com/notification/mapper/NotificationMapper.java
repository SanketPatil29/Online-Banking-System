package com.notification.mapper;


import com.notification.DTO.NotificationDto;
import com.notification.Entities.Notification;

public class NotificationMapper {

    public static Notification dtoToNotification(NotificationDto notificationDto) {
        Notification notification = new Notification();
        notification.setRecipientEmail(notificationDto.getRecipientEmail());
        notification.setSubject(notificationDto.getSubject());
        notification.setMessage(notificationDto.getMessage());
        notification.setStatus("SENT"); // Set status to "SENT" as default
        // Set timestamp if needed

        return notification;
    }

    public static NotificationDto notificationToDto(Notification notification) {
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setRecipientEmail(notification.getRecipientEmail());
        notificationDto.setSubject(notification.getSubject());
        notificationDto.setMessage(notification.getMessage());
        // You can map other fields if needed

        return notificationDto;
    }
}