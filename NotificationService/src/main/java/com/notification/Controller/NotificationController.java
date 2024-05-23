
package com.notification.Controller;

import com.notification.DTO.NotificationDto;
import com.notification.Service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendNotification(@RequestBody NotificationDto notificationDto) {
        logger.info("Request received to send notification: {}", notificationDto);
        try {
            notificationService.sendNotification(notificationDto);
            logger.info("Notification sent successfully: {}", notificationDto);
            return ResponseEntity.ok("Notification sent successfully");
        } catch (Exception e) {
            logger.error("Error occurred while sending notification: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send notification");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<NotificationDto>> getAllNotifications() {
        logger.info("Request received to get all notifications");
        try {
            List<NotificationDto> notifications = notificationService.getAllNotifications();
            logger.info("Notifications retrieved successfully, total count: {}", notifications.size());
            return ResponseEntity.ok(notifications);
        } catch (Exception e) {
            logger.error("Error occurred while retrieving notifications: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
