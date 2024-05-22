package com.user.Controller;

import com.user.DTO.UserInfoDto;
import com.user.Entities.Customer;
import com.user.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // ADD CUSTOMER REST API
    @PostMapping
    public ResponseEntity<Customer> addCustomer(@RequestBody UserInfoDto userInfoDto) {
        logger.info("Received request to add new customer: {}", userInfoDto);
        try {
            Customer addedCustomer = customerService.add(userInfoDto);
            logger.info("Customer added successfully with ID: {}", addedCustomer.getCustomer_id());
            return ResponseEntity.status(HttpStatus.CREATED).body(addedCustomer);
        } catch (Exception e) {
            logger.error("Error occurred while adding customer: {}", userInfoDto, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // GET ALL CUSTOMERS REST API
    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        logger.info("Received request to get all customers");
        try {
            List<Customer> customers = customerService.getAllCustomers();
            logger.info("Retrieved {} customers", customers.size());
            return ResponseEntity.ok(customers);
        } catch (Exception e) {
            logger.error("Error occurred while retrieving all customers", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // GET CUSTOMER REST API
    @GetMapping("/{c_id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("c_id") Long customerId) {
        logger.info("Received request to get customer with ID: {}", customerId);
        try {
            Customer customer = customerService.getCustomerById(customerId);
            if (customer != null) {
                logger.info("Customer retrieved successfully with ID: {}", customerId);
                return ResponseEntity.ok(customer);
            } else {
                logger.warn("Customer not found with ID: {}", customerId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            logger.error("Error occurred while retrieving customer with ID: {}", customerId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
