package com.user.Controller;

import com.user.DTO.LoginDTO;
import com.user.Repository.CustomerRepository;
import com.user.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/auth")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    private final UserService userService;
    private final CustomerRepository customerRepository;

    @Autowired
    public LoginController(UserService userService, CustomerRepository customerRepository) {
        this.userService = userService;
        this.customerRepository = customerRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();
        String role = loginDTO.getRole();

        logger.info("Login attempt for user: {}", username);

        boolean isAuthenticated = userService.authenticateUser(username, password, role);
        if (isAuthenticated) {
            logger.info("User authenticated successfully: {}", username);
            Long customerId = customerRepository.findCustomerIdByUsername(username);
            if (customerId != null) {
                logger.info("Customer ID retrieved successfully for user: {}", username);
                return ResponseEntity.ok(customerId);
            } else {
                logger.error("Failed to retrieve customer ID for user: {}", username);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to retrieve customer ID");
            }
        } else {
            logger.warn("Authentication failed for user: {}", username);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username, password, or role");
        }
    }
}