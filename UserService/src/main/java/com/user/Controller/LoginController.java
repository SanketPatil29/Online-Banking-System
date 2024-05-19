package com.user.Controller;

import com.user.DTO.LoginDTO;
import com.user.Repository.CustomerRepository;
import com.user.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


//@CrossOrigin("http://192.168.18.214:3000")

@RestController
@RequestMapping("/auth")

public class LoginController {

    private UserService userService;
    private CustomerRepository customerRepository;

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

        boolean isAuthenticated = userService.authenticateUser(username, password, role);
        if (isAuthenticated) {
            Long customerId = customerRepository.findCustomerIdByUsername(username);
            if (customerId != null) {
                return ResponseEntity.ok(customerId);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to retrieve customer ID");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username, password, or role");
        }
    }
}