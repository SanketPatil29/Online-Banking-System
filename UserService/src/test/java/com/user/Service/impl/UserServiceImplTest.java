//package com.user.Service.impl;
//
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class UserServiceImplTest {
//
//    @Test
//    void authenticateUser() {
//    }
//}


package com.user.Service.impl;

import com.user.Entities.User;
import com.user.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Test
    void authenticateUser_ValidCredentials_ReturnsTrue() {
        // Arrange
        UserServiceImpl userService = new UserServiceImpl(userRepository);
        String username = "testUser";
        String password = "testPassword";
        String role = "testRole";
        User mockUser = new User(username, password, role);
        when(userRepository.findByUsername(username)).thenReturn(mockUser);

        // Act
        boolean isAuthenticated = userService.authenticateUser(username, password, role);

        // Assert
        assertTrue(isAuthenticated, "Authentication should succeed with valid credentials");
    }

    @Test
    void authenticateUser_InvalidUsername_ReturnsFalse() {
        // Arrange
        UserServiceImpl userService = new UserServiceImpl(userRepository);
        String username = "invalidUser";
        String password = "testPassword";
        String role = "testRole";
        when(userRepository.findByUsername(username)).thenReturn(null);

        // Act
        boolean isAuthenticated = userService.authenticateUser(username, password, role);

        // Assert
        assertFalse(isAuthenticated, "Authentication should fail with invalid username");
    }

    @Test
    void authenticateUser_InvalidPassword_ReturnsFalse() {
        // Arrange
        UserServiceImpl userService = new UserServiceImpl(userRepository);
        String username = "testUser";
        String password = "invalidPassword";
        String role = "testRole";
        User mockUser = new User(username, "testPassword", role); // Correct password is "testPassword"
        when(userRepository.findByUsername(username)).thenReturn(mockUser);

        // Act
        boolean isAuthenticated = userService.authenticateUser(username, password, role);

        // Assert
        assertFalse(isAuthenticated, "Authentication should fail with invalid password");
    }

    @Test
    void authenticateUser_InvalidRole_ReturnsFalse() {
        // Arrange
        UserServiceImpl userService = new UserServiceImpl(userRepository);
        String username = "testUser";
        String password = "testPassword";
        String role = "invalidRole"; // Role doesn't match
        User mockUser = new User(username, password, "testRole");
        when(userRepository.findByUsername(username)).thenReturn(mockUser);

        // Act
        boolean isAuthenticated = userService.authenticateUser(username, password, role);

        // Assert
        assertFalse(isAuthenticated, "Authentication should fail with invalid role");
    }
}
