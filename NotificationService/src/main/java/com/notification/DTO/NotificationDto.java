package com.notification.DTO;

import lombok.Data;

@Data
public class NotificationDto {
    private String recipientEmail;
    private String subject;
    private String message;
}