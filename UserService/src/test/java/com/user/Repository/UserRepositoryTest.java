package com.user.Repository;

import com.user.Entities.User;
import com.user.Repository.UserRepository;
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
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void findByUsername_ExistingUser_ReturnsUser() {
        // Arrange
        User user = new User("testUser", "testPassword", "testRole");
        userRepository.save(user);

        // Act
        User foundUser = userRepository.findByUsername("testUser");

        // Assert
        assertEquals("testUser", foundUser.getUsername());
        assertEquals("testPassword", foundUser.getPassword());
        assertEquals("testRole", foundUser.getRole());
    }

    @Test
    void findByUsername_NonExistingUser_ReturnsNull() {
        // Arrange - No need to arrange anything

        // Act
        User foundUser = userRepository.findByUsername("nonExistingUser");

        // Assert
        assertNull(foundUser);
    }

    @Test
    void findAll_ReturnsAllUsers() {
        // Arrange
        User user1 = new User("user1", "password1", "role1");
        User user2 = new User("user2", "password2", "role2");
        userRepository.saveAll(List.of(user1, user2));

        // Act
        List<User> allUsers = userRepository.findAll();

        // Assert
        assertFalse(allUsers.isEmpty());
//        assertEquals(2, allUsers.size());
    }
}
