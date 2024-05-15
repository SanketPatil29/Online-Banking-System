package com.user.Controller;

import com.user.DTO.UserInfoDto;
import com.user.Entities.Customer;
import com.user.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // ADD CUSTOMER REST API
    @PostMapping
    public ResponseEntity<Customer> addCustomer(@RequestBody UserInfoDto userInfoDto) {
        Customer addedCustomer = customerService.add(userInfoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedCustomer);
    }

    // GET ALL CUSTOMERS REST API
    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    // GET CUSTOMER REST API
    @GetMapping("/{c_id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("c_id") Long customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        return ResponseEntity.ok(customer);
    }
}
