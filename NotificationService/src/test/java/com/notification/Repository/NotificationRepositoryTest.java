package com.notification.Repository;

import com.notification.Entities.Notification;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class NotificationRepositoryTest {

    @Autowired
    private NotificationRepository notificationRepository;

    @Test
    void testSaveAndFindAll() {
        Notification notification = new Notification();
        notification.setRecipientEmail("test@example.com");
        notification.setSubject("Test Subject");
        notification.setMessage("Test Message");
        notification.setStatus("SENT");

        notificationRepository.save(notification);

        List<Notification> notifications = notificationRepository.findAll();
        assertFalse(notifications.isEmpty());
        assertEquals(1, notifications.size());
        assertEquals("test@example.com", notifications.get(0).getRecipientEmail());
    }
}
