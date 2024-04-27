package com.user.Controller;
import com.user.DTO.UserInfoDto;
import com.user.Entities.Customer;
import com.user.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private CustomerService customerService;
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // ADD CUSTOMER REST API
    @PostMapping
    public ResponseEntity<Customer> addCustomer(@RequestBody UserInfoDto userInfoDto){
        return new ResponseEntity<>(customerService.add(userInfoDto),HttpStatus.CREATED);
    }

    // GET CUSTOMER REST API
    @GetMapping("/{c_id}")
    public Customer get(@PathVariable Long c_id){
        return customerService.get(c_id);
    }


}
